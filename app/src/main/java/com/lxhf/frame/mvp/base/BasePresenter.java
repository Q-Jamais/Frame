package com.lxhf.frame.mvp.base;

import com.lxhf.frame.mvp.retrofit.ApiStores;
import com.lxhf.frame.mvp.retrofit.AppClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jamais on 17/6/14.
 * E-mail : liutl@hfvast.com
 */
public class BasePresenter<V> {
    public V mvpView;
    protected ApiStores apiStores;
    private CompositeSubscription mCompositeSubscription;

    /**
     * 绑定View,将在每个界面的Presenter实例化中调用
     *
     * @author Jamais
     * @created at 17/6/14 上午11:26
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores = AppClient.retrofit().create(ApiStores.class);

    }

    /**
     * 解绑view,在界面退出消亡时调用
     *
     * @author Jamais
     * @created at 17/6/14 上午11:22
     */
    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    /**
     * RXjava取消注册，以避免内存泄露
     * @author Jamais
     * @created at 17/6/14 上午11:24
     */
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    /**
     * 添加订阅
     * @author Jamais
     * @created at 17/6/14 上午11:24
     */
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
