// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Asserts;
import android.net.Uri;
import java.lang.ref.WeakReference;

public final class zad extends zaa
{
    private WeakReference<ImageManager.OnImageLoadedListener> zand;
    
    public zad(final ImageManager.OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull((Object)onImageLoadedListener);
        this.zand = new WeakReference<ImageManager.OnImageLoadedListener>(onImageLoadedListener);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof zad)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final zad zad = (zad)o;
        final ImageManager.OnImageLoadedListener onImageLoadedListener = this.zand.get();
        final ImageManager.OnImageLoadedListener onImageLoadedListener2 = zad.zand.get();
        return onImageLoadedListener2 != null && onImageLoadedListener != null && Objects.equal((Object)onImageLoadedListener2, (Object)onImageLoadedListener) && Objects.equal((Object)zad.zamv, (Object)this.zamv);
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zamv });
    }
    
    @Override
    protected final void zaa(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
        if (!b2) {
            final ImageManager.OnImageLoadedListener onImageLoadedListener = this.zand.get();
            if (onImageLoadedListener != null) {
                onImageLoadedListener.onImageLoaded(this.zamv.uri, drawable, b3);
            }
        }
    }
}
