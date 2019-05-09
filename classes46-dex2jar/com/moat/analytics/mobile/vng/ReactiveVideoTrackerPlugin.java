// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.Map;
import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.vng.a.b.a;

public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker>
{
    private final String a;
    
    public ReactiveVideoTrackerPlugin(final String a) {
        this.a = a;
    }
    
    public ReactiveVideoTracker c() {
        return x.a((x.a<ReactiveVideoTracker>)new x.a<ReactiveVideoTracker>() {
            @Override
            public a<ReactiveVideoTracker> a() {
                p.a("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return (a<ReactiveVideoTracker>)com.moat.analytics.mobile.vng.a.b.a.a(new y(ReactiveVideoTrackerPlugin.this.a));
            }
        }, ReactiveVideoTracker.class);
    }
    
    public ReactiveVideoTracker d() {
        return new a();
    }
    
    static class a implements ReactiveVideoTracker
    {
        @Override
        public void changeTargetView(final View view) {
        }
        
        @Override
        public void dispatchEvent(final MoatAdEvent moatAdEvent) {
        }
        
        @Override
        public void setActivity(final Activity activity) {
        }
        
        @Override
        public void setPlayerVolume(final Double n) {
        }
        
        @Override
        public void stopTracking() {
        }
        
        @Override
        public boolean trackVideoAd(final Map<String, String> map, final Integer n, final View view) {
            return false;
        }
    }
}
