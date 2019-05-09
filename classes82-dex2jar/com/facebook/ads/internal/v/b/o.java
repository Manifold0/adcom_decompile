// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import android.util.Log;
import android.os.Environment;
import java.io.File;
import android.content.Context;

public final class o
{
    public static File a(final Context context) {
        return new File(a(context, true), "video-cache");
    }
    
    private static File a(Context string, final boolean b) {
    Label_0093_Outer:
        while (true) {
            final File file = null;
            while (true) {
            Label_0186:
                while (true) {
                    try {
                        final String externalStorageState = Environment.getExternalStorageState();
                        File file2 = file;
                        if (b) {
                            file2 = file;
                            if ("mounted".equals(externalStorageState)) {
                                file2 = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), string.getPackageName()), "cache");
                                if (file2.exists() || file2.mkdirs()) {
                                    break Label_0186;
                                }
                                Log.w("ProxyCache", "Unable to create external cache directory");
                                file2 = file;
                            }
                        }
                        File cacheDir;
                        if ((cacheDir = file2) == null) {
                            cacheDir = string.getCacheDir();
                        }
                        File file3;
                        if ((file3 = cacheDir) == null) {
                            string = (Context)("/data/data/" + string.getPackageName() + "/cache/");
                            Log.w("ProxyCache", "Can't define system cache directory! '" + (String)string + "%s' will be used.");
                            file3 = new File((String)string);
                        }
                        return file3;
                    }
                    catch (NullPointerException ex) {
                        final String externalStorageState = "";
                        continue Label_0093_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
}
