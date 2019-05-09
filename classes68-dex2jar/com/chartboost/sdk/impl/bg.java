// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.view.View;
import android.content.Context;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Tracking.a;
import org.json.JSONObject;

public class bg implements Runnable
{
    private final bd a;
    private final bf b;
    private final int c;
    private final JSONObject d;
    private final String e;
    
    bg(final bd a, final bf b, final int c, final String e, final JSONObject d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.e = e;
        this.d = d;
    }
    
    @Override
    public void run() {
        Label_0897: {
            Label_0832: {
                Label_0778: {
                    Label_0721: {
                        Label_0664: {
                            Label_0585: {
                                Label_0517: {
                                    Label_0333: {
                                        Label_0268: {
                                            Label_0203: {
                                                Label_0135: {
                                                    try {
                                                        switch (this.c) {
                                                            case 0: {
                                                                this.b.b((JSONObject)null);
                                                                return;
                                                            }
                                                            case 1: {
                                                                break;
                                                            }
                                                            case 2: {
                                                                break Label_0135;
                                                            }
                                                            case 3: {
                                                                break Label_0203;
                                                            }
                                                            case 4: {
                                                                break Label_0268;
                                                            }
                                                            case 5: {
                                                                break Label_0333;
                                                            }
                                                            case 6: {
                                                                break Label_0333;
                                                            }
                                                            case 7: {
                                                                break Label_0517;
                                                            }
                                                            case 8: {
                                                                break Label_0585;
                                                            }
                                                            case 9: {
                                                                break Label_0585;
                                                            }
                                                            case 10: {
                                                                break Label_0664;
                                                            }
                                                            case 11: {
                                                                break Label_0721;
                                                            }
                                                            case 12: {
                                                                break Label_0778;
                                                            }
                                                            case 13: {
                                                                break Label_0832;
                                                            }
                                                            case 14: {
                                                                break Label_0897;
                                                            }
                                                            default: {
                                                                return;
                                                            }
                                                        }
                                                    }
                                                    catch (Exception ex) {
                                                        com.chartboost.sdk.Tracking.a.a(this.getClass(), "run(" + this.e + ")", ex);
                                                        return;
                                                    }
                                                    this.b.h();
                                                    return;
                                                    try {
                                                        final float n = (float)this.d.getDouble("duration");
                                                        CBLogging.a("NativeBridgeCommand", "######### JS->Native Video current player duration" + n * 1000.0f);
                                                        this.b.a(n * 1000.0f);
                                                        return;
                                                    }
                                                    catch (Exception ex4) {
                                                        this.b.e("Parsing exception unknown field for current player duration");
                                                        CBLogging.b("NativeBridgeCommand", "Cannot find duration parameter for the video");
                                                        return;
                                                    }
                                                }
                                                try {
                                                    final String string = this.d.getString("message");
                                                    Log.d(be.class.getName(), "JS->Native Debug message: " + string);
                                                    this.b.c(string);
                                                    return;
                                                }
                                                catch (Exception ex5) {
                                                    CBLogging.b("NativeBridgeCommand", "Exception occured while parsing the message for webview debug track event");
                                                    this.b.c("Exception occured while parsing the message for webview debug track event");
                                                    return;
                                                }
                                            }
                                            try {
                                                final String string2 = this.d.getString("message");
                                                Log.d(be.class.getName(), "JS->Native Error message: " + string2);
                                                this.b.d(string2);
                                                return;
                                            }
                                            catch (Exception ex6) {
                                                CBLogging.b("NativeBridgeCommand", "Error message is empty");
                                                this.b.d("");
                                                return;
                                            }
                                        }
                                        try {
                                            String s2;
                                            final String s = s2 = this.d.getString("url");
                                            if (!s.startsWith("http://")) {
                                                s2 = s;
                                                if (!s.startsWith("https://")) {
                                                    s2 = "http://" + s;
                                                }
                                            }
                                            final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s2));
                                            final bf.b y = this.b.y();
                                            if (y == null) {
                                                return;
                                            }
                                            final Context context = ((View)y).getContext();
                                            if (context != null && intent.resolveActivity(context.getPackageManager()) != null) {
                                                context.startActivity(intent);
                                                CBLogging.a(be.class.getName(), "JS->Native Track MRAID openUrl: " + s2);
                                            }
                                            return;
                                        }
                                        catch (ActivityNotFoundException ex2) {
                                            com.chartboost.sdk.Tracking.a.a(this.getClass(), "ActivityNotFoundException occured when opening a url in a browser", (Exception)ex2);
                                            CBLogging.b("NativeBridgeCommand", "ActivityNotFoundException occured when opening a url in a browser");
                                            return;
                                        }
                                        catch (Exception ex3) {
                                            com.chartboost.sdk.Tracking.a.a(this.getClass(), "Exception while opening a browser view with MRAID url", ex3);
                                            CBLogging.b("NativeBridgeCommand", "Exception while opening a browser view with MRAID url");
                                            return;
                                        }
                                    }
                                    this.b.z();
                                    return;
                                    try {
                                        final float n2 = (float)this.d.getDouble("duration");
                                        CBLogging.a("NativeBridgeCommand", "######### JS->Native Video total player duration" + n2 * 1000.0f);
                                        this.b.b(n2 * 1000.0f);
                                        return;
                                    }
                                    catch (Exception ex7) {
                                        this.b.e("Parsing exception unknown field for total player duration");
                                        CBLogging.b("NativeBridgeCommand", "Cannot find duration parameter for the video");
                                        return;
                                    }
                                }
                                try {
                                    final String string3 = this.d.getString("event");
                                    this.b.b(string3);
                                    Log.d(be.class.getName(), "JS->Native Track VAST event message: " + string3);
                                    return;
                                }
                                catch (Exception ex8) {
                                    CBLogging.b("NativeBridgeCommand", "Exception occured while parsing the message for webview tracking VAST events");
                                    return;
                                }
                            }
                            this.a.onHideCustomView();
                            this.b.b(1);
                            this.b.w();
                            return;
                            while (true) {
                                try {
                                    final String string4 = this.d.getString("name");
                                    if (!s.a().a(string4)) {
                                        this.b.m = string4;
                                    }
                                    this.b.b(3);
                                    return;
                                }
                                catch (Exception ex9) {
                                    CBLogging.b("NativeBridgeCommand", "Cannot find video file name");
                                    this.b.e("Parsing exception unknown field for video pause");
                                    continue;
                                }
                                break;
                            }
                        }
                        while (true) {
                            try {
                                final String string5 = this.d.getString("name");
                                if (!s.a().a(string5)) {
                                    this.b.m = string5;
                                }
                                this.b.b(2);
                                return;
                            }
                            catch (Exception ex10) {
                                CBLogging.b("NativeBridgeCommand", "Cannot find video file name");
                                this.b.e("Parsing exception unknown field for video play");
                                continue;
                            }
                            break;
                        }
                    }
                    try {
                        final String string6 = this.d.getString("name");
                        if (!s.a().a(string6)) {
                            this.b.m = string6;
                        }
                        this.b.x();
                        return;
                    }
                    catch (Exception ex11) {
                        CBLogging.b("NativeBridgeCommand", "Cannot find video file name");
                        this.b.e("Parsing exception unknown field for video replay");
                        return;
                    }
                }
                try {
                    final String string7 = this.d.getString("message");
                    Log.d(be.class.getName(), "JS->Native Warning message: " + string7);
                    this.b.e(string7);
                    return;
                }
                catch (Exception ex12) {
                    CBLogging.b("NativeBridgeCommand", "Warning message is empty");
                    this.b.e("");
                    return;
                }
            }
            try {
                this.b.c(this.d);
            }
            catch (Exception ex13) {
                CBLogging.b("NativeBridgeCommand", "Invalid set orientation command");
            }
        }
    }
}
