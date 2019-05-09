package com.applovin.impl.sdk;

import com.applovin.impl.p016a.C1234g;
import com.applovin.impl.p016a.C1235h;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONObject;

final class fn extends fl {
    /* renamed from: a */
    private final JSONObject f2525a;

    fn(C1234g c1234g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(c1234g, appLovinAdLoadListener, appLovinSdkImpl);
        if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified.");
        }
        this.f2525a = c1234g.m1882c();
    }

    public void run() {
        this.e.mo4172d(this.c, "Processing SDK JSON response...");
        String a = bu.m2389a(this.f2525a, "xml", null, this.d);
        if (!AppLovinSdkUtils.isValidString(a)) {
            this.e.mo4173e(this.c, "No VAST response received.");
            m2871a(C1235h.NO_WRAPPER_RESPONSE);
        } else if (a.length() < ((Integer) this.d.get(ea.dt)).intValue()) {
            try {
                m2872a(gg.m2980a(a, this.d));
            } catch (Throwable th) {
                this.e.mo4174e(this.c, "Unable to parse VAST response", th);
                m2871a(C1235h.XML_PARSING);
            }
        } else {
            this.e.mo4173e(this.c, "VAST response is over max length");
            m2871a(C1235h.XML_PARSING);
        }
    }
}
