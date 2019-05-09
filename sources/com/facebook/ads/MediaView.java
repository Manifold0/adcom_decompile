package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p020a.C1861a.C1804a;
import com.facebook.ads.internal.adapters.p020a.C1862b;
import com.facebook.ads.internal.adapters.p020a.C1863c;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p017t.C2115g;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.p051s.C2087d;
import com.facebook.ads.internal.view.C1807q;
import com.facebook.ads.internal.view.C2506j;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p019c.C2249b;
import com.facebook.ads.internal.view.p019c.C2252d;

public class MediaView extends C1783f {
    /* renamed from: a */
    private static final String f3662a = MediaView.class.getSimpleName();
    /* renamed from: b */
    private C2506j f3663b;
    /* renamed from: c */
    private ImageView f3664c;
    /* renamed from: d */
    private C2249b f3665d;
    /* renamed from: e */
    private RecyclerView f3666e;
    /* renamed from: f */
    private MediaViewVideoRenderer f3667f;
    /* renamed from: g */
    private View f3668g;
    @Nullable
    /* renamed from: h */
    private MediaViewListener f3669h;
    /* renamed from: i */
    private boolean f3670i;
    /* renamed from: j */
    private boolean f3671j;
    /* renamed from: k */
    private boolean f3672k;

    public MediaView(Context context) {
        super(context);
        setIconView(new ImageView(context));
        setImageRenderer(new C2249b(context));
        this.f3663b = new C2506j(context);
        m3924b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context));
        m3923a();
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setIconView(new ImageView(context, attributeSet));
        setImageRenderer(new C2249b(context, attributeSet));
        this.f3663b = new C2506j(context, attributeSet);
        m3924b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet));
        m3923a();
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setIconView(new ImageView(context, attributeSet, i));
        setImageRenderer(new C2249b(context, attributeSet, i));
        this.f3663b = new C2506j(context, attributeSet, i);
        m3924b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i));
        m3923a();
    }

    @TargetApi(21)
    public MediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setIconView(new ImageView(context, attributeSet, i, i2));
        setImageRenderer(new C2249b(context, attributeSet, i, i2));
        this.f3663b = new C2506j(context, attributeSet, i);
        m3924b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i, i2));
        m3923a();
    }

    /* renamed from: a */
    private void m3923a() {
        C2580j.m6643a(this, C2580j.INTERNAL_AD_MEDIA);
        C2580j.m6643a(this.f3665d, C2580j.INTERNAL_AD_MEDIA);
        C2580j.m6643a(this.f3667f, C2580j.INTERNAL_AD_MEDIA);
        C2580j.m6643a(this.f3666e, C2580j.INTERNAL_AD_MEDIA);
        this.f3671j = true;
    }

    /* renamed from: b */
    private void m3924b() {
        if (this.f3670i) {
            throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
        }
        if (this.f3666e != null) {
            C2600x.m6689b(this.f3663b);
        }
        float f = C2600x.f6420b;
        int round = Math.round(4.0f * f);
        int round2 = Math.round(f * 12.0f);
        this.f3663b.setChildSpacing(round);
        this.f3663b.setPadding(0, round2, 0, round2);
        this.f3663b.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.f3663b, layoutParams);
    }

    private void setIconView(ImageView imageView) {
        if (this.f3670i) {
            throw new IllegalStateException("Image renderer must be set before nativeBannerAd.");
        }
        if (this.f3664c != null) {
            C2600x.m6689b(this.f3664c);
        }
        imageView.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) imageView, layoutParams);
        this.f3664c = imageView;
    }

    private void setImageRenderer(C2249b c2249b) {
        if (this.f3670i) {
            throw new IllegalStateException("Image renderer must be set before nativeAd.");
        }
        if (this.f3665d != null) {
            removeView(this.f3665d);
        }
        c2249b.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) c2249b, layoutParams);
        this.f3665d = c2249b;
    }

    @VisibleForTesting
    /* renamed from: a */
    void m3925a(View view, LayoutParams layoutParams) {
        this.f3671j = false;
        addView(view, layoutParams);
        this.f3671j = true;
    }

    /* renamed from: a */
    void m3926a(final NativeAdBase nativeAdBase, boolean z) {
        this.f3670i = true;
        nativeAdBase.m4077b(this);
        this.f3665d.setVisibility(8);
        this.f3665d.m5757a(null, null);
        this.f3667f.setVisibility(8);
        this.f3667f.mo5339a();
        if (this.f3666e != null) {
            this.f3666e.setVisibility(8);
            this.f3666e.setAdapter(null);
        }
        this.f3664c.setVisibility(0);
        bringChildToFront(this.f3664c);
        this.f3668g = this.f3664c;
        C2252d a = new C2252d(this.f3664c).m5771a();
        if (z) {
            a.m5773a(new C1802e(this) {
                /* renamed from: b */
                final /* synthetic */ MediaView f3760b;

                /* renamed from: a */
                public void mo5349a(boolean z) {
                    nativeAdBase.m4078f().m5321a(z, true);
                }
            });
        }
        C2115g i = nativeAdBase.m4078f().m5333i();
        if (i != null) {
            a.m5775a(i.m5352a());
            return;
        }
        if (z) {
            nativeAdBase.m4078f().m5321a(false, true);
        }
        C2625a.m6741b(getContext(), "api", C2626b.f6543h, new Exception("Native Ad Icon is null. Loaded: " + nativeAdBase.m4078f().m5330f()));
    }

    public void addView(View view) {
        if (!this.f3671j) {
            super.addView(view);
        }
    }

    public void addView(View view, int i) {
        if (!this.f3671j) {
            super.addView(view, i);
        }
    }

    public void addView(View view, int i, int i2) {
        if (!this.f3671j) {
            super.addView(view, i, i2);
        }
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (!this.f3671j) {
            super.addView(view, i, layoutParams);
        }
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (!this.f3671j) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.f3666e || view == this.f3667f || view == this.f3665d || view == this.f3664c) {
            super.bringChildToFront(view);
        }
    }

    public void destroy() {
        this.f3667f.pause(false);
        this.f3667f.destroy();
    }

    protected View getAdContentsView() {
        return this.f3668g;
    }

    protected C2085c getAdEventManager() {
        return C2087d.m5183a(getContext());
    }

    public void setListener(final MediaViewListener mediaViewListener) {
        this.f3669h = mediaViewListener;
        if (mediaViewListener == null) {
            this.f3667f.setListener(null);
        } else {
            this.f3667f.setListener(new C1807q(this) {
                /* renamed from: b */
                final /* synthetic */ MediaView f3766b;

                /* renamed from: a */
                public void mo5351a() {
                    mediaViewListener.onVolumeChange(this.f3766b, this.f3766b.f3667f.getVolume());
                }

                /* renamed from: b */
                public void mo5352b() {
                    mediaViewListener.onPause(this.f3766b);
                }

                /* renamed from: c */
                public void mo5353c() {
                    mediaViewListener.onPlay(this.f3766b);
                }

                /* renamed from: d */
                public void mo5354d() {
                    mediaViewListener.onFullscreenBackground(this.f3766b);
                }

                /* renamed from: e */
                public void mo5355e() {
                    mediaViewListener.onFullscreenForeground(this.f3766b);
                }

                /* renamed from: f */
                public void mo5356f() {
                    mediaViewListener.onExitFullscreen(this.f3766b);
                }

                /* renamed from: g */
                public void mo5357g() {
                    mediaViewListener.onEnterFullscreen(this.f3766b);
                }

                /* renamed from: h */
                public void mo5358h() {
                    mediaViewListener.onComplete(this.f3766b);
                }
            });
        }
    }

    void setNativeAd(final NativeAd nativeAd) {
        int i;
        this.f3670i = true;
        nativeAd.m4074a(this);
        this.f3664c.setVisibility(8);
        this.f3664c.setImageDrawable(null);
        if (nativeAd.m4085d() == null) {
            i = 0;
        } else {
            for (NativeAd adCoverImage : nativeAd.m4085d()) {
                if (adCoverImage.getAdCoverImage() == null) {
                    i = 0;
                    break;
                }
            }
            boolean z = true;
        }
        if (i != 0) {
            boolean q = C2078a.m5107q(getContext());
            this.f3666e = this.f3663b;
            ((C2506j) this.f3666e).setCurrentPosition(0);
            Adapter c1862b = q ? new C1862b((C2506j) this.f3666e, nativeAd.m4078f().m5345u()) : new C1863c((C2506j) this.f3666e, nativeAd.m4078f().m5345u());
            c1862b.m4198a(new C1804a(this) {
                /* renamed from: b */
                final /* synthetic */ MediaView f3762b;

                /* renamed from: a */
                public void mo5350a() {
                    nativeAd.m4078f().m5321a(true, true);
                }
            });
            this.f3666e.setAdapter(c1862b);
            this.f3668g = this.f3666e;
            this.f3665d.setVisibility(8);
            this.f3665d.m5757a(null, null);
            this.f3667f.setVisibility(8);
            this.f3667f.mo5339a();
            bringChildToFront(this.f3666e);
            this.f3666e.setVisibility(0);
            return;
        }
        z = VERSION.SDK_INT >= 14 && !TextUtils.isEmpty(nativeAd.m4081a());
        if (z) {
            nativeAd.m4078f().m5323b(this.f3672k);
            this.f3668g = this.f3667f.getVideoView();
            this.f3665d.setVisibility(8);
            this.f3665d.m5757a(null, null);
            if (this.f3666e != null) {
                this.f3666e.setVisibility(8);
                this.f3666e.setAdapter(null);
            }
            bringChildToFront(this.f3667f);
            this.f3667f.setNativeAd(nativeAd);
            this.f3667f.setVisibility(0);
        } else if (nativeAd.getAdCoverImage() != null) {
            this.f3668g = this.f3665d.getBodyImageView();
            this.f3667f.setVisibility(8);
            this.f3667f.mo5339a();
            if (this.f3666e != null) {
                this.f3666e.setVisibility(8);
                this.f3666e.setAdapter(null);
            }
            bringChildToFront(this.f3665d);
            this.f3665d.setVisibility(0);
            new C2252d(this.f3665d).m5772a(getHeight(), getWidth()).m5774a(C2078a.m5103m(getContext())).m5773a(new C1802e(this) {
                /* renamed from: b */
                final /* synthetic */ MediaView f3764b;

                /* renamed from: a */
                public void mo5349a(boolean z) {
                    nativeAd.m4078f().m5321a(z, true);
                }
            }).m5775a(nativeAd.m4078f().m5334j().m5352a());
        }
    }

    public void setVideoRenderer(MediaViewVideoRenderer mediaViewVideoRenderer) {
        if (this.f3670i) {
            throw new IllegalStateException("Video renderer must be set before nativeAd.");
        }
        if (this.f3667f != null) {
            removeView(this.f3667f);
            this.f3667f.destroy();
        }
        mediaViewVideoRenderer.setAdEventManager(getAdEventManager());
        mediaViewVideoRenderer.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        m3925a((View) mediaViewVideoRenderer, layoutParams);
        this.f3667f = mediaViewVideoRenderer;
        this.f3672k = !(this.f3667f instanceof DefaultMediaViewVideoRenderer);
    }
}
