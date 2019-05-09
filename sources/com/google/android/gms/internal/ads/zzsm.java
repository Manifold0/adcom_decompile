package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzsm implements zzm {
    private final Context mContext;
    private final Object mLock = new Object();
    @Nullable
    @GuardedBy("mLock")
    private zzsf zzbnl;
    @GuardedBy("mLock")
    private boolean zzbnm;

    public zzsm(Context context) {
        this.mContext = context;
    }

    private final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzbnl == null) {
                return;
            }
            this.zzbnl.disconnect();
            this.zzbnl = null;
            Binder.flushPendingCommands();
        }
    }

    private final Future<ParcelFileDescriptor> zzb(zzsg zzsg) {
        Future zzsn = new zzsn(this);
        BaseConnectionCallbacks zzso = new zzso(this, zzsn, zzsg);
        BaseOnConnectionFailedListener zzsr = new zzsr(this, zzsn);
        synchronized (this.mLock) {
            this.zzbnl = new zzsf(this.mContext, zzbv.zzez().zzsa(), zzso, zzsr);
            this.zzbnl.checkAvailabilityAndConnect();
        }
        return zzsn;
    }

    public final zzp zzc(zzr<?> zzr) throws zzae {
        zzsg zzh = zzsg.zzh(zzr);
        long intValue = (long) ((Integer) zzkb.zzik().zzd(zznk.zzbdx)).intValue();
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        try {
            zzsi zzsi = (zzsi) new zzaev((ParcelFileDescriptor) zzb(zzh).get(intValue, TimeUnit.MILLISECONDS)).zza(zzsi.CREATOR);
            if (zzsi.zzbnj) {
                throw new zzae(zzsi.zzbnk);
            }
            zzp zzp;
            if (zzsi.zzbnh.length != zzsi.zzbni.length) {
                zzp = null;
            } else {
                Map hashMap = new HashMap();
                for (int i = 0; i < zzsi.zzbnh.length; i++) {
                    hashMap.put(zzsi.zzbnh[i], zzsi.zzbni[i]);
                }
                zzp = new zzp(zzsi.statusCode, zzsi.data, hashMap, zzsi.zzac, zzsi.zzad);
            }
            zzakb.m3915v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return zzp;
        } catch (InterruptedException e) {
            zzakb.m3915v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return null;
        } catch (ExecutionException e2) {
            zzakb.m3915v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return null;
        } catch (TimeoutException e3) {
            zzakb.m3915v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
            return null;
        } catch (Throwable th) {
            zzakb.m3915v("Http assets remote cache took " + (zzbv.zzer().elapsedRealtime() - elapsedRealtime) + "ms");
        }
    }
}
