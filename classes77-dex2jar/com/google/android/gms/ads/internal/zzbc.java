// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import android.support.v4.util.SimpleArrayMap;
import java.util.concurrent.Future;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzacq;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzue;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.internal.ads.zzlr;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.util.Log;
import com.google.android.gms.internal.ads.zzjj;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyf;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzoy;
import com.google.android.gms.internal.ads.zzoj;
import android.view.View;
import com.google.android.gms.internal.ads.zzos;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzgd;
import com.google.android.gms.internal.ads.zzev;
import com.google.android.gms.internal.ads.zzox;
import com.google.android.gms.internal.ads.zzod;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import com.google.android.gms.internal.ads.zzane;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzanz;
import java.util.concurrent.Callable;
import com.google.android.gms.internal.ads.zzaki;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzoz;
import com.google.android.gms.internal.ads.zzpd;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzon;
import java.util.List;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzov;
import java.util.UUID;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzjn;
import android.content.Context;
import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzaoj;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.internal.ads.zzacm;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzaqw;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzpa;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbc extends zzd implements zzpa
{
    private final Object mLock;
    private zzaqw zzaaa;
    @Nullable
    private zzaqw zzaab;
    private int zzaac;
    @GuardedBy("mLock")
    private zzacm zzaad;
    private final String zzaae;
    private boolean zzwl;
    @VisibleForTesting
    private boolean zzzy;
    private zzaoj<zzpb> zzzz;
    
    public zzbc(final Context context, final zzw zzw, final zzjn zzjn, final String s, final zzxn zzxn, final zzang zzang) {
        this(context, zzw, zzjn, s, zzxn, zzang, false);
    }
    
    public zzbc(final Context context, final zzw zzw, final zzjn zzjn, final String s, final zzxn zzxn, final zzang zzang, final boolean zzzy) {
        super(context, zzjn, s, zzxn, zzang, zzw);
        this.mLock = new Object();
        this.zzzz = new zzaoj<zzpb>();
        this.zzaac = 1;
        this.zzaae = UUID.randomUUID().toString();
        this.zzzy = zzzy;
    }
    
    private static zzov zza(final zzpb zzpb) {
        zzov zzov = null;
        Object o = null;
        if (zzpb instanceof zzoq) {
            final zzoq zzoq = (zzoq)zzpb;
            zzov = new zzov(zzoq.getHeadline(), zzoq.getImages(), zzoq.getBody(), zzoq.zzkg(), zzoq.getCallToAction(), zzoq.getAdvertiser(), -1.0, null, null, zzoq.zzkc(), zzoq.getVideoController(), zzoq.zzkd(), zzoq.zzke(), zzoq.getMediationAdapterClassName(), zzoq.getExtras());
            if (zzoq.zzka() != null) {
                o = ObjectWrapper.unwrap(zzoq.zzka());
            }
            else {
                o = null;
            }
        }
        else if (zzpb instanceof zzoo) {
            final zzoo zzoo = (zzoo)zzpb;
            zzov = new zzov(zzoo.getHeadline(), zzoo.getImages(), zzoo.getBody(), zzoo.zzjz(), zzoo.getCallToAction(), null, zzoo.getStarRating(), zzoo.getStore(), zzoo.getPrice(), zzoo.zzkc(), zzoo.getVideoController(), zzoo.zzkd(), zzoo.zzke(), zzoo.getMediationAdapterClassName(), zzoo.getExtras());
            if (zzoo.zzka() != null) {
                o = ObjectWrapper.unwrap(zzoo.zzka());
            }
            else {
                o = null;
            }
        }
        if (o instanceof zzpd) {
            zzov.zzb((zzoz)o);
        }
        return zzov;
    }
    
    private static void zza(final zzbw zzbw, final zzbw zzbw2) {
        if (zzbw2.zzade == null) {
            zzbw2.zzade = zzbw.zzade;
        }
        if (zzbw2.zzadf == null) {
            zzbw2.zzadf = zzbw.zzadf;
        }
        if (zzbw2.zzadh == null) {
            zzbw2.zzadh = zzbw.zzadh;
        }
        if (zzbw2.zzadi == null) {
            zzbw2.zzadi = zzbw.zzadi;
        }
        if (zzbw2.zzadk == null) {
            zzbw2.zzadk = zzbw.zzadk;
        }
        if (zzbw2.zzadj == null) {
            zzbw2.zzadj = zzbw.zzadj;
        }
        if (zzbw2.zzads == null) {
            zzbw2.zzads = zzbw.zzads;
        }
        if (zzbw2.zzacy == null) {
            zzbw2.zzacy = zzbw.zzacy;
        }
        if (zzbw2.zzadt == null) {
            zzbw2.zzadt = zzbw.zzadt;
        }
        if (zzbw2.zzacz == null) {
            zzbw2.zzacz = zzbw.zzacz;
        }
        if (zzbw2.zzada == null) {
            zzbw2.zzada = zzbw.zzada;
        }
        if (zzbw2.zzacv == null) {
            zzbw2.zzacv = zzbw.zzacv;
        }
        if (zzbw2.zzacw == null) {
            zzbw2.zzacw = zzbw.zzacw;
        }
        if (zzbw2.zzacx == null) {
            zzbw2.zzacx = zzbw.zzacx;
        }
    }
    
    private final void zza(final zzoo zzoo) {
        zzakk.zzcrm.post((Runnable)new zzbg(this, zzoo));
    }
    
    private final void zza(final zzoq zzoq) {
        zzakk.zzcrm.post((Runnable)new zzbi(this, zzoq));
    }
    
    private final void zza(final zzov zzov) {
        zzakk.zzcrm.post((Runnable)new zzbh(this, zzov));
    }
    
    private final boolean zzcp() {
        return this.zzvw.zzacw != null && this.zzvw.zzacw.zzcfp;
    }
    
    @Nullable
    private final zzwy zzcw() {
        if (this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq) {
            return this.zzvw.zzacw.zzcod;
        }
        return null;
    }
    
    private final void zzdx() {
        final zzacm zzdr = this.zzdr();
        if (zzdr != null) {
            zzdr.zzmc();
        }
    }
    
    @Override
    public final String getAdUnitId() {
        return this.zzvw.zzacp;
    }
    
    public final String getUuid() {
        return this.zzaae;
    }
    
    @Override
    public final void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }
    
    @Override
    public final void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }
    
    @Override
    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }
    
    @Override
    public final void zza(final zzaaw zzaaw) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }
    
    public final void zza(zzaji ex, zznx zznx) {
        if (((zzaji)ex).zzacv != null) {
            this.zzvw.zzacv = ((zzaji)ex).zzacv;
        }
        if (((zzaji)ex).errorCode != -2) {
            zzakk.zzcrm.post((Runnable)new zzbd(this, (zzaji)ex));
        }
        else {
            final int zzceg = ((zzaji)ex).zzcgs.zzceg;
            if (zzceg == 1) {
                this.zzvw.zzadv = 0;
                final zzbw zzvw = this.zzvw;
                zzbv.zzej();
                zzvw.zzacu = zzabl.zza(this.zzvw.zzrt, this, (zzaji)ex, this.zzvw.zzacq, null, this.zzwh, this, zznx);
                final String value = String.valueOf(this.zzvw.zzacu.getClass().getName());
                String concat;
                if (value.length() != 0) {
                    concat = "AdRenderer: ".concat(value);
                }
                else {
                    concat = new String("AdRenderer: ");
                }
                zzakb.zzck(concat);
                return;
            }
            final JSONArray jsonArray = new JSONArray();
            try {
                final JSONArray jsonArray2 = new JSONObject(((zzaji)ex).zzcos.zzceo).getJSONArray("slots");
                for (int i = 0; i < jsonArray2.length(); ++i) {
                    final JSONArray jsonArray3 = jsonArray2.getJSONObject(i).getJSONArray("ads");
                    for (int j = 0; j < jsonArray3.length(); ++j) {
                        jsonArray.put(jsonArray3.get(j));
                    }
                }
            }
            catch (JSONException ex2) {
                zzakb.zzc("Malformed native ad response", (Throwable)ex2);
                this.zzi(0);
                return;
            }
            this.zzdx();
            zznx = (zznx)new ArrayList();
            for (int k = 0; k < zzceg; ++k) {
                ((List<zzanz<Object>>)zznx).add(zzaki.zza((Callable<Object>)new zzbe(this, k, jsonArray, zzceg, (zzaji)ex)));
            }
            int l = 0;
            while (l < ((List)zznx).size()) {
                while (true) {
                    try {
                        ex = (InterruptedException)((List<Object>)zznx).get(l);
                        ex = (InterruptedException)((Future<zzpb>)ex).get((long)zzkb.zzik().zzd(zznk.zzbao), TimeUnit.MILLISECONDS);
                        zzakk.zzcrm.post((Runnable)new zzbf(this, (zzpb)ex, l, (List)zznx));
                        ++l;
                    }
                    catch (InterruptedException ex) {
                        zzane.zzc("", (Throwable)ex);
                        Thread.currentThread().interrupt();
                        continue;
                    }
                    catch (ExecutionException ex3) {}
                    catch (CancellationException ex) {
                        goto Label_0417;
                    }
                    catch (TimeoutException ex) {
                        goto Label_0417;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public final void zza(final zzod zzod) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }
    
    @Override
    public final void zza(final zzox zzox) {
        if (this.zzaaa != null) {
            this.zzaaa.zzb(zzox);
        }
    }
    
    @Override
    public final void zza(final zzoz zzoz) {
        if (this.zzvw.zzacw.zzcob != null) {
            zzbv.zzeo().zzqd().zza(this.zzvw.zzacv, this.zzvw.zzacw, new zzev(zzoz), null);
        }
    }
    
    @Override
    protected final boolean zza(final zzajh zzajh, final zzajh zzajh2) {
        this.zzd(null);
        if (!this.zzvw.zzfo()) {
            throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
        }
    Label_0338:
        while (true) {
            if (!zzajh2.zzceq) {
                final zzpb zzcoj = zzajh2.zzcoj;
                if (this.zzzy) {
                    this.zzzz.set(zzcoj);
                    break Label_0338;
                }
                if (zzcoj instanceof zzoq && this.zzvw.zzadg != null) {
                    this.zza(zza(zzajh2.zzcoj));
                    break Label_0338;
                }
                if (zzcoj instanceof zzoq && this.zzvw.zzadf != null) {
                    this.zza((zzoq)zzajh2.zzcoj);
                    break Label_0338;
                }
                if (zzcoj instanceof zzoo && this.zzvw.zzadg != null) {
                    this.zza(zza(zzajh2.zzcoj));
                    break Label_0338;
                }
                if (zzcoj instanceof zzoo && this.zzvw.zzade != null) {
                    this.zza((zzoo)zzajh2.zzcoj);
                    break Label_0338;
                }
                if (zzcoj instanceof zzos && this.zzvw.zzadi != null && this.zzvw.zzadi.get((Object)((zzos)zzcoj).getCustomTemplateId()) != null) {
                    zzakk.zzcrm.post((Runnable)new zzbj(this, ((zzos)zzcoj).getCustomTemplateId(), zzajh2));
                    break Label_0338;
                }
                zzakb.zzdk("No matching listener for retrieved native ad template.");
                this.zzi(0);
                return false;
            }
        Label_0511_Outer:
            while (true) {
                this.zzdx();
                zzxz zzmo = null;
                zzyc zzmp = null;
                zzqs zzmt = null;
                String zzc = null;
                Label_0622: {
                    while (true) {
                    Label_0616:
                        while (true) {
                            Label_0610: {
                                try {
                                    zzyf zzmu;
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
                                        final String headline = zzmu.getHeadline();
                                        final List images = zzmu.getImages();
                                        final String body = zzmu.getBody();
                                        zzpw zzjz;
                                        if (zzmu.zzjz() != null) {
                                            zzjz = zzmu.zzjz();
                                        }
                                        else {
                                            zzjz = null;
                                        }
                                        final String callToAction = zzmu.getCallToAction();
                                        final String advertiser = zzmu.getAdvertiser();
                                        final double starRating = zzmu.getStarRating();
                                        final String store = zzmu.getStore();
                                        final String price = zzmu.getPrice();
                                        final zzlo videoController = zzmu.getVideoController();
                                        View view;
                                        if (zzmu.zzmw() != null) {
                                            view = (View)ObjectWrapper.unwrap(zzmu.zzmw());
                                        }
                                        else {
                                            view = null;
                                        }
                                        final zzov zzov = new zzov(headline, images, body, zzjz, callToAction, advertiser, starRating, store, price, null, videoController, view, zzmu.zzke(), zzc, zzmu.getExtras());
                                        zzov.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmu, zzov));
                                        this.zza(zzov);
                                    }
                                    else {
                                        if (zzmo == null || this.zzvw.zzadg == null) {
                                            break Label_0622;
                                        }
                                        final String headline2 = zzmo.getHeadline();
                                        final List images2 = zzmo.getImages();
                                        final String body2 = zzmo.getBody();
                                        if (zzmo.zzjz() == null) {
                                            break Label_0610;
                                        }
                                        final zzpw zzjz2 = zzmo.zzjz();
                                        final String callToAction2 = zzmo.getCallToAction();
                                        final double starRating2 = zzmo.getStarRating();
                                        final String store2 = zzmo.getStore();
                                        final String price2 = zzmo.getPrice();
                                        final zzlo videoController2 = zzmo.getVideoController();
                                        if (zzmo.zzmw() == null) {
                                            break Label_0616;
                                        }
                                        final View view2 = (View)ObjectWrapper.unwrap(zzmo.zzmw());
                                        final zzov zzov2 = new zzov(headline2, images2, body2, zzjz2, callToAction2, null, starRating2, store2, price2, null, videoController2, view2, zzmo.zzke(), zzc, zzmo.getExtras());
                                        zzov2.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmo, zzov2));
                                        this.zza(zzov2);
                                    }
                                    return super.zza(zzajh, zzajh2);
                                }
                                catch (RemoteException ex) {
                                    zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                                    return super.zza(zzajh, zzajh2);
                                }
                                continue Label_0338;
                            }
                            final zzpw zzjz2 = null;
                            continue Label_0511_Outer;
                        }
                        final View view2 = null;
                        continue;
                    }
                }
                if (zzmo != null && this.zzvw.zzade != null) {
                    final String headline3 = zzmo.getHeadline();
                    final List images3 = zzmo.getImages();
                    final String body3 = zzmo.getBody();
                    zzpw zzjz3;
                    if (zzmo.zzjz() != null) {
                        zzjz3 = zzmo.zzjz();
                    }
                    else {
                        zzjz3 = null;
                    }
                    final String callToAction3 = zzmo.getCallToAction();
                    final double starRating3 = zzmo.getStarRating();
                    final String store3 = zzmo.getStore();
                    final String price3 = zzmo.getPrice();
                    final Bundle extras = zzmo.getExtras();
                    final zzlo videoController3 = zzmo.getVideoController();
                    View view3;
                    if (zzmo.zzmw() != null) {
                        view3 = (View)ObjectWrapper.unwrap(zzmo.zzmw());
                    }
                    else {
                        view3 = null;
                    }
                    final zzoo zzoo = new zzoo(headline3, images3, body3, zzjz3, callToAction3, starRating3, store3, price3, null, extras, videoController3, view3, zzmo.zzke(), zzc);
                    zzoo.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmo, zzoo));
                    this.zza(zzoo);
                    continue Label_0338;
                }
                if (zzmp != null && this.zzvw.zzadg != null) {
                    final String headline4 = zzmp.getHeadline();
                    final List images4 = zzmp.getImages();
                    final String body4 = zzmp.getBody();
                    zzpw zzkg;
                    if (zzmp.zzkg() != null) {
                        zzkg = zzmp.zzkg();
                    }
                    else {
                        zzkg = null;
                    }
                    final String callToAction4 = zzmp.getCallToAction();
                    final String advertiser2 = zzmp.getAdvertiser();
                    final zzlo videoController4 = zzmp.getVideoController();
                    View view4;
                    if (zzmp.zzmw() != null) {
                        view4 = (View)ObjectWrapper.unwrap(zzmp.zzmw());
                    }
                    else {
                        view4 = null;
                    }
                    final zzov zzov3 = new zzov(headline4, images4, body4, zzkg, callToAction4, advertiser2, -1.0, null, null, null, videoController4, view4, zzmp.zzke(), zzc, zzmp.getExtras());
                    zzov3.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmp, zzov3));
                    this.zza(zzov3);
                    continue Label_0338;
                }
                if (zzmp != null && this.zzvw.zzadf != null) {
                    final String headline5 = zzmp.getHeadline();
                    final List images5 = zzmp.getImages();
                    final String body5 = zzmp.getBody();
                    zzpw zzkg2;
                    if (zzmp.zzkg() != null) {
                        zzkg2 = zzmp.zzkg();
                    }
                    else {
                        zzkg2 = null;
                    }
                    final String callToAction5 = zzmp.getCallToAction();
                    final String advertiser3 = zzmp.getAdvertiser();
                    final Bundle extras2 = zzmp.getExtras();
                    final zzlo videoController5 = zzmp.getVideoController();
                    View view5;
                    if (zzmp.zzmw() != null) {
                        view5 = (View)ObjectWrapper.unwrap(zzmp.zzmw());
                    }
                    else {
                        view5 = null;
                    }
                    final zzoq zzoq = new zzoq(headline5, images5, body5, zzkg2, callToAction5, advertiser3, null, extras2, videoController5, view5, zzmp.zzke(), zzc);
                    zzoq.zzb(new zzoy(this.zzvw.zzrt, this, this.zzvw.zzacq, zzmp, zzoq));
                    this.zza(zzoq);
                    continue Label_0338;
                }
                if (zzmt != null && this.zzvw.zzadi != null && this.zzvw.zzadi.get((Object)zzmt.getCustomTemplateId()) != null) {
                    zzakk.zzcrm.post((Runnable)new zzbk(this, zzmt));
                    continue Label_0338;
                }
                break;
            }
            break;
        }
        zzakb.zzdk("No matching mapper/listener for retrieved native ad template.");
        this.zzi(0);
        return false;
    }
    
    @Override
    protected final boolean zza(final zzjj zzjj, final zzajh zzajh, final boolean b) {
        return this.zzvv.zzdz();
    }
    
    @Override
    public final boolean zza(final zzjj zzjj, final zznx zznx) {
        try {
            this.zzdq();
            return super.zza(zzjj, zznx, this.zzaac);
        }
        catch (Exception ex) {
            if (zzane.isLoggable(4)) {
                Log.i("Ads", "Error initializing webview.", (Throwable)ex);
            }
            return false;
        }
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
    protected final void zzb(final boolean b) {
        String s = null;
        super.zzb(b);
        if (this.zzwl && (boolean)zzkb.zzik().zzd(zznk.zzbcb)) {
            this.zzdt();
        }
        if (this.zzcp() && (this.zzaab != null || this.zzaaa != null)) {
            zzaqw zzaqw;
            if (this.zzaab != null) {
                zzaqw = this.zzaab;
            }
            else if (this.zzaaa != null) {
                zzaqw = this.zzaaa;
                s = "javascript";
            }
            else {
                zzaqw = null;
            }
            if (zzaqw.getWebView() != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
                this.zzwb = zzbv.zzfa().zza(new StringBuilder(23).append(this.zzvw.zzacr.zzcve).append(".").append(this.zzvw.zzacr.zzcvf).toString(), zzaqw.getWebView(), "", "javascript", s);
                if (this.zzwb != null) {
                    zzbv.zzfa().zzm(this.zzwb);
                }
            }
        }
    }
    
    @Override
    protected final void zzbq() {
        this.zzb(false);
    }
    
    @Override
    protected final void zzc(final int n, final boolean b) {
        this.zzdx();
        super.zzc(n, b);
    }
    
    @Override
    public final void zzcd() {
        final zzajh zzacw = this.zzvw.zzacw;
        if (zzacw.zzbtx == null) {
            super.zzcd();
        }
        else {
            while (true) {
                while (true) {
                    zzxq zzbtx;
                    try {
                        zzbtx = zzacw.zzbtx;
                        zzlo zzlo = null;
                        final zzxz zzmo = zzbtx.zzmo();
                        if (zzmo != null) {
                            zzlo = zzmo.getVideoController();
                            if (zzlo == null) {
                                break;
                            }
                            final zzlr zzio = zzlo.zzio();
                            if (zzio != null) {
                                zzio.onVideoEnd();
                                return;
                            }
                            break;
                        }
                    }
                    catch (RemoteException ex) {
                        zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                        return;
                    }
                    final zzyc zzmp = zzbtx.zzmp();
                    if (zzmp != null) {
                        final zzlo zzlo = zzmp.getVideoController();
                        continue;
                    }
                    final zzqs zzmt = zzbtx.zzmt();
                    if (zzmt != null) {
                        final zzlo zzlo = zzmt.getVideoController();
                        continue;
                    }
                    continue;
                }
            }
        }
    }
    
    @Override
    public final void zzce() {
        if (this.zzvw.zzacw != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty)) {
            this.zzbs();
            return;
        }
        super.zzce();
    }
    
    @Override
    public final void zzcj() {
        if (this.zzvw.zzacw != null && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty)) {
            this.zzbr();
            return;
        }
        super.zzcj();
    }
    
    @Override
    public final void zzcr() {
        if (this.zzcp() && this.zzwb != null) {
            zzue zzue = null;
            if (this.zzaab != null) {
                zzue = this.zzaab;
            }
            else if (this.zzaaa != null) {
                zzue = this.zzaaa;
            }
            if (zzue != null) {
                zzue.zza("onSdkImpression", new HashMap<String, Object>());
            }
        }
    }
    
    @Override
    public final void zzcs() {
        super.zzby();
        if (this.zzaab != null) {
            this.zzaab.destroy();
            this.zzaab = null;
        }
    }
    
    @Override
    public final void zzct() {
        if (this.zzaaa != null) {
            this.zzaaa.destroy();
            this.zzaaa = null;
        }
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
    
    final void zzdq() throws zzarg {
        synchronized (this.mLock) {
            zzakb.v("Initializing webview native ads utills");
            this.zzaad = new zzacq(this.zzvw.zzrt, this, this.zzaae, this.zzvw.zzacq, this.zzvw.zzacr);
        }
    }
    
    @Nullable
    public final zzacm zzdr() {
        synchronized (this.mLock) {
            return this.zzaad;
        }
    }
    
    protected final Future<zzpb> zzds() {
        return this.zzzz;
    }
    
    public final void zzdt() {
        if (this.zzvw.zzacw != null && this.zzaaa != null) {
            zzbv.zzeo().zzqd().zza(this.zzvw.zzacv, this.zzvw.zzacw, this.zzaaa.getView(), this.zzaaa);
            this.zzwl = false;
            return;
        }
        this.zzwl = true;
        zzakb.zzdk("Request to enable ActiveView before adState is available.");
    }
    
    public final void zzdu() {
        this.zzwl = false;
        if (this.zzvw.zzacw != null && this.zzaaa != null) {
            zzbv.zzeo().zzqd().zzh(this.zzvw.zzacw);
            return;
        }
        zzakb.zzdk("Request to enable ActiveView before adState is available.");
    }
    
    public final SimpleArrayMap<String, zzrf> zzdv() {
        Preconditions.checkMainThread("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzvw.zzadi;
    }
    
    public final void zzdw() {
        if (this.zzaaa != null && this.zzaaa.zztm() != null && this.zzvw.zzadj != null && this.zzvw.zzadj.zzbjr != null) {
            this.zzaaa.zztm().zzb(this.zzvw.zzadj.zzbjr);
        }
    }
    
    public final void zzf(final zzaqw zzaaa) {
        this.zzaaa = zzaaa;
    }
    
    public final void zzg(@Nullable final zzaqw zzaab) {
        this.zzaab = zzaab;
    }
    
    @Override
    protected final void zzi(final int n) {
        this.zzc(n, false);
    }
    
    @Override
    public final void zzi(final View view) {
        if (this.zzwb != null) {
            zzbv.zzfa().zza(this.zzwb, view);
        }
    }
    
    public final void zzj(final int zzaac) {
        Preconditions.checkMainThread("setMaxNumberOfAds must be called on the main UI thread.");
        this.zzaac = zzaac;
    }
    
    @Nullable
    @Override
    public final zzrc zzr(final String s) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        if (this.zzvw.zzadh == null) {
            return null;
        }
        return (zzrc)this.zzvw.zzadh.get((Object)s);
    }
}
