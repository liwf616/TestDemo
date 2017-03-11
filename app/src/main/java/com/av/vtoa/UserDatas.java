package com.av.vtoa;

import android.content.Context;
import android.os.AsyncTask;

import com.av.vtoa.model.VideoModel;
import com.av.vtoa.video.VideoLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liwenfeng on 17/3/5.
 */

public class UserDatas {
    private static volatile UserDatas sUserDatas;

    private Context mContext;

    // songs
    private AsyncTask<String, Void, String> mTask;

    private List<VideoModel> mVideos;

    public static UserDatas getInstance() {
        if (sUserDatas == null) {
            synchronized (UserDatas.class) {
                if (sUserDatas == null) {
                    sUserDatas = new UserDatas();
                }
            }
        }
        return sUserDatas;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void loadDatas() {
        loadMusics();
    }

    public void loadMusics() {
        if (null != mTask && mTask.isCancelled()) {
            mTask.cancel(true);
        }
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>();
        ExecutorService exec = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, blockingQueue);
        mTask = new loadSongs().executeOnExecutor(exec);
    }

    private class loadSongs extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String...params) {
            mVideos = VideoLoader.getAllVideos(mContext);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }
    }
}
