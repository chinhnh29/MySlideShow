package com.photoeditor.slideshow.java;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
//import androidx.core.net.MailTo;
//import com.daimajia.androidanimations.library.Techniques;
//import com.daimajia.androidanimations.library.YoYo;
//import com.daimajia.androidanimations.library.bouncing_entrances.BounceInDownAnimator;
//import com.daimajia.androidanimations.library.bouncing_entrances.BounceInUpAnimator;
//import com.google.firebase.analytics.FirebaseAnalytics;
//import com.photoeditor.slideshow.common.AppConst;
//import com.photoeditor.slideshow.java.util.OnCustomClickListener;
//import com.photoeditor.slideshow.java.util.OnCustomTouchListener;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.common.BitmapResult;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.lingala.zip4j.util.InternalZipConstants;

import jp.wasabeef.blurry.internal.BlurFactor;


public class PhotorTool {
    public static final String BASE_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";
    private static final BlurFactor factor = new BlurFactor();

    static /* synthetic */ void lambda$scanFile$1(String str, Uri uri) {
    }

    public static Bitmap scalePreserveRatioBlur2(Context context, Bitmap bitmap, int i, int i2) {
        if (i2 <= 0 || i <= 0 || bitmap != null) {
        }
        return bitmap;
    }

//    public static Bitmap scalePreserveRatioBlur(Context context, Bitmap bitmap, int i, int i2) {
//        if (i2 <= 0 || i <= 0 || bitmap == null) {
//            return bitmap;
//        }
//        float f = (float) i;
//        float width = (float) bitmap.getWidth();
//        float f2 = f / width;
//        float f3 = (float) i2;
//        float height = (float) bitmap.getHeight();
//        float f4 = f3 / height;
//        int floor = (int) Math.floor((double) (width * f2));
//        int floor2 = (int) Math.floor((double) (f2 * height));
//        if (floor > i || floor2 > i2) {
//            floor = (int) Math.floor((double) (width * f4));
//            floor2 = (int) Math.floor((double) (height * f4));
//        }
//        BlurFactor blurFactor = factor;
//        blurFactor.width = bitmap.getWidth();
//        blurFactor.height = bitmap.getHeight();
//        Bitmap of = Blur.m353of(context, bitmap, blurFactor);
//        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, floor, floor2, false);
//        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(createBitmap);
//        Paint paint = new Paint();
//        paint.setColor(-16777216);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), paint);
//        if (of == null) {
//            return createScaledBitmap;
//        }
//        int width2 = of.getWidth();
//        int height2 = of.getHeight();
//        if (width2 >= height2) {
//            width2 = height2;
//        }
//        float f5 = f / f3;
//        if (f5 < 1.0f) {
//            width2 = (int) (((float) width2) * f5);
//        }
//        canvas.drawBitmap(of, new Rect(0, 0, width2, (int) (((float) width2) / f5)), new Rect(0, 0, i, i2), (Paint) null);
//        float f6 = ((float) floor) / ((float) floor2);
//        float f7 = 0.0f;
//        float f8 = f6 >= f5 ? 0.0f : ((float) (i - floor)) / 2.0f;
//        if (f6 >= f5) {
//            f7 = ((float) (i2 - floor2)) / 2.0f;
//        }
//        canvas.drawBitmap(createScaledBitmap, f8, f7, (Paint) null);
//        return createBitmap;
//    }

    public static Bitmap scalePreserveRatio(Context context, Bitmap bitmap, int i, int i2) {
//        if (i2 <= 0 || i <= 0 || bitmap == null) {
//            return bitmap;
//        }
//        float f = (float) i;
//        float width = (float) bitmap.getWidth();
//        float f2 = f / width;
//        float f3 = (float) i2;
//        float height = (float) bitmap.getHeight();
//        float f4 = f3 / height;
//        int floor = (int) Math.floor((double) (width * f2));
//        int floor2 = (int) Math.floor((double) (f2 * height));
//        if (floor > i || floor2 > i2) {
//            floor = (int) Math.floor((double) (width * f4));
//            floor2 = (int) Math.floor((double) (height * f4));
//        }
//        BlurFactor blurFactor = factor;
//        blurFactor.width = bitmap.getWidth() / 2;
//        blurFactor.height = bitmap.getHeight() / 2;
//        if (context == null) {
//            return null;
//        }
//        Bitmap of = Blur.m353of(context, bitmap, blurFactor);
//        if (of != null) {
//            of = scaleCenterCrop(of, i2, i);
//        }
//        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, floor, floor2, true);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(createBitmap);
//        float f5 = 0.0f;
//        if (of != null) {
//            canvas.drawBitmap(of, 0.0f, 0.0f, (Paint) null);
//        }
//        float f6 = ((float) floor) / ((float) floor2);
//        float f7 = f / f3;
//        float f8 = f6 >= f7 ? 0.0f : ((float) (i - floor)) / 2.0f;
//        if (f6 >= f7) {
//            f5 = ((float) (i2 - floor2)) / 2.0f;
//        }
//        canvas.drawBitmap(createScaledBitmap, f8, f5, (Paint) null);
        return createBitmap;
    }

    public static Bitmap scalePreserveRatioOld(Bitmap bitmap, int i, int i2) {
        if (i2 <= 0 || i <= 0 || bitmap == null) {
            return bitmap;
        }
        float f = (float) i;
        float width = (float) bitmap.getWidth();
        float f2 = f / width;
        float f3 = (float) i2;
        float height = (float) bitmap.getHeight();
        float f4 = f3 / height;
        int floor = (int) Math.floor((double) (width * f2));
        int floor2 = (int) Math.floor((double) (f2 * height));
        if (floor > i || floor2 > i2) {
            floor = (int) Math.floor((double) (width * f4));
            floor2 = (int) Math.floor((double) (height * f4));
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, floor, floor2, true);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), paint);
        float f5 = ((float) floor) / ((float) floor2);
        float f6 = f / f3;
        float f7 = 0.0f;
        float f8 = f5 >= f6 ? 0.0f : ((float) (i - floor)) / 2.0f;
        if (f5 >= f6) {
            f7 = ((float) (i2 - floor2)) / 2.0f;
        }
        canvas.drawBitmap(createScaledBitmap, f8, f7, (Paint) null);
        return createBitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        Tiny.BitmapCompressOptions bitmapCompressOptions = new Tiny.BitmapCompressOptions();
        bitmapCompressOptions.width = bitmap.getWidth() / 4;
        bitmapCompressOptions.height = bitmap.getHeight() / 4;
        BitmapResult compressSync = Tiny.getInstance().source(bitmap).asBitmap().withOptions(bitmapCompressOptions).compressSync();
        bitmap.recycle();
        return compressSync.bitmap;
    }

    public static String replaceAllSpace(String str) {
        return str.replaceAll("\\s+", "").toLowerCase();
    }

    public static Bitmap scaleCenterCrop(Bitmap bitmap, int i, int i2) {
        float f = (float) i2;
        float width = (float) bitmap.getWidth();
        float f2 = (float) i;
        float height = (float) bitmap.getHeight();
        float max = Math.max(f / width, f2 / height);
        float f3 = width * max;
        float f4 = max * height;
        float f5 = (f - f3) / 2.0f;
        float f6 = (f2 - f4) / 2.0f;
        RectF rectF = new RectF(f5, f6, f3 + f5, f4 + f6);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i, bitmap.getConfig());
        new Canvas(createBitmap).drawBitmap(bitmap, (Rect) null, rectF, (Paint) null);
        return createBitmap;
    }

    public static int getDuration(Context context, String str) {
        Uri parse = Uri.parse(str);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, parse);
        int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(9)) / 1000;
        mediaMetadataRetriever.release();
        return parseInt;
    }

    public static void vibrate(Context context, int i) {
//        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
//        if (Build.VERSION.SDK_INT >= 26) {
//            vibrator.vibrate(VibrationEffect.createOneShot((long) i, -1));
//        } else {
//            vibrator.vibrate((long) i);
//        }
    }

    public static void removeGlobalOnLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (getSdkVersion() < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static Bitmap getThumbnail(Context context, String str) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeStream(context.getAssets().open(str), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = 2;
        options2.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeStream(context.getAssets().open(str), null, options2);
    }

    public static void animationShake(View view) {
//        YoYo.with(Techniques.Shake).duration(500).playOn(view);
    }

    public static void showAnimationBottom(View... viewArr) {
//        for (View view : viewArr) {
//            if (view.getVisibility() != 0) {
//                view.setVisibility(0);
//                YoYo.with(new BounceInUpAnimator()).duration(400).playOn(view);
//            }
//        }
    }

    public static void showAnimationToBottom(View... viewArr) {
//        for (View view : viewArr) {
//            if (view.getVisibility() != 0) {
//                view.setVisibility(0);
//                YoYo.with(new BounceInDownAnimator()).duration(400).playOn(view);
//            }
//        }
    }

    public static void sortList(ArrayList<File> arrayList) {
//        Collections.sort(arrayList, $$Lambda$PhotorTool$Jnru6GNYpNyUIJmTXI9P0mDuL_8.INSTANCE);
    }

    static /* synthetic */ int lambda$sortList$0(File file, File file2) {
        if (file.getAbsolutePath().length() > file2.getAbsolutePath().length()) {
            return 1;
        }
        if (file.getAbsolutePath().length() < file2.getAbsolutePath().length()) {
            return -1;
        }
        return file.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }

    public static boolean deleteDirectory(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!deleteDirectory(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static List<String> loadStickerFromAssets(Context context, String str) {
        String[] list;
        ArrayList arrayList = new ArrayList();
        if (context.getAssets() == null) {
            return null;
        }
        try {
            if (context.getAssets().list(str) == null || (list = context.getAssets().list(str)) == null) {
                return null;
            }
            for (String str2 : list) {
                arrayList.add(str + InternalZipConstants.ZIP_FILE_SEPARATOR + str2);
            }
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void unzip(File file, File file2) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    File file3 = new File(file2, nextEntry.getName());
                    File parentFile = nextEntry.isDirectory() ? file3 : file3.getParentFile();
                    if (!parentFile.isDirectory()) {
                        if (!parentFile.mkdirs()) {
                            throw new FileNotFoundException("Failed to ensure directory: " + parentFile.getAbsolutePath());
                        }
                    }
                    if (!nextEntry.isDirectory()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file3);
                        while (true) {
                            try {
                                int read = zipInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            } finally {
                                fileOutputStream.close();
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        } finally {
            zipInputStream.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r4.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0033, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0034, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0037, code lost:
        throw r4;
     */
    public static void copy(File file, File file2) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(file);
//        FileOutputStream fileOutputStream = new FileOutputStream(file2);
//        byte[] bArr = new byte[1024];
//        while (true) {
//            int read = fileInputStream.read(bArr);
//            if (read > 0) {
//                fileOutputStream.write(bArr, 0, read);
//            } else {
//                fileOutputStream.close();
//                fileInputStream.close();
//                return;
//            }
//        }
    }

    public static void scanFile(Context context, String str) {
        scanFile(context, new String[]{str});
    }

    public static void scanFile(Context context, String[] strArr) {
        MediaScannerConnection.scanFile(context, strArr, (String[]) null, null);
    }

    public static void createFolder(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void shareApp(Context context) {
//        disableExposure();
//        Intent intent = new Intent("android.intent.action.SEND");
//        intent.setType("text/plain");
//        intent.putExtra("android.intent.extra.TEXT", BASE_GOOGLE_PLAY + context.getPackageName());
//        context.startActivity(Intent.createChooser(intent, FirebaseAnalytics.Event.SHARE));
    }

    public static void hideKeyboard(Context context, EditText editText) {
//        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private static void disableExposure() {
        if (getSdkVersion() >= 24) {
            try {
                StrictMode.class.getMethod("disableDeathOnFileUriExposure", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return capitalize(str2);
        }
        return capitalize(str) + " " + str2;
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    public static void openApp(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        }
    }

//    public static void sendEmailMoree(Context context, String[] strArr, String str, String str2) {
//        disableExposure();
//        Intent intent = new Intent("android.intent.action.SENDTO");
//        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
//        intent.putExtra("android.intent.extra.EMAIL", strArr);
//        intent.putExtra("android.intent.extra.SUBJECT", str);
//        intent.putExtra("android.intent.extra.TEXT", str2);
//        if (intent.resolveActivity(context.getPackageManager()) != null) {
//            context.startActivity(intent);
//        } else {
//            Toast.makeText(context, "you need install gmail", 0).show();
//        }
//    }

//    public static void openMarket(Context context, String str) {
//        Intent intent = new Intent("android.intent.action.VIEW");
//        try {
//            intent.setData(Uri.parse("market://details?id=" + str));
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException unused) {
//            openBrowser(context, BASE_GOOGLE_PLAY + str);
//        }
//    }

//    public static void openBrowser(Context context, String str) {
//        if (!str.startsWith("http://") && !str.startsWith(AppConst.BASE_PREFIX)) {
//            str = "http://" + str;
//        }
//        try {
//            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean isBlank(String str) {
        if (!(str == null || str.length() == 0)) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.codePointAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static void clickScaleView(final View view, final OnCustomClickListener onCustomClickListener) {
//        setOnCustomTouchView(view, new OnCustomTouchListener() {
//            /* class com.photoeditor.slideshow.java.PhotorTool.C50181 */
//
//            @Override // com.photoeditor.slideshow.java.util.OnCustomTouchListener
//            public void OnCustomTouchOther(View view, MotionEvent motionEvent) {
//            }
//
//            private void setScale(float f) {
//                view.setScaleX(f);
//                view.setScaleY(f);
//            }
//
//            @Override // com.photoeditor.slideshow.java.util.OnCustomTouchListener
//            public void OnCustomTouchDown(View view, MotionEvent motionEvent) {
//                setScale(0.9f);
//            }
//
//            @Override // com.photoeditor.slideshow.java.util.OnCustomTouchListener
//            public void OnCustomTouchMoveOut(View view, MotionEvent motionEvent) {
//                setScale(1.0f);
//            }
//
//            @Override // com.photoeditor.slideshow.java.util.OnCustomTouchListener
//            public void OnCustomTouchUp(View view, MotionEvent motionEvent) {
//                setScale(1.0f);
//                OnCustomClickListener onCustomClickListener = onCustomClickListener;
//                if (onCustomClickListener != null) {
//                    onCustomClickListener.OnCustomClick(view, motionEvent);
//                }
//            }
//        });
//    }

//    public static void setOnCustomTouchView(View view, final OnCustomTouchListener onCustomTouchListener) {
//        view.setOnTouchListener(new View.OnTouchListener() {
//            /* class com.photoeditor.slideshow.java.PhotorTool.View$OnTouchListenerC50192 */
//            private Rect rect;
//
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (onCustomTouchListener == null) {
//                    return false;
//                }
//                if (motionEvent.getAction() == 0) {
//                    this.rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
//                    onCustomTouchListener.OnCustomTouchDown(view, motionEvent);
//                } else {
//                    Rect rect2 = this.rect;
//                    if (rect2 != null && !rect2.contains(view.getLeft() + ((int) motionEvent.getX()), view.getTop() + ((int) motionEvent.getY()))) {
//                        onCustomTouchListener.OnCustomTouchMoveOut(view, motionEvent);
//                        return false;
//                    } else if (motionEvent.getAction() == 1) {
//                        onCustomTouchListener.OnCustomTouchUp(view, motionEvent);
//                    } else {
//                        onCustomTouchListener.OnCustomTouchOther(view, motionEvent);
//                    }
//                }
//                return true;
//            }
//        });
//    }

    public static int getRandomIndex(int i, int i2) {
        return ((int) (Math.random() * ((double) ((i2 - i) + 1)))) + i;
    }

    public static float convertDpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static DisplayMetrics getDisplayInfo() {
        return Resources.getSystem().getDisplayMetrics();
    }

    public static boolean haveNetworkConnection(Context context) {
        try {
            NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getAllNetworkInfo();
            boolean z = false;
            boolean z2 = false;
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                    z = true;
                }
                if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                    z2 = true;
                }
            }
            if (z || z2) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;
        }
    }

    public static boolean checkHasPermission(Activity activity) {
        return isPermissionAllow(activity, "android.permission.READ_EXTERNAL_STORAGE") && isPermissionAllow(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    private static boolean isPermissionAllow(Activity activity, String str) {
        if (getSdkVersion() >= 23 && ContextCompat.checkSelfPermission(activity, str) != 0) {
            return false;
        }
        return true;
    }

    public static void showToast(Context context, int i) {
//        Toast.makeText(context, context.getResources().getString(i), 0).show();
    }
}