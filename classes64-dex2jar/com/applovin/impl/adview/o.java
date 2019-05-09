// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class o implements View$OnTouchListener
{
    final /* synthetic */ n a;
    
    o(final n a) {
        this.a = a;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        return false;
    }
}
