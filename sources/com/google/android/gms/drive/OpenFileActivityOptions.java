package com.google.android.gms.drive;

import android.support.annotation.NonNull;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.internal.FilterHolder;
import java.util.List;

public final class OpenFileActivityOptions {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    public final String zzay;
    public final String[] zzaz;
    public final DriveId zzbb;
    public final FilterHolder zzbc;

    public static class Builder {
        private final OpenFileActivityBuilder zzbd = new OpenFileActivityBuilder();

        public OpenFileActivityOptions build() {
            this.zzbd.zzf();
            return new OpenFileActivityOptions(this.zzbd.getTitle(), this.zzbd.zzr(), this.zzbd.zzs(), this.zzbd.zzt());
        }

        public Builder setActivityStartFolder(DriveId driveId) {
            this.zzbd.setActivityStartFolder(driveId);
            return this;
        }

        public Builder setActivityTitle(@NonNull String str) {
            this.zzbd.setActivityTitle(str);
            return this;
        }

        public Builder setMimeType(@NonNull List<String> list) {
            this.zzbd.setMimeType((String[]) list.toArray(new String[0]));
            return this;
        }

        public Builder setSelectionFilter(@NonNull Filter filter) {
            this.zzbd.setSelectionFilter(filter);
            return this;
        }
    }

    private OpenFileActivityOptions(String str, String[] strArr, Filter filter, DriveId driveId) {
        this.zzay = str;
        this.zzaz = strArr;
        this.zzbc = filter == null ? null : new FilterHolder(filter);
        this.zzbb = driveId;
    }
}
