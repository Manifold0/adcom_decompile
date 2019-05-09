package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collections;

public final class zzu extends zzm<UserMetadata> {
    public zzu(String str, int i) {
        super(str, Arrays.asList(new String[]{zza(str, "permissionId"), zza(str, "displayName"), zza(str, "picture"), zza(str, "isAuthenticatedUser"), zza(str, "emailAddress")}), Collections.emptyList(), GmsVersion.VERSION_MANCHEGO);
    }

    private static String zza(String str, String str2) {
        return new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(str2).length()).append(str).append(".").append(str2).toString();
    }

    private final String zzf(String str) {
        return zza(getName(), str);
    }

    protected final boolean zzb(DataHolder dataHolder, int i, int i2) {
        return dataHolder.hasColumn(zzf("permissionId")) && !dataHolder.hasNull(zzf("permissionId"), i, i2);
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        String string = dataHolder.getString(zzf("permissionId"), i, i2);
        if (string == null) {
            return null;
        }
        String string2 = dataHolder.getString(zzf("displayName"), i, i2);
        String string3 = dataHolder.getString(zzf("picture"), i, i2);
        Boolean valueOf = Boolean.valueOf(dataHolder.getBoolean(zzf("isAuthenticatedUser"), i, i2));
        return new UserMetadata(string, string2, string3, valueOf.booleanValue(), dataHolder.getString(zzf("emailAddress"), i, i2));
    }
}
