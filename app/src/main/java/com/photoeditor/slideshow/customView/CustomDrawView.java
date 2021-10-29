//package com.photoeditor.slideshow.customView;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.Point;
//import android.os.Handler;
//import android.util.AttributeSet;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//
//import com.photoeditor.slideshow.R;
//import com.photoeditor.slideshow.imagetovideo.CustomPreviewView;
//import com.photoeditor.slideshow.imagetovideo.Decor;
//import com.photoeditor.slideshow.imagetovideo.DecorText;
//import com.photoeditor.slideshow.imagetovideo.OnHandlerItemListener;
//import com.photoeditor.slideshow.interfaces.OnChangeFocusSticker;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomDrawView extends View {
//    private static float MENU_BUTTON_SIZE = 64.0f;
//    private static final String TAG = "dsk1";
//    private static final int TYPE_CHANGE = 3;
//    private static final int TYPE_DELETE = 4;
//    private static final int TYPE_MOVE = 2;
//    private static final int TYPE_NONE = 0;
//    private static final int TYPE_TAP = 1;
//    private Paint backgroundPaint;
//    GestureDetector gestureDetector;
//    private boolean isStickerOutSize;
//    private Bitmap mBitmapDeco;
//    private CustomPreviewView mCustomPreviewView;
//    private DecorText mDecorText;
//    public List<Decor> mDecors;
//    private Bitmap mDeleteBitmap;
//    private float mDeltaY;
//    public int mFocusObjectIndex;
//    private Paint mFramePaint;
//    private boolean mIsDraw;
//    public boolean mIsMove;
//    private Point mLastPoint;
//    private float mLengthOld;
//    public OnHandlerItemListener mOnHandlerItemListener;
//    private Bitmap mOpenBitmap;
//    private float mScale;
//    private float mScaleY;
//    private long mTimeCurrent;
//    private int mTouchType;
//    private OnChangeFocusSticker onChangeFocusSticker;
//
//    public interface OnChangeItemStickerListener {
//        void changeSticker(int i, int i2);
//    }
//
//    public void setCustomPreviewView(CustomPreviewView customPreviewView) {
//        this.mCustomPreviewView = customPreviewView;
//    }
//
//    public void setOnChangeFocusSticker(OnChangeFocusSticker onChangeFocusSticker2) {
//        this.onChangeFocusSticker = onChangeFocusSticker2;
//    }
//
//    public void setOnHandlerItemListener(OnHandlerItemListener onHandlerItemListener) {
//        this.mOnHandlerItemListener = onHandlerItemListener;
//    }
//
//    public Bitmap getBitmapDeco() {
//        return this.mBitmapDeco;
//    }
//
//    public List<Decor> getDecor() {
//        return this.mDecors;
//    }
//
//    public float getScale() {
//        return this.mScale;
//    }
//
//    public float getDeltaY() {
//        return this.mDeltaY;
//    }
//
//    public void clearData() {
//        List<Decor> list = this.mDecors;
//        if (list != null) {
//            list.clear();
//        }
//    }
//
//    public void clearDataPosition() {
//        int i = this.mFocusObjectIndex;
//        if (i != -1) {
//            this.mDecors.remove(i);
//            this.mFocusObjectIndex--;
//        }
//    }
//
//    public CustomDrawView(Context context) {
//        this(context, (AttributeSet) null);
//    }
//
//    public CustomDrawView(Context context, AttributeSet attributeSet) {
//        this(context, attributeSet, 0);
//    }
//
//    public CustomDrawView(Context context, AttributeSet attributeSet, int i) {
//        super(context, attributeSet, i);
//        this.mIsMove = true;
//        this.mScale = 1.0f;
//        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
//            public boolean onDoubleTap(MotionEvent motionEvent) {
//                if (CustomDrawView.this.mFocusObjectIndex == -1 || ((Decor) CustomDrawView.this.mDecors.get(CustomDrawView.this.mFocusObjectIndex)).getType() != 0 || CustomDrawView.this.mOnHandlerItemListener == null) {
//                    return true;
//                }
//                CustomDrawView.this.mOnHandlerItemListener.clickItem(CustomDrawView.this.mFocusObjectIndex);
//                return true;
//            }
//        });
//        this.isStickerOutSize = false;
//        this.mTouchType = 0;
//        if (this.mDecors == null) {
//            this.mDecors = new ArrayList();
//        }
//        this.mFocusObjectIndex = -1;
//        this.mLastPoint = null;
//        initialize();
//    }
//
//    /* access modifiers changed from: protected */
//    public void onMeasure(int i, int i2) {
//        super.onMeasure(i, i);
//    }
//
//    private void initialize() {
//        Resources resources = getResources();
//        float f = resources.getDisplayMetrics().density * 2.0f;
//        Paint paint = new Paint();
//        this.mFramePaint = paint;
//        paint.setAntiAlias(true);
//        this.mFramePaint.setStrokeWidth(f);
//        MENU_BUTTON_SIZE = f * 13.0f;
//        Paint paint2 = new Paint();
//        this.backgroundPaint = paint2;
//        paint2.setColor(Color.parseColor("#F57500"));
//        this.mFramePaint.setColor(getResources().getColor(R.color.orange));
//        this.mOpenBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_resize);
//        this.mDeleteBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_cancel_2);
//    }
//
//    /* access modifiers changed from: protected */
//    public void onFinishInflate() {
//        super.onFinishInflate();
//        this.mIsDraw = true;
//    }
//
//    /* access modifiers changed from: protected */
//    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (this.mIsDraw) {
//            drawCake(canvas);
//        }
//    }
//
//    private void decorNoBorder() {
//        this.mBitmapDeco = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(this.mBitmapDeco);
//        canvas.drawColor(-1);
//        for (Decor draw : this.mDecors) {
//            draw.draw(canvas);
//        }
//    }
//
//    public void addAllDecor(List<Decor> list) {
//        this.mDecors.addAll(list);
//    }
//
//    public void addAllDecorText(List<DecorText> list) {
//        this.mDecors.addAll(list);
//    }
//
//    public void drawFrameSelectedItem(int i) {
//        this.mIsDraw = true;
//        this.mFocusObjectIndex = i;
//        invalidate();
//    }
//
//    private void drawCake(Canvas canvas) {
//        for (Decor draw : this.mDecors) {
//            draw.draw(canvas);
//        }
//        int i = this.mFocusObjectIndex;
//        if (i >= 0 && i < this.mDecors.size()) {
//            drawFrame(canvas, this.mDecors.get(this.mFocusObjectIndex));
//        }
//    }
//
//    public boolean onTouchEvent(MotionEvent motionEvent) {
//        OnHandlerItemListener onHandlerItemListener;
//        int i;
//        int i2;
//        Decor decor;
//        Point point = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
//        OnHandlerItemListener onHandlerItemListener2 = this.mOnHandlerItemListener;
//        if (onHandlerItemListener2 != null) {
//            onHandlerItemListener2.touchItem();
//        }
//        int actionMasked = motionEvent.getActionMasked();
//        if (actionMasked == 0) {
//            judgeTouchPoint(point);
//            if (this.mTouchType == 4 && this.mDecors.get(this.mFocusObjectIndex).getTouchType(point) == 4) {
//                this.mDecors.remove(this.mFocusObjectIndex);
//                int i3 = this.mFocusObjectIndex;
//                if (!(i3 == -1 || (onHandlerItemListener = this.mOnHandlerItemListener) == null)) {
//                    onHandlerItemListener.onDeleteItem(i3);
//                }
//            }
//        } else if (actionMasked == 1) {
//            judgeTouchPoint(point);
//            if (this.isStickerOutSize) {
//                setCenterScreen();
//            }
//            if (System.currentTimeMillis() - this.mTimeCurrent < 500) {
//                this.mTimeCurrent = System.currentTimeMillis();
//                return true;
//            }
//            this.mTimeCurrent = System.currentTimeMillis();
//            if (!this.mDecors.isEmpty()) {
//                int i4 = this.mTouchType;
//                if (i4 != 0 && i4 != 1 && (i = this.mFocusObjectIndex) >= 0 && i < this.mDecors.size() && this.mDecors.get(this.mFocusObjectIndex).getType() == 2) {
//                    Decor decor2 = this.mDecors.get(this.mFocusObjectIndex);
//                }
//                this.mIsMove = true;
//                this.mTouchType = 0;
//            }
//        } else if (actionMasked != 2) {
//            if (actionMasked == 5) {
//                judgeTouchPoint(point);
//                if (!this.mDecors.isEmpty() && motionEvent.getPointerCount() == 2) {
//                    float[] fArr = {motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1)};
//                    this.mLengthOld = lengthTwoPoint(fArr[0], fArr[2], fArr[1], fArr[3]);
//                }
//            } else if (actionMasked == 6) {
//                judgeTouchPoint(point);
//                if (motionEvent.getPointerCount() == 2) {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            boolean unused = CustomDrawView.this.mIsMove = true;
//                        }
//                    }, 500);
//                }
//            }
//        } else if (!this.mDecors.isEmpty()) {
//            if (motionEvent.getPointerCount() == 2) {
//                float[] fArr2 = {motionEvent.getX(0), motionEvent.getX(1), motionEvent.getY(0), motionEvent.getY(1)};
//                float lengthTwoPoint = lengthTwoPoint(fArr2[0], fArr2[2], fArr2[1], fArr2[3]);
//                this.mIsMove = false;
//                int i5 = this.mFocusObjectIndex;
//                if (i5 != -1) {
//                    this.mDecors.get(i5).setZoom(this.mLengthOld, lengthTwoPoint);
//                    this.mLengthOld = lengthTwoPoint;
//                    OnHandlerItemListener onHandlerItemListener3 = this.mOnHandlerItemListener;
//                    if (onHandlerItemListener3 != null) {
//                        onHandlerItemListener3.onZoomItem();
//                    }
//                }
//            } else if (this.mTouchType == 3) {
//                int i6 = this.mFocusObjectIndex;
//                if (i6 != -1) {
//                    this.mDecors.get(i6).setChange(point);
//                    OnHandlerItemListener onHandlerItemListener4 = this.mOnHandlerItemListener;
//                    if (onHandlerItemListener4 != null) {
//                        onHandlerItemListener4.onZoomItem();
//                    }
//                }
//            } else if (this.mIsMove && (i2 = this.mFocusObjectIndex) != -1 && i2 < this.mDecors.size() && (decor = this.mDecors.get(this.mFocusObjectIndex)) != null) {
//                float x = decor.getX() + ((float) (point.x - this.mLastPoint.x));
//                float y = decor.getY() + ((float) (point.y - this.mLastPoint.y));
//                decor.setX(x);
//                decor.setY(y);
//                this.mDecors.set(this.mFocusObjectIndex, decor);
//                checkStickerOutSize(x, y);
//            }
//        }
//        this.mLastPoint = point;
//        invalidate();
//        this.gestureDetector.onTouchEvent(motionEvent);
//        return true;
//    }
//
//    private void checkStickerOutSize(float f, float f2) {
//        Decor decor = this.mDecors.get(this.mFocusObjectIndex);
//        if ((decor.getWidth() / 2.0f) + f <= 0.0f || f - (decor.getWidth() / 2.0f) >= ((float) getWidth())) {
//            this.isStickerOutSize = true;
//        } else if ((decor.getHeight() / 2.0f) + f2 <= 0.0f || f2 - (decor.getHeight() / 2.0f) >= ((float) getWidth())) {
//            this.isStickerOutSize = true;
//        } else {
//            this.isStickerOutSize = false;
//        }
//    }
//
//    private void setCenterScreen() {
//        int i = this.mFocusObjectIndex;
//        if (i != -1 && i < this.mDecors.size()) {
//            Decor decor = this.mDecors.get(this.mFocusObjectIndex);
//            decor.setX((float) (getWidth() / 2));
//            decor.setY((float) (getHeight() / 2));
//            this.mDecors.set(this.mFocusObjectIndex, decor);
//        }
//    }
//
//    private void judgeTouchPoint(Point point) {
//        OnChangeFocusSticker onChangeFocusSticker2;
//        this.mTouchType = 0;
//        for (int size = this.mDecors.size() - 1; size >= 0; size--) {
//            int touchType = this.mDecors.get(size).getTouchType(point);
//            if (touchType != 0) {
//                int i = this.mFocusObjectIndex;
//                if (i == size) {
//                    this.mTouchType = touchType;
//                } else {
//                    this.mTouchType = 2;
//                }
//                if (!(i == size || (onChangeFocusSticker2 = this.onChangeFocusSticker) == null)) {
//                    onChangeFocusSticker2.changeSticker(i, size);
//                }
//                this.mFocusObjectIndex = size;
//                return;
//            }
//            int i2 = this.mFocusObjectIndex;
//            if (i2 == size) {
//                OnChangeFocusSticker onChangeFocusSticker3 = this.onChangeFocusSticker;
//                if (onChangeFocusSticker3 != null) {
//                    onChangeFocusSticker3.changeSticker(i2, -1);
//                }
//                this.mFocusObjectIndex = -1;
//            }
//        }
//    }
//
//    private void drawFrame(Canvas canvas, Decor decor) {
//        Point[] rectPoints = decor.getRectPoints();
//        int i = 0;
//        while (i < rectPoints.length) {
//            i++;
//            canvas.drawLine((float) rectPoints[i].x, (float) rectPoints[i].y, (float) rectPoints[i % rectPoints.length].x, (float) rectPoints[i % rectPoints.length].y, this.mFramePaint);
//        }
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setFilterBitmap(true);
//        paint.setDither(true);
//        Matrix matrix = new Matrix();
//        matrix.setScale(MENU_BUTTON_SIZE / ((float) this.mDeleteBitmap.getWidth()), MENU_BUTTON_SIZE / ((float) this.mDeleteBitmap.getHeight()));
//        float f = MENU_BUTTON_SIZE;
//        matrix.postRotate((float) decor.getRotation(), f / 2.0f, f / 2.0f);
//        matrix.postTranslate(((float) rectPoints[0].x) - (MENU_BUTTON_SIZE / 2.0f), ((float) rectPoints[0].y) - (MENU_BUTTON_SIZE / 2.0f));
//        canvas.drawBitmap(this.mDeleteBitmap, matrix, paint);
//        Matrix matrix2 = new Matrix();
//        matrix2.setScale(MENU_BUTTON_SIZE / ((float) this.mOpenBitmap.getWidth()), MENU_BUTTON_SIZE / ((float) this.mOpenBitmap.getHeight()));
//        float f2 = MENU_BUTTON_SIZE;
//        matrix2.postRotate((float) decor.getRotation(), f2 / 2.0f, f2 / 2.0f);
//        matrix2.postTranslate(((float) rectPoints[2].x) - (MENU_BUTTON_SIZE / 2.0f), ((float) rectPoints[2].y) - (MENU_BUTTON_SIZE / 2.0f));
//        canvas.drawBitmap(this.mOpenBitmap, matrix2, paint);
//    }
//
//    private Matrix getMatrix(Bitmap bitmap) {
//        Matrix matrix = new Matrix();
//        int height = getHeight();
//        float max = Math.max(((float) getWidth()) / ((float) bitmap.getWidth()), ((float) height) / ((float) bitmap.getHeight())) * 1.3f;
//        this.mScale = max;
//        float f = 2.0f * max;
//        float f2 = 1.2f * max;
//        this.mDeltaY = (((((float) getHeight()) - (((float) bitmap.getHeight()) * max)) / f) - ((((float) getHeight()) - (((float) bitmap.getHeight()) * max)) / f2)) * 1.3f;
//        matrix.preScale(max, max);
//        matrix.preTranslate((((float) getWidth()) - (((float) bitmap.getWidth()) * max)) / f, (((float) getHeight()) - (((float) bitmap.getHeight()) * max)) / f2);
//        return matrix;
//    }
//
//    private float lengthTwoPoint(float f, float f2, float f3, float f4) {
//        float f5 = f3 - f;
//        float f6 = f4 - f2;
//        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
//    }
//
//    public Decor addDecoItem(Bitmap bitmap, int i) {
//        Decor decor = new Decor(bitmap, (float) (getWidth() / 2), (float) (getHeight() / 2), ((float) bitmap.getWidth()) * 1.0f, ((float) bitmap.getHeight()) * 1.0f, (Paint) null, 0, i, 1.0f);
//        addDecoItem(decor);
//        return decor;
//    }
//
//    public DecorText addDecoItemText(Bitmap bitmap, int i, String str) {
//        float max = Math.max(bitmap.getWidth(), bitmap.getHeight()) >= Math.max(getWidth() / 2, getHeight() / 2) ? ((float) Math.max(getWidth() / 2, getHeight() / 2)) / ((float) Math.max(bitmap.getWidth(), bitmap.getHeight())) : 1.0f;
//        DecorText decorText = new DecorText(bitmap, (float) (getWidth() / 2), (float) (getHeight() / 2), ((float) bitmap.getWidth()) * max, ((float) bitmap.getHeight()) * max, (Paint) null, 0, i, max);
//        this.mDecorText = decorText;
//        decorText.setText(str);
//        addDecoItem(this.mDecorText);
//        return this.mDecorText;
//    }
//
//    public void addDecoItem(Decor decor) {
//        List<Decor> list = this.mDecors;
//        if (list != null) {
//            list.add(decor);
//            this.mFocusObjectIndex = this.mDecors.size() - 1;
//        }
//        invalidate();
//    }
//
//    public int getDecorCount() {
//        return this.mDecors.size();
//    }
//
//    public void removeFrame() {
//        this.mFocusObjectIndex = -1;
//        invalidate();
//    }
//
//    /* access modifiers changed from: protected */
//    public void onDetachedFromWindow() {
//        clearData();
//        super.onDetachedFromWindow();
//    }
//}
