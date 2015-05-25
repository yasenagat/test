package com.example.mymodule.app2.win;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zf on 2015/3/31.
 */
public class CodeHisWinEntity {

    private List<Item> list;
    private int totalIssue;


    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public int getTotalIssue() {
        return totalIssue;
    }

    public void setTotalIssue(int totalIssue) {
        this.totalIssue = totalIssue;
    }

    public static class Item {

        private String winLevel = "";
        private AtomicInteger totalWinTimes = new AtomicInteger(0);
        private String lastWinIssue = "";

        public Item() {
        }

        public Item(String winLevel) {
            this.winLevel = winLevel;
        }

        public String getWinLevel() {
            return winLevel;
        }

        public void setWinLevel(String winLevel) {
            this.winLevel = winLevel;
        }

        public AtomicInteger getTotalWinTimes() {
            return totalWinTimes;
        }

        public void setTotalWinTimes(AtomicInteger totalWinTimes) {
            this.totalWinTimes = totalWinTimes;
        }

        public String getLastWinIssue() {
            return lastWinIssue;
        }

        public void setLastWinIssue(String lastWinIssue) {
            this.lastWinIssue = lastWinIssue;
        }

    }


}
