// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.ActivityNotFoundException;
import android.view.Window;
import android.view.WindowManager$BadTokenException;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.os.Build$VERSION;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import com.tapjoy.TJContentActivity;
import android.os.SystemClock;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyErrorMessage;
import android.app.Activity;
import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;

public class gh extends gj
{
    private static final String h;
    private static gh i;
    final String a;
    final gx b;
    private final gc j;
    private e k;
    private boolean l;
    private long m;
    private Context n;
    private boolean o;
    
    static {
        h = gh.class.getSimpleName();
    }
    
    public gh(final gc j, final String a, final gx b, final Context n) {
        this.o = false;
        this.j = j;
        this.a = a;
        this.b = b;
        this.n = n;
    }
    
    private static Boolean a(final Context context) {
        try {
            final Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (metaData != null) {
                final Object value = metaData.get("tapjoy:hardwareAccelerated");
                if (value instanceof Boolean) {
                    return (Boolean)value;
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return null;
    }
    
    public static void a() {
        final gh i = gh.i;
        if (i != null) {
            i.e();
        }
    }
    
    private void a(final Activity activity, final gd gd, final ez ez) {
        if (this.l) {
            TapjoyLog.e(gh.h, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Content is already displayed"));
            return;
        }
        this.l = true;
        gh.i = this;
        this.g = ez.a;
        (this.k = new e((Context)activity)).setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public final void onCancel(final DialogInterface dialogInterface) {
                gd.d(gh.this.a);
            }
        });
        this.k.setOnDismissListener((DialogInterface$OnDismissListener)new DialogInterface$OnDismissListener() {
            public final void onDismiss(final DialogInterface dialogInterface) {
                gh.i = null;
                gj.a((Context)activity, gh.this.b.g);
                gh.this.j.a(gh.this.b.k, SystemClock.elapsedRealtime() - gh.this.m);
                if (!gh.this.d) {
                    gd.a(gh.this.a, gh.this.f, gh.this.b.h);
                }
                if (gh.this.o && gh.this.b.k != null && gh.this.b.k.containsKey("action_id")) {
                    final String string = gh.this.b.k.get("action_id").toString();
                    if (string != null && string.length() > 0) {
                        final gc c = gh.this.j;
                        if (c.b != null) {
                            final gl b = c.b;
                            final String a = gl.a();
                            String a2 = b.b.a();
                            final String a3 = b.a.a();
                            if (a3 == null || !a.equals(a3)) {
                                b.a.a(a);
                                a2 = "";
                            }
                            int n;
                            if (a2.length() == 0) {
                                n = 1;
                            }
                            else {
                                n = 0;
                            }
                            String concat;
                            if (n != 0) {
                                concat = string;
                            }
                            else {
                                concat = a2;
                                if (!a2.contains(string)) {
                                    concat = a2.concat("," + string);
                                }
                            }
                            b.b.a(concat);
                        }
                    }
                }
                if (activity instanceof TJContentActivity) {
                    activity.finish();
                }
            }
        });
        this.k.setCanceledOnTouchOutside(false);
        final hs hs = new hs((Context)activity, this.b, new ht((Context)activity, this.b, (ht.a)new ht.a() {
            @Override
            public final void a() {
                gh.this.k.cancel();
            }
            
            @Override
            public final void a(final gv gv) {
                if (gh.this.g instanceof ex) {
                    final ex ex = (ex)gh.this.g;
                    if (ex != null && ex.c != null) {
                        ex.c.a();
                    }
                }
                gh.this.j.a(gh.this.b.k, gv.b);
                gj.a((Context)activity, gv.d);
                if (!ct.c(gv.e)) {
                    gh.this.e.a((Context)activity, gv.e, ct.b(gv.f));
                    gh.this.d = true;
                }
                gd.a(gh.this.a, gv.g);
                if (gv.c) {
                    gh.this.k.dismiss();
                }
            }
            
            @Override
            public final void b() {
                gh.this.o = !gh.this.o;
            }
        }));
        final FrameLayout contentView = new FrameLayout((Context)activity);
        contentView.addView((View)hs, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 17));
        this.k.setContentView((View)contentView);
        Label_0301: {
            if (!Boolean.FALSE) {
                break Label_0301;
            }
            final Window window = this.k.getWindow();
            Label_0412: {
                if (Build$VERSION.SDK_INT != 16 || !"4.1.2".equals(Build$VERSION.RELEASE)) {
                    break Label_0412;
                }
                Label_0401: {
                    if (!Boolean.FALSE.equals(a(window.getContext()))) {
                        break Label_0401;
                    }
                    int n = 0;
                Label_0288_Outer:
                    while (true) {
                        if (n == 0) {
                            break Label_0301;
                        }
                        final int b = ai.a.b;
                        final aj aj = new aj();
                        Label_0654: {
                            switch (ai.ai$1.a[b - 1]) {
                                case 1: {
                                    break Label_0654;
                                }
                                case 2: {
                                    break Label_0654;
                                }
                                case 3: {
                                    break Label_0654;
                                }
                                case 4: {
                                    break Label_0654;
                                }
                            }
                            while (true) {
                                hs.startAnimation(aj.b().a());
                                try {
                                    this.k.show();
                                    this.k.getWindow().setLayout(-1, -1);
                                    if ((activity.getWindow().getAttributes().flags & 0x400) != 0x0) {
                                        this.k.getWindow().setFlags(1024, 1024);
                                    }
                                    this.m = SystemClock.elapsedRealtime();
                                    this.j.a(this.b.k);
                                    ez.a();
                                    final et g = this.g;
                                    if (g != null) {
                                        g.b();
                                    }
                                    gd.c(this.a);
                                    return;
                                    window.setFlags(16777216, 16777216);
                                    break Label_0412;
                                    final al al = new al();
                                    al.a = false;
                                    al.b = -60.0f;
                                    aj.a(al.a()).a((Animation)new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new am().a(-0.4f).b(0.3f).a());
                                    continue;
                                    final al al2 = new al();
                                    al2.a = true;
                                    al2.b = 60.0f;
                                    aj.a(al2.a()).a((Animation)new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new am().a(0.3f).b(-0.4f).a());
                                    continue;
                                    final al al3 = new al();
                                    al3.a = false;
                                    al3.b = 60.0f;
                                    aj.a(al3.a()).a((Animation)new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new am().a(1.0f).b(0.3f).a());
                                    continue;
                                    n = 1;
                                    continue Label_0288_Outer;
                                    final al al4 = new al();
                                    al4.a = true;
                                    al4.b = -60.0f;
                                    aj.a(al4.a()).a((Animation)new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).a(new am().a(0.3f).b(1.0f).a());
                                    continue;
                                }
                                catch (WindowManager$BadTokenException ex) {
                                    throw ex;
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private void e() {
        if (this.k != null) {
            this.k.dismiss();
        }
    }
    
    @Override
    public final void a(final gd gd, final ez ez) {
        final Activity a = com.tapjoy.internal.c.a(this.n);
        if (a != null && !a.isFinishing()) {
            try {
                this.a(a, gd, ez);
                final String a2 = this.a;
                return;
            }
            catch (WindowManager$BadTokenException ex) {}
        }
        final Activity a3 = fu.a();
        while (true) {
            Label_0161: {
                boolean b;
                if (a3 != null) {
                    if ((a3.getWindow().getAttributes().flags & 0x400) == 0x0) {
                        break Label_0161;
                    }
                    b = true;
                }
                else {
                    b = false;
                }
                final Context e = gc.a().e;
                try {
                    TJContentActivity.start(e, (TJContentActivity.ContentProducer)new TJContentActivity.AbstractContentProducer() {
                        @Override
                        public final void dismiss(final Activity activity) {
                            gh.this.e();
                        }
                        
                        @Override
                        public final void show(final Activity activity) {
                            try {
                                gh.this.a(activity, gd, ez);
                            }
                            catch (WindowManager$BadTokenException ex) {
                                fz.b("Failed to show the content for \"{}\" caused by invalid activity", gh.this.a);
                                gd.a(gh.this.a, gh.this.f, null);
                            }
                        }
                    }, b);
                    final String a4 = this.a;
                    return;
                }
                catch (ActivityNotFoundException ex2) {
                    if (a3 != null && !a3.isFinishing()) {
                        try {
                            this.a(a3, gd, ez);
                            final String a5 = this.a;
                            return;
                        }
                        catch (WindowManager$BadTokenException ex3) {}
                    }
                    fz.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.a);
                    gd.a(this.a, this.f, null);
                    return;
                }
            }
            boolean b = false;
            continue;
        }
    }
    
    @Override
    public final void b() {
        final gx b = this.b;
        if (b.a != null) {
            b.a.b();
        }
        if (b.b != null) {
            b.b.b();
        }
        b.c.b();
        if (b.e != null) {
            b.e.b();
        }
        if (b.f != null) {
            b.f.b();
        }
        if (b.m != null && b.m.a != null) {
            b.m.a.b();
        }
    }
    
    @Override
    public final boolean c() {
        final gx b = this.b;
        return b.c != null && b.c.b != null && (b.m == null || b.m.a == null || b.m.a.b != null) && ((b.b != null && b.f != null && b.b.b != null && b.f.b != null) || (b.a != null && b.e != null && b.a.b != null && b.e.b != null));
    }
}
