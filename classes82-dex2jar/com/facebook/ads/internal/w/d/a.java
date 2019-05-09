// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.d;

import com.facebook.ads.internal.w.h.b;
import java.io.File;
import android.content.Context;

public final class a
{
    public static boolean a(final Context context) {
        try {
            return new File(context.getFilesDir(), "com.facebook.ads.ipc").exists();
        }
        catch (Exception ex) {
            com.facebook.ads.internal.w.h.a.a(context, "ipc", b.ac, ex);
            return false;
        }
    }
}
