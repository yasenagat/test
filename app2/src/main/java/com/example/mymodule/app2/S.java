package com.example.mymodule.app2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zf on 2015/3/11.
 */
public class S {
    public static void main(String[] args) {

        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 5; i++) {
            list.add(new User());
        }
        for (User u : list) {
            System.out.println(u);
        }

        ArrayList<User> list1 = (ArrayList<User>) ((ArrayList<User>) list).clone();

        for (User u : list1) {
            System.out.println(u);
        }
    }
}
