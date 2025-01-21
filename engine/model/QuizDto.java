package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.util.List;


public record QuizDto(Integer id,
                      @NotBlank(message = "The 'title' field must not be blank.") String title,
                      @NotBlank(message = "The 'text' field must not be blank.") String text,
                      @NotNull(message = "The 'options' field must not be null.")
                      @Size(min=2, message = "The 'options' field must be a list with at least 2 elements.")
                      List<String> options,
                      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                      List<Integer> answer) { }
