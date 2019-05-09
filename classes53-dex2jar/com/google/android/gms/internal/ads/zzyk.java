// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Set;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import android.content.Context;
import java.util.ArrayList;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import java.util.List;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.ads.mediation.zza;
import java.util.Iterator;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.RemoteException;
import org.json.JSONObject;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;

@zzadh
public final class zzyk extends zzxr
{
    private final MediationAdapter zzbus;
    private zzyl zzbut;
    
    public zzyk(final MediationAdapter zzbus) {
        this.zzbus = zzbus;
    }
    
    private final Bundle zza(final String s, final zzjj zzjj, final String s2) throws RemoteException {
        final String value = String.valueOf(s);
        while (true) {
            Label_0118: {
                if (value.length() == 0) {
                    break Label_0118;
                }
                final String concat = "Server parameters: ".concat(value);
                zzane.zzdk(concat);
                Bundle bundle = null;
                Label_0132: {
                    try {
                        bundle = new Bundle();
                        if (s != null) {
                            final JSONObject jsonObject = new JSONObject(s);
                            bundle = new Bundle();
                            final Iterator keys = jsonObject.keys();
                            while (keys.hasNext()) {
                                final String s3 = keys.next();
                                bundle.putString(s3, jsonObject.getString(s3));
                            }
                        }
                        break Label_0132;
                    }
                    catch (Throwable t) {
                        zzane.zzb("", t);
                        throw new RemoteException();
                    }
                    break Label_0118;
                }
                if (this.zzbus instanceof AdMobAdapter) {
                    bundle.putString("adJson", s2);
                    if (zzjj != null) {
                        bundle.putInt("tagForChildDirectedTreatment", zzjj.zzaqa);
                    }
                }
                return bundle;
            }
            final String concat = new String("Server parameters: ");
            continue;
        }
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
            this.zzbus.onDestroy();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final Bundle getInterstitialAdapterInfo() {
        if (!(this.zzbus instanceof zzatm)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a v2 MediationInterstitialAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a v2 MediationInterstitialAdapter: ");
            }
            zzane.zzdk(concat);
            return new Bundle();
        }
        return ((zzatm)this.zzbus).getInterstitialAdapterInfo();
    }
    
    public final zzlo getVideoController() {
        if (!(this.zzbus instanceof zza)) {
            return null;
        }
        try {
            return ((zza)this.zzbus).getVideoController();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            return null;
        }
    }
    
    public final IObjectWrapper getView() throws RemoteException {
        if (!(this.zzbus instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
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
            return ObjectWrapper.wrap((Object)((MediationBannerAdapter)this.zzbus).getBannerView());
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final boolean isInitialized() throws RemoteException {
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationRewardedVideoAdAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        zzane.zzck("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter)this.zzbus).isInitialized();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void pause() throws RemoteException {
        try {
            this.zzbus.onPause();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void resume() throws RemoteException {
        try {
            this.zzbus.onResume();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void setImmersiveMode(final boolean b) throws RemoteException {
        if (!(this.zzbus instanceof OnImmersiveModeUpdatedListener)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not an OnImmersiveModeUpdatedListener: ".concat(value);
            }
            else {
                concat = new String("Not an OnImmersiveModeUpdatedListener: ");
            }
            zzane.zzdj(concat);
            return;
        }
        try {
            ((OnImmersiveModeUpdatedListener)this.zzbus).onImmersiveModeUpdated(b);
        }
        catch (Throwable t) {
            zzane.zzb("", t);
        }
    }
    
    public final void showInterstitial() throws RemoteException {
        if (!(this.zzbus instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
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
            ((MediationInterstitialAdapter)this.zzbus).showInterstitial();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void showVideo() throws RemoteException {
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationRewardedVideoAdAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        zzane.zzck("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter)this.zzbus).showVideo();
        }
        catch (Throwable t) {
            zzane.zzb("", t);
            throw new RemoteException();
        }
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzaic zzaic, final List<String> list) throws RemoteException {
        if (!(this.zzbus instanceof InitializableMediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not an InitializableMediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("Not an InitializableMediationRewardedVideoAdAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        zzane.zzck("Initialize rewarded video adapter.");
        InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter;
        ArrayList<Bundle> list2;
        try {
            initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter)this.zzbus;
            list2 = new ArrayList<Bundle>();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(this.zza(iterator.next(), null, null));
            }
        }
        catch (Throwable t) {
            zzane.zzc("Could not initialize rewarded video adapter.", t);
            throw new RemoteException();
        }
        initializableMediationRewardedVideoAdAdapter.initialize((Context)ObjectWrapper.unwrap(objectWrapper), new zzaif(zzaic), list2);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final zzaic zzaic, final String s2) throws RemoteException {
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationRewardedVideoAdAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        while (true) {
            zzane.zzck("Initialize rewarded video adapter.");
            while (true) {
                Label_0271: {
                    while (true) {
                        Label_0263: {
                            zzyj zzyj;
                            try {
                                final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzbus;
                                final Bundle zza = this.zza(s2, zzjj, null);
                                if (zzjj == null) {
                                    break Label_0263;
                                }
                                if (zzjj.zzapy == null) {
                                    break Label_0271;
                                }
                                final HashSet<String> set = new HashSet<String>(zzjj.zzapy);
                                Date date;
                                if (zzjj.zzapw == -1L) {
                                    date = null;
                                }
                                else {
                                    date = new Date(zzjj.zzapw);
                                }
                                zzyj = new zzyj(date, zzjj.zzapx, set, zzjj.zzaqe, zzm(zzjj), zzjj.zzaqa, zzjj.zzaql);
                                if (zzjj.zzaqg != null) {
                                    final Bundle bundle = zzjj.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                                    final MediationAdRequest mediationAdRequest = zzyj;
                                    final Bundle bundle2 = bundle;
                                    mediationRewardedVideoAdAdapter.initialize((Context)ObjectWrapper.unwrap(objectWrapper), mediationAdRequest, s, new zzaif(zzaic), zza, bundle2);
                                    return;
                                }
                            }
                            catch (Throwable t) {
                                zzane.zzb("", t);
                                throw new RemoteException();
                            }
                            final Bundle bundle3 = null;
                            final MediationAdRequest mediationAdRequest = zzyj;
                            final Bundle bundle2 = bundle3;
                            continue;
                        }
                        final Bundle bundle2 = null;
                        final MediationAdRequest mediationAdRequest = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final zzxt zzxt) throws RemoteException {
        this.zza(objectWrapper, zzjj, s, null, zzxt);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final String s2, final zzxt zzxt) throws RemoteException {
        if (!(this.zzbus instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
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
        while (true) {
            zzane.zzck("Requesting interstitial ad from adapter.");
            while (true) {
                Label_0242: {
                    while (true) {
                        try {
                            final MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter)this.zzbus;
                            if (zzjj.zzapy == null) {
                                break Label_0242;
                            }
                            final HashSet<String> set = new HashSet<String>(zzjj.zzapy);
                            Date date;
                            if (zzjj.zzapw == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(zzjj.zzapw);
                            }
                            final zzyj zzyj = new zzyj(date, zzjj.zzapx, set, zzjj.zzaqe, zzm(zzjj), zzjj.zzaqa, zzjj.zzaql);
                            if (zzjj.zzaqg != null) {
                                final Bundle bundle = zzjj.zzaqg.getBundle(mediationInterstitialAdapter.getClass().getName());
                                mediationInterstitialAdapter.requestInterstitialAd((Context)ObjectWrapper.unwrap(objectWrapper), new zzyl(zzxt), this.zza(s, zzjj, s2), zzyj, bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzane.zzb("", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final String s2, final zzxt zzxt, final zzpl zzpl, final List<String> list) throws RemoteException {
        if (!(this.zzbus instanceof MediationNativeAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationNativeAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationNativeAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        while (true) {
            while (true) {
                Label_0250: {
                    while (true) {
                        try {
                            final MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter)this.zzbus;
                            if (zzjj.zzapy == null) {
                                break Label_0250;
                            }
                            final HashSet<String> set = new HashSet<String>(zzjj.zzapy);
                            Date date;
                            if (zzjj.zzapw == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(zzjj.zzapw);
                            }
                            final zzyo zzyo = new zzyo(date, zzjj.zzapx, set, zzjj.zzaqe, zzm(zzjj), zzjj.zzaqa, zzpl, list, zzjj.zzaql);
                            if (zzjj.zzaqg != null) {
                                final Bundle bundle = zzjj.zzaqg.getBundle(mediationNativeAdapter.getClass().getName());
                                this.zzbut = new zzyl(zzxt);
                                mediationNativeAdapter.requestNativeAd((Context)ObjectWrapper.unwrap(objectWrapper), this.zzbut, this.zza(s, zzjj, s2), zzyo, bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzane.zzb("", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjn zzjn, final zzjj zzjj, final String s, final zzxt zzxt) throws RemoteException {
        this.zza(objectWrapper, zzjn, zzjj, s, null, zzxt);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjn zzjn, final zzjj zzjj, final String s, final String s2, final zzxt zzxt) throws RemoteException {
        if (!(this.zzbus instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
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
                Label_0258: {
                    while (true) {
                        try {
                            final MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter)this.zzbus;
                            if (zzjj.zzapy == null) {
                                break Label_0258;
                            }
                            final HashSet<String> set = new HashSet<String>(zzjj.zzapy);
                            Date date;
                            if (zzjj.zzapw == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(zzjj.zzapw);
                            }
                            final zzyj zzyj = new zzyj(date, zzjj.zzapx, set, zzjj.zzaqe, zzm(zzjj), zzjj.zzaqa, zzjj.zzaql);
                            if (zzjj.zzaqg != null) {
                                final Bundle bundle = zzjj.zzaqg.getBundle(mediationBannerAdapter.getClass().getName());
                                mediationBannerAdapter.requestBannerAd((Context)ObjectWrapper.unwrap(objectWrapper), new zzyl(zzxt), this.zza(s, zzjj, s2), zzb.zza(zzjn.width, zzjn.height, zzjn.zzarb), zzyj, bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzane.zzb("", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public final void zza(final zzjj zzjj, final String s, final String s2) throws RemoteException {
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a MediationRewardedVideoAdAdapter: ");
            }
            zzane.zzdk(concat);
            throw new RemoteException();
        }
        while (true) {
            zzane.zzck("Requesting rewarded video ad from adapter.");
            while (true) {
                Label_0225: {
                    while (true) {
                        try {
                            final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzbus;
                            if (zzjj.zzapy == null) {
                                break Label_0225;
                            }
                            final HashSet<String> set = new HashSet<String>(zzjj.zzapy);
                            Date date;
                            if (zzjj.zzapw == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(zzjj.zzapw);
                            }
                            final zzyj zzyj = new zzyj(date, zzjj.zzapx, set, zzjj.zzaqe, zzm(zzjj), zzjj.zzaqa, zzjj.zzaql);
                            if (zzjj.zzaqg != null) {
                                final Bundle bundle = zzjj.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                                mediationRewardedVideoAdAdapter.loadAd(zzyj, this.zza(s, zzjj, s2), bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzane.zzb("", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public final void zzc(final zzjj zzjj, final String s) throws RemoteException {
        this.zza(zzjj, s, null);
    }
    
    public final void zzi(final IObjectWrapper objectWrapper) throws RemoteException {
        try {
            ((OnContextChangedListener)this.zzbus).onContextChanged((Context)ObjectWrapper.unwrap(objectWrapper));
        }
        catch (Throwable t) {
            zzane.zzc("Failed", t);
        }
    }
    
    public final zzxz zzmo() {
        final NativeAdMapper zzmx = this.zzbut.zzmx();
        if (zzmx instanceof NativeAppInstallAdMapper) {
            return new zzym((NativeAppInstallAdMapper)zzmx);
        }
        return null;
    }
    
    public final zzyc zzmp() {
        final NativeAdMapper zzmx = this.zzbut.zzmx();
        if (zzmx instanceof NativeContentAdMapper) {
            return new zzyn((NativeContentAdMapper)zzmx);
        }
        return null;
    }
    
    public final Bundle zzmq() {
        if (!(this.zzbus instanceof zzatl)) {
            final String value = String.valueOf(this.zzbus.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "Not a v2 MediationBannerAdapter: ".concat(value);
            }
            else {
                concat = new String("Not a v2 MediationBannerAdapter: ");
            }
            zzane.zzdk(concat);
            return new Bundle();
        }
        return ((zzatl)this.zzbus).zzmq();
    }
    
    public final Bundle zzmr() {
        return new Bundle();
    }
    
    public final boolean zzms() {
        return this.zzbus instanceof InitializableMediationRewardedVideoAdAdapter;
    }
    
    public final zzqs zzmt() {
        final NativeCustomTemplateAd zzmz = this.zzbut.zzmz();
        if (zzmz instanceof zzqv) {
            return ((zzqv)zzmz).zzku();
        }
        return null;
    }
    
    public final zzyf zzmu() {
        final UnifiedNativeAdMapper zzmy = this.zzbut.zzmy();
        if (zzmy != null) {
            return new zzze(zzmy);
        }
        return null;
    }
}
