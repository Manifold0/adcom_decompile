// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "TokenDataCreator")
public class TokenData extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<TokenData> CREATOR;
    @SafeParcelable$Field(getter = "getGrantedScopes", id = 6)
    private final List<String> zzaa;
    @SafeParcelable$Field(getter = "getScopeData", id = 7)
    private final String zzab;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    @SafeParcelable$Field(getter = "getToken", id = 2)
    private final String zzw;
    @SafeParcelable$Field(getter = "getExpirationTimeSecs", id = 3)
    private final Long zzx;
    @SafeParcelable$Field(getter = "isCached", id = 4)
    private final boolean zzy;
    @SafeParcelable$Field(getter = "isSnowballed", id = 5)
    private final boolean zzz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    @SafeParcelable$Constructor
    TokenData(@SafeParcelable$Param(id = 1) final int zzv, @SafeParcelable$Param(id = 2) final String s, @SafeParcelable$Param(id = 3) final Long zzx, @SafeParcelable$Param(id = 4) final boolean zzy, @SafeParcelable$Param(id = 5) final boolean zzz, @SafeParcelable$Param(id = 6) final List<String> zzaa, @SafeParcelable$Param(id = 7) final String zzab) {
        this.zzv = zzv;
        this.zzw = Preconditions.checkNotEmpty(s);
        this.zzx = zzx;
        this.zzy = zzy;
        this.zzz = zzz;
        this.zzaa = zzaa;
        this.zzab = zzab;
    }
    
    @Nullable
    public static TokenData zza(Bundle bundle, final String s) {
        bundle.setClassLoader(TokenData.class.getClassLoader());
        bundle = bundle.getBundle(s);
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(TokenData.class.getClassLoader());
        return (TokenData)bundle.getParcelable("TokenData");
    }
    
    public boolean equals(final Object o) {
        if (o instanceof TokenData) {
            final TokenData tokenData = (TokenData)o;
            if (TextUtils.equals((CharSequence)this.zzw, (CharSequence)tokenData.zzw) && Objects.equal((Object)this.zzx, (Object)tokenData.zzx) && this.zzy == tokenData.zzy && this.zzz == tokenData.zzz && Objects.equal((Object)this.zzaa, (Object)tokenData.zzaa) && Objects.equal((Object)this.zzab, (Object)tokenData.zzab)) {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzw, this.zzx, this.zzy, this.zzz, this.zzaa, this.zzab });
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeString(parcel, 2, this.zzw, false);
        SafeParcelWriter.writeLongObject(parcel, 3, this.zzx, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzy);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzz);
        SafeParcelWriter.writeStringList(parcel, 6, (List)this.zzaa, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzab, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzb() {
        return this.zzw;
    }
}
