// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "HandleClientLifecycleEventRequestCreator")
public final class zzj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzj> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 2)
    private final ClientAppContext zzhi;
    @SafeParcelable$Field(id = 3)
    private final int zzhj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    public zzj(final int n) {
        this(1, null, n);
    }
    
    @SafeParcelable$Constructor
    zzj(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final ClientAppContext zzhi, @SafeParcelable$Param(id = 3) final int zzhj) {
        this.versionCode = versionCode;
        this.zzhi = zzhi;
        this.zzhj = zzhj;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhi, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzhj);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
