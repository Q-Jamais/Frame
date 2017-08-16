package com.lxhf.frame.mvp.vp.main;


import com.lxhf.frame.mvp.base.BasePresenter;
import com.lxhf.frame.mvp.model.MainModel;
import com.lxhf.frame.mvp.retrofit.ApiCallback;

/**
 * Created by Jamais on 17/6/15.
 * E-mail : liutl@hfvast.com
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void loadDataByRetrofitRxjava(String cityId) {
        mvpView.showLoading();
        addSubscription(apiStores.loadDataByRetrofitRxjava(cityId),
                new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }

}
