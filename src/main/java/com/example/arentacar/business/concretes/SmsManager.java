package com.example.arentacar.business.concretes;


import com.example.arentacar.modal.Sms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsManager {
    @Value("#{systemEnvironment['TWILIO_ACCOUNT_SID']}")
    private String SID;
    @Value("#{systemEnvironment['TWILIO_ACCOUNT_TOKEN']}")
    private String authToken;
    @Value("#{systemEnvironment['TWILIO_PHONE_NUMBER']}")
    private String fromPhoneNumber;
    @Value("#{systemEnvironment['TO_PHONE_NUMBER']}")
    private String toPhoneNumber;

    public void sendSms(Sms sms) {
        Twilio.init(SID, authToken);
        Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), sms.getMessage()).create();
        log.info("Sms başarıyla gönderildi Sid", message.getSid());
    }


}
