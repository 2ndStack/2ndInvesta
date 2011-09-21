package org.jasoet.mandiri.controller;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoginController.class);

/* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public String index(ModelMap modelMap) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Security Context Contains " + auth);

        modelMap.addAttribute("mainNav", "none");
        return "admin/login";
    }
}


/*
Domain Name: 	www.mandiriinvesta.com

Public Key: 	6Ld-X8ISAAAAAGzM5gdFBiL7byQUPsFT7da3wvHe

Private Key: 	6Ld-X8ISAAAAAPOyMfEcXK9XVyUkJC9bcw7DuTDB

*/