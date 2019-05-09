// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

@zzadh
public class zzaop<T> implements zzaol<T>
{
    private final Object mLock;
    private int zzbqb;
    private final BlockingQueue<zzaoq> zzcwi;
    private T zzcwj;
    
    public zzaop() {
        this.mLock = new Object();
        this.zzbqb = 0;
        this.zzcwi = new LinkedBlockingQueue<zzaoq>();
    }
    
    public final int getStatus() {
        return this.zzbqb;
    }
    
    public final void reject() {
        synchronized (this.mLock) {
            if (this.zzbqb != 0) {
                throw new UnsupportedOperationException();
            }
        }
        this.zzbqb = -1;
        final Iterator<zzaoq> iterator = this.zzcwi.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzcwl.run();
        }
        this.zzcwi.clear();
    }
    // monitorexit(o)
    
    @Override
    public final void zza(final zzaoo<T> zzaoo, final zzaom zzaom) {
        while (true) {
            Label_0050: {
                synchronized (this.mLock) {
                    if (this.zzbqb == 1) {
                        zzaoo.zze(this.zzcwj);
                    }
                    else {
                        if (this.zzbqb != -1) {
                            break Label_0050;
                        }
                        zzaom.run();
                    }
                    return;
                }
            }
            if (this.zzbqb == 0) {
                final zzaoo<Object> zzaoo2;
                this.zzcwi.add(new zzaoq(zzaoo2, zzaom));
            }
        }
    }
    
    @Override
    public final void zzk(final T t) {
        synchronized (this.mLock) {
            if (this.zzbqb != 0) {
                throw new UnsupportedOperationException();
            }
        }
        final T zzcwj;
        this.zzcwj = zzcwj;
        this.zzbqb = 1;
        final Iterator<zzaoq> iterator = (Iterator<zzaoq>)this.zzcwi.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzcwk.zze(zzcwj);
        }
        this.zzcwi.clear();
    }
    // monitorexit(o)
}
