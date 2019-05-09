// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import java.io.Closeable;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import android.util.Log;
import java.io.OutputStream;
import java.util.concurrent.Executors;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;

public final class zzff
{
    private final ExecutorService zzdo;
    private volatile InputStream zzdp;
    private volatile boolean zzdq;
    
    public zzff() {
        this.zzdo = Executors.newSingleThreadExecutor();
        this.zzdp = null;
        this.zzdq = false;
    }
    
    private static void zza(final OutputStream outputStream, final boolean b, final long n) {
        int n2 = 1;
        Label_0018: {
            if (!b) {
                break Label_0018;
            }
            try {
                while (true) {
                    outputStream.write(n2);
                    return;
                    n2 = 0;
                    continue;
                }
            }
            catch (IOException ex) {
                Log.w("NearbyConnections", String.format("Unable to deliver status for Payload %d", n), (Throwable)ex);
            }
            finally {
                IOUtils.closeQuietly((Closeable)outputStream);
            }
        }
    }
    
    final void shutdown() {
        this.zzdq = true;
        this.zzdo.shutdownNow();
        IOUtils.closeQuietly((Closeable)this.zzdp);
    }
    
    final void zza(final InputStream inputStream, final OutputStream outputStream, final OutputStream outputStream2, final long n) {
        this.zzdo.execute(new zzfg(this, inputStream, outputStream, n, outputStream2));
    }
}
