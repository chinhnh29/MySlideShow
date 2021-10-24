package com.photoeditor.slideshow.models.music;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Music implements Parcelable {
    public static final Creator<Music> CREATOR = new Creator<Music>() {
        public Music createFromParcel(Parcel parcel) {
            return new Music(parcel);
        }

        public Music[] newArray(int i) {
            return new Music[i];
        }
    };
    @SerializedName("app_name")
    @Expose
    private String appName = "";
    @SerializedName("artist")
    @Expose
    private String artist = "Unknown";
    @SerializedName("audio")
    @Expose
    private String audio = "";
    private String content = "";
    @SerializedName("country")
    @Expose
    private String country = "";
    private String duration = "0";
    private boolean favourite = false;
    private String fileName = "";
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private Integer id = 0;
    @SerializedName("image")
    @Expose
    private String image = "";
    private boolean isPause = false;
    @SerializedName("license")
    @Expose
    private String license = "Unknown";
    private Boolean localFile = false;
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
    private String parentId = "";
    @SerializedName("parent_name")
    @Expose
    private String parentName = "";
    @SerializedName("priority")
    @Expose
    private Integer priority = 1;
    @SerializedName("thumbnail_image")
    @Expose
    private String thumbnail = "";
    private int type = 0;
    @SerializedName("video")
    @Expose
    private String video = "";

    public int describeContents() {
        return 0;
    }

    public boolean isPause() {
        return this.isPause;
    }

    public void setPause(boolean z) {
        this.isPause = z;
    }

    public Music(int i) {
        this.type = i;
    }

    protected Music(Parcel parcel) {
        boolean z = false;
        Boolean bool = null;
        if (parcel.readByte() == 0) {
            this.id = null;
        } else {
            this.id = (parcel.readInt());
        }
        this.name = parcel.readString();
        if (parcel.readByte() == 0) {
            this.moduleId = null;
        } else {
            this.moduleId = (parcel.readInt());
        }
        this.moduleName = parcel.readString();
        this.appName = parcel.readString();
        this.parentId = parcel.readString();
        this.parentName = parcel.readString();
        this.audio = parcel.readString();
        if (parcel.readByte() == 0) {
            this.priority = null;
        } else {
            this.priority = (parcel.readInt());
        }
        this.video = parcel.readString();
        this.image = parcel.readString();
        this.thumbnail = parcel.readString();
        byte readByte = parcel.readByte();
        if (readByte != 0) {
            bool = (readByte == 1);
        }
        this.localFile = bool;
        this.artist = parcel.readString();
        this.content = parcel.readString();
        this.duration = parcel.readString();
        this.license = parcel.readString();
        this.country = parcel.readString();
        this.favourite = parcel.readByte() != 0 || z;
        this.type = parcel.readInt();
        this.fileName = parcel.readString();
    }

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

    public String getAudio() {
        return this.audio;
    }

    public void setAudio(String str) {
        this.audio = str;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer num) {
        this.priority = num;
    }

    public String getVideo() {
        return this.video;
    }

    public void setVideo(String str) {
        this.video = str;
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

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setDuration(String str) {
        if (str == null) {
            str = "0";
        }
        this.duration = str;
    }

    public boolean isFavourite() {
        return this.favourite;
    }

    public void setFavourite(boolean z) {
        this.favourite = z;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String str) {
        this.license = str;
    }

    public void setData(Music music) {
        this.id = music.id;
        this.name = music.name;
        this.moduleId = music.moduleId;
        this.moduleName = music.moduleName;
        this.appName = music.appName;
        this.parentId = music.parentId;
        this.parentName = music.parentName;
        this.audio = music.audio;
        this.priority = music.priority;
        this.video = music.video;
        this.image = music.image;
        this.thumbnail = music.thumbnail;
        this.localFile = music.localFile;
        this.artist = music.artist;
        this.content = music.content;
        this.duration = music.duration;
        this.license = music.license;
        this.favourite = music.favourite;
        this.country = music.country;
        this.fileName = music.fileName;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public Music() {
    }

    public Music(Music music) {
        this.id = music.id;
        this.name = music.name;
        this.moduleId = music.moduleId;
        this.moduleName = music.moduleName;
        this.appName = music.appName;
        this.parentId = music.parentId;
        this.parentName = music.parentName;
        this.audio = music.audio;
        this.priority = music.priority;
        this.video = music.video;
        this.image = music.image;
        this.thumbnail = music.thumbnail;
        this.localFile = music.localFile;
        this.artist = music.artist;
        this.content = music.content;
        this.duration = music.duration;
        this.license = music.license;
        this.favourite = music.favourite;
        this.country = music.country;
        this.fileName = music.fileName;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Music)) {
            return false;
        }
        Music music = (Music) obj;
        if (this.favourite != music.favourite || !Objects.equals(this.id, music.id) || !Objects.equals(this.name, music.name) || !Objects.equals(this.moduleId, music.moduleId) || !Objects.equals(this.moduleName, music.moduleName) || !Objects.equals(this.appName, music.appName) || !Objects.equals(this.parentId, music.parentId) || !Objects.equals(this.parentName, music.parentName) || !Objects.equals(this.audio, music.audio) || !Objects.equals(this.priority, music.priority) || !Objects.equals(this.video, music.video) || !Objects.equals(this.image, music.image) || !Objects.equals(this.thumbnail, music.thumbnail) || !Objects.equals(this.localFile, music.localFile) || !Objects.equals(this.artist, music.artist) || !Objects.equals(this.content, music.content) || !Objects.equals(this.duration, music.duration)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.name, this.moduleId, this.moduleName, this.appName, this.parentId, this.parentName, this.audio, this.priority, this.video, this.image, this.thumbnail, this.localFile);
    }

    @Override
    public String toString() {
        return "Music{id=" + this.id + ", name='" + this.name + '\'' + ", moduleId=" + this.moduleId + ", moduleName='" + this.moduleName + '\'' + ", appName='" + this.appName + '\'' + ", parentId=" + this.parentId + ", parentName='" + this.parentName + '\'' + ", audio='" + this.audio + '\'' + ", priority=" + this.priority + ", video='" + this.video + '\'' + ", image='" + this.image + '\'' + ", thumbnail='" + this.thumbnail + '\'' + ", localFile=" + this.localFile + ", artist='" + this.artist + '\'' + ", content='" + this.content + '\'' + ", duration='" + this.duration + '\'' + ", favourite=" + this.favourite + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        if (this.id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.id);
        }
        parcel.writeString(this.name);
        if (this.moduleId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.moduleId);
        }
        parcel.writeString(this.moduleName);
        parcel.writeString(this.appName);
        parcel.writeString(this.parentId);
        parcel.writeString(this.parentName);
        parcel.writeString(this.audio);
        if (this.priority == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.priority);
        }
        parcel.writeString(this.video);
        parcel.writeString(this.image);
        parcel.writeString(this.thumbnail);
        Boolean bool = this.localFile;
        if (bool != null) {
            i2 = bool ? 1 : 2;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.artist);
        parcel.writeString(this.content);
        parcel.writeString(this.duration);
        parcel.writeString(this.license);
        parcel.writeString(this.country);
        parcel.writeByte(this.favourite ? (byte) 1 : 0);
        parcel.writeInt(this.type);
        parcel.writeString(this.fileName);
    }
}
