// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.ads.internal.zzbc;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzabl
{
    public static zzalc zza(final Context context, final zza zza, final zzaji zzaji, final zzci zzci, @Nullable final zzaqw zzaqw, final zzxn zzxn, final zzabm zzabm, final zznx zznx) {
        final zzaej zzcos = zzaji.zzcos;
        Object o;
        if (zzcos.zzceq) {
            o = new zzabr(context, zzaji, zzxn, zzabm, zznx, zzaqw);
        }
        else if (zzcos.zzare || zza instanceof zzbc) {
            if (zzcos.zzare && zza instanceof zzbc) {
                o = new zzabt(context, (zzbc)zza, zzaji, zzci, zzabm, zznx);
            }
            else {
                o = new zzabo(zzaji, zzabm);
            }
        }
        else if ((boolean)zzkb.zzik().zzd(zznk.zzaxd) && PlatformVersion.isAtLeastKitKat() && !PlatformVersion.isAtLeastLollipop() && zzaqw != null && zzaqw.zzud().zzvs()) {
            o = new zzabq(context, zzaji, zzaqw, zzabm);
        }
        else {
            o = new zzabn(context, zzaji, zzaqw, zzabm);
        }
        final String value = String.valueOf(((zzabr)o).getClass().getName());
        String concat;
        if (value.length() != 0) {
            concat = "AdRenderer: ".concat(value);
        }
        else {
            concat = new String("AdRenderer: ");
        }
        zzakb.zzck(concat);
        ((zzalc<zzanz>)o).zznt();
        return (zzalc)o;
    }
}
