// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import android.support.annotation.Nullable;
import java.util.Map;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzalt
{
    private static zzv zzctf;
    private static final Object zzctg;
    @Deprecated
    private static final zzalz<Void> zzcth;
    
    static {
        zzctg = new Object();
        zzcth = new zzalu();
    }
    
    public zzalt(Context applicationContext) {
        if (applicationContext.getApplicationContext() != null) {
            applicationContext = applicationContext.getApplicationContext();
        }
        zzbb(applicationContext);
    }
    
    @VisibleForTesting
    private static zzv zzbb(final Context context) {
        synchronized (zzalt.zzctg) {
            if (zzalt.zzctf == null) {
                zznk.initialize(context);
                zzv zzba;
                if (zzkb.zzik().zzd(zznk.zzbdv)) {
                    zzba = zzaln.zzba(context);
                }
                else {
                    zzba = new zzv(new zzam(new File(context.getCacheDir(), "volley")), new zzaj(new zzas()));
                    zzba.start();
                }
                zzalt.zzctf = zzba;
            }
            return zzalt.zzctf;
        }
    }
    
    public final zzanz<String> zza(final int n, final String s, @Nullable Map<String, String> zzaly, @Nullable final byte[] array) {
        final zzama<String> zzama = new zzama<String>(null);
        final zzalx zzalx = new zzalx(this, s, zzama);
        final zzamy zzamy = new zzamy((String)null);
        zzaly = new zzaly(this, n, s, zzama, zzalx, array, (Map)zzaly, zzamy);
        while (true) {
            if (!com.google.android.gms.internal.ads.zzamy.isEnabled()) {
                break Label_0075;
            }
            try {
                zzamy.zza(s, "GET", (Map)zzaly.getHeaders(), zzaly.zzg());
                zzalt.zzctf.zze((zzr<Object>)zzaly);
                return zzama;
            }
            catch (zza zza) {
                zzakb.zzdk(zza.getMessage());
                continue;
            }
            break;
        }
    }
    
    @Deprecated
    public final <T> zzanz<T> zza(final String s, final zzalz<T> zzalz) {
        final zzaoj<Object> zzaoj = new zzaoj<Object>();
        zzalt.zzctf.zze((zzr<Object>)new zzamb(s, (zzaoj<zzp>)zzaoj));
        return (zzanz<T>)zzano.zza((zzanz<?>)zzano.zza(zzaoj, (zzank<Object, Object>)new zzalw(this, zzalz), zzaki.zzcrj), Throwable.class, (zzanj<? super Throwable, ?>)new zzalv(this, zzalz), zzaoe.zzcvz);
    }
    
    public final zzanz<String> zzc(final String s, final Map<String, String> map) {
        return this.zza(0, s, map, null);
    }
}
