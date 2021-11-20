package com.photoeditor.slideshow.imagetovideo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.work.WorkRequest;

import com.photoeditor.slideshow.java.PhotorTool;

import java.io.File;

class MakeVideoUtils {
    private static final String TAG = "khanh";
    private Thread concatImage;
    private Thread editAudioThread;
    private boolean hasAudio;
    private File inputAudio;
    private boolean isMusicLonger;
    private boolean isRunning;
    private OnFinishEncoderListener listener;
    private ConcatImageUtils mConcatImageUtils;
    private Context mContext;
    private boolean mHasChangeVolume;
    private boolean mHasConvertAudio;
    private boolean mHasToLoopAudio;
    private boolean mHasToTrimAudio;
    private VideoMaker videoMaker;
    private Thread mergeVideo;
    private File outputImage;
    private File outputLoopAudio;
    private File outputTrimAudio;
    private File outputVideo;
    private String outputVideoPath;

    MakeVideoUtils(Context context, File file, OnFinishEncoderListener onFinishEncoderListener) {
        this.isMusicLonger = false;
        this.isRunning = true;
        this.hasAudio = true;
        this.concatImage = null;
        this.mergeVideo = null;
        this.isRunning = true;
        this.mContext = context;
        this.inputAudio = file;
        this.videoMaker = VideoMaker.getInstance();
        this.mConcatImageUtils = new ConcatImageUtils();
        this.listener = onFinishEncoderListener;
    }

    /* access modifiers changed from: package-private */
    public void makeVideo(Handler handler, boolean mHasTrimAudio, boolean mHasLoopAudio, boolean isAudioVideo, boolean isMusicLonger) {
        this.mHasToTrimAudio = mHasTrimAudio;
        this.mHasToLoopAudio = mHasLoopAudio;
        this.isMusicLonger = isMusicLonger;
        this.mHasConvertAudio = SharePreferencesUtils.getInstance(this.mContext).getBoolean(GlobalDef.KEY_HAS_CONVERT_AUDIO, false);
        prepare();
        execute(handler, isAudioVideo);
        finish();
    }

    public void setInputAudio(File file) {
        this.inputAudio = file;
    }

    public void editAudio(boolean z, boolean z2) {
//        this.mHasToLoopAudio = z2;
//        this.mHasToTrimAudio = z;
//        this.mHasConvertAudio = SharePreferencesUtils.getInstance(this.mContext).getBoolean(GlobalDef.KEY_HAS_CONVERT_AUDIO, false);
//        prepareAudio();
//        Thread thread = new Thread(editAudio());
//        this.editAudioThread = thread;
//        thread.start();
    }

    public void interruptEditAudio() throws SecurityException {
        Thread thread = this.editAudioThread;
        if (thread != null) {
            thread.interrupt();
        }
    }

    private void prepare() {
        prepareAudio();
        prepareImage();
    }

    private void prepareImage() {
        this.mConcatImageUtils.prepareEncoder(this.outputImage);
        this.videoMaker.clearBuffer(true, false, false);
    }

    private void prepareAudio() {
        File file = new File(GlobalDef.DEFAULT_FOLDER_OUTPUT);
        if (file.exists() || file.mkdirs()) {
            if (this.inputAudio == null) {
                this.hasAudio = false;
            }
            PhotorTool.createFolder(GlobalDef.TEMP_FOLDER);
            this.outputImage = new File(GlobalDef.TEMP_FOLDER + File.separator + ".tempImage.mp4");
            boolean z = SharePreferencesUtils.getInstance(this.mContext).getBoolean(GlobalDef.KEY_HAS_CONVERT_AUDIO, false);
            this.mHasConvertAudio = z;
            if (z) {
                this.outputTrimAudio = new File(GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio.aac");
            } else {
                this.outputTrimAudio = new File(GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio.mp3");
            }
            this.outputVideoPath = GlobalDef.DEFAULT_FOLDER_OUTPUT + File.separator + "Video_" + (System.currentTimeMillis() / WorkRequest.MIN_BACKOFF_MILLIS) + ".mp4";
            this.outputVideo = new File(this.outputVideoPath);
        }
    }

    private void execute(Handler handler, boolean isAudioVideo) {
        try {
            concatImage = new Thread(concatImage(handler));
            concatImage.start();
            if (isAudioVideo) {
//                if (this.editAudioThread == null) {
//                    Thread thread2 = new Thread(editAudio());
//                    this.editAudioThread = thread2;
//                    thread2.start();
//                }
//                this.editAudioThread.join();
            }
            this.concatImage.join();
//            mergeVideo = new Thread(mergeVideo(isAudioVideo));
//            mergeVideo.start();
//            mergeVideo.join();
        } catch (Exception e) {
            Log.e("ChinhNH", "execute: failed");
        }
    }

    private void finish() {
        this.videoMaker.clearBuffer(true, true, true);
        this.videoMaker.setProcessing(false);
        OnFinishEncoderListener onFinishEncoderListener = this.listener;
        if (onFinishEncoderListener != null && this.isRunning) {
            onFinishEncoderListener.onFinishEncoder(this.outputVideoPath);
        }
    }

    private Runnable concatImage(Handler handler) {
        return () -> {
            int totalFrames = videoMaker.getTotalFrames();
            int currentFrame = 0;
            while (currentFrame < totalFrames && isRunning) {
                try {
                    mConcatImageUtils.concatImage(currentFrame);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                currentFrame++;
                if (isRunning) {
                    Message message = new Message();
                    message.arg1 = (currentFrame * 100) / totalFrames;
                    handler.sendMessage(message);
                }
            }
            if (isRunning) {
                try {
                    mConcatImageUtils.finishConcatImage();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
    }


//    private Runnable editAudio() {
//        return new Runnable() {
//            public final void run() {
//                trimAudio(VideoMaker.VOLUME, mParent.getParamsTrimAudio()[0], mParent.getParamsTrimAudio()[1], inputAudio, outputTrimAudio);
//                outputLoopAudio = loopAudio(mParent.getParamsTrimAudio()[0], mParent.getParamsTrimAudio()[1], mParent.getTotalDuration(), outputTrimAudio);
//            }
//        };
//    }

//    private Runnable mergeVideo(boolean z) {
//        return () -> mergeAudioToVideo(outputImage, outputLoopAudio, outputVideo, z);
//    }


    private void trimAudio(float f, int i, int i2, File file, File file2) {
//        int i3;
//        final boolean[] zArr = {true};
//        final boolean[] zArr2 = new boolean[1];
//        if (this.mHasToTrimAudio) {
//            i3 = i2 - i;
//        } else {
//            i3 = (int) this.mParent.getTotalDuration();
//        }
//        if (this.mHasToTrimAudio || f < 0.9f || this.mHasConvertAudio) {
//            FfmpegUtils.executeCommand(this.mContext, String.format(Locale.US, "-y -ss %d -i \"%s\" -t %d %s -c copy \"%s\"", Integer.valueOf(i), file.getAbsolutePath(), Integer.valueOf(i3), "", file2.getAbsolutePath()), new FFmpegInterface() {
//                /* class com.photoeditor.slideshow.imagetovideo.MakeVideoUtils.C50011 */
//
//                @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//                public void onSuccess() {
//                    zArr2[0] = true;
//                }
//
//                @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//                public void onFailed() {
//                    zArr2[0] = false;
//                }
//
//                @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//                public void onFinish() {
//                    zArr[0] = false;
//                }
//            });
//            while (zArr[0] && this.isRunning) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            return;
//        }
//        this.outputTrimAudio = file;
//        this.mHasChangeVolume = false;
    }

//        private File loopAudio ( int i, int i2, float f, File file){
//        String str;
//        if (!this.mHasToLoopAudio) {
//            return file;
//        }
//        final boolean[] zArr = {true};
//        int i3 = ((int) (f / ((float) (i2 - i)))) + 1;
//        if (this.mHasConvertAudio) {
//            str = GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio2.aac";
//        } else {
//            str = GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio2.mp3";
//        }
//        final String createListToLoop = FfmpegUtils.createListToLoop(i3, file.getAbsolutePath());
//        FfmpegUtils.executeCommand(this.mContext, String.format(Locale.US, "-y -f concat -safe 0 -i %s -c copy %s", createListToLoop, str), new FFmpegInterface() {
//            /* class com.photoeditor.slideshow.imagetovideo.MakeVideoUtils.C50022 */
//
//            @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//            public void onFailed() {
//            }
//
//            @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//            public void onSuccess() {
//                MakeVideoUtils.this.deleteFile(new File(createListToLoop));
//            }
//
//            @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//            public void onFinish() {
//                zArr[0] = false;
//            }
//        });
//        while (zArr[0] && this.isRunning) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return new File(str);
//    }


//    private boolean mergeAudioToVideo(File file, File file2, File file3, boolean z) {
//        String str;
//        final boolean[] zArr = new boolean[1];
//        final boolean[] zArr2 = {true};
//        if (z) {
//            if (this.mHasConvertAudio) {
//                if (!(file == null || file2 == null)) {
//                    if (this.mHasToLoopAudio || this.isMusicLonger) {
//                        str = String.format("-y -i \"%s\" -i \"%s\" -c:v copy -c:a copy -bsf:a aac_adtstoasc -shortest  -strict experimental \"%s\"", file.getAbsolutePath(), file2.getAbsolutePath(), this.outputVideoPath);
//                    } else {
//                        str = String.format("-y -i \"%s\" -i \"%s\" -c:v copy -c:a copy -bsf:a aac_adtstoasc  -strict experimental \"%s\"", file.getAbsolutePath(), file2.getAbsolutePath(), this.outputVideoPath);
//                    }
//                    if (str.equals("")) {
//                        return false;
//                    }
//                    FfmpegUtils.executeCommand(this.mContext, str, new FFmpegInterface() {
//                        /* class com.photoeditor.slideshow.imagetovideo.MakeVideoUtils.C50033 */
//
//                        @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//                        public void onSuccess() {
//                            zArr[0] = true;
//                        }
//
//                        @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//                        public void onFailed() {
//                            zArr[0] = false;
//                        }
//
//                        @Override // com.photoeditor.slideshow.imagetovideo.FFmpegInterface
//                        public void onFinish() {
//                            zArr2[0] = false;
//                        }
//                    });
//                    while (zArr2[0] && this.isRunning) {
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return zArr[0];
//                }
//            } else if (!(file == null || file2 == null)) {
//                if (this.mHasToLoopAudio || this.isMusicLonger) {
//                    str = String.format("-y -i \"%s\" -i \"%s\" -c:v copy -shortest  -strict experimental \"%s\"", file.getAbsolutePath(), file2.getAbsolutePath(), this.outputVideoPath);
//                } else {
//                    str = String.format("-y -i \"%s\" -i \"%s\" -c:v copy  -strict experimental \"%s\"", file.getAbsolutePath(), file2.getAbsolutePath(), this.outputVideoPath);
//                }
//                if (str.equals("")) {
//                }
//            }
//        } else if (this.mHasConvertAudio) {
//            if (file != null) {
//                str = String.format("-y -i \"%s\" -c:v copy -c:a copy -bsf:a aac_adtstoasc -shortest -strict experimental \"%s\"", file.getAbsolutePath(), this.outputVideoPath);
//                if (str.equals("")) {
//                }
//            }
//        } else if (file != null) {
//            str = String.format("-y -i \"%s\" -c:v copy -c:a copy -shortest -strict experimental \"%s\"", file.getAbsolutePath(), this.outputVideoPath);
//            if (str.equals("")) {
//            }
//        }
//        str = "";
//        if (str.equals("")) {
//        }
//    }

    public void stopVideoProcess() {
//        this.isRunning = false;
//        FfmpegUtils.killProcess(this.mContext);
//        ConcatImageUtils concatImageUtils = this.mConcatImageUtils;
//        if (concatImageUtils != null) {
//            concatImageUtils.stop();
//        }
//        try {
//            interruptEditAudio();
//            Thread thread = this.concatImage;
//            if (thread != null) {
//                thread.interrupt();
//            }
//            Thread thread2 = this.mergeVideo;
//            if (thread2 != null) {
//                thread2.interrupt();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        deleteFile(this.outputVideo);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deleteFile(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }
}
