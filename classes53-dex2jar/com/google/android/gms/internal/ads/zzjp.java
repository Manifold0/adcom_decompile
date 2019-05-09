// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;

@zzadh
public final class zzjp extends zzlb
{
    private final AppEventListener zzvo;
    
    public zzjp(final AppEventListener zzvo) {
        this.zzvo = zzvo;
    }
    
    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }
    
    public final void onAppEvent(final String s, final String s2) {
        this.zzvo.onAppEvent(s, s2);
    }
}
