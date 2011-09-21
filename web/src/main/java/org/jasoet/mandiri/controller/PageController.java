package org.jasoet.mandiri.controller;


import org.jasoet.mandiri.dao.PageDAO;
import org.jasoet.mandiri.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    private PageDAO pageDAO;
/* --------------------- Getter and Setter ---------------------*/

    public PageDAO getPageDAO() {
        return pageDAO;
    }

    @Autowired
    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public String page(ModelMap modelMap, @PathVariable("id") Long id) {

        Page page = pageDAO.get(id);
        modelMap.put("data", page);

        return "page/index";
    }
}
