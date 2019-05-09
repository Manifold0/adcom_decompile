// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.os.RemoteException;
import com.google.android.gms.internal.drive.zzet;
import android.os.Message;
import java.lang.ref.WeakReference;
import android.os.Handler;
import java.util.concurrent.TimeUnit;
import android.os.IBinder;
import android.content.Intent;
import android.os.Binder;
import android.content.Context;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.drive.zzfj;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.internal.GmsLogger;
import android.app.Service;

public class DriveEventService extends Service implements ChangeListener, CompletionListener, zzd, zzi
{
    public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
    private static final GmsLogger zzbx;
    private final String name;
    @GuardedBy("this")
    private CountDownLatch zzch;
    @VisibleForTesting
    @GuardedBy("this")
    zza zzci;
    @GuardedBy("this")
    boolean zzcj;
    @VisibleForTesting
    private int zzck;
    
    static {
        zzbx = new GmsLogger("DriveEventService", "");
    }
    
    protected DriveEventService() {
        this("DriveEventService");
    }
    
    protected DriveEventService(final String name) {
        this.zzcj = false;
        this.zzck = -1;
        this.name = name;
    }
    
    private final void zza(final zzfj zzfj) {
        while (true) {
            final DriveEvent zzak = zzfj.zzak();
            Label_0125: {
                Label_0116: {
                Label_0107:
                    while (true) {
                        Label_0156: {
                            try {
                                switch (zzak.getType()) {
                                    case 3:
                                    case 5:
                                    case 6: {
                                        DriveEventService.zzbx.wfmt("DriveEventService", "Unhandled event: %s", new Object[] { zzak });
                                        return;
                                    }
                                    case 1: {
                                        this.onChange((ChangeEvent)zzak);
                                        return;
                                    }
                                    case 2: {
                                        break;
                                    }
                                    case 4: {
                                        break Label_0116;
                                    }
                                    case 7: {
                                        break Label_0125;
                                    }
                                    default: {
                                        break Label_0156;
                                    }
                                }
                            }
                            catch (Exception ex) {
                                DriveEventService.zzbx.e("DriveEventService", String.format("Error handling event in %s", this.name), (Throwable)ex);
                                return;
                            }
                            break Label_0107;
                        }
                        continue;
                    }
                    this.onCompletion((CompletionEvent)zzak);
                    return;
                }
                this.zza((com.google.android.gms.drive.events.zzb)zzak);
                return;
            }
            DriveEventService.zzbx.wfmt("DriveEventService", "Unhandled transfer state event in %s: %s", new Object[] { this.name, (zzv)zzak });
        }
    }
    
    private final void zzv() throws SecurityException {
        final int callingUid = this.getCallingUid();
        if (callingUid == this.zzck) {
            return;
        }
        if (UidVerifier.isGooglePlayServicesUid((Context)this, callingUid)) {
            this.zzck = callingUid;
            return;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
    
    @VisibleForTesting
    protected int getCallingUid() {
        return Binder.getCallingUid();
    }
    
    public final IBinder onBind(final Intent intent) {
        IBinder binder = null;
        synchronized (this) {
            if (!"com.google.android.gms.drive.events.HANDLE_EVENT".equals(intent.getAction())) {
                return binder;
            }
            Label_0091: {
                if (this.zzci != null || this.zzcj) {
                    break Label_0091;
                }
                this.zzcj = true;
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                this.zzch = new CountDownLatch(1);
                new zzh(this, countDownLatch).start();
                try {
                    if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                        DriveEventService.zzbx.e("DriveEventService", "Failed to synchronously initialize event handler.");
                    }
                    binder = new zzb((zzh)null).asBinder();
                    return binder;
                }
                catch (InterruptedException ex) {
                    throw new RuntimeException("Unable to start event handler", ex);
                }
            }
        }
    }
    
    public void onChange(final ChangeEvent changeEvent) {
        DriveEventService.zzbx.wfmt("DriveEventService", "Unhandled change event in %s: %s", new Object[] { this.name, changeEvent });
    }
    
    public void onCompletion(final CompletionEvent completionEvent) {
        DriveEventService.zzbx.wfmt("DriveEventService", "Unhandled completion event in %s: %s", new Object[] { this.name, completionEvent });
    }
    
    public void onDestroy() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/drive/events/DriveEventService.zzci:Lcom/google/android/gms/drive/events/DriveEventService$zza;
        //     6: ifnull          62
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/drive/events/DriveEventService.zzci:Lcom/google/android/gms/drive/events/DriveEventService$zza;
        //    13: invokestatic    com/google/android/gms/drive/events/DriveEventService$zza.zza:(Lcom/google/android/gms/drive/events/DriveEventService$zza;)Landroid/os/Message;
        //    16: astore_1       
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/drive/events/DriveEventService.zzci:Lcom/google/android/gms/drive/events/DriveEventService$zza;
        //    21: aload_1        
        //    22: invokevirtual   com/google/android/gms/drive/events/DriveEventService$zza.sendMessage:(Landroid/os/Message;)Z
        //    25: pop            
        //    26: aload_0        
        //    27: aconst_null    
        //    28: putfield        com/google/android/gms/drive/events/DriveEventService.zzci:Lcom/google/android/gms/drive/events/DriveEventService$zza;
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/drive/events/DriveEventService.zzch:Ljava/util/concurrent/CountDownLatch;
        //    35: ldc2_w          5000
        //    38: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //    41: invokevirtual   java/util/concurrent/CountDownLatch.await:(JLjava/util/concurrent/TimeUnit;)Z
        //    44: ifne            57
        //    47: getstatic       com/google/android/gms/drive/events/DriveEventService.zzbx:Lcom/google/android/gms/common/internal/GmsLogger;
        //    50: ldc             "DriveEventService"
        //    52: ldc             "Failed to synchronously quit event handler. Will quit itself"
        //    54: invokevirtual   com/google/android/gms/common/internal/GmsLogger.w:(Ljava/lang/String;Ljava/lang/String;)V
        //    57: aload_0        
        //    58: aconst_null    
        //    59: putfield        com/google/android/gms/drive/events/DriveEventService.zzch:Ljava/util/concurrent/CountDownLatch;
        //    62: aload_0        
        //    63: invokespecial   android/app/Service.onDestroy:()V
        //    66: aload_0        
        //    67: monitorexit    
        //    68: return         
        //    69: astore_1       
        //    70: aload_0        
        //    71: monitorexit    
        //    72: aload_1        
        //    73: athrow         
        //    74: astore_1       
        //    75: goto            57
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  2      31     69     74     Any
        //  31     57     74     78     Ljava/lang/InterruptedException;
        //  31     57     69     74     Any
        //  57     62     69     74     Any
        //  62     66     69     74     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0057:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean onUnbind(final Intent intent) {
        return true;
    }
    
    public final void zza(final com.google.android.gms.drive.events.zzb zzb) {
        DriveEventService.zzbx.wfmt("DriveEventService", "Unhandled changes available event in %s: %s", new Object[] { this.name, zzb });
    }
    
    static final class zza extends Handler
    {
        private final WeakReference<DriveEventService> zzcn;
        
        private zza(final DriveEventService driveEventService) {
            this.zzcn = new WeakReference<DriveEventService>(driveEventService);
        }
        
        private final Message zzb(final zzfj zzfj) {
            return this.obtainMessage(1, (Object)zzfj);
        }
        
        private final Message zzx() {
            return this.obtainMessage(2);
        }
        
        public final void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    DriveEventService.zzbx.wfmt("DriveEventService", "Unexpected message type: %s", new Object[] { message.what });
                }
                case 1: {
                    final DriveEventService driveEventService = this.zzcn.get();
                    if (driveEventService != null) {
                        driveEventService.zza((zzfj)message.obj);
                        return;
                    }
                    this.getLooper().quit();
                }
                case 2: {
                    this.getLooper().quit();
                }
            }
        }
    }
    
    @VisibleForTesting
    final class zzb extends zzet
    {
        private zzb() {
        }
        
        @Override
        public final void zzc(final zzfj zzfj) throws RemoteException {
            synchronized (DriveEventService.this) {
                DriveEventService.this.zzv();
                if (DriveEventService.this.zzci != null) {
                    DriveEventService.this.zzci.sendMessage(DriveEventService.this.zzci.zzb(zzfj));
                }
                else {
                    DriveEventService.zzbx.e("DriveEventService", "Receiving event before initialize is completed.");
                }
            }
        }
    }
}
