package com.facebook.ads.internal.p025w.p072g;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p057e.C2612d;
import com.facebook.ads.internal.p025w.p068a.C2564b;
import com.facebook.ads.internal.p025w.p072g.C2623b.C2620a;
import com.facebook.ads.internal.p038g.C2002b;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.p046v.p047a.C2138a;
import com.facebook.ads.internal.p046v.p047a.C2152p;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: com.facebook.ads.internal.w.g.a */
public class C2622a {
    @Nullable
    /* renamed from: a */
    private static C2622a f6489a;
    /* renamed from: b */
    private final C2623b f6490b;
    /* renamed from: c */
    private final C2621a f6491c;

    /* renamed from: com.facebook.ads.internal.w.g.a$a */
    private static class C2621a implements C2620a {
        /* renamed from: a */
        private final C2138a f6486a;
        /* renamed from: b */
        private final ThreadPoolExecutor f6487b;
        /* renamed from: c */
        private final C2046c f6488c;

        /* renamed from: com.facebook.ads.internal.w.g.a$a$1 */
        class C26171 implements OnClickListener {
            /* renamed from: a */
            final /* synthetic */ C2621a f6481a;

            C26171(C2621a c2621a) {
                this.f6481a = c2621a;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }

        public C2621a(ThreadPoolExecutor threadPoolExecutor, C2046c c2046c, Context context) {
            this.f6486a = C2612d.m6713b(context);
            this.f6487b = threadPoolExecutor;
            this.f6488c = c2046c;
        }

        /* renamed from: a */
        static /* synthetic */ Map m6733a(C2621a c2621a, String str) {
            Map c2152p = new C2152p();
            Map c2152p2 = new C2152p();
            Map c2152p3 = new C2152p();
            c2152p.m5511a("user_identifier", C2002b.f4431b);
            c2152p.m5511a("config_id", "297035420885434");
            c2152p.m5511a("category_id", "277149136230712");
            c2152p.m5511a("access_token", "693953940997901|9bf29a1f2745746a6c60d707f5bc23c2");
            c2152p.m5511a("client_time", (System.currentTimeMillis() / 1000) + "");
            String f = c2621a.f6488c.m4976f();
            if (f != null) {
                c2152p3.m5511a("client_token", f);
            }
            c2152p2.m5511a("description", str);
            c2152p2.m5511a("misc_info", C2581k.m6645a(c2152p3));
            c2152p.m5512b("metadata", C2581k.m6645a(c2152p2));
            return c2152p;
        }

        /* renamed from: a */
        public void mo5645a() {
            Context a = C2564b.m6613a();
            if (a != null) {
                Builder builder = new Builder(a);
                builder.setTitle("What Happened?");
                final View editText = new EditText(a);
                editText.setSingleLine(false);
                editText.setImeOptions(1073741824);
                editText.setHint("May others login as you to debug? How do you reproduce the issue?");
                editText.setMaxLines(2);
                editText.setMinLines(2);
                builder.setView(editText);
                builder.setNegativeButton("Cancel", new C26171(this));
                builder.setPositiveButton("Send Report", new OnClickListener(this) {
                    /* renamed from: b */
                    final /* synthetic */ C2621a f6485b;

                    public void onClick(final DialogInterface dialogInterface, int i) {
                        this.f6485b.f6487b.execute(new Runnable(this) {
                            /* renamed from: b */
                            final /* synthetic */ C26192 f6483b;

                            public void run() {
                                String str;
                                C2138a a = this.f6483b.f6485b.f6486a;
                                if (TextUtils.isEmpty(AdInternalSettings.getUrlPrefix())) {
                                    str = "https://graph.facebook.com/693953940997901/bugs";
                                } else {
                                    str = String.format(Locale.US, "https://graph.%s.facebook.com/693953940997901/bugs", new Object[]{AdInternalSettings.getUrlPrefix()});
                                }
                                a.m5470b(str, this.f6483b.f6485b.f6486a.m5462a().m5508a(C2621a.m6733a(this.f6483b.f6485b, editText.getText().toString())));
                                dialogInterface.cancel();
                            }
                        });
                    }
                });
                builder.create().show();
            }
        }
    }

    public C2622a(Context context, ThreadPoolExecutor threadPoolExecutor, C2046c c2046c) {
        this.f6490b = new C2623b(context);
        this.f6491c = new C2621a(threadPoolExecutor, c2046c, context);
    }

    /* renamed from: a */
    public static void m6736a(Context context, ThreadPoolExecutor threadPoolExecutor, C2046c c2046c) {
        if (C2078a.m5092c(context) && f6489a == null) {
            f6489a = new C2622a(context, threadPoolExecutor, c2046c);
            C2622a c2622a = f6489a;
            c2622a.f6490b.m6737a(c2622a.f6491c);
        }
    }
}
