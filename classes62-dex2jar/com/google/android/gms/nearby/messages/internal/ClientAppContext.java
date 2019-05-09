// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.util.Log;
import java.util.Locale;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ClientAppContextCreator")
public final class ClientAppContext extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<ClientAppContext> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(id = 4)
    private final boolean zzfg;
    @SafeParcelable$Field(id = 6)
    private final String zzfi;
    @SafeParcelable$Field(id = 2)
    private final String zzfj;
    @Nullable
    @SafeParcelable$Field(id = 3)
    private final String zzhe;
    @Deprecated
    @SafeParcelable$Field(id = 5)
    public final int zzhf;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    @SafeParcelable$Constructor
    ClientAppContext(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) String zzhe, @Nullable @SafeParcelable$Param(id = 3) String value, @SafeParcelable$Param(id = 4) final boolean zzfg, @SafeParcelable$Param(id = 5) final int zzhf, @Nullable @SafeParcelable$Param(id = 6) final String zzfi) {
        this.versionCode = versionCode;
        this.zzfj = (String)Preconditions.checkNotNull((Object)zzhe);
        zzhe = value;
        if (value != null) {
            zzhe = value;
            if (!value.isEmpty()) {
                zzhe = value;
                if (!value.startsWith("0p:")) {
                    Log.w("NearbyMessages", String.format(Locale.US, "ClientAppContext: 0P identifier(%s) without 0P prefix(%s)", value, "0p:"));
                    zzhe = String.valueOf("0p:");
                    value = String.valueOf(value);
                    if (value.length() != 0) {
                        zzhe = zzhe.concat(value);
                    }
                    else {
                        zzhe = new String(zzhe);
                    }
                }
            }
        }
        this.zzhe = zzhe;
        this.zzfg = zzfg;
        this.zzhf = zzhf;
        this.zzfi = zzfi;
    }
    
    public ClientAppContext(final String s, @Nullable final String s2, final boolean b, @Nullable final String s3, final int n) {
        this(1, s, s2, b, n, s3);
    }
    
    @Nullable
    static final ClientAppContext zza(@Nullable final ClientAppContext clientAppContext, @Nullable final String s, @Nullable final String s2, final boolean b) {
        if (clientAppContext != null) {
            return clientAppContext;
        }
        if (s != null || s2 != null) {
            return new ClientAppContext(s, s2, b, null, 0);
        }
        return null;
    }
    
    private static boolean zzc(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return TextUtils.isEmpty((CharSequence)s2);
        }
        return s.equals(s2);
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ClientAppContext)) {
                return false;
            }
            final ClientAppContext clientAppContext = (ClientAppContext)o;
            if (!zzc(this.zzfj, clientAppContext.zzfj) || !zzc(this.zzhe, clientAppContext.zzhe) || this.zzfg != clientAppContext.zzfg || !zzc(this.zzfi, clientAppContext.zzfi) || this.zzhf != clientAppContext.zzhf) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzfj, this.zzhe, this.zzfg, this.zzfi, this.zzhf });
    }
    
    public final String toString() {
        return String.format(Locale.US, "{realClientPackageName: %s, zeroPartyIdentifier: %s, useRealClientApiKey: %b, apiKey: %s, callingContext: %d}", this.zzfj, this.zzhe, this.zzfg, this.zzfi, this.zzhf);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzfj, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzhe, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzfg);
        SafeParcelWriter.writeInt(parcel, 5, this.zzhf);
        SafeParcelWriter.writeString(parcel, 6, this.zzfi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
