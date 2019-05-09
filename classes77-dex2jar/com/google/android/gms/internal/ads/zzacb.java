// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.graphics.drawable.BitmapDrawable;
import android.content.res.Resources;
import android.os.Looper;
import com.google.android.gms.common.util.PlatformVersion;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.graphics.Bitmap$Config;
import android.graphics.BitmapFactory$Options;
import java.io.InputStream;

final class zzacb implements zzalz<zzon>
{
    private final /* synthetic */ String zzbwo;
    private final /* synthetic */ zzabv zzcal;
    private final /* synthetic */ boolean zzcav;
    private final /* synthetic */ double zzcaw;
    private final /* synthetic */ boolean zzcax;
    
    zzacb(final zzabv zzcal, final boolean zzcav, final double zzcaw, final boolean zzcax, final String zzbwo) {
        this.zzcal = zzcal;
        this.zzcav = zzcav;
        this.zzcaw = zzcaw;
        this.zzcax = zzcax;
        this.zzbwo = zzbwo;
    }
    
    @TargetApi(19)
    private final zzon zzd(final InputStream inputStream) {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inDensity = (int)(160.0 * this.zzcaw);
        if (!this.zzcax) {
            bitmapFactory$Options.inPreferredConfig = Bitmap$Config.RGB_565;
        }
        final long uptimeMillis = SystemClock.uptimeMillis();
        Bitmap decodeStream;
        while (true) {
            try {
                decodeStream = BitmapFactory.decodeStream(inputStream, (Rect)null, bitmapFactory$Options);
                if (decodeStream == null) {
                    this.zzcal.zzd(2, this.zzcav);
                    return null;
                }
            }
            catch (Exception ex) {
                zzakb.zzb("Error grabbing image.", (Throwable)ex);
                decodeStream = null;
                continue;
            }
            break;
        }
        final long uptimeMillis2 = SystemClock.uptimeMillis();
        if (PlatformVersion.isAtLeastKitKat() && zzakb.zzqp()) {
            zzakb.v(new StringBuilder(108).append("Decoded image w: ").append(decodeStream.getWidth()).append(" h:").append(decodeStream.getHeight()).append(" bytes: ").append(decodeStream.getAllocationByteCount()).append(" time: ").append(uptimeMillis2 - uptimeMillis).append(" on ui thread: ").append(Looper.getMainLooper().getThread() == Thread.currentThread()).toString());
        }
        return new zzon((Drawable)new BitmapDrawable(Resources.getSystem(), decodeStream), Uri.parse(this.zzbwo), this.zzcaw);
    }
}
