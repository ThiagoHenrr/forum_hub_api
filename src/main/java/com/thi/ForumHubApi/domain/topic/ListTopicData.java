package com.thi.ForumHubApi.domain.topic;

import java.time.LocalDateTime;

public record ListTopicData(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String author,
        String status,
        String course
) {

    public ListTopicData(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getAuthor().getName(), topic.getStatus(), topic.getCourse().getName());
    }
}
