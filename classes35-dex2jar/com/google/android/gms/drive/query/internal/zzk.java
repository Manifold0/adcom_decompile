// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import java.util.List;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.query.Filter;

public final class zzk implements zzj<Boolean>
{
    private Boolean zzlw;
    
    private zzk() {
        this.zzlw = false;
    }
    
    public static boolean zza(@Nullable final Filter filter) {
        return filter != null && filter.zza((zzj<Boolean>)new zzk());
    }
}
