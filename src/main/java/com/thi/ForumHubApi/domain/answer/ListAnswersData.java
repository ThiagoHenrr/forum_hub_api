package com.thi.ForumHubApi.domain.answer;

public record ListAnswersData(
        Long id,
        String message,
        String author
) {

    public ListAnswersData(Answer answer){
        this(answer.getId(),answer.getMessage(), answer.getAuthor().getName());
    }
}
