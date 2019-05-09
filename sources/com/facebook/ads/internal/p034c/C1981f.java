package com.facebook.ads.internal.p034c;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.PointerIconCompat;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p033b.C1954e;
import com.facebook.ads.internal.p034c.C1969a.C1964a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.EnumSet;

@UiThread
/* renamed from: com.facebook.ads.internal.c.f */
public class C1981f extends C1971b {
    /* renamed from: e */
    private final C1982g f4342e;
    @Nullable
    /* renamed from: f */
    private C1977d f4343f;

    public C1981f(C1982g c1982g) {
        super(c1982g.f4344a.getApplicationContext());
        this.f4342e = c1982g;
    }

    /* renamed from: h */
    private void m4718h() {
        m4667a(PointerIconCompat.TYPE_NO_DROP, null);
        this.b.m4734b();
        this.f4342e.m4728a(null);
    }

    /* renamed from: a */
    Message mo5455a() {
        Message obtain = Message.obtain(null, PointerIconCompat.TYPE_ALIAS);
        obtain.getData().putString("STR_PLACEMENT_KEY", this.f4342e.f4345b);
        obtain.getData().putString("STR_AD_ID_KEY", this.c);
        obtain.getData().putString("STR_BID_PAYLOAD_KEY", this.f4342e.f4349f);
        obtain.getData().putString("STR_EXTRA_HINTS_KEY", this.f4342e.f4347d);
        obtain.getData().putSerializable("SRL_INT_CACHE_FLAGS_KEY", this.f4342e.f4348e);
        obtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.f4776a);
        return obtain;
    }

    /* renamed from: a */
    public void mo5456a(Message message) {
        Ad a = this.f4342e.m4727a();
        if (a == null) {
            C2625a.m6741b(this.a, "api", C2626b.f6549n, new Exception("Ad object is null"));
            return;
        }
        Bundle bundle;
        switch (message.what) {
            case 10:
            case 1023:
                this.d.m4664a(C1964a.ERROR);
                if (this.b.f4355b) {
                    m4718h();
                }
                bundle = message.getData().getBundle("BUNDLE_EXTRAS_KEY");
                if (bundle != null) {
                    int i = bundle.getInt("INT_ERROR_CODE_KEY");
                    String string = bundle.getString("STR_ERROR_MESSAGE_KEY");
                    if (this.f4342e.f4346c != null) {
                        this.f4342e.f4346c.onError(a, new AdError(i, string));
                    } else {
                        Log.e(AudienceNetworkAds.TAG, string);
                    }
                } else {
                    C2625a.m6741b(this.a, "api", C2626b.f6548m, new Exception("Missing bundle for message: " + message));
                }
                this.f4342e.m4728a(null);
                return;
            case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /*1015*/:
                this.b.m4733a("Received load confirmation.");
                break;
            case PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW /*1016*/:
                this.b.m4733a("Received show confirmation.");
                break;
            case PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW /*1017*/:
                this.b.m4733a("Received destroy confirmation.");
                break;
            case PointerIconCompat.TYPE_GRAB /*1020*/:
                this.d.m4664a(C1964a.LOADED);
                bundle = message.getData().getBundle("BUNDLE_EXTRAS_KEY");
                if (bundle != null) {
                    this.f4342e.f4350g = bundle.getLong("LONG_INVALIDATION_TIME_KEY");
                } else {
                    C2625a.m6741b(this.a, "api", C2626b.f6548m, new Exception("Missing bundle for message: " + message));
                }
                this.f4342e.m4728a(null);
                break;
            case 1022:
                this.d.m4664a(C1964a.SHOWN);
                if (this.b.f4355b) {
                    m4718h();
                    break;
                }
                break;
        }
        if (this.f4342e.f4346c != null) {
            switch (message.what) {
                case PointerIconCompat.TYPE_GRAB /*1020*/:
                    this.f4342e.f4346c.onAdLoaded(a);
                    return;
                case PointerIconCompat.TYPE_GRABBING /*1021*/:
                    this.f4342e.f4346c.onInterstitialDisplayed(a);
                    return;
                case 1022:
                    this.f4342e.f4346c.onInterstitialDismissed(a);
                    return;
                case 1024:
                    this.f4342e.f4346c.onAdClicked(a);
                    return;
                case InputDeviceCompat.SOURCE_GAMEPAD /*1025*/:
                    this.f4342e.f4346c.onLoggingImpression(a);
                    return;
                case 1026:
                    if (this.f4342e.f4346c instanceof InterstitialAdExtendedListener) {
                        ((InterstitialAdExtendedListener) this.f4342e.f4346c).onInterstitialActivityDestroyed();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m4721a(InterstitialAd interstitialAd, EnumSet<CacheFlag> enumSet, String str) {
        C2065a a = C1954e.m4637a(this.a, Integer.valueOf(0), Integer.valueOf(1));
        if (a != null) {
            m4668a(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, a.m5039b());
        } else if (!this.d.m4665a(C1964a.LOADING, "load()")) {
            this.f4342e.m4728a(interstitialAd);
            if (this.f4343f != null) {
                this.f4343f.m4692a((EnumSet) enumSet, str);
                return;
            }
            this.f4342e.f4348e = enumSet;
            this.f4342e.f4349f = str;
            if (!m4671a(this.f4342e.f4344a)) {
                mo5457c();
            } else if (this.b.f4355b) {
                m4672b();
            } else {
                this.b.f4356c = true;
                this.b.m4731a();
            }
        }
    }

    /* renamed from: a */
    public boolean m4722a(InterstitialAd interstitialAd) {
        if (this.d.m4665a(C1964a.SHOWING, "show()")) {
            return false;
        }
        this.f4342e.m4728a(interstitialAd);
        if (this.b.f4355b) {
            m4667a(PointerIconCompat.TYPE_COPY, null);
            return true;
        } else if (this.f4343f != null) {
            return this.f4343f.m4696e();
        } else {
            this.f4343f = new C1977d(this.f4342e, this, this.c);
            this.f4343f.m4696e();
            return false;
        }
    }

    /* renamed from: c */
    public void mo5457c() {
        this.f4343f = new C1977d(this.f4342e, this, this.c);
        this.f4343f.m4692a(this.f4342e.f4348e, this.f4342e.f4349f);
    }

    /* renamed from: d */
    public void mo5458d() {
        if (this.b.f4355b) {
            m4718h();
        }
        if (this.f4343f != null) {
            this.f4343f.mo5449a();
        }
        this.d.m4664a(C1964a.DESTROYED);
    }

    /* renamed from: f */
    public boolean m4725f() {
        return this.f4343f != null ? this.f4343f.m4695d() : this.d.f4315a == C1964a.LOADED;
    }

    /* renamed from: g */
    public boolean m4726g() {
        return this.f4343f != null ? this.f4343f.m4694c() : this.f4342e.f4350g > 0 && C2597v.m6666a() > this.f4342e.f4350g;
    }
}
