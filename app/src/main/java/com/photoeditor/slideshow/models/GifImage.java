package com.photoeditor.slideshow.models;

public class GifImage {
    public static final float RATIO_DEFAULT = -1.0f;
    public static final int TIME_MODE_CHANGE_SPEED = 5;
    public static final int TIME_MODE_DEFAULT = 3;
    public static final int TIME_MODE_FULL = 4;
    public static final int TIME_MODE_IGNORE = 7;
    public static final int TIME_MODE_START_TO_END = 6;
    public static final int TYPE_CENTER_CROP = 1;
    public static final int TYPE_SCALE_DEFAULT = 0;
    public static final int TYPE_SCALE_XY = 2;
    private int duration = 0;
    private int durationDefault = 1;
    private float endHorizontalBias = 0.0f;
    private float endVerticalBias = 0.0f;
    private float heightPercent = 1.0f;
    private float horizontalBias = 0.0f;

    /* renamed from: id */
    public String f506id = "";
    private String path = "";
    private int position;
    private float ratio = -1.0f;
    private int resId = 0;
    public float rotate = 0.0f;
    private int start = 0;
    private int start2 = -1;
    public String thumbnail = "";
    private int timeMode = 3;
    private int typeScale = 1;
    private float verticalBias = 0.0f;
    private float widthPercent = 1.0f;

    public GifImage(Integer num, Integer num2, String str, Integer num3, float f, float f2, float f3, float f4, float f5) {
        this.start = num.intValue();
        this.duration = num2.intValue();
        this.path = str;
        this.resId = num3.intValue();
        this.widthPercent = f;
        this.heightPercent = f2;
        this.verticalBias = f3;
        this.horizontalBias = f4;
        this.ratio = f5;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public GifImage(Integer num, Integer num2, Integer num3) {
        this.start = num.intValue();
        this.duration = num2.intValue();
        this.resId = num3.intValue();
    }

    public GifImage(Integer num, Integer num2, String str) {
        this.start = num.intValue();
        this.duration = num2.intValue();
        this.path = str;
    }

    public GifImage(int i) {
        this.resId = i;
    }

    public GifImage(String str) {
        this.path = str;
    }

    public Integer getStart() {
        return Integer.valueOf(this.start);
    }

    public void setStart(Integer num) {
        this.start = num.intValue();
    }

    public Integer getDuration() {
        return Integer.valueOf(this.duration);
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public Integer getResId() {
        return Integer.valueOf(this.resId);
    }

    public void setResId(Integer num) {
        this.resId = num.intValue();
    }

    public float getWidthPercent() {
        return this.widthPercent;
    }

    public void setWidthPercent(float f) {
        this.widthPercent = f;
    }

    public float getHeightPercent() {
        return this.heightPercent;
    }

    public void setHeightPercent(float f) {
        this.heightPercent = f;
    }

    public float getVerticalBias() {
        return this.verticalBias;
    }

    public void setVerticalBias(float f) {
        this.verticalBias = f;
        if (this.endVerticalBias == 0.0f) {
            this.endVerticalBias = f;
        }
    }

    public float getHorizontalBias() {
        return this.horizontalBias;
    }

    public void setHorizontalBias(float f) {
        this.horizontalBias = f;
        if (this.endHorizontalBias == 0.0f) {
            this.endHorizontalBias = f;
        }
    }

    public float getEndVerticalBias() {
        return this.endVerticalBias;
    }

    public void setEndVerticalBias(float f) {
        this.endVerticalBias = f;
    }

    public float getEndHorizontalBias() {
        return this.endHorizontalBias;
    }

    public void setEndHorizontalBias(float f) {
        this.endHorizontalBias = f;
    }

    public float getRatio() {
        return this.ratio;
    }

    public void setRatio(float f) {
        this.ratio = f;
    }

    public int getTypeScale() {
        return this.typeScale;
    }

    public void setTypeScale(int i) {
        this.typeScale = i;
    }

    public int getStart2() {
        return this.start2;
    }

    public void setStart2(int i) {
        this.start2 = i;
    }

    public int getTimeMode() {
        return this.timeMode;
    }

    public void setTimeMode(int i) {
        this.timeMode = i;
    }

    public float getRotate() {
        return this.rotate;
    }

    public void setRotate(float f) {
        this.rotate = f;
    }

    public String getId() {
        return this.f506id;
    }

    public void setId(String str) {
        this.f506id = str;
    }

    public int getDurationDefault() {
        return this.durationDefault;
    }

    public void setDurationDefault(int i) {
        this.durationDefault = i;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String str) {
        this.thumbnail = str;
    }

    public String toString() {
        return "GifImage{start=" + this.start + ", duration=" + this.duration + ", path='" + this.path + '\'' + ", resId=" + this.resId + ", widthPercent=" + this.widthPercent + ", heightPercent=" + this.heightPercent + ", verticalBias=" + this.verticalBias + ", horizontalBias=" + this.horizontalBias + ", endVerticalBias=" + this.endVerticalBias + ", endHorizontalBias=" + this.endHorizontalBias + ", ratio=" + this.ratio + ", typeScale=" + this.typeScale + ", timeMode=" + this.timeMode + ", rotate=" + this.rotate + '}';
    }
}
