package com.thi.ForumHubApi.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicData(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String course
) {
}
