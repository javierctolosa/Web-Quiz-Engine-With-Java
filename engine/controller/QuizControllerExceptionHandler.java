package engine.controller;

import engine.model.QuizResultDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class QuizControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders httpHeaders,
                                                                  @NonNull HttpStatusCode httpStatusCode,
                                                                  @NonNull WebRequest request) {
        Map<String, Object> responseExceptionBody = new LinkedHashMap<>();
        responseExceptionBody.put("timestamp", LocalDateTime.now());
        responseExceptionBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseExceptionBody.put("exception", ex.getClass());
        responseExceptionBody.put("description", ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList().get(0));

        return ResponseEntity.badRequest().body(responseExceptionBody);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<QuizResultDto> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new QuizResultDto(false, ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, Object> responseExceptionBody = new LinkedHashMap<>();
        responseExceptionBody.put("timestamp", LocalDateTime.now());
        responseExceptionBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseExceptionBody.put("exception", ex.getClass());
        responseExceptionBody.put("description", ex.getMessage().replace("postAnswer.arg0: ", ""));
        return ResponseEntity.badRequest().body(responseExceptionBody);
    }

}
