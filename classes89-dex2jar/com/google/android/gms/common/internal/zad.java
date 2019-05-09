// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.support.v4.app.Fragment;

final class zad extends DialogRedirect
{
    private final /* synthetic */ Fragment val$fragment;
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaoh;
    
    zad(final Intent zaoh, final Fragment val$fragment, final int val$requestCode) {
        this.zaoh = zaoh;
        this.val$fragment = val$fragment;
        this.val$requestCode = val$requestCode;
    }
    
    public final void redirect() {
        if (this.zaoh != null) {
            this.val$fragment.startActivityForResult(this.zaoh, this.val$requestCode);
        }
    }
}
