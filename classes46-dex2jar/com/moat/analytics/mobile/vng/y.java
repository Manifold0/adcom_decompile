// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.Locale;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class y extends c<Object> implements ReactiveVideoTracker
{
    private Integer l;
    
    public y(final String s) {
        super(s);
        p.a("[SUCCESS] ", this.a() + " created");
    }
    
    @Override
    String a() {
        return "ReactiveVideoTracker";
    }
    
    protected JSONObject a(final MoatAdEvent moatAdEvent) {
        if (moatAdEvent.d == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.b.equals(MoatAdEvent.a) && !this.a(moatAdEvent.b, this.l)) {
            moatAdEvent.d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }
    
    @Override
    protected Map<String, Object> g() {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final View view = this.k.get();
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
        hashMap.put("duration", this.l);
        hashMap.put("width", n);
        hashMap.put("height", n2);
        return hashMap;
    }
    
    @Override
    public boolean trackVideoAd(final Map<String, String> map, final Integer l, final View view) {
        if (this.e) {
            p.a(3, "ReactiveVideoTracker", this, "trackVideoAd already called");
            p.a("[ERROR] ", this.a() + " trackVideoAd can't be called twice");
            return false;
        }
        if (l < 1000) {
            p.a(3, "ReactiveVideoTracker", this, String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", l));
            return false;
        }
        this.l = l;
        return super.a(map, new Object(), view);
    }
}
