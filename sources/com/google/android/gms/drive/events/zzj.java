package com.google.android.gms.drive.events;

import android.support.annotation.Nullable;
import com.google.android.gms.drive.DriveId;

public final class zzj {
    public static boolean zza(int i, @Nullable DriveId driveId) {
        switch (i) {
            case 1:
            case 8:
                return driveId != null;
            case 4:
            case 7:
                return driveId == null;
            default:
                return false;
        }
    }
}
