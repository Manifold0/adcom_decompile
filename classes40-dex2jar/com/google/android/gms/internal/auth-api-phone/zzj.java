// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api-phone;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

public final class zzj extends SmsRetrieverClient
{
    public zzj(@NonNull final Activity activity) {
        super(activity);
    }
    
    public zzj(@NonNull final Context context) {
        super(context);
    }
    
    @Override
    public final Task<Void> startSmsRetriever() {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzk(this));
    }
}
