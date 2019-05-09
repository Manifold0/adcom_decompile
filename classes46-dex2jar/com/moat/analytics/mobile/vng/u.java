// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.HashMap;
import android.view.View;
import java.util.Map;
import android.media.MediaPlayer;

class u extends h<MediaPlayer> implements NativeVideoTracker
{
    u(final String s) {
        super(s);
        com.moat.analytics.mobile.vng.p.a(3, "NativeVideoTracker", this, "In initialization method.");
        com.moat.analytics.mobile.vng.p.a("[SUCCESS] ", this.a() + " created");
    }
    
    @Override
    String a() {
        return "NativeVideoTracker";
    }
    
    @Override
    protected Map<String, Object> g() {
        final MediaPlayer mediaPlayer = this.j.get();
        final HashMap<String, Integer> hashMap = (HashMap<String, Integer>)new HashMap<String, Object>();
        hashMap.put("width", mediaPlayer.getVideoWidth());
        hashMap.put("height", mediaPlayer.getVideoHeight());
        hashMap.put("duration", mediaPlayer.getDuration());
        return (Map<String, Object>)hashMap;
    }
    
    @Override
    protected Integer j() {
        return this.j.get().getCurrentPosition();
    }
    
    @Override
    protected boolean k() {
        return this.j.get().isPlaying();
    }
    
    @Override
    protected Integer l() {
        return this.j.get().getDuration();
    }
    
    @Override
    public boolean trackVideoAd(final Map<String, String> map, final MediaPlayer mediaPlayer, final View view) {
        if (mediaPlayer == null) {
            com.moat.analytics.mobile.vng.p.a(3, "NativeVideoTracker", this, "Null player instance. Not tracking.");
            com.moat.analytics.mobile.vng.p.a("[ERROR] ", this.a() + " tracking didn't start, Null player instance");
            return false;
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.a(map, mediaPlayer, view);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.p.a(3, "NativeVideoTracker", this, "Playback has already completed. Not tracking.");
            com.moat.analytics.mobile.vng.p.a("[ERROR] ", this.a() + " tracking didn't started for " + this.e() + ", playback has already completed");
            return false;
        }
    }
}
