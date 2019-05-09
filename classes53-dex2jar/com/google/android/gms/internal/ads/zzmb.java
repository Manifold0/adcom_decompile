// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzmb
{
    private static final Object sLock;
    @GuardedBy("sLock")
    private static zzmb zzate;
    private zzlj zzatf;
    private RewardedVideoAd zzatg;
    
    static {
        sLock = new Object();
    }
    
    private zzmb() {
    }
    
    public static zzmb zziv() {
        synchronized (zzmb.sLock) {
            if (zzmb.zzate == null) {
                zzmb.zzate = new zzmb();
            }
            return zzmb.zzate;
        }
    }
    
    public final RewardedVideoAd getRewardedVideoAdInstance(final Context context) {
        synchronized (zzmb.sLock) {
            if (this.zzatg != null) {
                return this.zzatg;
            }
            return this.zzatg = new zzahm(context, zzjr.zza(context, false, (zzjr.zza<zzagz>)new zzjz(zzkb.zzig(), context, new zzxm())));
        }
    }
    
    public final void openDebugMenu(final Context context, final String s) {
        Label_0030: {
            if (this.zzatf == null) {
                break Label_0030;
            }
            boolean b = true;
            while (true) {
                Preconditions.checkState(b, (Object)"MobileAds.initialize() must be called prior to opening debug menu.");
                try {
                    this.zzatf.zzb(ObjectWrapper.wrap((Object)context), s);
                    return;
                    b = false;
                }
                catch (RemoteException ex) {
                    zzane.zzb("Unable to open debug menu.", (Throwable)ex);
                }
            }
        }
    }
    
    public final void setAppMuted(final boolean appMuted) {
        Label_0026: {
            if (this.zzatf == null) {
                break Label_0026;
            }
            boolean b = true;
            while (true) {
                Preconditions.checkState(b, (Object)"MobileAds.initialize() must be called prior to setting app muted state.");
                try {
                    this.zzatf.setAppMuted(appMuted);
                    return;
                    b = false;
                }
                catch (RemoteException ex) {
                    zzane.zzb("Unable to set app mute state.", (Throwable)ex);
                }
            }
        }
    }
    
    public final void setAppVolume(final float appVolume) {
        final boolean b = true;
        while (true) {
            while (true) {
                boolean b2 = false;
                Label_0016: {
                    if (0.0f <= appVolume && appVolume <= 1.0f) {
                        b2 = true;
                        break Label_0016;
                    }
                    Label_0048: {
                        break Label_0048;
                        while (true) {
                            boolean b3 = false;
                            Preconditions.checkState(b3, (Object)"MobileAds.initialize() must be called prior to setting the app volume.");
                            try {
                                this.zzatf.setAppVolume(appVolume);
                                return;
                                b2 = false;
                                break;
                                b3 = false;
                            }
                            catch (RemoteException ex) {
                                zzane.zzb("Unable to set app volume.", (Throwable)ex);
                                return;
                            }
                        }
                    }
                }
                Preconditions.checkArgument(b2, (Object)"The app volume must be a value between 0 and 1 inclusive.");
                if (this.zzatf != null) {
                    final boolean b3 = b;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    public final void zza(final Context context, final String s, final zzmd zzmd) {
        synchronized (zzmb.sLock) {
            if (this.zzatf != null) {
                return;
            }
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null.");
            }
        }
        while (true) {
            try {
                final Context context2;
                (this.zzatf = zzjr.zza(context2, false, (zzjr.zza<zzlj>)new zzjw(zzkb.zzig(), context2))).zza();
                if (s != null) {
                    this.zzatf.zza(s, ObjectWrapper.wrap((Object)new zzmc(this, context2)));
                }
            }
            // monitorexit(zzmd)
            catch (RemoteException ex) {
                zzane.zzc("MobileAdsSettingManager initialization failed", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public final float zzdo() {
        if (this.zzatf == null) {
            return 1.0f;
        }
        try {
            return this.zzatf.zzdo();
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to get app volume.", (Throwable)ex);
            return 1.0f;
        }
    }
    
    public final boolean zzdp() {
        if (this.zzatf == null) {
            return false;
        }
        try {
            return this.zzatf.zzdp();
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to get app mute state.", (Throwable)ex);
            return false;
        }
    }
}
