package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.cha.C1521p.C1480c;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements C1483l<ReactiveVideoTracker> {
    /* renamed from: ˊ */
    private final String f3425;

    /* renamed from: com.moat.analytics.mobile.cha.ReactiveVideoTrackerPlugin$1 */
    class C14811 implements C1480c<ReactiveVideoTracker> {
        /* renamed from: ˊ */
        private /* synthetic */ ReactiveVideoTrackerPlugin f3424;

        C14811(ReactiveVideoTrackerPlugin reactiveVideoTrackerPlugin) {
            this.f3424 = reactiveVideoTrackerPlugin;
        }

        /* renamed from: ˋ */
        public final Optional<ReactiveVideoTracker> mo4353() {
            C1487a.m3712("[INFO] ", "Attempting to create ReactiveVideoTracker");
            return Optional.of(new C1543w(this.f3424.f3425));
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.ReactiveVideoTrackerPlugin$d */
    static class C1482d implements ReactiveVideoTracker {
        C1482d() {
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
        this.f3425 = str;
    }

    public ReactiveVideoTracker create() throws C1518o {
        return (ReactiveVideoTracker) C1521p.m3846(new C14811(this), ReactiveVideoTracker.class);
    }

    public ReactiveVideoTracker createNoOp() {
        return new C1482d();
    }
}
