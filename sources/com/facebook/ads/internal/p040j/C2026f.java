package com.facebook.ads.internal.p040j;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.games.GamesStatusCodes;

@VisibleForTesting
/* renamed from: com.facebook.ads.internal.j.f */
public abstract class C2026f<T> {
    /* renamed from: a */
    private C2032a f4498a;

    /* renamed from: com.facebook.ads.internal.j.f$a */
    public enum C2032a {
        UNKNOWN(GamesStatusCodes.STATUS_VIDEO_NOT_ACTIVE, "An unknown error has occurred."),
        DATABASE_SELECT(3001, "Failed to read from database."),
        DATABASE_INSERT(3002, "Failed to insert row into database."),
        DATABASE_UPDATE(3003, "Failed to update row in database."),
        DATABASE_DELETE(AuthApiStatusCodes.AUTH_TOKEN_ERROR, "Failed to delete row from database.");
        
        /* renamed from: f */
        private final int f4527f;
        /* renamed from: g */
        private final String f4528g;

        private C2032a(int i, String str) {
            this.f4527f = i;
            this.f4528g = str;
        }

        /* renamed from: a */
        public int m4918a() {
            return this.f4527f;
        }

        /* renamed from: b */
        public String m4919b() {
            return this.f4528g;
        }
    }

    /* renamed from: a */
    protected void m4894a(C2032a c2032a) {
        this.f4498a = c2032a;
    }

    @Nullable
    /* renamed from: b */
    abstract T mo5462b();

    /* renamed from: c */
    public C2032a m4896c() {
        return this.f4498a;
    }
}
