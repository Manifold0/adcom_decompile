// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.os.Bundle;
import android.content.IntentFilter;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.app.Activity;

public class CustomTabActivity extends Activity
{
    public static final String CUSTOM_TAB_REDIRECT_ACTION;
    private static final int CUSTOM_TAB_REDIRECT_REQUEST_CODE = 2;
    public static final String DESTROY_ACTION;
    private BroadcastReceiver closeReceiver;
    
    static {
        CUSTOM_TAB_REDIRECT_ACTION = CustomTabActivity.class.getSimpleName() + ".action_customTabRedirect";
        DESTROY_ACTION = CustomTabActivity.class.getSimpleName() + ".action_destroy";
    }
    
    protected void onActivityResult(final int n, final int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 == 0) {
            intent = new Intent(CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION);
            intent.putExtra(CustomTabMainActivity.EXTRA_URL, this.getIntent().getDataString());
            LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
            this.closeReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    CustomTabActivity.this.finish();
                }
            };
            LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.closeReceiver, new IntentFilter(CustomTabActivity.DESTROY_ACTION));
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Intent intent = new Intent((Context)this, (Class)CustomTabMainActivity.class);
        intent.setAction(CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION);
        intent.putExtra(CustomTabMainActivity.EXTRA_URL, this.getIntent().getDataString());
        intent.addFlags(603979776);
        this.startActivityForResult(intent, 2);
    }
    
    protected void onDestroy() {
        LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.closeReceiver);
        super.onDestroy();
    }
}
