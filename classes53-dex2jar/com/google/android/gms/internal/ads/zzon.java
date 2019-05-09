// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.graphics.drawable.Drawable;
import android.net.Uri;

@zzadh
public final class zzon extends zzpx
{
    private final Uri mUri;
    private final Drawable zzbhu;
    private final double zzbhv;
    
    public zzon(final Drawable zzbhu, final Uri mUri, final double zzbhv) {
        this.zzbhu = zzbhu;
        this.mUri = mUri;
        this.zzbhv = zzbhv;
    }
    
    public final double getScale() {
        return this.zzbhv;
    }
    
    public final Uri getUri() throws RemoteException {
        return this.mUri;
    }
    
    public final IObjectWrapper zzjy() throws RemoteException {
        return ObjectWrapper.wrap((Object)this.zzbhu);
    }
}
