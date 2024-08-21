package com.thi.ForumHubApi.domain.answer;

import jakarta.validation.constraints.NotNull;

public record UpdateAnswerData(
        @NotNull
        Long id,
        String message
) {
}
