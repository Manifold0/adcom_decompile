// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzw;
import android.support.annotation.Nullable;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzvf
{
    private final Context mContext;
    private final Object mLock;
    private final String zzbpx;
    private zzalo<zzuu> zzbpy;
    private zzalo<zzuu> zzbpz;
    @Nullable
    private zzvw zzbqa;
    private int zzbqb;
    private final zzang zzyf;
    
    public zzvf(final Context context, final zzang zzyf, final String zzbpx) {
        this.mLock = new Object();
        this.zzbqb = 1;
        this.zzbpx = zzbpx;
        this.mContext = context.getApplicationContext();
        this.zzyf = zzyf;
        this.zzbpy = new zzvr<zzuu>();
        this.zzbpz = new zzvr<zzuu>();
    }
    
    public zzvf(final Context context, final zzang zzang, final String s, final zzalo<zzuu> zzbpy, final zzalo<zzuu> zzbpz) {
        this(context, zzang, s);
        this.zzbpy = zzbpy;
        this.zzbpz = zzbpz;
    }
    
    protected final zzvw zza(@Nullable final zzci zzci) {
        final zzvw zzvw = new zzvw(this.zzbpz);
        zzaoe.zzcvy.execute(new zzvg(this, zzci, zzvw));
        zzvw.zza(new zzvo(this, zzvw), new zzvp(this, zzvw));
        return zzvw;
    }
    
    public final zzvs zzb(@Nullable final zzci zzci) {
        synchronized (this.mLock) {
            if (this.zzbqa == null || this.zzbqa.getStatus() == -1) {
                this.zzbqb = 2;
                this.zzbqa = this.zza((zzci)null);
                return this.zzbqa.zzlz();
            }
            if (this.zzbqb == 0) {
                return this.zzbqa.zzlz();
            }
        }
        if (this.zzbqb == 1) {
            this.zzbqb = 2;
            this.zza((zzci)null);
            // monitorexit(zzci)
            return this.zzbqa.zzlz();
        }
        if (this.zzbqb == 2) {
            // monitorexit(zzci)
            return this.zzbqa.zzlz();
        }
        // monitorexit(zzci)
        return this.zzbqa.zzlz();
    }
}
