// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;

public final class AdView extends BaseAdView
{
    public AdView(final Context context) {
        super(context, 0);
        Preconditions.checkNotNull((Object)context, (Object)"Context cannot be null");
    }
    
    public AdView(final Context context, final AttributeSet set) {
        super(context, set, 0);
    }
    
    public AdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, 0);
    }
    
    public final VideoController getVideoController() {
        if (this.zzut != null) {
            return this.zzut.getVideoController();
        }
        return null;
    }
}
