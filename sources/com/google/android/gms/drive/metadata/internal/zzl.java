package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.zzb;
import java.util.ArrayList;
import java.util.Collection;

public class zzl<T extends Parcelable> extends zzb<T> {
    public zzl(String str, Collection<String> collection, Collection<String> collection2, int i) {
        super(str, collection, collection2, i);
    }

    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        Collection collection = (Collection) obj;
        bundle.putParcelableArrayList(getName(), collection instanceof ArrayList ? (ArrayList) collection : new ArrayList(collection));
    }

    protected /* synthetic */ Object zzb(Bundle bundle) {
        return zzc(bundle);
    }

    protected Collection<T> zzc(Bundle bundle) {
        return bundle.getParcelableArrayList(getName());
    }
}
