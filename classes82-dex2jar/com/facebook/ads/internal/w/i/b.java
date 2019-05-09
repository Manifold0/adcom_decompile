// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.i;

import com.facebook.ads.internal.w.b.z;
import android.view.Window;
import com.facebook.ads.internal.w.h.a;
import android.app.Activity;
import android.app.KeyguardManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class b
{
    private static final String a;
    
    static {
        a = b.class.getSimpleName();
    }
    
    public static Map<String, String> a(final Context context) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (context == null) {
            Log.v(b.a, "Null context in window interactive check.");
            return hashMap;
        }
        KeyguardManager keyguardManager;
        boolean b;
        Window window;
        int flags;
        String s;
        String s2;
        Label_0119_Outer:Label_0140_Outer:
        while (true) {
            Label_0190: {
                while (true) {
                Label_0213:
                    while (true) {
                    Label_0207:
                        while (true) {
                            Label_0202: {
                                try {
                                    keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
                                    if (keyguardManager == null || !keyguardManager.inKeyguardRestrictedInputMode()) {
                                        break Label_0202;
                                    }
                                    b = true;
                                    hashMap.put("kgr", String.valueOf(b));
                                    if (!(context instanceof Activity)) {
                                        break Label_0190;
                                    }
                                    window = ((Activity)context).getWindow();
                                    if (window != null) {
                                        flags = window.getAttributes().flags;
                                        hashMap.put("wt", Integer.toString(window.getAttributes().type));
                                        if ((0x400000 & flags) <= 0) {
                                            break Label_0207;
                                        }
                                        s = "1";
                                        hashMap.put("wfdkg", s);
                                        if ((0x80000 & flags) <= 0) {
                                            break Label_0213;
                                        }
                                        s2 = "1";
                                        hashMap.put("wfswl", s2);
                                    }
                                    else {
                                        Log.v(com.facebook.ads.internal.w.i.b.a, "Invalid window in window interactive check, assuming interactive.");
                                    }
                                }
                                catch (Exception ex) {
                                    Log.e(com.facebook.ads.internal.w.i.b.a, "Exception in window info check", (Throwable)ex);
                                    com.facebook.ads.internal.w.h.a.b(context, "risky", com.facebook.ads.internal.w.h.b.H, ex);
                                }
                                break;
                            }
                            b = false;
                            continue Label_0119_Outer;
                        }
                        s = "0";
                        continue Label_0140_Outer;
                    }
                    s2 = "0";
                    continue;
                }
            }
            Log.v(com.facebook.ads.internal.w.i.b.a, "Invalid Activity context in window interactive check, assuming interactive.");
            break;
        }
        return hashMap;
    }
    
    public static boolean b(final Context context) {
        return !z.b(a(context));
    }
}
