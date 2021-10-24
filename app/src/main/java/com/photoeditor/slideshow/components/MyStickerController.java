//package com.photoeditor.slideshow.components;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.adapter.AnimationStickerAdapter;
//import com.photoeditor.slideshow.adapter.StickerChooseAdapter;
//import com.photoeditor.slideshow.common.ExtentionsKt;
//import com.photoeditor.slideshow.customView.CustomDrawView;
//import com.photoeditor.slideshow.customView.lineview.TuSdkMovieColorRectView;
//import com.photoeditor.slideshow.customView.lineview.TuSdkMovieScrollPlayLineView;
//import com.photoeditor.slideshow.customView.lineview.TuSdkRangeSelectionBar;
//import com.photoeditor.slideshow.fragment.main.MainFragment;
//import com.photoeditor.slideshow.imagetovideo.Animation;
//import com.photoeditor.slideshow.imagetovideo.Decor;
//import com.photoeditor.slideshow.imagetovideo.OnHandlerItemListener;
//import com.photoeditor.slideshow.imagetovideo.VideoMaker;
//import com.photoeditor.slideshow.interfaces.EditStickerListener;
//import com.photoeditor.slideshow.interfaces.LineviewInterface;
//import com.photoeditor.slideshow.interfaces.OnChangeFocusSticker;
//import com.photoeditor.slideshow.java.C2671Lo;
//import com.photoeditor.slideshow.models.TextAnimation;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import kotlin.jvm.internal.Intrinsics;
//
////
////@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000³\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b#*\u0001:\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B=\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0013J\u0006\u0010E\u001a\u00020FJ\b\u0010G\u001a\u00020FH\u0002J\u000e\u0010H\u001a\u00020F2\u0006\u0010I\u001a\u00020JJ\b\u0010K\u001a\u00020FH\u0002J\u0018\u0010L\u001a\u00020F2\u0006\u0010M\u001a\u00020\u00152\u0006\u0010N\u001a\u00020\u0015H\u0016J\u0010\u0010O\u001a\u00020F2\u0006\u0010P\u001a\u00020\u0015H\u0002J\u0006\u0010Q\u001a\u00020FJ\u0010\u0010R\u001a\u00020F2\u0006\u0010P\u001a\u00020\u0015H\u0016J\u0006\u0010S\u001a\u00020FJ\u0006\u0010T\u001a\u00020FJ\u0006\u0010U\u001a\u00020FJ\u0010\u0010V\u001a\u00020F2\u0006\u0010W\u001a\u00020\u001bH\u0002J\b\u0010X\u001a\u00020FH\u0016J\b\u0010Y\u001a\u00020FH\u0002J\u0006\u0010Z\u001a\u00020FJ\b\u0010[\u001a\u00020FH\u0002J\b\u0010\\\u001a\u00020%H\u0002J\b\u0010]\u001a\u00020FH\u0002J\u0010\u0010^\u001a\u00020F2\u0006\u0010P\u001a\u00020\u0015H\u0016J\u0010\u0010_\u001a\u00020F2\u0006\u0010P\u001a\u00020\u0015H\u0016J\b\u0010`\u001a\u00020FH\u0016J\u0010\u0010a\u001a\u00020F2\u0006\u0010P\u001a\u00020\u0015H\u0016J\b\u0010b\u001a\u00020FH\u0002J\b\u0010c\u001a\u00020FH\u0002J\b\u0010d\u001a\u00020FH\u0002J\u0010\u0010e\u001a\u00020F2\u0006\u0010W\u001a\u00020\u001bH\u0002J\u0010\u0010f\u001a\u00020F2\u0006\u0010W\u001a\u00020\u001bH\u0002J\b\u0010g\u001a\u00020FH\u0002J\u0010\u0010h\u001a\u00020F2\u0006\u0010i\u001a\u00020%H\u0002J\u0010\u0010j\u001a\u00020F2\u0006\u0010i\u001a\u00020%H\u0002J\u0010\u0010k\u001a\u00020F2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010l\u001a\u00020FH\u0016R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010&\"\u0004\b'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010+\u001a\u0012\u0012\u0004\u0012\u00020\u001b0,j\b\u0012\u0004\u0012\u00020\u001b`-X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u001c\u00103\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u000e\u00108\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u00020:X\u0004¢\u0006\u0004\n\u0002\u0010;R\u000e\u0010<\u001a\u00020=X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010C\u001a\u0012\u0012\u0004\u0012\u00020D0,j\b\u0012\u0004\u0012\u00020D`-X\u000e¢\u0006\u0002\n\u0000¨\u0006m"}, mo49141d2 = {"Lcom/photoeditor/slideshow/components/MyStickerController;", "Lcom/photoeditor/slideshow/interfaces/OnChangeFocusSticker;", "Lcom/photoeditor/slideshow/imagetovideo/OnHandlerItemListener;", "Lcom/photoeditor/slideshow/adapter/StickerChooseAdapter$OnClickStickerChoosedListener;", "Lcom/photoeditor/slideshow/adapter/AnimationStickerAdapter$ListenerAnimSticker;", "Lcom/photoeditor/slideshow/interfaces/LineviewInterface;", "context", "Landroid/content/Context;", "videoMaker", "Lcom/photoeditor/slideshow/imagetovideo/VideoMaker;", "mCustomDrawView1", "Lcom/photoeditor/slideshow/customView/CustomDrawView;", "myVideoPlayer", "Lcom/photoeditor/slideshow/components/MyVideoPlayer;", "lineView", "Lcom/photoeditor/slideshow/customView/lineview/TuSdkMovieScrollPlayLineView;", "recycleView", "Landroidx/recyclerview/widget/RecyclerView;", "recycler_effect_sticker", "(Landroid/content/Context;Lcom/photoeditor/slideshow/imagetovideo/VideoMaker;Lcom/photoeditor/slideshow/customView/CustomDrawView;Lcom/photoeditor/slideshow/components/MyVideoPlayer;Lcom/photoeditor/slideshow/customView/lineview/TuSdkMovieScrollPlayLineView;Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView;)V", "animation", "", "animationAdapter", "Lcom/photoeditor/slideshow/adapter/AnimationStickerAdapter;", "getContext", "()Landroid/content/Context;", "currentDecor", "Lcom/photoeditor/slideshow/imagetovideo/Decor;", "currentProgress", "", "editStickerListener", "Lcom/photoeditor/slideshow/interfaces/EditStickerListener;", "getEditStickerListener", "()Lcom/photoeditor/slideshow/interfaces/EditStickerListener;", "setEditStickerListener", "(Lcom/photoeditor/slideshow/interfaces/EditStickerListener;)V", "isInitThumb", "", "()Z", "setInitThumb", "(Z)V", "layoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "listDecor", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mAdapterChoosedSticker", "Lcom/photoeditor/slideshow/adapter/StickerChooseAdapter;", "mCurrentColorRectView", "Lcom/photoeditor/slideshow/customView/lineview/TuSdkMovieColorRectView;", "mCurrentPosition", "mCustomDrawView", "getMCustomDrawView", "()Lcom/photoeditor/slideshow/customView/CustomDrawView;", "setMCustomDrawView", "(Lcom/photoeditor/slideshow/customView/CustomDrawView;)V", "mLineView", "mOnScrollingPlayPositionListener", "com/photoeditor/slideshow/components/MyStickerController$mOnScrollingPlayPositionListener$1", "Lcom/photoeditor/slideshow/components/MyStickerController$mOnScrollingPlayPositionListener$1;", "mOnSelectTimeChangeListener", "Lcom/photoeditor/slideshow/customView/lineview/TuSdkRangeSelectionBar$OnSelectRangeChangedListener;", "mOnTouchSelectBarlistener", "Lcom/photoeditor/slideshow/customView/lineview/TuSdkRangeSelectionBar$OnTouchSelectBarListener;", "mRecyclerViewChoosedSticker", "mVideoMaker", "recyclerEffectSticker", "textAnimationList", "Lcom/photoeditor/slideshow/models/TextAnimation;", "addListColorRect", "", "addRectStickerToView", "addSticker", "bitmap", "Landroid/graphics/Bitmap;", "addStickerVideo", "changeSticker", "oldP", "newP", "chooseSticker", "position", "clickAddMoreSticker", "clickItem", "clickPauseVideo", "clickPlayVideo", "donePreviewAnimSticker", "hasNewDecor", "decor", "hasWidthLineView", "initEvent", "initLineView", "initView", "isFocusSticker", "loadDataAnim", "onClickAnimSticker", "onDeleteItem", "onZoomItem", "onclickStickerChoose", "onlyPausePreview", "onlyResumePreview", "pausePreview", "previewAtFrameVideo", "scrollToAnimSticker", "setUnSelectorSticker", "showHideSticker", "isShow", "showHideTime", "startPreviewAnimation", "touchItem", "slideshow_release"}, mo49142k = 1, mo49143mv = {1, 4, 0})
/////* compiled from: MyStickerController.kt */
//public final class MyStickerController implements OnChangeFocusSticker, OnHandlerItemListener, StickerChooseAdapter.OnClickStickerChoosedListener, AnimationStickerAdapter.ListenerAnimSticker, LineviewInterface {
//    private int animation;
//    private AnimationStickerAdapter animationAdapter;
//    private final Context context;
//    private Decor currentDecor;
//    /* access modifiers changed from: private */
//    public float currentProgress;
//    private EditStickerListener editStickerListener;
//    private boolean isInitThumb;
//    private RecyclerView.LayoutManager layoutManager;
//    /* access modifiers changed from: private */
//    public ArrayList<Decor> listDecor = new ArrayList<>();
//    private StickerChooseAdapter mAdapterChoosedSticker;
//    /* access modifiers changed from: private */
//    public TuSdkMovieColorRectView mCurrentColorRectView;
//    /* access modifiers changed from: private */
//    public int mCurrentPosition = -1;
//    private CustomDrawView mCustomDrawView;
//    /* access modifiers changed from: private */
//    public TuSdkMovieScrollPlayLineView mLineView;
//    private final MyStickerController$mOnScrollingPlayPositionListener$1 mOnScrollingPlayPositionListener;
//    private final TuSdkRangeSelectionBar.OnSelectRangeChangedListener mOnSelectTimeChangeListener;
//    private final TuSdkRangeSelectionBar.OnTouchSelectBarListener mOnTouchSelectBarlistener;
//    private RecyclerView mRecyclerViewChoosedSticker;
//    private VideoMaker mVideoMaker;
//    /* access modifiers changed from: private */
//    public MyVideoPlayer myVideoPlayer;
//    private RecyclerView recyclerEffectSticker;
//    private ArrayList<TextAnimation> textAnimationList = new ArrayList<>();
//
//    private final void initView() {
//    }
//
//    public void clickItem(int i) {
//    }
//
//    public void onZoomItem() {
//    }
//
//    public void touchItem() {
//    }
//
//    public MyStickerController(Context context2, VideoMaker videoMaker, CustomDrawView customDrawView, MyVideoPlayer myVideoPlayer2, TuSdkMovieScrollPlayLineView tuSdkMovieScrollPlayLineView, RecyclerView recyclerView, RecyclerView recyclerView2) {
//        Intrinsics.checkNotNullParameter(context2, "context");
//        Intrinsics.checkNotNullParameter(videoMaker, "videoMaker");
//        Intrinsics.checkNotNullParameter(customDrawView, "mCustomDrawView1");
//        Intrinsics.checkNotNullParameter(myVideoPlayer2, "myVideoPlayer");
//        Intrinsics.checkNotNullParameter(tuSdkMovieScrollPlayLineView, "lineView");
//        Intrinsics.checkNotNullParameter(recyclerView, "recycleView");
//        Intrinsics.checkNotNullParameter(recyclerView2, "recycler_effect_sticker");
//        this.context = context2;
//        this.recyclerEffectSticker = recyclerView2;
//        this.mCustomDrawView = customDrawView;
//        this.myVideoPlayer = myVideoPlayer2;
//        this.mLineView = tuSdkMovieScrollPlayLineView;
//        this.mVideoMaker = videoMaker;
//        this.mRecyclerViewChoosedSticker = recyclerView;
//        initView();
//        initEvent();
//        this.mOnScrollingPlayPositionListener = new MyStickerController$mOnScrollingPlayPositionListener$1(this);
//        this.mOnTouchSelectBarlistener = new MyStickerController$mOnTouchSelectBarlistener$1(this);
//        this.mOnSelectTimeChangeListener = new MyStickerController$mOnSelectTimeChangeListener$1(this, myVideoPlayer2);
//    }
//
//    public final Context getContext() {
//        return this.context;
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
//    public final CustomDrawView getMCustomDrawView() {
//        return this.mCustomDrawView;
//    }
//
//    public final void setMCustomDrawView(CustomDrawView customDrawView) {
//        this.mCustomDrawView = customDrawView;
//    }
//
//    public void hasWidthLineView() {
//        addRectStickerToView();
//    }
//
//    private final void initEvent() {
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        if (customDrawView != null) {
//            customDrawView.setOnChangeFocusSticker(this);
//        }
//        CustomDrawView customDrawView2 = this.mCustomDrawView;
//        if (customDrawView2 != null) {
//            customDrawView2.setOnHandlerItemListener(this);
//        }
//    }
//
//    public final boolean isInitThumb() {
//        return this.isInitThumb;
//    }
//
//    public final void setInitThumb(boolean z) {
//        this.isInitThumb = z;
//    }
//
//    public final void initLineView() {
//        if (!this.isInitThumb) {
//            this.isInitThumb = true;
//            this.mLineView.initView(this);
//            this.mLineView.setOnProgressChangedListener(this.mOnScrollingPlayPositionListener);
//            this.mLineView.setSelectRangeChangedListener(this.mOnSelectTimeChangeListener);
//            this.mLineView.setOnTouchSelectBarListener(this.mOnTouchSelectBarlistener);
//            this.mLineView.setType(1);
//            Bitmap decodeResource = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_insta);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//            this.mLineView.addBitmap(decodeResource);
//        }
//        if (this.mAdapterChoosedSticker == null) {
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context, 0, false);
//            Context context2 = this.context;
//            CustomDrawView customDrawView = this.mCustomDrawView;
//            this.mAdapterChoosedSticker = new StickerChooseAdapter(context2, customDrawView != null ? customDrawView.getDecor() : null, this);
//            this.mRecyclerViewChoosedSticker.setLayoutManager(linearLayoutManager);
//            this.mRecyclerViewChoosedSticker.setAdapter(this.mAdapterChoosedSticker);
//        }
//        if (this.animationAdapter == null) {
//            this.animationAdapter = new AnimationStickerAdapter(this.context, this.textAnimationList);
//            RecyclerView.LayoutManager linearLayoutManager2 = new LinearLayoutManager(this.context, 0, false);
//            this.layoutManager = linearLayoutManager2;
//            this.recyclerEffectSticker.setLayoutManager(linearLayoutManager2);
//            this.recyclerEffectSticker.setAdapter(this.animationAdapter);
//            AnimationStickerAdapter animationStickerAdapter = this.animationAdapter;
//            if (animationStickerAdapter != null) {
//                animationStickerAdapter.setListenerAnimSticker(this);
//            }
//            loadDataAnim();
//        }
//    }
//
//    private final void addRectStickerToView() {
//        if (!this.listDecor.isEmpty()) {
//            Iterator<Decor> it = this.listDecor.iterator();
//            while (it.hasNext()) {
//                Decor next = it.next();
//                TuSdkMovieColorRectView recoverColorRect = this.mLineView.recoverColorRect(this.context.getResources().getColor(R.color.orange_tran), 0.0f, 1.0f);
//                Intrinsics.checkNotNullExpressionValue(next, "decor");
//                next.setTuSdkMovieColorRectView(recoverColorRect);
//            }
//        }
//    }
//
//    public void onClickAnimSticker(int i) {
//        if (isFocusSticker()) {
//            this.animation = i;
//            Decor decor = this.currentDecor;
//            if (decor != null) {
//                decor.setAnim(i);
//            }
//            startPreviewAnimation(i);
//            return;
//        }
//        AnimationStickerAdapter animationStickerAdapter = this.animationAdapter;
//        if (animationStickerAdapter != null) {
//            animationStickerAdapter.setUnSelectorEffect(i);
//        }
//    }
//
//    private final boolean isFocusSticker() {
//        int i = this.mCurrentPosition;
//        if (i != -1) {
//            CustomDrawView customDrawView = this.mCustomDrawView;
//            Intrinsics.checkNotNull(customDrawView);
//            if (i < customDrawView.getDecor().size()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private final void startPreviewAnimation(int i) {
//        this.mVideoMaker.clearBuffer(false, true, false);
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        if (customDrawView != null) {
//            customDrawView.setVisibility(4);
//        }
//        this.mVideoMaker.removeDraftSticker(this.currentDecor);
//        switch (i) {
//            case 0:
//                Decor decor = this.currentDecor;
//                if (decor != null) {
//                    decor.setAnimation(Animation.NONE);
//                    break;
//                }
//                break;
//            case 1:
//                Decor decor2 = this.currentDecor;
//                if (decor2 != null) {
//                    decor2.setAnimation(Animation.FADE);
//                    break;
//                }
//                break;
//            case 2:
//                Decor decor3 = this.currentDecor;
//                if (decor3 != null) {
//                    decor3.setAnimation(Animation.GROW);
//                    break;
//                }
//                break;
//            case 3:
//                Decor decor4 = this.currentDecor;
//                if (decor4 != null) {
//                    decor4.setAnimation(Animation.FLIP_V);
//                    break;
//                }
//                break;
//            case 4:
//                Decor decor5 = this.currentDecor;
//                if (decor5 != null) {
//                    decor5.setAnimation(Animation.FLIP_H);
//                    break;
//                }
//                break;
//            case 5:
//                Decor decor6 = this.currentDecor;
//                if (decor6 != null) {
//                    decor6.setAnimation(Animation.LANDING);
//                    break;
//                }
//                break;
//            case 6:
//                Decor decor7 = this.currentDecor;
//                if (decor7 != null) {
//                    decor7.setAnimation(Animation.ROTATION);
//                    break;
//                }
//                break;
//            case 7:
//                Decor decor8 = this.currentDecor;
//                if (decor8 != null) {
//                    decor8.setAnimation(Animation.RANDOM_BAR);
//                    break;
//                }
//                break;
//        }
//        this.mVideoMaker.addDraftSticker(this.currentDecor);
//        EditStickerListener editStickerListener2 = this.editStickerListener;
//        if (editStickerListener2 != null) {
//            editStickerListener2.previewAnimSticker();
//        }
//    }
//
//    public final void donePreviewAnimSticker() {
//        this.mVideoMaker.removeAllDraftSticker();
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        if (customDrawView != null) {
//            ExtentionsKt.show(customDrawView);
//        }
//    }
//
//    private final void loadDataAnim() {
//        String[] stringArray = this.context.getResources().getStringArray(R.array.array_text_animation);
//        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ray.array_text_animation)");
//        int length = stringArray.length;
//        for (int i = 0; i < length; i++) {
//            TextAnimation textAnimation = new TextAnimation(stringArray[i], false);
//            if (i == 0) {
//                textAnimation.setSelect(true);
//            }
//            if (i != 1) {
//                this.textAnimationList.add(textAnimation);
//            }
//        }
//        AnimationStickerAdapter animationStickerAdapter = this.animationAdapter;
//        if (animationStickerAdapter != null) {
//            animationStickerAdapter.notifyDataSetChanged();
//        }
//    }
//
//    public void onclickStickerChoose(int i) {
//        chooseSticker(i);
//    }
//
//    private final void chooseSticker(int i) {
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        Intrinsics.checkNotNull(customDrawView);
//        Decor decor = customDrawView.getDecor().get(i);
//        this.currentDecor = decor;
//        if (decor != null) {
//            Intrinsics.checkNotNull(decor);
//            hasNewDecor(decor);
//            CustomDrawView customDrawView2 = this.mCustomDrawView;
//            if (customDrawView2 != null) {
//                customDrawView2.drawFrameSelectedItem(i);
//            }
//            showHideTime(true);
//            CustomDrawView customDrawView3 = this.mCustomDrawView;
//            if (customDrawView3 != null) {
//                customDrawView3.post(new MyStickerController$chooseSticker$1(this));
//            }
//            if (MainFragment.Companion.isPlayingVideo()) {
//                pausePreview();
//            }
//            this.mVideoMaker.removeAllDraftSticker();
//            Decor decor2 = this.currentDecor;
//            Intrinsics.checkNotNull(decor2);
//            previewAtFrameVideo(decor2);
//            this.mCurrentPosition = i;
//            StickerChooseAdapter stickerChooseAdapter = this.mAdapterChoosedSticker;
//            if (stickerChooseAdapter != null) {
//                stickerChooseAdapter.setSelectorSticker(i);
//            }
//            Decor decor3 = this.currentDecor;
//            Intrinsics.checkNotNull(decor3);
//            scrollToAnimSticker(decor3);
//        }
//    }
//
//    private final void previewAtFrameVideo(Decor decor) {
//        float startTime = decor.getStartTime() * ((float) 30);
//        EditStickerListener editStickerListener2 = this.editStickerListener;
//        if (editStickerListener2 != null) {
//            editStickerListener2.seekVideoTo(startTime / ((float) this.myVideoPlayer.getMTotalFrames()));
//        }
//    }
//
//    private final void scrollToAnimSticker(Decor decor) {
//        AnimationStickerAdapter animationStickerAdapter = this.animationAdapter;
//        if (animationStickerAdapter != null) {
//            animationStickerAdapter.updateAnim(decor.getAnim());
//        }
//        RecyclerView.LayoutManager layoutManager2 = this.layoutManager;
//        if (layoutManager2 != null) {
//            layoutManager2.scrollToPosition(decor.getAnim());
//        }
//    }
//
//    public final void clickPauseVideo() {
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        if (customDrawView != null) {
//            customDrawView.post(new MyStickerController$clickPauseVideo$1(this));
//        }
//        CustomDrawView customDrawView2 = this.mCustomDrawView;
//        Intrinsics.checkNotNull(customDrawView2);
//        List<Decor> decor = customDrawView2.getDecor();
//        Intrinsics.checkNotNullExpressionValue(decor, "mCustomDrawView!!.decor");
//        if (!decor.isEmpty()) {
//            chooseSticker(0);
//        }
//    }
//
//    public final void clickPlayVideo() {
//        this.mCurrentPosition = -1;
//        showHideSticker(false);
//        this.mVideoMaker.removeAllDraftSticker();
//        addStickerVideo();
//    }
//
//    private final void pausePreview() {
//        EditStickerListener editStickerListener2 = this.editStickerListener;
//        if (editStickerListener2 != null) {
//            editStickerListener2.pauseVideo();
//        }
//    }
//
//    /* access modifiers changed from: private */
//    public final void onlyPausePreview() {
//        EditStickerListener editStickerListener2 = this.editStickerListener;
//        if (editStickerListener2 != null) {
//            editStickerListener2.onlyPauseVideo();
//        }
//    }
//
//    private final void onlyResumePreview() {
//        EditStickerListener editStickerListener2 = this.editStickerListener;
//        if (editStickerListener2 != null) {
//            editStickerListener2.onlyResumeVideo();
//        }
//    }
//
//    private final void addStickerVideo() {
//        this.mVideoMaker.commitEditSticker();
//        if (this.mVideoMaker.getDecors() != null) {
//            this.mVideoMaker.getDecors().clear();
//            VideoMaker videoMaker = this.mVideoMaker;
//            CustomDrawView customDrawView = this.mCustomDrawView;
//            Intrinsics.checkNotNull(customDrawView);
//            videoMaker.addAllDecor(customDrawView.getDecor());
//        }
//    }
//
//    private final void showHideSticker(boolean z) {
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        if (customDrawView != null) {
//            customDrawView.setVisibility(z ? 0 : 8);
//        }
//        showHideTime(z);
//        setUnSelectorSticker();
//    }
//
//    private final void setUnSelectorSticker() {
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        Intrinsics.checkNotNull(customDrawView);
//        for (Decor next : customDrawView.getDecor()) {
//            Intrinsics.checkNotNullExpressionValue(next, "decor");
//            next.setSelector(false);
//        }
//        StickerChooseAdapter stickerChooseAdapter = this.mAdapterChoosedSticker;
//        if (stickerChooseAdapter != null) {
//            stickerChooseAdapter.notifyDataSetChanged();
//        }
//    }
//
//    public final void clickAddMoreSticker() {
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        if (customDrawView != null) {
//            ExtentionsKt.show(customDrawView);
//        }
//        this.listDecor.clear();
//    }
//
//    public final void addListColorRect() {
//        StickerChooseAdapter stickerChooseAdapter = this.mAdapterChoosedSticker;
//        if (stickerChooseAdapter != null) {
//            stickerChooseAdapter.notifyDataSetChanged();
//        }
//        addRectStickerToView();
//    }
//
//    public final void addSticker(Bitmap bitmap) {
//        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
//        CustomDrawView customDrawView = this.mCustomDrawView;
//        Intrinsics.checkNotNull(customDrawView);
//        this.mCurrentPosition = customDrawView.getDecorCount();
//        CustomDrawView customDrawView2 = this.mCustomDrawView;
//        if (customDrawView2 != null) {
//            customDrawView2.post(new MyStickerController$addSticker$1(this, bitmap));
//        }
//    }
//
//    public void changeSticker(int i, int i2) {
//        if (this.isInitThumb) {
//            this.mCurrentPosition = i2;
//            if (i2 == -1) {
//                this.currentDecor = null;
//                showHideTime(false);
//                return;
//            }
//            chooseSticker(i2);
//        }
//    }
//
//    private final void hasNewDecor(Decor decor) {
//        float mTotalFrames = (float) (this.myVideoPlayer.getMTotalFrames() / 30);
//        float startTime = decor.getStartTime() / mTotalFrames;
//        float endTime = decor.getEndTime() / mTotalFrames;
//        this.mLineView.setLeftBarPosition(startTime);
//        this.mLineView.setRightBarPosition(endTime);
//        showHideTime(true);
//        this.mCurrentColorRectView = decor.getTuSdkMovieColorRectView();
//    }
//
//    private final void showHideTime(boolean z) {
//        AnimationStickerAdapter animationStickerAdapter;
//        this.mLineView.setShowSelectBar(z);
//        if (!z && (animationStickerAdapter = this.animationAdapter) != null) {
//            animationStickerAdapter.setUnSelectorEffect();
//        }
//    }
//
//    public void onDeleteItem(int i) {
//        C2671Lo.m319d("delete item " + i);
//    }
//}
