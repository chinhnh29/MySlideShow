package com.photoeditor.slideshow.interfaces;

public interface ServiceListener {
    void renderFailed();

    void renderProgress(int i);

    void renderSuccess(String str);
}
