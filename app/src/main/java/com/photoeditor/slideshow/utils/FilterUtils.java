//package com.photoeditor.slideshow.utils;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Matrix;
//import com.daasuu.gpuv.egl.filter.GlFilter;
//import com.daasuu.gpuv.egl.filter.GlToneCurveFilter;
//import com.photoeditor.slideshow.my_slide_show.obj.FilterModel;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//import jp.co.cyberagent.android.gpuimage.GPUImage;
//import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
//
//
//public final class FilterUtils {
//    public static final FilterUtils INSTANCE = new FilterUtils();
//    private static final String blueColor = "B";
//    private static final String filCateHoliday = "Holiday";
//    private static final String filCateLight = "Light";
//    private static final String filCateModern = "Modern";
//    private static final String filCateVintage = "Vintage";
//    private static final int filterCurve = 1;
//    private static final int filterGray = 2;
//    private static final int filterGroup = 3;
//    private static final String greenColor = "G";
//    private static final int noFilter = 0;
//    private static final String redColor = "R";
//
//    private FilterUtils() {
//    }
//
//    public final int getNoFilter() {
//        return noFilter;
//    }
//
//    public final int getFilterCurve() {
//        return filterCurve;
//    }
//
//    public final int getFilterGray() {
//        return filterGray;
//    }
//
//    public final int getFilterGroup() {
//        return filterGroup;
//    }
//
//    public final String getRedColor() {
//        return redColor;
//    }
//
//    public final String getGreenColor() {
//        return greenColor;
//    }
//
//    public final String getBlueColor() {
//        return blueColor;
//    }
//
//    public final String getFilCateModern() {
//        return filCateModern;
//    }
//
//    public final String getFilCateHoliday() {
//        return filCateHoliday;
//    }
//
//    public final String getFilCateVintage() {
//        return filCateVintage;
//    }
//
//    public final String getFilCateLight() {
//        return filCateLight;
//    }
//
//    public final GlFilter getFilter(int i, Context context) {
//        return i != 0 ? new GlFilter() : new GlFilter();
//    }
//
//    public static /* synthetic */ GlFilter getFilter$default(FilterUtils filterUtils, File file, Context context, boolean z, int i, Object obj) {
//        if ((i & 4) != 0) {
//            z = false;
//        }
//        return filterUtils.getFilter(file, context, z);
//    }
//
//    public final GlFilter getFilter(File file, Context context, boolean z) throws FileNotFoundException {
//        if (file == null) {
//            return new GlFilter();
//        }
//        return new GlToneCurveFilter(new FileInputStream(file));
//    }
//
//    public static /* synthetic */ GlFilter getFilter$default(FilterUtils filterUtils, InputStream inputStream, Context context, boolean z, int i, Object obj) {
//        if ((i & 4) != 0) {
//            z = false;
//        }
//        return filterUtils.getFilter(inputStream, context, z);
//    }
//
//    public final GlFilter getFilter(InputStream inputStream, Context context, boolean z) {
//        if (inputStream == null) {
//            return new GlFilter();
//        }
//        return new GlToneCurveFilter(inputStream);
//    }
//
//    public final GPUImageFilter getFilter1(InputStream inputStream, Context context, boolean z) {
//        if (inputStream == null) {
//            return new GPUImageFilter();
//        }
//        return new GPUImageFilter(inputStream);
//    }
//
//    public final Bitmap filterBitmap(Context context, Bitmap bitmap, File file) {
//        GPUImage gPUImage = new GPUImage(context);
//        gPUImage.setImage(bitmap);
//        gPUImage.setFilter(getFilter(file, context, true));
//        Bitmap bitmapWithFilterApplied = gPUImage.getBitmapWithFilterApplied();
//        return bitmapWithFilterApplied;
//    }
//
//    public final Bitmap filterBitmap(Context context, Bitmap bitmap, FilterModel filterModel) {
//        if (filterModel != null && !filterModel.getId().equalsIgnoreCase("none")) {
//            File file = new File(filterModel.getPath());
//            if (file.exists()) {
//                GPUImage gPUImage = new GPUImage(context);
//                gPUImage.setImage(bitmap);
//                try {
//                    gPUImage.setFilter(getFilter(new FileInputStream(file), context, true));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                Bitmap bitmapWithFilterApplied = gPUImage.getBitmapWithFilterApplied();
//                return createFlippedBitmap(bitmapWithFilterApplied, false, true);
//            }
//        }
//        return bitmap;
//    }
//
//    public final Bitmap createFlippedBitmap(Bitmap bitmap, boolean z, boolean z2) {
//        Matrix matrix = new Matrix();
//        float f = -1.0f;
//        float f2 = z ? -1.0f : 1.0f;
//        if (!z2) {
//            f = 1.0f;
//        }
//        matrix.postScale(f2, f, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
//        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        return createBitmap;
//    }
//
//    public final Bitmap filterBitmap(Context context, Bitmap bitmap, GlFilter glFilter) {
//        GPUImage gPUImage = new GPUImage(context);
//        gPUImage.setImage(bitmap);
//        gPUImage.setFilter(glFilter);
//        Bitmap bitmapWithFilterApplied = gPUImage.getBitmapWithFilterApplied();
//        return bitmapWithFilterApplied;
//    }
//}
