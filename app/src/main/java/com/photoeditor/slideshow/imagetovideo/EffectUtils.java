package com.photoeditor.slideshow.imagetovideo;

import android.graphics.Bitmap;
import android.graphics.Matrix;

class EffectUtils {
    private boolean isEffectPreviewTran = false;
    private VideoMaker mVideoMaker;
    private int posTransition = 0;

    EffectUtils(VideoMaker videoMaker) {
        this.mVideoMaker = videoMaker;
    }

    /* renamed from: com.photoeditor.slideshow.imagetovideo.EffectUtils$1 */
//    static /* synthetic */ class C26601 {
//        static final /* synthetic */ int[] $SwitchMap$com$photoeditor$slideshow$imagetovideo$Effect;
//
//        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
//        /* JADX WARNING: Failed to process nested try/catch */
//        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
//        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
//        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
//        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
//        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
//        static {
//            /*
//                com.photoeditor.slideshow.imagetovideo.Effect[] r0 = com.photoeditor.slideshow.imagetovideo.Effect.values()
//                int r0 = r0.length
//                int[] r0 = new int[r0]
//                $SwitchMap$com$photoeditor$slideshow$imagetovideo$Effect = r0
//                com.photoeditor.slideshow.imagetovideo.Effect r1 = com.photoeditor.slideshow.imagetovideo.Effect.DOWN     // Catch:{ NoSuchFieldError -> 0x0012 }
//                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
//                r2 = 1
//                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
//            L_0x0012:
//                int[] r0 = $SwitchMap$com$photoeditor$slideshow$imagetovideo$Effect     // Catch:{ NoSuchFieldError -> 0x001d }
//                com.photoeditor.slideshow.imagetovideo.Effect r1 = com.photoeditor.slideshow.imagetovideo.Effect.UP     // Catch:{ NoSuchFieldError -> 0x001d }
//                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
//                r2 = 2
//                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
//            L_0x001d:
//                int[] r0 = $SwitchMap$com$photoeditor$slideshow$imagetovideo$Effect     // Catch:{ NoSuchFieldError -> 0x0028 }
//                com.photoeditor.slideshow.imagetovideo.Effect r1 = com.photoeditor.slideshow.imagetovideo.Effect.LEFT     // Catch:{ NoSuchFieldError -> 0x0028 }
//                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
//                r2 = 3
//                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
//            L_0x0028:
//                int[] r0 = $SwitchMap$com$photoeditor$slideshow$imagetovideo$Effect     // Catch:{ NoSuchFieldError -> 0x0033 }
//                com.photoeditor.slideshow.imagetovideo.Effect r1 = com.photoeditor.slideshow.imagetovideo.Effect.RIGHT     // Catch:{ NoSuchFieldError -> 0x0033 }
//                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
//                r2 = 4
//                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
//            L_0x0033:
//                int[] r0 = $SwitchMap$com$photoeditor$slideshow$imagetovideo$Effect     // Catch:{ NoSuchFieldError -> 0x003e }
//                com.photoeditor.slideshow.imagetovideo.Effect r1 = com.photoeditor.slideshow.imagetovideo.Effect.ZOOM_IN     // Catch:{ NoSuchFieldError -> 0x003e }
//                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
//                r2 = 5
//                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
//            L_0x003e:
//                int[] r0 = $SwitchMap$com$photoeditor$slideshow$imagetovideo$Effect     // Catch:{ NoSuchFieldError -> 0x0049 }
//                com.photoeditor.slideshow.imagetovideo.Effect r1 = com.photoeditor.slideshow.imagetovideo.Effect.ZOOM_OUT     // Catch:{ NoSuchFieldError -> 0x0049 }
//                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
//                r2 = 6
//                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
//            L_0x0049:
//                return
//            */
//            throw new UnsupportedOperationException("Method not decompiled: com.photoeditor.slideshow.imagetovideo.EffectUtils.C26601.<clinit>():void");
//        }
//    }

    /* access modifiers changed from: package-private */
    public void effect(Effect effect, Matrix matrix, Bitmap bitmap, int indexImage, int currentFrame, int width, int height) {
        this.isEffectPreviewTran = false;
        switch (effect.ordinal()) {
            case 1:
            case 2:
            case 3:
            case 4:
                translate(matrix, bitmap, indexImage, currentFrame, effect, width, height);
                return;
            case 5:
            case 6:
                zoom(matrix, bitmap, indexImage, currentFrame, effect, width, height);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void effectTransition(Effect effect, Matrix matrix, Bitmap bitmap, int i, int i2, int i3, int i4, int i5) {
        this.isEffectPreviewTran = true;
        this.posTransition = i5;
        switch (effect.ordinal()) {
            case 1:
            case 2:
            case 3:
            case 4:
                translate(matrix, bitmap, i, i2, effect, i3, i4);
                return;
            case 5:
            case 6:
                zoom(matrix, bitmap, i, i2, effect, i3, i4);
                return;
            default:
                return;
        }
    }

    private void translate(Matrix matrix, Bitmap bitmap, int i, int i2, Effect effect, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8 = 0;
        int i9;
        int i10 =0;
        int i11 = 0;
        int i12;
        int i13 = 0;
        if (!this.isEffectPreviewTran) {
            int startFrame = (int) getStartFrame(i);
            i6 = (int) (((float) startFrame) + ((this.mVideoMaker.getListImageModel().get(i).getSecond() + VideoMaker.DURATION_TRANSITION) * 30.0f));
            i5 = startFrame;
        } else if (this.posTransition == i) {
            i5 = 0;
            i6 = 105;
        } else {
            i5 = 60;
            i6 = 120;
        }
        int i15 = i2 - i5;
        int i16 = i6 - i5;
        matrix.postScale(1.2f, 1.2f);
        int width = (int) (((float) bitmap.getWidth()) * 1.2f);
        int height = (int) (((float) bitmap.getHeight()) * 1.2f);
        if (effect.ordinal() == 1) {
            i11 = (i4 - height) / 2;
            i10 = (int) (((float) i11) + (((float) i4) * 0.1f));
            i12 = (i3 - width) / 2;
        } else if (effect.ordinal() != 2) {
            if (effect.ordinal() == 3) {
                i13 = (i3 - width) / 2;
                i8 = (int) (((float) i13) + (((float) i3) * 0.1f));
                i9 = (i4 - height) / 2;
            } else if (effect.ordinal() != 4) {
                i9 = 0;
                i8 = 0;
                i7 = 0;
                float f = (float) i16;
                matrix.postTranslate(((float) i13) + ((((float) ((i8 - i13) * i15)) * 1.0f) / f), ((float) i9) + ((((float) ((i7 - i9) * i15)) * 1.0f) / f));
            } else {
                i13 = (i3 - width) / 2;
                i8 = (int) (((float) i13) - (((float) i3) * 0.1f));
                i9 = (i4 - height) / 2;
            }
            i7 = i9;
            float f2 = (float) i16;
            matrix.postTranslate(((float) i13) + ((((float) ((i8 - i13) * i15)) * 1.0f) / f2), ((float) i9) + ((((float) ((i7 - i9) * i15)) * 1.0f) / f2));
        } else {
            i11 = (i4 - height) / 2;
            i10 = (int) (((float) i11) - (((float) i4) * 0.1f));
            i12 = (i3 - width) / 2;
        }
        i7 = i10;
        i9 = i11;
        i13 = i8;
        float f22 = (float) i16;
        matrix.postTranslate(((float) i13) + ((((float) ((i8 - i13) * i15)) * 1.0f) / f22), ((float) i9) + ((((float) ((i7 - i9) * i15)) * 1.0f) / f22));
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

    private void zoom(Matrix matrix, Bitmap bitmap, int i, int i2, Effect effect, int i3, int i4) {
        int i5;
        int i6;
        if (!this.isEffectPreviewTran) {
            int startFrame = (int) getStartFrame(i);
            int i7 = startFrame;
            i6 = (int) (((float) startFrame) + ((this.mVideoMaker.getListImageModel().get(i).getSecond() + VideoMaker.DURATION_TRANSITION) * 30.0f));
            i5 = i7;
        } else if (this.posTransition == i) {
            i5 = 0;
            i6 = 105;
        } else {
            i5 = 60;
            i6 = 120;
        }
        int i8 = i2 - i5;
        int i9 = i6 - i5;
        float f = 1.2f;
        float f2 = 1.0f;
        if (effect != Effect.ZOOM_IN) {
            f = 1.0f;
            if (effect == Effect.ZOOM_OUT) {
                f2 = 1.2f;
            }
        }
        float f3 = f + ((((float) i8) * (f2 - f)) / ((float) i9));
        matrix.setScale(f3, f3);
        matrix.postTranslate((((float) i3) - (((float) bitmap.getWidth()) * f3)) * 0.5f, (((float) i4) - (((float) bitmap.getHeight()) * f3)) * 0.5f);
    }
}
