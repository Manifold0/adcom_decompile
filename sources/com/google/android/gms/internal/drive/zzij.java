package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.zzm;
import java.util.Arrays;

public final class zzij extends zzm<DriveId> {
    public static final zzij zzkt = new zzij();

    private zzij() {
        super("driveId", Arrays.asList(new String[]{"sqlId", "resourceId", "mimeType"}), Arrays.asList(new String[]{"dbInstanceId"}), GmsVersion.VERSION_HALLOUMI);
    }

    protected final boolean zzb(DataHolder dataHolder, int i, int i2) {
        for (String hasColumn : zzar()) {
            if (!dataHolder.hasColumn(hasColumn)) {
                return false;
            }
        }
        return true;
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        long j = dataHolder.getMetadata().getLong("dbInstanceId");
        int i3 = DriveFolder.MIME_TYPE.equals(dataHolder.getString(zzhp.zzjs.getName(), i, i2)) ? 1 : 0;
        String string = dataHolder.getString("resourceId", i, i2);
        Long valueOf = Long.valueOf(dataHolder.getLong("sqlId", i, i2));
        if ("generated-android-null".equals(string)) {
            string = null;
        }
        return new DriveId(string, valueOf.longValue(), j, i3);
    }
}
