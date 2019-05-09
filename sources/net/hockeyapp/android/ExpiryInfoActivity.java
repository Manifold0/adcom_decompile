package net.hockeyapp.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import net.hockeyapp.android.utils.Util;

public class ExpiryInfoActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C0982R.string.hockeyapp_expiry_info_title));
        setContentView(C0982R.layout.hockeyapp_activity_expiry_info);
        String appName = Util.getAppName(this);
        ((TextView) findViewById(C0982R.id.label_message)).setText(String.format(getString(C0982R.string.hockeyapp_expiry_info_text), new Object[]{appName}));
    }
}
