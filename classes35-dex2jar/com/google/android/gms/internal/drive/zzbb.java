// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.OpenFileActivityOptions;
import android.content.IntentSender;
import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.Drive;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.drive.DriveClient;

public final class zzbb extends DriveClient
{
    public zzbb(@NonNull final Activity activity, @Nullable final Drive.zza zza) {
        super(activity, zza);
    }
    
    public zzbb(@NonNull final Context context, @Nullable final Drive.zza zza) {
        super(context, zza);
    }
    
    @Override
    public final Task<DriveId> getDriveId(@NonNull final String s) {
        Preconditions.checkNotNull((Object)s, (Object)"resourceId must not be null");
        return (Task<DriveId>)this.doRead((TaskApiCall)new zzbc(this, s));
    }
    
    @Override
    public final Task<TransferPreferences> getUploadPreferences() {
        return (Task<TransferPreferences>)this.doRead((TaskApiCall)new zzbd(this));
    }
    
    @Override
    public final Task<IntentSender> newCreateFileActivityIntentSender(final CreateFileActivityOptions createFileActivityOptions) {
        return (Task<IntentSender>)this.doRead((TaskApiCall)new zzbg(this, createFileActivityOptions));
    }
    
    @Override
    public final Task<IntentSender> newOpenFileActivityIntentSender(final OpenFileActivityOptions openFileActivityOptions) {
        return (Task<IntentSender>)this.doRead((TaskApiCall)new zzbf(this, openFileActivityOptions));
    }
    
    @Override
    public final Task<Void> requestSync() {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbh(this));
    }
    
    @Override
    public final Task<Void> setUploadPreferences(@NonNull final TransferPreferences transferPreferences) {
        Preconditions.checkNotNull((Object)transferPreferences, (Object)"transferPreferences cannot be null.");
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbe(this, transferPreferences));
    }
}
