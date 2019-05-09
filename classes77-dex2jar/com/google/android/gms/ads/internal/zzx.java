// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import java.util.Iterator;
import com.google.android.gms.internal.ads.zzakk;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Map;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzael;
import com.google.android.gms.internal.ads.zzait;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@ParametersAreNonnullByDefault
public final class zzx
{
    private final Context mContext;
    private boolean zzxc;
    private zzait zzxd;
    private zzael zzxe;
    
    public zzx(final Context mContext, final zzait zzxd, final zzael zzxe) {
        this.mContext = mContext;
        this.zzxd = zzxd;
        this.zzxe = zzxe;
        if (this.zzxe == null) {
            this.zzxe = new zzael();
        }
    }
    
    private final boolean zzcx() {
        return (this.zzxd != null && this.zzxd.zzpg().zzcni) || this.zzxe.zzcfr;
    }
    
    public final void recordClick() {
        this.zzxc = true;
    }
    
    public final boolean zzcy() {
        return !this.zzcx() || this.zzxc;
    }
    
    public final void zzs(@Nullable String s) {
        if (this.zzcx()) {
            if (s == null) {
                s = "";
            }
            if (this.zzxd != null) {
                this.zzxd.zza(s, null, 3);
                return;
            }
            if (this.zzxe.zzcfr && this.zzxe.zzcfs != null) {
                for (final String s2 : this.zzxe.zzcfs) {
                    if (!TextUtils.isEmpty((CharSequence)s2)) {
                        final String replace = s2.replace("{NAVIGATION_URL}", Uri.encode(s));
                        zzbv.zzek();
                        zzakk.zzd(this.mContext, "", replace);
                    }
                }
            }
        }
    }
}
