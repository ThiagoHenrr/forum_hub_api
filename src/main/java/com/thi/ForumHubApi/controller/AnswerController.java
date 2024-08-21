package com.thi.ForumHubApi.controller;

import com.thi.ForumHubApi.domain.answer.*;
import com.thi.ForumHubApi.domain.topic.Topic;
import com.thi.ForumHubApi.domain.topic.TopicRepository;
import com.thi.ForumHubApi.domain.user.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic/{topicId}/answer")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@PathVariable Long topicId, @RequestBody @Valid AnswerData data, UriComponentsBuilder uriBuilder){

        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(optionalTopic.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        User user = (User) authentication.getPrincipal();
        Answer answer = new Answer(data, optionalTopic.get(), user);

        answerRepository.save(answer);

        URI uri = uriBuilder.path("/topic/{topicId}/answer/{id}").buildAndExpand(optionalTopic.get().getId()).toUri();
        return ResponseEntity.created(uri).body(new AnswerDetailsData(answer));
    }

    @GetMapping
    public ResponseEntity listAnswers(@PathVariable Long topicId){
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if(optionalTopic.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<ListAnswersData> ans = answerRepository.findByTopicId(topicId);

        return ResponseEntity.ok(ans);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@PathVariable Long topicId, @RequestBody @Valid UpdateAnswerData data){
        Answer ans = answerRepository.getReferenceById(data.id());
        if(!comparingUsers(ans)){
            return ResponseEntity.notFound().build();
        }
        ans.updateAnswer(data);

        return ResponseEntity.ok(new AnswerDetailsData(ans));
    }

    private boolean comparingUsers(Answer answer){
        User author = answer.getAuthor();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return author.equals(currentUser);
    }

}
