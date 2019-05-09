// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.g;

import android.app.Activity;
import java.util.Locale;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog$Builder;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.v.a.p;
import java.util.Map;
import com.facebook.ads.internal.w.e.d;
import com.facebook.ads.internal.m.c;
import java.util.concurrent.ThreadPoolExecutor;
import android.content.Context;
import android.support.annotation.Nullable;

public class a
{
    @Nullable
    private static a a;
    private final b b;
    private final a c;
    
    public a(final Context context, final ThreadPoolExecutor threadPoolExecutor, final c c) {
        this.b = new b(context);
        this.c = new a(threadPoolExecutor, c, context);
    }
    
    public static void a(final Context context, final ThreadPoolExecutor threadPoolExecutor, final c c) {
        if (!com.facebook.ads.internal.r.a.c(context) || com.facebook.ads.internal.w.g.a.a != null) {
            return;
        }
        com.facebook.ads.internal.w.g.a.a = new a(context, threadPoolExecutor, c);
        final a a = com.facebook.ads.internal.w.g.a.a;
        a.b.a((b.a)a.c);
    }
    
    private static class a implements b.a
    {
        private final com.facebook.ads.internal.v.a.a a;
        private final ThreadPoolExecutor b;
        private final c c;
        
        public a(final ThreadPoolExecutor b, final c c, final Context context) {
            this.a = d.b(context);
            this.b = b;
            this.c = c;
        }
        
        static /* synthetic */ Map a(final a a, final String s) {
            final p p2 = new p();
            final p p3 = new p();
            final p p4 = new p();
            p2.a("user_identifier", com.facebook.ads.internal.g.b.b);
            p2.a("config_id", "297035420885434");
            p2.a("category_id", "277149136230712");
            p2.a("access_token", "693953940997901|9bf29a1f2745746a6c60d707f5bc23c2");
            p2.a("client_time", System.currentTimeMillis() / 1000L + "");
            final String f = a.c.f();
            if (f != null) {
                p4.a("client_token", f);
            }
            p3.a("description", s);
            p3.a("misc_info", k.a(p4));
            p2.b("metadata", k.a(p3));
            return p2;
        }
        
        @Override
        public void a() {
            final Activity a = com.facebook.ads.internal.w.a.b.a();
            if (a == null) {
                return;
            }
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)a);
            alertDialog$Builder.setTitle((CharSequence)"What Happened?");
            final EditText view = new EditText((Context)a);
            view.setSingleLine(false);
            view.setImeOptions(1073741824);
            view.setHint((CharSequence)"May others login as you to debug? How do you reproduce the issue?");
            view.setMaxLines(2);
            view.setMinLines(2);
            alertDialog$Builder.setView((View)view);
            alertDialog$Builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    dialogInterface.cancel();
                }
            });
            alertDialog$Builder.setPositiveButton((CharSequence)"Send Report", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    com.facebook.ads.internal.w.g.a.a.this.b.execute(new Runnable() {
                        @Override
                        public void run() {
                            final com.facebook.ads.internal.v.a.a a = com.facebook.ads.internal.w.g.a.a.this.a;
                            final String urlPrefix = AdInternalSettings.getUrlPrefix();
                            String format;
                            if (TextUtils.isEmpty((CharSequence)urlPrefix)) {
                                format = "https://graph.facebook.com/693953940997901/bugs";
                            }
                            else {
                                format = String.format(Locale.US, "https://graph.%s.facebook.com/693953940997901/bugs", urlPrefix);
                            }
                            a.b(format, com.facebook.ads.internal.w.g.a.a.this.a.a().a(com.facebook.ads.internal.w.g.a.a.a(com.facebook.ads.internal.w.g.a.a.this, view.getText().toString())));
                            dialogInterface.cancel();
                        }
                    });
                }
            });
            alertDialog$Builder.create().show();
        }
    }
}
