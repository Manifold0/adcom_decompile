// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.TimerTask;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class bi implements DialogInterface$OnClickListener
{
    final /* synthetic */ bh a;
    
    bi(final bh a) {
        this.a = a;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        dialogInterface.dismiss();
        this.a.b.f.schedule(new bj(this), 200L);
    }
}
