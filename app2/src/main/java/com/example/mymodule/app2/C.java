package com.example.mymodule.app2;

/**
 * Created by zf on 2015/4/30.
 */
public class C {

    public static void main(String[] args) {
//        String winCode = "0,1,1";
//        String value = "1";
//        int count = 0;
//        for (String code : winCode.split(",")) {
//            if (code.equals(value)) {
//                count = count + 1;
//            }
//        }
//        System.out.println(count);
        String selectCode_ = "07,10*08,09";
        String[] temp = selectCode_.split("\\*");

        System.out.println(selectCode_.indexOf("1*"));
    }
}
