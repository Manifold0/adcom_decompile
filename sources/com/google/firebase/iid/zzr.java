package com.google.firebase.iid;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.concurrent.Executor;

final class zzr implements MessagingChannel {
    private final FirebaseApp zzam;
    private final zzan zzan;
    private final zzat zzbi;
    private final Executor zzbj;

    zzr(FirebaseApp firebaseApp, zzan zzan, Executor executor) {
        this(firebaseApp, zzan, executor, new zzat(firebaseApp.getApplicationContext(), zzan));
    }

    @VisibleForTesting
    private zzr(FirebaseApp firebaseApp, zzan zzan, Executor executor, zzat zzat) {
        this.zzam = firebaseApp;
        this.zzan = zzan;
        this.zzbi = zzat;
        this.zzbj = executor;
    }

    public final boolean isAvailable() {
        return this.zzan.zzac() != 0;
    }

    public final boolean isChannelBuilt() {
        return true;
    }

    public final Task<Void> buildChannel(String str, String str2) {
        return Tasks.forResult(null);
    }

    public final Task<String> getToken(String str, String str2, String str3, String str4) {
        return zzc(zza(str, str3, str4, new Bundle()));
    }

    public final Task<Void> deleteToken(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("delete", "1");
        return zzb(zzc(zza(str, str3, str4, bundle)));
    }

    public final Task<Void> deleteInstanceId(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("iid-operation", "delete");
        bundle.putString("delete", "1");
        return zzb(zzc(zza(str, "*", "*", bundle)));
    }

    public final Task<Void> ackMessage(String str) {
        return null;
    }

    public final Task<Void> subscribeToTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String str4 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString(str4, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        str4 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4), bundle)));
    }

    public final Task<Void> unsubscribeFromTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String str4 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString(str4, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        bundle.putString("delete", "1");
        str4 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str3);
        return zzb(zzc(zza(str, str2, valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4), bundle)));
    }

    private final Task<Bundle> zza(String str, String str2, String str3, Bundle bundle) {
        bundle.putString("scope", str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.zzam.getOptions().getApplicationId());
        bundle.putString("gmsv", Integer.toString(this.zzan.zzaf()));
        bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
        bundle.putString("app_ver", this.zzan.zzad());
        bundle.putString("app_ver_name", this.zzan.zzae());
        bundle.putString("cliv", "fiid-12451000");
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzbj.execute(new zzs(this, bundle, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    private static String zza(Bundle bundle) throws IOException {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String string = bundle.getString("registration_id");
        if (string == null) {
            string = bundle.getString("unregistered");
            if (string == null) {
                string = bundle.getString("error");
                if ("RST".equals(string)) {
                    throw new IOException("INSTANCE_ID_RESET");
                } else if (string != null) {
                    throw new IOException(string);
                } else {
                    String valueOf = String.valueOf(bundle);
                    Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 21).append("Unexpected response: ").append(valueOf).toString(), new Throwable());
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
            }
        }
        return string;
    }

    private final <T> Task<Void> zzb(Task<T> task) {
        return task.continueWith(zzi.zze(), new zzt(this));
    }

    private final Task<String> zzc(Task<Bundle> task) {
        return task.continueWith(this.zzbj, new zzu(this));
    }

    final /* synthetic */ void zza(Bundle bundle, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(this.zzbi.zzc(bundle));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }
}
