package com.thi.ForumHubApi.topic;

import com.thi.ForumHubApi.course.Course;
import com.thi.ForumHubApi.user.User;

import java.time.LocalDateTime;

public record ListTopicData(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        String course
) {

    public ListTopicData(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getCourse().getName());
    }
}
