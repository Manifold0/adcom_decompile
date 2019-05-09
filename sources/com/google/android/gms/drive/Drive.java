package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.drive.zzaf;
import com.google.android.gms.internal.drive.zzaw;
import com.google.android.gms.internal.drive.zzbb;
import com.google.android.gms.internal.drive.zzbr;
import com.google.android.gms.internal.drive.zzcb;
import com.google.android.gms.internal.drive.zzch;
import com.google.android.gms.internal.drive.zzeb;
import java.util.Set;

public final class Drive {
    @Deprecated
    public static final Api<NoOptions> API = new Api("Drive.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<zzaw, NoOptions> CLIENT_BUILDER = new zze();
    public static final ClientKey<zzaw> CLIENT_KEY = new ClientKey();
    @Deprecated
    public static final DriveApi DriveApi = new zzaf();
    @Deprecated
    public static final DrivePreferencesApi DrivePreferencesApi = new zzcb();
    private static final Api<zzb> INTERNAL_API = new Api("Drive.INTERNAL_API", zzq, CLIENT_KEY);
    public static final Scope SCOPE_APPFOLDER = new Scope(Scopes.DRIVE_APPFOLDER);
    public static final Scope SCOPE_FILE = new Scope(Scopes.DRIVE_FILE);
    private static final AbstractClientBuilder<zzaw, zzb> zzq = new zzf();
    private static final AbstractClientBuilder<zzaw, zza> zzr = new zzg();
    private static final Scope zzs = new Scope(Scopes.DRIVE_FULL);
    private static final Scope zzt = new Scope(Scopes.DRIVE_APPS);
    public static final Api<zza> zzu = new Api("Drive.API_CONNECTIONLESS", zzr, CLIENT_KEY);
    @Deprecated
    private static final zzj zzv = new zzbr();
    private static final zzl zzw = new zzeb();

    public static class zza implements HasGoogleSignInAccountOptions {
        private final Bundle zzx = new Bundle();
        private final GoogleSignInAccount zzy;

        public zza(@NonNull GoogleSignInAccount googleSignInAccount) {
            this.zzy = googleSignInAccount;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            zza zza = (zza) obj;
            if (!Objects.equal(this.zzy, zza.getGoogleSignInAccount())) {
                return false;
            }
            String string = this.zzx.getString("method_trace_filename");
            String string2 = zza.zzx.getString("method_trace_filename");
            return ((string == null && string2 == null) || !(string == null || string2 == null || !string.equals(string2))) && this.zzx.getBoolean("bypass_initial_sync") == zza.zzx.getBoolean("bypass_initial_sync") && this.zzx.getInt("proxy_type") == zza.zzx.getInt("proxy_type");
        }

        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzy;
        }

        public final int hashCode() {
            String string = this.zzx.getString("method_trace_filename", "");
            int i = this.zzx.getInt("proxy_type");
            boolean z = this.zzx.getBoolean("bypass_initial_sync");
            return Objects.hashCode(new Object[]{this.zzy, string, Integer.valueOf(i), Boolean.valueOf(z)});
        }

        public final Bundle zzg() {
            return this.zzx;
        }
    }

    public static class zzb implements Optional {
    }

    private Drive() {
    }

    public static DriveClient getDriveClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbb(activity, new zza(googleSignInAccount));
    }

    public static DriveClient getDriveClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbb(context, new zza(googleSignInAccount));
    }

    public static DriveResourceClient getDriveResourceClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzch(activity, new zza(googleSignInAccount));
    }

    public static DriveResourceClient getDriveResourceClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzch(context, new zza(googleSignInAccount));
    }

    private static void zza(GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        Set requestedScopes = googleSignInAccount.getRequestedScopes();
        boolean z = requestedScopes.contains(SCOPE_FILE) || requestedScopes.contains(SCOPE_APPFOLDER) || requestedScopes.contains(zzs) || requestedScopes.contains(zzt);
        Preconditions.checkArgument(z, "You must request a Drive scope in order to interact with the Drive API.");
    }
}
