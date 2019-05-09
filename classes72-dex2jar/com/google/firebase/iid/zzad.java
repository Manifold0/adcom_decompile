// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import java.util.concurrent.TimeUnit;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import android.os.Bundle;
import android.os.Message;
import java.util.Iterator;
import com.google.android.gms.common.stats.ConnectionTracker;
import android.util.Log;
import android.os.IBinder;
import android.content.ComponentName;
import java.util.ArrayDeque;
import android.os.Handler;
import android.os.Handler$Callback;
import com.google.android.gms.internal.firebase_messaging.zza;
import android.os.Looper;
import android.util.SparseArray;
import java.util.Queue;
import android.os.Messenger;
import javax.annotation.concurrent.GuardedBy;
import android.content.ServiceConnection;

final class zzad implements ServiceConnection
{
    @GuardedBy("this")
    int state;
    final Messenger zzbx;
    zzai zzby;
    @GuardedBy("this")
    final Queue<zzak<?>> zzbz;
    @GuardedBy("this")
    final SparseArray<zzak<?>> zzca;
    final /* synthetic */ zzab zzcb;
    
    private zzad(final zzab zzcb) {
        this.zzcb = zzcb;
        this.state = 0;
        this.zzbx = new Messenger((Handler)new zza(Looper.getMainLooper(), (Handler$Callback)new zzae(this)));
        this.zzbz = new ArrayDeque<zzak<?>>();
        this.zzca = (SparseArray<zzak<?>>)new SparseArray();
    }
    
    private final void zzy() {
        this.zzcb.zzbu.execute(new zzag(this));
    }
    
    public final void onServiceConnected(final ComponentName p0, final IBinder p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: ldc             "MessengerIpcClient"
        //     4: iconst_2       
        //     5: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //     8: ifeq            19
        //    11: ldc             "MessengerIpcClient"
        //    13: ldc             "Service connected"
        //    15: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    18: pop            
        //    19: aload_2        
        //    20: ifnonnull       33
        //    23: aload_0        
        //    24: iconst_0       
        //    25: ldc             "Null service connection"
        //    27: invokevirtual   com/google/firebase/iid/zzad.zza:(ILjava/lang/String;)V
        //    30: aload_0        
        //    31: monitorexit    
        //    32: return         
        //    33: aload_0        
        //    34: new             Lcom/google/firebase/iid/zzai;
        //    37: dup            
        //    38: aload_2        
        //    39: invokespecial   com/google/firebase/iid/zzai.<init>:(Landroid/os/IBinder;)V
        //    42: putfield        com/google/firebase/iid/zzad.zzby:Lcom/google/firebase/iid/zzai;
        //    45: aload_0        
        //    46: iconst_2       
        //    47: putfield        com/google/firebase/iid/zzad.state:I
        //    50: aload_0        
        //    51: invokespecial   com/google/firebase/iid/zzad.zzy:()V
        //    54: goto            30
        //    57: astore_1       
        //    58: aload_0        
        //    59: monitorexit    
        //    60: aload_1        
        //    61: athrow         
        //    62: astore_1       
        //    63: aload_0        
        //    64: iconst_0       
        //    65: aload_1        
        //    66: invokevirtual   android/os/RemoteException.getMessage:()Ljava/lang/String;
        //    69: invokevirtual   com/google/firebase/iid/zzad.zza:(ILjava/lang/String;)V
        //    72: goto            30
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  2      19     57     62     Any
        //  23     30     57     62     Any
        //  33     45     62     75     Landroid/os/RemoteException;
        //  33     45     57     62     Any
        //  45     54     57     62     Any
        //  63     72     57     62     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
    
    public final void onServiceDisconnected(final ComponentName componentName) {
        synchronized (this) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Service disconnected");
            }
            this.zza(2, "Service disconnected");
        }
    }
    
    final void zza(final int n) {
        synchronized (this) {
            final zzak zzak = (zzak)this.zzca.get(n);
            if (zzak != null) {
                Log.w("MessengerIpcClient", new StringBuilder(31).append("Timing out request: ").append(n).toString());
                this.zzca.remove(n);
                zzak.zza(new zzal(3, "Timed out waiting for response"));
                this.zzz();
            }
        }
    }
    
    final void zza(int i, final String s) {
    Label_0268:
        while (true) {
            Label_0271: {
            Label_0076_Outer:
                while (true) {
                    Label_0136: {
                        while (true) {
                        Label_0279:
                            while (true) {
                                Label_0115: {
                                    synchronized (this) {
                                        if (Log.isLoggable("MessengerIpcClient", 3)) {
                                            final String value = String.valueOf(s);
                                            if (value.length() == 0) {
                                                break Label_0115;
                                            }
                                            final String concat = "Disconnected: ".concat(value);
                                            Log.d("MessengerIpcClient", concat);
                                        }
                                        switch (this.state) {
                                            case 0: {
                                                throw new IllegalStateException();
                                            }
                                            case 1:
                                            case 2: {
                                                break Label_0136;
                                            }
                                            case 4: {
                                                break Label_0268;
                                            }
                                            case 3: {
                                                break Label_0271;
                                            }
                                            default: {
                                                break Label_0279;
                                            }
                                        }
                                        i = this.state;
                                        throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(i).toString());
                                    }
                                }
                                final String concat = new String("Disconnected: ");
                                continue Label_0076_Outer;
                            }
                            continue;
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 2)) {
                        Log.v("MessengerIpcClient", "Unbinding service");
                    }
                    this.state = 4;
                    ConnectionTracker.getInstance().unbindService(this.zzcb.zzx, (ServiceConnection)this);
                    final zzal zzal = new zzal(i, s);
                    final Iterator<zzak> iterator = (Iterator<zzak>)this.zzbz.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().zza(zzal);
                    }
                    this.zzbz.clear();
                    for (i = 0; i < this.zzca.size(); ++i) {
                        ((zzak)this.zzca.valueAt(i)).zza(zzal);
                    }
                    this.zzca.clear();
                    break;
                }
                return;
            }
            this.state = 4;
            continue Label_0268;
        }
    }
    // monitorexit(this)
    
    final boolean zza(final Message message) {
        final int arg1 = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", new StringBuilder(41).append("Received response to request: ").append(arg1).toString());
        }
        final zzak zzak;
        synchronized (this) {
            zzak = (zzak)this.zzca.get(arg1);
            if (zzak == null) {
                Log.w("MessengerIpcClient", new StringBuilder(50).append("Received response for unknown request: ").append(arg1).toString());
                return true;
            }
            this.zzca.remove(arg1);
            this.zzz();
            // monitorexit(this)
            if (message.getData().getBoolean("unsupported", false)) {
                zzak.zza(new zzal(4, "Not supported by GmsCore"));
                return true;
            }
        }
        final Bundle bundle;
        zzak.zzb(bundle);
        return true;
    }
    
    final void zzaa() {
        synchronized (this) {
            if (this.state == 1) {
                this.zza(1, "Timed out while binding");
            }
        }
    }
    
    final boolean zzb(final zzak zzak) {
        boolean b3;
        while (true) {
            boolean b = false;
            final boolean b2 = true;
            Label_0254: {
                Label_0233: {
                    Label_0216: {
                        Label_0083: {
                            while (true) {
                                Label_0259: {
                                    synchronized (this) {
                                        switch (this.state) {
                                            case 0: {
                                                break Label_0083;
                                            }
                                            case 1: {
                                                break Label_0216;
                                            }
                                            case 2: {
                                                break Label_0233;
                                            }
                                            case 3:
                                            case 4: {
                                                break Label_0254;
                                            }
                                            default: {
                                                break Label_0259;
                                            }
                                        }
                                        throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.state).toString());
                                    }
                                    break Label_0083;
                                }
                                continue;
                            }
                        }
                        this.zzbz.add(zzak);
                        if (this.state == 0) {
                            b = true;
                        }
                        Preconditions.checkState(b);
                        if (Log.isLoggable("MessengerIpcClient", 2)) {
                            Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                        }
                        this.state = 1;
                        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                        intent.setPackage("com.google.android.gms");
                        if (!ConnectionTracker.getInstance().bindService(this.zzcb.zzx, intent, (ServiceConnection)this, 1)) {
                            this.zza(0, "Unable to bind to service");
                            b3 = b2;
                            break;
                        }
                        this.zzcb.zzbu.schedule(new zzaf(this), 30L, TimeUnit.SECONDS);
                        b3 = b2;
                        break;
                    }
                    this.zzbz.add(zzak);
                    b3 = b2;
                    break;
                }
                this.zzbz.add(zzak);
                this.zzy();
                b3 = b2;
                break;
            }
            b3 = false;
            break;
        }
        // monitorexit(this)
        return b3;
    }
    
    final void zzz() {
        synchronized (this) {
            if (this.state == 2 && this.zzbz.isEmpty() && this.zzca.size() == 0) {
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
                }
                this.state = 3;
                ConnectionTracker.getInstance().unbindService(this.zzcb.zzx, (ServiceConnection)this);
            }
        }
    }
}
