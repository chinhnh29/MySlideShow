package com.photoeditor.slideshow.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.customView.TabTranIndicator;
//import com.photoeditor.slideshow.customView.recyclical.RecyclicalKt;
//import com.photoeditor.slideshow.customView.recyclical.datasource.DataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSourceKt;
//import com.photoeditor.slideshow.dialog.DialogDownloadDetail;
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
import com.zxy.tiny.Tiny;
import com.zxy.tiny.common.BitmapResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    public final RecyclerView recycleTran;
    /* access modifiers changed from: private */
    public final TabTranIndicator tabTran;
//    private TransitionInterface transitionInterface;
    /* access modifiers changed from: private */
//    public final GifTransitionViewModel transitionViewModel;
    /* access modifiers changed from: private */
//    public final View viewEmpty;


    public interface TransitionDownloadInterface {
        void downloadSuccess(GifTransition gifTransition);
    }

    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Transition.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Transition.RANDOM_JSON.ordinal()] = 1;
            iArr[Transition.RANDOM_DRAW.ordinal()] = 2;
            iArr[Transition.DRAW.ordinal()] = 3;
            iArr[Transition.IMAGE.ordinal()] = 4;
        }
    }

    public MyTranController(/*DialogViewModel dialogViewModel,*/ GifTransition gifTransition,
                                                                 /*GifTransitionViewModel gifTransitionViewModel,*/ LifecycleOwner lifecycleOwner,
                            Context context2, RecyclerView recyclerView, TabTranIndicator tabTranIndicator,
                            VideoMaker videoMaker/*, View view*/, Activity activity2) {
//        final DialogViewModel dialogViewModel2 = dialogViewModel;
        GifTransition gifTransition2 = gifTransition;
//        GifTransitionViewModel gifTransitionViewModel2 = gifTransitionViewModel;
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        Context context3 = context2;
//        RecyclerView recyclerView2 = recyclerView;
//        TabTranIndicator tabTranIndicator2 = tabTranIndicator;
//        View view2 = view;
//        this.transitionViewModel = gifTransitionViewModel2;
        this.context = context3;
        this.recycleTran = recyclerView;
        this.tabTran = tabTranIndicator;
        this.mVideoMaker = videoMaker;
//        this.viewEmpty = view2;
        this.activity = activity2;
        if (gifTransition2 != null) {
            this.currentTransition = gifTransition2;
            this.oldTransition = gifTransition2;
            if (gifTransition.getId() != null) {
                String id = gifTransition.getId();
                this.currentIdTranSelected = id;
            }
        }
//        gifTransitionViewModel.getListGifCategory().observe(lifecycleOwner2, new Observer<ArrayList<DataCategory>>(this) {
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
//                        if (i < listCategory.size()) {
//                            Object obj = listCategory.get(i);
//                            try {
//                                String name = ((DataCategory) obj).getName();
//                                setCurrentScreen(name + "_View");
//                            } catch (Exception unused) {
//                            }
//                        }
//                        tabTran.setCurrentTab(i);
//                        dataSourceTran.clear();
//                        Object obj2 = arrayList.get(i);
//                        String id = ((DataCategory) obj2).getId();
//                        parentId = id;
//                        Object obj3 = arrayList.get(i);
//                        moduleId = ((DataCategory) obj3).getModuleId();
//                        GifTransitionViewModel.getDataGif$default(this$0.transitionViewModel, this$0.parentId, this$0.moduleId, (Function0) null, 4, (Object) null);
//                    }
//                });
//                tabTran.setCurrentTab(0);
//                dataSourceTran.clear();
//                parentId = "Classic";
//                GifTransitionViewModel.getDataGif$default(transitionViewModel, parentId, "", (Function0) null, 4, (Object) null);
//            }
//        });
//        gifTransitionViewModel.getListGifTransition().observe(lifecycleOwner2, new Observer<ArrayList<GifTransition>>(this) {
//            final /* synthetic */ MyTranController this$0;
//
//            {
//                this.this$0 = r1;
//            }
//
//            public final void onChanged(ArrayList<GifTransition> arrayList) {
//                if (isModeTran) {
//                    ViewUtils.INSTANCE.runLayoutAnimation(recycleTran);
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
//        initRecyleTran();
//        this.jobRandom = JobKt.Job$default((Job) null, 1, (Object) null);
//        this.jobLoadBitmap = JobKt.Job$default((Job) null, 1, (Object) null);
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
//            Transition type = gifTransition.getType();
//            if (type != null) {
//                int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
//                if (i == 1) {
//                    randomTranJson(gifTransition);
//                    return;
//                } else if (i == 2) {
//                    randomTranDraw(gifTransition);
//                    return;
//                } else if (i == 3) {
//                    getBitmapForTransition(gifTransition);
//                    return;
//                } else if (i == 4) {
//                    applyTransitionJson(gifTransition);
//                    return;
//                }
//            }
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
//        ArrayList arrayList = new ArrayList();
//        ArrayList<GifTransition> arrayList2 = this.listTransition;
//        Iterator<GifTransition> it = arrayList2.iterator();
//        while (it.hasNext()) {
//            GifTransition next = it.next();
//            Boolean localFile = next.getLocalFile();
//            if (localFile && next.getPath() != null) {
//                arrayList.add(next);
//            }
//        }
//        if (!arrayList.isEmpty()) {
//            ArrayList arrayList3 = new ArrayList();
//            Job.DefaultImpls.cancel$default(this.jobRandom, (CancellationException) null, 1, (Object) null);
//            this.jobRandom = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new MyTranController$randomTranJson$1(this, arrayList, arrayList3, gifTransition, (Continuation) null), 3, (Object) null);
//            return;
//        }
//        Toast.makeText(this.context, R.string.not_download, 0).show();
    }
//
//    private final void randomTranDraw(GifTransition gifTransition) {
//        ArrayList arrayList = new ArrayList();
//        Job.DefaultImpls.cancel$default(this.jobRandom, (CancellationException) null, 1, (Object) null);
//        this.jobRandom = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new MyTranController$randomTranDraw$1(this, arrayList, gifTransition, (Continuation) null), 3, (Object) null);
//    }
//
//    private final void getBitmapForTransition(GifTransition gifTransition) {
//        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(this.jobLoadBitmap)), (CoroutineContext) null, (CoroutineStart) null, new MyTranController$getBitmapForTransition$1(this, gifTransition, (Continuation) null), 3, (Object) null);
//    }
//
//    /* access modifiers changed from: private */
//    public final ArrayList<Bitmap> getListBitmap(File file) {
//        Tiny.BitmapCompressOptions bitmapCompressOptions = new Tiny.BitmapCompressOptions();
//        ArrayList<Bitmap> arrayList = new ArrayList<>();
//        File[] listFiles = file.listFiles();
//        Arrays.sort(listFiles);
//        for (File source : listFiles) {
//            BitmapResult compressSync = Tiny.getInstance().source(source).asBitmap().withOptions(bitmapCompressOptions).compressSync();
//            Bitmap bitmap = compressSync.bitmap;
//            if (bitmap != null) {
//                arrayList.add(bitmap);
//            }
//        }
//        return arrayList;
//    }
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
