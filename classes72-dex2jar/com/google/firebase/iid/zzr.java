// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.tasks.Continuation;
import android.util.Log;
import java.io.IOException;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.os.Build$VERSION;
import com.google.android.gms.tasks.Task;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import com.google.firebase.FirebaseApp;

final class zzr implements MessagingChannel
{
    private final FirebaseApp zzam;
    private final zzan zzan;
    private final zzat zzbi;
    private final Executor zzbj;
    
    zzr(final FirebaseApp firebaseApp, final zzan zzan, final Executor executor) {
        this(firebaseApp, zzan, executor, new zzat(firebaseApp.getApplicationContext(), zzan));
    }
    
    @VisibleForTesting
    private zzr(final FirebaseApp zzam, final zzan zzan, final Executor zzbj, final zzat zzbi) {
        this.zzam = zzam;
        this.zzan = zzan;
        this.zzbi = zzbi;
        this.zzbj = zzbj;
    }
    
    private final Task<Bundle> zza(final String s, final String s2, final String s3, final Bundle bundle) {
        bundle.putString("scope", s3);
        bundle.putString("sender", s2);
        bundle.putString("subtype", s2);
        bundle.putString("appid", s);
        bundle.putString("gmp_app_id", this.zzam.getOptions().getApplicationId());
        bundle.putString("gmsv", Integer.toString(this.zzan.zzaf()));
        bundle.putString("osv", Integer.toString(Build$VERSION.SDK_INT));
        bundle.putString("app_ver", this.zzan.zzad());
        bundle.putString("app_ver_name", this.zzan.zzae());
        bundle.putString("cliv", "fiid-12451000");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzbj.execute(new zzs(this, bundle, taskCompletionSource));
        return (Task<Bundle>)taskCompletionSource.getTask();
    }
    
    private static String zza(final Bundle bundle) throws IOException {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String s = bundle.getString("registration_id");
        if (s != null || (s = bundle.getString("unregistered")) != null) {
            return s;
        }
        final String string = bundle.getString("error");
        if ("RST".equals(string)) {
            throw new IOException("INSTANCE_ID_RESET");
        }
        if (string != null) {
            throw new IOException(string);
        }
        final String value = String.valueOf(bundle);
        Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(value).length() + 21).append("Unexpected response: ").append(value).toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    
    private final <T> Task<Void> zzb(final Task<T> task) {
        return (Task<Void>)task.continueWith(zzi.zze(), (Continuation)new zzt(this));
    }
    
    private final Task<String> zzc(final Task<Bundle> task) {
        return (Task<String>)task.continueWith(this.zzbj, (Continuation)new zzu(this));
    }
    
    @Override
    public final Task<Void> ackMessage(final String s) {
        return null;
    }
    
    @Override
    public final Task<Void> buildChannel(final String s, final String s2) {
        return (Task<Void>)Tasks.forResult((Object)null);
    }
    
    @Override
    public final Task<Void> deleteInstanceId(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("iid-operation", "delete");
        bundle.putString("delete", "1");
        return this.zzb(this.zzc(this.zza(s, "*", "*", bundle)));
    }
    
    @Override
    public final Task<Void> deleteToken(final String s, final String s2, final String s3, final String s4) {
        final Bundle bundle = new Bundle();
        bundle.putString("delete", "1");
        return this.zzb(this.zzc(this.zza(s, s3, s4, bundle)));
    }
    
    @Override
    public final Task<String> getToken(final String s, final String s2, final String s3, final String s4) {
        return this.zzc(this.zza(s, s3, s4, new Bundle()));
    }
    
    @Override
    public final boolean isAvailable() {
        return this.zzan.zzac() != 0;
    }
    
    @Override
    public final boolean isChannelBuilt() {
        return true;
    }
    
    @Override
    public final Task<Void> subscribeToTopic(final String s, final String s2, String s3) {
        final Bundle bundle = new Bundle();
        final String value = String.valueOf("/topics/");
        final String value2 = String.valueOf(s3);
        String concat;
        if (value2.length() != 0) {
            concat = value.concat(value2);
        }
        else {
            concat = new String(value);
        }
        bundle.putString("gcm.topic", concat);
        final String value3 = String.valueOf("/topics/");
        s3 = String.valueOf(s3);
        if (s3.length() != 0) {
            s3 = value3.concat(s3);
        }
        else {
            s3 = new String(value3);
        }
        return this.zzb(this.zzc(this.zza(s, s2, s3, bundle)));
    }
    
    @Override
    public final Task<Void> unsubscribeFromTopic(final String s, final String s2, String s3) {
        final Bundle bundle = new Bundle();
        final String value = String.valueOf("/topics/");
        final String value2 = String.valueOf(s3);
        String concat;
        if (value2.length() != 0) {
            concat = value.concat(value2);
        }
        else {
            concat = new String(value);
        }
        bundle.putString("gcm.topic", concat);
        bundle.putString("delete", "1");
        final String value3 = String.valueOf("/topics/");
        s3 = String.valueOf(s3);
        if (s3.length() != 0) {
            s3 = value3.concat(s3);
        }
        else {
            s3 = new String(value3);
        }
        return this.zzb(this.zzc(this.zza(s, s2, s3, bundle)));
    }
}
