package com.wilber.ident_core_api.email.service;

public interface IEmailService {

    void sendEmail(String[] toUser, String subject, String message);

}
