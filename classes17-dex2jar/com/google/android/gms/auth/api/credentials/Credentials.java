// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import android.support.annotation.NonNull;
import android.app.Activity;

public class Credentials
{
    public static CredentialsClient getClient(@NonNull final Activity activity) {
        return new CredentialsClient(activity, CredentialsOptions.DEFAULT);
    }
    
    public static CredentialsClient getClient(@NonNull final Activity activity, @NonNull final CredentialsOptions credentialsOptions) {
        return new CredentialsClient(activity, credentialsOptions);
    }
    
    public static CredentialsClient getClient(@NonNull final Context context) {
        return new CredentialsClient(context, CredentialsOptions.DEFAULT);
    }
    
    public static CredentialsClient getClient(@NonNull final Context context, @NonNull final CredentialsOptions credentialsOptions) {
        return new CredentialsClient(context, credentialsOptions);
    }
}
