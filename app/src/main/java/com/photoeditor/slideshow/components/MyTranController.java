//package com.photoeditor.slideshow.components;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.os.SystemClock;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.lifecycle.LifecycleOwner;
//import androidx.lifecycle.Observer;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.gomin.slideshowmaker.videoeditor.videomaker.musicvideo.R;
//import com.google.firebase.analytics.FirebaseAnalytics;
//import com.photoeditor.slideshow.activity.MainActivity;
//import com.photoeditor.slideshow.customView.TabTranIndicator;
//import com.photoeditor.slideshow.customView.recyclical.RecyclicalKt;
//import com.photoeditor.slideshow.customView.recyclical.datasource.DataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSourceKt;
//import com.photoeditor.slideshow.dialog.DialogDownloadDetail;
//import com.photoeditor.slideshow.imagetovideo.Transition;
//import com.photoeditor.slideshow.imagetovideo.VideoMaker;
//import com.photoeditor.slideshow.interfaces.EditStickerListener;
//import com.photoeditor.slideshow.interfaces.TransitionInterface;
//import com.photoeditor.slideshow.models.DataCategory;
//import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.utils.ViewUtils;
//import com.photoeditor.slideshow.viewmodel.DialogViewModel;
//import com.photoeditor.slideshow.viewmodel.GifTransitionViewModel;
//import com.zxy.tiny.Tiny;
//import com.zxy.tiny.common.BitmapResult;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.concurrent.CancellationException;
//
//import kotlin.Metadata;
//import kotlin.coroutines.Continuation;
//import kotlin.coroutines.CoroutineContext;
//import kotlin.jvm.functions.Function0;
//import kotlin.jvm.functions.Function1;
//import kotlin.jvm.functions.Function2;
//import kotlin.jvm.internal.Intrinsics;
//import kotlinx.coroutines.CompletableJob;
//import kotlinx.coroutines.CoroutineScopeKt;
//import kotlinx.coroutines.CoroutineStart;
//import kotlinx.coroutines.Dispatchers;
//import kotlinx.coroutines.Job;
//import kotlinx.coroutines.JobKt;
//
////@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001[B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016J\u0010\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0005H\u0002J\u0010\u0010D\u001a\u00020B2\u0006\u0010E\u001a\u00020\u0005H\u0002J\b\u0010F\u001a\u00020#H\u0002J\u0006\u0010G\u001a\u00020BJ\u000e\u0010H\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0005J\u000e\u0010I\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0005J\u0006\u0010J\u001a\u00020BJ\u0006\u0010K\u001a\u00020BJ\u0010\u0010K\u001a\u00020B2\b\u0010?\u001a\u0004\u0018\u00010@J\u0006\u0010L\u001a\u00020BJ\u0018\u0010M\u001a\u00020B2\u0006\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u00020PH\u0002J\u0010\u0010Q\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0005H\u0002J \u0010R\u001a\u0012\u0012\u0004\u0012\u00020S02j\b\u0012\u0004\u0012\u00020S`42\u0006\u0010T\u001a\u00020UH\u0002J\b\u0010V\u001a\u00020BH\u0002J\u0010\u0010W\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0005H\u0002J\u0010\u0010X\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0005H\u0002J\u000e\u0010Y\u001a\u00020B2\u0006\u0010Z\u001a\u00020\u0018R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R\u001a\u0010+\u001a\u00020#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R\u000e\u0010-\u001a\u00020.X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u000e¢\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0012\u0012\u0004\u0012\u00020302j\b\u0012\u0004\u0012\u000203`4X\u000e¢\u0006\u0002\n\u0000R\"\u00105\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u000102j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`4X\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u000e\u0010>\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006\\"}, mo49141d2 = {"Lcom/photoeditor/slideshow/components/MyTranController;", "", "dialogViewModel", "Lcom/photoeditor/slideshow/viewmodel/DialogViewModel;", "gifTransition", "Lcom/photoeditor/slideshow/models/GifTransition;", "transitionViewModel", "Lcom/photoeditor/slideshow/viewmodel/GifTransitionViewModel;", "viewLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "recycleTran", "Landroidx/recyclerview/widget/RecyclerView;", "tabTran", "Lcom/photoeditor/slideshow/customView/TabTranIndicator;", "mVideoMaker", "Lcom/photoeditor/slideshow/imagetovideo/VideoMaker;", "viewEmpty", "Landroid/view/View;", "activity", "Landroid/app/Activity;", "(Lcom/photoeditor/slideshow/viewmodel/DialogViewModel;Lcom/photoeditor/slideshow/models/GifTransition;Lcom/photoeditor/slideshow/viewmodel/GifTransitionViewModel;Landroidx/lifecycle/LifecycleOwner;Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;Lcom/photoeditor/slideshow/customView/TabTranIndicator;Lcom/photoeditor/slideshow/imagetovideo/VideoMaker;Landroid/view/View;Landroid/app/Activity;)V", "currentIdTranSelected", "", "currentTransition", "dataSourceTran", "Lcom/photoeditor/slideshow/customView/recyclical/datasource/SelectableDataSource;", "editStickerListener", "Lcom/photoeditor/slideshow/interfaces/EditStickerListener;", "getEditStickerListener", "()Lcom/photoeditor/slideshow/interfaces/EditStickerListener;", "setEditStickerListener", "(Lcom/photoeditor/slideshow/interfaces/EditStickerListener;)V", "isHaveCategory", "", "isModeTran", "isPremium", "()Z", "setPremium", "(Z)V", "isUnlockMate", "setUnlockMate", "isUseTranFromTheme", "setUseTranFromTheme", "jobLoadBitmap", "Lkotlinx/coroutines/CompletableJob;", "jobRandom", "Lkotlinx/coroutines/Job;", "listCategory", "Ljava/util/ArrayList;", "Lcom/photoeditor/slideshow/models/DataCategory;", "Lkotlin/collections/ArrayList;", "listTransition", "mLastClickTime", "", "moduleId", "oldTransition", "getOldTransition", "()Lcom/photoeditor/slideshow/models/GifTransition;", "setOldTransition", "(Lcom/photoeditor/slideshow/models/GifTransition;)V", "parentId", "transitionInterface", "Lcom/photoeditor/slideshow/interfaces/TransitionInterface;", "actionChangeTransition", "", "tranModel", "applyTransitionJson", "tran", "canTouch", "cancelTranNew", "changeTransition", "changeTransitionByTheme", "clearTransition", "clickTran", "doneTranNew", "downloadTranPack", "item", "index", "", "getBitmapForTransition", "getListBitmap", "Landroid/graphics/Bitmap;", "folder", "Ljava/io/File;", "initRecyleTran", "randomTranDraw", "randomTranJson", "setCurrentScreen", "screen", "TransitionDownloadInterface", "slideshow_release"}, mo49142k = 1, mo49143mv = {1, 4, 0})
/////* compiled from: MyTranController.kt */
//public final class MyTranController {
//    private final Activity activity;
//    /* access modifiers changed from: private */
//    public final Context context;
//    /* access modifiers changed from: private */
//    public String currentIdTranSelected = "";
//    /* access modifiers changed from: private */
//    public GifTransition currentTransition = new GifTransition("none", "none", "classic", R.color.trans, 1, (Boolean) null, Transition.NONE);
//    /* access modifiers changed from: private */
//    public SelectableDataSource<Object> dataSourceTran = SelectableDataSourceKt.emptySelectableDataSource();
//    private EditStickerListener editStickerListener;
//    /* access modifiers changed from: private */
//    public boolean isHaveCategory;
//    /* access modifiers changed from: private */
//    public boolean isModeTran;
//    private boolean isPremium;
//    private boolean isUnlockMate;
//    private boolean isUseTranFromTheme;
//    private final CompletableJob jobLoadBitmap;
//    private Job jobRandom;
//    /* access modifiers changed from: private */
//    public ArrayList<DataCategory> listCategory = new ArrayList<>();
//    /* access modifiers changed from: private */
//    public ArrayList<GifTransition> listTransition;
//    private long mLastClickTime;
//    /* access modifiers changed from: private */
//    public final VideoMaker mVideoMaker;
//    /* access modifiers changed from: private */
//    public String moduleId = "";
//    private GifTransition oldTransition = new GifTransition("none", "none", "classic", R.color.trans, 1, (Boolean) null, Transition.NONE);
//    /* access modifiers changed from: private */
//    public String parentId = "classic";
//    /* access modifiers changed from: private */
//    public final RecyclerView recycleTran;
//    /* access modifiers changed from: private */
//    public final TabTranIndicator tabTran;
//    private TransitionInterface transitionInterface;
//    /* access modifiers changed from: private */
//    public final GifTransitionViewModel transitionViewModel;
//    /* access modifiers changed from: private */
//    public final View viewEmpty;
//
//    @Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, mo49141d2 = {"Lcom/photoeditor/slideshow/components/MyTranController$TransitionDownloadInterface;", "", "downloadSuccess", "", "tranModel", "Lcom/photoeditor/slideshow/models/GifTransition;", "slideshow_release"}, mo49142k = 1, mo49143mv = {1, 4, 0})
//    /* compiled from: MyTranController.kt */
//    public interface TransitionDownloadInterface {
//        void downloadSuccess(GifTransition gifTransition);
//    }
//
//    @Metadata(mo49139bv = {1, 0, 3}, mo49142k = 3, mo49143mv = {1, 4, 0})
//    public final /* synthetic */ class WhenMappings {
//        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
//
//        static {
//            int[] iArr = new int[Transition.values().length];
//            $EnumSwitchMapping$0 = iArr;
//            iArr[Transition.RANDOM_JSON.ordinal()] = 1;
//            iArr[Transition.RANDOM_DRAW.ordinal()] = 2;
//            iArr[Transition.DRAW.ordinal()] = 3;
//            iArr[Transition.IMAGE.ordinal()] = 4;
//        }
//    }
//
//    public MyTranController(DialogViewModel dialogViewModel, GifTransition gifTransition, GifTransitionViewModel gifTransitionViewModel, LifecycleOwner lifecycleOwner, Context context2, RecyclerView recyclerView, TabTranIndicator tabTranIndicator, VideoMaker videoMaker, View view, Activity activity2) {
//        final DialogViewModel dialogViewModel2 = dialogViewModel;
//        GifTransition gifTransition2 = gifTransition;
//        GifTransitionViewModel gifTransitionViewModel2 = gifTransitionViewModel;
//        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
//        Context context3 = context2;
//        RecyclerView recyclerView2 = recyclerView;
//        TabTranIndicator tabTranIndicator2 = tabTranIndicator;
//        View view2 = view;
//        Intrinsics.checkNotNullParameter(dialogViewModel2, "dialogViewModel");
//        Intrinsics.checkNotNullParameter(gifTransitionViewModel2, "transitionViewModel");
//        Intrinsics.checkNotNullParameter(lifecycleOwner2, "viewLifecycleOwner");
//        Intrinsics.checkNotNullParameter(context3, "context");
//        Intrinsics.checkNotNullParameter(recyclerView2, "recycleTran");
//        Intrinsics.checkNotNullParameter(tabTranIndicator2, "tabTran");
//        Intrinsics.checkNotNullParameter(view2, "viewEmpty");
//        this.transitionViewModel = gifTransitionViewModel2;
//        this.context = context3;
//        this.recycleTran = recyclerView2;
//        this.tabTran = tabTranIndicator2;
//        this.mVideoMaker = videoMaker;
//        this.viewEmpty = view2;
//        this.activity = activity2;
//        if (gifTransition2 != null) {
//            this.currentTransition = gifTransition2;
//            this.oldTransition = gifTransition2;
//            if (gifTransition.getId() != null) {
//                String id = gifTransition.getId();
//                Intrinsics.checkNotNullExpressionValue(id, "gifTransition.id");
//                this.currentIdTranSelected = id;
//            }
//        }
//        gifTransitionViewModel.getListGifCategory().observe(lifecycleOwner2, new Observer<ArrayList<DataCategory>>(this) {
//            final /* synthetic */ MyTranController this$0;
//
//            {
//                this.this$0 = r1;
//            }
//
//            public final void onChanged(final ArrayList<DataCategory> arrayList) {
//                this.this$0.isHaveCategory = true;
//                MyTranController myTranController = this.this$0;
//                Intrinsics.checkNotNullExpressionValue(arrayList, "it");
//                myTranController.listCategory = arrayList;
//                this.this$0.tabTran.addTabsFromUrl(this.this$0.listCategory);
//                this.this$0.tabTran.setVisibility(0);
//                this.this$0.tabTran.display();
//                this.this$0.tabTran.setListener(new TabTranIndicator.OnTabListener(this) {
//                    final /* synthetic */ C25271 this$0;
//
//                    public void onTabChanged(int i) {
//                    }
//
//                    {
//                        this.this$0 = r1;
//                    }
//
//                    public void onTabClicked(int i) {
//                        if (i < this.this$0.this$0.listCategory.size()) {
//                            Object obj = this.this$0.this$0.listCategory.get(i);
//                            Intrinsics.checkNotNullExpressionValue(obj, "listCategory[position]");
//                            try {
//                                String name = ((DataCategory) obj).getName();
//                                MyTranController myTranController = this.this$0.this$0;
//                                myTranController.setCurrentScreen(name + "_View");
//                            } catch (Exception unused) {
//                            }
//                        }
//                        this.this$0.this$0.tabTran.setCurrentTab(i);
//                        this.this$0.this$0.dataSourceTran.clear();
//                        MyTranController myTranController2 = this.this$0.this$0;
//                        Object obj2 = arrayList.get(i);
//                        Intrinsics.checkNotNullExpressionValue(obj2, "it[position]");
//                        String id = ((DataCategory) obj2).getId();
//                        Intrinsics.checkNotNullExpressionValue(id, "it[position].id");
//                        myTranController2.parentId = id;
//                        MyTranController myTranController3 = this.this$0.this$0;
//                        Object obj3 = arrayList.get(i);
//                        Intrinsics.checkNotNullExpressionValue(obj3, "it[position]");
//                        String moduleId = ((DataCategory) obj3).getModuleId();
//                        Intrinsics.checkNotNullExpressionValue(moduleId, "it[position].moduleId");
//                        myTranController3.moduleId = moduleId;
//                        GifTransitionViewModel.getDataGif$default(this.this$0.this$0.transitionViewModel, this.this$0.this$0.parentId, this.this$0.this$0.moduleId, (Function0) null, 4, (Object) null);
//                    }
//                });
//                this.this$0.tabTran.setCurrentTab(0);
//                this.this$0.dataSourceTran.clear();
//                this.this$0.parentId = "Classic";
//                GifTransitionViewModel.getDataGif$default(this.this$0.transitionViewModel, this.this$0.parentId, "", (Function0) null, 4, (Object) null);
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
//                if (this.this$0.isModeTran) {
//                    ViewUtils.INSTANCE.runLayoutAnimation(this.this$0.recycleTran);
//                    this.this$0.listTransition = arrayList;
//                    Intrinsics.checkNotNullExpressionValue(arrayList, "it");
//                    DataSource.DefaultImpls.set$default(this.this$0.dataSourceTran, arrayList, (Function2) null, (Function2) null, 6, (Object) null);
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
//                    DialogDownloadDetail.Companion.getInstance().showDialog(this.this$0.context, dialogViewModel2);
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
//    public final GifTransition getOldTransition() {
//        return this.oldTransition;
//    }
//
//    public final void setOldTransition(GifTransition gifTransition) {
//        Intrinsics.checkNotNullParameter(gifTransition, "<set-?>");
//        this.oldTransition = gifTransition;
//    }
//
//    public final void setCurrentScreen(String str) {
//        Intrinsics.checkNotNullParameter(str, "screen");
//        MainActivity.Companion companion = MainActivity.Companion;
//        Context context2 = this.context;
//        Intrinsics.checkNotNull(context2);
//        FirebaseAnalytics instance = FirebaseAnalytics.getInstance(context2);
//        Intrinsics.checkNotNullExpressionValue(instance, "FirebaseAnalytics.getInstance(context!!)");
//        companion.setFirebaseAnalytics(instance);
//        if (MainActivity.Companion.getFirebaseAnalytics() != null && this.activity != null) {
//            MainActivity.Companion.getFirebaseAnalytics().setCurrentScreen(this.activity, str, str);
//        }
//    }
//
//    public final void clickTran(TransitionInterface transitionInterface2) {
//        this.transitionInterface = transitionInterface2;
//        GifTransitionViewModel.getDataGifCategory$default(this.transitionViewModel, (Function1) null, 1, (Object) null);
//    }
//
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
//    public final void changeTransition(GifTransition gifTransition) {
//        Intrinsics.checkNotNullParameter(gifTransition, "tranModel");
//        this.isUseTranFromTheme = false;
//        actionChangeTransition(gifTransition);
//    }
//
//    private final void actionChangeTransition(GifTransition gifTransition) {
//        if (this.mVideoMaker != null) {
//            this.currentTransition = gifTransition;
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
//            this.mVideoMaker.applyTransitionNew(gifTransition);
//        }
//    }
//
//    private final void applyTransitionJson(GifTransition gifTransition) {
//        ArrayList arrayList = new ArrayList();
//        Job.DefaultImpls.cancel$default(this.jobRandom, (CancellationException) null, 1, (Object) null);
//        this.jobRandom = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new MyTranController$applyTransitionJson$1(this, gifTransition, arrayList, (Continuation) null), 3, (Object) null);
//    }
//
//    private final void randomTranJson(GifTransition gifTransition) {
//        ArrayList arrayList = new ArrayList();
//        ArrayList<GifTransition> arrayList2 = this.listTransition;
//        Intrinsics.checkNotNull(arrayList2);
//        Iterator<GifTransition> it = arrayList2.iterator();
//        while (it.hasNext()) {
//            GifTransition next = it.next();
//            Intrinsics.checkNotNullExpressionValue(next, "tran");
//            Boolean localFile = next.getLocalFile();
//            Intrinsics.checkNotNullExpressionValue(localFile, "tran.localFile");
//            if (localFile.booleanValue() && next.getPath() != null) {
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
//    }
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
//            Intrinsics.checkNotNullExpressionValue(compressSync, "Tiny.getInstance().sourc…          .compressSync()");
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
//            Intrinsics.checkNotNullExpressionValue(id, "oldTransition.id");
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
//}
