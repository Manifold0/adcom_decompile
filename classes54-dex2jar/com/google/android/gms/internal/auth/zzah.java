// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "UserChallengeRequestCreator")
public final class zzah extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzah> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final String accountType;
    @SafeParcelable$Field(id = 3)
    private final PendingIntent zzbx;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzai();
    }
    
    @SafeParcelable$Constructor
    zzah(@SafeParcelable$Param(id = 1) final int n, @SafeParcelable$Param(id = 2) final String s, @SafeParcelable$Param(id = 3) final PendingIntent pendingIntent) {
        this.zzv = 1;
        this.accountType = (String)Preconditions.checkNotNull((Object)s);
        this.zzbx = (PendingIntent)Preconditions.checkNotNull((Object)pendingIntent);
    }
    
    public zzah(final String s, final PendingIntent pendingIntent) {
        this(1, s, pendingIntent);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeString(parcel, 2, this.accountType, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzbx, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
