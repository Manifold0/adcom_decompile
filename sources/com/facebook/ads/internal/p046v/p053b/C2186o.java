package com.facebook.ads.internal.p046v.p053b;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.ironsource.sdk.constants.Constants;
import java.io.File;

/* renamed from: com.facebook.ads.internal.v.b.o */
public final class C2186o {
    /* renamed from: a */
    public static File m5597a(Context context) {
        return new File(C2186o.m5598a(context, true), "video-cache");
    }

    /* renamed from: a */
    private static File m5598a(Context context, boolean z) {
        File file = null;
        Object externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        if (z && "mounted".equals(r1)) {
            File file2 = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), Constants.JAVASCRIPT_INTERFACE_NAME), "data"), context.getPackageName()), "cache");
            if (file2.exists() || file2.mkdirs()) {
                file = file2;
            } else {
                Log.w("ProxyCache", "Unable to create external cache directory");
            }
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        Log.w("ProxyCache", "Can't define system cache directory! '" + str + "%s' will be used.");
        return new File(str);
    }
}
