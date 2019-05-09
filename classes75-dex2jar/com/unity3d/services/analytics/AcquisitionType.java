// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.analytics;

public enum AcquisitionType
{
    PREMIUM, 
    SOFT;
    
    @Override
    public String toString() {
        switch (this) {
            default: {
                return "";
            }
            case SOFT: {
                return "soft";
            }
            case PREMIUM: {
                return "premium";
            }
        }
    }
}
