package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzalt {
    private static zzv zzctf;
    private static final Object zzctg = new Object();
    @Deprecated
    private static final zzalz<Void> zzcth = new zzalu();

    public zzalt(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        zzbb(context);
    }

    @VisibleForTesting
    private static zzv zzbb(Context context) {
        zzv zzba;
        synchronized (zzctg) {
            if (zzctf == null) {
                zznk.initialize(context);
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbdv)).booleanValue()) {
                    zzba = zzaln.zzba(context);
                } else {
                    zzba = new zzv(new zzam(new File(context.getCacheDir(), "volley")), new zzaj(new zzas()));
                    zzba.start();
                }
                zzctf = zzba;
            }
            zzba = zzctf;
        }
        return zzba;
    }

    public final zzanz<String> zza(int i, String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        Object zzama = new zzama();
        zzy zzalx = new zzalx(this, str, zzama);
        zzamy zzamy = new zzamy(null);
        zzr zzaly = new zzaly(this, i, str, zzama, zzalx, bArr, map, zzamy);
        if (zzamy.isEnabled()) {
            try {
                zzamy.zza(str, "GET", zzaly.getHeaders(), zzaly.zzg());
            } catch (Throwable e) {
                zzakb.zzdk(e.getMessage());
            }
        }
        zzctf.zze(zzaly);
        return zzama;
    }

    @Deprecated
    public final <T> zzanz<T> zza(String str, zzalz<T> zzalz) {
        zzanz zzaoj = new zzaoj();
        zzctf.zze(new zzamb(str, zzaoj));
        return zzano.zza(zzano.zza(zzaoj, new zzalw(this, zzalz), zzaki.zzcrj), Throwable.class, new zzalv(this, zzalz), zzaoe.zzcvz);
    }

    public final zzanz<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }
}
