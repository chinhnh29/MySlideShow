package com.photoeditor.slideshow;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressLoadingDialogUtil {
    private static ProgressDialog pd;


    public static void showProgressDialog(Context context) {
        pd = new ProgressDialog(context);
        pd.setMessage("loading");
        pd.show();
    }

    public static void hideProgressDialog() {
        if (pd != null) pd.dismiss();
    }
}
