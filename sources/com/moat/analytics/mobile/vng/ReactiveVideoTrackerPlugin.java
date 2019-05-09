package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.vng.C0882x.C0815a;
import com.moat.analytics.mobile.vng.p013a.p015b.C0820a;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker> {
    /* renamed from: a */
    private final String f1336a;

    /* renamed from: com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin$1 */
    class C08161 implements C0815a<ReactiveVideoTracker> {
        /* renamed from: a */
        final /* synthetic */ ReactiveVideoTrackerPlugin f1335a;

        C08161(ReactiveVideoTrackerPlugin reactiveVideoTrackerPlugin) {
            this.f1335a = reactiveVideoTrackerPlugin;
        }

        /* renamed from: a */
        public C0820a<ReactiveVideoTracker> mo1653a() {
            C0858p.m1579a("[INFO] ", "Attempting to create ReactiveVideoTracker");
            return C0820a.m1433a(new C0883y(this.f1335a.f1336a));
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin$a */
    static class C0817a implements ReactiveVideoTracker {
        C0817a() {
        }

        public void changeTargetView(View view) {
        }

        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public void setActivity(Activity activity) {
        }

        public void setPlayerVolume(Double d) {
        }

        public void stopTracking() {
        }

        public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.f1336a = str;
    }

    /* renamed from: a */
    public /* synthetic */ Object mo1660a() {
        return m1428c();
    }

    /* renamed from: b */
    public /* synthetic */ Object mo1661b() {
        return m1429d();
    }

    /* renamed from: c */
    public ReactiveVideoTracker m1428c() {
        return (ReactiveVideoTracker) C0882x.m1628a(new C08161(this), ReactiveVideoTracker.class);
    }

    /* renamed from: d */
    public ReactiveVideoTracker m1429d() {
        return new C0817a();
    }
}
