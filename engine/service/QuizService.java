package engine.service;

import engine.mapping.QuizCompletionMapper;
import engine.mapping.QuizMapper;
import engine.mapping.QuizResultMapper;
import engine.model.AnswerDto;
import engine.model.QuizCompletionDto;
import engine.model.QuizDto;
import engine.model.QuizResultDto;
import engine.repository.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class QuizService {
    private final QuizDaoRepository quizDaoRepository;
    private final QuizResultDaoRepository quizResultDaoRepository;
    private final QuizCompletionRepository quizCompletionRepository;
    final QuizMapper quizMapper;
    final QuizResultMapper quizResultMapper;
    final QuizCompletionMapper quizCompletionMapper;

    public QuizService(QuizDaoRepository quizDaoRepository,
                       QuizResultDaoRepository quizResultDaoRepository,
                       QuizCompletionRepository quizCompletionRepository,
                       QuizMapper quizMapper,
                       QuizResultMapper quizResultMapper,
                       QuizCompletionMapper quizCompletionMapper) {
        this.quizDaoRepository = quizDaoRepository;
        this.quizResultDaoRepository = quizResultDaoRepository;
        this.quizCompletionRepository = quizCompletionRepository;
        this.quizMapper = quizMapper;
        this.quizResultMapper = quizResultMapper;
        this.quizCompletionMapper = quizCompletionMapper;
    }

    @PostConstruct
    private void init() {
        if (!quizResultDaoRepository.existsById(1)) {
            quizResultDaoRepository.save(
                    quizResultMapper.quizResultDtoToQuizResultDao(
                            new QuizResultDto(true, "Congratulations, you're right")));
        }
        if (!quizResultDaoRepository.existsById(2)) {
            quizResultDaoRepository.save(
                    quizResultMapper.quizResultDtoToQuizResultDao(
                            new QuizResultDto(false, "Wrong answer! Please, try again.")));
        }
    }

    public QuizResultDto evaluateAnswer(int id, AnswerDto answer) {
        QuizDao quizDao = quizDaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz with ID:" + id + "not found."));
        if (new HashSet<>(quizDao.getAnswer())
                .equals(new HashSet<>(answer.answer()))) {
            return quizResultMapper.quizResultDaoToQuizResultDto(quizResultDaoRepository.findBySuccess(true,
                    Limit.of(1)));
        }
        return quizResultMapper.quizResultDaoToQuizResultDto(quizResultDaoRepository.findBySuccess(false,
                Limit.of(1)));
    }

    public void addQuizCompletion(int quizId, UserDao userDao) {
        QuizDao quizDao = quizDaoRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz with ID:" + quizId + "not found."));

        quizCompletionRepository.save(new QuizCompletion(quizDao, userDao, LocalDateTime.now()));
    }

//    public List<QuizDto> getQuizPage(int page) {
//        return StreamSupport.stream(quizDaoRepository.findAll(PageRequest.of(page, 10))
//                .spliterator(), true).map(quizMapper::quizDaoToQuizDto).toList();
//    }

    public Page<QuizDto> getQuizPage(int page) {
        return quizDaoRepository.findAll(PageRequest.of(page, 10)).map(quizMapper::quizDaoToQuizDto);
    }

    public Page<QuizCompletionDto> getQuizCompletionPage(int page, UserDao userDao) {
        return quizCompletionRepository.findByUserDao(userDao, PageRequest.of(page, 10,
                Sort.by("completedAt").descending()))
                .map(quizCompletionMapper::quizCompletionToQuizCompletionDto);

    }


    public QuizDto getQuizDtoById(int id) {
        return quizMapper.quizDaoToQuizDto(quizDaoRepository.findById(id).orElse(null));
    }

    public QuizDao getQuizDaoById(int id) {
        return quizDaoRepository.findById(id).orElse(null);
    }

    public boolean existsById(int id) {
        return quizDaoRepository.existsById(id);
    }

    public void deleteQuizById(int id) {
        quizDaoRepository.deleteById(id);
    }

    public QuizDto addQuiz(QuizDto quizDto, UserDao userDao) {
        QuizDao quizDao = quizMapper.quizDtoToQuizDao(quizDto);
        quizDao.setUser(userDao);
        QuizDao quizDaoSaved = quizDaoRepository.save(quizDao);
        return quizMapper.quizDaoToQuizDto(quizDaoSaved);
    }
}
