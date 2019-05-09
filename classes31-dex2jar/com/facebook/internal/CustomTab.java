// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.support.customtabs.CustomTabsIntent;
import android.content.Context;
import android.support.customtabs.CustomTabsIntent$Builder;
import android.app.Activity;
import com.facebook.FacebookSdk;
import android.os.Bundle;
import android.net.Uri;

public class CustomTab
{
    private Uri uri;
    
    public CustomTab(final String s, final Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        this.uri = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + "dialog/" + s, bundle2);
    }
    
    public void openCustomTab(final Activity activity, final String package1) {
        final CustomTabsIntent build = new CustomTabsIntent$Builder().build();
        build.intent.setPackage(package1);
        build.intent.addFlags(1073741824);
        build.launchUrl((Context)activity, this.uri);
    }
}
