//package com.photoeditor.slideshow.models;
//
//import android.app.Application;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.MutableLiveData;
//import com.google.android.exoplayer2.util.MimeTypes;
//import com.orhanobut.hawk.Hawk;
//import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.common.MyData;
//import com.photoeditor.slideshow.components.MyTranController;
//import com.photoeditor.slideshow.imagetovideo.Transition;
//import com.photoeditor.slideshow.models.DataCategory;
//import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.utils.GifTransitionUtils;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import kotlin.Metadata;
//import kotlin.Unit;
//import kotlin.collections.CollectionsKt;
//import kotlin.jvm.functions.Function0;
//import kotlin.jvm.functions.Function1;
//import kotlin.jvm.internal.Intrinsics;
//
//
//public final class GifTransitionViewModel extends AndroidViewModel {
//    /* access modifiers changed from: private */
//    public final MutableLiveData<Boolean> _isShowProgress;
//    /* access modifiers changed from: private */
//    public final MutableLiveData<ArrayList<DataCategory>> _listGifCategory = new MutableLiveData<>();
//    /* access modifiers changed from: private */
//    public final MutableLiveData<ArrayList<GifTransition>> _listGifTransition = new MutableLiveData<>();
//    /* access modifiers changed from: private */
//    public final MutableLiveData<Integer> _progress;
//    private String currentId;
//    /* access modifiers changed from: private */
//    public final HashMap<String, ArrayList<GifTransition>> hashMapListGif;
//    private final GifTransitionUtils musicUtils = new GifTransitionUtils();
//
//    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
//    public GifTransitionViewModel(Application application) {
//        super(application);
//        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
//        HashMap<String, ArrayList<GifTransition>> hashMap = new HashMap<>();
//        this.hashMapListGif = hashMap;
//        this._progress = new MutableLiveData<>();
//        this._isShowProgress = new MutableLiveData<>();
//        this.currentId = "";
//        ArrayList arrayListOf = CollectionsKt.arrayListOf(new GifTransition("none", "none", "classic", R.color.trans, 1, (Boolean) null, Transition.NONE), new GifTransition("RandomClassic", "Random", "classic", R.drawable.ic_random, 1, (Boolean) null, Transition.RANDOM_CLASSIC));
//        arrayListOf.addAll(MyData.INSTANCE.getListGifTran());
//        hashMap.put("Classic", arrayListOf);
//        ArrayList arrayListOf2 = CollectionsKt.arrayListOf(new GifTransition("none", "none", "Special", R.color.trans, 1, (Boolean) null, Transition.NONE), new GifTransition("RandomMate", "Random", "Special", R.drawable.ic_random, 1, (Boolean) null, Transition.RANDOM_MATE));
//        arrayListOf2.addAll(MyData.INSTANCE.getListGifTranSpecial1());
//        hashMap.put("Test", arrayListOf2);
//    }
//
//    public final MutableLiveData<ArrayList<DataCategory>> getListGifCategory() {
//        return this._listGifCategory;
//    }
//
//    public final MutableLiveData<ArrayList<GifTransition>> getListGifTransition() {
//        return this._listGifTransition;
//    }
//
//    public final MutableLiveData<Integer> getProgress() {
//        return this._progress;
//    }
//
//    public final MutableLiveData<Boolean> isShowProgress() {
//        return this._isShowProgress;
//    }
//
//    public static /* synthetic */ void getDataGifCategory$default(GifTransitionViewModel gifTransitionViewModel, Function1 function1, int i, Object obj) {
//        if ((i & 1) != 0) {
//            function1 = null;
//        }
//        gifTransitionViewModel.getDataGifCategory(function1);
//    }
//
//    public final void getDataGifCategory(Function1<? super List<? extends DataCategory>, Unit> function1) {
//        this.musicUtils.getCategoryTransitionJson(new GifTransitionViewModel$getDataGifCategory$1(this, function1));
//    }
//
//    /* access modifiers changed from: private */
//    public final ArrayList<DataCategory> addLocalCategory(List<? extends DataCategory> list) {
//        ArrayList<DataCategory> arrayList = new ArrayList<>();
//        arrayList.add(new DataCategory("Classic", "Classic"));
//        arrayList.add(new DataCategory("Test", "Mate"));
//        Collection collection = list;
//        if (!(collection == null || collection.isEmpty())) {
//            arrayList.addAll(collection);
//        }
//        return arrayList;
//    }
//
//    public static  void getDataGif$default(GifTransitionViewModel gifTransitionViewModel, String str, String str2, Function0 function0, int i, Object obj) {
//        if ((i & 4) != 0) {
//            function0 = null;
//        }
//        gifTransitionViewModel.getDataGif(str, str2, function0);
//    }
//
//    public final void getDataGif(String str, String str2, Function0<Unit> function0) {
//        this.currentId = str;
//        ArrayList arrayList = this.hashMapListGif.get(str);
//        if (arrayList != null) {
//            this._listGifTransition.postValue(arrayList);
//            if (function0 != null) {
//                function0.invoke();
//                return;
//            }
//            return;
//        }
//        ArrayList arrayList2 = (ArrayList) Hawk.get("TRANSITION" + str, new ArrayList());
//        this.hashMapListGif.put(str, arrayList2);
//        this.musicUtils.getTransition(str, str2, new GifTransitionViewModel$getDataGif$2(this, str, function0, arrayList2));
//    }
//
//    public final void getAllDataGif(Function1<? super HashMap<String, ArrayList<GifTransition>>, Unit> function1) {
//        getDataGifCategory(new GifTransitionViewModel$getAllDataGif$1(this, function1));
//    }
//
//    public final void downloadMusic(GifTransition gifTransition, String str, MyTranController.TransitionDownloadInterface transitionDownloadInterface) {
//        Intrinsics.checkNotNullParameter(gifTransition, "gifSticker");
//        Intrinsics.checkNotNullParameter(str, "parentId");
//        this._isShowProgress.setValue(true);
//        this.musicUtils.downloadTransition(gifTransition, new GifTransitionViewModel$downloadMusic$1(this, str, transitionDownloadInterface, gifTransition));
//    }
//
//    public final void updateListGif(GifTransition gifTransition, String str) {
//        Intrinsics.checkNotNullParameter(gifTransition, "gifSticker");
//        Intrinsics.checkNotNullParameter(str, "parentId");
//        ArrayList arrayList = this.hashMapListGif.get(str);
//        if (arrayList == null) {
//            arrayList = new ArrayList();
//            this.hashMapListGif.put(str, arrayList);
//        }
//        ArrayList arrayList2 = new ArrayList();
//        Iterator it = arrayList.iterator();
//        while (it.hasNext()) {
//            GifTransition gifTransition2 = (GifTransition) it.next();
//            Intrinsics.checkNotNullExpressionValue(gifTransition2, "data");
//            if (Intrinsics.areEqual((Object) gifTransition2.getId(), (Object) gifTransition.getId())) {
//                arrayList2.add(gifTransition);
//            } else {
//                arrayList2.add(gifTransition2);
//            }
//        }
//        this.hashMapListGif.put(str, arrayList2);
//        if (Intrinsics.areEqual((Object) this.currentId, (Object) str)) {
//            this._listGifTransition.postValue(arrayList2);
//        }
//    }
//}
