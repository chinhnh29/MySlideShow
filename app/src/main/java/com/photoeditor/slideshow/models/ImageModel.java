package com.photoeditor.slideshow.models;

import android.graphics.Bitmap;

import com.photoeditor.slideshow.imagetovideo.Transition;

public class ImageModel {
    private Bitmap blurBitmap;
    private Bitmap originBitmap = null;
    private float second;
    private Transition transition;
    private String url;
    private int widthPixel;

    public ImageModel() {
    }

    public ImageModel(Bitmap bitmap, Bitmap bitmap2, String str, Transition transition2) {
        this.originBitmap = bitmap;
        this.blurBitmap = bitmap2;
        this.url = str;
        this.transition = transition2;
    }

    public int getWidthPixel() {
        return this.widthPixel;
    }

    public void setWidthPixel(int i) {
        this.widthPixel = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Bitmap getOriginBitmap() {
        return this.originBitmap;
    }

    public void setOriginBitmap(Bitmap bitmap) {
        this.originBitmap = bitmap;
    }

    public Bitmap getBlurBitmap() {
        return this.blurBitmap;
    }

    public void setBlurBitmap(Bitmap bitmap) {
        this.blurBitmap = bitmap;
    }

    public float getSecond() {
        return this.second;
    }

    public void setSecond(float f) {
        this.second = f;
    }

    public Transition getTransition() {
        return this.transition;
    }

    public void setTransition(Transition transition2) {
        this.transition = transition2;
    }
}
