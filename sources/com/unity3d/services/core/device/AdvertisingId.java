package com.unity3d.services.core.device;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.unity3d.services.core.log.DeviceLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@TargetApi(9)
public class AdvertisingId {
    private static final String ADVERTISING_ID_SERVICE_NAME = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
    private static AdvertisingId instance = null;
    private String advertisingIdentifier = null;
    private boolean limitedAdvertisingTracking = false;

    private interface GoogleAdvertisingInfo extends IInterface {

        public static abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {

            private static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {
                private final IBinder _binder;

                GoogleAdvertisingInfoImplementation(IBinder binder) {
                    this._binder = binder;
                }

                public IBinder asBinder() {
                    return this._binder;
                }

                public String getId() throws RemoteException {
                    Parcel localParcel1 = Parcel.obtain();
                    Parcel localParcel2 = Parcel.obtain();
                    try {
                        localParcel1.writeInterfaceToken(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        this._binder.transact(1, localParcel1, localParcel2, 0);
                        localParcel2.readException();
                        String str = localParcel2.readString();
                        return str;
                    } finally {
                        localParcel2.recycle();
                        localParcel1.recycle();
                    }
                }

                public boolean getEnabled(boolean paramBoolean) throws RemoteException {
                    boolean bool = true;
                    Parcel localParcel1 = Parcel.obtain();
                    Parcel localParcel2 = Parcel.obtain();
                    try {
                        int i;
                        localParcel1.writeInterfaceToken(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        if (paramBoolean) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        localParcel1.writeInt(i);
                        this._binder.transact(2, localParcel1, localParcel2, 0);
                        localParcel2.readException();
                        if (localParcel2.readInt() == 0) {
                            bool = false;
                        }
                        localParcel2.recycle();
                        localParcel1.recycle();
                        return bool;
                    } catch (Throwable th) {
                        localParcel2.recycle();
                        localParcel1.recycle();
                    }
                }
            }

            public static GoogleAdvertisingInfo create(IBinder binder) {
                if (binder == null) {
                    return null;
                }
                IInterface localIInterface = binder.queryLocalInterface(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                if (localIInterface == null || !(localIInterface instanceof GoogleAdvertisingInfo)) {
                    return new GoogleAdvertisingInfoImplementation(binder);
                }
                return (GoogleAdvertisingInfo) localIInterface;
            }

            public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
                int i = 0;
                switch (code) {
                    case 1:
                        data.enforceInterface(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        String str1 = getId();
                        reply.writeNoException();
                        reply.writeString(str1);
                        return true;
                    case 2:
                        boolean bool1;
                        data.enforceInterface(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        if (data.readInt() != 0) {
                            bool1 = true;
                        } else {
                            bool1 = false;
                        }
                        boolean bool2 = getEnabled(bool1);
                        reply.writeNoException();
                        if (bool2) {
                            i = 1;
                        }
                        reply.writeInt(i);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            }
        }

        boolean getEnabled(boolean z) throws RemoteException;

        String getId() throws RemoteException;
    }

    private class GoogleAdvertisingServiceConnection implements ServiceConnection {
        private final BlockingQueue<IBinder> _binderQueue;
        boolean _consumed;

        private GoogleAdvertisingServiceConnection() {
            this._consumed = false;
            this._binderQueue = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                this._binderQueue.put(service);
            } catch (InterruptedException e) {
                DeviceLog.debug("Couldn't put service to binder que");
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }

        public IBinder getBinder() throws InterruptedException {
            if (this._consumed) {
                throw new IllegalStateException();
            }
            this._consumed = true;
            return (IBinder) this._binderQueue.take();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fetchAdvertisingId(android.content.Context r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0034 in list [B:15:0x0044]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1364497552.run(Unknown Source)
*/
        /*
        r6 = this;
        r1 = new com.unity3d.services.core.device.AdvertisingId$GoogleAdvertisingServiceConnection;
        r5 = 0;
        r1.<init>();
        r4 = new android.content.Intent;
        r5 = "com.google.android.gms.ads.identifier.service.START";
        r4.<init>(r5);
        r5 = "com.google.android.gms";
        r4.setPackage(r5);
        r2 = 0;
        r5 = 1;
        r2 = r7.bindService(r4, r1, r5);	 Catch:{ Exception -> 0x0035 }
    L_0x0018:
        if (r2 == 0) goto L_0x002f;
    L_0x001a:
        r5 = r1.getBinder();	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        r0 = com.unity3d.services.core.device.AdvertisingId.GoogleAdvertisingInfo.GoogleAdvertisingInfoBinder.create(r5);	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        r5 = r0.getId();	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        r6.advertisingIdentifier = r5;	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        r5 = 1;	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        r5 = r0.getEnabled(r5);	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        r6.limitedAdvertisingTracking = r5;	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
    L_0x002f:
        if (r2 == 0) goto L_0x0034;
    L_0x0031:
        r7.unbindService(r1);
    L_0x0034:
        return;
    L_0x0035:
        r3 = move-exception;
        r5 = "Couldn't bind to identifier service intent";
        com.unity3d.services.core.log.DeviceLog.exception(r5, r3);
        goto L_0x0018;
    L_0x003c:
        r3 = move-exception;
        r5 = "Couldn't get advertising info";	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        com.unity3d.services.core.log.DeviceLog.exception(r5, r3);	 Catch:{ Exception -> 0x003c, all -> 0x0048 }
        if (r2 == 0) goto L_0x0034;
    L_0x0044:
        r7.unbindService(r1);
        goto L_0x0034;
    L_0x0048:
        r5 = move-exception;
        if (r2 == 0) goto L_0x004e;
    L_0x004b:
        r7.unbindService(r1);
    L_0x004e:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.services.core.device.AdvertisingId.fetchAdvertisingId(android.content.Context):void");
    }

    private static AdvertisingId getInstance() {
        if (instance == null) {
            instance = new AdvertisingId();
        }
        return instance;
    }

    public static void init(Context context) {
        getInstance().fetchAdvertisingId(context);
    }

    public static String getAdvertisingTrackingId() {
        return getInstance().advertisingIdentifier;
    }

    public static boolean getLimitedAdTracking() {
        return getInstance().limitedAdvertisingTracking;
    }
}
