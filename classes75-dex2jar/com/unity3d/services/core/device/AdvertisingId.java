// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.device;

import com.unity3d.services.core.log.DeviceLog;
import android.content.ComponentName;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import android.content.ServiceConnection;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(9)
public class AdvertisingId
{
    private static final String ADVERTISING_ID_SERVICE_NAME = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
    private static AdvertisingId instance;
    private String advertisingIdentifier;
    private boolean limitedAdvertisingTracking;
    
    static {
        AdvertisingId.instance = null;
    }
    
    public AdvertisingId() {
        this.advertisingIdentifier = null;
        this.limitedAdvertisingTracking = false;
    }
    
    private void fetchAdvertisingId(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: aconst_null    
        //     6: invokespecial   com/unity3d/services/core/device/AdvertisingId$GoogleAdvertisingServiceConnection.<init>:(Lcom/unity3d/services/core/device/AdvertisingId;Lcom/unity3d/services/core/device/AdvertisingId$1;)V
        //     9: astore          4
        //    11: new             Landroid/content/Intent;
        //    14: dup            
        //    15: ldc             "com.google.android.gms.ads.identifier.service.START"
        //    17: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
        //    20: astore          5
        //    22: aload           5
        //    24: ldc             "com.google.android.gms"
        //    26: invokevirtual   android/content/Intent.setPackage:(Ljava/lang/String;)Landroid/content/Intent;
        //    29: pop            
        //    30: iconst_0       
        //    31: istore_2       
        //    32: aload_1        
        //    33: aload           5
        //    35: aload           4
        //    37: iconst_1       
        //    38: invokevirtual   android/content/Context.bindService:(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
        //    41: istore_3       
        //    42: iload_3        
        //    43: istore_2       
        //    44: iload_2        
        //    45: ifeq            81
        //    48: aload           4
        //    50: invokevirtual   com/unity3d/services/core/device/AdvertisingId$GoogleAdvertisingServiceConnection.getBinder:()Landroid/os/IBinder;
        //    53: invokestatic    com/unity3d/services/core/device/AdvertisingId$GoogleAdvertisingInfo$GoogleAdvertisingInfoBinder.create:(Landroid/os/IBinder;)Lcom/unity3d/services/core/device/AdvertisingId$GoogleAdvertisingInfo;
        //    56: astore          5
        //    58: aload_0        
        //    59: aload           5
        //    61: invokeinterface com/unity3d/services/core/device/AdvertisingId$GoogleAdvertisingInfo.getId:()Ljava/lang/String;
        //    66: putfield        com/unity3d/services/core/device/AdvertisingId.advertisingIdentifier:Ljava/lang/String;
        //    69: aload_0        
        //    70: aload           5
        //    72: iconst_1       
        //    73: invokeinterface com/unity3d/services/core/device/AdvertisingId$GoogleAdvertisingInfo.getEnabled:(Z)Z
        //    78: putfield        com/unity3d/services/core/device/AdvertisingId.limitedAdvertisingTracking:Z
        //    81: iload_2        
        //    82: ifeq            91
        //    85: aload_1        
        //    86: aload           4
        //    88: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //    91: return         
        //    92: astore          5
        //    94: ldc             "Couldn't bind to identifier service intent"
        //    96: aload           5
        //    98: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   101: goto            44
        //   104: astore          5
        //   106: ldc             "Couldn't get advertising info"
        //   108: aload           5
        //   110: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   113: iload_2        
        //   114: ifeq            91
        //   117: aload_1        
        //   118: aload           4
        //   120: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //   123: return         
        //   124: astore          5
        //   126: iload_2        
        //   127: ifeq            136
        //   130: aload_1        
        //   131: aload           4
        //   133: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //   136: aload           5
        //   138: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  32     42     92     104    Ljava/lang/Exception;
        //  48     81     104    124    Ljava/lang/Exception;
        //  48     81     124    139    Any
        //  106    113    124    139    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0081:
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
    
    public static String getAdvertisingTrackingId() {
        return getInstance().advertisingIdentifier;
    }
    
    private static AdvertisingId getInstance() {
        if (AdvertisingId.instance == null) {
            AdvertisingId.instance = new AdvertisingId();
        }
        return AdvertisingId.instance;
    }
    
    public static boolean getLimitedAdTracking() {
        return getInstance().limitedAdvertisingTracking;
    }
    
    public static void init(final Context context) {
        getInstance().fetchAdvertisingId(context);
    }
    
    private interface GoogleAdvertisingInfo extends IInterface
    {
        boolean getEnabled(final boolean p0) throws RemoteException;
        
        String getId() throws RemoteException;
        
        public abstract static class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo
        {
            public static GoogleAdvertisingInfo create(final IBinder binder) {
                if (binder == null) {
                    return null;
                }
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (queryLocalInterface != null && queryLocalInterface instanceof GoogleAdvertisingInfo) {
                    return (GoogleAdvertisingInfo)queryLocalInterface;
                }
                return new GoogleAdvertisingInfoImplementation(binder);
            }
            
            public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
                final int n3 = 0;
                switch (n) {
                    default: {
                        return super.onTransact(n, parcel, parcel2, n2);
                    }
                    case 1: {
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        final String id = this.getId();
                        parcel2.writeNoException();
                        parcel2.writeString(id);
                        return true;
                    }
                    case 2: {
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        final boolean enabled = this.getEnabled(parcel.readInt() != 0);
                        parcel2.writeNoException();
                        n = n3;
                        if (enabled) {
                            n = 1;
                        }
                        parcel2.writeInt(n);
                        return true;
                    }
                }
            }
            
            private static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo
            {
                private final IBinder _binder;
                
                GoogleAdvertisingInfoImplementation(final IBinder binder) {
                    this._binder = binder;
                }
                
                public IBinder asBinder() {
                    return this._binder;
                }
                
                @Override
                public boolean getEnabled(final boolean b) throws RemoteException {
                    final boolean b2 = true;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        int n;
                        if (b) {
                            n = 1;
                        }
                        else {
                            n = 0;
                        }
                        obtain.writeInt(n);
                        this._binder.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        return obtain2.readInt() != 0 && b2;
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
                
                @Override
                public String getId() throws RemoteException {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        this._binder.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
            }
        }
    }
    
    private class GoogleAdvertisingServiceConnection implements ServiceConnection
    {
        private final BlockingQueue<IBinder> _binderQueue;
        boolean _consumed;
        
        private GoogleAdvertisingServiceConnection() {
            this._consumed = false;
            this._binderQueue = new LinkedBlockingQueue<IBinder>();
        }
        
        public IBinder getBinder() throws InterruptedException {
            if (this._consumed) {
                throw new IllegalStateException();
            }
            this._consumed = true;
            return this._binderQueue.take();
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            try {
                this._binderQueue.put(binder);
            }
            catch (InterruptedException ex) {
                DeviceLog.debug("Couldn't put service to binder que");
            }
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
        }
    }
}
