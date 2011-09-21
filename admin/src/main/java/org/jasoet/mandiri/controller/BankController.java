package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.domain.Bank;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class BankController {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserContoller.class);
    private BankDAO bankDAO;

/* --------------------- Getter and Setter ---------------------*/

    @Autowired
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

/* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/bank/new", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        Bank bank = new Bank();
        modelMap.addAttribute("bank", bank);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "bank");


        return "bank/create";
    }

    @RequestMapping(value = "/bank/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Bank data = bankDAO.get(id);
        bankDAO.delete(data);
        return "redirect:/bank";
    }

    @RequestMapping(value = "/bank/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Bank bank = bankDAO.get(id);
        modelMap.addAttribute("bank", bank);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "bank");


        return "bank/edit";
    }

    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        modelMap.addAttribute("data", bankDAO.getAll());
        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "bank");

        return "bank/index";
    }

    @RequestMapping(value = "/bank", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("bank") Bank bank, BindingResult result, ModelMap modelMap) {
        if (bank == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("bank", bank);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "bank");


            return "bank/create";
        }

        bankDAO.save(bank);

        return "redirect:/bank/" + bank.getId();
    }

    @RequestMapping(value = "/bank/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id) {
        Bank data = bankDAO.get(id);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "bank");

        return "bank/show";
    }

    @RequestMapping(value = "/bank", method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("bank") Bank bank, BindingResult result, ModelMap modelMap) {
        if (bank == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("bank", bank);
            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "bank");
            return "bank/edit";
        }

        bankDAO.merge(bank);

        return "redirect:/bank/" + bank.getId();
    }
}
