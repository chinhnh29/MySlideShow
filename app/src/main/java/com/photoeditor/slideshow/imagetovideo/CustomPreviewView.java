package com.photoeditor.slideshow.imagetovideo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.util.concurrent.ScheduledFuture;

public class CustomPreviewView extends FrameLayout {
    private Integer currentFrame = 0;
    private ScheduledFuture<?> gotoFrameSchedule;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean isChangerSticker;
    private VideoMaker mVideoMaker;
    private OnTouchInDecorListener onTouchInDecorListener;
    private PreviewImageView preViewImage;
    private PreviewGifView previewGifView;

    public interface OnTouchInDecorListener {
//        void onTouchInDecor(Decor decor);
    }

    public void handleTouchEvent(MotionEvent motionEvent) {
    }

    public boolean isChangerSticker() {
        return this.isChangerSticker;
    }

    public void setChangerSticker(boolean z) {
        this.isChangerSticker = z;
    }

    public void setOnTouchInDecorListener(OnTouchInDecorListener onTouchInDecorListener2) {
        this.onTouchInDecorListener = onTouchInDecorListener2;
    }

    public CustomPreviewView(Context context) {
        super(context);
        init();
    }

    public CustomPreviewView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_HARDWARE, null);
    }

    public void setVideoMaker(VideoMaker videoMaker) {
        this.preViewImage = new PreviewImageView(getContext());
        this.previewGifView = new PreviewGifView(getContext());
        LayoutParams layoutParams = new LayoutParams(getMeasuredWidth(), getMeasuredHeight());
        new LayoutParams(getMeasuredWidth(), getMeasuredHeight());
        addView(this.preViewImage, layoutParams);
        this.mVideoMaker = videoMaker;
        this.preViewImage.setVideoMaker(videoMaker);
        this.previewGifView.setVideoMaker(videoMaker);
        this.previewGifView.setOpaque(false);
        this.previewGifView.setAlpha(1.0f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        VideoMaker.WIDTH_PREVIEW = getMeasuredWidth();
        VideoMaker.HEIGHT_PREVIEW = getMeasuredHeight();
    }

    public void previewAtFrame(int i) {
        Log.e("ChinhNH", "previewAtFrame: " + i);
        this.currentFrame = i;
        preViewImage.previewAtFrame(i);
        previewGifView.previewAtFrame(i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        handleTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}