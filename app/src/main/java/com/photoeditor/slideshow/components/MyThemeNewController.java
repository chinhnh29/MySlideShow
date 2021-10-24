//package com.photoeditor.slideshow.components;
//
//import android.content.Context;
//import android.os.SystemClock;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.fragment.app.FragmentActivity;
//import androidx.lifecycle.LifecycleOwner;
//import androidx.lifecycle.Observer;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.analytics.FirebaseAnalytics;
//import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.activity.MainActivity;
//import com.photoeditor.slideshow.customView.TabIndicator;
//import com.photoeditor.slideshow.customView.recyclical.RecyclicalKt;
//import com.photoeditor.slideshow.customView.recyclical.datasource.DataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSource;
//import com.photoeditor.slideshow.customView.recyclical.datasource.SelectableDataSourceKt;
//import com.photoeditor.slideshow.dialog.DialogDownloadDetail;
//import com.photoeditor.slideshow.imagetovideo.Transition;
//import com.photoeditor.slideshow.models.DataCategory;
//import com.photoeditor.slideshow.models.GifTheme;
//import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.models.music.Music;
//import com.photoeditor.slideshow.utils.ViewUtils;
//import com.photoeditor.slideshow.viewmodel.DialogViewModel;
//import com.photoeditor.slideshow.viewmodel.GifThemeViewModel;
//import com.photoeditor.slideshow.viewmodel.GifTransitionViewModel;
//import com.photoeditor.slideshow.viewmodel.MusicViewModel;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import kotlin.Metadata;
//import kotlin.Unit;
//import kotlin.jvm.functions.Function0;
//import kotlin.jvm.functions.Function1;
//import kotlin.jvm.functions.Function2;
//import kotlin.jvm.internal.Intrinsics;
//
////@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001IB\u0001\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0002\u0010\u001eJ\u0010\u00109\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0004H\u0002J\b\u0010:\u001a\u00020\u0005H\u0002J\u001a\u0010;\u001a\u00020\u00062\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060=J\u0010\u0010>\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010?\u001a\u00020\u00062\b\u0010@\u001a\u0004\u0018\u00010\u0004J\u0006\u0010A\u001a\u00020\u0006J\u0006\u0010B\u001a\u00020\u0006J\u0010\u0010C\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0004H\u0002J\b\u0010D\u001a\u00020\u0006H\u0002J\u000e\u0010E\u001a\u00020\u00062\u0006\u0010F\u001a\u00020\u0004J\u000e\u0010G\u001a\u00020\u00062\u0006\u0010H\u001a\u00020 R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0004¢\u0006\u0002\n\u0000R \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010'X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u0012\u0012\u0004\u0012\u00020+0*j\b\u0012\u0004\u0012\u00020+`,X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010-\u001a\u0012\u0012\u0004\u0012\u00020.0*j\b\u0012\u0004\u0012\u00020.`,X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u000e¢\u0006\u0002\n\u0000RJ\u00101\u001a>\u0012\u0004\u0012\u00020 \u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002030*j\b\u0012\u0004\u0012\u000203`,02j\u001e\u0012\u0004\u0012\u00020 \u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002030*j\b\u0012\u0004\u0012\u000203`,`4X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0004¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010#\"\u0004\b7\u0010%R\u000e\u00108\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, mo49141d2 = {"Lcom/photoeditor/slideshow/components/MyThemeNewController;", "", "chooseThemeNew", "Lkotlin/Function2;", "Lcom/photoeditor/slideshow/models/GifTheme;", "", "", "musicViewModel", "Lcom/photoeditor/slideshow/viewmodel/MusicViewModel;", "transitionViewModel", "Lcom/photoeditor/slideshow/viewmodel/GifTransitionViewModel;", "dialogViewModel", "Lcom/photoeditor/slideshow/viewmodel/DialogViewModel;", "gifTheme", "gifThemeViewModel", "Lcom/photoeditor/slideshow/viewmodel/GifThemeViewModel;", "viewLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "recycleView", "Landroidx/recyclerview/widget/RecyclerView;", "tabLayout", "Lcom/photoeditor/slideshow/customView/TabIndicator;", "myVideoPlayer", "Lcom/photoeditor/slideshow/components/MyVideoPlayer;", "viewEmpty", "Landroid/view/View;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Lkotlin/jvm/functions/Function2;Lcom/photoeditor/slideshow/viewmodel/MusicViewModel;Lcom/photoeditor/slideshow/viewmodel/GifTransitionViewModel;Lcom/photoeditor/slideshow/viewmodel/DialogViewModel;Lcom/photoeditor/slideshow/models/GifTheme;Lcom/photoeditor/slideshow/viewmodel/GifThemeViewModel;Landroidx/lifecycle/LifecycleOwner;Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;Lcom/photoeditor/slideshow/customView/TabIndicator;Lcom/photoeditor/slideshow/components/MyVideoPlayer;Landroid/view/View;Landroidx/fragment/app/FragmentActivity;)V", "currentIdTranSelected", "", "currentTheme", "getCurrentTheme", "()Lcom/photoeditor/slideshow/models/GifTheme;", "setCurrentTheme", "(Lcom/photoeditor/slideshow/models/GifTheme;)V", "dataSourceTheme", "Lcom/photoeditor/slideshow/customView/recyclical/datasource/SelectableDataSource;", "hasDataCategory", "listCategory", "Ljava/util/ArrayList;", "Lcom/photoeditor/slideshow/models/DataCategory;", "Lkotlin/collections/ArrayList;", "listMusic", "Lcom/photoeditor/slideshow/models/music/Music;", "mLastClickTime", "", "mapTransition", "Ljava/util/HashMap;", "Lcom/photoeditor/slideshow/models/GifTransition;", "Lkotlin/collections/HashMap;", "oldTheme", "getOldTheme", "setOldTheme", "parentId", "actionDownloadTheme", "canTouch", "cancelTheme", "action", "Lkotlin/Function1;", "checkMusicOnline", "chooseTheme", "themeOnline", "clickTheme", "doneTheme", "downloadTheme", "initRecycleView", "onClickTheme", "item", "setCurrentScreen", "screen", "ThemeInterface", "slideshow_release"}, mo49142k = 1, mo49143mv = {1, 4, 0})
/////* compiled from: MyThemeNewController.kt */
//public final class MyThemeNewController {
//    private final FragmentActivity activity;
//    /* access modifiers changed from: private */
//    public final Function2<GifTheme, Boolean, Unit> chooseThemeNew;
//    /* access modifiers changed from: private */
//    public final Context context;
//    /* access modifiers changed from: private */
//    public String currentIdTranSelected = "";
//    private GifTheme currentTheme = new GifTheme("none", "none", "classic", R.color.trans, 1, (Boolean) null, Transition.NONE);
//    /* access modifiers changed from: private */
//    public SelectableDataSource<Object> dataSourceTheme = SelectableDataSourceKt.emptySelectableDataSource();
//    /* access modifiers changed from: private */
//    public final GifThemeViewModel gifThemeViewModel;
//    /* access modifiers changed from: private */
//    public boolean hasDataCategory;
//    /* access modifiers changed from: private */
//    public ArrayList<DataCategory> listCategory = new ArrayList<>();
//    /* access modifiers changed from: private */
//    public ArrayList<Music> listMusic = new ArrayList<>();
//    private long mLastClickTime;
//    /* access modifiers changed from: private */
//    public HashMap<String, ArrayList<GifTransition>> mapTransition = new HashMap<>();
//    /* access modifiers changed from: private */
//    public final MusicViewModel musicViewModel;
//    private final MyVideoPlayer myVideoPlayer;
//    private GifTheme oldTheme = new GifTheme("none", "none", "classic", R.color.trans, 1, (Boolean) null, Transition.NONE);
//    /* access modifiers changed from: private */
//    public String parentId = "";
//    /* access modifiers changed from: private */
//    public final RecyclerView recycleView;
//    /* access modifiers changed from: private */
//    public final TabIndicator tabLayout;
//    /* access modifiers changed from: private */
//    public final GifTransitionViewModel transitionViewModel;
//    /* access modifiers changed from: private */
//    public final View viewEmpty;
//
//    @Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, mo49141d2 = {"Lcom/photoeditor/slideshow/components/MyThemeNewController$ThemeInterface;", "", "downloadSuccess", "", "gifTheme", "Lcom/photoeditor/slideshow/models/GifTheme;", "slideshow_release"}, mo49142k = 1, mo49143mv = {1, 4, 0})
//    /* compiled from: MyThemeNewController.kt */
//    public interface ThemeInterface {
//        void downloadSuccess(GifTheme gifTheme);
//    }
//
//    public MyThemeNewController(Function2<? super GifTheme, ? super Boolean, Unit> function2, MusicViewModel musicViewModel2, GifTransitionViewModel gifTransitionViewModel, DialogViewModel dialogViewModel, GifTheme gifTheme, GifThemeViewModel gifThemeViewModel2, LifecycleOwner lifecycleOwner, Context context2, RecyclerView recyclerView, TabIndicator tabIndicator, MyVideoPlayer myVideoPlayer2, View view, FragmentActivity fragmentActivity) {
//        Function2<? super GifTheme, ? super Boolean, Unit> function22 = function2;
//        MusicViewModel musicViewModel3 = musicViewModel2;
//        GifTransitionViewModel gifTransitionViewModel2 = gifTransitionViewModel;
//        final DialogViewModel dialogViewModel2 = dialogViewModel;
//        GifThemeViewModel gifThemeViewModel3 = gifThemeViewModel2;
//        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
//        RecyclerView recyclerView2 = recyclerView;
//        TabIndicator tabIndicator2 = tabIndicator;
//        View view2 = view;
//        Intrinsics.checkNotNullParameter(function22, "chooseThemeNew");
//        Intrinsics.checkNotNullParameter(musicViewModel3, "musicViewModel");
//        Intrinsics.checkNotNullParameter(gifTransitionViewModel2, "transitionViewModel");
//        Intrinsics.checkNotNullParameter(dialogViewModel2, "dialogViewModel");
//        Intrinsics.checkNotNullParameter(gifThemeViewModel3, "gifThemeViewModel");
//        Intrinsics.checkNotNullParameter(lifecycleOwner2, "viewLifecycleOwner");
//        Intrinsics.checkNotNullParameter(recyclerView2, "recycleView");
//        Intrinsics.checkNotNullParameter(tabIndicator2, "tabLayout");
//        Intrinsics.checkNotNullParameter(view2, "viewEmpty");
//        this.chooseThemeNew = function22;
//        this.musicViewModel = musicViewModel3;
//        this.transitionViewModel = gifTransitionViewModel2;
//        this.gifThemeViewModel = gifThemeViewModel3;
//        this.context = context2;
//        this.recycleView = recyclerView2;
//        this.tabLayout = tabIndicator2;
//        this.myVideoPlayer = myVideoPlayer2;
//        this.viewEmpty = view2;
//        this.activity = fragmentActivity;
//        if (gifTheme != null) {
//            String name = gifTheme.getName();
//            Intrinsics.checkNotNullExpressionValue(name, "gifTheme.name");
//            this.currentIdTranSelected = name;
//        }
//        gifThemeViewModel2.getListGifCategory().observe(lifecycleOwner2, new Observer<ArrayList<DataCategory>>(this) {
//            final /* synthetic */ MyThemeNewController this$0;
//
//            {
//                this.this$0 = r1;
//            }
//
//            public final void onChanged(final ArrayList<DataCategory> arrayList) {
//                if (arrayList != null) {
//                    this.this$0.transitionViewModel.getAllDataGif(new Function1<HashMap<String, ArrayList<GifTransition>>, Unit>(this) {
//                        final /* synthetic */ C25141 this$0;
//
//                        {
//                            this.this$0 = r1;
//                        }
//
//                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
//                            invoke((HashMap<String, ArrayList<GifTransition>>) (HashMap) obj);
//                            return Unit.INSTANCE;
//                        }
//
//                        public final void invoke(HashMap<String, ArrayList<GifTransition>> hashMap) {
//                            Intrinsics.checkNotNullParameter(hashMap, "mapTran");
//                            this.this$0.this$0.mapTransition = hashMap;
//                            this.this$0.this$0.musicViewModel.getAllMusicOnline(new Function1<ArrayList<Music>, Unit>(this) {
//                                final /* synthetic */ C25151 this$0;
//
//                                {
//                                    this.this$0 = r1;
//                                }
//
//                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
//                                    invoke((ArrayList<Music>) (ArrayList) obj);
//                                    return Unit.INSTANCE;
//                                }
//
//                                public final void invoke(final ArrayList<Music> arrayList) {
//                                    Intrinsics.checkNotNullParameter(arrayList, "listMusic");
//                                    this.this$0.this$0.this$0.listMusic = arrayList;
//                                    this.this$0.this$0.this$0.hasDataCategory = true;
//                                    this.this$0.this$0.this$0.listCategory = arrayList;
//                                    this.this$0.this$0.this$0.tabLayout.addTabsFromUrl(this.this$0.this$0.this$0.listCategory);
//                                    this.this$0.this$0.this$0.tabLayout.display();
//                                    this.this$0.this$0.this$0.tabLayout.setListener(new TabIndicator.OnTabListener(this) {
//                                        final /* synthetic */ C25161 this$0;
//
//                                        public void onTabChanged(int i) {
//                                        }
//
//                                        {
//                                            this.this$0 = r1;
//                                        }
//
//                                        public void onTabClicked(int i) {
//                                            if (i < this.this$0.this$0.this$0.this$0.listCategory.size()) {
//                                                Object obj = this.this$0.this$0.this$0.this$0.listCategory.get(i);
//                                                Intrinsics.checkNotNullExpressionValue(obj, "listCategory[position]");
//                                                try {
//                                                    String name = ((DataCategory) obj).getName();
//                                                    MyThemeNewController myThemeNewController = this.this$0.this$0.this$0.this$0;
//                                                    myThemeNewController.setCurrentScreen(name + "_View");
//                                                } catch (Exception unused) {
//                                                }
//                                            }
//                                            this.this$0.this$0.this$0.this$0.tabLayout.setCurrentTab(i);
//                                            this.this$0.this$0.this$0.this$0.dataSourceTheme.clear();
//                                            MyThemeNewController myThemeNewController2 = this.this$0.this$0.this$0.this$0;
//                                            Object obj2 = arrayList.get(i);
//                                            Intrinsics.checkNotNullExpressionValue(obj2, "it[position]");
//                                            String id = ((DataCategory) obj2).getId();
//                                            Intrinsics.checkNotNullExpressionValue(id, "it[position].id");
//                                            myThemeNewController2.parentId = id;
//                                            GifThemeViewModel access$getGifThemeViewModel$p = this.this$0.this$0.this$0.this$0.gifThemeViewModel;
//                                            Object obj3 = arrayList.get(i);
//                                            Intrinsics.checkNotNullExpressionValue(obj3, "it[position]");
//                                            String moduleId = ((DataCategory) obj3).getModuleId();
//                                            Intrinsics.checkNotNullExpressionValue(moduleId, "it[position].moduleId");
//                                            GifThemeViewModel.getDataGif$default(access$getGifThemeViewModel$p, moduleId, this.this$0.this$0.this$0.this$0.parentId, arrayList, this.this$0.this$0.this$0.this$0.mapTransition, (Function0) null, 16, (Object) null);
//                                        }
//                                    });
//                                    try {
//                                        Object obj = this.this$0.this$0.this$0.listCategory.get(0);
//                                        Intrinsics.checkNotNullExpressionValue(obj, "listCategory[0]");
//                                        String name = ((DataCategory) obj).getName();
//                                        MyThemeNewController myThemeNewController = this.this$0.this$0.this$0;
//                                        myThemeNewController.setCurrentScreen(name + "_View");
//                                    } catch (Exception unused) {
//                                    }
//                                    this.this$0.this$0.this$0.tabLayout.setCurrentTab(0);
//                                    if (arrayList.size() > 0) {
//                                        MyThemeNewController myThemeNewController2 = this.this$0.this$0.this$0;
//                                        Object obj2 = arrayList.get(0);
//                                        Intrinsics.checkNotNullExpressionValue(obj2, "it[0]");
//                                        String id = ((DataCategory) obj2).getId();
//                                        Intrinsics.checkNotNullExpressionValue(id, "it[0].id");
//                                        myThemeNewController2.parentId = id;
//                                        GifThemeViewModel access$getGifThemeViewModel$p = this.this$0.this$0.this$0.gifThemeViewModel;
//                                        Object obj3 = arrayList.get(0);
//                                        Intrinsics.checkNotNullExpressionValue(obj3, "it[0]");
//                                        String moduleId = ((DataCategory) obj3).getModuleId();
//                                        Intrinsics.checkNotNullExpressionValue(moduleId, "it[0].moduleId");
//                                        GifThemeViewModel.getDataGif$default(access$getGifThemeViewModel$p, moduleId, this.this$0.this$0.this$0.parentId, arrayList, this.this$0.this$0.this$0.mapTransition, (Function0) null, 16, (Object) null);
//                                    }
//                                }
//                            });
//                        }
//                    });
//                }
//            }
//        });
//        gifThemeViewModel2.getListGifTransition().observe(lifecycleOwner2, new Observer<ArrayList<GifTheme>>(this) {
//            final /* synthetic */ MyThemeNewController this$0;
//
//            {
//                this.this$0 = r1;
//            }
//
//            public final void onChanged(ArrayList<GifTheme> arrayList) {
//                if (arrayList != null) {
//                    ViewUtils.INSTANCE.runLayoutAnimation(this.this$0.recycleView);
//                    DataSource.DefaultImpls.set$default(this.this$0.dataSourceTheme, arrayList, (Function2) null, (Function2) null, 6, (Object) null);
//                }
//            }
//        });
//        gifThemeViewModel2.isFailedToLoadTheme().observe(lifecycleOwner2, new Observer<Boolean>(this) {
//            final /* synthetic */ MyThemeNewController this$0;
//
//            {
//                this.this$0 = r1;
//            }
//
//            public final void onChanged(Boolean bool) {
//                Intrinsics.checkNotNullExpressionValue(bool, "it");
//                if (bool.booleanValue()) {
//                    Toast.makeText(this.this$0.context, R.string.pls_check_network, 0).show();
//                }
//            }
//        });
//        gifThemeViewModel2.isShowProgress().observe(lifecycleOwner2, new Observer<Boolean>(this) {
//            final /* synthetic */ MyThemeNewController this$0;
//
//            {
//                this.this$0 = r1;
//            }
//
//            public final void onChanged(Boolean bool) {
//                Intrinsics.checkNotNullExpressionValue(bool, "it");
//                if (bool.booleanValue()) {
//                    DialogDownloadDetail.Companion.getInstance().showDialog(this.this$0.context, dialogViewModel2, new Function1<Boolean, Unit>(this) {
//                        final /* synthetic */ C25204 this$0;
//
//                        {
//                            this.this$0 = r1;
//                        }
//
//                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
//                            invoke(((Boolean) obj).booleanValue());
//                            return Unit.INSTANCE;
//                        }
//
//                        public final void invoke(boolean z) {
//                            this.this$0.this$0.gifThemeViewModel.setShowDialogValue(false);
//                        }
//                    });
//                } else {
//                    DialogDownloadDetail.Companion.getInstance().hideDialog();
//                }
//            }
//        });
//        try {
//            gifThemeViewModel2.getProgress().observe(lifecycleOwner2, C25225.INSTANCE);
//        } catch (Exception unused) {
//        }
//        initRecycleView();
//    }
//
//    public final GifTheme getCurrentTheme() {
//        return this.currentTheme;
//    }
//
//    public final void setCurrentTheme(GifTheme gifTheme) {
//        Intrinsics.checkNotNullParameter(gifTheme, "<set-?>");
//        this.currentTheme = gifTheme;
//    }
//
//    public final GifTheme getOldTheme() {
//        return this.oldTheme;
//    }
//
//    public final void setOldTheme(GifTheme gifTheme) {
//        Intrinsics.checkNotNullParameter(gifTheme, "<set-?>");
//        this.oldTheme = gifTheme;
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
//    public final void clickTheme() {
//        RecyclerView.Adapter adapter;
//        if (!this.hasDataCategory) {
//            GifThemeViewModel.getDataGifCategory$default(this.gifThemeViewModel, (Function1) null, 1, (Object) null);
//            return;
//        }
//        RecyclerView recyclerView = this.recycleView;
//        if (recyclerView != null && (adapter = recyclerView.getAdapter()) != null) {
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    private final void initRecycleView() {
//        RecyclicalKt.setup(this.recycleView, new MyThemeNewController$initRecycleView$1(this));
//    }
//
//    public final void onClickTheme(GifTheme gifTheme) {
//        Intrinsics.checkNotNullParameter(gifTheme, "item");
//        chooseTheme(gifTheme);
//        this.chooseThemeNew.invoke(gifTheme, false);
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
//    public final void cancelTheme(Function1<? super Boolean, Unit> function1) {
//        Intrinsics.checkNotNullParameter(function1, "action");
//        if (!Intrinsics.areEqual((Object) this.currentTheme, (Object) this.oldTheme)) {
//            function1.invoke(true);
//            String name = this.oldTheme.getName();
//            Intrinsics.checkNotNullExpressionValue(name, "oldTheme.name");
//            this.currentIdTranSelected = name;
//            chooseTheme(this.oldTheme);
//            this.chooseThemeNew.invoke(this.oldTheme, true);
//            return;
//        }
//        function1.invoke(false);
//    }
//
//    public final void doneTheme() {
//        this.oldTheme = this.currentTheme;
//    }
//
//    /* access modifiers changed from: private */
//    public final void downloadTheme(GifTheme gifTheme) {
//        MyVideoPlayer myVideoPlayer2 = this.myVideoPlayer;
//        if (myVideoPlayer2 != null) {
//            myVideoPlayer2.pausePreview();
//        }
//        this.gifThemeViewModel.setShowDialog();
//        actionDownloadTheme(gifTheme);
//    }
//
//    private final void checkMusicOnline(GifTheme gifTheme) {
//        this.musicViewModel.getAllMusicOnline(new MyThemeNewController$checkMusicOnline$1(this, gifTheme));
//    }
//
//    /* access modifiers changed from: private */
//    public final void actionDownloadTheme(GifTheme gifTheme) {
//        this.gifThemeViewModel.downloadMusic(gifTheme, this.parentId, new MyThemeNewController$actionDownloadTheme$1(this));
//    }
//
//    public final void chooseTheme(GifTheme gifTheme) {
//        if (gifTheme != null) {
//            this.currentTheme = gifTheme;
//            MyVideoPlayer myVideoPlayer2 = this.myVideoPlayer;
//            if (myVideoPlayer2 != null) {
//                myVideoPlayer2.chooseThemeNew(gifTheme);
//            }
//        }
//    }
//}
