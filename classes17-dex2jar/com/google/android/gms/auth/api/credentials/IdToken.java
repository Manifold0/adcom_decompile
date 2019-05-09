// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.NonNull;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "IdTokenCreator")
@SafeParcelable$Reserved({ 1000 })
public final class IdToken extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<IdToken> CREATOR;
    @NonNull
    @SafeParcelable$Field(getter = "getIdToken", id = 2)
    private final String zzak;
    @NonNull
    @SafeParcelable$Field(getter = "getAccountType", id = 1)
    private final String zzr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    @SafeParcelable$Constructor
    public IdToken(@NonNull @SafeParcelable$Param(id = 1) final String zzr, @NonNull @SafeParcelable$Param(id = 2) final String zzak) {
        final boolean b = true;
        Preconditions.checkArgument(!TextUtils.isEmpty((CharSequence)zzr), (Object)"account type string cannot be null or empty");
        Preconditions.checkArgument(!TextUtils.isEmpty((CharSequence)zzak) && b, (Object)"id token string cannot be null or empty");
        this.zzr = zzr;
        this.zzak = zzak;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof IdToken)) {
                return false;
            }
            final IdToken idToken = (IdToken)o;
            if (!Objects.equal((Object)this.zzr, (Object)idToken.zzr) || !Objects.equal((Object)this.zzak, (Object)idToken.zzak)) {
                return false;
            }
        }
        return true;
    }
    
    @NonNull
    public final String getAccountType() {
        return this.zzr;
    }
    
    @NonNull
    public final String getIdToken() {
        return this.zzak;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getAccountType(), false);
        SafeParcelWriter.writeString(parcel, 2, this.getIdToken(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
