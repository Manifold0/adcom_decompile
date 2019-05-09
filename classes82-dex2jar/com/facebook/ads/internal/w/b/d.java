// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import org.xmlpull.v1.XmlPullParser;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.res.XmlResourceParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import android.content.SharedPreferences;
import android.os.Build$VERSION;
import com.facebook.ads.internal.w.f.a;
import java.util.concurrent.Executors;
import android.content.Context;

public class d
{
    public static volatile a a;
    private static int b;
    
    static {
        d.b = -1;
        d.a = d.a.a;
    }
    
    public static int a(final Context context) {
        if (d.a == d.a.a) {
            boolean b;
            if (d.a == d.a.c) {
                b = true;
            }
            else {
                b = false;
            }
            if (!b && d.a == d.a.a) {
                d.a = d.a.b;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (d.a == d.a.c) {
                            return;
                        }
                        final SharedPreferences sharedPreferences = context.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("FBAdPrefs", context), 0);
                        final int int1 = sharedPreferences.getInt("AppMinSdkVersion", -1);
                        if (int1 != -1) {
                            d.b = int1;
                            d.a = d.a.c;
                            return;
                        }
                        int n;
                        if (Build$VERSION.SDK_INT >= 24) {
                            n = d(context);
                        }
                        else {
                            n = d.b(context);
                        }
                        d.b = n;
                        sharedPreferences.edit().putInt("AppMinSdkVersion", n).commit();
                        d.a = d.a.c;
                    }
                });
            }
        }
        return d.b;
    }
    
    public static int b(final Context context) {
        final int n = 0;
        try {
            final XmlResourceParser openXmlResourceParser = context.getAssets().openXmlResourceParser("AndroidManifest.xml");
            int int1 = 0;
        Label_0086:
            while (true) {
                int1 = n;
                if (((XmlPullParser)openXmlResourceParser).next() == 1) {
                    break;
                }
                if (((XmlPullParser)openXmlResourceParser).getEventType() != 2 || !((XmlPullParser)openXmlResourceParser).getName().equals("uses-sdk")) {
                    continue;
                }
                for (int i = 0; i < ((XmlPullParser)openXmlResourceParser).getAttributeCount(); ++i) {
                    if (((XmlPullParser)openXmlResourceParser).getAttributeName(i).equals("minSdkVersion")) {
                        int1 = Integer.parseInt(((XmlPullParser)openXmlResourceParser).getAttributeValue(i));
                        break Label_0086;
                    }
                }
            }
            return int1;
        }
        catch (IOException ex) {
            return 0;
        }
        catch (XmlPullParserException ex2) {
            return 0;
        }
    }
    
    private static int d(final Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).minSdkVersion;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return 0;
        }
    }
    
    enum a
    {
        a, 
        b, 
        c;
    }
}
