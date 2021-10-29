package com.photoeditor.slideshow.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.models.Data;
import com.photoeditor.slideshow.models.DataModule;
import com.photoeditor.slideshow.models.DataNew;
import com.photoeditor.slideshow.models.GifSticker;
import com.photoeditor.slideshow.utils.GifUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.google.android.exoplayer2.util.MimeTypes;
//import com.photoeditor.slideshow.models.DataCategory;


public final class GifViewModel extends AndroidViewModel {
    public static final String KEY_GIF_CATEGORY = "key_gif_category";
    public static final String KEY_GIF_PARENT = "key_gif_parent";
    private final MutableLiveData<GifSticker> _chooseGif = new MutableLiveData<>();
//    public final MutableLiveData<ArrayList<DataCategory>> _listGifCategory = new MutableLiveData<>();
    public final MutableLiveData<Integer> _progress = new MutableLiveData<>();
    private final HashMap<String, MutableLiveData<ArrayList<GifSticker>>> hashMapListGif = new HashMap<>();
    private final GifUtils musicUtils = new GifUtils();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GifViewModel(Application application) {
        super(application);
    }
//
//    public final MutableLiveData<ArrayList<DataCategory>> getListGifCategory() {
//        return this._listGifCategory;
//    }

    public final MutableLiveData<GifSticker> getChooseGif() {
        return this._chooseGif;
    }

    public final MutableLiveData<Integer> getProgress() {
        return this._progress;
    }

    public final void getDataGifCategory() {
//        ArrayList listCategory = Hawk.get(KEY_GIF_CATEGORY, new ArrayList());
//        if (listCategory.size() > 0) {
//            this._listGifCategory.postValue(listCategory);
//        }
//
//        Data<DataNew> data;
////        DataModule<DataNew> body = response.body();
//        List<DataNew> results = (body == null || (data = body.getData()) == null) ? null : data.getResults();
//        ArrayList arrayList = new ArrayList();
//        if (results != null) {
//            for (DataNew dataNew : results) {
//                Gson gson = this.this$0.gson;
//                Category category = (Category) gson.fromJson(dataNew.getContent(), Category.class);
//                String valueOf = String.valueOf(dataNew.getId().intValue());
//                DataCategory dataCategory = new DataCategory(valueOf, category.getName());
//                if (category.getName() == null) {
//                    dataCategory.setName("null");
//                }
//                arrayList.add(dataCategory);
//            }
//        }
//        this.$result.invoke(arrayList);

    }

    public final MutableLiveData<ArrayList<GifSticker>> getListGif(String str) {
        MutableLiveData<ArrayList<GifSticker>> mutableLiveData = this.hashMapListGif.get(str);
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        MutableLiveData<ArrayList<GifSticker>> mutableLiveData2 = new MutableLiveData<>();
        this.hashMapListGif.put(str, mutableLiveData2);
        return mutableLiveData2;
    }

    public final void getDataGif(String str) {
        MutableLiveData<ArrayList<GifSticker>> mutableLiveData = this.hashMapListGif.get(str);
        this.hashMapListGif.put(str, mutableLiveData);
        ArrayList arrayList = Hawk.get(KEY_GIF_PARENT + str, new ArrayList());
        if (arrayList.size() > 0) {
            mutableLiveData.postValue(arrayList);
        }
//        this.musicUtils.getGif(str, new GifViewModel$getDataGif$1(str, objectRef));
    }

    public final void downloadMusic(GifSticker gifSticker, String str) {
//        Intrinsics.checkNotNullParameter(gifSticker, "gifSticker");
//        Intrinsics.checkNotNullParameter(str, "parentId");
//        this.musicUtils.downloadMp3(gifSticker, new GifViewModel$downloadMusic$1(this, str));
    }

    public final void setChooseGif(GifSticker gifSticker) {
        this._chooseGif.postValue(gifSticker);
    }

    /* access modifiers changed from: private */
    public final void updateListGif(GifSticker gifSticker, String str) {
//        MutableLiveData mutableLiveData = this.hashMapListGif.get(str);
//        if (mutableLiveData == null) {
//            mutableLiveData = new MutableLiveData();
//            this.hashMapListGif.put(str, mutableLiveData);
//        }
//        ArrayList arrayList = (ArrayList) mutableLiveData.getValue();
//        if (arrayList != null) {
//            ArrayList arrayList2 = new ArrayList();
//            Iterator it = arrayList.iterator();
//            while (it.hasNext()) {
//                GifSticker gifSticker2 = (GifSticker) it.next();
//                Intrinsics.checkNotNullExpressionValue(gifSticker2, "data");
//                if (Intrinsics.areEqual((Object) gifSticker2.getId(), (Object) gifSticker.getId())) {
//                    arrayList2.add(gifSticker);
//                } else {
//                    arrayList2.add(gifSticker2);
//                }
//            }
//            mutableLiveData.postValue(arrayList2);
//        }
    }


    public static final class Companion {
        private Companion() {
        }

        public final String getKEY_GIF_CATEGORY() {
            return GifViewModel.KEY_GIF_CATEGORY;
        }

        public final String getKEY_GIF_PARENT() {
            return GifViewModel.KEY_GIF_PARENT;
        }
    }
}
