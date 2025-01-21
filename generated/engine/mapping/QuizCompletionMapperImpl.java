package engine.mapping;

import engine.model.QuizCompletionDto;
import engine.repository.QuizCompletion;
import engine.repository.QuizDao;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T22:38:08-0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (JetBrains s.r.o.)"
)
@Component
public class QuizCompletionMapperImpl implements QuizCompletionMapper {

    @Override
    public QuizCompletionDto quizCompletionToQuizCompletionDto(QuizCompletion quizCompletion) {
        if ( quizCompletion == null ) {
            return null;
        }

        int quizId = 0;
        LocalDateTime completedAt = null;

        Integer id = quizCompletionQuizDaoId( quizCompletion );
        if ( id != null ) {
            quizId = id;
        }
        completedAt = quizCompletion.getCompletedAt();

        QuizCompletionDto quizCompletionDto = new QuizCompletionDto( quizId, completedAt );

        return quizCompletionDto;
    }

    private Integer quizCompletionQuizDaoId(QuizCompletion quizCompletion) {
        QuizDao quizDao = quizCompletion.getQuizDao();
        if ( quizDao == null ) {
            return null;
        }
        return quizDao.getId();
    }
}
