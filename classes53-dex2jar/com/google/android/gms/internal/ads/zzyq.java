// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzadh
public final class zzyq<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener
{
    private final zzxt zzbuu;
    
    public zzyq(final zzxt zzbuu) {
        this.zzbuu = zzbuu;
    }
    
    @Override
    public final void onClick(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onClick.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzyr(this));
            return;
        }
        try {
            this.zzbuu.onAdClicked();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onDismissScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onDismissScreen.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzdk("#008 Must be called on the main UI thread.");
            zzamu.zzsy.post((Runnable)new zzyu(this));
            return;
        }
        try {
            this.zzbuu.onAdClosed();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onDismissScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onDismissScreen.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzyz(this));
            return;
        }
        try {
            this.zzbuu.onAdClosed();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onFailedToReceiveAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest.ErrorCode errorCode) {
        final String value = String.valueOf(errorCode);
        zzane.zzck(new StringBuilder(String.valueOf(value).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(value).toString());
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzyv(this, errorCode));
            return;
        }
        try {
            this.zzbuu.onAdFailedToLoad(zzzc.zza(errorCode));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onFailedToReceiveAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest.ErrorCode errorCode) {
        final String value = String.valueOf(errorCode);
        zzane.zzck(new StringBuilder(String.valueOf(value).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(value).append(".").toString());
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzza(this, errorCode));
            return;
        }
        try {
            this.zzbuu.onAdFailedToLoad(zzzc.zza(errorCode));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onLeaveApplication(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onLeaveApplication.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzyw(this));
            return;
        }
        try {
            this.zzbuu.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onLeaveApplication(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onLeaveApplication.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzzb(this));
            return;
        }
        try {
            this.zzbuu.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onPresentScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onPresentScreen.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzyx(this));
            return;
        }
        try {
            this.zzbuu.onAdOpened();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onPresentScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onPresentScreen.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzys(this));
            return;
        }
        try {
            this.zzbuu.onAdOpened();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onReceivedAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzane.zzck("Adapter called onReceivedAd.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzyy(this));
            return;
        }
        try {
            this.zzbuu.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onReceivedAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzane.zzck("Adapter called onReceivedAd.");
        zzkb.zzif();
        if (!zzamu.zzsh()) {
            zzane.zzd("#008 Must be called on the main UI thread.", null);
            zzamu.zzsy.post((Runnable)new zzyt(this));
            return;
        }
        try {
            this.zzbuu.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
