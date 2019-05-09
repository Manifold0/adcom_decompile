package com.kongregate.p000o.p003a;

import com.kongregate.android.internal.util.C0562j;
import java.util.Locale;

/* renamed from: com.kongregate.o.a.f */
public enum C0584f {
    HARD_CURRENCY_BALANCE,
    HARD_CURRENCY_BOUGHT,
    HARD_CURRENCY_SPENT,
    HARD_CURRENCY_EARNED,
    SOFT_CURRENCY_BALANCE,
    SOFT_CURRENCY_BOUGHT,
    SOFT_CURRENCY_SPENT,
    SOFT_CURRENCY_EARNED,
    SOFT_CURRENCY_2_BALANCE,
    SOFT_CURRENCY_2_BOUGHT,
    SOFT_CURRENCY_2_SPENT,
    SOFT_CURRENCY_2_EARNED,
    AB_TEST,
    PLAYER_LEVEL,
    TUTORIAL_COMPLETED,
    SERVER_PLAYER_ID,
    FILTER_TYPE,
    GAME_USERNAME,
    GOOGLE_PLAY_SERVICES_ID,
    GOOGLE_PLAY_SERVICES_ALIAS,
    LEGACY_USER,
    HARD_CURRENCY_CHANGE,
    SOFT_CURRENCY_CHANGE,
    TYPE,
    DISCOUNT_PERCENT,
    CONTEXT_OF_OFFER;

    /* renamed from: a */
    public String m866a() {
        return toString().toLowerCase(Locale.US);
    }

    /* renamed from: a */
    public static C0584f m865a(String str) {
        try {
            return C0584f.valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException e) {
            C0562j.m759c("Unrecognized game callback field name: " + str);
            return null;
        }
    }
}
