package com.lxhf.frame.mvp.base;

/**
 * UI中要实现的方法都在View中（这里是所有界面view的父类——公共方法）
 * Created by Jamais on 17/6/14.
 * E-mail : liutl@hfvast.com
 */
public interface BaseView {

    void showLoading();//   显示加载条

    void hideLoading();//   隐藏加载条
}
