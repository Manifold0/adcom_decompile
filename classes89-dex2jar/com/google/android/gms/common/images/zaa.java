// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.images;

import com.google.android.gms.internal.base.zak;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import com.google.android.gms.common.internal.Asserts;
import android.graphics.Bitmap;
import android.content.Context;
import android.net.Uri;

public abstract class zaa
{
    final zab zamv;
    private int zamw;
    protected int zamx;
    private boolean zamy;
    private boolean zamz;
    private boolean zana;
    private boolean zanb;
    
    public zaa(final Uri uri, final int zamx) {
        this.zamw = 0;
        this.zamx = 0;
        this.zamy = false;
        this.zamz = true;
        this.zana = false;
        this.zanb = true;
        this.zamv = new zab(uri);
        this.zamx = zamx;
    }
    
    final void zaa(final Context context, final Bitmap bitmap, final boolean b) {
        Asserts.checkNotNull((Object)bitmap);
        this.zaa((Drawable)new BitmapDrawable(context.getResources(), bitmap), b, false, true);
    }
    
    final void zaa(final Context context, final zak zak) {
        if (this.zanb) {
            this.zaa(null, false, true, false);
        }
    }
    
    final void zaa(final Context context, final zak zak, final boolean b) {
        Drawable drawable = null;
        if (this.zamx != 0) {
            drawable = context.getResources().getDrawable(this.zamx);
        }
        this.zaa(drawable, b, false, false);
    }
    
    protected abstract void zaa(final Drawable p0, final boolean p1, final boolean p2, final boolean p3);
    
    protected final boolean zaa(final boolean b, final boolean b2) {
        return this.zamz && !b2 && !b;
    }
}
