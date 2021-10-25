package com.photoeditor.slideshow.imagetovideo;

import android.os.Environment;
import net.lingala.zip4j.util.InternalZipConstants;

public class GlobalDef {
    public static final String DEFAULT_FOLDER_OUTPUT;
    public static final String DEFAULT_FOLDER_OUTPUT_ZIP;
    public static final String DEFAULT_MUSIC = "Bemylife.mp3";
    public static final String FOLDER_AUDIO;
    public static final String KEY_HAS_CONVERT_AUDIO = "has_convert_audio";
    public static final String KEY_NAME_PREFERENCES = "music_video_maker";
    public static final int ONE_SECOND = 1000;
    public static final String OUTPUT_FOLDER_NAME = "SlideShow";
    public static final String PATH_FOLDER_EXTRACT_THEMES;
    public static final String PATH_FOLDER_EXTRACT_TRANSITION;
    public static final int QUALITY_VIDEO_1080 = 1088;
    public static final int QUALITY_VIDEO_720 = 720;
    public static final String TEMP_FOLDER;
    public static boolean isShowRate = false;
    public static boolean isShowtoday = false;

    static {
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + InternalZipConstants.ZIP_FILE_SEPARATOR + "SlideShow";
        DEFAULT_FOLDER_OUTPUT = str;
        PATH_FOLDER_EXTRACT_TRANSITION = str + "/.trans";
        PATH_FOLDER_EXTRACT_THEMES = str + "/.themes";
        DEFAULT_FOLDER_OUTPUT_ZIP = str + "/.zip/";
        TEMP_FOLDER = str + "/.mvm_temp";
        FOLDER_AUDIO = str + "/.Audio/";
    }
}