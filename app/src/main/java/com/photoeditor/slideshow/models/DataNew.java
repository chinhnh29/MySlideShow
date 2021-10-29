package com.photoeditor.slideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataNew {
    @SerializedName("app_name")
    @Expose
    private String appName;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private Integer f505id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("module_id")
    @Expose
    private Integer moduleId;
    @SerializedName("module_name")
    @Expose
    private String moduleName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("parent_name")
    @Expose
    private String parentName;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getId() {
        return this.f505id;
    }

    public void setId(Integer num) {
        this.f505id = num;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public Integer getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(Integer num) {
        this.moduleId = num;
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

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String str) {
        this.parentName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "DataNew{id=" + this.f505id + ", content='" + this.content + '\'' + ", moduleId=" + this.moduleId + ", moduleName='" + this.moduleName + '\'' + ", appName='" + this.appName + '\'' + ", parentId='" + this.parentId + '\'' + ", parentName='" + this.parentName + '\'' + ", status=" + this.status + ", priority=" + this.priority + '}';
    }
}
