package com.moat.analytics.mobile.cha;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.cha.s */
final class C1527s extends C1502i implements NativeVideoTracker {
    /* renamed from: ॱˊ */
    private WeakReference<MediaPlayer> f3587;

    C1527s(String str) {
        super(str);
        C1487a.m3715(3, "NativeVideoTracker", this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            String str2 = "PartnerCode is " + (str == null ? "null" : "empty");
            String str3 = "NativeDisplayTracker creation problem, " + str2;
            C1487a.m3715(3, "NativeVideoTracker", this, str3);
            C1487a.m3712("[ERROR] ", str3);
            this.ॱ = new C1518o(str2);
        }
        C1487a.m3712("[SUCCESS] ", "NativeVideoTracker created");
    }

    /* renamed from: ͺ */
    final boolean mo4375() {
        return (this.f3587 == null || this.f3587.get() == null) ? false : true;
    }

    /* renamed from: ˋ */
    final String mo4374() {
        return "NativeVideoTracker";
    }

    public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            m3727();
            m3729();
            if (mediaPlayer == null) {
                throw new C1518o("Null player instance");
            }
            mediaPlayer.getCurrentPosition();
            this.f3587 = new WeakReference(mediaPlayer);
            return super.mo4373(map, view);
        } catch (Exception e) {
            throw new C1518o("Playback has already completed");
        } catch (Exception e2) {
            C1518o.m3840(e2);
            String ˎ = C1518o.m3839("trackVideoAd", e2);
            if (this.ˊ != null) {
                this.ˊ.onTrackingFailedToStart(ˎ);
            }
            C1487a.m3715(3, "NativeVideoTracker", this, ˎ);
            C1487a.m3712("[ERROR] ", "NativeVideoTracker " + ˎ);
            return false;
        }
    }

    /* renamed from: ॱˋ */
    final Integer mo4376() {
        return Integer.valueOf(((MediaPlayer) this.f3587.get()).getCurrentPosition());
    }

    /* renamed from: ॱˎ */
    final boolean mo4377() {
        return ((MediaPlayer) this.f3587.get()).isPlaying();
    }

    /* renamed from: ॱᐝ */
    final Integer mo4378() {
        return Integer.valueOf(((MediaPlayer) this.f3587.get()).getDuration());
    }

    /* renamed from: ᐝ */
    final Map<String, Object> mo4379() throws C1518o {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f3587.get();
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    /* renamed from: ˋ */
    final void mo4368(List<String> list) throws C1518o {
        Object obj;
        if (this.f3587 == null || this.f3587.get() == null) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            list.add("Player is null");
        }
        super.mo4368(list);
    }
}
