package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.BeritaDAO;
import org.jasoet.mandiri.domain.Berita;
import org.jasoet.mandiri.domain.Staff;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class BeritaController {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserContoller.class);
    private BeritaDAO beritaDAO;

/* --------------------- Getter and Setter ---------------------*/

    @Autowired
    public void setBeritaDAO(BeritaDAO beritaDAO) {
        this.beritaDAO = beritaDAO;
    }

/* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/berita/new", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        Berita berita = new Berita();
        modelMap.addAttribute("berita", berita);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "berita");


        return "berita/create";
    }

    @RequestMapping(value = "/berita/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Berita data = beritaDAO.get(id);
        beritaDAO.delete(data);
        return "redirect:/berita";
    }

    @RequestMapping(value = "/berita/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Berita berita = beritaDAO.get(id);
        modelMap.addAttribute("berita", berita);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "berita");

        return "berita/edit";
    }

    @RequestMapping(value = "/berita", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {


        modelMap.addAttribute("data", beritaDAO.getAllOrderByTanggalDesc());

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "berita");


        return "berita/index";
    }

    @RequestMapping(value = "/berita", method = RequestMethod.POST)
    public String save(@ModelAttribute("berita") Berita berita, BindingResult result, ModelMap modelMap) {
        if (berita == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("berita", berita);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "berita");

            return "berita/create";
        }
        Staff loggedStaff = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        berita.setStaff(loggedStaff);
        berita.setTanggal(new Date());
        beritaDAO.save(berita);

        return "redirect:/berita/" + berita.getId();
    }

    @RequestMapping(value = "/berita/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id) {
        Berita data = beritaDAO.get(id);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "berita");


        return "berita/show";
    }

    @RequestMapping(value = "/berita", method = RequestMethod.PUT)
    public String update(@ModelAttribute("berita") Berita berita, BindingResult result, ModelMap modelMap) {
        if (berita == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("berita", berita);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "berita");

            return "berita/edit";
        }
        Staff loggedStaff = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        berita.setStaff(loggedStaff);
        berita.setTanggal(new Date());
        beritaDAO.merge(berita);

        return "redirect:/berita/" + berita.getId();
    }
}
