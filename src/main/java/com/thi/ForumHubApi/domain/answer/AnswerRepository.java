package com.thi.ForumHubApi.domain.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    ListAnswersData findAllById(Long topicId);

    List<ListAnswersData> findByTopicId(Long topicId);
}
