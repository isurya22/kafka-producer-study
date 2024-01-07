package com.suryakafka.controller;

import com.suryakafka.dto.Customer;
import com.suryakafka.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
       try{
           for(int i=0; i <= 10000; i++){
               publisher.sendMessageToTopic(message +" : "+ i);
           }
          // publisher.sendMessageToTopic(message);
           return ResponseEntity.ok("message published Sucessfully !!!");

       } catch(Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish")
    public void sendEvents(@RequestBody Customer customer){
        publisher.sendEventsToTopic(customer);
    }
}
