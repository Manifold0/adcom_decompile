// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Callable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.ads.internal.zzbo;
import android.support.annotation.Nullable;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaso
{
    public static zzaqw zza(final Context context, final zzasi zzasi, final String s, final boolean b, final boolean b2, @Nullable final zzci zzci, final zzang zzang, final zznx zznx, final zzbo zzbo, final zzw zzw, final zzhs zzhs) throws zzarg {
        try {
            return (zzaqw)zzaml.zzb((Callable)new zzasp(context, zzasi, s, b, b2, zzci, zzang, zznx, zzbo, zzw, zzhs));
        }
        catch (Throwable t) {
            zzbv.zzeo().zza(t, "AdWebViewFactory.newAdWebView2");
            throw new zzarg("Webview initialization failed.", t);
        }
    }
}
