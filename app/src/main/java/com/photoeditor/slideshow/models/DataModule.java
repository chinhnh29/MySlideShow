package com.photoeditor.slideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModule<T> {
    @SerializedName("data")
    @Expose
    private Data<T> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }

    public Data<T> getData() {
        return this.data;
    }

    public void setData(Data<T> data2) {
        this.data = data2;
    }

    public String toString() {
        return "DataModule{success=" + this.success + ", data=" + this.data + '}';
    }
}
