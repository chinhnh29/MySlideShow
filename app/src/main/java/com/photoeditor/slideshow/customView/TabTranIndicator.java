package com.photoeditor.slideshow.customView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.customView.TabTranIndicator;
import com.photoeditor.slideshow.java.PhotorThread;
import com.photoeditor.slideshow.models.DataCategory;

import java.util.List;

public class TabTranIndicator extends HorizontalScrollView {
    private static final String TAG = "BabyTabIconIndicator";
    private int height = 0;
    private List<DataCategory> mListUrls;
    /* access modifiers changed from: private */
    public OnTabListener mListener;
    private LinearLayout rootLayout;
    private int width = 0;

    public interface OnTabListener {
        void onTabChanged(int i);

        void onTabClicked(int i);
    }

    public TabTranIndicator(Context context) {
        super(context);
        init();
    }

    public TabTranIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TabTranIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setScrollbarFadingEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        this.rootLayout = new LinearLayout(getContext());
        setRootLayoutHeight(-2);
        this.rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(this.rootLayout);
    }

    private void setRootLayoutHeight(int i) {
        this.rootLayout.setLayoutParams(new LayoutParams(-2, i));
    }

    private void animateToTab(TabIcon tabIcon) {
        PhotorThread.getInstance().runOnUI(new PhotorThread.IHandler() {
            public final void onWork() {
                smoothScrollTo(tabIcon.getLeft() - ((getWidth() - tabIcon.getWidth()) / 2), 0);
            }
        });
    }

    public void setListener(OnTabListener onTabListener) {
        this.mListener = onTabListener;
    }

    public void addTabsFromUrl(List<DataCategory> list) {
        this.mListUrls = list;
    }

    public void display() {
        this.rootLayout.removeAllViews();
        List<DataCategory> list = this.mListUrls;
        if (list != null && !list.isEmpty()) {
            int size = this.mListUrls.size();
            for (int i = 0; i < size; i++) {
                addTab(i, this.mListUrls.get(i));
            }
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.width = layoutParams.width;
        this.height = layoutParams.height;
        super.setLayoutParams(layoutParams);
        setRootLayoutHeight(this.height);
    }

    public void setCurrentTab(int i) {
        int childCount = this.rootLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            TabIcon tabIcon = (TabIcon) this.rootLayout.getChildAt(i2);
            if (i2 == i) {
                tabIcon.setTabSelected(true);
                animateToTab(tabIcon);
            } else {
                tabIcon.setTabSelected(false);
            }
        }
    }

    private void addTab(int i, DataCategory dataCategory) {
        TabIcon url = new TabIcon(getContext()).setTabIndex(i).setUrl(dataCategory.getName());
        if (i == 2) {
            url.setTabSelected(true);
        }
        this.rootLayout.addView(url);
    }

    private class TabIcon extends LinearLayout {
        private int mTabIndex = 0;
        private LinearLayout root;
        private TextView textView;

        public TabIcon(Context context) {
            super(context);
            inflate(context, R.layout.item_tran_tab, this);
            this.root = (LinearLayout) findViewById(R.id.linear_root_item_tab_icon);
            this.textView = (TextView) findViewById(R.id.txt_name_tab);
            setTabSelected(false);
            this.root.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    TabIcon.this.lambda$new$0$TabTranIndicator$TabIcon(view);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$TabTranIndicator$TabIcon(View view) {
            if (TabTranIndicator.this.mListener != null) {
                TabTranIndicator.this.mListener.onTabClicked(this.mTabIndex);
            }
        }

        public TabIcon setUrl(String str) {
            this.textView.setText(str);
            return this;
        }

        public void setListener() {
            setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    if (TabTranIndicator.this.mListener != null) {
                        TabTranIndicator.this.mListener.onTabClicked(mTabIndex);
                    }
                }
            });
        }

        public int getTabIndex() {
            return this.mTabIndex;
        }

        public TabIcon setTabIndex(int i) {
            this.mTabIndex = i;
            return this;
        }

        /* access modifiers changed from: private */
        public void setTabSelected(boolean z) {
            if (z) {
                TextView textView2 = this.textView;
                textView2.setTypeface(textView2.getTypeface(), Typeface.BOLD);
                this.textView.setTextColor(Color.parseColor("#FCA352"));
                return;
            }
            TextView textView3 = this.textView;
            textView3.setTypeface(textView3.getTypeface(), Typeface.NORMAL);
            this.textView.setTextColor(Color.parseColor("#979797"));
        }
    }
}
