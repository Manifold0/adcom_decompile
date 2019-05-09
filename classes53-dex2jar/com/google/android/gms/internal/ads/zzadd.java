// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzadd implements UncaughtExceptionHandler
{
    private final /* synthetic */ zzadb zzccb;
    private final /* synthetic */ UncaughtExceptionHandler zzccc;
    
    zzadd(final zzadb zzccb, final UncaughtExceptionHandler zzccc) {
        this.zzccb = zzccb;
        this.zzccc = zzccc;
    }
    
    @Override
    public final void uncaughtException(final Thread thread, final Throwable t) {
        try {
            this.zzccb.zza(thread, t);
        }
        catch (Throwable t2) {
            zzane.e("AdMob exception reporter failed reporting the exception.");
        }
        finally {
            if (this.zzccc != null) {
                this.zzccc.uncaughtException(thread, t);
            }
        }
    }
}
