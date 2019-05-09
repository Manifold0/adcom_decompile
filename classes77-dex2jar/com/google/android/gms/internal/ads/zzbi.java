// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.util.Base64;

public final class zzbi
{
    public static String zza(final byte[] array, final boolean b) {
        int n;
        if (b) {
            n = 11;
        }
        else {
            n = 2;
        }
        return Base64.encodeToString(array, n);
    }
    
    public static byte[] zza(String o, final boolean b) throws IllegalArgumentException {
        int n;
        if (b) {
            n = 11;
        }
        else {
            n = 2;
        }
        final byte[] decode = Base64.decode((String)o, n);
        if (decode.length == 0 && ((String)o).length() > 0) {
            o = String.valueOf(o);
            if (((String)o).length() != 0) {
                o = "Unable to decode ".concat((String)o);
            }
            else {
                o = new String("Unable to decode ");
            }
            throw new IllegalArgumentException((String)o);
        }
        return decode;
    }
}
