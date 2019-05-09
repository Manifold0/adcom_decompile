package com.google.android.gms.internal.auth-api-phone;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.Task;

public final class zzj extends SmsRetrieverClient {
    public zzj(@NonNull Activity activity) {
        super(activity);
    }

    public zzj(@NonNull Context context) {
        super(context);
    }

    public final Task<Void> startSmsRetriever() {
        return doWrite(new zzk(this));
    }
}
