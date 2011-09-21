package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.BeritaDAO;
import org.jasoet.mandiri.dao.PageDAO;
import org.jasoet.mandiri.domain.Page;
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
public class PageController {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserContoller.class);
    private PageDAO pageDAO;

/* --------------------- Getter and Setter ---------------------*/

    @Autowired
    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

/* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/page/new", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        Page page = new Page();
        modelMap.addAttribute("page", page);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "page");


        return "page/create";
    }

    @RequestMapping(value = "/page/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Page data = pageDAO.get(id);
        pageDAO.delete(data);
        return "redirect:/page";
    }

    @RequestMapping(value = "/page/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Page page = pageDAO.get(id);
        modelMap.addAttribute("page", page);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "page");

        return "page/edit";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {


        modelMap.addAttribute("data", pageDAO.getAllOrderByTanggalDesc());

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "page");


        return "page/index";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public String save(@ModelAttribute("page") Page page, BindingResult result, ModelMap modelMap) {
        if (page == null) throw new IllegalArgumentException("A Page is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("page", page);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "page");

            return "page/create";
        }
        Staff loggedStaff = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        page.setStaff(loggedStaff);
        page.setTanggal(new Date());
        pageDAO.save(page);

        return "redirect:/page/" + page.getId();
    }

    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id) {
        Page data = pageDAO.get(id);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "page");


        return "page/show";
    }

    @RequestMapping(value = "/page", method = RequestMethod.PUT)
    public String update(@ModelAttribute("page") Page page, BindingResult result, ModelMap modelMap) {
        if (page == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("page", page);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "page");

            return "page/edit";
        }
        Staff loggedStaff = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        page.setStaff(loggedStaff);
        page.setTanggal(new Date());
        pageDAO.merge(page);

        return "redirect:/page/" + page.getId();
    }
}
