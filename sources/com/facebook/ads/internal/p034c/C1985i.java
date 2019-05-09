package com.facebook.ads.internal.p034c;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdExtendedListener;
import com.facebook.ads.S2SRewardedVideoAdListener;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p033b.C1954e;
import com.facebook.ads.internal.p034c.C1969a.C1964a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.settings.AdInternalSettings;

@UiThread
/* renamed from: com.facebook.ads.internal.c.i */
public class C1985i extends C1971b {
    /* renamed from: e */
    private final C1986j f4361e;
    @Nullable
    /* renamed from: f */
    private C1980e f4362f;

    public C1985i(C1986j c1986j) {
        super(c1986j.f4363a);
        this.f4361e = c1986j;
    }

    /* renamed from: h */
    private void m4735h() {
        m4667a(2002, null);
        this.b.m4734b();
        this.f4361e.m4746a(null);
    }

    /* renamed from: a */
    Message mo5455a() {
        Message obtain = Message.obtain(null, 2000);
        obtain.getData().putString("STR_PLACEMENT_KEY", this.f4361e.f4364b);
        obtain.getData().putString("STR_AD_ID_KEY", this.c);
        obtain.getData().putString("STR_BID_PAYLOAD_KEY", this.f4361e.f4368f);
        obtain.getData().putString("STR_EXTRA_HINTS_KEY", this.f4361e.f4366d);
        obtain.getData().putBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY", this.f4361e.f4369g);
        obtain.getData().putSerializable("SRL_RV_REWARD_DATA_KEY", this.f4361e.f4367e);
        obtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.f4776a);
        return obtain;
    }

    /* renamed from: a */
    public void mo5456a(Message message) {
        Ad a = this.f4361e.m4745a();
        if (a == null) {
            C2625a.m6741b(this.a, "api", C2626b.f6549n, new Exception("Ad object is null"));
            return;
        }
        Bundle bundle;
        switch (message.what) {
            case 10:
            case 2103:
                this.d.m4664a(C1964a.ERROR);
                if (this.b.f4355b) {
                    m4735h();
                }
                bundle = message.getData().getBundle("BUNDLE_EXTRAS_KEY");
                if (bundle != null) {
                    int i = bundle.getInt("INT_ERROR_CODE_KEY");
                    String string = bundle.getString("STR_ERROR_MESSAGE_KEY");
                    if (this.f4361e.f4365c != null) {
                        this.f4361e.f4365c.onError(a, new AdError(i, string));
                    } else {
                        Log.e(AudienceNetworkAds.TAG, string);
                    }
                } else {
                    C2625a.m6741b(this.a, "api", C2626b.f6548m, new Exception("Missing bundle for message: " + message));
                }
                this.f4361e.m4746a(null);
                return;
            case 2010:
                this.b.m4733a("Received load confirmation.");
                break;
            case 2011:
                this.b.m4733a("Received show confirmation.");
                break;
            case AdError.BROKEN_MEDIA_ERROR_CODE /*2100*/:
                this.d.m4664a(C1964a.LOADED);
                bundle = message.getData().getBundle("BUNDLE_EXTRAS_KEY");
                if (bundle != null) {
                    this.f4361e.f4371i = bundle.getLong("LONG_INVALIDATION_TIME_KEY");
                    this.f4361e.f4370h = bundle.getInt("INT_RV_VIDEO_DURATION_KEY");
                } else {
                    C2625a.m6741b(this.a, "api", C2626b.f6548m, new Exception("Missing bundle for message: " + message));
                }
                this.f4361e.m4746a(null);
                break;
            case 2106:
                this.d.m4664a(C1964a.SHOWN);
                if (this.b.f4355b) {
                    m4735h();
                }
                this.f4361e.m4746a(null);
                break;
        }
        if (this.f4361e.f4365c != null) {
            switch (message.what) {
                case AdError.BROKEN_MEDIA_ERROR_CODE /*2100*/:
                    this.f4361e.f4365c.onAdLoaded(a);
                    return;
                case 2104:
                    this.f4361e.f4365c.onAdClicked(a);
                    return;
                case 2105:
                    this.f4361e.f4365c.onLoggingImpression(a);
                    return;
                case 2106:
                    if (this.f4361e.f4365c instanceof RewardedVideoAdExtendedListener) {
                        ((RewardedVideoAdExtendedListener) this.f4361e.f4365c).onRewardedVideoActivityDestroyed();
                        return;
                    }
                    return;
                case 2107:
                    this.f4361e.f4365c.onRewardedVideoCompleted();
                    return;
                case 2108:
                    if (this.f4361e.f4365c instanceof S2SRewardedVideoAdListener) {
                        ((S2SRewardedVideoAdListener) this.f4361e.f4365c).onRewardServerSuccess();
                        return;
                    }
                    return;
                case 2109:
                    if (this.f4361e.f4365c instanceof S2SRewardedVideoAdListener) {
                        ((S2SRewardedVideoAdListener) this.f4361e.f4365c).onRewardServerFailed();
                        return;
                    }
                    return;
                case 2110:
                    this.f4361e.f4365c.onRewardedVideoClosed();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m4738a(RewardData rewardData) {
        this.f4361e.f4367e = rewardData;
        if (this.b.f4355b) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("SRL_RV_REWARD_DATA_KEY", rewardData);
            m4667a(AdError.INTERNAL_ERROR_2003, bundle);
        } else if (this.f4362f != null) {
            this.f4362f.m4712a(rewardData);
        }
    }

    /* renamed from: a */
    public void m4739a(RewardedVideoAd rewardedVideoAd, String str, boolean z) {
        C2065a a = C1954e.m4637a(this.a, Integer.valueOf(0), Integer.valueOf(1));
        if (a != null) {
            m4668a(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, a.m5039b());
        } else if (!this.d.m4665a(C1964a.LOADING, "load()")) {
            this.f4361e.m4746a(rewardedVideoAd);
            if (this.f4362f != null) {
                this.f4362f.m4713a(str, z);
                return;
            }
            this.f4361e.f4368f = str;
            this.f4361e.f4369g = z;
            if (!m4671a(this.f4361e.f4363a)) {
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
    public boolean m4740a(RewardedVideoAd rewardedVideoAd, int i) {
        if (this.d.m4665a(C1964a.SHOWING, "show()")) {
            return false;
        }
        this.f4361e.m4746a(rewardedVideoAd);
        if (this.b.f4355b) {
            Bundle bundle = new Bundle();
            bundle.putInt("INT_RV_APP_ORIENTATION_KEY", i);
            m4667a(2001, bundle);
            return true;
        } else if (this.f4362f != null) {
            return this.f4362f.m4714a(i);
        } else {
            this.f4362f = new C1980e(this.f4361e, this, this.c);
            this.f4362f.m4714a(i);
            return false;
        }
    }

    /* renamed from: c */
    public void mo5457c() {
        this.f4362f = new C1980e(this.f4361e, this, this.c);
        this.f4362f.m4713a(this.f4361e.f4368f, this.f4361e.f4369g);
    }

    /* renamed from: d */
    public void mo5458d() {
        if (this.b.f4355b) {
            m4735h();
        }
        if (this.f4362f != null) {
            this.f4362f.mo5449a();
        }
        this.d.m4664a(C1964a.DESTROYED);
    }

    /* renamed from: f */
    public boolean m4743f() {
        return this.f4362f != null ? this.f4362f.m4717d() : this.d.f4315a == C1964a.LOADED;
    }

    /* renamed from: g */
    public boolean m4744g() {
        return this.f4362f != null ? this.f4362f.m4716c() : this.f4361e.f4371i > 0 && C2597v.m6666a() > this.f4361e.f4371i;
    }
}
