package com.facebook.ads.internal.view.p056b;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.b.c */
public class C2233c {
    /* renamed from: a */
    public final String f5183a;
    /* renamed from: b */
    public final long f5184b;
    /* renamed from: c */
    public final long f5185c;
    /* renamed from: d */
    public final long f5186d;
    /* renamed from: e */
    public final long f5187e;
    /* renamed from: f */
    public final long f5188f;
    /* renamed from: g */
    public final long f5189g;
    /* renamed from: h */
    public final long f5190h;

    /* renamed from: com.facebook.ads.internal.view.b.c$a */
    public static class C2232a {
        /* renamed from: a */
        private final String f5175a;
        /* renamed from: b */
        private long f5176b = -1;
        /* renamed from: c */
        private long f5177c = -1;
        /* renamed from: d */
        private long f5178d = -1;
        /* renamed from: e */
        private long f5179e = -1;
        /* renamed from: f */
        private long f5180f = -1;
        /* renamed from: g */
        private long f5181g = -1;
        /* renamed from: h */
        private long f5182h = -1;

        public C2232a(String str) {
            this.f5175a = str;
        }

        /* renamed from: a */
        public C2232a m5710a(long j) {
            this.f5176b = j;
            return this;
        }

        /* renamed from: a */
        public C2233c m5711a() {
            return new C2233c(this.f5175a, this.f5176b, this.f5177c, this.f5178d, this.f5179e, this.f5180f, this.f5181g, this.f5182h);
        }

        /* renamed from: b */
        public C2232a m5712b(long j) {
            this.f5177c = j;
            return this;
        }

        /* renamed from: c */
        public C2232a m5713c(long j) {
            this.f5178d = j;
            return this;
        }

        /* renamed from: d */
        public C2232a m5714d(long j) {
            this.f5179e = j;
            return this;
        }

        /* renamed from: e */
        public C2232a m5715e(long j) {
            this.f5180f = j;
            return this;
        }

        /* renamed from: f */
        public C2232a m5716f(long j) {
            this.f5181g = j;
            return this;
        }

        /* renamed from: g */
        public C2232a m5717g(long j) {
            this.f5182h = j;
            return this;
        }
    }

    private C2233c(String str, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.f5183a = str;
        this.f5184b = j;
        this.f5185c = j2;
        this.f5186d = j3;
        this.f5187e = j4;
        this.f5188f = j5;
        this.f5189g = j6;
        this.f5190h = j7;
    }

    /* renamed from: a */
    public Map<String, String> m5718a() {
        Map<String, String> hashMap = new HashMap(7);
        hashMap.put("initial_url", this.f5183a);
        hashMap.put("handler_time_ms", String.valueOf(this.f5184b));
        hashMap.put("load_start_ms", String.valueOf(this.f5185c));
        hashMap.put("response_end_ms", String.valueOf(this.f5186d));
        hashMap.put("dom_content_loaded_ms", String.valueOf(this.f5187e));
        hashMap.put("scroll_ready_ms", String.valueOf(this.f5188f));
        hashMap.put("load_finish_ms", String.valueOf(this.f5189g));
        hashMap.put("session_finish_ms", String.valueOf(this.f5190h));
        return hashMap;
    }
}
