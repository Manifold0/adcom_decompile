// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.j;

import java.util.Date;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.kongregate.android.internal.sdk.l;
import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import com.kongregate.android.internal.util.StringUtils;
import android.database.sqlite.SQLiteDatabase;
import com.kongregate.o.b.e;
import android.content.ContentValues;
import com.kongregate.android.internal.sdk.NativeAPI;
import com.kongregate.o.c.d;
import com.kongregate.android.internal.util.q;
import com.kongregate.android.internal.util.i;
import java.util.concurrent.atomic.AtomicInteger;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicLong;
import com.kongregate.o.a.j;
import org.json.JSONObject;
import android.annotation.TargetApi;
import android.provider.BaseColumns;

@TargetApi(9)
public class a implements BaseColumns
{
    public static final String a = "active_user";
    public static final String b = "active_user";
    public static final String d = "user_id";
    private static final JSONObject m;
    private static final a n;
    protected volatile j c;
    private final AtomicLong e;
    private final AtomicReference<JSONObject> f;
    private final AtomicLong g;
    private final AtomicReference<String> h;
    private final AtomicBoolean i;
    private final AtomicReference<Context> j;
    private final AtomicReference<a> k;
    private final AtomicReference<String> l;
    private AtomicBoolean o;
    private AtomicInteger p;
    
    static {
        m = i.c("{'username':'Guest', 'user_id':0, 'points':0, 'points_away':50, 'avatar_url':'http://cdn4.kongcdn.com/assets/avatars/defaults/headbot.jpg', 'chat_avatar_url':'http://cdn4.kongcdn.com/assets/avatars/defaults_chat_sized/headbot.jpg', 'levelbug_url':'http://cdn1.kongcdn.com/images/presentation/levelbug/levelbug1.gif', 'banned':false, 'premium':false}");
        n = new a();
    }
    
    public a() {
        this.e = new AtomicLong(0L);
        this.f = new AtomicReference<JSONObject>(new JSONObject());
        this.g = new AtomicLong(-1L);
        this.h = new AtomicReference<String>("");
        this.i = new AtomicBoolean(true);
        this.j = new AtomicReference<Context>(null);
        this.k = new AtomicReference<a>(null);
        this.l = new AtomicReference<String>("");
        this.o = new AtomicBoolean(false);
        this.p = new AtomicInteger(0);
    }
    
    public static a a() {
        return com.kongregate.o.j.a.n;
    }
    
    private void a(final q q) {
        if (q == null) {
            return;
        }
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                q.a("user_data", com.kongregate.o.j.a.this.f.get().toString());
                q.a("kong_cookies", com.kongregate.o.g.a.b().f());
                if (!com.kongregate.o.j.a.this.f()) {
                    q.b("session_expired");
                    com.kongregate.o.j.a.this.g.set(0L);
                }
                q.c();
            }
        });
    }
    
    private void a(final JSONObject jsonObject, final q q) {
        this.f.set(jsonObject);
        this.a(q);
        if (jsonObject != null) {
            this.a(jsonObject.optInt("notifications_count", 0), "summary", null);
        }
    }
    
    private boolean a(final JSONObject jsonObject, String s, final q q) {
        JSONObject m = null;
        Label_0021: {
            if (jsonObject != null) {
                m = jsonObject;
                if (jsonObject.has("id")) {
                    break Label_0021;
                }
            }
            m = com.kongregate.o.j.a.m;
        }
        final long g = this.g();
        final long optLong = m.optLong("id", 0L);
        if (optLong != 0L && !NativeAPI.g().e()) {
            com.kongregate.android.internal.util.j.c("Kong user login detected but not supported, logging out");
            this.a(false);
            return false;
        }
        b a = com.kongregate.o.j.b.a(optLong);
        if (a == null) {
            a = new b();
        }
        a.a(m, s);
        com.kongregate.android.internal.util.j.b("Set active user: " + a.c() + "=" + a.e());
        a.b();
        final ContentValues contentValues = new ContentValues();
        final SQLiteDatabase a2 = com.kongregate.o.e.b.a();
        contentValues.put("user_id", Long.valueOf(a.e()));
        com.kongregate.android.internal.util.j.a("active_user updated, rows=" + a2.update("active_user", contentValues, (String)null, (String[])null));
        final boolean b = g != a.e();
        this.i.compareAndSet(false, b);
        this.e.set(a.e());
        this.a(m, q);
        com.kongregate.o.b.e.b(a2, a.e());
        s = this.h.getAndSet(a.a());
        if (b) {
            this.d("KONG_API_EVENT_USER_CHANGED");
        }
        this.z();
        final String a3 = a.a();
        if (s != null && !s.equals(a3)) {
            this.d("KONG_API_EVENT_GAME_AUTH_TOKEN_CHANGED");
        }
        return b;
    }
    
    private void d(final String s) {
        if (NativeAPI.g().e()) {
            final a a = this.k.get();
            if (a != null) {
                a.a(s);
            }
        }
    }
    
    private void y() {
        com.kongregate.o.g.a.b().i();
        this.a(this.x());
        final String a = com.kongregate.o.g.a.b().a("m_pass");
        final SharedPreferences$Editor edit = this.j.get().getSharedPreferences("kongregate_active_user", 0).edit();
        if (StringUtils.c((CharSequence)a)) {
            edit.remove("m_pass_user");
        }
        else {
            edit.putLong("m_pass_user", this.g());
        }
        edit.apply();
    }
    
    private void z() {
        com.kongregate.o.c.d.c();
        final SharedPreferences sharedPreferences = this.j.get().getSharedPreferences("kongregate_active_user", 0);
        final long long1 = sharedPreferences.getLong("m_pass_user", -1L);
        final long g = this.g();
        if (long1 >= 0L && long1 != g) {
            com.kongregate.android.internal.util.j.a("uid no longer matches m_pass uid. clear m_pass cookie so we get a new one.");
            com.kongregate.o.g.a.b().d("m_pass");
            sharedPreferences.edit().remove("m_pass_user").apply();
        }
    }
    
    String a(final String s) {
        return "notifications_count." + s;
    }
    
    public void a(final int n) {
        this.c.a(this.g(), n);
    }
    
    public void a(final int n, final String s, final String s2) {
        if (!NativeAPI.g().e()) {
            return;
        }
        if ("summary".equals(s)) {
            if (this.p.getAndSet(n) != n) {
                this.d("KONG_API_EVENT_NOTIFICATION_COUNT_UPDATED");
            }
        }
        else if ("chat".equals(s) && "guild".equals(s2)) {
            final boolean b = n > 0;
            if (this.o.getAndSet(b) != b) {
                this.d("KONG_API_EVENT_NOTIFICATION_COUNT_UPDATED");
            }
        }
        this.t().edit().putInt(this.a("summary"), this.p.get()).putBoolean(this.a("guild"), this.o.get()).apply();
    }
    
    public void a(final Context context, final a a) {
        this.k.set(a);
        this.j.set(context.getApplicationContext());
        this.c = new j(context);
        if (NativeAPI.g().e()) {
            this.o.set(this.t().getBoolean(this.a("guild"), false));
            this.p.set(this.t().getInt(this.a("summary"), 0));
            this.l.set(this.t().getString("character_token", ""));
        }
    }
    
    public void a(String s, final long n) {
        if (n == 0L) {
            s = "Guest_Game_Auth_Token";
        }
        if (s == null) {
            com.kongregate.android.internal.util.j.c("Game Auth Token may not be null");
        }
        else if (!s.equals(this.h.get())) {
            com.kongregate.o.c.d.a(new Runnable() {
                @Override
                public void run() {
                    com.kongregate.android.internal.util.j.b("Game auth token set to: " + com.kongregate.o.j.a.this.h + " for " + n);
                    final b a = com.kongregate.o.j.b.a(n);
                    if (a != null) {
                        a.a(s);
                        a.b();
                    }
                    final String s = com.kongregate.o.j.a.this.h.getAndSet(s);
                    if (s != null && !s.equals(s)) {
                        com.kongregate.o.j.a.this.d("KONG_API_EVENT_GAME_AUTH_TOKEN_CHANGED");
                    }
                }
            });
        }
    }
    
    public void a(final JSONObject jsonObject) {
        com.kongregate.o.c.d.a(new Runnable() {
            final /* synthetic */ JSONObject a = jsonObject.optJSONObject("data");
            final /* synthetic */ String b = jsonObject.optString("token", (String)null);
            
            @Override
            public void run() {
                com.kongregate.o.j.a.this.a(this.a, this.b, com.kongregate.o.j.a.this.x());
                com.kongregate.o.j.a.this.d("KONG_API_EVENT_LOGIN_COMPLETE");
            }
        });
    }
    
    public void a(final JSONObject jsonObject, final String s) {
        this.i.set(false);
        this.y();
        this.b(jsonObject, s);
    }
    
    public void a(final boolean b) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.o.g.a.b().e();
                com.kongregate.o.j.a.this.a(null, null, com.kongregate.o.j.a.this.x());
                final a a = com.kongregate.o.j.a.this.k.get();
                if (b && a != null) {
                    a.a();
                }
            }
        });
    }
    
    public void b(final String s) {
        while (true) {
            final Intent a = com.kongregate.android.internal.sdk.l.a("KONG_API_EVENT_CHARACTER_TOKEN_REQUEST");
            Label_0115: {
                synchronized (this.l) {
                    final String s2 = this.l.get();
                    if (StringUtils.a((CharSequence)s)) {
                        com.kongregate.android.internal.util.j.a("Requesting initial character token");
                        LocalBroadcastManager.getInstance((Context)this.j.get()).sendBroadcast(a);
                    }
                    else {
                        if (!StringUtils.a((CharSequence)s2) && !s2.equals(s)) {
                            break Label_0115;
                        }
                        com.kongregate.android.internal.util.j.a("Character token is no longer valid, requesting a new one");
                        this.c("");
                        LocalBroadcastManager.getInstance((Context)this.j.get()).sendBroadcast(a);
                    }
                    return;
                }
            }
            com.kongregate.android.internal.util.j.a("Character token invalidated, but we already have a new one, ignoring.");
        }
    }
    
    public void b(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            long optLong;
            if (NativeAPI.g().e()) {
                optLong = jsonObject.optLong("id", 0L);
            }
            else {
                optLong = 0L;
            }
            if (optLong != 0L || this.f()) {
                this.a(s, optLong);
                com.kongregate.o.c.d.a(new Runnable() {
                    @Override
                    public void run() {
                        com.kongregate.o.j.a.this.a(jsonObject, com.kongregate.o.j.a.this.x());
                    }
                });
                return;
            }
            this.c();
        }
    }
    
    public void b(final boolean b) {
        this.c.a(this.g(), b);
    }
    
    public boolean b() {
        final q x = this.x();
        JSONObject c;
        String a;
        if (x != null) {
            c = com.kongregate.android.internal.util.i.c(x.a("user_data"));
            a = x.a("kong_cookies");
        }
        else {
            com.kongregate.android.internal.util.j.d("Couldn't create/load shared data store");
            a = null;
            c = null;
        }
        com.kongregate.o.g.a.b().c(a);
        return this.a(c, null, x);
    }
    
    public void c() {
        com.kongregate.android.internal.util.j.b("invalidating active user");
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.o.j.a.this.g.set(com.kongregate.o.j.a.this.g());
                final q x = com.kongregate.o.j.a.this.x();
                x.a("session_expired", Long.toString(com.kongregate.o.j.a.this.g.get()));
                x.c();
                com.kongregate.o.j.a.this.a(true);
            }
        });
    }
    
    public void c(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        synchronized (this.l) {
            this.l.set(s2);
            this.t().edit().putString("character_token", s2).apply();
            com.kongregate.o.c.d.a(new Runnable() {
                @Override
                public void run() {
                    final com.kongregate.android.internal.browser.b a = com.kongregate.android.internal.browser.b.a();
                    if (a != null) {
                        a.e();
                    }
                }
            });
        }
    }
    
    public boolean d() {
        return this.i.get();
    }
    
    public void e() {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                long value;
                long long1 = value = com.kongregate.o.j.a.this.g.get();
                Label_0056: {
                    if (long1 != -1L) {
                        break Label_0056;
                    }
                    final q x = com.kongregate.o.j.a.this.x();
                    while (true) {
                        try {
                            long1 = Long.parseLong(x.a("session_expired"));
                            com.kongregate.o.j.a.this.g.set(long1);
                            value = long1;
                            if (value > 0L) {
                                final b a = com.kongregate.o.j.b.a(value);
                                if (a != null && !a.f()) {
                                    final Intent intent = new Intent("com.kongregate.android.internal.sdk.ExpiredSesssion");
                                    intent.putExtra("username", a.c());
                                    LocalBroadcastManager.getInstance((Context)com.kongregate.o.j.a.this.j.get()).sendBroadcast(intent);
                                }
                            }
                        }
                        catch (NumberFormatException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        });
    }
    
    public boolean f() {
        return this.g() == 0L;
    }
    
    public long g() {
        if (NativeAPI.g().e()) {
            return this.e.get();
        }
        return 0L;
    }
    
    public String h() {
        return this.f.get().optString("username");
    }
    
    public boolean i() {
        return this.f.get().optBoolean("premium");
    }
    
    public String j() {
        if (!this.f.get().isNull("powerup_rewards_tier")) {
            return this.f.get().optString("powerup_rewards_tier");
        }
        return null;
    }
    
    public Date k() {
        if (!this.f.get().isNull("pur_link_date")) {
            return com.kongregate.android.internal.util.i.a(this.f.get().optString("pur_link_date"));
        }
        return null;
    }
    
    public Date l() {
        if (!this.f.get().isNull("join_date")) {
            return com.kongregate.android.internal.util.i.a(this.f.get().optString("join_date"));
        }
        return null;
    }
    
    public Double m() {
        if (this.f.get().has("spent_on_kreds")) {
            return this.f.get().optDouble("spent_on_kreds");
        }
        return null;
    }
    
    public boolean n() {
        return this.f.get().optBoolean("accepted_privacy_policy");
    }
    
    public int o() {
        final int optInt = this.f.get().optInt("accepted_privacy_policy_version", 0);
        if (optInt > 0) {
            return optInt;
        }
        return this.c.b(this.g());
    }
    
    public Date p() {
        if (!this.f.get().isNull("privacy_policy_accepted_at")) {
            return com.kongregate.android.internal.util.i.a(this.f.get().optString("privacy_policy_accepted_at"));
        }
        return this.c.c(this.g());
    }
    
    public boolean q() {
        return this.f.get().optBoolean("parent_email_required");
    }
    
    public int r() {
        return this.p.get();
    }
    
    public boolean s() {
        return this.o.get();
    }
    
    SharedPreferences t() {
        return this.j.get().getSharedPreferences("kongregate_active_user", 0);
    }
    
    public boolean u() {
        return this.c.a(this.g());
    }
    
    public String v() {
        return this.h.get();
    }
    
    public String w() {
        return this.l.get();
    }
    
    q x() {
        return q.a(this.j.get(), com.kongregate.o.d.b.a().c() + ".api.");
    }
    
    public interface a
    {
        void a();
        
        void a(final String p0);
    }
}
