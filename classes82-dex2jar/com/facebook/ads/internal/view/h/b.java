// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.h;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.TextView;
import android.widget.Button;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.t.k;
import com.facebook.ads.internal.t.j;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.view.View;
import java.util.ArrayList;
import com.facebook.ads.internal.t.e;
import android.widget.LinearLayout;

public class b extends LinearLayout implements c
{
    private static final int a;
    private final e b;
    private final ArrayList<View> c;
    
    static {
        a = (int)(x.b * 6.0f);
    }
    
    public b(final Context context, final e b, final j j, final k k, final MediaView mediaView, final AdOptionsView adOptionsView) {
        super(context);
        this.c = new ArrayList<View>();
        this.b = b;
        this.setOrientation(0);
        final int n = (int)(x.b * k.b());
        final a a = new a(this.getContext(), this.b, j, adOptionsView);
        ((View)a).setPadding(com.facebook.ads.internal.view.h.b.a, com.facebook.ads.internal.view.h.b.a, com.facebook.ads.internal.view.h.b.a, com.facebook.ads.internal.view.h.b.a);
        final Button button = new Button(this.getContext());
        j.c((TextView)button);
        button.setText((CharSequence)b.a("call_to_action"));
        this.addView((View)mediaView, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n, n));
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(0, -2);
        linearLayout$LayoutParams.weight = 1.0f;
        linearLayout$LayoutParams.gravity = 16;
        this.addView((View)a, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.addView((View)button, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n * 2, n));
        this.c.add((View)mediaView);
        this.c.add((View)button);
    }
    
    public void a() {
        this.b.z();
    }
    
    public View getView() {
        return (View)this;
    }
    
    public ArrayList<View> getViewsForInteraction() {
        return this.c;
    }
}
