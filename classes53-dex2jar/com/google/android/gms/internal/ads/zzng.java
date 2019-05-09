// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Iterator;
import org.json.JSONObject;
import android.content.SharedPreferences$Editor;
import java.util.ArrayList;
import java.util.Collection;

@zzadh
public final class zzng
{
    private final Collection<zzna<?>> zzats;
    private final Collection<zzna<String>> zzatt;
    private final Collection<zzna<String>> zzatu;
    
    public zzng() {
        this.zzats = new ArrayList<zzna<?>>();
        this.zzatt = new ArrayList<zzna<String>>();
        this.zzatu = new ArrayList<zzna<String>>();
    }
    
    public final void zza(final SharedPreferences$Editor sharedPreferences$Editor, final int n, final JSONObject jsonObject) {
        for (final zzna<Object> zzna : this.zzats) {
            if (zzna.getSource() == 1) {
                zzna.zza(sharedPreferences$Editor, zzna.zzb(jsonObject));
            }
        }
    }
    
    public final void zza(final zzna zzna) {
        this.zzats.add(zzna);
    }
    
    public final void zzb(final zzna<String> zzna) {
        this.zzatt.add(zzna);
    }
    
    public final void zzc(final zzna<String> zzna) {
        this.zzatu.add(zzna);
    }
    
    public final List<String> zzjb() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<zzna<String>> iterator = this.zzatt.iterator();
        while (iterator.hasNext()) {
            final String s = zzkb.zzik().zzd(iterator.next());
            if (s != null) {
                list.add(s);
            }
        }
        return list;
    }
    
    public final List<String> zzjc() {
        final List<String> zzjb = this.zzjb();
        final Iterator<zzna<String>> iterator = this.zzatu.iterator();
        while (iterator.hasNext()) {
            final String s = zzkb.zzik().zzd(iterator.next());
            if (s != null) {
                zzjb.add(s);
            }
        }
        return zzjb;
    }
}
