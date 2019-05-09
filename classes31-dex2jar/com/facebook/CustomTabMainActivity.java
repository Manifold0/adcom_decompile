// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.content.IntentFilter;
import com.facebook.internal.CustomTab;
import android.os.Bundle;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.app.Activity;

public class CustomTabMainActivity extends Activity
{
    public static final String EXTRA_CHROME_PACKAGE;
    public static final String EXTRA_PARAMS;
    public static final String EXTRA_URL;
    private static final String OAUTH_DIALOG = "oauth";
    public static final String REFRESH_ACTION;
    private BroadcastReceiver redirectReceiver;
    private boolean shouldCloseCustomTab;
    
    static {
        EXTRA_PARAMS = CustomTabMainActivity.class.getSimpleName() + ".extra_params";
        EXTRA_CHROME_PACKAGE = CustomTabMainActivity.class.getSimpleName() + ".extra_chromePackage";
        EXTRA_URL = CustomTabMainActivity.class.getSimpleName() + ".extra_url";
        REFRESH_ACTION = CustomTabMainActivity.class.getSimpleName() + ".action_refresh";
    }
    
    public CustomTabMainActivity() {
        this.shouldCloseCustomTab = true;
    }
    
    public static final String getRedirectUrl() {
        return "fb" + FacebookSdk.getApplicationId() + "://authorize";
    }
    
    private void sendResult(final int result, final Intent intent) {
        LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.redirectReceiver);
        if (intent != null) {
            this.setResult(result, intent);
        }
        else {
            this.setResult(result);
        }
        this.finish();
    }
    
    protected void onCreate(Bundle bundleExtra) {
        super.onCreate(bundleExtra);
        if (CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION.equals(this.getIntent().getAction())) {
            this.setResult(0);
            this.finish();
        }
        else if (bundleExtra == null) {
            bundleExtra = this.getIntent().getBundleExtra(CustomTabMainActivity.EXTRA_PARAMS);
            new CustomTab("oauth", bundleExtra).openCustomTab(this, this.getIntent().getStringExtra(CustomTabMainActivity.EXTRA_CHROME_PACKAGE));
            this.shouldCloseCustomTab = false;
            this.redirectReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    final Intent intent2 = new Intent((Context)CustomTabMainActivity.this, (Class)CustomTabMainActivity.class);
                    intent2.setAction(CustomTabMainActivity.REFRESH_ACTION);
                    intent2.putExtra(CustomTabMainActivity.EXTRA_URL, intent.getStringExtra(CustomTabMainActivity.EXTRA_URL));
                    intent2.addFlags(603979776);
                    CustomTabMainActivity.this.startActivity(intent2);
                }
            };
            LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.redirectReceiver, new IntentFilter(CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION));
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        if (CustomTabMainActivity.REFRESH_ACTION.equals(intent.getAction())) {
            LocalBroadcastManager.getInstance((Context)this).sendBroadcast(new Intent(CustomTabActivity.DESTROY_ACTION));
            this.sendResult(-1, intent);
        }
        else if (CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION.equals(intent.getAction())) {
            this.sendResult(-1, intent);
        }
    }
    
    protected void onResume() {
        super.onResume();
        if (this.shouldCloseCustomTab) {
            this.sendResult(0, null);
        }
        this.shouldCloseCustomTab = true;
    }
}
