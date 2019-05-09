// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import java.util.List;
import android.support.annotation.NonNull;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.internal.FilterHolder;

public final class OpenFileActivityOptions
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    public final String zzay;
    public final String[] zzaz;
    public final DriveId zzbb;
    public final FilterHolder zzbc;
    
    private OpenFileActivityOptions(final String zzay, final String[] zzaz, final Filter filter, final DriveId zzbb) {
        this.zzay = zzay;
        this.zzaz = zzaz;
        FilterHolder zzbc;
        if (filter == null) {
            zzbc = null;
        }
        else {
            zzbc = new FilterHolder(filter);
        }
        this.zzbc = zzbc;
        this.zzbb = zzbb;
    }
    
    public static class Builder
    {
        private final OpenFileActivityBuilder zzbd;
        
        public Builder() {
            this.zzbd = new OpenFileActivityBuilder();
        }
        
        public OpenFileActivityOptions build() {
            this.zzbd.zzf();
            return new OpenFileActivityOptions(this.zzbd.getTitle(), this.zzbd.zzr(), this.zzbd.zzs(), this.zzbd.zzt(), null);
        }
        
        public Builder setActivityStartFolder(final DriveId activityStartFolder) {
            this.zzbd.setActivityStartFolder(activityStartFolder);
            return this;
        }
        
        public Builder setActivityTitle(@NonNull final String activityTitle) {
            this.zzbd.setActivityTitle(activityTitle);
            return this;
        }
        
        public Builder setMimeType(@NonNull final List<String> list) {
            this.zzbd.setMimeType(list.toArray(new String[0]));
            return this;
        }
        
        public Builder setSelectionFilter(@NonNull final Filter selectionFilter) {
            this.zzbd.setSelectionFilter(selectionFilter);
            return this;
        }
    }
}
