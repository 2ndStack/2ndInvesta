package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.ConfigPropertyDAO;
import org.jasoet.mandiri.domain.ConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: Deny Prasetyo, S.T.
 * Email : jasoet87@gmail.com
 * Date: 5/1/11
 * Time: 1:58 PM
 */
@Controller
public class TransferController {
    private ConfigPropertyDAO configPropertyDAO;

    public ConfigPropertyDAO getConfigPropertyDAO() {
        return configPropertyDAO;
    }

    @Autowired
    public void setConfigPropertyDAO(ConfigPropertyDAO configPropertyDAO) {
        this.configPropertyDAO = configPropertyDAO;
    }

    @RequestMapping(value = "/transfer/show", method = RequestMethod.GET)
    public String show(ModelMap modelMap) {
        ConfigProperty cf = configPropertyDAO.findByKeyz("biayaTransfer");
        String biayaTransfer = cf.getText();
        modelMap.addAttribute("biayaTransfer", biayaTransfer);
        return "transfer/show";
    }

    @RequestMapping(value = "/transfer/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap) {
        ConfigProperty cf = configPropertyDAO.findByKeyz("biayaTransfer");
        String biayaTransfer = cf.getText();
        modelMap.addAttribute("biayaTransfer", biayaTransfer);
        return "transfer/edit";
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public String save(ModelMap modelMap, @RequestParam(value = "biayaTransfer", defaultValue = "1000") String biayaTransfer) {

        try {
            Integer.parseInt(biayaTransfer);
        } catch (NumberFormatException ex) {
            biayaTransfer = "3000";
        }

        ConfigProperty cf = configPropertyDAO.findByKeyz("biayaTransfer");
        cf.setText(biayaTransfer + "");
        configPropertyDAO.merge(cf);

        modelMap.addAttribute("biayaTransfer", biayaTransfer);
        return "transfer/show";
    }
}
