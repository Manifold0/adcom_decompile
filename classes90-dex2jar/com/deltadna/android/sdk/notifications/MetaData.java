// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;
import android.os.Bundle;

class MetaData
{
    static final String APPLICATION_ID = "ddna_application_id";
    static final String NOTIFICATION_ICON = "ddna_notification_icon";
    static final String NOTIFICATION_TITLE = "ddna_notification_title";
    static final String SENDER_ID = "ddna_sender_id";
    @Deprecated
    static final String START_LAUNCH_INTENT = "ddna_start_launch_intent";
    static Bundle values;
    
    private MetaData() {
    }
    
    static Bundle get(final Context context) {
        synchronized (MetaData.class) {
            Label_0043: {
                if (MetaData.values != null) {
                    break Label_0043;
                }
                try {
                    MetaData.values = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                    if (MetaData.values == null) {
                        MetaData.values = Bundle.EMPTY;
                    }
                    return MetaData.values;
                }
                catch (PackageManager$NameNotFoundException ex) {
                    throw new RuntimeException((Throwable)ex);
                }
            }
        }
    }
}
