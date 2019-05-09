// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import java.util.Collection;
import com.google.android.gms.drive.metadata.zza;

public class zzb extends zza<Boolean>
{
    public zzb(final String s, final int n) {
        super(s, n);
    }
    
    public zzb(final String s, final Collection<String> collection, final Collection<String> collection2, final int n) {
        super(s, collection, collection2, 7000000);
    }
    
    protected Boolean zze(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getBoolean(this.getName(), n, n2);
    }
}
