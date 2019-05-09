// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.drive.zzia;
import com.google.android.gms.internal.drive.zzik;
import com.google.android.gms.internal.drive.zzic;
import com.google.android.gms.internal.drive.zzhp;
import java.util.HashMap;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Map;

public final class zzf
{
    private static final Map<String, MetadataField<?>> zzip;
    private static final Map<String, zzg> zziq;
    
    static {
        zzip = new HashMap<String, MetadataField<?>>();
        zziq = new HashMap<String, zzg>();
        zzb(zzhp.zziv);
        zzb(zzhp.zzkb);
        zzb(zzhp.zzjs);
        zzb(zzhp.zzjz);
        zzb(zzhp.zzkc);
        zzb(zzhp.zzji);
        zzb(zzhp.zzjh);
        zzb(zzhp.zzjj);
        zzb(zzhp.zzjk);
        zzb(zzhp.zzjl);
        zzb(zzhp.zzjf);
        zzb(zzhp.zzjn);
        zzb(zzhp.zzjo);
        zzb(zzhp.zzjp);
        zzb(zzhp.zzjx);
        zzb(zzhp.zziw);
        zzb(zzhp.zzju);
        zzb(zzhp.zziy);
        zzb(zzhp.zzjg);
        zzb(zzhp.zziz);
        zzb(zzhp.zzja);
        zzb(zzhp.zzjb);
        zzb(zzhp.zzjc);
        zzb(zzhp.zzjr);
        zzb(zzhp.zzjm);
        zzb(zzhp.zzjt);
        zzb(zzhp.zzjv);
        zzb(zzhp.zzjw);
        zzb(zzhp.zzjy);
        zzb(zzhp.zzkd);
        zzb(zzhp.zzke);
        zzb(zzhp.zzje);
        zzb(zzhp.zzjd);
        zzb(zzhp.zzka);
        zzb(zzhp.zzjq);
        zzb(zzhp.zzix);
        zzb(zzhp.zzkf);
        zzb(zzhp.zzkg);
        zzb(zzhp.zzkh);
        zzb(zzhp.zzki);
        zzb(zzhp.zzkj);
        zzb(zzhp.zzkk);
        zzb(zzhp.zzkl);
        zzb(zzic.zzkn);
        zzb(zzic.zzkp);
        zzb(zzic.zzkq);
        zzb(zzic.zzkr);
        zzb(zzic.zzko);
        zzb(zzic.zzks);
        zzb(zzik.zzku);
        zzb(zzik.zzkv);
        zza(zzo.zziu);
        zza(zzia.zzkm);
    }
    
    public static void zza(final DataHolder dataHolder) {
        final Iterator<zzg> iterator = zzf.zziq.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().zzb(dataHolder);
        }
    }
    
    private static void zza(final zzg zzg) {
        if (zzf.zziq.put(zzg.zzav(), zzg) != null) {
            final String zzav = zzg.zzav();
            throw new IllegalStateException(new StringBuilder(String.valueOf(zzav).length() + 46).append("A cleaner for key ").append(zzav).append(" has already been registered").toString());
        }
    }
    
    public static Collection<MetadataField<?>> zzau() {
        return Collections.unmodifiableCollection((Collection<? extends MetadataField<?>>)zzf.zzip.values());
    }
    
    private static void zzb(final MetadataField<?> metadataField) {
        if (zzf.zzip.containsKey(metadataField.getName())) {
            final String value = String.valueOf(metadataField.getName());
            String concat;
            if (value.length() != 0) {
                concat = "Duplicate field name registered: ".concat(value);
            }
            else {
                concat = new String("Duplicate field name registered: ");
            }
            throw new IllegalArgumentException(concat);
        }
        zzf.zzip.put(metadataField.getName(), metadataField);
    }
    
    public static MetadataField<?> zzd(final String s) {
        return zzf.zzip.get(s);
    }
}
