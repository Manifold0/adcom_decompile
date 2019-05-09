// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.b;

import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Bitmap;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.c.b;
import android.widget.ImageView$ScaleType;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Color;
import android.widget.ImageView;
import android.view.View$OnTouchListener;
import android.net.Uri;
import android.annotation.TargetApi;
import android.widget.LinearLayout;

@TargetApi(19)
public class a extends LinearLayout
{
    private static final int a;
    private static final Uri b;
    private static final View$OnTouchListener c;
    private static final int d;
    private ImageView e;
    private e f;
    private ImageView g;
    private a h;
    private String i;
    
    static {
        a = Color.rgb(224, 224, 224);
        b = Uri.parse("http://www.facebook.com");
        c = (View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    default: {
                        return false;
                    }
                    case 0: {
                        x.a(view, com.facebook.ads.internal.view.b.a.d);
                        return false;
                    }
                    case 1: {
                        x.a(view, 0);
                        return false;
                    }
                }
            }
        };
        d = Color.argb(34, 0, 0, 0);
    }
    
    public a(final Context context) {
        super(context);
        final float density = this.getResources().getDisplayMetrics().density;
        final int n = (int)(50.0f * density);
        final int n2 = (int)(density * 4.0f);
        x.a((View)this, -1);
        this.setGravity(16);
        (this.e = new ImageView(context)).setContentDescription((CharSequence)"Close");
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(n, n);
        this.e.setScaleType(ImageView$ScaleType.CENTER);
        this.e.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.a));
        this.e.setOnTouchListener(com.facebook.ads.internal.view.b.a.c);
        this.e.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (com.facebook.ads.internal.view.b.a.this.h != null) {
                    com.facebook.ads.internal.view.b.a.this.h.a();
                }
            }
        });
        this.addView((View)this.e, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.f = new e(context);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(0, -2);
        linearLayout$LayoutParams2.weight = 1.0f;
        this.f.setPadding(0, n2, 0, n2);
        this.addView((View)this.f, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        this.g = new ImageView(context);
        final LinearLayout$LayoutParams linearLayout$LayoutParams3 = new LinearLayout$LayoutParams(n, n);
        this.g.setContentDescription((CharSequence)"Open native browser");
        this.g.setScaleType(ImageView$ScaleType.CENTER);
        this.g.setOnTouchListener(com.facebook.ads.internal.view.b.a.c);
        this.g.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (TextUtils.isEmpty((CharSequence)com.facebook.ads.internal.view.b.a.this.i) || "about:blank".equals(com.facebook.ads.internal.view.b.a.this.i)) {
                    return;
                }
                final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(com.facebook.ads.internal.view.b.a.this.i));
                intent.addFlags(268435456);
                com.facebook.ads.internal.view.b.a.this.getContext().startActivity(intent);
            }
        });
        this.addView((View)this.g, (ViewGroup$LayoutParams)linearLayout$LayoutParams3);
        this.setupDefaultNativeBrowser(context);
    }
    
    private void setupDefaultNativeBrowser(final Context context) {
        final List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", com.facebook.ads.internal.view.b.a.b), 65536);
        Bitmap imageBitmap;
        if (queryIntentActivities.size() == 0) {
            this.g.setVisibility(8);
            imageBitmap = null;
        }
        else if (queryIntentActivities.size() == 1 && "com.android.chrome".equals(queryIntentActivities.get(0).activityInfo.packageName)) {
            imageBitmap = com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.c);
        }
        else {
            imageBitmap = com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.d);
        }
        this.g.setImageBitmap(imageBitmap);
    }
    
    public void setListener(final a h) {
        this.h = h;
    }
    
    public void setTitle(final String title) {
        this.f.setTitle(title);
    }
    
    public void setUrl(final String s) {
        this.i = s;
        if (TextUtils.isEmpty((CharSequence)s) || "about:blank".equals(s)) {
            this.f.setSubtitle(null);
            this.g.setEnabled(false);
            this.g.setColorFilter((ColorFilter)new PorterDuffColorFilter(com.facebook.ads.internal.view.b.a.a, PorterDuff$Mode.SRC_IN));
            return;
        }
        this.f.setSubtitle(s);
        this.g.setEnabled(true);
        this.g.setColorFilter((ColorFilter)null);
    }
    
    public interface a
    {
        void a();
    }
}
