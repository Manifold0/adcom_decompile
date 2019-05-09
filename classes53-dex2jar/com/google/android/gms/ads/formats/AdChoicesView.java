// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RelativeLayout;

public class AdChoicesView extends RelativeLayout
{
    public AdChoicesView(final Context context) {
        super(context);
    }
    
    public AdChoicesView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public AdChoicesView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    @TargetApi(21)
    public AdChoicesView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
}
