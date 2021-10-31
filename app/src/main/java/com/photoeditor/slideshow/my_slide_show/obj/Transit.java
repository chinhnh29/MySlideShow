package com.photoeditor.slideshow.my_slide_show.obj;

import com.photoeditor.slideshow.enumm.TYPE_TRAN;
import com.photoeditor.slideshow.imagetovideo.Transition;

public class Transit {
    private String displayName = "";
    private String fileName = "";
    private String id;
    private String image = "";
    private Boolean isJsonTran = false;
    private Boolean localFile = false;
    private String moduleId = "";
    private String parentId;
    private String path;
    private String preview = "";
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

    public Transit() {
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i) {
        this.resId = i;
    }

    public void setData(Transit transit) {
        this.id = transit.id;
        this.fileName = transit.fileName;
        this.moduleId = transit.moduleId;
        this.parentId = transit.parentId;
        this.image = transit.image;
        this.thumbnail = transit.thumbnail;
        this.localFile = transit.localFile;
        this.type = transit.type;
    }

    public Transit(Transition transition) {
        this.type = transition;
    }

    public Transit(String str, String str2, String str3, int i, Boolean bool, Boolean bool2, Transition transition) {
        this.id = str;
        this.fileName = str2;
        this.displayName = str2;
        this.parentId = str3;
        this.resId = i;
        this.localFile = bool;
        this.pro = bool2;
        this.type = transition;
    }

    public Transit(Transit transit) {
        this.id = transit.id;
        this.preview = transit.preview;
        this.displayName = transit.displayName;
        this.fileName = transit.fileName;
        this.moduleId = transit.moduleId;
        this.parentId = transit.parentId;
        this.image = transit.image;
        this.thumbnail = transit.thumbnail;
        this.localFile = transit.localFile;
        this.pro = transit.pro;
        this.resId = transit.resId;
        this.type = transit.type;
        this.isJsonTran = transit.isJsonTran;
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

}
