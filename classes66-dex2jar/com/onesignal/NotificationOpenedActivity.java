// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

public class NotificationOpenedActivity extends Activity
{
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        NotificationOpenedProcessor.processFromContext((Context)this, this.getIntent());
        this.finish();
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        NotificationOpenedProcessor.processFromContext((Context)this, this.getIntent());
        this.finish();
    }
}
