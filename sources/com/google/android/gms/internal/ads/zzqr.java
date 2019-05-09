package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzqr extends NativeContentAd {
    private final VideoController zzasv = new VideoController();
    private final List<Image> zzbko = new ArrayList();
    private final AdChoicesInfo zzbkq;
    private final zzqo zzbkr;
    private final zzpz zzbks;

    public zzqr(zzqo zzqo) {
        zzpz zzpz;
        AdChoicesInfo adChoicesInfo = null;
        this.zzbkr = zzqo;
        try {
            List images = this.zzbkr.getImages();
            if (images != null) {
                for (Object next : images) {
                    zzpw zzpw;
                    Object next2;
                    if (next2 instanceof IBinder) {
                        IBinder iBinder = (IBinder) next2;
                        if (iBinder != null) {
                            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                            if (queryLocalInterface instanceof zzpw) {
                                zzpw = (zzpw) queryLocalInterface;
                            } else {
                                next2 = new zzpy(iBinder);
                            }
                            if (zzpw != null) {
                                this.zzbko.add(new zzpz(zzpw));
                            }
                        }
                    }
                    zzpw = null;
                    if (zzpw != null) {
                        this.zzbko.add(new zzpz(zzpw));
                    }
                }
            }
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
        try {
            zzpw zzkg = this.zzbkr.zzkg();
            zzpz = zzkg != null ? new zzpz(zzkg) : null;
        } catch (Throwable e2) {
            zzane.zzb("", e2);
            zzpz = null;
        }
        this.zzbks = zzpz;
        try {
            if (this.zzbkr.zzkf() != null) {
                adChoicesInfo = new zzpv(this.zzbkr.zzkf());
            }
        } catch (Throwable e22) {
            zzane.zzb("", e22);
        }
        this.zzbkq = adChoicesInfo;
    }

    private final IObjectWrapper zzka() {
        try {
            return this.zzbkr.zzka();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzbkr.destroy();
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final AdChoicesInfo getAdChoicesInfo() {
        return this.zzbkq;
    }

    public final CharSequence getAdvertiser() {
        try {
            return this.zzbkr.getAdvertiser();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getBody() {
        try {
            return this.zzbkr.getBody();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzbkr.getCallToAction();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzbkr.getExtras();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzbkr.getHeadline();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final List<Image> getImages() {
        return this.zzbko;
    }

    public final Image getLogo() {
        return this.zzbks;
    }

    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzbkr.getMediationAdapterClassName();
        } catch (Throwable e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzbkr.getVideoController() != null) {
                this.zzasv.zza(this.zzbkr.getVideoController());
            }
        } catch (Throwable e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzasv;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzbkr.performClick(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzbkr.recordImpression(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzbkr.reportTouchEvent(bundle);
        } catch (Throwable e) {
            zzane.zzb("", e);
        }
    }

    protected final /* synthetic */ Object zzbe() {
        return zzka();
    }
}
