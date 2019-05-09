package com.facebook.ads.internal.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.p036e.C1992a;
import com.facebook.ads.internal.p045n.C2051a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.AdInternalSettings;

@UiThread
public class AdsMessengerService extends Service {
    /* renamed from: a */
    private Messenger f4476a;
    /* renamed from: b */
    private boolean f4477b;
    /* renamed from: c */
    private final ServiceConnection f4478c = new C20191(this);

    /* renamed from: com.facebook.ads.internal.ipc.AdsMessengerService$1 */
    class C20191 implements ServiceConnection {
        /* renamed from: a */
        final /* synthetic */ AdsMessengerService f4474a;

        C20191(AdsMessengerService adsMessengerService) {
            this.f4474a = adsMessengerService;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f4474a.f4477b = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f4474a.f4477b = false;
            this.f4474a.unbindService(this.f4474a.f4478c);
        }
    }

    @VisibleForTesting
    /* renamed from: com.facebook.ads.internal.ipc.AdsMessengerService$a */
    public static class C2020a extends Handler {
        /* renamed from: a */
        private final C2021a f4475a;

        private C2020a(Context context) {
            this.f4475a = new C2021a(context);
        }

        public void handleMessage(Message message) {
            String string = message.getData().getString("STR_AD_ID_KEY");
            switch (message.what) {
                case 1:
                    C1992a.m4771a().m4776a(string, message.replyTo);
                    return;
                case 2:
                    C1992a.m4771a().m4780d(string);
                    return;
                default:
                    if (!this.f4475a.m4874a(message)) {
                        super.handleMessage(message);
                        return;
                    }
                    return;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return this.f4476a.getBinder();
    }

    public void onCreate() {
        AdInternalSettings.f4779d = true;
        C2051a.m4996a(this);
        C2051a.m4997b(this);
        this.f4476a = new Messenger(new C2020a(getApplicationContext()));
        if (C2078a.m5086Y(getApplicationContext())) {
            bindService(new Intent(getApplicationContext(), AdsProcessPriorityService.class), this.f4478c, 1);
        }
    }

    public void onDestroy() {
        C1992a.m4771a().m4777b();
        if (this.f4477b) {
            unbindService(this.f4478c);
        }
    }
}
