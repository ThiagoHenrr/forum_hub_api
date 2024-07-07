package com.thi.ForumHubApi.topic;

import jakarta.validation.constraints.NotNull;

public record updateTopicData(
        @NotNull
        Long id,
        String title,
        String message
) {
}
