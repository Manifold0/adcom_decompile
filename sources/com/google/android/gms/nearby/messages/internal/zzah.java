package com.google.android.gms.nearby.messages.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.nearby.zzgw;
import com.google.android.gms.internal.nearby.zzgy;
import com.google.android.gms.internal.nearby.zzhb;
import com.google.android.gms.internal.nearby.zzhd;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;

public final class zzah extends GmsClient<zzs> {
    private final int zzfh;
    private final ClientAppContext zzhi;
    private final zzhd<ListenerKey, IBinder> zzhl = new zzhd();

    @TargetApi(14)
    zzah(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings, MessagesOptions messagesOptions) {
        super(context, looper, 62, clientSettings, connectionCallbacks, onConnectionFailedListener);
        String realClientPackageName = clientSettings.getRealClientPackageName();
        int zzb = zzb(context);
        if (messagesOptions != null) {
            this.zzhi = new ClientAppContext(realClientPackageName, null, false, null, zzb);
            this.zzfh = messagesOptions.zzfh;
        } else {
            this.zzhi = new ClientAppContext(realClientPackageName, null, false, null, zzb);
            this.zzfh = -1;
        }
        if (zzb == 1 && PlatformVersion.isAtLeastIceCreamSandwich()) {
            Activity activity = (Activity) context;
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Registering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", new Object[]{activity.getPackageName()}));
            }
            activity.getApplication().registerActivityLifecycleCallbacks(new zzaj(activity, this));
        }
    }

    static int zzb(Context context) {
        return context instanceof Activity ? 1 : context instanceof Application ? 2 : context instanceof Service ? 3 : 0;
    }

    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesService");
        return queryLocalInterface instanceof zzs ? (zzs) queryLocalInterface : new zzt(iBinder);
    }

    public final void disconnect() {
        try {
            zzf(2);
        } catch (RemoteException e) {
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Failed to emit CLIENT_DISCONNECTED from override of GmsClient#disconnect(): %s", new Object[]{e}));
            }
        }
        this.zzhl.clear();
        super.disconnect();
    }

    @NonNull
    protected final Bundle getGetServiceRequestExtraArgs() {
        Bundle getServiceRequestExtraArgs = super.getGetServiceRequestExtraArgs();
        getServiceRequestExtraArgs.putInt("NearbyPermissions", this.zzfh);
        getServiceRequestExtraArgs.putParcelable("ClientAppContext", this.zzhi);
        return getServiceRequestExtraArgs;
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    @NonNull
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.nearby.messages.internal.INearbyMessagesService";
    }

    @NonNull
    protected final String getStartServiceAction() {
        return "com.google.android.gms.nearby.messages.service.NearbyMessagesService.START";
    }

    public final boolean requiresGooglePlayServices() {
        return Nearby.zza(getContext());
    }

    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, PendingIntent pendingIntent) throws RemoteException {
        ((zzs) getService()).zza(new zzcg(null, new zzgy(listenerHolder), pendingIntent));
    }

    @Deprecated
    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, PendingIntent pendingIntent, @Nullable zzab zzab, SubscribeOptions subscribeOptions) throws RemoteException {
        zza((ListenerHolder) listenerHolder, pendingIntent, zzab, subscribeOptions, this.zzhi.zzhf);
    }

    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, PendingIntent pendingIntent, @Nullable zzab zzab, SubscribeOptions subscribeOptions, int i) throws RemoteException {
        ((zzs) getService()).zza(new SubscribeRequest(null, subscribeOptions.getStrategy(), new zzgy(listenerHolder), subscribeOptions.getFilter(), pendingIntent, null, zzab, subscribeOptions.zzgb, subscribeOptions.zzgc, this.zzhi.zzhf));
    }

    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, ListenerHolder<MessageListener> listenerHolder2) throws RemoteException {
        IBinder zzgy = new zzgy(listenerHolder);
        if (this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            ((zzs) getService()).zza(new zzcg((IBinder) this.zzhl.get(listenerHolder2.getListenerKey()), zzgy, null));
            this.zzhl.remove(listenerHolder2.getListenerKey());
            return;
        }
        zzgy.zza(new Status(0));
    }

    @Deprecated
    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, ListenerHolder<MessageListener> listenerHolder2, @Nullable zzab zzab, SubscribeOptions subscribeOptions, @Nullable byte[] bArr) throws RemoteException {
        zza(listenerHolder, listenerHolder2, zzab, subscribeOptions, null, this.zzhi.zzhf);
    }

    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, ListenerHolder<MessageListener> listenerHolder2, @Nullable zzab zzab, SubscribeOptions subscribeOptions, @Nullable byte[] bArr, int i) throws RemoteException {
        if (!this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            this.zzhl.zza(listenerHolder2.getListenerKey(), new zzgw(listenerHolder2));
        }
        ((zzs) getService()).zza(new SubscribeRequest((IBinder) this.zzhl.get(listenerHolder2.getListenerKey()), subscribeOptions.getStrategy(), new zzgy(listenerHolder), subscribeOptions.getFilter(), null, null, zzab, subscribeOptions.zzgb, i));
    }

    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, zzaf zzaf) throws RemoteException {
        ((zzs) getService()).zza(new zzce(zzaf, new zzgy(listenerHolder)));
    }

    @Deprecated
    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, zzaf zzaf, @Nullable zzv zzv, PublishOptions publishOptions) throws RemoteException {
        zza((ListenerHolder) listenerHolder, zzaf, zzv, publishOptions, this.zzhi.zzhf);
    }

    final void zza(ListenerHolder<ResultHolder<Status>> listenerHolder, zzaf zzaf, @Nullable zzv zzv, PublishOptions publishOptions, int i) throws RemoteException {
        ((zzs) getService()).zza(new zzbz(zzaf, publishOptions.getStrategy(), new zzgy(listenerHolder), zzv, i));
    }

    final void zzb(ListenerHolder<ResultHolder<Status>> listenerHolder, ListenerHolder<StatusCallback> listenerHolder2) throws RemoteException {
        if (!this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            this.zzhl.zza(listenerHolder2.getListenerKey(), new zzhb(listenerHolder2));
        }
        zzcb zzcb = new zzcb(new zzgy(listenerHolder), (IBinder) this.zzhl.get(listenerHolder2.getListenerKey()));
        zzcb.zzix = true;
        ((zzs) getService()).zza(zzcb);
    }

    final void zzc(ListenerHolder<ResultHolder<Status>> listenerHolder, ListenerHolder<StatusCallback> listenerHolder2) throws RemoteException {
        IBinder zzgy = new zzgy(listenerHolder);
        if (this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            zzcb zzcb = new zzcb(zzgy, (IBinder) this.zzhl.get(listenerHolder2.getListenerKey()));
            zzcb.zzix = false;
            ((zzs) getService()).zza(zzcb);
            this.zzhl.remove(listenerHolder2.getListenerKey());
            return;
        }
        zzgy.zza(new Status(0));
    }

    final void zzf(int i) throws RemoteException {
        String str;
        switch (i) {
            case 1:
                str = "ACTIVITY_STOPPED";
                break;
            case 2:
                str = "CLIENT_DISCONNECTED";
                break;
            default:
                if (Log.isLoggable("NearbyMessagesClient", 5)) {
                    Log.w("NearbyMessagesClient", String.format("Received unknown/unforeseen client lifecycle event %d, can't do anything with it.", new Object[]{Integer.valueOf(i)}));
                    return;
                }
                return;
        }
        if (isConnected()) {
            zzj zzj = new zzj(i);
            if (Log.isLoggable("NearbyMessagesClient", 3)) {
                Log.d("NearbyMessagesClient", String.format("Emitting client lifecycle event %s", new Object[]{str}));
            }
            ((zzs) getService()).zza(zzj);
        } else if (Log.isLoggable("NearbyMessagesClient", 3)) {
            Log.d("NearbyMessagesClient", String.format("Failed to emit client lifecycle event %s due to GmsClient being disconnected", new Object[]{str}));
        }
    }
}
