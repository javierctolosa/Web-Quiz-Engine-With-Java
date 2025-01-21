package engine.mapping;

import engine.model.QuizResultDto;
import engine.repository.QuizResultDao;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T21:07:45-0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (JetBrains s.r.o.)"
)
@Component
public class QuizResultMapperImpl implements QuizResultMapper {

    @Override
    public QuizResultDao quizResultDtoToQuizResultDao(QuizResultDto quizResultDto) {
        if ( quizResultDto == null ) {
            return null;
        }

        QuizResultDao quizResultDao = new QuizResultDao();

        quizResultDao.setSuccess( quizResultDto.success() );
        quizResultDao.setFeedback( quizResultDto.feedback() );

        return quizResultDao;
    }

    @Override
    public QuizResultDto quizResultDaoToQuizResultDto(QuizResultDao quizResultDao) {
        if ( quizResultDao == null ) {
            return null;
        }

        boolean success = false;
        String feedback = null;

        success = quizResultDao.isSuccess();
        feedback = quizResultDao.getFeedback();

        QuizResultDto quizResultDto = new QuizResultDto( success, feedback );

        return quizResultDto;
    }
}
