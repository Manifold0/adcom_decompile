// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwg
{
    private final Object mLock;
    @GuardedBy("mLock")
    private zzwn zzbrb;
    
    public zzwg() {
        this.mLock = new Object();
    }
    
    public final zzwn zzb(Context context, final zzang zzang) {
        while (true) {
            while (true) {
                Object o = null;
                Label_0068: {
                    synchronized (this.mLock) {
                        if (this.zzbrb == null) {
                            o = context.getApplicationContext();
                            if (o != null) {
                                break Label_0068;
                            }
                            o = zznk.zzaub;
                            this.zzbrb = new zzwn(context, zzang, (String)zzkb.zzik().zzd((zzna)o));
                        }
                        return this.zzbrb;
                    }
                }
                context = (Context)o;
                continue;
            }
        }
    }
}
