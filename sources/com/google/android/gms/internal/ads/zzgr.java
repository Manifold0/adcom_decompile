package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzadh
public final class zzgr {
    private final int zzaiy;
    private final int zzaiz;
    private final int zzaja;
    private final zzgq zzajb = new zzgv();

    public zzgr(int i) {
        this.zzaiz = i;
        this.zzaiy = 6;
        this.zzaja = 0;
    }

    @VisibleForTesting
    private final String zzy(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        zzgt zzgt = new zzgt();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzaiz, new zzgs(this));
        for (String zzb : split) {
            String[] zzb2 = zzgu.zzb(zzb, false);
            if (zzb2.length != 0) {
                zzgx.zza(zzb2, this.zzaiz, this.zzaiy, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzgt.write(this.zzajb.zzx(((zzgy) it.next()).zzajf));
            } catch (Throwable e) {
                zzakb.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzgt.toString();
    }

    public final String zza(ArrayList<String> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            stringBuilder.append(((String) obj).toLowerCase(Locale.US));
            stringBuilder.append('\n');
        }
        return zzy(stringBuilder.toString());
    }
}
