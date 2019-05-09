// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.images;

import com.google.android.gms.internal.base.zae;
import com.google.android.gms.internal.base.zaj;
import android.graphics.drawable.Drawable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Asserts;
import android.net.Uri;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public final class zac extends zaa
{
    private WeakReference<ImageView> zanc;
    
    public zac(final ImageView imageView, final int n) {
        super(null, n);
        Asserts.checkNotNull((Object)imageView);
        this.zanc = new WeakReference<ImageView>(imageView);
    }
    
    public zac(final ImageView imageView, final Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull((Object)imageView);
        this.zanc = new WeakReference<ImageView>(imageView);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof zac)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final zac zac = (zac)o;
        final ImageView imageView = this.zanc.get();
        final ImageView imageView2 = zac.zanc.get();
        return imageView2 != null && imageView != null && Objects.equal((Object)imageView2, (Object)imageView);
    }
    
    @Override
    public final int hashCode() {
        return 0;
    }
    
    @Override
    protected final void zaa(Drawable imageDrawable, final boolean b, final boolean b2, final boolean b3) {
        final Uri uri = null;
        final ImageView imageView = this.zanc.get();
        if (imageView != null) {
            boolean b4;
            if (!b2 && !b3) {
                b4 = true;
            }
            else {
                b4 = false;
            }
            if (b4 && imageView instanceof zaj) {
                final int zach = zaj.zach();
                if (this.zamx != 0 && zach == this.zamx) {
                    return;
                }
            }
            final boolean zaa = this.zaa(b, b2);
            if (zaa) {
                final Drawable drawable = imageView.getDrawable();
                Drawable zacf;
                if (drawable != null) {
                    zacf = drawable;
                    if (drawable instanceof zae) {
                        zacf = ((zae)drawable).zacf();
                    }
                }
                else {
                    zacf = null;
                }
                imageDrawable = new zae(zacf, imageDrawable);
            }
            imageView.setImageDrawable(imageDrawable);
            if (imageView instanceof zaj) {
                Uri uri2 = uri;
                if (b3) {
                    uri2 = this.zamv.uri;
                }
                zaj.zaa(uri2);
                int zamx;
                if (b4) {
                    zamx = this.zamx;
                }
                else {
                    zamx = 0;
                }
                zaj.zai(zamx);
            }
            if (zaa) {
                ((zae)imageDrawable).startTransition(250);
            }
        }
    }
}
