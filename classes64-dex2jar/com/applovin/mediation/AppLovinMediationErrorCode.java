// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.mediation;

public enum AppLovinMediationErrorCode
{
    ADAPTER_CONFIGURATION_ERROR(-5100), 
    ADAPTER_NOT_FOUND(-5101), 
    INTERNAL_AD_SIZE_NOT_SUPPORTED(-5502), 
    INTERNAL_AD_TYPE_NOT_SUPPORTED(-5501), 
    INTERNAL_ERROR(-5500), 
    NETWORK_AD_NOT_READY(-5205), 
    NETWORK_BAD_REQUEST(-5203), 
    NETWORK_DISPLAY_INVALID_STATE(-5301), 
    NETWORK_DISPLAY_UNSPECIFIED(-5300), 
    NETWORK_INTERNAL_ERROR(-5206), 
    NETWORK_INVALID_CONFIGURATION(-5202), 
    NETWORK_INVALID_STATE(-5201), 
    NETWORK_NO_CONNECTION(-5003), 
    NETWORK_NO_FILL(-5204), 
    NETWORK_SERVER_ERROR(-5206), 
    NETWORK_TIMEOUT(-5002), 
    NETWORK_UNSPECIFIED(-5200);
    
    private final int a;
    
    private AppLovinMediationErrorCode(final int a) {
        this.a = a;
    }
    
    public int getErrorCode() {
        return this.a;
    }
}
