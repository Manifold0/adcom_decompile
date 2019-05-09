package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.internal.drive.zzia;
import com.google.android.gms.internal.drive.zzic;
import com.google.android.gms.internal.drive.zzik;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzf {
    private static final Map<String, MetadataField<?>> zzip = new HashMap();
    private static final Map<String, zzg> zziq = new HashMap();

    static {
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

    public static void zza(DataHolder dataHolder) {
        for (zzg zzb : zziq.values()) {
            zzb.zzb(dataHolder);
        }
    }

    private static void zza(zzg zzg) {
        if (zziq.put(zzg.zzav(), zzg) != null) {
            String zzav = zzg.zzav();
            throw new IllegalStateException(new StringBuilder(String.valueOf(zzav).length() + 46).append("A cleaner for key ").append(zzav).append(" has already been registered").toString());
        }
    }

    public static Collection<MetadataField<?>> zzau() {
        return Collections.unmodifiableCollection(zzip.values());
    }

    private static void zzb(MetadataField<?> metadataField) {
        if (zzip.containsKey(metadataField.getName())) {
            String str = "Duplicate field name registered: ";
            String valueOf = String.valueOf(metadataField.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        zzip.put(metadataField.getName(), metadataField);
    }

    public static MetadataField<?> zzd(String str) {
        return (MetadataField) zzip.get(str);
    }
}
