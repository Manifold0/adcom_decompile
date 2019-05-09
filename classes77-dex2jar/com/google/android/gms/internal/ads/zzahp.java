// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzahp implements Runnable
{
    private final /* synthetic */ zzxq zzclu;
    private final /* synthetic */ zzahn zzclv;
    private final /* synthetic */ zzahv zzclw;
    private final /* synthetic */ zzjj zzyh;
    
    zzahp(final zzahn zzclv, final zzxq zzclu, final zzjj zzyh, final zzahv zzclw) {
        this.zzclv = zzclv;
        this.zzclu = zzclu;
        this.zzyh = zzyh;
        this.zzclw = zzclw;
    }
    
    @Override
    public final void run() {
        try {
            this.zzclu.zza(ObjectWrapper.wrap((Object)this.zzclv.mContext), this.zzyh, (String)null, (zzaic)this.zzclw, this.zzclv.zzcln);
        }
        catch (RemoteException ex) {
            final String value = String.valueOf(this.zzclv.zzbth);
            String concat;
            if (value.length() != 0) {
                concat = "Fail to initialize adapter ".concat(value);
            }
            else {
                concat = new String("Fail to initialize adapter ");
            }
            zzakb.zzc(concat, (Throwable)ex);
            this.zzclv.zza(this.zzclv.zzbth, 0);
        }
    }
}
