//package com.photoeditor.slideshow.components;
//
//import android.content.Context;
//import android.media.MediaMetadataRetriever;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.gms.measurement.api.AppMeasurementSdk;
//import com.google.firebase.analytics.FirebaseAnalytics;
//import com.liulishuo.filedownloader.BaseDownloadTask;
//import com.liulishuo.filedownloader.FileDownloader;
//import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.activity.MainActivity;
//import com.photoeditor.slideshow.common.AppConst;
//import com.photoeditor.slideshow.customView.recyclical.RecyclicalKt;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSourceKt;
//import com.photoeditor.slideshow.fragment.main.MainFragment;
//import com.photoeditor.slideshow.imagetovideo.GlobalDef;
//import com.photoeditor.slideshow.imagetovideo.VideoMaker;
//import com.photoeditor.slideshow.interfaces.EditStickerListener;
//import com.photoeditor.slideshow.java.PhotorThread;
//import com.photoeditor.slideshow.java.PhotorTool;
//import com.photoeditor.slideshow.models.LocalSong;
//import com.photoeditor.slideshow.models.ThemeModel;
//
//import net.lingala.zip4j.util.InternalZipConstants;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;
//
//import kotlin.jvm.internal.Intrinsics;
//import kotlin.text.StringsKt;
//
//
//public final class MyThemeController {
//    /* access modifiers changed from: private */
//    public final Context context;
//    private int currentThemeChoose = -1;
//    /* access modifiers changed from: private */
//    public SelectableDataSource<Object> dataSourceTheme = SelectableDataSourceKt.emptySelectableDataSource();
//    /* access modifiers changed from: private */
//    public int draft_index_theme = -1;
//    private EditStickerListener editStickerListener;
//    /* access modifiers changed from: private */
//    public final View emptyView;
//    /* access modifiers changed from: private */
//    public final MainFragment fragment;
//    /* access modifiers changed from: private */
//    public HashMap<String, Integer> hashMap = new HashMap<>();
//    private boolean isChangeTheme;
//    /* access modifiers changed from: private */
//    public boolean isChangerVideo;
//    private boolean isInit;
//    /* access modifiers changed from: private */
//    public final List<ThemeModel> listThemes;
//    /* access modifiers changed from: private */
//    public final RecyclerView mRecyclerTheme;
//    /* access modifiers changed from: private */
//    public final VideoMaker mVideoMaker;
//    private int positionThemeChoosed = -1;
//
//    public MyThemeController(RecyclerView recyclerView, Context context2, VideoMaker videoMaker, List<? extends ThemeModel> list, MainFragment mainFragment, View view) {
//        Intrinsics.checkNotNullParameter(recyclerView, "mRecyclerTheme");
//        Intrinsics.checkNotNullParameter(context2, "context");
//        Intrinsics.checkNotNullParameter(view, "emptyView");
//        this.mRecyclerTheme = recyclerView;
//        this.context = context2;
//        this.mVideoMaker = videoMaker;
//        this.listThemes = list;
//        this.fragment = mainFragment;
//        this.emptyView = view;
//    }
//
//    public final EditStickerListener getEditStickerListener() {
//        return this.editStickerListener;
//    }
//
//    public final void setEditStickerListener(EditStickerListener editStickerListener2) {
//        this.editStickerListener = editStickerListener2;
//    }
//
//    public final boolean isInit() {
//        return this.isInit;
//    }
//
//    public final void setInit(boolean z) {
//        this.isInit = z;
//    }
//
//    public final void changeModeTheme() {
//        if (!this.isInit) {
//            this.isInit = true;
//            if (this.listThemes != null) {
//                this.dataSourceTheme.add(0);
//                for (ThemeModel themeModel : this.listThemes) {
//                    this.dataSourceTheme.add(themeModel);
//                }
//            }
//            initRecycleTheme();
//        }
//    }
//
//    private final void initRecycleTheme() {
//        RecyclicalKt.setup(this.mRecyclerTheme, new MyThemeController$initRecycleTheme$1(this));
//    }
//
//    /* access modifiers changed from: private */
//    public final void cancelCurrentTheme() {
//        if (this.isChangeTheme) {
//            this.isChangeTheme = false;
//            MainFragment mainFragment = this.fragment;
//            if (mainFragment != null) {
//                mainFragment.removeCurrentMusic();
//            }
//            EditStickerListener editStickerListener2 = this.editStickerListener;
//            if (editStickerListener2 != null) {
//                editStickerListener2.stopVideo();
//            }
//            this.draft_index_theme = -1;
//            VideoMaker videoMaker = this.mVideoMaker;
//            if (videoMaker != null) {
//                videoMaker.removeTheme();
//            }
//            VideoMaker videoMaker2 = this.mVideoMaker;
//            if (videoMaker2 != null) {
//                videoMaker2.setTheme((ThemeModel) null);
//            }
//            EditStickerListener editStickerListener3 = this.editStickerListener;
//            if (editStickerListener3 != null) {
//                editStickerListener3.restartVideo();
//            }
//        }
//    }
//
//    public final void unlockItem(String str) {
//        Intrinsics.checkNotNullParameter(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
//        List<ThemeModel> list = this.listThemes;
//        Intrinsics.checkNotNull(list);
//        for (ThemeModel next : list) {
//            if (Intrinsics.areEqual((Object) next.getThemeName(), (Object) str)) {
//                next.setIsThemePro(0);
//            }
//        }
//        RecyclerView.Adapter adapter = this.mRecyclerTheme.getAdapter();
//        if (adapter != null) {
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    public final void doneTheme() {
//        int i = this.draft_index_theme;
//        this.currentThemeChoose = i;
//        List<ThemeModel> list = this.listThemes;
//        if (list != null && i >= 0 && i < list.size()) {
//            ThemeModel themeModel = this.listThemes.get(this.currentThemeChoose);
//            if (themeModel.getThemeName() != null) {
//                MainActivity.Companion companion = MainActivity.Companion;
//                FirebaseAnalytics instance = FirebaseAnalytics.getInstance(this.context);
//                Intrinsics.checkNotNullExpressionValue(instance, "FirebaseAnalytics.getInstance(context)");
//                companion.setFirebaseAnalytics(instance);
//                MainActivity.Companion.getFirebaseAnalytics().logEvent("EditThemes_Name_" + themeModel.getThemeName() + "_Done", new Bundle());
//            }
//        }
//    }
//
//    public final void cancelTheme() {
//        int i = this.currentThemeChoose;
//        if (i <= 0) {
//            this.draft_index_theme = -1;
//            this.dataSourceTheme.deselectAll();
//            cancelCurrentTheme();
//        } else if (this.draft_index_theme != i && i > 0) {
//            this.dataSourceTheme.deselectAll();
//            this.dataSourceTheme.selectAt(this.currentThemeChoose);
//            this.draft_index_theme = this.currentThemeChoose;
//            List<ThemeModel> list = this.listThemes;
//            Intrinsics.checkNotNull(list);
//            chooseTheme(list.get(this.currentThemeChoose - 1), this.currentThemeChoose);
//        }
//    }
//
//    public final void purchaseSuccess() {
//        List<ThemeModel> list = this.listThemes;
//        Intrinsics.checkNotNull(list);
//        for (ThemeModel next : list) {
//            next.setIsThemePro(0);
//            next.setShowPremium(false);
//        }
//        RecyclerView.Adapter adapter = this.mRecyclerTheme.getAdapter();
//        if (adapter != null) {
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    /* access modifiers changed from: private */
//    public final void downloadThemePack(ThemeModel themeModel, int i) {
//        if (new File(GlobalDef.PATH_FOLDER_EXTRACT_THEMES + InternalZipConstants.ZIP_FILE_SEPARATOR + themeModel.getThemeName()).exists()) {
//            List<ThemeModel> list = this.listThemes;
//            if (list != null) {
//                downloadThemeSuccess(list.get(i));
//                return;
//            }
//            return;
//        }
//        List<ThemeModel> list2 = this.listThemes;
//        if (list2 != null) {
//            ThemeModel themeModel2 = list2.get(i);
//            themeModel2.setDownloading(true);
//            RecyclerView.Adapter adapter = this.mRecyclerTheme.getAdapter();
//            if (adapter != null) {
//                adapter.notifyDataSetChanged();
//            }
//            BaseDownloadTask create = FileDownloader.getImpl().create(AppConst.URL_THEME_DOWNLOAD + themeModel2.getThemeDownloadUrl());
//            int start = create.setPath(GlobalDef.DEFAULT_FOLDER_OUTPUT_ZIP + themeModel2.getThemeDownloadUrl()).setListener(new MyThemeController$downloadThemePack$id$1(this, themeModel2)).start();
//            String themeName = themeModel2.getThemeName();
//            Intrinsics.checkNotNullExpressionValue(themeName, "model.themeName");
//            this.hashMap.put(themeName, Integer.valueOf(start));
//        }
//    }
//
//    /* access modifiers changed from: private */
//    public final void downloadThemeSuccess(ThemeModel themeModel) {
//        themeModel.setDownloading(false);
//        themeModel.setPercentDownload(0);
//        themeModel.setDownloaded(true);
//        RecyclerView.Adapter adapter = this.mRecyclerTheme.getAdapter();
//        if (adapter != null) {
//            adapter.notifyDataSetChanged();
//        }
//        File file = new File(GlobalDef.PATH_FOLDER_EXTRACT_THEMES + InternalZipConstants.ZIP_FILE_SEPARATOR + themeModel.getThemeName() + InternalZipConstants.ZIP_FILE_SEPARATOR + themeModel.getThemeName() + ".mp3");
//        if (file.exists()) {
//            PhotorTool.createFolder(AppConst.INSTANCE.getFOLDER_MUSIC());
//            PhotorTool.copy(file, new File(AppConst.INSTANCE.getFOLDER_MUSIC() + themeModel.getThemeName() + ".mp3"));
//        }
//        PhotorThread.getInstance().runBackground(new MyThemeController$downloadThemeSuccess$1(this, themeModel));
//    }
//
//    /* access modifiers changed from: private */
//    public final void chooseTheme(ThemeModel themeModel, int i) {
//        this.isChangeTheme = true;
//        MainFragment mainFragment = this.fragment;
//        if (mainFragment != null) {
//            mainFragment.showProgressDialog(this.context.getString(R.string.text_loading_themes));
//        }
//        EditStickerListener editStickerListener2 = this.editStickerListener;
//        if (editStickerListener2 != null) {
//            editStickerListener2.stopVideo();
//        }
//        PhotorThread.getInstance().runBackground(new MyThemeController$chooseTheme$1(this, themeModel, i));
//    }
//
//    /* access modifiers changed from: private */
//    public final void changeThemeData(ThemeModel themeModel, int i) {
//        String str;
//        if (themeModel.getMyListGif() == null) {
//            PhotorThread.getInstance().runBackground(new MyThemeController$changeThemeData$1(this, i));
//        } else {
//            nextAction(themeModel);
//        }
//        LocalSong localSong = new LocalSong();
//        localSong.setSongTitle(themeModel.getNameDisplay() + ".mp3");
//        String str2 = AppConst.INSTANCE.getFOLDER_MUSIC() + themeModel.getThemeName() + ".mp3";
//        if (!new File(str2).exists()) {
//            File[] listFiles = new File(AppConst.INSTANCE.getFOLDER_Video()).listFiles();
//            if (listFiles != null) {
//                int length = listFiles.length;
//                String str3 = "a";
//                int i2 = 0;
//                while (i2 < length) {
//                    File[] listFiles2 = listFiles[i2].listFiles();
//                    if (listFiles2 != null) {
//                        int length2 = listFiles2.length;
//                        int i3 = 0;
//                        while (i3 < length2) {
//                            File file = listFiles2[i3];
//                            Intrinsics.checkNotNullExpressionValue(file, "musicName");
//                            String absolutePath = file.getAbsolutePath();
//                            Intrinsics.checkNotNullExpressionValue(absolutePath, "musicName.absolutePath");
//                            String music_download_url = themeModel.getMusic_download_url();
//                            Intrinsics.checkNotNullExpressionValue(music_download_url, "theme.music_download_url");
//                            File[] fileArr = listFiles;
//                            if (StringsKt.contains$default((CharSequence) absolutePath, (CharSequence) music_download_url, false, 2, (Object) null)) {
//                                str3 = file.getAbsolutePath();
//                                Intrinsics.checkNotNullExpressionValue(str3, "musicName.absolutePath");
//                            }
//                            i3++;
//                            listFiles = fileArr;
//                        }
//                    }
//                    i2++;
//                    listFiles = listFiles;
//                }
//                str2 = str3;
//            } else {
//                str2 = "a";
//            }
//        }
//        if (!Intrinsics.areEqual((Object) str2, (Object) "a")) {
//            localSong.setSongData(str2);
//            Uri parse = Uri.parse(str2);
//            String str4 = "";
//            try {
//                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
//                mediaMetadataRetriever.setDataSource(this.context, parse);
//                str = mediaMetadataRetriever.extractMetadata(9);
//                Intrinsics.checkNotNull(str);
//                try {
//                    mediaMetadataRetriever.release();
//                } catch (Exception unused) {
//                    str4 = str;
//                }
//            } catch (Exception unused2) {
//                str = str4;
//                localSong.setTime(str);
//                int parseInt = Integer.parseInt(str) / 1000;
//            }
//            localSong.setTime(str);
//            int parseInt2 = Integer.parseInt(str) / 1000;
//        }
//    }
//
//    /* access modifiers changed from: private */
//    public final void nextAction(ThemeModel themeModel) {
//        PhotorThread.getInstance().runBackground(new MyThemeController$nextAction$1(this, themeModel));
//    }
//}
