// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import java.lang.ref.WeakReference;
import android.app.Activity;
import android.provider.Settings$System;
import java.io.Serializable;
import android.content.Intent;
import com.facebook.ads.AudienceNetworkActivity;
import android.net.Uri$Builder;
import android.net.Uri;
import java.util.Locale;
import com.facebook.ads.AdSettings;
import android.support.annotation.Nullable;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.AdError;
import android.text.TextUtils;
import org.json.JSONObject;
import java.util.Map;
import com.facebook.ads.internal.adapters.b.f;
import com.facebook.ads.internal.adapters.b.n;
import java.util.Iterator;
import com.facebook.ads.internal.adapters.b.d;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.e.c;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.adapters.b.q;
import java.util.UUID;
import com.facebook.ads.internal.adapters.b.a;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public class k extends s
{
    private final String c;
    private final AtomicBoolean d;
    private Context e;
    private t f;
    private String g;
    private String h;
    private long i;
    private com.facebook.ads.internal.adapters.b.a j;
    private u k;
    private com.facebook.ads.internal.settings.a.a l;
    private String m;
    private a n;
    
    public k() {
        this.c = UUID.randomUUID().toString();
        this.d = new AtomicBoolean();
    }
    
    private void a(final Context context, final boolean b, final q q) {
        this.n = new a(this, this.f, this.d);
        com.facebook.ads.internal.adapters.c.a.a(context, o.a(q), b, (com.facebook.ads.internal.adapters.c.a.b)this.n);
    }
    
    private void a(final com.facebook.ads.internal.h.b b, final q q) {
        b.a(q.f().b(), com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
        b.a(q.j().a());
        final String g = q.j().g();
        final Context e = this.e;
        final d j = q.j();
        int n;
        if (com.facebook.ads.internal.r.a.S(e)) {
            n = Math.min(x.a.heightPixels, j.i());
        }
        else {
            n = j.i();
        }
        final Context e2 = this.e;
        final d i = q.j();
        int n2;
        if (com.facebook.ads.internal.r.a.S(e2)) {
            n2 = Math.min(x.a.widthPixels, i.h());
        }
        else {
            n2 = i.h();
        }
        b.a(g, n, n2);
        final Iterator<String> iterator = q.k().d().iterator();
        while (iterator.hasNext()) {
            b.a(iterator.next(), -1, -1);
        }
    }
    
    private static boolean b(final q q, final boolean b) {
        final n j = q.j().j();
        return j != null && (!b || !j.i());
    }
    
    @Override
    public int a() {
        int n;
        if (this.j == null) {
            n = -1;
        }
        else {
            if (this.l != com.facebook.ads.internal.settings.a.a.j) {
                return ((q)this.j).j().d();
            }
            int n2 = 0;
            final Iterator<q> iterator = ((f)this.j).j().iterator();
            while (true) {
                n = n2;
                if (!iterator.hasNext()) {
                    break;
                }
                final int d = iterator.next().j().d();
                if (n2 >= d) {
                    continue;
                }
                n2 = d;
            }
        }
        return n;
    }
    
    public void a(final Context e, final t f, final Map<String, Object> map, final boolean b, String s) {
        this.e = e;
        this.f = f;
        this.d.set(false);
        this.h = map.get("placementId");
        this.i = (long)map.get("requestTime");
        final int k = ((com.facebook.ads.internal.m.d)map.get("definition")).k();
        this.m = s;
        if (this.h != null) {
            s = this.h.split("_")[0];
        }
        else {
            s = "";
        }
        this.g = s;
        final boolean equals = "choose_your_own_ad_rewarded_video".equals(map.get("data_model_type"));
        this.j = com.facebook.ads.internal.adapters.b.a.a(equals, (JSONObject)map.get("data"));
        com.facebook.ads.internal.settings.a.a l;
        if (equals) {
            l = com.facebook.ads.internal.settings.a.a.j;
        }
        else if (b((q)this.j, true)) {
            l = com.facebook.ads.internal.settings.a.a.i;
        }
        else {
            l = com.facebook.ads.internal.settings.a.a.h;
        }
        this.l = l;
        this.j.b(this.m);
        this.j.a(k);
        if (this.l == com.facebook.ads.internal.settings.a.a.h && TextUtils.isEmpty((CharSequence)((q)this.j).j().a())) {
            this.f.a(this, AdError.internalError(2003));
            return;
        }
        this.k = new u(this.c, this, f);
        LocalBroadcastManager.getInstance(this.e).registerReceiver((BroadcastReceiver)this.k, this.k.a());
        if (this.l == com.facebook.ads.internal.settings.a.a.h) {
            final com.facebook.ads.internal.h.b b2 = new com.facebook.ads.internal.h.b(e);
            final q q = (q)this.j;
            this.a(b2, q);
            b2.a(new b(this, this.f, b2, this.d, b) {
                @Override
                void a(final boolean b, @Nullable final k k, @Nullable final t t) {
                    if (k != null && t != null) {
                        this.g.set(true);
                        String s;
                        if (b) {
                            s = this.f.c(q.j().a());
                        }
                        else {
                            s = q.j().a();
                        }
                        q.c(s);
                        if (!b((q)k.this.j, false)) {
                            t.a(k);
                            return;
                        }
                        k.this.a(k.this.e, b, q);
                    }
                }
            });
            return;
        }
        if (this.l == com.facebook.ads.internal.settings.a.a.i) {
            this.a(e, b, (q)this.j);
            return;
        }
        final com.facebook.ads.internal.h.b b3 = new com.facebook.ads.internal.h.b(e);
        final f f2 = (f)this.j;
        final Iterator<q> iterator = f2.j().iterator();
        while (iterator.hasNext()) {
            this.a(b3, iterator.next());
        }
        b3.a(new b(this, this.f, b3, this.d, b) {
            @Override
            void a(final boolean b, @Nullable final k k, @Nullable final t t) {
                if (k != null && t != null) {
                    this.g.set(true);
                    for (final q q : f2.j()) {
                        String s;
                        if (b) {
                            s = this.f.c(q.j().a());
                        }
                        else {
                            s = q.j().a();
                        }
                        q.c(s);
                        if (b(q, false)) {
                            continue;
                        }
                    }
                    t.a(k);
                }
            }
        });
    }
    
    @Override
    public boolean b() {
        if (!this.d.get()) {
            return false;
        }
        String string;
        if (this.a != null) {
            final String urlPrefix = AdSettings.getUrlPrefix();
            String format;
            if (urlPrefix == null || urlPrefix.isEmpty()) {
                format = "https://www.facebook.com/audience_network/server_side_reward";
            }
            else {
                format = String.format(Locale.US, "https://www.%s.facebook.com/audience_network/server_side_reward", urlPrefix);
            }
            final Uri parse = Uri.parse(format);
            final Uri$Builder uri$Builder = new Uri$Builder();
            uri$Builder.scheme(parse.getScheme());
            uri$Builder.authority(parse.getAuthority());
            uri$Builder.path(parse.getPath());
            uri$Builder.query(parse.getQuery());
            uri$Builder.fragment(parse.getFragment());
            uri$Builder.appendQueryParameter("puid", this.a.getUserID());
            uri$Builder.appendQueryParameter("pc", this.a.getCurrency());
            uri$Builder.appendQueryParameter("ptid", this.c);
            uri$Builder.appendQueryParameter("appid", this.g);
            string = uri$Builder.build().toString();
        }
        else {
            string = null;
        }
        this.j.a(string);
        final Intent intent = new Intent(this.e, AudienceNetworkActivity.getAdActivity());
        intent.putExtra("viewType", (Serializable)this.l);
        intent.putExtra("rewardedVideoAdDataBundle", (Serializable)this.j);
        intent.putExtra("uniqueId", this.c);
        intent.putExtra("rewardServerURL", string);
        intent.putExtra("placementId", this.h);
        intent.putExtra("requestTime", this.i);
        if (this.b != -1 && Settings$System.getInt(this.e.getContentResolver(), "accelerometer_rotation", 0) != 1) {
            intent.putExtra("predefinedOrientationKey", this.b);
        }
        else if (!com.facebook.ads.internal.r.a.y(this.e)) {
            intent.putExtra("predefinedOrientationKey", 6);
        }
        if (!(this.e instanceof Activity)) {
            intent.setFlags(intent.getFlags() | 0x10000000);
        }
        this.e.startActivity(intent);
        return true;
    }
    
    @Override
    public String getClientToken() {
        return this.j.a();
    }
    
    @Override
    public void onDestroy() {
        if (this.k == null) {
            return;
        }
        try {
            LocalBroadcastManager.getInstance(this.e).unregisterReceiver((BroadcastReceiver)this.k);
        }
        catch (Exception ex) {}
    }
    
    private static class a implements com.facebook.ads.internal.adapters.c.a.b
    {
        final WeakReference<k> a;
        final WeakReference<t> b;
        final AtomicBoolean c;
        
        a(final k k, final t t, final AtomicBoolean c) {
            this.a = new WeakReference<k>(k);
            this.b = new WeakReference<t>(t);
            this.c = c;
        }
        
        @Override
        public void a() {
            this.c.set(true);
            if (this.b.get() != null && this.a.get() != null) {
                this.b.get().a(this.a.get());
            }
        }
        
        @Override
        public void a(final AdError adError) {
            if (this.b.get() != null && this.a.get() != null) {
                this.b.get().a(this.a.get(), adError);
            }
        }
    }
    
    private abstract static class b implements com.facebook.ads.internal.h.a
    {
        final WeakReference<k> d;
        final WeakReference<t> e;
        final com.facebook.ads.internal.h.b f;
        final AtomicBoolean g;
        final boolean h;
        
        private b(final k k, final t t, final com.facebook.ads.internal.h.b f, final AtomicBoolean g, final boolean h) {
            this.d = new WeakReference<k>(k);
            this.e = new WeakReference<t>(t);
            this.f = f;
            this.g = g;
            this.h = h;
        }
        
        @Override
        public void a() {
            this.a(true, this.d.get(), this.e.get());
        }
        
        abstract void a(final boolean p0, @Nullable final k p1, @Nullable final t p2);
        
        @Override
        public void b() {
            if (this.e.get() == null || this.d.get() == null) {
                return;
            }
            if (this.h) {
                this.e.get().a(this.d.get(), AdError.CACHE_ERROR);
                return;
            }
            this.a(false, this.d.get(), this.e.get());
        }
    }
}
