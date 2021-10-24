//package com.photoeditor.slideshow.imagetovideo;
//
//import android.content.Context;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import androidx.work.WorkRequest;
//import com.photoeditor.slideshow.java.C2671Lo;
//import com.photoeditor.slideshow.java.PhotorTool;
//import java.io.File;
//import java.util.Locale;
//
//class MakeVideoUtils {
//    private static final String TAG = "khanh";
//    private Thread concatImage;
//    private Thread editAudioThread;
//    private boolean hasAudio;
//    private File inputAudio;
//    private boolean isMusicLonger;
//    private boolean isRunning;
//    private OnFinishEncoderListener listener;
//    private ConcatImageUtils mConcatImageUtils;
//    private Context mContext;
//    private boolean mHasChangeVolume;
//    private boolean mHasConvertAudio;
//    private boolean mHasToLoopAudio;
//    private boolean mHasToTrimAudio;
//    private VideoMaker mParent;
//    private Thread mergeVideo;
//    private File outputImage;
//    private File outputLoopAudio;
//    private File outputTrimAudio;
//    private File outputVideo;
//    private String outputVideoPath;
//
//    MakeVideoUtils(Context context, File file, OnFinishEncoderListener onFinishEncoderListener) {
//        this.isMusicLonger = false;
//        this.isRunning = true;
//        this.hasAudio = true;
//        this.concatImage = null;
//        this.mergeVideo = null;
//        this.isRunning = true;
//        this.mContext = context;
//        this.inputAudio = file;
//        this.mParent = VideoMaker.getInstance();
//        this.mConcatImageUtils = new ConcatImageUtils();
//        this.listener = onFinishEncoderListener;
//    }
//
//    /* access modifiers changed from: package-private */
//    public void makeVideo(Handler handler, boolean z, boolean z2, boolean z3, boolean z4) {
//        this.mHasToTrimAudio = z;
//        this.mHasToLoopAudio = z2;
//        this.isMusicLonger = z4;
//        this.mHasConvertAudio = SharePreferencesUtils.getInstance(this.mContext).getBoolean(GlobalDef.KEY_HAS_CONVERT_AUDIO, false);
//        prepare();
//        execute(handler, z3);
//        finish();
//    }
//
//    public void setInputAudio(File file) {
//        this.inputAudio = file;
//    }
//
//    public void editAudio(boolean z, boolean z2) {
//        this.mHasToLoopAudio = z2;
//        this.mHasToTrimAudio = z;
//        this.mHasConvertAudio = SharePreferencesUtils.getInstance(this.mContext).getBoolean(GlobalDef.KEY_HAS_CONVERT_AUDIO, false);
//        prepareAudio();
//        Thread thread = new Thread(editAudio());
//        this.editAudioThread = thread;
//        thread.start();
//    }
//
//    public void interruptEditAudio() throws SecurityException {
//        Thread thread = this.editAudioThread;
//        if (thread != null) {
//            thread.interrupt();
//        }
//    }
//
//    private void prepare() {
//        prepareAudio();
//        prepareImage();
//    }
//
//    private void prepareImage() {
//        this.mConcatImageUtils.prepareEncoder(this.outputImage);
//        this.mParent.clearBuffer(true, false, false);
//    }
//
//    private void prepareAudio() {
//        File file = new File(GlobalDef.DEFAULT_FOLDER_OUTPUT);
//        if (file.exists() || file.mkdirs()) {
//            if (this.inputAudio == null) {
//                this.hasAudio = false;
//            }
//            PhotorTool.createFolder(GlobalDef.TEMP_FOLDER);
//            this.outputImage = new File(GlobalDef.TEMP_FOLDER + File.separator + ".tempImage.mp4");
//            boolean z = SharePreferencesUtils.getInstance(this.mContext).getBoolean(GlobalDef.KEY_HAS_CONVERT_AUDIO, false);
//            this.mHasConvertAudio = z;
//            if (z) {
//                this.outputTrimAudio = new File(GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio.aac");
//            } else {
//                this.outputTrimAudio = new File(GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio.mp3");
//            }
//            this.outputVideoPath = GlobalDef.DEFAULT_FOLDER_OUTPUT + File.separator + "Video_" + (System.currentTimeMillis() / WorkRequest.MIN_BACKOFF_MILLIS) + ".mp4";
//            this.outputVideo = new File(this.outputVideoPath);
//        }
//    }
//
//    private void execute(Handler handler, boolean z) {
//        C2671Lo.m320d(TAG, "execute() create video...");
//        try {
//            Thread thread = new Thread(concatImage(handler));
//            this.concatImage = thread;
//            thread.start();
//            if (z) {
//                if (this.editAudioThread == null) {
//                    Thread thread2 = new Thread(editAudio());
//                    this.editAudioThread = thread2;
//                    thread2.start();
//                }
//                this.editAudioThread.join();
//            }
//            this.concatImage.join();
//            Thread thread3 = new Thread(mergeVideo(z));
//            this.mergeVideo = thread3;
//            thread3.start();
//            this.mergeVideo.join();
//        } catch (Exception e) {
//            C2671Lo.m323e(TAG, e.toString());
//        }
//    }
//
//    private void finish() {
//        C2671Lo.m320d(TAG, "Finish Video Encode: " + this.outputVideoPath);
//        this.mParent.clearBuffer(true, true, true);
//        this.mParent.setProcessing(false);
//        OnFinishEncoderListener onFinishEncoderListener = this.listener;
//        if (onFinishEncoderListener != null && this.isRunning) {
//            onFinishEncoderListener.onFinishEncoder(this.outputVideoPath);
//        }
//    }
//
//    private Runnable concatImage(Handler handler) {
//        return new Runnable(handler) {
//            public final /* synthetic */ Handler f$1;
//
//            {
//                this.f$1 = r2;
//            }
//
//            public final void run() {
//                MakeVideoUtils.this.lambda$concatImage$0$MakeVideoUtils(this.f$1);
//            }
//        };
//    }
//
//    public /* synthetic */ void lambda$concatImage$0$MakeVideoUtils(Handler handler) {
//        int totalFrames = this.mParent.getTotalFrames();
//        int i = 0;
//        while (i < totalFrames && this.isRunning) {
//            try {
//                this.mConcatImageUtils.concatImage(i);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            i++;
//            if (this.isRunning) {
//                Message message = new Message();
//                message.arg1 = (i * 100) / totalFrames;
//                handler.sendMessage(message);
//            }
//        }
//        if (this.isRunning) {
//            Log.d(TAG, "finishConcatImage: ");
//            try {
//                this.mConcatImageUtils.finishConcatImage();
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//    }
//
//    private Runnable editAudio() {
//        return new Runnable() {
//            public final void run() {
//                MakeVideoUtils.this.lambda$editAudio$1$MakeVideoUtils();
//            }
//        };
//    }
//
//    public /* synthetic */ void lambda$editAudio$1$MakeVideoUtils() {
//        trimAudio(VideoMaker.VOLUME, this.mParent.getParamsTrimAudio()[0], this.mParent.getParamsTrimAudio()[1], this.inputAudio, this.outputTrimAudio);
//        this.outputLoopAudio = loopAudio(this.mParent.getParamsTrimAudio()[0], this.mParent.getParamsTrimAudio()[1], this.mParent.getTotalDuration(), this.outputTrimAudio);
//    }
//
//    private Runnable mergeVideo(boolean z) {
//        return new Runnable(z) {
//            public final /* synthetic */ boolean f$1;
//
//            {
//                this.f$1 = r2;
//            }
//
//            public final void run() {
//                MakeVideoUtils.this.lambda$mergeVideo$2$MakeVideoUtils(this.f$1);
//            }
//        };
//    }
//
//    public /* synthetic */ void lambda$mergeVideo$2$MakeVideoUtils(boolean z) {
//        mergeAudioToVideo(this.outputImage, this.outputLoopAudio, this.outputVideo, z);
//    }
//
//    private void trimAudio(float f, int i, int i2, File file, File file2) {
//        int i3;
//        final boolean[] zArr = {true};
//        final boolean[] zArr2 = new boolean[1];
//        if (this.mHasToTrimAudio) {
//            i3 = i2 - i;
//        } else {
//            i3 = (int) this.mParent.getTotalDuration();
//        }
//        if (this.mHasToTrimAudio || f < 0.9f || this.mHasConvertAudio) {
//            FfmpegUtils.executeCommand(this.mContext, String.format(Locale.US, "-y -ss %d -i \"%s\" -t %d %s -c copy \"%s\"", new Object[]{Integer.valueOf(i), file.getAbsolutePath(), Integer.valueOf(i3), "", file2.getAbsolutePath()}), new FFmpegInterface() {
//                public void onSuccess() {
//                    zArr2[0] = true;
//                }
//
//                public void onFailed() {
//                    zArr2[0] = false;
//                }
//
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
//    }
//
//    private File loopAudio(int i, int i2, float f, File file) {
//        String str;
//        if (!this.mHasToLoopAudio) {
//            return file;
//        }
//        C2671Lo.m320d(TAG, "loopAudio()");
//        final boolean[] zArr = {true};
//        int i3 = ((int) (f / ((float) (i2 - i)))) + 1;
//        if (this.mHasConvertAudio) {
//            str = GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio2.aac";
//        } else {
//            str = GlobalDef.TEMP_FOLDER + File.separator + ".tempAudio2.mp3";
//        }
//        final String createListToLoop = FfmpegUtils.createListToLoop(i3, file.getAbsolutePath());
//        FfmpegUtils.executeCommand(this.mContext, String.format(Locale.US, "-y -f concat -safe 0 -i %s -c copy %s", new Object[]{createListToLoop, str}), new FFmpegInterface() {
//            public void onFailed() {
//            }
//
//            public void onSuccess() {
//                MakeVideoUtils.this.deleteFile(new File(createListToLoop));
//            }
//
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
//
//    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c7 A[RETURN] */
//    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c8  */
//    /* Code decompiled incorrectly, please refer to instructions dump. */
//    private boolean mergeAudioToVideo(File r7, File r8, File r9, boolean r10) {
//        /*
//            r6 = this;
//            r9 = 1
//            boolean[] r0 = new boolean[r9]
//            boolean[] r1 = new boolean[r9]
//            r2 = 0
//            r1[r2] = r9
//            java.lang.String r3 = ""
//            r4 = 2
//            if (r10 == 0) goto L_0x0092
//            boolean r10 = r6.mHasConvertAudio
//            r5 = 3
//            if (r10 == 0) goto L_0x0053
//            if (r7 == 0) goto L_0x00c0
//            if (r8 == 0) goto L_0x00c0
//            boolean r10 = r6.mHasToLoopAudio
//            if (r10 != 0) goto L_0x0039
//            boolean r10 = r6.isMusicLonger
//            if (r10 == 0) goto L_0x001f
//            goto L_0x0039
//        L_0x001f:
//            java.lang.Object[] r10 = new java.lang.Object[r5]
//            java.lang.String r7 = r7.getAbsolutePath()
//            r10[r2] = r7
//            java.lang.String r7 = r8.getAbsolutePath()
//            r10[r9] = r7
//            java.lang.String r7 = r6.outputVideoPath
//            r10[r4] = r7
//            java.lang.String r7 = "-y -i \"%s\" -i \"%s\" -c:v copy -c:a copy -bsf:a aac_adtstoasc  -strict experimental \"%s\""
//            java.lang.String r7 = java.lang.String.format(r7, r10)
//            goto L_0x00c1
//        L_0x0039:
//            java.lang.Object[] r10 = new java.lang.Object[r5]
//            java.lang.String r7 = r7.getAbsolutePath()
//            r10[r2] = r7
//            java.lang.String r7 = r8.getAbsolutePath()
//            r10[r9] = r7
//            java.lang.String r7 = r6.outputVideoPath
//            r10[r4] = r7
//            java.lang.String r7 = "-y -i \"%s\" -i \"%s\" -c:v copy -c:a copy -bsf:a aac_adtstoasc -shortest  -strict experimental \"%s\""
//            java.lang.String r7 = java.lang.String.format(r7, r10)
//            goto L_0x00c1
//        L_0x0053:
//            if (r7 == 0) goto L_0x00c0
//            if (r8 == 0) goto L_0x00c0
//            boolean r10 = r6.mHasToLoopAudio
//            if (r10 != 0) goto L_0x0079
//            boolean r10 = r6.isMusicLonger
//            if (r10 == 0) goto L_0x0060
//            goto L_0x0079
//        L_0x0060:
//            java.lang.Object[] r10 = new java.lang.Object[r5]
//            java.lang.String r7 = r7.getAbsolutePath()
//            r10[r2] = r7
//            java.lang.String r7 = r8.getAbsolutePath()
//            r10[r9] = r7
//            java.lang.String r7 = r6.outputVideoPath
//            r10[r4] = r7
//            java.lang.String r7 = "-y -i \"%s\" -i \"%s\" -c:v copy  -strict experimental \"%s\""
//            java.lang.String r7 = java.lang.String.format(r7, r10)
//            goto L_0x00c1
//        L_0x0079:
//            java.lang.Object[] r10 = new java.lang.Object[r5]
//            java.lang.String r7 = r7.getAbsolutePath()
//            r10[r2] = r7
//            java.lang.String r7 = r8.getAbsolutePath()
//            r10[r9] = r7
//            java.lang.String r7 = r6.outputVideoPath
//            r10[r4] = r7
//            java.lang.String r7 = "-y -i \"%s\" -i \"%s\" -c:v copy -shortest  -strict experimental \"%s\""
//            java.lang.String r7 = java.lang.String.format(r7, r10)
//            goto L_0x00c1
//        L_0x0092:
//            boolean r8 = r6.mHasConvertAudio
//            if (r8 == 0) goto L_0x00ab
//            if (r7 == 0) goto L_0x00c0
//            java.lang.Object[] r8 = new java.lang.Object[r4]
//            java.lang.String r7 = r7.getAbsolutePath()
//            r8[r2] = r7
//            java.lang.String r7 = r6.outputVideoPath
//            r8[r9] = r7
//            java.lang.String r7 = "-y -i \"%s\" -c:v copy -c:a copy -bsf:a aac_adtstoasc -shortest -strict experimental \"%s\""
//            java.lang.String r7 = java.lang.String.format(r7, r8)
//            goto L_0x00c1
//        L_0x00ab:
//            if (r7 == 0) goto L_0x00c0
//            java.lang.Object[] r8 = new java.lang.Object[r4]
//            java.lang.String r7 = r7.getAbsolutePath()
//            r8[r2] = r7
//            java.lang.String r7 = r6.outputVideoPath
//            r8[r9] = r7
//            java.lang.String r7 = "-y -i \"%s\" -c:v copy -c:a copy -shortest -strict experimental \"%s\""
//            java.lang.String r7 = java.lang.String.format(r7, r8)
//            goto L_0x00c1
//        L_0x00c0:
//            r7 = r3
//        L_0x00c1:
//            boolean r8 = r7.equals(r3)
//            if (r8 == 0) goto L_0x00c8
//            return r2
//        L_0x00c8:
//            java.lang.StringBuilder r8 = new java.lang.StringBuilder
//            r8.<init>()
//            java.lang.String r9 = "mergeAudioToVideo: "
//            r8.append(r9)
//            r8.append(r7)
//            java.lang.String r8 = r8.toString()
//            java.lang.String r9 = "khanh"
//            com.photoeditor.slideshow.java.C2671Lo.m320d(r9, r8)
//            android.content.Context r8 = r6.mContext
//            com.photoeditor.slideshow.imagetovideo.MakeVideoUtils$3 r9 = new com.photoeditor.slideshow.imagetovideo.MakeVideoUtils$3
//            r9.<init>(r0, r1)
//            com.photoeditor.slideshow.imagetovideo.FfmpegUtils.executeCommand(r8, r7, r9)
//        L_0x00e8:
//            boolean r7 = r1[r2]
//            if (r7 == 0) goto L_0x00fb
//            boolean r7 = r6.isRunning
//            if (r7 == 0) goto L_0x00fb
//            r7 = 100
//            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x00f6 }
//            goto L_0x00e8
//        L_0x00f6:
//            r7 = move-exception
//            r7.printStackTrace()
//            goto L_0x00e8
//        L_0x00fb:
//            boolean r7 = r0[r2]
//            return r7
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.photoeditor.slideshow.imagetovideo.MakeVideoUtils.mergeAudioToVideo(java.io.File, java.io.File, java.io.File, boolean):boolean");
//    }
//
//    public void stopVideoProcess() {
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
//    }
//
//    /* access modifiers changed from: private */
//    public void deleteFile(File file) {
//        if (file != null && file.exists()) {
//            file.delete();
//        }
//    }
//}
