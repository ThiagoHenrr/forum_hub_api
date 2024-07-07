package com.thi.ForumHubApi.controller;

import com.thi.ForumHubApi.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topic")
public class TopicController {

    @Autowired
    private TopicRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid TopicData data){

        Optional<Topic> optionalTopicTitle = repository.findByTitle(data.title());
        Optional<Topic> optionalTopicMessage = repository.findByMessage(data.message());

        //Do it better with exceptions(?)
        if(optionalTopicTitle.isPresent() && optionalTopicMessage.isEmpty()){
            repository.save(new Topic(data));
        } else if(optionalTopicTitle.isEmpty() && optionalTopicMessage.isPresent() ){
            repository.save(new Topic(data));
        }

    }

    @GetMapping
    public Page<ListTopicData> listAll(@PageableDefault(size = 3, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable){
        return repository.findAll(pageable).map(ListTopicData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid updateTopicData data){
        Topic topic = repository.getReferenceById(data.id());
        topic.updateTopic(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        Optional<Topic> topicId = repository.findById(id);

        if(topicId.isPresent()) {
            repository.deleteById(id);
        }
    }
}
