package com.thi.ForumHubApi.domain.answer;

import com.thi.ForumHubApi.domain.topic.Topic;

import java.time.LocalDateTime;

public record AnswerDetailsData(
        Long id,
        LocalDateTime creationDate,
        String author,
        String Topic,
        String message
) {

    public AnswerDetailsData(Answer answer){
        this(answer.getId(), answer.getDate(), answer.getAuthor().getName(), answer.getTopic().getMessage(), answer.getMessage());
    }
}
