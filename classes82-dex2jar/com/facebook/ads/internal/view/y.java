// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.View$MeasureSpec;
import android.content.Context;
import com.facebook.ads.internal.view.i.a;

public class y extends a
{
    public y(final Context context) {
        super(context);
    }
    
    protected void onMeasure(int n, final int n2) {
        int n3;
        if (View$MeasureSpec.getMode(n) == 1073741824) {
            n3 = n;
        }
        else {
            n3 = n2;
            if (View$MeasureSpec.getMode(n2) == 1073741824) {
                n = n2;
                n3 = n2;
            }
        }
        super.onMeasure(n, n3);
    }
}
