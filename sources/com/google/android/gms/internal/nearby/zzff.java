package com.google.android.gms.internal.nearby;

import android.util.Log;
import com.google.android.gms.common.util.IOUtils;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzff {
    private final ExecutorService zzdo = Executors.newSingleThreadExecutor();
    private volatile InputStream zzdp = null;
    private volatile boolean zzdq = false;

    private static void zza(OutputStream outputStream, boolean z, long j) {
        int i = 1;
        if (!z) {
            i = 0;
        }
        try {
            outputStream.write(i);
        } catch (Throwable e) {
            Log.w("NearbyConnections", String.format("Unable to deliver status for Payload %d", new Object[]{Long.valueOf(j)}), e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    final void shutdown() {
        this.zzdq = true;
        this.zzdo.shutdownNow();
        IOUtils.closeQuietly(this.zzdp);
    }

    final void zza(InputStream inputStream, OutputStream outputStream, OutputStream outputStream2, long j) {
        this.zzdo.execute(new zzfg(this, inputStream, outputStream, j, outputStream2));
    }
}
