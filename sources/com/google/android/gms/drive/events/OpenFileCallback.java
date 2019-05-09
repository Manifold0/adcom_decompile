package com.google.android.gms.drive.events;

import android.support.annotation.NonNull;
import com.google.android.gms.drive.DriveContents;

public abstract class OpenFileCallback {
    public abstract void onContents(@NonNull DriveContents driveContents);

    public abstract void onError(@NonNull Exception exception);

    public abstract void onProgress(long j, long j2);
}
