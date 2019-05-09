package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;
import java.util.List;

public interface zzj<F> {
    <T> F zza(zzb<T> zzb, T t);

    <T> F zza(zzx zzx, MetadataField<T> metadataField, T t);

    F zza(zzx zzx, List<F> list);

    F zza(F f);

    F zzbb();

    F zzbc();

    <T> F zzc(MetadataField<T> metadataField, T t);

    F zze(MetadataField<?> metadataField);

    F zzg(String str);
}
