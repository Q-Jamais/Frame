package com.lxhf.frame.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.lxhf.frame.broadcast.NetWorkBroadcastReceiver;
import com.lxhf.frame.manage.AppManager;
import com.lxhf.frame.mvp.retrofit.ApiStores;
import com.lxhf.frame.mvp.retrofit.AppClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 所有activity必须继承此类，将所有界面共享操作或相关全局参宿都在此类中声明，以及对所有界面的管理
 * Created by Jamais on 17/5/31.
 * E-mail : liutl@hfvast.com
 */

public class BaseActivity extends AppCompatActivity {

    private Activity mActivity;
    public ProgressDialog progressDialog;
    private NetWorkBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  界面创建时就加入到栈中
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 注册广播接收器
     *
     * @author Jamais
     * @created at 17/7/27 下午2:50
     */
    public void registerBroadrecevicer(NetWorkBroadcastReceiver.NetWorkChanageListen mActivity) {
        //获取广播对象
        receiver = new NetWorkBroadcastReceiver();
        receiver.setNetWorkChangeListen(mActivity);
        //创建意图过滤器
        IntentFilter filter = new IntentFilter();
        //添加动作，监听网络
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    /**
     * 解除广播
     *
     * @author Jamais
     * @created at 17/7/27 下午3:01
     */
    public void unRegisterReceiver() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    /**
     * 初始化
     *
     * @author Jamais
     * @created at 17/6/15 上午9:16
     */
    private void init() {
        ButterKnife.bind(this);
        mActivity = this;
    }


    @Override
    protected void onDestroy() {
        unRegisterReceiver();
        //  界面退出销毁时就移除栈中
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    /**
     * 加载进度条显示（无说明）
     *
     * @author Jamais
     * @created at 17/6/15 上午9:43
     */
    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        return progressDialog;
    }

    /**
     * 加载进度条显示（有说明）
     *
     * @author Jamais
     * @created at 17/6/15 上午9:43
     */
    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    /**
     * 加载进度条隐藏
     *
     * @author Jamais
     * @created at 17/6/15 上午9:44
     */
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }
}
