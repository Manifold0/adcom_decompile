package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.drive.metadata.zza;
import java.util.Collection;

public abstract class zzm<T extends ReflectedParcelable> extends zza<T> {
    public zzm(String str, Collection<String> collection, Collection<String> collection2, int i) {
        super(str, collection, collection2, i);
    }

    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        bundle.putParcelable(getName(), (ReflectedParcelable) obj);
    }

    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return (ReflectedParcelable) bundle.getParcelable(getName());
    }
}
