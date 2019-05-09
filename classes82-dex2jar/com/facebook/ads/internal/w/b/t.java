// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.os.Handler;
import android.view.WindowManager$LayoutParams;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.View;
import android.view.View$OnSystemUiVisibilityChangeListener;

public class t implements View$OnSystemUiVisibilityChangeListener
{
    private final View a;
    private int b;
    @Nullable
    private Window c;
    private a d;
    private final Runnable e;
    
    public t(final View a) {
        this.d = t.a.a;
        this.e = new Runnable() {
            @Override
            public void run() {
                t.this.a(false);
            }
        };
        (this.a = a).setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)this);
    }
    
    private void a(final int n, final boolean b) {
        if (this.c == null) {
            return;
        }
        final WindowManager$LayoutParams attributes = this.c.getAttributes();
        if (b) {
            attributes.flags |= n;
        }
        else {
            attributes.flags &= ~n;
        }
        this.c.setAttributes(attributes);
    }
    
    private void a(final boolean b) {
        if (t.a.a.equals(this.d)) {
            return;
        }
        int systemUiVisibility = 3840;
        if (!b) {
            systemUiVisibility = 3847;
        }
        final Handler handler = this.a.getHandler();
        if (handler != null && b) {
            handler.removeCallbacks(this.e);
            handler.postDelayed(this.e, 2000L);
        }
        this.a.setSystemUiVisibility(systemUiVisibility);
    }
    
    public void a() {
        this.c = null;
    }
    
    public void a(final Window c) {
        this.c = c;
    }
    
    public void a(final a d) {
        this.d = d;
        switch (t$2.a[this.d.ordinal()]) {
            default: {
                this.a(67108864, false);
                this.a(134217728, false);
                this.a.setSystemUiVisibility(0);
            }
            case 1: {
                this.a(67108864, true);
                this.a(134217728, true);
                this.a(false);
            }
        }
    }
    
    public void onSystemUiVisibilityChange(final int b) {
        final int b2 = this.b;
        this.b = b;
        if (((b2 ^ b) & 0x2) != 0x0 && (b & 0x2) == 0x0) {
            this.a(true);
        }
    }
    
    public enum a
    {
        a, 
        b;
    }
}
