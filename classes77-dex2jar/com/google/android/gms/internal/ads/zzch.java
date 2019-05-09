// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.List;
import android.content.Context;

public final class zzch extends zzcg
{
    private zzch(final Context context, final String s, final boolean b) {
        super(context, s, b);
    }
    
    public static zzch zza(final String s, final Context context, final boolean b) {
        zzcg.zza(context, b);
        return new zzch(context, s, b);
    }
    
    @Override
    protected final List<Callable<Void>> zza(final zzcz zzcz, final zzba zzba, final zzax zzax) {
        if (zzcz.zzab() == null || !this.zzqt) {
            return super.zza(zzcz, zzba, zzax);
        }
        final int zzx = zzcz.zzx();
        final ArrayList<zzds> list = new ArrayList<zzds>();
        list.addAll((Collection<?>)super.zza(zzcz, zzba, zzax));
        list.add(new zzds(zzcz, "1QeH3Cf7T53ayw17Ebbo9YTdhU+IFx0X5nCtC5gZQym4uicOVPXxYWmMK9k58i8n", "bHJRpFJ+2R5LAbYQUBDMyfYpLd1oiGixlpIqMJOBQPY=", zzba, zzx, 24));
        return (List<Callable<Void>>)list;
    }
}
