package org.jasoet.mandiri.util;

import java.io.File;

/**
 * Author : Deny Prasetyo
 * E-Mail : jasoet87@gmail.com
 * Twitter : @jasoet
 */
public class ConfigUtil {
    private String reportProfitLocation;
    private String reportBonusLocation;

    private File profitLocation;
    private File bonusLocation;

    private String reportProfitRekLocation;
    private String reportBonusRekLocation;

    private File profitRekLocation;
    private File bonusRekLocation;

    private String reportBackupLocation;

    private File backupLocation;

    public String getReportBackupLocation() {
        return reportBackupLocation;
    }

    public void setReportBackupLocation(String reportBackupLocation) {
        this.reportBackupLocation = reportBackupLocation;
        File f = new File(this.reportBackupLocation);
        if (f.exists() || f.isDirectory()) {
            backupLocation = f;
        } else {
            backupLocation = null;
        }
    }

    public File getBackupLocation() {
        return backupLocation;
    }

    public void setBackupLocation(File backupLocation) {
        this.backupLocation = backupLocation;
    }

    public File getProfitLocation() {
        return profitLocation;
    }

    public void setProfitLocation(File profitLocation) {
        this.profitLocation = profitLocation;
    }

    public File getBonusLocation() {
        return bonusLocation;
    }

    public void setBonusLocation(File bonusLocation) {
        this.bonusLocation = bonusLocation;
    }

    public String getReportProfitLocation() {
        return reportProfitLocation;
    }

    public void setReportProfitLocation(String reportProfitLocation) {
        this.reportProfitLocation = reportProfitLocation;
        File f = new File(this.reportProfitLocation);
        if (f.exists() || f.isDirectory()) {
            profitLocation = f;
        } else {
            profitLocation = null;
        }
    }

    public String getReportBonusLocation() {
        return reportBonusLocation;
    }

    public void setReportBonusLocation(String reportBonusLocation) {
        this.reportBonusLocation = reportBonusLocation;
        File f = new File(this.reportBonusLocation);
        if (f.exists() && f.isDirectory()) {
            bonusLocation = f;
        } else {
            bonusLocation = null;
        }
    }

    public String getReportProfitRekLocation() {
        return reportProfitRekLocation;
    }

    public void setReportProfitRekLocation(String reportProfitRekLocation) {
        this.reportProfitRekLocation = reportProfitRekLocation;
        File f = new File(this.reportProfitRekLocation);
        if (f.exists() || f.isDirectory()) {
            profitRekLocation = f;
        } else {
            profitRekLocation = null;
        }
    }

    public String getReportBonusRekLocation() {
        return reportBonusRekLocation;
    }

    public void setReportBonusRekLocation(String reportBonusRekLocation) {
        this.reportBonusRekLocation = reportBonusRekLocation;
        File f = new File(this.reportBonusRekLocation);
        if (f.exists() || f.isDirectory()) {
            bonusRekLocation = f;
        } else {
            bonusRekLocation = null;
        }
    }

    public File getProfitRekLocation() {
        return profitRekLocation;
    }

    public void setProfitRekLocation(File profitRekLocation) {
        this.profitRekLocation = profitRekLocation;
    }

    public File getBonusRekLocation() {
        return bonusRekLocation;
    }

    public void setBonusRekLocation(File bonusRekLocation) {
        this.bonusRekLocation = bonusRekLocation;
    }
}
