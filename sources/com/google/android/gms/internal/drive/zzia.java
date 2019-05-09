package com.google.android.gms.internal.drive;

import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties.zza;
import com.google.android.gms.drive.metadata.internal.zzc;
import com.google.android.gms.drive.metadata.internal.zzg;
import com.google.android.gms.drive.metadata.internal.zzm;
import java.util.Arrays;

public class zzia extends zzm<AppVisibleCustomProperties> {
    public static final zzg zzkm = new zzib();

    public zzia(int i) {
        super("customProperties", Arrays.asList(new String[]{"hasCustomProperties", "sqlId"}), Arrays.asList(new String[]{"customPropertiesExtra", "customPropertiesExtraHolder"}), GmsVersion.VERSION_LONGHORN);
    }

    private static void zzc(DataHolder dataHolder) {
        Bundle metadata = dataHolder.getMetadata();
        if (metadata != null) {
            synchronized (dataHolder) {
                DataHolder dataHolder2 = (DataHolder) metadata.getParcelable("customPropertiesExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    metadata.remove("customPropertiesExtraHolder");
                }
            }
        }
    }

    private static AppVisibleCustomProperties zzf(DataHolder dataHolder, int i, int i2) {
        Bundle metadata = dataHolder.getMetadata();
        SparseArray sparseParcelableArray = metadata.getSparseParcelableArray("customPropertiesExtra");
        if (sparseParcelableArray == null) {
            if (metadata.getParcelable("customPropertiesExtraHolder") != null) {
                synchronized (dataHolder) {
                    DataHolder dataHolder2 = (DataHolder) dataHolder.getMetadata().getParcelable("customPropertiesExtraHolder");
                    if (dataHolder2 == null) {
                    } else {
                        try {
                            int i3;
                            zza zza;
                            Bundle metadata2 = dataHolder2.getMetadata();
                            String string = metadata2.getString("entryIdColumn");
                            String string2 = metadata2.getString("keyColumn");
                            String string3 = metadata2.getString("visibilityColumn");
                            String string4 = metadata2.getString("valueColumn");
                            LongSparseArray longSparseArray = new LongSparseArray();
                            for (i3 = 0; i3 < dataHolder2.getCount(); i3++) {
                                int windowIndex = dataHolder2.getWindowIndex(i3);
                                long j = dataHolder2.getLong(string, i3, windowIndex);
                                String string5 = dataHolder2.getString(string2, i3, windowIndex);
                                int integer = dataHolder2.getInteger(string3, i3, windowIndex);
                                zzc zzc = new zzc(new CustomPropertyKey(string5, integer), dataHolder2.getString(string4, i3, windowIndex));
                                zza = (zza) longSparseArray.get(j);
                                if (zza == null) {
                                    zza = new zza();
                                    longSparseArray.put(j, zza);
                                }
                                zza.zza(zzc);
                            }
                            SparseArray sparseArray = new SparseArray();
                            for (i3 = 0; i3 < dataHolder.getCount(); i3++) {
                                DataHolder dataHolder3 = dataHolder;
                                zza = (zza) longSparseArray.get(dataHolder3.getLong("sqlId", i3, dataHolder.getWindowIndex(i3)));
                                if (zza != null) {
                                    sparseArray.append(i3, zza.zzat());
                                }
                            }
                            dataHolder.getMetadata().putSparseParcelableArray("customPropertiesExtra", sparseArray);
                        } finally {
                            dataHolder2.close();
                            dataHolder.getMetadata().remove("customPropertiesExtraHolder");
                        }
                    }
                }
                sparseParcelableArray = metadata.getSparseParcelableArray("customPropertiesExtra");
            }
            if (sparseParcelableArray == null) {
                return AppVisibleCustomProperties.zzil;
            }
        }
        return (AppVisibleCustomProperties) sparseParcelableArray.get(i, AppVisibleCustomProperties.zzil);
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zzf(dataHolder, i, i2);
    }
}
