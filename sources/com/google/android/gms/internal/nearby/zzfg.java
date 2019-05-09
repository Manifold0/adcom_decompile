package com.google.android.gms.internal.nearby;

import android.util.Log;
import com.google.android.gms.common.util.IOUtils;
import java.io.InputStream;
import java.io.OutputStream;

final class zzfg implements Runnable {
    private final /* synthetic */ long zzcx;
    private final /* synthetic */ InputStream zzdr;
    private final /* synthetic */ OutputStream zzds;
    private final /* synthetic */ OutputStream zzdt;
    private final /* synthetic */ zzff zzdu;

    zzfg(zzff zzff, InputStream inputStream, OutputStream outputStream, long j, OutputStream outputStream2) {
        this.zzdu = zzff;
        this.zzdr = inputStream;
        this.zzds = outputStream;
        this.zzcx = j;
        this.zzdt = outputStream2;
    }

    public final void run() {
        Throwable e;
        boolean z = false;
        this.zzdu.zzdp = this.zzdr;
        try {
            IOUtils.copyStream(this.zzdr, this.zzds, false, 65536);
            IOUtils.closeQuietly(this.zzdr);
            zzff.zza(this.zzdt, false, this.zzcx);
            IOUtils.closeQuietly(this.zzds);
            this.zzdu.zzdp = null;
        } catch (Throwable e2) {
            if (this.zzdu.zzdq) {
                Log.d("NearbyConnections", String.format("Terminating copying stream for Payload %d due to shutdown of OutgoingPayloadStreamer.", new Object[]{Long.valueOf(this.zzcx)}));
            } else {
                Log.w("NearbyConnections", String.format("Exception copying stream for Payload %d", new Object[]{Long.valueOf(this.zzcx)}), e2);
            }
            IOUtils.closeQuietly(this.zzdr);
            zzff.zza(this.zzdt, true, this.zzcx);
            IOUtils.closeQuietly(this.zzds);
            this.zzdu.zzdp = null;
        } catch (Throwable th) {
            e2 = th;
            z = true;
            IOUtils.closeQuietly(this.zzdr);
            zzff.zza(this.zzdt, z, this.zzcx);
            IOUtils.closeQuietly(this.zzds);
            this.zzdu.zzdp = null;
            throw e2;
        }
    }
}
