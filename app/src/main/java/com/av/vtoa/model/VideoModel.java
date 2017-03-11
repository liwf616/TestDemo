package com.av.vtoa.model;

import com.av.vtoa.model.MediaModel;

import static com.av.vtoa.Constants.FILE_KIND_VIDEO;

/**
 * Created by LiJiaZhi on 16/12/19. music
 */

public class VideoModel extends MediaModel {

    public int type = FILE_KIND_VIDEO;
    public long albumId;
    public String albumName;
    public long artistId;
    public long id;
    public int trackNumber;

    public VideoModel(String path) {
        this.path = path;
        catorytype = 1;
    }

    public VideoModel(String title, String path, int duration, long date) {
        this.title = title;
        this.path = path;
        this.duration = duration;
        this.date = date;

        catorytype = 1;
    }
    public VideoModel(long id, long albumId, long artistId, String title, String artistName, String albumName, int duration,
                      int trackNumber, String path, long date) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artist = artistName;
        this.duration = duration;
        this.id = id;
        this.title = title;
        this.trackNumber = trackNumber;
        this.path = path;
        this.date = date;

        catorytype = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        com.av.vtoa.model.VideoModel songModel = (com.av.vtoa.model.VideoModel) o;

        return path.equals(songModel.path);

    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
