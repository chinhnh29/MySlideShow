package com.photoeditor.slideshow.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RelativeLayout;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.photoeditor.slideshow.EditActivity;
import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.customView.recyclical.RecyclicalKt;
//import com.photoeditor.slideshow.customView.recyclical.datasource.DataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSourceKt;
//import com.photoeditor.slideshow.dialog.DialogDownloadDetail;
import com.photoeditor.slideshow.common.AppConst;
import com.photoeditor.slideshow.common.MyData;
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
import com.photoeditor.slideshow.models.main.TransitionDrawModel;
import com.photoeditor.slideshow.models.main.TransitionJsonModel;
import com.photoeditor.slideshow.my_slide_show.list_category_transit.CategoryTransitAdapter;
import com.photoeditor.slideshow.my_slide_show.list_category_transit.FrameAdapter;
import com.photoeditor.slideshow.my_slide_show.list_category_transit.FrameTabAdapter;
import com.photoeditor.slideshow.my_slide_show.list_category_transit.TransitionAdapter;
import com.photoeditor.slideshow.my_slide_show.obj.FrameTab;
import com.photoeditor.slideshow.my_slide_show.obj.DataCategoryTrans;
import com.photoeditor.slideshow.my_slide_show.obj.FrameInfo;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.common.BitmapResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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

    /* access modifiers changed from: private */
//    public final TabTranIndicator tabTran;
    //    private TransitionInterface transitionInterface;
    /* access modifiers changed from: private */
//    public final GifTransitionViewModel transitionViewModel;
    /* access modifiers changed from: private */
//    public final View viewEmpty;

    @BindView(R.id.rcv_list_tab_transit)
    RecyclerView rcvTabTrans;
    @BindView(R.id.list_transit)
    RecyclerView rcvListTran;
    @BindView(R.id.rcv_list_tab_frame)
    RecyclerView rcvTabFrame;
    @BindView(R.id.rcv_list_frame)
    RecyclerView rcvListFrame;


    private final RelativeLayout rlMenuEditSelected;

    private List<DataCategoryTrans> dataCategoryTransList;
    private List<GifTransition> transitList;
    private TransitionAdapter transitionAdapter;

    private List<FrameTab> frameTabList;
    private List<FrameInfo> frameList;
    private FrameAdapter frameAdapter;

    private MyData myData;


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

    public MyTranController(GifTransition transit, EditActivity editActivity,
            /*GifTransitionViewModel gifTransitionViewModel,*/ LifecycleOwner lifecycleOwner,
                            Context context,
                            VideoMaker videoMaker/*, View view*/) {
        this.activity = editActivity;
        rlMenuEditSelected = activity.findViewById(R.id.rl_mnu_select);
        ButterKnife.bind(this, rlMenuEditSelected);
//        final DialogViewModel dialogViewModel2 = dialogViewModel;
//        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
//        Context context3 = context2;
//        RecyclerView recyclerView2 = recyclerView;
//        TabTranIndicator tabTranIndicator2 = tabTranIndicator;
//        View view2 = view;
//        this.transitionViewModel = gifTransitionViewModel;
        this.context = context;
        this.mVideoMaker = videoMaker;
//        this.viewEmpty = view2;
        this.currentTransition = transit;
        if (currentTransition != null) {
            this.oldTransition = transit;
            if (transit.getId() != null) {
                String id = transit.getId();
                this.currentIdTranSelected = id;
            }
        }
        if (listTransition == null) listTransition = new ArrayList<>();
        myData = new MyData();
        initRecycleTran();
        initRecycleFrame();
    }

    private void initRecycleFrame() {
        frameTabList = initTabFrames();
        FrameTabAdapter adapter = new FrameTabAdapter(context, frameTabList, this);
        rcvTabFrame.setAdapter(adapter);

        frameList = initFrameList();
        frameAdapter = new FrameAdapter(context, frameList, this);
        rcvListFrame.setAdapter(frameAdapter);
    }

    private List<FrameInfo> initFrameList() {
        List<FrameInfo> frameInfoList = new ArrayList<>();
        frameInfoList.add(new FrameInfo("Frame s1", R.drawable.slide_d));
        frameInfoList.add(new FrameInfo("Frame s2", R.drawable.slide_d));
        frameInfoList.add(new FrameInfo("Frame s3", R.drawable.slide_d));
        return frameInfoList;
    }

    private List<FrameTab> initTabFrames() {
        List<FrameTab> frameTabs = new ArrayList<>();
        frameTabs.add(new FrameTab("Static"));
        frameTabs.add(new FrameTab("Dynamic"));
        return frameTabs;
    }

    public void updateListFrame(String name) {
        frameList.clear();
        if (name.equalsIgnoreCase("Static")) {
            frameList.add(new FrameInfo("Frame s1", R.drawable.slide_d));
            frameList.add(new FrameInfo("Frame s2", R.drawable.slide_d));
            frameList.add(new FrameInfo("Frame s3", R.drawable.slide_d));
        } else {
            frameList.add(new FrameInfo("Frame d1", R.drawable.slide_d));
            frameList.add(new FrameInfo("Frame d2", R.drawable.slide_d));
            frameList.add(new FrameInfo("Frame d3", R.drawable.slide_d));
        }
        frameAdapter.notifyDataSetChanged();
    }

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
        if (name.equalsIgnoreCase("Mate")) { //ok
            transitList.add(new GifTransition("none", "none", "Special", R.color.trans, true, null, Transition.NONE));
            transitList.add(new GifTransition("RandomMate", "Random", "Special", R.drawable.ic_random, true, null, Transition.RANDOM_MATE));
            transitList.add(new GifTransition("Column1", "Column1", "Special", R.drawable.ramdom, true, null, Transition.COLUMN1));
            transitList.add(new GifTransition("Column2", "Column2", "Special", R.drawable.ramdom, true, null, Transition.COLUMN2));
            transitList.add(new GifTransition("Triangle", "Triangle", "Special", R.drawable.ramdom, true, null, Transition.TRIANGLE));
            transitList.add(new GifTransition("CIRCLE", "CIRCLE", "Special", R.drawable.ramdom, true, null, Transition.CIRCLE));
            transitList.add(new GifTransition("BOARD", "BOARD", "Special", R.drawable.ramdom, true, null, Transition.BOARD));
            transitList.add(new GifTransition("BLIND_H", "BLIND_H", "Special", R.drawable.ramdom, true, null, Transition.BLIND_H));
            transitList.add(new GifTransition("BLIND_V", "BLIND_V", "Special", R.drawable.ramdom, true, null, Transition.BLIND_V));
            transitList.add(new GifTransition("DISSOLVE", "DISSOLVE", "Special", R.drawable.ramdom, true, null, Transition.DISSOLVE));
        } else if (name.equalsIgnoreCase("Brush")) {
            transitList.add(new GifTransition("RANDOM Brush", "Random", "Special",
                    R.drawable.ramdom, true, null, Transition.RANDOM_DRAW));
            transitList.add(new GifTransition("BRUSH 1", "Brush 1", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 1"));
            transitList.add(new GifTransition("BRUSH 2", "Brush 2", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 2"));
            transitList.add(new GifTransition("BRUSH 3", "Brush 3", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 3"));
            transitList.add(new GifTransition("BRUSH 4", "Brush 4", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 4"));
            transitList.add(new GifTransition("BRUSH 5", "Brush 5", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 5"));
            transitList.add(new GifTransition("BRUSH 6", "Brush 6", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 6"));
            transitList.add(new GifTransition("BRUSH 7", "Brush 7", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 7"));
            transitList.add(new GifTransition("BRUSH 8", "Brush 8", "Special",
                    R.drawable.ramdom, true, null, Transition.DRAW, AppConst.INSTANCE.getFOLDER_JSON() + "Brush 8"));
        } else if (name.equalsIgnoreCase("Json")) {
            transitList.add(new GifTransition("BRUSH 1", "Brush 1", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE() + "Happy Halloween/Video 6 P1.json"));
            transitList.add(new GifTransition("BRUSH 2", "Brush 2", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE() + "Scary night"));
            transitList.add(new GifTransition("BRUSH 3", "Brush 3", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE()));
            transitList.add(new GifTransition("BRUSH 4", "Brush 4", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE()));
            transitList.add(new GifTransition("BRUSH 5", "Brush 5", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE()));
            transitList.add(new GifTransition("BRUSH 6", "Brush 6", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE()));
            transitList.add(new GifTransition("BRUSH 7", "Brush 7", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE()));
            transitList.add(new GifTransition("BRUSH 8", "Brush 8", "Special",
                    R.drawable.ramdom, true, null, Transition.IMAGE, AppConst.INSTANCE.getFOLDER_LOTTIE()));
        }
        rcvListTran.setAdapter(transitionAdapter);
        transitionAdapter.notifyDataSetChanged();
    }


    private List<GifTransition> initListTransit() {
        List<GifTransition> transitList = new ArrayList<>();
        transitList.add(new GifTransition("none", "none", "classic", R.color.trans, true, null, Transition.NONE));
        transitList.add(new GifTransition("RandomClassic", "Random", "classic", R.drawable.ic_random, true, (Boolean) null, Transition.RANDOM_CLASSIC));
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
        dataCategoryTransList.add(new DataCategoryTrans("Json"));
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
    public final void changeTransitionByTheme(GifTransition gifTransition) {
        this.isUseTranFromTheme = true;
        actionChangeTransition(gifTransition);
    }

    public final void changeTransition(GifTransition gifTransition) {
        this.isUseTranFromTheme = false;
        actionChangeTransition(gifTransition);
    }

    private void actionChangeTransition(GifTransition gifTransition) {
        if (this.mVideoMaker != null) {
            this.currentTransition = gifTransition;
            Transition type = gifTransition.getType();
            if (type != null) {
                if (type.ordinal() == Transition.RANDOM_JSON.ordinal()) {
                    randomTranJson(gifTransition);
                    return;
                } else if (type.ordinal() == Transition.RANDOM_DRAW.ordinal()) {
                    randomTranDraw(gifTransition);
                    return;
                } else if (type.ordinal() == Transition.DRAW.ordinal()) {
                    getBitmapForTransition(gifTransition);
                    return;
                } else if (type.ordinal() == Transition.IMAGE.ordinal()) {
                    applyTransitionJson(gifTransition);
                    return;
                }
            }
            mVideoMaker.applyTransitionNew(gifTransition);
        }
    }


    private void applyTransitionJson(GifTransition gifTransition) {
        ArrayList<TransitionJsonModel> transitionJsonModelArrayList = new ArrayList<>();
        LottieResult<LottieComposition> fromJsonInputStreamSync;
        try {
            if ((fromJsonInputStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(
                    new FileInputStream(new File(gifTransition.getPath())), gifTransition.getPath())) != null) {
                LottieComposition value = fromJsonInputStreamSync.getValue();
                transitionJsonModelArrayList.add(new TransitionJsonModel(gifTransition, value, 0, 0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if ((!transitionJsonModelArrayList.isEmpty()) && mVideoMaker != null) {
            mVideoMaker.applyRandomJsonTransition(gifTransition, transitionJsonModelArrayList);
        }
    }

    private void randomTranJson(GifTransition gifTransition) {
        List<GifTransition> gifTransitionList = new ArrayList<>();
        for (GifTransition next : transitList) {
            boolean localFile = next.getLocalFile();
            if (localFile && next.getPath() != null) {
                gifTransitionList.add(next);
            }
        }
        if (!gifTransitionList.isEmpty()) {
            ArrayList<TransitionJsonModel> transitionJsonModelArrayList = new ArrayList<>();
            for (GifTransition gifTransition1 : gifTransitionList) {
                try {
                    File file = new File(gifTransition.getPath());
                    LottieCompositionFactory.fromJsonInputStream(new FileInputStream(file),
                            file.getAbsolutePath()).addListener(obj -> {
                        transitionJsonModelArrayList.add(new TransitionJsonModel(gifTransition, obj, 0, 0));
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }


            if (!transitionJsonModelArrayList.isEmpty()) {
                mVideoMaker.applyRandomJsonTransition(gifTransition, transitionJsonModelArrayList);
            }
        }
    }

    private void randomTranDraw(GifTransition gifTrans) {
        ArrayList<TransitionDrawModel> transitionDrawModelArrayList = new ArrayList<>();
        listTransition = myData.getListGifTranDrawBrush();
        new Thread(() -> {
            if (listTransition != null) {
                for (GifTransition gifTransition : listTransition) {
                    boolean localFile = gifTransition.getLocalFile();
                    if (localFile && gifTransition.getPath() != null) {
                        File file = new File(gifTransition.getPath());
                        if (file.exists()) {
                            transitionDrawModelArrayList.add(new TransitionDrawModel(gifTransition, getListBitmap(file)));
                        }
                    }
                }
                if (!transitionDrawModelArrayList.isEmpty()) {
                    mVideoMaker.applyTransitionRandomDraw(gifTrans, transitionDrawModelArrayList);
                }
            }
        }).start();
    }

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
