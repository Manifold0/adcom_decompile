// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.util;

import android.os.Bundle;

public class Utils
{
    public static String byteToHex(final byte b) {
        final char[] array2;
        final char[] array = array2 = new char[16];
        array2[0] = '0';
        array2[1] = '1';
        array2[2] = '2';
        array2[3] = '3';
        array2[4] = '4';
        array2[5] = '5';
        array2[6] = '6';
        array2[7] = '7';
        array2[8] = '8';
        array2[9] = '9';
        array2[10] = 'a';
        array2[11] = 'b';
        array2[12] = 'c';
        array2[13] = 'd';
        array2[14] = 'e';
        array2[15] = 'f';
        return new String(new char[] { array[b >> 4 & 0xF], array[b & 0xF] });
    }
    
    public static String convert(final String s) {
        try {
            final byte[] bytes = s.getBytes();
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; ++i) {
                if ((bytes[i] & 0x80) > 0) {
                    sb.append("%" + byteToHex(bytes[i]));
                }
                else {
                    sb.append((char)bytes[i]);
                }
            }
            return new String(sb.toString().getBytes(), "ISO-8859-1");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getData(final String s, final Bundle bundle) {
        return bundle.getString(s);
    }
}
