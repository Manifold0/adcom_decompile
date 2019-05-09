// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Random;

@zzadh
public final class zzkc extends zzlh
{
    private Object mLock;
    private final Random zzasg;
    private long zzash;
    
    public zzkc() {
        this.mLock = new Object();
        this.zzasg = new Random();
        this.zzil();
    }
    
    public final long getValue() {
        return this.zzash;
    }
    
    public final void zzil() {
        final Object mLock = this.mLock;
        // monitorenter(mLock)
        int n = 3;
        long zzash = 0L;
        while (true) {
            final int n2 = n - 1;
            Label_0065: {
                if (n2 <= 0) {
                    break Label_0065;
                }
                try {
                    final long n3 = zzash = this.zzasg.nextInt() + 2147483648L;
                    n = n2;
                    if (n3 == this.zzash) {
                        continue;
                    }
                    zzash = n3;
                    n = n2;
                    if (n3 != 0L) {
                        zzash = n3;
                        this.zzash = zzash;
                        return;
                    }
                    continue;
                }
                finally {
                }
                // monitorexit(mLock)
            }
        }
    }
}
