// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgz extends zzgq
{
    private MessageDigest zzaje;
    private final int zzajh;
    private final int zzaji;
    
    public zzgz(final int zzaji) {
        int zzajh = zzaji / 8;
        if (zzaji % 8 > 0) {
            ++zzajh;
        }
        this.zzajh = zzajh;
        this.zzaji = zzaji;
    }
    
    public final byte[] zzx(final String s) {
        while (true) {
            final int n = 0;
            while (true) {
                int i = 0;
                final byte[] array;
                long n3 = 0L;
                Label_0177: {
                    long n2 = 0L;
                    Label_0198: {
                        synchronized (this.mLock) {
                            this.zzaje = this.zzhg();
                            if (this.zzaje == null) {
                                // monitorexit(this.mLock)
                                return new byte[0];
                            }
                            this.zzaje.reset();
                            this.zzaje.update(s.getBytes(Charset.forName("UTF-8")));
                            final byte[] digest = this.zzaje.digest();
                            if (digest.length > this.zzajh) {
                                i = this.zzajh;
                            }
                            else {
                                i = digest.length;
                            }
                            array = new byte[i];
                            System.arraycopy(digest, 0, array, 0, array.length);
                            if (this.zzaji % 8 <= 0) {
                                return array;
                            }
                            n2 = 0L;
                            i = n;
                            if (i >= array.length) {
                                n2 >>>= 8 - this.zzaji % 8;
                                i = this.zzajh - 1;
                                break Label_0198;
                            }
                            n3 = n2;
                            if (i > 0) {
                                n3 = n2 << 8;
                            }
                        }
                        break Label_0177;
                    }
                    while (i >= 0) {
                        array[i] = (byte)(0xFFL & n2);
                        n2 >>>= 8;
                        --i;
                    }
                    return array;
                }
                long n2 = n3 + (array[i] & 0xFF);
                ++i;
                continue;
            }
        }
    }
}
