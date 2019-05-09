// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaad
{
    private final zzaqw zzbnd;
    private final boolean zzbwm;
    private final String zzbwn;
    
    public zzaad(final zzaqw zzbnd, final Map<String, String> map) {
        this.zzbnd = zzbnd;
        this.zzbwn = map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzbwm = Boolean.parseBoolean(map.get("allowOrientationChange"));
            return;
        }
        this.zzbwm = true;
    }
    
    public final void execute() {
        if (this.zzbnd == null) {
            zzakb.zzdk("AdWebView is null");
            return;
        }
        int requestedOrientation;
        if ("portrait".equalsIgnoreCase(this.zzbwn)) {
            requestedOrientation = zzbv.zzem().zzrm();
        }
        else if ("landscape".equalsIgnoreCase(this.zzbwn)) {
            requestedOrientation = zzbv.zzem().zzrl();
        }
        else if (this.zzbwm) {
            requestedOrientation = -1;
        }
        else {
            requestedOrientation = zzbv.zzem().zzrn();
        }
        this.zzbnd.setRequestedOrientation(requestedOrientation);
    }
}
