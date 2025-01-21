package engine.mapping;

import engine.model.QuizResultDto;
import engine.repository.QuizResultDao;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuizResultMapper {
    QuizResultDao quizResultDtoToQuizResultDao(QuizResultDto quizResultDto);
    QuizResultDto quizResultDaoToQuizResultDto(QuizResultDao quizResultDao);
}
