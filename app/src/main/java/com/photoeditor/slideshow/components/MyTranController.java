package com.photoeditor.slideshow.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.customView.recyclical.RecyclicalKt;
//import com.photoeditor.slideshow.customView.recyclical.datasource.DataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSourceKt;
//import com.photoeditor.slideshow.dialog.DialogDownloadDetail;
import com.photoeditor.slideshow.common.AppConst;
import com.photoeditor.slideshow.imagetovideo.Transition;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;
//import com.photoeditor.slideshow.interfaces.EditStickerListener;
//import com.photoeditor.slideshow.interfaces.TransitionInterface;
import com.photoeditor.slideshow.models.DataCategory;
import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.models.GifTransitionViewModel;
//import com.photoeditor.slideshow.utils.ViewUtils;
//import com.photoeditor.slideshow.viewmodel.DialogViewModel;
//import com.photoeditor.slideshow.viewmodel.GifTransitionViewModel;
//import com.photoeditor.slideshow.models.GifTransitionViewModel;
import com.photoeditor.slideshow.models.main.TransitionJsonModel;
import com.photoeditor.slideshow.my_slide_show.list_category_transit.CategoryTransitAdapter;
import com.photoeditor.slideshow.my_slide_show.list_category_transit.TransitionAdapter;
import com.photoeditor.slideshow.my_slide_show.obj.DataCategoryTrans;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.common.BitmapResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;


public final class MyTranController {
    private final Activity activity;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public String currentIdTranSelected = "";
    /* access modifiers changed from: private */
    public GifTransition currentTransition = new GifTransition("none", "none", "classic", R.color.trans, true, (Boolean) null, Transition.NONE);
    /* access modifiers changed from: private */
//    public SelectableDataSource<Object> dataSourceTran = SelectableDataSourceKt.emptySelectableDataSource();
//    private EditStickerListener editStickerListener;
    /* access modifiers changed from: private */
    public boolean isHaveCategory;
    /* access modifiers changed from: private */
    public boolean isModeTran;
    private boolean isPremium;
    private boolean isUnlockMate;
    private boolean isUseTranFromTheme;
    //    private final CompletableJob jobLoadBitmap;
//    private Job jobRandom;
    /* access modifiers changed from: private */
    public ArrayList<DataCategory> listCategory = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<GifTransition> listTransition;
    private long mLastClickTime;
    /* access modifiers changed from: private */
    public final VideoMaker mVideoMaker;
    /* access modifiers changed from: private */
    public String moduleId = "";
    private GifTransition oldTransition = new GifTransition("none", "none", "classic", R.color.trans, true, (Boolean) null, Transition.NONE);
    /* access modifiers changed from: private */
    public String parentId = "classic";
    /* access modifiers changed from: private */
    public final RecyclerView rcvTabTrans;
    public final RecyclerView rcvListTran;
    /* access modifiers changed from: private */
//    public final TabTranIndicator tabTran;
    //    private TransitionInterface transitionInterface;
    /* access modifiers changed from: private */
//    public final GifTransitionViewModel transitionViewModel;
    /* access modifiers changed from: private */
//    public final View viewEmpty;

    private List<DataCategoryTrans> dataCategoryTransList;
    private List<GifTransition> transitList;


    public interface TransitionDownloadInterface {
        void downloadSuccess(GifTransition gifTransition);
    }

    public static final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Transition.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Transition.RANDOM_JSON.ordinal()] = 1;
            iArr[Transition.RANDOM_DRAW.ordinal()] = 2;
            iArr[Transition.DRAW.ordinal()] = 3;
            iArr[Transition.IMAGE.ordinal()] = 4;
        }
    }

    public MyTranController(GifTransition transit,
            /*GifTransitionViewModel gifTransitionViewModel,*/ LifecycleOwner lifecycleOwner,
                            Context context2, RecyclerView recyclerView, RecyclerView rcvListTrans,
                            VideoMaker videoMaker/*, View view*/, Activity activity2) {


//        final DialogViewModel dialogViewModel2 = dialogViewModel;
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        Context context3 = context2;
//        RecyclerView recyclerView2 = recyclerView;
//        TabTranIndicator tabTranIndicator2 = tabTranIndicator;
//        View view2 = view;
//        this.transitionViewModel = gifTransitionViewModel;
        this.context = context3;
        this.dataCategoryTransList = dataCategoryTransList;
        this.rcvTabTrans = recyclerView;
        this.rcvListTran = rcvListTrans;
        this.mVideoMaker = videoMaker;
//        this.viewEmpty = view2;
        this.activity = activity2;
        this.currentTransition = transit;
        if (currentTransition != null) {
            this.oldTransition = transit;
            if (transit.getId() != null) {
                String id = transit.getId();
                this.currentIdTranSelected = id;
            }
        }
//        gifTransitionViewModel.getListGifCategory().observe(lifecycleOwner2, new Observer<ArrayList<DataCategory>>() {
//            public final void onChanged(final ArrayList<DataCategory> arrayList) {
//                isHaveCategory = true;
//                listCategory = arrayList;
//                tabTran.addTabsFromUrl(listCategory);
//                tabTran.setVisibility(View.VISIBLE);
//                tabTran.display();
//                tabTran.setListener(new TabTranIndicator.OnTabListener() {
//                    public void onTabChanged(int i) {
//                    }
//
//                    public void onTabClicked(int i) {
//                        tabTran.setCurrentTab(i);
////                        dataSourceTran.clear();
//                        parentId = listCategory.get(i).getId();
//                        moduleId = listCategory.get(i).getModuleId();
//                        transitionViewModel.getDataGif(parentId, moduleId);
//                    }
//                });
//                tabTran.setCurrentTab(0);
////                dataSourceTran.clear();
//                parentId = "Classic";
//                transitionViewModel.getDataGif(parentId, moduleId);
//            }
//        });
//        gifTransitionViewModel.getListGifTransition().observe(lifecycleOwner2, new Observer<ArrayList<GifTransition>>(this) {
//
//            public final void onChanged(ArrayList<GifTransition> arrayList) {
//                if (isModeTran) {
//                    ViewUtils.INSTANCE.runLayoutAnimation(rcvTabTrans);
//                    listTransition = arrayList;
//                    Intrinsics.checkNotNullExpressionValue(arrayList, "it");
//                    DataSource.DefaultImpls.set$default(dataSourceTran, arrayList, (Function2) null, (Function2) null, 6, (Object) null);
//                }
//            }
//        });
//        gifTransitionViewModel.isShowProgress().observe(lifecycleOwner2, new Observer<Boolean>(this) {
//            final /* synthetic */ MyTranController this$0;
//
//            {
//                this.this$0 = r1;
//            }
//
//            public final void onChanged(Boolean bool) {
//                Intrinsics.checkNotNullExpressionValue(bool, "it");
//                if (bool.booleanValue()) {
//                    DialogDownloadDetail.Companion.getInstance().showDialog(context, dialogViewModel2);
//                } else {
//                    DialogDownloadDetail.Companion.getInstance().hideDialog();
//                }
//            }
//        });
//        try {
//            gifTransitionViewModel.getProgress().observe(lifecycleOwner2, C25314.INSTANCE);
//        } catch (Exception unused) {
//        }
        initRecycleTran();
//        this.jobRandom = JobKt.Job$default((Job) null, 1, (Object) null);
//        this.jobLoadBitmap = JobKt.Job$default((Job) null, 1, (Object) null);
    }

    TransitionAdapter transitionAdapter;

    private void initRecycleTran() {
        dataCategoryTransList = initListCategoryTran();
        CategoryTransitAdapter adapter = new CategoryTransitAdapter(context, dataCategoryTransList, this);
        rcvTabTrans.setAdapter(adapter);

        transitList = initListTransit();
        transitionAdapter = new TransitionAdapter(context, transitList, this);
        rcvListTran.setAdapter(transitionAdapter);
    }


    public void updateListTransit(String name) {
        transitList.clear();
        if (name.equalsIgnoreCase("Mate")) {
            transitList.add(new GifTransition("Column1", "Column1", "Special", R.drawable.ramdom, true, (Boolean) null, Transition.COLUMN1));
            transitList.add(new GifTransition("Column2", "Column2", "Special", R.drawable.ramdom, true, (Boolean) null, Transition.COLUMN2));
            transitList.add(new GifTransition("Triangle", "Triangle", "Special", R.drawable.ramdom, true, null, Transition.TRIANGLE));
            transitList.add(new GifTransition("CIRCLE", "CIRCLE", "Special", R.drawable.ramdom, true, null, Transition.CIRCLE));
            transitList.add(new GifTransition("BOARD", "BOARD", "Special", R.drawable.ramdom, true, null, Transition.BOARD));
            transitList.add(new GifTransition("BLIND_H", "BLIND_H", "Special", R.drawable.ramdom, true, null, Transition.BLIND_H));
            transitList.add(new GifTransition("BLIND_V", "BLIND_V", "Special", R.drawable.ramdom, true, null, Transition.BLIND_V));
            transitList.add(new GifTransition("DISSOLVE", "DISSOLVE", "Special", R.drawable.ramdom, true, null, Transition.DISSOLVE));
        } else if (name.equalsIgnoreCase("Brush")) {
            transitList.add(new GifTransition("BRUSH 1", "Brush 1", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 1"));
            transitList.add(new GifTransition("BRUSH 2", "Brush 2", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON()  + "Arrow 1"));
            transitList.add(new GifTransition("BRUSH 3", "Brush 3", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON()));
            transitList.add(new GifTransition("BRUSH 4", "Brush 4", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON()));
            transitList.add(new GifTransition("BRUSH 5", "Brush 5", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON()));
            transitList.add(new GifTransition("BRUSH 6", "Brush 6", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON()));
            transitList.add(new GifTransition("BRUSH 7", "Brush 7", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON()));
            transitList.add(new GifTransition("BRUSH 8", "Brush 8", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON()));
        }
        rcvListTran.setAdapter(transitionAdapter);
        transitionAdapter.notifyDataSetChanged();
    }


    private List<GifTransition> initListTransit() {
        List<GifTransition> transitList = new ArrayList<>();
        transitList.add(new GifTransition("Clock", "Clock", "classic", R.drawable.clock, true, null, Transition.CLOCK));
        transitList.add(new GifTransition("Slide L", "Slide L", "classic", R.drawable.slide_l, true, null, Transition.SLIDE_LEFT));
        transitList.add(new GifTransition("Slide R", "Slide R", "classic", R.drawable.slide_r, true, null, Transition.SLIDE_RIGHT));
        transitList.add(new GifTransition("Slide D", "Slide D", "classic", R.drawable.slide_d, true, null, Transition.SLIDE_DOWN));
        transitList.add(new GifTransition("Slide U", "Slide U", "classic", R.drawable.slide_u, true, null, Transition.SLIDE_UP));
        transitList.add(new GifTransition("Flash B", "Flash B", "classic", R.drawable.flash_b, true, null, Transition.FLASH_B));
        transitList.add(new GifTransition("Flash W", "Flash W", "classic", R.drawable.flash_w, true, (Boolean) null, Transition.FLASH_W));
        transitList.add(new GifTransition("Zoom", "Zoom", "classic", R.drawable.zoom, true, (Boolean) null, Transition.ZOOM));
        transitList.add(new GifTransition("Fade", "Fade", "classic", R.drawable.fade, true, (Boolean) null, Transition.FADE));
        return transitList;
    }

    private List<DataCategoryTrans> initListCategoryTran() {
        List<DataCategoryTrans> dataCategoryTransList = new ArrayList<>();
        dataCategoryTransList.add(new DataCategoryTrans("Classic"));
        dataCategoryTransList.add(new DataCategoryTrans("Mate"));
        dataCategoryTransList.add(new DataCategoryTrans("Water"));
        dataCategoryTransList.add(new DataCategoryTrans("Arrow"));
        dataCategoryTransList.add(new DataCategoryTrans("Brush"));
        dataCategoryTransList.add(new DataCategoryTrans("Valentine"));
        return dataCategoryTransList;
    }

//    public final EditStickerListener getEditStickerListener() {
//        return this.editStickerListener;
//    }
//
//    public final void setEditStickerListener(EditStickerListener editStickerListener2) {
//        this.editStickerListener = editStickerListener2;
//    }

    public final GifTransition getOldTransition() {
        return this.oldTransition;
    }

    public final void setOldTransition(GifTransition gifTransition) {
        this.oldTransition = gifTransition;
    }

    public final void setCurrentScreen(String str) {
//        Context context2 = this.context;
//        companion.setFirebaseAnalytics(instance);
//        if (MainActivity.Companion.getFirebaseAnalytics() != null && this.activity != null) {
//            MainActivity.Companion.getFirebaseAnalytics().setCurrentScreen(this.activity, str, str);
//        }
    }

//    public final void clickTran(TransitionInterface transitionInterface2) {
//        this.transitionInterface = transitionInterface2;
//        GifTransitionViewModel.getDataGifCategory$default(this.transitionViewModel, (Function1) null, 1, (Object) null);
//    }

    //    public final void clickTran() {
//        this.isModeTran = true;
//        if (!this.isHaveCategory) {
//            GifTransitionViewModel.getDataGifCategory$default(this.transitionViewModel, (Function1) null, 1, (Object) null);
//            return;
//        }
//        GifTransitionViewModel.getDataGif$default(this.transitionViewModel, this.parentId, this.moduleId, (Function0) null, 4, (Object) null);
//        RecyclerView.Adapter adapter = this.recycleTran.getAdapter();
//        if (adapter != null) {
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    private final void initRecyleTran() {
//        RecyclicalKt.setup(this.recycleTran, new MyTranController$initRecyleTran$1(this));
//    }
//
//    /* access modifiers changed from: private */
//    public final boolean canTouch() {
//        if (SystemClock.elapsedRealtime() - this.mLastClickTime < ((long) 1000)) {
//            return false;
//        }
//        this.mLastClickTime = SystemClock.elapsedRealtime();
//        return true;
//    }
//
//    /* access modifiers changed from: private */
//    public final void downloadTranPack(GifTransition gifTransition, int i) {
//        GifTransitionViewModel gifTransitionViewModel = this.transitionViewModel;
//        String parentId2 = gifTransition.getParentId();
//        Intrinsics.checkNotNullExpressionValue(parentId2, "item.parentId");
//        gifTransitionViewModel.downloadMusic(gifTransition, parentId2, new MyTranController$downloadTranPack$1(this));
//    }
//
//    public final boolean isUseTranFromTheme() {
//        return this.isUseTranFromTheme;
//    }
//
//    public final void setUseTranFromTheme(boolean z) {
//        this.isUseTranFromTheme = z;
//    }
//
//    public final void changeTransitionByTheme(GifTransition gifTransition) {
//        Intrinsics.checkNotNullParameter(gifTransition, "tranModel");
//        this.isUseTranFromTheme = true;
//        actionChangeTransition(gifTransition);
//    }
//
    public final void changeTransition(GifTransition gifTransition) {
        this.isUseTranFromTheme = false;
        actionChangeTransition(gifTransition);
    }

    private final void actionChangeTransition(GifTransition gifTransition) {
        if (this.mVideoMaker != null) {
            this.currentTransition = gifTransition;
            Transition type = gifTransition.getType();
            if (type != null) {
                if (type.ordinal() == Transition.RANDOM_JSON.ordinal()) {
                    randomTranJson(gifTransition);
                    return;
                } else if (type.ordinal() == Transition.RANDOM_DRAW.ordinal()) {
//                    randomTranDraw(gifTransition);
                    return;
                } else if (type.ordinal() == Transition.DRAW.ordinal()) {
                    getBitmapForTransition(gifTransition);
                    return;
                } else if (type.ordinal() == Transition.IMAGE.ordinal()) {
//                    applyTransitionJson(gifTransition);
                    return;
                }
            }
            this.mVideoMaker.applyTransitionNew(gifTransition);
        }
    }


    //    private final void applyTransitionJson(GifTransition gifTransition) {
//        ArrayList arrayList = new ArrayList();
//        Job.DefaultImpls.cancel$default(this.jobRandom, (CancellationException) null, 1, (Object) null);
//        this.jobRandom = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new MyTranController$applyTransitionJson$1(this, gifTransition, arrayList, (Continuation) null), 3, (Object) null);
//    }
//
    private final void randomTranJson(GifTransition gifTransition) {
        List<GifTransition> gifTransitionList = new ArrayList<>();
        Iterator<GifTransition> iterator = transitList.iterator();
        while (iterator.hasNext()) {
            GifTransition next = iterator.next();
            boolean localFile = next.getLocalFile();

            if (localFile && next.getPath() != null) {
                Log.e("ChinhNH", "randomTranJson: " + next.getPath());
                gifTransitionList.add(next);
            }
        }
        if (!gifTransitionList.isEmpty()) {
            ArrayList<TransitionJsonModel> jsonModelArrayList = new ArrayList<>();
            Iterator<GifTransition> it = gifTransitionList.iterator();
            while (it.hasNext()) {
                GifTransition gifTransition1 = it.next();
                try {
                    File file = new File(gifTransition.getPath());
                    LottieCompositionFactory.fromJsonInputStream(new FileInputStream(file),
                            file.getAbsolutePath()).addListener(obj -> {
                        jsonModelArrayList.add(new TransitionJsonModel(gifTransition, obj, 0, 0));
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }


            if (!jsonModelArrayList.isEmpty()) {
                mVideoMaker.applyRandomJsonTransition(gifTransition, jsonModelArrayList);
            }

        }
    }
//
//    private final void randomTranDraw(GifTransition gifTransition) {
//        ArrayList arrayList = new ArrayList();
//
//        Job.DefaultImpls.cancel$default(this.jobRandom, (CancellationException) null, 1, (Object) null);
//        this.jobRandom = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new MyTranController$randomTranDraw$1(this, arrayList, gifTransition, (Continuation) null), 3, (Object) null);
//    }
//
    private final void getBitmapForTransition(GifTransition gifTransition) {
//        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(this.jobLoadBitmap)), (CoroutineContext) null, (CoroutineStart) null, new MyTranController$getBitmapForTransition$1(this, gifTransition, (Continuation) null), 3, (Object) null);
        File file = new File(gifTransition.getPath());
        if (file.exists()) {
            ArrayList<Bitmap> listBitmap = getListBitmap(file);
            mVideoMaker.applyTransitionDraw(gifTransition, listBitmap);
        }
    }

    /* access modifiers changed from: private */
    public final ArrayList<Bitmap> getListBitmap(File file) {
        Tiny.BitmapCompressOptions bitmapCompressOptions = new Tiny.BitmapCompressOptions();
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        File[] listFiles = file.listFiles();
        Arrays.sort(listFiles);
        for (File source : listFiles) {
            BitmapResult compressSync = Tiny.getInstance().source(source).asBitmap().withOptions(bitmapCompressOptions).compressSync();
            Bitmap bitmap = compressSync.bitmap;
            if (bitmap != null) {
                arrayList.add(bitmap);
            }
        }
        return arrayList;
    }
//
//    public final void cancelTranNew() {
//        this.isModeTran = false;
//        if (!Intrinsics.areEqual((Object) this.currentTransition, (Object) this.oldTransition)) {
//            String id = this.oldTransition.getId();
//            this.currentIdTranSelected = id;
//            changeTransition(this.oldTransition);
//        }
//    }
//
//    public final void clearTransition() {
//        GifTransition gifTransition = new GifTransition("none", "none", "classic", R.color.trans, true, false, Transition.NONE);
//        this.currentTransition = gifTransition;
//        this.oldTransition = gifTransition;
//        changeTransition(gifTransition);
//    }
//
//    public final void doneTranNew() {
//        this.isModeTran = false;
//        GifTransition gifTransition = this.currentTransition;
//        this.oldTransition = gifTransition;
//        VideoMaker videoMaker = this.mVideoMaker;
//        if (videoMaker != null) {
//            videoMaker.setCurrentTransition(gifTransition);
//        }
//    }
//
//    public final boolean isUnlockMate() {
//        return this.isUnlockMate;
//    }
//
//    public final void setUnlockMate(boolean z) {
//        this.isUnlockMate = z;
//    }
//
//    public final boolean isPremium() {
//        return this.isPremium;
//    }
//
//    public final void setPremium(boolean z) {
//        this.isPremium = z;
//    }
}
