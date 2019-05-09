// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class bq implements DialogInterface$OnClickListener
{
    final /* synthetic */ bo a;
    
    bq(final bo a) {
        this.a = a;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.a.a.b.skipVideo();
        this.a.a.b.resumeReportRewardTask();
    }
}
