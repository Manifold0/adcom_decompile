package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzabl {
    public static zzalc zza(Context context, zza zza, zzaji zzaji, zzci zzci, @Nullable zzaqw zzaqw, zzxn zzxn, zzabm zzabm, zznx zznx) {
        zzalc zzabr;
        zzaej zzaej = zzaji.zzcos;
        if (zzaej.zzceq) {
            zzabr = new zzabr(context, zzaji, zzxn, zzabm, zznx, zzaqw);
        } else if (zzaej.zzare || (zza instanceof zzbc)) {
            zzabr = (zzaej.zzare && (zza instanceof zzbc)) ? new zzabt(context, (zzbc) zza, zzaji, zzci, zzabm, zznx) : new zzabo(zzaji, zzabm);
        } else {
            zzabr = (((Boolean) zzkb.zzik().zzd(zznk.zzaxd)).booleanValue() && PlatformVersion.isAtLeastKitKat() && !PlatformVersion.isAtLeastLollipop() && zzaqw != null && zzaqw.zzud().zzvs()) ? new zzabq(context, zzaji, zzaqw, zzabm) : new zzabn(context, zzaji, zzaqw, zzabm);
        }
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(zzabr.getClass().getName());
        zzakb.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzabr.zznt();
        return zzabr;
    }
}
