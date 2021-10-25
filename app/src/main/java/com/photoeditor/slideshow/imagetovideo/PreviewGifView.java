package com.photoeditor.slideshow.imagetovideo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.TextureView;

import com.photoeditor.slideshow.java.util.ThreadPool;

import java.util.concurrent.atomic.AtomicBoolean;

public class PreviewGifView extends TextureView {
    Canvas canvas = null;
    private AtomicBoolean checkLock = new AtomicBoolean(false);
    private Integer currentFrame = 0;
    private VideoMaker mVideoMaker;

    public PreviewGifView(Context context) {
        super(context);
    }

    public void setVideoMaker(VideoMaker videoMaker) {
        this.mVideoMaker = videoMaker;
    }

    public void previewAtFrame(int currentFrame) {
        this.currentFrame = currentFrame;
        if (!this.checkLock.get()) {
            ThreadPool.globleExecutor().submit(new Runnable() {
                public final void run() {
                    checkLock.set(true);
                    Canvas lockCanvas = lockCanvas();
                    canvas = lockCanvas;
                    try {
                        lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        unlockCanvasAndPost(canvas);
                        throw th;
                    }
                    unlockCanvasAndPost(canvas);
                    checkLock.set(false);
                }
            });
        }
    }
}
