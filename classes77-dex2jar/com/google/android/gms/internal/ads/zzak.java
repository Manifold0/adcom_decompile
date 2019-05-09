// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

public final class zzak
{
    private static final Comparator<byte[]> zzbv;
    private final List<byte[]> zzbr;
    private final List<byte[]> zzbs;
    private int zzbt;
    private final int zzbu;
    
    static {
        zzbv = new zzal();
    }
    
    public zzak(final int n) {
        this.zzbr = new LinkedList<byte[]>();
        this.zzbs = new ArrayList<byte[]>(64);
        this.zzbt = 0;
        this.zzbu = 4096;
    }
    
    private final void zzn() {
        synchronized (this) {
            while (this.zzbt > this.zzbu) {
                final byte[] array = this.zzbr.remove(0);
                this.zzbs.remove(array);
                this.zzbt -= array.length;
            }
        }
    }
    // monitorexit(this)
    
    public final void zza(final byte[] array) {
        // monitorenter(this)
        if (array == null) {
            return;
        }
        try {
            if (array.length <= this.zzbu) {
                this.zzbr.add(array);
                final int binarySearch = Collections.binarySearch(this.zzbs, array, zzak.zzbv);
                int n;
                if ((n = binarySearch) < 0) {
                    n = -binarySearch - 1;
                }
                this.zzbs.add(n, array);
                this.zzbt += array.length;
                this.zzn();
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public final byte[] zzb(final int n) {
        // monitorenter(this)
        int i = 0;
        try {
            while (i < this.zzbs.size()) {
                final byte[] array = this.zzbs.get(i);
                if (array.length >= n) {
                    this.zzbt -= array.length;
                    this.zzbs.remove(i);
                    this.zzbr.remove(array);
                    return array;
                }
                ++i;
            }
            return new byte[n];
        }
        finally {
        }
        // monitorexit(this)
    }
}
