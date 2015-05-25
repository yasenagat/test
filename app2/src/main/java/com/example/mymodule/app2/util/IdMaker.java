package com.example.mymodule.app2.util;

/**
 * @author GaoFeng
 * @time 2014年1月13日
 *
 */

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class IdMaker {
    static long sn = 1;
    private static final int MAX_VALUE = 999998;
    private static final String FORMAT14 = "yyyyMMddHHmmss";
    private static final Format DF14 = new SimpleDateFormat(FORMAT14);

    private static final String FORMAT8 = "yyyyMMdd";    //日
    private static final Format DF8 = new SimpleDateFormat(FORMAT8);

    public static String Get_Recharge_OrderId() {
        return Get_Order_Id();
    }

    public static String Get_Trade_No() {
        return Get_Order_Id();
    }

    public static String Get_Order_Id() {
        return "TRN" + GetSN();
    }

    public static String Get_Ticket_Id() {
        return "TCK" + GetSN();
    }

    public static String Get_AAT_MSGID() {
        return "AAT" + GetSN();
    }

    public static String Get_SMS_MSGID() {
        return "SMS" + GetSN();
    }

    public static String Get_SN() {
        return GetSN();
    }

    public static String Get_DB_Id() {
        return "DB" + GetSN();
    }

    public static String Get_MSG_Id() {
        return "MSG" + GetSN();
    }

    public static String TimeFormat() {
        Date date = new Date();
        return DF14.format(date);
    }

    public static String DateFormat() {
        Date date = new Date();
        return DF8.format(date);
    }

    public static String LastDateFormat() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

        Date date = cal.getTime();

        return DF8.format(date);
    }

    static synchronized String GetSN() {
        sn = (sn + 1) % MAX_VALUE;
        return TimeFormat() + String.format("%06d", sn);
    }

    public static String GetUUID32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
