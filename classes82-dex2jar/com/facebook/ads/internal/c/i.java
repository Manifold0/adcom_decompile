// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.c.a.c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.RewardData;
import com.facebook.ads.S2SRewardedVideoAdListener;
import com.facebook.ads.RewardedVideoAdExtendedListener;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.w.h.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.Serializable;
import android.os.Handler;
import android.os.Message;
import com.facebook.ads.RewardedVideoAd;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

@UiThread
public class i extends b
{
    private final j e;
    @Nullable
    private e f;
    
    public i(final j e) {
        super(e.a);
        this.e = e;
    }
    
    private void h() {
        this.a(2002, null);
        this.b.b();
        this.e.a(null);
    }
    
    @Override
    Message a() {
        final Message obtain = Message.obtain((Handler)null, 2000);
        obtain.getData().putString("STR_PLACEMENT_KEY", this.e.b);
        obtain.getData().putString("STR_AD_ID_KEY", this.c);
        obtain.getData().putString("STR_BID_PAYLOAD_KEY", this.e.f);
        obtain.getData().putString("STR_EXTRA_HINTS_KEY", this.e.d);
        obtain.getData().putBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY", this.e.g);
        obtain.getData().putSerializable("SRL_RV_REWARD_DATA_KEY", (Serializable)this.e.e);
        obtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.a);
        return obtain;
    }
    
    @Override
    public void a(final Message message) {
        final RewardedVideoAd a = this.e.a();
        if (a == null) {
            com.facebook.ads.internal.w.h.a.b(this.a, "api", com.facebook.ads.internal.w.h.b.n, new Exception("Ad object is null"));
        }
        else {
            switch (message.what) {
                case 2010: {
                    this.b.a("Received load confirmation.");
                    break;
                }
                case 2011: {
                    this.b.a("Received show confirmation.");
                    break;
                }
                case 2100: {
                    this.d.a(com.facebook.ads.internal.c.a.a.c);
                    final Bundle bundle = message.getData().getBundle("BUNDLE_EXTRAS_KEY");
                    if (bundle != null) {
                        this.e.i = bundle.getLong("LONG_INVALIDATION_TIME_KEY");
                        this.e.h = bundle.getInt("INT_RV_VIDEO_DURATION_KEY");
                    }
                    else {
                        com.facebook.ads.internal.w.h.a.b(this.a, "api", com.facebook.ads.internal.w.h.b.m, new Exception("Missing bundle for message: " + message));
                    }
                    this.e.a(null);
                    break;
                }
                case 2106: {
                    this.d.a(com.facebook.ads.internal.c.a.a.e);
                    if (this.b.b) {
                        this.h();
                    }
                    this.e.a(null);
                    break;
                }
                case 10:
                case 2103: {
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
                    case 2100: {
                        this.e.c.onAdLoaded(a);
                    }
                    case 2104: {
                        this.e.c.onAdClicked(a);
                    }
                    case 2106: {
                        if (this.e.c instanceof RewardedVideoAdExtendedListener) {
                            ((RewardedVideoAdExtendedListener)this.e.c).onRewardedVideoActivityDestroyed();
                            return;
                        }
                        break;
                    }
                    case 2108: {
                        if (this.e.c instanceof S2SRewardedVideoAdListener) {
                            ((S2SRewardedVideoAdListener)this.e.c).onRewardServerSuccess();
                            return;
                        }
                        break;
                    }
                    case 2109: {
                        if (this.e.c instanceof S2SRewardedVideoAdListener) {
                            ((S2SRewardedVideoAdListener)this.e.c).onRewardServerFailed();
                            return;
                        }
                        break;
                    }
                    case 2105: {
                        this.e.c.onLoggingImpression(a);
                    }
                    case 2110: {
                        this.e.c.onRewardedVideoClosed();
                    }
                    case 2107: {
                        this.e.c.onRewardedVideoCompleted();
                    }
                }
            }
        }
    }
    
    public void a(final RewardData e) {
        this.e.e = e;
        if (this.b.b) {
            final Bundle bundle = new Bundle();
            bundle.putSerializable("SRL_RV_REWARD_DATA_KEY", (Serializable)e);
            this.a(2003, bundle);
        }
        else if (this.f != null) {
            this.f.a(e);
        }
    }
    
    public void a(final RewardedVideoAd rewardedVideoAd, final String f, final boolean g) {
        final com.facebook.ads.internal.protocol.a a = com.facebook.ads.internal.b.e.a(this.a, 0, 1);
        if (a != null) {
            this.a(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, a.b());
        }
        else if (!this.d.a(com.facebook.ads.internal.c.a.a.b, "load()")) {
            this.e.a(rewardedVideoAd);
            if (this.f != null) {
                this.f.a(f, g);
                return;
            }
            this.e.f = f;
            this.e.g = g;
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
    
    public boolean a(final RewardedVideoAd rewardedVideoAd, final int n) {
        if (this.d.a(com.facebook.ads.internal.c.a.a.d, "show()")) {
            return false;
        }
        this.e.a(rewardedVideoAd);
        if (this.b.b) {
            final Bundle bundle = new Bundle();
            bundle.putInt("INT_RV_APP_ORIENTATION_KEY", n);
            this.a(2001, bundle);
            return true;
        }
        if (this.f != null) {
            return this.f.a(n);
        }
        (this.f = new e(this.e, this, this.c)).a(n);
        return false;
    }
    
    @Override
    public void c() {
        (this.f = new e(this.e, this, this.c)).a(this.e.f, this.e.g);
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
        return this.e.i > 0L && v.a() > this.e.i;
    }
}
