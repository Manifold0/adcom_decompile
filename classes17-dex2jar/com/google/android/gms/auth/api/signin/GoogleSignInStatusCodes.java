// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.CommonStatusCodes;

public final class GoogleSignInStatusCodes extends CommonStatusCodes
{
    public static final int SIGN_IN_CANCELLED = 12501;
    public static final int SIGN_IN_CURRENTLY_IN_PROGRESS = 12502;
    public static final int SIGN_IN_FAILED = 12500;
    
    private GoogleSignInStatusCodes() {
    }
    
    public static String getStatusCodeString(final int n) {
        switch (n) {
            default: {
                return CommonStatusCodes.getStatusCodeString(n);
            }
            case 12500: {
                return "A non-recoverable sign in failure occurred";
            }
            case 12501: {
                return "Sign in action cancelled";
            }
            case 12502: {
                return "Sign-in in progress";
            }
        }
    }
}
