// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import java.io.Serializable;
import com.facebook.ads.internal.adapters.AdAdapter;
import android.view.View$OnLongClickListener;
import android.graphics.drawable.Drawable;
import com.facebook.ads.internal.protocol.e;
import java.util.EnumSet;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.adapters.n;
import com.facebook.ads.a.a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.view.c.c;
import android.os.Bundle;
import android.view.View;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.b.d;
import android.content.Context;
import android.widget.RelativeLayout;

public class InstreamVideoAdView extends RelativeLayout implements Ad
{
    private final Context a;
    private final String b;
    private final AdSize c;
    private d d;
    @Nullable
    private f e;
    private boolean f;
    @Nullable
    private InstreamVideoAdListener g;
    @Nullable
    private View h;
    @Nullable
    private Bundle i;
    @Nullable
    private c j;
    
    public InstreamVideoAdView(final Context context, final Bundle i) {
        this(context, i.getString("placementID"), (AdSize)i.get("adSize"));
        this.i = i;
    }
    
    public InstreamVideoAdView(final Context a, final String b, final AdSize c) {
        super(a);
        this.f = false;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = this.getController();
    }
    
    private void a(final String s) {
        if (this.i != null) {
            (this.e = (f)new com.facebook.ads.internal.adapters.d().a(AdPlacementType.INSTREAM)).a(this.getContext(), new a() {
                @Override
                public void a(final n n) {
                    InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g == null) {
                        return;
                    }
                    InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                }
                
                @Override
                public void a(final n n, final View view) {
                    if (view == null) {
                        throw new IllegalStateException("Cannot present null view");
                    }
                    InstreamVideoAdView.this.h = view;
                    InstreamVideoAdView.this.removeAllViews();
                    InstreamVideoAdView.this.h.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
                    InstreamVideoAdView.this.addView(InstreamVideoAdView.this.h);
                }
                
                @Override
                public void a(final n n, final AdError adError) {
                    if (InstreamVideoAdView.this.g == null) {
                        return;
                    }
                    InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, adError);
                }
                
                @Override
                public void b(final n n) {
                    if (InstreamVideoAdView.this.g == null) {
                        return;
                    }
                    InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
                }
                
                @Override
                public void c(final n n) {
                }
                
                @Override
                public void d(final n n) {
                    if (InstreamVideoAdView.this.g == null) {
                        return;
                    }
                    InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
                }
            }, this.d.g, this.i.getBundle("adapter"), EnumSet.of(CacheFlag.NONE));
            return;
        }
        this.d.b(s);
    }
    
    private d getController() {
        (this.d = new d(this.getContext(), new com.facebook.ads.internal.b.a(this.b, com.facebook.ads.internal.protocol.e.n, AdPlacementType.INSTREAM, this.c.toInternalAdSize(), 1))).a(new com.facebook.ads.internal.adapters.a() {
            @Override
            public void a() {
                if (InstreamVideoAdView.this.g == null) {
                    return;
                }
                InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
            }
            
            @Override
            public void a(final View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                InstreamVideoAdView.this.h = view;
                InstreamVideoAdView.this.removeAllViews();
                InstreamVideoAdView.this.h.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
                InstreamVideoAdView.this.addView(InstreamVideoAdView.this.h);
                if (com.facebook.ads.internal.r.a.b(InstreamVideoAdView.this.a)) {
                    InstreamVideoAdView.this.j = new c();
                    InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.b);
                    InstreamVideoAdView.this.j.b(InstreamVideoAdView.this.a.getPackageName());
                    if (InstreamVideoAdView.this.d.b() != null) {
                        InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.d.b().a());
                    }
                    InstreamVideoAdView.this.h.getOverlay().add((Drawable)InstreamVideoAdView.this.j);
                    InstreamVideoAdView.this.h.setOnLongClickListener((View$OnLongClickListener)new View$OnLongClickListener() {
                        public boolean onLongClick(final View view) {
                            boolean b = false;
                            if (InstreamVideoAdView.this.h == null || InstreamVideoAdView.this.j == null) {
                                return false;
                            }
                            InstreamVideoAdView.this.j.setBounds(0, 0, InstreamVideoAdView.this.h.getWidth(), InstreamVideoAdView.this.h.getHeight());
                            final c f = InstreamVideoAdView.this.j;
                            if (!InstreamVideoAdView.this.j.a()) {
                                b = true;
                            }
                            f.a(b);
                            return true;
                        }
                    });
                }
            }
            
            @Override
            public void a(final AdAdapter adAdapter) {
                if (InstreamVideoAdView.this.d != null) {
                    InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                    }
                }
            }
            
            @Override
            public void a(final com.facebook.ads.internal.protocol.a a) {
                if (InstreamVideoAdView.this.g == null) {
                    return;
                }
                InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, AdError.getAdErrorFromWrapper(a));
            }
            
            @Override
            public void b() {
                if (InstreamVideoAdView.this.g == null) {
                    return;
                }
                InstreamVideoAdView.this.g.onLoggingImpression(InstreamVideoAdView.this);
            }
            
            @Override
            public void c() {
                if (InstreamVideoAdView.this.g == null) {
                    return;
                }
                InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
            }
        });
        return this.d;
    }
    
    public void destroy() {
        if (this.j != null && com.facebook.ads.internal.r.a.b(this.a)) {
            this.j.b();
            if (this.h != null) {
                this.h.getOverlay().remove((Drawable)this.j);
            }
        }
        if (this.d != null) {
            this.d.a(true);
            this.d = null;
            this.d = this.getController();
            this.e = null;
            this.f = false;
            this.removeAllViews();
        }
    }
    
    public String getPlacementId() {
        return this.b;
    }
    
    public Bundle getSaveInstanceState() {
        n e;
        if (this.e != null) {
            e = this.e;
        }
        else {
            e = (n)this.d.f;
        }
        if (e == null) {
            return null;
        }
        final Bundle g = e.g();
        if (g == null) {
            return null;
        }
        final Bundle bundle = new Bundle();
        bundle.putBundle("adapter", g);
        bundle.putString("placementID", this.b);
        bundle.putSerializable("adSize", (Serializable)this.c);
        return bundle;
    }
    
    public boolean isAdInvalidated() {
        return this.d == null || this.d.g();
    }
    
    public boolean isAdLoaded() {
        return this.f;
    }
    
    public void loadAd() {
        this.a((String)null);
    }
    
    public void loadAdFromBid(final String s) {
        this.a(s);
    }
    
    public void setAdListener(final InstreamVideoAdListener g) {
        this.g = g;
    }
    
    public boolean show() {
        if (!this.f || (this.d == null && this.e == null)) {
            if (this.g != null) {
                this.g.onError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            }
            return false;
        }
        if (this.e != null) {
            this.e.e();
        }
        else {
            this.d.e();
        }
        this.f = false;
        return true;
    }
}
