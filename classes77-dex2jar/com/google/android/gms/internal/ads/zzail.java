// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;

final class zzail implements Runnable
{
    private final /* synthetic */ Bitmap val$bitmap;
    private final /* synthetic */ zzaii zzcna;
    
    zzail(final zzaii zzcna, final Bitmap val$bitmap) {
        this.zzcna = zzcna;
        this.val$bitmap = val$bitmap;
    }
    
    @Override
    public final void run() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.val$bitmap.compress(Bitmap$CompressFormat.PNG, 0, (OutputStream)byteArrayOutputStream);
        synchronized (this.zzcna.mLock) {
            this.zzcna.zzcmn.zzecm = new zzbft();
            this.zzcna.zzcmn.zzecm.zzedl = byteArrayOutputStream.toByteArray();
            this.zzcna.zzcmn.zzecm.mimeType = "image/png";
            this.zzcna.zzcmn.zzecm.zzamf = 1;
        }
    }
}
