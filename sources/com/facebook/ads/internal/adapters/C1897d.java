package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;

/* renamed from: com.facebook.ads.internal.adapters.d */
public class C1897d {
    /* renamed from: a */
    private static AdAdapter f4039a;

    /* renamed from: a */
    public AdAdapter m4382a(AdPlacementType adPlacementType) {
        if (f4039a != null) {
            return f4039a;
        }
        switch (adPlacementType) {
            case BANNER:
                return new C1902e();
            case INTERSTITIAL:
                return new C1918g();
            case NATIVE:
                return new C1924i();
            case NATIVE_BANNER:
                return new C1925j();
            case INSTREAM:
                return new C1912f();
            case REWARDED_VIDEO:
                return new C1931k();
            default:
                return null;
        }
    }
}
