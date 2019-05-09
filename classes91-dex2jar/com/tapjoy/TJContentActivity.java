// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.fq;
import com.tapjoy.internal.gc;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;

public class TJContentActivity extends Activity
{
    private static volatile ContentProducer a;
    private ContentProducer b;
    private boolean c;
    
    public TJContentActivity() {
        this.c = false;
    }
    
    private boolean a(final Intent intent) {
        final String stringExtra = intent.getStringExtra("com.tapjoy.internal.content.producer.id");
        if (stringExtra == null) {
            return false;
        }
        synchronized (TJContentActivity.class) {
            if (TJContentActivity.a != null && stringExtra.equals(toIdentityString(TJContentActivity.a))) {
                this.b = TJContentActivity.a;
                TJContentActivity.a = null;
                // monitorexit(TJContentActivity.class)
                if (intent.getBooleanExtra("com.tapjoy.internal.content.fullscreen", false)) {
                    this.getWindow().setFlags(1024, 1024);
                }
                this.b.show(this);
                return true;
            }
            return false;
        }
    }
    
    public static void start(final Context context, final ContentProducer a, final boolean b) {
        final Intent intent = new Intent(context, (Class)TJContentActivity.class);
        intent.setFlags(276889600);
        intent.putExtra("com.tapjoy.internal.content.producer.id", toIdentityString(a));
        intent.putExtra("com.tapjoy.internal.content.fullscreen", b);
        synchronized (TJContentActivity.class) {
            TJContentActivity.a = a;
            context.startActivity(intent);
        }
    }
    
    public static String toIdentityString(final Object o) {
        if (o == null) {
            return "null";
        }
        return o.getClass().getName() + System.identityHashCode(o);
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        this.b.onActivityResult(this, n, n2, intent);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        if (!this.a(this.getIntent())) {
            this.finish();
        }
    }
    
    protected void onDestroy() {
        if (this.b != null) {
            this.b.dismiss(this);
        }
        super.onDestroy();
    }
    
    protected void onStart() {
        super.onStart();
        if (gc.a().n) {
            this.c = true;
            fq.a(this);
        }
    }
    
    protected void onStop() {
        if (this.c) {
            this.c = false;
            fq.b(this);
        }
        super.onStop();
    }
    
    public abstract static class AbstractContentProducer implements ContentProducer
    {
        @Override
        public void dismiss(final Activity activity) {
        }
        
        @Override
        public void onActivityResult(final Activity activity, final int n, final int n2, final Intent intent) {
        }
    }
    
    public interface ContentProducer
    {
        void dismiss(final Activity p0);
        
        void onActivityResult(final Activity p0, final int p1, final int p2, final Intent p3);
        
        void show(final Activity p0);
    }
}
