// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.graphics.Canvas;
import com.facebook.ads.internal.w.c.c;
import android.view.View;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.o.d;
import android.content.Context;
import android.content.res.Resources;
import com.facebook.ads.internal.view.i.b.x;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.a;
import android.graphics.Paint;
import com.facebook.ads.internal.view.i.a.b;
import android.widget.ImageView;

public class f extends ImageView implements b
{
    private static final int a;
    private final Paint b;
    @Nullable
    private a c;
    private final x d;
    
    static {
        a = (int)(4.0f * Resources.getSystem().getDisplayMetrics().density);
    }
    
    public f(final Context context) {
        super(context);
        this.d = new x() {
            @Override
            public void a(final w w) {
                f.this.a();
            }
        };
        (this.b = new Paint()).setColor(-1728053248);
        this.setColorFilter(-1);
        this.setPadding(f.a, f.a, f.a, f.a);
        this.c();
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (f.this.c == null) {
                    return;
                }
                if (f.this.b()) {
                    f.this.c.setVolume(1.0f);
                }
                else {
                    f.this.c.setVolume(0.0f);
                }
                f.this.a();
            }
        });
    }
    
    private boolean b() {
        return this.c != null && this.c.getVolume() == 0.0f;
    }
    
    private void c() {
        this.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.g));
    }
    
    public final void a() {
        if (this.c == null) {
            return;
        }
        if (this.b()) {
            this.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.h));
            return;
        }
        this.c();
    }
    
    public void a(final a c) {
        this.c = c;
        if (this.c != null) {
            this.c.getEventBus().a(this.d);
        }
    }
    
    public void b(final a a) {
        if (this.c != null) {
            this.c.getEventBus().b(this.d);
        }
        this.c = null;
    }
    
    protected void onDraw(final Canvas canvas) {
        final int n = this.getWidth() / 2;
        final int n2 = this.getHeight() / 2;
        canvas.drawCircle((float)n, (float)n2, (float)Math.min(n, n2), this.b);
        super.onDraw(canvas);
    }
}
