// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.identifier;

import java.util.concurrent.CountDownLatch;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import android.os.RemoteException;
import android.util.Log;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.internal.ads_identifier.zzf;
import java.util.concurrent.TimeUnit;
import android.content.ServiceConnection;
import com.google.android.gms.common.stats.ConnectionTracker;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import android.os.SystemClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads_identifier.zze;
import android.support.annotation.Nullable;
import com.google.android.gms.common.BlockingServiceConnection;
import javax.annotation.concurrent.GuardedBy;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@KeepForSdk
public class AdvertisingIdClient
{
    @GuardedBy("this")
    private final Context mContext;
    @Nullable
    @GuardedBy("this")
    private BlockingServiceConnection zze;
    @Nullable
    @GuardedBy("this")
    private zze zzf;
    @GuardedBy("this")
    private boolean zzg;
    private final Object zzh;
    @Nullable
    @GuardedBy("mAutoDisconnectTaskLock")
    private zza zzi;
    private final boolean zzj;
    private final long zzk;
    
    @KeepForSdk
    public AdvertisingIdClient(final Context context) {
        this(context, 30000L, false, false);
    }
    
    @VisibleForTesting
    private AdvertisingIdClient(Context context, final long zzk, final boolean b, final boolean zzj) {
        this.zzh = new Object();
        Preconditions.checkNotNull((Object)context);
        if (b) {
            final Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            this.mContext = context;
        }
        else {
            this.mContext = context;
        }
        this.zzg = false;
        this.zzk = zzk;
        this.zzj = zzj;
    }
    
    @KeepForSdk
    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        final zzb zzb = new zzb(context);
        final boolean boolean1 = zzb.getBoolean("gads:ad_id_app_context:enabled", false);
        final float float1 = zzb.getFloat("gads:ad_id_app_context:ping_ratio", 0.0f);
        final String string = zzb.getString("gads:ad_id_use_shared_preference:experiment_id", "");
        context = (Context)new AdvertisingIdClient(context, -1L, boolean1, zzb.getBoolean("gads:ad_id_use_persistent_service:enabled", false));
        try {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            ((AdvertisingIdClient)context).zza(false);
            final Info info = ((AdvertisingIdClient)context).getInfo();
            ((AdvertisingIdClient)context).zza(info, boolean1, float1, SystemClock.elapsedRealtime() - elapsedRealtime, string, null);
            return info;
        }
        catch (Throwable t) {
            ((AdvertisingIdClient)context).zza(null, boolean1, float1, -1L, string, t);
            throw t;
        }
        finally {
            ((AdvertisingIdClient)context).finish();
        }
    }
    
    @KeepForSdk
    public static boolean getIsAdIdFakeForDebugLogging(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        final zzb zzb = new zzb(context);
        context = (Context)new AdvertisingIdClient(context, -1L, zzb.getBoolean("gads:ad_id_app_context:enabled", false), zzb.getBoolean("com.google.android.gms.ads.identifier.service.PERSISTENT_START", false));
        try {
            ((AdvertisingIdClient)context).zza(false);
            return ((AdvertisingIdClient)context).zzb();
        }
        finally {
            ((AdvertisingIdClient)context).finish();
        }
    }
    
    @KeepForSdk
    public static void setShouldSkipGmsCoreVersionCheck(final boolean b) {
    }
    
    private static BlockingServiceConnection zza(final Context context, final boolean b) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000)) {
                default: {
                    throw new IOException("Google Play services not available");
                }
                case 0:
                case 2: {
                    break;
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        Label_0117: {
            if (!b) {
                break Label_0117;
            }
            String s = "com.google.android.gms.ads.identifier.service.PERSISTENT_START";
            while (true) {
                final BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
                final Intent intent = new Intent(s);
                intent.setPackage("com.google.android.gms");
                try {
                    if (ConnectionTracker.getInstance().bindService(context, intent, (ServiceConnection)blockingServiceConnection, 1)) {
                        return blockingServiceConnection;
                    }
                    break;
                    s = "com.google.android.gms.ads.identifier.service.START";
                    continue;
                }
                catch (Throwable t) {
                    throw new IOException(t);
                }
                break;
            }
        }
        throw new IOException("Connection failure");
    }
    
    @VisibleForTesting
    private static zze zza(final Context context, final BlockingServiceConnection blockingServiceConnection) throws IOException {
        try {
            return zzf.zza(blockingServiceConnection.getServiceWithTimeout(10000L, TimeUnit.MILLISECONDS));
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted exception");
        }
        catch (Throwable t) {
            throw new IOException(t);
        }
    }
    
    private final void zza() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzh:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzi:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    11: ifnull          31
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzi:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    18: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.zzo:Ljava/util/concurrent/CountDownLatch;
        //    21: invokevirtual   java/util/concurrent/CountDownLatch.countDown:()V
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzi:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    28: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.join:()V
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzk:J
        //    35: lconst_0       
        //    36: lcmp           
        //    37: ifle            56
        //    40: aload_0        
        //    41: new             Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    44: dup            
        //    45: aload_0        
        //    46: aload_0        
        //    47: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzk:J
        //    50: invokespecial   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.<init>:(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;J)V
        //    53: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzi:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    56: aload_1        
        //    57: monitorexit    
        //    58: return         
        //    59: astore_2       
        //    60: aload_1        
        //    61: monitorexit    
        //    62: aload_2        
        //    63: athrow         
        //    64: astore_2       
        //    65: goto            31
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      24     59     64     Any
        //  24     31     64     68     Ljava/lang/InterruptedException;
        //  24     31     59     64     Any
        //  31     56     59     64     Any
        //  56     58     59     64     Any
        //  60     62     59     64     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    
    @VisibleForTesting
    private final void zza(final boolean b) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzg) {
                this.finish();
            }
            this.zze = zza(this.mContext, this.zzj);
            this.zzf = zza(this.mContext, this.zze);
            this.zzg = true;
            if (b) {
                this.zza();
            }
        }
    }
    
    @VisibleForTesting
    private final boolean zza(final Info info, final boolean b, final float n, final long n2, final String s, final Throwable t) {
        if (Math.random() > n) {
            return false;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s2;
        if (b) {
            s2 = "1";
        }
        else {
            s2 = "0";
        }
        hashMap.put("app_context", s2);
        if (info != null) {
            String s3;
            if (info.isLimitAdTrackingEnabled()) {
                s3 = "1";
            }
            else {
                s3 = "0";
            }
            hashMap.put("limit_ad_tracking", s3);
        }
        if (info != null && info.getId() != null) {
            hashMap.put("ad_id_size", Integer.toString(info.getId().length()));
        }
        if (t != null) {
            hashMap.put("error", t.getClass().getName());
        }
        if (s != null && !s.isEmpty()) {
            hashMap.put("experiment_id", s);
        }
        hashMap.put("tag", "AdvertisingIdClient");
        hashMap.put("time_spent", Long.toString(n2));
        new com.google.android.gms.ads.identifier.zza(this, hashMap).start();
        return true;
    }
    
    private final boolean zzb() throws IOException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        // monitorexit(t)
        Label_0097: {
            synchronized (this) {
                if (this.zzg) {
                    break Label_0097;
                }
                synchronized (this.zzh) {
                    if (this.zzi == null || !this.zzi.zzp) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
            }
            try {
                this.zza(false);
                if (!this.zzg) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            catch (Exception ex) {
                throw new IOException("AdvertisingIdClient cannot reconnect.", ex);
            }
        }
        Preconditions.checkNotNull((Object)this.zze);
        Preconditions.checkNotNull((Object)this.zzf);
        try {
            final boolean zzc = this.zzf.zzc();
            // monitorexit(this)
            this.zza();
            return zzc;
        }
        catch (RemoteException ex2) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", (Throwable)ex2);
            throw new IOException("Remote exception");
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.finish();
        super.finalize();
    }
    
    public final void finish() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: invokestatic    com/google/android/gms/common/internal/Preconditions.checkNotMainThread:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    11: ifnull          21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zze:Lcom/google/android/gms/common/BlockingServiceConnection;
        //    18: ifnonnull       24
        //    21: aload_0        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzg:Z
        //    28: ifeq            45
        //    31: invokestatic    com/google/android/gms/common/stats/ConnectionTracker.getInstance:()Lcom/google/android/gms/common/stats/ConnectionTracker;
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zze:Lcom/google/android/gms/common/BlockingServiceConnection;
        //    42: invokevirtual   com/google/android/gms/common/stats/ConnectionTracker.unbindService:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
        //    45: aload_0        
        //    46: iconst_0       
        //    47: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzg:Z
        //    50: aload_0        
        //    51: aconst_null    
        //    52: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzf:Lcom/google/android/gms/internal/ads_identifier/zze;
        //    55: aload_0        
        //    56: aconst_null    
        //    57: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zze:Lcom/google/android/gms/common/BlockingServiceConnection;
        //    60: aload_0        
        //    61: monitorexit    
        //    62: return         
        //    63: astore_1       
        //    64: aload_0        
        //    65: monitorexit    
        //    66: aload_1        
        //    67: athrow         
        //    68: astore_1       
        //    69: ldc_w           "AdvertisingIdClient"
        //    72: ldc_w           "AdvertisingIdClient unbindService failed."
        //    75: aload_1        
        //    76: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  7      21     63     68     Any
        //  21     23     63     68     Any
        //  24     45     68     83     Ljava/lang/Throwable;
        //  24     45     63     68     Any
        //  45     62     63     68     Any
        //  64     66     63     68     Any
        //  69     80     63     68     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    public Info getInfo() throws IOException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        // monitorexit(t)
        Label_0097: {
            synchronized (this) {
                if (this.zzg) {
                    break Label_0097;
                }
                synchronized (this.zzh) {
                    if (this.zzi == null || !this.zzi.zzp) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
            }
            try {
                this.zza(false);
                if (!this.zzg) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            catch (Exception ex) {
                throw new IOException("AdvertisingIdClient cannot reconnect.", ex);
            }
        }
        Preconditions.checkNotNull((Object)this.zze);
        Preconditions.checkNotNull((Object)this.zzf);
        try {
            final Info info = new Info(this.zzf.getId(), this.zzf.zzb(true));
            // monitorexit(this)
            this.zza();
            return info;
        }
        catch (RemoteException ex2) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", (Throwable)ex2);
            throw new IOException("Remote exception");
        }
    }
    
    @KeepForSdk
    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        this.zza(true);
    }
    
    @KeepForSdkWithMembers
    public static final class Info
    {
        private final String zzq;
        private final boolean zzr;
        
        public Info(final String zzq, final boolean zzr) {
            this.zzq = zzq;
            this.zzr = zzr;
        }
        
        public final String getId() {
            return this.zzq;
        }
        
        public final boolean isLimitAdTrackingEnabled() {
            return this.zzr;
        }
        
        @Override
        public final String toString() {
            final String zzq = this.zzq;
            return new StringBuilder(String.valueOf(zzq).length() + 7).append("{").append(zzq).append("}").append(this.zzr).toString();
        }
    }
    
    @VisibleForTesting
    static final class zza extends Thread
    {
        private WeakReference<AdvertisingIdClient> zzm;
        private long zzn;
        CountDownLatch zzo;
        boolean zzp;
        
        public zza(final AdvertisingIdClient advertisingIdClient, final long zzn) {
            this.zzm = new WeakReference<AdvertisingIdClient>(advertisingIdClient);
            this.zzn = zzn;
            this.zzo = new CountDownLatch(1);
            this.zzp = false;
            this.start();
        }
        
        private final void disconnect() {
            final AdvertisingIdClient advertisingIdClient = this.zzm.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzp = true;
            }
        }
        
        @Override
        public final void run() {
            try {
                if (!this.zzo.await(this.zzn, TimeUnit.MILLISECONDS)) {
                    this.disconnect();
                }
            }
            catch (InterruptedException ex) {
                this.disconnect();
            }
        }
    }
}
