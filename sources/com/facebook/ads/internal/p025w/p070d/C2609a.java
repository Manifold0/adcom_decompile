package com.facebook.ads.internal.p025w.p070d;

import android.content.Context;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import java.io.File;

/* renamed from: com.facebook.ads.internal.w.d.a */
public final class C2609a {
    /* renamed from: a */
    public static boolean m6706a(Context context) {
        try {
            return new File(context.getFilesDir(), "com.facebook.ads.ipc").exists();
        } catch (Exception e) {
            C2625a.m6738a(context, "ipc", C2626b.ac, e);
            return false;
        }
    }
}
