// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class bp implements DialogInterface$OnClickListener
{
    final /* synthetic */ bo a;
    
    bp(final bo a) {
        this.a = a;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.a.a.b.continueVideo();
        this.a.a.b.resumeReportRewardTask();
    }
}
