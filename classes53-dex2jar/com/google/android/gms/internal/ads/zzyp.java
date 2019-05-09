// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.zzb;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationInterstitialListener;
import android.app.Activity;
import java.util.List;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Map;
import android.os.RemoteException;
import java.util.HashMap;
import org.json.JSONObject;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzadh
public final class zzyp<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzxr
{
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzbvb;
    private final NETWORK_EXTRAS zzbvc;
    
    public zzyp(final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzbvb, final NETWORK_EXTRAS zzbvc) {
        this.zzbvb = zzbvb;
        this.zzbvc = zzbvc;
    }
    
    private final SERVER_PARAMETERS zza(String s, final int n, final String s2) throws RemoteException {
        HashMap<String, String> hashMap2 = null;
        Label_0098: {
            if (s != null) {
                try {
                    final JSONObject jsonObject = new JSONObject(s);
                    final HashMap hashMap = new HashMap<String, String>(jsonObject.length());
                    final Iterator keys = jsonObject.keys();
                    while (true) {
                        hashMap2 = (HashMap<String, String>)hashMap;
                        if (!keys.hasNext()) {
                            break Label_0098;
                        }
                        s = keys.next();
                        hashMap.put(s, jsonObject.getString(s));
                    }
                }
                catch (Throwable t) {
                    zzane.zzb("", t);
                    throw new RemoteException();
                }
            }
            hashMap2 = new HashMap<String, String>(0);
        }
        final Class<SERVER_PARAMETERS> serverParametersType = this.zzbvb.getServerParametersType();
        MediationServerParameters mediationServerParameters = null;
        if (serverParametersType != null) {
            mediationServerParameters = serverParametersType.newInstance();
            mediationServerParameters.load(hashMap2);
        }
        return (SERVER_PARAMETERS)mediationServerParameters;
    }
    
    private static boolean zzm(final zzjj zzjj) {
        if (!zzjj.zzapz) {
            zzkb.zzif();
            if (!zzamu.zzsg()) {
                return false;
            }
        }
        return true;
    }
    
    public final void destroy() throws RemoteException {
        try {
            this.zzbvb.destroy();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }
    
    public final zzlo getVideoController() {
        return null;
    }
    
    public final IObjectWrapper getView() throws RemoteException {
        if (!(this.zzbvb instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationBannerAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationBannerAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap((Object)((MediationBannerAdapter)this.zzbvb).getBannerView());
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public final void pause() throws RemoteException {
        throw new RemoteException();
    }
    
    public final void resume() throws RemoteException {
        throw new RemoteException();
    }
    
    public final void setImmersiveMode(final boolean b) {
    }
    
    public final void showInterstitial() throws RemoteException {
        if (!(this.zzbvb instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationInterstitialAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationInterstitialAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        zzane.zzck("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.zzbvb).showInterstitial();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void showVideo() {
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzaic zzaic, final List<String> list) {
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final zzaic zzaic, final String s2) throws RemoteException {
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final zzxt zzxt) throws RemoteException {
        this.zza(objectWrapper, zzjj, s, null, zzxt);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final String s2, final zzxt zzxt) throws RemoteException {
        if (!(this.zzbvb instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationInterstitialAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationInterstitialAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        zzane.zzck("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter)this.zzbvb).requestInterstitialAd(new zzyq<Object, Object>(zzxt), (Activity)ObjectWrapper.unwrap(objectWrapper), this.zza(s, zzjj.zzaqa, s2), zzzc.zza(zzjj, zzm(zzjj)), this.zzbvc);
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final String s2, final zzxt zzxt, final zzpl zzpl, final List<String> list) {
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjn zzjn, final zzjj zzjj, final String s, final zzxt zzxt) throws RemoteException {
        this.zza(objectWrapper, zzjn, zzjj, s, null, zzxt);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjn zzjn, final zzjj zzjj, final String s, final String s2, final zzxt zzxt) throws RemoteException {
        int n = 0;
        if (!(this.zzbvb instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationBannerAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationBannerAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        while (true) {
            zzane.zzck("Requesting banner ad from adapter.");
            while (true) {
                Label_0264: {
                    try {
                        final MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter)this.zzbvb;
                        final zzyq zzyq = new zzyq(zzxt);
                        final Activity activity = (Activity)ObjectWrapper.unwrap(objectWrapper);
                        final MediationServerParameters zza = this.zza(s, zzjj.zzaqa, s2);
                        final AdSize[] array = { AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER };
                        AdSize adSize;
                        if (n < 6) {
                            if (array[n].getWidth() != zzjn.width || array[n].getHeight() != zzjn.height) {
                                break Label_0264;
                            }
                            adSize = array[n];
                        }
                        else {
                            adSize = new AdSize(zzb.zza(zzjn.width, zzjn.height, zzjn.zzarb));
                        }
                        mediationBannerAdapter.requestBannerAd(zzyq, activity, zza, adSize, zzzc.zza(zzjj, zzm(zzjj)), this.zzbvc);
                        return;
                    }
                    catch (Throwable t) {
                        zzane.zzb("", t);
                        throw new RemoteException();
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public final void zza(final zzjj zzjj, final String s, final String s2) {
    }
    
    public final void zzc(final zzjj zzjj, final String s) {
    }
    
    public final void zzi(final IObjectWrapper objectWrapper) throws RemoteException {
    }
    
    public final zzxz zzmo() {
        return null;
    }
    
    public final zzyc zzmp() {
        return null;
    }
    
    public final Bundle zzmq() {
        return new Bundle();
    }
    
    public final Bundle zzmr() {
        return new Bundle();
    }
    
    public final boolean zzms() {
        return false;
    }
    
    public final zzqs zzmt() {
        return null;
    }
    
    public final zzyf zzmu() {
        return null;
    }
}
