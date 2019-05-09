// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.b;

public enum aa
{
    a("com.facebook.ads.rewarded_video.completed"), 
    b("com.facebook.ads.rewarded_video.completed.without.reward"), 
    c("com.facebook.ads.rewarded_video.end_activity"), 
    d("com.facebook.ads.rewarded_video.error"), 
    e("com.facebook.ads.rewarded_video.ad_click"), 
    f("com.facebook.ads.rewarded_video.ad_impression"), 
    g("com.facebook.ads.rewarded_video.closed"), 
    h("com.facebook.ads.rewarded_video.server_reward_success"), 
    i("com.facebook.ads.rewarded_video.server_reward_failed"), 
    j("com.facebook.ads.rewarded_video.activity_destroyed"), 
    k("com.facebook.ads.rewarded_video.choose_your_own_ad");
    
    private String l;
    
    private aa(final String l) {
        this.l = l;
    }
    
    public String a() {
        return this.l;
    }
    
    public String a(final String s) {
        return this.l + ":" + s;
    }
}
