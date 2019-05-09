package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;

final /* synthetic */ class zzzd {
    private static final /* synthetic */ int[] zzbvf = new int[Gender.values().length];
    static final /* synthetic */ int[] zzbvg = new int[ErrorCode.values().length];

    static {
        try {
            zzbvg[ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzbvg[ErrorCode.INVALID_REQUEST.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzbvg[ErrorCode.NETWORK_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzbvg[ErrorCode.NO_FILL.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzbvf[Gender.FEMALE.ordinal()] = 1;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zzbvf[Gender.MALE.ordinal()] = 2;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zzbvf[Gender.UNKNOWN.ordinal()] = 3;
        } catch (NoSuchFieldError e7) {
        }
    }
}
