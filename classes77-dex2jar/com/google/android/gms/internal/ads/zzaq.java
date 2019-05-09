// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.io.InputStream;
import java.util.List;

public final class zzaq
{
    private final int zzce;
    private final List<zzl> zzcf;
    private final int zzcg;
    private final InputStream zzch;
    
    public zzaq(final int n, final List<zzl> list) {
        this(n, list, -1, null);
    }
    
    public zzaq(final int zzce, final List<zzl> zzcf, final int zzcg, final InputStream zzch) {
        this.zzce = zzce;
        this.zzcf = zzcf;
        this.zzcg = zzcg;
        this.zzch = zzch;
    }
    
    public final InputStream getContent() {
        return this.zzch;
    }
    
    public final int getContentLength() {
        return this.zzcg;
    }
    
    public final int getStatusCode() {
        return this.zzce;
    }
    
    public final List<zzl> zzq() {
        return Collections.unmodifiableList((List<? extends zzl>)this.zzcf);
    }
}
