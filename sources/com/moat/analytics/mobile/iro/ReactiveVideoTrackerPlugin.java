package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.iro.C0794s.C0747a;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements C0750m<ReactiveVideoTracker> {
    /* renamed from: ˋ */
    private final String f1119;

    /* renamed from: com.moat.analytics.mobile.iro.ReactiveVideoTrackerPlugin$5 */
    class C07485 implements C0747a<ReactiveVideoTracker> {
        /* renamed from: ˎ */
        private /* synthetic */ ReactiveVideoTrackerPlugin f1118;

        C07485(ReactiveVideoTrackerPlugin reactiveVideoTrackerPlugin) {
            this.f1118 = reactiveVideoTrackerPlugin;
        }

        /* renamed from: ˏ */
        public final Optional<ReactiveVideoTracker> mo1625() {
            C0756b.m1232("[INFO] ", "Attempting to create ReactiveVideoTracker");
            return Optional.of(new C0805w(this.f1118.f1119));
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.ReactiveVideoTrackerPlugin$b */
    static class C0749b implements ReactiveVideoTracker {
        C0749b() {
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

        public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }

        public final void setPlayerVolume(Double d) {
        }

        public final void changeTargetView(View view) {
        }

        public final void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public final void stopTracking() {
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.f1119 = str;
    }

    public ReactiveVideoTracker create() throws C0785o {
        return (ReactiveVideoTracker) C0794s.m1382(new C07485(this), ReactiveVideoTracker.class);
    }

    public ReactiveVideoTracker createNoOp() {
        return new C0749b();
    }
}
