package com.photoeditor.slideshow.common;

import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.imagetovideo.Transition;
import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.models.ItemShare;
//import com.photoeditor.slideshow.models.TimerModel;
//import com.photoeditor.slideshow.models.music.Music;

import java.util.ArrayList;
import java.util.List;


public final class MyData {
    public static final MyData INSTANCE;
    //    private static Music currentMusicPlaying;
    private static boolean isShowAnim;
    private List<GifTransition> listGifTran;


    private ArrayList<GifTransition> listGifTranSpecial1;
    private ArrayList<GifTransition> listGifTranDrawBrush;
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

    public MyData() {
        listGifTran = new ArrayList<>();
        listGifTran.add(new GifTransition("Clock", "Clock", "classic", R.drawable.clock, true, null, Transition.CLOCK));
        listGifTran.add(new GifTransition("Slide Left", "Slide Left", "classic", R.drawable.slide_l, true, null, Transition.SLIDE_LEFT));
        listGifTran.add(new GifTransition("Slide Right", "Slide Right", "classic", R.drawable.slide_r, true, null, Transition.SLIDE_RIGHT));
        listGifTran.add(new GifTransition("Slide Down", "Slide Down", "classic", R.drawable.slide_d, true, null, Transition.SLIDE_DOWN));
        listGifTran.add(new GifTransition("Slide Up", "Slide Up", "classic", R.drawable.slide_u, true, null, Transition.SLIDE_UP));
        listGifTran.add(new GifTransition("Flash Black", "Flash B", "classic", R.drawable.flash_b, true, null, Transition.FLASH_B));
        listGifTran.add(new GifTransition("Flash White", "Flash W", "classic", R.drawable.flash_w, true, (Boolean) null, Transition.FLASH_W));
        listGifTran.add(new GifTransition("Zoom", "Zoom", "classic", R.drawable.zoom, true, (Boolean) null, Transition.ZOOM));
        listGifTran.add(new GifTransition("Fade", "Fade", "classic", R.drawable.fade, true, (Boolean) null, Transition.FADE));

        listGifTranSpecial1 = new ArrayList<>();
        listGifTranSpecial1.add(new GifTransition("Column1", "Column1", "Special", R.drawable.ramdom, true, (Boolean) null, Transition.COLUMN1));
        listGifTranSpecial1.add(new GifTransition("Column2", "Column2", "Special", R.drawable.ramdom, true, (Boolean) null, Transition.COLUMN2));
        listGifTranSpecial1.add(new GifTransition("Triangle", "Triangle", "Special", R.drawable.ramdom, true, true, Transition.TRIANGLE));
        listGifTranSpecial1.add(new GifTransition("CIRCLE", "CIRCLE", "Special", R.drawable.ramdom, true, true, Transition.CIRCLE));
        listGifTranSpecial1.add(new GifTransition("BOARD", "BOARD", "Special", R.drawable.ramdom, true, true, Transition.BOARD));
        listGifTranSpecial1.add(new GifTransition("BLIND_H", "BLIND_H", "Special", R.drawable.ramdom, true, true, Transition.BLIND_H));
        listGifTranSpecial1.add(new GifTransition("DISSOLVE", "DISSOLVE", "Special", R.drawable.ramdom, true, true, Transition.DISSOLVE));

        listGifTranDrawBrush = new ArrayList<>();
        listGifTranDrawBrush.add(new GifTransition("BRUSH 1", "Brush 1", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 1"));
        listGifTranDrawBrush.add(new GifTransition("BRUSH 2", "Brush 2", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 2"));
        listGifTranDrawBrush.add(new GifTransition("BRUSH 3", "Brush 3", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 3"));
        listGifTranDrawBrush.add(new GifTransition("BRUSH 4", "Brush 4", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 4"));
        listGifTranDrawBrush.add(new GifTransition("BRUSH 5", "Brush 5", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 5"));
        listGifTranDrawBrush.add(new GifTransition("BRUSH 6", "Brush 6", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 6"));
        listGifTranDrawBrush.add(new GifTransition("BRUSH 7", "Brush 7", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 7"));
        listGifTranDrawBrush.add(new GifTransition("BRUSH 8", "Brush 8", "Special",
                R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 8"));

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

    public  List<GifTransition> getListGifTran() {
        return listGifTran;
    }

    public final ArrayList<GifTransition> getListGifTranSpecial1() {
        return listGifTranSpecial1;
    }

    public final ArrayList<GifTransition> getListGifTranDrawBrush() {
        return listGifTranDrawBrush;
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
