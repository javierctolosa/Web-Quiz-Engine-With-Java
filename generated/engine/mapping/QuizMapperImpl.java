package engine.mapping;

import engine.model.QuizDto;
import engine.repository.QuizDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T21:07:45-0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (JetBrains s.r.o.)"
)
@Component
public class QuizMapperImpl implements QuizMapper {

    @Override
    public QuizDao quizDtoToQuizDao(QuizDto quizDto) {
        if ( quizDto == null ) {
            return null;
        }

        QuizDao quizDao = new QuizDao();

        quizDao.setId( quizDto.id() );
        quizDao.setTitle( quizDto.title() );
        quizDao.setText( quizDto.text() );
        List<String> list = quizDto.options();
        if ( list != null ) {
            quizDao.setOptions( new ArrayList<String>( list ) );
        }
        List<Integer> list1 = quizDto.answer();
        if ( list1 != null ) {
            quizDao.setAnswer( new ArrayList<Integer>( list1 ) );
        }

        return quizDao;
    }

    @Override
    public QuizDto quizDaoToQuizDto(QuizDao quizDao) {
        if ( quizDao == null ) {
            return null;
        }

        Integer id = null;
        String title = null;
        String text = null;
        List<String> options = null;
        List<Integer> answer = null;

        id = quizDao.getId();
        title = quizDao.getTitle();
        text = quizDao.getText();
        List<String> list = quizDao.getOptions();
        if ( list != null ) {
            options = new ArrayList<String>( list );
        }
        List<Integer> list1 = quizDao.getAnswer();
        if ( list1 != null ) {
            answer = new ArrayList<Integer>( list1 );
        }

        QuizDto quizDto = new QuizDto( id, title, text, options, answer );

        return quizDto;
    }
}
