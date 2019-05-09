// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzuf extends zzus<zzwb> implements zzuo, zzuu
{
    private final zzasv zzbpj;
    
    public zzuf(final Context context, final zzang zzang) throws zzarg {
        try {
            (this.zzbpj = new zzasv(new zzash(context))).setWillNotDraw(true);
            this.zzbpj.zza(new zzug(this));
            this.zzbpj.zza(new zzuh(this));
            this.zzbpj.addJavascriptInterface(new zzun((zzur)this, null), "GoogleJsInterface");
            zzbv.zzek().zza(context, zzang.zzcw, this.zzbpj.getSettings());
        }
        catch (Throwable t) {
            throw new zzarg("Init failed.", t);
        }
    }
    
    @Override
    public final void destroy() {
        this.zzbpj.destroy();
    }
    
    @Override
    public final void zza(final zzuv zzuv) {
        this.zzbpj.zza(new zzuk(zzuv));
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
    public final void zzb(final String s, final JSONObject jsonObject) {
        zzup.zza(this, s, jsonObject);
    }
    
    @Override
    public final void zzbb(final String s) {
        this.zzbc(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", s));
    }
    
    @Override
    public final void zzbc(final String s) {
        zzaoe.zzcvy.execute(new zzui(this, s));
    }
    
    @Override
    public final void zzbd(final String s) {
        zzaoe.zzcvy.execute(new zzuj(this, s));
    }
    
    @Override
    public final void zzbe(final String s) {
        zzaoe.zzcvy.execute(new zzul(this, s));
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
