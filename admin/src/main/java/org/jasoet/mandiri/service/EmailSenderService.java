package org.jasoet.mandiri.service;

import org.jasoet.mandiri.dao.EmailDAO;
import org.jasoet.mandiri.domain.Email;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * User: Deny Prasetyo, S.T.
 * http://www.jasoet.com
 */
@Service("emailSenderService")
public class EmailSenderService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmailSenderService.class);
    private EmailDAO emailDAO;
    private MailSender mailSender;
    private SimpleMailMessage mailTemplate;

    public EmailDAO getEmailDAO() {
        return emailDAO;
    }

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    public MailSender getMailSender() {
        return mailSender;
    }

    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public SimpleMailMessage getMailTemplate() {
        return mailTemplate;
    }

    @Autowired
    public void setMailTemplate(SimpleMailMessage mailTemplate) {
        this.mailTemplate = mailTemplate;
    }


    public void run() {
        Email toSend = emailDAO.getFirstPriorityEmail();
        if (toSend == null) {
            return;
        }
        toSend.setDelivered(true);
        emailDAO.save(toSend);
        try {

            sendEmail(toSend.getTo(), toSend.getSubject(), toSend.getText());
            log.info("Mail Send Successful to : " + toSend.getTo() + " With Subject: " + toSend.getSubject());
        } catch (MailException me) {
            toSend.setDelivered(false);
            emailDAO.save(toSend);
            log.error("MailException : " + me.getMessage());
        }
    }

    public void sendEmail(String to, String subject, String text) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage(mailTemplate);
        mailMessage.setTo(to);
        mailMessage.setText(text);

        mailMessage.setSubject(subject);
        mailSender.send(mailMessage);
    }
}
