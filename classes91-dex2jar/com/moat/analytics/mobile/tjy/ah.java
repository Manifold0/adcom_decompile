// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.view.View;
import java.util.HashMap;
import android.media.MediaPlayer;
import java.util.Map;

class ah extends i implements NativeVideoTracker
{
    public ah(final String s, final a a, final ap ap) {
        super(s, a, ap);
    }
    
    @Override
    protected Map a() {
        final MediaPlayer mediaPlayer = (MediaPlayer)this.f.get();
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("width", mediaPlayer.getVideoWidth());
        hashMap.put("height", mediaPlayer.getVideoHeight());
        hashMap.put("duration", mediaPlayer.getDuration());
        return hashMap;
    }
    
    @Override
    public void changeTargetView(final View view) {
        super.changeTargetView(view);
    }
    
    @Override
    protected Integer f() {
        return ((MediaPlayer)this.f.get()).getCurrentPosition();
    }
    
    @Override
    protected boolean g() {
        return ((MediaPlayer)this.f.get()).isPlaying();
    }
    
    @Override
    protected Integer h() {
        return ((MediaPlayer)this.f.get()).getDuration();
    }
    
    @Override
    public boolean trackVideoAd(final Map map, final MediaPlayer mediaPlayer, final View view) {
        if (mediaPlayer == null) {
            this.a("Null player instance. Not tracking.");
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.a(map, mediaPlayer, view);
        }
        catch (IllegalStateException ex) {
            this.a("Playback has already completed. Not tracking.");
            return false;
        }
    }
}
