package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1877i;
import com.facebook.ads.internal.p025w.p026b.C2577g;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p068a.C2564b;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.component.a */
public class C2295a extends C2294c {
    /* renamed from: b */
    private final String f5428b;
    /* renamed from: c */
    private final C2630a f5429c;
    /* renamed from: d */
    private final C2598w f5430d;
    @Nullable
    /* renamed from: e */
    private final C2085c f5431e;
    @Nullable
    /* renamed from: f */
    private final C1789a f5432f;

    /* renamed from: com.facebook.ads.internal.view.component.a$a */
    public interface C2258a {
        /* renamed from: a */
        void mo5563a();
    }

    public C2295a(Context context, boolean z, boolean z2, String str, C1876h c1876h, C2085c c2085c, C1789a c1789a, C2630a c2630a, C2598w c2598w) {
        super(context, z, z2, c1876h);
        this.f5431e = c2085c;
        this.f5432f = c1789a;
        this.f5428b = str;
        this.f5429c = c2630a;
        this.f5430d = c2598w;
    }

    @Nullable
    /* renamed from: a */
    private C1842b m5909a(Uri uri, String str, Map<String, String> map, boolean z) {
        return C1843c.m4143a(getContext(), this.f5431e, str, uri, map, z, false);
    }

    /* renamed from: a */
    private void m5912a(String str, String str2, String str3, Map<String, String> map, boolean z, @Nullable C2258a c2258a) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.f5431e == null) {
            setVisibility(8);
            return;
        }
        setText(str);
        final String str4 = str3;
        final Map<String, String> map2 = map;
        final String str5 = str2;
        final boolean z2 = z;
        final C2258a c2258a2 = c2258a;
        setOnClickListener(new OnClickListener(this) {
            /* renamed from: f */
            final /* synthetic */ C2295a f5321f;

            /* renamed from: com.facebook.ads.internal.view.component.a$1$1 */
            class C22551 implements DialogInterface.OnClickListener {
                /* renamed from: a */
                final /* synthetic */ C22571 f5314a;

                C22551(C22571 c22571) {
                    this.f5314a = c22571;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    map2.put("is_two_step", "true");
                    this.f5314a.m5781a();
                }
            }

            /* renamed from: com.facebook.ads.internal.view.component.a$1$2 */
            class C22562 implements DialogInterface.OnClickListener {
                /* renamed from: a */
                final /* synthetic */ C22571 f5315a;

                C22562(C22571 c22571) {
                    this.f5315a = c22571;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (this.f5315a.f5321f.f5431e != null) {
                        this.f5315a.f5321f.f5431e.mo5482k(str4, map2);
                    }
                }
            }

            /* renamed from: a */
            private void m5781a() {
                if (!C2078a.m5101k(this.f5321f.getContext()) || this.f5321f.f5430d.m6673b()) {
                    try {
                        Uri parse = Uri.parse(str5);
                        this.f5321f.f5429c.m6771a(map2);
                        map2.put("touch", C2581k.m6645a(this.f5321f.f5430d.m6676e()));
                        C1842b a = this.f5321f.m5909a(parse, str4, map2, z2);
                        if (a != null && c2258a2 == null) {
                            a.mo5376a();
                        } else if (c2258a2 != null) {
                            c2258a2.mo5563a();
                        }
                        if (this.f5321f.f5432f != null) {
                            this.f5321f.f5432f.mo5335a(this.f5321f.f5428b);
                        }
                    } catch (Throwable e) {
                        Log.e(String.valueOf(C2295a.class), "Error while opening " + str5, e);
                    } catch (Throwable e2) {
                        Log.e(String.valueOf(C2295a.class), "Error executing action", e2);
                    }
                }
            }

            public void onClick(View view) {
                if (this.f5321f.f5430d.m6672a(this.f5321f.getContext())) {
                    if (this.f5321f.f5431e != null) {
                        this.f5321f.f5431e.mo5480i(str4, map2);
                    }
                } else if (C2078a.m5095e(this.f5321f.getContext())) {
                    if (this.f5321f.f5431e != null) {
                        this.f5321f.f5431e.mo5481j(str4, map2);
                    }
                    C2577g.m6637a(new C22551(this), new C22562(this), C2564b.m6613a());
                } else {
                    m5781a();
                }
            }
        });
    }

    /* renamed from: a */
    public void m5917a(C1877i c1877i, String str, Map<String, String> map) {
        m5912a(c1877i.m4296b(), c1877i.m4295a(), str, map, false, null);
    }

    /* renamed from: a */
    public void m5918a(C1877i c1877i, String str, Map<String, String> map, @Nullable C2258a c2258a) {
        m5912a(c1877i.m4296b(), c1877i.m4295a(), str, map, false, c2258a);
    }

    /* renamed from: a */
    public void m5919a(C1877i c1877i, String str, Map<String, String> map, boolean z) {
        m5912a(c1877i.m4296b(), c1877i.m4295a(), str, map, z, null);
    }

    /* renamed from: b */
    public void m5920b(C1877i c1877i, String str, Map<String, String> map) {
        Uri parse = Uri.parse(c1877i.m4295a());
        this.f5429c.m6771a((Map) map);
        map.put("touch", C2581k.m6645a(this.f5430d.m6676e()));
        C1842b a = m5909a(parse, str, (Map) map, false);
        if (a != null) {
            a.mo5377b();
        }
    }
}
