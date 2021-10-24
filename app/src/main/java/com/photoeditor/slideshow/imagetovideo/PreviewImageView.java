package com.photoeditor.slideshow.imagetovideo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

public class PreviewImageView extends View {
    Canvas canvas = null;
    public AtomicBoolean checkLock = new AtomicBoolean(false);
    private Integer currentFrame = 0;
    private VideoMaker mVideoMaker;

    public PreviewImageView(Context context) {
        super(context);
    }

    public void setVideoMaker(VideoMaker videoMaker) {
        setLayerType(LAYER_TYPE_HARDWARE, (Paint) null);
        this.mVideoMaker = videoMaker;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas2) {
        mVideoMaker.previewImage(canvas2, this.currentFrame);
        checkLock.set(false);
    }

    public void previewAtFrame(int i) {
        if (!this.checkLock.get()) {
            this.checkLock.set(true);
            this.currentFrame = i;
            postInvalidate();
        }
    }
}
