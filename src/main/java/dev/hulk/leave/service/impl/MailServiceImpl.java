package dev.hulk.leave.service.impl;

import dev.hulk.leave.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String destination, String message){
        SimpleMailMessage mgs = new SimpleMailMessage();

        mgs.setTo(destination);
        mgs.setSubject("Leave Request Notification");
        mgs.setText(message);

        javaMailSender.send(mgs);
        System.out.println("Mail sent!");
    }
}
