package com.photoeditor.slideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.photoeditor.slideshow.imagetovideo.Transition;
import com.photoeditor.slideshow.models.music.Music;

public class GifTheme {
    private String fileName;
    private GifTransition gifTransition;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private String id;
    @SerializedName("image")
    @Expose
    private String image = "";
    private Boolean isJson = false;
    private Boolean localFile = false;
    @SerializedName("module_id")
    @Expose
    private String moduleId = "";
    @SerializedName("music")
    @Expose
    private String music = "";
    private Music musicModel;
    private String musicPath;
    @SerializedName("name")
    @Expose
    private String name = "";
    @SerializedName("other_file")
    @Expose
    private String otherFile = "";
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    private Boolean proBoolean = false;
    private int resId = 0;
    @SerializedName("pro")
    @Expose
    private String stringPro;
    private String themePath;
    private String thumbnail = "";
    @SerializedName("tran")
    @Expose
    private String transition = "";
    private Transition type;
    private String zipName;

    public Boolean getJson() {
        return this.isJson;
    }

    public void setJson(Boolean bool) {
        this.isJson = bool;
    }

    public Music getMusicModel() {
        return this.musicModel;
    }

    public void setMusicModel(Music music2) {
        this.musicModel = music2;
    }

    public GifTransition getGifTransition() {
        return this.gifTransition;
    }

    public void setGifTransition(GifTransition gifTransition2) {
        this.gifTransition = gifTransition2;
    }

    public String getMusicPath() {
        return this.musicPath;
    }

    public void setMusicPath(String str) {
        this.musicPath = str;
    }

    public String getThemePath() {
        return this.themePath;
    }

    public void setThemePath(String str) {
        this.themePath = str;
    }

    public String getZipName() {
        return this.zipName;
    }

    public void setZipName(String str) {
        this.zipName = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public GifTheme() {
    }

    public String getTransition() {
        return this.transition;
    }

    public void setTransition(String str) {
        this.transition = str;
    }

    public String getMusic() {
        return this.music;
    }

    public void setMusic(String str) {
        this.music = str;
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i) {
        this.resId = i;
    }

    public void setData(GifTheme gifTheme) {
        this.id = gifTheme.id;
        this.name = gifTheme.name;
        this.moduleId = gifTheme.moduleId;
        this.parentId = gifTheme.parentId;
        this.image = gifTheme.image;
        this.thumbnail = gifTheme.thumbnail;
        this.localFile = gifTheme.localFile;
    }

    public GifTheme(String str, String str2, String str3, int i, Boolean bool, Boolean bool2, Transition transition2) {
        this.id = str;
        this.name = str2;
        this.parentId = str3;
        this.resId = i;
        this.localFile = bool;
        this.proBoolean = bool2;
        this.type = transition2;
    }

    public GifTheme(GifTheme gifTheme) {
        this.id = gifTheme.id;
        this.name = gifTheme.name;
        this.moduleId = gifTheme.moduleId;
        this.parentId = gifTheme.parentId;
        this.image = gifTheme.image;
        this.thumbnail = gifTheme.thumbnail;
        this.localFile = gifTheme.localFile;
        this.proBoolean = gifTheme.proBoolean;
        this.resId = gifTheme.resId;
        this.fileName = gifTheme.fileName;
    }

    public String getOtherFile() {
        return this.otherFile;
    }

    public void setOtherFile(String str) {
        this.otherFile = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public Boolean getPro() {
        String str = this.stringPro;
        if (str == null || (!str.contains("true") && !this.stringPro.contains("yes"))) {
            return this.proBoolean;
        }
        return true;
    }

    public void setPro(Boolean bool) {
        this.proBoolean = bool;
    }

    public Transition getType() {
        return this.type;
    }

    public void setType(Transition transition2) {
        this.type = transition2;
    }

    public String toString() {
        return "GifTheme{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", moduleId='" + this.moduleId + '\'' + ", parentId='" + this.parentId + '\'' + ", otherFile='" + this.otherFile + '\'' + ", image='" + this.image + '\'' + ", resId=" + this.resId + ", thumbnail='" + this.thumbnail + '\'' + ", localFile=" + this.localFile + ", pro=" + this.proBoolean + ", type=" + this.type + ", fileName='" + this.fileName + '\'' + '}';
    }
}
