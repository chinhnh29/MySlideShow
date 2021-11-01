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
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.Log;
import android.widget.Toast;

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
        paint.setColor(0);
        this.paint.setStrokeWidth(20.0f);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    }

    public void setLottieDrawable(LottieDrawable lottieDrawable2) {
        this.lottieDrawable = lottieDrawable2;
    }

    public void transition(Transition transition, Canvas canvas, Matrix matrix, Matrix matrix2,
                           Bitmap bitmap, Paint paint2, int index, int currentFrame, int widthPreview, int heightPreview, boolean z, int i5) {
        Matrix matrix3 = matrix2;
        Bitmap bitmap2 = bitmap;
        Paint paint3 = paint2;
        this.isPreviewTransition = z;
        this.positionTran = i5;
        int tran = transition.ordinal();
        if (tran == Transition.NONE.ordinal()) {
            setNone(canvas, matrix3, bitmap2, paint3);
        } else if (tran == Transition.SLIDE_LEFT.ordinal() || tran == Transition.SLIDE_DOWN.ordinal() ||
                tran == Transition.SLIDE_RIGHT.ordinal() || tran == Transition.SLIDE_UP.ordinal()) {
            slideTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview, transition);
        } else if (tran == Transition.FADE.ordinal()) {
            fadeTransition(canvas, matrix2, paint2, bitmap, index, currentFrame, 100, 255);
        } else if (tran == Transition.FLASH_B.ordinal()) {
            flashTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, false);
        } else if (tran == Transition.FLASH_W.ordinal()) {
            flashTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, true);
        } else if (tran == Transition.ZOOM.ordinal()) {
//            transitionDraw(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview);
            zoomTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview);
        } else if (tran == Transition.CLOCK.ordinal()) {
            Log.e("ChinhNH", "transition: " + "clock");
            //chua chay duoc
            clockTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview);
        } else if (tran == Transition.CIRCLE.ordinal()) {
            Log.e("ChinhNH", "transition: " + "clock");
            circleTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview);
        } else if (tran == Transition.COLUMN1.ordinal()) {
            testColumn(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame);
        }  else if (tran == Transition.COLUMN2.ordinal()) {
            testColumnCenter(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame);
        }  else if (tran == Transition.TRIANGLE.ordinal()) {
            testTryangle(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview);
        } else if (tran == Transition.BOARD.ordinal()) {
            chessBoardTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame);
        } else if (tran == Transition.BLIND_H.ordinal()) {
            blindTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame);
        } else if (tran == Transition.BLIND_V.ordinal()) {
            blindVerticalTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame);
        } else if (tran == Transition.DISSOLVE.ordinal()) {
            dissolveTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame);
        } else {
            canvas.drawBitmap(bitmap2, matrix3, paint3);
        }

//            case 5:
//        transitionDraw(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview);
//                break;
//            case 8:
//                drawImageLottieTransition(canvas, matrix, matrix2, paint2, bitmap, index, currentFrame, widthPreview, heightPreview);
//            default:
//                canvas.drawBitmap(bitmap2, matrix3, paint3);
//                break;
//        }
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
        int i5 = i;
        canvas.save();
        if (!checkStartTime(i5, i2)) {
            canvas.drawBitmap(bitmap, matrix2, paint2);
        } else {
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
                    int saveLayer = canvas.saveLayer((RectF) null, (Paint) null, Canvas.ALL_SAVE_FLAG);
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

    private void clockTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap,
                                 int index, int currentFrame, int widthPreview, int heightPreview) {
        canvas.save();
        canvas.drawBitmap(bitmap, matrix2, paint2);
        if (checkStartTime(index, currentFrame) && index != 0) {
            Log.e("ChinhNH", "clockTransition: ");
            clock(canvas, matrix, this.mVideoMaker.getLastImage(index), index - 1, currentFrame, widthPreview, heightPreview);
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

    private void zoomTransition(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int index, int i2, int i3, int i4) {
        if (checkStartTime(index, i2)) {
            canvas.drawBitmap(this.mVideoMaker.getLastImage(index), matrix, paint2);
            if (index != 0) {
                zoom(canvas, matrix2, paint2, bitmap, index, i2, i3, i4);
                return;
            }
            return;
        }
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

    private void clock(Canvas canvas, Matrix matrix, Bitmap bitmap, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int tan;
        double d = 0;
        double tan2 = 0;
        double d2 = 0;
        double tan3 = 0;
        double d3;
        int i7;
        int i8;
        int[] startAndStopFrameOfImageNew = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i));
        if (this.isPreviewTransition) {
            i6 = 60;
            i5 = (int) ((VideoMaker.DURATION_TRANSITION + 2.0f) * 30.0f);
        } else {
            int i9 = (int) (((float) startAndStopFrameOfImageNew[1]) - (VideoMaker.DURATION_TRANSITION * 30.0f));
            i5 = startAndStopFrameOfImageNew[1];
            i6 = i9;
        }
        int i10 = i2 - i6;
        if (i10 >= 0 && ((float) i10) <= VideoMaker.DURATION_TRANSITION * 30.0f) {
            int i11 = i5 - i6;
            canvas.concat(matrix);
            int i12 = (i10 * 359) / i11;
            if (i12 <= 90) {
                double d4 = (double) i12;
                tan = (int) (((double) (i3 / 2)) + (((1.0d / Math.tan(Math.toRadians(d4))) * ((double) i3)) / 2.0d));
                d = (double) (i4 / 2);
                tan2 = Math.tan(Math.toRadians(d4));
            } else {
                if (i12 <= 180) {
                    double d5 = (double) i12;
                    tan = (int) (((double) (i3 / 2)) + (((1.0d / Math.tan(Math.toRadians(d5))) * ((double) i3)) / 2.0d));
                    d2 = (double) (i4 / 2);
                    tan3 = Math.tan(Math.toRadians(d5));
                } else if (i12 <= 270) {
                    double d6 = (double) i12;
                    tan = (int) (((double) (i3 / 2)) - (((1.0d / Math.tan(Math.toRadians(d6))) * ((double) i3)) / 2.0d));
                    d2 = (double) (i4 / 2);
                    tan3 = Math.tan(Math.toRadians(d6));
                } else {
                    double d7 = (double) i12;
                    tan = (int) (((double) (i3 / 2)) - (((1.0d / Math.tan(Math.toRadians(d7))) * ((double) i3)) / 2.0d));
                    d = (double) (i4 / 2);
                    tan2 = Math.tan(Math.toRadians(d7));
                }
                d3 = d2 + ((tan3 * ((double) i4)) / 2.0d);
                i7 = (int) d3;
                if (tan > i3) {
                    tan = i3;
                }
                if (tan < 0) {
                    tan = 0;
                }
                if (i7 > i4) {
                    i7 = i4;
                }
                if (i7 < 0) {
                    i7 = 0;
                }
                i8 = 3;
                int i13 = i4 / 2;
                Point[] pointArr = {new Point(i3, 0), new Point(0, 0), new Point(0, i4), new Point(i3, i4), new Point(i3, i13)};
                Path path = new Path();
                float f = (float) i3;
                float f2 = (float) i13;
                path.moveTo(f, f2);
                path.lineTo((float) (i3 / 2), f2);
                path.lineTo((float) tan, (float) i7);
                if (tan != i3 || i7 > i13) {
                    if (i7 != 0) {
                        i8 = 1;
                    } else if (tan == 0) {
                        i8 = 2;
                    } else if (i7 != i4) {
                        if (tan == i3 && i7 > i13) {
                            i8 = 4;
                        }
                    }
                    while (i8 < 4) {
                        path.lineTo((float) pointArr[i8].x, (float) pointArr[i8].y);
                        i8++;
                    }
                    path.lineTo(f, f2);
                    Paint paint2 = new Paint(2);
                    paint2.setStyle(Paint.Style.FILL);
                    if (bitmap != null) {
                        paint2.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                    }
                    canvas.drawPath(path, paint2);
                }
                i8 = 0;
                while (i8 < 4) {
                }
                path.lineTo(f, f2);
                Paint paint22 = new Paint(2);
                paint22.setStyle(Paint.Style.FILL);
                if (bitmap != null) {
                }
                canvas.drawPath(path, paint22);
            }
            d3 = d - ((tan2 * ((double) i4)) / 2.0d);
            i7 = (int) d3;
            if (tan > i3) {
            }
            if (tan < 0) {
            }
            if (i7 > i4) {
            }
            if (i7 < 0) {
            }
            i8 = 3;
            int i132 = i4 / 2;
            Point[] pointArr2 = {new Point(i3, 0), new Point(0, 0), new Point(0, i4), new Point(i3, i4), new Point(i3, i132)};
            Path path2 = new Path();
            float f3 = (float) i3;
            float f22 = (float) i132;
            path2.moveTo(f3, f22);
            path2.lineTo((float) (i3 / 2), f22);
            path2.lineTo((float) tan, (float) i7);
            if (i7 != 0) {
            }
            while (i8 < 4) {
            }
            path2.lineTo(f3, f22);
            Paint paint222 = new Paint(2);
            paint222.setStyle(Paint.Style.FILL);
            if (bitmap != null) {
            }
            canvas.drawPath(path2, paint222);
        }
    }


    private void slide(Canvas canvas, Matrix matrix, Matrix matrix2, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4, Transition transition) {
        int i5;
        int i6;
        int i7;
        int i8;
        int[] startAndStopFrameOfImageNew = getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i));
        if (bitmap != null) {
            bitmap.getWidth();
            int height = bitmap.getHeight();
            if (this.isPreviewTransition) {
                i5 = 60;
                i6 = (int) ((VideoMaker.DURATION_TRANSITION + 2.0f) * 30.0f);
            } else {
                i6 = startAndStopFrameOfImageNew[1];
                i5 = (int) (((float) startAndStopFrameOfImageNew[1]) - (VideoMaker.DURATION_TRANSITION * 30.0f));
            }
            int i9 = i2 - i5;
            int i10 = i6 - i5;
            matrix2.set(matrix);
            switch (transition) {
                case SLIDE_LEFT:
                    i3 = -i3;
                    i7 = (i4 - height) / 2;
                    i8 = i7;
                    float f = (float) i10;
                    matrix2.postTranslate(((float) 0) + ((((float) ((i3 - 0) * i9)) * 1.0f) / f), ((float) i7) + ((((float) ((i8 - i7) * i9)) * 1.0f) / f));
                    canvas.drawBitmap(bitmap, matrix2, paint2);
                    return;
                case SLIDE_RIGHT:
                    i7 = (i4 - height) / 2;
                    i8 = i7;
                    float f2 = (float) i10;
                    matrix2.postTranslate(((float) 0) + ((((float) ((i3 - 0) * i9)) * 1.0f) / f2), ((float) i7) + ((((float) ((i8 - i7) * i9)) * 1.0f) / f2));
                    canvas.drawBitmap(bitmap, matrix2, paint2);
                    return;
                case SLIDE_UP:
                    i4 = -i4;
                    i8 = i4;
                    i3 = 0;
                    i7 = 0;
                    float f22 = (float) i10;
                    matrix2.postTranslate(((float) 0) + ((((float) ((i3 - 0) * i9)) * 1.0f) / f22), ((float) i7) + ((((float) ((i8 - i7) * i9)) * 1.0f) / f22));
                    canvas.drawBitmap(bitmap, matrix2, paint2);
                    return;
                case SLIDE_DOWN:
                    i8 = i4;
                    i3 = 0;
                    i7 = 0;
                    float f222 = (float) i10;
                    matrix2.postTranslate(((float) 0) + ((((float) ((i3 - 0) * i9)) * 1.0f) / f222), ((float) i7) + ((((float) ((i8 - i7) * i9)) * 1.0f) / f222));
                    canvas.drawBitmap(bitmap, matrix2, paint2);
                    return;
                default:
                    i8 = 0;
                    i3 = 0;
                    i7 = 0;
                    float f2222 = (float) i10;
                    matrix2.postTranslate(((float) 0) + ((((float) ((i3 - 0) * i9)) * 1.0f) / f2222), ((float) i7) + ((((float) ((i8 - i7) * i9)) * 1.0f) / f2222));
                    canvas.drawBitmap(bitmap, matrix2, paint2);
                    return;
            }
        }
    }


    private void fadeTransition(Canvas canvas, Matrix matrix, Paint paint2, Bitmap bitmap, int i, int i2, int i3, int i4) {
        if (checkStartTime(i, i2)) {
            fadeIn(getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[0], VideoMaker.DURATION_TRANSITION, i2, i3, i4, paint2);
        } else if (checkEndTime(i, i2)) {
            fadeOut(getStartAndStopFrameOfImageNew(this.mVideoMaker.getListImageModel().get(i).getSecond(), (int) getStartFrame(i))[1], VideoMaker.DURATION_TRANSITION, i2, i3, i4, paint2);
        } else {
            paint2.setAlpha(255);
        }
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
