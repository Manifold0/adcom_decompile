package com.google.games.bridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public final class NativeBridgeActivity extends Activity {
    private static final int BG_COLOR = 1090519039;
    private static final String BRIDGED_INTENT = "BRIDGED_INTENT";
    private static final int GPG_RESPONSE_CODE = 4673607;
    private static final String TAG = "NativeBridgeActivity";
    private boolean pendingResult;

    private native void forwardActivityResult(int i, int i2, Intent intent);

    static {
        System.loadLibrary("gpg");
    }

    public void onCreate(Bundle savedInstanceState) {
        View v = new View(this);
        v.setBackgroundColor(BG_COLOR);
        setContentView(v);
        super.onCreate(savedInstanceState);
    }

    protected void onStart() {
        Intent bridgedIntent = (Intent) getIntent().getParcelableExtra(BRIDGED_INTENT);
        if (bridgedIntent != null) {
            startActivityForResult(bridgedIntent, GPG_RESPONSE_CODE);
        }
        super.onStart();
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        this.pendingResult = requestCode == GPG_RESPONSE_CODE;
        if (this.pendingResult) {
            Log.d(TAG, "starting GPG activity: " + intent);
        } else {
            Log.i(TAG, "starting non-GPG activity: " + requestCode + " " + intent);
        }
        super.startActivityForResult(intent, requestCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GPG_RESPONSE_CODE) {
            Log.d(TAG, "Forwarding activity result to native SDK.");
            forwardActivityResult(requestCode, resultCode, data);
            this.pendingResult = false;
        } else {
            Log.d(TAG, "onActivityResult for unknown request code: " + requestCode + " calling finish()");
        }
        finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void launchBridgeIntent(Activity parentActivity, Intent intent) {
        Log.d(TAG, "Launching bridge activity: parent:" + parentActivity + " intent " + intent);
        Intent bridgeIntent = new Intent(parentActivity, NativeBridgeActivity.class);
        bridgeIntent.putExtra(BRIDGED_INTENT, intent);
        parentActivity.startActivity(bridgeIntent);
    }

    protected void onDestroy() {
        if (this.pendingResult) {
            Log.w(TAG, "onDestroy called with pendingResult == true.  forwarding canceled result");
            forwardActivityResult(GPG_RESPONSE_CODE, 0, null);
            this.pendingResult = false;
        }
        super.onDestroy();
    }
}
