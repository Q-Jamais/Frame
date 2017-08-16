package com.lxhf.frame.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lxhf.frame.R;
import com.lxhf.frame.broadcast.NetWorkBroadcastReceiver;
import com.lxhf.frame.manage.PermissionManager;
import com.lxhf.frame.mvp.base.MvpActivity;
import com.lxhf.frame.mvp.model.MainModel;
import com.lxhf.frame.mvp.vp.main.MainPresenter;
import com.lxhf.frame.mvp.vp.main.MainView;
import com.lxhf.frame.ui.adapter.MainViewPagerAdpter;
import com.lxhf.frame.ui.common.TabEnum;
import com.lxhf.frame.utils.L;
import com.lxhf.frame.utils.T;

import butterknife.BindView;

/**
 * 由Activity/Fragment实现View里方法，包含一个Presenter的引用
 *
 * @author Jamais
 * @created at 17/6/15 上午9:57
 */
public class MainActivity extends MvpActivity<MainPresenter> implements MainView, NetWorkBroadcastReceiver.NetWorkChanageListen,
        NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.navigationviewMain)
    NavigationView navigationviewMain;
    @BindView(R.id.mainDrawerLayout)
    DrawerLayout mainDrawerLayout;
    @BindView(R.id.floatingActionBtn)
    FloatingActionButton floatingActionBtn;
    @BindView(R.id.tabMain)
    TabLayout tabMain;
    @BindView(R.id.viewPagerMain)
    ViewPager viewPagerMain;

    //  导航栏Tab
    private TabEnum[] mTabs;
    private MainViewPagerAdpter mMainViewPagerAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBroadrecevicer(this);
//        checkPermission();//    申请权限
        initView();
//        getData();//  http获取数据演示
    }

    /**
     * 申请权限
     *
     * @author Jamais
     * @created at 17/8/14 下午2:26
     */
    private void checkPermission() {
        PermissionManager.requestMultiPermissions(this, mPermissionGrant);
    }

    private void initView() {
        setToolBar();
        setLeftDrawerLayout();
        setTabLayout();
        setViewPager();
    }

    /**
     * 标题栏设置
     *
     * @author Jamais
     * @created at 17/6/20 上午10:36
     */
    private void setToolBar() {
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//    返回键
//        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    /**
     * 侧滑视图
     *
     * @author Jamais
     * @created at 17/8/15 上午10:52
     */
    private void setLeftDrawerLayout() {
        //  开关设置（与ToolBar联动）
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mainDrawerLayout, mainToolbar, R.string.drawerOpen, R.string.drawerClose);
        mainDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationviewMain.setNavigationItemSelectedListener(this);
    }

    /**
     * 导航栏
     *
     * @author Jamais
     * @created at 17/8/15 下午3:59
     */
    private void setTabLayout() {
        mTabs = TabEnum.getTabArr();
        for (TabEnum tab : mTabs) {
            tabMain.addTab(tabMain.newTab().setText(tab.name().toString().toUpperCase()));
        }
    }

    /**
     * viewpager和Tablayout联动
     *
     * @author Jamais
     * @created at 17/8/16 上午9:15
     */
    private void setViewPager() {
        mMainViewPagerAdpter = new MainViewPagerAdpter(getSupportFragmentManager(), TabEnum.getTabArr());
        viewPagerMain.setAdapter(mMainViewPagerAdpter);
        tabMain.setupWithViewPager(viewPagerMain);
        viewPagerMain.setCurrentItem(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mainDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void getData() {
        mvpPresenter.loadDataByRetrofitRxjava("101310222");
    }


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void getDataSuccess(MainModel model) {
        L.i(TAG, model.getWeatherinfo().toString());
    }

    @Override
    public void getDataFail(String msg) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_1:
                T.toastAtBottomS(this, item.getTitle().toString());
                break;
            case R.id.main_menu_2:
                T.toastAtBottomS(this, item.getTitle().toString());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void netWorkChange(String type) {
        //  根据网络变化做出相应操作
        switch (type) {
            case NetWorkBroadcastReceiver.NetWorkChangeType.TYPE_WIFI_Y:
                T.toastAtBottomS(this, "WIFI网络可用");
                break;
            case NetWorkBroadcastReceiver.NetWorkChangeType.TYPE_WIFI_N:
                T.toastAtBottomS(this, "WIFI网络不可用");
                break;
            case NetWorkBroadcastReceiver.NetWorkChangeType.TYPE_MOBILE:
                T.toastAtBottomS(this, "手机网络可用");
                break;
            case NetWorkBroadcastReceiver.NetWorkChangeType.TYPE_NET_BREAK:
                T.toastAtBottomS(this, "网络中断");
                break;
        }
    }

    /**
     * 回调申请权限结果
     *
     * @author Jamais
     * @created at 17/8/14 下午2:26
     */
    private PermissionManager.PermissionGrant mPermissionGrant = new PermissionManager.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionManager.CODE_RECORD_AUDIO:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_GET_ACCOUNTS:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_READ_PHONE_STATE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_CALL_PHONE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_CAMERA:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_ACCESS_COARSE_LOCATION:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_READ_EXTERNAL_STORAGE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_WRITE_EXTERNAL_STORAGE:
                    Toast.makeText(MainActivity.this, "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionManager.CODE_MULTI_PERMISSION:
                    Toast.makeText(MainActivity.this, "Result All Permission Grant", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionManager.requestPermissionsResult(this, requestCode, permissions, grantResults, null);
    }

}
