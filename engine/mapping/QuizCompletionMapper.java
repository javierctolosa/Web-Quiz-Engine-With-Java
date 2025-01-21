package engine.mapping;

import engine.model.QuizCompletionDto;
import engine.repository.QuizCompletion;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuizCompletionMapper {
    @Mapping(target = "quizId", source = "quizDao.id")
    QuizCompletionDto quizCompletionToQuizCompletionDto(QuizCompletion quizCompletion);
}
