// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

final class zzx implements InstanceIdResult
{
    private final String zzbp;
    private final String zzbq;
    
    zzx(final String zzbp, final String zzbq) {
        this.zzbp = zzbp;
        this.zzbq = zzbq;
    }
    
    @Override
    public final String getId() {
        return this.zzbp;
    }
    
    @Override
    public final String getToken() {
        return this.zzbq;
    }
}
