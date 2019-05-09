// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.support.annotation.Nullable;
import java.util.Collections;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.Map;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.drive.zzic;
import java.util.Date;
import com.google.android.gms.internal.drive.zzik;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.common.data.Freezable;

public abstract class Metadata implements Freezable<Metadata>
{
    public static final int CONTENT_AVAILABLE_LOCALLY = 1;
    public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;
    
    public String getAlternateLink() {
        return this.zza(zzhp.zziw);
    }
    
    public int getContentAvailability() {
        final Integer n = this.zza(zzik.zzku);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    public Date getCreatedDate() {
        return this.zza((MetadataField<Date>)zzic.zzkn);
    }
    
    public Map<CustomPropertyKey, String> getCustomProperties() {
        final AppVisibleCustomProperties appVisibleCustomProperties = this.zza((MetadataField<AppVisibleCustomProperties>)zzhp.zzix);
        if (appVisibleCustomProperties == null) {
            return Collections.emptyMap();
        }
        return appVisibleCustomProperties.zzas();
    }
    
    public String getDescription() {
        return this.zza(zzhp.zziy);
    }
    
    public DriveId getDriveId() {
        return this.zza(zzhp.zziv);
    }
    
    public String getEmbedLink() {
        return this.zza(zzhp.zziz);
    }
    
    public String getFileExtension() {
        return this.zza(zzhp.zzja);
    }
    
    public long getFileSize() {
        return this.zza(zzhp.zzjb);
    }
    
    @Nullable
    public Date getLastViewedByMeDate() {
        return this.zza((MetadataField<Date>)zzic.zzko);
    }
    
    public String getMimeType() {
        return this.zza((MetadataField<String>)zzhp.zzjs);
    }
    
    @Nullable
    public Date getModifiedByMeDate() {
        return this.zza((MetadataField<Date>)zzic.zzkq);
    }
    
    public Date getModifiedDate() {
        return this.zza((MetadataField<Date>)zzic.zzkp);
    }
    
    public String getOriginalFilename() {
        return this.zza(zzhp.zzjt);
    }
    
    public long getQuotaBytesUsed() {
        return this.zza((MetadataField<Long>)zzhp.zzjy);
    }
    
    @Nullable
    public Date getSharedWithMeDate() {
        return this.zza((MetadataField<Date>)zzic.zzkr);
    }
    
    public String getTitle() {
        return this.zza((MetadataField<String>)zzhp.zzkb);
    }
    
    public String getWebContentLink() {
        return this.zza(zzhp.zzkd);
    }
    
    public String getWebViewLink() {
        return this.zza(zzhp.zzke);
    }
    
    public boolean isEditable() {
        final Boolean b = this.zza(zzhp.zzjh);
        return b != null && b;
    }
    
    public boolean isExplicitlyTrashed() {
        final Boolean b = this.zza(zzhp.zzji);
        return b != null && b;
    }
    
    public boolean isFolder() {
        return "application/vnd.google-apps.folder".equals(this.getMimeType());
    }
    
    public boolean isInAppFolder() {
        final Boolean b = this.zza(zzhp.zzjf);
        return b != null && b;
    }
    
    public boolean isPinnable() {
        final Boolean b = this.zza(zzik.zzkv);
        return b != null && b;
    }
    
    public boolean isPinned() {
        final Boolean b = this.zza((MetadataField<Boolean>)zzhp.zzjk);
        return b != null && b;
    }
    
    public boolean isRestricted() {
        final Boolean b = this.zza(zzhp.zzjm);
        return b != null && b;
    }
    
    public boolean isShared() {
        final Boolean b = this.zza(zzhp.zzjn);
        return b != null && b;
    }
    
    public boolean isStarred() {
        final Boolean b = this.zza((MetadataField<Boolean>)zzhp.zzjz);
        return b != null && b;
    }
    
    public boolean isTrashable() {
        final Boolean b = this.zza(zzhp.zzjq);
        return b == null || b;
    }
    
    public boolean isTrashed() {
        final Boolean b = this.zza((MetadataField<Boolean>)zzhp.zzkc);
        return b != null && b;
    }
    
    public boolean isViewed() {
        final Boolean b = this.zza(zzhp.zzjr);
        return b != null && b;
    }
    
    public abstract <T> T zza(final MetadataField<T> p0);
}
