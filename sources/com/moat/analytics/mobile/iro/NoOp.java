package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.iro.NativeDisplayTracker.MoatUserInteractionType;
import java.util.Map;

abstract class NoOp {

    public static class MoatAnalytics extends MoatAnalytics {
        public void start(MoatOptions moatOptions, Application application) {
        }

        public void start(Application application) {
        }

        public void prepareNativeDisplayTracking(String str) {
        }
    }

    public static class MoatFactory extends MoatFactory {
        public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
            return new C0745b();
        }

        public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
            return new C0745b();
        }

        public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
            return new C0744a();
        }

        public NativeVideoTracker createNativeVideoTracker(@NonNull String str) {
            return new C0746c();
        }

        public <T> T createCustomTracker(C0750m<T> c0750m) {
            return c0750m.createNoOp();
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.NoOp$a */
    static class C0744a implements NativeDisplayTracker {
        C0744a() {
        }

        public final void startTracking() {
        }

        public final void setListener(TrackerListener trackerListener) {
        }

        public final void removeListener() {
        }

        public final void setActivity(Activity activity) {
        }

        public final void stopTracking() {
        }

        public final void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType) {
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.NoOp$b */
    static class C0745b implements WebAdTracker {
        C0745b() {
        }

        public final void startTracking() {
        }

        public final void setListener(TrackerListener trackerListener) {
        }

        public final void removeListener() {
        }

        public final void setActivity(Activity activity) {
        }

        public final void stopTracking() {
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.NoOp$c */
    static class C0746c implements NativeVideoTracker {
        C0746c() {
        }

        public final void setActivity(Activity activity) {
        }

        public final void setListener(TrackerListener trackerListener) {
        }

        public final void removeListener() {
        }

        public final void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        public final void removeVideoListener() {
        }

        public final void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }

        public final void setPlayerVolume(Double d) {
        }

        public final void changeTargetView(View view) {
        }

        public final void stopTracking() {
        }
    }

    NoOp() {
    }
}
