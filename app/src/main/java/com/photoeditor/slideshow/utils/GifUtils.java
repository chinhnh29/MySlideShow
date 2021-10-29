package com.photoeditor.slideshow.utils;

//import com.androidnetworking.AndroidNetworking;
import com.bumptech.glide.Registry;
import com.google.gson.Gson;
//import com.photoeditor.slideshow.activity.MainActivity;
//import com.photoeditor.slideshow.common.AppConst;
//import com.photoeditor.slideshow.interfaces.music.ServiceInterface;
//import com.photoeditor.slideshow.models.DataCategory;
import com.photoeditor.slideshow.models.GifSticker;
//import com.photoeditor.slideshow.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class GifUtils {
    public final Gson gson = new Gson();

    public interface DownLoadCallback {

        /* compiled from: GifUtils.kt */
        public static final class DefaultImpls {
            public static void complete(DownLoadCallback downLoadCallback, GifSticker gifSticker) {
            }

            public static void error(DownLoadCallback downLoadCallback) {
            }

            public static void progress(DownLoadCallback downLoadCallback, long j, long j2) {
            }
        }

        void complete(GifSticker gifSticker);

        void error();

        void progress(long j, long j2);
    }

//    public final void getGif(String str, Function1<? super ArrayList<GifSticker>, Unit> function1) {
////        ((ServiceInterface) RetrofitInstance.getRetrofit().create(ServiceInterface.class)).getItem("122", str).enqueue(new GifUtils$getGif$1(this, str, function1));
//    }

    /* access modifiers changed from: private */
//    public final String getNameUrl(String str) {
//        int length = str.length();
//        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
//        String substring = str.substring(lastIndexOf$default((CharSequence) str, '/', 0, false, 6, (Object) null) + 1, length);
//        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
//        return substring;
//    }

//    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
//        if ((i2 & 2) != 0) {
//            i = StringsKt.getLastIndex(charSequence);
//        }
//        if ((i2 & 4) != 0) {
//            z = false;
//        }
//        return StringsKt.lastIndexOf(charSequence, c, i, z);
//    }


//    public final void getGifCategory(Function1<? super List<? extends DataCategory>, Unit> function1) {
////        Intrinsics.checkNotNullParameter(function1, "result");
////        ((ServiceInterface) RetrofitInstance.getRetrofit().create(ServiceInterface.class)).getCategory("122").enqueue(new GifUtils$getGifCategory$1(this, function1));
//    }

    public static /* synthetic */ void downloadMp3$default(GifUtils gifUtils, GifSticker gifSticker, DownLoadCallback downLoadCallback, int i, Object obj) {
        if ((i & 2) != 0) {
            downLoadCallback = null;
        }
        gifUtils.downloadMp3(gifSticker, downLoadCallback);
    }

    public final void downloadMp3(GifSticker gifSticker, DownLoadCallback downLoadCallback) {
//        Intrinsics.checkNotNullParameter(gifSticker, "gif");
//        AppConst.INSTANCE.setCurrent_type_download(Registry.BUCKET_GIF);
//        AppConst appConst = AppConst.INSTANCE;
//        String name = gifSticker.getName();
//        Intrinsics.checkNotNullExpressionValue(name, "gif.name");
//        appConst.setCurrent_name_item_download(name);
//        MainActivity.Companion companion = MainActivity.Companion;
//        String name2 = gifSticker.getName();
//        Intrinsics.checkNotNullExpressionValue(name2, "gif.name");
//        companion.logEvent("ItemDownloadShow", Registry.BUCKET_GIF, name2);
//        AndroidNetworking.download(gifSticker.getImage(), AppConst.INSTANCE.getFOLDER_TEMP_GIF(), gifSticker.getName()).build().setDownloadProgressListener(new GifUtils$downloadMp3$1(this, downLoadCallback)).startDownload(new GifUtils$downloadMp3$2(gifSticker, downLoadCallback));
    }
}
