package org.jasoet.mandiri.controller;

//import org.apache.poi.ss.usermodel.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.dao.BonusDAO;
import org.jasoet.mandiri.domain.Bank;
import org.jasoet.mandiri.domain.Bonus;
import org.jasoet.mandiri.util.BonusObjectJSON;
import org.jasoet.mandiri.util.enums.Month;
import org.jasoet.mandiri.util.enums.ProfitStatus;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.FilenameFilter;

@Controller
public class BonusContoller {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BonusContoller.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    private BonusDAO bonusDAO;
    private BankDAO bankDAO;

    /* --------------------- Getter and Setter ---------------------*/
    @Autowired
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    @Autowired
    public void setBonusDAO(BonusDAO bonusDAO) {
        this.bonusDAO = bonusDAO;
    }

    public File rekeningBonusReportLocation;

    public File getRekeningBonusReportLocation() {
        return rekeningBonusReportLocation;
    }

    @Value("#{configUtil.bonusRekLocation}")
    public void setRekeningBonusReportLocation(File rekeningBonusReportLocation) {
        this.rekeningBonusReportLocation = rekeningBonusReportLocation;
    }

    public File bonusReportLocation;


    public File getBonusReportLocation() {
        return bonusReportLocation;
    }

    @Value("#{configUtil.bonusLocation}")
    public void setBonusReportLocation(File profitReportLocation) {
        this.bonusReportLocation = profitReportLocation;
    }

    /* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/bonus/{namaBank}", method = RequestMethod.GET)
    public String index(ModelMap modelMap, @PathVariable("namaBank") String namaBank) {
        Bank selected = null;
        try {
            selected = bankDAO.findByNama(namaBank);
        } catch (DataAccessException ex) {
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

        modelMap.addAttribute("mainNav", "bonus");
        modelMap.addAttribute("namaBank", namaBank.toLowerCase());

        return "bonus/index";
    }

    @RequestMapping(value = "/bonus/{namaBank}/index.json", method = RequestMethod.GET)
    public
    @ResponseBody
    BonusObjectJSON showJSON(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
        String sSearch = request.getParameter("sSearch") != null ? request.getParameter("sSearch") : "";
        int iDisplayStart = request.getParameter("iDisplayStart") != null ? Integer.parseInt(request.getParameter("iDisplayStart")) : 0;
        int iDisplayLength = request.getParameter("iDisplayLength") != null ? Integer.parseInt(request.getParameter("iDisplayLength")) : 0;
        int sEcho = request.getParameter("sEcho") != null ? Integer.parseInt(request.getParameter("sEcho")) : 0;
        String sSearch_3 = request.getParameter("sSearch_3").isEmpty() ? ProfitStatus.COMPLETED.name() : request.getParameter("sSearch_3");
        String sSearch_1 = request.getParameter("sSearch_1");

        Bank selected = null;
        try {
            selected = bankDAO.findByNama(namaBank);
        } catch (DataAccessException ex) {
            return null;
        }

        if (selected == null) {
            return null;
        }

        int month;
        int year;
        try {
            String[] dates = sSearch_1.split("-");
            year = Integer.valueOf(dates[0]);
            month = Integer.valueOf(dates[1]);
        } catch (Exception e) {
            Calendar cal = Calendar.getInstance();
            month = cal.get(Calendar.MONTH);
            year = cal.get(Calendar.YEAR);
        }

        BonusObjectJSON result;
        try {
            ProfitStatus status = ProfitStatus.valueOf(sSearch_3);
            List<Bonus> data = bonusDAO.findByLimit(sSearch, selected, status, Month.valueOf(month), year, iDisplayStart, iDisplayLength);
            result = new BonusObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = bonusDAO.countBy(sSearch, selected, status, Month.valueOf(month), year);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        } catch (IllegalArgumentException ex) {
            List<Bonus> data = bonusDAO.findByLimit(sSearch, selected, ProfitStatus.PENDING, Month.valueOf(month), year, iDisplayStart, iDisplayLength);
            result = new BonusObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = bonusDAO.countBy(sSearch, selected, ProfitStatus.PENDING, Month.valueOf(month), year);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        }

        return result;
    }

    @RequestMapping(value = "/bonus/{namaBank}/{id}/show", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Bonus bonus = bonusDAO.get(id);
        if (bonus == null) {
            return "redirect:/bonus/" + namaBank;
        }

        modelMap.addAttribute("mainNav", "bonus");
        modelMap.addAttribute("namaBank", namaBank);
        modelMap.addAttribute("bulan", bonus.getBulan().name());
        modelMap.addAttribute("status", bonus.getStatus().name());
        modelMap.addAttribute("data", bonus);
        return "bonus/show";
    }

    @RequestMapping(value = "/bonus/{namaBank}/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Bonus bonus = bonusDAO.get(id);
        modelMap.addAttribute("bonus", bonus);
        String bulan = bonus.getBulan().name();
        modelMap.addAttribute("bulan", bulan + "-" + bonus.getTahun());

        modelMap.addAttribute("mainNav", "bonus");
        modelMap.addAttribute("sidebar", "bonus");
        modelMap.addAttribute("namaBank", namaBank);


        return "bonus/edit";
    }

    @RequestMapping(value = "/bonus/{namaBank}/{id}/COMPLETED", method = RequestMethod.GET)
    public String setCompleted(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Bonus bonus = bonusDAO.get(id);
        bonus.setStatus(ProfitStatus.COMPLETED);

        bonusDAO.merge(bonus);

        modelMap.addAttribute("mainNav", "masterData");
        modelMap.addAttribute("sidebar", "bonus");

        return "redirect:/bonus/" + namaBank + "/" + id + "/show";
    }

    @RequestMapping(value = "/bonus/{namaBank}/{id}/PENDING", method = RequestMethod.GET)
    public String setPending(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Bonus bonus = bonusDAO.get(id);
        bonus.setStatus(ProfitStatus.PENDING);

        bonusDAO.merge(bonus);

        modelMap.addAttribute("mainNav", "masterData");
        modelMap.addAttribute("sidebar", "bonus");

        return "redirect:/bonus/" + namaBank + "/" + id + "/show";
    }


    @RequestMapping(value = "/bonus/{namaBank}", method = RequestMethod.POST)
    public String editPost(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
        Long id = Long.valueOf(request.getParameter("id"));
        Bonus bonus = bonusDAO.get(id);
        ProfitStatus status = ProfitStatus.valueOf(request.getParameter("status"));
        String keterangan = request.getParameter("keterangan");

        bonus.setStatus(status);
        bonus.setKeterangan(keterangan);
        bonusDAO.merge(bonus);

        return "redirect:/bonus/" + namaBank + "/" + id + "/show";
    }
/*
    @RequestMapping(value = "/bonus/{namaBank}/masal", method = RequestMethod.GET)
    public String masal(ModelMap modelMap, HttpServletRequest request, @PathVariable("namaBank") String namaBank) {

        List<Integer> listTahun = new ArrayList<Integer>();
        int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
        for (int i = 3; i >= 0; i--) {
            listTahun.add(tahunSekarang - i);
        }
        modelMap.addAttribute("listTahun", listTahun);
        modelMap.addAttribute("tahunSekarang", tahunSekarang);
        modelMap.addAttribute("bulanSekarang", bulanSekarang);
        modelMap.addAttribute("mainNav", "bonus");
        modelMap.addAttribute("namaBank", namaBank);

        return "bonus/masal";
    }

    @RequestMapping(value = "/bonus/{namaBank}/masal", method = RequestMethod.POST)
    public String masalPost(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
        Bank selected = null;
        try {
            selected = bankDAO.findByName(namaBank);
        } catch (DataAccessException ex) {
            return "redirect:/dashboard";
        }

        if (selected == null) {
            return "redirect:/dashboard";
        }

        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));
        ProfitStatus status = ProfitStatus.valueOf(request.getParameter("status"));

        bonusDAO.editMasal(selected, Month.valueOf(month), year, status);

        return "redirect:/bonus/" + namaBank;
    }*/

    @RequestMapping(value = "/bonus/{namaBank}/laporan", method = RequestMethod.GET)
    public String laporan(ModelMap modelMap, HttpServletRequest request, @PathVariable("namaBank") String namaBank) {

        List<Integer> listTahun = new ArrayList<Integer>();
        int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
        for (int i = 3; i >= 0; i--) {
            listTahun.add(tahunSekarang - i);
        }
        modelMap.addAttribute("listTahun", listTahun);
        modelMap.addAttribute("tahunSekarang", tahunSekarang);
        modelMap.addAttribute("bulanSekarang", bulanSekarang);
        modelMap.addAttribute("mainNav", "bonus");
        modelMap.addAttribute("namaBank", namaBank);

        return "bonus/laporan";
    }

    @RequestMapping(value = "/bonus/{namaBank}/laporan", method = RequestMethod.POST)
    public String laporanPost(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {

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


        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));

        int start = 0;
        int size = 5000;
        int no = 1;

        long totalRow = bonusDAO.countByMonth(selected, Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<Bonus> listBonus = bonusDAO.findByMonth(selected, Month.valueOf(month), year, start, size);

            Sheet sheet = wb.createSheet("DETAIL PROFIT " + (j + 1));

            // Create a row and put some cells in it. Rows are 0 based.
            Row rowTitle = sheet.createRow(0);
            String title = "PROFIT Bank " + namaBank + " " + Month.valueOf(month).name() + " " + year;
            rowTitle.createCell(0).setCellValue(title);

            short i = 2;

            Row rowTh = sheet.createRow(i);

            // Or do it on one line.
            rowTh.createCell(0).setCellValue("NO");
            rowTh.createCell(1).setCellValue("USERNAME");
            rowTh.createCell(2).setCellValue("NAMA");
            rowTh.createCell(3).setCellValue("REKENING");
            rowTh.createCell(4).setCellValue("ATAS NAMA REKENING");
            rowTh.createCell(5).setCellValue("PROFIT");
            rowTh.createCell(6).setCellValue("TGL AKTIF");
            rowTh.createCell(7).setCellValue("BANK");
            i++;

            List<Row> listRow = new ArrayList<Row>();
            for (Bonus p : listBonus) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(no);
                row.createCell(1).setCellValue(p.getUser().getUsername());
                row.createCell(2).setCellValue(p.getUser().getNama());
                row.createCell(3).setCellValue(p.getUser().getNomorRekening());
                row.createCell(4).setCellValue(p.getUser().getNamaRekening());
                row.createCell(5).setCellValue(p.getJumlah());

                row.createCell(6).setCellValue(dateFormat.format(p.getUser().getTanggalAktivasi()));
                row.createCell(7).setCellValue(p.getUser().getBank().getNama());

                i++;
                no++;
            }

            start = start + size;
        }

        // Write the output to a file
        try {
            dateFormat = new SimpleDateFormat("yyyyMMdd");
            File outputFile = new File(bonusReportLocation, namaBank.toLowerCase() + "/" + dateFormat.format(new Date()) + "_" + "bonus_" + namaBank + "_(" + Month.valueOf(month).name() + "-" + year + ").xls");
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/bonus/" + namaBank + "/list";
    }

    @RequestMapping(value = "/bonus/{namaBank}/list", method = RequestMethod.GET)
    public String listLaporan(ModelMap modelMap, @PathVariable("namaBank") String namaBank) {

        File laporanDir = new File(bonusReportLocation, "/" + namaBank);
        String[] daftar = laporanDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xls");
            }
        });

        modelMap.addAttribute("data", daftar);
        modelMap.addAttribute("namaBank", namaBank);
        return "bonus/daftar";
    }


    @RequestMapping(value = "/bonus/{namaBank}/laporanrek", method = RequestMethod.GET)
    public String laporanrek(ModelMap modelMap, HttpServletRequest request, @PathVariable("namaBank") String namaBank) {

        List<Integer> listTahun = new ArrayList<Integer>();
        int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
        for (int i = 3; i >= 0; i--) {
            listTahun.add(tahunSekarang - i);
        }
        modelMap.addAttribute("listTahun", listTahun);
        modelMap.addAttribute("tahunSekarang", tahunSekarang);
        modelMap.addAttribute("bulanSekarang", bulanSekarang);
        modelMap.addAttribute("mainNav", "laporan");
        modelMap.addAttribute("namaBank", namaBank);

        return "bonusrek/laporan";
    }

    @RequestMapping(value = "/bonus/{namaBank}/laporanrek", method = RequestMethod.POST)
    public String laporanrekPost(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {

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


        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));

        int start = 0;
        int size = 5000;
        int no = 1;

        long totalRow = bonusDAO.countByMonthRekening(selected, Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<Map<String, Object>> listBonus = bonusDAO.findByMonthRekening(selected, Month.valueOf(month), year, start, size);

            Sheet sheet = wb.createSheet("DETAIL PROFIT " + (j + 1));

            // Create a row and put some cells in it. Rows are 0 based.
            Row rowTitle = sheet.createRow(0);
            String title = "PROFIT Bank " + namaBank + " " + Month.valueOf(month).name() + " " + year;
            rowTitle.createCell(0).setCellValue(title);

            short i = 2;

            Row rowTh = sheet.createRow(i);

            // Or do it on one line.
            rowTh.createCell(0).setCellValue("NO");
            rowTh.createCell(1).setCellValue("REKENING");
            rowTh.createCell(2).setCellValue("ATAS NAMA REKENING");
            rowTh.createCell(3).setCellValue("PROFIT");
            rowTh.createCell(4).setCellValue("BANK");
            i++;

            for (Map<String, Object> p : listBonus) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(no);
                row.createCell(1).setCellValue("" + p.get("nomorRekening"));
                row.createCell(2).setCellValue("" + p.get("namaRekening"));
                row.createCell(3).setCellValue("" + p.get("jumlah"));
                row.createCell(4).setCellValue("" + p.get("namaBank"));
                i++;
                no++;
            }

            start = start + size;
        }

        // Write the output to a file
        try {
            dateFormat = new SimpleDateFormat("yyyyMMdd");
            File outputFile = new File(rekeningBonusReportLocation, namaBank.toLowerCase() + "/" + dateFormat.format(new Date()) + "_" + "bonus_by_rekening_" + namaBank + "_(" + Month.valueOf(month).name() + "-" + year + ").xls");
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/bonus/" + namaBank + "/listrek";
    }

    @RequestMapping(value = "/bonus/{namaBank}/listrek", method = RequestMethod.GET)
    public String listLaporanrek(ModelMap modelMap, @PathVariable("namaBank") String namaBank) {

        File laporanDir = new File(rekeningBonusReportLocation, "/" + namaBank);
        String[] daftar = laporanDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xls");
            }
        });

        modelMap.addAttribute("data", daftar);
        modelMap.addAttribute("namaBank", namaBank);
        return "bonusrek/daftar";
    }

}
