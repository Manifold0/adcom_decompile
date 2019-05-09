// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import android.util.Log;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.e.a;

public class m extends b
{
    private static final String c;
    private final a d;
    private final c e;
    private l f;
    private boolean g;
    
    static {
        c = m.class.getSimpleName();
    }
    
    public m(final Context context, final c e, final a d, final com.facebook.ads.internal.x.a a, final com.facebook.ads.internal.adapters.c c) {
        super(context, c, a);
        this.e = e;
        this.d = d;
    }
    
    public void a(final l f) {
        this.f = f;
    }
    
    @Override
    protected void a(final Map<String, String> map) {
        if (this.f != null && !TextUtils.isEmpty((CharSequence)this.f.getClientToken())) {
            this.e.a(this.f.getClientToken(), map);
        }
    }
    
    public void b() {
        synchronized (this) {
            if (this.g && this.f != null) {
                this.g = true;
                if (this.d != null && !TextUtils.isEmpty((CharSequence)this.f.d())) {
                    this.d.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (m.this.d.c()) {
                                Log.w(m.c, "Webview already destroyed, cannot activate");
                                return;
                            }
                            m.this.d.loadUrl("javascript:" + m.this.f.d());
                        }
                    });
                }
            }
        }
    }
}
