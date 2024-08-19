package com.thi.ForumHubApi.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerData(
        @NotBlank
        String message,
        @NotNull
        Long id
) {
}
