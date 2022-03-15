package com.photoeditor.slideshow.viewmodel;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.common.AppConst;
import com.photoeditor.slideshow.imagetovideo.OnFinishEncoderListener;
import com.photoeditor.slideshow.imagetovideo.TimeUtils;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;
import com.photoeditor.slideshow.interfaces.ServiceListener;
import com.photoeditor.slideshow.java.PhotorThread;
import com.photoeditor.slideshow.java.PhotorTool;
import com.photoeditor.slideshow.utils.Constants;
import com.photoeditor.slideshow.utils.LocalService;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenderActivity extends AppCompatActivity implements OnFinishEncoderListener, ServiceListener {

    @BindView(R.id.txt_quality)
    TextView txtQuality;
    @BindView(R.id.txt_estime_time)
    TextView txtEstimateTime;
    @BindView(R.id.txt_percent)
    TextView txtPercent;
    public boolean isAudioVideo;
    public boolean isCancel;
    private boolean isFirst;
    private boolean isMusic;
    public boolean isMusicLonger;
    public boolean isRenderSuccess;
    private boolean isStop;
    public boolean mHasLoopAudio;
    public boolean mHasTrimAudio;
    private Handler mMakeVideoHandler;
    public LocalService mService;
    public VideoMaker mVideoMaker;
    private View mView;
    public long startTime;
    private long startTimeTrack;
    private String videoPath;
    private boolean isForeground = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_render);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        isAudioVideo = intent.getBooleanExtra(Constants.EXTRA_IS_AUDIO_VIDEO, false);
        mHasTrimAudio = intent.getBooleanExtra(Constants.EXTRA_IS_HAS_TRIM_AUDIO, false);
        mHasLoopAudio = intent.getBooleanExtra(Constants.EXTRA_IS_LOOP_AUDIO, false);
        isMusicLonger = intent.getBooleanExtra(Constants.EXTRA_IS_MUSIC_LONGER, false);

        mVideoMaker = VideoMaker.getInstance();
        PhotorThread.getInstance().runUIDelay(() -> startMyService(), 1000);
        initView();
    }

    @OnClick({R.id.btn_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                mService.cancelRender();
                break;
        }
    }

    private final void initView() {
//        TextView textView = (TextView) _$_findCachedViewById(R.id.txt_quality);
//        StringBuilder sb = new StringBuilder();
//        sb.append((Integer) Hawk.get(AppConst.KEY_QUALITY, 1080));
//        sb.append('p');
//        textView.setText(sb.toString());
//        TextView textView2 = (TextView) _$_findCachedViewById(R.id.txtA);
//        textView2.setText("Video_" + TimeUtils.parseTimeStampToString(System.currentTimeMillis()) + ".mp4");
//        if (AppConst.INSTANCE.isPREMIUM()) {
////            ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) _$_findCachedViewById(R.id.adContainer);
////            if (shimmerFrameLayout != null) {
////                ExtentionsKt.gone(shimmerFrameLayout);
////            }
////            TextView textView3 = (TextView) _$_findCachedViewById(R.id.txt_loading_render);
////            if (textView3 != null) {
////                ExtentionsKt.gone(textView3);
////            }
//        } else if (isHaveInternet()) {
//            FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(R.id.layout_ad);
//            if (frameLayout != null) {
//                ExtentionsKt.show(frameLayout);
//            }
//            showAds();
//        }

        StringBuilder sb = new StringBuilder();
        sb.append((Integer) Hawk.get(AppConst.KEY_QUALITY, 1080));
        sb.append('p');
//        txtQuality.setText(sb.toString());
//        TextView textView2 = (TextView) _$_findCachedViewById(R.id.txtA);
//        textView2.setText("Video_" + TimeUtils.parseTimeStampToString(System.currentTimeMillis()) + ".mp4");
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((LocalService.LocalBinder) service).getService();
            mService.setServiceListener(RenderActivity.this);
            Log.e("ChinhNH", "onServiceConnected:  " + isAudioVideo);
            Log.e("ChinhNH", "onServiceConnected:  " + mHasTrimAudio);
            Log.e("ChinhNH", "onServiceConnected:  " + mHasLoopAudio);
            Log.e("ChinhNH", "onServiceConnected:  " + isMusicLonger);
            mService.renderVideo(mVideoMaker, isAudioVideo, mHasTrimAudio, mHasLoopAudio, isMusicLonger);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public final void startMyService() {
        Intent intent = new Intent(this, LocalService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.isForeground = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.isForeground = true;
    }

    @Override
    public void onFinishEncoder(String str) {
        if (mMakeVideoHandler != null) {
            mMakeVideoHandler.removeCallbacksAndMessages(null);
        }
        this.mMakeVideoHandler = null;
        if (str == null) {
            renderFailed();
            return;
        }
        PhotorTool.scanFile(this, str);
//        ((MainActivity) activity).isForeground();
//        isSafe();
    }

    @Override
    public void renderFailed() {
        unbindService(serviceConnection);
        runOnUiThread(() -> Toast.makeText(RenderActivity.this, getString(R.string.error_render), Toast.LENGTH_LONG).show());
    }

    @Override
    public void renderProgress(int i) {
        String str;
        if (!this.isFirst) {
            this.isFirst = true;
            long currentTimeMillis = (System.currentTimeMillis() - this.startTime) / ((long) 10);
            long j = (long) 60;
            long j2 = currentTimeMillis / j;
            long j3 = currentTimeMillis % j;
            if (j2 >= 1) {
                str = j2 + ' ' + getString(R.string.minutes) + ' ' + j3 + ' ' + getString(R.string.second);
            } else {
                str = j3 + ' ' + getString(R.string.second);
            }
//            txtEstimateTime.setText(str);
        }
        if (i >= 0 && 100 >= i) {

            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append('%');
            txtPercent.setText(sb.toString());
//            ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.progressPercentRender);
//            if (progressBar != null) {
//                progressBar.setProgress(i);
//            }
        }
    }

    @Override
    public void renderSuccess(String str) {
        unbindService(serviceConnection);
        this.isRenderSuccess = true;
        if (str == null) {
            runOnUiThread(() -> Toast.makeText(RenderActivity.this, getString(R.string.error_render), Toast.LENGTH_LONG).show());
            return;
        }
        PhotorTool.scanFile(this, str);
        this.videoPath = str;
        try {
//                if (isSafe()) {
            if (isForeground) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isRenderSuccess = false;
//                        MyDialog.getInstance().hideDialogConfirm();
//                        Ad/ Dialog.getInstance().hideLoading();
                        nextAction(str);
                    }
                });
            }
//                }
        } catch (Exception unused2) {
        }
    }

    public final void nextAction(String str) {
//        start(new SaveFragment(this.isMusic, str, false, false, 8, (DefaultConstructorMarker) null));
    }

}
