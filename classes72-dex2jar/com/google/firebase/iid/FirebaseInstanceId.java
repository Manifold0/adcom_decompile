// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.content.SharedPreferences$Editor;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.events.EventHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import android.support.annotation.WorkerThread;
import android.os.Looper;
import android.os.Build$VERSION;
import android.util.Log;
import android.support.annotation.Nullable;
import java.util.concurrent.ThreadFactory;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.io.IOException;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import com.google.firebase.events.Subscriber;
import java.util.concurrent.TimeUnit;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class FirebaseInstanceId
{
    private static final long zzai;
    private static zzaw zzaj;
    @VisibleForTesting
    @GuardedBy("FirebaseInstanceId.class")
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
    
    static {
        zzai = TimeUnit.HOURS.toSeconds(8L);
    }
    
    FirebaseInstanceId(final FirebaseApp firebaseApp, final Subscriber subscriber) {
        this(firebaseApp, new zzan(firebaseApp.getApplicationContext()), zzi.zzf(), zzi.zzf(), subscriber);
    }
    
    private FirebaseInstanceId(final FirebaseApp zzam, final zzan zzan, final Executor executor, final Executor zzal, final Subscriber subscriber) {
        this.zzar = false;
        if (com.google.firebase.iid.zzan.zza(zzam) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        while (true) {
            while (true) {
                Label_0176: {
                    synchronized (FirebaseInstanceId.class) {
                        if (FirebaseInstanceId.zzaj == null) {
                            FirebaseInstanceId.zzaj = new zzaw(zzam.getApplicationContext());
                        }
                        // monitorexit(FirebaseInstanceId.class)
                        this.zzam = zzam;
                        this.zzan = zzan;
                        if (this.zzao == null) {
                            final MessagingChannel zzao = (MessagingChannel)zzam.get((Class)MessagingChannel.class);
                            if (zzao == null || !zzao.isAvailable()) {
                                break Label_0176;
                            }
                            this.zzao = zzao;
                        }
                        this.zzao = this.zzao;
                        this.zzal = zzal;
                        this.zzaq = new zzba(FirebaseInstanceId.zzaj);
                        this.zzas = new zza(subscriber);
                        this.zzap = new zzaq(executor);
                        if (this.zzas.isEnabled()) {
                            this.zzg();
                        }
                        return;
                    }
                }
                final FirebaseApp firebaseApp;
                this.zzao = new zzr(firebaseApp, zzan, executor);
                continue;
            }
        }
    }
    
    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }
    
    @Keep
    public static FirebaseInstanceId getInstance(@NonNull final FirebaseApp firebaseApp) {
        return (FirebaseInstanceId)firebaseApp.get((Class)FirebaseInstanceId.class);
    }
    
    private final void startSync() {
        synchronized (this) {
            if (!this.zzar) {
                this.zza(0L);
            }
        }
    }
    
    private final Task<InstanceIdResult> zza(final String s, final String s2) {
        final String zzd = zzd(s2);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzal.execute(new zzn(this, s, s2, taskCompletionSource, zzd));
        return (Task<InstanceIdResult>)taskCompletionSource.getTask();
    }
    
    private final <T> T zza(final Task<T> task) throws IOException {
        try {
            return (T)Tasks.await((Task)task, 30000L, TimeUnit.MILLISECONDS);
        }
        catch (ExecutionException ex) {
            final Throwable cause = ex.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    this.zzm();
                }
                throw (IOException)cause;
            }
            if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
            }
            throw new IOException(ex);
        }
        catch (InterruptedException ex2) {}
        catch (TimeoutException ex3) {
            goto Label_0069;
        }
    }
    
    static void zza(final Runnable runnable, final long n) {
        synchronized (FirebaseInstanceId.class) {
            if (FirebaseInstanceId.zzak == null) {
                FirebaseInstanceId.zzak = new ScheduledThreadPoolExecutor(1, (ThreadFactory)new NamedThreadFactory("FirebaseInstanceId"));
            }
            FirebaseInstanceId.zzak.schedule(runnable, n, TimeUnit.SECONDS);
        }
    }
    
    @Nullable
    @VisibleForTesting
    private static zzax zzb(final String s, final String s2) {
        return FirebaseInstanceId.zzaj.zzb("", s, s2);
    }
    
    private static String zzd(final String s) {
        if (!s.isEmpty() && !s.equalsIgnoreCase("fcm")) {
            final String s2 = s;
            if (!s.equalsIgnoreCase("gcm")) {
                return s2;
            }
        }
        return "*";
    }
    
    private final void zzg() {
        final zzax zzj = this.zzj();
        if (!this.zzo() || zzj == null || zzj.zzj(this.zzan.zzad()) || this.zzaq.zzaq()) {
            this.startSync();
        }
    }
    
    private static String zzi() {
        return zzan.zza(FirebaseInstanceId.zzaj.zzg("").getKeyPair());
    }
    
    static boolean zzl() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (Build$VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }
    
    @WorkerThread
    public void deleteInstanceId() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        this.zza(this.zzao.deleteInstanceId(zzi()));
        this.zzm();
    }
    
    @WorkerThread
    public void deleteToken(final String s, String zzd) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zzd = zzd(zzd);
        this.zza(this.zzao.deleteToken(zzi(), zzax.zza(zzb(s, zzd)), s, zzd));
        FirebaseInstanceId.zzaj.zzc("", s, zzd);
    }
    
    public long getCreationTime() {
        return FirebaseInstanceId.zzaj.zzg("").getCreationTime();
    }
    
    @WorkerThread
    public String getId() {
        this.zzg();
        return zzi();
    }
    
    @NonNull
    public Task<InstanceIdResult> getInstanceId() {
        return this.zza(com.google.firebase.iid.zzan.zza(this.zzam), "*");
    }
    
    @Deprecated
    @Nullable
    public String getToken() {
        final zzax zzj = this.zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            this.startSync();
        }
        if (zzj != null) {
            return zzj.zzbq;
        }
        return null;
    }
    
    @WorkerThread
    public String getToken(final String s, final String s2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        return this.zza(this.zza(s, s2)).getToken();
    }
    
    public final Task<Void> zza(final String s) {
        synchronized (this) {
            final Task<Void> zza = this.zzaq.zza(s);
            this.startSync();
            return zza;
        }
    }
    
    final void zza(final long n) {
        synchronized (this) {
            zza(new zzay(this, this.zzan, this.zzaq, Math.min(Math.max(30L, n << 1), FirebaseInstanceId.zzai)), n);
            this.zzar = true;
        }
    }
    
    final void zza(final boolean zzar) {
        synchronized (this) {
            this.zzar = zzar;
        }
    }
    
    final void zzb(final String s) throws IOException {
        final zzax zzj = this.zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            throw new IOException("token not available");
        }
        this.zza(this.zzao.subscribeToTopic(zzi(), zzj.zzbq, s));
    }
    
    @VisibleForTesting
    public final void zzb(final boolean enabled) {
        this.zzas.setEnabled(enabled);
    }
    
    final void zzc(final String s) throws IOException {
        final zzax zzj = this.zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            throw new IOException("token not available");
        }
        this.zza(this.zzao.unsubscribeFromTopic(zzi(), zzj.zzbq, s));
    }
    
    final FirebaseApp zzh() {
        return this.zzam;
    }
    
    @Nullable
    final zzax zzj() {
        return zzb(com.google.firebase.iid.zzan.zza(this.zzam), "*");
    }
    
    final String zzk() throws IOException {
        return this.getToken(com.google.firebase.iid.zzan.zza(this.zzam), "*");
    }
    
    final void zzm() {
        synchronized (this) {
            FirebaseInstanceId.zzaj.zzal();
            if (this.zzas.isEnabled()) {
                this.startSync();
            }
        }
    }
    
    final boolean zzn() {
        return this.zzao.isAvailable();
    }
    
    final boolean zzo() {
        return this.zzao.isChannelBuilt();
    }
    
    final void zzp() throws IOException {
        this.zza(this.zzao.buildChannel(zzi(), zzax.zza(this.zzj())));
    }
    
    final void zzq() {
        FirebaseInstanceId.zzaj.zzh("");
        this.startSync();
    }
    
    @VisibleForTesting
    public final boolean zzr() {
        return this.zzas.isEnabled();
    }
    
    private final class zza
    {
        private final boolean zzaz;
        private final Subscriber zzba;
        @Nullable
        @GuardedBy("this")
        private EventHandler<DataCollectionDefaultChange> zzbb;
        @Nullable
        @GuardedBy("this")
        private Boolean zzbc;
        
        zza(final Subscriber zzba) {
            this.zzba = zzba;
            this.zzaz = this.zzu();
            this.zzbc = this.zzt();
            if (this.zzbc == null && this.zzaz) {
                zzba.subscribe((Class)DataCollectionDefaultChange.class, (EventHandler)(this.zzbb = (EventHandler<DataCollectionDefaultChange>)new zzq(this)));
            }
        }
        
        @Nullable
        private final Boolean zzt() {
            final Context applicationContext = FirebaseInstanceId.this.zzam.getApplicationContext();
            final SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return sharedPreferences.getBoolean("auto_init", false);
            }
            try {
                final PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null) {
                    final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128);
                    if (applicationInfo != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
                        return applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled");
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex) {}
            return null;
        }
        
        private final boolean zzu() {
            try {
                Class.forName("com.google.firebase.messaging.FirebaseMessaging");
                return true;
            }
            catch (ClassNotFoundException ex) {
                final Context applicationContext = FirebaseInstanceId.this.zzam.getApplicationContext();
                final Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                intent.setPackage(applicationContext.getPackageName());
                final ResolveInfo resolveService = applicationContext.getPackageManager().resolveService(intent, 0);
                if (resolveService == null || resolveService.serviceInfo == null) {
                    return false;
                }
                return true;
            }
        }
        
        final boolean isEnabled() {
            synchronized (this) {
                boolean booleanValue;
                if (this.zzbc != null) {
                    booleanValue = this.zzbc;
                }
                else {
                    booleanValue = (this.zzaz && FirebaseInstanceId.this.zzam.isDataCollectionDefaultEnabled());
                }
                return booleanValue;
            }
        }
        
        final void setEnabled(final boolean b) {
            synchronized (this) {
                if (this.zzbb != null) {
                    this.zzba.unsubscribe((Class)DataCollectionDefaultChange.class, (EventHandler)this.zzbb);
                    this.zzbb = null;
                }
                final SharedPreferences$Editor edit = FirebaseInstanceId.this.zzam.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
                edit.putBoolean("auto_init", b);
                edit.apply();
                if (b) {
                    FirebaseInstanceId.this.zzg();
                }
                this.zzbc = b;
            }
        }
    }
}
