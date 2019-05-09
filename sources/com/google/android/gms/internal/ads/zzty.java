package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzty {
    private final LinkedList<zztz> zzbon = new LinkedList();
    private zzjj zzboo;
    private final int zzbop;
    private boolean zzboq;
    private final String zzye;

    zzty(zzjj zzjj, String str, int i) {
        Preconditions.checkNotNull(zzjj);
        Preconditions.checkNotNull(str);
        this.zzboo = zzjj;
        this.zzye = str;
        this.zzbop = i;
    }

    final String getAdUnitId() {
        return this.zzye;
    }

    final int getNetworkType() {
        return this.zzbop;
    }

    final int size() {
        return this.zzbon.size();
    }

    final void zza(zzss zzss, zzjj zzjj) {
        this.zzbon.add(new zztz(this, zzss, zzjj));
    }

    final boolean zzb(zzss zzss) {
        zztz zztz = new zztz(this, zzss);
        this.zzbon.add(zztz);
        return zztz.load();
    }

    final zztz zzl(@Nullable zzjj zzjj) {
        if (zzjj != null) {
            this.zzboo = zzjj;
        }
        return (zztz) this.zzbon.remove();
    }

    final zzjj zzlf() {
        return this.zzboo;
    }

    final int zzlg() {
        Iterator it = this.zzbon.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zztz) it.next()).zzwa ? i + 1 : i;
        }
        return i;
    }

    final int zzlh() {
        Iterator it = this.zzbon.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zztz) it.next()).load() ? i + 1 : i;
        }
        return i;
    }

    final void zzli() {
        this.zzboq = true;
    }

    final boolean zzlj() {
        return this.zzboq;
    }
}
