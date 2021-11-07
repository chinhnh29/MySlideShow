package com.photoeditor.slideshow.common;

import android.os.Environment;
import java.util.ArrayList;


public final class AppConst {
    public static final String BASE_IMAGE_URL = "http://volio-admin.s3.us-east-2.amazonaws.com/";
    public static final String BASE_PREFIX = "https://";
    public static final String CATEGORY_TRANSITION = "CATEGORY_TRANSITION";
    public static final String EVENT_HOMEGIFT = "Home_giftbox";
    public static final String EVENT_IAP = "IAP_Show";
    public static final String EVENT_POLICY_UPDATE = "Policy_Update";
    public static final String EVENT_SETTING = "Setting_dialogcheckupdate";
    public static final String EVENT_TEMPLATE = "Template_lyrics";
    public static final String EVENT_TEMPLATE_BEAT = "Template_beat";
    public static final String EVENT_TEMPLATE_NEW = "Template_New";
    public static final String EVENT_TEMPLATE_VALENTINE = "Template_valentine";
    private static final String FOLDER_JSON = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/SlideShow/.json/");
    private static final String FOLDER_LOTTIE = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/SlideShow/.themes/");
    private static final String FOLDER_MUSIC;
    private static final String FOLDER_TEMPLATE = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/SlideShow/.template/");
    private static final String FOLDER_TEMP_GIF = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/SlideShow/.gif/");
    private static final String FOLDER_TEMP_MUSIC = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/SlideShow/.music/");
    private static final String FOLDER_Video;
    public static final String FORMAT_DATE_DEFAULT = "yyyy/MM/dd";
    public static final String FORMAT_DATE_VN = "dd/MM/yyyy";
    public static final String ID_REWARD_MAIN = "ca-app-pub-8998561540057077/9661447653";
    public static final int IMAGE_PREVIEW_COUNT = 10;
    public static final AppConst INSTANCE = new AppConst();
    public static final String KEY_ARTIST = "artist_name";
    public static final String KEY_DOWNLOAD_FILE = "download_file";
    public static final String KEY_LISCENCE = "license_name";
    public static final String KEY_LIST_ALL_MUSIC = "KEY_LIST_ALL_MUSIC";
    public static final String KEY_LIST_CATE_THEME = "KEY_LIST_CATE_THEME";
    public static final String KEY_LIST_DRAFT = "KEY_LIST_DRAFT";
    public static final String KEY_LIST_IMAGE = "list_image";
    public static final String KEY_LIST_THEME = "KEY_LIST_THEME";
    public static final String KEY_LIST_UNLOCK = "KEY_LIST_UNLOCK";
    public static final String KEY_MUSIC = "music_name";
    public static final String KEY_PREMIUM = "KEY_PREMIUM";
    public static final String KEY_QUALITY = "KEY_QUALITY";
    public static final String KEY_SHOW_TERM = "KEY_SHOW_TERM";
    public static final String KEY_TAG = "tag";
    public static final String KEY_TRANSITION = "theme_transition";
    public static final String Key_Size = "Key_Size";
    public static final String ONLINE_MUSIC_FOLDER_NAME = "SlideShow";
    public static final String URL_THEME_DOWNLOAD = "http://mycat.asia/slideshow/theme_download/";
    public static final String URL_THEME_THUMB = "http://mycat.asia/slideshow/them_thumb/";
    private static int checkFirstGetIap;
    private static String current_music_item_download;
    private static String current_name_item_download = "";
    private static String current_type_download = "";
    private static String event = "";
    private static String iapFrom = "Home";
    private static boolean isFeedBackIap;
    private static boolean isPREMIUM;
    private static boolean isRate;
    private static boolean isShowDialogDraft;
//    private static ArrayList<WatermarkModel> listWatermark = new ArrayList<>();
    private static String screen = "";
    private static int typeDeepLink;
    private static String typeEvent = "";

    static {
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/SlideShow/";
        FOLDER_Video = str;
        FOLDER_MUSIC = str + "Music/";
    }

    private AppConst() {
    }

    public final boolean isRate() {
        return isRate;
    }

    public final void setRate(boolean z) {
        isRate = z;
    }

    public final boolean isShowDialogDraft() {
        return isShowDialogDraft;
    }

    public final void setShowDialogDraft(boolean z) {
        isShowDialogDraft = z;
    }

    public final String getEvent() {
        return event;
    }

    public final void setEvent(String str) {
        event = str;
    }

    public final String getScreen() {
        return screen;
    }

    public final void setScreen(String str) {
        screen = str;
    }

    public final String getTypeEvent() {
        return typeEvent;
    }

    public final void setTypeEvent(String str) {
        typeEvent = str;
    }

    public final int getTypeDeepLink() {
        return typeDeepLink;
    }

    public final void setTypeDeepLink(int i) {
        typeDeepLink = i;
    }

    public final boolean isPREMIUM() {
        return isPREMIUM;
    }

    public final void setPREMIUM(boolean z) {
        isPREMIUM = z;
    }

    public final int getCheckFirstGetIap() {
        return checkFirstGetIap;
    }

    public final void setCheckFirstGetIap(int i) {
        checkFirstGetIap = i;
    }

//    public final ArrayList<WatermarkModel> getListWatermark() {
//        return listWatermark;
//    }

//    public final void setListWatermark(ArrayList<WatermarkModel> arrayList) {
//        listWatermark = arrayList;
//    }

    public final String getFOLDER_Video() {
        return FOLDER_Video;
    }

    public final String getFOLDER_MUSIC() {
        return FOLDER_MUSIC;
    }

    public final String getFOLDER_TEMP_MUSIC() {
        return FOLDER_TEMP_MUSIC;
    }

    public final String getFOLDER_TEMP_GIF() {
        return FOLDER_TEMP_GIF;
    }

    public final String getFOLDER_JSON() {
        return FOLDER_JSON;
    }
    public final String getFOLDER_LOTTIE() {
        return FOLDER_LOTTIE;
    }

    public final String getFOLDER_TEMPLATE() {
        return FOLDER_TEMPLATE;
    }

    public final String getCurrent_type_download() {
        return current_type_download;
    }

    public final void setCurrent_type_download(String str) {
        current_type_download = str;
    }

    public final String getCurrent_name_item_download() {
        return current_name_item_download;
    }

    public final void setCurrent_name_item_download(String str) {
        current_name_item_download = str;
    }

    public final String getCurrent_music_item_download() {
        return current_music_item_download;
    }

    public final void setCurrent_music_item_download(String str) {
        current_music_item_download = str;
    }

    public final boolean isFeedBackIap() {
        return isFeedBackIap;
    }

    public final void setFeedBackIap(boolean z) {
        isFeedBackIap = z;
    }

    public final String getIapFrom() {
        return iapFrom;
    }

    public final void setIapFrom(String str) {
        iapFrom = str;
    }
}
