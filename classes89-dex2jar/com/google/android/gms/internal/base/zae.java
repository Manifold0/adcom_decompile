// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.base;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$ConstantState;
import android.os.SystemClock;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

public final class zae extends Drawable implements Drawable$Callback
{
    private int mAlpha;
    private int mFrom;
    private boolean zamz;
    private int zanh;
    private long zani;
    private int zanj;
    private int zank;
    private int zanl;
    private boolean zanm;
    private zai zann;
    private Drawable zano;
    private Drawable zanp;
    private boolean zanq;
    private boolean zanr;
    private boolean zans;
    private int zant;
    
    public zae(Drawable zacg, final Drawable drawable) {
        this(null);
        Drawable zacg2 = zacg;
        if (zacg == null) {
            zacg2 = zag.zanu;
        }
        (this.zano = zacg2).setCallback((Drawable$Callback)this);
        final zai zann = this.zann;
        zann.zanw |= zacg2.getChangingConfigurations();
        if ((zacg = drawable) == null) {
            zacg = zag.zanu;
        }
        (this.zanp = zacg).setCallback((Drawable$Callback)this);
        final zai zann2 = this.zann;
        zann2.zanw |= zacg.getChangingConfigurations();
    }
    
    zae(final zai zai) {
        this.zanh = 0;
        this.zank = 255;
        this.mAlpha = 0;
        this.zamz = true;
        this.zann = new zai(zai);
    }
    
    private final boolean canConstantState() {
        if (!this.zanq) {
            this.zanr = (this.zano.getConstantState() != null && this.zanp.getConstantState() != null);
            this.zanq = true;
        }
        return this.zanr;
    }
    
    public final void draw(final Canvas canvas) {
        final int n = 1;
        int n2 = 1;
        final int n3 = 0;
        switch (this.zanh) {
            case 1: {
                this.zani = SystemClock.uptimeMillis();
                this.zanh = 2;
                n2 = n3;
                break;
            }
            case 2: {
                if (this.zani >= 0L) {
                    final float n4 = (SystemClock.uptimeMillis() - this.zani) / (float)this.zanl;
                    if (n4 >= 1.0f) {
                        n2 = n;
                    }
                    else {
                        n2 = 0;
                    }
                    if (n2 != 0) {
                        this.zanh = 0;
                    }
                    this.mAlpha = (int)(Math.min(n4, 1.0f) * this.zanj + 0.0f);
                    break;
                }
                break;
            }
        }
        final int mAlpha = this.mAlpha;
        final boolean zamz = this.zamz;
        final Drawable zano = this.zano;
        final Drawable zanp = this.zanp;
        if (n2 != 0) {
            if (!zamz || mAlpha == 0) {
                zano.draw(canvas);
            }
            if (mAlpha == this.zank) {
                zanp.setAlpha(this.zank);
                zanp.draw(canvas);
            }
            return;
        }
        if (zamz) {
            zano.setAlpha(this.zank - mAlpha);
        }
        zano.draw(canvas);
        if (zamz) {
            zano.setAlpha(this.zank);
        }
        if (mAlpha > 0) {
            zanp.setAlpha(mAlpha);
            zanp.draw(canvas);
            zanp.setAlpha(this.zank);
        }
        this.invalidateSelf();
    }
    
    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zann.mChangingConfigurations | this.zann.zanw;
    }
    
    public final Drawable$ConstantState getConstantState() {
        if (this.canConstantState()) {
            this.zann.mChangingConfigurations = this.getChangingConfigurations();
            return this.zann;
        }
        return null;
    }
    
    public final int getIntrinsicHeight() {
        return Math.max(this.zano.getIntrinsicHeight(), this.zanp.getIntrinsicHeight());
    }
    
    public final int getIntrinsicWidth() {
        return Math.max(this.zano.getIntrinsicWidth(), this.zanp.getIntrinsicWidth());
    }
    
    public final int getOpacity() {
        if (!this.zans) {
            this.zant = Drawable.resolveOpacity(this.zano.getOpacity(), this.zanp.getOpacity());
            this.zans = true;
        }
        return this.zant;
    }
    
    public final void invalidateDrawable(final Drawable drawable) {
        final Drawable$Callback callback = this.getCallback();
        if (callback != null) {
            callback.invalidateDrawable((Drawable)this);
        }
    }
    
    public final Drawable mutate() {
        if (!this.zanm && super.mutate() == this) {
            if (!this.canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zano.mutate();
            this.zanp.mutate();
            this.zanm = true;
        }
        return this;
    }
    
    protected final void onBoundsChange(final Rect rect) {
        this.zano.setBounds(rect);
        this.zanp.setBounds(rect);
    }
    
    public final void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        final Drawable$Callback callback = this.getCallback();
        if (callback != null) {
            callback.scheduleDrawable((Drawable)this, runnable, n);
        }
    }
    
    public final void setAlpha(final int n) {
        if (this.mAlpha == this.zank) {
            this.mAlpha = n;
        }
        this.zank = n;
        this.invalidateSelf();
    }
    
    public final void setColorFilter(final ColorFilter colorFilter) {
        this.zano.setColorFilter(colorFilter);
        this.zanp.setColorFilter(colorFilter);
    }
    
    public final void startTransition(final int n) {
        this.mFrom = 0;
        this.zanj = this.zank;
        this.mAlpha = 0;
        this.zanl = 250;
        this.zanh = 1;
        this.invalidateSelf();
    }
    
    public final void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        final Drawable$Callback callback = this.getCallback();
        if (callback != null) {
            callback.unscheduleDrawable((Drawable)this, runnable);
        }
    }
    
    public final Drawable zacf() {
        return this.zanp;
    }
}
