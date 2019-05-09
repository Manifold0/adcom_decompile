package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.internal.zzm;
import java.util.Collection;

final class zzhr extends zzm<BitmapTeleporter> {
    zzhr(String str, Collection collection, Collection collection2, int i) {
        super(str, collection, collection2, GmsVersion.VERSION_KENAFA);
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        throw new IllegalStateException("Thumbnail field is write only");
    }
}
