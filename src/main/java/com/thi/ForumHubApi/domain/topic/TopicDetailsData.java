package com.thi.ForumHubApi.domain.topic;

import com.thi.ForumHubApi.domain.course.Course;
import com.thi.ForumHubApi.domain.user.User;

import java.time.LocalDateTime;

public record TopicDetailsData(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        String author,
        Course course
) {

    public TopicDetailsData(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getAuthor().getName(), topic.getCourse());
    }
}
