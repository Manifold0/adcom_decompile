// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata;

import java.util.Iterator;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

public abstract class zza<T> implements MetadataField<T>
{
    private final String fieldName;
    private final Set<String> zzig;
    private final Set<String> zzih;
    private final int zzii;
    
    protected zza(final String s, final int zzii) {
        this.fieldName = (String)Preconditions.checkNotNull((Object)s, (Object)"fieldName");
        this.zzig = Collections.singleton(s);
        this.zzih = Collections.emptySet();
        this.zzii = zzii;
    }
    
    protected zza(final String s, final Collection<String> collection, final Collection<String> collection2, final int zzii) {
        this.fieldName = (String)Preconditions.checkNotNull((Object)s, (Object)"fieldName");
        this.zzig = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>(collection));
        this.zzih = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>(collection2));
        this.zzii = zzii;
    }
    
    @Override
    public final String getName() {
        return this.fieldName;
    }
    
    @Override
    public String toString() {
        return this.fieldName;
    }
    
    @Override
    public final T zza(final Bundle bundle) {
        Preconditions.checkNotNull((Object)bundle, (Object)"bundle");
        if (bundle.get(this.fieldName) != null) {
            return this.zzb(bundle);
        }
        return null;
    }
    
    @Override
    public final T zza(final DataHolder dataHolder, final int n, final int n2) {
        if (this.zzb(dataHolder, n, n2)) {
            return this.zzc(dataHolder, n, n2);
        }
        return null;
    }
    
    protected abstract void zza(final Bundle p0, final T p1);
    
    @Override
    public final void zza(final DataHolder dataHolder, final MetadataBundle metadataBundle, final int n, final int n2) {
        Preconditions.checkNotNull((Object)dataHolder, (Object)"dataHolder");
        Preconditions.checkNotNull((Object)metadataBundle, (Object)"bundle");
        if (this.zzb(dataHolder, n, n2)) {
            metadataBundle.zzb((MetadataField<Object>)this, this.zzc(dataHolder, n, n2));
        }
    }
    
    @Override
    public final void zza(final T t, final Bundle bundle) {
        Preconditions.checkNotNull((Object)bundle, (Object)"bundle");
        if (t == null) {
            bundle.putString(this.fieldName, (String)null);
            return;
        }
        this.zza(bundle, t);
    }
    
    public final Collection<String> zzar() {
        return this.zzig;
    }
    
    protected abstract T zzb(final Bundle p0);
    
    protected boolean zzb(final DataHolder dataHolder, final int n, final int n2) {
        for (final String s : this.zzig) {
            if (!dataHolder.hasColumn(s) || dataHolder.hasNull(s, n, n2)) {
                return false;
            }
        }
        return true;
    }
    
    protected abstract T zzc(final DataHolder p0, final int p1, final int p2);
}
