// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

public class ReactiveVideoTrackerPlugin implements ac
{
    private final String a;
    
    public ReactiveVideoTrackerPlugin(final String a) {
        this.a = a;
    }
    
    public ReactiveVideoTracker b() {
        return new bg();
    }
    
    public ReactiveVideoTracker b(final a a, final ap ap) {
        return (ReactiveVideoTracker)ay.a(ap, new bf(this, a, ap), new be());
    }
}
