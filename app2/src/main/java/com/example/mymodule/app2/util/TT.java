package com.example.mymodule.app2.util;

/**
 * Created by zf on 2015/3/24.
 */
public class TT {

    public static void main(String[] args) {

        try {
//            String key = "3A3132333435363738393B3132333435";
//            System.out.println(key.length());
//            String res = MessageCodeHelper.EncodeMode3("1234", key);
//
////            String res = "OIFxDlZU2OhfMyJO/vyAow==";
//            System.out.println(res);
//
//            MessageCodeHelper.DecodeMode3(res, key);

            System.out.println(StringTools.MD5EncodeToHex("com.dgcy.lty.android.sdfc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
