// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.drive.events.zzi;
import com.google.android.gms.drive.events.zzj;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.UidVerifier;
import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import android.os.IInterface;
import android.os.IBinder;
import android.content.pm.ServiceInfo;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import java.util.HashMap;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.drive.events.zzl;
import com.google.android.gms.drive.events.zzd;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.events.ChangeListener;
import java.util.Map;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.GmsClient;

public final class zzaw extends GmsClient<zzeo>
{
    private final String zzdz;
    protected final boolean zzea;
    private volatile DriveId zzeb;
    private volatile DriveId zzec;
    private volatile boolean zzed;
    @VisibleForTesting
    @GuardedBy("changeEventCallbackMap")
    private final Map<DriveId, Map<ChangeListener, zzee>> zzee;
    @VisibleForTesting
    @GuardedBy("changesAvailableEventCallbackMap")
    private final Map<zzd, zzee> zzef;
    @VisibleForTesting
    @GuardedBy("uploadProgressEventCallbackMap")
    private final Map<DriveId, Map<zzl, zzee>> zzeg;
    @VisibleForTesting
    @GuardedBy("pinnedDownloadProgressEventCallbackMap")
    private final Map<DriveId, Map<zzl, zzee>> zzeh;
    private final Bundle zzx;
    
    public zzaw(final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final Bundle zzx) {
        super(context, looper, 11, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzed = false;
        this.zzee = new HashMap<DriveId, Map<ChangeListener, zzee>>();
        this.zzef = new HashMap<zzd, zzee>();
        this.zzeg = new HashMap<DriveId, Map<zzl, zzee>>();
        this.zzeh = new HashMap<DriveId, Map<zzl, zzee>>();
        this.zzdz = clientSettings.getRealClientPackageName();
        this.zzx = zzx;
        final Intent intent = new Intent("com.google.android.gms.drive.events.HANDLE_EVENT");
        intent.setPackage(context.getPackageName());
        final List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        switch (queryIntentServices.size()) {
            default: {
                final String action = intent.getAction();
                throw new IllegalStateException(new StringBuilder(String.valueOf(action).length() + 72).append("AndroidManifest.xml can only define one service that handles the ").append(action).append(" action").toString());
            }
            case 0: {
                this.zzea = false;
            }
            case 1: {
                final ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
                if (!serviceInfo.exported) {
                    final String name = serviceInfo.name;
                    throw new IllegalStateException(new StringBuilder(String.valueOf(name).length() + 60).append("Drive event service ").append(name).append(" must be exported in AndroidManifest.xml").toString());
                }
                this.zzea = true;
            }
        }
    }
    
    public final void disconnect() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/internal/drive/zzaw.isConnected:()Z
        //     4: ifeq            26
        //     7: aload_0        
        //     8: invokevirtual   com/google/android/gms/internal/drive/zzaw.getService:()Landroid/os/IInterface;
        //    11: checkcast       Lcom/google/android/gms/internal/drive/zzeo;
        //    14: new             Lcom/google/android/gms/internal/drive/zzad;
        //    17: dup            
        //    18: invokespecial   com/google/android/gms/internal/drive/zzad.<init>:()V
        //    21: invokeinterface com/google/android/gms/internal/drive/zzeo.zza:(Lcom/google/android/gms/internal/drive/zzad;)V
        //    26: aload_0        
        //    27: invokespecial   com/google/android/gms/common/internal/GmsClient.disconnect:()V
        //    30: aload_0        
        //    31: getfield        com/google/android/gms/internal/drive/zzaw.zzee:Ljava/util/Map;
        //    34: astore_1       
        //    35: aload_1        
        //    36: monitorenter   
        //    37: aload_0        
        //    38: getfield        com/google/android/gms/internal/drive/zzaw.zzee:Ljava/util/Map;
        //    41: invokeinterface java/util/Map.clear:()V
        //    46: aload_1        
        //    47: monitorexit    
        //    48: aload_0        
        //    49: getfield        com/google/android/gms/internal/drive/zzaw.zzef:Ljava/util/Map;
        //    52: astore_1       
        //    53: aload_1        
        //    54: monitorenter   
        //    55: aload_0        
        //    56: getfield        com/google/android/gms/internal/drive/zzaw.zzef:Ljava/util/Map;
        //    59: invokeinterface java/util/Map.clear:()V
        //    64: aload_1        
        //    65: monitorexit    
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/internal/drive/zzaw.zzeg:Ljava/util/Map;
        //    70: astore_1       
        //    71: aload_1        
        //    72: monitorenter   
        //    73: aload_0        
        //    74: getfield        com/google/android/gms/internal/drive/zzaw.zzeg:Ljava/util/Map;
        //    77: invokeinterface java/util/Map.clear:()V
        //    82: aload_1        
        //    83: monitorexit    
        //    84: aload_0        
        //    85: getfield        com/google/android/gms/internal/drive/zzaw.zzeh:Ljava/util/Map;
        //    88: astore_1       
        //    89: aload_1        
        //    90: monitorenter   
        //    91: aload_0        
        //    92: getfield        com/google/android/gms/internal/drive/zzaw.zzeh:Ljava/util/Map;
        //    95: invokeinterface java/util/Map.clear:()V
        //   100: aload_1        
        //   101: monitorexit    
        //   102: return         
        //   103: astore_2       
        //   104: aload_1        
        //   105: monitorexit    
        //   106: aload_2        
        //   107: athrow         
        //   108: astore_2       
        //   109: aload_1        
        //   110: monitorexit    
        //   111: aload_2        
        //   112: athrow         
        //   113: astore_2       
        //   114: aload_1        
        //   115: monitorexit    
        //   116: aload_2        
        //   117: athrow         
        //   118: astore_2       
        //   119: aload_1        
        //   120: monitorexit    
        //   121: aload_2        
        //   122: athrow         
        //   123: astore_1       
        //   124: goto            26
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      26     123    127    Landroid/os/RemoteException;
        //  37     48     103    108    Any
        //  55     66     108    113    Any
        //  73     84     113    118    Any
        //  91     102    118    123    Any
        //  104    106    103    108    Any
        //  109    111    108    113    Any
        //  114    116    113    118    Any
        //  119    121    118    123    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 75, Size: 75
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    protected final Bundle getGetServiceRequestExtraArgs() {
        final String packageName = this.getContext().getPackageName();
        Preconditions.checkNotNull((Object)packageName);
        Preconditions.checkState(!this.getClientSettings().getAllRequestedScopes().isEmpty());
        final Bundle bundle = new Bundle();
        if (!packageName.equals(this.zzdz)) {
            bundle.putString("proxy_package_name", this.zzdz);
        }
        bundle.putAll(this.zzx);
        return bundle;
    }
    
    public final int getMinApkVersion() {
        return 12451000;
    }
    
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }
    
    protected final String getStartServiceAction() {
        return "com.google.android.gms.drive.ApiService.START";
    }
    
    protected final void onPostInitHandler(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        if (bundle != null) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.zzeb = (DriveId)bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.zzec = (DriveId)bundle.getParcelable("com.google.android.gms.drive.appdata_id");
            this.zzed = true;
        }
        super.onPostInitHandler(n, binder, bundle, n2);
    }
    
    public final boolean requiresAccount() {
        return true;
    }
    
    public final boolean requiresSignIn() {
        return !this.getContext().getPackageName().equals(this.zzdz) || !UidVerifier.isGooglePlayServicesUid(this.getContext(), Process.myUid());
    }
    
    final PendingResult<Status> zza(final GoogleApiClient googleApiClient, final DriveId driveId, ChangeListener changeListener) {
        while (true) {
            Preconditions.checkArgument(zzj.zza(1, driveId));
            Preconditions.checkNotNull((Object)changeListener, (Object)"listener");
            Preconditions.checkState(this.isConnected(), (Object)"Client must be connected");
            while (true) {
                Label_0197: {
                    synchronized (this.zzee) {
                        Map<ChangeListener, zzee> map = this.zzee.get(driveId);
                        if (map == null) {
                            map = new HashMap<ChangeListener, zzee>();
                            this.zzee.put(driveId, map);
                            final Object o = map.get(changeListener);
                            if (o == null) {
                                final zzee zzee = new zzee(this.getLooper(), this.getContext(), 1, changeListener);
                                map.put(changeListener, zzee);
                                changeListener = (ChangeListener)zzee;
                            }
                            else {
                                changeListener = (ChangeListener)o;
                                if (((zzee)o).zzg(1)) {
                                    return (PendingResult<Status>)new zzat(googleApiClient, Status.RESULT_SUCCESS);
                                }
                            }
                            ((zzee)changeListener).zzf(1);
                            return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzax(this, googleApiClient, new com.google.android.gms.internal.drive.zzj(1, driveId), (zzee)changeListener));
                        }
                        break Label_0197;
                    }
                }
                continue;
            }
        }
    }
    
    public final DriveId zzad() {
        return this.zzeb;
    }
    
    public final DriveId zzae() {
        return this.zzec;
    }
    
    public final boolean zzaf() {
        return this.zzed;
    }
    
    public final boolean zzag() {
        return this.zzea;
    }
    
    final PendingResult<Status> zzb(final GoogleApiClient googleApiClient, final DriveId driveId, ChangeListener changeListener) {
        Preconditions.checkArgument(zzj.zza(1, driveId));
        Preconditions.checkState(this.isConnected(), (Object)"Client must be connected");
        Preconditions.checkNotNull((Object)changeListener, (Object)"listener");
        final Map<ChangeListener, zzee> map;
        synchronized (this.zzee) {
            map = this.zzee.get(driveId);
            if (map == null) {
                return (PendingResult<Status>)new zzat(googleApiClient, Status.RESULT_SUCCESS);
            }
            changeListener = (ChangeListener)map.remove(changeListener);
            if (changeListener == null) {
                return (PendingResult<Status>)new zzat(googleApiClient, Status.RESULT_SUCCESS);
            }
        }
        if (map.isEmpty()) {
            this.zzee.remove(driveId);
        }
        final GoogleApiClient googleApiClient2;
        final BaseImplementation$ApiMethodImpl execute = googleApiClient2.execute((BaseImplementation$ApiMethodImpl)new zzay(this, googleApiClient2, new zzgm(driveId, 1), (zzee)changeListener));
        // monitorexit(map2)
        return (PendingResult<Status>)execute;
    }
}
