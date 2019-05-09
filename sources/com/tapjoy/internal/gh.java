package com.tapjoy.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.BadTokenException;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.tapjoy.TJContentActivity;
import com.tapjoy.TJContentActivity.AbstractContentProducer;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.ai.C28371;
import com.tapjoy.internal.ai.C2838a;
import com.tapjoy.internal.ht.C2952a;

public class gh extends gj {
    /* renamed from: h */
    private static final String f7910h = gh.class.getSimpleName();
    /* renamed from: i */
    private static gh f7911i;
    /* renamed from: a */
    final String f7912a;
    /* renamed from: b */
    final gx f7913b;
    /* renamed from: j */
    private final gc f7914j;
    /* renamed from: k */
    private C2883e f7915k;
    /* renamed from: l */
    private boolean f7916l;
    /* renamed from: m */
    private long f7917m;
    /* renamed from: n */
    private Context f7918n;
    /* renamed from: o */
    private boolean f7919o = false;

    /* renamed from: a */
    public static void m7949a() {
        gh ghVar = f7911i;
        if (ghVar != null) {
            ghVar.m7959e();
        }
    }

    public gh(gc gcVar, String str, gx gxVar, Context context) {
        this.f7914j = gcVar;
        this.f7912a = str;
        this.f7913b = gxVar;
        this.f7918n = context;
    }

    /* renamed from: b */
    public final void mo6291b() {
        gx gxVar = this.f7913b;
        if (gxVar.f8005a != null) {
            gxVar.f8005a.m8020b();
        }
        if (gxVar.f8006b != null) {
            gxVar.f8006b.m8020b();
        }
        gxVar.f8007c.m8020b();
        if (gxVar.f8009e != null) {
            gxVar.f8009e.m8020b();
        }
        if (gxVar.f8010f != null) {
            gxVar.f8010f.m8020b();
        }
        if (gxVar.f8017m != null && gxVar.f8017m.f8019a != null) {
            gxVar.f8017m.f8019a.m8020b();
        }
    }

    /* renamed from: c */
    public final boolean mo6292c() {
        gx gxVar = this.f7913b;
        return (gxVar.f8007c == null || gxVar.f8007c.f8035b == null || ((gxVar.f8017m != null && gxVar.f8017m.f8019a != null && gxVar.f8017m.f8019a.f8035b == null) || ((gxVar.f8006b == null || gxVar.f8010f == null || gxVar.f8006b.f8035b == null || gxVar.f8010f.f8035b == null) && (gxVar.f8005a == null || gxVar.f8009e == null || gxVar.f8005a.f8035b == null || gxVar.f8009e.f8035b == null)))) ? false : true;
    }

    /* renamed from: a */
    public final void mo6290a(final gd gdVar, final ez ezVar) {
        Activity a = C2850c.m7313a(this.f7918n);
        if (!(a == null || a.isFinishing())) {
            try {
                m7950a(a, gdVar, ezVar);
                new Object[1][0] = this.f7912a;
                return;
            } catch (BadTokenException e) {
            }
        }
        Activity a2 = fu.m7788a();
        boolean z = a2 != null ? (a2.getWindow().getAttributes().flags & 1024) != 0 : false;
        try {
            TJContentActivity.start(gc.m7831a().f7853e, new AbstractContentProducer(this) {
                /* renamed from: c */
                final /* synthetic */ gh f7901c;

                public final void show(Activity activity) {
                    try {
                        this.f7901c.m7950a(activity, gdVar, ezVar);
                    } catch (BadTokenException e) {
                        fz.m7817b("Failed to show the content for \"{}\" caused by invalid activity", this.f7901c.f7912a);
                        gdVar.mo6138a(this.f7901c.f7912a, this.f7901c.f, null);
                    }
                }

                public final void dismiss(Activity activity) {
                    this.f7901c.m7959e();
                }
            }, z);
            new Object[1][0] = this.f7912a;
        } catch (ActivityNotFoundException e2) {
            if (!(a2 == null || a2.isFinishing())) {
                try {
                    m7950a(a2, gdVar, ezVar);
                    new Object[1][0] = this.f7912a;
                    return;
                } catch (BadTokenException e3) {
                    fz.m7817b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.f7912a);
                    gdVar.mo6138a(this.f7912a, this.f, null);
                }
            }
            fz.m7817b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.f7912a);
            gdVar.mo6138a(this.f7912a, this.f, null);
        }
    }

    /* renamed from: a */
    private void m7950a(final Activity activity, final gd gdVar, ez ezVar) {
        if (this.f7916l) {
            TapjoyLog.m7127e(f7910h, new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Content is already displayed"));
            return;
        }
        this.f7916l = true;
        f7911i = this;
        this.g = ezVar.f7707a;
        this.f7915k = new C2883e(activity);
        this.f7915k.setOnCancelListener(new OnCancelListener(this) {
            /* renamed from: b */
            final /* synthetic */ gh f7903b;

            public final void onCancel(DialogInterface dialog) {
                gdVar.mo6141d(this.f7903b.f7912a);
            }
        });
        this.f7915k.setOnDismissListener(new OnDismissListener(this) {
            /* renamed from: c */
            final /* synthetic */ gh f7906c;

            public final void onDismiss(DialogInterface dialog) {
                gh.f7911i = null;
                gj.m7800a(activity, this.f7906c.f7913b.f8011g);
                this.f7906c.f7914j.m7842a(this.f7906c.f7913b.f8015k, SystemClock.elapsedRealtime() - this.f7906c.f7917m);
                if (!this.f7906c.d) {
                    gdVar.mo6138a(this.f7906c.f7912a, this.f7906c.f, this.f7906c.f7913b.f8012h);
                }
                if (this.f7906c.f7919o && this.f7906c.f7913b.f8015k != null && this.f7906c.f7913b.f8015k.containsKey("action_id")) {
                    String obj = this.f7906c.f7913b.f8015k.get("action_id").toString();
                    if (obj != null && obj.length() > 0) {
                        gc c = this.f7906c.f7914j;
                        if (c.f7850b != null) {
                            Object obj2;
                            gl glVar = c.f7850b;
                            String a = gl.m7969a();
                            String a2 = glVar.f7925b.m8219a();
                            String a3 = glVar.f7924a.m8219a();
                            if (a3 == null || !a.equals(a3)) {
                                glVar.f7924a.m8220a(a);
                                a2 = "";
                            }
                            if (a2.length() == 0) {
                                obj2 = 1;
                            } else {
                                obj2 = null;
                            }
                            if (obj2 != null) {
                                a2 = obj;
                            } else if (!a2.contains(obj)) {
                                a2 = a2.concat("," + obj);
                            }
                            glVar.f7925b.m8220a(a2);
                        }
                    }
                }
                if (activity instanceof TJContentActivity) {
                    activity.finish();
                }
            }
        });
        this.f7915k.setCanceledOnTouchOutside(false);
        View hsVar = new hs(activity, this.f7913b, new ht(activity, this.f7913b, new C2952a(this) {
            /* renamed from: c */
            final /* synthetic */ gh f7909c;

            /* renamed from: a */
            public final void mo6318a() {
                this.f7909c.f7915k.cancel();
            }

            /* renamed from: a */
            public final void mo6319a(gv gvVar) {
                if (this.f7909c.g instanceof ex) {
                    ex exVar = (ex) this.f7909c.g;
                    if (!(exVar == null || exVar.f7705c == null)) {
                        exVar.f7705c.m7661a();
                    }
                }
                this.f7909c.f7914j.m7843a(this.f7909c.f7913b.f8015k, gvVar.f7986b);
                gj.m7800a(activity, gvVar.f7988d);
                if (!ct.m7339c(gvVar.f7989e)) {
                    this.f7909c.e.mo6163a(activity, gvVar.f7989e, ct.m7338b(gvVar.f7990f));
                    this.f7909c.d = true;
                }
                gdVar.mo6137a(this.f7909c.f7912a, gvVar.f7991g);
                if (gvVar.f7987c) {
                    this.f7909c.f7915k.dismiss();
                }
            }

            /* renamed from: b */
            public final void mo6320b() {
                this.f7909c.f7919o = !this.f7909c.f7919o;
            }
        }));
        View frameLayout = new FrameLayout(activity);
        frameLayout.addView(hsVar, new LayoutParams(-2, -2, 17));
        this.f7915k.setContentView(frameLayout);
        if (Boolean.FALSE.booleanValue()) {
            boolean z;
            int i;
            aj ajVar;
            al alVar;
            Window window = this.f7915k.getWindow();
            if (VERSION.SDK_INT == 16 && "4.1.2".equals(VERSION.RELEASE)) {
                if (Boolean.FALSE.equals(m7948a(window.getContext()))) {
                    z = false;
                    if (z) {
                        i = C2838a.f7169b;
                        ajVar = new aj();
                        switch (C28371.f7167a[i - 1]) {
                            case 1:
                                alVar = new al();
                                alVar.f7185a = false;
                                alVar.f7186b = 60.0f;
                                ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(1.0f).m7168b(0.3f).m7166a());
                                break;
                            case 2:
                                alVar = new al();
                                alVar.f7185a = false;
                                alVar.f7186b = -60.0f;
                                ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(-0.4f).m7168b(0.3f).m7166a());
                                break;
                            case 3:
                                alVar = new al();
                                alVar.f7185a = true;
                                alVar.f7186b = -60.0f;
                                ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(0.3f).m7168b(1.0f).m7166a());
                                break;
                            case 4:
                                alVar = new al();
                                alVar.f7185a = true;
                                alVar.f7186b = 60.0f;
                                ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(0.3f).m7168b(-0.4f).m7166a());
                                break;
                        }
                        hsVar.startAnimation(ajVar.m7162b().mo6175a());
                    }
                } else {
                    window.setFlags(16777216, 16777216);
                }
            }
            z = true;
            if (z) {
                i = C2838a.f7169b;
                ajVar = new aj();
                switch (C28371.f7167a[i - 1]) {
                    case 1:
                        alVar = new al();
                        alVar.f7185a = false;
                        alVar.f7186b = 60.0f;
                        ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(1.0f).m7168b(0.3f).m7166a());
                        break;
                    case 2:
                        alVar = new al();
                        alVar.f7185a = false;
                        alVar.f7186b = -60.0f;
                        ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(-0.4f).m7168b(0.3f).m7166a());
                        break;
                    case 3:
                        alVar = new al();
                        alVar.f7185a = true;
                        alVar.f7186b = -60.0f;
                        ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(0.3f).m7168b(1.0f).m7166a());
                        break;
                    case 4:
                        alVar = new al();
                        alVar.f7185a = true;
                        alVar.f7186b = 60.0f;
                        ajVar.m7164a(alVar.m7165a()).m7164a(new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f)).m7164a(new am().m7167a(0.3f).m7168b(-0.4f).m7166a());
                        break;
                }
                hsVar.startAnimation(ajVar.m7162b().mo6175a());
            }
        }
        try {
            this.f7915k.show();
            this.f7915k.getWindow().setLayout(-1, -1);
            if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
                this.f7915k.getWindow().setFlags(1024, 1024);
            }
            this.f7917m = SystemClock.elapsedRealtime();
            this.f7914j.m7841a(this.f7913b.f8015k);
            ezVar.m7689a();
            et etVar = this.g;
            if (etVar != null) {
                etVar.m7664b();
            }
            gdVar.mo6140c(this.f7912a);
        } catch (BadTokenException e) {
            throw e;
        }
    }

    /* renamed from: e */
    private void m7959e() {
        if (this.f7915k != null) {
            this.f7915k.dismiss();
        }
    }

    /* renamed from: a */
    private static Boolean m7948a(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                Object obj = bundle.get("tapjoy:hardwareAccelerated");
                if (obj instanceof Boolean) {
                    return (Boolean) obj;
                }
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }
}
