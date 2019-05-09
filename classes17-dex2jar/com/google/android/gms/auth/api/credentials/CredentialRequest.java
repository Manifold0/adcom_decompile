// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CredentialRequestCreator")
public final class CredentialRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<CredentialRequest> CREATOR;
    @SafeParcelable$Field(getter = "getAccountTypes", id = 2)
    private final String[] zzaa;
    @SafeParcelable$Field(getter = "getCredentialPickerConfig", id = 3)
    private final CredentialPickerConfig zzab;
    @SafeParcelable$Field(getter = "getCredentialHintPickerConfig", id = 4)
    private final CredentialPickerConfig zzac;
    @SafeParcelable$Field(getter = "isIdTokenRequested", id = 5)
    private final boolean zzad;
    @SafeParcelable$Field(getter = "getServerClientId", id = 6)
    private final String zzae;
    @SafeParcelable$Field(getter = "getIdTokenNonce", id = 7)
    private final String zzaf;
    @SafeParcelable$Field(getter = "getRequireUserMediation", id = 8)
    private final boolean zzag;
    @SafeParcelable$Field(id = 1000)
    private final int zzu;
    @SafeParcelable$Field(getter = "isPasswordLoginSupported", id = 1)
    private final boolean zzz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    @SafeParcelable$Constructor
    CredentialRequest(@SafeParcelable$Param(id = 1000) final int zzu, @SafeParcelable$Param(id = 1) final boolean zzz, @SafeParcelable$Param(id = 2) final String[] array, @SafeParcelable$Param(id = 3) final CredentialPickerConfig credentialPickerConfig, @SafeParcelable$Param(id = 4) final CredentialPickerConfig credentialPickerConfig2, @SafeParcelable$Param(id = 5) final boolean zzad, @SafeParcelable$Param(id = 6) final String zzae, @SafeParcelable$Param(id = 7) final String zzaf, @SafeParcelable$Param(id = 8) final boolean zzag) {
        this.zzu = zzu;
        this.zzz = zzz;
        this.zzaa = (String[])Preconditions.checkNotNull((Object)array);
        CredentialPickerConfig build = credentialPickerConfig;
        if (credentialPickerConfig == null) {
            build = new CredentialPickerConfig.Builder().build();
        }
        this.zzab = build;
        CredentialPickerConfig build2;
        if ((build2 = credentialPickerConfig2) == null) {
            build2 = new CredentialPickerConfig.Builder().build();
        }
        this.zzac = build2;
        if (zzu < 3) {
            this.zzad = true;
            this.zzae = null;
            this.zzaf = null;
        }
        else {
            this.zzad = zzad;
            this.zzae = zzae;
            this.zzaf = zzaf;
        }
        this.zzag = zzag;
    }
    
    private CredentialRequest(final Builder builder) {
        this(4, builder.zzz, builder.zzaa, builder.zzab, builder.zzac, builder.zzad, builder.zzae, builder.zzaf, false);
    }
    
    @NonNull
    public final String[] getAccountTypes() {
        return this.zzaa;
    }
    
    @NonNull
    public final Set<String> getAccountTypesSet() {
        return new HashSet<String>(Arrays.asList(this.zzaa));
    }
    
    @NonNull
    public final CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzac;
    }
    
    @NonNull
    public final CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzab;
    }
    
    @Nullable
    public final String getIdTokenNonce() {
        return this.zzaf;
    }
    
    @Nullable
    public final String getServerClientId() {
        return this.zzae;
    }
    
    @Deprecated
    public final boolean getSupportsPasswordLogin() {
        return this.isPasswordLoginSupported();
    }
    
    public final boolean isIdTokenRequested() {
        return this.zzad;
    }
    
    public final boolean isPasswordLoginSupported() {
        return this.zzz;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.isPasswordLoginSupported());
        SafeParcelWriter.writeStringArray(parcel, 2, this.getAccountTypes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getCredentialPickerConfig(), n, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.getCredentialHintPickerConfig(), n, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, this.getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, this.getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzu);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzag);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static final class Builder
    {
        private String[] zzaa;
        private CredentialPickerConfig zzab;
        private CredentialPickerConfig zzac;
        private boolean zzad;
        @Nullable
        private String zzae;
        @Nullable
        private String zzaf;
        private boolean zzag;
        private boolean zzz;
        
        public Builder() {
            this.zzad = false;
            this.zzag = false;
            this.zzae = null;
        }
        
        public final CredentialRequest build() {
            if (this.zzaa == null) {
                this.zzaa = new String[0];
            }
            if (!this.zzz && this.zzaa.length == 0) {
                throw new IllegalStateException("At least one authentication method must be specified");
            }
            return new CredentialRequest(this, null);
        }
        
        public final Builder setAccountTypes(final String... array) {
            String[] zzaa = array;
            if (array == null) {
                zzaa = new String[0];
            }
            this.zzaa = zzaa;
            return this;
        }
        
        public final Builder setCredentialHintPickerConfig(final CredentialPickerConfig zzac) {
            this.zzac = zzac;
            return this;
        }
        
        public final Builder setCredentialPickerConfig(final CredentialPickerConfig zzab) {
            this.zzab = zzab;
            return this;
        }
        
        public final Builder setIdTokenNonce(@Nullable final String zzaf) {
            this.zzaf = zzaf;
            return this;
        }
        
        public final Builder setIdTokenRequested(final boolean zzad) {
            this.zzad = zzad;
            return this;
        }
        
        public final Builder setPasswordLoginSupported(final boolean zzz) {
            this.zzz = zzz;
            return this;
        }
        
        public final Builder setServerClientId(@Nullable final String zzae) {
            this.zzae = zzae;
            return this;
        }
        
        @Deprecated
        public final Builder setSupportsPasswordLogin(final boolean passwordLoginSupported) {
            return this.setPasswordLoginSupported(passwordLoginSupported);
        }
    }
}
