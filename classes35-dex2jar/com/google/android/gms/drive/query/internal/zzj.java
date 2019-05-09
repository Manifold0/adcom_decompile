// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import java.util.List;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;

public interface zzj<F>
{
     <T> F zza(final zzb<T> p0, final T p1);
    
     <T> F zza(final zzx p0, final MetadataField<T> p1, final T p2);
    
    F zza(final zzx p0, final List<F> p1);
    
    F zza(final F p0);
    
    F zzbb();
    
    F zzbc();
    
     <T> F zzc(final MetadataField<T> p0, final T p1);
    
    F zze(final MetadataField<?> p0);
    
    F zzg(final String p0);
}
