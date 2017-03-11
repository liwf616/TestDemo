package com.av.vtoa.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.av.vtoa.base.BaseFragment;
import com.av.vtoa.R;

/**
 * Created by LiJiaZhi on 16/11/22.
 * video列表
 */
public class VideoFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private TextView mEmptyTv;

    private boolean mIsInit = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View parentView, Bundle savedInstanceState) {
        mEmptyTv = findViewById(R.id.empty_tv);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        mIsInit = true;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
