package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PurchaseActivity extends Activity {
    protected static final String TAG = "UnityIAP";
    private boolean receivedResult;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Creating purchase activity");
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("vr", false)) {
            View decorView = getWindow().getDecorView();
            if (VERSION.SDK_INT >= 11) {
                decorView.setSystemUiVisibility(6);
            }
        }
        String productId = getIntent().getExtras().getString("productId");
        String developerPayload = getIntent().getExtras().getString("developerPayload");
        String purchaseType = getIntent().getExtras().getString("type");
        String oldSkuMetadata = getIntent().getExtras().getString("oldSkuMetadata");
        String newSku = getIntent().getExtras().getString("newSku");
        if (purchaseType == null || !purchaseType.equals("subscription_update")) {
            if (!GooglePlayPurchasing.ContinuePurchase(this, productId, developerPayload)) {
                setResult(0);
                finish();
            }
        } else if (!GooglePlayPurchasing.ContinueSubscriptionUpdate(this, oldSkuMetadata, newSku)) {
            setResult(0);
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        processActivityResult(requestCode, resultCode, data);
        this.receivedResult = true;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!this.receivedResult) {
            processActivityResult(GooglePlayPurchasing.ACTIVITY_REQUEST_CODE, 0, null);
        }
    }

    public void processActivityResult(int requestCode, int resultCode, Intent data) {
        GooglePlayPurchasing.ProcessActivityResult(requestCode, resultCode, data);
        finish();
    }
}
