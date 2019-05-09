// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import org.json.JSONObject;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

public class bd extends f implements ReactiveVideoTracker
{
    private Integer j;
    
    public bd(final String s, final a a, final ap ap) {
        super(s, a, ap);
    }
    
    @Override
    protected Map a() {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        final View view = (View)this.g.get();
        Integer n;
        Integer n2;
        if (view != null) {
            n = view.getWidth();
            n2 = view.getHeight();
        }
        else {
            n2 = 0;
            n = 0;
        }
        hashMap.put("duration", this.j);
        hashMap.put("width", n);
        hashMap.put("height", n2);
        return hashMap;
    }
    
    @Override
    protected JSONObject a(final MoatAdEvent moatAdEvent) {
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE && !this.a(moatAdEvent.adPlayhead, this.j)) {
            moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }
    
    @Override
    public boolean trackVideoAd(final Map map, final Integer j, final View view) {
        if (j < 1000) {
            this.a(String.format("Invalid duration = %d. Please make sure duration is in milliseconds.", j));
            return false;
        }
        this.j = j;
        return super.a(map, new Object(), view);
    }
}
