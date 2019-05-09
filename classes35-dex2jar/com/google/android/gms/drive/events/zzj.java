// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.support.annotation.Nullable;
import com.google.android.gms.drive.DriveId;

public final class zzj
{
    public static boolean zza(final int n, @Nullable final DriveId driveId) {
        boolean b = true;
        switch (n) {
            default: {
                b = false;
                break;
            }
            case 1:
            case 8: {
                if (driveId == null) {
                    return false;
                }
                break;
            }
            case 4:
            case 7: {
                if (driveId != null) {
                    return false;
                }
                break;
            }
        }
        return b;
    }
}
