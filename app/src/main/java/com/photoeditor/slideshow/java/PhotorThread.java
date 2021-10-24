package com.photoeditor.slideshow.java;

import android.os.AsyncTask;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

public class PhotorThread {
    private static PhotorThread ThreadUtils;
    private List<AsyncTask> listTasks = new ArrayList();

    public interface IBackground {
        void doingBackground();

        void onCancel();

        void onCompleted();
    }

    public interface IHandler {
        void onWork();
    }

    public interface IHandlerData<E> {
        void onWork(E e);
    }

    public interface IHandlerExtra extends IHandler {
        void onLoadFailed();
    }

    public static PhotorThread getInstance() {
        if (ThreadUtils == null) {
            ThreadUtils = new PhotorThread();
        }
        return ThreadUtils;
    }

    public DoJobBackground runBackground(IBackground iBackground) {
        DoJobBackground doJobBackground = new DoJobBackground();
        doJobBackground.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new IBackground[]{iBackground});
        this.listTasks.add(doJobBackground);
        return doJobBackground;
    }

    public void removeAllBackgroundThreads() {
        List<AsyncTask> list = this.listTasks;
        if (list != null && !list.isEmpty()) {
            for (AsyncTask next : this.listTasks) {
                if (!next.isCancelled() && next.getStatus().equals(AsyncTask.Status.RUNNING)) {
                    next.cancel(true);
                }
            }
            this.listTasks.clear();
        }
        this.listTasks = null;
        ThreadUtils = null;
    }

    public Handler runOnUI(final IHandler iHandler) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            public void run() {
                if (iHandler != null) {
                    iHandler.onWork();
                }
            }
        });
        return handler;
    }

    public Handler runUIDelay(final IHandler iHandler, int i) {
        Handler handler = new Handler();
        handler.postDelayed(() -> iHandler.onWork(), i);
        return handler;
    }

    public class DoJobBackground extends AsyncTask<IBackground, Void, Void> {
        private IBackground mListener;

        public DoJobBackground() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(IBackground... iBackgroundArr) {
            IBackground iBackground = iBackgroundArr[0];
            this.mListener = iBackground;
            if (iBackground == null) {
                return null;
            }
            iBackground.doingBackground();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            super.onCancelled();
            IBackground iBackground = this.mListener;
            if (iBackground != null) {
                iBackground.onCancel();
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            IBackground iBackground = this.mListener;
            if (iBackground != null) {
                iBackground.onCompleted();
            }
        }
    }
}
