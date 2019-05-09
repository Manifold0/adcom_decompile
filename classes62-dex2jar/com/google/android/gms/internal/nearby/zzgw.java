// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.nearby.messages.internal.zzaf;
import com.google.android.gms.nearby.messages.Message;
import java.util.Iterator;
import com.google.android.gms.nearby.messages.BleSignal;
import com.google.android.gms.nearby.messages.Distance;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.nearby.messages.internal.Update;
import java.util.Collections;
import android.content.Intent;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.internal.zzn;

public final class zzgw extends zzn
{
    private final ListenerHolder<MessageListener> zzjj;
    
    public zzgw(final ListenerHolder<MessageListener> zzjj) {
        this.zzjj = zzjj;
    }
    
    public static void zza(final Intent intent, final MessageListener messageListener) {
        final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.nearby.messages.UPDATES");
        List<Update> list;
        if (bundleExtra == null) {
            list = Collections.emptyList();
        }
        else {
            list = (List<Update>)bundleExtra.getParcelableArrayList("com.google.android.gms.nearby.messages.UPDATES");
        }
        zza(list, messageListener);
    }
    
    public static void zza(final Iterable<Update> iterable, final MessageListener messageListener) {
        for (final Update update : iterable) {
            if (update.zzg(1)) {
                messageListener.onFound(update.zzhk);
            }
            if (update.zzg(2)) {
                messageListener.onLost(update.zzhk);
            }
            if (update.zzg(4)) {
                messageListener.onDistanceChanged(update.zzhk, update.zzjf);
            }
            if (update.zzg(8)) {
                messageListener.onBleSignalChanged(update.zzhk, update.zzjg);
            }
            if (update.zzg(16)) {
                final Message zzhk = update.zzhk;
                final zzgs zzjh = update.zzjh;
            }
        }
    }
    
    @Override
    public final void zza(final zzaf zzaf) {
    }
    
    @Override
    public final void zza(final List<Update> list) throws RemoteException {
        this.zzjj.notifyListener((ListenerHolder$Notifier)new zzgx(this, list));
    }
    
    @Override
    public final void zzb(final zzaf zzaf) {
    }
}
