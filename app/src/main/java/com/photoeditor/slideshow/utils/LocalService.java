package com.photoeditor.slideshow.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.imagetovideo.OnFinishEncoderListener;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;
import com.photoeditor.slideshow.interfaces.ServiceListener;
import com.photoeditor.slideshow.viewmodel.RenderActivity;


public final class LocalService extends Service implements OnFinishEncoderListener {
    private final String CHANNEL_ID = "CHANNEL_ID_11122aa";
    private final LocalBinder binder = new LocalBinder();
    public Handler handler;
    public ServiceListener serviceListener;
    private Thread threadCreateVideo;
    private VideoMaker videoMaker;

    public final class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public final LocalService getService() {
            return LocalService.this;
        }
    }

    public final void setServiceListener(ServiceListener serviceListener2) {
        this.serviceListener = serviceListener2;
    }

    public void onDestroy() {
        super.onDestroy();
        this.serviceListener = null;
    }

    public final void cancelRender() {
        try {
            videoMaker.stopMakerVideo();
        } catch (Exception unused) {
        }
        if (threadCreateVideo != null) {
            if (threadCreateVideo.isAlive()) {
                threadCreateVideo.interrupt();
            }
        }
        stopSelf();
    }

    public final void renderVideo(VideoMaker videoMaker2, boolean isAudioVideo, boolean mHasTrimAudio,
                                  boolean mHasLoopAudio, boolean isMusicLonger) {
        if (videoMaker2 == null) {
            failedRenderVideo();
            return;
        }
        this.videoMaker = videoMaker2;
        videoMaker.setOnFinishEncoderListener(this);
        videoMaker.setContext(getApplicationContext());
        this.handler = new Handler(msg -> {
            if (serviceListener == null) {
                return false;
            }
            serviceListener.renderProgress(msg.arg1);
            return false;
        });
        if (isAudioVideo) {
            videoMaker.editAudio(mHasTrimAudio, mHasLoopAudio);
        }
        Thread thread = new Thread(() -> videoMaker.makeVideo(handler, mHasTrimAudio, mHasLoopAudio, isAudioVideo, isMusicLonger));
        this.threadCreateVideo = thread;
        thread.start();
    }

    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    private final void endService() {
        stopSelf();
    }

    public void onFinishEncoder(String str) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.handler = null;
        if (str == null) {
            failedRenderVideo();
            return;
        }
        createNotification();
        if (serviceListener != null) {
            serviceListener.renderSuccess(str);
        }
        endService();
    }

    private final void failedRenderVideo() {
        if (serviceListener != null) {
            serviceListener.renderFailed();
        }
        endService();
    }

    private final void createNotification() {
        Intent intent = new Intent(getApplicationContext(), RenderActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        createNotificationChannel();
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(getApplicationContext(),
                this.CHANNEL_ID).setSmallIcon((int) R.mipmap.ic_launcher_round)
                .setContentText(getApplicationContext().getString(R.string.export_success)).setContentIntent(activity)
                .setContentTitle(getApplicationContext().getString(R.string.app_name)).setDefaults(-1).setPriority(0).setAutoCancel(true);
        Notification build = autoCancel.build();
        NotificationManagerCompat from = NotificationManagerCompat.from(getApplicationContext());
        from.notify(1, build);
    }

    private final void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            String string = getString(R.string.app_name);
            String string2 = getString(R.string.export_success);
            NotificationChannel notificationChannel = new NotificationChannel(this.CHANNEL_ID, string, 3);
            notificationChannel.setDescription(string2);
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        stopSelf();
    }
}
