package com.xl.skinplugin.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.lang.reflect.InvocationTargetException;

public class XApp {
    private static Application application;

    private XApp() {
        throw new UnsupportedOperationException("unable to initialize");
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void init(@NonNull final Application app) {
        if (application == null) {
            XApp.application = app;
        }
    }

    protected static void init(@NonNull final Context context) {
        init((Application) context.getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static Application getApp() {
        if (application != null) {
            return application;
        }
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            init((Application) app);
            return application;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

}
