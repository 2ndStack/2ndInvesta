package org.jasoet.mandiri.controller;

import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.dao.ProfitDAO;
import org.jasoet.mandiri.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class LaporanController {
    /*------------------------------ Fields ------------------------------*/
    private ProfitDAO profitDAO;
    private BankDAO bankDAO;

/* --------------------- Getter and Setter ---------------------*/

    public ProfitDAO getProfitDAO() {
        return profitDAO;
    }

    @Autowired
    public void setProfitDAO(ProfitDAO profitDAO) {
        this.profitDAO = profitDAO;
    }

    public BankDAO getBankDAO() {
        return bankDAO;
    }

    @Autowired
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }
    /* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/laporan/profit/{namaBank}", method = RequestMethod.GET)
    public String index(ModelMap modelMap, @PathVariable("namaBank") String namaBank) {
        Bank selected = null;
        try {
            selected = bankDAO.findByNama(namaBank);
        } catch (DataAccessException ex) {
            return "redirect:/dashboard";
        } catch (EntityNotFoundException ex) {
            return "redirect:/dashboard";
        }

        if (selected == null) {
            return "redirect:/dashboard";
        }

        List<Integer> listTahun = new ArrayList<Integer>();
        int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
        for (int i = 3; i >= 0; i--) {
            listTahun.add(tahunSekarang - i);
        }

        modelMap.addAttribute("bank", selected);
        modelMap.addAttribute("listTahun", listTahun);
        modelMap.addAttribute("tahunSekarang", tahunSekarang);
        modelMap.addAttribute("bulanSekarang", bulanSekarang);

        modelMap.addAttribute("mainNav", "profit");
        modelMap.addAttribute("namaBank", namaBank.toLowerCase());

        return "profit/index";
    }


}
