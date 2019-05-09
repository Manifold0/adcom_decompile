package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.cha.NativeDisplayTracker.MoatUserInteractionType;
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
            return new C1479e();
        }

        public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
            return new C1479e();
        }

        public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
            return new C1478c();
        }

        public NativeVideoTracker createNativeVideoTracker(@NonNull String str) {
            return new C1477b();
        }

        public <T> T createCustomTracker(C1483l<T> c1483l) {
            return c1483l.createNoOp();
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.NoOp$b */
    static class C1477b implements NativeVideoTracker {
        C1477b() {
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

    /* renamed from: com.moat.analytics.mobile.cha.NoOp$c */
    static class C1478c implements NativeDisplayTracker {
        C1478c() {
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

    /* renamed from: com.moat.analytics.mobile.cha.NoOp$e */
    static class C1479e implements WebAdTracker {
        C1479e() {
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

    NoOp() {
    }
}
