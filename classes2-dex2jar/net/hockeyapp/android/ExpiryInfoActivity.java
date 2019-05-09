// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.content.Context;
import net.hockeyapp.android.utils.Util;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;

public class ExpiryInfoActivity extends Activity
{
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle((CharSequence)this.getString(R$string.hockeyapp_expiry_info_title));
        this.setContentView(R$layout.hockeyapp_activity_expiry_info);
        ((TextView)this.findViewById(R$id.label_message)).setText((CharSequence)String.format(this.getString(R$string.hockeyapp_expiry_info_text), Util.getAppName((Context)this)));
    }
}
