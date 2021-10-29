package com.photoeditor.slideshow.models;

import com.airbnb.lottie.LottieDrawable;
import java.util.ArrayList;
import java.util.List;

public class TransitionDrawableModel {
    private int frame = -1;
    private int haftFrame;
    private List<Integer> listFrameTransition = new ArrayList();
    private LottieDrawable lottieDrawable;
    private int totalFrame = 0;

    public void addFrameTransition(int i) {
        this.listFrameTransition.add(Integer.valueOf(i));
    }

    public int getFrameToDraw(int i) {
        this.frame = -1;
        for (Integer intValue : this.listFrameTransition) {
            int intValue2 = intValue.intValue();
            int i2 = this.haftFrame;
            if (i >= intValue2 - i2 && i < intValue2 + i2) {
                if (i <= intValue2) {
                    this.frame = i - (intValue2 - i2);
                } else {
                    this.frame = i2 + (i - intValue2);
                }
                return this.frame;
            }
        }
        return this.frame;
    }

    public LottieDrawable getLottieDrawable() {
        return this.lottieDrawable;
    }

    public void setLottieDrawable(LottieDrawable lottieDrawable2) {
        this.lottieDrawable = lottieDrawable2;
        int maxFrame = (int) lottieDrawable2.getMaxFrame();
        this.totalFrame = maxFrame;
        this.haftFrame = maxFrame / 2;
    }

    public List<Integer> getListFrameTransition() {
        return this.listFrameTransition;
    }

    public void setListFrameTransition(List<Integer> list) {
        this.listFrameTransition = list;
    }
}
