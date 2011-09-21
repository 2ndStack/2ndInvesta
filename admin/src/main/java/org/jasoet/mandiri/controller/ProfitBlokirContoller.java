package org.jasoet.mandiri.controller;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.dao.ProfitBlokirDAO;
import org.jasoet.mandiri.domain.Bank;
import org.jasoet.mandiri.domain.ProfitBlokir;
import org.jasoet.mandiri.util.ProfitBlokirObjectJSON;
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
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProfitBlokirContoller {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProfitBlokirContoller.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    private ProfitBlokirDAO profitBlokirDAO;
    private BankDAO bankDAO;

    /* --------------------- Getter and Setter ---------------------*/
    @Autowired
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    @Autowired
    public void setProfitBlokirDAO(ProfitBlokirDAO profitBlokirDAO) {
        this.profitBlokirDAO = profitBlokirDAO;
    }

    public File profitReportLocation;

    public File rekeningProfitReportLocation;

    public File getRekeningProfitReportLocation() {
        return rekeningProfitReportLocation;
    }

    @Value("#{configUtil.profitRekLocation}")
    public void setRekeningProfitReportLocation(File rekeningProfitReportLocation) {
        this.rekeningProfitReportLocation = rekeningProfitReportLocation;
    }

    public File getProfitReportLocation() {
        return profitReportLocation;
    }

    @Value("#{configUtil.profitLocation}")
    public void setProfitReportLocation(File profitReportLocation) {
        this.profitReportLocation = profitReportLocation;
    }

    /* -------------------------- Other Methods -------------------------- */

    @RequestMapping(value = "/profitblokir/{namaBank}", method = RequestMethod.GET)
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

        modelMap.addAttribute("mainNav", "profitblokir");
        modelMap.addAttribute("namaBank", namaBank.toLowerCase());

        return "profitblokir/index";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/index.json", method = RequestMethod.GET)
    public
    @ResponseBody
    ProfitBlokirObjectJSON showJSON(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
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
        } catch (EntityNotFoundException ex) {
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

        ProfitBlokirObjectJSON result;
        try {
            ProfitStatus status = ProfitStatus.valueOf(sSearch_3);
            List<ProfitBlokir> data = profitBlokirDAO.findByLimit(sSearch, selected, status, Month.valueOf(month), year, iDisplayStart, iDisplayLength);
            result = new ProfitBlokirObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = profitBlokirDAO.countBy(sSearch, selected, status, Month.valueOf(month), year);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        } catch (IllegalArgumentException ex) {
            List<ProfitBlokir> data = profitBlokirDAO.findByLimit(sSearch, selected, ProfitStatus.PENDING, Month.valueOf(month), year, iDisplayStart, iDisplayLength);
            result = new ProfitBlokirObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = profitBlokirDAO.countBy(sSearch, selected, ProfitStatus.PENDING, Month.valueOf(month), year);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        }

        return result;
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/{id}/show", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        ProfitBlokir profit = profitBlokirDAO.get(id);
        if (profit == null) {
            return "redirect:/profitblokir/" + namaBank;
        }

        modelMap.addAttribute("mainNav", "profitblokir");
        modelMap.addAttribute("namaBank", namaBank);
        modelMap.addAttribute("bulan", profit.getBulan().name());
        modelMap.addAttribute("status", profit.getStatus().name());
        modelMap.addAttribute("data", profit);
        return "profitblokir/show";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        ProfitBlokir profit = profitBlokirDAO.get(id);
        modelMap.addAttribute("profit", profit);
        String bulan = profit.getBulan().name();
        modelMap.addAttribute("bulan", bulan + "-" + profit.getTahun());

        modelMap.addAttribute("mainNav", "profitblokir");
        modelMap.addAttribute("sidebar", "profit");
        modelMap.addAttribute("namaBank", namaBank);


        return "profitblokir/edit";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/{id}/COMPLETED", method = RequestMethod.GET)
    public String setCompleted(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        ProfitBlokir profit = profitBlokirDAO.get(id);
        profit.setStatus(ProfitStatus.COMPLETED);

        profitBlokirDAO.merge(profit);

        modelMap.addAttribute("mainNav", "masterData");
        modelMap.addAttribute("sidebar", "profit");

        return "redirect:/profitblokir/" + namaBank + "/" + id + "/show";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/{id}/PENDING", method = RequestMethod.GET)
    public String setPending(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        ProfitBlokir profit = profitBlokirDAO.get(id);
        profit.setStatus(ProfitStatus.PENDING);

        profitBlokirDAO.merge(profit);

        modelMap.addAttribute("mainNav", "masterData");
        modelMap.addAttribute("sidebar", "profit");

        return "redirect:/profitblokir/" + namaBank + "/" + id + "/show";
    }


    @RequestMapping(value = "/profitblokir/{namaBank}", method = RequestMethod.POST)
    public String editPost(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
        Long id = Long.valueOf(request.getParameter("id"));
        ProfitBlokir profit = profitBlokirDAO.get(id);
        ProfitStatus status = ProfitStatus.valueOf(request.getParameter("status"));
        String keterangan = request.getParameter("keterangan");

        profit.setStatus(status);
        profit.setKeterangan(keterangan);
        profitBlokirDAO.merge(profit);

        return "redirect:/profitblokir/" + namaBank + "/" + id + "/show";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/masal", method = RequestMethod.GET)
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
        modelMap.addAttribute("mainNav", "profitblokir");
        modelMap.addAttribute("namaBank", namaBank);

        return "profitblokir/masal";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/masal", method = RequestMethod.POST)
    public String masalPost(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
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
        ProfitStatus status = ProfitStatus.valueOf(request.getParameter("status"));

        profitBlokirDAO.editMasal(selected, Month.valueOf(month), year, status);

        return "redirect:/profitblokir/" + namaBank;
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/laporan", method = RequestMethod.GET)
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
        modelMap.addAttribute("mainNav", "profitblokir");
        modelMap.addAttribute("namaBank", namaBank);

        return "profitblokir/laporan";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/laporan", method = RequestMethod.POST)
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

        long totalRow = profitBlokirDAO.countByMonth(selected, Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<ProfitBlokir> listProfit = profitBlokirDAO.findByMonth(selected, Month.valueOf(month), year, start, size);

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
            for (ProfitBlokir p : listProfit) {
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
            File outputFile = new File(profitReportLocation, namaBank.toLowerCase() + "/" + dateFormat.format(new Date()) + "_" + "profitblokir_" + namaBank + "_(" + Month.valueOf(month).name() + "-" + year + ").xls");
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/profitblokir/" + namaBank + "/list";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/list", method = RequestMethod.GET)
    public String listLaporan(ModelMap modelMap, @PathVariable("namaBank") String namaBank) {

        File laporanDir = new File(profitReportLocation, "/" + namaBank);
        String[] daftar = laporanDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xls");
            }
        });

        modelMap.addAttribute("data", daftar);
        modelMap.addAttribute("namaBank", namaBank);
        return "profitblokir/daftar";
    }


    @RequestMapping(value = "/profitblokir/{namaBank}/laporanrek", method = RequestMethod.GET)
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
        modelMap.addAttribute("mainNav", "profitblokir");
        modelMap.addAttribute("namaBank", namaBank);

        return "profitrek/laporan";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/laporanrek", method = RequestMethod.POST)
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

        long totalRow = profitBlokirDAO.countByMonthRekening(selected, Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<Map<String, Object>> listProfit = profitBlokirDAO.findByMonthRekening(selected, Month.valueOf(month), year, start, size);

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

            for (Map<String, Object> p : listProfit) {
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
            File outputFile = new File(rekeningProfitReportLocation, namaBank.toLowerCase() + "/" + dateFormat.format(new Date()) + "_" + "profitblokir_by_rekening_" + namaBank + "_(" + Month.valueOf(month).name() + "-" + year + ").xls");
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/profitblokir/" + namaBank + "/listrek";
    }

    @RequestMapping(value = "/profitblokir/{namaBank}/listrek", method = RequestMethod.GET)
    public String listLaporanrek(ModelMap modelMap, @PathVariable("namaBank") String namaBank) {

        File laporanDir = new File(rekeningProfitReportLocation, "/" + namaBank);
        String[] daftar = laporanDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xls");
            }
        });

        modelMap.addAttribute("data", daftar);
        modelMap.addAttribute("namaBank", namaBank);
        return "profitrek/daftar";
    }


/*
@RequestMapping(value = "/profitblokir/{namaBank}/laporan/bank", method = RequestMethod.GET)
public String laporanBank(ModelMap modelMap, HttpServletRequest request, @PathVariable("namaBank") String namaBank) {

    List<Integer> listTahun = new ArrayList<Integer>();
    int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
    int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
    for (int i = 3; i >= 0; i--) {
        listTahun.add(tahunSekarang - i);
    }
    modelMap.addAttribute("listTahun", listTahun);
    modelMap.addAttribute("tahunSekarang", tahunSekarang);
    modelMap.addAttribute("bulanSekarang", bulanSekarang);

    return "profitblokir/laporan/bank";
}*/
/*
    @RequestMapping(value = "/profitblokir/{namaBank}/laporan/bank", method = RequestMethod.POST)
    public String laporanBankPost(HttpServletRequest request) {

        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));

        int start = 0;
        int size = 5000;
        int no = 1;

        long totalRow = profitBlokirDAO.countByMonth(Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<ProfitBlokir> listProfit = profitBlokirDAO.findByMonth(Month.valueOf(month), year, start, size);

            Sheet sheet = wb.createSheet("DETAIL PROFIT " + (j + 1));

            // Create a row and put some cells in it. Rows are 0 based.
            Row rowTitle = sheet.createRow(0);
            String title = "PROFIT INVESTA " + Month.valueOf(month).name() + " " + year;
            rowTitle.createCell(0).setCellValue(title);

            short i = 2;

            Row rowTh = sheet.createRow(i);

            // Or do it on one line.
            rowTh.createCell(0).setCellValue("NO");
            rowTh.createCell(1).setCellValue("USERNAME");
            rowTh.createCell(2).setCellValue("NAMA");
            rowTh.createCell(3).setCellValue("REKENING");
            rowTh.createCell(4).setCellValue("NAMA REKENING");
            rowTh.createCell(5).setCellValue("PROFIT");
            rowTh.createCell(6).setCellValue("TGL AKTIF");
            rowTh.createCell(7).setCellValue("BANK");
            i++;

            List<Row> listRow = new ArrayList<Row>();
            for (Profit p : listProfit) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(no);
                row.createCell(1).setCellValue(p.getUser().getUsername());
                row.createCell(2).setCellValue(p.getUser().getNama());
                row.createCell(3).setCellValue(p.getUser().getNomorRekening());
                row.createCell(4).setCellValue(p.getUser().getNamaRekening());
                row.createCell(5).setCellValue(p.getJumlah());
                row.createCell(6).setCellValue(p.getUser().getTanggalAktivasi());
                row.createCell(7).setCellValue(p.getUser().getBank().getNama());

                i++;
                no++;
            }

            start = start + size;
        }

        // Write the output to a file
        try {
            FileOutputStream fileOut = new FileOutputStream("d:/workbook.xls");
//            FileOutputStream fileOut = new FileOutputStream("/home/tediscript/Dropbox/jasoet/workbook.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/profitblokir/laporan/bank";
    }*/

}
