// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.ads.mediation.zza;
import java.util.Iterator;
import org.json.JSONException;
import android.os.RemoteException;
import org.json.JSONObject;
import android.os.Bundle;

public final class zzzp extends zzzk
{
    private final zzatg zzbvi;
    
    public zzzp(final zzatg zzbvi) {
        this.zzbvi = zzbvi;
    }
    
    private static Bundle zzbt(final String s) throws RemoteException {
        final String value = String.valueOf(s);
        while (true) {
            Label_0104: {
                if (value.length() == 0) {
                    break Label_0104;
                }
                final String concat = "Server parameters: ".concat(value);
                zzane.zzdk(concat);
                try {
                    Bundle bundle = new Bundle();
                    if (s != null) {
                        final JSONObject jsonObject = new JSONObject(s);
                        bundle = new Bundle();
                        final Iterator keys = jsonObject.keys();
                        while (keys.hasNext()) {
                            final String s2 = keys.next();
                            bundle.putString(s2, jsonObject.getString(s2));
                        }
                        return bundle;
                    }
                    return bundle;
                }
                catch (JSONException ex) {
                    zzane.zzb("", (Throwable)ex);
                    throw new RemoteException();
                }
            }
            final String concat = new String("Server parameters: ");
            continue;
        }
    }
    
    public final zzlo getVideoController() {
        if (!(this.zzbvi instanceof zza)) {
            return null;
        }
        try {
            return ((zza)this.zzbvi).getVideoController();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            return null;
        }
    }
    
    public final void showInterstitial() throws RemoteException {
        try {
            throw new NullPointerException();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final String s, final Bundle bundle, final zzzm zzzm) throws RemoteException {
        zzzs zzzs = null;
        zzatg zzbvi = null;
        Context context = null;
        int n2 = 0;
    Label_0166:
        while (true) {
            while (true) {
                int n = 0;
                Label_0209: {
                    Label_0146: {
                        Label_0131: {
                            Label_0116: {
                                Label_0101: {
                                    try {
                                        zzzs = new zzzs(this, zzzm);
                                        zzbvi = this.zzbvi;
                                        context = (Context)ObjectWrapper.unwrap(objectWrapper);
                                        n = -1;
                                        switch (s.hashCode()) {
                                            case -1396342996: {
                                                break Label_0101;
                                            }
                                            case 604727084: {
                                                break Label_0116;
                                            }
                                            case -239580146: {
                                                break Label_0131;
                                            }
                                            case -1052618729: {
                                                break Label_0146;
                                            }
                                            default: {
                                                break Label_0209;
                                            }
                                        }
                                        throw new IllegalArgumentException("Internal Error");
                                    }
                                    catch (Throwable t) {
                                        zzane.zzb("Error generating signals for RTB", t);
                                        throw new RemoteException();
                                    }
                                }
                                if (s.equals("banner")) {
                                    n = 0;
                                }
                                break Label_0209;
                            }
                            if (s.equals("interstitial")) {
                                n = 1;
                            }
                            break Label_0209;
                        }
                        if (s.equals("rewarded")) {
                            n = 2;
                        }
                        break Label_0209;
                    }
                    if (s.equals("native")) {
                        n = 3;
                    }
                }
                switch (n) {
                    case 0: {
                        n2 = zzath.zzdgn;
                        break Label_0166;
                    }
                    case 1: {
                        n2 = zzath.zzdgo;
                        break Label_0166;
                    }
                    case 2: {
                        n2 = zzath.zzdgp;
                        break Label_0166;
                    }
                    case 3: {
                        n2 = zzath.zzdgq;
                        break Label_0166;
                    }
                    default: {
                        continue;
                    }
                }
                break;
            }
        }
        zzbvi.zza(new zzati(context, n2, bundle), (zzatj)zzzs);
    }
    
    public final void zza(final byte[] array, final String s, final Bundle bundle, final IObjectWrapper objectWrapper, final zzzf zzzf, final zzxt zzxt, final zzjn zzjn) throws RemoteException {
        try {
            final zzzq zzzq = new zzzq(this, zzzf, zzxt);
            final zzatg zzbvi = this.zzbvi;
            new zzatf((Context)ObjectWrapper.unwrap(objectWrapper), array, zzbt(s), bundle);
            zzb.zza(zzjn.width, zzjn.height, zzjn.zzarb);
            zzzq.zzau(String.valueOf(zzbvi.getClass().getSimpleName()).concat(" does not support banner."));
        }
        catch (Throwable t) {
            zzane.zzb("Adapter failed to render banner ad.", t);
            throw new RemoteException();
        }
    }
    
    public final void zza(final byte[] array, final String s, final Bundle bundle, final IObjectWrapper objectWrapper, final zzzh zzzh, final zzxt zzxt) throws RemoteException {
        try {
            final zzzr zzzr = new zzzr(this, zzzh, zzxt);
            final zzatg zzbvi = this.zzbvi;
            new zzatf((Context)ObjectWrapper.unwrap(objectWrapper), array, zzbt(s), bundle);
            zzzr.zzau(String.valueOf(zzbvi.getClass().getSimpleName()).concat(" does not support interstitial."));
        }
        catch (Throwable t) {
            zzane.zzb("Adapter failed to render interstitial ad.", t);
            throw new RemoteException();
        }
    }
    
    public final zzzt zznc() throws RemoteException {
        return zzzt.zza(this.zzbvi.zzwa());
    }
    
    public final zzzt zznd() throws RemoteException {
        return zzzt.zza(this.zzbvi.zzvz());
    }
}
