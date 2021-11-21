package com.photoeditor.slideshow.my_slide_show.obj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.photoeditor.slideshow.enumm.TYPE_TRAN;
import com.photoeditor.slideshow.imagetovideo.Transition;

public class FilterModel {
    @SerializedName("name")
    @Expose
    private String displayName = "";
    private String fileName = "";
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("other_file")
    @Expose
    private String image = "";
    private Boolean isJsonTran = false;
    private Boolean localFile = false;
    @SerializedName("module_id")
    @Expose
    private String moduleId = "";
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    private String path;
    @SerializedName("image")
    @Expose
    private String preview = "";
    @SerializedName("pro")
    @Expose
    private Boolean pro = false;
    private int resId = 0;
    private String serverName;
    private String thumbnail = "";
    private Transition type;
    private TYPE_TRAN type_tran;

    public TYPE_TRAN getType_tran() {
        return this.type_tran;
    }

    public void setType_tran(TYPE_TRAN type_tran2) {
        this.type_tran = type_tran2;
    }

    public Boolean getJsonTran() {
        return this.isJsonTran;
    }

    public void setJsonTran(Boolean bool) {
        this.isJsonTran = bool;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(String str) {
        this.moduleId = str;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String str) {
        this.parentId = str;
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

    public FilterModel() {
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i) {
        this.resId = i;
    }

    public void setData(FilterModel filterModel) {
        this.id = filterModel.id;
        this.fileName = filterModel.fileName;
        this.moduleId = filterModel.moduleId;
        this.parentId = filterModel.parentId;
        this.image = filterModel.image;
        this.thumbnail = filterModel.thumbnail;
        this.localFile = filterModel.localFile;
        this.type = filterModel.type;
    }

    public FilterModel(Transition transition) {
        this.type = transition;
    }

    public FilterModel(String id, String fileName, String parentId, int resId, boolean localFile, boolean pro, Transition transition) {
        this.id = id;
        this.fileName = fileName;
        this.displayName = fileName;
        this.parentId = parentId;
        this.resId = resId;
        this.localFile = localFile;
        this.pro = pro;
        this.type = transition;
    }

    public FilterModel(FilterModel filterModel) {
        this.id = filterModel.id;
        this.preview = filterModel.preview;
        this.displayName = filterModel.displayName;
        this.fileName = filterModel.fileName;
        this.moduleId = filterModel.moduleId;
        this.parentId = filterModel.parentId;
        this.image = filterModel.image;
        this.thumbnail = filterModel.thumbnail;
        this.localFile = filterModel.localFile;
        this.pro = filterModel.pro;
        this.resId = filterModel.resId;
        this.type = filterModel.type;
        this.isJsonTran = filterModel.isJsonTran;
    }

    public Boolean getPro() {
        return this.pro;
    }

    public void setPro(Boolean bool) {
        this.pro = bool;
    }

    public Transition getType() {
        return this.type;
    }

    public void setType(Transition transition) {
        this.type = transition;
    }

    public String getPreview() {
        return this.preview;
    }

    public void setPreview(String str) {
        this.preview = str;
    }

    public String toString() {
        return "GifTransition{id='" + this.id + '\'' + ", displayName='" + this.displayName + '\'' + ", fileName='" + this.fileName + '\'' + ", moduleId='" + this.moduleId + '\'' + ", parentId='" + this.parentId + '\'' + ", image='" + this.image + '\'' + ", preview='" + this.preview + '\'' + ", pro=" + this.pro + ", resId=" + this.resId + ", thumbnail='" + this.thumbnail + '\'' + ", localFile=" + this.localFile + ", type=" + this.type + '}';
    }
}
