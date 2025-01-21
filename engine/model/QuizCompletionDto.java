package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record QuizCompletionDto(
        @JsonProperty(value = "id") int quizId,
        LocalDateTime completedAt) {
}
