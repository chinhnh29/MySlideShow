package com.photoeditor.slideshow.components;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaygoo.widget.RangeSeekBar;
import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.common.ExtentionsKt;
import com.photoeditor.slideshow.enumm.VIDEO_RATIO;
//import com.photoeditor.slideshow.fragment.main.MainFragment;
import com.photoeditor.slideshow.imagetovideo.ConvertDurationUtils;
import com.photoeditor.slideshow.imagetovideo.CustomPreviewView;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;
import com.photoeditor.slideshow.interfaces.VideoPlayInterface;
import com.photoeditor.slideshow.java.PhotorTool;
//import com.photoeditor.slideshow.models.DrafVideoModel;
import com.photoeditor.slideshow.models.GifTheme;
import com.photoeditor.slideshow.models.ThemeLottieModel;
//import com.photoeditor.slideshow.test.MusicCutView;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;


public final class MyVideoPlayer {
    private int beforeFrame;
    public final Context context;
    private GifTheme currentThemeModel;
    private int currentTimePlay;
    //    private final DrafVideoModel drafVideoModel;
    /* access modifiers changed from: private */
    public int duration;
    private int endTime;
    private final ImageView img_play;
    /* access modifiers changed from: private */
    public boolean isChangeTime;
    private boolean isLoopMusic;
    private boolean isPlay;
    //    private Job job;
//    private final CompletableJob jobLoadImage;
    private long lastTime;
    private ArrayList<String> listImage;
    /* access modifiers changed from: private */
    public final ArrayList<ThemeLottieModel> listThemeLottieModel;
    /* access modifiers changed from: private */
    public int loopCount;
    private MediaPlayer mAudioPreview;
    private int mCurrentFrame;
    private String mCurrentSongPath;
    private CustomPreviewView mCustomPreviewView;
    private boolean mHasTrimAudio;
    private Handler mImagePreview;
    private final ImageView mImgControl;
    /* access modifiers changed from: private */
    public boolean mIsImagePreviewing;
    private boolean mIsPreviewStopping;
    /* access modifiers changed from: private */
    public final RangeSeekBar mSeekBar;
    private int mTotalFrames;
    private final TextView mTvDuration;
    /* access modifiers changed from: private */
    public final TextView mTvTimeControl;
    private VideoMaker mVideoMaker;
    private float mVolume;
    //    private MusicCutView musicCutView;
    private GifTheme oldThemeModel;
    private int startTime;
    /* access modifiers changed from: private */
    public int totalFrameMusic;
    private Handler transitionPreview;
    /* access modifiers changed from: private */
    public VideoPlayInterface videoPlayInterface;

    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[VIDEO_RATIO.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[VIDEO_RATIO.MUOI_SAU_CHIN.ordinal()] = 1;
            iArr[VIDEO_RATIO.CHIN_MUOI_SAU.ordinal()] = 2;
            iArr[VIDEO_RATIO.BA_BON.ordinal()] = 3;
            iArr[VIDEO_RATIO.BON_BA.ordinal()] = 4;
            int[] iArr2 = new int[VIDEO_RATIO.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[VIDEO_RATIO.MUOI_SAU_CHIN.ordinal()] = 1;
            iArr2[VIDEO_RATIO.CHIN_MUOI_SAU.ordinal()] = 2;
            iArr2[VIDEO_RATIO.BA_BON.ordinal()] = 3;
            iArr2[VIDEO_RATIO.BON_BA.ordinal()] = 4;
            iArr2[VIDEO_RATIO.MOT_MOT.ordinal()] = 5;
        }
    }

    public MyVideoPlayer(/*MusicCutView musicCutView2,*/ Context context2, VideoMaker videoMaker,
                                                         ArrayList<String> arrayList, CustomPreviewView customPreviewView, RangeSeekBar rangeSeekBar,
                                                         ImageView mImgControl, ImageView img_play, TextView mTvTimeControl, TextView mTvDuration,
                                                         VideoPlayInterface videoPlayInterface2/*, DrafVideoModel drafVideoModel2*/) {
//        this.musicCutView = musicCutView2;
        this.context = context2;
        this.mSeekBar = rangeSeekBar;
        this.mImgControl = mImgControl;
        this.img_play = img_play;
        this.mTvTimeControl = mTvTimeControl;
        this.mTvDuration = mTvDuration;
//        this.drafVideoModel = drafVideoModel2;
        this.duration = 30000;
        this.isPlay = true;
        this.mVolume = 1.0f;
        this.isLoopMusic = true;
        this.isChangeTime = true;
        this.listImage = arrayList;
        this.videoPlayInterface = videoPlayInterface2;
        this.mCustomPreviewView = customPreviewView;
        this.mVideoMaker = videoMaker;
        mVideoMaker.setContext(context2);
        initVideo();
        initEvent();
//        this.jobLoadImage = JobKt.Job$default((Job) null, 1, (Object) null);
        this.listThemeLottieModel = new ArrayList<>();
    }

//    public final MusicCutView getMusicCutView() {
//        return this.musicCutView;
//    }

//    public final void setMusicCutView(MusicCutView musicCutView2) {
//        this.musicCutView = musicCutView2;
//    }

    public final boolean isPlay() {
        return this.isPlay;
    }

    public final void setPlay(boolean z) {
        this.isPlay = z;
    }

    public final int getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(int i) {
        this.startTime = i;
    }

    public final int getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(int i) {
        this.endTime = i;
    }

    public final String getMCurrentSongPath() {
        return this.mCurrentSongPath;
    }

    public final void setMCurrentSongPath(String str) {
        this.mCurrentSongPath = str;
    }

    public final int getMCurrentFrame() {
        return this.mCurrentFrame;
    }

    public final void setMCurrentFrame(int i) {
        this.mCurrentFrame = i;
    }

    public final VideoMaker getMVideoMaker() {
        return this.mVideoMaker;
    }

    public final void setMVideoMaker(VideoMaker videoMaker) {
        this.mVideoMaker = videoMaker;
    }

    public final ArrayList<String> getListImage() {
        return this.listImage;
    }

    public final void setListImage(ArrayList<String> arrayList) {
        this.listImage = arrayList;
    }

    public final CustomPreviewView getMCustomPreviewView() {
        return this.mCustomPreviewView;
    }

    public final void setMCustomPreviewView(CustomPreviewView customPreviewView) {
        this.mCustomPreviewView = customPreviewView;
    }

    public final int getMTotalFrames() {
        return this.mTotalFrames;
    }

    public final void setMTotalFrames(int i) {
        this.mTotalFrames = i;
    }

    public final MediaPlayer getMAudioPreview() {
        return this.mAudioPreview;
    }

    public final void setMAudioPreview(MediaPlayer mediaPlayer) {
        this.mAudioPreview = mediaPlayer;
    }

    public final Handler getMImagePreview() {
        return this.mImagePreview;
    }

    public final void setMImagePreview(Handler handler) {
        this.mImagePreview = handler;
    }

    public final Handler getTransitionPreview() {
        return this.transitionPreview;
    }

    public final void setTransitionPreview(Handler handler) {
        this.transitionPreview = handler;
    }

    public final float getMVolume() {
        return this.mVolume;
    }

    public final void setMVolume(float f) {
        this.mVolume = f;
    }

    public final boolean getMHasTrimAudio() {
        return this.mHasTrimAudio;
    }

    public final void setMHasTrimAudio(boolean z) {
        this.mHasTrimAudio = z;
    }

    public final boolean isLoopMusic() {
        return this.isLoopMusic;
    }

    public final void setLoopMusic(boolean z) {
        this.isLoopMusic = z;
    }

    private final void initVideo() {
//        DrafVideoModel drafVideoModel2 = this.drafVideoModel;
//        if (drafVideoModel2 != null) {
//            VideoMaker videoMaker = this.mVideoMaker;
//            if (videoMaker != null) {
//                videoMaker.addVideoModel(drafVideoModel2);
//            }
//        } else {
        mVideoMaker.addImages2(this.listImage);
        initView();
    }

    public final void setListener(VideoPlayInterface videoPlayInterface2) {
        this.videoPlayInterface = videoPlayInterface2;
    }

    private final void initView() {
        CustomPreviewView customPreviewView = this.mCustomPreviewView;
        if (customPreviewView != null) {
            customPreviewView.setVideoMaker(this.mVideoMaker);
        }
        VideoMaker videoMaker = this.mVideoMaker;
        this.mTotalFrames = videoMaker.getTotalFrames();
        this.mImagePreview = new Handler();
        this.transitionPreview = new Handler();
        this.mTvDuration.setText(ConvertDurationUtils.convertDurationText(this.mTotalFrames / 30));
    }

    public final int getTotalTime() {
        return this.mTotalFrames / 30;
    }

    public final void updateTimeVideo() {
        VideoMaker videoMaker = this.mVideoMaker;
        if (videoMaker != null) {
            int totalFrames = videoMaker.getTotalFrames();
            this.mTotalFrames = totalFrames;
            this.mTvDuration.setText(ConvertDurationUtils.convertDurationText(totalFrames / 30));
        }
    }

    public final void setMusic(String str, int i, int i2) {
//        this.startTime = i;
//        this.loopCount = 0;
//        this.currentTimePlay = 0;
//        this.mCurrentSongPath = str;
//        this.endTime = i2;
//        VideoMaker videoMaker = this.mVideoMaker;
//        if (videoMaker != null) {
//            videoMaker.setParamsTrimAudio(i, i2);
//        }
//        this.totalFrameMusic = (this.endTime - this.startTime) * 30;
//        try {
//            MediaPlayer mediaPlayer = new MediaPlayer();
//            this.mAudioPreview = mediaPlayer;
//            if (mediaPlayer != null && str != null) {
//                if (mediaPlayer != null) {
//                    mediaPlayer.setDataSource(str);
//                }
//                MediaPlayer mediaPlayer2 = this.mAudioPreview;
//                if (mediaPlayer2 != null) {
//                    mediaPlayer2.prepareAsync();
//                }
//                MediaPlayer mediaPlayer3 = this.mAudioPreview;
//                if (mediaPlayer3 != null) {
//                    mediaPlayer3.setLooping(true);
//                }
//                MediaPlayer mediaPlayer4 = this.mAudioPreview;
//                if (mediaPlayer4 != null) {
//                    float f = this.mVolume;
//                    mediaPlayer4.setVolume(f, f);
//                }
//                MediaPlayer mediaPlayer5 = this.mAudioPreview;
//                if (mediaPlayer5 != null) {
//                    mediaPlayer5.setOnPreparedListener(new MyVideoPlayer$setMusic$1(this, i));
//                }
//                MediaPlayer mediaPlayer6 = this.mAudioPreview;
//                if (mediaPlayer6 != null) {
//                    mediaPlayer6.setOnCompletionListener(new MyVideoPlayer$setMusic$2(this));
//                }
//            }
//        } catch (Exception unused) {
//        }
    }

    public final void clearMusic() {
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
        MediaPlayer mediaPlayer2 = this.mAudioPreview;
        if (mediaPlayer2 != null) {
            mediaPlayer2.stop();
        }
        MediaPlayer mediaPlayer3 = this.mAudioPreview;
        if (mediaPlayer3 != null) {
            mediaPlayer3.release();
        }
        this.mAudioPreview = null;
        this.mCurrentSongPath = null;
    }

    public final void setStartEndMusic(int i, int i2) {
        this.startTime = i;
        this.endTime = i2;
        this.totalFrameMusic = (i2 - i) * 30;
        VideoMaker videoMaker = this.mVideoMaker;
        if (videoMaker != null) {
            videoMaker.setParamsTrimAudio(i, i2);
        }
    }

    public final void updateTimeEndMusic(int i) {
        if (this.mCurrentSongPath != null) {
            int i2 = this.startTime;
            int i3 = i + i2;
            this.endTime = i3;
            this.totalFrameMusic = (i3 - i2) * 30;
            VideoMaker videoMaker = this.mVideoMaker;
            if (videoMaker != null) {
                videoMaker.setParamsTrimAudio(i2, i3);
            }
        }
    }

    private final void initEvent() {
        this.mSeekBar.setOnRangeChangedListener(new MyVideoPlayer$initEvent$1(this));
    }

    public final void clickPlayPause() {
        if (this.isPlay) {
            pausePreview();
        } else {
            resumePreview();
        }
    }

    public final void pausePreview() {
        Handler handler = this.mImagePreview;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        try {
            MediaPlayer mediaPlayer = this.mAudioPreview;
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        } catch (IllegalStateException unused) {
        }
        this.mImgControl.setVisibility(View.VISIBLE);
        img_play.setImageResource(R.drawable.ic_play_music);
//        Job job2 = this.job;
//        if (job2 != null) {
//            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
//        }
    }

    public final void actionHaveNewImage(ArrayList<String> arrayList) {
        this.listImage = arrayList;
        VideoMaker videoMaker = this.mVideoMaker;
        if (videoMaker != null) {
            videoMaker.hasNewImage(arrayList);
        }
        this.mCurrentFrame = 0;
        resumePreview();
        VideoMaker videoMaker2 = this.mVideoMaker;
        int totalFrames = videoMaker2.getTotalFrames();
        this.mTotalFrames = totalFrames;
        this.mTvDuration.setText(ConvertDurationUtils.convertDurationText(totalFrames / 30));
    }

    public final void resumePreview() {
        this.mImgControl.setVisibility(View.GONE);
        img_play.setImageResource(R.drawable.ic_pause_new);
        this.isPlay = true;
        try {
            int i = this.mCurrentFrame;
            int i2 = this.totalFrameMusic;
            if (i <= i2) {
                MediaPlayer mediaPlayer = this.mAudioPreview;
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo((this.startTime * 1000) + ((i / 30) * 1000));
                }
                MediaPlayer mediaPlayer2 = this.mAudioPreview;
                if (mediaPlayer2 != null) {
                    mediaPlayer2.setLooping(true);
                }
                MediaPlayer mediaPlayer3 = this.mAudioPreview;
                if (mediaPlayer3 != null) {
                    mediaPlayer3.start();
                }
            } else if (this.isLoopMusic) {
                MediaPlayer mediaPlayer4 = this.mAudioPreview;
                if (mediaPlayer4 != null) {
                    mediaPlayer4.seekTo((this.startTime * 1000) + (((i - i2) / 30) * 1000));
                }
                MediaPlayer mediaPlayer5 = this.mAudioPreview;
                if (mediaPlayer5 != null) {
                    mediaPlayer5.setLooping(true);
                }
                MediaPlayer mediaPlayer6 = this.mAudioPreview;
                if (mediaPlayer6 != null) {
                    mediaPlayer6.start();
                }
            } else {
                MediaPlayer mediaPlayer7 = this.mAudioPreview;
                if (mediaPlayer7 != null) {
                    mediaPlayer7.pause();
                }
            }
        } catch (IllegalStateException unused) {
        }
        playSlideShow();
    }

    public final void changeVolume(int i) {
        float f = (((float) i) * 1.0f) / ((float) 100);
        this.mVolume = f;
        VideoMaker.VOLUME = f;
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            float f2 = this.mVolume;
            mediaPlayer.setVolume(f2, f2);
        }
    }

    public final void playSlideShow() {
        if (mImagePreview != null) {
            mImagePreview.removeCallbacksAndMessages(null);
            mImagePreview.post(runnablePreview(mImagePreview));
        }
    }

    private final Runnable runnablePreview(Handler handler) {
        return new MyVideoPlayer$runnablePreview$1(this, handler, (long) 33);
    }

    /* access modifiers changed from: private */
    public final void previewFrame(int i) {
        this.lastTime = System.currentTimeMillis();
        CustomPreviewView customPreviewView = this.mCustomPreviewView;
        if (customPreviewView != null) {
            customPreviewView.previewAtFrame(i);
        }
    }

    public final int getBeforeFrame() {
        return this.beforeFrame;
    }

    public final void setBeforeFrame(int i) {
        this.beforeFrame = i;
    }

    public final void seekVideoToSecond(float f) {
        float f2 = (float) 30;
        int i = (int) (f * f2);
        this.mCurrentFrame = i;
        previewFrame(i);
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
            MediaPlayer mediaPlayer2 = this.mAudioPreview;
            if (mediaPlayer2.getCurrentPosition() >= this.endTime * 1000) {
                MediaPlayer mediaPlayer3 = this.mAudioPreview;
                if (mediaPlayer3 != null) {
                    mediaPlayer3.seekTo(this.startTime * 1000);
                }
            } else if (this.mIsImagePreviewing) {
                MediaPlayer mediaPlayer4 = this.mAudioPreview;
                if (mediaPlayer4.isPlaying()) {
                    MediaPlayer mediaPlayer5 = this.mAudioPreview;
                    float f3 = ((((float) this.mCurrentFrame) * 1.0f) / f2) * ((float) 1000);
                    int i2 = this.endTime;
                    int i3 = this.startTime;
                    mediaPlayer5.seekTo((int) ((f3 % ((float) ((i2 - i3) * 1000))) + ((float) (i3 * 1000))));
                }
            }
        }
        MediaPlayer mediaPlayer6 = this.mAudioPreview;
        if (mediaPlayer6 != null) {
            float f4 = ((((float) this.mCurrentFrame) * 1.0f) / f2) * ((float) 1000);
            int i4 = this.endTime;
            int i5 = this.startTime;
            mediaPlayer6.seekTo((int) ((f4 % ((float) ((i4 - i5) * 1000))) + ((float) (i5 * 1000))));
        }
    }

    public final void seekVideoTo(float f) {
        int i = (int) (f * ((float) this.mTotalFrames));
        this.mCurrentFrame = i;
        this.mTvTimeControl.setText(ConvertDurationUtils.convertDurationText(i / 30));
        previewFrame(this.mCurrentFrame);
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
            MediaPlayer mediaPlayer2 = this.mAudioPreview;
            if (mediaPlayer2.getCurrentPosition() >= this.endTime * 1000) {
                MediaPlayer mediaPlayer3 = this.mAudioPreview;
                if (mediaPlayer3 != null) {
                    mediaPlayer3.seekTo(this.startTime * 1000);
                }
            } else if (this.mIsImagePreviewing) {
                MediaPlayer mediaPlayer4 = this.mAudioPreview;
                if (mediaPlayer4.isPlaying()) {
                    MediaPlayer mediaPlayer5 = this.mAudioPreview;
                    float f2 = ((((float) this.mCurrentFrame) * 1.0f) / ((float) 30)) * ((float) 1000);
                    int i2 = this.endTime;
                    int i3 = this.startTime;
                    mediaPlayer5.seekTo((int) ((f2 % ((float) ((i2 - i3) * 1000))) + ((float) (i3 * 1000))));
                }
            }
        }
        MediaPlayer mediaPlayer6 = this.mAudioPreview;
        if (mediaPlayer6 != null) {
            float f3 = ((((float) this.mCurrentFrame) * 1.0f) / ((float) 30)) * ((float) 1000);
            int i4 = this.endTime;
            int i5 = this.startTime;
            mediaPlayer6.seekTo((int) ((f3 % ((float) ((i4 - i5) * 1000))) + ((float) (i5 * 1000))));
        }
    }

    public final void restartEnd() {
        this.isChangeTime = true;
        stopPreview();
        startPreview();
    }

    public final void stopPreview() {
//        Job job2 = this.job;
//        if (job2 != null) {
//            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
//        }
//        MainFragment.Companion.setPlayingVideo(false);
        this.currentTimePlay = 0;
        this.loopCount = 0;
        this.mCurrentFrame = 0;
        TextView textView = this.mTvTimeControl;
        VideoMaker videoMaker = this.mVideoMaker;
        textView.setText(videoMaker != null ? ConvertDurationUtils.convertDurationText(videoMaker.getTotalFrames() / 30) : null);
        try {
            MediaPlayer mediaPlayer = this.mAudioPreview;
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        } catch (IllegalStateException unused) {
        }
        Handler handler = this.mImagePreview;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        this.mIsImagePreviewing = false;
        this.mIsPreviewStopping = true;
    }

    public final void startPreview() {
//        MainFragment.Companion.setPlayingVideo(true);
        ImageView imageView = this.mImgControl;

        mImgControl.setVisibility(View.GONE);
        img_play.setImageResource(R.drawable.ic_pause_new);
        checkTrimAndLoop();
        this.mIsImagePreviewing = true;
        this.mIsPreviewStopping = false;
        this.mCurrentFrame = 0;
        VideoMaker videoMaker = this.mVideoMaker;
        Integer valueOf = videoMaker != null ? videoMaker.getTotalFrames() : null;
        this.mTotalFrames = valueOf.intValue();
        playSlideShow();
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(this.startTime * 1000);
        }
        MediaPlayer mediaPlayer2 = this.mAudioPreview;
        if (mediaPlayer2 != null) {
            mediaPlayer2.start();
        }
    }

    private final void checkTrimAndLoop() {
        this.mHasTrimAudio = true;
    }

    public final boolean isMusicLongerVideo() {
        float f = (float) (this.endTime - this.startTime);
        VideoMaker videoMaker = this.mVideoMaker;
        Float valueOf = videoMaker != null ? videoMaker.getTotalDuration() : null;
        return f >= valueOf.floatValue();
    }

    public final void release() {
        VideoMaker videoMaker = this.mVideoMaker;
        if (videoMaker != null) {
            videoMaker.release();
        }
    }

    public final void changeVideoRatio(VIDEO_RATIO video_ratio) {
        int i;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        int i2 = 0;
        int i3 = PhotorTool.getDisplayInfo().widthPixels;
        int i4 = WhenMappings.$EnumSwitchMapping$0[video_ratio.ordinal()];
        if (i4 != 1) {
            if (i4 == 2) {
                i2 = (i3 * 9) / 16;
            } else if (i4 != 3) {
                i = i4 != 4 ? i3 : (i3 * 3) / 4;
            } else {
                i2 = (i3 * 3) / 4;
            }
            int i5 = i2;
            i = i3;
            i3 = i5;
        } else {
            i = (i3 * 9) / 16;
        }
        CustomPreviewView customPreviewView = this.mCustomPreviewView;
        if (!(customPreviewView == null || (layoutParams2 = customPreviewView.getLayoutParams()) == null)) {
            layoutParams2.width = i3;
        }
        CustomPreviewView customPreviewView2 = this.mCustomPreviewView;
        if (!(customPreviewView2 == null || (layoutParams = customPreviewView2.getLayoutParams()) == null)) {
            layoutParams.height = i;
        }
        CustomPreviewView customPreviewView3 = this.mCustomPreviewView;
        if (customPreviewView3 != null) {
            customPreviewView3.requestLayout();
        }
        VideoMaker videoMaker = this.mVideoMaker;
        if (videoMaker != null) {
            videoMaker.changeVideoRatio(video_ratio);
        }
    }

    public final void changeVideoRatio(View view, View view2, VIDEO_RATIO video_ratio) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        CustomPreviewView customPreviewView = this.mCustomPreviewView;
        ViewGroup.LayoutParams layoutParams = customPreviewView != null ? customPreviewView.getLayoutParams() : null;
        ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
        int i = WhenMappings.$EnumSwitchMapping$1[video_ratio.ordinal()];
        if (i == 1) {
            int i2 = (measuredWidth * 9) / 16;
            if (layoutParams != null) {
                resize(layoutParams, 1.7777778f, measuredWidth, measuredHeight);
            }
            resize(layoutParams2, 1.7777778f, measuredWidth, measuredHeight);
        } else if (i == 2) {
            int i3 = (measuredWidth * 9) / 16;
            if (layoutParams != null) {
                resize(layoutParams, 0.5625f, measuredWidth, measuredHeight);
            }
            resize(layoutParams2, 0.5625f, measuredWidth, measuredHeight);
        } else if (i == 3) {
            int i4 = (measuredWidth * 3) / 4;
            if (layoutParams != null) {
                resize(layoutParams, 0.75f, measuredWidth, measuredHeight);
            }
            resize(layoutParams2, 0.75f, measuredWidth, measuredHeight);
        } else if (i == 4) {
            int i5 = (measuredWidth * 3) / 4;
            if (layoutParams != null) {
                resize(layoutParams, 1.3333334f, measuredWidth, measuredHeight);
            }
            resize(layoutParams2, 1.3333334f, measuredWidth, measuredHeight);
        } else if (i == 5) {
            if (layoutParams != null) {
                resize(layoutParams, 1.0f, measuredWidth, measuredHeight);
            }
            resize(layoutParams2, 1.0f, measuredWidth, measuredHeight);
        }
        view2.setLayoutParams(layoutParams2);
        view2.requestLayout();
        CustomPreviewView customPreviewView2 = this.mCustomPreviewView;
        if (customPreviewView2 != null) {
            customPreviewView2.setLayoutParams(layoutParams);
        }
        CustomPreviewView customPreviewView3 = this.mCustomPreviewView;
        if (customPreviewView3 != null) {
            customPreviewView3.requestLayout();
        }
        restartEnd();
        VideoMaker videoMaker = this.mVideoMaker;
        if (videoMaker != null) {
            videoMaker.changeVideoRatio(video_ratio);
        }
    }

    private final void resize(ViewGroup.LayoutParams layoutParams, float f, int i, int i2) {
        layoutParams.width = i;
        layoutParams.height = (int) (((float) i) / f);
        if (layoutParams.height > i2) {
            layoutParams.height = i2;
            layoutParams.width = (int) (((float) i2) * f);
        }
    }

    public final void acceptTheme() {
        this.oldThemeModel = this.currentThemeModel;
    }

    public final void cancelTheme() {
        if (!this.oldThemeModel.equals(this.currentThemeModel)) {
            chooseThemeNew(this.oldThemeModel);
        }
    }

    public final void chooseThemeNew(GifTheme gifTheme) {
        VideoMaker videoMaker;
        this.currentThemeModel = gifTheme;
        if (!(gifTheme == null || (videoMaker = this.mVideoMaker) == null)) {
            videoMaker.chooseThemeNew(gifTheme);
        }
        resumePreview();
    }

    private final void getLottieDrawable(GifTheme gifTheme) {
//        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(this.jobLoadImage)), (CoroutineContext) null, (CoroutineStart) null, new MyVideoPlayer$getLottieDrawable$1(this, gifTheme, (Continuation) null), 3, (Object) null);
    }
}
