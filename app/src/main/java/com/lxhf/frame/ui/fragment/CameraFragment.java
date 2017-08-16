package com.lxhf.frame.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxhf.frame.R;

/**
 * Created by Jamais on 17/8/16.
 * E-mail : liutl@hfvast.com
 */
public class CameraFragment extends Fragment{

    public static CameraFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CameraFragment fragment = new CameraFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment, container, false);
    }
}
