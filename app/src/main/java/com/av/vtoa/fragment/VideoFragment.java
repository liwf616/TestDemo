package com.av.vtoa.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.av.vtoa.UserDatas;
import com.av.vtoa.base.BaseFragment;
import com.av.vtoa.R;
import com.av.vtoa.model.VideoModel;

import java.util.List;

/**
 * Created by LiJiaZhi on 16/11/22.
 * video列表
 */
public class VideoFragment extends BaseFragment implements UserDatas.DataChangedListener {

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
        UserDatas.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        UserDatas.getInstance().unregister(this);
    }

//    @Override
    public void updateVideos(List<VideoModel> list) {
//        mAdapter = new SongsAdapter((MainActivity) getActivity(), list);
//        if (mAdapter.getDatas().size() == 0) {
//            mEmptyTv.setVisibility(View.VISIBLE);
//        } else {
//            mEmptyTv.setVisibility(View.GONE);
//            mRecyclerView.setAdapter(mAdapter);
//        }
//        if (mSortType == 0) {
//            sortByName_fresh();
//        } else if (mSortType == 1) {
//            sortByDate_fresh();
//        } else if (mSortType == 2) {
//            sortByTrack_fresh();
//        } else if (mSortType == 3) {
//            sortByArtist_fresh();
//        } else if (mSortType == 4) {
//            sortByAlbum_fresh();
//        }
    }
}
