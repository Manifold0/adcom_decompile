// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.Preconditions;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class zzu extends AbstractSafeParcelable
{
    private transient volatile boolean zzbr;
    
    public zzu() {
        this.zzbr = false;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Preconditions.checkState(!this.zzbr);
        this.zzbr = true;
        this.zza(parcel, n);
    }
    
    protected abstract void zza(final Parcel p0, final int p1);
}
