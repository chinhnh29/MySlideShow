package com.photoeditor.slideshow.my_slide_show.obj;

public class Ratio {
    private String scale;
    private int resId;
    private VIDEO_RATIO video_ratio;


    public Ratio(String scale, int resId, VIDEO_RATIO video_ratio) {
        this.scale = scale;
        this.resId = resId;
        this.video_ratio = video_ratio;
    }

    public VIDEO_RATIO getVideo_ratio() {
        return video_ratio;
    }

    public void setVideo_ratio(VIDEO_RATIO video_ratio) {
        this.video_ratio = video_ratio;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
