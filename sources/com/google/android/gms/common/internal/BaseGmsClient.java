package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface> {
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = new String[]{"service_esmobile", "service_googleme"};
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzbt = new Feature[0];
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
    private volatile zzb zzcq;
    @VisibleForTesting
    protected AtomicInteger zzcr;

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(@Nullable Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(@NonNull ConnectionResult connectionResult);
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        private final /* synthetic */ BaseGmsClient zzct;

        @KeepForSdk
        public LegacyClientCallbackAdapter(BaseGmsClient baseGmsClient) {
            this.zzct = baseGmsClient;
        }

        public void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                this.zzct.getRemoteService(null, this.zzct.getScopes());
            } else if (this.zzct.zzcl != null) {
                this.zzct.zzcl.onConnectionFailed(connectionResult);
            }
        }
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    protected abstract class zzc<TListener> {
        private final /* synthetic */ BaseGmsClient zzct;
        private TListener zzcu;
        private boolean zzcv = false;

        public zzc(BaseGmsClient baseGmsClient, TListener tListener) {
            this.zzct = baseGmsClient;
            this.zzcu = tListener;
        }

        protected abstract void zza(TListener tListener);

        protected abstract void zzn();

        public final void zzo() {
            synchronized (this) {
                Object obj = this.zzcu;
                if (this.zzcv) {
                    String valueOf = String.valueOf(this);
                    Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                }
            }
            if (obj != null) {
                try {
                    zza(obj);
                } catch (RuntimeException e) {
                    zzn();
                    throw e;
                }
            }
            zzn();
            synchronized (this) {
                this.zzcv = true;
            }
            unregister();
        }

        public final void unregister() {
            removeListener();
            synchronized (this.zzct.zzch) {
                this.zzct.zzch.remove(this);
            }
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzcu = null;
            }
        }
    }

    private abstract class zza extends zzc<Boolean> {
        private final int statusCode;
        private final Bundle zzcs;
        private final /* synthetic */ BaseGmsClient zzct;

        @BinderThread
        protected zza(BaseGmsClient baseGmsClient, int i, Bundle bundle) {
            this.zzct = baseGmsClient;
            super(baseGmsClient, Boolean.valueOf(true));
            this.statusCode = i;
            this.zzcs = bundle;
        }

        protected abstract void zza(ConnectionResult connectionResult);

        protected abstract boolean zzm();

        protected final void zzn() {
        }

        protected final /* synthetic */ void zza(Object obj) {
            PendingIntent pendingIntent = null;
            if (((Boolean) obj) == null) {
                this.zzct.zza(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzm()) {
                        this.zzct.zza(1, null);
                        zza(new ConnectionResult(8, null));
                        return;
                    }
                    return;
                case 10:
                    this.zzct.zza(1, null);
                    throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", new Object[]{getClass().getSimpleName(), this.zzct.getStartServiceAction(), this.zzct.getServiceDescriptor()}));
                default:
                    this.zzct.zza(1, null);
                    if (this.zzcs != null) {
                        pendingIntent = (PendingIntent) this.zzcs.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
                    }
                    zza(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }
    }

    final class zzb extends com.google.android.gms.internal.common.zze {
        private final /* synthetic */ BaseGmsClient zzct;

        public zzb(BaseGmsClient baseGmsClient, Looper looper) {
            this.zzct = baseGmsClient;
            super(looper);
        }

        public final void handleMessage(Message message) {
            PendingIntent pendingIntent = null;
            if (this.zzct.zzcr.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 7 || ((message.what == 4 && !this.zzct.enableLocalFallback()) || message.what == 5)) && !this.zzct.isConnecting()) {
                zza(message);
            } else if (message.what == 4) {
                this.zzct.zzco = new ConnectionResult(message.arg2);
                if (!this.zzct.zzl() || this.zzct.zzcp) {
                    if (this.zzct.zzco != null) {
                        r0 = this.zzct.zzco;
                    } else {
                        r0 = new ConnectionResult(8);
                    }
                    this.zzct.zzcf.onReportServiceBinding(r0);
                    this.zzct.onConnectionFailed(r0);
                    return;
                }
                this.zzct.zza(3, null);
            } else if (message.what == 5) {
                if (this.zzct.zzco != null) {
                    r0 = this.zzct.zzco;
                } else {
                    r0 = new ConnectionResult(8);
                }
                this.zzct.zzcf.onReportServiceBinding(r0);
                this.zzct.onConnectionFailed(r0);
            } else if (message.what == 3) {
                if (message.obj instanceof PendingIntent) {
                    pendingIntent = (PendingIntent) message.obj;
                }
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                this.zzct.zzcf.onReportServiceBinding(connectionResult);
                this.zzct.onConnectionFailed(connectionResult);
            } else if (message.what == 6) {
                this.zzct.zza(5, null);
                if (this.zzct.zzck != null) {
                    this.zzct.zzck.onConnectionSuspended(message.arg2);
                }
                this.zzct.onConnectionSuspended(message.arg2);
                this.zzct.zza(5, 1, null);
            } else if (message.what == 2 && !this.zzct.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((zzc) message.obj).zzo();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
            }
        }

        private static void zza(Message message) {
            zzc zzc = (zzc) message.obj;
            zzc.zzn();
            zzc.unregister();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    @VisibleForTesting
    public static final class zzd extends com.google.android.gms.common.internal.IGmsCallbacks.zza {
        private BaseGmsClient zzcw;
        private final int zzcx;

        public zzd(@NonNull BaseGmsClient baseGmsClient, int i) {
            this.zzcw = baseGmsClient;
            this.zzcx = i;
        }

        @BinderThread
        public final void zza(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @BinderThread
        public final void onPostInitComplete(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcw.onPostInitHandler(i, iBinder, bundle, this.zzcx);
            this.zzcw = null;
        }

        @BinderThread
        public final void zza(int i, @NonNull IBinder iBinder, @NonNull zzb zzb) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzb);
            this.zzcw.zza(zzb);
            onPostInitComplete(i, iBinder, zzb.zzda);
        }
    }

    @VisibleForTesting
    public final class zze implements ServiceConnection {
        private final /* synthetic */ BaseGmsClient zzct;
        private final int zzcx;

        public zze(BaseGmsClient baseGmsClient, int i) {
            this.zzct = baseGmsClient;
            this.zzcx = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                this.zzct.zzb(16);
                return;
            }
            synchronized (this.zzct.zzcd) {
                IGmsServiceBroker iGmsServiceBroker;
                BaseGmsClient baseGmsClient = this.zzct;
                if (iBinder == null) {
                    iGmsServiceBroker = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        iGmsServiceBroker = new zza(iBinder);
                    } else {
                        iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                baseGmsClient.zzce = iGmsServiceBroker;
            }
            this.zzct.zza(0, null, this.zzcx);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (this.zzct.zzcd) {
                this.zzct.zzce = null;
            }
            this.zzct.mHandler.sendMessage(this.zzct.mHandler.obtainMessage(6, this.zzcx, 1));
        }
    }

    protected final class zzf extends zza {
        private final /* synthetic */ BaseGmsClient zzct;
        private final IBinder zzcy;

        @BinderThread
        public zzf(BaseGmsClient baseGmsClient, int i, IBinder iBinder, Bundle bundle) {
            this.zzct = baseGmsClient;
            super(baseGmsClient, i, bundle);
            this.zzcy = iBinder;
        }

        protected final void zza(ConnectionResult connectionResult) {
            if (this.zzct.zzcl != null) {
                this.zzct.zzcl.onConnectionFailed(connectionResult);
            }
            this.zzct.onConnectionFailed(connectionResult);
        }

        protected final boolean zzm() {
            try {
                String interfaceDescriptor = this.zzcy.getInterfaceDescriptor();
                if (this.zzct.getServiceDescriptor().equals(interfaceDescriptor)) {
                    IInterface createServiceInterface = this.zzct.createServiceInterface(this.zzcy);
                    if (createServiceInterface == null) {
                        return false;
                    }
                    if (!this.zzct.zza(2, 4, createServiceInterface) && !this.zzct.zza(3, 4, createServiceInterface)) {
                        return false;
                    }
                    this.zzct.zzco = null;
                    Bundle connectionHint = this.zzct.getConnectionHint();
                    if (this.zzct.zzck != null) {
                        this.zzct.zzck.onConnected(connectionHint);
                    }
                    return true;
                }
                String serviceDescriptor = this.zzct.getServiceDescriptor();
                Log.e("GmsClient", new StringBuilder((String.valueOf(serviceDescriptor).length() + 34) + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(serviceDescriptor).append(" vs. ").append(interfaceDescriptor).toString());
                return false;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzg extends zza {
        private final /* synthetic */ BaseGmsClient zzct;

        @BinderThread
        public zzg(BaseGmsClient baseGmsClient, @Nullable int i, Bundle bundle) {
            this.zzct = baseGmsClient;
            super(baseGmsClient, i, null);
        }

        protected final void zza(ConnectionResult connectionResult) {
            if (this.zzct.enableLocalFallback() && this.zzct.zzl()) {
                this.zzct.zzb(16);
                return;
            }
            this.zzct.zzcf.onReportServiceBinding(connectionResult);
            this.zzct.onConnectionFailed(connectionResult);
        }

        protected final boolean zzm() {
            this.zzct.zzcf.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    @Nullable
    @KeepForSdk
    protected abstract T createServiceInterface(IBinder iBinder);

    @KeepForSdk
    @NonNull
    protected abstract String getServiceDescriptor();

    @KeepForSdk
    @NonNull
    protected abstract String getStartServiceAction();

    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzca = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzb(this, looper);
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = str;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzca = handler.getLooper();
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = null;
    }

    @KeepForSdk
    protected String getStartServicePackage() {
        return "com.google.android.gms";
    }

    @Nullable
    private final String zzj() {
        return this.zzcn == null ? this.mContext.getClass().getName() : this.zzcn;
    }

    @Nullable
    @KeepForSdk
    protected String getLocalStartServiceAction() {
        return null;
    }

    private final void zza(zzb zzb) {
        this.zzcq = zzb;
    }

    @Nullable
    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        zzb zzb = this.zzcq;
        if (zzb == null) {
            return null;
        }
        return zzb.zzdb;
    }

    @KeepForSdk
    @CallSuper
    protected void onConnectedLocked(@NonNull T t) {
        this.zzbw = System.currentTimeMillis();
    }

    @KeepForSdk
    @CallSuper
    protected void onConnectionSuspended(int i) {
        this.zzbu = i;
        this.zzbv = System.currentTimeMillis();
    }

    @KeepForSdk
    @CallSuper
    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbx = connectionResult.getErrorCode();
        this.zzby = System.currentTimeMillis();
    }

    private final void zza(int i, T t) {
        boolean z = true;
        if ((i == 4) != (t != null)) {
            z = false;
        }
        Preconditions.checkArgument(z);
        synchronized (this.mLock) {
            this.zzcj = i;
            this.zzcg = t;
            onSetConnectState(i, t);
            switch (i) {
                case 1:
                    if (this.zzci != null) {
                        this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                        this.zzci = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    String zzt;
                    String packageName;
                    zzh zzh;
                    if (!(this.zzci == null || this.zzbz == null)) {
                        zzt = this.zzbz.zzt();
                        packageName = this.zzbz.getPackageName();
                        Log.e("GmsClient", new StringBuilder((String.valueOf(zzt).length() + 70) + String.valueOf(packageName).length()).append("Calling connect() while still connected, missing disconnect() for ").append(zzt).append(" on ").append(packageName).toString());
                        this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                        this.zzcr.incrementAndGet();
                    }
                    this.zzci = new zze(this, this.zzcr.get());
                    if (this.zzcj != 3 || getLocalStartServiceAction() == null) {
                        zzh = new zzh(getStartServicePackage(), getStartServiceAction(), false, 129);
                    } else {
                        zzh = new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129);
                    }
                    this.zzbz = zzh;
                    if (!this.zzcb.zza(new zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq()), this.zzci, zzj())) {
                        zzt = this.zzbz.zzt();
                        packageName = this.zzbz.getPackageName();
                        Log.e("GmsClient", new StringBuilder((String.valueOf(zzt).length() + 34) + String.valueOf(packageName).length()).append("unable to connect to service: ").append(zzt).append(" on ").append(packageName).toString());
                        zza(16, null, this.zzcr.get());
                        break;
                    }
                    break;
                case 4:
                    onConnectedLocked(t);
                    break;
            }
        }
    }

    @KeepForSdk
    void onSetConnectState(int i, T t) {
    }

    private final boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzcj != i) {
                z = false;
            } else {
                zza(i2, (IInterface) t);
                z = true;
            }
        }
        return z;
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzcc.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(this), isGooglePlayServicesAvailable, null);
            return;
        }
        connect(new LegacyClientCallbackAdapter(this));
    }

    @KeepForSdk
    public void connect(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, null);
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 4;
        }
        return z;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 2 || this.zzcj == 3;
        }
        return z;
    }

    private final boolean zzk() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcj == 3;
        }
        return z;
    }

    @KeepForSdk
    public void disconnect() {
        this.zzcr.incrementAndGet();
        synchronized (this.zzch) {
            int size = this.zzch.size();
            for (int i = 0; i < size; i++) {
                ((zzc) this.zzch.get(i)).removeListener();
            }
            this.zzch.clear();
        }
        synchronized (this.zzcd) {
            this.zzce = null;
        }
        zza(1, null);
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzcr.get(), i));
    }

    private final void zzb(int i) {
        int i2;
        if (zzk()) {
            i2 = 5;
            this.zzcp = true;
        } else {
            i2 = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i2, this.zzcr.get(), 16));
    }

    @KeepForSdk
    @VisibleForTesting
    protected void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, @Nullable PendingIntent pendingIntent) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzcr.get(), i, pendingIntent));
    }

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzca;
    }

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zzbt;
    }

    @KeepForSdk
    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @KeepForSdk
    protected void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzf(this, i, iBinder, bundle)));
    }

    protected final void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new zzg(this, i, null)));
    }

    @KeepForSdk
    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    @KeepForSdk
    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzcj == 5) {
                throw new DeadObjectException();
            }
            checkConnected();
            Preconditions.checkState(this.zzcg != null, "Client is connected but service is null");
            t = this.zzcg;
        }
        return t;
    }

    @WorkerThread
    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Throwable e;
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzcm);
        getServiceRequest.zzy = this.mContext.getPackageName();
        getServiceRequest.zzdk = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzdj = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdl = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (iAccountAccessor != null) {
                getServiceRequest.zzdi = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdl = getAccount();
        }
        getServiceRequest.zzdm = zzbt;
        getServiceRequest.zzdn = getApiFeatures();
        try {
            synchronized (this.zzcd) {
                if (this.zzce != null) {
                    this.zzce.getService(new zzd(this, this.zzcr.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (Throwable e2) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            triggerConnectionSuspended(1);
        } catch (SecurityException e3) {
            throw e3;
        } catch (RemoteException e4) {
            e2 = e4;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            onPostInitHandler(8, null, null, this.zzcr.get());
        } catch (RuntimeException e5) {
            e2 = e5;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            onPostInitHandler(8, null, null, this.zzcr.get());
        }
    }

    @KeepForSdk
    protected boolean enableLocalFallback() {
        return false;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    public void onUserSignOut(@NonNull SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
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
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    @KeepForSdk
    protected Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.mLock) {
            int i = this.zzcj;
            IInterface iInterface = this.zzcg;
        }
        synchronized (this.zzcd) {
            IGmsServiceBroker iGmsServiceBroker = this.zzce;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzbw > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzbw;
            String format = simpleDateFormat.format(new Date(this.zzbw));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(" ").append(format).toString());
        }
        if (this.zzbv > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzbu) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzbu));
                    break;
            }
            append = printWriter.append(" lastSuspendedTime=");
            j = this.zzbv;
            format = simpleDateFormat.format(new Date(this.zzbv));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(" ").append(format).toString());
        }
        if (this.zzby > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzbx));
            append = printWriter.append(" lastFailedTime=");
            j = this.zzby;
            String format2 = simpleDateFormat.format(new Date(this.zzby));
            append.println(new StringBuilder(String.valueOf(format2).length() + 21).append(j).append(" ").append(format2).toString());
        }
    }

    @Nullable
    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        IBinder iBinder;
        synchronized (this.zzcd) {
            if (this.zzce == null) {
                iBinder = null;
            } else {
                iBinder = this.zzce.asBinder();
            }
        }
        return iBinder;
    }

    private final boolean zzl() {
        if (this.zzcp || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        if (isConnected() && this.zzbz != null) {
            return this.zzbz.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}
