package com.facebook.ads.internal.p028u;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.p025w.p026b.C2579i;
import com.facebook.ads.internal.p025w.p026b.C2584n;
import com.facebook.ads.internal.p025w.p026b.C2595u;
import com.facebook.ads.internal.p025w.p026b.C2595u.C2594a;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p057e.C2612d;
import com.facebook.ads.internal.p025w.p072g.C2622a;
import com.facebook.ads.internal.p028u.C2132e.C2131a;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p038g.C2002b;
import com.facebook.ads.internal.p041k.C2035a;
import com.facebook.ads.internal.p041k.C2038e;
import com.facebook.ads.internal.p042l.C2042a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.p045n.C2057d;
import com.facebook.ads.internal.p046v.p047a.C2124b;
import com.facebook.ads.internal.p046v.p047a.C2138a;
import com.facebook.ads.internal.p046v.p047a.C2149m;
import com.facebook.ads.internal.p046v.p047a.C2150n;
import com.facebook.ads.internal.p048p.C2063a;
import com.facebook.ads.internal.p049q.C2077a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2066b;
import com.facebook.ads.internal.protocol.C2070e;
import com.facebook.ads.internal.protocol.C2075h;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.File;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;

/* renamed from: com.facebook.ads.internal.u.c */
public class C2129c {
    @Nullable
    /* renamed from: a */
    private static C2127a f4925a;
    /* renamed from: j */
    private static final C2584n f4926j = new C2584n();
    /* renamed from: k */
    private static final ThreadPoolExecutor f4927k = ((ThreadPoolExecutor) Executors.newCachedThreadPool(f4926j));
    /* renamed from: b */
    private final Context f4928b;
    /* renamed from: c */
    private final C2130d f4929c = C2130d.m5425a();
    /* renamed from: d */
    private final C2078a f4930d = C2078a.af(this.f4928b);
    /* renamed from: e */
    private Map<String, String> f4931e;
    /* renamed from: f */
    private C1856b f4932f;
    /* renamed from: g */
    private C2122b f4933g;
    /* renamed from: h */
    private C2138a f4934h;
    /* renamed from: i */
    private final String f4935i;

    /* renamed from: com.facebook.ads.internal.u.c$b */
    public interface C1856b {
        /* renamed from: a */
        void mo5379a(C2065a c2065a);

        /* renamed from: a */
        void mo5380a(C2133f c2133f);
    }

    /* renamed from: com.facebook.ads.internal.u.c$2 */
    class C21252 extends C2124b {
        /* renamed from: a */
        final /* synthetic */ C2129c f4921a;

        C21252(C2129c c2129c) {
            this.f4921a = c2129c;
        }

        /* renamed from: a */
        void m5400a(C2149m c2149m) {
            C2121a.m5387b(this.f4921a.f4933g);
            this.f4921a.f4934h = null;
            try {
                C2150n a = c2149m.m5500a();
                if (a != null) {
                    String e = a.m5505e();
                    C2132e a2 = this.f4921a.f4929c.m5429a(e);
                    if (a2.m5431b() == C2131a.ERROR) {
                        C2134g c2134g = (C2134g) a2;
                        String f = c2134g.m5437f();
                        this.f4921a.m5408a(C2065a.m5036a(AdErrorType.adErrorTypeFromCode(c2134g.m5438g(), AdErrorType.ERROR_MESSAGE), f == null ? e : f));
                        return;
                    }
                }
            } catch (JSONException e2) {
            }
            this.f4921a.m5408a(C2065a.m5036a(AdErrorType.NETWORK_ERROR, c2149m.getMessage()));
        }

        /* renamed from: a */
        public void mo5504a(C2150n c2150n) {
            if (c2150n != null) {
                String e = c2150n.m5505e();
                C2121a.m5387b(this.f4921a.f4933g);
                this.f4921a.f4934h = null;
                this.f4921a.m5412a(e);
            }
        }

        /* renamed from: a */
        public void mo5505a(Exception exception) {
            if (C2149m.class.equals(exception.getClass())) {
                m5400a((C2149m) exception);
            } else {
                this.f4921a.m5408a(C2065a.m5036a(AdErrorType.NETWORK_ERROR, exception.getMessage()));
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.u.c$a */
    public interface C2127a {
        /* renamed from: a */
        C2128c m5403a(C2129c c2129c, C2122b c2122b);

        /* renamed from: a */
        void m5404a(C2129c c2129c, Map<String, String> map);
    }

    /* renamed from: com.facebook.ads.internal.u.c$c */
    public static class C2128c {
        @Nullable
        /* renamed from: a */
        public final C2133f f4923a;
        @Nullable
        /* renamed from: b */
        public final C2065a f4924b;
    }

    public C2129c(Context context) {
        String str;
        this.f4928b = context.getApplicationContext();
        if (TextUtils.isEmpty(AdInternalSettings.getUrlPrefix())) {
            str = "https://graph.facebook.com/network_ads_common";
        } else {
            str = String.format(Locale.US, "https://graph.%s.facebook.com/network_ads_common", new Object[]{AdInternalSettings.getUrlPrefix()});
        }
        this.f4935i = str;
    }

    /* renamed from: a */
    private void m5408a(C2065a c2065a) {
        if (this.f4932f != null) {
            this.f4932f.mo5379a(c2065a);
        }
        m5421a();
    }

    /* renamed from: a */
    private void m5411a(C2133f c2133f) {
        if (this.f4932f != null) {
            this.f4932f.mo5380a(c2133f);
        }
        m5421a();
    }

    /* renamed from: a */
    private void m5412a(String str) {
        Exception e;
        boolean z = true;
        int i = 0;
        try {
            C2132e a = this.f4929c.m5429a(str);
            C2046c a2 = a.mo5506a();
            if (a2 != null) {
                this.f4930d.m5121a(a2.m4972b());
                if (AdInternalSettings.f4779d) {
                    Context context;
                    File file;
                    if (C2078a.m5083V(this.f4928b)) {
                        context = this.f4928b;
                        if (context == null) {
                            z = false;
                        }
                        if (z) {
                            try {
                                file = new File(context.getFilesDir(), "com.facebook.ads.ipc");
                                if (!file.exists()) {
                                    z = file.createNewFile();
                                }
                            } catch (Exception e2) {
                                e = e2;
                            }
                        }
                        e = !z ? new Exception("Can't create ipc marker.") : null;
                        if (e != null) {
                            C2625a.m6738a(context, "ipc", C2626b.ac, e);
                        }
                    } else {
                        context = this.f4928b;
                        if (context == null) {
                            z = false;
                        }
                        if (z) {
                            try {
                                file = new File(context.getFilesDir(), "com.facebook.ads.ipc");
                                if (file.exists()) {
                                    z = file.delete();
                                }
                            } catch (Exception e3) {
                                e = e3;
                            }
                        }
                        e = !z ? new Exception("Can't delete ipc marker.") : null;
                        if (e != null) {
                            C2625a.m6738a(context, "ipc", C2626b.ac, e);
                        }
                    }
                }
                C1993a.m4786a(this.f4928b, a2.m4973c());
                C2121a.m5384a(a2.m4970a().m4983d(), this.f4933g);
                C2622a.m6736a(this.f4928b, f4927k, a2);
            }
            switch (a.m5431b()) {
                case ADS:
                    if (C2078a.m5116z(this.f4928b)) {
                        C2063a.m5034a(this.f4928b, m5415c());
                    }
                    C2133f c2133f = (C2133f) a;
                    if (a2 != null) {
                        if (a2.m4970a().m4984e()) {
                            C2121a.m5385a(str, this.f4933g);
                        }
                        Object obj = this.f4931e != null ? (String) this.f4931e.get("CLIENT_REQUEST_ID") : null;
                        String c = a.m5432c();
                        if (!(TextUtils.isEmpty(c) || TextUtils.isEmpty(obj))) {
                            StringBuilder stringBuilder = new StringBuilder();
                            while (i < "73q8p304q6q511r89s8os2801s1o9sq1".length()) {
                                char charAt = "73q8p304q6q511r89s8os2801s1o9sq1".charAt(i);
                                if ((charAt >= 'a' && charAt <= 'm') || (charAt >= 'A' && charAt <= 'M')) {
                                    charAt = (char) (charAt + 13);
                                } else if ((charAt >= 'n' && charAt <= 'z') || (charAt >= 'N' && charAt <= 'Z')) {
                                    charAt = (char) (charAt - 13);
                                }
                                stringBuilder.append(charAt);
                                i++;
                            }
                            byte[] bytes = (obj + c + stringBuilder.toString()).getBytes("iso-8859-1");
                            MessageDigest instance = MessageDigest.getInstance(Constants.SHA1);
                            instance.update(bytes, 0, bytes.length);
                            if (!a.m5433d().equals(C2579i.m6641a(instance.digest()))) {
                                C2625a.m6741b(this.f4928b, "network", C2626b.f6555t, new C2075h());
                            }
                            bytes = (c + obj + stringBuilder.toString()).getBytes("iso-8859-1");
                            instance = MessageDigest.getInstance(Constants.SHA1);
                            instance.update(bytes, 0, bytes.length);
                            C2038e.m4936a(new C2035a(c, C2579i.m6641a(instance.digest())), this.f4928b);
                        }
                        if (!(TextUtils.isEmpty(a.m5434e()) || TextUtils.isEmpty(obj))) {
                            new C2077a(this.f4928b, obj, a.m5434e()).m5061a();
                        }
                    }
                    m5411a(c2133f);
                    return;
                case ERROR:
                    C2134g c2134g = (C2134g) a;
                    String f = c2134g.m5437f();
                    AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(c2134g.m5438g(), AdErrorType.ERROR_MESSAGE);
                    if (f != null) {
                        str = f;
                    }
                    m5408a(C2065a.m5036a(adErrorTypeFromCode, str));
                    return;
                default:
                    m5408a(C2065a.m5036a(AdErrorType.UNKNOWN_RESPONSE, str));
                    return;
            }
        } catch (Exception e4) {
            m5408a(C2065a.m5036a(AdErrorType.PARSER_FAILURE, e4.getMessage()));
        }
        m5408a(C2065a.m5036a(AdErrorType.PARSER_FAILURE, e4.getMessage()));
    }

    /* renamed from: c */
    private C2124b m5415c() {
        return new C21252(this);
    }

    /* renamed from: a */
    public void m5421a() {
        if (this.f4934h != null) {
            this.f4934h.m5476c(1);
            this.f4934h.m5473b(1);
            this.f4934h = null;
        }
    }

    /* renamed from: a */
    public void m5422a(C2122b c2122b) {
        m5423a(c2122b, false);
    }

    /* renamed from: a */
    public void m5423a(final C2122b c2122b, final boolean z) {
        m5421a();
        if (!(z || f4925a == null)) {
            C2128c a = f4925a.m5403a(this, c2122b);
            if (a != null) {
                if (a.f4923a != null) {
                    m5411a(a.f4923a);
                    return;
                } else if (a.f4924b != null) {
                    m5408a(a.f4924b);
                    return;
                }
            }
        }
        if (C2595u.m6664a(this.f4928b) == C2594a.NONE) {
            m5408a(new C2065a(AdErrorType.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.f4933g = c2122b;
        C2042a.m4945a(this.f4928b);
        if (C2121a.m5386a(c2122b)) {
            String c = C2121a.m5388c(c2122b);
            if (c != null) {
                m5412a(c);
                return;
            } else {
                m5408a(C2065a.m5036a(AdErrorType.LOAD_TOO_FREQUENTLY, null));
                return;
            }
        }
        f4927k.submit(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C2129c f4920c;

            public void run() {
                C2002b.m4828a(this.f4920c.f4928b);
                C2057d.m5011a(this.f4920c.f4928b);
                if (c2122b.m5396f().m5052a()) {
                    try {
                        c2122b.m5396f().m5051a(C2002b.f4431b);
                    } catch (C2066b e) {
                        this.f4920c.m5408a(C2065a.m5037a(e));
                    }
                    this.f4920c.m5412a(c2122b.m5396f().m5053b());
                    return;
                }
                this.f4920c.f4931e = c2122b.m5397g();
                if (z && C2129c.f4925a != null) {
                    C2129c.f4925a.m5404a(this.f4920c, this.f4920c.f4931e);
                }
                try {
                    this.f4920c.f4931e.put("M_BANNER_KEY", new String(Base64.encode((this.f4920c.f4928b.getPackageName() + " " + this.f4920c.f4928b.getPackageManager().getInstallerPackageName(this.f4920c.f4928b.getPackageName())).getBytes(), 2)));
                } catch (Exception e2) {
                }
                try {
                    boolean z = c2122b.m5391a() == C2070e.NATIVE_250 || c2122b.m5391a() == C2070e.NATIVE_UNKNOWN || c2122b.m5391a() == C2070e.NATIVE_BANNER || c2122b.m5391a() == null;
                    this.f4920c.f4934h = C2612d.m6710a(this.f4920c.f4928b, z);
                    this.f4920c.f4934h.m5474b(this.f4920c.f4935i, this.f4920c.f4934h.m5462a().m5508a(this.f4920c.f4931e), this.f4920c.m5415c());
                } catch (Exception e3) {
                    this.f4920c.m5408a(C2065a.m5036a(AdErrorType.AD_REQUEST_FAILED, e3.getMessage()));
                }
            }
        });
    }

    /* renamed from: a */
    public void m5424a(C1856b c1856b) {
        this.f4932f = c1856b;
    }
}
