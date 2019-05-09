package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Tracking.C1391a;
import org.json.JSONObject;

public class bg implements Runnable {
    /* renamed from: a */
    private final bd f3181a;
    /* renamed from: b */
    private final bf f3182b;
    /* renamed from: c */
    private final int f3183c;
    /* renamed from: d */
    private final JSONObject f3184d;
    /* renamed from: e */
    private final String f3185e;

    bg(bd bdVar, bf bfVar, int i, String str, JSONObject jSONObject) {
        this.f3181a = bdVar;
        this.f3182b = bfVar;
        this.f3183c = i;
        this.f3185e = str;
        this.f3184d = jSONObject;
    }

    public void run() {
        try {
            float f;
            String string;
            CharSequence string2;
            switch (this.f3183c) {
                case 0:
                    this.f3182b.m3304b(null);
                    return;
                case 1:
                    this.f3182b.mo4301h();
                    return;
                case 2:
                    try {
                        f = (float) this.f3184d.getDouble("duration");
                        CBLogging.m3097a("NativeBridgeCommand", "######### JS->Native Video current player duration" + (f * 1000.0f));
                        this.f3182b.m3490a(f * 1000.0f);
                        return;
                    } catch (Exception e) {
                        this.f3182b.m3503e("Parsing exception unknown field for current player duration");
                        CBLogging.m3099b("NativeBridgeCommand", "Cannot find duration parameter for the video");
                        return;
                    }
                case 3:
                    try {
                        string = this.f3184d.getString("message");
                        Log.d(be.class.getName(), "JS->Native Debug message: " + string);
                        this.f3182b.m3497c(string);
                        return;
                    } catch (Exception e2) {
                        CBLogging.m3099b("NativeBridgeCommand", "Exception occured while parsing the message for webview debug track event");
                        this.f3182b.m3497c("Exception occured while parsing the message for webview debug track event");
                        return;
                    }
                case 4:
                    try {
                        string = this.f3184d.getString("message");
                        Log.d(be.class.getName(), "JS->Native Error message: " + string);
                        this.f3182b.m3501d(string);
                        return;
                    } catch (Exception e3) {
                        CBLogging.m3099b("NativeBridgeCommand", "Error message is empty");
                        this.f3182b.m3501d("");
                        return;
                    }
                case 5:
                    try {
                        string = this.f3184d.getString("url");
                        if (!(string.startsWith("http://") || string.startsWith("https://"))) {
                            string = "http://" + string;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(string));
                        View y = this.f3182b.m3521y();
                        if (y != null) {
                            Context context = y.getContext();
                            if (context != null && intent.resolveActivity(context.getPackageManager()) != null) {
                                context.startActivity(intent);
                                CBLogging.m3097a(be.class.getName(), "JS->Native Track MRAID openUrl: " + string);
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Exception e4) {
                        C1391a.m3206a(getClass(), "ActivityNotFoundException occured when opening a url in a browser", e4);
                        CBLogging.m3099b("NativeBridgeCommand", "ActivityNotFoundException occured when opening a url in a browser");
                        return;
                    } catch (Exception e42) {
                        C1391a.m3206a(getClass(), "Exception while opening a browser view with MRAID url", e42);
                        CBLogging.m3099b("NativeBridgeCommand", "Exception while opening a browser view with MRAID url");
                        return;
                    }
                case 6:
                    this.f3182b.m3522z();
                    return;
                case 7:
                    try {
                        f = (float) this.f3184d.getDouble("duration");
                        CBLogging.m3097a("NativeBridgeCommand", "######### JS->Native Video total player duration" + (f * 1000.0f));
                        this.f3182b.m3493b(f * 1000.0f);
                        return;
                    } catch (Exception e5) {
                        this.f3182b.m3503e("Parsing exception unknown field for total player duration");
                        CBLogging.m3099b("NativeBridgeCommand", "Cannot find duration parameter for the video");
                        return;
                    }
                case 8:
                    try {
                        string = this.f3184d.getString("event");
                        this.f3182b.m3495b(string);
                        Log.d(be.class.getName(), "JS->Native Track VAST event message: " + string);
                        return;
                    } catch (Exception e6) {
                        CBLogging.m3099b("NativeBridgeCommand", "Exception occured while parsing the message for webview tracking VAST events");
                        return;
                    }
                case 9:
                    this.f3181a.onHideCustomView();
                    this.f3182b.m3494b(1);
                    this.f3182b.m3519w();
                    return;
                case 10:
                    try {
                        string2 = this.f3184d.getString("name");
                        if (!C1454s.m3627a().m3631a(string2)) {
                            this.f3182b.f3167m = string2;
                        }
                    } catch (Exception e7) {
                        CBLogging.m3099b("NativeBridgeCommand", "Cannot find video file name");
                        this.f3182b.m3503e("Parsing exception unknown field for video pause");
                    }
                    this.f3182b.m3494b(3);
                    return;
                case 11:
                    try {
                        string2 = this.f3184d.getString("name");
                        if (!C1454s.m3627a().m3631a(string2)) {
                            this.f3182b.f3167m = string2;
                        }
                    } catch (Exception e8) {
                        CBLogging.m3099b("NativeBridgeCommand", "Cannot find video file name");
                        this.f3182b.m3503e("Parsing exception unknown field for video play");
                    }
                    this.f3182b.m3494b(2);
                    return;
                case 12:
                    try {
                        string2 = this.f3184d.getString("name");
                        if (!C1454s.m3627a().m3631a(string2)) {
                            this.f3182b.f3167m = string2;
                        }
                        this.f3182b.m3520x();
                        return;
                    } catch (Exception e9) {
                        CBLogging.m3099b("NativeBridgeCommand", "Cannot find video file name");
                        this.f3182b.m3503e("Parsing exception unknown field for video replay");
                        return;
                    }
                case 13:
                    try {
                        string = this.f3184d.getString("message");
                        Log.d(be.class.getName(), "JS->Native Warning message: " + string);
                        this.f3182b.m3503e(string);
                        return;
                    } catch (Exception e10) {
                        CBLogging.m3099b("NativeBridgeCommand", "Warning message is empty");
                        this.f3182b.m3503e("");
                        return;
                    }
                case 14:
                    try {
                        this.f3182b.m3498c(this.f3184d);
                        return;
                    } catch (Exception e11) {
                        CBLogging.m3099b("NativeBridgeCommand", "Invalid set orientation command");
                        return;
                    }
                default:
                    return;
            }
        } catch (Exception e422) {
            C1391a.m3206a(getClass(), "run(" + this.f3185e + ")", e422);
        }
        C1391a.m3206a(getClass(), "run(" + this.f3185e + ")", e422);
    }
}
