// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.HashMap;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.Map;

class as implements er
{
    final /* synthetic */ du a;
    final /* synthetic */ boolean b;
    final /* synthetic */ EventServiceImpl c;
    
    as(final EventServiceImpl c, final du a, final boolean b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void a(final aj aj) {
        try {
            final HashMap a = this.c.a(this.a, aj);
            final String string = this.c.a(this.c.a(), a).toString();
            final String string2 = this.c.a(this.c.b(), a).toString();
            if (this.b) {
                this.c.a.getPersistentPostbackManager().a(string, this.a.b(), true, string2);
                return;
            }
            this.c.a.getPostbackService().dispatchPostbackAsync(string, this.a.b(), string2, null);
        }
        catch (Exception ex) {
            this.c.a.getLogger().e("EventServiceImpl", "Unable to track event due to failure to convert event parameters into JSONObject for event: " + this.a, ex);
        }
    }
}
