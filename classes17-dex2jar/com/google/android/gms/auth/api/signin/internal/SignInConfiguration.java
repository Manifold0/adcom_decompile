// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SignInConfigurationCreator")
@SafeParcelable$Reserved({ 1 })
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<SignInConfiguration> CREATOR;
    @SafeParcelable$Field(getter = "getConsumerPkgName", id = 2)
    private final String zzbr;
    @SafeParcelable$Field(getter = "getGoogleConfig", id = 5)
    private GoogleSignInOptions zzbs;
    
    static {
        CREATOR = (Parcelable$Creator)new zzx();
    }
    
    @SafeParcelable$Constructor
    public SignInConfiguration(@SafeParcelable$Param(id = 2) final String s, @SafeParcelable$Param(id = 5) final GoogleSignInOptions zzbs) {
        this.zzbr = Preconditions.checkNotEmpty(s);
        this.zzbs = zzbs;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof SignInConfiguration) {
            final SignInConfiguration signInConfiguration = (SignInConfiguration)o;
            if (this.zzbr.equals(signInConfiguration.zzbr)) {
                if (this.zzbs == null) {
                    if (signInConfiguration.zzbs != null) {
                        return false;
                    }
                }
                else if (!this.zzbs.equals((Object)signInConfiguration.zzbs)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    public final int hashCode() {
        return new HashAccumulator().addObject((Object)this.zzbr).addObject((Object)this.zzbs).hash();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbr, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzbs, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final GoogleSignInOptions zzm() {
        return this.zzbs;
    }
}
