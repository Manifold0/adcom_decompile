package com.moat.analytics.mobile.iro;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.iro.q */
final class C0790q extends C0768g implements NativeVideoTracker {
    /* renamed from: ͺ */
    private WeakReference<MediaPlayer> f1264;

    C0790q(String str) {
        super(str);
        C0756b.m1234(3, "NativeVideoTracker", this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            String str2 = "PartnerCode is " + (str == null ? "null" : "empty");
            String str3 = "NativeDisplayTracker creation problem, " + str2;
            C0756b.m1234(3, "NativeVideoTracker", this, str3);
            C0756b.m1232("[ERROR] ", str3);
            this.ˋ = new C0785o(str2);
        }
        C0756b.m1232("[SUCCESS] ", "NativeVideoTracker created");
    }

    /* renamed from: ॱˊ */
    final boolean mo1650() {
        return (this.f1264 == null || this.f1264.get() == null) ? false : true;
    }

    /* renamed from: ˊ */
    final String mo1647() {
        return "NativeVideoTracker";
    }

    public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            m1243();
            m1246();
            if (mediaPlayer == null) {
                throw new C0785o("Null player instance");
            }
            mediaPlayer.getCurrentPosition();
            this.f1264 = new WeakReference(mediaPlayer);
            return super.mo1644(map, view);
        } catch (Exception e) {
            throw new C0785o("Playback has already completed");
        } catch (Exception e2) {
            C0785o.m1351(e2);
            String ॱ = C0785o.m1350("trackVideoAd", e2);
            if (this.ˊ != null) {
                this.ˊ.onTrackingFailedToStart(ॱ);
            }
            C0756b.m1234(3, "NativeVideoTracker", this, ॱ);
            C0756b.m1232("[ERROR] ", "NativeVideoTracker " + ॱ);
            return false;
        }
    }

    /* renamed from: ˋॱ */
    final Integer mo1649() {
        return Integer.valueOf(((MediaPlayer) this.f1264.get()).getCurrentPosition());
    }

    /* renamed from: ॱˎ */
    final boolean mo1651() {
        return ((MediaPlayer) this.f1264.get()).isPlaying();
    }

    /* renamed from: ʻॱ */
    final Integer mo1646() {
        return Integer.valueOf(((MediaPlayer) this.f1264.get()).getDuration());
    }

    /* renamed from: ˊॱ */
    final Map<String, Object> mo1648() throws C0785o {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f1264.get();
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* renamed from: ˏ */
    final void mo1641(List<String> list) throws C0785o {
        Object obj;
        if (this.f1264 == null || this.f1264.get() == null) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            list.add("Player is null");
        }
        super.mo1641(list);
    }
}
