package com.applovin.mediation;

import com.applovin.sdk.AppLovinErrorCodes;

public enum AppLovinMediationErrorCode {
    ADAPTER_CONFIGURATION_ERROR(-5100),
    ADAPTER_NOT_FOUND(AppLovinErrorCodes.MEDIATION_ADAPTER_TIMEOUT),
    NETWORK_UNSPECIFIED(-5200),
    NETWORK_INVALID_STATE(-5201),
    NETWORK_INVALID_CONFIGURATION(-5202),
    NETWORK_BAD_REQUEST(-5203),
    NETWORK_TIMEOUT(AppLovinErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED_ON_RENDER),
    NETWORK_NO_CONNECTION(AppLovinErrorCodes.MEDIATION_ADAPTER_RENDER_NOT_READY_AD),
    NETWORK_NO_FILL(-5204),
    NETWORK_AD_NOT_READY(-5205),
    NETWORK_SERVER_ERROR(-5206),
    NETWORK_INTERNAL_ERROR(-5206),
    NETWORK_DISPLAY_UNSPECIFIED(-5300),
    NETWORK_DISPLAY_INVALID_STATE(-5301),
    INTERNAL_ERROR(-5500),
    INTERNAL_AD_TYPE_NOT_SUPPORTED(-5501),
    INTERNAL_AD_SIZE_NOT_SUPPORTED(-5502);
    
    /* renamed from: a */
    private final int f2649a;

    private AppLovinMediationErrorCode(int i) {
        this.f2649a = i;
    }

    public int getErrorCode() {
        return this.f2649a;
    }
}
