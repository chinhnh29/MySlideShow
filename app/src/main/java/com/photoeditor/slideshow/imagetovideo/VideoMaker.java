package com.photoeditor.slideshow.imagetovideo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;

import androidx.work.WorkRequest;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieListener;
import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.enumm.VIDEO_RATIO;
import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.common.AppConst;
import com.photoeditor.slideshow.common.MyData;
//import com.photoeditor.slideshow.java.PhotorTool;
//import com.photoeditor.slideshow.models.DrafVideoModel;
import com.photoeditor.slideshow.java.PhotorTool;
import com.photoeditor.slideshow.models.GifImage;
import com.photoeditor.slideshow.models.GifTheme;
import com.photoeditor.slideshow.models.GifTransition;
import com.photoeditor.slideshow.models.ImageModel;
//import com.photoeditor.slideshow.models.LocalSong;
import com.photoeditor.slideshow.models.MyGif;
import com.photoeditor.slideshow.models.ThemeLottieModel;
//import com.photoeditor.slideshow.models.ThemeModel;
//import com.photoeditor.slideshow.models.TransitionDrawableModel;
import com.photoeditor.slideshow.models.TransitionDrawableModel;
import com.photoeditor.slideshow.models.main.TransitionDrawModel;
import com.photoeditor.slideshow.models.main.TransitionJsonModel;
import com.photoeditor.slideshow.my_slide_show.obj.Transit;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.common.BitmapResult;

import net.lingala.zip4j.util.InternalZipConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import jp.wasabeef.blurry.internal.Blur;
import jp.wasabeef.blurry.internal.BlurFactor;


public class VideoMaker {
    public static float DURATION_TRANSITION = 1.5f;
    public static final int FPS = 30;
    public static int HEIGHT_PREVIEW = 0;
    private static final String TAG = "dsk";
    private static int VIDEO_HEIGHT = 1080;
    private static int VIDEO_WIDTH = 1920;
    public static float VOLUME = 1.0f;
    public static int WIDTH_PREVIEW;
    private static VideoMaker instance;
    public int DURATION_IMAGE = 2;
    private Activity activity;
    private float arX = 1.0f;
    private float arY = 1.0f;
    public Bitmap bitmapWatermark;
    private int bmIdNew = 0;
    private Context context;
    private GifTheme currentThemeModel = new GifTheme("none", "none", "classic", R.color.trans, true,
            (Boolean) null, Transition.NONE);
    private float currentTile = 1.0f;
    private GifTransition currentTransition = new GifTransition("none", "none", "classic", R.color.trans, true,
            (Boolean) null, Transition.NONE);
    private VIDEO_RATIO currentVideoRatio = VIDEO_RATIO.MOT_MOT;
    private Rect desRect = new Rect();
    private int end;
    private BlurFactor factor;
    private int frameDraw = 0;
    private HashMap<String, List<Bitmap>> hashMapBitmapDraw = new HashMap<>();
    private HashMap<String, GifImage> hashMapGif = new HashMap<>();
    private HashMap<String, Movie> hashMapMovie = new HashMap<>();
    private int heightWatermark = 0;
    private String idWatermark;
    private ImageView imgWatermark;
    private File inputAudio;
    private boolean isGif = false;
    private ArrayList<TransitionDrawModel> listDrawModel;
    private File[] listFile;
    private ArrayList<GifImage> listGifImage = new ArrayList<>();
    private ArrayList<GifImage> listGifThemes = new ArrayList<>();
    private List<MyGif> listGifToDraw = new ArrayList();
    private List<ImageModel> listImageModel = new ArrayList();
    private List<ThemeLottieModel> listThemeLottieModel = new ArrayList();
    private ArrayList<TransitionJsonModel> listTransitionJson = new ArrayList<>();
    private ArrayList<TransitionJsonModel> listTransitionJsonToRandom;
    private List<GifTransition> listTransitionModel = new ArrayList<>();
    private OnFinishEncoderListener listener;
    LottieAnimationView lottieAnimationView;
    private LottieDrawable lottieDrawableTransition;
    LottieAnimationView lottieTransition;
    private SparseArray<Bitmap> mBufferImage = new SparseArray<>();
    private SparseArray<Bitmap> mBufferSticker = new SparseArray<>();
    private SparseArray<Bitmap> mBufferTextSticker = new SparseArray<>();
    //    private List<Decor> mDecorBackupList = new ArrayList();
//    private List<Decor> mDecorList = new ArrayList();
    private List<Effect> mEffectList = new ArrayList();
    private EffectUtils mEffectUtils = new EffectUtils(this);
    private int mEndTimeAudio;
    private InputStream mInputStream;
    public boolean mIsPremium = false;
    public boolean mIsUnlockMate = false;
    private Matrix mLastMatrix = new Matrix();
    private MakeVideoUtils mMakeVideoUtils;
    private Movie mMovie;
    private Paint mPaintImage = new Paint(2);
    private int mStartTimeAudio;
    //    private List<DecorText> mTextList = new ArrayList();
    private List mThemeContainer = new ArrayList();
    private TransitionUtils mTransitionUtils = new TransitionUtils(this);
    private String oldTransitionPath = "";
    private Tiny.BitmapCompressOptions options = new Tiny.BitmapCompressOptions();
    private Random random = new Random();
    //    private LocalSong song;
    private Rect srcRect = new Rect();
    private int start;
    public int totalFrame = 1;
    public int totalFrameTransition = 0;
    private TransitionDrawableModel transitionDrawableModel;
    private MyData myData;

    public void setProcessing(boolean z) {
    }

    public void setMode(int i) {
        VIDEO_WIDTH = i;
        VIDEO_HEIGHT = i;
        this.arX = (((float) i) * 1.0f) / ((float) WIDTH_PREVIEW);
        this.arY = (((float) i) * 1.0f) / ((float) HEIGHT_PREVIEW);
    }

    /* access modifiers changed from: package-private */
    public int getVideoWidth() {
        return ((Integer) Hawk.get(AppConst.KEY_QUALITY, 1080)).intValue();
    }

    /* access modifiers changed from: package-private */
    public int getVideoHeight() {
        return (int) (((float) ((Integer) Hawk.get(AppConst.KEY_QUALITY, 1080)).intValue()) /
                getTileFromData(this.currentVideoRatio));
    }

    public static VideoMaker getInstance() {
        if (instance == null) {
            instance = new VideoMaker();
        }
        return instance;
    }

    private VideoMaker() {
        int intValue = (Hawk.get(AppConst.KEY_QUALITY, 1080));
        VIDEO_WIDTH = intValue;
        VIDEO_HEIGHT = intValue;
        BlurFactor blurFactor = new BlurFactor();
        this.factor = blurFactor;
        blurFactor.radius = 25;
    }

    public void setImage(ImageView imageView) {
        this.imgWatermark = imageView;
    }

    public void setIdWatermark(String str) {
        this.idWatermark = str;
    }

    public void commitEditSticker() {
//        clearBuffer(false, true, false);
//        this.mDecorBackupList.clear();
    }

//    public static String getDefaultAudioPath() {
//        return GlobalDef.FOLDER_AUDIO + GlobalDef.DEFAULT_MUSIC;
//    }

    public void setContext(Context context2) {
        this.context = context2;
        this.mMakeVideoUtils = new MakeVideoUtils(context2, this.inputAudio, this.listener);
    }

    public void setContext(Activity activity2, Context context2, LottieAnimationView lottieAnimationView2) {
        this.context = context2;
        this.activity = activity2;
        this.mMakeVideoUtils = new MakeVideoUtils(context2, this.inputAudio, this.listener);
        this.lottieAnimationView = new LottieAnimationView(activity2);
    }

    public void addAudio(String str) {
        this.inputAudio = new File(str);
    }

//    public void removeAllDraftSticker() {
//        this.mDecorList.clear();
//    }

    public List<GifImage> getListGifImage() {
        return this.listGifImage;
    }

    public void addImages2(ArrayList<String> arrayList) {
        float duration = (float) this.DURATION_IMAGE;
        this.listTransitionModel.clear();
        this.listImageModel.clear();
        this.mEffectList.clear();
        this.mBufferImage.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setUrl(arrayList.get(i));
            imageModel.setSecond(duration);
            this.listImageModel.add(imageModel);
            this.mEffectList.add(Effect.values()[5]);
            this.listTransitionModel.add(
                    new GifTransition(Transition.NONE)
            );
        }
    }

    public void addImagesGif(ArrayList<String> listImage) {
        this.isGif = true;
        this.DURATION_IMAGE = 3;
        float second = 3;
        this.listTransitionModel.clear();
        this.listImageModel.clear();
        this.mEffectList.clear();
        this.mBufferImage.clear();
        int size = listImage.size();
        for (int i = 0; i < size; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setUrl(listImage.get(i));
            imageModel.setSecond(second);
            this.listImageModel.add(imageModel);
            this.mEffectList.add(Effect.values()[5]); //zoom
//            this.mEffectList.add(Effect.values()[this.random.nextInt(Effect.values().length)]);
            this.listTransitionModel.add(
                    new GifTransition("Slide R", "Slide R", "classic", R.drawable.slide_r, true, null, Transition.SLIDE_RIGHT)
            );
        }
    }

    public void hasNewImage(ArrayList<String> arrayList) {
        this.bmIdNew = 0;
        this.mBufferImage.clear();
        float second = this.listImageModel.get(0).getSecond();
        GifTransition gifTransition = this.listTransitionModel.get(0);
        this.listTransitionModel.clear();
        this.listImageModel.clear();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            ImageModel imageModel = new ImageModel();
            imageModel.setUrl(it.next());
            imageModel.setSecond(second);
            this.listImageModel.add(imageModel);
            this.mEffectList.add(Effect.values()[this.random.nextInt(Effect.values().length)]);
            int i = C26701.$SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition[this.currentTransition.getType().ordinal()];
            if (i == 1) {
                this.listTransitionModel.add(getRandomClassicTran());
            } else if (i == 2) {
                this.listTransitionModel.add(getRandomMateTran());
            } else if (i != 3) {
                this.listTransitionModel.add(gifTransition);
            }
        }
        if (this.currentTransition.getType().ordinal() == 3) {
            updateRandomTransitionDraw();
        }
        this.totalFrame = getTotalFrames();
        updateTimeVideo();
        if (!listTransitionJson.isEmpty()) {
            updateTransitionJson();
        }
    }

//    public void addVideoModel(DrafVideoModel drafVideoModel) {
//        this.listTransitionModel.clear();
//        this.listImageModel.clear();
//        this.mEffectList.clear();
//        this.mBufferImage.clear();
//        List<ImageModel> listImage = drafVideoModel.getListImage();
//        this.listImageModel = listImage;
//        try {
//            this.DURATION_IMAGE = (int) listImage.get(0).getSecond();
//        } catch (Exception e) {
//            e.printStackTrace();
//            this.DURATION_IMAGE = 3;
//        }
//        for (ImageModel next : this.listImageModel) {
//            this.mEffectList.add(Effect.values()[this.random.nextInt(Effect.values().length)]);
//            this.listTransitionModel.add(drafVideoModel.getTransition());
//        }
//    }

    public List<GifTransition> getListTransitionModel() {
        return this.listTransitionModel;
    }

    private Bitmap getImage(int index, int widthPreview, int heightPreview) {
        if (index >= this.listImageModel.size()) {
            return null;
        }
        BitmapResult compressSync = Tiny.getInstance().source(this.listImageModel.get(index).getUrl()).asBitmap().withOptions(this.options).compressSync();
        if (compressSync.success) {
            return scalePreserveRatio(compressSync.bitmap, widthPreview, heightPreview);
        }
        Bitmap decodeScaledBitmapFromSdCard = decodeScaledBitmapFromSdCard(this.listImageModel.get(index).getUrl(), WIDTH_PREVIEW, HEIGHT_PREVIEW);
        if (decodeScaledBitmapFromSdCard == null) {
            decodeScaledBitmapFromSdCard = Bitmap.createBitmap(WIDTH_PREVIEW, HEIGHT_PREVIEW, Bitmap.Config.ARGB_8888);
            new Canvas(decodeScaledBitmapFromSdCard).drawColor(-1);
        }
        this.listImageModel.get(index).setOriginBitmap(decodeScaledBitmapFromSdCard);
        return scalePreserveRatio(decodeScaledBitmapFromSdCard, widthPreview, heightPreview);
    }

    private Bitmap scalePreserveRatio(Bitmap bitmap, int i, int i2) {
        Bitmap bitmap2 = bitmap;
        int i3 = i;
        int i4 = i2;
        if (i4 <= 0 || i3 <= 0 || bitmap2 == null) {
            return bitmap2;
        }
        float f = (float) i3;
        float width = (float) bitmap.getWidth();
        float f2 = f / width;
        float f3 = (float) i4;
        float height = (float) bitmap.getHeight();
        float f4 = f3 / height;
        int floor = (int) Math.floor((double) (width * f2));
        int floor2 = (int) Math.floor((double) (f2 * height));
        if (floor > i3 || floor2 > i4) {
            floor = (int) Math.floor((double) (width * f4));
            floor2 = (int) Math.floor((double) (height * f4));
        }
        this.factor.width = bitmap.getWidth();
        this.factor.height = bitmap.getHeight();
        Bitmap of = Blur.of(this.context, bitmap2, this.factor);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, floor, floor2, false);
        Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(-16777216);
        paint.setStyle(Paint.Style.FILL);
        Canvas canvas2 = canvas;
        canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), paint);
        if (of == null) {
            return createScaledBitmap;
        }
        int width2 = of.getWidth();
        int height2 = of.getHeight();
        if (width2 >= height2) {
            width2 = height2;
        }
        float tileFromData = getTileFromData(this.currentVideoRatio);
        if (tileFromData < 1.0f) {
            width2 = (int) (((float) width2) * tileFromData);
        }
        Canvas canvas3 = canvas2;
        canvas3.drawBitmap(of, new Rect(0, 0, width2, (int) (((float) width2) / tileFromData)), new Rect(0, 0, i3, i4), (Paint) null);
        float f5 = ((float) floor) / ((float) floor2);
        float f6 = f / f3;
        float f7 = 0.0f;
        float f8 = f5 >= f6 ? 0.0f : ((float) (i3 - floor)) / 2.0f;
        if (f5 >= f6) {
            f7 = ((float) (i4 - floor2)) / 2.0f;
        }
        canvas3.drawBitmap(createScaledBitmap, f8, f7, (Paint) null);
        return createBitmap;
    }

    /* renamed from: com.photoeditor.slideshow.imagetovideo.VideoMaker$1 */
    public static /* synthetic */ class C26701 {
        static final /* synthetic */ int[] $SwitchMap$com$photoeditor$slideshow$enumm$VIDEO_RATIO;
        static final /* synthetic */ int[] $SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition;

        static {
            int[] iArr = new int[VIDEO_RATIO.values().length];
            $SwitchMap$com$photoeditor$slideshow$enumm$VIDEO_RATIO = iArr;
            try {
                iArr[VIDEO_RATIO.CHIN_MUOI_SAU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$photoeditor$slideshow$enumm$VIDEO_RATIO[VIDEO_RATIO.MUOI_SAU_CHIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$photoeditor$slideshow$enumm$VIDEO_RATIO[VIDEO_RATIO.BON_BA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$photoeditor$slideshow$enumm$VIDEO_RATIO[VIDEO_RATIO.BA_BON.ordinal()] = 4;
            $SwitchMap$com$photoeditor$slideshow$enumm$VIDEO_RATIO[VIDEO_RATIO.MOT_MOT.ordinal()] = 5;
            int[] iArr2 = new int[Transition.values().length];
            $SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition = iArr2;
            iArr2[Transition.RANDOM_CLASSIC.ordinal()] = 1;
            $SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition[Transition.RANDOM_MATE.ordinal()] = 2;
            $SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition[Transition.RANDOM_DRAW.ordinal()] = 3;
            $SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition[Transition.IMAGE.ordinal()] = 4;
        }
    }

    private float getTileFromData(VIDEO_RATIO video_ratio) {
        int i = video_ratio.ordinal();
        if (i == 1) {
            this.currentTile = 0.5625f;
        } else if (i == 2) {
            this.currentTile = 1.7777778f;
        } else if (i == 3) {
            this.currentTile = 1.3333334f;
        } else if (i == 4) {
            this.currentTile = 0.75f;
        }
        return this.currentTile;
    }

    public float getTileFromData() {
        int i = this.currentVideoRatio.ordinal();
        if (i == 1) {
            return 0.5625f;
        }
        if (i == 2) {
            return 1.7777778f;
        }
        if (i != 3) {
            return i != 4 ? 1.0f : 0.75f;
        }
        return 1.3333334f;
    }

    private static Bitmap decodeScaledBitmapFromSdCard(String str, int i, int i2) {
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options2);
        options2.inSampleSize = calculateInSampleSize(options2, i, i2);
        int i3 = 0;
        options2.inJustDecodeBounds = false;
        Matrix matrix = new Matrix();
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                i3 = 180;
            } else if (attributeInt == 6) {
                i3 = 90;
            } else if (attributeInt == 8) {
                i3 = 270;
            }
            matrix.setRotate((float) i3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options2);
        if (i3 == 0) {
            return decodeFile;
        }
        return Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options2, int i, int i2) {
        int i3 = options2.outHeight;
        int i4 = options2.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i2));
        int round2 = Math.round(((float) i4) / ((float) i));
        return round < round2 ? round : round2;
    }

    /* access modifiers changed from: package-private */
    public Bitmap getLastImage(int i) {
        if (i == 0) {
            return this.mBufferImage.get(0);
        }
        return this.mBufferImage.get(i - 1);
    }

    private void updateImageCache(int index, int widthPreview, int heightPreview) {
        if (this.mBufferImage.get(index) == null) {
            this.mBufferImage.put(index, getImage(index, widthPreview, heightPreview));
            int i4 = index + 1;
            if (i4 < this.listImageModel.size()) {
                new Thread(() -> {
                    if (mBufferImage.get(index) == null) {
                        try {
                            mBufferImage.put(index, getImage(index, widthPreview, heightPreview));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }
        }
    }

//    public void removeAllDraftText() {
//        this.mTextList.clear();
//    }

    public void removeTheme() {
//        List list = this.mThemeContainer;
//        if (list != null && list.size() != 0) {
//            for (int size = this.mThemeContainer.size() - 1; size >= 0; size--) {
//                Object obj = this.mThemeContainer.get(size);
//                if (obj instanceof DecorText) {
//                    this.mTextList.remove((DecorText) obj);
//                } else if (obj instanceof Decor) {
//                    this.mDecorList.remove((Decor) obj);
//                }
//                this.mThemeContainer.remove(obj);
//            }
//            clearBuffer(true, true, true);
//        }
    }

//    public void setTheme(ThemeModel themeModel) {
//        if (themeModel == null) {
//            this.listGifToDraw.clear();
//        }
//    }

    /* access modifiers changed from: package-private */
    public void previewImage(Canvas canvas, int currentFrame) {
        drawImages(canvas, currentFrame, (int) (((float) WIDTH_PREVIEW) * this.arX), (int) (((float) HEIGHT_PREVIEW) * this.arY));
//        drawTransitionJson(canvas, currentFrame, (int) (((float) WIDTH_PREVIEW) * this.arX), (int) (((float) HEIGHT_PREVIEW) * this.arY));
//        drawThemeNew(canvas, currentFrame, (int) (((float) WIDTH_PREVIEW) * this.arX), (int) (((float) HEIGHT_PREVIEW) * this.arY));
    }

    /* access modifiers changed from: package-private */
    public void drawWatermark(Canvas canvas, int i, int i2) {
        ImageView imageView;
        if (!(this.bitmapWatermark != null || (imageView = this.imgWatermark) == null || imageView.getDrawable() == null)) {
            this.bitmapWatermark = ((BitmapDrawable) this.imgWatermark.getDrawable()).getBitmap();
        }
        if (this.bitmapWatermark != null) {
            this.srcRect.left = 0;
            this.srcRect.top = 0;
            this.srcRect.right = this.bitmapWatermark.getWidth();
            this.srcRect.bottom = this.bitmapWatermark.getHeight();
            this.desRect.left = i / 30;
            this.desRect.bottom = (i2 * 29) / 30;
            this.desRect.top = (i2 * 26) / 30;
            this.heightWatermark = this.desRect.bottom - this.desRect.top;
            Rect rect = this.desRect;
            rect.right = rect.left + ((this.heightWatermark * this.bitmapWatermark.getWidth()) / this.bitmapWatermark.getHeight());
            canvas.drawBitmap(this.bitmapWatermark, this.srcRect, this.desRect, (Paint) null);
        }
    }

//    public List<Decor> getDecors() {
//        return this.mDecorList;
//    }

//    public void addAllDecor(List<Decor> list) {
//        this.mDecorList.addAll(list);
//    }

//    public void addDraftSticker(Decor decor) {
//        decor.setStartTime(0.0f);
//        decor.setEndTime(getTotalDuration());
//        this.mDecorList.add(decor);
//    }

//    public void removeDraftSticker(Decor decor) {
//        this.mDecorList.remove(decor);
//    }

    public void clearBuffer(boolean z, boolean z2, boolean z3) {
//        if (z) {
//            List<ImageModel> list = this.listImageModel;
//            int size = list != null ? list.size() : 0;
//            for (int i = 0; i < size; i++) {
//                while (this.mBufferImage.get(i) != null) {
//                    this.mBufferImage.remove(i);
//                }
//            }
//            this.mBufferImage.clear();
//        }
//        if (z2) {
//            List<Decor> list2 = this.mDecorList;
//            int size2 = list2 != null ? list2.size() : 0;
//            for (int i2 = 0; i2 < size2; i2++) {
//                while (this.mBufferSticker.get(i2) != null) {
//                    this.mBufferSticker.remove(i2);
//                }
//            }
//            this.mBufferSticker.clear();
//            List<DecorText> list3 = this.mTextList;
//            int size3 = list3 != null ? list3.size() : 0;
//            for (int i3 = 0; i3 < size3; i3++) {
//                while (this.mBufferTextSticker.get(i3) != null) {
//                    this.mBufferTextSticker.remove(i3);
//                }
//            }
//            this.mBufferTextSticker.clear();
//        }
    }

    /* access modifiers changed from: package-private */
    public void generateFrame(Canvas canvas, int i, int i2, int i3) {
//        canvas.drawColor(-16777216);
//        drawImages(canvas, i, i2, i3);
//        drawTransitionJson(canvas, i, i2, i3);
//        drawThemeNew(canvas, i, i2, i3);
//        drawWatermark(canvas, i2, i3);
    }

    private void drawThemeNew(Canvas canvas, int i, int i2, int i3) {
        if (listThemeLottieModel != null && listThemeLottieModel.size() > 0) {
            for (ThemeLottieModel themeLottieModel : this.listThemeLottieModel) {
                if ((i > themeLottieModel.getStart() && i <= themeLottieModel.getEnd()) ||
                        (i > themeLottieModel.getStart2() && i <= themeLottieModel.getEnd2())) {
                    this.frameDraw = i;
                    if (i > themeLottieModel.getMaxFrame()) {
                        this.frameDraw = i % themeLottieModel.getMaxFrame();
                    }
                    try {
                        this.lottieAnimationView.setComposition(themeLottieModel.getLottieComposition());
                        LottieDrawable lottieDrawable = (LottieDrawable) this.lottieAnimationView.getDrawable();
                        lottieDrawable.setProgress(((float) this.frameDraw) / lottieDrawable.getMaxFrame());
                        drawLottieDrawable(lottieDrawable, canvas, i2, i3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void drawTransitionJson(Canvas canvas, int currentFrame, int widthPreview, int heightPreview) {
        int startFrame;
        if (lottieTransition == null) {
            lottieTransition = new LottieAnimationView(context);
        }
        if (!listTransitionJson.isEmpty()) {
            try {
                for (TransitionJsonModel transitionJsonModel : listTransitionJson) {
                    if (transitionJsonModel.getComposition() != null && transitionJsonModel.getStartFrame() >= 0 && transitionJsonModel.getEndFrame() >= 0 &&
                            currentFrame >= transitionJsonModel.getStartFrame() && currentFrame <= transitionJsonModel.getEndFrame() && (startFrame = currentFrame - transitionJsonModel.getStartFrame()) >= 0) {
                        lottieTransition.setComposition(transitionJsonModel.getComposition());
                        LottieDrawable lottieDrawable = (LottieDrawable) this.lottieTransition.getDrawable();
                        lottieDrawable.setProgress(((float) startFrame) / lottieDrawable.getMaxFrame());
                        lottieDrawable.setFrame(startFrame);
                        drawLottieDrawable(lottieDrawable, canvas, widthPreview, heightPreview);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void drawLottieDrawable(LottieDrawable lottieDrawable, Canvas canvas, int i, int i2) {
        float intrinsicWidth = ((float) i) / ((float) lottieDrawable.getIntrinsicWidth());
        float intrinsicHeight = ((float) i2) / ((float) lottieDrawable.getIntrinsicHeight());
        float max = Math.max(intrinsicWidth, intrinsicHeight);
        canvas.save();
        canvas.translate(((intrinsicWidth - max) * ((float) lottieDrawable.getIntrinsicWidth())) / 2.0f, ((intrinsicHeight - max) * ((float) lottieDrawable.getIntrinsicHeight())) / 2.0f);
        canvas.scale(max, max);
        lottieDrawable.draw(canvas);
        canvas.restore();
    }

    public void removeGif(String str) {
        if (str != null && this.listGifImage != null) {
            GifImage remove = this.hashMapGif.remove(str);
            if (!(remove == null || remove.getPath() == null)) {
                this.hashMapMovie.remove(remove.getPath());
            }
            for (int i = 0; i < this.listGifImage.size(); i++) {
                if (this.listGifImage.get(i).f506id.equals(str)) {
                    this.listGifImage.remove(i);
                    return;
                }
            }
        }
    }


    public GifTheme getCurrentThemeModel() {
        return this.currentThemeModel;
    }

    public void editAudio(boolean z, boolean z2) {
        this.mMakeVideoUtils.setInputAudio(this.inputAudio);
        this.mMakeVideoUtils.editAudio(z, z2);
    }

    public void makeVideo(Handler handler, boolean z, boolean z2, boolean z3, boolean z4) {
        this.mMakeVideoUtils.makeVideo(handler, z, z2, z3, z4);
    }

    public void stopMakerVideo() {
        mMakeVideoUtils.stopVideoProcess();
        setProcessing(false);
    }

    private void drawImages(Canvas canvas, int currentFrame, int widthPreview, int heightPreview) {
        int i4;
        int indexImageModel = 0;
        while (indexImageModel < this.listImageModel.size()) { // duyệt danh sách hình ảnh
            if (checkImageToDraw(indexImageModel, currentFrame)) { //kiểm tra hình ảnh xem đã vẽ chưa
                updateImageCache(indexImageModel, widthPreview, heightPreview); //nếu chưa thì update vào buffer để vẽ

                //xóa bớt bitmap trong danh sách đi để đỡ tốn bộ nhớ
                if (this.mBufferImage.size() >= 3) {
                    for (int i = 0; i <= indexImageModel - 3; i++) {
                        Bitmap bitmap = this.mBufferImage.get(i);
                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                        this.mBufferImage.remove(i);
                    }
                }
                Bitmap bitmap = this.mBufferImage.get(indexImageModel);
                if (bitmap != null) {
                    Matrix matrix = new Matrix();
                    Matrix matrix2 = matrix;

                    //zoom nhẹ lên để hiển thị trước trong khi chờ chuyển cảnh
                    this.mEffectUtils.effect(this.mEffectList.get(indexImageModel),
                            matrix, bitmap, indexImageModel, currentFrame, widthPreview, heightPreview);
                    if (TimeUtils.checkEndTime2(currentFrame, this.listImageModel.get(indexImageModel).getSecond(), getStartFrame(indexImageModel))) {
                        this.mLastMatrix = matrix2;
                    }
                    if (indexImageModel > 0) {
                        this.bmIdNew = indexImageModel - 1;
                    }
                    if (this.bmIdNew < this.listTransitionModel.size()) {
                        if (!this.isGif) {
                            int i8 = 255;
                            if (currentFrame <= 30) {
                                i8 = ((currentFrame * 200) / 30) + 55;
                            } else {
                                if (currentFrame > this.totalFrame - 30) {
                                    i8 = 255 - ((((currentFrame - this.totalFrame) + 30) * 200) / 30);
                                }
                            }
                            this.mPaintImage.setAlpha(i8);
                        }
                        if (this.listTransitionModel.get(this.bmIdNew) == null || this.listTransitionModel.get(this.bmIdNew).getType() == null) {
//                            i4 = index;
                        } else {

                            //Kiểm tra xem có phải vẽ hình ảnh hay không?
                            GifTransition gifTransition = this.listTransitionModel.get(this.bmIdNew);
                            if (!this.hashMapBitmapDraw.isEmpty()) {
                                mTransitionUtils.setListBitmap(this.hashMapBitmapDraw.get(gifTransition.getId()));
                            }
                            Transition type = gifTransition.getType();
//                            i4 = index;
                            mTransitionUtils.transition(type, canvas, this.mLastMatrix, matrix2, bitmap, this.mPaintImage,
                                    indexImageModel, currentFrame, widthPreview, heightPreview, false, 0);
                        }
                    } else {
                        return;
                    }
                }
            }
            i4 = indexImageModel;
            indexImageModel = i4 + 1;
        }
    }

    private boolean checkImageToDraw(int indexImage, int currentFrame) {
        int[] startAndStopFrameOfImage2 = TimeUtils.getStartAndStopFrameOfImage2(this.listImageModel.get(indexImage).getSecond(), getStartFrame(indexImage));
        if (currentFrame < startAndStopFrameOfImage2[0] || currentFrame > startAndStopFrameOfImage2[1]) {
            return false;
        }
        return true;
    }

    private float getStartFrame(int indexImage) {
        float f = 0.0f;
        if (indexImage <= 0) {
            return 0.0f;
        }
        for (int i2 = 0; i2 < indexImage; i2++) {
            if (i2 < this.listImageModel.size()) {
                f += this.listImageModel.get(i2).getSecond();
            }
        }
        return f * 30.0f;
    }

    public int getTotalFrames() {
        int size = this.listImageModel.size() * this.DURATION_IMAGE * 30;
        this.totalFrame = size;
        return size;
    }

    public float getTotalDuration() {
        return (((float) getTotalFrames()) * 1.0f) / 30.0f;
    }

    public void setDuration(float f, boolean z, boolean z2) {
//        for (ImageModel second : this.listImageModel) {
//            second.setSecond(f);
//        }
    }

    public void setParamsTrimAudio(int i, int i2) {
        this.mStartTimeAudio = i;
        this.mEndTimeAudio = i2;
    }

    public int[] getParamsTrimAudio() {
        int i = this.mEndTimeAudio;
        if (i - this.mStartTimeAudio > getTotalFrames() / 30) {
            i = this.mStartTimeAudio + (getTotalFrames() / 30);
        }
        return new int[]{this.mStartTimeAudio, i};
    }

    public void release() {
        clearBuffer(true, true, true);
        instance = null;
        this.mMakeVideoUtils = null;
    }

    public void changeVideoRatio(VIDEO_RATIO video_ratio) {
        this.currentVideoRatio = video_ratio;
        this.mBufferImage.clear();
        VIDEO_HEIGHT = (int) (((float) VIDEO_WIDTH) / getTileFromData(video_ratio));
    }

    public List<ImageModel> getListImageModel() {
        return this.listImageModel;
    }

//    public void setOnFinishEncoderListener(OnFinishEncoderListener onFinishEncoderListener) {
//        this.listener = onFinishEncoderListener;
//    }

    public GifImage getGifImage(String str) {
        return this.hashMapGif.get(str);
    }

    public void updateTimeVideo() {
        ArrayList<GifImage> arrayList = this.listGifImage;
        if (arrayList != null && arrayList.size() > 0) {
            new Thread(new Runnable() {
                public final void run() {
                    com.photoeditor.slideshow.imagetovideo.VideoMaker.this.lambda$updateTimeVideo$1$VideoMaker();
                }
            }).start();
        }
        updateTimeListTranJson();
        updateTimeThemeNew();
    }

    public /* synthetic */ void lambda$updateTimeVideo$1$VideoMaker() {
        Iterator<GifImage> it = this.listGifImage.iterator();
        while (it.hasNext()) {
            GifImage next = it.next();
            int frameToMilisecond = TimeUtils.frameToMilisecond((int) getStartFrame(next.getPosition()));
            if (frameToMilisecond > 0) {
                next.setStart(frameToMilisecond - (next.getDuration() / 2));
            }
        }
    }

    public void updateTimeAllImage(int i) {
//        for (ImageModel second : this.listImageModel) {
//            second.setSecond((float) i);
//        }
//        this.DURATION_IMAGE = i;
//        updateTimeVideo();
    }

    public void updateTimeOneImage(int i, int i2) {
        if (this.listImageModel.size() > i2 && i2 >= 0) {
            this.listImageModel.get(i2).setSecond((float) i);
        }
        updateTimeVideo();
    }

    public GifTransition getCurrentTransition() {
        return this.currentTransition;
    }

    public void setCurrentTransition(GifTransition gifTransition) {
        this.currentTransition = gifTransition;
    }

//    public DrafVideoModel getModelDraft() {
//        for (ImageModel next : this.listImageModel) {
//            next.setOriginBitmap((Bitmap) null);
//            next.setBlurBitmap((Bitmap) null);
//        }
//        List<ImageModel> list = this.listImageModel;
//        GifTransition gifTransition = this.currentTransition;
//        GifTheme gifTheme = this.currentThemeModel;
//        return new DrafVideoModel(list, gifTransition, gifTheme, "DraftVideo_" + (System.currentTimeMillis() / WorkRequest.MIN_BACKOFF_MILLIS), TimeUtils.parseTimeStampToString(System.currentTimeMillis()), System.currentTimeMillis(), this.song, this.start, this.end, this.idWatermark);
//    }

    public void applyTransitionDraw(GifTransition gifTransition, ArrayList<Bitmap> bitmapList) {
        deleteOldTran();
        this.mTransitionUtils.setListBitmap(bitmapList);
        this.listTransitionModel.clear();
        this.listGifImage.clear();
        for (ImageModel next : this.listImageModel) {
            this.listTransitionModel.add(gifTransition);
        }
    }

    public void applyTransitionRandomDraw(GifTransition gifTransition, ArrayList<TransitionDrawModel> transitionDrawModelArrayList) {
        deleteOldTran();
        this.listDrawModel = transitionDrawModelArrayList;
        this.currentTransition = gifTransition;
        updateRandomTransitionDraw();
    }

    private void updateRandomTransitionDraw() {
        for (ImageModel next : this.listImageModel) {
            TransitionDrawModel transitionDrawModel = this.listDrawModel.get(PhotorTool.getRandomIndex(0, this.listDrawModel.size() - 1));
            this.hashMapBitmapDraw.put(transitionDrawModel.getTransition().getId(), transitionDrawModel.getListBitmap());
            this.listTransitionModel.add(transitionDrawModel.getTransition());
        }
    }

    private void deleteOldTran() {
        this.listGifImage.clear();
        this.listTransitionJson.clear();
        this.hashMapBitmapDraw.clear();
        this.listTransitionModel.clear();
        this.transitionDrawableModel = null;
        if (this.mTransitionUtils.getListBitmap() != null) {
            this.mTransitionUtils.getListBitmap().clear();
        }
    }

    public void applyRandomJsonTransition(GifTransition gifTransition, ArrayList<TransitionJsonModel> transitionJsonModelList) {
        deleteOldTran();
        this.listTransitionJsonToRandom = transitionJsonModelList;
        this.currentTransition = gifTransition;
        updateTransitionJson();
    }

    private void updateTransitionJson() {
        if (listTransitionJsonToRandom != null && !listTransitionJsonToRandom.isEmpty()) {
            for (ImageModel next : this.listImageModel) {
                TransitionJsonModel transitionJsonModel = this.listTransitionJsonToRandom.get(PhotorTool.getRandomIndex(0, this.listTransitionJsonToRandom.size() - 1));
                this.listTransitionJson.add(transitionJsonModel);
                this.listTransitionModel.add(transitionJsonModel.getTransition());
            }
            updateTimeListTranJson();
        }
    }

    private void updateTimeListTranJson() {
        if (!this.listTransitionJson.isEmpty()) {
            for (int i = 0; i < this.listImageModel.size(); i++) {
                int startFrame = (int) getStartFrame(i);
                if (startFrame > 0 && i < this.listTransitionJson.size()) {
                    TransitionJsonModel transitionJsonModel = this.listTransitionJson.get(i);
                    int endFrame = (int) (((float) startFrame) - (transitionJsonModel.getComposition().getEndFrame() / 2.0f));
                    transitionJsonModel.setStartFrame(endFrame);
                    transitionJsonModel.setEndFrame((int) (((float) endFrame) + transitionJsonModel.getComposition().getEndFrame()));
                }
            }
        }
    }

    public void applyTransitionNew(GifTransition gifTransition) {
        deleteOldTran();
        this.hashMapMovie.remove(this.oldTransitionPath);
        this.currentTransition = gifTransition;
        new Thread(() -> {
            int i = gifTransition.getType().ordinal();
            if (i == Transition.RANDOM_CLASSIC.ordinal()) {
                listTransitionModel.clear();
                for (ImageModel next : listImageModel) {
                    listTransitionModel.add(getRandomClassicTran());
                }
            } else if (i == Transition.RANDOM_MATE.ordinal()) {
                listTransitionModel.clear();
                for (ImageModel next2 : listImageModel) {
                    listTransitionModel.add(getRandomMateTran());
                }
            } else if (i != Transition.IMAGE.ordinal()) {
                listTransitionModel.clear();
                listGifImage.clear();
                for (ImageModel next3 : listImageModel) {
                    listTransitionModel.add(gifTransition);
                }
            } else {
                listTransitionModel.clear();
                for (ImageModel next4 : listImageModel) {
                    listTransitionModel.add(gifTransition);
                }
                listGifImage.clear();
                transitionDrawableModel = new TransitionDrawableModel();
                lottieDrawableTransition = new LottieDrawable();
                try {
                    File file = new File(gifTransition.getPath());
                    LottieCompositionFactory.fromJsonInputStream(new FileInputStream(file),
                            file.getAbsolutePath()).addListener(obj -> {
                                lottieDrawableTransition.setComposition(obj);
                                transitionDrawableModel.setLottieDrawable(lottieDrawableTransition);
                            });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for (int i2 = 0; i2 < listImageModel.size(); i2++) {
                    int startFrame = (int) getStartFrame(i2);
                    if (startFrame > 0) {
                        transitionDrawableModel.addFrameTransition(startFrame);
                    }
                }
            }
        }).start();
    }

    public void lambda$applyTransitionNew$3$VideoMaker(GifTransition gifTransition) {
        int i = C26701.$SwitchMap$com$photoeditor$slideshow$imagetovideo$Transition[gifTransition.getType().ordinal()];
//        int i = gifTransition.get
//        if (i == 1) {
//            this.listTransitionModel.clear();
//            for (ImageModel next : this.listImageModel) {
//                this.listTransitionModel.add(getRandomClassicTran());
//            }
//        } else if (i == 2) {
//            this.listTransitionModel.clear();
//            for (ImageModel next2 : this.listImageModel) {
//                this.listTransitionModel.add(getRandomMateTran());
//            }
//        } else if (i != 4) {
//            this.listTransitionModel.clear();
//            this.listGifImage.clear();
//            for (ImageModel next3 : this.listImageModel) {
//                this.listTransitionModel.add(gifTransition);
//            }
//        } else {
//            this.listTransitionModel.clear();
//            for (ImageModel next4 : this.listImageModel) {
//                this.listTransitionModel.add(gifTransition);
//            }
//            this.listGifImage.clear();
//            this.transitionDrawableModel = new TransitionDrawableModel();
//            this.lottieDrawableTransition = new LottieDrawable();
//            try {
//                File file = new File(gifTransition.getPath());
//                LottieCompositionFactory.fromJsonInputStream(new FileInputStream(file),
//                        file.getAbsolutePath()).addListener(new LottieListener() {
//                    public final void onResult(Object obj) {
//                        com.photoeditor.slideshow.imagetovideo.VideoMaker.this.lambda$null$2$VideoMaker((LottieComposition) obj);
//                    }
//                });
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            for (int i2 = 0; i2 < this.listImageModel.size(); i2++) {
//                int startFrame = (int) getStartFrame(i2);
//                if (startFrame > 0) {
//                    this.transitionDrawableModel.addFrameTransition(startFrame);
//                }
//            }
//        }
    }

    private GifTransition getRandomClassicTran() {
        if (myData == null) myData = new MyData();
        return myData.getListGifTran().get(PhotorTool.getRandomIndex(0, myData.getListGifTran().size() - 1));
    }



    private GifTransition getRandomMateTran() {
        if (myData == null) myData = new MyData();
        return myData.getListGifTranSpecial1().get(PhotorTool.getRandomIndex(0, myData.getListGifTranSpecial1().size() - 1));
    }

    public void chooseThemeNew(GifTheme gifTheme) {
//        this.listThemeLottieModel.clear();
//        this.currentThemeModel = gifTheme;
//        if (gifTheme.getJson().booleanValue()) {
//            chooseThemeNewLottie(gifTheme);
//        }
    }

    public void chooseThemeNewLottie(GifTheme gifTheme) {
        this.listGifThemes.clear();
        this.currentThemeModel = gifTheme;
        if (gifTheme != null) {
            if (gifTheme.getType().equals(Transition.NONE)) {
                this.listThemeLottieModel.clear();
                return;
            }
            GifTheme gifTheme2 = this.currentThemeModel;
            if (gifTheme2 != null && gifTheme2.getThemePath() != null) {
                File file = new File(this.currentThemeModel.getThemePath());
                if (file.exists()) {
                    this.listThemeLottieModel.clear();
                    File[] listFiles = file.listFiles();
                    this.listFile = listFiles;
                    Arrays.sort(listFiles);
                    getDrawbleTheme(0);
                }
            }
        }
    }

    private void getDrawbleTheme(int i) {
        File[] fileArr = this.listFile;
        if (fileArr != null && fileArr.length != 0) {
            File file = fileArr[i];
            if (file != null && file.getAbsolutePath().contains(".json")) {
                try {
                    LottieCompositionFactory.fromJsonInputStream(new FileInputStream(file), file.getAbsolutePath()).addListener((LottieListener) obj -> {
                        ThemeLottieModel themeLottieModel = new ThemeLottieModel();
                        LottieComposition lottieComposition = (LottieComposition) obj;
                        themeLottieModel.setLottieComposition(lottieComposition);
                        themeLottieModel.setMaxFrame((int) lottieComposition.getEndFrame());
                        listThemeLottieModel.add(themeLottieModel);
                        if (i >= listFile.length - 1) {
                            updateTimeThemeNew();
                        } else {
                            getDrawbleTheme(i + 1);
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (i >= this.listFile.length - 1) {
                updateTimeThemeNew();
            } else {
                getDrawbleTheme(i + 1);
            }
        }
    }


    public void updateTimeThemeNew(GifTheme gifTheme, ArrayList<ThemeLottieModel> arrayList) {
        this.currentThemeModel = gifTheme;
        this.listThemeLottieModel = arrayList;
        if (arrayList.size() == 2) {
            getTotalFrames();
            ThemeLottieModel themeLottieModel = this.listThemeLottieModel.get(0);
            ThemeLottieModel themeLottieModel2 = this.listThemeLottieModel.get(1);
            if (this.totalFrame > themeLottieModel.getMaxFrame() * 2) {
                themeLottieModel.setStart(0);
                themeLottieModel.setEnd(themeLottieModel.getMaxFrame());
                themeLottieModel.setStart2(this.totalFrame - themeLottieModel.getMaxFrame());
                themeLottieModel.setEnd2(this.totalFrame);
                themeLottieModel2.setStart(themeLottieModel.getMaxFrame());
                themeLottieModel2.setEnd(this.totalFrame - themeLottieModel.getMaxFrame());
                return;
            }
            getTotalFrames();
            themeLottieModel.setStart(0);
            themeLottieModel.setEnd(getTotalFrames());
            themeLottieModel2.setStart(-1);
            themeLottieModel2.setEnd(-1);
            return;
        }
        getTotalFrames();
        ThemeLottieModel themeLottieModel3 = this.listThemeLottieModel.get(0);
        themeLottieModel3.setStart(0);
        themeLottieModel3.setEnd(this.totalFrame);
    }

    private void updateTimeThemeNew() {
        if (!this.listThemeLottieModel.isEmpty()) {
            if (this.listThemeLottieModel.size() == 2) {
                getTotalFrames();
                ThemeLottieModel themeLottieModel = this.listThemeLottieModel.get(0);
                ThemeLottieModel themeLottieModel2 = this.listThemeLottieModel.get(1);
                if (this.totalFrame > themeLottieModel.getMaxFrame() * 2) {
                    themeLottieModel.setStart(0);
                    themeLottieModel.setEnd(themeLottieModel.getMaxFrame());
                    themeLottieModel.setStart2(this.totalFrame - themeLottieModel.getMaxFrame());
                    themeLottieModel.setEnd2(this.totalFrame);
                    themeLottieModel2.setStart(themeLottieModel.getMaxFrame());
                    themeLottieModel2.setEnd(this.totalFrame - themeLottieModel.getMaxFrame());
                    return;
                }
                getTotalFrames();
                themeLottieModel.setStart(0);
                themeLottieModel.setEnd(getTotalFrames());
                themeLottieModel2.setStart(-1);
                themeLottieModel2.setEnd(-1);
                return;
            }
            getTotalFrames();
            ThemeLottieModel themeLottieModel3 = this.listThemeLottieModel.get(0);
            themeLottieModel3.setStart(0);
            themeLottieModel3.setEnd(this.totalFrame);
        }
    }

//    public String getTextTracking() {
//        String str;
//        int i = C26701.$SwitchMap$com$photoeditor$slideshow$enumm$VIDEO_RATIO[this.currentVideoRatio.ordinal()];
//        String str2 = this.listImageModel.size() + "images - " + (i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? ""
//                : " 1:1" : "3:4" : "4:3" : "16:9" : " 9:16") + " - " + this.currentThemeModel.getName() + " - " +
//                this.currentTransition.getDisplayName() + " - ";
//        File file = this.inputAudio;
//        String str3 = "none";
//        if (file != null) {
//            String absolutePath = file.getAbsolutePath();
//            int lastIndexOf = absolutePath.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR);
//            int lastIndexOf2 = absolutePath.lastIndexOf(".");
//            if (lastIndexOf2 > lastIndexOf) {
//                str3 = absolutePath.substring(lastIndexOf + 1, lastIndexOf2);
//            }
//            str = str2 + str3;
//        } else {
//            str = str2 + str3;
//        }
//        return str + " - " + this.DURATION_IMAGE + "s";
//    }

//    public void setMusicNew(LocalSong localSong, int i, int i2) {
//        this.song = localSong;
//        this.start = i;
//        this.end = i2;
//    }

    public void updateTimeGif(int i) {
        Iterator<GifImage> it = this.listGifImage.iterator();
        while (it.hasNext()) {
            GifImage next = it.next();
            if (next.getStart().intValue() + next.getDuration().intValue() > i) {
                if (i - next.getDuration().intValue() >= 0) {
                    next.setStart(Integer.valueOf((i - next.getDuration().intValue()) - 50));
                } else {
                    next.setStart(0);
                    next.setDuration(i - 50);
                }
            }
        }
    }
}
