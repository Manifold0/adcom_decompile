// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AccountChangeEventsResponseCreator")
public class AccountChangeEventsResponse extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AccountChangeEventsResponse> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zze;
    @SafeParcelable$Field(id = 2)
    private final List<AccountChangeEvent> zzl;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    AccountChangeEventsResponse(@SafeParcelable$Param(id = 1) final int zze, @SafeParcelable$Param(id = 2) final List<AccountChangeEvent> list) {
        this.zze = zze;
        this.zzl = (List<AccountChangeEvent>)Preconditions.checkNotNull((Object)list);
    }
    
    public AccountChangeEventsResponse(final List<AccountChangeEvent> list) {
        this.zze = 1;
        this.zzl = (List<AccountChangeEvent>)Preconditions.checkNotNull((Object)list);
    }
    
    public List<AccountChangeEvent> getEvents() {
        return this.zzl;
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzl, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
