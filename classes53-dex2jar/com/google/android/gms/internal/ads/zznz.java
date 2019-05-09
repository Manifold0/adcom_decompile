// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.view.View;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;

@zzadh
public final class zznz implements CustomRenderedAd
{
    private final zzoa zzbgv;
    
    public zznz(final zzoa zzbgv) {
        this.zzbgv = zzbgv;
    }
    
    @Override
    public final String getBaseUrl() {
        try {
            return this.zzbgv.zzjn();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final String getContent() {
        try {
            return this.zzbgv.getContent();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final void onAdRendered(final View view) {
        try {
            final zzoa zzbgv = this.zzbgv;
            IObjectWrapper wrap;
            if (view != null) {
                wrap = ObjectWrapper.wrap((Object)view);
            }
            else {
                wrap = null;
            }
            zzbgv.zzg(wrap);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void recordClick() {
        try {
            this.zzbgv.recordClick();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void recordImpression() {
        try {
            this.zzbgv.recordImpression();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
