package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.RoleDAO;
import org.jasoet.mandiri.domain.Role;
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
public class RoleController {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserContoller.class);
    private RoleDAO roleDAO;

/* --------------------- Getter and Setter ---------------------*/

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

/* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/role/new", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        Role role = new Role();
        modelMap.addAttribute("role", role);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "role");


        return "role/create";
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
        Role data = roleDAO.get(id);
        roleDAO.delete(data);
        return "redirect:/role";
    }

    @RequestMapping(value = "/role/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") String id) {
        Role role = roleDAO.get(id);
        modelMap.addAttribute("role", role);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "role");

        return "role/edit";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        modelMap.addAttribute("data", roleDAO.getAll());
        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "role");


        return "role/index";
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("role") Role role, BindingResult result, ModelMap modelMap) {
        if (role == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("role", role);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "role");


            return "role/create";
        }

        roleDAO.save(role);

        return "redirect:/role/" + role.getAuthority();
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") String id) {
        Role data = roleDAO.get(id);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "role");

        return "role/show";
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("role") Role role, BindingResult result, ModelMap modelMap) {
        if (role == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("role", role);
            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "role");
            return "role/edit";
        }

        roleDAO.merge(role);

        return "redirect:/role/" + role.getAuthority();
    }
}
