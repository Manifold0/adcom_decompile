package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseInstanceId {
    private static final long zzai = TimeUnit.HOURS.toSeconds(8);
    private static zzaw zzaj;
    @GuardedBy("FirebaseInstanceId.class")
    @VisibleForTesting
    private static ScheduledThreadPoolExecutor zzak;
    private final Executor zzal;
    private final FirebaseApp zzam;
    private final zzan zzan;
    private MessagingChannel zzao;
    private final zzaq zzap;
    private final zzba zzaq;
    @GuardedBy("this")
    private boolean zzar;
    private final zza zzas;

    private class zza {
        private final boolean zzaz = zzu();
        private final Subscriber zzba;
        @Nullable
        @GuardedBy("this")
        private EventHandler<DataCollectionDefaultChange> zzbb;
        @Nullable
        @GuardedBy("this")
        private Boolean zzbc = zzt();
        final /* synthetic */ FirebaseInstanceId zzbd;

        zza(FirebaseInstanceId firebaseInstanceId, Subscriber subscriber) {
            this.zzbd = firebaseInstanceId;
            this.zzba = subscriber;
            if (this.zzbc == null && this.zzaz) {
                this.zzbb = new zzq(this);
                subscriber.subscribe(DataCollectionDefaultChange.class, this.zzbb);
            }
        }

        final synchronized boolean isEnabled() {
            boolean booleanValue;
            booleanValue = this.zzbc != null ? this.zzbc.booleanValue() : this.zzaz && this.zzbd.zzam.isDataCollectionDefaultEnabled();
            return booleanValue;
        }

        final synchronized void setEnabled(boolean z) {
            if (this.zzbb != null) {
                this.zzba.unsubscribe(DataCollectionDefaultChange.class, this.zzbb);
                this.zzbb = null;
            }
            Editor edit = this.zzbd.zzam.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
            edit.putBoolean("auto_init", z);
            edit.apply();
            if (z) {
                this.zzbd.zzg();
            }
            this.zzbc = Boolean.valueOf(z);
        }

        @Nullable
        private final Boolean zzt() {
            Context applicationContext = this.zzbd.zzam.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128);
                    if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled"))) {
                        return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
                    }
                }
            } catch (NameNotFoundException e) {
            }
            return null;
        }

        private final boolean zzu() {
            try {
                Class.forName("com.google.firebase.messaging.FirebaseMessaging");
                return true;
            } catch (ClassNotFoundException e) {
                Context applicationContext = this.zzbd.zzam.getApplicationContext();
                Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                intent.setPackage(applicationContext.getPackageName());
                ResolveInfo resolveService = applicationContext.getPackageManager().resolveService(intent, 0);
                if (resolveService == null || resolveService.serviceInfo == null) {
                    return false;
                }
                return true;
            }
        }
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
    }

    FirebaseInstanceId(FirebaseApp firebaseApp, Subscriber subscriber) {
        this(firebaseApp, new zzan(firebaseApp.getApplicationContext()), zzi.zzf(), zzi.zzf(), subscriber);
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzan zzan, Executor executor, Executor executor2, Subscriber subscriber) {
        this.zzar = false;
        if (zzan.zza(firebaseApp) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (zzaj == null) {
                zzaj = new zzaw(firebaseApp.getApplicationContext());
            }
        }
        this.zzam = firebaseApp;
        this.zzan = zzan;
        if (this.zzao == null) {
            MessagingChannel messagingChannel = (MessagingChannel) firebaseApp.get(MessagingChannel.class);
            if (messagingChannel == null || !messagingChannel.isAvailable()) {
                this.zzao = new zzr(firebaseApp, zzan, executor);
            } else {
                this.zzao = messagingChannel;
            }
        }
        this.zzao = this.zzao;
        this.zzal = executor2;
        this.zzaq = new zzba(zzaj);
        this.zzas = new zza(this, subscriber);
        this.zzap = new zzaq(executor);
        if (this.zzas.isEnabled()) {
            zzg();
        }
    }

    private final void zzg() {
        zzax zzj = zzj();
        if (!zzo() || zzj == null || zzj.zzj(this.zzan.zzad()) || this.zzaq.zzaq()) {
            startSync();
        }
    }

    final FirebaseApp zzh() {
        return this.zzam;
    }

    final synchronized void zza(boolean z) {
        this.zzar = z;
    }

    private final synchronized void startSync() {
        if (!this.zzar) {
            zza(0);
        }
    }

    final synchronized void zza(long j) {
        zza(new zzay(this, this.zzan, this.zzaq, Math.min(Math.max(30, j << 1), zzai)), j);
        this.zzar = true;
    }

    static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzak == null) {
                zzak = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            zzak.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    @WorkerThread
    public String getId() {
        zzg();
        return zzi();
    }

    private static String zzi() {
        return zzan.zza(zzaj.zzg("").getKeyPair());
    }

    public long getCreationTime() {
        return zzaj.zzg("").getCreationTime();
    }

    @NonNull
    public Task<InstanceIdResult> getInstanceId() {
        return zza(zzan.zza(this.zzam), "*");
    }

    private final Task<InstanceIdResult> zza(String str, String str2) {
        String zzd = zzd(str2);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzal.execute(new zzn(this, str, str2, taskCompletionSource, zzd));
        return taskCompletionSource.getTask();
    }

    @WorkerThread
    public void deleteInstanceId() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zza(this.zzao.deleteInstanceId(zzi()));
        zzm();
    }

    @Nullable
    @Deprecated
    public String getToken() {
        zzax zzj = zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            startSync();
        }
        return zzj != null ? zzj.zzbq : null;
    }

    @Nullable
    final zzax zzj() {
        return zzb(zzan.zza(this.zzam), "*");
    }

    @Nullable
    @VisibleForTesting
    private static zzax zzb(String str, String str2) {
        return zzaj.zzb("", str, str2);
    }

    final String zzk() throws IOException {
        return getToken(zzan.zza(this.zzam), "*");
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((InstanceIdResult) zza(zza(str, str2))).getToken();
        }
        throw new IOException("MAIN_THREAD");
    }

    private final <T> T zza(Task<T> task) throws IOException {
        try {
            return Tasks.await(task, 30000, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            Throwable th = e;
            Throwable e2 = th.getCause();
            if (e2 instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(e2.getMessage())) {
                    zzm();
                }
                throw ((IOException) e2);
            } else if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else {
                throw new IOException(th);
            }
        } catch (InterruptedException e3) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (TimeoutException e4) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String zzd = zzd(str2);
        zza(this.zzao.deleteToken(zzi(), zzax.zza(zzb(str, zzd)), str, zzd));
        zzaj.zzc("", str, zzd);
    }

    public final synchronized Task<Void> zza(String str) {
        Task<Void> zza;
        zza = this.zzaq.zza(str);
        startSync();
        return zza;
    }

    final void zzb(String str) throws IOException {
        zzax zzj = zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            throw new IOException("token not available");
        }
        zza(this.zzao.subscribeToTopic(zzi(), zzj.zzbq, str));
    }

    final void zzc(String str) throws IOException {
        zzax zzj = zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            throw new IOException("token not available");
        }
        zza(this.zzao.unsubscribeFromTopic(zzi(), zzj.zzbq, str));
    }

    static boolean zzl() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }

    final synchronized void zzm() {
        zzaj.zzal();
        if (this.zzas.isEnabled()) {
            startSync();
        }
    }

    final boolean zzn() {
        return this.zzao.isAvailable();
    }

    final boolean zzo() {
        return this.zzao.isChannelBuilt();
    }

    final void zzp() throws IOException {
        zza(this.zzao.buildChannel(zzi(), zzax.zza(zzj())));
    }

    final void zzq() {
        zzaj.zzh("");
        startSync();
    }

    @VisibleForTesting
    public final boolean zzr() {
        return this.zzas.isEnabled();
    }

    @VisibleForTesting
    public final void zzb(boolean z) {
        this.zzas.setEnabled(z);
    }

    private static String zzd(String str) {
        if (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) {
            return "*";
        }
        return str;
    }

    final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        String zzi = zzi();
        zzax zzb = zzb(str, str2);
        if (zzb == null || zzb.zzj(this.zzan.zzad())) {
            this.zzap.zza(str, str3, new zzo(this, zzi, zzax.zza(zzb), str, str3)).addOnCompleteListener(this.zzal, new zzp(this, str, str3, taskCompletionSource, zzi));
            return;
        }
        taskCompletionSource.setResult(new zzx(zzi, zzb.zzbq));
    }

    final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3, Task task) {
        if (task.isSuccessful()) {
            String str4 = (String) task.getResult();
            zzaj.zza("", str, str2, str4, this.zzan.zzad());
            taskCompletionSource.setResult(new zzx(str3, str4));
            return;
        }
        taskCompletionSource.setException(task.getException());
    }

    final /* synthetic */ Task zza(String str, String str2, String str3, String str4) {
        return this.zzao.getToken(str, str2, str3, str4);
    }
}
