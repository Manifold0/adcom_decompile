package com.google.android.gms.drive;

import android.support.annotation.Nullable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.internal.drive.zzic;
import com.google.android.gms.internal.drive.zzik;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public abstract class Metadata implements Freezable<Metadata> {
    public static final int CONTENT_AVAILABLE_LOCALLY = 1;
    public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;

    public String getAlternateLink() {
        return (String) zza(zzhp.zziw);
    }

    public int getContentAvailability() {
        Integer num = (Integer) zza(zzik.zzku);
        return num == null ? 0 : num.intValue();
    }

    public Date getCreatedDate() {
        return (Date) zza(zzic.zzkn);
    }

    public Map<CustomPropertyKey, String> getCustomProperties() {
        AppVisibleCustomProperties appVisibleCustomProperties = (AppVisibleCustomProperties) zza(zzhp.zzix);
        return appVisibleCustomProperties == null ? Collections.emptyMap() : appVisibleCustomProperties.zzas();
    }

    public String getDescription() {
        return (String) zza(zzhp.zziy);
    }

    public DriveId getDriveId() {
        return (DriveId) zza(zzhp.zziv);
    }

    public String getEmbedLink() {
        return (String) zza(zzhp.zziz);
    }

    public String getFileExtension() {
        return (String) zza(zzhp.zzja);
    }

    public long getFileSize() {
        return ((Long) zza(zzhp.zzjb)).longValue();
    }

    @Nullable
    public Date getLastViewedByMeDate() {
        return (Date) zza(zzic.zzko);
    }

    public String getMimeType() {
        return (String) zza(zzhp.zzjs);
    }

    @Nullable
    public Date getModifiedByMeDate() {
        return (Date) zza(zzic.zzkq);
    }

    public Date getModifiedDate() {
        return (Date) zza(zzic.zzkp);
    }

    public String getOriginalFilename() {
        return (String) zza(zzhp.zzjt);
    }

    public long getQuotaBytesUsed() {
        return ((Long) zza(zzhp.zzjy)).longValue();
    }

    @Nullable
    public Date getSharedWithMeDate() {
        return (Date) zza(zzic.zzkr);
    }

    public String getTitle() {
        return (String) zza(zzhp.zzkb);
    }

    public String getWebContentLink() {
        return (String) zza(zzhp.zzkd);
    }

    public String getWebViewLink() {
        return (String) zza(zzhp.zzke);
    }

    public boolean isEditable() {
        Boolean bool = (Boolean) zza(zzhp.zzjh);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isExplicitlyTrashed() {
        Boolean bool = (Boolean) zza(zzhp.zzji);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isFolder() {
        return DriveFolder.MIME_TYPE.equals(getMimeType());
    }

    public boolean isInAppFolder() {
        Boolean bool = (Boolean) zza(zzhp.zzjf);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isPinnable() {
        Boolean bool = (Boolean) zza(zzik.zzkv);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isPinned() {
        Boolean bool = (Boolean) zza(zzhp.zzjk);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isRestricted() {
        Boolean bool = (Boolean) zza(zzhp.zzjm);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isShared() {
        Boolean bool = (Boolean) zza(zzhp.zzjn);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isStarred() {
        Boolean bool = (Boolean) zza(zzhp.zzjz);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isTrashable() {
        Boolean bool = (Boolean) zza(zzhp.zzjq);
        return bool == null ? true : bool.booleanValue();
    }

    public boolean isTrashed() {
        Boolean bool = (Boolean) zza(zzhp.zzkc);
        return bool == null ? false : bool.booleanValue();
    }

    public boolean isViewed() {
        Boolean bool = (Boolean) zza(zzhp.zzjr);
        return bool == null ? false : bool.booleanValue();
    }

    public abstract <T> T zza(MetadataField<T> metadataField);
}
