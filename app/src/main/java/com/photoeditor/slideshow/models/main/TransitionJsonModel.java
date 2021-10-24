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

    public static /* synthetic */ TransitionJsonModel copy$default(TransitionJsonModel transitionJsonModel, GifTransition gifTransition, LottieComposition lottieComposition, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            gifTransition = transitionJsonModel.transition;
        }
        if ((i3 & 2) != 0) {
            lottieComposition = transitionJsonModel.composition;
        }
        if ((i3 & 4) != 0) {
            i = transitionJsonModel.startFrame;
        }
        if ((i3 & 8) != 0) {
            i2 = transitionJsonModel.endFrame;
        }
        return transitionJsonModel.copy(gifTransition, lottieComposition, i, i2);
    }

    public final GifTransition component1() {
        return this.transition;
    }

    public final LottieComposition component2() {
        return this.composition;
    }

    public final int component3() {
        return this.startFrame;
    }

    public final int component4() {
        return this.endFrame;
    }

    public final TransitionJsonModel copy(GifTransition gifTransition, LottieComposition lottieComposition, int i, int i2) {
//        Intrinsics.checkNotNullParameter(gifTransition, "transition");
//        Intrinsics.checkNotNullParameter(lottieComposition, "composition");
        return new TransitionJsonModel(gifTransition, lottieComposition, i, i2);
    }

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

    public int hashCode() {
        GifTransition gifTransition = this.transition;
        int i = 0;
        int hashCode = (gifTransition != null ? gifTransition.hashCode() : 0) * 31;
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            i = lottieComposition.hashCode();
        }
        return ((((hashCode + i) * 31) + this.startFrame) * 31) + this.endFrame;
    }

    public String toString() {
        return "TransitionJsonModel(transition=" + this.transition + ", composition=" + this.composition + ", startFrame=" + this.startFrame + ", endFrame=" + this.endFrame + ")";
    }

    public TransitionJsonModel(GifTransition gifTransition, LottieComposition lottieComposition, int i, int i2) {
//        Intrinsics.checkNotNullParameter(gifTransition, "transition");
//        Intrinsics.checkNotNullParameter(lottieComposition, "composition");
        this.transition = gifTransition;
        this.composition = lottieComposition;
        this.startFrame = i;
        this.endFrame = i2;
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

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TransitionJsonModel(GifTransition gifTransition, LottieComposition lottieComposition, int i, int i2, int i3/*, DefaultConstructorMarker defaultConstructorMarker*/) {
        this(gifTransition, lottieComposition, (i3 & 4) != 0 ? -1 : i, (i3 & 8) != 0 ? -1 : i2);
    }

    public final int getEndFrame() {
        return this.endFrame;
    }

    public final void setEndFrame(int i) {
        this.endFrame = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TransitionJsonModel(TransitionJsonModel transitionJsonModel) {
        this(transitionJsonModel.transition, transitionJsonModel.composition, 0, 0, 12/*, (DefaultConstructorMarker) null*/);
    }
}
