package com.lxhf.frame.bean;

/**
 * Created by Jamais on 17/8/15.
 * E-mail : liutl@hfvast.com
 */
public class Tab {
    private String name;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tab{" +
                "name='" + name + '\'' +
                '}';
    }
}
