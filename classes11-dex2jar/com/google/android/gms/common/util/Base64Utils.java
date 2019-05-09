// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Base64Utils
{
    @KeepForSdk
    public static byte[] decode(final String s) {
        if (s == null) {
            return null;
        }
        return Base64.decode(s, 0);
    }
    
    @KeepForSdk
    public static byte[] decodeUrlSafe(final String s) {
        if (s == null) {
            return null;
        }
        return Base64.decode(s, 10);
    }
    
    @KeepForSdk
    public static byte[] decodeUrlSafeNoPadding(final String s) {
        if (s == null) {
            return null;
        }
        return Base64.decode(s, 11);
    }
    
    @KeepForSdk
    public static String encode(final byte[] array) {
        if (array == null) {
            return null;
        }
        return Base64.encodeToString(array, 0);
    }
    
    @KeepForSdk
    public static String encodeUrlSafe(final byte[] array) {
        if (array == null) {
            return null;
        }
        return Base64.encodeToString(array, 10);
    }
    
    @KeepForSdk
    public static String encodeUrlSafeNoPadding(final byte[] array) {
        if (array == null) {
            return null;
        }
        return Base64.encodeToString(array, 11);
    }
}
