// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.content.IntentSender;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi$Settings;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApi;

public abstract class DriveClient extends GoogleApi<Drive.zza>
{
    public DriveClient(@NonNull final Activity activity, @Nullable final Drive.zza zza) {
        super(activity, (Api)Drive.zzu, (Api$ApiOptions)zza, GoogleApi$Settings.DEFAULT_SETTINGS);
    }
    
    public DriveClient(@NonNull final Context context, @NonNull final Drive.zza zza) {
        super(context, (Api)Drive.zzu, (Api$ApiOptions)zza, GoogleApi$Settings.DEFAULT_SETTINGS);
    }
    
    public abstract Task<DriveId> getDriveId(@NonNull final String p0);
    
    public abstract Task<TransferPreferences> getUploadPreferences();
    
    public abstract Task<IntentSender> newCreateFileActivityIntentSender(final CreateFileActivityOptions p0);
    
    public abstract Task<IntentSender> newOpenFileActivityIntentSender(final OpenFileActivityOptions p0);
    
    public abstract Task<Void> requestSync();
    
    public abstract Task<Void> setUploadPreferences(@NonNull final TransferPreferences p0);
}
