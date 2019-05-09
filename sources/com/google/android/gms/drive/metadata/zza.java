package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class zza<T> implements MetadataField<T> {
    private final String fieldName;
    private final Set<String> zzig;
    private final Set<String> zzih;
    private final int zzii;

    protected zza(String str, int i) {
        this.fieldName = (String) Preconditions.checkNotNull(str, "fieldName");
        this.zzig = Collections.singleton(str);
        this.zzih = Collections.emptySet();
        this.zzii = i;
    }

    protected zza(String str, Collection<String> collection, Collection<String> collection2, int i) {
        this.fieldName = (String) Preconditions.checkNotNull(str, "fieldName");
        this.zzig = Collections.unmodifiableSet(new HashSet(collection));
        this.zzih = Collections.unmodifiableSet(new HashSet(collection2));
        this.zzii = i;
    }

    public final String getName() {
        return this.fieldName;
    }

    public String toString() {
        return this.fieldName;
    }

    public final T zza(Bundle bundle) {
        Preconditions.checkNotNull(bundle, String.BUNDLE);
        return bundle.get(this.fieldName) != null ? zzb(bundle) : null;
    }

    public final T zza(DataHolder dataHolder, int i, int i2) {
        return zzb(dataHolder, i, i2) ? zzc(dataHolder, i, i2) : null;
    }

    protected abstract void zza(Bundle bundle, T t);

    public final void zza(DataHolder dataHolder, MetadataBundle metadataBundle, int i, int i2) {
        Preconditions.checkNotNull(dataHolder, "dataHolder");
        Preconditions.checkNotNull(metadataBundle, String.BUNDLE);
        if (zzb(dataHolder, i, i2)) {
            metadataBundle.zzb(this, zzc(dataHolder, i, i2));
        }
    }

    public final void zza(T t, Bundle bundle) {
        Preconditions.checkNotNull(bundle, String.BUNDLE);
        if (t == null) {
            bundle.putString(this.fieldName, null);
        } else {
            zza(bundle, (Object) t);
        }
    }

    public final Collection<String> zzar() {
        return this.zzig;
    }

    protected abstract T zzb(Bundle bundle);

    protected boolean zzb(DataHolder dataHolder, int i, int i2) {
        for (String str : this.zzig) {
            if (dataHolder.hasColumn(str)) {
                if (dataHolder.hasNull(str, i, i2)) {
                }
            }
            return false;
        }
        return true;
    }

    protected abstract T zzc(DataHolder dataHolder, int i, int i2);
}
