package com.thi.ForumHubApi.domain.answer;

import com.thi.ForumHubApi.domain.topic.ListTopicData;
import com.thi.ForumHubApi.domain.topic.Topic;

import java.util.List;

public record TopicWithAnswers(
        ListTopicData topic,
        List<ListAnswersData> answers
) {
}
