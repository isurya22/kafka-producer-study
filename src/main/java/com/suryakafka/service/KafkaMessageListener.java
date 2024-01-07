package com.suryakafka.service;

import com.suryakafka.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "maxit-demo10",groupId = "mi-group")
    public void consume(Customer customer){
        logger.info("consumer consume the events {}", customer.toString());
    }
//    @KafkaListener(topics = "maxit-demo-5",groupId = "mi-group2")
//    public void consume2(String message){
//        logger.info("consumer2 consume the message {}",message);
//    }
//    @KafkaListener(topics = "maxit-demo-5",groupId = "mi-group2")
//    public void consume3(String message){
//        logger.info("consumer3 consume the message {}",message);
//    }
//    @KafkaListener(topics = "maxit-demo-5",groupId = "mi-group2")
//    public void consume4(String message){
//        logger.info("consumer4 consume the message {}",message);
//    }
}
