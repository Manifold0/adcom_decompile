// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@SafeParcelable$Class(creator = "ClientIdentityCreator")
@SafeParcelable$Reserved({ 1000 })
public class ClientIdentity extends AbstractSafeParcelable
{
    @KeepForSdk
    public static final Parcelable$Creator<ClientIdentity> CREATOR;
    @Nullable
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 2)
    private final String packageName;
    @SafeParcelable$Field(defaultValueUnchecked = "0", id = 1)
    private final int uid;
    
    static {
        CREATOR = (Parcelable$Creator)new zab();
    }
    
    @SafeParcelable$Constructor
    public ClientIdentity(@SafeParcelable$Param(id = 1) final int uid, @Nullable @SafeParcelable$Param(id = 2) final String packageName) {
        this.uid = uid;
        this.packageName = packageName;
    }
    
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null || !(o instanceof ClientIdentity)) {
                return false;
            }
            final ClientIdentity clientIdentity = (ClientIdentity)o;
            if (clientIdentity.uid != this.uid || !Objects.equal((Object)clientIdentity.packageName, (Object)this.packageName)) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        return this.uid;
    }
    
    public String toString() {
        final int uid = this.uid;
        final String packageName = this.packageName;
        return new StringBuilder(String.valueOf(packageName).length() + 12).append(uid).append(":").append(packageName).toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.uid);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
