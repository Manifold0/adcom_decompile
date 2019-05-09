// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.api.Api;

public final class zai<O extends Api.ApiOptions>
{
    private final Api<O> mApi;
    private final O zabh;
    private final boolean zacu;
    private final int zacv;
    
    private zai(final Api<O> mApi) {
        this.zacu = true;
        this.mApi = mApi;
        this.zabh = null;
        this.zacv = System.identityHashCode(this);
    }
    
    private zai(final Api<O> mApi, final O zabh) {
        this.zacu = false;
        this.mApi = mApi;
        this.zabh = zabh;
        this.zacv = Objects.hashCode(new Object[] { this.mApi, this.zabh });
    }
    
    public static <O extends Api.ApiOptions> zai<O> zaa(final Api<O> api) {
        return new zai<O>(api);
    }
    
    public static <O extends Api.ApiOptions> zai<O> zaa(final Api<O> api, final O o) {
        return new zai<O>(api, o);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zai)) {
                return false;
            }
            final zai zai = (zai)o;
            if (this.zacu || zai.zacu || !Objects.equal((Object)this.mApi, (Object)zai.mApi) || !Objects.equal((Object)this.zabh, (Object)zai.zabh)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        return this.zacv;
    }
    
    public final String zan() {
        return this.mApi.getName();
    }
}
