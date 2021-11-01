package com.photoeditor.slideshow.models.main;

import com.airbnb.lottie.LottieComposition;
//import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.photoeditor.slideshow.models.GifTransition;

//import kotlin.jvm.internal.DefaultConstructorMarker;
//import kotlin.jvm.internal.Intrinsics;


public final class TransitionJsonModel {
    private final LottieComposition composition;
    private int endFrame;
    private int startFrame;
    private final GifTransition transition;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransitionJsonModel)) {
            return false;
        }
        TransitionJsonModel transitionJsonModel = (TransitionJsonModel) obj;
        return this.transition.equals(transitionJsonModel.transition) &&
                this.composition.equals(transitionJsonModel.composition) &&
                this.startFrame == transitionJsonModel.startFrame && this.endFrame == transitionJsonModel.endFrame;
    }

    public TransitionJsonModel(GifTransition gifTransition, LottieComposition lottieComposition, int startFrame, int endFrame) {
        this.transition = gifTransition;
        this.composition = lottieComposition;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    public final GifTransition getTransition() {
        return this.transition;
    }

    public final LottieComposition getComposition() {
        return this.composition;
    }

    public final int getStartFrame() {
        return this.startFrame;
    }

    public final void setStartFrame(int i) {
        this.startFrame = i;
    }



    public final int getEndFrame() {
        return this.endFrame;
    }

    public final void setEndFrame(int endFrame) {
        this.endFrame = endFrame;
    }

}
