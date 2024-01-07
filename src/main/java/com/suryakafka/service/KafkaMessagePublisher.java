package com.suryakafka.service;

import com.suryakafka.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("maxit-demo-1", message);
        future.whenComplete((result,ex)->{
            if(ex == null){
                System.out.println("Send Message=[" + message + "] with offset=[" +result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +message+ "] due to : "+ex.getMessage());
            }
        });
    }

    public void sendEventsToTopic(Customer customer){
        try{
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("maxit-demo", customer);
            future.whenComplete((result,ex)->{
                if(ex == null){
                    System.out.println("Send Message=[" + customer.toString() + "] with offset=[" +result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +customer.toString()+ "] due to : "+ex.getMessage());
                }
            });

        } catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
        }


    }
}
