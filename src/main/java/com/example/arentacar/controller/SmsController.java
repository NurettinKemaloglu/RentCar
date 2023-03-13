package com.example.arentacar.controller;

import com.example.arentacar.business.concretes.SmsManager;
import com.example.arentacar.modal.Sms;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/sms")
@AllArgsConstructor
public class SmsController {
    SimpMessagingTemplate simpMessagingTemplate ;
    SmsManager smsManager;

    private  static final String TOPIC_NAME = "/topic/sms";

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Object> sendSms(@RequestBody Sms sms){
        try {
            smsManager.sendSms(sms);

        }catch (Exception e){
            simpMessagingTemplate.convertAndSend(TOPIC_NAME, getDateAndTime()+"Sms gönderme işlemi başarız oldu");
        }
        simpMessagingTemplate.convertAndSend(TOPIC_NAME,getDateAndTime()+"Mesaj teslim edildi");
        return ResponseEntity.ok().build();
    }

    public String getDateAndTime(){
      return   DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    }
}
