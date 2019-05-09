// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.ipc;

import com.facebook.ads.CacheFlag;
import java.io.Serializable;
import com.facebook.ads.internal.c.e;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.internal.c.j;
import com.facebook.ads.internal.w.h.b;
import com.facebook.ads.internal.c.a.c;
import com.facebook.ads.internal.c.d;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.internal.c.g;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.EnumSet;
import android.os.Message;
import android.content.Context;

public class a
{
    public static final String a;
    private final Context b;
    private final com.facebook.ads.internal.e.a c;
    
    static {
        a = a.class.getSimpleName();
    }
    
    a(final Context b) {
        this.b = b;
        this.c = com.facebook.ads.internal.e.a.a();
    }
    
    public boolean a(final Message message) {
        final String string = message.getData().getString("STR_AD_ID_KEY");
        switch (message.what) {
            default: {
                return false;
            }
            case 1010: {
                final String string2 = message.getData().getString("STR_PLACEMENT_KEY");
                final String string3 = message.getData().getString("STR_BID_PAYLOAD_KEY");
                final EnumSet e = (EnumSet)message.getData().getSerializable("SRL_INT_CACHE_FLAGS_KEY");
                final String string4 = message.getData().getString("STR_EXTRA_HINTS_KEY");
                AdInternalSettings.a = message.getData().getBundle("BUNDLE_SETTINGS_KEY");
                final g g = new g(this.b, null, string2);
                g.d = string4;
                g.f = string3;
                g.e = (EnumSet<CacheFlag>)e;
                final com.facebook.ads.internal.e.a.a e2 = com.facebook.ads.internal.e.a.a().e(string);
                if (e2 != null) {
                    if (e2.c == null) {
                        final d c = new d(g, this.c, string);
                        c.a(g.e, g.f);
                        e2.c = c;
                    }
                    else if (e2.c instanceof d) {
                        ((d)e2.c).a(g.e, g.f);
                    }
                    this.c.a(1015, string);
                }
                else {
                    com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing ad: " + string));
                }
                return true;
            }
            case 2000: {
                final String string5 = message.getData().getString("STR_PLACEMENT_KEY");
                final String string6 = message.getData().getString("STR_BID_PAYLOAD_KEY");
                final boolean boolean1 = message.getData().getBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY");
                final String string7 = message.getData().getString("STR_EXTRA_HINTS_KEY");
                AdInternalSettings.a = message.getData().getBundle("BUNDLE_SETTINGS_KEY");
                final j j = new j(this.b, string5, null);
                j.d = string7;
                j.f = string6;
                j.g = (boolean)boolean1;
                final Serializable serializable = message.getData().getSerializable("SRL_RV_REWARD_DATA_KEY");
                if (serializable instanceof RewardData) {
                    j.e = (RewardData)serializable;
                }
                final com.facebook.ads.internal.e.a.a e3 = com.facebook.ads.internal.e.a.a().e(string);
                if (e3 != null) {
                    if (e3.c == null) {
                        final e c2 = new e(j, this.c, string);
                        c2.a(j.f, j.g);
                        e3.c = c2;
                    }
                    else if (e3.c instanceof e) {
                        ((e)e3.c).a(j.f, j.g);
                    }
                    this.c.a(2010, string);
                }
                else {
                    com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing ad: " + string));
                }
                return true;
            }
            case 1011: {
                final com.facebook.ads.internal.c.c a = com.facebook.ads.internal.e.a.a().a(string);
                if (a != null && a instanceof d) {
                    ((d)a).e();
                    this.c.a(1016, string);
                }
                else {
                    com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing bundle for message: " + message));
                }
                return true;
            }
            case 2001: {
                final com.facebook.ads.internal.c.c a2 = com.facebook.ads.internal.e.a.a().a(string);
                if (a2 != null && a2 instanceof e) {
                    ((e)a2).a(message.getData().getInt("INT_RV_APP_ORIENTATION_KEY", 0));
                    this.c.a(2011, string);
                }
                else {
                    com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing ad: " + string));
                }
                return true;
            }
            case 1012: {
                com.facebook.ads.internal.e.a.a().b(string);
                this.c.a(1017, string);
                return true;
            }
            case 2002: {
                com.facebook.ads.internal.e.a.a().b(string);
                this.c.a(2012, string);
                return true;
            }
            case 2003: {
                final com.facebook.ads.internal.c.c a3 = com.facebook.ads.internal.e.a.a().a(string);
                if (a3 != null && a3 instanceof e) {
                    final e e4 = (e)a3;
                    final Serializable serializable2 = message.getData().getSerializable("SRL_RV_REWARD_DATA_KEY");
                    if (serializable2 instanceof RewardData) {
                        e4.a((RewardData)serializable2);
                    }
                }
                else {
                    com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing ad: " + string));
                }
                return true;
            }
        }
    }
}
