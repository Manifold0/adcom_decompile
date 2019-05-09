// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.reward.RewardItem;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;

@zzadh
public final class zzaif implements MediationRewardedVideoAdListener
{
    private final zzaic zzcmj;
    
    public zzaif(final zzaic zzcmj) {
        this.zzcmj = zzcmj;
    }
    
    @Override
    public final void onAdClicked(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzcmj.zzv(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdClosed(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzcmj.zzu(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdFailedToLoad(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, final int n) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdFailedToLoad.");
        try {
            this.zzcmj.zzd(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter), n);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLeftApplication(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzcmj.zzw(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLoaded(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        try {
            this.zzcmj.zzr(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdOpened(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzcmj.zzs(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onInitializationFailed(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, final int n) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onInitializationFailed.");
        try {
            this.zzcmj.zzc(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter), n);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onInitializationSucceeded(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onInitializationSucceeded.");
        try {
            this.zzcmj.zzq(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onRewarded(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, final RewardItem rewardItem) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onRewarded.");
        Label_0036: {
            if (rewardItem == null) {
                break Label_0036;
            }
            try {
                this.zzcmj.zza(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter), new zzaig(rewardItem));
                return;
                this.zzcmj.zza(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter), new zzaig("", 1));
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public final void onVideoCompleted(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onVideoCompleted.");
        try {
            this.zzcmj.zzx(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onVideoStarted(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onVideoStarted.");
        try {
            this.zzcmj.zzt(ObjectWrapper.wrap((Object)mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void zzc(final Bundle bundle) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdMetadataChanged.");
        try {
            this.zzcmj.zzc(bundle);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
