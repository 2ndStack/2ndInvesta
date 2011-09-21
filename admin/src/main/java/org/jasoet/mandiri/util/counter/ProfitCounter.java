package org.jasoet.mandiri.util.counter;

import org.jasoet.mandiri.domain.User;

import java.util.Calendar;

public class ProfitCounter {

    /*
   Profit Bulan pertama
   Tanggal 1 - 5  = 20%
   Tanggal 6 - 10 = 15%
   Tanggal 11 - 20 = 10%
   Tanggal 21 - 31 = 0%
    */
    public static int monthLimit() {
        return 18;
    }

    public static int firstMonthProfitPercentage(User u) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(u.getTanggalAktivasi());

        int tanggalAktif = cal.get(Calendar.DAY_OF_MONTH);

        if (tanggalAktif >= 1 && tanggalAktif <= 5) {
            return 20;
        } else if (tanggalAktif >= 6 && tanggalAktif <= 10) {
            return 15;
        } else if (tanggalAktif >= 11 && tanggalAktif <= 20) {
            return 10;
        } else {
            return 0;
        }

    }

    /*
   Profit Bulan ke 2 dan seterusnya     = 20%
    */
    public static int normalProfitPercentage() {
        return 20;
    }
}
