package org.jasoet.mandiri.controller;


import org.jasoet.mandiri.dao.BeritaDAO;
import org.jasoet.mandiri.dao.PageDAO;
import org.jasoet.mandiri.domain.Berita;
import org.jasoet.mandiri.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.jsp.tagext.PageData;
import java.util.List;

@Controller
public class HomeController {

    private BeritaDAO beritaDAO;
    private PageDAO pageDAO;
/* --------------------- Getter and Setter ---------------------*/

    public PageDAO getPageDAO() {
        return pageDAO;
    }

    @Autowired
    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    public BeritaDAO getBeritaDAO() {
        return beritaDAO;
    }

    @Autowired
    public void setBeritaDAO(BeritaDAO beritaDAO) {
        this.beritaDAO = beritaDAO;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String berita(ModelMap modelMap) {

        List<Berita> listBerita = beritaDAO.getEntriesOrderByTanggalDesc(0, 15);
        modelMap.put("listBerita", listBerita);



        //halaman depan berada pada page dengan id 20 sampai 29
        try {
            Page front1 = pageDAO.get(20l);
            Page front2 = pageDAO.get(21l);
            Page front3 = pageDAO.get(22l);
            Page front4 = pageDAO.get(23l);
            Page front5 = pageDAO.get(24l);
            Page front6 = pageDAO.get(25l);
            Page front7 = pageDAO.get(26l);
            Page front8 = pageDAO.get(27l);
            Page front9 = pageDAO.get(28l);
            Page front10 = pageDAO.get(29l);

            modelMap.put("front1", front1);
            modelMap.put("front2", front2);
            modelMap.put("front3", front3);
            modelMap.put("front4", front4);
            modelMap.put("front5", front5);
            modelMap.put("front6", front6);
            modelMap.put("front7", front7);
            modelMap.put("front8", front8);
            modelMap.put("front9", front9);
            modelMap.put("front10", front10);
        } catch (Exception ex) {
             ex.printStackTrace();
        }

        return "home";
    }
}
