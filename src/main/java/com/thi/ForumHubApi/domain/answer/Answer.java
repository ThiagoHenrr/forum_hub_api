package com.thi.ForumHubApi.domain.answer;

import com.thi.ForumHubApi.domain.user.Profile;
import com.thi.ForumHubApi.domain.topic.Topic;
import com.thi.ForumHubApi.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Table(name = "answer")
@Entity(name = "Answer")
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private final LocalDateTime date = LocalDateTime.now();
    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private String solution;

    public Answer(){}

    public Answer(AnswerData data, Topic topic, User author){
        this.message = data.message();
        this.topic = topic;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Answer{" + "\n" +
                "id=" + id +  "\n" +
                ", message='" + message + '\'' +  "\n" +
                ", topic=" + topic.getId() +  "\n" +
                ", date=" + date +  "\n" +
                ", author=" + author +  "\n" +
                ", solution='" + solution + '\'' +  "\n" +
                '}';
    }
}
