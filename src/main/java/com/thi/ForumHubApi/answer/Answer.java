package com.thi.ForumHubApi.answer;

import com.thi.ForumHubApi.topic.Topic;
import com.thi.ForumHubApi.user.Profile;
import jakarta.persistence.*;

import java.sql.Date;

@Table(name = "answer")
@Entity(name = "Answer")
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    private String solution;
}
