package com.google.android.gms.internal.nearby;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.internal.Update;
import com.google.android.gms.nearby.messages.internal.zzaf;
import com.google.android.gms.nearby.messages.internal.zzn;
import java.util.Collections;
import java.util.List;

public final class zzgw extends zzn {
    private final ListenerHolder<MessageListener> zzjj;

    public zzgw(ListenerHolder<MessageListener> listenerHolder) {
        this.zzjj = listenerHolder;
    }

    public static void zza(Intent intent, MessageListener messageListener) {
        String str = "com.google.android.gms.nearby.messages.UPDATES";
        Bundle bundleExtra = intent.getBundleExtra(str);
        zza(bundleExtra == null ? Collections.emptyList() : bundleExtra.getParcelableArrayList(str), messageListener);
    }

    public static void zza(Iterable<Update> iterable, MessageListener messageListener) {
        for (Update update : iterable) {
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
                Message message = update.zzhk;
                zzgs zzgs = update.zzjh;
            }
        }
    }

    public final void zza(zzaf zzaf) {
    }

    public final void zza(List<Update> list) throws RemoteException {
        this.zzjj.notifyListener(new zzgx(this, list));
    }

    public final void zzb(zzaf zzaf) {
    }
}
