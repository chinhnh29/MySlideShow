package com.photoeditor.slideshow.my_slide_show.obj;

public class FrameInfo {
    private String fileName;
    private int resId;

    public FrameInfo(String fileName, int resId) {
        this.fileName = fileName;
        this.resId = resId;
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
}
