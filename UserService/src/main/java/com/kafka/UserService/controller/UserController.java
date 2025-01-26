package com.kafka.UserService.controller;

import com.kafka.UserService.dto.CreateUserRequestDto;
import com.kafka.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_USER_TOPIC;

    private  final UserService userService;



    private final KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto);
        return ResponseEntity.ok("User is created");
    }

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
