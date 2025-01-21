package engine.validation;

import engine.service.QuizService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class MaxListSizeValidator implements ConstraintValidator<MaxListSize, Integer> {

    private final QuizService quizService;

    public MaxListSizeValidator(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && quizService.existsById(value);
    }
}
