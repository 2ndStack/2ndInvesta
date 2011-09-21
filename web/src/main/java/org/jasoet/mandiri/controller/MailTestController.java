package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.service.MandiriMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: Deny Prasetyo, S.T.
 * http://www.jasoet.com
 */
@Controller
public class MailTestController {
    private MandiriMailService mailService;

    public MandiriMailService getMailService() {
        return mailService;
    }

    @Autowired
    public void setMailService(MandiriMailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "/mail/send", method = RequestMethod.GET)
    public String berita() {

        String[] str = new String[]{"jasoet@gmail.com", "joss@jasoet.com"};
        mailService.sendEmail("jasoet87@gmail.com", str, "Idih", "UDUH");

        return "redirect:/home";
    }
}
