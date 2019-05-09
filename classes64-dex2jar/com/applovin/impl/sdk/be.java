// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.widget.Toast;
import com.applovin.sdk.AppLovinSdkUtils;
import android.content.Context;

public class be
{
    private final AppLovinSdkImpl a;
    private final String b;
    private final Context c;
    
    public be(final AppLovinSdkImpl a, final Context c, final String b) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    void a() {
        AppLovinSdkUtils.runOnUiThread(new bf(this));
    }
    
    void a(final String s, final Throwable t) {
        this.a.getLogger().userError("IncentivizedConfirmationManager", "Unable to show incentivized ad reward dialog. Have you defined com.applovin.adview.AppLovinConfirmationActivity in your manifest?", t);
        Toast.makeText(this.c, (CharSequence)s, 1).show();
    }
    
    String b() {
        if (this.b.equals("accepted")) {
            return this.a.get(ea.aa);
        }
        if (this.b.equals("quota_exceeded")) {
            return this.a.get(ea.ab);
        }
        if (this.b.equals("rejected")) {
            return this.a.get(ea.ac);
        }
        return this.a.get(ea.ad);
    }
}
