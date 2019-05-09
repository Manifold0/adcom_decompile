// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(19)
public class zzakz extends zzakx
{
    @Override
    public final boolean isAttachedToWindow(final View view) {
        return view.isAttachedToWindow();
    }
    
    @Override
    public final ViewGroup$LayoutParams zzro() {
        return new ViewGroup$LayoutParams(-1, -1);
    }
}
