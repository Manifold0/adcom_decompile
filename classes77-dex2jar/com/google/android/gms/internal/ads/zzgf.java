// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.Iterator;
import com.google.android.gms.ads.internal.zzbv;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgf
{
    private final Object mLock;
    private int zzahm;
    private List<zzge> zzahn;
    
    public zzgf() {
        this.mLock = new Object();
        this.zzahn = new LinkedList<zzge>();
    }
    
    public final boolean zza(final zzge zzge) {
        synchronized (this.mLock) {
            return this.zzahn.contains(zzge);
        }
    }
    
    public final boolean zzb(final zzge zzge) {
        synchronized (this.mLock) {
            final Iterator<zzge> iterator = this.zzahn.iterator();
            while (iterator.hasNext()) {
                final zzge zzge2 = iterator.next();
                if ((boolean)zzkb.zzik().zzd(zznk.zzawq) && !zzbv.zzeo().zzqh().zzqu()) {
                    if (zzge != zzge2 && zzge2.getSignature().equals(zzge.getSignature())) {
                        iterator.remove();
                        return true;
                    }
                    continue;
                }
                else {
                    if ((boolean)zzkb.zzik().zzd(zznk.zzaws) && !zzbv.zzeo().zzqh().zzqw() && zzge != zzge2 && zzge2.zzgp().equals(zzge.zzgp())) {
                        iterator.remove();
                        return true;
                    }
                    continue;
                }
            }
            return false;
        }
    }
    
    public final void zzc(final zzge zzge) {
        synchronized (this.mLock) {
            if (this.zzahn.size() >= 10) {
                zzakb.zzck(new StringBuilder(41).append("Queue is full, current size = ").append(this.zzahn.size()).toString());
                this.zzahn.remove(0);
            }
            zzge.zzo(this.zzahm++);
            this.zzahn.add(zzge);
        }
    }
    
    @Nullable
    public final zzge zzgv() {
        while (true) {
            final zzge zzge = null;
            int n = 0;
            while (true) {
                int n2 = 0;
                int n3 = 0;
                Label_0174: {
                    Label_0163: {
                        synchronized (this.mLock) {
                            if (this.zzahn.size() == 0) {
                                zzakb.zzck("Queue empty");
                                return null;
                            }
                            if (this.zzahn.size() >= 2) {
                                n2 = Integer.MIN_VALUE;
                                final Iterator<zzge> iterator = this.zzahn.iterator();
                                n3 = 0;
                                if (!iterator.hasNext()) {
                                    this.zzahn.remove(n);
                                    return zzge;
                                }
                                final int score = iterator.next().getScore();
                                if (score > n2) {
                                    n = score;
                                    n2 = n3;
                                    break Label_0174;
                                }
                                break Label_0163;
                            }
                        }
                        break;
                    }
                    final int n4 = n2;
                    n2 = n;
                    n = n4;
                }
                ++n3;
                final int n5 = n;
                n = n2;
                n2 = n5;
                continue;
            }
        }
        final zzge zzge2 = this.zzahn.get(0);
        zzge2.zzgq();
        // monitorexit(o)
        return zzge2;
    }
}
