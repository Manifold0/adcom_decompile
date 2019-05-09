package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class zae extends Drawable implements Callback {
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

    public zae(Drawable drawable, Drawable drawable2) {
        this(null);
        if (drawable == null) {
            drawable = zag.zanu;
        }
        this.zano = drawable;
        drawable.setCallback(this);
        zai zai = this.zann;
        zai.zanw |= drawable.getChangingConfigurations();
        if (drawable2 == null) {
            drawable2 = zag.zanu;
        }
        this.zanp = drawable2;
        drawable2.setCallback(this);
        zai = this.zann;
        zai.zanw |= drawable2.getChangingConfigurations();
    }

    zae(zai zai) {
        this.zanh = 0;
        this.zank = 255;
        this.mAlpha = 0;
        this.zamz = true;
        this.zann = new zai(zai);
    }

    public final void invalidateDrawable(Drawable drawable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final int getChangingConfigurations() {
        return (super.getChangingConfigurations() | this.zann.mChangingConfigurations) | this.zann.zanw;
    }

    public final void setAlpha(int i) {
        if (this.mAlpha == this.zank) {
            this.mAlpha = i;
        }
        this.zank = i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.zano.setColorFilter(colorFilter);
        this.zanp.setColorFilter(colorFilter);
    }

    public final int getIntrinsicWidth() {
        return Math.max(this.zano.getIntrinsicWidth(), this.zanp.getIntrinsicWidth());
    }

    public final int getIntrinsicHeight() {
        return Math.max(this.zano.getIntrinsicHeight(), this.zanp.getIntrinsicHeight());
    }

    protected final void onBoundsChange(Rect rect) {
        this.zano.setBounds(rect);
        this.zanp.setBounds(rect);
    }

    public final ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zann.mChangingConfigurations = getChangingConfigurations();
        return this.zann;
    }

    public final int getOpacity() {
        if (!this.zans) {
            this.zant = Drawable.resolveOpacity(this.zano.getOpacity(), this.zanp.getOpacity());
            this.zans = true;
        }
        return this.zant;
    }

    private final boolean canConstantState() {
        if (!this.zanq) {
            boolean z = (this.zano.getConstantState() == null || this.zanp.getConstantState() == null) ? false : true;
            this.zanr = z;
            this.zanq = true;
        }
        return this.zanr;
    }

    public final Drawable mutate() {
        if (!this.zanm && super.mutate() == this) {
            if (canConstantState()) {
                this.zano.mutate();
                this.zanp.mutate();
                this.zanm = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    public final Drawable zacf() {
        return this.zanp;
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zanj = this.zank;
        this.mAlpha = 0;
        this.zanl = 250;
        this.zanh = 1;
        invalidateSelf();
    }

    public final void draw(Canvas canvas) {
        int i = 1;
        int i2 = 0;
        switch (this.zanh) {
            case 1:
                this.zani = SystemClock.uptimeMillis();
                this.zanh = 2;
                break;
            case 2:
                if (this.zani >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zani)) / ((float) this.zanl);
                    if (uptimeMillis < 1.0f) {
                        i = 0;
                    }
                    if (i != 0) {
                        this.zanh = 0;
                    }
                    this.mAlpha = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) this.zanj)) + 0.0f);
                    break;
                }
                break;
        }
        i2 = i;
        i = this.mAlpha;
        boolean z = this.zamz;
        Drawable drawable = this.zano;
        Drawable drawable2 = this.zanp;
        if (i2 != 0) {
            if (!z || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zank) {
                drawable2.setAlpha(this.zank);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z) {
            drawable.setAlpha(this.zank - i);
        }
        drawable.draw(canvas);
        if (z) {
            drawable.setAlpha(this.zank);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zank);
        }
        invalidateSelf();
    }
}
