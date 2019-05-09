// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation.customevent;

import java.util.HashMap;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
public final class CustomEventExtras implements NetworkExtras
{
    private final HashMap<String, Object> zzdgj;
    
    public CustomEventExtras() {
        this.zzdgj = new HashMap<String, Object>();
    }
    
    public final Object getExtra(final String s) {
        return this.zzdgj.get(s);
    }
    
    public final void setExtra(final String s, final Object o) {
        this.zzdgj.put(s, o);
    }
}
