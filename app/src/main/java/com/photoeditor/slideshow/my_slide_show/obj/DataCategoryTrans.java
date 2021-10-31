package com.photoeditor.slideshow.my_slide_show.obj;

public class DataCategoryTrans {
    private String appName;
    private String id;
    private String image = "";
    private Boolean isPro;
    private String moduleId = "";
    private String moduleName;
    private String name = "";
    private Integer priority;
    private Integer status;
    private String thumbnail;

    public DataCategoryTrans() {
    }

    public DataCategoryTrans(String name) {
        this.name = name;
    }

    public DataCategoryTrans(String str, String str2) {
        this.id = str;
        this.name = str2;
    }

    public DataCategoryTrans(String str, String str2, String str3) {
        this.id = str;
        this.name = str2;
        this.image = str3;
    }

    public DataCategoryTrans(String str, String str2, String str3, String str4) {
        this.id = str;
        this.name = str3;
        this.moduleId = str2;
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
}
