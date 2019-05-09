package com.applovin.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.widget.ImageView;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.gd;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.io.File;

public class AppLovinSdkUtils {
    public static final String TAG = "AppLovinSdkUtils";
    /* renamed from: a */
    private static final Handler f2565a = new Handler(Looper.getMainLooper());

    public static int dpToPx(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static boolean isLocalFile(Uri uri) {
        return uri != null && ParametersKeys.FILE.equalsIgnoreCase(uri.getScheme());
    }

    public static boolean isValidString(String str) {
        return str != null && str.length() > 1;
    }

    public static boolean openUri(Context context, Uri uri, AppLovinSdkImpl appLovinSdkImpl) {
        boolean z;
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!(context instanceof Activity)) {
                intent.setFlags(DriveFile.MODE_READ_ONLY);
            }
            appLovinSdkImpl.getSessionTracker().m2647a();
            context.startActivity(intent);
            z = true;
        } catch (Throwable th) {
            appLovinSdkImpl.getLogger().mo4174e(TAG, "Unable to open \"" + uri + "\".", th);
            z = false;
        }
        if (!z) {
            appLovinSdkImpl.getSessionTracker().m2649b();
        }
        return z;
    }

    public static void recycleImageView(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null && (drawable instanceof BitmapDrawable)) {
                ((BitmapDrawable) drawable).getBitmap().recycle();
            }
        }
    }

    public static String retrieveSdkKey(Context context) {
        Bundle d = gd.m2955d(context);
        if (d == null) {
            return null;
        }
        String string = d.getString("applovin.sdk.key");
        return string != null ? string : "";
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            f2565a.post(runnable);
        }
    }

    public static void safePopulateImageView(Context context, ImageView imageView, int i, int i2) {
        recycleImageView(imageView);
        Bitmap a = gd.m2934a(context, i, i2);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }

    public static void safePopulateImageView(ImageView imageView, Bitmap bitmap) {
        recycleImageView(imageView);
        if (imageView != null && bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void safePopulateImageView(ImageView imageView, Uri uri, int i) {
        recycleImageView(imageView);
        Bitmap a = gd.m2935a(new File(uri.getPath()), i);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }
}
