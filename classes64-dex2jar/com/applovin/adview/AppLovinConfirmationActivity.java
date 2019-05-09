// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import android.content.Intent;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.os.Bundle;
import android.app.Activity;

public class AppLovinConfirmationActivity extends Activity
{
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        final Intent intent = this.getIntent();
        alertDialog$Builder.setTitle((CharSequence)intent.getStringExtra("dialog_title"));
        alertDialog$Builder.setMessage((CharSequence)intent.getStringExtra("dialog_body"));
        alertDialog$Builder.setCancelable(false);
        alertDialog$Builder.setPositiveButton((CharSequence)intent.getStringExtra("dialog_button_text"), (DialogInterface$OnClickListener)new a(this));
        alertDialog$Builder.show();
    }
}
