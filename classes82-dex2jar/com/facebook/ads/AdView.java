// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.content.res.Configuration;
import com.facebook.ads.internal.adapters.AdAdapter;
import android.graphics.drawable.Drawable;
import android.view.View$OnLongClickListener;
import com.facebook.ads.internal.b.a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.f;
import android.content.Context;
import com.facebook.ads.internal.view.c.c;
import android.view.View;
import com.facebook.ads.internal.b.b;
import com.facebook.ads.internal.protocol.d;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

public class AdView extends RelativeLayout implements Ad
{
    private final DisplayMetrics a;
    private final d b;
    private final String c;
    private b d;
    private AdListener e;
    private View f;
    private c g;
    private String h;
    
    public AdView(final Context context, final String c, final AdSize adSize) {
        super(context);
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.a = this.getContext().getResources().getDisplayMetrics();
        this.b = adSize.toInternalAdSize();
        this.c = c;
        final a a = new a(c, com.facebook.ads.internal.protocol.f.a(this.b), AdPlacementType.BANNER, adSize.toInternalAdSize(), 1);
        a.a(this.h);
        (this.d = new b(context, a)).a(new com.facebook.ads.internal.adapters.a() {
            @Override
            public void a() {
                if (AdView.this.e != null) {
                    AdView.this.e.onAdClicked(AdView.this);
                }
            }
            
            @Override
            public void a(final View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                AdView.this.f = view;
                AdView.this.removeAllViews();
                AdView.this.addView(AdView.this.f);
                if (AdView.this.f instanceof com.facebook.ads.internal.view.c.a) {
                    com.facebook.ads.internal.protocol.f.a(AdView.this.a, AdView.this.f, AdView.this.b);
                }
                if (AdView.this.e != null) {
                    AdView.this.e.onAdLoaded(AdView.this);
                }
                if (com.facebook.ads.internal.r.a.b(AdView.this.getContext())) {
                    AdView.this.g = new c();
                    AdView.this.g.a(c);
                    AdView.this.g.b(AdView.this.getContext().getPackageName());
                    if (AdView.this.d.b() != null) {
                        AdView.this.g.a(AdView.this.d.b().a());
                    }
                    if (AdView.this.f instanceof com.facebook.ads.internal.view.c.a) {
                        AdView.this.g.a(((com.facebook.ads.internal.view.c.a)AdView.this.f).getViewabilityChecker());
                    }
                    AdView.this.f.setOnLongClickListener((View$OnLongClickListener)new View$OnLongClickListener() {
                        public boolean onLongClick(final View view) {
                            boolean b = false;
                            AdView.this.g.setBounds(0, 0, AdView.this.f.getWidth(), AdView.this.f.getHeight());
                            final c f = AdView.this.g;
                            if (!AdView.this.g.a()) {
                                b = true;
                            }
                            f.a(b);
                            return true;
                        }
                    });
                    AdView.this.f.getOverlay().add((Drawable)AdView.this.g);
                }
            }
            
            @Override
            public void a(final AdAdapter adAdapter) {
                if (AdView.this.d != null) {
                    AdView.this.d.e();
                }
            }
            
            @Override
            public void a(final com.facebook.ads.internal.protocol.a a) {
                if (AdView.this.e != null) {
                    AdView.this.e.onError(AdView.this, AdError.getAdErrorFromWrapper(a));
                }
            }
            
            @Override
            public void b() {
                if (AdView.this.e != null) {
                    AdView.this.e.onLoggingImpression(AdView.this);
                }
            }
        });
    }
    
    private void a(final String s) {
        this.d.b(s);
    }
    
    public void destroy() {
        if (this.d != null) {
            this.d.a(true);
            this.d = null;
        }
        if (this.g != null && com.facebook.ads.internal.r.a.b(this.getContext())) {
            this.g.b();
            this.f.getOverlay().remove((Drawable)this.g);
        }
        this.removeAllViews();
        this.f = null;
        this.e = null;
    }
    
    @Deprecated
    public void disableAutoRefresh() {
    }
    
    public String getPlacementId() {
        return this.c;
    }
    
    public boolean isAdInvalidated() {
        return this.d == null || this.d.g();
    }
    
    public void loadAd() {
        this.a((String)null);
    }
    
    public void loadAdFromBid(final String s) {
        this.a(s);
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f != null) {
            com.facebook.ads.internal.protocol.f.a(this.a, this.f, this.b);
        }
    }
    
    public void setAdListener(final AdListener e) {
        this.e = e;
    }
    
    public void setExtraHints(final ExtraHints extraHints) {
        this.h = extraHints.getHints();
    }
}
