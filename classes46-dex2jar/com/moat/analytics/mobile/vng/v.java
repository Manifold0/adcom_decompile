// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.media.MediaPlayer;
import android.app.Activity;
import android.webkit.WebView;
import android.view.ViewGroup;
import java.util.Map;
import android.view.View;
import android.app.Application;

abstract class v
{
    public static class a extends MoatAnalytics
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
    
    public static class b extends MoatFactory
    {
        @Override
        public <T> T createCustomTracker(final MoatPlugin<T> moatPlugin) {
            return null;
        }
        
        @Override
        public NativeDisplayTracker createNativeDisplayTracker(final View view, final Map<String, String> map) {
            return new c();
        }
        
        @Override
        public NativeVideoTracker createNativeVideoTracker(final String s) {
            return new d();
        }
        
        @Override
        public WebAdTracker createWebAdTracker(final ViewGroup viewGroup) {
            return new e();
        }
        
        @Override
        public WebAdTracker createWebAdTracker(final WebView webView) {
            return new e();
        }
    }
    
    static class c implements NativeDisplayTracker
    {
        @Override
        public void reportUserInteractionEvent(final MoatUserInteractionType moatUserInteractionType) {
        }
        
        @Override
        public void setActivity(final Activity activity) {
        }
        
        @Override
        public void startTracking() {
        }
        
        @Override
        public void stopTracking() {
        }
    }
    
    static class d implements NativeVideoTracker
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
        public boolean trackVideoAd(final Map<String, String> map, final MediaPlayer mediaPlayer, final View view) {
            return false;
        }
    }
    
    static class e implements WebAdTracker
    {
        @Override
        public void setActivity(final Activity activity) {
        }
        
        @Override
        public void startTracking() {
        }
        
        @Override
        public void stopTracking() {
        }
    }
}
