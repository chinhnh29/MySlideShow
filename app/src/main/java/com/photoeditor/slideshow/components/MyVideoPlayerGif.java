package com.photoeditor.slideshow.components;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.google.firebase.messaging.ServiceStarter;
//import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.SeekBar;
import com.photoeditor.slideshow.ProgressLoadingDialogUtil;
import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.common.ExtentionsKt;
import com.photoeditor.slideshow.enumm.VIDEO_RATIO;
//import com.photoeditor.slideshow.imagetovideo.ConvertDurationUtils;
import com.photoeditor.slideshow.imagetovideo.ConvertDurationUtils;
import com.photoeditor.slideshow.imagetovideo.CustomPreviewView;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;
import com.photoeditor.slideshow.interfaces.VideoPlayInterface;
//import com.photoeditor.slideshow.java.PhotorThread;
//import com.photoeditor.slideshow.java.PhotorTool;
//import com.photoeditor.slideshow.models.DrafVideoModel;
import com.photoeditor.slideshow.java.PhotorThread;
import com.photoeditor.slideshow.models.GifTheme;

//import org.jetbrains.anko.Sdk27PropertiesKt;

import java.io.IOException;
import java.util.ArrayList;

//import kotlin.jvm.internal.DefaultConstructorMarker;
//import kotlin.jvm.internal.Intrinsics;

//import com.gomin.slideshowmaker.videoeditor.videomaker.musicvideo.R;
//import com.google.android.gms.analytics.ecommerce.Promotion;
//import com.photoeditor.slideshow.java.C2671Lo;


public final class MyVideoPlayerGif {
    private int beforeFrame;
    private int currentFrameTran;
    private GifTheme currentThemeModel;
    //    private final DrafVideoModel drafVideoModel;
    private int endTime;
    private ImageView img_play;
    private boolean isPlay;
    private boolean isPreviewTransition;
    private ArrayList<String> listImage;
    private int mAudioDuration;
    private MediaPlayer mAudioPreview;
    private int mCurrentFrame;
    private String mCurrentSongPath;
    private CustomPreviewView mCustomPreviewView;
    private boolean mHasLoopAudio;
    private boolean mHasTrimAudio;
    private Handler mImagePreview;
    private ImageView mImgControl;
    /* access modifiers changed from: private */
    public boolean mIsImagePreviewing;
    private boolean mIsPreviewStopping;
    /* access modifiers changed from: private */
    public final RangeSeekBar mSeekBar;
    private int mTotalFrames;
    private TextView mTvDuration;
    /* access modifiers changed from: private */
    public TextView mTvTimeControl;
    private VideoMaker mVideoMaker;
    private float mVolume;
    private GifTheme oldThemeModel;
    private int startTime;
    private int totalFramesTran;
    private Handler transitionPreview;
    /* access modifiers changed from: private */
    public VideoPlayInterface videoPlayInterface;
    private final View videoView;
    private Context context;

    //    @Metadata(mo49139bv = {1, 0, 3}, mo49142k = 3, mo49143mv = {1, 4, 0})
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

    public final void changeTimeAllVideo(int i, int i2) {
    }

    public MyVideoPlayerGif(Context context, View view, VideoMaker videoMaker, ArrayList<String> listImage,
                            CustomPreviewView customPreviewView, RangeSeekBar rangeSeekBar, ImageView mImgControl,
                            ImageView img_play, TextView mTvTimeControl, TextView mTvDuration,
                            VideoPlayInterface videoPlayInterface/*,
                             DrafVideoModel drafVideoModel2*/) {
        this.videoView = view;
        this.mSeekBar = rangeSeekBar;
        this.mImgControl = mImgControl;
        this.img_play = img_play;
        this.mTvTimeControl = mTvTimeControl;
        this.context =context;
        this.mTvDuration = mTvDuration;
//        this.drafVideoModel = drafVideoModel2;
        this.isPlay = true;
//        this.mAudioDuration = ServiceStarter.ERROR_UNKNOWN;
        this.totalFramesTran = 120;
        this.mVolume = 1.0f;
        this.listImage = listImage;
        this.videoPlayInterface = videoPlayInterface;
        this.mCustomPreviewView = customPreviewView;
        this.mVideoMaker = videoMaker;
        this.mVideoMaker.setContext(context);
        initVideo();
        initEvent();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
//    public /* synthetic */ MyVideoPlayerGif(Context context/*, View view*/, VideoMaker videoMaker,
//                                            ArrayList arrayList, CustomPreviewView customPreviewView,
//            /*RangeSeekBar rangeSeekBar,*/ ImageView imageView,
//                                            ImageView imageView2, TextView textView, TextView textView2,
//                                            VideoPlayInterface videoPlayInterface2,/*
//                                            DrafVideoModel drafVideoModel2, */int i /*,
//            DefaultConstructorMarker defaultConstructorMarker*/) {
//        this(context/*, view*/, videoMaker, arrayList, customPreviewView,
//                /* rangeSeekBar, */imageView, imageView2, textView, textView2, videoPlayInterface2/*,
//                  (i & 4096) != 0 ? null : drafVideoModel2*/);
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

    public final int getMAudioDuration() {
        return this.mAudioDuration;
    }

    public final void setMAudioDuration(int i) {
        this.mAudioDuration = i;
    }

    public final int getMCurrentFrame() {
        return this.mCurrentFrame;
    }

    public final void setMCurrentFrame(int mCurrentFrame) {
        this.mCurrentFrame = mCurrentFrame;
    }

    public final int getCurrentFrameTran() {
        return this.currentFrameTran;
    }

    public final void setCurrentFrameTran(int i) {
        this.currentFrameTran = i;
    }

    public final int getTotalFramesTran() {
        return this.totalFramesTran;
    }

    public final void setTotalFramesTran(int i) {
        this.totalFramesTran = i;
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
//        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
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

    public final boolean getMHasLoopAudio() {
        return this.mHasLoopAudio;
    }

    public final void setMHasLoopAudio(boolean z) {
        this.mHasLoopAudio = z;
    }

    public final boolean getMHasTrimAudio() {
        return this.mHasTrimAudio;
    }

    public final void setMHasTrimAudio(boolean z) {
        this.mHasTrimAudio = z;
    }

    private final void initVideo() {
//        DrafVideoModel drafVideoModel2 = this.drafVideoModel;
//        if (drafVideoModel2 != null) {
//            VideoMaker videoMaker = this.mVideoMaker;
//            if (videoMaker != null) {
//                videoMaker.addVideoModel(drafVideoModel2);
//            }
//        } else {

        mVideoMaker.addImagesGif(this.listImage);
//        }
        initView();
    }

    public final void setListener(VideoPlayInterface videoPlayInterface2) {
//        Intrinsics.checkNotNullParameter(videoPlayInterface2, "videoPlayInterface");
        this.videoPlayInterface = videoPlayInterface2;
    }

    private final void initView() {
        mCustomPreviewView.setVideoMaker(this.mVideoMaker);
        this.mTotalFrames = mVideoMaker.getTotalFrames();
        this.mImagePreview = new Handler();
        this.transitionPreview = new Handler();
        this.mTvDuration.setText(ConvertDurationUtils.convertDurationText(this.mTotalFrames / 30));
    }

    public final int getTotalTime() {
        return this.mTotalFrames / 30;
    }

    public final void updateTimeVideo() {
        this.mTotalFrames = mVideoMaker.getTotalFrames();
    }

    private final void initEvent() {
        mSeekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {

            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                SeekBar leftSeekBar = view.getLeftSeekBar();
                setMCurrentFrame(Math.round((leftSeekBar.getProgress() * ((float) getMTotalFrames())) / ((float) 100)));
                mTvTimeControl.setText(ConvertDurationUtils.convertDurationText(getMCurrentFrame() / 30));
                mCustomPreviewView.previewAtFrame(getMCurrentFrame());
                if (mAudioPreview != null) {
                    mAudioPreview.pause();
                    if (mAudioPreview.getCurrentPosition() >= getEndTime() * 1000) {
                        mAudioPreview.seekTo(getStartTime() * 1000);
                    } else if (mIsImagePreviewing) {
                        if (mAudioPreview.isPlaying()) {
                            mAudioPreview.seekTo((int) (((((((float) getMCurrentFrame()) * 1.0f) /
                                    ((float) 30)) * ((float) 1000)) % ((float) ((getEndTime() - getStartTime()) * 1000))) +
                                    ((float) (getStartTime() * 1000))));
                        }
                    }
                    mAudioPreview.seekTo((int) (((((((float) getMCurrentFrame()) * 1.0f) / ((float) 30)) * ((float) 1000)) % ((float) ((getEndTime() - getStartTime()) * 1000))) + ((float) (getStartTime() * 1000))));
                }
                resumePreview();
            }
        });
    }

    public final void setTimeAudio(int i, int i2) {
        this.startTime = i;
        this.endTime = i2;
        checkTrimAndLoop();
        VideoMaker videoMaker = this.mVideoMaker;
        if (videoMaker != null) {
            videoMaker.setParamsTrimAudio(this.startTime, this.endTime);
        }
    }

    public final void clickPlayPause() {
        if (this.isPlay) {
            pausePreview();
        } else {
            resumePreview();
        }
    }

    public final void pausePreview() {
        this.isPlay = false;
        if (this.isPreviewTransition) {
            Handler handler = this.transitionPreview;
            if (handler != null) {
                handler.removeCallbacksAndMessages((Object) null);
            }
        } else {
            Handler handler2 = this.mImagePreview;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages((Object) null);
            }
            try {
                MediaPlayer mediaPlayer = this.mAudioPreview;
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }
            } catch (IllegalStateException unused) {
            }
        }
//        ExtentionsKt.show(this.mImgControl);
//        Sdk27PropertiesKt.setImageResource(this.img_play, R.drawable.ic_play_new);
    }

    public final void actionHaveNewImage(ArrayList<String> arrayList) {
//        Intrinsics.checkNotNullParameter(arrayList, "list");
        this.listImage = arrayList;
        initVideo();
        this.mCurrentFrame = 0;
        playSlideShow();
    }

    public final void resumePreview() {
//        ExtentionsKt.gone(this.mImgControl);
//        Sdk27PropertiesKt.setImageResource(this.img_play, R.drawable.ic_pause_new);
        this.isPlay = true;
        if (this.isPreviewTransition) {
            Handler handler = this.transitionPreview;
            if (handler != null) {
//                Intrinsics.checkNotNull(handler);
                handler.post(runnablePreviewTransition(handler));
                return;
            }
            return;
        }
        try {
            MediaPlayer mediaPlayer = this.mAudioPreview;
            if (mediaPlayer != null) {
                float f = this.mVolume;
                mediaPlayer.setVolume(f, f);
            }
            MediaPlayer mediaPlayer2 = this.mAudioPreview;
            if (mediaPlayer2 != null) {
                mediaPlayer2.setLooping(true);
            }
            MediaPlayer mediaPlayer3 = this.mAudioPreview;
            if (mediaPlayer3 != null) {
                mediaPlayer3.start();
            }
        } catch (IllegalStateException unused) {
        }
        playSlideShow();
    }

    public final void playSlideShow() {
        if (mImagePreview != null) {
            mImagePreview.removeCallbacksAndMessages(null);
            mImagePreview.post(runnablePreview(mImagePreview));
        }
    }

    public final void clearMusic() {
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        this.mAudioPreview = null;
        this.mCurrentSongPath = null;
    }

    private Runnable runnablePreview(Handler handler) {
        return new Runnable() {
            @Override
            public void run() {
                if (getMCurrentFrame() >= getMTotalFrames() || !mIsImagePreviewing) {
                    restartEnd();
                } else {
                    handler.postDelayed(this, 33);
                }
                float percent = ((float) getMCurrentFrame()) / ((float) getMTotalFrames());
                videoPlayInterface.currentVideoPercent(percent);

                mSeekBar.setProgress(mCurrentFrame * ((float) 100) / mTotalFrames);
                mTvTimeControl.setText(ConvertDurationUtils.convertDurationText(getMCurrentFrame() / 30));

                mCustomPreviewView.previewAtFrame(getMCurrentFrame());
                setBeforeFrame(getMCurrentFrame());
                setMCurrentFrame(getMCurrentFrame() + 1);
            }
        };
    }

    public final int getBeforeFrame() {
        return this.beforeFrame;
    }

    public final void setBeforeFrame(int beforeFrame) {
        this.beforeFrame = beforeFrame;
    }

    public final float getPercent() {
        return ((float) this.mCurrentFrame) / ((float) this.mTotalFrames);
    }

    private final Runnable runnablePreviewTransition(Handler handler) {
        return new Runnable() {
            @Override
            public void run() {
                if (getCurrentFrameTran() >= getTotalFramesTran() || !mIsImagePreviewing) {
                    setCurrentFrameTran(0);
                    handler.postDelayed(this, 33);
                } else {
                    handler.postDelayed(this, 33);
                }
                CustomPreviewView mCustomPreviewView = getMCustomPreviewView();
                if (mCustomPreviewView != null) {
                    mCustomPreviewView.previewAtFrame(getCurrentFrameTran());
                }
                setCurrentFrameTran(getCurrentFrameTran() + 1);
            }
        };
    }

    public final void seekVideoToSecond(float f) {
//        C2671Lo.m320d("vinh123", "second " + f);
        float f2 = (float) 30;
        int i = (int) (f * f2);
        this.mCurrentFrame = i;
        CustomPreviewView customPreviewView = this.mCustomPreviewView;
        if (customPreviewView != null) {
            customPreviewView.previewAtFrame(i);
        }
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
            MediaPlayer mediaPlayer2 = this.mAudioPreview;
//            Intrinsics.checkNotNull(mediaPlayer2);
            if (mediaPlayer2.getCurrentPosition() >= this.endTime * 1000) {
                MediaPlayer mediaPlayer3 = this.mAudioPreview;
                if (mediaPlayer3 != null) {
                    mediaPlayer3.seekTo(this.startTime * 1000);
                }
            } else if (this.mIsImagePreviewing) {
                MediaPlayer mediaPlayer4 = this.mAudioPreview;
//                Intrinsics.checkNotNull(mediaPlayer4);
                if (mediaPlayer4.isPlaying()) {
                    MediaPlayer mediaPlayer5 = this.mAudioPreview;
//                    Intrinsics.checkNotNull(mediaPlayer5);
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
//        this.mTvTimeControl.setText(ConvertDurationUtils.convertDurationText(i / 30));
        CustomPreviewView customPreviewView = this.mCustomPreviewView;
        if (customPreviewView != null) {
            customPreviewView.previewAtFrame(this.mCurrentFrame);
        }
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
            MediaPlayer mediaPlayer2 = this.mAudioPreview;
//            Intrinsics.checkNotNull(mediaPlayer2);
            if (mediaPlayer2.getCurrentPosition() >= this.endTime * 1000) {
                MediaPlayer mediaPlayer3 = this.mAudioPreview;
                if (mediaPlayer3 != null) {
                    mediaPlayer3.seekTo(this.startTime * 1000);
                }
            } else if (this.mIsImagePreviewing) {
                MediaPlayer mediaPlayer4 = this.mAudioPreview;
//                Intrinsics.checkNotNull(mediaPlayer4);
                if (mediaPlayer4.isPlaying()) {
                    MediaPlayer mediaPlayer5 = this.mAudioPreview;
//                    Intrinsics.checkNotNull(mediaPlayer5);
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
        stopPreview();
//        ProgressLoadingDialogUtil.showProgressDialog(context);
        PhotorThread.getInstance().runBackground(new PhotorThread.IBackground() {
            @Override
            public void doingBackground() {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onCompleted() {
//                ProgressLoadingDialogUtil.hideProgressDialog();
                startPreview();
            }
        });
    }



    public final void stopPreview() {
        this.mCurrentFrame = 0;
        TextView textView = this.mTvTimeControl;
        VideoMaker videoMaker = this.mVideoMaker;
        textView.setText(ConvertDurationUtils.convertDurationText(videoMaker.getTotalFrames() / 30));
        try {
            MediaPlayer mediaPlayer = this.mAudioPreview;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        } catch (IllegalStateException unused) {

        }
        if (mImagePreview != null) {
            mImagePreview.removeCallbacksAndMessages(null);
        }
        this.mIsImagePreviewing = false;
        this.mIsPreviewStopping = true;
    }

    private final void previewOnStop() {
        this.mCurrentFrame = 0;
        MediaPlayer mediaPlayer = this.mAudioPreview;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        Handler handler = this.mImagePreview;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        this.mIsImagePreviewing = false;
        this.mIsPreviewStopping = true;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0027 A[Catch:{ IOException -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0030 A[Catch:{ IOException -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041 A[Catch:{ IOException -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050 A[Catch:{ IOException -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void startPreview() {
        mImgControl.setVisibility(View.GONE);
        img_play.setImageResource(R.drawable.ic_pause_new);
        if (mAudioPreview != null) mAudioPreview.release();
        try {
            checkTrimAndLoop();
            this.mIsImagePreviewing = true;
            this.mIsPreviewStopping = false;
            this.mCurrentFrame = 0;
            mTotalFrames = mVideoMaker.getTotalFrames();
            playSlideShow();
            if (mCurrentSongPath != null) {
                if (this.mAudioPreview == null) {
                    this.mAudioPreview = new MediaPlayer();
                }
                mAudioPreview.setDataSource(mCurrentSongPath);
                mAudioPreview.prepare();
                mAudioPreview.setLooping(true);
                mAudioPreview.seekTo(this.startTime * 1000);
                float f = this.mVolume;
                mAudioPreview.setVolume(f, f);
                mAudioPreview.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final void checkTrimAndLoop() {
        this.mHasLoopAudio = false;
        this.mHasTrimAudio = true;
    }

    public final void release() {
//        VideoMaker videoMaker = this.mVideoMaker;
//        if (videoMaker != null) {
//            videoMaker.release();
//        }
    }

    public final void changeVideoRatio(VIDEO_RATIO video_ratio) {
        int i;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        int i2;
//        Intrinsics.checkNotNullParameter(video_ratio, "ratio");
//        int i3 = PhotorTool.getDisplayInfo().widthPixels;
//        int i4 = WhenMappings.$EnumSwitchMapping$0[video_ratio.ordinal()];
//        if (i4 != 1) {
//            if (i4 == 2) {
//                i2 = (i3 * 9) / 16;
//            } else if (i4 != 3) {
//                i = i4 != 4 ? i3 : (i3 * 3) / 4;
//            } else {
//                i2 = (i3 * 3) / 4;
//            }
//            int i5 = i2;
//            i = i3;
//            i3 = i5;
//        } else {
//            i = (i3 * 9) / 16;
//        }
//        CustomPreviewView customPreviewView = this.mCustomPreviewView;
//        if (!(customPreviewView == null || (layoutParams2 = customPreviewView.getLayoutParams()) == null)) {
//            layoutParams2.width = i3;
//        }
//        CustomPreviewView customPreviewView2 = this.mCustomPreviewView;
//        if (!(customPreviewView2 == null || (layoutParams = customPreviewView2.getLayoutParams()) == null)) {
//            layoutParams.height = i;
//        }
//        CustomPreviewView customPreviewView3 = this.mCustomPreviewView;
//        if (customPreviewView3 != null) {
//            customPreviewView3.requestLayout();
//        }
//        restartEnd();
//        VideoMaker videoMaker = this.mVideoMaker;
//        if (videoMaker != null) {
//            videoMaker.changeVideoRatio(video_ratio);
//        }
    }

    public final void changeVideoRatio(View view, View view2, VIDEO_RATIO video_ratio) {
//        Intrinsics.checkNotNullParameter(view, "viewParent");
//        Intrinsics.checkNotNullParameter(view2, Promotion.ACTION_VIEW);
//        Intrinsics.checkNotNullParameter(video_ratio, "ratio");
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
//            Intrinsics.checkNotNullExpressionValue(layoutParams2, "viewLayoutParams");
            resize(layoutParams2, 1.7777778f, measuredWidth, measuredHeight);
        } else if (i == 2) {
            int i3 = (measuredWidth * 9) / 16;
            if (layoutParams != null) {
                resize(layoutParams, 0.5625f, measuredWidth, measuredHeight);
            }
//            Intrinsics.checkNotNullExpressionValue(layoutParams2, "viewLayoutParams");
            resize(layoutParams2, 0.5625f, measuredWidth, measuredHeight);
        } else if (i == 3) {
            int i4 = (measuredWidth * 3) / 4;
            if (layoutParams != null) {
                resize(layoutParams, 0.75f, measuredWidth, measuredHeight);
            }
//            Intrinsics.checkNotNullExpressionValue(layoutParams2, "viewLayoutParams");
            resize(layoutParams2, 0.75f, measuredWidth, measuredHeight);
        } else if (i == 4) {
            int i5 = (measuredWidth * 3) / 4;
            if (layoutParams != null) {
                resize(layoutParams, 1.3333334f, measuredWidth, measuredHeight);
            }
//            Intrinsics.checkNotNullExpressionValue(layoutParams2, "viewLayoutParams");
            resize(layoutParams2, 1.3333334f, measuredWidth, measuredHeight);
        } else if (i == 5) {
            if (layoutParams != null) {
                resize(layoutParams, 1.0f, measuredWidth, measuredHeight);
            }
//            Intrinsics.checkNotNullExpressionValue(layoutParams2, "viewLayoutParams");
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
//            videoMaker.changeVideoRatio(video_ratio);
        }
    }

    private void resize(ViewGroup.LayoutParams layoutParams, float f, int i, int i2) {
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
//        if (!Intrinsics.areEqual((Object) this.oldThemeModel, (Object) this.currentThemeModel)) {
//            chooseThemeNew(this.oldThemeModel);
//        }
    }

    public final void chooseThemeNew(GifTheme gifTheme) {
//        this.currentThemeModel = gifTheme;
//        VideoMaker videoMaker = this.mVideoMaker;
//        if (videoMaker != null) {
//            videoMaker.chooseThemeNew(gifTheme);
//        }
//        this.mCurrentFrame = 0;
//        resumePreview();
    }

    public final void startPreview2() {
        /*
//            r3 = this;

            android.widget.ImageView r0 = r3.mImgControl
            if (r0 == 0) goto L_0x0009
            android.view.View r0 = (android.view.View) r0
            com.photoeditor.slideshow.common.ExtentionsKt.gone(r0)
        L_0x0009:
            android.widget.ImageView r0 = r3.img_play
            r1 = 2131231183(0x7f0801cf, float:1.807844E38)
            org.jetbrains.anko.Sdk27PropertiesKt.setImageResource(r0, r1)
            android.media.MediaPlayer r0 = r3.mAudioPreview     // Catch:{ IllegalStateException -> 0x0018 }
            if (r0 == 0) goto L_0x0018
            r0.reset()     // Catch:{ IllegalStateException -> 0x0018 }
        L_0x0018:
            r3.checkTrimAndLoop()     // Catch:{ IOException -> 0x007f }
            r0 = 1
            r3.mIsImagePreviewing = r0     // Catch:{ IOException -> 0x007f }
            r1 = 0
            r3.mIsPreviewStopping = r1     // Catch:{ IOException -> 0x007f }
            r3.mCurrentFrame = r1     // Catch:{ IOException -> 0x007f }
            com.photoeditor.slideshow.imagetovideo.VideoMaker r1 = r3.mVideoMaker     // Catch:{ IOException -> 0x007f }
            if (r1 == 0) goto L_0x0030
            int r1 = r1.getTotalFrames()     // Catch:{ IOException -> 0x007f }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ IOException -> 0x007f }
            goto L_0x0031
        L_0x0030:
            r1 = 0
        L_0x0031:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ IOException -> 0x007f }
            int r1 = r1.intValue()     // Catch:{ IOException -> 0x007f }
            r3.mTotalFrames = r1     // Catch:{ IOException -> 0x007f }
            r3.playSlideShow()     // Catch:{ IOException -> 0x007f }
            android.media.MediaPlayer r1 = r3.mAudioPreview     // Catch:{ IOException -> 0x007f }
            if (r1 != 0) goto L_0x0048
            android.media.MediaPlayer r1 = new android.media.MediaPlayer     // Catch:{ IOException -> 0x007f }
            r1.<init>()     // Catch:{ IOException -> 0x007f }
            r3.mAudioPreview = r1     // Catch:{ IOException -> 0x007f }
        L_0x0048:
            android.media.MediaPlayer r1 = r3.mAudioPreview     // Catch:{ IOException -> 0x007f }
            if (r1 == 0) goto L_0x0083
            java.lang.String r2 = r3.mCurrentSongPath     // Catch:{ IOException -> 0x007f }
            if (r2 == 0) goto L_0x0083
            if (r1 == 0) goto L_0x0055
            r1.setDataSource(r2)     // Catch:{ IOException -> 0x007f }
        L_0x0055:
            android.media.MediaPlayer r1 = r3.mAudioPreview     // Catch:{ IOException -> 0x007f }
            if (r1 == 0) goto L_0x005c
            r1.prepare()     // Catch:{ IOException -> 0x007f }
        L_0x005c:
            android.media.MediaPlayer r1 = r3.mAudioPreview     // Catch:{ IOException -> 0x007f }
            if (r1 == 0) goto L_0x0063
            r1.setLooping(r0)     // Catch:{ IOException -> 0x007f }
        L_0x0063:
            android.media.MediaPlayer r0 = r3.mAudioPreview     // Catch:{ IOException -> 0x007f }
            if (r0 == 0) goto L_0x006e
            int r1 = r3.startTime     // Catch:{ IOException -> 0x007f }
            int r1 = r1 * 1000
            r0.seekTo(r1)     // Catch:{ IOException -> 0x007f }
        L_0x006e:
            android.media.MediaPlayer r0 = r3.mAudioPreview     // Catch:{ IOException -> 0x007f }
            if (r0 == 0) goto L_0x0077
            float r1 = r3.mVolume     // Catch:{ IOException -> 0x007f }
            r0.setVolume(r1, r1)     // Catch:{ IOException -> 0x007f }
        L_0x0077:
            android.media.MediaPlayer r0 = r3.mAudioPreview     // Catch:{ IOException -> 0x007f }
            if (r0 == 0) goto L_0x0083
            r0.start()     // Catch:{ IOException -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0083:
            return
        */
//        throw new UnsupportedOperationException("Method not decompiled: com.photoeditor.slideshow.components.MyVideoPlayerGif.startPreview():void");
    }

}
