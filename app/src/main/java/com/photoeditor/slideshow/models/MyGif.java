package com.photoeditor.slideshow.models;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class MyGif {
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("end")
    @Expose
    private Integer end;
    private float endAt;
    private int fps;
    @SerializedName("start")
    @Expose
    private Integer start;
    private float startAt = 0.0f;
    @SerializedName("type")
    @Expose
    private Integer type;
    private String urls;

    public Integer getStart() {
        return this.start;
    }

    public void setStart(Integer num) {
        this.start = num;
    }

    public Integer getEnd() {
        return this.end;
    }

    public void setEnd(Integer num) {
        this.end = num;
    }

    public float getStartAt() {
        return this.startAt;
    }

    public void setStartAt(float f) {
        this.startAt = f;
    }

    public float getEndAt() {
        return this.endAt;
    }

    public void setEndAt(float f) {
        this.endAt = f;
    }

    public int getFps() {
        return this.fps;
    }

    public void setFps(int i) {
        this.fps = i;
    }

    public List<String> getPaths() {
        if (!TextUtils.isEmpty(this.urls)) {
            return Arrays.asList(this.urls.split(","));
        }
        return null;
    }

    public String getUrls() {
        return this.urls;
    }

    public void setUrls(String str) {
        this.urls = str;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }
}
