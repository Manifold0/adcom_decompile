// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import com.facebook.ads.internal.adapters.b.i;
import com.facebook.ads.internal.w.b.g;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.view.View;
import android.content.ActivityNotFoundException;
import android.util.Log;
import com.facebook.ads.internal.w.b.k;
import android.view.View$OnClickListener;
import android.text.TextUtils;
import com.facebook.ads.internal.a.b;
import java.util.Map;
import android.net.Uri;
import com.facebook.ads.internal.adapters.b.h;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.w.b.w;

public class a extends c
{
    private final String b;
    private final a c;
    private final w d;
    @Nullable
    private final com.facebook.ads.internal.s.c e;
    @Nullable
    private final a.a f;
    
    public a(final Context context, final boolean b, final boolean b2, final String b3, final h h, final com.facebook.ads.internal.s.c e, final a.a f, final a c, final w d) {
        super(context, b, b2, h);
        this.e = e;
        this.f = f;
        this.b = b3;
        this.c = c;
        this.d = d;
    }
    
    @Nullable
    private b a(final Uri uri, final String s, final Map<String, String> map, final boolean b) {
        return com.facebook.ads.internal.a.c.a(this.getContext(), this.e, s, uri, map, b, false);
    }
    
    private void a(final String text, final String s, final String s2, final Map<String, String> map, final boolean b, @Nullable final a a) {
        if (TextUtils.isEmpty((CharSequence)text) || TextUtils.isEmpty((CharSequence)s) || this.e == null) {
            this.setVisibility(8);
            return;
        }
        this.setText(text);
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            private void a() {
                if (!com.facebook.ads.internal.r.a.k(com.facebook.ads.internal.view.component.a.this.getContext()) || com.facebook.ads.internal.view.component.a.this.d.b()) {
                    try {
                        final Uri parse = Uri.parse(s);
                        com.facebook.ads.internal.view.component.a.this.c.a(map);
                        map.put("touch", k.a(com.facebook.ads.internal.view.component.a.this.d.e()));
                        final b a = com.facebook.ads.internal.view.component.a.this.a(parse, s2, map, b);
                        if (a == null || a != null) {
                            goto Label_0174;
                        }
                        a.a();
                        if (com.facebook.ads.internal.view.component.a.this.f != null) {
                            com.facebook.ads.internal.view.component.a.this.f.a(com.facebook.ads.internal.view.component.a.this.b);
                        }
                    }
                    catch (ActivityNotFoundException ex) {
                        Log.e(String.valueOf(a.class), "Error while opening " + s, (Throwable)ex);
                    }
                    catch (Exception ex2) {
                        Log.e(String.valueOf(a.class), "Error executing action", (Throwable)ex2);
                    }
                }
            }
            
            public void onClick(final View view) {
                if (com.facebook.ads.internal.view.component.a.this.d.a(com.facebook.ads.internal.view.component.a.this.getContext())) {
                    if (com.facebook.ads.internal.view.component.a.this.e != null) {
                        com.facebook.ads.internal.view.component.a.this.e.i(s2, map);
                    }
                    return;
                }
                if (com.facebook.ads.internal.r.a.e(com.facebook.ads.internal.view.component.a.this.getContext())) {
                    if (com.facebook.ads.internal.view.component.a.this.e != null) {
                        com.facebook.ads.internal.view.component.a.this.e.j(s2, map);
                    }
                    g.a((DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            map.put("is_two_step", "true");
                            a$1.this.a();
                        }
                    }, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            if (com.facebook.ads.internal.view.component.a.this.e != null) {
                                com.facebook.ads.internal.view.component.a.this.e.k(s2, map);
                            }
                        }
                    }, (Context)com.facebook.ads.internal.w.a.b.a());
                    return;
                }
                this.a();
            }
        });
    }
    
    public void a(final i i, final String s, final Map<String, String> map) {
        this.a(i.b(), i.a(), s, map, false, null);
    }
    
    public void a(final i i, final String s, final Map<String, String> map, @Nullable final a a) {
        this.a(i.b(), i.a(), s, map, false, a);
    }
    
    public void a(final i i, final String s, final Map<String, String> map, final boolean b) {
        this.a(i.b(), i.a(), s, map, b, null);
    }
    
    public void b(final i i, final String s, final Map<String, String> map) {
        final Uri parse = Uri.parse(i.a());
        this.c.a(map);
        map.put("touch", k.a(this.d.e()));
        final b a = this.a(parse, s, map, false);
        if (a != null) {
            a.b();
        }
    }
    
    public interface a
    {
        void a();
    }
}
