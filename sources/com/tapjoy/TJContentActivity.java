package com.tapjoy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.gc;

public class TJContentActivity extends Activity {
    /* renamed from: a */
    private static volatile ContentProducer f6895a;
    /* renamed from: b */
    private ContentProducer f6896b;
    /* renamed from: c */
    private boolean f6897c = false;

    public interface ContentProducer {
        void dismiss(Activity activity);

        void onActivityResult(Activity activity, int i, int i2, Intent intent);

        void show(Activity activity);
    }

    public static abstract class AbstractContentProducer implements ContentProducer {
        public void dismiss(Activity activity) {
        }

        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        }
    }

    public static void start(Context context, ContentProducer producer, boolean fullscreen) {
        Intent intent = new Intent(context, TJContentActivity.class);
        intent.setFlags(276889600);
        intent.putExtra("com.tapjoy.internal.content.producer.id", toIdentityString(producer));
        intent.putExtra("com.tapjoy.internal.content.fullscreen", fullscreen);
        synchronized (TJContentActivity.class) {
            f6895a = producer;
            context.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if (!m7034a(getIntent())) {
            finish();
        }
    }

    protected void onDestroy() {
        if (this.f6896b != null) {
            this.f6896b.dismiss(this);
        }
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
        if (gc.m7831a().f7862n) {
            this.f6897c = true;
            fq.m7775a(this);
        }
    }

    protected void onStop() {
        if (this.f6897c) {
            this.f6897c = false;
            fq.m7780b(this);
        }
        super.onStop();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.f6896b.onActivityResult(this, requestCode, resultCode, data);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private boolean m7034a(android.content.Intent r6) {
        /*
        r5 = this;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = 0;
        r1 = "com.tapjoy.internal.content.producer.id";
        r1 = r6.getStringExtra(r1);
        if (r1 != 0) goto L_0x000c;
    L_0x000b:
        return r0;
    L_0x000c:
        r2 = com.tapjoy.TJContentActivity.class;
        monitor-enter(r2);
        r3 = f6895a;	 Catch:{ all -> 0x003f }
        if (r3 == 0) goto L_0x003d;
    L_0x0013:
        r3 = f6895a;	 Catch:{ all -> 0x003f }
        r3 = toIdentityString(r3);	 Catch:{ all -> 0x003f }
        r1 = r1.equals(r3);	 Catch:{ all -> 0x003f }
        if (r1 == 0) goto L_0x003d;
    L_0x001f:
        r1 = f6895a;	 Catch:{ all -> 0x003f }
        r5.f6896b = r1;	 Catch:{ all -> 0x003f }
        r1 = 0;
        f6895a = r1;	 Catch:{ all -> 0x003f }
        monitor-exit(r2);	 Catch:{ all -> 0x003f }
        r1 = "com.tapjoy.internal.content.fullscreen";
        r0 = r6.getBooleanExtra(r1, r0);
        if (r0 == 0) goto L_0x0036;
    L_0x002f:
        r0 = r5.getWindow();
        r0.setFlags(r4, r4);
    L_0x0036:
        r0 = r5.f6896b;
        r0.show(r5);
        r0 = 1;
        goto L_0x000b;
    L_0x003d:
        monitor-exit(r2);	 Catch:{ all -> 0x003f }
        goto L_0x000b;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJContentActivity.a(android.content.Intent):boolean");
    }

    public static String toIdentityString(Object object) {
        if (object == null) {
            return "null";
        }
        return object.getClass().getName() + System.identityHashCode(object);
    }
}
