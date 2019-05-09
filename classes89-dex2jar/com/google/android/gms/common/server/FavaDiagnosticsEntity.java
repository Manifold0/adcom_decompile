// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@SafeParcelable$Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable
{
    @KeepForSdk
    public static final Parcelable$Creator<FavaDiagnosticsEntity> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(id = 2)
    private final String zapj;
    @SafeParcelable$Field(id = 3)
    private final int zapk;
    
    static {
        CREATOR = (Parcelable$Creator)new zaa();
    }
    
    @SafeParcelable$Constructor
    public FavaDiagnosticsEntity(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final String zapj, @SafeParcelable$Param(id = 3) final int zapk) {
        this.zalf = zalf;
        this.zapj = zapj;
        this.zapk = zapk;
    }
    
    @KeepForSdk
    public FavaDiagnosticsEntity(final String zapj, final int zapk) {
        this.zalf = 1;
        this.zapj = zapj;
        this.zapk = zapk;
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeString(parcel, 2, this.zapj, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zapk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
