package com.example.mymodule.app2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zf on 2015/3/16.
 */
public class G {

    public static void main(String [] args){
//        System.out.println(setWinCodeRed("10,04,07,03,11","06,07"));
        for(int i = 0 ; i < 200; i ++){
            System.out.println(randomNumFromTotal(3));
        }
    }

    private static int randomNumFromTotal(int total) {
        int num = (int) (Math.random() * total);
        return num;
    }

    public static String setStrRed(String str) {
        return "<font color='red'>" + str + "</font>";
    }

    public static String setWinCodeRed(String drawCode, String code) {

        StringBuffer retBuf = new StringBuffer("");

        String[] drawCodes = drawCode.split(",");

        List<String> codes = new ArrayList<String>();

        for (String c : code.split(",")) {
            codes.add(c);
        }

        for (String d : drawCodes) {

            if (codes.contains(d)) {
                retBuf.append(setStrRed(d));
                retBuf.append(" ");
            } else {
                retBuf.append(d);
                retBuf.append(" ");
            }
        }

        return retBuf.toString();
    }
}
