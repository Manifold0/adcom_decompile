package com.moat.analytics.mobile.tjy;

import android.media.MediaPlayer;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

class ah extends C2744i implements NativeVideoTracker {
    public ah(String str, C2742a c2742a, ap apVar) {
        super(str, c2742a, apVar);
    }

    /* renamed from: a */
    protected Map mo6086a() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f.get();
        Map hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* renamed from: a */
    public /* synthetic */ boolean mo6087a(Map map, Object obj, View view) {
        return trackVideoAd(map, (MediaPlayer) obj, view);
    }

    public void changeTargetView(View view) {
        super.changeTargetView(view);
    }

    /* renamed from: f */
    protected Integer mo6089f() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getCurrentPosition());
    }

    /* renamed from: g */
    protected boolean mo6090g() {
        return ((MediaPlayer) this.f.get()).isPlaying();
    }

    /* renamed from: h */
    protected Integer mo6091h() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getDuration());
    }

    public boolean trackVideoAd(Map map, MediaPlayer mediaPlayer, View view) {
        if (mediaPlayer == null) {
            m6819a("Null player instance. Not tracking.");
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.mo6087a(map, mediaPlayer, view);
        } catch (IllegalStateException e) {
            m6819a("Playback has already completed. Not tracking.");
            return false;
        }
    }
}
