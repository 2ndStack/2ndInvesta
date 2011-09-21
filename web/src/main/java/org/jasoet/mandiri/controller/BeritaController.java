package org.jasoet.mandiri.controller;


import org.jasoet.mandiri.dao.BeritaDAO;
import org.jasoet.mandiri.domain.Berita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BeritaController {

    private BeritaDAO beritaDAO;
/* --------------------- Getter and Setter ---------------------*/

    public BeritaDAO getBeritaDAO() {
        return beritaDAO;
    }

    @Autowired
    public void setBeritaDAO(BeritaDAO beritaDAO) {
        this.beritaDAO = beritaDAO;
    }

    @RequestMapping(value = "/berita", method = RequestMethod.GET)
    public String berita(ModelMap modelMap) {

        List<Berita> berita = beritaDAO.getEntriesOrderByTanggalDesc(0, 20);
        modelMap.put("berita", berita);

        return "berita/index";
    }


    @RequestMapping(value = "/berita/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id) {

        Berita berita = beritaDAO.get(id);
        modelMap.put("data", berita);

        return "berita/show";
    }


}
