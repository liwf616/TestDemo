package com.av.vtoa.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.av.vtoa.R;
import com.av.vtoa.model.BaseModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by LiJiaZhi on 16/11/7. base
 */

public abstract class BaseActivity extends AppCompatActivity {
    // 统计
    protected FirebaseAnalytics mFirebaseAnalytics;

    protected FrameLayout mContentLayout;

    protected View rootView;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initBundleExtra(Bundle savedInstanceState);

    protected abstract void findViewById();

    protected abstract void initListeners();

    protected abstract void initData();

    protected boolean isStartEventBus() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        if (bundle != null) {
            // 如果系统回收的Activity， 但是系统却保留了Fragment， 当Activity被重新初始化， 此时， 系统保存的Fragment 的getActivity为空，
            // 所以要移除旧的Fragment ， 重新初始化新的Fragment
            String FRAGMENTS_TAG = "android:support:fragments";
            bundle.remove(FRAGMENTS_TAG);
        }
        super.onCreate(bundle);
        rootView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        setContentView(rootView);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            mContentLayout.addView(LayoutInflater.from(this).inflate(layoutId, null));
        }

        initPopupFragment(getPopupFragment());

        initBundleExtra(bundle);
        findViewById();
        initListeners();
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isStartEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(BaseModel event) {
    }

    @Override
    public void onStop() {
        if (isStartEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    protected void initPopupFragment(Fragment popupFragment) {
        if (popupFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.layout_content, popupFragment);
            transaction.commitAllowingStateLoss();
        }
    }

    protected Fragment getPopupFragment() {
        return null;
    }
}
