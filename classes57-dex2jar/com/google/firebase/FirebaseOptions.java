// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import android.text.TextUtils;
import com.google.android.gms.common.internal.StringResourceValueReader;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Strings;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import com.google.firebase.annotations.PublicApi;

@PublicApi
public final class FirebaseOptions
{
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    
    private FirebaseOptions(@NonNull final String zzb, @NonNull final String zza, @Nullable final String zzc, @Nullable final String zzd, @Nullable final String zze, @Nullable final String zzf, @Nullable final String zzg) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(zzb), (Object)"ApplicationId must be set.");
        this.zzb = zzb;
        this.zza = zza;
        this.zzc = zzc;
        this.zzd = zzd;
        this.zze = zze;
        this.zzf = zzf;
        this.zzg = zzg;
    }
    
    @PublicApi
    public static FirebaseOptions fromResource(final Context context) {
        final StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(context);
        final String string = stringResourceValueReader.getString("google_app_id");
        if (TextUtils.isEmpty((CharSequence)string)) {
            return null;
        }
        return new FirebaseOptions(string, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString("project_id"));
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof FirebaseOptions) {
            final FirebaseOptions firebaseOptions = (FirebaseOptions)o;
            if (Objects.equal((Object)this.zzb, (Object)firebaseOptions.zzb) && Objects.equal((Object)this.zza, (Object)firebaseOptions.zza) && Objects.equal((Object)this.zzc, (Object)firebaseOptions.zzc) && Objects.equal((Object)this.zzd, (Object)firebaseOptions.zzd) && Objects.equal((Object)this.zze, (Object)firebaseOptions.zze) && Objects.equal((Object)this.zzf, (Object)firebaseOptions.zzf) && Objects.equal((Object)this.zzg, (Object)firebaseOptions.zzg)) {
                return true;
            }
        }
        return false;
    }
    
    @PublicApi
    public final String getApiKey() {
        return this.zza;
    }
    
    @PublicApi
    public final String getApplicationId() {
        return this.zzb;
    }
    
    @PublicApi
    public final String getDatabaseUrl() {
        return this.zzc;
    }
    
    @KeepForSdk
    public final String getGaTrackingId() {
        return this.zzd;
    }
    
    @PublicApi
    public final String getGcmSenderId() {
        return this.zze;
    }
    
    @PublicApi
    public final String getProjectId() {
        return this.zzg;
    }
    
    @PublicApi
    public final String getStorageBucket() {
        return this.zzf;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzb, this.zza, this.zzc, this.zzd, this.zze, this.zzf, this.zzg });
    }
    
    @Override
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("applicationId", (Object)this.zzb).add("apiKey", (Object)this.zza).add("databaseUrl", (Object)this.zzc).add("gcmSenderId", (Object)this.zze).add("storageBucket", (Object)this.zzf).add("projectId", (Object)this.zzg).toString();
    }
    
    @PublicApi
    public static final class Builder
    {
        private String zza;
        private String zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private String zzg;
        
        @PublicApi
        public Builder() {
        }
        
        @PublicApi
        public Builder(final FirebaseOptions firebaseOptions) {
            this.zzb = firebaseOptions.zzb;
            this.zza = firebaseOptions.zza;
            this.zzc = firebaseOptions.zzc;
            this.zzd = firebaseOptions.zzd;
            this.zze = firebaseOptions.zze;
            this.zzf = firebaseOptions.zzf;
            this.zzg = firebaseOptions.zzg;
        }
        
        @PublicApi
        public final FirebaseOptions build() {
            return new FirebaseOptions(this.zzb, this.zza, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, (byte)0);
        }
        
        @PublicApi
        public final Builder setApiKey(@NonNull final String s) {
            this.zza = Preconditions.checkNotEmpty(s, (Object)"ApiKey must be set.");
            return this;
        }
        
        @PublicApi
        public final Builder setApplicationId(@NonNull final String s) {
            this.zzb = Preconditions.checkNotEmpty(s, (Object)"ApplicationId must be set.");
            return this;
        }
        
        @PublicApi
        public final Builder setDatabaseUrl(@Nullable final String zzc) {
            this.zzc = zzc;
            return this;
        }
        
        @KeepForSdk
        public final Builder setGaTrackingId(@Nullable final String zzd) {
            this.zzd = zzd;
            return this;
        }
        
        @PublicApi
        public final Builder setGcmSenderId(@Nullable final String zze) {
            this.zze = zze;
            return this;
        }
        
        @PublicApi
        public final Builder setProjectId(@Nullable final String zzg) {
            this.zzg = zzg;
            return this;
        }
        
        @PublicApi
        public final Builder setStorageBucket(@Nullable final String zzf) {
            this.zzf = zzf;
            return this;
        }
    }
}
