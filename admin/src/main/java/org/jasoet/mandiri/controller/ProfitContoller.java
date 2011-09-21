package org.jasoet.mandiri.controller;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jasoet.mandiri.dao.BankDAO;
import org.jasoet.mandiri.dao.ConfigPropertyDAO;
import org.jasoet.mandiri.dao.ProfitBlokirDAO;
import org.jasoet.mandiri.dao.ProfitDAO;
import org.jasoet.mandiri.domain.Bank;
import org.jasoet.mandiri.domain.ConfigProperty;
import org.jasoet.mandiri.domain.Profit;
import org.jasoet.mandiri.util.ProfitObjectJSON;
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
public class ProfitContoller {
/*------------------------------ Fields ------------------------------*/

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProfitContoller.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    private ProfitDAO profitDAO;
    private BankDAO bankDAO;
    private ConfigPropertyDAO configPropertyDAO;
    private ProfitBlokirDAO profitBlokirDAO;

    /* --------------------- Getter and Setter ---------------------*/

    public ProfitBlokirDAO getProfitBlokirDAO() {
        return profitBlokirDAO;
    }

    @Autowired
    public void setProfitBlokirDAO(ProfitBlokirDAO profitBlokirDAO) {
        this.profitBlokirDAO = profitBlokirDAO;
    }

    public ConfigPropertyDAO getConfigPropertyDAO() {
        return configPropertyDAO;
    }

    @Autowired
    public void setConfigPropertyDAO(ConfigPropertyDAO configPropertyDAO) {
        this.configPropertyDAO = configPropertyDAO;
    }

    @Autowired
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    @Autowired
    public void setProfitDAO(ProfitDAO profitDAO) {
        this.profitDAO = profitDAO;
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

    @RequestMapping(value = "/profit/all", method = RequestMethod.GET)
    public String indexAll(ModelMap modelMap) {

        List<Integer> listTahun = new ArrayList<Integer>();
        int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
        for (int i = 3; i >= 0; i--) {
            listTahun.add(tahunSekarang - i);
        }

        modelMap.addAttribute("listTahun", listTahun);
        modelMap.addAttribute("tahunSekarang", tahunSekarang);
        modelMap.addAttribute("bulanSekarang", bulanSekarang);
        modelMap.addAttribute("namaBank", "all");
        modelMap.addAttribute("mainNav", "profit");

        return "profit/indexAll";
    }

    @RequestMapping(value = "/profit/{namaBank}", method = RequestMethod.GET)
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

        modelMap.addAttribute("listTahun", listTahun);
        modelMap.addAttribute("tahunSekarang", tahunSekarang);
        modelMap.addAttribute("bulanSekarang", bulanSekarang);
        modelMap.addAttribute("namaBank", namaBank.toLowerCase());
        modelMap.addAttribute("mainNav", "profit");

        return "profit/index";
    }

    @RequestMapping(value = "/profit/{namaBank}/index.json", method = RequestMethod.GET)
    public
    @ResponseBody
    ProfitObjectJSON showJSON(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
        String sSearch = request.getParameter("sSearch") != null ? request.getParameter("sSearch") : "";
        int iDisplayStart = request.getParameter("iDisplayStart") != null ? Integer.parseInt(request.getParameter("iDisplayStart")) : 0;
        int iDisplayLength = request.getParameter("iDisplayLength") != null ? Integer.parseInt(request.getParameter("iDisplayLength")) : 0;
        int sEcho = request.getParameter("sEcho") != null ? Integer.parseInt(request.getParameter("sEcho")) : 0;
        String sSearch_3 = request.getParameter("sSearch_3").isEmpty() ? ProfitStatus.COMPLETED.name() : request.getParameter("sSearch_3");
        String sSearch_1 = request.getParameter("sSearch_1");

        ProfitObjectJSON result = null;

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


        try {
            ProfitStatus status = ProfitStatus.valueOf(sSearch_3);
            List<Profit> data = profitDAO.findByLimit(sSearch, selected, status, Month.valueOf(month), year, iDisplayStart, iDisplayLength);
            result = new ProfitObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = profitDAO.countBy(sSearch, selected, status, Month.valueOf(month), year);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        } catch (IllegalArgumentException ex) {
            List<Profit> data = profitDAO.findByLimit(sSearch, selected, ProfitStatus.PENDING, Month.valueOf(month), year, iDisplayStart, iDisplayLength);
            result = new ProfitObjectJSON(data);
            result.setsEcho(sEcho + 1);
            long resultCount = profitDAO.countBy(sSearch, selected, ProfitStatus.PENDING, Month.valueOf(month), year);
            result.setiTotalRecords(resultCount);
            result.setiTotalDisplayRecords(resultCount);
        }


        return result;
    }

    @RequestMapping(value = "/profit/{namaBank}/{id}/show", method = RequestMethod.GET)
    public String show(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Profit profit = profitDAO.get(id);
        if (profit == null) {
            return "redirect:/profit/" + namaBank;
        }

        modelMap.addAttribute("mainNav", "profit");
        modelMap.addAttribute("namaBank", namaBank);
        modelMap.addAttribute("bulan", profit.getBulan().name());
        modelMap.addAttribute("status", profit.getStatus().name());
        modelMap.addAttribute("data", profit);
        return "profit/show";
    }

    @RequestMapping(value = "/profit/{namaBank}/{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Profit profit = profitDAO.get(id);
        modelMap.addAttribute("profit", profit);
        String bulan = profit.getBulan().name();
        modelMap.addAttribute("bulan", bulan + "-" + profit.getTahun());

        modelMap.addAttribute("mainNav", "profit");
        modelMap.addAttribute("sidebar", "profit");
        modelMap.addAttribute("namaBank", namaBank);


        return "profit/edit";
    }

    @RequestMapping(value = "/profit/{namaBank}/{id}/COMPLETED", method = RequestMethod.GET)
    public String setCompleted(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Profit profit = profitDAO.get(id);
        profit.setStatus(ProfitStatus.COMPLETED);

        profitDAO.merge(profit);

        modelMap.addAttribute("mainNav", "masterData");
        modelMap.addAttribute("sidebar", "profit");

        return "redirect:/profit/" + namaBank + "/" + id + "/show";
    }

    @RequestMapping(value = "/profit/{namaBank}/{id}/PENDING", method = RequestMethod.GET)
    public String setPending(ModelMap modelMap, @PathVariable("id") Long id, @PathVariable("namaBank") String namaBank) {
        Profit profit = profitDAO.get(id);
        profit.setStatus(ProfitStatus.PENDING);

        profitDAO.merge(profit);

        modelMap.addAttribute("mainNav", "masterData");
        modelMap.addAttribute("sidebar", "profit");

        return "redirect:/profit/" + namaBank + "/" + id + "/show";
    }


    @RequestMapping(value = "/profit/{namaBank}", method = RequestMethod.POST)
    public String editPost(HttpServletRequest request, @PathVariable("namaBank") String namaBank) {
        Long id = Long.valueOf(request.getParameter("id"));
        Profit profit = profitDAO.get(id);
        ProfitStatus status = ProfitStatus.valueOf(request.getParameter("status"));
        String keterangan = request.getParameter("keterangan");

        profit.setStatus(status);
        profit.setKeterangan(keterangan);
        profitDAO.merge(profit);

        return "redirect:/profit/" + namaBank + "/" + id + "/show";
    }

    @RequestMapping(value = "/profit/{namaBank}/masal", method = RequestMethod.GET)
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
        modelMap.addAttribute("mainNav", "profit");
        modelMap.addAttribute("namaBank", namaBank);

        return "profit/masal";
    }

    @RequestMapping(value = "/profit/{namaBank}/masal", method = RequestMethod.POST)
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

        profitDAO.editMasal(selected, Month.valueOf(month), year, status);

        return "redirect:/profit/" + namaBank;
    }

    @RequestMapping(value = "/profit/{namaBank}/laporan", method = RequestMethod.GET)
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
        modelMap.addAttribute("mainNav", "laporan");
        modelMap.addAttribute("namaBank", namaBank);

        ConfigProperty property = configPropertyDAO.findByKeyz("biayaTransfer");
        String biayaTransfer = "0";
        if (property != null) {
            biayaTransfer = property.getText();
        }
        modelMap.addAttribute("biayaTransfer", biayaTransfer);

        return "profit/laporan";
    }

    @RequestMapping(value = "/profit/manual", method = RequestMethod.GET)
    public String manual(ModelMap modelMap, HttpServletRequest request) {

        List<Integer> listTahun = new ArrayList<Integer>();
        int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
        int bulanSekarang = Calendar.getInstance().get(Calendar.MONTH);
        for (int i = 3; i >= 0; i--) {
            listTahun.add(tahunSekarang - i);
        }
        modelMap.addAttribute("listTahun", listTahun);
        modelMap.addAttribute("tahunSekarang", tahunSekarang);
        modelMap.addAttribute("bulanSekarang", bulanSekarang);
        modelMap.addAttribute("mainNav", "profit");

        return "profit/manual";
    }

    @RequestMapping(value = "/profit/manual", method = RequestMethod.POST)
    public String manualPost(HttpServletRequest request) {

        final int transMonth = Integer.valueOf(request.getParameter("month"));
        final int transYear = Integer.valueOf(request.getParameter("year"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                profitDAO.monthlyProfit(Month.valueOf(transMonth), transYear);
                profitBlokirDAO.monthlyProfit(Month.valueOf(transMonth), transYear);
            }
        }).start();


        return "profit/manual_success";
    }

    //laporan 2
    @RequestMapping(value = "/profit/{namaBank}/laporan", method = RequestMethod.POST)
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


        String sbiayaTransfer = request.getParameter("biayaTransfer");
        ConfigProperty cf = configPropertyDAO.findByKeyz("biayaTransfer");
        cf.setText(sbiayaTransfer);
        configPropertyDAO.merge(cf);

        int biayaTransfer = Integer.valueOf(sbiayaTransfer);

        int start = 0;
        int size = 500000;
        int no = 1;

        int totalProfit = 0;
        int totalNettProfit = 0;

        long totalRow = profitDAO.countByMonth(selected, Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<Profit> listProfit = profitDAO.findByMonth(selected, Month.valueOf(month), year, start, size);
            short i = 2;

            Sheet sheet = wb.createSheet("DETAIL PROFIT " + (j + 1));
            Row rowTitle = sheet.createRow(0);
            String title = "PROFIT Bank " + namaBank + " " + Month.valueOf(month).name() + " " + year;
            rowTitle.createCell(0).setCellValue(title);
            Row rowTh = sheet.createRow(i);
            rowTh.createCell(0).setCellValue("NO");
            rowTh.createCell(1).setCellValue("USERNAME");
            rowTh.createCell(2).setCellValue("NAMA");
            rowTh.createCell(3).setCellValue("REKENING");
            rowTh.createCell(4).setCellValue("PROFIT");
            rowTh.createCell(5).setCellValue("NETT PROFIT");

            i++;

            List<Row> listRow = new ArrayList<Row>();
            for (Profit p : listProfit) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(no);
                row.createCell(1).setCellValue(p.getUser().getUsername());
                row.createCell(2).setCellValue(p.getUser().getNama());
                row.createCell(3).setCellValue(p.getUser().getNomorRekening());
                row.createCell(4).setCellValue(p.getJumlah());
                row.createCell(5).setCellValue(p.getJumlah() - biayaTransfer);

                totalProfit = totalProfit + p.getJumlah();
                totalNettProfit = totalNettProfit + (p.getJumlah() - biayaTransfer);

                i++;
                no++;

            }
            if (j == (page - 1)) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue("");
                row.createCell(2).setCellValue("");
                row.createCell(3).setCellValue("TOTAL");
                row.createCell(4).setCellValue(totalProfit);
                row.createCell(5).setCellValue(totalNettProfit);
            }

            start = start + size;
        }

        // Write the output to a file
        try {
            dateFormat = new SimpleDateFormat("yyyyMMdd");
            File outputFile = new File(profitReportLocation, namaBank.toLowerCase() + "/" + dateFormat.format(new Date()) + "_" + "profit_" + namaBank + "_(" + Month.valueOf(month).name() + "-" + year + ").xls");
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/profit/" + namaBank + "/list";
    }


    @RequestMapping(value = "/profit/{namaBank}/list", method = RequestMethod.GET)
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
        return "profit/daftar";
    }


    @RequestMapping(value = "/profit/{namaBank}/laporanrek", method = RequestMethod.GET)
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
        modelMap.addAttribute("mainNav", "laporanRekening");
        modelMap.addAttribute("namaBank", namaBank);

        ConfigProperty property = configPropertyDAO.findByKeyz("biayaTransfer");
        String biayaTransfer = "0";
        if (property != null) {
            biayaTransfer = property.getText();
        }
        modelMap.addAttribute("biayaTransfer", biayaTransfer);

        return "profitrek/laporan";
    }

    @RequestMapping(value = "/profit/{namaBank}/laporanrek", method = RequestMethod.POST)
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

        String sbiayaTransfer = request.getParameter("biayaTransfer");
        ConfigProperty cf = configPropertyDAO.findByKeyz("biayaTransfer");
        cf.setText(sbiayaTransfer);
        configPropertyDAO.merge(cf);

        int biayaTransfer = Integer.valueOf(sbiayaTransfer);

        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));

        int start = 0;
        int size = 500000;
        int no = 1;

        long totalRow = profitDAO.countByMonthRekening(selected, Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();
        Workbook wb2 = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<Map<String, Object>> listProfit = profitDAO.findByMonthRekening(selected, Month.valueOf(month), year, start, size);
            short i = 2;


            //laporan 3
            Sheet sheet = wb.createSheet("DETAIL PROFIT " + (j + 1));
            Row rowTitle = sheet.createRow(0);
            String title = "PROFIT Bank " + namaBank + " " + Month.valueOf(month).name() + " " + year;
            rowTitle.createCell(0).setCellValue(title);
            Row rowTh = sheet.createRow(i);
            rowTh.createCell(0).setCellValue("NO");
            rowTh.createCell(1).setCellValue("NAMA");
            rowTh.createCell(2).setCellValue("REKENING");
            rowTh.createCell(3).setCellValue("PROFIT");
            rowTh.createCell(4).setCellValue("NETT PROFIT");
            rowTh.createCell(5).setCellValue("SOSIAL");
            rowTh.createCell(6).setCellValue("JML ID");
            rowTh.createCell(7).setCellValue("KETERANGAN");

            //laporan 4
            Sheet sheet2 = wb2.createSheet("DETAIL PROFIT " + (j + 1));
            Row rowTitle2 = sheet.createRow(0);
            String title2 = "PROFIT Bank " + namaBank + " " + Month.valueOf(month).name() + " " + year;
            rowTitle2.createCell(0).setCellValue(title2);
            Row rowTh2 = sheet2.createRow(i);

            //beda tempat untuk BRI
            if (namaBank.toLowerCase().equals("bri")) {
                rowTh2.createCell(0).setCellValue("NO");
                rowTh2.createCell(1).setCellValue("REKENING");
                rowTh2.createCell(2).setCellValue("JUMLAH");
                rowTh2.createCell(3).setCellValue("NAMA");
                rowTh2.createCell(4).setCellValue("KETERANGAN");
            } else {
                rowTh2.createCell(0).setCellValue("NO");
                rowTh2.createCell(1).setCellValue("NAMA");
                rowTh2.createCell(2).setCellValue("REKENING");
                rowTh2.createCell(3).setCellValue("JUMLAH");
                rowTh2.createCell(4).setCellValue("KETERANGAN");
            }


            i++;

            for (Map<String, Object> p : listProfit) {
                int profit = Integer.parseInt("" + p.get("jumlah"));
                int jumlahId = Integer.parseInt("" + p.get("jumlahId"));
                int nettProfit = profit - (jumlahId * biayaTransfer);
                String ssosial = "0";
                if (profit >= 1000000) {
                    int sosial = (int) (0.025 * profit);
                    nettProfit = nettProfit - sosial;
                    ssosial = "" + sosial;
                }

                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(no);
                row.createCell(1).setCellValue("" + p.get("namaRekening"));
                row.createCell(2).setCellValue("" + p.get("nomorRekening"));
                row.createCell(3).setCellValue("" + p.get("jumlah"));
                row.createCell(4).setCellValue(nettProfit);
                row.createCell(5).setCellValue(ssosial);
                row.createCell(6).setCellValue("" + p.get("jumlahId"));
                String keterangan = "profit bulan " + Month.valueOf(month).name() + " " + year;
                row.createCell(7).setCellValue("" + (p.get("keterangan")!=null? p.get("keterangan"):""));

//                [nama bank, biaya transfer, jumlah id yg di group]
                Row row2 = sheet2.createRow(i);

                if (namaBank.toLowerCase().equals("bri")) {
                    row2.createCell(0).setCellValue(no);
                    row2.createCell(1).setCellValue("" + p.get("nomorRekening"));
                    row2.createCell(2).setCellValue("" + nettProfit);
                    row2.createCell(3).setCellValue("" + p.get("namaRekening"));
                    String keterangan2 = "profit bulan " + Month.valueOf(month).name() + " " + year;
                    row2.createCell(4).setCellValue(keterangan2);
                } else {
                    row2.createCell(0).setCellValue(no);
                    row2.createCell(1).setCellValue("" + p.get("namaRekening"));
                    row2.createCell(2).setCellValue("" + p.get("nomorRekening"));
                    row2.createCell(3).setCellValue("" + nettProfit);
                    String keterangan2 = "profit bulan " + Month.valueOf(month).name() + " " + year;
                    row2.createCell(4).setCellValue(keterangan2);
                }


                i++;
                no++;
            }

            start = start + size;
        }

        // Write the output to a file
        try {
            dateFormat = new SimpleDateFormat("yyyyMMdd");

            File outputFile = new File(rekeningProfitReportLocation, namaBank.toLowerCase() + "/" + dateFormat.format(new Date()) + "_" + "laporan_rekap_profit_" + namaBank + "_(" + Month.valueOf(month).name() + "-" + year + ").xls");
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            wb.write(fileOut);
            fileOut.close();

            File outputFile2 = new File(rekeningProfitReportLocation, namaBank.toLowerCase() + "/" + dateFormat.format(new Date()) + "_" + "laporan_paryroll_" + namaBank + "_(" + Month.valueOf(month).name() + "-" + year + ").xls");
            FileOutputStream fileOut2 = new FileOutputStream(outputFile2);
            wb2.write(fileOut2);
            fileOut2.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/profit/" + namaBank + "/listrek";
    }

    @RequestMapping(value = "/profit/{namaBank}/listrek", method = RequestMethod.GET)
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
        modelMap.addAttribute("mainNav", "asipLaporan");
        return "profitrek/daftar";
    }


/*
@RequestMapping(value = "/profit/{namaBank}/laporan/bank", method = RequestMethod.GET)
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

    return "profit/laporan/bank";
}*/
/*
    @RequestMapping(value = "/profit/{namaBank}/laporan/bank", method = RequestMethod.POST)
    public String laporanBankPost(HttpServletRequest request) {

        int month = Integer.valueOf(request.getParameter("month"));
        int year = Integer.valueOf(request.getParameter("year"));

        int start = 0;
        int size = 5000;
        int no = 1;

        long totalRow = profitDAO.countByMonth(Month.valueOf(month), year);
        long page = totalRow / size;
        if (totalRow % size != 0) {
            page = page + 1;
        }

        Workbook wb = new HSSFWorkbook();

        for (int j = 0; j < page; j++) {
            List<Profit> listProfit = profitDAO.findByMonth(Month.valueOf(month), year, start, size);

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

        return "redirect:/profit/laporan/bank";
    }*/

}
