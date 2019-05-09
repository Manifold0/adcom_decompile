// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AccountChangeEventsRequestCreator")
public class AccountChangeEventsRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AccountChangeEventsRequest> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zze;
    @Deprecated
    @SafeParcelable$Field(id = 3)
    private String zzg;
    @SafeParcelable$Field(id = 2)
    private int zzi;
    @SafeParcelable$Field(id = 4)
    private Account zzk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    public AccountChangeEventsRequest() {
        this.zze = 1;
    }
    
    @SafeParcelable$Constructor
    AccountChangeEventsRequest(@SafeParcelable$Param(id = 1) final int zze, @SafeParcelable$Param(id = 2) final int zzi, @SafeParcelable$Param(id = 3) final String zzg, @SafeParcelable$Param(id = 4) final Account zzk) {
        this.zze = zze;
        this.zzi = zzi;
        this.zzg = zzg;
        if (zzk == null && !TextUtils.isEmpty((CharSequence)zzg)) {
            this.zzk = new Account(zzg, "com.google");
            return;
        }
        this.zzk = zzk;
    }
    
    public Account getAccount() {
        return this.zzk;
    }
    
    @Deprecated
    public String getAccountName() {
        return this.zzg;
    }
    
    public int getEventIndex() {
        return this.zzi;
    }
    
    public AccountChangeEventsRequest setAccount(final Account zzk) {
        this.zzk = zzk;
        return this;
    }
    
    @Deprecated
    public AccountChangeEventsRequest setAccountName(final String zzg) {
        this.zzg = zzg;
        return this;
    }
    
    public AccountChangeEventsRequest setEventIndex(final int zzi) {
        this.zzi = zzi;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeInt(parcel, 2, this.zzi);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzk, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
