// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.AbstractMap;
import java.util.HashSet;

@zzadh
public final class zzwd implements zzuo, zzwc
{
    private final zzwb zzbqz;
    private final HashSet<AbstractMap.SimpleEntry<String, zzv<? super zzwb>>> zzbra;
    
    public zzwd(final zzwb zzbqz) {
        this.zzbqz = zzbqz;
        this.zzbra = new HashSet<AbstractMap.SimpleEntry<String, zzv<? super zzwb>>>();
    }
    
    @Override
    public final void zza(final String s, final zzv<? super zzwb> zzv) {
        this.zzbqz.zza(s, zzv);
        this.zzbra.add((AbstractMap.SimpleEntry<String, zzv<? super zzwb>>)new AbstractMap.SimpleEntry(s, zzv));
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
        this.zzbqz.zzb(s, zzv);
        this.zzbra.remove(new AbstractMap.SimpleEntry(s, zzv));
    }
    
    @Override
    public final void zzb(final String s, final JSONObject jsonObject) {
        zzup.zza(this, s, jsonObject);
    }
    
    @Override
    public final void zzbe(final String s) {
        this.zzbqz.zzbe(s);
    }
    
    @Override
    public final void zzf(final String s, final String s2) {
        zzup.zza(this, s, s2);
    }
    
    @Override
    public final void zzmd() {
        for (final AbstractMap.SimpleEntry<K, zzv<?>> simpleEntry : this.zzbra) {
            final String value = String.valueOf(simpleEntry.getValue().toString());
            String concat;
            if (value.length() != 0) {
                concat = "Unregistering eventhandler: ".concat(value);
            }
            else {
                concat = new String("Unregistering eventhandler: ");
            }
            zzakb.v(concat);
            this.zzbqz.zzb((String)simpleEntry.getKey(), (zzv<? super zzwb>)simpleEntry.getValue());
        }
        this.zzbra.clear();
    }
}
