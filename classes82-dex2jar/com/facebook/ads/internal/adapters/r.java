// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.w.b.k;
import android.text.TextUtils;
import java.util.Map;
import com.facebook.ads.internal.x.a;
import android.content.Context;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.s.c;

public class r extends b
{
    private final c c;
    private final w d;
    private q e;
    
    public r(final Context context, final c c, final a a, final w d, final com.facebook.ads.internal.adapters.c c2) {
        super(context, c2, a);
        this.c = c;
        this.d = d;
    }
    
    public void a(final q e) {
        this.e = e;
    }
    
    @Override
    protected void a(final Map<String, String> map) {
        if (this.e != null && !TextUtils.isEmpty((CharSequence)this.e.a())) {
            map.put("touch", k.a(this.d.e()));
            this.c.a(this.e.a(), map);
        }
    }
}
