// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.io.File;
import android.content.SharedPreferences$Editor;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class SharedPreferencesUtils
{
    private SharedPreferencesUtils() {
    }
    
    @Deprecated
    @KeepForSdk
    public static void publishWorldReadableSharedPreferences(final Context context, final SharedPreferences$Editor sharedPreferences$Editor, final String s) {
        final File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
        final File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.setExecutable(true, false);
        }
        file.setExecutable(true, false);
        sharedPreferences$Editor.commit();
        new File(file, String.valueOf(s).concat(".xml")).setReadable(true, false);
    }
}
