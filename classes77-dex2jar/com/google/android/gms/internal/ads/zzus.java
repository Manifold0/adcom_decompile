// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.net.Uri;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.common.util.Predicate;
import java.util.Iterator;
import java.util.HashMap;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzus<ReferenceT>
{
    @GuardedBy("this")
    private final Map<String, CopyOnWriteArrayList<zzv<? super ReferenceT>>> zzbpn;
    
    public zzus() {
        this.zzbpn = new HashMap<String, CopyOnWriteArrayList<zzv<? super ReferenceT>>>();
    }
    
    private final void zzb(final String s, final Map<String, String> map) {
        while (true) {
            while (true) {
                synchronized (this) {
                    if (!zzakb.isLoggable(2)) {
                        break;
                    }
                    final String value = String.valueOf(s);
                    if (value.length() != 0) {
                        final String concat = "Received GMSG: ".concat(value);
                        zzakb.v(concat);
                        for (final String s2 : map.keySet()) {
                            final String s3 = map.get(s2);
                            zzakb.v(new StringBuilder(String.valueOf(s2).length() + 4 + String.valueOf(s3).length()).append("  ").append(s2).append(": ").append(s3).toString());
                        }
                        break;
                    }
                }
                final String concat = new String("Received GMSG: ");
                continue;
            }
        }
        final Throwable t;
        final CopyOnWriteArrayList<zzv<? super ReferenceT>> list = this.zzbpn.get(t);
        if (list != null) {
            final Iterator<zzv<? super ReferenceT>> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                zzaoe.zzcvy.execute(new zzut(this, iterator2.next(), map));
            }
        }
    }
    // monitorexit(this)
    
    public abstract ReferenceT getReference();
    
    public void reset() {
        synchronized (this) {
            this.zzbpn.clear();
        }
    }
    
    public final void zza(final String s, final zzv<? super ReferenceT> zzv) {
        synchronized (this) {
            CopyOnWriteArrayList<zzv<? super ReferenceT>> list;
            if ((list = this.zzbpn.get(s)) == null) {
                list = new CopyOnWriteArrayList<zzv<? super ReferenceT>>();
                this.zzbpn.put(s, list);
            }
            list.add(zzv);
        }
    }
    
    public final void zza(final String s, final Predicate<zzv<? super ReferenceT>> predicate) {
        while (true) {
            final ArrayList<zzv<? super ReferenceT>> list2;
            synchronized (this) {
                final CopyOnWriteArrayList<zzv<? super ReferenceT>> list = this.zzbpn.get(s);
                if (list == null) {
                    return;
                }
                list2 = new ArrayList<zzv<? super ReferenceT>>();
                for (final zzv<? super ReferenceT> zzv : list) {
                    if (predicate.apply((Object)zzv)) {
                        list2.add(zzv);
                    }
                }
            }
            final CopyOnWriteArrayList list3;
            list3.removeAll(list2);
        }
    }
    
    public final void zzb(final String s, final zzv<? super ReferenceT> zzv) {
        synchronized (this) {
            final CopyOnWriteArrayList<zzv<? super ReferenceT>> list = this.zzbpn.get(s);
            if (list != null) {
                list.remove(zzv);
            }
        }
    }
    
    public final boolean zzf(final Uri uri) {
        if ("gmsg".equalsIgnoreCase(uri.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            final String path = uri.getPath();
            zzbv.zzek();
            this.zzb(path, zzakk.zzg(uri));
            return true;
        }
        return false;
    }
}
