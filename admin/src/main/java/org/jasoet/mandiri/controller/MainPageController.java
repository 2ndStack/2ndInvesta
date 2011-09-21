package org.jasoet.mandiri.controller;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {
    /* -------------------------- Other Methods -------------------------- */
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MainPageController.class);


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(ModelMap modelMap) {


        modelMap.addAttribute("mainNav", "home");
        return "main/dashboard";
    }

}
