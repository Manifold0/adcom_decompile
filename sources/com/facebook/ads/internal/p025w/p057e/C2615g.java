package com.facebook.ads.internal.p025w.p057e;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import com.adjust.sdk.Constants;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.C2094a.C2093a;
import com.google.android.gms.drive.DriveFile;

/* renamed from: com.facebook.ads.internal.w.e.g */
public class C2615g {
    /* renamed from: a */
    public static void m6721a(C2615g c2615g, Context context, Uri uri, String str) {
        Object obj = (C2615g.m6722a(uri.getScheme()) && uri.getHost().equals("play.google.com")) ? 1 : null;
        if (uri.getScheme().equals("market") || obj != null) {
            try {
                c2615g.m6725a(context, uri);
                return;
            } catch (C2611c e) {
                c2615g.m6726a(context, uri, str);
                return;
            }
        }
        c2615g.m6726a(context, uri, str);
    }

    /* renamed from: a */
    private static boolean m6722a(String str) {
        return "http".equalsIgnoreCase(str) || Constants.SCHEME.equalsIgnoreCase(str);
    }

    /* renamed from: b */
    private void m6723b(Context context, Uri uri) {
        context.startActivity(m6724c(context, uri));
    }

    /* renamed from: c */
    private Intent m6724c(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent(null);
        if (VERSION.SDK_INT >= 15) {
            intent.setSelector(null);
        }
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("create_new_tab", false);
        return intent;
    }

    /* renamed from: a */
    public void m6725a(Context context, Uri uri) {
        int i;
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/")), 0)) {
            if (resolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            throw new C2611c();
        }
        Intent c = m6724c(context, uri);
        c.setPackage("com.android.vending");
        context.startActivity(c);
    }

    /* renamed from: a */
    public void m6726a(Context context, Uri uri, String str) {
        if (C2615g.m6722a(uri.getScheme()) && C2078a.m5105o(context)) {
            Intent intent = new Intent();
            intent.setClassName(context.getPackageName(), AdInternalSettings.f4779d ? "com.facebook.ads.internal.ipc.RemoteANActivity" : "com.facebook.ads.AudienceNetworkActivity");
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, C2093a.BROWSER);
            intent.putExtra(AudienceNetworkActivity.BROWSER_URL, uri.toString());
            intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, str);
            intent.putExtra(AudienceNetworkActivity.HANDLER_TIME, System.currentTimeMillis());
            try {
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                intent.setClassName(context.getPackageName(), "com.facebook.ads.InterstitialAdActivity");
                try {
                    context.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException e2) {
                    m6723b(context, uri);
                    return;
                }
            }
        }
        m6723b(context, uri);
    }
}
