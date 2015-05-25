package com.example.mymodule.app2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zf on 2015/4/20.
 */
public class D {


    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("0,1,2,3,4,5,6,7,8,9");
        list.add("0,1,2,3,4,5,6,7,8,9");
        list.add("0,1,2,3,4,5,6,7,8,9");

        List<String> result = new ArrayList<String>();

        Descartes(list, result);

        List<String> _result = new ArrayList<String>();

        select_3Butong(result, _result);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("===========================");

        for (String s : _result) {
            System.out.println(s);
        }

        System.out.println(_result.size());
    }

    public static void Descartes(List<String> str_list, List<String> result) {
        ArrayList<String[]> result_string_list = new ArrayList<String[]>();

        int index = 0;

        while (index < str_list.size()) {
            String[] current_str = str_list.get(index).split(",");

            if (index == 0) {
                for (int i = 0; i < current_str.length; ++i) {
                    String[] tmp = new String[str_list.size()];
                    tmp[index] = current_str[i];
                    result_string_list.add(tmp);
                }
            } else {

                ArrayList<String[]> tmp_string_list = (ArrayList<String[]>) result_string_list.clone();
                result_string_list.clear();

                for (int i = 0; i < tmp_string_list.size(); ++i) {
                    for (int k = 0; k < current_str.length; ++k) {

                        String[] tmp = tmp_string_list.get(i);
                        tmp[index] = current_str[k];
                        result_string_list.add(tmp.clone());
                    }
                }
            }
            index++;
        }

        for (int i = 0; i < result_string_list.size(); ++i) {

            StringBuffer sb = new StringBuffer("");
            for (int j = 0; j < result_string_list.get(i).length; j++) {
                sb.append(result_string_list.get(i)[j]);
                if (j != result_string_list.get(i).length - 1) {
                    sb.append(",");
                }
            }
            result.add(sb.toString());
        }
    }

    public static void delete_3Tong_3Butong(List<String> str_list, List<String> result) {

        String[] num;
        for (int i = 0; i < str_list.size(); i++) {

            num = str_list.get(i).split(",");

            if (num.length == 3) {
                if (num[0].equals(num[1]) && num[0].equals(num[2])) {

                } else if (!num[0].equals(num[1]) && !num[0].equals(num[2]) && !num[1].equals(num[2])) {

                } else {
                    result.add(str_list.get(i));
                }
            }
        }
    }

    public static void select_3Butong(List<String> str_list, List<String> result) {

        String[] num;
        for (int i = 0; i < str_list.size(); i++) {

            num = str_list.get(i).split(",");

            if (num.length == 3) {
                if (!num[0].equals(num[1]) && !num[0].equals(num[2]) && !num[1].equals(num[2])) {
                    result.add(str_list.get(i));
                }
            }
        }
    }

    public static void Descartes_(List<String> str_list, List<String> result) {
        Descartes(str_list, result);

        //delete repeat deal
        String one[] = null, two[] = null;
        String temp = "";
        //sort
        List<String> list_result = new ArrayList<String>();
        List<String> list_result_real = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++) {
            one = result.get(i).split(",");
            if (Integer.parseInt(one[0]) > Integer.parseInt(one[1])) {
                temp = one[0];
                one[0] = one[1];
                one[1] = temp;
            }
            list_result.add(one[0] + "," + one[1]);
        }
        //delete repeat
        for (int i = 0; i < list_result.size(); i++) {
            if (!list_result_real.contains(list_result.get(i))) {
                list_result_real.add(list_result.get(i));
            }
        }
        result.clear();
        result.addAll(list_result_real);
    }

    public static void Descartes___(List<String> str_list, List<String> result) {

        ArrayList<String[]> result_string_list = new ArrayList<String[]>();

        int index = 0;

        while (index < str_list.size()) {

            //12,34=>01,02
            String[] current_str = str_list.get(index).split(",");

            if (index == 0) {
                for (int i = 0; i < current_str.length; ++i) {
                    String[] tmp = new String[str_list.size()];
                    tmp[index] = current_str[i];
                    result_string_list.add(tmp);
                }
            } else {

                ArrayList<String[]> tmp_string_list = (ArrayList<String[]>) result_string_list.clone();
                result_string_list.clear();

                for (int i = 0; i < tmp_string_list.size(); ++i) {
                    for (int k = 0; k < current_str.length; ++k) {

                        String[] tmp = tmp_string_list.get(i);

                        tmp[index] = current_str[k];
                        result_string_list.add(tmp.clone());

                    }
                }
            }
            index++;
        }

        for (int i = 0; i < result_string_list.size(); ++i) {

            StringBuffer sb = new StringBuffer("");

            if (!isRepeat(result_string_list.get(i))) {
                for (int j = 0; j < result_string_list.get(i).length; j++) {
//                    System.out.println(" i : " + i + " : " + result_string_list.get(i)[j]);
                    sb.append(result_string_list.get(i)[j]);
                    if (j != result_string_list.get(i).length - 1) {
                        sb.append(",");
                    }
                }
                result.add(sb.toString());
            }

        }
    }

    private static boolean isRepeat(String[] strs) {

        Set<String> set = new HashSet<String>();

        for (String str : strs) {
            set.add(str);
        }

        if (strs.length == set.size()) {
            return false;
        } else {
            return true;
        }
    }

}
