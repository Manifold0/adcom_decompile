// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.internal.nearby.zzhb;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.internal.nearby.zzgw;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.nearby.zzgy;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.Nearby;
import android.support.annotation.NonNull;
import android.os.Parcelable;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;
import android.app.Service;
import android.app.Application;
import android.annotation.TargetApi;
import android.app.Application$ActivityLifecycleCallbacks;
import android.util.Log;
import android.app.Activity;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.internal.nearby.zzhd;
import com.google.android.gms.common.internal.GmsClient;

public final class zzah extends GmsClient<zzs>
{
    private final int zzfh;
    private final ClientAppContext zzhi;
    private final zzhd<ListenerHolder$ListenerKey, IBinder> zzhl;
    
    @TargetApi(14)
    zzah(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final ClientSettings clientSettings, final MessagesOptions messagesOptions) {
        super(context, looper, 62, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzhl = new zzhd<ListenerHolder$ListenerKey, IBinder>();
        final String realClientPackageName = clientSettings.getRealClientPackageName();
        final int zzb = zzb(context);
        if (messagesOptions != null) {
            this.zzhi = new ClientAppContext(realClientPackageName, null, false, null, zzb);
            this.zzfh = messagesOptions.zzfh;
        }
        else {
            this.zzhi = new ClientAppContext(realClientPackageName, null, false, null, zzb);
            this.zzfh = -1;
        }
        if (zzb == 1 && PlatformVersion.isAtLeastIceCreamSandwich()) {
            final Activity activity = (Activity)context;
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Registering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", activity.getPackageName()));
            }
            activity.getApplication().registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new zzaj(activity, this, null));
        }
    }
    
    static int zzb(final Context context) {
        if (context instanceof Activity) {
            return 1;
        }
        if (context instanceof Application) {
            return 2;
        }
        if (context instanceof Service) {
            return 3;
        }
        return 0;
    }
    
    public final void disconnect() {
        while (true) {
            try {
                this.zzf(2);
                this.zzhl.clear();
                super.disconnect();
            }
            catch (RemoteException ex) {
                if (Log.isLoggable("NearbyMessagesClient", 2)) {
                    Log.v("NearbyMessagesClient", String.format("Failed to emit CLIENT_DISCONNECTED from override of GmsClient#disconnect(): %s", ex));
                }
                continue;
            }
            break;
        }
    }
    
    @NonNull
    protected final Bundle getGetServiceRequestExtraArgs() {
        final Bundle getServiceRequestExtraArgs = super.getGetServiceRequestExtraArgs();
        getServiceRequestExtraArgs.putInt("NearbyPermissions", this.zzfh);
        getServiceRequestExtraArgs.putParcelable("ClientAppContext", (Parcelable)this.zzhi);
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
        return Nearby.zza(this.getContext());
    }
    
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final PendingIntent pendingIntent) throws RemoteException {
        ((zzs)this.getService()).zza(new zzcg(null, (IBinder)new zzgy(listenerHolder), pendingIntent));
    }
    
    @Deprecated
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final PendingIntent pendingIntent, @Nullable final zzab zzab, final SubscribeOptions subscribeOptions) throws RemoteException {
        this.zza(listenerHolder, pendingIntent, zzab, subscribeOptions, this.zzhi.zzhf);
    }
    
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final PendingIntent pendingIntent, @Nullable final zzab zzab, final SubscribeOptions subscribeOptions, final int n) throws RemoteException {
        ((zzs)this.getService()).zza(new SubscribeRequest(null, subscribeOptions.getStrategy(), (IBinder)new zzgy(listenerHolder), subscribeOptions.getFilter(), pendingIntent, null, (IBinder)zzab, subscribeOptions.zzgb, subscribeOptions.zzgc, this.zzhi.zzhf));
    }
    
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final ListenerHolder<MessageListener> listenerHolder2) throws RemoteException {
        final zzgy zzgy = new zzgy(listenerHolder);
        if (!this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            zzgy.zza(new Status(0));
            return;
        }
        ((zzs)this.getService()).zza(new zzcg(this.zzhl.get(listenerHolder2.getListenerKey()), (IBinder)zzgy, null));
        this.zzhl.remove(listenerHolder2.getListenerKey());
    }
    
    @Deprecated
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final ListenerHolder<MessageListener> listenerHolder2, @Nullable final zzab zzab, final SubscribeOptions subscribeOptions, @Nullable final byte[] array) throws RemoteException {
        this.zza(listenerHolder, listenerHolder2, zzab, subscribeOptions, null, this.zzhi.zzhf);
    }
    
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final ListenerHolder<MessageListener> listenerHolder2, @Nullable final zzab zzab, final SubscribeOptions subscribeOptions, @Nullable final byte[] array, final int n) throws RemoteException {
        if (!this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            this.zzhl.zza(listenerHolder2.getListenerKey(), (IBinder)new zzgw(listenerHolder2));
        }
        ((zzs)this.getService()).zza(new SubscribeRequest(this.zzhl.get(listenerHolder2.getListenerKey()), subscribeOptions.getStrategy(), (IBinder)new zzgy(listenerHolder), subscribeOptions.getFilter(), null, null, (IBinder)zzab, subscribeOptions.zzgb, n));
    }
    
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final zzaf zzaf) throws RemoteException {
        ((zzs)this.getService()).zza(new zzce(zzaf, (IBinder)new zzgy(listenerHolder)));
    }
    
    @Deprecated
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final zzaf zzaf, @Nullable final zzv zzv, final PublishOptions publishOptions) throws RemoteException {
        this.zza(listenerHolder, zzaf, zzv, publishOptions, this.zzhi.zzhf);
    }
    
    final void zza(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final zzaf zzaf, @Nullable final zzv zzv, final PublishOptions publishOptions, final int n) throws RemoteException {
        ((zzs)this.getService()).zza(new zzbz(zzaf, publishOptions.getStrategy(), (IBinder)new zzgy(listenerHolder), (IBinder)zzv, n));
    }
    
    final void zzb(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final ListenerHolder<StatusCallback> listenerHolder2) throws RemoteException {
        if (!this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            this.zzhl.zza(listenerHolder2.getListenerKey(), (IBinder)new zzhb(listenerHolder2));
        }
        final zzcb zzcb = new zzcb((IBinder)new zzgy(listenerHolder), this.zzhl.get(listenerHolder2.getListenerKey()));
        zzcb.zzix = true;
        ((zzs)this.getService()).zza(zzcb);
    }
    
    final void zzc(final ListenerHolder<BaseImplementation$ResultHolder<Status>> listenerHolder, final ListenerHolder<StatusCallback> listenerHolder2) throws RemoteException {
        final zzgy zzgy = new zzgy(listenerHolder);
        if (!this.zzhl.containsKey(listenerHolder2.getListenerKey())) {
            zzgy.zza(new Status(0));
            return;
        }
        final zzcb zzcb = new zzcb((IBinder)zzgy, this.zzhl.get(listenerHolder2.getListenerKey()));
        zzcb.zzix = false;
        ((zzs)this.getService()).zza(zzcb);
        this.zzhl.remove(listenerHolder2.getListenerKey());
    }
    
    final void zzf(final int n) throws RemoteException {
        String s = null;
        Label_0061: {
            switch (n) {
                default: {
                    if (Log.isLoggable("NearbyMessagesClient", 5)) {
                        Log.w("NearbyMessagesClient", String.format("Received unknown/unforeseen client lifecycle event %d, can't do anything with it.", n));
                        break;
                    }
                    break;
                }
                case 1: {
                    s = "ACTIVITY_STOPPED";
                    break Label_0061;
                }
                case 2: {
                    s = "CLIENT_DISCONNECTED";
                    break Label_0061;
                }
            }
            return;
        }
        if (this.isConnected()) {
            final zzj zzj = new zzj(n);
            if (Log.isLoggable("NearbyMessagesClient", 3)) {
                Log.d("NearbyMessagesClient", String.format("Emitting client lifecycle event %s", s));
            }
            ((zzs)this.getService()).zza(zzj);
            return;
        }
        if (Log.isLoggable("NearbyMessagesClient", 3)) {
            Log.d("NearbyMessagesClient", String.format("Failed to emit client lifecycle event %s due to GmsClient being disconnected", s));
        }
    }
}
