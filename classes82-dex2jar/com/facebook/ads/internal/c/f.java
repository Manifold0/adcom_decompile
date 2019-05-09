// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.c.a.c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.b.e;
import com.facebook.ads.CacheFlag;
import java.util.EnumSet;
import com.facebook.ads.InterstitialAdExtendedListener;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.w.h.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.Serializable;
import android.os.Handler;
import android.os.Message;
import com.facebook.ads.InterstitialAd;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

@UiThread
public class f extends b
{
    private final g e;
    @Nullable
    private d f;
    
    public f(final g e) {
        super(e.a.getApplicationContext());
        this.e = e;
    }
    
    private void h() {
        this.a(1012, null);
        this.b.b();
        this.e.a(null);
    }
    
    @Override
    Message a() {
        final Message obtain = Message.obtain((Handler)null, 1010);
        obtain.getData().putString("STR_PLACEMENT_KEY", this.e.b);
        obtain.getData().putString("STR_AD_ID_KEY", this.c);
        obtain.getData().putString("STR_BID_PAYLOAD_KEY", this.e.f);
        obtain.getData().putString("STR_EXTRA_HINTS_KEY", this.e.d);
        obtain.getData().putSerializable("SRL_INT_CACHE_FLAGS_KEY", (Serializable)this.e.e);
        obtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.a);
        return obtain;
    }
    
    @Override
    public void a(final Message message) {
        final InterstitialAd a = this.e.a();
        if (a == null) {
            com.facebook.ads.internal.w.h.a.b(this.a, "api", com.facebook.ads.internal.w.h.b.n, new Exception("Ad object is null"));
        }
        else {
            switch (message.what) {
                case 1015: {
                    this.b.a("Received load confirmation.");
                    break;
                }
                case 1016: {
                    this.b.a("Received show confirmation.");
                    break;
                }
                case 1017: {
                    this.b.a("Received destroy confirmation.");
                    break;
                }
                case 1020: {
                    this.d.a(com.facebook.ads.internal.c.a.a.c);
                    final Bundle bundle = message.getData().getBundle("BUNDLE_EXTRAS_KEY");
                    if (bundle != null) {
                        this.e.g = bundle.getLong("LONG_INVALIDATION_TIME_KEY");
                    }
                    else {
                        com.facebook.ads.internal.w.h.a.b(this.a, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing bundle for message: " + message));
                    }
                    this.e.a(null);
                    break;
                }
                case 1022: {
                    this.d.a(com.facebook.ads.internal.c.a.a.e);
                    if (this.b.b) {
                        this.h();
                        break;
                    }
                    break;
                }
                case 10:
                case 1023: {
                    this.d.a(com.facebook.ads.internal.c.a.a.g);
                    if (this.b.b) {
                        this.h();
                    }
                    final Bundle bundle2 = message.getData().getBundle("BUNDLE_EXTRAS_KEY");
                    if (bundle2 != null) {
                        final int int1 = bundle2.getInt("INT_ERROR_CODE_KEY");
                        final String string = bundle2.getString("STR_ERROR_MESSAGE_KEY");
                        if (this.e.c != null) {
                            this.e.c.onError(a, new AdError(int1, string));
                        }
                        else {
                            Log.e("FBAudienceNetwork", string);
                        }
                    }
                    else {
                        com.facebook.ads.internal.w.h.a.b(this.a, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing bundle for message: " + message));
                    }
                    this.e.a(null);
                    return;
                }
            }
            if (this.e.c != null) {
                switch (message.what) {
                    default: {}
                    case 1020: {
                        this.e.c.onAdLoaded(a);
                    }
                    case 1021: {
                        this.e.c.onInterstitialDisplayed(a);
                    }
                    case 1022: {
                        this.e.c.onInterstitialDismissed(a);
                    }
                    case 1024: {
                        this.e.c.onAdClicked(a);
                    }
                    case 1025: {
                        this.e.c.onLoggingImpression(a);
                    }
                    case 1026: {
                        if (this.e.c instanceof InterstitialAdExtendedListener) {
                            ((InterstitialAdExtendedListener)this.e.c).onInterstitialActivityDestroyed();
                            return;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    public void a(final InterstitialAd interstitialAd, final EnumSet<CacheFlag> e, final String f) {
        final com.facebook.ads.internal.protocol.a a = e.a(this.a, 0, 1);
        if (a != null) {
            this.a(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, a.b());
        }
        else if (!this.d.a(com.facebook.ads.internal.c.a.a.b, "load()")) {
            this.e.a(interstitialAd);
            if (this.f != null) {
                this.f.a(e, f);
                return;
            }
            this.e.e = e;
            this.e.f = f;
            if (!this.a(this.e.a)) {
                this.c();
                return;
            }
            if (this.b.b) {
                this.b();
                return;
            }
            this.b.c = true;
            this.b.a();
        }
    }
    
    public boolean a(final InterstitialAd interstitialAd) {
        if (this.d.a(com.facebook.ads.internal.c.a.a.d, "show()")) {
            return false;
        }
        this.e.a(interstitialAd);
        if (this.b.b) {
            this.a(1011, null);
            return true;
        }
        if (this.f != null) {
            return this.f.e();
        }
        (this.f = new d(this.e, this, this.c)).e();
        return false;
    }
    
    @Override
    public void c() {
        (this.f = new d(this.e, this, this.c)).a(this.e.e, this.e.f);
    }
    
    @Override
    public void d() {
        if (this.b.b) {
            this.h();
        }
        if (this.f != null) {
            this.f.a();
        }
        this.d.a(com.facebook.ads.internal.c.a.a.f);
    }
    
    public boolean f() {
        if (this.f != null) {
            return this.f.d();
        }
        return this.d.a == com.facebook.ads.internal.c.a.a.c;
    }
    
    public boolean g() {
        if (this.f != null) {
            return this.f.c();
        }
        return this.e.g > 0L && v.a() > this.e.g;
    }
}
