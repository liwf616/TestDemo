package com.av.vtoa.video;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.av.vtoa.model.VideoModel;

import java.util.ArrayList;

public class VideoLoader {
    public static ArrayList<VideoModel> getAllVideos(Context context) {
        return getSongsForCursor(makeSongCursor(context, null, null));
    }

    public static ArrayList<VideoModel> getSongsForCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if ((cursor != null) && (cursor.moveToFirst()))
            do {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
//                String artist = cursor.getString(2);
//                String album = cursor.getString(3);
                int duration = cursor.getInt(4);
                String path=cursor.getString(5);
                String mime_type = cursor.getString(6);
                String mimetype = cursor.getString(7);
//                String diplayname = cursor.getString(8);
                long date = cursor.getLong(9);
                long size = cursor.getLong(10);

                arrayList.add(new VideoModel(title, path, duration,date));
            } while (cursor.moveToNext());

        if (cursor != null)
            cursor.close();
        return arrayList;
    }

    public static Cursor makeSongCursor(Context context, String selection, String[] paramArrayOfString) {
        return makeSongCursor(context, selection, paramArrayOfString, MediaStore.Audio.Media.DATA);
    }

    private static Cursor makeSongCursor(Context context, String selection, String[] paramArrayOfString,
        String sortOrder) {
        String selectionStatement = "video=1 AND title != ''";

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = selectionStatement + " AND " + selection;
        }

        String[] mediaColumns = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.ARTIST,
                MediaStore.Video.Media.ALBUM,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DATE_MODIFIED,
                MediaStore.Video.Media.SIZE
            };

        return context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                mediaColumns,selectionStatement, paramArrayOfString, sortOrder);

    }
}
