package com.facebook.ads.internal.p025w.p026b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import java.io.IOException;
import java.util.concurrent.Executors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: com.facebook.ads.internal.w.b.d */
public class C2570d {
    /* renamed from: a */
    public static volatile C2569a f6327a = C2569a.NOT_INITIALIZED;
    /* renamed from: b */
    private static int f6328b = -1;

    /* renamed from: com.facebook.ads.internal.w.b.d$a */
    enum C2569a {
        NOT_INITIALIZED,
        INITIALIZING,
        INITIALIZED
    }

    /* renamed from: a */
    public static int m6623a(final Context context) {
        if (f6327a == C2569a.NOT_INITIALIZED) {
            if ((f6327a == C2569a.INITIALIZED ? 1 : null) == null && f6327a == C2569a.NOT_INITIALIZED) {
                f6327a = C2569a.INITIALIZING;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    public void run() {
                        if (C2570d.f6327a != C2569a.INITIALIZED) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences(C2616a.m6730a("FBAdPrefs", context), 0);
                            int i = sharedPreferences.getInt("AppMinSdkVersion", -1);
                            if (i != -1) {
                                C2570d.f6328b = i;
                                C2570d.f6327a = C2569a.INITIALIZED;
                                return;
                            }
                            i = VERSION.SDK_INT >= 24 ? C2570d.m6626d(context) : C2570d.m6624b(context);
                            C2570d.f6328b = i;
                            sharedPreferences.edit().putInt("AppMinSdkVersion", i).commit();
                            C2570d.f6327a = C2569a.INITIALIZED;
                        }
                    }
                });
            }
        }
        return f6328b;
    }

    /* renamed from: b */
    public static int m6624b(Context context) {
        int i = 0;
        try {
            XmlPullParser openXmlResourceParser = context.getAssets().openXmlResourceParser("AndroidManifest.xml");
            loop0:
            while (openXmlResourceParser.next() != 1) {
                if (openXmlResourceParser.getEventType() == 2 && openXmlResourceParser.getName().equals("uses-sdk")) {
                    for (int i2 = 0; i2 < openXmlResourceParser.getAttributeCount(); i2++) {
                        if (openXmlResourceParser.getAttributeName(i2).equals("minSdkVersion")) {
                            i = Integer.parseInt(openXmlResourceParser.getAttributeValue(i2));
                            break loop0;
                        }
                    }
                    continue;
                }
            }
        } catch (XmlPullParserException e) {
        } catch (IOException e2) {
        }
        return i;
    }

    /* renamed from: d */
    private static int m6626d(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).minSdkVersion;
        } catch (NameNotFoundException e) {
            return i;
        }
    }
}
