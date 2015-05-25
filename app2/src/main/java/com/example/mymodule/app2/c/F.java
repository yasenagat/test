package com.example.mymodule.app2.c;

import com.example.mymodule.app2.win.CodeEntity;
import com.example.mymodule.app2.win.CodeHisWinEntity;
import com.example.mymodule.app2.win.ICodeHisWin;
import com.example.mymodule.app2.win.SSQCodeHisWin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zf on 2015/4/1.
 */
public class F {

    public static void main(String args[]) {

        FileReader input;
        try {

            input = new FileReader("d:/3.txt");

            BufferedReader br = new BufferedReader(input);
            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
//                System.out.println("readline=" + line);

                if (line != null) {

                    String str[] = line.split("\\s");
//                    System.out.println("length : " + str.length);

                    Set<String> red_tuo = new HashSet<String>();
                    Set<String> blue_tuo = new HashSet<String>();

                    String code[] = str[2].split("\\|");

                    for (String red : code[0].split(",")) {
                        red_tuo.add(red);
                    }

                    for (String blue : code[1].split(",")) {
                        blue_tuo.add(blue);
                    }
                    SSQCodeHisWin.winCodeList.add(new CodeEntity(red_tuo, blue_tuo, str[1]));
                }

            }


//            System.out.println("getWinCodeList : " + new SSQCodeHisWin().getWinCodeList().size());

//            for (CodeEntity winEntity : new SSQCodeHisWin().getWinCodeList()) {
//
//                System.out.println(winEntity.getRed_tuo());
//            }

            ICodeHisWin iCodeHisWin = new SSQCodeHisWin();
            long start = System.currentTimeMillis();

            List<String> investCode = new ArrayList<String>();
            {

                FileReader input_ = new FileReader("d:/2.txt");

                BufferedReader br_ = new BufferedReader(input_);
                String line_ = br_.readLine();
                while (line_ != null) {
                    line_ = br_.readLine();
//                System.out.println("readline=" + line);

                    if (line_ != null) {

//                        System.out.println("line_ : " + line_);
                        investCode.add(line_);


//                        System.out.println(" time : " + (System.currentTimeMillis() - start));

//                        System.out.println("总期数 : " + codeHisWinEntity.getTotalIssue());


                    }

                }

            }

            System.out.println("investCode.size() : " + investCode.size());
            for (String code : investCode) {

                CodeHisWinEntity codeHisWinEntity = iCodeHisWin.calWinHis(code);
//                System.out.println("code : " + code);
                print(code, codeHisWinEntity);
            }


//            CodeHisWinEntity codeHisWinEntity = iCodeHisWin.calWinHis("01,02,03,04,05,06|01,02,03");
//
//
            System.out.println("time : " + (System.currentTimeMillis() - start));
//
//            System.out.println("总期数 : " + codeHisWinEntity.getTotalIssue());
//
//            for (CodeHisWinEntity.Item item : codeHisWinEntity.getList()) {
//                System.out.println(item.getWinLevel() + " : " + item.getTotalWinTimes() + " : " + item.getLastWinIssue());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void print(String code, CodeHisWinEntity codeHisWinEntity) {
        if (codeHisWinEntity.getList().get(0).getTotalWinTimes().intValue() > 0) {
            System.out.println("code : " + code + "  " + codeHisWinEntity.getList().get(0).getWinLevel() + " : " + codeHisWinEntity.getList().get(0).getTotalWinTimes() + " : " + codeHisWinEntity.getList().get(0).getLastWinIssue());
        }
//
//        if (codeHisWinEntity.getList().get(1).getTotalWinTimes().intValue() > 0) {
//            System.out.println(codeHisWinEntity.getList().get(1).getWinLevel() + " : " + codeHisWinEntity.getList().get(1).getTotalWinTimes() + " : " + codeHisWinEntity.getList().get(1).getLastWinIssue());
//        }

//                for (CodeHisWinEntity.Item item : codeHisWinEntity.getList()) {
//                    System.out.println(item.getWinLevel() + " : " + item.getTotalWinTimes() + " : " + item.getLastWinIssue());
//                }
    }

}
