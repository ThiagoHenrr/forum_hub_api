package com.thi.ForumHubApi.topic;

import com.thi.ForumHubApi.course.Course;
import com.thi.ForumHubApi.user.User;

import java.time.LocalDateTime;

public record TopicDetailsData(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        User author,
        Course course
) {

    public TopicDetailsData(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getAuthor(), topic.getCourse());
    }
}
