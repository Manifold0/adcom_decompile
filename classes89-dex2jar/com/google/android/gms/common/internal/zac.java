// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.app.Activity;

final class zac extends DialogRedirect
{
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaoh;
    
    zac(final Intent zaoh, final Activity val$activity, final int val$requestCode) {
        this.zaoh = zaoh;
        this.val$activity = val$activity;
        this.val$requestCode = val$requestCode;
    }
    
    public final void redirect() {
        if (this.zaoh != null) {
            this.val$activity.startActivityForResult(this.zaoh, this.val$requestCode);
        }
    }
}
