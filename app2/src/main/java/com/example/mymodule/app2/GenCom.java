package com.example.mymodule.app2;

/**
 * Created by zf on 2015/3/6.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GenCom {
    //组合算法+笛卡尔积

    //计算字符串笛卡尔积(一维数组乘积)
    //使用堆栈算法，不使用递归
    public static void Descartes(ArrayList<String> str_list, ArrayList<String> result) {
//        ArrayList<char[]> result_char_list = new ArrayList<char[]>();
        ArrayList<String[]> result_string_list = new ArrayList<String[]>();

        int index = 0;

        while (index < str_list.size()) {
//            System.out.println("index : " + index);
            String cur_str = str_list.get(index);

            //12,34=>01,02
            String[] current_str = str_list.get(index).split(",");

//            System.out.println("cur_str : " + cur_str);
            if (index == 0) {
                for (int i = 0; i < current_str.length; ++i) {
//                    System.out.println(i);
                    String[] tmp = new String[str_list.size()];
                    tmp[index] = current_str[i];
                    result_string_list.add(tmp);
//                    char[] tmp = new char[str_list.size()];
//                    tmp[index] = cur_str.charAt(i);
//                    result_char_list.add(tmp);
                }
            } else {
//                ArrayList<char[]> tmp_char_list = (ArrayList<char[]>) result_char_list.clone();
//                result_char_list.clear();

                ArrayList<String[]> tmp_string_list = (ArrayList<String[]>) result_string_list.clone();
                result_string_list.clear();

                for (int i = 0; i < tmp_string_list.size(); ++i) {
                    for (int k = 0; k < current_str.length; ++k) {

                        String[] tmp = tmp_string_list.get(i);
//                        System.out.println("tmp.length : " + tmp.length);
//                        System.out.println("index : " + index);
//                        System.out.println("k : " + k);
                        tmp[index] = current_str[k];
                        result_string_list.add(tmp.clone());

//                        char[] tmp = tmp_char_list.get(i);
//                        tmp[index] = cur_str.charAt(k);
//                        result_char_list.add(tmp.clone());
                    }
                }
            }
            index++;
        }

//        System.out.println("result_char_list.size() : " + result_char_list.size());
        for (int i = 0; i < result_string_list.size(); ++i) {

//            System.out.println(result_string_list.get(i));
            StringBuffer sb = new StringBuffer("");
            for (int j = 0; j < result_string_list.get(i).length; j++) {
                System.out.println(" i : " + i + " : " + result_string_list.get(i)[j]);
                sb.append(result_string_list.get(i)[j]);
                if (j != result_string_list.get(i).length - 1) {
                    sb.append(",");
                }
            }

            result.add(sb.toString());
//            result.add(String.valueOf(result_char_list.get(i)));
        }
    }

    //按标志位组合字符串
    private static void MakeCom(String[] str_list, boolean[] flags, ArrayList<String> result) {
        ArrayList<String> choice_str = new ArrayList<String>();

        for (int i = 0; i < str_list.length; ++i) {
            if (flags[i] == true) {
                choice_str.add(str_list[i]);
            }
        }

        //选择完，计算笛卡尔积
        Descartes(choice_str, result);
    }

    //组合算法,n为串数
    //核心算法:选号标记移位算法(选择该位为true,不选为false)，选号标记总最左(栈底)开始，循环移至最右(栈顶)。
    //移动选号位的选择：从最左边(栈底)起向右(栈顶)查询，最近一个上位有空位(false)的选号位(true)
    //将选定的选号位向右移一位(升栈)，左边标记位全部降至左边(栈底)
    //循环上面两个流程至全部选号位移至最右(栈顶)
    public static void GenCom(String[] str_list, int n, ArrayList<String> result) {
        if (n <= 0 || n > str_list.length) {
            return;
        }

        //标志数组
        boolean[] flags = new boolean[str_list.length];

        //初始化前n是选号位
        for (int i = 0; i < n; i++) {
            flags[i] = true;
        }
        //后面都是非选号位
        for (int i = n; i < str_list.length; i++) {
            flags[i] = false;
        }

        //计算初始化组合
        MakeCom(str_list, flags, result);

        while (true) {
            int num_count = 0;  //从最左边起，连续邻位true true的个数
            boolean move = false;   //是否进行了移位

            //找邻位true false组合
            for (int i = 0; i < str_list.length - 1; ++i)   //前置1位防越界
            {
                if (flags[i]) {
                    if (flags[i + 1]) //true true邻位组合，继续向右查找
                    {
                        num_count++;
                    } else        //第一个可升位位置
                    {
                        //相邻选号位true false变换为false true
                        //实现升栈
                        flags[i] = false;
                        flags[i + 1] = true;

                        //左边选号位将至栈底
                        for (int j = 0; j < num_count; j++) {
                            flags[j] = true;
                        }
                        for (int j = num_count; j < i; j++) {
                            flags[j] = false;
                        }

                        move = true;
                        break;
                    }
                }
            }

            if (move) {
                MakeCom(str_list, flags, result);
            } else {
                break;
            }
        }
    }

    public static void Descartes_(ArrayList<String> str_list, ArrayList<String> result) {
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

    public static void Descartes___(ArrayList<String> str_list, ArrayList<String> result) {

        ArrayList<String[]> result_string_list = new ArrayList<String[]>();

        int index = 0;

        while (index < str_list.size()) {

            String cur_str = str_list.get(index);

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
                    System.out.println(" i : " + i + " : " + result_string_list.get(i)[j]);
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

    public static void main(String[] args) {
//        String src_str = "01,02#03#04,05,06";
//
//        String[] str_list = src_str.split("#");
//
//        ArrayList<String> result = new ArrayList<String>();
//
////        for (int i = 1; i <= str_list.length; ++i) {
////            GenCom.GenCom(str_list, i, result);
////        }
//        GenCom.GenCom(str_list, 3, result);
//        for (int i = 0; i < result.size(); ++i) {
//            System.out.print(i + " : " + result.get(i) + "\r\n");
//        }

        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("0,1,3");
        l1.add("0,1,3");
        l1.add("0,1,3");
        ArrayList<String> l2 = new ArrayList<String>();
        Descartes___(l1, l2);

        for (String s : l2) {
            System.out.println("s : " + s);
        }

//        ArrayList<String> l1 = new ArrayList<String>();
//        l1.add("0,1,");
//        l1.add("0,1");
//        ArrayList<String> l2 = new ArrayList<String>();
//        Descartes_(l1, l2);
//
//        for (String s : l2) {
//            System.out.println("s : " + s);
//        }

//        System.out.println(Math.max(100,100));;

//        for(int i = 0 ; i < 50 ; i ++){
//            System.out.println(new Random().nextInt(1));
//        }
//        float money = Float.valueOf("0.002");
//
//        System.out.println((money * 100) < Float.valueOf("1"));
//        System.out.println(money < 0.01f);
//        System.out.println((Float.valueOf("1") / 100));
    }
}
