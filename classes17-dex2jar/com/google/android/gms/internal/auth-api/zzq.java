// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import android.app.PendingIntent;
import com.google.android.gms.auth.api.credentials.HintRequest;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.Auth;
import android.content.Context;

public final class zzq
{
    public static PendingIntent zzc(final Context context, @Nullable final Auth.AuthCredentialsOptions authCredentialsOptions, final HintRequest hintRequest) {
        Preconditions.checkNotNull((Object)context, (Object)"context must not be null");
        Preconditions.checkNotNull((Object)hintRequest, (Object)"request must not be null");
        if (authCredentialsOptions == null) {}
        final Intent putExtra = new Intent("com.google.android.gms.auth.api.credentials.PICKER").putExtra("claimedCallingPackage", (String)null);
        SafeParcelableSerializer.serializeToIntentExtra((SafeParcelable)hintRequest, putExtra, "com.google.android.gms.credentials.HintRequest");
        return PendingIntent.getActivity(context, 2000, putExtra, 134217728);
    }
}
