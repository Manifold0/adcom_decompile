package com.applovin.impl.p016a;

import com.google.android.gms.nearby.messages.Strategy;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* renamed from: com.applovin.impl.a.h */
public enum C1235h {
    UNSPECIFIED(-1),
    XML_PARSING(100),
    GENERAL_WRAPPER_ERROR(Strategy.TTL_SECONDS_DEFAULT),
    TIMED_OUT(301),
    WRAPPER_LIMIT_REACHED(IronSourceConstants.OFFERWALL_AVAILABLE),
    NO_WRAPPER_RESPONSE(303),
    GENERAL_LINEAR_ERROR(400),
    NO_MEDIA_FILE_PROVIDED(401),
    MEDIA_FILE_TIMEOUT(402),
    MEDIA_FILE_ERROR(405),
    GENERAL_COMPANION_AD_ERROR(600),
    UNABLE_TO_FETCH_COMPANION_AD_RESOURCE(IronSourceError.ERROR_BN_LOAD_WHILE_LONG_INITIATION),
    CAN_NOT_FIND_COMPANION_AD_RESOURCE(IronSourceError.ERROR_BN_LOAD_PLACEMENT_CAPPED);
    
    /* renamed from: n */
    private final int f1634n;

    private C1235h(int i) {
        this.f1634n = i;
    }

    /* renamed from: a */
    public int m1888a() {
        return this.f1634n;
    }
}
