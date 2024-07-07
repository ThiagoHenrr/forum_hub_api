package com.thi.ForumHubApi.topic;


import com.thi.ForumHubApi.answer.Answer;
import com.thi.ForumHubApi.course.Course;
import com.thi.ForumHubApi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private LocalDateTime creationDate;
    private String status;
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

    @PrePersist
    protected void onCreate(){
        creationDate = LocalDateTime.now();
    }

}
