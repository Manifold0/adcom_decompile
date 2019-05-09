// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.regex.Pattern;
import java.io.File;
import android.content.Context;

@zzadh
public final class zzaln extends zzaj
{
    private final Context mContext;
    
    private zzaln(final Context mContext, final zzar zzar) {
        super(zzar);
        this.mContext = mContext;
    }
    
    public static zzv zzba(final Context context) {
        final zzv zzv = new zzv(new zzam(new File(context.getCacheDir(), "admob_volley")), new zzaln(context, new zzas()));
        zzv.start();
        return zzv;
    }
    
    @Override
    public final zzp zzc(final zzr<?> zzr) throws zzae {
        if (zzr.zzh() && zzr.getMethod() == 0 && Pattern.matches((String)zzkb.zzik().zzd(zznk.zzbdw), zzr.getUrl())) {
            zzkb.zzif();
            if (zzamu.zzbe(this.mContext)) {
                final zzp zzc = new zzsm(this.mContext).zzc(zzr);
                if (zzc != null) {
                    final String value = String.valueOf(zzr.getUrl());
                    String concat;
                    if (value.length() != 0) {
                        concat = "Got gmscore asset response: ".concat(value);
                    }
                    else {
                        concat = new String("Got gmscore asset response: ");
                    }
                    zzakb.v(concat);
                    return zzc;
                }
                final String value2 = String.valueOf(zzr.getUrl());
                String concat2;
                if (value2.length() != 0) {
                    concat2 = "Failed to get gmscore asset response: ".concat(value2);
                }
                else {
                    concat2 = new String("Failed to get gmscore asset response: ");
                }
                zzakb.v(concat2);
            }
        }
        return super.zzc(zzr);
    }
}
