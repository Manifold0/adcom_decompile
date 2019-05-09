package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.zzd;

public abstract class zza extends AbstractSafeParcelable implements Filter {
    public String toString() {
        return String.format("Filter[%s]", new Object[]{zza(new zzd())});
    }
}
