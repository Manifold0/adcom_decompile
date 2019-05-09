// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class zzf implements zzt
{
    private final Map<String, List<zzr<?>>> zzp;
    private final zzd zzq;
    
    zzf(final zzd zzq) {
        this.zzp = new HashMap<String, List<zzr<?>>>();
        this.zzq = zzq;
    }
    
    private final boolean zzb(final zzr<?> zzr) {
        final boolean b = false;
        synchronized (this) {
            final String url = zzr.getUrl();
            boolean b2;
            if (this.zzp.containsKey(url)) {
                List<zzr<?>> list;
                if ((list = this.zzp.get(url)) == null) {
                    list = new ArrayList<zzr<?>>();
                }
                zzr.zzb("waiting-for-response");
                list.add(zzr);
                this.zzp.put(url, list);
                if (zzaf.DEBUG) {
                    zzaf.d("Request for cacheKey=%s is in flight, putting on hold.", url);
                }
                b2 = true;
            }
            else {
                this.zzp.put(url, null);
                zzr.zza(this);
                b2 = b;
                if (zzaf.DEBUG) {
                    zzaf.d("new request, sending to network %s", url);
                    b2 = b;
                }
            }
            return b2;
        }
    }
    
    @Override
    public final void zza(final zzr<?> zzr) {
        synchronized (this) {
            final String url = zzr.getUrl();
            final List<zzr<?>> list = this.zzp.remove(url);
            if (list == null || list.isEmpty()) {
                return;
            }
            if (zzaf.DEBUG) {
                zzaf.v("%d waiting requests for cacheKey=%s; resend to network", list.size(), url);
            }
            final zzr<?> zzr2 = list.remove(0);
            this.zzp.put(url, list);
            zzr2.zza(this);
            try {
                this.zzq.zzi.put(zzr2);
            }
            catch (InterruptedException ex) {
                zzaf.e("Couldn't add request to queue. %s", ex.toString());
                Thread.currentThread().interrupt();
                this.zzq.quit();
            }
        }
    }
    
    @Override
    public final void zza(final zzr<?> zzr, final zzx<?> zzx) {
        if (zzx.zzbg == null || zzx.zzbg.zzb()) {
            this.zza(zzr);
        }
        else {
            final String url = zzr.getUrl();
            synchronized (this) {
                final List<zzr<?>> list = this.zzp.remove(url);
                // monitorexit(this)
                if (list != null) {
                    if (zzaf.DEBUG) {
                        zzaf.v("Releasing %d waiting requests for cacheKey=%s.", list.size(), url);
                    }
                    final Iterator<zzr<?>> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        this.zzq.zzk.zzb(iterator.next(), zzx);
                    }
                }
            }
        }
    }
}
