package com.example.mymodule.app2.win;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zf on 2015/3/31.
 */
public class CodeEntity {

    private Set<String> red_dan = new HashSet<String>();
    private Set<String> red_tuo = new HashSet<String>();
    private Set<String> blue_dan = new HashSet<String>();
    private Set<String> blue_tuo = new HashSet<String>();

    private String issue;

    public CodeEntity() {
    }

    public CodeEntity(Set<String> red_tuo, Set<String> blue_tuo, String issue) {
        this.red_tuo = red_tuo;
        this.blue_tuo = blue_tuo;

        this.red_dan = new HashSet<String>();
        this.blue_dan = new HashSet<String>();

        this.issue = issue;
    }

    public CodeEntity(Set<String> red_dan, Set<String> red_tuo, Set<String> blue_dan, Set<String> blue_tuo) {
        this.red_dan = red_dan;
        this.red_tuo = red_tuo;
        this.blue_dan = blue_dan;
        this.blue_tuo = blue_tuo;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Set<String> getRed_dan() {
        return red_dan;
    }

    public void setRed_dan(Set<String> red_dan) {
        this.red_dan = red_dan;
    }

    public Set<String> getRed_tuo() {
        return red_tuo;
    }

    public void setRed_tuo(Set<String> red_tuo) {
        this.red_tuo = red_tuo;
    }

    public Set<String> getBlue_dan() {
        return blue_dan;
    }

    public void setBlue_dan(Set<String> blue_dan) {
        this.blue_dan = blue_dan;
    }

    public Set<String> getBlue_tuo() {
        return blue_tuo;
    }

    public void setBlue_tuo(Set<String> blue_tuo) {
        this.blue_tuo = blue_tuo;
    }
}
