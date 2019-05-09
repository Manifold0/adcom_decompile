package com.tapjoy.internal;

import android.content.Context;
import com.ironsource.sdk.constants.Constants;
import java.io.File;

/* renamed from: com.tapjoy.internal.w */
public final class C2996w {
    /* renamed from: a */
    private static String f8236a = Constants.JAVASCRIPT_INTERFACE_NAME;
    /* renamed from: b */
    private static String f8237b = "data";

    /* renamed from: a */
    public static File m8226a(Context context) {
        try {
            File externalFilesDir = context.getExternalFilesDir(null);
            int i = 0;
            while (externalFilesDir != null && i < 2) {
                i++;
                externalFilesDir = externalFilesDir.getParentFile();
            }
            return externalFilesDir;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
