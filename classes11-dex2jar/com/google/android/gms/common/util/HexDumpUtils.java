// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class HexDumpUtils
{
    @KeepForSdk
    public static String dump(final byte[] array, int n, final int n2, final boolean b) {
        if (array == null || array.length == 0 || n < 0 || n2 <= 0 || n + n2 > array.length) {
            return null;
        }
        int n3 = 57;
        if (b) {
            n3 = 75;
        }
        final StringBuilder sb = new StringBuilder(n3 * ((n2 + 16 - 1) / 16));
        int i = n2;
        int n4 = 0;
        int n5 = 0;
        while (i > 0) {
            int n6;
            if (n5 == 0) {
                if (n2 < 65536) {
                    sb.append(String.format("%04X:", n));
                    n6 = n;
                }
                else {
                    sb.append(String.format("%08X:", n));
                    n6 = n;
                }
            }
            else {
                n6 = n4;
                if (n5 == 8) {
                    sb.append(" -");
                    n6 = n4;
                }
            }
            sb.append(String.format(" %02X", array[n] & 0xFF));
            --i;
            final int n7 = n5 + 1;
            if (b && (n7 == 16 || i == 0)) {
                final int n8 = 16 - n7;
                if (n8 > 0) {
                    for (int j = 0; j < n8; ++j) {
                        sb.append("   ");
                    }
                }
                if (n8 >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (int k = 0; k < n7; ++k) {
                    char c = (char)array[n6 + k];
                    if (c < ' ' || c > '~') {
                        c = '.';
                    }
                    sb.append(c);
                }
            }
            if (n7 == 16 || i == 0) {
                sb.append('\n');
                n5 = 0;
            }
            else {
                n5 = n7;
            }
            ++n;
            n4 = n6;
        }
        return sb.toString();
    }
}
