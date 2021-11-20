package com.photoeditor.slideshow.imagetovideo;

import android.graphics.Canvas;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.common.AppConst;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* access modifiers changed from: package-private */
public class ConcatImageUtils {
    private static final int BIT_RATE = 5000000;
    private static final float BPP = 0.25f;
    private static final int IFRAME_INTERVAL = 5;
    private static final String MIME_TYPE = "video/avc";
    private static final String TAG = "khanh";
    private boolean isRunning;
    private MediaCodec.BufferInfo mBufferInfo;
    private MediaCodec mEncoder;
    private long mFakePts;
    private Surface mInputSurface;
    private MediaMuxer mMuxer;
    private boolean mMuxerStarted;
    private VideoMaker videoMaker;
    private int mTrackIndex;

    private int calcBitRate(float f, int i, int i2) {
        return (int) (f * 30.0f * ((float) i) * ((float) i2));
    }

    ConcatImageUtils() {
        this.isRunning = true;
        this.isRunning = true;
        this.videoMaker = VideoMaker.getInstance();
    }

    /* access modifiers changed from: package-private */
    public void prepareEncoder(File file) {
        this.mBufferInfo = new MediaCodec.BufferInfo();
        int intValue = Hawk.get(AppConst.KEY_QUALITY, 1080);
        VideoMaker videoMaker = this.videoMaker;
        int tileFromData = videoMaker != null ? (int) (((float) intValue) / videoMaker.getTileFromData()) : 0;
        if (intValue % 2 == 1) {
            intValue--;
        }
        if (tileFromData % 2 == 1) {
            tileFromData--;
        }
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", intValue, tileFromData);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", calcBitRate(0.5f, this.videoMaker.getVideoWidth(), this.videoMaker.getVideoHeight()));
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("i-frame-interval", 5);
        try {
            this.mEncoder = MediaCodec.createEncoderByType("video/avc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.mEncoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            this.mInputSurface = this.mEncoder.createInputSurface();
            this.mEncoder.start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (file != null) {
            try {
                this.mMuxer = new MediaMuxer(file.toString(), MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        this.mTrackIndex = -1;
        this.mMuxerStarted = false;
    }

    /* access modifiers changed from: package-private */
    public void concatImage(int currentFrame) {
        if (this.isRunning) {
            try {
                drainEncoder(false);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            if (mInputSurface != null) {
                Canvas lockCanvas = mInputSurface.lockCanvas(null);
                videoMaker.generateFrame(lockCanvas, currentFrame, videoMaker.getVideoWidth(), this.videoMaker.getVideoHeight());
                this.mInputSurface.unlockCanvasAndPost(lockCanvas);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void finishConcatImage() {
        try {
            drainEncoder(true);
            try {
                releaseEncoder();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    private void drainEncoder(boolean z) throws IllegalStateException {
        if (z) {
            this.mEncoder.signalEndOfInputStream();
        }
        ByteBuffer[] outputBuffers = this.mEncoder.getOutputBuffers();
        while (this.isRunning) {
            int dequeueOutputBuffer = this.mEncoder.dequeueOutputBuffer(this.mBufferInfo, 2500);
            if (dequeueOutputBuffer == -1) {
                if (!z) {
                    return;
                }
            } else if (dequeueOutputBuffer == -3) {
                outputBuffers = this.mEncoder.getOutputBuffers();
            } else if (dequeueOutputBuffer == -2) {
                if (!this.mMuxerStarted) {
                    MediaFormat outputFormat = this.mEncoder.getOutputFormat();
                    if (mMuxer != null) {
                        this.mTrackIndex = mMuxer.addTrack(outputFormat);
                        this.mMuxer.start();
                    }
                    this.mMuxerStarted = true;
                } else {
                    throw new RuntimeException("format changed twice");
                }
            } else if (dequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                if (byteBuffer != null) {
                    if ((mBufferInfo.flags & 2) != 0) {
                        mBufferInfo.size = 0;
                    }
                    if (mBufferInfo.size != 0) {
                        if (this.mMuxerStarted) {
                            byteBuffer.position(this.mBufferInfo.offset);
                            byteBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                            mBufferInfo.presentationTimeUs = this.mFakePts;
                            mFakePts += 33333;
                            if (mMuxer != null) {
                                mMuxer.writeSampleData(this.mTrackIndex, byteBuffer, this.mBufferInfo);
                            }
                        } else {
                            throw new RuntimeException("muxer hasn't started");
                        }
                    }
                    this.mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((this.mBufferInfo.flags & 4) != 0) {
                        return;
                    }
                } else {
                    throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                }
            }
            if (!this.isRunning) {
                try {
                    mEncoder.signalEndOfInputStream();
                    try {
                        releaseEncoder();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }

    private void releaseEncoder() {
        if (mEncoder != null) {
            mEncoder.stop();
            try {
                this.mEncoder.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mEncoder = null;
        }
        if (mInputSurface != null) {
            try {
                mInputSurface.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mInputSurface = null;
        }
        if (mMuxer != null) {
            try {
                mMuxer.stop();
                this.mMuxer.release();
            } catch (Exception unused) {
            }
            this.mMuxer = null;
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}