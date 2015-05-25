package com.example.mymodule.app2.win;

import com.example.mymodule.app2.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zf on 2015/3/31.
 */
public abstract class AbstractCodeHisWin implements ICodeHisWin {

    @Override
    public CodeHisWinEntity calWinHis(String code) {

        CodeEntity codeEntity = codeEntity(code);

//        System.out.println("codeEntity.getRed_dan() : " + codeEntity.getRed_dan());
//        System.out.println("codeEntity.getRed_tuo() : " + codeEntity.getRed_tuo());
//        System.out.println("codeEntity.getBlue_dan() : " + codeEntity.getBlue_dan());
//        System.out.println("codeEntity.getBlue_tuo() : " + codeEntity.getBlue_tuo());

        Set<String> temp_red_dan, temp_red_tuo, temp_blue_dan, temp_blue_tuo;

        List<CodeHisWinEntity.Item> items = getLevelItem();

        int totalIssue = 0;
        int level;
        int red_dan = 0, red_tuo = 0, blue_dan = 0, blue_tuo = 0;
        for (CodeEntity winEntity : getWinCodeList()) {

            temp_red_dan = new HashSet<String>();
            temp_red_dan.addAll(winEntity.getRed_tuo());
            temp_red_dan.retainAll(codeEntity.getRed_dan());

            red_dan = temp_red_dan.size();


            temp_red_tuo = new HashSet<String>();
            temp_red_tuo.addAll(winEntity.getRed_tuo());

//            System.out.println("temp_red_tuo : " + temp_red_tuo);
//            System.out.println("winEntity.getRed_tuo() : " + winEntity.getRed_tuo());

            temp_red_tuo.retainAll(codeEntity.getRed_tuo());

            red_tuo = temp_red_tuo.size();

//            System.out.println("temp_red_tuo : " + temp_red_tuo);

            temp_blue_dan = new HashSet<String>();
            temp_blue_dan.addAll(winEntity.getBlue_tuo());
            temp_blue_dan.retainAll(codeEntity.getBlue_dan());

            blue_dan = temp_blue_dan.size();

            temp_blue_tuo = new HashSet<String>();
            temp_blue_tuo.addAll(winEntity.getBlue_tuo());
            temp_blue_tuo.retainAll(codeEntity.getBlue_tuo());

            blue_tuo = temp_blue_tuo.size();

            totalIssue++;
//            System.out.println("issue : " + winEntity.getIssue());
//            System.out.println("winEntity.getRed_dan() : " + winEntity.getRed_dan());
//            System.out.println("winEntity.getRed_tuo() : " + winEntity.getRed_tuo());
//            System.out.println("winEntity.getBlue_dan() : " + winEntity.getBlue_dan());
//            System.out.println("winEntity.getBlue_tuo() : " + winEntity.getBlue_tuo());
            level = getLevel(red_dan, red_tuo, blue_dan, blue_tuo, codeEntity.getRed_dan().size(), codeEntity.getRed_tuo().size(), codeEntity.getBlue_dan().size(), codeEntity.getBlue_tuo().size());
            if (level >= 1) {
                update(items.get(level - 1), winEntity.getIssue());
            } else {

            }

        }

        CodeHisWinEntity codeHisWinEntity = new CodeHisWinEntity();
        codeHisWinEntity.setTotalIssue(totalIssue);
        codeHisWinEntity.setList(items);

        return codeHisWinEntity;
    }

    public abstract List<CodeEntity> getWinCodeList();

    public abstract int getLevel(int red_dan, int red_tuo, int blue_dan, int blue_tuo, int red_dan_xuan, int red_tuo_xuan, int blue_dan_xuan, int blue_tuo_xuan);

    public abstract List<CodeHisWinEntity.Item> getLevelItem();

    private void update(CodeHisWinEntity.Item item, String issue) {

        item.getTotalWinTimes().incrementAndGet();
        item.setLastWinIssue(issue);

    }

    public CodeEntity codeEntity(String code) {
        CodeEntity codeEntity = new CodeEntity();

        if (!StringUtil.isEmpty(code)) {

            if (code.indexOf("|") > 0) {

                String s[] = code.split("\\|");

                if (s[0].indexOf("*") > 0) {
                    String red[] = s[0].split("\\*");
                    codeEntity.setRed_dan(codeToSet(red[0]));
                    codeEntity.setRed_tuo(codeToSet(red[1]));
                } else {
                    codeEntity.setRed_tuo(codeToSet(s[0]));
                }

                if (s[1].indexOf("*") > 0) {
                    String blue[] = s[1].split("\\*");
                    codeEntity.setBlue_dan(codeToSet(blue[0]));
                    codeEntity.setBlue_tuo(codeToSet(blue[1]));
                } else {
                    codeEntity.setBlue_tuo(codeToSet(s[1]));
                }
            }

        }


        return codeEntity;
    }

    public Set<String> codeToSet(String code) {

        Set<String> set = new HashSet<String>();
        if (!StringUtil.isEmpty(code)) {
            String codes[] = code.split(",");
            for (String s : codes) {
                set.add(s);
            }
        }
        return set;
    }
}
