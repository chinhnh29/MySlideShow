package com.photoeditor.slideshow.models;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;

public class ThemeLottieModel {
    private int end;
    private int end2;
    private LottieComposition lottieComposition;
    private LottieDrawable lottieDrawable;
    private int maxFrame;
    private int start;
    private int start2;

    public ThemeLottieModel() {
    }

    public LottieComposition getLottieComposition() {
        return this.lottieComposition;
    }

    public void setLottieComposition(LottieComposition lottieComposition2) {
        this.lottieComposition = lottieComposition2;
    }

    public ThemeLottieModel(LottieDrawable lottieDrawable2, int i, int i2, int i3) {
        this.lottieDrawable = lottieDrawable2;
        this.maxFrame = i;
        this.start = i2;
        this.end = i3;
    }

    public int getStart2() {
        return this.start2;
    }

    public void setStart2(int i) {
        this.start2 = i;
    }

    public int getEnd2() {
        return this.end2;
    }

    public void setEnd2(int i) {
        this.end2 = i;
    }

    public LottieDrawable getLottieDrawable() {
        return this.lottieDrawable;
    }

    public void setLottieDrawable(LottieDrawable lottieDrawable2) {
        this.lottieDrawable = lottieDrawable2;
    }

    public int getMaxFrame() {
        return this.maxFrame;
    }

    public void setMaxFrame(int i) {
        this.maxFrame = i;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int i) {
        this.end = i;
    }
}
