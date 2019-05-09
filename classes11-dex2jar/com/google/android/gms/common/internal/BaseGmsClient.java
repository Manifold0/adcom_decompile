// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Message;
import com.google.android.gms.internal.common.zze;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.content.Intent;
import java.util.Collections;
import android.support.annotation.WorkerThread;
import android.os.RemoteException;
import android.os.DeadObjectException;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.app.PendingIntent;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.ServiceConnection;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import android.os.Looper;
import com.google.android.gms.common.util.VisibleForTesting;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.IInterface;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface>
{
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES;
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzbt;
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzbu;
    private long zzbv;
    private long zzbw;
    private int zzbx;
    private long zzby;
    @VisibleForTesting
    private zzh zzbz;
    private final Looper zzca;
    private final GmsClientSupervisor zzcb;
    private final GoogleApiAvailabilityLight zzcc;
    private final Object zzcd;
    @GuardedBy("mServiceBrokerLock")
    private IGmsServiceBroker zzce;
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzcf;
    @GuardedBy("mLock")
    private T zzcg;
    private final ArrayList<zzc<?>> zzch;
    @GuardedBy("mLock")
    private zze zzci;
    @GuardedBy("mLock")
    private int zzcj;
    private final BaseConnectionCallbacks zzck;
    private final BaseOnConnectionFailedListener zzcl;
    private final int zzcm;
    private final String zzcn;
    private ConnectionResult zzco;
    private boolean zzcp;
    private volatile com.google.android.gms.common.internal.zzb zzcq;
    @VisibleForTesting
    protected AtomicInteger zzcr;
    
    static {
        zzbt = new Feature[0];
        GOOGLE_PLUS_REQUIRED_FEATURES = new String[] { "service_esmobile", "service_googleme" };
    }
    
    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(final Context context, final Handler handler, final GmsClientSupervisor gmsClientSupervisor, final GoogleApiAvailabilityLight googleApiAvailabilityLight, final int zzcm, final BaseConnectionCallbacks zzck, final BaseOnConnectionFailedListener zzcl) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList<zzc<?>>();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzca = handler.getLooper();
        this.zzcb = Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzcm = zzcm;
        this.zzck = zzck;
        this.zzcl = zzcl;
        this.zzcn = null;
    }
    
    @KeepForSdk
    protected BaseGmsClient(final Context context, final Looper looper, final int n, final BaseConnectionCallbacks baseConnectionCallbacks, final BaseOnConnectionFailedListener baseOnConnectionFailedListener, final String s) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), n, Preconditions.checkNotNull(baseConnectionCallbacks), Preconditions.checkNotNull(baseOnConnectionFailedListener), s);
    }
    
    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(final Context context, final Looper looper, final GmsClientSupervisor gmsClientSupervisor, final GoogleApiAvailabilityLight googleApiAvailabilityLight, final int zzcm, final BaseConnectionCallbacks zzck, final BaseOnConnectionFailedListener zzcl, final String zzcn) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList<zzc<?>>();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = Preconditions.checkNotNull(context, "Context must not be null");
        this.zzca = Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzcb = Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzb(looper);
        this.zzcm = zzcm;
        this.zzck = zzck;
        this.zzcl = zzcl;
        this.zzcn = zzcn;
    }
    
    private final void zza(int zzq, final T zzcg) {
        boolean b = true;
        int n;
        if (zzq == 4) {
            n = 1;
        }
        else {
            n = 0;
        }
        int n2;
        if (zzcg != null) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n != n2) {
            b = false;
        }
        while (true) {
            Preconditions.checkArgument(b);
            Label_0474: {
            Label_0466:
                while (true) {
                    synchronized (this.mLock) {
                        this.onSetConnectState(this.zzcj = zzq, this.zzcg = zzcg);
                        switch (zzq) {
                            case 2:
                            case 3: {
                                if (this.zzci != null && this.zzbz != null) {
                                    final String zzt = this.zzbz.zzt();
                                    final String packageName = this.zzbz.getPackageName();
                                    Log.e("GmsClient", new StringBuilder(String.valueOf(zzt).length() + 70 + String.valueOf(packageName).length()).append("Calling connect() while still connected, missing disconnect() for ").append(zzt).append(" on ").append(packageName).toString());
                                    this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), (ServiceConnection)this.zzci, this.zzj());
                                    this.zzcr.incrementAndGet();
                                }
                                this.zzci = new zze(this.zzcr.get());
                                if (this.zzcj == 3 && this.getLocalStartServiceAction() != null) {
                                    final zzh zzbz = new zzh(this.getContext().getPackageName(), this.getLocalStartServiceAction(), true, 129);
                                    this.zzbz = zzbz;
                                    final GmsClientSupervisor zzcb = this.zzcb;
                                    final String zzt2 = this.zzbz.zzt();
                                    final String packageName2 = this.zzbz.getPackageName();
                                    zzq = this.zzbz.zzq();
                                    if (!zzcb.zza(new GmsClientSupervisor.zza(zzt2, packageName2, zzq), (ServiceConnection)this.zzci, this.zzj())) {
                                        final String zzt3 = this.zzbz.zzt();
                                        final String packageName3 = this.zzbz.getPackageName();
                                        Log.e("GmsClient", new StringBuilder(String.valueOf(zzt3).length() + 34 + String.valueOf(packageName3).length()).append("unable to connect to service: ").append(zzt3).append(" on ").append(packageName3).toString());
                                        this.zza(16, null, this.zzcr.get());
                                    }
                                    return;
                                }
                                break;
                            }
                            case 4: {
                                break Label_0466;
                            }
                            case 1: {
                                break Label_0474;
                            }
                            default: {
                                return;
                            }
                        }
                    }
                    final zzh zzbz = new zzh(this.getStartServicePackage(), this.getStartServiceAction(), false, 129);
                    continue;
                }
                this.onConnectedLocked(zzcg);
                return;
            }
            if (this.zzci != null) {
                this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), (ServiceConnection)this.zzci, this.zzj());
                this.zzci = null;
            }
        }
    }
    
    static /* synthetic */ void zza(final BaseGmsClient baseGmsClient, final int n) {
        baseGmsClient.zzb(16);
    }
    
    static /* synthetic */ void zza(final BaseGmsClient baseGmsClient, final int n, final IInterface interface1) {
        baseGmsClient.zza(n, null);
    }
    
    private final void zza(final com.google.android.gms.common.internal.zzb zzcq) {
        this.zzcq = zzcq;
    }
    
    private final boolean zza(final int n, final int n2, final T t) {
        synchronized (this.mLock) {
            if (this.zzcj != n) {
                return false;
            }
            this.zza(n2, t);
            return true;
        }
    }
    
    private final void zzb(int n) {
        if (this.zzk()) {
            n = 5;
            this.zzcp = true;
        }
        else {
            n = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(n, this.zzcr.get(), 16));
    }
    
    @Nullable
    private final String zzj() {
        if (this.zzcn == null) {
            return this.mContext.getClass().getName();
        }
        return this.zzcn;
    }
    
    private final boolean zzk() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzcj == 3) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private final boolean zzl() {
        if (this.zzcp || TextUtils.isEmpty((CharSequence)this.getServiceDescriptor()) || TextUtils.isEmpty((CharSequence)this.getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(this.getServiceDescriptor());
            return true;
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }
    
    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        final int googlePlayServicesAvailable = this.zzcc.isGooglePlayServicesAvailable(this.mContext, this.getMinApkVersion());
        if (googlePlayServicesAvailable != 0) {
            this.zza(1, null);
            this.triggerNotAvailable((ConnectionProgressReportCallbacks)new LegacyClientCallbackAdapter(), googlePlayServicesAvailable, null);
            return;
        }
        this.connect((ConnectionProgressReportCallbacks)new LegacyClientCallbackAdapter());
    }
    
    @KeepForSdk
    protected final void checkConnected() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    @KeepForSdk
    public void connect(@NonNull final ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzcf = Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.zza(2, null);
    }
    
    @Nullable
    @KeepForSdk
    protected abstract T createServiceInterface(final IBinder p0);
    
    @KeepForSdk
    public void disconnect() {
        this.zzcr.incrementAndGet();
        Object o = this.zzch;
        synchronized (o) {
            for (int size = this.zzch.size(), i = 0; i < size; ++i) {
                this.zzch.get(i).removeListener();
            }
            this.zzch.clear();
            // monitorexit(o)
            o = this.zzcd;
            // monitorenter(o)
            final BaseGmsClient baseGmsClient = this;
            final IGmsServiceBroker gmsServiceBroker = null;
            baseGmsClient.zzce = gmsServiceBroker;
            final Object o2 = o;
            // monitorexit(o2)
            final BaseGmsClient baseGmsClient2 = this;
            final int n = 1;
            final IInterface interface1 = null;
            baseGmsClient2.zza(n, (T)interface1);
            return;
        }
        try {
            final BaseGmsClient baseGmsClient = this;
            final IGmsServiceBroker gmsServiceBroker = null;
            baseGmsClient.zzce = gmsServiceBroker;
            final Object o2 = o;
            // monitorexit(o2)
            final BaseGmsClient baseGmsClient2 = this;
            final int n = 1;
            final IInterface interface1 = null;
            baseGmsClient2.zza(n, (T)interface1);
        }
        finally {
        }
        // monitorexit(o)
    }
    
    @KeepForSdk
    public void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/common/internal/BaseGmsClient.mLock:Ljava/lang/Object;
        //     4: astore          4
        //     6: aload           4
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzcj:I
        //    13: istore          5
        //    15: aload_0        
        //    16: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzcg:Landroid/os/IInterface;
        //    19: astore_2       
        //    20: aload           4
        //    22: monitorexit    
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzcd:Ljava/lang/Object;
        //    27: astore          4
        //    29: aload           4
        //    31: monitorenter   
        //    32: aload_0        
        //    33: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzce:Lcom/google/android/gms/common/internal/IGmsServiceBroker;
        //    36: astore          8
        //    38: aload           4
        //    40: monitorexit    
        //    41: aload_3        
        //    42: aload_1        
        //    43: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //    46: ldc_w           "mConnectState="
        //    49: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //    52: pop            
        //    53: iload           5
        //    55: tableswitch {
        //                2: 523
        //                3: 483
        //                4: 493
        //                5: 503
        //                6: 513
        //          default: 88
        //        }
        //    88: aload_3        
        //    89: ldc_w           "UNKNOWN"
        //    92: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //    95: aload_3        
        //    96: ldc_w           " mService="
        //    99: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   102: pop            
        //   103: aload_2        
        //   104: ifnonnull       533
        //   107: aload_3        
        //   108: ldc_w           "null"
        //   111: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   114: pop            
        //   115: aload_3        
        //   116: ldc_w           " mServiceBroker="
        //   119: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   122: pop            
        //   123: aload           8
        //   125: ifnonnull       566
        //   128: aload_3        
        //   129: ldc_w           "null"
        //   132: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   135: new             Ljava/text/SimpleDateFormat;
        //   138: dup            
        //   139: ldc_w           "yyyy-MM-dd HH:mm:ss.SSS"
        //   142: getstatic       java/util/Locale.US:Ljava/util/Locale;
        //   145: invokespecial   java/text/SimpleDateFormat.<init>:(Ljava/lang/String;Ljava/util/Locale;)V
        //   148: astore_2       
        //   149: aload_0        
        //   150: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbw:J
        //   153: lconst_0       
        //   154: lcmp           
        //   155: ifle            236
        //   158: aload_3        
        //   159: aload_1        
        //   160: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   163: ldc_w           "lastConnectedTime="
        //   166: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   169: astore          4
        //   171: aload_0        
        //   172: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbw:J
        //   175: lstore          6
        //   177: aload_2        
        //   178: new             Ljava/util/Date;
        //   181: dup            
        //   182: aload_0        
        //   183: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbw:J
        //   186: invokespecial   java/util/Date.<init>:(J)V
        //   189: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   192: astore          8
        //   194: aload           4
        //   196: new             Ljava/lang/StringBuilder;
        //   199: dup            
        //   200: aload           8
        //   202: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   205: invokevirtual   java/lang/String.length:()I
        //   208: bipush          21
        //   210: iadd           
        //   211: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   214: lload           6
        //   216: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   219: ldc_w           " "
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: aload           8
        //   227: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   233: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   236: aload_0        
        //   237: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbv:J
        //   240: lconst_0       
        //   241: lcmp           
        //   242: ifle            370
        //   245: aload_3        
        //   246: aload_1        
        //   247: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   250: ldc_w           "lastSuspendedCause="
        //   253: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   256: pop            
        //   257: aload_0        
        //   258: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbu:I
        //   261: tableswitch {
        //                2: 592
        //                3: 603
        //          default: 284
        //        }
        //   284: aload_3        
        //   285: aload_0        
        //   286: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbu:I
        //   289: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //   292: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   295: pop            
        //   296: aload_3        
        //   297: ldc_w           " lastSuspendedTime="
        //   300: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   303: astore          4
        //   305: aload_0        
        //   306: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbv:J
        //   309: lstore          6
        //   311: aload_2        
        //   312: new             Ljava/util/Date;
        //   315: dup            
        //   316: aload_0        
        //   317: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbv:J
        //   320: invokespecial   java/util/Date.<init>:(J)V
        //   323: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   326: astore          8
        //   328: aload           4
        //   330: new             Ljava/lang/StringBuilder;
        //   333: dup            
        //   334: aload           8
        //   336: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   339: invokevirtual   java/lang/String.length:()I
        //   342: bipush          21
        //   344: iadd           
        //   345: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   348: lload           6
        //   350: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   353: ldc_w           " "
        //   356: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   359: aload           8
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   367: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   370: aload_0        
        //   371: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzby:J
        //   374: lconst_0       
        //   375: lcmp           
        //   376: ifle            470
        //   379: aload_3        
        //   380: aload_1        
        //   381: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   384: ldc_w           "lastFailedStatus="
        //   387: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   390: aload_0        
        //   391: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzbx:I
        //   394: invokestatic    com/google/android/gms/common/api/CommonStatusCodes.getStatusCodeString:(I)Ljava/lang/String;
        //   397: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   400: pop            
        //   401: aload_3        
        //   402: ldc_w           " lastFailedTime="
        //   405: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   408: astore_1       
        //   409: aload_0        
        //   410: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzby:J
        //   413: lstore          6
        //   415: aload_2        
        //   416: new             Ljava/util/Date;
        //   419: dup            
        //   420: aload_0        
        //   421: getfield        com/google/android/gms/common/internal/BaseGmsClient.zzby:J
        //   424: invokespecial   java/util/Date.<init>:(J)V
        //   427: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   430: astore_2       
        //   431: aload_1        
        //   432: new             Ljava/lang/StringBuilder;
        //   435: dup            
        //   436: aload_2        
        //   437: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   440: invokevirtual   java/lang/String.length:()I
        //   443: bipush          21
        //   445: iadd           
        //   446: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   449: lload           6
        //   451: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   454: ldc_w           " "
        //   457: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   460: aload_2        
        //   461: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   464: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   467: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   470: return         
        //   471: astore_1       
        //   472: aload           4
        //   474: monitorexit    
        //   475: aload_1        
        //   476: athrow         
        //   477: astore_1       
        //   478: aload           4
        //   480: monitorexit    
        //   481: aload_1        
        //   482: athrow         
        //   483: aload_3        
        //   484: ldc_w           "REMOTE_CONNECTING"
        //   487: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   490: goto            95
        //   493: aload_3        
        //   494: ldc_w           "LOCAL_CONNECTING"
        //   497: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   500: goto            95
        //   503: aload_3        
        //   504: ldc_w           "CONNECTED"
        //   507: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   510: goto            95
        //   513: aload_3        
        //   514: ldc_w           "DISCONNECTING"
        //   517: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   520: goto            95
        //   523: aload_3        
        //   524: ldc_w           "DISCONNECTED"
        //   527: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   530: goto            95
        //   533: aload_3        
        //   534: aload_0        
        //   535: invokevirtual   com/google/android/gms/common/internal/BaseGmsClient.getServiceDescriptor:()Ljava/lang/String;
        //   538: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   541: ldc_w           "@"
        //   544: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   547: aload_2        
        //   548: invokeinterface android/os/IInterface.asBinder:()Landroid/os/IBinder;
        //   553: invokestatic    java/lang/System.identityHashCode:(Ljava/lang/Object;)I
        //   556: invokestatic    java/lang/Integer.toHexString:(I)Ljava/lang/String;
        //   559: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   562: pop            
        //   563: goto            115
        //   566: aload_3        
        //   567: ldc_w           "IGmsServiceBroker@"
        //   570: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   573: aload           8
        //   575: invokeinterface com/google/android/gms/common/internal/IGmsServiceBroker.asBinder:()Landroid/os/IBinder;
        //   580: invokestatic    java/lang/System.identityHashCode:(Ljava/lang/Object;)I
        //   583: invokestatic    java/lang/Integer.toHexString:(I)Ljava/lang/String;
        //   586: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   589: goto            135
        //   592: aload_3        
        //   593: ldc_w           "CAUSE_SERVICE_DISCONNECTED"
        //   596: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   599: pop            
        //   600: goto            296
        //   603: aload_3        
        //   604: ldc_w           "CAUSE_NETWORK_LOST"
        //   607: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   610: pop            
        //   611: goto            296
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      23     471    477    Any
        //  32     41     477    483    Any
        //  472    475    471    477    Any
        //  478    481    477    483    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    
    @KeepForSdk
    protected boolean enableLocalFallback() {
        return false;
    }
    
    @KeepForSdk
    public Account getAccount() {
        return null;
    }
    
    @KeepForSdk
    public Feature[] getApiFeatures() {
        return BaseGmsClient.zzbt;
    }
    
    @Nullable
    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        final com.google.android.gms.common.internal.zzb zzcq = this.zzcq;
        if (zzcq == null) {
            return null;
        }
        return zzcq.zzdb;
    }
    
    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }
    
    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }
    
    @KeepForSdk
    public String getEndpointPackageName() {
        if (this.isConnected() && this.zzbz != null) {
            return this.zzbz.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }
    
    @KeepForSdk
    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }
    
    @Nullable
    @KeepForSdk
    protected String getLocalStartServiceAction() {
        return null;
    }
    
    @KeepForSdk
    public final Looper getLooper() {
        return this.zzca;
    }
    
    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
    
    @WorkerThread
    @KeepForSdk
    public void getRemoteService(final IAccountAccessor ex, final Set<Scope> set) {
        final Bundle getServiceRequestExtraArgs = this.getGetServiceRequestExtraArgs();
        final GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzcm);
        getServiceRequest.zzy = this.mContext.getPackageName();
        getServiceRequest.zzdk = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzdj = (Scope[])set.toArray((Object[])new Scope[set.size()]);
        }
        while (true) {
            Account account = null;
            Label_0172: {
                if (!this.requiresSignIn()) {
                    break Label_0172;
                }
                if (this.getAccount() != null) {
                    account = this.getAccount();
                    break Label_0172;
                }
                Label_0156: {
                    break Label_0156;
                Block_10_Outer:
                    while (true) {
                        getServiceRequest.zzdm = BaseGmsClient.zzbt;
                        getServiceRequest.zzdn = this.getApiFeatures();
                        try {
                            synchronized (this.zzcd) {
                                if (this.zzce != null) {
                                    this.zzce.getService(new zzd(this, this.zzcr.get()), getServiceRequest);
                                }
                                else {
                                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                                }
                                return;
                                // iftrue(Label_0099:, !this.requiresAccount())
                                while (true) {
                                    getServiceRequest.zzdl = this.getAccount();
                                    continue Block_10_Outer;
                                    continue;
                                }
                                final Account account2 = new Account("<<default account>>", "com.google");
                            }
                        }
                        catch (DeadObjectException ex2) {
                            Log.w("GmsClient", "IGmsServiceBroker.getService failed", (Throwable)ex2);
                            this.triggerConnectionSuspended(1);
                            return;
                        }
                        catch (SecurityException ex3) {
                            throw ex3;
                        }
                        catch (RemoteException ex4) {}
                        catch (RuntimeException ex) {
                            goto Label_0228;
                        }
                    }
                }
            }
            getServiceRequest.zzdl = account;
            if (ex != null) {
                getServiceRequest.zzdi = ((IAccountAccessor)ex).asBinder();
            }
            continue;
        }
    }
    
    @KeepForSdk
    protected Set<Scope> getScopes() {
        return (Set<Scope>)Collections.EMPTY_SET;
    }
    
    @KeepForSdk
    public final T getService() throws DeadObjectException {
        synchronized (this.mLock) {
            if (this.zzcj == 5) {
                throw new DeadObjectException();
            }
        }
        this.checkConnected();
        Preconditions.checkState(this.zzcg != null, (Object)"Client is connected but service is null");
        final IInterface zzcg = this.zzcg;
        // monitorexit(o)
        return (T)zzcg;
    }
    
    @Nullable
    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzcd) {
            if (this.zzce == null) {
                return null;
            }
            return this.zzce.asBinder();
        }
    }
    
    @NonNull
    @KeepForSdk
    protected abstract String getServiceDescriptor();
    
    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }
    
    @NonNull
    @KeepForSdk
    protected abstract String getStartServiceAction();
    
    @KeepForSdk
    protected String getStartServicePackage() {
        return "com.google.android.gms";
    }
    
    @KeepForSdk
    public boolean isConnected() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzcj == 4) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @KeepForSdk
    public boolean isConnecting() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzcj == 2) {
                    return true;
                }
                if (this.zzcj == 3) {
                    return true;
                }
                return false;
            }
            return true;
            b = false;
            return b;
        }
    }
    
    @CallSuper
    @KeepForSdk
    protected void onConnectedLocked(@NonNull final T t) {
        this.zzbw = System.currentTimeMillis();
    }
    
    @CallSuper
    @KeepForSdk
    protected void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzbx = connectionResult.getErrorCode();
        this.zzby = System.currentTimeMillis();
    }
    
    @CallSuper
    @KeepForSdk
    protected void onConnectionSuspended(final int zzbu) {
        this.zzbu = zzbu;
        this.zzbv = System.currentTimeMillis();
    }
    
    @KeepForSdk
    protected void onPostInitHandler(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, n2, -1, (Object)new zzf(n, binder, bundle)));
    }
    
    @KeepForSdk
    void onSetConnectState(final int n, final T t) {
    }
    
    @KeepForSdk
    public void onUserSignOut(@NonNull final SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }
    
    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }
    
    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }
    
    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }
    
    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }
    
    @KeepForSdk
    public void triggerConnectionSuspended(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzcr.get(), n));
    }
    
    @KeepForSdk
    @VisibleForTesting
    protected void triggerNotAvailable(@NonNull final ConnectionProgressReportCallbacks connectionProgressReportCallbacks, final int n, @Nullable final PendingIntent pendingIntent) {
        this.zzcf = Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzcr.get(), n, (Object)pendingIntent));
    }
    
    protected final void zza(final int n, @Nullable final Bundle bundle, final int n2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, n2, -1, (Object)new zzg(n, null)));
    }
    
    @KeepForSdk
    public interface BaseConnectionCallbacks
    {
        @KeepForSdk
        void onConnected(@Nullable final Bundle p0);
        
        @KeepForSdk
        void onConnectionSuspended(final int p0);
    }
    
    @KeepForSdk
    public interface BaseOnConnectionFailedListener
    {
        void onConnectionFailed(@NonNull final ConnectionResult p0);
    }
    
    @KeepForSdk
    public interface ConnectionProgressReportCallbacks
    {
        @KeepForSdk
        void onReportServiceBinding(@NonNull final ConnectionResult p0);
    }
    
    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks
    {
        @KeepForSdk
        public LegacyClientCallbackAdapter() {
        }
        
        @Override
        public void onReportServiceBinding(@NonNull final ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient.this.getRemoteService(null, BaseGmsClient.this.getScopes());
            }
            else if (BaseGmsClient.this.zzcl != null) {
                BaseGmsClient.this.zzcl.onConnectionFailed(connectionResult);
            }
        }
    }
    
    @KeepForSdk
    public interface SignOutCallbacks
    {
        @KeepForSdk
        void onSignOutComplete();
    }
    
    private abstract class zza extends zzc<Boolean>
    {
        private final int statusCode;
        private final Bundle zzcs;
        
        @BinderThread
        protected zza(final int statusCode, final Bundle zzcs) {
            super(true);
            this.statusCode = statusCode;
            this.zzcs = zzcs;
        }
        
        protected abstract void zza(final ConnectionResult p0);
        
        protected abstract boolean zzm();
        
        @Override
        protected final void zzn() {
        }
    }
    
    final class zzb extends zze
    {
        public zzb(final Looper looper) {
            super(looper);
        }
        
        private static void zza(final Message message) {
            final zzc zzc = (zzc)message.obj;
            zzc.zzn();
            zzc.unregister();
        }
        
        private static boolean zzb(final Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
        
        public final void handleMessage(final Message message) {
            PendingIntent pendingIntent = null;
            if (BaseGmsClient.this.zzcr.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
                return;
            }
            if ((message.what == 1 || message.what == 7 || (message.what == 4 && !BaseGmsClient.this.enableLocalFallback()) || message.what == 5) && !BaseGmsClient.this.isConnecting()) {
                zza(message);
                return;
            }
            if (message.what == 4) {
                BaseGmsClient.this.zzco = new ConnectionResult(message.arg2);
                if (BaseGmsClient.this.zzl() && !BaseGmsClient.this.zzcp) {
                    BaseGmsClient.zza(BaseGmsClient.this, 3, null);
                    return;
                }
                ConnectionResult zzd;
                if (BaseGmsClient.this.zzco != null) {
                    zzd = BaseGmsClient.this.zzco;
                }
                else {
                    zzd = new ConnectionResult(8);
                }
                BaseGmsClient.this.zzcf.onReportServiceBinding(zzd);
                BaseGmsClient.this.onConnectionFailed(zzd);
            }
            else {
                if (message.what == 5) {
                    ConnectionResult zzd2;
                    if (BaseGmsClient.this.zzco != null) {
                        zzd2 = BaseGmsClient.this.zzco;
                    }
                    else {
                        zzd2 = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.zzcf.onReportServiceBinding(zzd2);
                    BaseGmsClient.this.onConnectionFailed(zzd2);
                    return;
                }
                if (message.what == 3) {
                    if (message.obj instanceof PendingIntent) {
                        pendingIntent = (PendingIntent)message.obj;
                    }
                    final ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                    BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult);
                    BaseGmsClient.this.onConnectionFailed(connectionResult);
                    return;
                }
                if (message.what == 6) {
                    BaseGmsClient.zza(BaseGmsClient.this, 5, null);
                    if (BaseGmsClient.this.zzck != null) {
                        BaseGmsClient.this.zzck.onConnectionSuspended(message.arg2);
                    }
                    BaseGmsClient.this.onConnectionSuspended(message.arg2);
                    BaseGmsClient.this.zza(5, 1, null);
                    return;
                }
                if (message.what == 2 && !BaseGmsClient.this.isConnected()) {
                    zza(message);
                    return;
                }
                if (zzb(message)) {
                    ((zzc)message.obj).zzo();
                    return;
                }
                Log.wtf("GmsClient", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), (Throwable)new Exception());
            }
        }
    }
    
    protected abstract class zzc<TListener>
    {
        private TListener zzcu;
        private boolean zzcv;
        
        public zzc(final TListener zzcu) {
            this.zzcu = zzcu;
            this.zzcv = false;
        }
        
        public final void removeListener() {
            synchronized (this) {
                this.zzcu = null;
            }
        }
        
        public final void unregister() {
            this.removeListener();
            synchronized (BaseGmsClient.this.zzch) {
                BaseGmsClient.this.zzch.remove(this);
            }
        }
        
        protected abstract void zza(final TListener p0);
        
        protected abstract void zzn();
        
        public final void zzo() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        com/google/android/gms/common/internal/BaseGmsClient$zzc.zzcu:Ljava/lang/Object;
            //     6: astore_1       
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/common/internal/BaseGmsClient$zzc.zzcv:Z
            //    11: ifeq            59
            //    14: aload_0        
            //    15: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //    18: astore_2       
            //    19: ldc             "GmsClient"
            //    21: new             Ljava/lang/StringBuilder;
            //    24: dup            
            //    25: aload_2        
            //    26: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //    29: invokevirtual   java/lang/String.length:()I
            //    32: bipush          47
            //    34: iadd           
            //    35: invokespecial   java/lang/StringBuilder.<init>:(I)V
            //    38: ldc             "Callback proxy "
            //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    43: aload_2        
            //    44: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    47: ldc             " being reused. This is not safe."
            //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    52: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    55: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //    58: pop            
            //    59: aload_0        
            //    60: monitorexit    
            //    61: aload_1        
            //    62: ifnull          96
            //    65: aload_0        
            //    66: aload_1        
            //    67: invokevirtual   com/google/android/gms/common/internal/BaseGmsClient$zzc.zza:(Ljava/lang/Object;)V
            //    70: aload_0        
            //    71: monitorenter   
            //    72: aload_0        
            //    73: iconst_1       
            //    74: putfield        com/google/android/gms/common/internal/BaseGmsClient$zzc.zzcv:Z
            //    77: aload_0        
            //    78: monitorexit    
            //    79: aload_0        
            //    80: invokevirtual   com/google/android/gms/common/internal/BaseGmsClient$zzc.unregister:()V
            //    83: return         
            //    84: astore_1       
            //    85: aload_0        
            //    86: monitorexit    
            //    87: aload_1        
            //    88: athrow         
            //    89: astore_1       
            //    90: aload_0        
            //    91: invokevirtual   com/google/android/gms/common/internal/BaseGmsClient$zzc.zzn:()V
            //    94: aload_1        
            //    95: athrow         
            //    96: aload_0        
            //    97: invokevirtual   com/google/android/gms/common/internal/BaseGmsClient$zzc.zzn:()V
            //   100: goto            70
            //   103: astore_1       
            //   104: aload_0        
            //   105: monitorexit    
            //   106: aload_1        
            //   107: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  2      59     84     89     Any
            //  59     61     84     89     Any
            //  65     70     89     96     Ljava/lang/RuntimeException;
            //  72     79     103    108    Any
            //  85     87     84     89     Any
            //  104    106    103    108    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 64, Size: 64
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
            //     at java.util.ArrayList.get(ArrayList.java:429)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
    }
    
    @VisibleForTesting
    public static final class zzd extends IGmsCallbacks.zza
    {
        private BaseGmsClient zzcw;
        private final int zzcx;
        
        public zzd(@NonNull final BaseGmsClient zzcw, final int zzcx) {
            this.zzcw = zzcw;
            this.zzcx = zzcx;
        }
        
        @BinderThread
        @Override
        public final void onPostInitComplete(final int n, @NonNull final IBinder binder, @Nullable final Bundle bundle) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcw.onPostInitHandler(n, binder, bundle, this.zzcx);
            this.zzcw = null;
        }
        
        @BinderThread
        @Override
        public final void zza(final int n, @Nullable final Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", (Throwable)new Exception());
        }
        
        @BinderThread
        @Override
        public final void zza(final int n, @NonNull final IBinder binder, @NonNull final zzb zzb) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzb);
            this.zzcw.zza(zzb);
            this.onPostInitComplete(n, binder, zzb.zzda);
        }
    }
    
    @VisibleForTesting
    public final class zze implements ServiceConnection
    {
        private final int zzcx;
        
        public zze(final int zzcx) {
            this.zzcx = zzcx;
        }
        
        public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            if (binder == null) {
                BaseGmsClient.zza(BaseGmsClient.this, 16);
                return;
            }
            synchronized (BaseGmsClient.this.zzcd) {
                final BaseGmsClient zzct = BaseGmsClient.this;
                IGmsServiceBroker gmsServiceBroker;
                if (binder == null) {
                    gmsServiceBroker = null;
                }
                else {
                    final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface != null && queryLocalInterface instanceof IGmsServiceBroker) {
                        gmsServiceBroker = (IGmsServiceBroker)queryLocalInterface;
                    }
                    else {
                        gmsServiceBroker = new IGmsServiceBroker.Stub.zza(binder);
                    }
                }
                zzct.zzce = gmsServiceBroker;
                // monitorexit(BaseGmsClient.zza(this.zzct))
                BaseGmsClient.this.zza(0, null, this.zzcx);
            }
        }
        
        public final void onServiceDisconnected(final ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzcd) {
                BaseGmsClient.this.zzce = null;
                // monitorexit(BaseGmsClient.zza(this.zzct))
                BaseGmsClient.this.mHandler.sendMessage(BaseGmsClient.this.mHandler.obtainMessage(6, this.zzcx, 1));
            }
        }
    }
    
    protected final class zzf extends zza
    {
        private final IBinder zzcy;
        
        @BinderThread
        public zzf(final int n, final IBinder zzcy, final Bundle bundle) {
            super(n, bundle);
            this.zzcy = zzcy;
        }
        
        @Override
        protected final void zza(final ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzcl != null) {
                BaseGmsClient.this.zzcl.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }
        
        @Override
        protected final boolean zzm() {
            while (true) {
                try {
                    final String interfaceDescriptor = this.zzcy.getInterfaceDescriptor();
                    if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                        final String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                        Log.e("GmsClient", new StringBuilder(String.valueOf(serviceDescriptor).length() + 34 + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(serviceDescriptor).append(" vs. ").append(interfaceDescriptor).toString());
                        return false;
                    }
                }
                catch (RemoteException ex) {
                    Log.w("GmsClient", "service probably died");
                    return false;
                }
                final IInterface serviceInterface = BaseGmsClient.this.createServiceInterface(this.zzcy);
                if (serviceInterface != null && (BaseGmsClient.this.zza(2, 4, serviceInterface) || BaseGmsClient.this.zza(3, 4, serviceInterface))) {
                    break;
                }
                return false;
            }
            BaseGmsClient.this.zzco = null;
            final Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
            if (BaseGmsClient.this.zzck != null) {
                BaseGmsClient.this.zzck.onConnected(connectionHint);
            }
            return true;
        }
    }
    
    protected final class zzg extends zza
    {
        @BinderThread
        public zzg(@Nullable final int n, final Bundle bundle) {
            super(n, null);
        }
        
        @Override
        protected final void zza(final ConnectionResult connectionResult) {
            if (BaseGmsClient.this.enableLocalFallback() && BaseGmsClient.this.zzl()) {
                BaseGmsClient.zza(BaseGmsClient.this, 16);
                return;
            }
            BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult);
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }
        
        @Override
        protected final boolean zzm() {
            BaseGmsClient.this.zzcf.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }
}
