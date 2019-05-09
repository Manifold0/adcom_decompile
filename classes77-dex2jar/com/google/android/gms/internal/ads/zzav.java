// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;

public class zzav extends zzr<String>
{
    private final Object mLock;
    private zzz<String> zzck;
    
    public zzav(final int n, final String s, final zzz<String> zzck, final zzy zzy) {
        super(n, s, zzy);
        this.mLock = new Object();
        this.zzck = zzck;
    }
    
    @Override
    protected final zzx<String> zza(final zzp zzp) {
        try {
            final byte[] data = zzp.data;
            final String s = zzp.zzab.get("Content-Type");
        Label_0077:
            while (true) {
                if (s != null) {
                    final String[] split = s.split(";");
                    for (int i = 1; i < split.length; ++i) {
                        final String[] split2 = split[i].trim().split("=");
                        if (split2.length == 2 && split2[0].equals("charset")) {
                            final String s2 = split2[1];
                            break Label_0077;
                        }
                    }
                }
                Label_0104: {
                    break Label_0104;
                    final String s2;
                    final String s3 = new String(data, s2);
                    return zzx.zza(s3, com.google.android.gms.internal.ads.zzap.zzb(zzp));
                }
                final String s2 = "ISO-8859-1";
                continue Label_0077;
            }
        }
        catch (UnsupportedEncodingException ex) {
            final String s3 = new String(zzp.data);
            return zzx.zza(s3, com.google.android.gms.internal.ads.zzap.zzb(zzp));
        }
    }
    
    protected void zzh(final String s) {
        synchronized (this.mLock) {
            final zzz<String> zzck = this.zzck;
            // monitorexit(this.mLock)
            if (zzck != null) {
                zzck.zzb(s);
            }
        }
    }
}
