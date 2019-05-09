// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.query.zzd;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class zza extends AbstractSafeParcelable implements Filter
{
    public String toString() {
        return String.format("Filter[%s]", this.zza((zzj<Object>)new zzd()));
    }
}
