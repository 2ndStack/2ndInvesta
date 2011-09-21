package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.RoleDAO;
import org.jasoet.mandiri.dao.StaffDAO;
import org.jasoet.mandiri.domain.Role;
import org.jasoet.mandiri.domain.Staff;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.codec.Hex;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StaffController {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserContoller.class);
    private StaffDAO staffDAO;
    private RoleDAO roleDAO;

    private PasswordEncoder passwordEncoder;
    private SaltSource saltSource;

/* --------------------- Getter and Setter ---------------------*/

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public SaltSource getSaltSource() {
        return saltSource;
    }

    @Autowired
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    @Autowired
    public void setStaffDAO(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

/* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/staff/password", method = RequestMethod.GET)
    public String changePassword(ModelMap modelMap) {
        Object updated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (updated instanceof Staff) {
            modelMap.addAttribute("staff", updated);
        } else {
            return "redirect:/dashboard";
        }

        modelMap.addAttribute("mainNav", "none");


        return "staff/cp";
    }

    @RequestMapping(value = "/staff/new", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        Staff staff = new Staff();
        List<Role> roles = roleDAO.getAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("staff", staff);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "staff");

        return "staff/create";
    }

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        Staff data = staffDAO.get(id);
        staffDAO.merge(data);
        staffDAO.delete(data);
        return "redirect:/staff";
    }

    @RequestMapping(value = "/staff/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Staff staff = staffDAO.get(id);
        List<Role> roles = roleDAO.getAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("staff", staff);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "staff");


        return "staff/edit";
    }

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        modelMap.addAttribute("data", staffDAO.getAll());
        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "staff");


        return "staff/index";
    }

    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("staff") Staff staff, BindingResult result, ModelMap modelMap) {
        if (staff == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("staff", staff);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "staff");


            return "staff/create";
        }
        String salt = new String(Hex.encode(staff.getUsername().getBytes()));
        staff.setSalt(salt);
        staff.setPassword(passwordEncoder.encodePassword(staff.getPassword(), salt));
        staffDAO.save(staff);

        return "redirect:/staff/" + staff.getId();
    }

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id) {
        Staff data = staffDAO.get(id);
        List<Role> roles = roleDAO.getAll();
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("data", data);

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "staff");


        return "staff/show";
    }

    @RequestMapping(value = "/staff", method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("staff") Staff staff, BindingResult result, ModelMap modelMap) {
        if (staff == null) throw new IllegalArgumentException("A Product is required");
        if (result.hasErrors()) {
            modelMap.addAttribute("staff", staff);

            modelMap.addAttribute("mainNav", "master");
            modelMap.addAttribute("tab", "staff");


            return "staff/edit";
        }

        Long id = staff.getId();

        Staff updated = staffDAO.get(id);
        updated.setTelephone(staff.getTelephone());
        updated.setEnabled(staff.isEnabled());
        updated.setRoles(staff.getRoles());

        staffDAO.merge(updated);

        return "redirect:/staff/" + id;
    }

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.PUT)
    public String updatePassword(ModelMap modelMap,
                                 @PathVariable("id") Long id,
                                 @RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword) {
        Staff updated = staffDAO.get(id);
        log.debug(currentPassword);
        log.debug(newPassword);
        log.debug(confirmPassword);

        if (passwordEncoder.isPasswordValid(updated.getPassword(), currentPassword, updated.getSalt())) {
            if (newPassword.equals(confirmPassword)) {
                updated.setPassword(passwordEncoder.encodePassword(newPassword, updated.getSalt()));
                staffDAO.merge(updated);


                return "redirect:/dashboard";
            }
        }
        modelMap.addAttribute("staff", staffDAO.get(id));
        modelMap.addAttribute("message", "Change Password Failed, Check your Current Password");

        modelMap.addAttribute("mainNav", "master");
        modelMap.addAttribute("tab", "staff");


        return "staff/cp";
    }
}
