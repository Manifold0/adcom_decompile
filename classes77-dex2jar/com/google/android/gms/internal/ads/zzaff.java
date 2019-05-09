// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzaff implements Runnable
{
    private final /* synthetic */ zzafa zzcgj;
    
    zzaff(final zzafa zzcgj) {
        this.zzcgj = zzcgj;
    }
    
    @Override
    public final void run() {
        if (this.zzcgj.zzcgi != null) {
            this.zzcgj.zzcgi.release();
            this.zzcgj.zzcgi = null;
        }
    }
}
