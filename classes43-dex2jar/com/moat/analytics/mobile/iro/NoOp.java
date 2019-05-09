// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import android.media.MediaPlayer;
import android.app.Activity;
import android.webkit.WebView;
import android.view.ViewGroup;
import java.util.Map;
import android.support.annotation.NonNull;
import android.view.View;
import android.app.Application;

abstract class NoOp
{
    public static class MoatAnalytics extends com.moat.analytics.mobile.iro.MoatAnalytics
    {
        @Override
        public void prepareNativeDisplayTracking(final String s) {
        }
        
        @Override
        public void start(final Application application) {
        }
        
        @Override
        public void start(final MoatOptions moatOptions, final Application application) {
        }
    }
    
    public static class MoatFactory extends com.moat.analytics.mobile.iro.MoatFactory
    {
        @Override
        public <T> T createCustomTracker(final m<T> m) {
            return m.createNoOp();
        }
        
        @Override
        public NativeDisplayTracker createNativeDisplayTracker(@NonNull final View view, @NonNull final Map<String, String> map) {
            return new a();
        }
        
        @Override
        public NativeVideoTracker createNativeVideoTracker(@NonNull final String s) {
            return new c();
        }
        
        @Override
        public WebAdTracker createWebAdTracker(@NonNull final ViewGroup viewGroup) {
            return new b();
        }
        
        @Override
        public WebAdTracker createWebAdTracker(@NonNull final WebView webView) {
            return new b();
        }
    }
    
    static final class a implements NativeDisplayTracker
    {
        @Override
        public final void removeListener() {
        }
        
        @Override
        public final void reportUserInteractionEvent(final MoatUserInteractionType moatUserInteractionType) {
        }
        
        @Override
        public final void setActivity(final Activity activity) {
        }
        
        @Override
        public final void setListener(final TrackerListener trackerListener) {
        }
        
        @Override
        public final void startTracking() {
        }
        
        @Override
        public final void stopTracking() {
        }
    }
    
    static final class b implements WebAdTracker
    {
        @Override
        public final void removeListener() {
        }
        
        @Override
        public final void setActivity(final Activity activity) {
        }
        
        @Override
        public final void setListener(final TrackerListener trackerListener) {
        }
        
        @Override
        public final void startTracking() {
        }
        
        @Override
        public final void stopTracking() {
        }
    }
    
    static final class c implements NativeVideoTracker
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
        public final boolean trackVideoAd(final Map<String, String> map, final MediaPlayer mediaPlayer, final View view) {
            return false;
        }
    }
}
