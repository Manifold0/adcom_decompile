package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zak;

public abstract class zaa {
    final zab zamv;
    private int zamw = 0;
    protected int zamx = 0;
    private boolean zamy = false;
    private boolean zamz = true;
    private boolean zana = false;
    private boolean zanb = true;

    public zaa(Uri uri, int i) {
        this.zamv = new zab(uri);
        this.zamx = i;
    }

    protected abstract void zaa(Drawable drawable, boolean z, boolean z2, boolean z3);

    final void zaa(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    final void zaa(Context context, zak zak) {
        if (this.zanb) {
            zaa(null, false, true, false);
        }
    }

    final void zaa(Context context, zak zak, boolean z) {
        Drawable drawable = null;
        if (this.zamx != 0) {
            drawable = context.getResources().getDrawable(this.zamx);
        }
        zaa(drawable, z, false, false);
    }

    protected final boolean zaa(boolean z, boolean z2) {
        return (!this.zamz || z2 || z) ? false : true;
    }
}
