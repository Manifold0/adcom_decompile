// 
// Decompiled by Procyon v0.5.34
// 

package com.google.games.bridge;

import android.view.View;
import android.os.Bundle;
import android.os.Parcelable;
import android.content.Context;
import android.util.Log;
import android.content.Intent;
import android.app.Activity;

public final class NativeBridgeActivity extends Activity
{
    private static final int BG_COLOR = 1090519039;
    private static final String BRIDGED_INTENT = "BRIDGED_INTENT";
    private static final int GPG_RESPONSE_CODE = 4673607;
    private static final String TAG = "NativeBridgeActivity";
    private boolean pendingResult;
    
    static {
        System.loadLibrary("gpg");
    }
    
    private native void forwardActivityResult(final int p0, final int p1, final Intent p2);
    
    public static void launchBridgeIntent(final Activity activity, final Intent intent) {
        Log.d("NativeBridgeActivity", "Launching bridge activity: parent:" + activity + " intent " + intent);
        final Intent intent2 = new Intent((Context)activity, (Class)NativeBridgeActivity.class);
        intent2.putExtra("BRIDGED_INTENT", (Parcelable)intent);
        activity.startActivity(intent2);
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n == 4673607) {
            Log.d("NativeBridgeActivity", "Forwarding activity result to native SDK.");
            this.forwardActivityResult(n, n2, intent);
            this.pendingResult = false;
        }
        else {
            Log.d("NativeBridgeActivity", "onActivityResult for unknown request code: " + n + " calling finish()");
        }
        this.finish();
        super.onActivityResult(n, n2, intent);
    }
    
    public void onCreate(final Bundle bundle) {
        final View contentView = new View((Context)this);
        contentView.setBackgroundColor(1090519039);
        this.setContentView(contentView);
        super.onCreate(bundle);
    }
    
    protected void onDestroy() {
        if (this.pendingResult) {
            Log.w("NativeBridgeActivity", "onDestroy called with pendingResult == true.  forwarding canceled result");
            this.forwardActivityResult(4673607, 0, null);
            this.pendingResult = false;
        }
        super.onDestroy();
    }
    
    protected void onStart() {
        final Intent intent = (Intent)this.getIntent().getParcelableExtra("BRIDGED_INTENT");
        if (intent != null) {
            this.startActivityForResult(intent, 4673607);
        }
        super.onStart();
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.pendingResult = (n == 4673607);
        if (this.pendingResult) {
            Log.d("NativeBridgeActivity", "starting GPG activity: " + intent);
        }
        else {
            Log.i("NativeBridgeActivity", "starting non-GPG activity: " + n + " " + intent);
        }
        super.startActivityForResult(intent, n);
    }
}
