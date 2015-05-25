package com.example.mymodule.app2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zf on 2015/3/5.
 */
public class T {

    public static void main(String[] args) {


//        List<String> _tuo_red = new ArrayList<String>();
//        List<String> _dan_red = new ArrayList<String>();
//
//        parseCode("01,02,03,04",_tuo_red,_dan_red);
//
//        for(String tuo : _tuo_red){
//            System.out.println(tuo);
//        }
//        System.out.println("-------");
//        for(String dan : _dan_red){
//            System.out.println(dan);
//        }

        List<User> list = new ArrayList<User>();
//        list.add(new User(1));
//        list.add(new User(2));
//        list.add(new User(3));
//
//        for (User u : list) {
//            System.out.println(u);
//        }
//
//        List<User> list2 = new ArrayList<User>();
//        list2.add(list.get(0));
//        list2.add(list.get(1));
//        list2.add(list.get(2));
//
//        for (User u : list2) {
//            System.out.println(u);
//        }

        for (int i = 0; i < 40; i++) {
            list.add(new User(i));
        }

        List<User> l = cutList(list, 0, 10);

        for (User u : l) {
            System.out.println(u.id);
        }
    }

    public static class User {
        public int id;

        User(int id) {
            this.id = id;
        }
    }

    public static void parseCode(String code, List<String> _tuo_red, List<String> _dan_red) {

        try {

            if (!StringUtil.isEmpty(code)) {

                String[] _t = null;

                //没有胆
                if (code != null && code.indexOf("*") == -1) {

                    _t = code.split(",");

                    if (_t != null & _t.length > 0) {

                        for (int j = 0; j < _t.length; j++) {
                            _tuo_red.add(_t[j]);
                        }
                    }

                } else {

                    String[] _s = null;

                    _s = code.split("\\*");

                    if (_s != null /*&& _s.length == 2*/) {
                        //有胆

                        _t = _s[0].split(",");

                        //胆
                        if (_t != null & _t.length > 0) {

                            for (int j = 0; j < _t.length; j++) {
                                _dan_red.add(_t[j]);
                            }
                        }
                        //拖
                        if (_s.length == 2) {
                            _t = _s[1].split(",");

                            if (_t != null & _t.length > 0) {
                                for (int j = 0; j < _t.length; j++) {
                                    _tuo_red.add(_t[j]);
                                }
                            }
                        }

                    } else {

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <T> List<T> cutList(List<T> list, int startIndex, int endIndex) {

        List<T> retList = new ArrayList<T>();

        for (int i = startIndex; i < endIndex; i++) {
            retList.add(list.get(i));
        }

        return retList;
    }
}
