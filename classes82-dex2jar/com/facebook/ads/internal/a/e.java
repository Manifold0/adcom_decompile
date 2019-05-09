// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.Iterator;
import com.facebook.ads.internal.w.h.a;
import com.facebook.ads.internal.w.h.b;
import java.util.Map;
import android.text.TextUtils;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import java.util.HashSet;
import java.util.Collection;
import org.json.JSONArray;

public class e
{
    public static Collection<String> a(final JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        final HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            set.add(jsonArray.optString(i));
        }
        return set;
    }
    
    public static boolean a(final Context context, final a a, final c c) {
        final d a2 = a.a();
        if (a2 != null && a2 != d.a) {
            final Collection<String> b = a.b();
            if (b != null && !b.isEmpty()) {
                final Iterator<String> iterator = b.iterator();
                while (true) {
                    while (iterator.hasNext()) {
                        if (a(context, iterator.next())) {
                            final int n = 1;
                            int n2;
                            if (a2 == d.b) {
                                n2 = 1;
                            }
                            else {
                                n2 = 0;
                            }
                            if (n != n2) {
                                return false;
                            }
                            final String clientToken = a.getClientToken();
                            if (!TextUtils.isEmpty((CharSequence)clientToken)) {
                                c.b(clientToken, null);
                                return true;
                            }
                            a.b(context, "api", com.facebook.ads.internal.w.h.b.j, new Exception("Ad is invalidated without token."));
                            return true;
                        }
                    }
                    final int n = 0;
                    continue;
                }
            }
        }
        return false;
    }
    
    public static boolean a(final Context context, final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(s, 1);
            return true;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
        catch (RuntimeException ex2) {
            return false;
        }
    }
    
    public interface a
    {
        d a();
        
        Collection<String> b();
        
        String getClientToken();
    }
}
