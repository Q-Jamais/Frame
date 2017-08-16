package com.lxhf.frame.mvp.vp.main;

import com.lxhf.frame.mvp.base.BaseView;
import com.lxhf.frame.mvp.model.MainModel;

/**
 * 处理业务的方法
 * Created by Jamais on 17/6/15.
 * E-mail : liutl@hfvast.com
 */
public interface MainView extends BaseView {

    void getDataSuccess(MainModel model);

    void getDataFail(String msg);

}
