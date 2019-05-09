// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzks;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzjj;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzox;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzwy;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyf;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzoo;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzoz;
import com.google.android.gms.internal.ads.zzoy;
import com.google.android.gms.internal.ads.zzoj;
import com.google.android.gms.internal.ads.zzon;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.view.View;
import com.google.android.gms.internal.ads.zzakb;
import java.util.List;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzxa;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzjn;
import android.content.Context;
import com.google.android.gms.internal.ads.zzajh;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzpa;

@zzadh
@ParametersAreNonnullByDefault
public final class zzq extends zzd implements zzpa
{
    private boolean zzvm;
    private zzajh zzwr;
    private boolean zzws;
    
    public zzq(final Context context, final zzw zzw, final zzjn zzjn, final String s, final zzxn zzxn, final zzang zzang) {
        super(context, zzjn, s, zzxn, zzang, zzw);
        this.zzws = false;
    }
    
    private static zzajh zza(final zzaji zzaji, final int n) {
        return new zzajh(zzaji.zzcgs.zzccv, null, zzaji.zzcos.zzbsn, n, zzaji.zzcos.zzbso, zzaji.zzcos.zzces, zzaji.zzcos.orientation, zzaji.zzcos.zzbsu, zzaji.zzcgs.zzccy, zzaji.zzcos.zzceq, null, null, null, zzaji.zzcod, null, zzaji.zzcos.zzcer, zzaji.zzacv, zzaji.zzcos.zzcep, zzaji.zzcoh, zzaji.zzcoi, zzaji.zzcos.zzcev, zzaji.zzcob, null, zzaji.zzcos.zzcfe, zzaji.zzcos.zzcff, zzaji.zzcos.zzcff, zzaji.zzcos.zzcfh, zzaji.zzcos.zzcfi, null, zzaji.zzcos.zzbsr, zzaji.zzcos.zzcfl, zzaji.zzcoq, zzaji.zzcos.zzzl, zzaji.zzcor, zzaji.zzcos.zzcfp, zzaji.zzcos.zzbsp, zzaji.zzcos.zzzm, zzaji.zzcos.zzcfq);
    }
    
    private final void zza(final zzov zzov) {
        zzakk.zzcrm.post((Runnable)new zzs(this, zzov));
    }
    
    private final boolean zzb(final zzajh zzajh, final zzajh zzajh2) {
        this.zzd(null);
        if (!this.zzvw.zzfo()) {
            zzakb.zzdk("Native ad does not have custom rendering mode.");
            this.zzi(0);
            return false;
        }
        zzyf zzmu;
        zzxz zzmo = null;
        zzyc zzmp = null;
        zzqs zzmt = null;
        String zzc = null;
        String headline;
        List images;
        String body;
        zzpw zzjz;
        String callToAction;
        String advertiser;
        double starRating;
        String store;
        String price;
        zzlo videoController;
        View view;
        zzov zzov;
        String headline2;
        List images2;
        String body2;
        zzpw zzjz2;
        String callToAction2;
        double starRating2;
        String store2;
        String price2;
        zzlo videoController2;
        View view2;
        zzov zzov2;
        String headline3;
        List images3;
        String body3;
        zzpw zzjz3;
        String callToAction3;
        double starRating3;
        String store3;
        String price3;
        Bundle extras;
        zzlo videoController3;
        View view3;
        zzoo zzoo;
        String headline4;
        List images4;
        String body4;
        zzpw zzkg;
        String callToAction4;
        String advertiser2;
        zzlo videoController4;
        View view4;
        zzov zzov3;
        String headline5;
        List images5;
        String body5;
        zzpw zzkg2;
        String callToAction5;
        String advertiser3;
        Bundle extras2;
        zzlo videoController5;
        View view5;
        zzoq zzoq;
        Label_0501_Outer:Label_0328_Outer:
        while (true) {
            while (true) {
                Label_0614: {
                    while (true) {
                    Label_0608:
                        while (true) {
                            Label_0602: {
                                try {
                                    if (zzajh2.zzbtx != null) {
                                        zzmu = zzajh2.zzbtx.zzmu();
                                    }
                                    else {
                                        zzmu = null;
                                    }
                                    if (zzajh2.zzbtx != null) {
                                        zzmo = zzajh2.zzbtx.zzmo();
                                    }
                                    else {
                                        zzmo = null;
                                    }
                                    if (zzajh2.zzbtx != null) {
                                        zzmp = zzajh2.zzbtx.zzmp();
                                    }
                                    else {
                                        zzmp = null;
                                    }
                                    if (zzajh2.zzbtx != null) {
                                        zzmt = zzajh2.zzbtx.zzmt();
                                    }
                                    else {
                                        zzmt = null;
                                    }
                                    zzc = zzd.zzc(zzajh2);
                                    if (zzmu != null && this.zzvw.zzadg != null) {
                                        headline = zzmu.getHeadline();
                                        images = zzmu.getImages();
                                        body = zzmu.getBody();
                                        if (zzmu.zzjz() != null) {
                                            zzjz = zzmu.zzjz();
                                        }
                                        else {
                                            zzjz = null;
                                        }
                                        callToAction = zzmu.getCallToAction();
                                        advertiser = zzmu.getAdvertiser();
                                        starRating = zzmu.getStarRating();
                                        store = zzmu.getStore();
                                        price = zzmu.getPrice();
                                        videoController = zzmu.getVideoController();
                                        if (zzmu.zzmw() != null) {
                                            view = (View)ObjectWrapper.unwrap(zzmu.zzmw());
                                        }
                                        else {
                                            view = null;
                                        }
                                        zzov = new zzov(headline, images, body, zzjz, callToAction, advertiser, starRating, store, price, null, videoController, view, zzmu.zzke(), zzc, zzmu.getExtras());
                                        zzov.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmu, zzov));
                                        this.zza(zzov);
                                    }
                                    else {
                                        if (zzmo == null || this.zzvw.zzadg == null) {
                                            break Label_0614;
                                        }
                                        headline2 = zzmo.getHeadline();
                                        images2 = zzmo.getImages();
                                        body2 = zzmo.getBody();
                                        if (zzmo.zzjz() == null) {
                                            break Label_0602;
                                        }
                                        zzjz2 = zzmo.zzjz();
                                        callToAction2 = zzmo.getCallToAction();
                                        starRating2 = zzmo.getStarRating();
                                        store2 = zzmo.getStore();
                                        price2 = zzmo.getPrice();
                                        videoController2 = zzmo.getVideoController();
                                        if (zzmo.zzmw() == null) {
                                            break Label_0608;
                                        }
                                        view2 = (View)ObjectWrapper.unwrap(zzmo.zzmw());
                                        zzov2 = new zzov(headline2, images2, body2, zzjz2, callToAction2, null, starRating2, store2, price2, null, videoController2, view2, zzmo.zzke(), zzc, zzmo.getExtras());
                                        zzov2.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmo, zzov2));
                                        this.zza(zzov2);
                                    }
                                    return super.zza(zzajh, zzajh2);
                                }
                                catch (RemoteException ex) {
                                    zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                                    this.zzi(0);
                                    return false;
                                }
                            }
                            zzjz2 = null;
                            continue Label_0501_Outer;
                        }
                        view2 = null;
                        continue Label_0328_Outer;
                    }
                }
                if (zzmo != null && this.zzvw.zzade != null) {
                    headline3 = zzmo.getHeadline();
                    images3 = zzmo.getImages();
                    body3 = zzmo.getBody();
                    if (zzmo.zzjz() != null) {
                        zzjz3 = zzmo.zzjz();
                    }
                    else {
                        zzjz3 = null;
                    }
                    callToAction3 = zzmo.getCallToAction();
                    starRating3 = zzmo.getStarRating();
                    store3 = zzmo.getStore();
                    price3 = zzmo.getPrice();
                    extras = zzmo.getExtras();
                    videoController3 = zzmo.getVideoController();
                    if (zzmo.zzmw() != null) {
                        view3 = (View)ObjectWrapper.unwrap(zzmo.zzmw());
                    }
                    else {
                        view3 = null;
                    }
                    zzoo = new zzoo(headline3, images3, body3, zzjz3, callToAction3, starRating3, store3, price3, null, extras, videoController3, view3, zzmo.zzke(), zzc);
                    zzoo.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmo, zzoo));
                    zzakk.zzcrm.post((Runnable)new com.google.android.gms.ads.internal.zzt(this, zzoo));
                    continue;
                }
                if (zzmp != null && this.zzvw.zzadg != null) {
                    headline4 = zzmp.getHeadline();
                    images4 = zzmp.getImages();
                    body4 = zzmp.getBody();
                    if (zzmp.zzkg() != null) {
                        zzkg = zzmp.zzkg();
                    }
                    else {
                        zzkg = null;
                    }
                    callToAction4 = zzmp.getCallToAction();
                    advertiser2 = zzmp.getAdvertiser();
                    videoController4 = zzmp.getVideoController();
                    if (zzmp.zzmw() != null) {
                        view4 = (View)ObjectWrapper.unwrap(zzmp.zzmw());
                    }
                    else {
                        view4 = null;
                    }
                    zzov3 = new zzov(headline4, images4, body4, zzkg, callToAction4, advertiser2, -1.0, null, null, null, videoController4, view4, zzmp.zzke(), zzc, zzmp.getExtras());
                    zzov3.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmp, zzov3));
                    this.zza(zzov3);
                    continue;
                }
                if (zzmp != null && this.zzvw.zzadf != null) {
                    headline5 = zzmp.getHeadline();
                    images5 = zzmp.getImages();
                    body5 = zzmp.getBody();
                    if (zzmp.zzkg() != null) {
                        zzkg2 = zzmp.zzkg();
                    }
                    else {
                        zzkg2 = null;
                    }
                    callToAction5 = zzmp.getCallToAction();
                    advertiser3 = zzmp.getAdvertiser();
                    extras2 = zzmp.getExtras();
                    videoController5 = zzmp.getVideoController();
                    if (zzmp.zzmw() != null) {
                        view5 = (View)ObjectWrapper.unwrap(zzmp.zzmw());
                    }
                    else {
                        view5 = null;
                    }
                    zzoq = new zzoq(headline5, images5, body5, zzkg2, callToAction5, advertiser3, null, extras2, videoController5, view5, zzmp.zzke(), zzc);
                    zzoq.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmp, zzoq));
                    zzakk.zzcrm.post((Runnable)new zzu(this, zzoq));
                    continue;
                }
                if (zzmt != null && this.zzvw.zzadi != null && this.zzvw.zzadi.get((Object)zzmt.getCustomTemplateId()) != null) {
                    zzakk.zzcrm.post((Runnable)new zzv(this, zzmt));
                    continue;
                }
                break;
            }
            break;
        }
        zzakb.zzdk("No matching mapper/listener for retrieved native ad template.");
        this.zzi(0);
        return false;
    }
    
    private final boolean zzc(final zzajh zzajh, final zzajh zzajh2) {
        final View zze = zzas.zze(zzajh2);
        if (zze == null) {
            return false;
        }
        final View nextView = this.zzvw.zzacs.getNextView();
        if (nextView != null) {
            if (nextView instanceof zzaqw) {
                ((zzaqw)nextView).destroy();
            }
            this.zzvw.zzacs.removeView(nextView);
        }
        Label_0070: {
            if (zzas.zzf(zzajh2)) {
                break Label_0070;
            }
            try {
                this.zzg(zze);
                if (this.zzvw.zzacs.getChildCount() > 1) {
                    this.zzvw.zzacs.showNext();
                }
                if (zzajh != null) {
                    final View nextView2 = this.zzvw.zzacs.getNextView();
                    if (nextView2 != null) {
                        this.zzvw.zzacs.removeView(nextView2);
                    }
                    this.zzvw.zzfn();
                }
                this.zzvw.zzacs.setMinimumWidth(this.zzbk().widthPixels);
                this.zzvw.zzacs.setMinimumHeight(this.zzbk().heightPixels);
                this.zzvw.zzacs.requestLayout();
                this.zzvw.zzacs.setVisibility(0);
                return true;
            }
            catch (Throwable t) {
                zzbv.zzeo().zza(t, "AdLoaderManager.swapBannerViews");
                zzakb.zzc("Could not add mediation view to view hierarchy.", t);
                return false;
            }
        }
    }
    
    @Nullable
    private final zzwy zzcw() {
        if (this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq) {
            return this.zzvw.zzacw.zzcod;
        }
        return null;
    }
    
    @Nullable
    @Override
    public final zzlo getVideoController() {
        return null;
    }
    
    @Override
    public final void pause() {
        if (!this.zzws) {
            throw new IllegalStateException("Native Ad does not support pause().");
        }
        super.pause();
    }
    
    @Override
    public final void resume() {
        if (!this.zzws) {
            throw new IllegalStateException("Native Ad does not support resume().");
        }
        super.resume();
    }
    
    @Override
    public final void setManualImpressionsEnabled(final boolean zzvm) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = zzvm;
    }
    
    @Override
    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
    }
    
    public final void zza(final zzaji zzaji, final zznx zznx) {
        this.zzwr = null;
        if (zzaji.errorCode != -2) {
            this.zzwr = zza(zzaji, zzaji.errorCode);
        }
        else if (!zzaji.zzcos.zzceq) {
            zzakb.zzdk("partialAdState is not mediation");
            this.zzwr = zza(zzaji, 0);
        }
        if (this.zzwr != null) {
            zzakk.zzcrm.post((Runnable)new zzr(this));
            return;
        }
        if (zzaji.zzacv != null) {
            this.zzvw.zzacv = zzaji.zzacv;
        }
        this.zzvw.zzadv = 0;
        final zzbw zzvw = this.zzvw;
        zzbv.zzej();
        zzvw.zzacu = zzabl.zza(this.zzvw.zzrt, this, zzaji, this.zzvw.zzacq, null, this.zzwh, this, zznx);
    }
    
    @Override
    public final void zza(final zzod zzod) {
        throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
    }
    
    @Override
    public final void zza(final zzox zzox) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable)null);
    }
    
    @Override
    public final void zza(final zzoz zzoz) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable)null);
    }
    
    @Override
    protected final boolean zza(@Nullable final zzajh zzajh, final zzajh zzajh2) {
        if (!this.zzvw.zzfo()) {
            throw new IllegalStateException("AdLoader API does not support custom rendering.");
        }
        if (zzajh2.zzceq) {
            if (zzajh2.zzbtw != null && zzajh2.zzbtw.zzmf()) {
                if (this.zzvw.zzfo() && this.zzvw.zzacs != null) {
                    this.zzvw.zzacs.zzfr().zzdb(zzajh2.zzcev);
                }
                int n;
                if (!super.zza(zzajh, zzajh2)) {
                    n = 0;
                }
                else if (this.zzvw.zzfo() && !this.zzc(zzajh, zzajh2)) {
                    this.zzi(0);
                    n = 0;
                }
                else {
                    if (!this.zzvw.zzfp()) {
                        super.zza(zzajh2, false);
                    }
                    n = 1;
                }
                if (n == 0) {
                    return false;
                }
                this.zzws = true;
            }
            else {
                if (zzajh2.zzbtw == null || !zzajh2.zzbtw.zzmg()) {
                    this.zzi(0);
                    zzakb.zzdk("Response is neither banner nor native.");
                    return false;
                }
                if (!this.zzb(zzajh, zzajh2)) {
                    return false;
                }
            }
            this.zze(new ArrayList<Integer>(Arrays.asList(2)));
            return true;
        }
        this.zzi(0);
        zzakb.zzdk("newState is not mediation.");
        return false;
    }
    
    @Override
    protected final boolean zza(final zzjj zzjj, final zzajh zzajh, final boolean b) {
        return false;
    }
    
    protected final void zzb(@Nullable final IObjectWrapper objectWrapper) {
        Object unwrap;
        if (objectWrapper != null) {
            unwrap = ObjectWrapper.unwrap(objectWrapper);
        }
        else {
            unwrap = null;
        }
        if (unwrap instanceof zzoz) {
            ((zzoz)unwrap).zzkl();
        }
        super.zzb(this.zzvw.zzacw, false);
    }
    
    @Override
    public final boolean zzb(zzjj zzjj) {
        int n;
        if (this.zzvw.zzadn != null && this.zzvw.zzadn.size() == 1 && this.zzvw.zzadn.get(0) == 2) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            zzakb.e("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
            this.zzi(0);
            return false;
        }
        if (this.zzvw.zzadm != null) {
            if (zzjj.zzaqb != this.zzvm) {
                zzjj = new zzjj(zzjj.versionCode, zzjj.zzapw, zzjj.extras, zzjj.zzapx, zzjj.zzapy, zzjj.zzapz, zzjj.zzaqa, zzjj.zzaqb || this.zzvm, zzjj.zzaqc, zzjj.zzaqd, zzjj.zzaqe, zzjj.zzaqf, zzjj.zzaqg, zzjj.zzaqh, zzjj.zzaqi, zzjj.zzaqj, zzjj.zzaqk, zzjj.zzaql);
            }
            return super.zzb(zzjj);
        }
        return super.zzb(zzjj);
    }
    
    @Override
    protected final void zzbq() {
        super.zzbq();
        final zzajh zzacw = this.zzvw.zzacw;
        if (zzacw == null || zzacw.zzbtw == null || !zzacw.zzbtw.zzmf() || this.zzvw.zzadm == null) {
            return;
        }
        try {
            this.zzvw.zzadm.zza((zzks)this, ObjectWrapper.wrap((Object)this.zzvw.zzrt));
            super.zzb(this.zzvw.zzacw, false);
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void zzce() {
        if (this.zzvw.zzacw != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty) && this.zzvw.zzacw.zzbtw != null && this.zzvw.zzacw.zzbtw.zzmg()) {
            this.zzbs();
            return;
        }
        super.zzce();
    }
    
    @Override
    public final void zzcj() {
        if (this.zzvw.zzacw != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty) && this.zzvw.zzacw.zzbtw != null && this.zzvw.zzacw.zzbtw.zzmg()) {
            this.zzbr();
            return;
        }
        super.zzcj();
    }
    
    @Override
    public final void zzcr() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable)null);
    }
    
    @Override
    public final void zzcs() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable)null);
    }
    
    @Override
    public final void zzct() {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable)null);
    }
    
    @Override
    public final boolean zzcu() {
        return this.zzcw() != null && this.zzcw().zzbta;
    }
    
    @Override
    public final boolean zzcv() {
        return this.zzcw() != null && this.zzcw().zzbtb;
    }
    
    public final void zzd(@Nullable final List<String> zzads) {
        Preconditions.checkMainThread("setNativeTemplates must be called on the main UI thread.");
        this.zzvw.zzads = zzads;
    }
    
    public final void zze(final List<Integer> zzadn) {
        Preconditions.checkMainThread("setAllowedAdTypes must be called on the main UI thread.");
        this.zzvw.zzadn = zzadn;
    }
    
    @Override
    public final void zzi(final View view) {
        zzane.zzd("#005 Unexpected call to an abstract (unimplemented) method.", (Throwable)null);
    }
    
    @Nullable
    @Override
    public final zzrc zzr(final String s) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        return (zzrc)this.zzvw.zzadh.get((Object)s);
    }
}
