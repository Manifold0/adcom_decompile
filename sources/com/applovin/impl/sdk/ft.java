package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinErrorCodes;
import com.tapjoy.TJAdUnitConstants;

class ft implements af<T> {
    /* renamed from: a */
    final /* synthetic */ String f2534a;
    /* renamed from: b */
    final /* synthetic */ AppLovinSdkImpl f2535b;
    /* renamed from: c */
    final /* synthetic */ fs f2536c;

    ft(fs fsVar, String str, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2536c = fsVar;
        this.f2534a = str;
        this.f2535b = appLovinSdkImpl;
    }

    /* renamed from: a */
    public void mo4128a(int i) {
        Object obj = 1;
        Object obj2 = (i < 200 || i >= TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL) ? 1 : null;
        if (i == AppLovinErrorCodes.NO_NETWORK) {
            obj = null;
        }
        if (obj2 == null || r0 == null) {
            this.f2536c.mo4128a(i);
        } else if (this.f2536c.f2280o > 0) {
            this.f2536c.e.mo4178w(this.f2534a, "Unable to send request due to server failure (code " + i + "). " + this.f2536c.f2280o + " attempts left, retrying in " + (((double) this.f2536c.f2281p) / 1000.0d) + " seconds...");
            this.f2536c.f2280o = this.f2536c.f2280o - 1;
            if (this.f2536c.f2280o == 0) {
                this.f2536c.m2489c(this.f2536c.f2282q);
                if (!TextUtils.isEmpty(this.f2536c.f2276k) && this.f2536c.f2276k.length() >= 4) {
                    this.f2536c.f2275j = this.f2536c.f2276k;
                    this.f2536c.e.mo4175i(this.f2536c.m2482a(), "Switching to backup endpoint " + this.f2536c.f2276k);
                }
            }
            this.f2535b.getTaskManager().m2856a(this.f2536c, fe.BACKGROUND, this.f2536c.f2281p);
        } else {
            if (this.f2536c.f2276k == null || !this.f2536c.f2276k.equals(this.f2536c.f2275j)) {
                this.f2536c.m2489c(this.f2536c.f2282q);
            } else {
                this.f2536c.m2489c(this.f2536c.f2283r);
            }
            this.f2536c.mo4128a(i);
        }
    }

    /* renamed from: a */
    public void mo4129a(T t, int i) {
        this.f2536c.f2280o = 0;
        this.f2536c.mo4129a((Object) t, i);
    }
}
