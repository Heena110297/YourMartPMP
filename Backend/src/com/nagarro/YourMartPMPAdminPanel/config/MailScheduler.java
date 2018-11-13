package com.nagarro.YourMartPMPAdminPanel.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
@EnableScheduling
class MailScheduler {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Scheduled(cron = "0 0 12 * * ?")
    public void demoServiceMethod()
    {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("nimitjohri5@gmail.com");
        email.setSubject("Your Product list");
        email.setText("list of products");
        mailSender.send(email);
    }
}
