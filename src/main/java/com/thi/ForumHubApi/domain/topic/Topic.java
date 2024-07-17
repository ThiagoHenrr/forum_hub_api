package com.thi.ForumHubApi.domain.topic;


import com.thi.ForumHubApi.domain.answer.Answer;
import com.thi.ForumHubApi.domain.course.Course;
import com.thi.ForumHubApi.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topic")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", updatable = false)
    private final LocalDateTime creationDate = LocalDateTime.now();
    private String status;
    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    public Topic(TopicData data){
        this.title = data.title();
        this.message = data.message();
        this.course = new Course(data.course());
    }

    public void updateTopic(UpdateTopicData data) {
        if(data.title() != null){
            this.title = data.title();
        }
        if(data.message() != null){
            this.message = data.message();
        }
    }
}
