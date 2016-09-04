package com.example.ll.zhihudaily.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * the basic Fragment
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity=getActivity();
        // Inflate the layout for this fragment
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initData() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
    }

    protected abstract View initView(LayoutInflater inflater
            , ViewGroup container, Bundle savedInstanceState);
}
