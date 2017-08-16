package com.lxhf.frame.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lxhf.frame.ui.common.TabEnum;
import com.lxhf.frame.ui.fragment.CameraFragment;
import com.lxhf.frame.ui.fragment.SlidersFragment;
import com.lxhf.frame.ui.fragment.SnackBarsFragment;
import com.lxhf.frame.ui.fragment.SpinnerFragment;
import com.lxhf.frame.ui.fragment.TextFieldsFragment;

/**
 * Created by Jamais on 17/8/15.
 * E-mail : liutl@hfvast.com
 */
public class MainViewPagerAdpter extends FragmentStatePagerAdapter {
    private TabEnum[] mTabs;
    private Fragment[] mFragments;

    public MainViewPagerAdpter(FragmentManager fm, TabEnum[] mTabs) {
        super(fm);
        this.mTabs = mTabs;
        mFragments = new Fragment[mTabs.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments[position] == null) {
            switch (mTabs[position]) {
                case CAMERA:
                    mFragments[position] = CameraFragment.newInstance();
                    break;
                case SLIDERS:
                    mFragments[position] = SlidersFragment.newInstance();
                    break;
                case SPINNERS:
                    mFragments[position] = SpinnerFragment.newInstance();
                    break;
                case TEXTFIELDS:
                    mFragments[position] = TextFieldsFragment.newInstance();
                    break;
                case SNACKBARS:
                    mFragments[position] = SnackBarsFragment.newInstance();
                    break;
                default:
                    break;
            }
        }

        return mFragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position].toString().toUpperCase();
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }
}
