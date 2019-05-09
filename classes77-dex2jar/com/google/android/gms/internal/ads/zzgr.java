// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Locale;
import java.util.ArrayList;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

@zzadh
public final class zzgr
{
    private final int zzaiy;
    private final int zzaiz;
    private final int zzaja;
    private final zzgq zzajb;
    
    public zzgr(final int zzaiz) {
        this.zzajb = new zzgv();
        this.zzaiz = zzaiz;
        this.zzaiy = 6;
        this.zzaja = 0;
    }
    
    @VisibleForTesting
    private final String zzy(String s) {
        final String[] split = s.split("\n");
        if (split.length == 0) {
            return "";
        }
        s = (String)new zzgt();
        final PriorityQueue<zzgy> priorityQueue = new PriorityQueue<zzgy>(this.zzaiz, new zzgs(this));
        for (int i = 0; i < split.length; ++i) {
            final String[] zzb = zzgu.zzb(split[i], false);
            if (zzb.length != 0) {
                zzgx.zza(zzb, this.zzaiz, this.zzaiy, priorityQueue);
            }
        }
        for (final zzgy zzgy : priorityQueue) {
            try {
                ((zzgt)s).write(this.zzajb.zzx(zzgy.zzajf));
                continue;
            }
            catch (IOException ex) {
                zzakb.zzb("Error while writing hash to byteStream", (Throwable)ex);
            }
            break;
        }
        return ((zzgt)s).toString();
    }
    
    public final String zza(final ArrayList<String> list) {
        final StringBuilder sb = new StringBuilder();
        final ArrayList<String> list2 = list;
        final int size = list2.size();
        int i = 0;
        while (i < size) {
            final String value = list2.get(i);
            ++i;
            sb.append(value.toLowerCase(Locale.US));
            sb.append('\n');
        }
        return this.zzy(sb.toString());
    }
}
