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

    private AsyncTask<String, Void, String> mTask;

    private List<VideoModel> mVideos;

    private List<DataChangedListener> mListenerList = new ArrayList<>();

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
        loadMovies();
    }

    public void register(DataChangedListener listener) {
        mListenerList.add(listener);
        listener.updateVideos(getVideos());
    }

    public void unregister(DataChangedListener listener) {
        mListenerList.remove(listener);
    }

    public List<VideoModel> getVideos() {
        if (null == mVideos) {
            mVideos = new ArrayList<>();
        }
        return mVideos;
    }

    public void loadMovies() {
        if (null != mTask && mTask.isCancelled()) {
            mTask.cancel(true);
        }
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>();
        ExecutorService exec = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, blockingQueue);
        mTask = new loadVideos().executeOnExecutor(exec);
    }

    private class loadVideos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String...params) {
            mVideos = VideoLoader.getAllVideos(mContext);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            for (DataChangedListener listener : mListenerList) {
                if (null != listener) {
                    listener.updateVideos(mVideos);
                }
            }
        }

        @Override
        protected void onPreExecute() {
        }
    }

    public interface DataChangedListener {
        void updateVideos(List<VideoModel> list);

//        void updateRecords(List<RecordModel> list);
//
//        void updateCutters(List<CutterModel> list);
//
//        void updatePlayStatus(int mainType);
//
//        void sortByName(int sortType, boolean isNeedRevers);
//
//        void sortByDate(int sortType, boolean isNeedRevers);
//
//        void sortByTrack(int sortType, boolean isNeedRevers);
//
//        void sortByArtist(int sortType, boolean isNeedRevers);
//
//        void sortByAlbum(int sortType, boolean isNeedRevers);
    }
}
