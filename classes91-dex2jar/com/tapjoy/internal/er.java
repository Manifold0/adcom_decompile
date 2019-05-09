// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.UUID;
import java.util.zip.CRC32;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import android.util.Base64;

public final class er
{
    public final a a;
    public final String b;
    public final String c;
    public final String d;
    private final String e;
    private final int f;
    
    public er(final String e) {
        final int length = e.length();
        if (!e.matches("[A-Za-z0-9\\-_]*") || length < 60 || (length & 0x3) != 0x0) {
            throw new IllegalArgumentException("The given API key was malformed.");
        }
        byte[] decode;
        int length2;
        ByteBuffer wrap;
        try {
            decode = Base64.decode(e, 8);
            length2 = decode.length;
            wrap = ByteBuffer.wrap(decode);
            wrap.order(ByteOrder.BIG_ENDIAN);
            final int n = decode.length - 4;
            final int int1 = wrap.getInt(n);
            final CRC32 crc32 = new CRC32();
            crc32.update(decode, 0, n);
            if (int1 != (int)crc32.getValue()) {
                throw new IllegalArgumentException("The given API key was invalid.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("The given API key was malformed.", ex);
        }
        this.e = e;
        this.b = new UUID(wrap.getLong(0), wrap.getLong(8)).toString();
        this.f = wrap.get(16);
        this.a = er.a.a(wrap.get(17));
        this.c = e.substring(24, 44);
        if (this.f == 1) {
            this.d = null;
            return;
        }
        if (this.f != 2 || this.a != er.a.SDK_ANDROID) {
            throw new IllegalArgumentException("The given API key was not supported.");
        }
        if (length2 < 57) {
            throw new IllegalArgumentException("The given API key was invalid.");
        }
        final byte[] array = new byte[12];
        System.arraycopy(decode, 33, array, 0, 12);
        this.d = new String(ii.a(array));
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o instanceof er && this.e.equals(((er)o).e);
    }
    
    @Override
    public final String toString() {
        return this.e;
    }
    
    public enum a
    {
        RPC_ANALYTICS("RPC_ANALYTICS", 1, (byte)49), 
        SDK_ANDROID("SDK_ANDROID", 0, (byte)2);
        
        public byte a;
        
        private a(final String s, final int n, final byte a) {
            this.a = a;
        }
        
        public static a a(final byte b) {
            final a[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final a a = values[i];
                if (a.a == b) {
                    return a;
                }
            }
            return null;
        }
    }
}
