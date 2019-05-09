package com.kongregate.p000o.p001j;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v4.content.LocalBroadcastManager;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.api.KongregateEvent;
import com.kongregate.android.internal.browser.C0462b;
import com.kongregate.android.internal.sdk.C0507l;
import com.kongregate.android.internal.sdk.NativeAPI;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0571q;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p002g.C0640a;
import com.kongregate.p000o.p002g.C0650f;
import com.kongregate.p000o.p003a.C0594j;
import com.kongregate.p000o.p004e.C0637b;
import com.kongregate.p000o.p005b.C0613e;
import com.kongregate.p000o.p006c.C0626d;
import com.kongregate.p000o.p007d.C0635b;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

@TargetApi(9)
/* renamed from: com.kongregate.o.j.a */
public class C0666a implements BaseColumns {
    /* renamed from: a */
    public static final String f1081a = "active_user";
    /* renamed from: b */
    public static final String f1082b = "active_user";
    /* renamed from: d */
    public static final String f1083d = "user_id";
    /* renamed from: m */
    private static final JSONObject f1084m = C0561i.m749c("{'username':'Guest', 'user_id':0, 'points':0, 'points_away':50, 'avatar_url':'http://cdn4.kongcdn.com/assets/avatars/defaults/headbot.jpg', 'chat_avatar_url':'http://cdn4.kongcdn.com/assets/avatars/defaults_chat_sized/headbot.jpg', 'levelbug_url':'http://cdn1.kongcdn.com/images/presentation/levelbug/levelbug1.gif', 'banned':false, 'premium':false}");
    /* renamed from: n */
    private static final C0666a f1085n = new C0666a();
    /* renamed from: c */
    protected volatile C0594j f1086c;
    /* renamed from: e */
    private final AtomicLong f1087e = new AtomicLong(0);
    /* renamed from: f */
    private final AtomicReference<JSONObject> f1088f = new AtomicReference(new JSONObject());
    /* renamed from: g */
    private final AtomicLong f1089g = new AtomicLong(-1);
    /* renamed from: h */
    private final AtomicReference<String> f1090h = new AtomicReference("");
    /* renamed from: i */
    private final AtomicBoolean f1091i = new AtomicBoolean(true);
    /* renamed from: j */
    private final AtomicReference<Context> f1092j = new AtomicReference(null);
    /* renamed from: k */
    private final AtomicReference<C0463a> f1093k = new AtomicReference(null);
    /* renamed from: l */
    private final AtomicReference<String> f1094l = new AtomicReference("");
    /* renamed from: o */
    private AtomicBoolean f1095o = new AtomicBoolean(false);
    /* renamed from: p */
    private AtomicInteger f1096p = new AtomicInteger(0);

    /* renamed from: com.kongregate.o.j.a$a */
    public interface C0463a {
        /* renamed from: a */
        void mo1130a();

        /* renamed from: a */
        void mo1131a(String str);
    }

    /* renamed from: com.kongregate.o.j.a$3 */
    class C06603 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0666a f1071a;

        C06603(C0666a c0666a) {
            this.f1071a = c0666a;
        }

        public void run() {
            this.f1071a.f1089g.set(this.f1071a.m1177g());
            C0571q x = this.f1071a.m1194x();
            x.m817a("session_expired", Long.toString(this.f1071a.f1089g.get()));
            x.m820c();
            this.f1071a.m1167a(true);
        }
    }

    /* renamed from: com.kongregate.o.j.a$4 */
    class C06614 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0666a f1072a;

        C06614(C0666a c0666a) {
            this.f1072a = c0666a;
        }

        public void run() {
            long j = this.f1072a.f1089g.get();
            if (j == -1) {
                try {
                    j = Long.parseLong(this.f1072a.m1194x().m815a("session_expired"));
                } catch (NumberFormatException e) {
                }
                this.f1072a.f1089g.set(j);
            }
            if (j > 0) {
                C0668c a = C0669b.m1199a(j);
                if (a != null && !a.m1198f()) {
                    Intent intent = new Intent(C0507l.f559i);
                    intent.putExtra("username", a.m1195c());
                    LocalBroadcastManager.getInstance((Context) this.f1072a.f1092j.get()).sendBroadcast(intent);
                }
            }
        }
    }

    /* renamed from: com.kongregate.o.j.a$7 */
    class C06647 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0666a f1078a;

        C06647(C0666a c0666a) {
            this.f1078a = c0666a;
        }

        public void run() {
            C0462b a = C0462b.m266a();
            if (a != null) {
                a.m291e();
            }
        }
    }

    /* renamed from: a */
    public static C0666a m1145a() {
        return f1085n;
    }

    /* renamed from: a */
    public void m1163a(Context context, C0463a c0463a) {
        this.f1093k.set(c0463a);
        this.f1092j.set(context.getApplicationContext());
        this.f1086c = new C0594j(context);
        if (NativeAPI.m351g().m986e()) {
            this.f1095o.set(m1190t().getBoolean(m1160a("guild"), false));
            this.f1096p.set(m1190t().getInt(m1160a("summary"), 0));
            this.f1094l.set(m1190t().getString("character_token", ""));
        }
    }

    /* renamed from: b */
    public boolean m1171b() {
        JSONObject c;
        String a;
        C0571q x = m1194x();
        if (x != null) {
            c = C0561i.m749c(x.m815a("user_data"));
            a = x.m815a("kong_cookies");
        } else {
            C0562j.m761d("Couldn't create/load shared data store");
            a = null;
            c = null;
        }
        C0640a.m1055b().m1071c(a);
        return m1152a(c, null, x);
    }

    /* renamed from: a */
    public void m1165a(JSONObject jSONObject) {
        final JSONObject optJSONObject = jSONObject.optJSONObject("data");
        final String optString = jSONObject.optString("token", null);
        C0626d.m962a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C0666a f1068c;

            public void run() {
                this.f1068c.m1152a(optJSONObject, optString, this.f1068c.m1194x());
                this.f1068c.m1156d(KongregateEvent.LOGIN_COMPLETE);
            }
        });
    }

    /* renamed from: a */
    public void m1167a(final boolean z) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0666a f1070b;

            public void run() {
                C0640a.m1055b().m1075e();
                this.f1070b.m1152a(null, null, this.f1070b.m1194x());
                C0463a c0463a = (C0463a) this.f1070b.f1093k.get();
                if (z && c0463a != null) {
                    c0463a.mo1130a();
                }
            }
        });
    }

    /* renamed from: c */
    public void m1172c() {
        C0562j.m756b("invalidating active user");
        C0626d.m962a(new C06603(this));
    }

    /* renamed from: d */
    public boolean m1174d() {
        return this.f1091i.get();
    }

    /* renamed from: a */
    public void m1166a(JSONObject jSONObject, String str) {
        this.f1091i.set(false);
        m1158y();
        m1169b(jSONObject, str);
    }

    /* renamed from: e */
    public void m1175e() {
        C0626d.m962a(new C06614(this));
    }

    /* renamed from: a */
    private boolean m1152a(JSONObject jSONObject, String str, C0571q c0571q) {
        if (jSONObject == null || !jSONObject.has("id")) {
            jSONObject = f1084m;
        }
        long g = m1177g();
        long optLong = jSONObject.optLong("id", 0);
        if (optLong == 0 || NativeAPI.m351g().m986e()) {
            C0669b c0669b;
            C0669b a = C0669b.m1199a(optLong);
            if (a == null) {
                c0669b = new C0669b();
            } else {
                c0669b = a;
            }
            c0669b.m1202a(jSONObject, str);
            C0562j.m756b("Set active user: " + c0669b.m1195c() + RequestParameters.EQUAL + c0669b.m1197e());
            c0669b.m1203b();
            ContentValues contentValues = new ContentValues();
            SQLiteDatabase a2 = C0637b.m1044a();
            contentValues.put("user_id", Long.valueOf(c0669b.m1197e()));
            C0562j.m753a("active_user updated, rows=" + a2.update("active_user", contentValues, null, null));
            boolean z = g != c0669b.m1197e();
            this.f1091i.compareAndSet(false, z);
            this.f1087e.set(c0669b.m1197e());
            m1150a(jSONObject, c0571q);
            C0613e.m945b(a2, c0669b.m1197e());
            String str2 = (String) this.f1090h.getAndSet(c0669b.m1200a());
            if (z) {
                m1156d(KongregateEvent.USER_CHANGED);
            }
            m1159z();
            String a3 = c0669b.m1200a();
            if (!(str2 == null || str2.equals(a3))) {
                m1156d(KongregateEvent.GAME_AUTH_TOKEN_CHANGED);
            }
            return z;
        }
        C0562j.m759c("Kong user login detected but not supported, logging out");
        m1167a(false);
        return false;
    }

    /* renamed from: y */
    private void m1158y() {
        C0640a.m1055b().m1080i();
        m1147a(m1194x());
        CharSequence a = C0640a.m1055b().m1063a(C0650f.f1035d);
        Editor edit = ((Context) this.f1092j.get()).getSharedPreferences("kongregate_active_user", 0).edit();
        if (StringUtils.m587c(a)) {
            edit.remove("m_pass_user");
        } else {
            edit.putLong("m_pass_user", m1177g());
        }
        edit.apply();
    }

    /* renamed from: z */
    private void m1159z() {
        C0626d.m970c();
        SharedPreferences sharedPreferences = ((Context) this.f1092j.get()).getSharedPreferences("kongregate_active_user", 0);
        long j = sharedPreferences.getLong("m_pass_user", -1);
        long g = m1177g();
        if (j >= 0 && j != g) {
            C0562j.m753a("uid no longer matches m_pass uid. clear m_pass cookie so we get a new one.");
            C0640a.m1055b().m1073d(C0650f.f1035d);
            sharedPreferences.edit().remove("m_pass_user").apply();
        }
    }

    /* renamed from: f */
    public boolean m1176f() {
        return m1177g() == 0;
    }

    /* renamed from: g */
    public long m1177g() {
        return NativeAPI.m351g().m986e() ? this.f1087e.get() : 0;
    }

    /* renamed from: h */
    public String m1178h() {
        return ((JSONObject) this.f1088f.get()).optString("username");
    }

    /* renamed from: i */
    public boolean m1179i() {
        return ((JSONObject) this.f1088f.get()).optBoolean("premium");
    }

    /* renamed from: j */
    public String m1180j() {
        if (((JSONObject) this.f1088f.get()).isNull("powerup_rewards_tier")) {
            return null;
        }
        return ((JSONObject) this.f1088f.get()).optString("powerup_rewards_tier");
    }

    /* renamed from: k */
    public Date m1181k() {
        if (((JSONObject) this.f1088f.get()).isNull("pur_link_date")) {
            return null;
        }
        return C0561i.m737a(((JSONObject) this.f1088f.get()).optString("pur_link_date"));
    }

    /* renamed from: l */
    public Date m1182l() {
        if (((JSONObject) this.f1088f.get()).isNull("join_date")) {
            return null;
        }
        return C0561i.m737a(((JSONObject) this.f1088f.get()).optString("join_date"));
    }

    /* renamed from: m */
    public Double m1183m() {
        if (((JSONObject) this.f1088f.get()).has("spent_on_kreds")) {
            return Double.valueOf(((JSONObject) this.f1088f.get()).optDouble("spent_on_kreds"));
        }
        return null;
    }

    /* renamed from: n */
    public boolean m1184n() {
        return ((JSONObject) this.f1088f.get()).optBoolean("accepted_privacy_policy");
    }

    /* renamed from: o */
    public int m1185o() {
        int optInt = ((JSONObject) this.f1088f.get()).optInt("accepted_privacy_policy_version", 0);
        return optInt > 0 ? optInt : this.f1086c.m908b(m1177g());
    }

    /* renamed from: p */
    public Date m1186p() {
        if (((JSONObject) this.f1088f.get()).isNull("privacy_policy_accepted_at")) {
            return this.f1086c.m909c(m1177g());
        }
        return C0561i.m737a(((JSONObject) this.f1088f.get()).optString("privacy_policy_accepted_at"));
    }

    /* renamed from: q */
    public boolean m1187q() {
        return ((JSONObject) this.f1088f.get()).optBoolean("parent_email_required");
    }

    /* renamed from: r */
    public int m1188r() {
        return this.f1096p.get();
    }

    /* renamed from: s */
    public boolean m1189s() {
        return this.f1095o.get();
    }

    /* renamed from: a */
    public void m1162a(int i, String str, String str2) {
        if (NativeAPI.m351g().m986e()) {
            if ("summary".equals(str)) {
                if (this.f1096p.getAndSet(i) != i) {
                    m1156d(KongregateEvent.NOTIFICATION_COUNT_UPDATED);
                }
            } else if ("chat".equals(str) && "guild".equals(str2)) {
                boolean z = i > 0;
                if (this.f1095o.getAndSet(z) != z) {
                    m1156d(KongregateEvent.NOTIFICATION_COUNT_UPDATED);
                }
            }
            m1190t().edit().putInt(m1160a("summary"), this.f1096p.get()).putBoolean(m1160a("guild"), this.f1095o.get()).apply();
        }
    }

    /* renamed from: a */
    String m1160a(String str) {
        return "notifications_count." + str;
    }

    /* renamed from: t */
    SharedPreferences m1190t() {
        return ((Context) this.f1092j.get()).getSharedPreferences("kongregate_active_user", 0);
    }

    /* renamed from: b */
    public void m1170b(boolean z) {
        this.f1086c.m906a(m1177g(), z);
    }

    /* renamed from: u */
    public boolean m1191u() {
        return this.f1086c.m907a(m1177g());
    }

    /* renamed from: a */
    public void m1161a(int i) {
        this.f1086c.m905a(m1177g(), i);
    }

    /* renamed from: b */
    public void m1169b(final JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            long optLong = NativeAPI.m351g().m986e() ? jSONObject.optLong("id", 0) : 0;
            if (optLong != 0 || m1176f()) {
                m1164a(str, optLong);
                C0626d.m962a(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C0666a f1074b;

                    public void run() {
                        this.f1074b.m1150a(jSONObject, this.f1074b.m1194x());
                    }
                });
                return;
            }
            m1172c();
        }
    }

    /* renamed from: a */
    private void m1150a(JSONObject jSONObject, C0571q c0571q) {
        this.f1088f.set(jSONObject);
        m1147a(c0571q);
        if (jSONObject != null) {
            m1162a(jSONObject.optInt("notifications_count", 0), "summary", null);
        }
    }

    /* renamed from: a */
    public void m1164a(String str, final long j) {
        if (j == 0) {
            str = "Guest_Game_Auth_Token";
        }
        if (str == null) {
            C0562j.m759c("Game Auth Token may not be null");
        } else if (!str.equals(this.f1090h.get())) {
            C0626d.m962a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C0666a f1077c;

                public void run() {
                    C0562j.m756b("Game auth token set to: " + this.f1077c.f1090h + " for " + j);
                    C0669b a = C0669b.m1199a(j);
                    if (a != null) {
                        a.m1201a(str);
                        a.m1203b();
                    }
                    String str = (String) this.f1077c.f1090h.getAndSet(str);
                    if (str != null && !str.equals(str)) {
                        this.f1077c.m1156d(KongregateEvent.GAME_AUTH_TOKEN_CHANGED);
                    }
                }
            });
        }
    }

    /* renamed from: v */
    public String m1192v() {
        return (String) this.f1090h.get();
    }

    /* renamed from: w */
    public String m1193w() {
        return (String) this.f1094l.get();
    }

    /* renamed from: b */
    public void m1168b(String str) {
        Intent a = C0507l.m456a(KongregateEvent.CHARACTER_TOKEN_REQUEST);
        synchronized (this.f1094l) {
            CharSequence charSequence = (String) this.f1094l.get();
            if (StringUtils.m580a((CharSequence) str)) {
                C0562j.m753a("Requesting initial character token");
                LocalBroadcastManager.getInstance((Context) this.f1092j.get()).sendBroadcast(a);
            } else if (StringUtils.m580a(charSequence) || charSequence.equals(str)) {
                C0562j.m753a("Character token is no longer valid, requesting a new one");
                m1173c("");
                LocalBroadcastManager.getInstance((Context) this.f1092j.get()).sendBroadcast(a);
            } else {
                C0562j.m753a("Character token invalidated, but we already have a new one, ignoring.");
            }
        }
    }

    /* renamed from: c */
    public void m1173c(String str) {
        if (str == null) {
            str = "";
        }
        synchronized (this.f1094l) {
            this.f1094l.set(str);
            m1190t().edit().putString("character_token", str).apply();
            C0626d.m962a(new C06647(this));
        }
    }

    /* renamed from: d */
    private void m1156d(String str) {
        if (NativeAPI.m351g().m986e()) {
            C0463a c0463a = (C0463a) this.f1093k.get();
            if (c0463a != null) {
                c0463a.mo1131a(str);
            }
        }
    }

    /* renamed from: a */
    private void m1147a(final C0571q c0571q) {
        if (c0571q != null) {
            C0626d.m962a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0666a f1080b;

                public void run() {
                    c0571q.m817a("user_data", ((JSONObject) this.f1080b.f1088f.get()).toString());
                    c0571q.m817a("kong_cookies", C0640a.m1055b().m1077f());
                    if (!this.f1080b.m1176f()) {
                        c0571q.m819b("session_expired");
                        this.f1080b.f1089g.set(0);
                    }
                    c0571q.m820c();
                }
            });
        }
    }

    /* renamed from: x */
    C0571q m1194x() {
        return C0571q.m795a((Context) this.f1092j.get(), C0635b.m1001a().m1014c() + ".api.");
    }
}
