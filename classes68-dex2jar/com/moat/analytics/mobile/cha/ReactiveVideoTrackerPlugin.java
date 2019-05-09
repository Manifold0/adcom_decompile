// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import java.util.Map;
import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.cha.base.functional.Optional;

public class ReactiveVideoTrackerPlugin implements l<ReactiveVideoTracker>
{
    private final String \u02ca;
    
    public ReactiveVideoTrackerPlugin(final String \u02ca) {
        this.\u02ca = \u02ca;
    }
    
    @Override
    public ReactiveVideoTracker create() throws o {
        return p.\u02cb((p.c<ReactiveVideoTracker>)new p.c<ReactiveVideoTracker>() {
            @Override
            public final Optional<ReactiveVideoTracker> \u02cb() {
                a.\u02ca("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return (Optional<ReactiveVideoTracker>)Optional.of(new w(ReactiveVideoTrackerPlugin.this.\u02ca));
            }
        }, ReactiveVideoTracker.class);
    }
    
    @Override
    public ReactiveVideoTracker createNoOp() {
        return new d();
    }
    
    static final class d implements ReactiveVideoTracker
    {
        @Override
        public final void changeTargetView(final View view) {
        }
        
        @Override
        public final void dispatchEvent(final MoatAdEvent moatAdEvent) {
        }
        
        @Override
        public final void removeListener() {
        }
        
        @Override
        public final void removeVideoListener() {
        }
        
        @Override
        public final void setActivity(final Activity activity) {
        }
        
        @Override
        public final void setListener(final TrackerListener trackerListener) {
        }
        
        @Override
        public final void setPlayerVolume(final Double n) {
        }
        
        @Override
        public final void setVideoListener(final VideoTrackerListener videoTrackerListener) {
        }
        
        @Override
        public final void stopTracking() {
        }
        
        @Override
        public final boolean trackVideoAd(final Map<String, String> map, final Integer n, final View view) {
            return false;
        }
    }
}
