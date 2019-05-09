// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import java.io.IOException;
import android.util.Log;
import java.io.Closeable;
import com.google.android.gms.common.util.IOUtils;
import java.io.OutputStream;
import java.io.InputStream;

final class zzfg implements Runnable
{
    private final /* synthetic */ long zzcx;
    private final /* synthetic */ InputStream zzdr;
    private final /* synthetic */ OutputStream zzds;
    private final /* synthetic */ OutputStream zzdt;
    private final /* synthetic */ zzff zzdu;
    
    zzfg(final zzff zzdu, final InputStream zzdr, final OutputStream zzds, final long zzcx, final OutputStream zzdt) {
        this.zzdu = zzdu;
        this.zzdr = zzdr;
        this.zzds = zzds;
        this.zzcx = zzcx;
        this.zzdt = zzdt;
    }
    
    @Override
    public final void run() {
        boolean b = false;
        this.zzdu.zzdp = this.zzdr;
        while (true) {
            try {
                IOUtils.copyStream(this.zzdr, this.zzds, false, 65536);
                IOUtils.closeQuietly((Closeable)this.zzdr);
                zzff.zza(this.zzdu, this.zzdt, false, this.zzcx);
                IOUtils.closeQuietly((Closeable)this.zzds);
                this.zzdu.zzdp = null;
            }
            catch (IOException ex) {
                try {
                    if (!this.zzdu.zzdq) {
                        Log.w("NearbyConnections", String.format("Exception copying stream for Payload %d", this.zzcx), (Throwable)ex);
                    }
                    else {
                        Log.d("NearbyConnections", String.format("Terminating copying stream for Payload %d due to shutdown of OutgoingPayloadStreamer.", this.zzcx));
                    }
                    IOUtils.closeQuietly((Closeable)this.zzdr);
                    zzff.zza(this.zzdu, this.zzdt, true, this.zzcx);
                    IOUtils.closeQuietly((Closeable)this.zzds);
                    this.zzdu.zzdp = null;
                    return;
                }
                finally {
                    b = true;
                }
                IOUtils.closeQuietly((Closeable)this.zzdr);
                zzff.zza(this.zzdu, this.zzdt, b, this.zzcx);
                IOUtils.closeQuietly((Closeable)this.zzds);
                this.zzdu.zzdp = null;
                throw;
            }
            finally {
                continue;
            }
            break;
        }
    }
}
