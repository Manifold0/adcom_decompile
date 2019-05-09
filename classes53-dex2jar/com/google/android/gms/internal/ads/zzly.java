// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.view.View;
import android.os.RemoteException;
import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.VideoOptions;
import android.view.ViewGroup;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.VideoController;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdListener;

@zzadh
public final class zzly
{
    private zzjd zzapt;
    private AdListener zzapu;
    private AdSize[] zzarh;
    private final zzxm zzast;
    private final AtomicBoolean zzasu;
    private final VideoController zzasv;
    @VisibleForTesting
    private final zzkd zzasw;
    private Correlator zzasx;
    private zzks zzasy;
    private OnCustomRenderedAdLoadedListener zzasz;
    private ViewGroup zzata;
    private int zzatb;
    private final zzjm zzuk;
    private VideoOptions zzvg;
    private boolean zzvm;
    private AppEventListener zzvo;
    private String zzye;
    
    public zzly(final ViewGroup viewGroup) {
        this(viewGroup, null, false, zzjm.zzara, 0);
    }
    
    public zzly(final ViewGroup viewGroup, final int n) {
        this(viewGroup, null, false, zzjm.zzara, n);
    }
    
    public zzly(final ViewGroup viewGroup, final AttributeSet set, final boolean b) {
        this(viewGroup, set, b, zzjm.zzara, 0);
    }
    
    public zzly(final ViewGroup viewGroup, final AttributeSet set, final boolean b, final int n) {
        this(viewGroup, set, false, zzjm.zzara, n);
    }
    
    @VisibleForTesting
    private zzly(final ViewGroup viewGroup, final AttributeSet set, final boolean b, final zzjm zzjm, final int n) {
        this(viewGroup, set, b, zzjm, null, n);
    }
    
    @VisibleForTesting
    private zzly(final ViewGroup zzata, final AttributeSet set, final boolean b, zzjm context, final zzks zzks, int zzatb) {
        this.zzast = new zzxm();
        this.zzasv = new VideoController();
        this.zzasw = new zzlz(this);
        this.zzata = zzata;
        this.zzuk = context;
        this.zzasy = null;
        this.zzasu = new AtomicBoolean(false);
        this.zzatb = zzatb;
        if (set == null) {
            return;
        }
        context = (zzjm)zzata.getContext();
        try {
            final zzjq zzjq = new zzjq((Context)context, set);
            this.zzarh = zzjq.zzi(b);
            this.zzye = zzjq.getAdUnitId();
            if (zzata.isInEditMode()) {
                final zzamu zzif = zzkb.zzif();
                final AdSize adSize = this.zzarh[0];
                zzatb = this.zzatb;
                context = (zzjm)new zzjn((Context)context, adSize);
                ((zzjn)context).zzarg = zzu(zzatb);
                zzif.zza(zzata, (zzjn)context, "Ads by Google");
            }
        }
        catch (IllegalArgumentException ex) {
            zzkb.zzif().zza(zzata, new zzjn((Context)context, AdSize.BANNER), ex.getMessage(), ex.getMessage());
        }
    }
    
    private static zzjn zza(final Context context, final AdSize[] array, final int n) {
        final zzjn zzjn = new zzjn(context, array);
        zzjn.zzarg = zzu(n);
        return zzjn;
    }
    
    private static boolean zzu(final int n) {
        return n == 1;
    }
    
    public final void destroy() {
        try {
            if (this.zzasy != null) {
                this.zzasy.destroy();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final AdListener getAdListener() {
        return this.zzapu;
    }
    
    public final AdSize getAdSize() {
        try {
            if (this.zzasy != null) {
                final zzjn zzbk = this.zzasy.zzbk();
                if (zzbk != null) {
                    return zzbk.zzhy();
                }
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
        if (this.zzarh != null) {
            return this.zzarh[0];
        }
        return null;
    }
    
    public final AdSize[] getAdSizes() {
        return this.zzarh;
    }
    
    public final String getAdUnitId() {
        Label_0027: {
            if (this.zzye != null || this.zzasy == null) {
                break Label_0027;
            }
            try {
                this.zzye = this.zzasy.getAdUnitId();
                return this.zzye;
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                return this.zzye;
            }
        }
    }
    
    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }
    
    public final String getMediationAdapterClassName() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.zzck();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
        return null;
    }
    
    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzasz;
    }
    
    public final VideoController getVideoController() {
        return this.zzasv;
    }
    
    public final VideoOptions getVideoOptions() {
        return this.zzvg;
    }
    
    public final boolean isLoading() {
        try {
            if (this.zzasy != null) {
                return this.zzasy.isLoading();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
        return false;
    }
    
    public final void pause() {
        try {
            if (this.zzasy != null) {
                this.zzasy.pause();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void recordManualImpression() {
        if (!this.zzasu.getAndSet(true)) {
            try {
                if (this.zzasy != null) {
                    this.zzasy.zzbm();
                }
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    public final void resume() {
        try {
            if (this.zzasy != null) {
                this.zzasy.resume();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void setAdListener(final AdListener zzapu) {
        this.zzapu = zzapu;
        this.zzasw.zza(zzapu);
    }
    
    public final void setAdSizes(final AdSize... array) {
        if (this.zzarh != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.zza(array);
    }
    
    public final void setAdUnitId(final String zzye) {
        if (this.zzye != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzye = zzye;
    }
    
    public final void setAppEventListener(final AppEventListener zzvo) {
        try {
            this.zzvo = zzvo;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzjp zzjp;
                if (zzvo != null) {
                    zzjp = new zzjp(zzvo);
                }
                else {
                    zzjp = null;
                }
                zzasy.zza(zzjp);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void setCorrelator(final Correlator zzasx) {
        this.zzasx = zzasx;
        try {
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzlg zzaz;
                if (this.zzasx == null) {
                    zzaz = null;
                }
                else {
                    zzaz = this.zzasx.zzaz();
                }
                zzasy.zza(zzaz);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void setManualImpressionsEnabled(final boolean zzvm) {
        this.zzvm = zzvm;
        try {
            if (this.zzasy != null) {
                this.zzasy.setManualImpressionsEnabled(this.zzvm);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener zzasz) {
        this.zzasz = zzasz;
        try {
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzog zzog;
                if (zzasz != null) {
                    zzog = new zzog(zzasz);
                }
                else {
                    zzog = null;
                }
                zzasy.zza(zzog);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void setVideoOptions(final VideoOptions zzvg) {
        this.zzvg = zzvg;
        try {
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzmu zzmu;
                if (zzvg == null) {
                    zzmu = null;
                }
                else {
                    zzmu = new zzmu(zzvg);
                }
                zzasy.zza(zzmu);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void zza(final zzjd zzapt) {
        try {
            this.zzapt = zzapt;
            if (this.zzasy != null) {
                final zzks zzasy = this.zzasy;
                zzje zzje;
                if (zzapt != null) {
                    zzje = new zzje(zzapt);
                }
                else {
                    zzje = null;
                }
                zzasy.zza(zzje);
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void zza(final zzlw zzlw) {
    Label_0106_Outer:
        while (true) {
            Label_0047: {
                try {
                    if (this.zzasy != null) {
                        break Label_0289;
                    }
                    if ((this.zzarh == null || this.zzye == null) && this.zzasy == null) {
                        throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                    }
                    break Label_0047;
                }
                catch (RemoteException ex) {
                    zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                }
                return;
            }
            final Context context = this.zzata.getContext();
            final zzjn zza = zza(context, this.zzarh, this.zzatb);
            Label_0324: {
                if (!"search_v2".equals(zza.zzarb)) {
                    break Label_0324;
                }
                zzks zzasy = zzjr.zza(context, false, (zzjr.zza<zzks>)new zzjt(zzkb.zzig(), context, zza, this.zzye));
                while (true) {
                    (this.zzasy = zzasy).zza(new zzjf(this.zzasw));
                    if (this.zzapt != null) {
                        this.zzasy.zza(new zzje(this.zzapt));
                    }
                    if (this.zzvo != null) {
                        this.zzasy.zza(new zzjp(this.zzvo));
                    }
                    if (this.zzasz != null) {
                        this.zzasy.zza(new zzog(this.zzasz));
                    }
                    if (this.zzasx != null) {
                        this.zzasy.zza(this.zzasx.zzaz());
                    }
                    if (this.zzvg != null) {
                        this.zzasy.zza(new zzmu(this.zzvg));
                    }
                    this.zzasy.setManualImpressionsEnabled(this.zzvm);
                    try {
                        final IObjectWrapper zzbj = this.zzasy.zzbj();
                        if (zzbj != null) {
                            this.zzata.addView((View)ObjectWrapper.unwrap(zzbj));
                        }
                        if (this.zzasy.zzb(zzjm.zza(this.zzata.getContext(), zzlw))) {
                            this.zzast.zzj(zzlw.zzir());
                            return;
                        }
                        return;
                        zzasy = zzjr.zza(context, false, (zzjr.zza<zzks>)new zzjs(zzkb.zzig(), context, zza, this.zzye, this.zzast));
                        continue;
                    }
                    catch (RemoteException ex2) {
                        zzane.zzd("#007 Could not call remote method.", (Throwable)ex2);
                        continue Label_0106_Outer;
                    }
                    break;
                }
            }
            continue;
        }
    }
    
    public final void zza(final AdSize... zzarh) {
        this.zzarh = zzarh;
        while (true) {
            try {
                if (this.zzasy != null) {
                    this.zzasy.zza(zza(this.zzata.getContext(), this.zzarh, this.zzatb));
                }
                this.zzata.requestLayout();
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public final boolean zza(final zzks zzasy) {
        if (zzasy == null) {
            return false;
        }
        IObjectWrapper zzbj;
        try {
            zzbj = zzasy.zzbj();
            if (zzbj == null) {
                return false;
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            return false;
        }
        if (((View)ObjectWrapper.unwrap(zzbj)).getParent() != null) {
            return false;
        }
        this.zzata.addView((View)ObjectWrapper.unwrap(zzbj));
        this.zzasy = zzasy;
        return true;
    }
    
    public final zzlo zzbc() {
        if (this.zzasy == null) {
            return null;
        }
        try {
            return this.zzasy.getVideoController();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            return null;
        }
    }
}
