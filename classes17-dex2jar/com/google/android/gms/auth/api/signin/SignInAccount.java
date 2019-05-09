// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import javax.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SignInAccountCreator")
@SafeParcelable$Reserved({ 1 })
public class SignInAccount extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<SignInAccount> CREATOR;
    @Deprecated
    @SafeParcelable$Field(defaultValue = "", id = 4)
    private String zzba;
    @SafeParcelable$Field(getter = "getGoogleSignInAccount", id = 7)
    private GoogleSignInAccount zzbb;
    @Deprecated
    @SafeParcelable$Field(defaultValue = "", id = 8)
    private String zzbc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    @SafeParcelable$Constructor
    SignInAccount(@SafeParcelable$Param(id = 4) final String s, @SafeParcelable$Param(id = 7) final GoogleSignInAccount zzbb, @SafeParcelable$Param(id = 8) final String s2) {
        this.zzbb = zzbb;
        this.zzba = Preconditions.checkNotEmpty(s, (Object)"8.3 and 8.4 SDKs require non-null email");
        this.zzbc = Preconditions.checkNotEmpty(s2, (Object)"8.3 and 8.4 SDKs require non-null userId");
    }
    
    @Nullable
    public final GoogleSignInAccount getGoogleSignInAccount() {
        return this.zzbb;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 4, this.zzba, false);
        SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.zzbb, n, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzbc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
