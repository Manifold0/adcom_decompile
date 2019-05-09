// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;
import com.google.android.gms.ads.internal.zzbv;
import android.os.ParcelFileDescriptor;
import java.util.concurrent.Future;
import android.os.Binder;
import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import android.content.Context;

@zzadh
public final class zzsm implements zzm
{
    private final Context mContext;
    private final Object mLock;
    @Nullable
    @GuardedBy("mLock")
    private zzsf zzbnl;
    @GuardedBy("mLock")
    private boolean zzbnm;
    
    public zzsm(final Context mContext) {
        this.mLock = new Object();
        this.mContext = mContext;
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
    
    private final Future<ParcelFileDescriptor> zzb(final zzsg zzsg) {
        final zzsn zzsn = new zzsn(this);
        final zzso zzso = new zzso(this, zzsn, zzsg);
        final zzsr zzsr = new zzsr(this, zzsn);
        synchronized (this.mLock) {
            (this.zzbnl = new zzsf(this.mContext, zzbv.zzez().zzsa(), (BaseGmsClient$BaseConnectionCallbacks)zzso, (BaseGmsClient$BaseOnConnectionFailedListener)zzsr)).checkAvailabilityAndConnect();
            return zzsn;
        }
    }
    
    @Override
    public final zzp zzc(final zzr<?> zzr) throws zzae {
        final zzsg zzh = zzsg.zzh(zzr);
        final long n = (int)zzkb.zzik().zzd(zznk.zzbdx);
        final long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        Label_0137: {
            try {
                final zzsi zzsi = new zzaev(this.zzb(zzh).get(n, TimeUnit.MILLISECONDS)).zza(com.google.android.gms.internal.ads.zzsi.CREATOR);
                if (zzsi.zzbnj) {
                    throw new zzae(zzsi.zzbnk);
                }
                break Label_0137;
            }
            catch (InterruptedException ex) {
                return null;
                return null;
                // iftrue(Label_0201:, zzsi2.zzbnh.length == zzsi2.zzbni.length)
                return null;
                while (true) {
                    final zzsi zzsi2;
                    int n2 = 0;
                    final HashMap<String, String> hashMap;
                    Block_5: {
                        break Block_5;
                        Label_0201: {
                            hashMap = new HashMap<String, String>();
                        }
                        n2 = 0;
                        continue;
                        Label_0246:
                        zzp = new zzp(zzsi2.statusCode, zzsi2.data, hashMap, zzsi2.zzac, zzsi2.zzad);
                        return zzp;
                    }
                    hashMap.put(zzsi2.zzbnh[n2], zzsi2.zzbni[n2]);
                    ++n2;
                    continue;
                }
            }
            // iftrue(Label_0246:, n2 >= zzsi2.zzbnh.length)
            catch (ExecutionException ex2) {
                return null;
            }
            catch (TimeoutException ex3) {
                return null;
            }
            finally {
                zzakb.v(new StringBuilder(52).append("Http assets remote cache took ").append(zzbv.zzer().elapsedRealtime() - elapsedRealtime).append("ms").toString());
            }
        }
    }
}
