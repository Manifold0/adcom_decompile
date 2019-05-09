// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.PriorityQueue;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgx
{
    @VisibleForTesting
    private static long zza(final long n, final int n2) {
        long n3;
        if (n2 == 0) {
            n3 = 1L;
        }
        else {
            n3 = n;
            if (n2 != 1) {
                if (n2 % 2 == 0) {
                    return zza(n * n % 1073807359L, n2 / 2) % 1073807359L;
                }
                return zza(n * n % 1073807359L, n2 / 2) % 1073807359L * n % 1073807359L;
            }
        }
        return n3;
    }
    
    @VisibleForTesting
    private static String zza(final String[] array, final int n, final int n2) {
        if (array.length < n + n2) {
            zzakb.e("Unable to construct shingle");
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = n; i < n + n2 - 1; ++i) {
            sb.append(array[i]);
            sb.append(' ');
        }
        sb.append(array[n + n2 - 1]);
        return sb.toString();
    }
    
    @VisibleForTesting
    private static void zza(final int n, final long n2, final String s, final int n3, final PriorityQueue<zzgy> priorityQueue) {
        final zzgy zzgy = new zzgy(n2, s, n3);
        if ((priorityQueue.size() != n || (priorityQueue.peek().zzajg <= zzgy.zzajg && priorityQueue.peek().value <= zzgy.value)) && !priorityQueue.contains(zzgy)) {
            priorityQueue.add(zzgy);
            if (priorityQueue.size() > n) {
                priorityQueue.poll();
            }
        }
    }
    
    public static void zza(final String[] array, final int n, final int n2, final PriorityQueue<zzgy> priorityQueue) {
        if (array.length < n2) {
            zza(n, zzb(array, 0, array.length), zza(array, 0, array.length), array.length, priorityQueue);
        }
        else {
            long zzb = zzb(array, 0, n2);
            zza(n, zzb, zza(array, 0, n2), n2, priorityQueue);
            final long zza = zza(16785407L, n2 - 1);
            for (int i = 1; i < array.length - n2 + 1; ++i) {
                zzb = ((zzb + 1073807359L - (zzgu.zzz(array[i - 1]) + 2147483647L) % 1073807359L * zza % 1073807359L) % 1073807359L * 16785407L % 1073807359L + (zzgu.zzz(array[i + n2 - 1]) + 2147483647L) % 1073807359L) % 1073807359L;
                zza(n, zzb, zza(array, i, n2), array.length, priorityQueue);
            }
        }
    }
    
    private static long zzb(final String[] array, int i, final int n) {
        long n2 = (zzgu.zzz(array[0]) + 2147483647L) % 1073807359L;
        for (i = 1; i < n; ++i) {
            n2 = (n2 * 16785407L % 1073807359L + (zzgu.zzz(array[i]) + 2147483647L) % 1073807359L) % 1073807359L;
        }
        return n2;
    }
}
