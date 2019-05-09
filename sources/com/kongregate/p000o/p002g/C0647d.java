package com.kongregate.p000o.p002g;

import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0568p;
import com.kongregate.android.internal.util.StringUtils;
import com.tonyodev.fetch.FetchConst;
import java.net.URI;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.g.d */
public class C0647d {
    /* renamed from: a */
    public static C0646c m1099a(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (i < 10 && System.currentTimeMillis() - currentTimeMillis < 20000) {
            C0562j.m753a("Requesting pollForResult status(" + str + ") try #" + (i + 1));
            C0646c e = C0640a.m1055b().m1074e(str);
            C0562j.m753a("pollForResult - result: " + e.m789h());
            C0562j.m759c("pollForResult: " + e.m1095b());
            if (e.m787f()) {
                JSONObject c = e.m1096c();
                if (c != null) {
                    if (!c.optBoolean("success", false)) {
                        C0562j.m759c("pollForResult - error");
                        return new C0646c(C0568p.ERROR_DATA);
                    } else if (c.optString(str2, null) != null) {
                        C0562j.m756b("pollForResult - success!");
                        return e;
                    }
                }
            }
            C0562j.m756b("pollForResult: " + e.m789h().toString());
            try {
                C0562j.m759c("pollForResult try again in: 2000");
                Thread.sleep(FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
                i++;
            } catch (InterruptedException e2) {
                C0562j.m759c("pollForResult polling canceled");
                Thread.currentThread().interrupt();
                return new C0646c(C0568p.ERROR_CANCELED);
            }
        }
        C0562j.m759c("pollForResult - timeout");
        return new C0646c(C0568p.ERROR_TIMEOUT);
    }

    /* renamed from: a */
    public static URI m1100a(long j, String str, String str2, boolean z) {
        StringBuilder stringBuilder = new StringBuilder("/games_redirect/" + j);
        if (!StringUtils.m580a((CharSequence) str)) {
            stringBuilder.append("?path=").append(StringUtils.m596h(str));
            if (!StringUtils.m580a((CharSequence) str2)) {
                stringBuilder.append("&query=").append(StringUtils.m596h(str2));
            }
        }
        return C0640a.m1054a(stringBuilder.toString(), z);
    }
}
