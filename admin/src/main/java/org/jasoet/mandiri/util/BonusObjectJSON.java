package org.jasoet.mandiri.util;

import org.jasoet.mandiri.domain.Bonus;

import java.util.ArrayList;
import java.util.List;

public class BonusObjectJSON {
    private int sEcho;
    private long iTotalRecords;
    private long iTotalDisplayRecords;
    private List<String[]> aaData;

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public BonusObjectJSON(List<Bonus> data) {
        aaData = new ArrayList<String[]>();
        for (Bonus s : data) {
            aaData.add(s.getDataInArray());
        }
    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }


    public List<String[]> getAaData() {
        return aaData;
    }

    public void setAaData(List<String[]> aaData) {
        this.aaData = aaData;
    }
}
