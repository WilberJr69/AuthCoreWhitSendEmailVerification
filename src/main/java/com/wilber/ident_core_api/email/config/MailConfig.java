package com.wilber.ident_core_api.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${app.mail.email}")
    String userEmail;
    @Value("${app.mail.passwordEmailApp}")
    String password;

    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl javaMailSender =new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(userEmail);
        javaMailSender.setPassword(password);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");

        return javaMailSender;


    }


}
