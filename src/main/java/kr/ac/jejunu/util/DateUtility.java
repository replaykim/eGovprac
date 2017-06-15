package kr.ac.jejunu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by blue on 2017-06-15.
 */

public class DateUtility {
    public static String compareDate(Date date) {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년MM월dd일");

        long tmp = today.getTime() - date.getTime();

        //하루 이후
        if (tmp > 1000 * 60 * 60 * 24) {
            return formatter.format(date);
        } else {
            //한시간이후
            if (tmp > 1000 * 60 * 60) {
                return (tmp / (1000 * 60 * 60)) + "시간 전";

            } else {
                //일분 이후
                if (tmp > 1000 * 60) {
                    return (tmp / (1000 * 60)) + "분 전";
                } else {
                    return "방금전";
                }
            }
        }
    }
}
