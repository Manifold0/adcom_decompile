// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;

class fv implements af<JSONObject>
{
    final /* synthetic */ fu a;
    
    fv(final fu a) {
        this.a = a;
    }
    
    @Override
    public void a(final int n) {
        this.a.e.e("TaskReportReward", "Failed to report reward for ad: " + this.a.a.getAdIdNumber() + " - error code: " + n);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final int n) {
        this.a.e.d("TaskReportReward", "Reported reward successfully for ad: " + this.a.a.getAdIdNumber());
    }
}
