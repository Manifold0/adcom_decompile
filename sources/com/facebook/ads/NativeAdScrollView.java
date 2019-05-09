package com.facebook.ads;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import java.util.ArrayList;
import java.util.List;

public class NativeAdScrollView extends LinearLayout {
    public static final int DEFAULT_INSET = 20;
    public static final int DEFAULT_MAX_ADS = 10;
    /* renamed from: a */
    private final Context f3792a;
    /* renamed from: b */
    private final NativeAdsManager f3793b;
    /* renamed from: c */
    private final AdViewProvider f3794c;
    /* renamed from: d */
    private final Type f3795d;
    /* renamed from: e */
    private final int f3796e;
    /* renamed from: f */
    private final C1831b f3797f;
    /* renamed from: g */
    private final NativeAdViewAttributes f3798g;

    public interface AdViewProvider {
        View createView(NativeAd nativeAd, int i);

        void destroyView(NativeAd nativeAd, View view);
    }

    /* renamed from: com.facebook.ads.NativeAdScrollView$a */
    private class C1830a extends PagerAdapter {
        /* renamed from: a */
        final /* synthetic */ NativeAdScrollView f3789a;
        /* renamed from: b */
        private List<NativeAd> f3790b = new ArrayList();

        public C1830a(NativeAdScrollView nativeAdScrollView) {
            this.f3789a = nativeAdScrollView;
        }

        /* renamed from: a */
        public void m4100a() {
            this.f3790b.clear();
            int min = Math.min(this.f3789a.f3796e, this.f3789a.f3793b.getUniqueNativeAdCount());
            for (int i = 0; i < min; i++) {
                NativeAd nextNativeAd = this.f3789a.f3793b.nextNativeAd();
                nextNativeAd.m4076a(true);
                this.f3790b.add(nextNativeAd);
            }
            notifyDataSetChanged();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (i < this.f3790b.size()) {
                if (this.f3789a.f3795d != null) {
                    ((NativeAd) this.f3790b.get(i)).unregisterView();
                } else {
                    this.f3789a.f3794c.destroyView((NativeAd) this.f3790b.get(i), (View) obj);
                }
            }
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return this.f3790b.size();
        }

        public int getItemPosition(Object obj) {
            int indexOf = this.f3790b.indexOf(obj);
            return indexOf >= 0 ? indexOf : -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View render = this.f3789a.f3795d != null ? NativeAdView.render(this.f3789a.f3792a, (NativeAd) this.f3790b.get(i), this.f3789a.f3795d, this.f3789a.f3798g) : this.f3789a.f3794c.createView((NativeAd) this.f3790b.get(i), i);
            viewGroup.addView(render);
            return render;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    /* renamed from: com.facebook.ads.NativeAdScrollView$b */
    private class C1831b extends ViewPager {
        /* renamed from: a */
        final /* synthetic */ NativeAdScrollView f3791a;

        public C1831b(NativeAdScrollView nativeAdScrollView, Context context) {
            this.f3791a = nativeAdScrollView;
            super(context);
        }

        protected void onMeasure(int i, int i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
            }
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(i3, 1073741824));
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider) {
        this(context, nativeAdsManager, adViewProvider, null, null, 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, int i) {
        this(context, nativeAdsManager, adViewProvider, null, null, i);
    }

    private NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        super(context);
        if (!nativeAdsManager.isLoaded()) {
            throw new IllegalStateException("NativeAdsManager not loaded");
        } else if (type == null && adViewProvider == null) {
            throw new IllegalArgumentException("Must provide a NativeAdView.Type or AdViewProvider");
        } else {
            this.f3792a = context;
            this.f3793b = nativeAdsManager;
            this.f3798g = nativeAdViewAttributes;
            this.f3794c = adViewProvider;
            this.f3795d = type;
            this.f3796e = i;
            PagerAdapter c1830a = new C1830a(this);
            this.f3797f = new C1831b(this, context);
            this.f3797f.setAdapter(c1830a);
            setInset(20);
            c1830a.m4100a();
            addView(this.f3797f);
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type) {
        this(context, nativeAdsManager, null, type, new NativeAdViewAttributes(), 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, i);
    }

    public void setInset(int i) {
        if (i > 0) {
            float f = C2600x.f6420b;
            int round = Math.round(((float) i) * f);
            this.f3797f.setPadding(round, 0, round, 0);
            this.f3797f.setPageMargin(Math.round(f * ((float) (i / 2))));
            this.f3797f.setClipToPadding(false);
        }
    }
}
