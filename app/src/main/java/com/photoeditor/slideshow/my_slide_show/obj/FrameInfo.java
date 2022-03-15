package com.photoeditor.slideshow.my_slide_show.obj;

public class FrameInfo {
    private int resId;
    private String fileName;
    private String themePath;
    private String tabName;
    private String url;

    public FrameInfo(String fileName, int resId, String themePath) {
        this.fileName = fileName;
        this.resId = resId;
        this.themePath = themePath;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getThemePath() {
        return themePath;
    }

    public void setThemePath(String themePath) {
        this.themePath = themePath;
    }
}
