// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import java.util.Map;
import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.iro.base.functional.Optional;

public class ReactiveVideoTrackerPlugin implements m<ReactiveVideoTracker>
{
    private final String \u02cb;
    
    public ReactiveVideoTrackerPlugin(final String \u02cb) {
        this.\u02cb = \u02cb;
    }
    
    @Override
    public ReactiveVideoTracker create() throws o {
        return s.\u02ca((s.a<ReactiveVideoTracker>)new s.a<ReactiveVideoTracker>() {
            @Override
            public final Optional<ReactiveVideoTracker> \u02cf() {
                com.moat.analytics.mobile.iro.b.\u02ce("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return (Optional<ReactiveVideoTracker>)Optional.of(new w(ReactiveVideoTrackerPlugin.this.\u02cb));
            }
        }, ReactiveVideoTracker.class);
    }
    
    @Override
    public ReactiveVideoTracker createNoOp() {
        return new b();
    }
    
    static final class b implements ReactiveVideoTracker
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
