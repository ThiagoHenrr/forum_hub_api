package com.thi.ForumHubApi.controller;

import com.thi.ForumHubApi.domain.topic.*;
import com.thi.ForumHubApi.infra.security.SecurityFilter;
import com.thi.ForumHubApi.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("topic")
public class TopicController {

    @Autowired
    private TopicRepository repository;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SecurityFilter securityFilter;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid TopicData data, UriComponentsBuilder uriBuilder){

        Optional<Topic> optionalTopicTitle = repository.findByTitle(data.title());
        Optional<Topic> optionalTopicMessage = repository.findByMessage(data.message());

//        //Do it better with exceptions(?)
//        if(optionalTopicTitle.isPresent() && optionalTopicMessage.isEmpty()){
//            repository.save(new Topic(data));
//        } else if(optionalTopicTitle.isEmpty() && optionalTopicMessage.isPresent() ){
//            repository.save(new Topic(data));
//        }
        Topic topic = new Topic(data);
        repository.save(topic);

        URI uri = uriBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsData(topic));
    }

    @GetMapping()
    public ResponseEntity<Page<ListTopicData>> listAll(@PageableDefault(size = 3, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable){
        Page<ListTopicData> page = repository.findAll(pageable).map(ListTopicData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateTopicData data){
        Topic topic = repository.getReferenceById(data.id());
        topic.updateTopic(data);

        return ResponseEntity.ok(new TopicDetailsData(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Optional<Topic> topicId = repository.findById(id);

        if(topicId.isPresent()) {
            repository.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        Topic topic = repository.getReferenceById(id);
        return ResponseEntity.ok(new TopicDetailsData(topic));
    }
}
