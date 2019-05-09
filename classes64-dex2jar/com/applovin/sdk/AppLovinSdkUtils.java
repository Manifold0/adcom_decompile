// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import java.io.File;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.applovin.impl.sdk.gd;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import android.net.Uri;
import android.util.TypedValue;
import android.content.Context;
import android.os.Looper;
import android.os.Handler;

public class AppLovinSdkUtils
{
    public static final String TAG = "AppLovinSdkUtils";
    private static final Handler a;
    
    static {
        a = new Handler(Looper.getMainLooper());
    }
    
    public static int dpToPx(final Context context, final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, context.getResources().getDisplayMetrics());
    }
    
    public static boolean isLocalFile(final Uri uri) {
        return uri != null && "file".equalsIgnoreCase(uri.getScheme());
    }
    
    public static boolean isValidString(final String s) {
        return s != null && s.length() > 1;
    }
    
    public static boolean openUri(final Context context, final Uri uri, final AppLovinSdkImpl appLovinSdkImpl) {
        while (true) {
            try {
                final Intent intent = new Intent("android.intent.action.VIEW", uri);
                if (!(context instanceof Activity)) {
                    intent.setFlags(268435456);
                }
                appLovinSdkImpl.getSessionTracker().a();
                context.startActivity(intent);
                final boolean b = true;
                if (!b) {
                    appLovinSdkImpl.getSessionTracker().b();
                }
                return b;
            }
            catch (Throwable t) {
                appLovinSdkImpl.getLogger().e("AppLovinSdkUtils", "Unable to open \"" + uri + "\".", t);
                final boolean b = false;
                continue;
            }
            break;
        }
    }
    
    public static void recycleImageView(final ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                ((BitmapDrawable)drawable).getBitmap().recycle();
            }
        }
    }
    
    public static String retrieveSdkKey(final Context context) {
        final Bundle d = gd.d(context);
        if (d == null) {
            return null;
        }
        final String string = d.getString("applovin.sdk.key");
        if (string != null) {
            return string;
        }
        return "";
    }
    
    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
            return;
        }
        AppLovinSdkUtils.a.post(runnable);
    }
    
    public static void safePopulateImageView(final Context context, final ImageView imageView, final int n, final int n2) {
        recycleImageView(imageView);
        final Bitmap a = gd.a(context, n, n2);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }
    
    public static void safePopulateImageView(final ImageView imageView, final Bitmap imageBitmap) {
        recycleImageView(imageView);
        if (imageView != null && imageBitmap != null) {
            imageView.setImageBitmap(imageBitmap);
        }
    }
    
    public static void safePopulateImageView(final ImageView imageView, final Uri uri, final int n) {
        recycleImageView(imageView);
        final Bitmap a = gd.a(new File(uri.getPath()), n);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }
}
