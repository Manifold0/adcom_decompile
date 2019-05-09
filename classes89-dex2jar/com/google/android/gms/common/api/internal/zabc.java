// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zabc extends zabr
{
    private WeakReference<zaaw> zahm;
    
    zabc(final zaaw zaaw) {
        this.zahm = new WeakReference<zaaw>(zaaw);
    }
    
    @Override
    public final void zas() {
        final zaaw zaaw = this.zahm.get();
        if (zaaw == null) {
            return;
        }
        zaaw.resume();
    }
}
