package com.photoeditor.slideshow.models.main;

import android.graphics.Bitmap;

import com.photoeditor.slideshow.models.GifTransition;

import java.util.List;

public final class TransitionDrawModel {
    private final List<Bitmap> listBitmap;
    private final GifTransition transition;

    public static /* synthetic */ TransitionDrawModel copy$default(TransitionDrawModel transitionDrawModel, GifTransition gifTransition, List<Bitmap> list, int i, Object obj) {
        if ((i & 1) != 0) {
            gifTransition = transitionDrawModel.transition;
        }
        if ((i & 2) != 0) {
            list = transitionDrawModel.listBitmap;
        }
        return transitionDrawModel.copy(gifTransition, list);
    }

    public final GifTransition component1() {
        return this.transition;
    }

    public final List<Bitmap> component2() {
        return this.listBitmap;
    }

    public final TransitionDrawModel copy(GifTransition gifTransition, List<Bitmap> list) {
//        Intrinsics.checkNotNullParameter(gifTransition, "transition");
//        Intrinsics.checkNotNullParameter(list, "listBitmap");
        return new TransitionDrawModel(gifTransition, list);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransitionDrawModel)) {
            return false;
        }
        TransitionDrawModel transitionDrawModel = (TransitionDrawModel) obj;
        return this.transition.equals(transitionDrawModel.transition) && this.listBitmap.equals (transitionDrawModel.listBitmap);
    }

    public int hashCode() {
        GifTransition gifTransition = this.transition;
        int i = 0;
        int hashCode = (gifTransition != null ? gifTransition.hashCode() : 0) * 31;
        List<Bitmap> list = this.listBitmap;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "TransitionDrawModel(transition=" + this.transition + ", listBitmap=" + this.listBitmap + ")";
    }

    public TransitionDrawModel(GifTransition gifTransition, List<Bitmap> list) {
//        Intrinsics.checkNotNullParameter(gifTransition, "transition");
//        Intrinsics.checkNotNullParameter(list, "listBitmap");
        this.transition = gifTransition;
        this.listBitmap = list;
    }

    public final List<Bitmap> getListBitmap() {
        return this.listBitmap;
    }

    public final GifTransition getTransition() {
        return this.transition;
    }
}
