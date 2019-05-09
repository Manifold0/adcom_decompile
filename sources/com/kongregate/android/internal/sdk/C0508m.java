package com.kongregate.android.internal.sdk;

import com.kongregate.android.api.KongregateServices;
import com.kongregate.p000o.p001j.C0666a;

/* renamed from: com.kongregate.android.internal.sdk.m */
public class C0508m implements KongregateServices {
    public String getUsername() {
        return C0666a.m1145a().m1178h();
    }

    public long getUserId() {
        return C0666a.m1145a().m1177g();
    }

    public boolean isGuest() {
        return C0666a.m1145a().m1176f();
    }

    public boolean isConnected() {
        return false;
    }

    public boolean hasKongPlus() {
        return C0666a.m1145a().m1179i();
    }

    public int getNotificationCount() {
        return C0666a.m1145a().m1188r();
    }

    public boolean hasUnreadGuildMessages() {
        return C0666a.m1145a().m1189s();
    }

    public String getGameAuthToken() {
        return C0666a.m1145a().m1192v();
    }

    public void setCharacterToken(String str) {
        C0666a.m1145a().m1173c(str);
    }
}
