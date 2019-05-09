// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.ArrayList;
import java.util.List;

@zzadh
final class zzaoa
{
    private final Object zzcvu;
    private final List<Runnable> zzcvv;
    private boolean zzcvw;
    
    public zzaoa() {
        this.zzcvu = new Object();
        this.zzcvv = new ArrayList<Runnable>();
        this.zzcvw = false;
    }
    
    public final void zza(final Runnable runnable, final Executor executor) {
        synchronized (this.zzcvu) {
            if (this.zzcvw) {
                executor.execute(runnable);
            }
            else {
                this.zzcvv.add(new zzaob(executor, runnable));
            }
        }
    }
    
    public final void zzsm() {
        final ArrayList<Object> list = new ArrayList<Object>();
        Object zzcvu = this.zzcvu;
        synchronized (zzcvu) {
            if (this.zzcvw) {
                return;
            }
            list.addAll(this.zzcvv);
            this.zzcvv.clear();
            this.zzcvw = true;
            // monitorexit(zzcvu)
            zzcvu = list;
            final int size = ((ArrayList)zzcvu).size();
            int i = 0;
            while (i < size) {
                final Runnable value = ((ArrayList<Runnable>)zzcvu).get(i);
                ++i;
                value.run();
            }
        }
    }
}
