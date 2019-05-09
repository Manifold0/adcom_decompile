// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.File;
import android.content.Context;

public final class w
{
    private static String a;
    private static String b;
    
    static {
        w.a = "Android";
        w.b = "data";
    }
    
    public static File a(final Context context) {
        File file2;
        try {
            File file = context.getExternalFilesDir((String)null);
            int n = 0;
            while (true) {
                file2 = file;
                if (file == null) {
                    break;
                }
                file2 = file;
                if (n >= 2) {
                    break;
                }
                file = file.getParentFile();
                ++n;
            }
        }
        catch (RuntimeException ex) {
            file2 = null;
        }
        return file2;
    }
}
