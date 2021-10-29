package com.photoeditor.slideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class GifSticker {
    @SerializedName("app_name")
    @Expose
    private String appName = "";
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("other_file")
    @Expose
    private String image = "";
    private Boolean localFile = true;
    @SerializedName("module_id")
    @Expose
    private Integer moduleId;
    @SerializedName("module_name")
    @Expose
    private String moduleName = "";
    @SerializedName("name")
    @Expose
    private String name = "";
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("parent_name")
    @Expose
    private String parentName = "";
    @SerializedName("priority")
    @Expose
    private Integer priority = 1;
    @SerializedName("pro")
    @Expose
    private Boolean pro = false;
    private int resId = 0;
    @SerializedName("image")
    @Expose
    private String thumbnail = "";

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer num) {
        this.priority = num;
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

    public Boolean getLocalFile() {
        return this.localFile;
    }

    public void setLocalFile(Boolean bool) {
        this.localFile = bool;
    }

    public String toString() {
        return "GifSticker{id=" + this.id + ", name='" + this.name + '\'' + ", moduleId=" + this.moduleId + ", moduleName='" + this.moduleName + '\'' + ", appName='" + this.appName + '\'' + ", parentId=" + this.parentId + ", parentName='" + this.parentName + '\'' + ", priority=" + this.priority + ", image='" + this.image + '\'' + ", resId=" + this.resId + ", thumbnail='" + this.thumbnail + '\'' + ", localFile=" + this.localFile + ", pro=" + this.pro + '}';
    }

    public GifSticker() {
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i) {
        this.resId = i;
    }

    public void setData(GifSticker gifSticker) {
        this.id = gifSticker.id;
        this.name = gifSticker.name;
        this.moduleId = gifSticker.moduleId;
        this.moduleName = gifSticker.moduleName;
        this.appName = gifSticker.appName;
        this.parentId = gifSticker.parentId;
        this.parentName = gifSticker.parentName;
        this.priority = gifSticker.priority;
        this.image = gifSticker.image;
        this.thumbnail = gifSticker.thumbnail;
        this.localFile = gifSticker.localFile;
    }

    public GifSticker(GifSticker gifSticker) {
        this.id = gifSticker.id;
        this.name = gifSticker.name;
        this.moduleId = gifSticker.moduleId;
        this.moduleName = gifSticker.moduleName;
        this.appName = gifSticker.appName;
        this.parentId = gifSticker.parentId;
        this.parentName = gifSticker.parentName;
        this.priority = gifSticker.priority;
        this.image = gifSticker.image;
        this.thumbnail = gifSticker.thumbnail;
        this.localFile = gifSticker.localFile;
        this.pro = gifSticker.pro;
        this.resId = gifSticker.resId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GifSticker)) {
            return false;
        }
        GifSticker gifSticker = (GifSticker) obj;
        if (!Objects.equals(this.id, gifSticker.id) || !Objects.equals(this.name, gifSticker.name) || !Objects.equals(this.moduleId, gifSticker.moduleId) || !Objects.equals(this.moduleName, gifSticker.moduleName) || !Objects.equals(this.appName, gifSticker.appName) || !Objects.equals(this.parentId, gifSticker.parentId) || !Objects.equals(this.parentName, gifSticker.parentName) || !Objects.equals(this.priority, gifSticker.priority) || !Objects.equals(this.image, gifSticker.image) || !Objects.equals(this.thumbnail, gifSticker.thumbnail) || !Objects.equals(this.localFile, gifSticker.localFile) || !Objects.equals(this.pro, gifSticker.pro)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.name, this.moduleId, this.moduleName, this.appName, this.parentId, this.parentName, this.priority, this.image, this.thumbnail, this.localFile, this.pro});
    }

    public Boolean getPro() {
        return this.pro;
    }

    public void setPro(Boolean bool) {
        this.pro = bool;
    }
}
