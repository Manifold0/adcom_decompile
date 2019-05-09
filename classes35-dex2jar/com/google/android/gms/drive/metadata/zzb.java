// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;

public abstract class zzb<T> extends zza<Collection<T>>
{
    protected zzb(final String s, final Collection<String> collection, final Collection<String> collection2, final int n) {
        super(s, collection, collection2, n);
    }
    
    protected Collection<T> zzd(final DataHolder dataHolder, final int n, final int n2) {
        throw new UnsupportedOperationException("Cannot read collections from a dataHolder.");
    }
}
