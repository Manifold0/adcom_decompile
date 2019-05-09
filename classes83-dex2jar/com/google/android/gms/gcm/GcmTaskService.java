// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.os.RemoteException;
import android.os.IInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzx;
import android.os.Message;
import android.annotation.TargetApi;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.Parcelable;
import android.net.Uri;
import java.util.List;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import android.content.Context;
import android.support.annotation.CallSuper;
import com.google.android.gms.common.util.zzq;
import android.os.IBinder;
import android.content.Intent;
import java.util.concurrent.RejectedExecutionException;
import android.util.Log;
import android.os.Messenger;
import java.util.concurrent.ExecutorService;
import android.content.ComponentName;
import android.app.Service;

public abstract class GcmTaskService extends Service
{
    public static final String SERVICE_ACTION_EXECUTE_TASK = "com.google.android.gms.gcm.ACTION_TASK_READY";
    public static final String SERVICE_ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    public static final String SERVICE_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
    private ComponentName componentName;
    private final Object lock;
    private ExecutorService zzaih;
    private int zzhzj;
    private Messenger zzhzk;
    private GcmNetworkManager zzhzl;
    
    public GcmTaskService() {
        this.lock = new Object();
    }
    
    private final void zza(final zzb zzb) {
        try {
            this.zzaih.execute(zzb);
        }
        catch (RejectedExecutionException ex) {
            Log.e("GcmTaskService", "Executor is shutdown. onDestroy was called but main looper had an unprocessed start task message. The task will be retried with backoff delay.", (Throwable)ex);
            GcmTaskService.zzb.zza(zzb, 1);
        }
    }
    
    private final void zzdp(final int zzhzj) {
        synchronized (this.lock) {
            this.zzhzj = zzhzj;
            if (!this.zzhzl.zzhr(this.componentName.getClassName())) {
                this.stopSelf(this.zzhzj);
            }
        }
    }
    
    private final boolean zzhs(final String s) {
        while (true) {
            while (true) {
                synchronized (this.lock) {
                    if (!this.zzhzl.zzaa(s, this.componentName.getClassName())) {
                        final boolean b = true;
                        if (b) {
                            final String packageName = this.getPackageName();
                            Log.w("GcmTaskService", new StringBuilder(String.valueOf(packageName).length() + 44 + String.valueOf(s).length()).append(packageName).append(" ").append(s).append(": Task already running, won't start another").toString());
                        }
                        return b;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    @CallSuper
    public IBinder onBind(final Intent intent) {
        if (intent == null || !zzq.zzamb() || !"com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return null;
        }
        return this.zzhzk.getBinder();
    }
    
    @CallSuper
    public void onCreate() {
        super.onCreate();
        this.zzhzl = GcmNetworkManager.getInstance((Context)this);
        this.zzaih = Executors.newFixedThreadPool(2, new com.google.android.gms.gcm.zzb(this));
        this.zzhzk = new Messenger((Handler)new zza(Looper.getMainLooper()));
        this.componentName = new ComponentName((Context)this, (Class)this.getClass());
    }
    
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        final List<Runnable> shutdownNow = this.zzaih.shutdownNow();
        if (!shutdownNow.isEmpty()) {
            Log.e("GcmTaskService", new StringBuilder(79).append("Shutting down, but not all tasks are finished executing. Remaining: ").append(shutdownNow.size()).toString());
        }
    }
    
    public void onInitializeTasks() {
    }
    
    public abstract int onRunTask(final TaskParams p0);
    
    @CallSuper
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        if (intent == null) {
            this.zzdp(n2);
            return 2;
        }
        while (true) {
            String s = null;
            Label_0221: {
                try {
                    intent.setExtrasClassLoader(PendingCallback.class.getClassLoader());
                    s = intent.getAction();
                    if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(s)) {
                        s = intent.getStringExtra("tag");
                        final Parcelable parcelableExtra = intent.getParcelableExtra("callback");
                        final Bundle bundleExtra = intent.getBundleExtra("extras");
                        final ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("triggered_uris");
                        if (!(parcelableExtra instanceof PendingCallback)) {
                            final String packageName = this.getPackageName();
                            Log.e("GcmTaskService", new StringBuilder(String.valueOf(packageName).length() + 47 + String.valueOf(s).length()).append(packageName).append(" ").append(s).append(": Could not process request, invalid callback.").toString());
                            return 2;
                        }
                        if (this.zzhs(s)) {
                            return 2;
                        }
                        this.zza(new zzb(s, ((PendingCallback)parcelableExtra).zzfwl, bundleExtra, parcelableArrayListExtra));
                    }
                    else {
                        if (!"com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(s)) {
                            break Label_0221;
                        }
                        this.onInitializeTasks();
                    }
                    return 2;
                }
                finally {
                    this.zzdp(n2);
                }
            }
            Log.e("GcmTaskService", new StringBuilder(String.valueOf(s).length() + 37).append("Unknown action received ").append(s).append(", terminating").toString());
            return 2;
        }
    }
    
    @TargetApi(21)
    final class zza extends Handler
    {
        zza(final Looper looper) {
            super(looper);
        }
        
        public final void handleMessage(final Message message) {
            if (!zzx.zzb((Context)GcmTaskService.this, message.sendingUid, "com.google.android.gms")) {
                Log.e("GcmTaskService", "unable to verify presence of Google Play Services");
            }
            else {
                switch (message.what) {
                    default: {
                        final String value = String.valueOf(message);
                        Log.e("GcmTaskService", new StringBuilder(String.valueOf(value).length() + 31).append("Unrecognized message received: ").append(value).toString());
                    }
                    case 1: {
                        final Bundle data = message.getData();
                        if (data.isEmpty()) {
                            break;
                        }
                        final Messenger replyTo = message.replyTo;
                        if (replyTo == null) {
                            break;
                        }
                        final String string = data.getString("tag");
                        final ArrayList parcelableArrayList = data.getParcelableArrayList("triggered_uris");
                        if (!GcmTaskService.this.zzhs(string)) {
                            GcmTaskService.this.zza(new zzb(string, replyTo, data.getBundle("extras"), parcelableArrayList));
                            return;
                        }
                        break;
                    }
                    case 2: {
                        if (Log.isLoggable("GcmTaskService", 3)) {
                            final String value2 = String.valueOf(message);
                            Log.d("GcmTaskService", new StringBuilder(String.valueOf(value2).length() + 45).append("ignoring unimplemented stop message for now: ").append(value2).toString());
                            return;
                        }
                        break;
                    }
                    case 4: {
                        GcmTaskService.this.onInitializeTasks();
                    }
                }
            }
        }
    }
    
    final class zzb implements Runnable
    {
        private final Bundle mExtras;
        @Nullable
        private final Messenger mMessenger;
        private final String mTag;
        private final List<Uri> zzhzo;
        @Nullable
        private final zzd zzhzp;
        
        zzb(@NonNull final String mTag, final IBinder binder, final Bundle mExtras, final List<Uri> zzhzo) {
            this.mTag = mTag;
            zzd zzhzp;
            if (binder == null) {
                zzhzp = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.gcm.INetworkTaskCallback");
                if (queryLocalInterface instanceof zzd) {
                    zzhzp = (zzd)queryLocalInterface;
                }
                else {
                    zzhzp = new zze(binder);
                }
            }
            this.zzhzp = zzhzp;
            this.mExtras = mExtras;
            this.zzhzo = zzhzo;
            this.mMessenger = null;
        }
        
        zzb(@NonNull final String mTag, final Messenger mMessenger, final Bundle mExtras, final List<Uri> zzhzo) {
            this.mTag = mTag;
            this.mMessenger = mMessenger;
            this.mExtras = mExtras;
            this.zzhzo = zzhzo;
            this.zzhzp = null;
        }
        
        static /* synthetic */ void zza(final zzb zzb, final int n) {
            zzb.zzdq(1);
        }
        
        private final boolean zzauj() {
            return this.mMessenger != null;
        }
        
        private final void zzdq(final int arg1) {
            final Object zza = GcmTaskService.this.lock;
            // monitorenter(zza)
            try {
                Label_0113: {
                    if (!GcmTaskService.this.zzhzl.zzac(this.mTag, GcmTaskService.this.componentName.getClassName())) {
                        break Label_0113;
                    }
                    while (true) {
                    Block_7_Outer:
                        while (true) {
                            try {
                                GcmTaskService.this.zzhzl.zzab(this.mTag, GcmTaskService.this.componentName.getClassName());
                                if (!this.zzauj() && !GcmTaskService.this.zzhzl.zzhr(GcmTaskService.this.componentName.getClassName())) {
                                    GcmTaskService.this.stopSelf(GcmTaskService.this.zzhzj);
                                }
                                return;
                                GcmTaskService.this.zzhzl.zzab(this.mTag, GcmTaskService.this.componentName.getClassName());
                                // iftrue(Label_0257:, this.zzauj() || GcmTaskService.zzc(this.zzhzn).zzhr(GcmTaskService.zzb(this.zzhzn).getClassName()))
                                // iftrue(Label_0267:, !this.zzauj())
                                Block_9: {
                                    break Block_9;
                                    while (true) {
                                        final Messenger mMessenger = this.mMessenger;
                                        final Message obtain = Message.obtain();
                                        obtain.what = 3;
                                        obtain.arg1 = arg1;
                                        final Bundle data = new Bundle();
                                        data.putParcelable("component", (Parcelable)GcmTaskService.this.componentName);
                                        data.putString("tag", this.mTag);
                                        obtain.setData(data);
                                        mMessenger.send(obtain);
                                        continue Block_7_Outer;
                                        continue;
                                    }
                                    Label_0257: {
                                        return;
                                    }
                                }
                                GcmTaskService.this.stopSelf(GcmTaskService.this.zzhzj);
                                return;
                            }
                            finally {
                            }
                            // monitorexit(zza)
                            Label_0267: {
                                this.zzhzp.zzdr(arg1);
                            }
                            continue;
                        }
                    }
                }
            }
            catch (RemoteException ex) {
                final String value = String.valueOf(this.mTag);
                String concat;
                if (value.length() != 0) {
                    concat = "Error reporting result of operation to scheduler for ".concat(value);
                }
                else {
                    concat = new String("Error reporting result of operation to scheduler for ");
                }
                Log.e("GcmTaskService", concat);
            }
            finally {
                GcmTaskService.this.zzhzl.zzab(this.mTag, GcmTaskService.this.componentName.getClassName());
                if (!this.zzauj() && !GcmTaskService.this.zzhzl.zzhr(GcmTaskService.this.componentName.getClassName())) {
                    GcmTaskService.this.stopSelf(GcmTaskService.this.zzhzj);
                }
            }
        }
        
        @Override
        public final void run() {
            this.zzdq(GcmTaskService.this.onRunTask(new TaskParams(this.mTag, this.mExtras, this.zzhzo)));
        }
    }
}
