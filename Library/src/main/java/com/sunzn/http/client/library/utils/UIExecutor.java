package com.sunzn.http.client.library.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UIExecutor {

    private static final UIExecutor UIEXECUTOR = initUIExecutor();

    public static UIExecutor get() {
        return UIEXECUTOR;
    }

    private static UIExecutor initUIExecutor() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new AndroidUIExecutor();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new UIExecutor();
    }

    public Executor initExecutor() {
        return Executors.newCachedThreadPool();
    }

    public void execute(Runnable runnable) {
        initExecutor().execute(runnable);
    }

    static class AndroidUIExecutor extends UIExecutor {

        @Override
        public Executor initExecutor() {
            return new MainThreadExecutor();
        }

        static class MainThreadExecutor implements Executor {

            private final Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(@NonNull Runnable runnable) {
                handler.post(runnable);
            }

        }

    }

}
