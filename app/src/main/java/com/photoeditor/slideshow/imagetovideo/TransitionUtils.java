package com.photoeditor.slideshow.imagetovideo;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import com.airbnb.lottie.LottieDrawable;
import java.util.List;
import java.util.Random;

class TransitionUtils extends TransitionUtilsEx {
    private static final float maxFrame = (VideoMaker.DURATION_TRANSITION * 30.0f);
    private int currentBmId = -1;
    private int currentPosList = 0;
    private int drawFrame = 0;
    private int frameStartDraw = -1;
    private int idBitmapToDraw = 0;
    private boolean isPreviewTransition = false;
    private boolean isReverse = false;
    private List<Bitmap> listBitmap;
    private LottieDrawable lottieDrawable;
    private Paint mTransparentPaint;
    private VideoMaker mVideoMaker;
    private int oldId = -1;
    Paint paint = new Paint(1);
    private int positionTran = 0;
    private Random random = new Random();
    private Rect rect = new Rect();
    private float tile;
    private final Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    TransitionUtils(VideoMaker videoMaker) {
        this.mVideoMaker = videoMaker;
        Paint paint2 = new Paint();
        this.mTransparentPaint = paint2;
        paint2.setColor(0);
        this.mTransparentPaint.setStrokeWidth(20.0f);
        this.mTransparentPaint.setStyle(Paint.Style.FILL);
        this.mTransparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    }

    public void setLottieDrawable(LottieDrawable lottieDrawable2) {
        this.lottieDrawable = lottieDrawable2;
    }

    /* access modifiers changed from: package-private */
    public void transition(Transition transition, Canvas canvas, Matrix matrix, Matrix matrix2, Bitmap bitmap, Paint paint2, int i, int i2, int i3, int i4, boolean z, int i5) {
        Canvas canvas2 = canvas;
        Matrix matrix3 = matrix2;
        Bitmap bitmap2 = bitmap;
        Paint paint3 = paint2;
        this.isPreviewTransition = z;
        this.positionTran = i5;
        switch (transition.ordinal()) {
            case 1:
                setNone(canvas, matrix3, bitmap2, paint3);
                break;
            case 2:
                fadeTransition(canvas, matrix2, paint2, bitmap, i, i2, 100, 255);
                break;
            case 3:
                testColumn(canvas, matrix, matrix2, paint2, bitmap, i, i2);
                break;
            case 4:
            case 5:
                transitionDraw(canvas, matrix, matrix2, paint2, bitmap, i, i2, i3, i4);
                break;
            case 6:
                testTryangle(canvas, matrix, matrix2, paint2, bitmap, i, i2, i3, i4);
                break;
            case 7:
                testColumnCenter(canvas, matrix, matrix2, paint2, bitmap, i, i2);
                break;
            case 8:
                drawImageLottieTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2, i3, i4);
                break;
            case 9:
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                slideTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2, i3, i4, transition);
                break;
            case 14:
                clockTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2, i3, i4);
                break;
            case 15:
                circleTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2, i3, i4);
                break;
            case 16:
                zoomTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2, i3, i4);
                break;
            case 17:
                flashTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2, true);
                break;
            case 18:
                flashTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2, false);
                break;
            case 19:
                blindTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2);
                break;
            case 20:
                blindVerticalTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2);
                break;
            case 21:
                chessBoardTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2);
                break;
            case 22:
                dissolveTransition(canvas, matrix, matrix2, paint2, bitmap, i, i2);
                break;
            default:
                canvas.drawBitmap(bitmap2, matrix3, paint3);
                break;
        }
        paint3.setAlpha(255);
    }

    public void setListBitmap(List<Bitmap> list) {
        this.listBitmap = list;
    }

    public List<Bitmap> getListBitmap() {
        return this.listBitmap;
    }

    private void setNone(Canvas canvas, Matrix matrix, Bitmap bitmap, Paint paint2) {
        canvas.drawBitmap(bitmap, matrix, paint2);
    }

    private void transitionDraw(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas2 = canvas;
        Paint paint3 = paint2;
        int i5 = i;
        canvas.save();
        if (!checkStartTime(i5, i2)) {
            Matrix matrix3 = matrix2;
            Bitmap bitmap2 = bitmap;
            canvas.drawBitmap(bitmap, matrix2, paint2);
        } else {
            Matrix matrix4 = matrix2;
            Bitmap bitmap3 = bitmap;
            Matrix matrix5 = matrix;
            canvas.drawBitmap(this.mVideoMaker.getLastImage(i5), matrix, paint2);
            if (i5 != 0) {
                drawTest(canvas, matrix2, bitmap, i5 - 1, i2, i3, i4);
            }
        }
        canvas.restore();
    }

    private void drawTest(Canvas canvas, Matrix matrix, Bitmap bitmap, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int size;
        int i7;
        float f;
        if (this.listBitmap != null) {
            int[] startAndStopFrameOfImageNew = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i));
            if (this.isPreviewTransition) {
                i5 = 60;
                i6 = (int) ((VideoMaker.DURATION_TRANSITION + 2.0f) * 30.0f);
            } else {
                int i8 = (int) (((float) startAndStopFrameOfImageNew[1]) - (VideoMaker.DURATION_TRANSITION * 30.0f));
                i6 = startAndStopFrameOfImageNew[1];
                i5 = i8;
            }
            int i9 = i2 - i5;
            if (i9 >= 0 && ((float) i9) <= VideoMaker.DURATION_TRANSITION * 30.0f) {
                int i10 = i6 - i5;
                if (this.listBitmap.size() != 0 && (size = i10 / this.listBitmap.size()) > 0) {
                    int i11 = i9 / size;
                    this.paint.setColor(-1);
                    if (i11 >= this.listBitmap.size()) {
                        i11 = this.listBitmap.size() - 1;
                    }
                    int saveLayer = canvas.saveLayer((RectF) null, (Paint) null, 31);
                    this.paint.setColor(-1);
                    Bitmap bitmap2 = this.listBitmap.get(i11);
                    if (bitmap2 != null && bitmap2.getWidth() > 0 && bitmap2.getHeight() > 0) {
                        if (i3 > i4) {
                            f = (float) i3;
                            i7 = bitmap2.getWidth();
                        } else {
                            f = (float) i4;
                            i7 = bitmap2.getHeight();
                        }
                        float f2 = f / ((float) i7);
                        int save = canvas.save();
                        float f3 = ((float) i3) / 2.0f;
                        float f4 = ((float) i4) / 2.0f;
                        canvas.scale(f2, f2, f3, f4);
                        canvas.translate(f3 - (((float) bitmap2.getWidth()) / 2.0f), f4 - (((float) bitmap2.getWidth()) / 2.0f));
                        canvas.drawBitmap(this.listBitmap.get(i11), 0.0f, 0.0f, this.paint);
                        canvas.restoreToCount(save);
                        this.paint.setXfermode(this.xfermode);
                        canvas.drawBitmap(bitmap, matrix, this.paint);
                        this.paint.setXfermode((Xfermode) null);
                        canvas.restoreToCount(saveLayer);
                    }
                }
            }
        }
    }

    private boolean checkStartTime(int i, int i2) {
        if (this.isPreviewTransition) {
            return checkStartTimePreviewTran(i, i2);
        }
        return TimeUtils.checkStartTime3(i2, getStartFrame(i));
    }

    private boolean checkStartTimePreviewTran(int i, int i2) {
        int i3;
        int i4;
        if (i == this.positionTran) {
            i4 = (int) ((VideoMaker.DURATION_TRANSITION + 2.0f) * 30.0f);
            i3 = 0;
        } else {
            i3 = 60;
            i4 = 120;
        }
        int[] iArr = {i3, i4};
        if (i2 < iArr[0] || i2 > iArr[1]) {
            return false;
        }
        return true;
    }

    private float getStartFrame(int i) {
        float f = 0.0f;
        if (i <= 0) {
            return 0.0f;
        }
        for (int i2 = 0; i2 < i; i2++) {
            f += this.mVideoMaker.getListImageModel().get(i2).getSecond();
        }
        return f * 30.0f;
    }

    private boolean checkEndTime(int i, int i2) {
        return TimeUtils.checkEndTime(i, i2, this.mVideoMaker.getListImageModel().get(i).getSecond());
    }

    private void dissolveTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2) {
        int i3 = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0];
        int i4 = i2 - i3;
        int i5 = ((int) (((float) i3) + (VideoMaker.DURATION_TRANSITION * 30.0f))) - i3;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i, i2) && i != 0) {
            canvas.drawBitmap(dissolve(this.mVideoMaker.getLastImage(i), i4, i5, 40, 20), matrix, paint2);
        }
    }

    private Bitmap dissolve(Bitmap bitmap, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3;
        int i8 = i4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = (((float) i) * 1.0f) / ((float) i2);
        Paint paint2 = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint2);
        paint2.setAntiAlias(true);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        for (int i9 = 0; i9 < i7; i9++) {
            for (int i10 = 0; i10 < i8; i10++) {
                int i11 = (i10 * width) / i8;
                int i12 = (i9 * height) / i7;
                if (this.random.nextBoolean()) {
                    i6 = (int) (((float) i11) + (((float) (width / i8)) * f));
                    i5 = (height / i7) + i12;
                } else {
                    i6 = (width / i8) + i11;
                    i5 = (int) (((float) i12) + (((float) (height / i7)) * f));
                }
                this.rect.set(i11, i12, i6, i5);
                canvas.drawRect(this.rect, paint2);
            }
        }
        return createBitmap;
    }

    private void blindTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2) {
        Bitmap lastImage;
        int i3 = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0];
        int i4 = i2 - i3;
        int i5 = ((int) (((float) i3) + (VideoMaker.DURATION_TRANSITION * 30.0f))) - i3;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i, i2) && i != 0 && (lastImage = this.mVideoMaker.getLastImage(i)) != null) {
            canvas.drawBitmap(bind(lastImage, i4, i5, 20), matrix, paint2);
        }
    }

    private Bitmap bind(Bitmap bitmap, int i, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = (int) (((((float) (height / i3)) * 1.0f) / ((float) i2)) * ((float) i));
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint2);
        paint2.setAntiAlias(true);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = (i5 * height) / i3;
            float f = (float) (i6 + i4);
            canvas.drawRect(0.0f, (float) i6, (float) width, f, paint2);
        }
        return createBitmap;
    }

    private void testTryangle(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas2 = canvas;
        Paint paint3 = paint2;
        int i5 = i;
        int i6 = i2;
        int i7 = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i5).getSecond(), (int) getStartFrame(i5))[0];
        if (this.currentBmId != i5) {
            this.currentBmId = i5;
            this.isReverse = !this.isReverse;
        }
        int i8 = i6 - i7;
        int i9 = ((int) (((float) i7) + (VideoMaker.DURATION_TRANSITION * 30.0f))) - i7;
        Matrix matrix3 = matrix2;
        Bitmap bitmap2 = bitmap;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i5, i6) && i5 != 0) {
            canvas.drawBitmap(getBitmapTryAngle(matrix, this.mVideoMaker.getLastImage(i5), i8, i9, i3, i4), matrix, paint2);
        }
    }

    private Bitmap getBitmapTryAngle(Matrix matrix, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint2);
        canvas.drawBitmap(createBitmap, new Matrix(), (Paint) null);
        paint2.setAntiAlias(true);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        float f = (float) ((i * i3) / i2);
        Path path = new Path();
        if (this.isReverse) {
            path.moveTo(0.0f, 0.0f);
            float f2 = (float) i3;
            float f3 = (float) i4;
            path.lineTo(f2, f3);
            path.lineTo(f2, f3 - f);
            path.lineTo(f, 0.0f);
            path.lineTo(0.0f, 0.0f);
        } else {
            float f4 = (float) i3;
            path.moveTo(f4, 0.0f);
            path.lineTo(0.0f, f4);
            float f5 = f4 - f;
            path.lineTo(0.0f, f5);
            path.lineTo(f5, 0.0f);
            path.lineTo(f4, 0.0f);
        }
        canvas.drawPath(path, paint2);
        Path path2 = new Path();
        if (this.isReverse) {
            path2.moveTo(0.0f, 0.0f);
            float f6 = (float) i3;
            float f7 = (float) i4;
            path2.lineTo(f6, f7);
            path2.lineTo(f6 - f, f7);
            path2.lineTo(0.0f, f);
            path2.lineTo(0.0f, 0.0f);
        } else {
            float f8 = (float) i3;
            path2.moveTo(f8, 0.0f);
            path2.lineTo(0.0f, f8);
            path2.lineTo(f, f8);
            path2.lineTo(f8, f);
            path2.lineTo(f8, 0.0f);
        }
        canvas.drawPath(path2, paint2);
        return createBitmap;
    }

    private void blindVerticalTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2) {
        int i3 = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0];
        int i4 = i2 - i3;
        int i5 = ((int) (((float) i3) + 15.0f)) - i3;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i, i2) && i != 0) {
            canvas.drawBitmap(bindVertical(this.mVideoMaker.getLastImage(i), i4, i5, 20), matrix, paint2);
        }
    }

    private Bitmap bindVertical(Bitmap bitmap, int i, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = (int) (((((float) (height / i3)) * 1.0f) / ((float) i2)) * ((float) i));
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint2);
        Matrix matrix = new Matrix();
        canvas.rotate(90.0f, (float) (width / 2), (float) (height / 2));
        canvas.drawBitmap(createBitmap, matrix, (Paint) null);
        paint2.setAntiAlias(true);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = (i5 * height) / i3;
            float f = (float) (i6 + i4);
            canvas.drawRect(0.0f, (float) i6, (float) width, f, paint2);
        }
        return createBitmap;
    }

    private void flashTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, boolean z) {
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i, i2) && i != 0) {
            canvas.drawBitmap(this.mVideoMaker.getLastImage(i), matrix, paint2);
            flash(canvas, matrix, this.mVideoMaker.getLastImage(i), i - 1, i2, z);
        }
    }

    private void testColumnCenter(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2) {
        Bitmap columeBitmapCenter;
        int i3 = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0];
        float f = VideoMaker.DURATION_TRANSITION;
        int i4 = i2 - i3;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i, i2) && i != 0 && (columeBitmapCenter = getColumeBitmapCenter(matrix, this.mVideoMaker.getLastImage(i), i4, 11)) != null) {
            canvas.drawBitmap(columeBitmapCenter, matrix, paint2);
        }
    }

    private void testColumn(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2) {
        Bitmap lastImage;
        int i3 = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0];
        float f = VideoMaker.DURATION_TRANSITION;
        int i4 = i2 - i3;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i, i2) && i != 0 && (lastImage = this.mVideoMaker.getLastImage(i)) != null) {
            canvas.drawBitmap(getColumeBitmap(lastImage, i4, 6), matrix, paint2);
        }
    }

    private void drawImageLottieTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (i2 != this.oldId) {
            this.oldId = i2;
        }
        if (this.lottieDrawable != null) {
            int i5 = this.mVideoMaker.totalFrameTransition / 2;
            if (i2 > i5) {
                int i6 = i2 % 30;
                if (i6 < 0 || i6 >= i5) {
                    int i7 = 30 - i5;
                    if (i6 <= i7 || i6 >= 30) {
                        this.drawFrame = -1;
                    } else {
                        this.drawFrame = i6 - i7;
                    }
                } else {
                    this.drawFrame = i6 + i5;
                }
                int i8 = this.drawFrame;
                if (i8 >= 0) {
                    this.lottieDrawable.setFrame(i8);
                    float intrinsicWidth = ((float) i3) / ((float) this.lottieDrawable.getIntrinsicWidth());
                    float intrinsicHeight = ((float) i4) / ((float) this.lottieDrawable.getIntrinsicHeight());
                    float max = Math.max(intrinsicWidth, intrinsicHeight);
                    canvas.save();
                    canvas.translate(((intrinsicWidth - max) * ((float) this.lottieDrawable.getIntrinsicWidth())) / 2.0f, ((intrinsicHeight - max) * ((float) this.lottieDrawable.getIntrinsicHeight())) / 2.0f);
                    canvas.scale(max, max);
                    this.lottieDrawable.draw(canvas);
                    canvas.restore();
                } else {
                    return;
                }
            }
            int i9 = i2 / 30;
            int i10 = 30 - i5;
            if (i2 % 30 >= i10) {
                this.frameStartDraw = i10 + (i9 * 30);
            }
            int i11 = this.frameStartDraw;
            if (i11 > 0 && i2 >= i11) {
                this.idBitmapToDraw = i2 - i11;
            }
        }
    }

    private Bitmap getColumeBitmapCenter(Matrix matrix, Bitmap bitmap, int i, int i2) {
        int i3;
        Bitmap bitmap2 = bitmap;
        int i4 = i2;
        if (bitmap2 == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i5 = width / i4;
        int i6 = i4 - 1;
        int i7 = i6 / 2;
        int i8 = i7 + 1;
        int i9 = (int) (VideoMaker.DURATION_TRANSITION * 30.0f);
        int i10 = height / 2;
        int i11 = i10 / i9;
        int i12 = i10 / (i9 / 3);
        int i13 = (i12 - i11) / i7;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint2);
        paint2.setAntiAlias(true);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        int i14 = 0;
        while (i14 < i4) {
            int i15 = i8 - 1;
            if (i14 == i15) {
                i3 = i * i12;
            } else {
                i3 = ((i14 < i15 ? i13 * i14 : (i6 - i14) * i13) + i11) * i;
            }
            int i16 = i14 * i5;
            int i17 = i10 - i3;
            int i18 = i16 + i5;
            float f = (float) i16;
            float f2 = (float) i10;
            int i19 = i5;
            int i20 = i3 + i10;
            Canvas canvas2 = canvas;
            float f3 = f;
            int i21 = i14;
            float f4 = (float) i18;
            Paint paint3 = paint2;
            canvas2.drawRect(f, (float) i17, f4, f2, paint3);
            canvas2.drawRect(f, f2, f4, (float) i20, paint3);
            i14 = i21 + 1;
            i4 = i2;
            i5 = i19;
        }
        return createBitmap;
    }

    private Bitmap getColumeBitmap(Bitmap bitmap, int i, int i2) {
        int i3 = i2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = (int) (maxFrame / ((float) i3));
        int i5 = height / i4;
        int i6 = width / i3;
        int i7 = i / i4;
        int i8 = i % i4;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint2);
        paint2.setAntiAlias(true);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        int i9 = 0;
        while (i9 < i3) {
            int i10 = i9 * i6;
            canvas.drawRect((float) i10, (float) (i9 == i7 ? (i4 - i8) * i5 : i9 < i7 ? 0 : height), (float) (i10 + i6), (float) height, paint2);
            i9++;
        }
        return createBitmap;
    }

    private void chessBoardTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2) {
        Bitmap chessBoard;
        int i3 = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0];
        int i4 = i2 - i3;
        int i5 = ((int) (((float) i3) + 15.0f)) - i3;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i, i2) && i != 0 && (chessBoard = chessBoard(this.mVideoMaker.getLastImage(i), i4, i5, 20, 20)) != null) {
            canvas.drawBitmap(chessBoard, matrix, paint2);
        }
    }

    private Bitmap chessBoard(Bitmap bitmap, int i, int i2, int i3, int i4) {
        Bitmap bitmap2 = bitmap;
        int i5 = i3;
        if (bitmap2 == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = (float) i2;
        float f2 = (float) i;
        int i6 = (int) (((((float) (height / i5)) * 1.0f) / f) * f2);
        int i7 = (int) (((((float) (width / i4)) * 1.0f) / f) * f2);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint2);
        paint2.setAntiAlias(true);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        for (int i8 = 0; i8 < i5; i8++) {
            int i9 = (i8 * height) / i5;
            float f3 = (float) (i9 + i6);
            Paint paint3 = paint2;
            canvas.drawRect(0.0f, (float) i9, (float) width, f3, paint3);
            int i10 = (i8 * width) / i4;
            canvas.drawRect((float) i10, 0.0f, (float) (i10 + i7), (float) height, paint3);
        }
        return createBitmap;
    }

    private void flash(Canvas canvas, Matrix matrix, Bitmap bitmap, int i, int i2, boolean z) {
        int[] startAndStopFrameOfImageNew = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i));
        int i3 = (int) (((float) startAndStopFrameOfImageNew[1]) - (VideoMaker.DURATION_TRANSITION * 30.0f));
        int i4 = (int) (((((float) (i2 - i3)) * 1.0f) / ((float) (startAndStopFrameOfImageNew[1] - i3))) * 200.0f);
        Paint paint2 = new Paint(2);
        if (z) {
            ColorMatrix colorMatrix = new ColorMatrix();
            float f = (float) i4;
            colorMatrix.set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 1.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 1.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            paint2.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            paint2.setAlpha(255 - (i4 / 4));
            canvas.drawBitmap(bitmap, matrix, paint2);
            return;
        }
        canvas.drawBitmap(bitmap, matrix, paint2);
        canvas.drawColor(Color.argb(i4, 0, 0, 0));
    }

    private void slideTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4, Transition transition) {
        int i5 = i;
        Canvas canvas2 = canvas;
        Matrix matrix3 = matrix2;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i5, i2) && i5 != 0) {
            slide(canvas, matrix, matrix2, paint2, this.mVideoMaker.getLastImage(i5), i5 - 1, i2, i3, i4, transition);
        }
    }

    private void testTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas2 = canvas;
        Paint paint3 = paint2;
        int i5 = i;
        if (checkStartTime(i5, i2)) {
            Matrix matrix3 = matrix;
            canvas.drawBitmap(this.mVideoMaker.getLastImage(i5), matrix, paint2);
            if (i5 != 0) {
                cirleZoom(canvas, matrix2, paint2, bitmap, i, i2, i3, i4);
                return;
            }
            return;
        }
        Matrix matrix4 = matrix2;
        Bitmap bitmap2 = bitmap;
        canvas.drawBitmap(bitmap, matrix2, paint2);
    }

    private void cirleZoom(Canvas canvas, Matrix matrix, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas2 = canvas;
        Paint paint3 = paint2;
        int i5 = i;
        int i6 = i3;
        int i7 = i4;
        int[] startAndStopFrameOfImageNew = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i5).getSecond(), (int) getStartFrame(i5));
        int i8 = startAndStopFrameOfImageNew[0];
        int i9 = i6 / 2;
        int i10 = i2 - i8;
        float f = (float) (((int) (((float) startAndStopFrameOfImageNew[0]) + (VideoMaker.DURATION_TRANSITION * 30.0f))) - i8);
        float f2 = (((float) (i10 * i6)) * 1.0f) / f;
        float f3 = (((float) (i10 * i9)) * 1.0f) / f;
        Canvas canvas3 = new Canvas(Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888));
        Paint paint4 = new Paint();
        new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint3.setAntiAlias(true);
        canvas2.drawARGB(0, 0, 0, 0);
        paint3.setColor(-12434878);
        float f4 = (((float) i6) - f2) / 2.0f;
        float f5 = f4 + f2;
        float f6 = (((float) i7) - f2) / 2.0f;
        float f7 = f6 + f2;
        new Rect((int) f4, (int) f6, (int) f5, (int) f7);
        canvas3.drawRect(f4, f6, f5, f7, paint3);
        paint4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Paint paint5 = new Paint(2);
        paint5.setStyle(Paint.Style.FILL);
        paint5.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas2.drawCircle((float) i9, (float) (i7 / 2), f3, paint5);
    }

    private void clockTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        int i5 = i;
        canvas.save();
        Canvas canvas2 = canvas;
        Matrix matrix3 = matrix2;
        Paint paint3 = paint2;
        Bitmap bitmap2 = bitmap;
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(i5, i2) && i5 != 0) {
            clock(canvas, matrix, this.mVideoMaker.getLastImage(i5), i5 - 1, i2, i3, i4);
        }
        canvas.restore();
    }

    private void circleTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas2 = canvas;
        Matrix matrix3 = matrix2;
        Paint paint3 = paint2;
        Bitmap bitmap2 = bitmap;
        int i5 = i;
        if (!checkStartTime(i5, i2)) {
            canvas.drawBitmap(bitmap, matrix2, paint2);
        } else if (i5 != 0) {
            Matrix matrix4 = matrix;
            canvas.drawBitmap(this.mVideoMaker.getLastImage(i5), matrix, paint2);
            canvas.drawARGB(0, 0, 0, 0);
            circle(canvas, matrix2, paint2, bitmap, i, i2, i3, i4);
        } else {
            canvas.drawBitmap(bitmap, matrix2, paint2);
        }
    }

    private void circle(Canvas canvas, Matrix matrix, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.isPreviewTransition) {
            i6 = 60;
            i5 = (int) ((VideoMaker.DURATION_TRANSITION + 2.0f) * 30.0f);
        } else {
            int[] startAndStopFrameOfImageNew = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i));
            int i7 = startAndStopFrameOfImageNew[0];
            i5 = (int) (((float) startAndStopFrameOfImageNew[0]) + (VideoMaker.DURATION_TRANSITION * 30.0f));
            i6 = i7;
        }
        canvas.drawBitmap(circle(bitmap, ((((float) (i2 - i6)) * (((float) ((((double) i3) * Math.sqrt(2.0d)) / 2.0d)) - 0.0f)) / ((float) (i5 - i6))) + 0.0f), matrix, paint2);
    }

    private Bitmap circle(Bitmap bitmap, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        Rect rect2 = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Paint paint3 = new Paint(2);
        paint3.setFlags(1);
        paint3.setColor(-12434878);
        canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), f, paint3);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect2, rect2, paint2);
        return createBitmap;
    }

    private void zoomTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas2 = canvas;
        Paint paint3 = paint2;
        int i5 = i;
        if (checkStartTime(i5, i2)) {
            Matrix matrix3 = matrix;
            canvas.drawBitmap(this.mVideoMaker.getLastImage(i5), matrix, paint2);
            if (i5 != 0) {
                zoom(canvas, matrix2, paint2, bitmap, i, i2, i3, i4);
                return;
            }
            return;
        }
        Matrix matrix4 = matrix2;
        Bitmap bitmap2 = bitmap;
        canvas.drawBitmap(bitmap, matrix2, paint2);
    }

    private void zoom(Canvas canvas, Matrix matrix, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas2 = canvas;
        Paint paint3 = paint2;
        int i5 = i;
        int i6 = i3;
        int[] startAndStopFrameOfImageNew = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i5).getSecond(), (int) getStartFrame(i5));
        int i7 = startAndStopFrameOfImageNew[0];
        float f = (((float) ((i2 - i7) * i6)) * 1.0f) / ((float) (((int) (((float) startAndStopFrameOfImageNew[0]) + (VideoMaker.DURATION_TRANSITION * 30.0f))) - i7));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas3 = new Canvas(createBitmap);
        Paint paint4 = new Paint();
        Rect rect2 = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint3.setAntiAlias(true);
        canvas2.drawARGB(0, 0, 0, 0);
        paint3.setColor(-12434878);
        float f2 = (((float) i6) - f) / 2.0f;
        float f3 = f2 + f;
        float f4 = (((float) i4) - f) / 2.0f;
        float f5 = f4 + f;
        Rect rect3 = new Rect((int) f2, (int) f4, (int) f3, (int) f5);
        canvas3.drawRect(f2, f4, f3, f5, paint2);
        paint4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas3.drawBitmap(bitmap, rect2, rect3, paint4);
        canvas2.drawBitmap(createBitmap, matrix, paint3);
    }

    private int[] getStartAndStopFrameOfImageNew(float f, int i) {
        return new int[]{i, (int) (((float) i) + ((f + VideoMaker.DURATION_TRANSITION) * 30.0f))};
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0171 A[LOOP:0: B:42:0x016f->B:43:0x0171, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0190  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void clock(Canvas r19, Matrix r20, Bitmap r21, int r22, int r23, int r24, int r25) {
        /*
            r18 = this;
            r0 = r18
            r1 = r21
            r2 = r22
            r3 = r24
            r4 = r25
            com.photoeditor.slideshow.imagetovideo.VideoMaker r5 = r0.mVideoMaker
            java.util.List r5 = r5.getListImageModel()
            java.lang.Object r5 = r5.get(r2)
            com.photoeditor.slideshow.models.ImageModel r5 = (com.photoeditor.slideshow.models.ImageModel) r5
            float r5 = r5.getSecond()
            float r2 = r0.getStartFrame(r2)
            int r2 = (int) r2
            int[] r2 = r0.getStartAndStopFrameOfImageNew(r5, r2)
            boolean r5 = r0.isPreviewTransition
            r6 = 1106247680(0x41f00000, float:30.0)
            r7 = 1
            if (r5 == 0) goto L_0x0035
            r2 = 60
            r5 = 1073741824(0x40000000, float:2.0)
            float r8 = com.photoeditor.slideshow.imagetovideo.VideoMaker.DURATION_TRANSITION
            float r8 = r8 + r5
            float r8 = r8 * r6
            int r5 = (int) r8
            goto L_0x0045
        L_0x0035:
            r5 = r2[r7]
            float r5 = (float) r5
            float r8 = com.photoeditor.slideshow.imagetovideo.VideoMaker.DURATION_TRANSITION
            float r8 = r8 * r6
            float r5 = r5 - r8
            int r5 = (int) r5
            r2 = r2[r7]
            r17 = r5
            r5 = r2
            r2 = r17
        L_0x0045:
            int r8 = r23 - r2
            if (r8 < 0) goto L_0x01a1
            float r9 = (float) r8
            float r10 = com.photoeditor.slideshow.imagetovideo.VideoMaker.DURATION_TRANSITION
            float r10 = r10 * r6
            int r6 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x0054
            goto L_0x01a1
        L_0x0054:
            int r5 = r5 - r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r8)
            java.lang.String r6 = " - "
            r2.append(r6)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.String r6 = "dsk9"
            com.photoeditor.slideshow.java.C2671Lo.m320d(r6, r2)
            r19.concat(r20)
            int r8 = r8 * 359
            int r8 = r8 / r5
            r2 = 90
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r9 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r8 > r2) goto L_0x00a1
            int r2 = r3 / 2
            double r11 = (double) r2
            double r13 = (double) r8
            double r15 = java.lang.Math.toRadians(r13)
            double r15 = java.lang.Math.tan(r15)
            double r5 = r5 / r15
            double r7 = (double) r3
            double r5 = r5 * r7
            double r5 = r5 / r9
            double r11 = r11 + r5
            int r2 = (int) r11
            int r5 = r4 / 2
            double r5 = (double) r5
            double r7 = java.lang.Math.toRadians(r13)
            double r7 = java.lang.Math.tan(r7)
        L_0x009a:
            double r11 = (double) r4
            double r7 = r7 * r11
            double r7 = r7 / r9
            double r5 = r5 - r7
        L_0x009f:
            int r5 = (int) r5
            goto L_0x010b
        L_0x00a1:
            r2 = 180(0xb4, float:2.52E-43)
            if (r8 > r2) goto L_0x00c9
            int r2 = r3 / 2
            double r11 = (double) r2
            double r7 = (double) r8
            double r13 = java.lang.Math.toRadians(r7)
            double r13 = java.lang.Math.tan(r13)
            double r5 = r5 / r13
            double r13 = (double) r3
            double r5 = r5 * r13
            double r5 = r5 / r9
            double r11 = r11 + r5
            int r2 = (int) r11
            int r5 = r4 / 2
            double r5 = (double) r5
            double r7 = java.lang.Math.toRadians(r7)
            double r7 = java.lang.Math.tan(r7)
        L_0x00c3:
            double r11 = (double) r4
            double r7 = r7 * r11
            double r7 = r7 / r9
            double r5 = r5 + r7
            goto L_0x009f
        L_0x00c9:
            r2 = 270(0x10e, float:3.78E-43)
            if (r8 > r2) goto L_0x00ec
            int r2 = r3 / 2
            double r11 = (double) r2
            double r7 = (double) r8
            double r13 = java.lang.Math.toRadians(r7)
            double r13 = java.lang.Math.tan(r13)
            double r5 = r5 / r13
            double r13 = (double) r3
            double r5 = r5 * r13
            double r5 = r5 / r9
            double r11 = r11 - r5
            int r2 = (int) r11
            int r5 = r4 / 2
            double r5 = (double) r5
            double r7 = java.lang.Math.toRadians(r7)
            double r7 = java.lang.Math.tan(r7)
            goto L_0x00c3
        L_0x00ec:
            int r2 = r3 / 2
            double r11 = (double) r2
            double r7 = (double) r8
            double r13 = java.lang.Math.toRadians(r7)
            double r13 = java.lang.Math.tan(r13)
            double r5 = r5 / r13
            double r13 = (double) r3
            double r5 = r5 * r13
            double r5 = r5 / r9
            double r11 = r11 - r5
            int r2 = (int) r11
            int r5 = r4 / 2
            double r5 = (double) r5
            double r7 = java.lang.Math.toRadians(r7)
            double r7 = java.lang.Math.tan(r7)
            goto L_0x009a
        L_0x010b:
            if (r2 <= r3) goto L_0x010e
            r2 = r3
        L_0x010e:
            r6 = 0
            if (r2 >= 0) goto L_0x0112
            r2 = 0
        L_0x0112:
            if (r5 <= r4) goto L_0x0115
            r5 = r4
        L_0x0115:
            if (r5 >= 0) goto L_0x0118
            r5 = 0
        L_0x0118:
            r7 = 5
            android.graphics.Point[] r7 = new android.graphics.Point[r7]
            android.graphics.Point r8 = new android.graphics.Point
            r8.<init>(r3, r6)
            r7[r6] = r8
            android.graphics.Point r8 = new android.graphics.Point
            r8.<init>(r6, r6)
            r9 = 1
            r7[r9] = r8
            android.graphics.Point r8 = new android.graphics.Point
            r8.<init>(r6, r4)
            r10 = 2
            r7[r10] = r8
            android.graphics.Point r8 = new android.graphics.Point
            r8.<init>(r3, r4)
            r11 = 3
            r7[r11] = r8
            android.graphics.Point r8 = new android.graphics.Point
            int r12 = r4 / 2
            r8.<init>(r3, r12)
            r13 = 4
            r7[r13] = r8
            android.graphics.Path r8 = new android.graphics.Path
            r8.<init>()
            float r14 = (float) r3
            float r15 = (float) r12
            r8.moveTo(r14, r15)
            int r6 = r3 / 2
            float r6 = (float) r6
            r8.lineTo(r6, r15)
            float r6 = (float) r2
            float r9 = (float) r5
            r8.lineTo(r6, r9)
            if (r2 != r3) goto L_0x015f
            if (r5 > r12) goto L_0x015f
        L_0x015d:
            r11 = 0
            goto L_0x016f
        L_0x015f:
            if (r5 != 0) goto L_0x0163
            r11 = 1
            goto L_0x016f
        L_0x0163:
            if (r2 != 0) goto L_0x0167
            r11 = 2
            goto L_0x016f
        L_0x0167:
            if (r5 != r4) goto L_0x016a
            goto L_0x016f
        L_0x016a:
            if (r2 != r3) goto L_0x015d
            if (r5 <= r12) goto L_0x015d
            r11 = 4
        L_0x016f:
            if (r11 >= r13) goto L_0x0181
            r2 = r7[r11]
            int r2 = r2.x
            float r2 = (float) r2
            r3 = r7[r11]
            int r3 = r3.y
            float r3 = (float) r3
            r8.lineTo(r2, r3)
            int r11 = r11 + 1
            goto L_0x016f
        L_0x0181:
            r8.lineTo(r14, r15)
            android.graphics.Paint r2 = new android.graphics.Paint
            r2.<init>(r10)
            android.graphics.Paint$Style r3 = android.graphics.Paint.Style.FILL
            r2.setStyle(r3)
            if (r1 == 0) goto L_0x019c
            android.graphics.BitmapShader r3 = new android.graphics.BitmapShader
            android.graphics.Shader$TileMode r4 = android.graphics.Shader.TileMode.CLAMP
            android.graphics.Shader$TileMode r5 = android.graphics.Shader.TileMode.CLAMP
            r3.<init>(r1, r4, r5)
            r2.setShader(r3)
        L_0x019c:
            r1 = r19
            r1.drawPath(r8, r2)
        L_0x01a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.photoeditor.slideshow.imagetovideo.TransitionUtils.clock(android.graphics.Canvas, android.graphics.Matrix, android.graphics.Bitmap, int, int, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0053, code lost:
        r13 = 0;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0057, code lost:
        r7 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0061, code lost:
        r7 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0062, code lost:
        r0 = (float) r1;
        r8.postTranslate(((float) 0) + ((((float) ((r13 - 0) * r12)) * 1.0f) / r0), ((float) r14) + ((((float) ((r7 - r14) * r12)) * 1.0f) / r0));
        r6.drawBitmap(r10, r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void slide(Canvas r6, Matrix r7, Matrix r8, Paint r9, Bitmap r10, int r11, int r12, int r13, int r14, Transition r15) {
        /*
            r5 = this;
            com.photoeditor.slideshow.imagetovideo.VideoMaker r0 = r5.mVideoMaker
            java.util.List r0 = r0.getListImageModel()
            java.lang.Object r0 = r0.get(r11)
            com.photoeditor.slideshow.models.ImageModel r0 = (com.photoeditor.slideshow.models.ImageModel) r0
            float r0 = r0.getSecond()
            float r11 = r5.getStartFrame(r11)
            int r11 = (int) r11
            int[] r11 = r5.getStartAndStopFrameOfImageNew(r0, r11)
            if (r10 != 0) goto L_0x001c
            return
        L_0x001c:
            r10.getWidth()
            int r0 = r10.getHeight()
            boolean r1 = r5.isPreviewTransition
            r2 = 1106247680(0x41f00000, float:30.0)
            if (r1 == 0) goto L_0x0034
            r11 = 60
            r1 = 1073741824(0x40000000, float:2.0)
            float r3 = com.photoeditor.slideshow.imagetovideo.VideoMaker.DURATION_TRANSITION
            float r3 = r3 + r1
            float r3 = r3 * r2
            int r1 = (int) r3
            goto L_0x0041
        L_0x0034:
            r1 = 1
            r3 = r11[r1]
            float r3 = (float) r3
            float r4 = com.photoeditor.slideshow.imagetovideo.VideoMaker.DURATION_TRANSITION
            float r4 = r4 * r2
            float r3 = r3 - r4
            int r2 = (int) r3
            r1 = r11[r1]
            r11 = r2
        L_0x0041:
            int r12 = r12 - r11
            int r1 = r1 - r11
            r8.set(r7)
            int[] r7 = com.photoeditor.slideshow.imagetovideo.TransitionUtils.C26691.$SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition
            int r11 = r15.ordinal()
            r7 = r7[r11]
            r11 = 0
            switch(r7) {
                case 10: goto L_0x005d;
                case 11: goto L_0x0059;
                case 12: goto L_0x0056;
                case 13: goto L_0x0057;
                default: goto L_0x0052;
            }
        L_0x0052:
            r7 = 0
        L_0x0053:
            r13 = 0
            r14 = 0
            goto L_0x0062
        L_0x0056:
            int r14 = -r14
        L_0x0057:
            r7 = r14
            goto L_0x0053
        L_0x0059:
            int r14 = r14 - r0
            int r14 = r14 / 2
            goto L_0x0061
        L_0x005d:
            int r13 = -r13
            int r14 = r14 - r0
            int r14 = r14 / 2
        L_0x0061:
            r7 = r14
        L_0x0062:
            float r15 = (float) r11
            int r13 = r13 - r11
            int r13 = r13 * r12
            float r11 = (float) r13
            r13 = 1065353216(0x3f800000, float:1.0)
            float r11 = r11 * r13
            float r0 = (float) r1
            float r11 = r11 / r0
            float r15 = r15 + r11
            float r11 = (float) r14
            int r7 = r7 - r14
            int r7 = r7 * r12
            float r7 = (float) r7
            float r7 = r7 * r13
            float r7 = r7 / r0
            float r11 = r11 + r7
            r8.postTranslate(r15, r11)
            r6.drawBitmap(r10, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.photoeditor.slideshow.imagetovideo.TransitionUtils.slide(android.graphics.Canvas, android.graphics.Matrix, android.graphics.Matrix, android.graphics.Paint, android.graphics.Bitmap, int, int, int, int, com.photoeditor.slideshow.imagetovideo.Transition):void");
    }

    private void fadeTransition(Canvas canvas, Matrix matrix, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        Paint paint3 = paint2;
        int i5 = i;
        int i6 = i2;
        if (checkStartTime(i, i2)) {
            fadeIn(getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0], VideoMaker.DURATION_TRANSITION, i2, i3, i4, paint2);
        } else if (checkEndTime(i, i2)) {
            fadeOut(getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[1], VideoMaker.DURATION_TRANSITION, i2, i3, i4, paint2);
        } else {
            paint2.setAlpha(255);
        }
        Canvas canvas2 = canvas;
        Matrix matrix2 = matrix;
        Bitmap bitmap2 = bitmap;
        canvas.drawBitmap(bitmap, matrix, paint2);
    }

    public void fadeOut(int i, float f, int i2, int i3, int i4, Paint paint2) {
        int i5 = (int) (f * 30.0f);
        paint2.setAlpha(i4 + (((i2 - (i - i5)) * (i3 - i4)) / i5));
    }

    public void fadeIn(int i, float f, int i2, int i3, int i4, Paint paint2) {
        paint2.setAlpha(i3 + (((i2 - i) * (i4 - i3)) / ((int) (f * 30.0f))));
    }
}
