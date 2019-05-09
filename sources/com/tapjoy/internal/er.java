package com.tapjoy.internal;

import android.util.Base64;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import java.util.zip.CRC32;

public final class er {
    /* renamed from: a */
    public final C2909a f7686a;
    /* renamed from: b */
    public final String f7687b;
    /* renamed from: c */
    public final String f7688c;
    /* renamed from: d */
    public final String f7689d;
    /* renamed from: e */
    private final String f7690e;
    /* renamed from: f */
    private final int f7691f;

    /* renamed from: com.tapjoy.internal.er$a */
    public enum C2909a {
        SDK_ANDROID((byte) 2),
        RPC_ANALYTICS((byte) 49);
        
        /* renamed from: a */
        public byte f7685a;

        private C2909a(byte b) {
            this.f7685a = b;
        }

        /* renamed from: a */
        public static C2909a m7672a(byte b) {
            for (C2909a c2909a : C2909a.values()) {
                if (c2909a.f7685a == b) {
                    return c2909a;
                }
            }
            return null;
        }
    }

    public er(String str) {
        int length = str.length();
        if (str.matches("[A-Za-z0-9\\-_]*") && length >= 60 && (length & 3) == 0) {
            try {
                Object decode = Base64.decode(str, 8);
                int length2 = decode.length;
                ByteBuffer wrap = ByteBuffer.wrap(decode);
                wrap.order(ByteOrder.BIG_ENDIAN);
                int length3 = decode.length - 4;
                int i = wrap.getInt(length3);
                CRC32 crc32 = new CRC32();
                crc32.update(decode, 0, length3);
                if (i != ((int) crc32.getValue())) {
                    throw new IllegalArgumentException("The given API key was invalid.");
                }
                this.f7690e = str;
                this.f7687b = new UUID(wrap.getLong(0), wrap.getLong(8)).toString();
                this.f7691f = wrap.get(16);
                this.f7686a = C2909a.m7672a(wrap.get(17));
                this.f7688c = str.substring(24, 44);
                if (this.f7691f == 1) {
                    this.f7689d = null;
                    return;
                } else if (this.f7691f != 2 || this.f7686a != C2909a.SDK_ANDROID) {
                    throw new IllegalArgumentException("The given API key was not supported.");
                } else if (length2 < 57) {
                    throw new IllegalArgumentException("The given API key was invalid.");
                } else {
                    Object obj = new byte[12];
                    System.arraycopy(decode, 33, obj, 0, 12);
                    this.f7689d = new String(ii.m8189a(obj));
                    return;
                }
            } catch (Throwable e) {
                throw new IllegalArgumentException("The given API key was malformed.", e);
            }
        }
        throw new IllegalArgumentException("The given API key was malformed.");
    }

    public final boolean equals(Object o) {
        if (o instanceof er) {
            return this.f7690e.equals(((er) o).f7690e);
        }
        return false;
    }

    public final String toString() {
        return this.f7690e;
    }
}
