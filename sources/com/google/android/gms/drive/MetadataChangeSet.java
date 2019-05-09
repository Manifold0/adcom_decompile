package com.google.android.gms.drive;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties.zza;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.internal.drive.zzic;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public final class MetadataChangeSet {
    public static final int CUSTOM_PROPERTY_SIZE_LIMIT_BYTES = 124;
    public static final int INDEXABLE_TEXT_SIZE_LIMIT_BYTES = 131072;
    public static final int MAX_PRIVATE_PROPERTIES_PER_RESOURCE_PER_APP = 30;
    public static final int MAX_PUBLIC_PROPERTIES_PER_RESOURCE = 30;
    public static final int MAX_TOTAL_PROPERTIES_PER_RESOURCE = 100;
    public static final MetadataChangeSet zzav = new MetadataChangeSet(MetadataBundle.zzaw());
    private final MetadataBundle zzaw;

    public static class Builder {
        private final MetadataBundle zzaw = MetadataBundle.zzaw();
        private zza zzax;

        private static void zza(String str, int i, int i2) {
            Preconditions.checkArgument(i2 <= i, String.format(Locale.US, "%s must be no more than %d bytes, but is %d bytes.", new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)}));
        }

        private static int zzb(@Nullable String str) {
            return str == null ? 0 : str.getBytes().length;
        }

        private final zza zzq() {
            if (this.zzax == null) {
                this.zzax = new zza();
            }
            return this.zzax;
        }

        public MetadataChangeSet build() {
            if (this.zzax != null) {
                this.zzaw.zzb(zzhp.zzix, this.zzax.zzat());
            }
            return new MetadataChangeSet(this.zzaw);
        }

        public Builder deleteCustomProperty(CustomPropertyKey customPropertyKey) {
            Preconditions.checkNotNull(customPropertyKey, ParametersKeys.KEY);
            zzq().zza(customPropertyKey, null);
            return this;
        }

        public Builder setCustomProperty(CustomPropertyKey customPropertyKey, String str) {
            Preconditions.checkNotNull(customPropertyKey, ParametersKeys.KEY);
            Preconditions.checkNotNull(str, "value");
            zza("The total size of key string and value string of a custom property", MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES, zzb(customPropertyKey.getKey()) + zzb(str));
            zzq().zza(customPropertyKey, str);
            return this;
        }

        public Builder setDescription(String str) {
            this.zzaw.zzb(zzhp.zziy, str);
            return this;
        }

        public Builder setIndexableText(String str) {
            zza("Indexable text size", 131072, zzb(str));
            this.zzaw.zzb(zzhp.zzje, str);
            return this;
        }

        public Builder setLastViewedByMeDate(Date date) {
            this.zzaw.zzb(zzic.zzko, date);
            return this;
        }

        public Builder setMimeType(@NonNull String str) {
            Preconditions.checkNotNull(str);
            this.zzaw.zzb(zzhp.zzjs, str);
            return this;
        }

        public Builder setPinned(boolean z) {
            this.zzaw.zzb(zzhp.zzjk, Boolean.valueOf(z));
            return this;
        }

        public Builder setStarred(boolean z) {
            this.zzaw.zzb(zzhp.zzjz, Boolean.valueOf(z));
            return this;
        }

        public Builder setTitle(@NonNull String str) {
            Preconditions.checkNotNull(str, "Title cannot be null.");
            this.zzaw.zzb(zzhp.zzkb, str);
            return this;
        }

        public Builder setViewed() {
            this.zzaw.zzb(zzhp.zzjr, Boolean.valueOf(true));
            return this;
        }

        @Deprecated
        public Builder setViewed(boolean z) {
            if (z) {
                this.zzaw.zzb(zzhp.zzjr, Boolean.valueOf(true));
            } else if (this.zzaw.zzd(zzhp.zzjr)) {
                this.zzaw.zzc(zzhp.zzjr);
            }
            return this;
        }
    }

    public MetadataChangeSet(MetadataBundle metadataBundle) {
        this.zzaw = metadataBundle.zzax();
    }

    public final Map<CustomPropertyKey, String> getCustomPropertyChangeMap() {
        AppVisibleCustomProperties appVisibleCustomProperties = (AppVisibleCustomProperties) this.zzaw.zza(zzhp.zzix);
        return appVisibleCustomProperties == null ? Collections.emptyMap() : appVisibleCustomProperties.zzas();
    }

    @Nullable
    public final String getDescription() {
        return (String) this.zzaw.zza(zzhp.zziy);
    }

    @Nullable
    public final String getIndexableText() {
        return (String) this.zzaw.zza(zzhp.zzje);
    }

    @Nullable
    public final Date getLastViewedByMeDate() {
        return (Date) this.zzaw.zza(zzic.zzko);
    }

    @Nullable
    public final String getMimeType() {
        return (String) this.zzaw.zza(zzhp.zzjs);
    }

    @Nullable
    @KeepForSdk
    public final Bitmap getThumbnail() {
        BitmapTeleporter bitmapTeleporter = (BitmapTeleporter) this.zzaw.zza(zzhp.zzka);
        return bitmapTeleporter == null ? null : bitmapTeleporter.get();
    }

    @Nullable
    public final String getTitle() {
        return (String) this.zzaw.zza(zzhp.zzkb);
    }

    @Nullable
    public final Boolean isPinned() {
        return (Boolean) this.zzaw.zza(zzhp.zzjk);
    }

    @Nullable
    public final Boolean isStarred() {
        return (Boolean) this.zzaw.zza(zzhp.zzjz);
    }

    @Nullable
    public final Boolean isViewed() {
        return (Boolean) this.zzaw.zza(zzhp.zzjr);
    }

    public final MetadataBundle zzp() {
        return this.zzaw;
    }
}
