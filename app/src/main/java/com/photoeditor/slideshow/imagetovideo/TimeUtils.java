package com.photoeditor.slideshow.imagetovideo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    static int frameToMilisecond(int i) {
        return (int) ((((float) i) / 30.0f) * 1000.0f);
    }

    static boolean checkStartTime(int i, int i2, float f) {
        int[] startAndStopFrameOfImage = getStartAndStopFrameOfImage(i, f);
        if (i2 < startAndStopFrameOfImage[0] || ((float) i2) > ((float) startAndStopFrameOfImage[0]) + (VideoMaker.DURATION_TRANSITION * 30.0f)) {
            return false;
        }
        return true;
    }

    static boolean checkEndTime(int i, int i2, float f) {
        int[] startAndStopFrameOfImage = getStartAndStopFrameOfImage(i, f);
        if (((float) i2) < ((float) startAndStopFrameOfImage[1]) - (VideoMaker.DURATION_TRANSITION * 30.0f) || i2 > startAndStopFrameOfImage[1]) {
            return false;
        }
        return true;
    }

    public static int[] getStartAndStopFrameOfImage(int i, float f) {
        int i2 = (int) (((float) i) * f * 30.0f);
        return new int[]{i2, (int) (((float) i2) + ((f + VideoMaker.DURATION_TRANSITION) * 30.0f))};
    }

    public static int[] getStartAndStopFrameOfImage2(float f, float f2) {
        int i = (int) f2;
        return new int[]{i, (int) (((float) i) + ((f + VideoMaker.DURATION_TRANSITION) * 30.0f))};
    }

    static boolean checkStartTime2(int i, float f, float f2) {
        int[] startAndStopFrameOfImage2 = getStartAndStopFrameOfImage2(f, f2);
        if (i < startAndStopFrameOfImage2[0] || i > startAndStopFrameOfImage2[0]) {
            return false;
        }
        return true;
    }

    static boolean checkStartTime3(int i, float f) {
        int[] iArr = {(int) f, (int) (f + (VideoMaker.DURATION_TRANSITION * 30.0f))};
        if (i < iArr[0] || i > iArr[1]) {
            return false;
        }
        return true;
    }

    static boolean checkStartTime4(int i, float f) {
        if (f > 0.0f && f >= (VideoMaker.DURATION_TRANSITION * 30.0f) / 2.0f) {
            f = (float) ((int) (f - ((VideoMaker.DURATION_TRANSITION * 30.0f) / 2.0f)));
        }
        int[] iArr = {(int) f, (int) (f + ((VideoMaker.DURATION_TRANSITION / 2.0f) * 30.0f))};
        if (i < iArr[0] || i > iArr[1]) {
            return false;
        }
        return true;
    }

    static boolean checkEndTime2(int i, float f, float f2) {
        int[] startAndStopFrameOfImage2 = getStartAndStopFrameOfImage2(f, f2);
        if (((float) i) < ((float) startAndStopFrameOfImage2[1]) - (VideoMaker.DURATION_TRANSITION * 30.0f) || i > startAndStopFrameOfImage2[1]) {
            return false;
        }
        return true;
    }

    public static String parseTimeStampToString(long j) {
        try {
            return new SimpleDateFormat("MM-dd-yyyy hh:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return "xx";
        }
    }

    public static String parseTimeStampToString2(long j) {
        try {
            return new SimpleDateFormat("MM-dd-yyyy", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return "xx";
        }
    }
}
