package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.drive.Drive.zza;
import com.google.android.gms.tasks.Task;

public abstract class DriveClient extends GoogleApi<zza> {
    public DriveClient(@NonNull Activity activity, @Nullable zza zza) {
        super(activity, Drive.zzu, zza, Settings.DEFAULT_SETTINGS);
    }

    public DriveClient(@NonNull Context context, @NonNull zza zza) {
        super(context, Drive.zzu, zza, Settings.DEFAULT_SETTINGS);
    }

    public abstract Task<DriveId> getDriveId(@NonNull String str);

    public abstract Task<TransferPreferences> getUploadPreferences();

    public abstract Task<IntentSender> newCreateFileActivityIntentSender(CreateFileActivityOptions createFileActivityOptions);

    public abstract Task<IntentSender> newOpenFileActivityIntentSender(OpenFileActivityOptions openFileActivityOptions);

    public abstract Task<Void> requestSync();

    public abstract Task<Void> setUploadPreferences(@NonNull TransferPreferences transferPreferences);
}
