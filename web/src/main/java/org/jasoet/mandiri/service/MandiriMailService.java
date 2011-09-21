package org.jasoet.mandiri.service;

import org.jasoet.mandiri.dao.EmailDAO;
import org.jasoet.mandiri.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * User: Deny Prasetyo, S.T.
 * http://www.jasoet.com
 */

@Component
public class MandiriMailService {

    private EmailDAO emailDAO;

    public EmailDAO getEmailDAO() {
        return emailDAO;
    }

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    public void sendEmail(String to,String[] bcc, String subject, String text) {

        Email email = new Email();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);

        emailDAO.save(email);
    }

}
