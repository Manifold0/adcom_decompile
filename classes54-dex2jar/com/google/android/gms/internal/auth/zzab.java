// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "NotifyCompletionRequestCreator")
public final class zzab extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzab> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final String accountType;
    @SafeParcelable$Field(id = 3)
    private final int zzbw;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzac();
    }
    
    @SafeParcelable$Constructor
    zzab(@SafeParcelable$Param(id = 1) final int n, @SafeParcelable$Param(id = 2) final String s, @SafeParcelable$Param(id = 3) final int zzbw) {
        this.zzv = 1;
        this.accountType = (String)Preconditions.checkNotNull((Object)s);
        this.zzbw = zzbw;
    }
    
    public zzab(final String s, final int n) {
        this(1, s, n);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeString(parcel, 2, this.accountType, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbw);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
