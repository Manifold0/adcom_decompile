package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzoy extends zzpd {
    private Object mLock;
    @Nullable
    private zzxz zzbit;
    @Nullable
    private zzyc zzbiu;
    @Nullable
    private zzyf zzbiv;
    private final zzpa zzbiw;
    @Nullable
    private zzoz zzbix;
    private boolean zzbiy;

    private zzoy(Context context, zzpa zzpa, zzci zzci, zzpb zzpb) {
        super(context, zzpa, null, zzci, null, zzpb, null, null);
        this.zzbiy = false;
        this.mLock = new Object();
        this.zzbiw = zzpa;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzxz zzxz, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbit = zzxz;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzyc zzyc, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiu = zzyc;
    }

    public zzoy(Context context, zzpa zzpa, zzci zzci, zzyf zzyf, zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiv = zzyf;
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view = (View) ((WeakReference) entry.getValue()).get();
                if (view != null) {
                    hashMap.put((String) entry.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    public final void cancelUnconfirmedClick() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.cancelUnconfirmedClick();
            }
        }
    }

    public final void setClickConfirmingView(View view) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.setClickConfirmingView(view);
            }
        }
    }

    @Nullable
    public final View zza(OnClickListener onClickListener, boolean z) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                View zza = this.zzbix.zza(onClickListener, z);
                return zza;
            }
            IObjectWrapper zzmv;
            try {
                if (this.zzbiv != null) {
                    zzmv = this.zzbiv.zzmv();
                } else if (this.zzbit != null) {
                    zzmv = this.zzbit.zzmv();
                } else {
                    if (this.zzbiu != null) {
                        zzmv = this.zzbiu.zzmv();
                    }
                    zzmv = null;
                }
            } catch (Throwable e) {
                zzakb.zzc("Failed to call getAdChoicesContent", e);
            }
            if (zzmv != null) {
                zza = (View) ObjectWrapper.unwrap(zzmv);
                return zza;
            }
            return null;
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        synchronized (this.mLock) {
            this.zzbjd = true;
            if (this.zzbix != null) {
                this.zzbix.zza(view, (Map) map);
                this.zzbiw.recordImpression();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideImpressionRecording()) {
                        this.zzbiv.recordImpression();
                        this.zzbiw.recordImpression();
                    } else if (this.zzbit != null && !this.zzbit.getOverrideImpressionRecording()) {
                        this.zzbit.recordImpression();
                        this.zzbiw.recordImpression();
                    } else if (!(this.zzbiu == null || this.zzbiu.getOverrideImpressionRecording())) {
                        this.zzbiu.recordImpression();
                        this.zzbiw.recordImpression();
                    }
                } catch (Throwable e) {
                    zzakb.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(view, map, bundle, view2);
                this.zzbiw.onAdClicked();
            } else {
                try {
                    if (this.zzbiv != null && !this.zzbiv.getOverrideClickHandling()) {
                        this.zzbiv.zzj(ObjectWrapper.wrap(view));
                        this.zzbiw.onAdClicked();
                    } else if (this.zzbit != null && !this.zzbit.getOverrideClickHandling()) {
                        this.zzbit.zzj(ObjectWrapper.wrap(view));
                        this.zzbiw.onAdClicked();
                    } else if (!(this.zzbiu == null || this.zzbiu.getOverrideClickHandling())) {
                        this.zzbiu.zzj(ObjectWrapper.wrap(view));
                        this.zzbiw.onAdClicked();
                    }
                } catch (Throwable e) {
                    zzakb.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.mLock) {
            this.zzbiy = true;
            HashMap zzb = zzb(map);
            HashMap zzb2 = zzb(map2);
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                } else if (this.zzbit != null) {
                    this.zzbit.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbit.zzk(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzbiu.zzk(ObjectWrapper.wrap(view));
                }
            } catch (Throwable e) {
                zzakb.zzc("Failed to call prepareAd", e);
            }
            this.zzbiy = false;
        }
    }

    public final void zza(zzro zzro) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(zzro);
            }
        }
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            try {
                if (this.zzbiv != null) {
                    this.zzbiv.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbit != null) {
                    this.zzbit.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzbiu != null) {
                    this.zzbiu.zzl(ObjectWrapper.wrap(view));
                }
            } catch (Throwable e) {
                zzakb.zzc("Failed to call untrackView", e);
            }
        }
    }

    public final void zzc(@Nullable zzoz zzoz) {
        synchronized (this.mLock) {
            this.zzbix = zzoz;
        }
    }

    public final void zzcr() {
        if (this.zzbix != null) {
            this.zzbix.zzcr();
        }
    }

    public final void zzcs() {
        if (this.zzbix != null) {
            this.zzbix.zzcs();
        }
    }

    public final boolean zzkj() {
        boolean zzkj;
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                zzkj = this.zzbix.zzkj();
            } else {
                zzkj = this.zzbiw.zzcu();
            }
        }
        return zzkj;
    }

    public final boolean zzkk() {
        boolean zzkk;
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                zzkk = this.zzbix.zzkk();
            } else {
                zzkk = this.zzbiw.zzcv();
            }
        }
        return zzkk;
    }

    public final void zzkl() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.mLock) {
            this.zzbje = true;
            if (this.zzbix != null) {
                this.zzbix.zzkl();
            }
        }
    }

    public final boolean zzkm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbiy;
        }
        return z;
    }

    public final zzoz zzkn() {
        zzoz zzoz;
        synchronized (this.mLock) {
            zzoz = this.zzbix;
        }
        return zzoz;
    }

    @Nullable
    public final zzaqw zzko() {
        return null;
    }

    public final void zzkp() {
    }

    public final void zzkq() {
    }
}
