// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Predicate;
import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import android.support.annotation.Nullable;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzuw implements zzuo, zzuu
{
    private final Context mContext;
    private final zzaqw zzbnd;
    
    public zzuw(final Context mContext, final zzang zzang, @Nullable final zzci zzci, final zzw zzw) throws zzarg {
        this.mContext = mContext;
        zzbv.zzel();
        this.zzbnd = zzarc.zza(mContext, zzasi.zzvq(), "", false, false, zzci, zzang, null, null, null, zzhs.zzhm());
        this.zzbnd.getView().setWillNotDraw(true);
    }
    
    private static void runOnUiThread(final Runnable runnable) {
        zzkb.zzif();
        if (zzamu.zzsh()) {
            runnable.run();
            return;
        }
        zzakk.zzcrm.post(runnable);
    }
    
    @Override
    public final void destroy() {
        this.zzbnd.destroy();
    }
    
    @Override
    public final void zza(final zzuv zzuv) {
        final zzasc zzuf = this.zzbnd.zzuf();
        zzuv.getClass();
        zzuf.zza(zzuz.zzb(zzuv));
    }
    
    @Override
    public final void zza(final String s, final zzv<? super zzwb> zzv) {
        this.zzbnd.zza(s, new zzvd(zzv));
    }
    
    @Override
    public final void zza(final String s, final Map map) {
        zzup.zza(this, s, map);
    }
    
    @Override
    public final void zza(final String s, final JSONObject jsonObject) {
        zzup.zzb(this, s, jsonObject);
    }
    
    @Override
    public final void zzb(final String s, final zzv<? super zzwb> zzv) {
        this.zzbnd.zza(s, (Predicate<zzv<? super zzaqw>>)new zzuy(zzv));
    }
    
    @Override
    public final void zzb(final String s, final JSONObject jsonObject) {
        zzup.zza(this, s, jsonObject);
    }
    
    @Override
    public final void zzbb(final String s) {
        runOnUiThread(new zzva(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", s)));
    }
    
    @Override
    public final void zzbc(final String s) {
        runOnUiThread(new zzvb(this, s));
    }
    
    @Override
    public final void zzbd(final String s) {
        runOnUiThread(new zzvc(this, s));
    }
    
    @Override
    public final void zzbe(final String s) {
        runOnUiThread(new zzux(this, s));
    }
    
    @Override
    public final void zzf(final String s, final String s2) {
        zzup.zza(this, s, s2);
    }
    
    @Override
    public final zzwc zzlw() {
        return new zzwd(this);
    }
}
