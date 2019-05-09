// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.Context;
import android.app.Dialog;

public final class e extends Dialog
{
    private boolean a;
    
    public e(final Context context) {
        super(context, 16973835);
        this.a = false;
        this.requestWindowFeature(1);
        this.getWindow().setBackgroundDrawableResource(17170445);
    }
    
    public final void cancel() {
        this.a = true;
        super.cancel();
    }
    
    public final void show() {
        this.a = false;
        super.show();
    }
}
