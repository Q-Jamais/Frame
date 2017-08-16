package com.lxhf.frame.mvp.base;

import android.os.Bundle;

import com.lxhf.frame.ui.activity.BaseActivity;

/**
 * Created by Jamais on 17/6/14.
 * E-mail : liutl@hfvast.com
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;// 每个界面的Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}
