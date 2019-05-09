// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Api$ApiOptions$Optional;
import com.google.android.gms.common.internal.Objects;
import android.os.Bundle;
import com.google.android.gms.common.api.Api$ApiOptions$HasGoogleSignInAccountOptions;
import java.util.Set;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.drive.zzch;
import android.content.Context;
import com.google.android.gms.internal.drive.zzbb;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.drive.zzcb;
import com.google.android.gms.internal.drive.zzeb;
import com.google.android.gms.internal.drive.zzbr;
import com.google.android.gms.internal.drive.zzaf;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.internal.drive.zzaw;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public final class Drive
{
    @Deprecated
    public static final Api<Api$ApiOptions$NoOptions> API;
    private static final Api$AbstractClientBuilder<zzaw, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    public static final Api$ClientKey<zzaw> CLIENT_KEY;
    @Deprecated
    public static final DriveApi DriveApi;
    @Deprecated
    public static final DrivePreferencesApi DrivePreferencesApi;
    private static final Api<zzb> INTERNAL_API;
    public static final Scope SCOPE_APPFOLDER;
    public static final Scope SCOPE_FILE;
    private static final Api$AbstractClientBuilder<zzaw, zzb> zzq;
    private static final Api$AbstractClientBuilder<zzaw, zza> zzr;
    private static final Scope zzs;
    private static final Scope zzt;
    public static final Api<zza> zzu;
    @Deprecated
    private static final zzj zzv;
    private static final zzl zzw;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zze();
        zzq = new zzf();
        zzr = new zzg();
        SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
        SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
        zzs = new Scope("https://www.googleapis.com/auth/drive");
        zzt = new Scope("https://www.googleapis.com/auth/drive.apps");
        API = new Api("Drive.API", (Api$AbstractClientBuilder)Drive.CLIENT_BUILDER, (Api$ClientKey)Drive.CLIENT_KEY);
        INTERNAL_API = new Api("Drive.INTERNAL_API", (Api$AbstractClientBuilder)Drive.zzq, (Api$ClientKey)Drive.CLIENT_KEY);
        zzu = new Api("Drive.API_CONNECTIONLESS", (Api$AbstractClientBuilder)Drive.zzr, (Api$ClientKey)Drive.CLIENT_KEY);
        DriveApi = new zzaf();
        zzv = (zzj)new zzbr();
        zzw = (zzl)new zzeb();
        DrivePreferencesApi = new zzcb();
    }
    
    private Drive() {
    }
    
    public static DriveClient getDriveClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbb(activity, new zza(googleSignInAccount));
    }
    
    public static DriveClient getDriveClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbb(context, new zza(googleSignInAccount));
    }
    
    public static DriveResourceClient getDriveResourceClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzch(activity, new zza(googleSignInAccount));
    }
    
    public static DriveResourceClient getDriveResourceClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzch(context, new zza(googleSignInAccount));
    }
    
    private static void zza(final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount);
        final Set requestedScopes = googleSignInAccount.getRequestedScopes();
        Preconditions.checkArgument(requestedScopes.contains(Drive.SCOPE_FILE) || requestedScopes.contains(Drive.SCOPE_APPFOLDER) || requestedScopes.contains(Drive.zzs) || requestedScopes.contains(Drive.zzt), (Object)"You must request a Drive scope in order to interact with the Drive API.");
    }
    
    public static final class zza implements Api$ApiOptions$HasGoogleSignInAccountOptions
    {
        private final Bundle zzx;
        private final GoogleSignInAccount zzy;
        
        public zza(@NonNull final GoogleSignInAccount zzy) {
            this.zzx = new Bundle();
            this.zzy = zzy;
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (o != this) {
                if (o == null || o.getClass() != this.getClass()) {
                    return false;
                }
                final zza zza = (zza)o;
                if (!Objects.equal((Object)this.zzy, (Object)zza.getGoogleSignInAccount())) {
                    return false;
                }
                final String string = this.zzx.getString("method_trace_filename");
                final String string2 = zza.zzx.getString("method_trace_filename");
                if (((string != null || string2 != null) && (string == null || string2 == null || !string.equals(string2))) || this.zzx.getBoolean("bypass_initial_sync") != zza.zzx.getBoolean("bypass_initial_sync") || this.zzx.getInt("proxy_type") != zza.zzx.getInt("proxy_type")) {
                    return false;
                }
            }
            return true;
        }
        
        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzy;
        }
        
        @Override
        public final int hashCode() {
            return Objects.hashCode(new Object[] { this.zzy, this.zzx.getString("method_trace_filename", ""), this.zzx.getInt("proxy_type"), this.zzx.getBoolean("bypass_initial_sync") });
        }
        
        public final Bundle zzg() {
            return this.zzx;
        }
    }
    
    public static final class zzb implements Api$ApiOptions$Optional
    {
    }
}
