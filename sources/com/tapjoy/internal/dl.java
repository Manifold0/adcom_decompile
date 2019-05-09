package com.tapjoy.internal;

import java.io.IOException;
import java.io.Serializable;

public abstract class dl implements Serializable {
    /* renamed from: a */
    transient int f7363a = 0;
    /* renamed from: b */
    protected transient int f7364b = 0;
    /* renamed from: c */
    private final transient dn f7365c;
    /* renamed from: d */
    private final transient hx f7366d;

    /* renamed from: com.tapjoy.internal.dl$a */
    public static abstract class C2863a {
        /* renamed from: a */
        hu f7361a;
        /* renamed from: b */
        dp f7362b;

        protected C2863a() {
        }

        /* renamed from: a */
        public final C2863a m7404a(hx hxVar) {
            if (hxVar.mo6363c() > 0) {
                if (this.f7362b == null) {
                    this.f7361a = new hu();
                    this.f7362b = new dp(this.f7361a);
                }
                try {
                    this.f7362b.m7469a(hxVar);
                } catch (IOException e) {
                    throw new AssertionError();
                }
            }
            return this;
        }

        /* renamed from: a */
        public final C2863a m7403a(int i, dk dkVar, Object obj) {
            if (this.f7362b == null) {
                this.f7361a = new hu();
                this.f7362b = new dp(this.f7361a);
            }
            try {
                dkVar.m7402a().mo6217a(this.f7362b, i, obj);
                return this;
            } catch (IOException e) {
                throw new AssertionError();
            }
        }

        /* renamed from: a */
        public final hx m7405a() {
            return this.f7361a != null ? new hx(this.f7361a.m8130h().m8129g()) : hx.f8185b;
        }
    }

    protected dl(dn dnVar, hx hxVar) {
        if (dnVar == null) {
            throw new NullPointerException("adapter == null");
        } else if (hxVar == null) {
            throw new NullPointerException("unknownFields == null");
        } else {
            this.f7365c = dnVar;
            this.f7366d = hxVar;
        }
    }

    /* renamed from: a */
    public final hx m7406a() {
        hx hxVar = this.f7366d;
        return hxVar != null ? hxVar : hx.f8185b;
    }

    public String toString() {
        return dn.m7387c(this);
    }
}
