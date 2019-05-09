package com.tapjoy.mraid.controller;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.util.NetworkBroadcastReceiver;
import com.tapjoy.mraid.view.MraidView;

public class Network extends Abstract {
    /* renamed from: c */
    private ConnectivityManager f8273c;
    /* renamed from: d */
    private int f8274d;
    /* renamed from: e */
    private NetworkBroadcastReceiver f8275e;
    /* renamed from: f */
    private IntentFilter f8276f;

    /* renamed from: com.tapjoy.mraid.controller.Network$1 */
    static /* synthetic */ class C30071 {
        /* renamed from: a */
        static final /* synthetic */ int[] f8272a = new int[State.values().length];

        static {
            try {
                f8272a[State.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8272a[State.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public Network(MraidView adView, Context context) {
        super(adView, context);
        this.f8273c = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public String getNetwork() {
        String str;
        NetworkInfo networkInfo = null;
        try {
            networkInfo = this.f8273c.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str2 = "unknown";
        if (networkInfo != null) {
            switch (C30071.f8272a[networkInfo.getState().ordinal()]) {
                case 1:
                    str = "unknown";
                    break;
                case 2:
                    str = "offline";
                    break;
                default:
                    int type = networkInfo.getType();
                    if (type != 0) {
                        if (type != 1) {
                            str = str2;
                            break;
                        }
                        str = "wifi";
                        break;
                    }
                    str = "cell";
                    break;
            }
        }
        str = "offline";
        TapjoyLog.m7126d("MRAID Network", "getNetwork: " + str);
        return str;
    }

    public void startNetworkListener() {
        if (this.f8274d == 0) {
            this.f8275e = new NetworkBroadcastReceiver(this);
            this.f8276f = new IntentFilter();
            this.f8276f.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
        this.f8274d++;
        this.b.registerReceiver(this.f8275e, this.f8276f);
    }

    public void stopNetworkListener() {
        this.f8274d--;
        if (this.f8274d == 0) {
            this.b.unregisterReceiver(this.f8275e);
            this.f8275e = null;
            this.f8276f = null;
        }
    }

    public void onConnectionChanged() {
        String str = "window.mraidview.fireChangeEvent({ network: '" + getNetwork() + "'});";
        TapjoyLog.m7126d("MRAID Network", str);
        this.a.injectMraidJavaScript(str);
    }

    public void stopAllListeners() {
        this.f8274d = 0;
        try {
            this.b.unregisterReceiver(this.f8275e);
        } catch (Exception e) {
        }
    }
}
