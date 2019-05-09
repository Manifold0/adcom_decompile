// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.metadata.internal.zzb;
import com.google.android.gms.drive.metadata.internal.zzh;
import com.google.android.gms.drive.metadata.MetadataField;

public final class zzik
{
    public static final MetadataField<Integer> zzku;
    public static final MetadataField<Boolean> zzkv;
    
    static {
        zzku = new zzh("contentAvailability", 4300000);
        zzkv = new zzb("isPinnable", 4300000);
    }
}
