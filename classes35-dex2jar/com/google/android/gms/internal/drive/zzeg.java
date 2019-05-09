// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.events.zzk;
import com.google.android.gms.drive.events.zzr;
import com.google.android.gms.drive.events.zzl;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzd;
import com.google.android.gms.drive.events.zzn;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.events.zzo;
import com.google.android.gms.drive.events.zzq;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzi;
import android.util.Pair;
import android.os.Message;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;

final class zzeg extends Handler
{
    private final Context zzgu;
    
    private zzeg(final Looper looper, final Context zzgu) {
        super(looper);
        this.zzgu = zzgu;
    }
    
    public final void handleMessage(final Message message) {
        Label_0045: {
            switch (message.what) {
                default: {
                    zzee.zzbx.efmt("EventCallback", "Don't know how to handle this event in context %s", new Object[] { this.zzgu });
                    break;
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    final zzi zzi = (zzi)pair.first;
                    final DriveEvent driveEvent = (DriveEvent)pair.second;
                    switch (driveEvent.getType()) {
                        default: {
                            zzee.zzbx.wfmt("EventCallback", "Unexpected event: %s", new Object[] { driveEvent });
                            return;
                        }
                        case 1: {
                            ((ChangeListener)zzi).onChange((ChangeEvent)driveEvent);
                            return;
                        }
                        case 2: {
                            ((CompletionListener)zzi).onCompletion((CompletionEvent)driveEvent);
                            return;
                        }
                        case 3: {
                            final zzq zzq = (zzq)zzi;
                            final zzo zzo = (zzo)driveEvent;
                            final DataHolder zzy = zzo.zzy();
                            if (zzy != null) {
                                zzq.zza(new zzeh(new MetadataBuffer(zzy)));
                            }
                            if (zzo.zzz()) {
                                zzq.zzc(zzo.zzaa());
                                return;
                            }
                            break Label_0045;
                        }
                        case 4: {
                            ((zzd)zzi).zza((zzb)driveEvent);
                            return;
                        }
                        case 8: {
                            ((zzl)zzi).zza(new zze(((zzr)driveEvent).zzab()));
                            return;
                        }
                    }
                    break;
                }
            }
        }
    }
}
