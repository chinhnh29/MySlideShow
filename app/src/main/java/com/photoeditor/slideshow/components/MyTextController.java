//package com.photoeditor.slideshow.components;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.graphics.Typeface;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.gomin.slideshowmaker.videoeditor.videomaker.musicvideo.R;
//import com.photoeditor.slideshow.adapter.BaseAdapter;
//import com.photoeditor.slideshow.adapter.TextChooseAdapter;
//import com.photoeditor.slideshow.customView.CustomDrawView;
//import com.photoeditor.slideshow.imagetovideo.Decor;
//import com.photoeditor.slideshow.imagetovideo.DecorText;
//import com.photoeditor.slideshow.imagetovideo.OnHandlerItemListener;
//import com.photoeditor.slideshow.imagetovideo.VideoMaker;
//import com.photoeditor.slideshow.interfaces.EditStickerListener;
//import com.photoeditor.slideshow.interfaces.OnChangeFocusSticker;
//
//import java.util.ArrayList;
//
//import kotlin.jvm.internal.Intrinsics;
//
////@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010#\u001a\u00020$H\u0002J\u0018\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u001eH\u0016J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001eH\u0016J\u0006\u0010*\u001a\u00020$J\u0010\u0010+\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001eH\u0016J\u0010\u0010,\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001eH\u0016J\b\u0010-\u001a\u00020$H\u0016J\b\u0010.\u001a\u00020$H\u0002J\b\u0010/\u001a\u00020$H\u0002J\b\u00100\u001a\u00020$H\u0002J\u0010\u00101\u001a\u00020$2\u0006\u00102\u001a\u00020 H\u0002J\b\u00103\u001a\u00020$H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000¨\u00064"}, mo49141d2 = {"Lcom/photoeditor/slideshow/components/MyTextController;", "Lcom/photoeditor/slideshow/imagetovideo/OnHandlerItemListener;", "Lcom/photoeditor/slideshow/interfaces/OnChangeFocusSticker;", "Lcom/photoeditor/slideshow/adapter/BaseAdapter$OnItemSelected;", "context", "Landroid/content/Context;", "mCustomDrawView", "Lcom/photoeditor/slideshow/customView/CustomDrawView;", "mVideoMaker", "Lcom/photoeditor/slideshow/imagetovideo/VideoMaker;", "rcChooseText", "Landroidx/recyclerview/widget/RecyclerView;", "myVideoPlayer", "Lcom/photoeditor/slideshow/components/MyVideoPlayer;", "(Landroid/content/Context;Lcom/photoeditor/slideshow/customView/CustomDrawView;Lcom/photoeditor/slideshow/imagetovideo/VideoMaker;Landroidx/recyclerview/widget/RecyclerView;Lcom/photoeditor/slideshow/components/MyVideoPlayer;)V", "currentListTextChoose", "Ljava/util/ArrayList;", "Lcom/photoeditor/slideshow/imagetovideo/DecorText;", "Lkotlin/collections/ArrayList;", "editStickerListener", "Lcom/photoeditor/slideshow/interfaces/EditStickerListener;", "getEditStickerListener", "()Lcom/photoeditor/slideshow/interfaces/EditStickerListener;", "setEditStickerListener", "(Lcom/photoeditor/slideshow/interfaces/EditStickerListener;)V", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mBitmap", "Landroid/graphics/Bitmap;", "mCurrentPosition", "", "mText", "", "textChooseAdapter", "Lcom/photoeditor/slideshow/adapter/TextChooseAdapter;", "addText", "", "changeSticker", "oldP", "newP", "clickItem", "position", "clickModeText", "onDeleteItem", "onSelected", "onZoomItem", "pausePreview", "removeDrawEditText", "setUnSelectorText", "stringToBitMap", "s", "touchItem", "slideshow_release"}, mo49142k = 1, mo49143mv = {1, 4, 0})
/////* compiled from: MyTextController.kt */
//public final class MyTextController implements OnHandlerItemListener, OnChangeFocusSticker, BaseAdapter.OnItemSelected {
//    /* access modifiers changed from: private */
//    public final Context context;
//    /* access modifiers changed from: private */
//    public ArrayList<DecorText> currentListTextChoose = new ArrayList<>();
//    private EditStickerListener editStickerListener;
//    /* access modifiers changed from: private */
//    public LinearLayoutManager layoutManager;
//    /* access modifiers changed from: private */
//    public Bitmap mBitmap;
//    private int mCurrentPosition = -1;
//    /* access modifiers changed from: private */
//    public final CustomDrawView mCustomDrawView;
//    /* access modifiers changed from: private */
//    public String mText = "";
//    private final VideoMaker mVideoMaker;
//    /* access modifiers changed from: private */
//    public final MyVideoPlayer myVideoPlayer;
//    private final RecyclerView rcChooseText;
//    /* access modifiers changed from: private */
//    public TextChooseAdapter textChooseAdapter;
//
//    public void changeSticker(int i, int i2) {
//    }
//
//    public void clickItem(int i) {
//    }
//
//    public void onDeleteItem(int i) {
//    }
//
//    public void onSelected(int i) {
//    }
//
//    public void onZoomItem() {
//    }
//
//    public void touchItem() {
//    }
//
//    public MyTextController(Context context2, CustomDrawView customDrawView, VideoMaker videoMaker, RecyclerView recyclerView, MyVideoPlayer myVideoPlayer2) {
//        Intrinsics.checkNotNullParameter(context2, "context");
//        Intrinsics.checkNotNullParameter(customDrawView, "mCustomDrawView");
//        Intrinsics.checkNotNullParameter(videoMaker, "mVideoMaker");
//        Intrinsics.checkNotNullParameter(recyclerView, "rcChooseText");
//        Intrinsics.checkNotNullParameter(myVideoPlayer2, "myVideoPlayer");
//        this.context = context2;
//        this.mCustomDrawView = customDrawView;
//        this.mVideoMaker = videoMaker;
//        this.rcChooseText = recyclerView;
//        this.myVideoPlayer = myVideoPlayer2;
//        customDrawView.setOnHandlerItemListener(this);
//        customDrawView.setOnChangeFocusSticker(this);
//        customDrawView.clearData();
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
//    public final void clickModeText() {
//        if (this.textChooseAdapter == null) {
//            TextChooseAdapter textChooseAdapter2 = new TextChooseAdapter(this.context, this.currentListTextChoose);
//            this.textChooseAdapter = textChooseAdapter2;
//            if (textChooseAdapter2 != null) {
//                textChooseAdapter2.setOnItemSelected(this);
//            }
//        }
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context, 0, false);
//        this.layoutManager = linearLayoutManager;
//        this.rcChooseText.setLayoutManager(linearLayoutManager);
//        TextChooseAdapter textChooseAdapter3 = this.textChooseAdapter;
//        if (textChooseAdapter3 != null) {
//            this.rcChooseText.setAdapter(textChooseAdapter3);
//        }
//        this.mCurrentPosition = 0;
//        addText();
//    }
//
//    private final void addText() {
//        removeDrawEditText();
//        pausePreview();
//        String string = this.context.getResources().getString(R.string.text_your_text_here);
//        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ring.text_your_text_here)");
//        stringToBitMap(string);
//        this.mCustomDrawView.setVisibility(0);
//        setUnSelectorText();
//        this.mCustomDrawView.post(new MyTextController$addText$1(this));
//        this.mCurrentPosition = this.mCustomDrawView.getDecorCount();
//    }
//
//    private final void setUnSelectorText() {
//        for (Decor next : this.mCustomDrawView.getDecor()) {
//            Intrinsics.checkNotNullExpressionValue(next, "decor");
//            next.setSelector(false);
//        }
//        TextChooseAdapter textChooseAdapter2 = this.textChooseAdapter;
//        if (textChooseAdapter2 != null) {
//            textChooseAdapter2.notifyDataSetChanged();
//        }
//    }
//
//    private final void stringToBitMap(String str) {
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setAntiAlias(true);
//        paint.setTextSize(72.0f);
//        paint.setColor(-1);
//        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
//        Rect rect = new Rect();
//        paint.getTextBounds(str, 0, str.length(), rect);
//        this.mBitmap = Bitmap.createBitmap(rect.width() + 80, rect.height() + 40, Bitmap.Config.ARGB_8888);
//        Bitmap bitmap = this.mBitmap;
//        Intrinsics.checkNotNull(bitmap);
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawText(str, 40.0f, (float) (canvas.getHeight() - 20), paint);
//    }
//
//    private final void pausePreview() {
//        EditStickerListener editStickerListener2 = this.editStickerListener;
//        if (editStickerListener2 != null) {
//            editStickerListener2.pauseVideo();
//        }
//    }
//
//    private final void removeDrawEditText() {
//        this.mVideoMaker.removeAllDraftText();
//    }
//}
