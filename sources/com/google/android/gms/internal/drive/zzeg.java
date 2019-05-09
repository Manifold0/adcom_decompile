package com.google.android.gms.internal.drive;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzd;
import com.google.android.gms.drive.events.zzi;
import com.google.android.gms.drive.events.zzl;
import com.google.android.gms.drive.events.zzo;
import com.google.android.gms.drive.events.zzq;
import com.google.android.gms.drive.events.zzr;

final class zzeg extends Handler {
    private final Context zzgu;

    private zzeg(Looper looper, Context context) {
        super(looper);
        this.zzgu = context;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                Pair pair = (Pair) message.obj;
                zzi zzi = (zzi) pair.first;
                DriveEvent driveEvent = (DriveEvent) pair.second;
                switch (driveEvent.getType()) {
                    case 1:
                        ((ChangeListener) zzi).onChange((ChangeEvent) driveEvent);
                        return;
                    case 2:
                        ((CompletionListener) zzi).onCompletion((CompletionEvent) driveEvent);
                        return;
                    case 3:
                        zzq zzq = (zzq) zzi;
                        zzo zzo = (zzo) driveEvent;
                        DataHolder zzy = zzo.zzy();
                        if (zzy != null) {
                            zzq.zza(new zzeh(new MetadataBuffer(zzy)));
                        }
                        if (zzo.zzz()) {
                            zzq.zzc(zzo.zzaa());
                            return;
                        }
                        return;
                    case 4:
                        ((zzd) zzi).zza((zzb) driveEvent);
                        return;
                    case 8:
                        ((zzl) zzi).zza(new zze(((zzr) driveEvent).zzab()));
                        return;
                    default:
                        zzee.zzbx.wfmt("EventCallback", "Unexpected event: %s", new Object[]{driveEvent});
                        return;
                }
            default:
                zzee.zzbx.efmt("EventCallback", "Don't know how to handle this event in context %s", new Object[]{this.zzgu});
                return;
        }
    }
}
