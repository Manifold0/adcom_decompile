package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import com.adjust.sdk.Constants;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Tracking.C1391a;
import com.google.android.gms.drive.DriveFile;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executor;

public class ak {
    /* renamed from: a */
    final ai f3022a;
    /* renamed from: b */
    final Handler f3023b;
    /* renamed from: c */
    private final Executor f3024c;
    /* renamed from: d */
    private final ah f3025d;

    public ak(Executor executor, ah ahVar, ai aiVar, Handler handler) {
        this.f3024c = executor;
        this.f3025d = ahVar;
        this.f3022a = aiVar;
        this.f3023b = handler;
    }

    /* renamed from: a */
    public void m3396a(C1388c c1388c, boolean z, String str, CBClickError cBClickError, aj ajVar) {
        if (c1388c != null) {
            c1388c.f2778x = false;
            if (c1388c.m3176b()) {
                c1388c.f2766l = 4;
            }
        }
        if (z) {
            if (c1388c != null && c1388c.f2777w != null) {
                this.f3025d.m3371a(c1388c.f2777w);
            } else if (ajVar != null) {
                this.f3025d.m3371a(ajVar);
            }
        } else if (C1410i.f2926c != null) {
            C1410i.f2926c.didFailToRecordClick(str, cBClickError);
        }
    }

    /* renamed from: a */
    public void m3393a(C1388c c1388c, String str, Activity activity, aj ajVar) {
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                m3396a(c1388c, false, str, CBClickError.URI_INVALID, ajVar);
            } else if (scheme.equals("http") || scheme.equals(Constants.SCHEME)) {
                final String str2 = str;
                final C1388c c1388c2 = c1388c;
                final Activity activity2 = activity;
                final aj ajVar2 = ajVar;
                this.f3024c.execute(new Runnable(this) {
                    /* renamed from: e */
                    final /* synthetic */ ak f3021e;

                    public void run() {
                        HttpURLConnection httpURLConnection;
                        Throwable e;
                        String str;
                        Throwable th;
                        try {
                            String str2 = str2;
                            if (this.f3021e.f3022a.m3378b()) {
                                HttpURLConnection httpURLConnection2 = null;
                                try {
                                    httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                                } catch (Exception e2) {
                                    e = e2;
                                    try {
                                        CBLogging.m3098a("CBURLOpener", "Exception raised while opening a HTTP Conection", e);
                                        if (httpURLConnection2 != null) {
                                            httpURLConnection2.disconnect();
                                            str = str2;
                                            m3392a(str);
                                        }
                                        str = str2;
                                        m3392a(str);
                                    } catch (Throwable th2) {
                                        e = th2;
                                        if (httpURLConnection2 != null) {
                                            httpURLConnection2.disconnect();
                                        }
                                        throw e;
                                    }
                                }
                                try {
                                    httpURLConnection.setInstanceFollowRedirects(false);
                                    httpURLConnection.setConnectTimeout(10000);
                                    httpURLConnection.setReadTimeout(10000);
                                    String headerField = httpURLConnection.getHeaderField("Location");
                                    if (headerField == null) {
                                        headerField = str2;
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                        str = headerField;
                                    } else {
                                        str = headerField;
                                    }
                                } catch (Throwable e3) {
                                    th = e3;
                                    httpURLConnection2 = httpURLConnection;
                                    e = th;
                                    CBLogging.m3098a("CBURLOpener", "Exception raised while opening a HTTP Conection", e);
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                        str = str2;
                                        m3392a(str);
                                    }
                                    str = str2;
                                    m3392a(str);
                                } catch (Throwable e32) {
                                    th = e32;
                                    httpURLConnection2 = httpURLConnection;
                                    e = th;
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    throw e;
                                }
                                m3392a(str);
                            }
                            str = str2;
                            m3392a(str);
                        } catch (Exception e4) {
                            C1391a.m3206a(ak.class, "open followTask", e4);
                        }
                    }

                    /* renamed from: a */
                    private void m3392a(final String str) {
                        Runnable c14191 = new Runnable(this) {
                            /* renamed from: b */
                            final /* synthetic */ C14201 f3016b;

                            public void run() {
                                try {
                                    this.f3016b.f3021e.m3394a(c1388c2, str, activity2, ajVar2);
                                } catch (Exception e) {
                                    C1391a.m3206a(ak.class, "open openOnUiThread Runnable.run", e);
                                }
                            }
                        };
                        if (activity2 != null) {
                            activity2.runOnUiThread(c14191);
                        } else {
                            this.f3021e.f3023b.post(c14191);
                        }
                    }
                });
            } else {
                m3394a(c1388c, str, (Context) activity, ajVar);
            }
        } catch (URISyntaxException e) {
            m3396a(c1388c, false, str, CBClickError.URI_INVALID, ajVar);
        }
    }

    /* renamed from: a */
    void m3394a(C1388c c1388c, String str, Context context, aj ajVar) {
        Intent intent;
        if (c1388c != null && c1388c.m3176b()) {
            c1388c.f2766l = 5;
        }
        if (context == null) {
            context = C1410i.f2936m;
        }
        if (context == null) {
            m3396a(c1388c, false, str, CBClickError.NO_HOST_ACTIVITY, ajVar);
            return;
        }
        String str2;
        try {
            intent = new Intent("android.intent.action.VIEW");
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
            str2 = str;
        } catch (Exception e) {
            if (str.startsWith("market://")) {
                try {
                    str = "http://market.android.com/" + str.substring(9);
                    intent = new Intent("android.intent.action.VIEW");
                    if (!(context instanceof Activity)) {
                        intent.addFlags(DriveFile.MODE_READ_ONLY);
                    }
                    intent.setData(Uri.parse(str));
                    context.startActivity(intent);
                    str2 = str;
                } catch (Throwable e2) {
                    str2 = str;
                    CBLogging.m3098a("CBURLOpener", "Exception raised openeing an inavld playstore URL", e2);
                    m3396a(c1388c, false, str2, CBClickError.URI_UNRECOGNIZED, ajVar);
                    return;
                }
            }
            m3396a(c1388c, false, str, CBClickError.URI_UNRECOGNIZED, ajVar);
            str2 = str;
        }
        m3396a(c1388c, true, str2, null, ajVar);
    }

    /* renamed from: a */
    public boolean m3397a(String str) {
        try {
            Context context = C1410i.f2936m;
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            intent.setData(Uri.parse(str));
            if (context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            CBLogging.m3098a("CBURLOpener", "Cannot open URL", e);
            C1391a.m3206a(ak.class, "canOpenURL", e);
            return false;
        }
    }

    /* renamed from: a */
    public void m3395a(C1388c c1388c, String str, aj ajVar) {
        m3393a(c1388c, str, c1388c != null ? c1388c.f2761g.m3247a() : null, ajVar);
    }
}
