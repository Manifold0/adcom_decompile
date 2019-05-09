package com.unity3d.services.analytics;

public enum AcquisitionType {
    SOFT,
    PREMIUM;

    public String toString() {
        switch (this) {
            case SOFT:
                return "soft";
            case PREMIUM:
                return "premium";
            default:
                return "";
        }
    }
}
