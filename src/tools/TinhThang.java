/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Calendar;

/**
 *
 * @author hau
 */
public class TinhThang {

    public static boolean isSixMonthsAgo(Calendar referenceDate, Calendar dateToBeTested) {
        int year1 = referenceDate.get(Calendar.YEAR);
        int month1 = referenceDate.get(Calendar.MONTH);

        int year2 = dateToBeTested.get(Calendar.YEAR);
        int month2 = dateToBeTested.get(Calendar.MONTH);
        System.out.println("tools.TinhThang.isSixMonthsAgo(): " + ((year1 * 12 + month1) - (year2 * 12 + month2)));
        if((year1 * 12 + month1) - (year2 * 12 + month2) > 6) {
            return true;
        }
        return false;
    }
}
