package com.av.vtoa.model;

import java.io.Serializable;
/**
 * Created by LiJiaZhi on 16/12/19.
 *  song record saved
 */

public class MediaModel extends BaseModel  implements Serializable {
    public int playStatus = 0;//0 无效、1播放、2停止
    public int catorytype;//1:song    2:record    3:saved
}
