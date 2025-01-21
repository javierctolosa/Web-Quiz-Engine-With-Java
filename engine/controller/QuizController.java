package engine.controller;

import engine.model.*;
import engine.repository.QuizCompletion;
import engine.repository.QuizDao;
import engine.repository.UserDao;
import engine.service.QuizService;
import engine.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;

    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @GetMapping("/api/quizzes")
    public ResponseEntity<Page<QuizDto>> getPagedQuizList(@RequestParam("page") int page) {
        return ResponseEntity.ok(quizService.getQuizPage(page));
    }

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable("id") int id) {
        QuizDto bodyQuizDto = quizService.getQuizDtoById(id);
        if (bodyQuizDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bodyQuizDto);
    }

    @GetMapping("api/quizzes/completed")
    public ResponseEntity<Page<QuizCompletionDto>> geQuizCompletionForUser(@AuthenticationPrincipal UserDetails userDetails,
                                                                           @RequestParam("page") int page) {
        UserDao userDao = userService.getUserDaoByEmail(userDetails.getUsername());
        return ResponseEntity.ok(quizService.getQuizCompletionPage(page, userDao));
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<?> deleteQuizById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") int id) {
        UserDao userDao = userService.getUserDaoByEmail(userDetails.getUsername());
        QuizDao quizDao = quizService.getQuizDaoById(id);
        if (quizDao != null) {
            if (userDao.getId().equals(quizDao.getUser().getId())) {
                quizService.deleteQuizById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        if (userService.existsByEmail(userDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The email provided is already associated to an account.");
        }
        userService.saveUser(userDto);
        return ResponseEntity.ok(String.format("The user with email: %s was successfully created.", userDto.email()));
    }

    @PostMapping("/api/quizzes")
    public ResponseEntity<QuizDto> postQuiz(@AuthenticationPrincipal UserDetails userDetails,
                                            @Valid @RequestBody QuizDto quizDto) {
        UserDao userDao = userService.getUserDaoByEmail(userDetails.getUsername());
        return ResponseEntity.ok(quizService.addQuiz(quizDto, userDao));
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity<QuizResultDto> postAnswer(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id")
            @Min(value = 0, message = "The Quiz Id path variable must be zero or positive.")
            int id,
            @RequestBody AnswerDto answerDto) {
        if (!quizService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new QuizResultDto(false, "Quiz not found."));
        }
        QuizResultDto quizResultDto = quizService.evaluateAnswer(id, answerDto);
        if (quizResultDto.success()) {
            UserDao userDao = userService.getUserDaoByEmail(userDetails.getUsername());
            quizService.addQuizCompletion(id, userDao);
        }
        return ResponseEntity.ok(quizResultDto);
    }
}
