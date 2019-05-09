// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzadc implements UncaughtExceptionHandler
{
    private final /* synthetic */ UncaughtExceptionHandler zzcca;
    private final /* synthetic */ zzadb zzccb;
    
    zzadc(final zzadb zzccb, final UncaughtExceptionHandler zzcca) {
        this.zzccb = zzccb;
        this.zzcca = zzcca;
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
            if (this.zzcca != null) {
                this.zzcca.uncaughtException(thread, t);
            }
        }
    }
}
