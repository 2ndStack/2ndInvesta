package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.PaketDAO;
import org.jasoet.mandiri.domain.Paket;
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
public class PaketController {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserContoller.class);
    private PaketDAO paketDAO;

/* --------------------- Getter and Setter ---------------------*/

    @Autowired
    public void setPaketDAO(PaketDAO paketDAO) {
        this.paketDAO = paketDAO;
    }

/* -------------------------- Other Methods -------------------------- */

//    @RequestMapping(value = "/paket/new", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        Paket paket = new Paket();
        modelMap.addAttribute("paket", paket);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "paket");


        return "paket/create";
    }

    @RequestMapping(value = "/paket/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Paket data = paketDAO.get(id);
        data.setEnabled(false);
        paketDAO.merge(data);
        return "redirect:/paket";
    }

//    @RequestMapping(value = "/paket/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Paket paket = paketDAO.get(id);
        modelMap.addAttribute("paket", paket);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "paket");


        return "paket/edit";
    }

    @RequestMapping(value = "/paket", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        modelMap.addAttribute("data", paketDAO.getAll());
        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "paket");


        return "paket/index";
    }

//    @RequestMapping(value = "/paket", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("paket") Paket paket, BindingResult result, ModelMap modelMap) {
        if (paket == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("paket", paket);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "paket");


            return "paket/create";
        }

        paketDAO.save(paket);

        return "redirect:/paket/" + paket.getId();
    }

    @RequestMapping(value = "/paket/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id) {
        Paket data = paketDAO.get(id);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "paket");


        return "paket/show";
    }

//    @RequestMapping(value = "/paket", method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("paket") Paket paket, BindingResult result, ModelMap modelMap) {
        if (paket == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("paket", paket);
            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "paket");

            return "paket/edit";
        }

        paketDAO.merge(paket);

        return "redirect:/paket/" + paket.getId();
    }
}
