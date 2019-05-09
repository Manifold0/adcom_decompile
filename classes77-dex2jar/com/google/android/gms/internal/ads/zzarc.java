// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import com.google.android.gms.ads.internal.zzbo;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzarc
{
    public static zzanz<zzaqw> zza(final Context context, final zzang zzang, final String s, final zzci zzci, final zzw zzw) {
        return zzano.zza((zzanz<Object>)zzano.zzi((A)null), (zzanj<? super Object, ? extends zzaqw>)new zzard(context, zzci, zzang, zzw, s), zzaoe.zzcvy);
    }
    
    public static zzaqw zza(final Context context, final zzasi zzasi, final String s, final boolean b, final boolean b2, @Nullable final zzci zzci, final zzang zzang, final zznx zznx, final zzbo zzbo, final zzw zzw, final zzhs zzhs) throws zzarg {
        zznk.initialize(context);
        if (zzkb.zzik().zzd(zznk.zzaxy)) {
            return zzaso.zza(context, zzasi, s, b2, b, zzci, zzang, zznx, zzbo, zzw, zzhs);
        }
        try {
            return (zzaqw)zzaml.zzb((Callable)new zzare(context, zzasi, s, b, b2, zzci, zzang, zznx, zzbo, zzw, zzhs));
        }
        catch (Throwable t) {
            throw new zzarg("Webview initialization failed.", t);
        }
    }
}
