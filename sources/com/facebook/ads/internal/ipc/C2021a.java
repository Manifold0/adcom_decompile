package com.facebook.ads.internal.ipc;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p034c.C1972c;
import com.facebook.ads.internal.p034c.C1977d;
import com.facebook.ads.internal.p034c.C1980e;
import com.facebook.ads.internal.p034c.C1982g;
import com.facebook.ads.internal.p034c.C1986j;
import com.facebook.ads.internal.p036e.C1992a;
import com.facebook.ads.internal.p036e.C1992a.C1991a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.Serializable;
import java.util.EnumSet;

/* renamed from: com.facebook.ads.internal.ipc.a */
public class C2021a {
    /* renamed from: a */
    public static final String f4480a = C2021a.class.getSimpleName();
    /* renamed from: b */
    private final Context f4481b;
    /* renamed from: c */
    private final C1992a f4482c = C1992a.m4771a();

    C2021a(Context context) {
        this.f4481b = context;
    }

    /* renamed from: a */
    public boolean m4874a(Message message) {
        String string = message.getData().getString("STR_AD_ID_KEY");
        String string2;
        String string3;
        C1991a e;
        C1972c c1977d;
        C1972c a;
        switch (message.what) {
            case PointerIconCompat.TYPE_ALIAS /*1010*/:
                string2 = message.getData().getString("STR_PLACEMENT_KEY");
                String string4 = message.getData().getString("STR_BID_PAYLOAD_KEY");
                EnumSet enumSet = (EnumSet) message.getData().getSerializable("SRL_INT_CACHE_FLAGS_KEY");
                string3 = message.getData().getString("STR_EXTRA_HINTS_KEY");
                AdInternalSettings.f4776a = message.getData().getBundle("BUNDLE_SETTINGS_KEY");
                C1982g c1982g = new C1982g(this.f4481b, null, string2);
                c1982g.f4347d = string3;
                c1982g.f4349f = string4;
                c1982g.f4348e = enumSet;
                e = C1992a.m4771a().m4781e(string);
                if (e != null) {
                    if (e.f4398c == null) {
                        c1977d = new C1977d(c1982g, this.f4482c, string);
                        c1977d.m4692a(c1982g.f4348e, c1982g.f4349f);
                        e.f4398c = c1977d;
                    } else if (e.f4398c instanceof C1977d) {
                        ((C1977d) e.f4398c).m4692a(c1982g.f4348e, c1982g.f4349f);
                    }
                    this.f4482c.m4774a((int) PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, string);
                } else {
                    C2625a.m6741b(this.f4481b, "api", C2626b.f6548m, new Exception("Missing ad: " + string));
                }
                return true;
            case PointerIconCompat.TYPE_COPY /*1011*/:
                a = C1992a.m4771a().m4773a(string);
                if (a == null || !(a instanceof C1977d)) {
                    C2625a.m6741b(this.f4481b, "api", C2626b.f6548m, new Exception("Missing bundle for message: " + message));
                } else {
                    ((C1977d) a).m4696e();
                    this.f4482c.m4774a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, string);
                }
                return true;
            case PointerIconCompat.TYPE_NO_DROP /*1012*/:
                C1992a.m4771a().m4778b(string);
                this.f4482c.m4774a((int) PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, string);
                return true;
            case 2000:
                String string5 = message.getData().getString("STR_PLACEMENT_KEY");
                string2 = message.getData().getString("STR_BID_PAYLOAD_KEY");
                Boolean valueOf = Boolean.valueOf(message.getData().getBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY"));
                string3 = message.getData().getString("STR_EXTRA_HINTS_KEY");
                AdInternalSettings.f4776a = message.getData().getBundle("BUNDLE_SETTINGS_KEY");
                C1986j c1986j = new C1986j(this.f4481b, string5, null);
                c1986j.f4366d = string3;
                c1986j.f4368f = string2;
                c1986j.f4369g = valueOf.booleanValue();
                Serializable serializable = message.getData().getSerializable("SRL_RV_REWARD_DATA_KEY");
                if (serializable instanceof RewardData) {
                    c1986j.f4367e = (RewardData) serializable;
                }
                e = C1992a.m4771a().m4781e(string);
                if (e != null) {
                    if (e.f4398c == null) {
                        c1977d = new C1980e(c1986j, this.f4482c, string);
                        c1977d.m4713a(c1986j.f4368f, c1986j.f4369g);
                        e.f4398c = c1977d;
                    } else if (e.f4398c instanceof C1980e) {
                        ((C1980e) e.f4398c).m4713a(c1986j.f4368f, c1986j.f4369g);
                    }
                    this.f4482c.m4774a(2010, string);
                } else {
                    C2625a.m6741b(this.f4481b, "api", C2626b.f6548m, new Exception("Missing ad: " + string));
                }
                return true;
            case 2001:
                a = C1992a.m4771a().m4773a(string);
                if (a == null || !(a instanceof C1980e)) {
                    C2625a.m6741b(this.f4481b, "api", C2626b.f6548m, new Exception("Missing ad: " + string));
                } else {
                    ((C1980e) a).m4714a(message.getData().getInt("INT_RV_APP_ORIENTATION_KEY", 0));
                    this.f4482c.m4774a(2011, string);
                }
                return true;
            case 2002:
                C1992a.m4771a().m4778b(string);
                this.f4482c.m4774a(2012, string);
                return true;
            case AdError.INTERNAL_ERROR_2003 /*2003*/:
                a = C1992a.m4771a().m4773a(string);
                if (a == null || !(a instanceof C1980e)) {
                    C2625a.m6741b(this.f4481b, "api", C2626b.f6548m, new Exception("Missing ad: " + string));
                } else {
                    C1980e c1980e = (C1980e) a;
                    Serializable serializable2 = message.getData().getSerializable("SRL_RV_REWARD_DATA_KEY");
                    if (serializable2 instanceof RewardData) {
                        c1980e.m4712a((RewardData) serializable2);
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
