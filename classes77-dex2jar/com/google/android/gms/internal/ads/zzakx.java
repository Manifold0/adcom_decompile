// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;
import android.annotation.TargetApi;

@TargetApi(18)
public class zzakx extends zzakw
{
    @Override
    public boolean isAttachedToWindow(final View view) {
        return super.isAttachedToWindow(view) || view.getWindowId() != null;
    }
    
    @Override
    public final int zzrn() {
        return 14;
    }
}
