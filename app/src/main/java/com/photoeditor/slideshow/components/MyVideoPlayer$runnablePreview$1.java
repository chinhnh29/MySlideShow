package com.photoeditor.slideshow.components;

import android.media.MediaPlayer;
import android.os.Handler;
import com.photoeditor.slideshow.imagetovideo.ConvertDurationUtils;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;
import com.photoeditor.slideshow.interfaces.VideoPlayInterface;



public final class MyVideoPlayer$runnablePreview$1 implements Runnable {
    final /* synthetic */ Handler handler;
    final /* synthetic */ long $time;
    final /* synthetic */ MyVideoPlayer myVideoPlayer;

    MyVideoPlayer$runnablePreview$1(MyVideoPlayer myVideoPlayer, Handler handler, long j) {
        this.myVideoPlayer = myVideoPlayer;
        this.handler = handler;
        this.$time = j;
    }

    public void run() {
        if (this.myVideoPlayer.getMAudioPreview() != null) {
            if (this.myVideoPlayer.getMCurrentFrame() < 30) {
                MyVideoPlayer myVideoPlayer = this.myVideoPlayer;
                myVideoPlayer.setMVolume((((float) myVideoPlayer.getMCurrentFrame()) * 1.0f) / ((float) 30));
            } else {
                int mCurrentFrame = this.myVideoPlayer.getMCurrentFrame();
                VideoMaker mVideoMaker = this.myVideoPlayer.getMVideoMaker();
                if (mCurrentFrame >= mVideoMaker.totalFrame - 30) {
                    MyVideoPlayer myVideoPlayer2 = this.myVideoPlayer;
                    int mCurrentFrame2 = myVideoPlayer2.getMCurrentFrame();
                    VideoMaker mVideoMaker2 = this.myVideoPlayer.getMVideoMaker();
                    myVideoPlayer2.setMVolume(1.0f - ((((float) ((mCurrentFrame2 - mVideoMaker2.totalFrame) + 30)) * 1.0f) / ((float) 30)));
                } else {
                    this.myVideoPlayer.setMVolume(1.0f);
                }
            }
            MediaPlayer mAudioPreview = this.myVideoPlayer.getMAudioPreview();
            if (mAudioPreview != null) {
                mAudioPreview.setVolume(this.myVideoPlayer.getMVolume(), this.myVideoPlayer.getMVolume());
            }
            if (this.myVideoPlayer.getMCurrentFrame() > this.myVideoPlayer.totalFrameMusic) {
                if (!this.myVideoPlayer.isLoopMusic()) {
                    MediaPlayer mAudioPreview2 = this.myVideoPlayer.getMAudioPreview();
                    if (mAudioPreview2 != null) {
                        mAudioPreview2.pause();
                    }
                } else if (this.myVideoPlayer.isChangeTime) {
                    this.myVideoPlayer.isChangeTime = false;
                    MediaPlayer mAudioPreview3 = this.myVideoPlayer.getMAudioPreview();
                    if (mAudioPreview3 != null) {
                        mAudioPreview3.seekTo(this.myVideoPlayer.getStartTime() * 1000);
                    }
                }
            }
        }
        if (this.myVideoPlayer.getMCurrentFrame() >= this.myVideoPlayer.getMTotalFrames() || !this.myVideoPlayer.mIsImagePreviewing) {
            this.myVideoPlayer.restartEnd();
        } else {
            this.handler.postDelayed(this, this.$time);
        }
        float mCurrentFrame3 = ((float) this.myVideoPlayer.getMCurrentFrame()) / ((float) this.myVideoPlayer.getMTotalFrames());
        VideoPlayInterface access$getVideoPlayInterface$p = this.myVideoPlayer.videoPlayInterface;
        if (access$getVideoPlayInterface$p != null) {
            access$getVideoPlayInterface$p.currentVideoPercent(mCurrentFrame3);
        }
        if (this.myVideoPlayer.mSeekBar.isShown()) {
            this.myVideoPlayer.mSeekBar.setProgress(mCurrentFrame3 * ((float) 100));
        }
        if (this.myVideoPlayer.mTvTimeControl.isShown()) {
            this.myVideoPlayer.mTvTimeControl.setText(ConvertDurationUtils.convertDurationText(this.myVideoPlayer.getMCurrentFrame() / 30));
        }
//        this.this$0.getMusicCutView().setTimePlay(MathKt.roundToInt((((float) this.this$0.getMCurrentFrame()) / ((float) 30)) * ((float) 1000)));
        MyVideoPlayer myVideoPlayer3 = this.myVideoPlayer;
        myVideoPlayer3.previewFrame(myVideoPlayer3.getMCurrentFrame());
        MyVideoPlayer myVideoPlayer4 = this.myVideoPlayer;
        myVideoPlayer4.setBeforeFrame(myVideoPlayer4.getMCurrentFrame());
        MyVideoPlayer myVideoPlayer5 = this.myVideoPlayer;
        myVideoPlayer5.setMCurrentFrame(myVideoPlayer5.getMCurrentFrame() + 1);
    }
}
