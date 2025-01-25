package com.kafka.UserService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_USER_TOPIC;



    private final KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message)
    {
//        kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC,message);

        for(int i=0;i<1000;i++)
        {
            kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC,""+i%2,message+i);

        }


        return ResponseEntity.ok("Message Queued");
    }

}
