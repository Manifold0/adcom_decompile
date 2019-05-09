// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.app.Activity;

public abstract class FBUnityAppLinkBaseActivity extends Activity
{
    private Class<?> getMainActivityClass() {
        final Intent launchIntentForPackage = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
        try {
            return Class.forName(launchIntentForPackage.getComponent().getClassName());
        }
        catch (Exception ex) {
            Log.e(FB.TAG, "Unable to find Main Activity Class");
            return null;
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        Log.v(FB.TAG, "Saving deep link from deep linking activity");
        FB.SetIntent(this.getIntent());
        Log.v(FB.TAG, "Returning to main activity");
        this.startActivity(new Intent((Context)this, (Class)this.getMainActivityClass()));
        this.finish();
    }
}
