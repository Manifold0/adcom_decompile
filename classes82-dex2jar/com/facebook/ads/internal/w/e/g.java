// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.e;

import android.content.ActivityNotFoundException;
import java.io.Serializable;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.r.a;
import java.util.Iterator;
import android.content.pm.ResolveInfo;
import android.os.Build$VERSION;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;

public class g
{
    public static void a(final g g, final Context context, final Uri uri, final String s) {
        Label_0049: {
            if (!a(uri.getScheme()) || !uri.getHost().equals("play.google.com")) {
                break Label_0049;
            }
            int n = 1;
            while (true) {
                if (!uri.getScheme().equals("market")) {
                    if (n == 0) {
                        break Label_0049;
                    }
                }
                try {
                    g.a(context, uri);
                    return;
                    n = 0;
                    continue;
                }
                catch (c c) {
                    g.a(context, uri, s);
                    return;
                }
                break;
            }
        }
        g.a(context, uri, s);
    }
    
    private static boolean a(final String s) {
        return "http".equalsIgnoreCase(s) || "https".equalsIgnoreCase(s);
    }
    
    private void b(final Context context, final Uri uri) {
        context.startActivity(this.c(context, uri));
    }
    
    private Intent c(final Context context, final Uri uri) {
        final Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent((ComponentName)null);
        if (Build$VERSION.SDK_INT >= 15) {
            intent.setSelector((Intent)null);
        }
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addFlags(268435456);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("create_new_tab", false);
        return intent;
    }
    
    public void a(final Context context, final Uri uri) {
        final Iterator<ResolveInfo> iterator = (Iterator<ResolveInfo>)context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/")), 0).iterator();
        while (true) {
            while (iterator.hasNext()) {
                if (iterator.next().activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                    final int n = 1;
                    if (n == 0) {
                        throw new c();
                    }
                    final Intent c = this.c(context, uri);
                    c.setPackage("com.android.vending");
                    context.startActivity(c);
                    return;
                }
            }
            final int n = 0;
            continue;
        }
    }
    
    public void a(final Context context, final Uri uri, final String s) {
        if (a(uri.getScheme()) && a.o(context)) {
            final Intent intent = new Intent();
            final String packageName = context.getPackageName();
            Label_0110: {
                if (!AdInternalSettings.d) {
                    break Label_0110;
                }
                String s2 = "com.facebook.ads.internal.ipc.RemoteANActivity";
                while (true) {
                    intent.setClassName(packageName, s2);
                    intent.addFlags(268435456);
                    intent.putExtra("viewType", (Serializable)com.facebook.ads.internal.settings.a.a.k);
                    intent.putExtra("browserURL", uri.toString());
                    intent.putExtra("clientToken", s);
                    intent.putExtra("handlerTime", System.currentTimeMillis());
                    try {
                        context.startActivity(intent);
                        return;
                        s2 = "com.facebook.ads.AudienceNetworkActivity";
                        continue;
                    }
                    catch (ActivityNotFoundException ex) {
                        intent.setClassName(context.getPackageName(), "com.facebook.ads.InterstitialAdActivity");
                        try {
                            context.startActivity(intent);
                            return;
                        }
                        catch (ActivityNotFoundException ex2) {
                            this.b(context, uri);
                            return;
                        }
                    }
                    break;
                }
            }
        }
        this.b(context, uri);
    }
}
