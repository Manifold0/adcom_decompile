// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.content.Context;

@Deprecated
public class AdIconView extends MediaView
{
    public AdIconView(final Context context) {
        super(context);
    }
    
    public AdIconView(final Context context, @Nullable final AttributeSet set) {
        super(context, set);
    }
    
    public AdIconView(final Context context, @Nullable final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    @TargetApi(21)
    public AdIconView(final Context context, @Nullable final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
}
