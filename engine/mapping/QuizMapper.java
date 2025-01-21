package engine.mapping;

import engine.model.QuizDto;
import engine.repository.QuizDao;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuizMapper {
    QuizDao quizDtoToQuizDao(QuizDto quizDto);
    QuizDto quizDaoToQuizDto(QuizDao quizDao);
}
