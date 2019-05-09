package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzo extends zzl<DriveId> implements SearchableCollectionMetadataField<DriveId> {
    public static final zzg zziu = new zzp();

    public zzo(int i) {
        super("parents", Collections.emptySet(), Arrays.asList(new String[]{"parentsExtra", "dbInstanceId", "parentsExtraHolder"}), GmsVersion.VERSION_HALLOUMI);
    }

    private static void zzc(DataHolder dataHolder) {
        Bundle metadata = dataHolder.getMetadata();
        if (metadata != null) {
            synchronized (dataHolder) {
                DataHolder dataHolder2 = (DataHolder) metadata.getParcelable("parentsExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    metadata.remove("parentsExtraHolder");
                }
            }
        }
    }

    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return zzc(bundle);
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zzd(dataHolder, i, i2);
    }

    protected final Collection<DriveId> zzc(Bundle bundle) {
        Collection zzc = super.zzc(bundle);
        return zzc == null ? null : new HashSet(zzc);
    }

    protected final Collection<DriveId> zzd(DataHolder dataHolder, int i, int i2) {
        Bundle metadata = dataHolder.getMetadata();
        List parcelableArrayList = metadata.getParcelableArrayList("parentsExtra");
        if (parcelableArrayList == null) {
            if (metadata.getParcelable("parentsExtraHolder") != null) {
                synchronized (dataHolder) {
                    DataHolder dataHolder2 = (DataHolder) dataHolder.getMetadata().getParcelable("parentsExtraHolder");
                    if (dataHolder2 == null) {
                    } else {
                        try {
                            int count = dataHolder.getCount();
                            ArrayList arrayList = new ArrayList(count);
                            Map hashMap = new HashMap(count);
                            for (int i3 = 0; i3 < count; i3++) {
                                int windowIndex = dataHolder.getWindowIndex(i3);
                                ParentDriveIdSet parentDriveIdSet = new ParentDriveIdSet();
                                arrayList.add(parentDriveIdSet);
                                hashMap.put(Long.valueOf(dataHolder.getLong("sqlId", i3, windowIndex)), parentDriveIdSet);
                            }
                            Bundle metadata2 = dataHolder2.getMetadata();
                            String string = metadata2.getString("childSqlIdColumn");
                            String string2 = metadata2.getString("parentSqlIdColumn");
                            String string3 = metadata2.getString("parentResIdColumn");
                            int count2 = dataHolder2.getCount();
                            for (count = 0; count < count2; count++) {
                                int windowIndex2 = dataHolder2.getWindowIndex(count);
                                ParentDriveIdSet parentDriveIdSet2 = (ParentDriveIdSet) hashMap.get(Long.valueOf(dataHolder2.getLong(string, count, windowIndex2)));
                                parentDriveIdSet2.zzit.add(new zzq(dataHolder2.getString(string3, count, windowIndex2), dataHolder2.getLong(string2, count, windowIndex2), 1));
                            }
                            dataHolder.getMetadata().putParcelableArrayList("parentsExtra", arrayList);
                        } finally {
                            dataHolder2.close();
                            dataHolder.getMetadata().remove("parentsExtraHolder");
                        }
                    }
                }
                parcelableArrayList = metadata.getParcelableArrayList("parentsExtra");
            }
            if (parcelableArrayList == null) {
                return null;
            }
        }
        long j = metadata.getLong("dbInstanceId");
        ParentDriveIdSet parentDriveIdSet3 = (ParentDriveIdSet) parcelableArrayList.get(i);
        Set hashSet = new HashSet();
        for (zzq zzq : parentDriveIdSet3.zzit) {
            hashSet.add(new DriveId(zzq.zzab, zzq.zzac, j, zzq.zzad));
        }
        return hashSet;
    }
}
