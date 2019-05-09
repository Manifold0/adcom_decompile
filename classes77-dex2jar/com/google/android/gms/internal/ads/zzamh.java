// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import android.graphics.Bitmap;
import java.util.Map;

@zzadh
public final class zzamh
{
    private Map<Integer, Bitmap> zzctu;
    private AtomicInteger zzctv;
    
    public zzamh() {
        this.zzctu = new ConcurrentHashMap<Integer, Bitmap>();
        this.zzctv = new AtomicInteger(0);
    }
    
    public final Bitmap zza(final Integer n) {
        return this.zzctu.get(n);
    }
    
    public final int zzb(final Bitmap bitmap) {
        if (bitmap == null) {
            zzakb.zzck("Bitmap is null. Skipping putting into the Memory Map.");
            return -1;
        }
        final int andIncrement = this.zzctv.getAndIncrement();
        this.zzctu.put(andIncrement, bitmap);
        return andIncrement;
    }
    
    public final void zzb(final Integer n) {
        this.zzctu.remove(n);
    }
}
