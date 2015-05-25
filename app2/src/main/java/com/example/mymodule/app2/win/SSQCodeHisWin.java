package com.example.mymodule.app2.win;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zf on 2015/4/1.
 */
public class SSQCodeHisWin extends AbstractCodeHisWin {

    public static Map<String, Integer> levelMap = new HashMap<String, Integer>();

    static {
        levelMap.put("6+1", 1);
        levelMap.put("6+0", 2);
        levelMap.put("5+1", 3);
        levelMap.put("5+0", 4);
        levelMap.put("4+1", 4);
        levelMap.put("4+0", 5);
        levelMap.put("3+1", 5);
        levelMap.put("2+1", 6);
        levelMap.put("1+1", 6);
        levelMap.put("0+1", 6);
    }

    @Override
    public List<CodeEntity> getWinCodeList() {

        return winCodeList;
    }

    public static List<CodeEntity> winCodeList = new ArrayList<CodeEntity>();

    @Override
    public int getLevel(int red_dan, int red_tuo, int blue_dan, int blue_tuo, int red_dan_xuan, int red_tuo_xuan, int blue_dan_xuan, int blue_tuo_xuan) {

//        System.out.println(red_dan + " : " + red_tuo + " : " + blue_dan + " : " + blue_tuo);
        String key = null;
        int red_zhong = 0, blue_zhong = 0;

        if (red_dan_xuan > 0) {
            if (red_dan > 0) {
                red_zhong = (red_dan + Math.min(6 - red_dan_xuan, red_tuo));
            } else {
                red_zhong = 0;
            }
        } else {
            red_zhong = (red_tuo);
        }

        if (blue_dan_xuan > 0) {
            if (blue_dan > 0) {
                blue_zhong = (blue_dan + Math.min(1 - blue_dan_xuan, blue_tuo));
            } else {
                blue_zhong = 0;
            }

        } else {
            blue_zhong = (blue_tuo);
        }

        key = (red_zhong) + "+" + (blue_zhong);

//        System.out.println("key : " + key);
        Integer levle = levelMap.get(key);
        return levle == null ? -1 : levle;
    }

    @Override
    public List<CodeHisWinEntity.Item> getLevelItem() {
        List<CodeHisWinEntity.Item> items = new ArrayList<CodeHisWinEntity.Item>();
        items.add(new CodeHisWinEntity.Item("一等奖"));
        items.add(new CodeHisWinEntity.Item("二等奖"));
        items.add(new CodeHisWinEntity.Item("三等奖"));
        items.add(new CodeHisWinEntity.Item("四等奖"));
        items.add(new CodeHisWinEntity.Item("五等奖"));
        items.add(new CodeHisWinEntity.Item("六等奖"));
        return items;
    }

//    public static List<CodeHisWinEntity.Item> items = new ArrayList<CodeHisWinEntity.Item>();
//
//    static {
//        items.add(new CodeHisWinEntity.Item("一等奖"));
//        items.add(new CodeHisWinEntity.Item("二等奖"));
//        items.add(new CodeHisWinEntity.Item("三等奖"));
//        items.add(new CodeHisWinEntity.Item("四等奖"));
//        items.add(new CodeHisWinEntity.Item("五等奖"));
//        items.add(new CodeHisWinEntity.Item("六等奖"));
//    }
}
