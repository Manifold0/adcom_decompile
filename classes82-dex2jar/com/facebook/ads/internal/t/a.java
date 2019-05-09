// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import com.facebook.ads.internal.view.component.d;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.facebook.ads.internal.view.component.h;
import android.widget.LinearLayout;
import android.support.annotation.Nullable;
import com.facebook.ads.MediaView;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.view.View;
import java.util.ArrayList;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.view.h.c;
import android.widget.FrameLayout;

public class a extends FrameLayout implements c
{
    private static final int a;
    private final j b;
    private final e c;
    private final AdOptionsView d;
    private ArrayList<View> e;
    
    static {
        a = (int)(x.b * 110.0f);
    }
    
    public a(final Context context, final e c, final AdOptionsView d, @Nullable final MediaView mediaView, final MediaView mediaView2, final k k, final j b) {
        super(context);
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = new ArrayList<View>();
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        switch (a$1.a[k.ordinal()]) {
            case 1: {
                final h h = new h(this.getContext(), this.c, this.b);
                h.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, com.facebook.ads.internal.t.a.a));
                ((ViewGroup)linearLayout).addView((View)h);
            }
            case 2: {
                final RelativeLayout relativeLayout = new RelativeLayout(this.getContext());
                relativeLayout.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, (int)(180.0f * x.b)));
                x.a((View)relativeLayout, this.b.b());
                relativeLayout.addView((View)mediaView);
                final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, (int)(180.0f * x.b));
                layoutParams.addRule(13, -1);
                mediaView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                ((ViewGroup)linearLayout).addView((View)relativeLayout);
                this.e.add((View)mediaView);
                break;
            }
        }
        final d d2 = new d(this.getContext(), this.c, this.b, mediaView2, this.d, k == k.c || k == k.b, a(k));
        d2.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, (int)(a(k) * x.b)));
        ((ViewGroup)linearLayout).addView((View)d2);
        this.e.add((View)d2.getIconView());
        this.e.add((View)d2.getCallToActionView());
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
    }
    
    private static int a(final k k) {
        switch (a$1.a[k.ordinal()]) {
            default: {
                return 0;
            }
            case 3:
            case 4:
            case 5: {
                return k.b();
            }
            case 2: {
                return k.b() - 180;
            }
            case 1: {
                return (k.b() - 180) / 2;
            }
        }
    }
    
    public void a() {
        this.c.z();
    }
    
    public View getView() {
        return (View)this;
    }
    
    public ArrayList<View> getViewsForInteraction() {
        return this.e;
    }
}
