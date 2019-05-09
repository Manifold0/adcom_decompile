package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.drive.zzet;
import com.google.android.gms.internal.drive.zzfj;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class DriveEventService extends Service implements ChangeListener, CompletionListener, zzd, zzi {
    public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
    private static final GmsLogger zzbx = new GmsLogger("DriveEventService", "");
    private final String name;
    @GuardedBy("this")
    private CountDownLatch zzch;
    @GuardedBy("this")
    @VisibleForTesting
    zza zzci;
    @GuardedBy("this")
    boolean zzcj;
    @VisibleForTesting
    private int zzck;

    static final class zza extends Handler {
        private final WeakReference<DriveEventService> zzcn;

        private zza(DriveEventService driveEventService) {
            this.zzcn = new WeakReference(driveEventService);
        }

        private final Message zzb(zzfj zzfj) {
            return obtainMessage(1, zzfj);
        }

        private final Message zzx() {
            return obtainMessage(2);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    DriveEventService driveEventService = (DriveEventService) this.zzcn.get();
                    if (driveEventService != null) {
                        driveEventService.zza((zzfj) message.obj);
                        return;
                    } else {
                        getLooper().quit();
                        return;
                    }
                case 2:
                    getLooper().quit();
                    return;
                default:
                    DriveEventService.zzbx.wfmt("DriveEventService", "Unexpected message type: %s", new Object[]{Integer.valueOf(message.what)});
                    return;
            }
        }
    }

    @VisibleForTesting
    final class zzb extends zzet {
        private final /* synthetic */ DriveEventService zzcm;

        private zzb(DriveEventService driveEventService) {
            this.zzcm = driveEventService;
        }

        public final void zzc(zzfj zzfj) throws RemoteException {
            synchronized (this.zzcm) {
                this.zzcm.zzv();
                if (this.zzcm.zzci != null) {
                    this.zzcm.zzci.sendMessage(this.zzcm.zzci.zzb(zzfj));
                } else {
                    DriveEventService.zzbx.e("DriveEventService", "Receiving event before initialize is completed.");
                }
            }
        }
    }

    protected DriveEventService() {
        this("DriveEventService");
    }

    protected DriveEventService(String str) {
        this.zzcj = false;
        this.zzck = -1;
        this.name = str;
    }

    private final void zza(zzfj zzfj) {
        DriveEvent zzak = zzfj.zzak();
        try {
            switch (zzak.getType()) {
                case 1:
                    onChange((ChangeEvent) zzak);
                    return;
                case 2:
                    onCompletion((CompletionEvent) zzak);
                    return;
                case 4:
                    zza((zzb) zzak);
                    return;
                case 7:
                    zzv zzv = (zzv) zzak;
                    zzbx.wfmt("DriveEventService", "Unhandled transfer state event in %s: %s", new Object[]{this.name, zzv});
                    return;
                default:
                    zzbx.wfmt("DriveEventService", "Unhandled event: %s", new Object[]{zzak});
                    return;
            }
        } catch (Throwable e) {
            zzbx.e("DriveEventService", String.format("Error handling event in %s", new Object[]{this.name}), e);
        }
        zzbx.e("DriveEventService", String.format("Error handling event in %s", new Object[]{this.name}), e);
    }

    private final void zzv() throws SecurityException {
        int callingUid = getCallingUid();
        if (callingUid != this.zzck) {
            if (UidVerifier.isGooglePlayServicesUid(this, callingUid)) {
                this.zzck = callingUid;
                return;
            }
            throw new SecurityException("Caller is not GooglePlayServices");
        }
    }

    @VisibleForTesting
    protected int getCallingUid() {
        return Binder.getCallingUid();
    }

    public final synchronized IBinder onBind(Intent intent) {
        IBinder iBinder = null;
        synchronized (this) {
            if (ACTION_HANDLE_EVENT.equals(intent.getAction())) {
                if (this.zzci == null && !this.zzcj) {
                    this.zzcj = true;
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    this.zzch = new CountDownLatch(1);
                    new zzh(this, countDownLatch).start();
                    try {
                        if (!countDownLatch.await(5000, TimeUnit.MILLISECONDS)) {
                            zzbx.e("DriveEventService", "Failed to synchronously initialize event handler.");
                        }
                    } catch (Throwable e) {
                        throw new RuntimeException("Unable to start event handler", e);
                    }
                }
                iBinder = new zzb().asBinder();
            }
        }
        return iBinder;
    }

    public void onChange(ChangeEvent changeEvent) {
        zzbx.wfmt("DriveEventService", "Unhandled change event in %s: %s", new Object[]{this.name, changeEvent});
    }

    public void onCompletion(CompletionEvent completionEvent) {
        zzbx.wfmt("DriveEventService", "Unhandled completion event in %s: %s", new Object[]{this.name, completionEvent});
    }

    public synchronized void onDestroy() {
        if (this.zzci != null) {
            this.zzci.sendMessage(this.zzci.zzx());
            this.zzci = null;
            try {
                if (!this.zzch.await(5000, TimeUnit.MILLISECONDS)) {
                    zzbx.w("DriveEventService", "Failed to synchronously quit event handler. Will quit itself");
                }
            } catch (InterruptedException e) {
            }
            this.zzch = null;
        }
        super.onDestroy();
    }

    public boolean onUnbind(Intent intent) {
        return true;
    }

    public final void zza(zzb zzb) {
        zzbx.wfmt("DriveEventService", "Unhandled changes available event in %s: %s", new Object[]{this.name, zzb});
    }
}
