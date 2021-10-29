package com.photoeditor.slideshow.components;

import android.media.MediaPlayer;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.SeekBar;
import com.photoeditor.slideshow.imagetovideo.ConvertDurationUtils;


public final class MyVideoPlayer$initEvent$1 implements OnRangeChangedListener {
    final /* synthetic */ MyVideoPlayer this$0;

    public void onProgress(RangeSeekBar rangeSeekBar, float f) {
    }

    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
    }

    MyVideoPlayer$initEvent$1(MyVideoPlayer myVideoPlayer) {
        this.this$0 = myVideoPlayer;
    }

    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
        this.this$0.pausePreview();
    }

    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
        MyVideoPlayer myVideoPlayer = this.this$0;
        SeekBar leftSeekBar = rangeSeekBar.getLeftSeekBar();
        myVideoPlayer.setMCurrentFrame(Math.round((leftSeekBar.getProgress() * ((float) this.this$0.getMTotalFrames())) / ((float) 100)));
        this.this$0.mTvTimeControl.setText(ConvertDurationUtils.convertDurationText(this.this$0.getMCurrentFrame() / 30));
        MyVideoPlayer myVideoPlayer2 = this.this$0;
        myVideoPlayer2.previewFrame(myVideoPlayer2.getMCurrentFrame());
        if (this.this$0.getMAudioPreview() != null) {
            MediaPlayer mAudioPreview = this.this$0.getMAudioPreview();
            if (mAudioPreview != null) {
                mAudioPreview.pause();
            }
            MediaPlayer mAudioPreview2 = this.this$0.getMAudioPreview();
            if (mAudioPreview2.getCurrentPosition() >= this.this$0.getEndTime() * 1000) {
                if (this.this$0.isLoopMusic()) {
                    MediaPlayer mAudioPreview3 = this.this$0.getMAudioPreview();
                    if (mAudioPreview3 != null) {
                        mAudioPreview3.seekTo(this.this$0.getStartTime() * 1000);
                    }
                } else {
                    MediaPlayer mAudioPreview4 = this.this$0.getMAudioPreview();
                    if (mAudioPreview4 != null) {
                        mAudioPreview4.seekTo(0);
                    }
                    MediaPlayer mAudioPreview5 = this.this$0.getMAudioPreview();
                    if (mAudioPreview5 != null) {
                        mAudioPreview5.pause();
                    }
                }
            } else if (this.this$0.mIsImagePreviewing) {
                MediaPlayer mAudioPreview6 = this.this$0.getMAudioPreview();
                if (mAudioPreview6.isPlaying()) {
                    MediaPlayer mAudioPreview7 = this.this$0.getMAudioPreview();
                    mAudioPreview7.seekTo((int) (((((((float) this.this$0.getMCurrentFrame()) * 1.0f) / ((float) 30)) * ((float) 1000)) % ((float) ((this.this$0.getEndTime() - this.this$0.getStartTime()) * 1000))) + ((float) (this.this$0.getStartTime() * 1000))));
                }
            }
        }
        this.this$0.resumePreview();
    }
}
