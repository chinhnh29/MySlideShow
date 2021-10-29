package com.photoeditor.slideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCategory {
    @SerializedName("app_name")
    @Expose
    private String appName;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String f504id;
    @SerializedName("image")
    @Expose
    private String image = "";
    @SerializedName("is_pro")
    @Expose
    private Boolean isPro;
    @SerializedName("module_id")
    @Expose
    private String moduleId = "";
    @SerializedName("module_name")
    @Expose
    private String moduleName;
    @SerializedName("name")
    @Expose
    private String name = "";
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public DataCategory() {
    }

    public DataCategory(String str, String str2) {
        this.f504id = str;
        this.name = str2;
    }

    public DataCategory(String str, String str2, String str3) {
        this.f504id = str;
        this.name = str2;
        this.image = str3;
    }

    public DataCategory(String str, String str2, String str3, String str4) {
        this.f504id = str;
        this.name = str3;
        this.moduleId = str2;
    }

    public String getId() {
        return this.f504id;
    }

    public void setId(String str) {
        this.f504id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Boolean getIsPro() {
        return this.isPro;
    }

    public void setIsPro(Boolean bool) {
        this.isPro = bool;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(String str) {
        this.moduleId = str;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String str) {
        this.moduleName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String str) {
        this.thumbnail = str;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer num) {
        this.priority = num;
    }

    public String toString() {
        return "DataCategory{id='" + this.f504id + '\'' + ", name='" + this.name + '\'' + ", isPro=" + this.isPro + ", moduleId=" + this.moduleId + ", moduleName='" + this.moduleName + '\'' + ", appName='" + this.appName + '\'' + ", image='" + this.image + '\'' + ", thumbnail='" + this.thumbnail + '\'' + ", status=" + this.status + ", priority=" + this.priority + '}';
    }
}
