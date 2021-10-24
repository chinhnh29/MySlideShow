package com.photoeditor.slideshow.common;

import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.imagetovideo.Transition;
import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.models.ItemShare;
//import com.photoeditor.slideshow.models.TimerModel;
//import com.photoeditor.slideshow.models.music.Music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



public final class MyData {
    public static final MyData INSTANCE;
//    private static Music currentMusicPlaying;
    private static boolean isShowAnim;
    private static final ArrayList<GifTransition> listGifTran = (ArrayList<GifTransition>) Arrays.asList(
            new GifTransition("Clock", "Clock", "classic", R.drawable.clock, true,  null, Transition.CLOCK),
            new GifTransition("Slide L", "Slide L", "classic", R.drawable.slide_l, true,  null, Transition.SLIDE_LEFT),
            new GifTransition("Slide R", "Slide R", "classic", R.drawable.slide_r, true,  null, Transition.SLIDE_RIGHT),
            new GifTransition("Slide D", "Slide D", "classic", R.drawable.slide_d, true,  null, Transition.SLIDE_DOWN),
            new GifTransition("Slide U", "Slide U", "classic", R.drawable.slide_u, true,  null, Transition.SLIDE_UP),
            new GifTransition("Flash B", "Flash B", "classic", R.drawable.flash_b, true,  null, Transition.FLASH_B),
            new GifTransition("Flash W", "Flash W", "classic", R.drawable.flash_w, true, (Boolean) null, Transition.FLASH_W),
            new GifTransition("Zoom", "Zoom", "classic", R.drawable.zoom, true, (Boolean) null, Transition.ZOOM),
            new GifTransition("Fade", "Fade", "classic", R.drawable.fade, true, (Boolean) null, Transition.FADE));

    private static final ArrayList<GifTransition> listGifTranSpecial1 = (ArrayList<GifTransition>) Arrays.asList(
            new GifTransition("Column1", "Column1", "Special", R.drawable.ramdom, true, (Boolean) null, Transition.TEST2),
            new GifTransition("Column2", "Column2", "Special", R.drawable.ramdom, true, (Boolean) null, Transition.TEST),
            new GifTransition("Triangle", "Triangle", "Special", R.drawable.ramdom, true, true, Transition.TEST1),
            new GifTransition("CIRCLE", "CIRCLE", "Special", R.drawable.ramdom, true, true, Transition.CIRCLE),
            new GifTransition("BOARD", "BOARD", "Special", R.drawable.ramdom, true, true, Transition.BOARD),
            new GifTransition("BLIND_H", "BLIND_H", "Special", R.drawable.ramdom, true, true, Transition.BLIND_H));
//    private static final ArrayList<ItemShare> listShare = CollectionsKt.arrayListOf(
//            new ItemShare("Instagram", R.drawable.icon_share_instagram),
//            new ItemShare("Instagram story", R.drawable.icon_share_instagram_story),
//            new ItemShare("Facebook", R.drawable.icon_share_facebook),
//            new ItemShare("Messenger", R.drawable.icon_share_message),
//            new ItemShare("Stories facebook", R.drawable.icon_share_stories_facebook),
//            new ItemShare("Tiktok", R.drawable.ic_share_tiktok),
//            new ItemShare("Gmail", R.drawable.icon_share_gmail),
//            new ItemShare("Twitter", R.drawable.icon_share_twitter),
//            new ItemShare("Whatsapp", R.drawable.icon_share_whatsapp),
//            new ItemShare("More", R.drawable.icon_share_more));
//    private static final ArrayList<TimerModel> listTimer;

    static {
        MyData myData = new MyData();
        INSTANCE = myData;
//        listTimer = myData.getListTimer2();
    }

    private MyData() {
    }

//    public final Music getCurrentMusicPlaying() {
//        return currentMusicPlaying;
//    }

//    public final void setCurrentMusicPlaying(Music music) {
//        currentMusicPlaying = music;
//    }

    public final boolean isShowAnim() {
        return isShowAnim;
    }

    public final void setShowAnim(boolean z) {
        isShowAnim = z;
    }

    public final ArrayList<GifTransition> getListGifTran() {
        return listGifTran;
    }

    public final ArrayList<GifTransition> getListGifTranSpecial1() {
        return listGifTranSpecial1;
    }

//    public final ArrayList<ItemShare> getListShare() {
//        return listShare;
//    }

//    public final ArrayList<TimerModel> getListTimer() {
//        return listTimer;
//    }

//    private final ArrayList<TimerModel> getListTimer2() {
//        ArrayList<TimerModel> arrayList = new ArrayList<>();
//        for (int i = 3; i <= 12; i++) {
//            arrayList.add(new TimerModel(i));
//        }
//        return arrayList;
//    }
}
