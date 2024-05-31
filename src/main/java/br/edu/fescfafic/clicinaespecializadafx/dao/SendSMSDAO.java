package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.services.SendSMS;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSMSDAO {
    private SendSMS sms = new SendSMS();
    public void sendSMS(String mensagem){
        Twilio.init(sms.ACCOUNT_SID, sms.AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber("+5511987069733"), new PhoneNumber("+12513125765"), mensagem)
                .create();

        System.out.println(message.getSid());
    }
}
