package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zae;
import com.google.android.gms.internal.base.zaj;
import java.lang.ref.WeakReference;

public final class zac extends zaa {
    private WeakReference<ImageView> zanc;

    public zac(ImageView imageView, Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull(imageView);
        this.zanc = new WeakReference(imageView);
    }

    public zac(ImageView imageView, int i) {
        super(null, i);
        Asserts.checkNotNull(imageView);
        this.zanc = new WeakReference(imageView);
    }

    public final int hashCode() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zac)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImageView imageView = (ImageView) this.zanc.get();
        ImageView imageView2 = (ImageView) ((zac) obj).zanc.get();
        return (imageView2 == null || imageView == null || !Objects.equal(imageView2, imageView)) ? false : true;
    }

    protected final void zaa(Drawable drawable, boolean z, boolean z2, boolean z3) {
        Uri uri = null;
        ImageView imageView = (ImageView) this.zanc.get();
        if (imageView != null) {
            Drawable drawable2;
            Object obj = (z2 || z3) ? null : 1;
            if (obj != null && (imageView instanceof zaj)) {
                int zach = zaj.zach();
                if (this.zamx != 0 && zach == this.zamx) {
                    return;
                }
            }
            boolean zaa = zaa(z, z2);
            if (zaa) {
                drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof zae) {
                    drawable2 = ((zae) drawable2).zacf();
                }
                drawable2 = new zae(drawable2, drawable);
            } else {
                drawable2 = drawable;
            }
            imageView.setImageDrawable(drawable2);
            if (imageView instanceof zaj) {
                if (z3) {
                    uri = this.zamv.uri;
                }
                zaj.zaa(uri);
                zaj.zai(obj != null ? this.zamx : 0);
            }
            if (zaa) {
                ((zae) drawable2).startTransition(250);
            }
        }
    }
}
