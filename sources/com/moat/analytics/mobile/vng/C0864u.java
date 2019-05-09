package com.moat.analytics.mobile.vng;

import android.media.MediaPlayer;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.vng.u */
class C0864u extends C0837h<MediaPlayer> implements NativeVideoTracker {
    C0864u(String str) {
        super(str);
        C0858p.m1577a(3, "NativeVideoTracker", (Object) this, "In initialization method.");
        C0858p.m1579a("[SUCCESS] ", mo1662a() + " created");
    }

    /* renamed from: a */
    String mo1662a() {
        return "NativeVideoTracker";
    }

    /* renamed from: a */
    public /* synthetic */ boolean mo1667a(Map map, Object obj, View view) {
        return trackVideoAd(map, (MediaPlayer) obj, view);
    }

    /* renamed from: g */
    protected Map<String, Object> mo1664g() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.j.get();
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* renamed from: j */
    protected Integer mo1668j() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getCurrentPosition());
    }

    /* renamed from: k */
    protected boolean mo1680k() {
        return ((MediaPlayer) this.j.get()).isPlaying();
    }

    /* renamed from: l */
    protected Integer mo1681l() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getDuration());
    }

    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        if (mediaPlayer == null) {
            C0858p.m1577a(3, "NativeVideoTracker", (Object) this, "Null player instance. Not tracking.");
            C0858p.m1579a("[ERROR] ", mo1662a() + " tracking didn't start, Null player instance");
            return false;
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.mo1667a(map, mediaPlayer, view);
        } catch (Exception e) {
            C0858p.m1577a(3, "NativeVideoTracker", (Object) this, "Playback has already completed. Not tracking.");
            C0858p.m1579a("[ERROR] ", mo1662a() + " tracking didn't started for " + m1453e() + ", playback has already completed");
            return false;
        }
    }
}
