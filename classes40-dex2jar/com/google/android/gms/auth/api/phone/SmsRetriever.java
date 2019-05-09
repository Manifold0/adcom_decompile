// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.phone;

import android.content.Context;
import com.google.android.gms.internal.auth-api-phone.zzj;
import android.support.annotation.NonNull;
import android.app.Activity;

public final class SmsRetriever
{
    public static final String EXTRA_SMS_MESSAGE = "com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE";
    public static final String EXTRA_STATUS = "com.google.android.gms.auth.api.phone.EXTRA_STATUS";
    public static final String SMS_RETRIEVED_ACTION = "com.google.android.gms.auth.api.phone.SMS_RETRIEVED";
    
    private SmsRetriever() {
    }
    
    public static SmsRetrieverClient getClient(@NonNull final Activity activity) {
        return new zzj(activity);
    }
    
    public static SmsRetrieverClient getClient(@NonNull final Context context) {
        return new zzj(context);
    }
}
