// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import android.view.View;
import android.os.Build$VERSION;
import android.util.Log;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;

public class PurchaseActivity extends Activity
{
    protected static final String TAG = "UnityIAP";
    private boolean receivedResult;
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.processActivityResult(n, n2, intent);
        this.receivedResult = true;
    }
    
    public void onCreate(Bundle extras) {
        super.onCreate(extras);
        Log.i("UnityIAP", "Creating purchase activity");
        extras = this.getIntent().getExtras();
        if (extras != null && extras.getBoolean("vr", false)) {
            final View decorView = this.getWindow().getDecorView();
            if (Build$VERSION.SDK_INT >= 11) {
                decorView.setSystemUiVisibility(6);
            }
        }
        final String string = this.getIntent().getExtras().getString("productId");
        final String string2 = this.getIntent().getExtras().getString("developerPayload");
        final String string3 = this.getIntent().getExtras().getString("type");
        final String string4 = this.getIntent().getExtras().getString("oldSkuMetadata");
        final String string5 = this.getIntent().getExtras().getString("newSku");
        if (string3 != null && string3.equals("subscription_update")) {
            if (!GooglePlayPurchasing.ContinueSubscriptionUpdate(this, string4, string5)) {
                this.setResult(0);
                this.finish();
            }
        }
        else if (!GooglePlayPurchasing.ContinuePurchase(this, string, string2)) {
            this.setResult(0);
            this.finish();
        }
    }
    
    protected void onDestroy() {
        super.onDestroy();
        if (!this.receivedResult) {
            this.processActivityResult(999, 0, null);
        }
    }
    
    public void processActivityResult(final int n, final int n2, final Intent intent) {
        GooglePlayPurchasing.ProcessActivityResult(n, n2, intent);
        this.finish();
    }
}
