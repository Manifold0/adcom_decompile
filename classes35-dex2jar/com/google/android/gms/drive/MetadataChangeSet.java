// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.BitmapTeleporter;
import android.graphics.Bitmap;
import com.google.android.gms.internal.drive.zzic;
import java.util.Date;
import android.support.annotation.Nullable;
import java.util.Collections;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.Map;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class MetadataChangeSet
{
    public static final int CUSTOM_PROPERTY_SIZE_LIMIT_BYTES = 124;
    public static final int INDEXABLE_TEXT_SIZE_LIMIT_BYTES = 131072;
    public static final int MAX_PRIVATE_PROPERTIES_PER_RESOURCE_PER_APP = 30;
    public static final int MAX_PUBLIC_PROPERTIES_PER_RESOURCE = 30;
    public static final int MAX_TOTAL_PROPERTIES_PER_RESOURCE = 100;
    public static final MetadataChangeSet zzav;
    private final MetadataBundle zzaw;
    
    static {
        zzav = new MetadataChangeSet(MetadataBundle.zzaw());
    }
    
    public MetadataChangeSet(final MetadataBundle metadataBundle) {
        this.zzaw = metadataBundle.zzax();
    }
    
    public final Map<CustomPropertyKey, String> getCustomPropertyChangeMap() {
        final AppVisibleCustomProperties appVisibleCustomProperties = this.zzaw.zza((MetadataField<AppVisibleCustomProperties>)zzhp.zzix);
        if (appVisibleCustomProperties == null) {
            return Collections.emptyMap();
        }
        return appVisibleCustomProperties.zzas();
    }
    
    @Nullable
    public final String getDescription() {
        return this.zzaw.zza(zzhp.zziy);
    }
    
    @Nullable
    public final String getIndexableText() {
        return this.zzaw.zza(zzhp.zzje);
    }
    
    @Nullable
    public final Date getLastViewedByMeDate() {
        return this.zzaw.zza((MetadataField<Date>)zzic.zzko);
    }
    
    @Nullable
    public final String getMimeType() {
        return this.zzaw.zza((MetadataField<String>)zzhp.zzjs);
    }
    
    @Nullable
    @KeepForSdk
    public final Bitmap getThumbnail() {
        final BitmapTeleporter bitmapTeleporter = this.zzaw.zza(zzhp.zzka);
        if (bitmapTeleporter == null) {
            return null;
        }
        return bitmapTeleporter.get();
    }
    
    @Nullable
    public final String getTitle() {
        return this.zzaw.zza((MetadataField<String>)zzhp.zzkb);
    }
    
    @Nullable
    public final Boolean isPinned() {
        return this.zzaw.zza((MetadataField<Boolean>)zzhp.zzjk);
    }
    
    @Nullable
    public final Boolean isStarred() {
        return this.zzaw.zza((MetadataField<Boolean>)zzhp.zzjz);
    }
    
    @Nullable
    public final Boolean isViewed() {
        return this.zzaw.zza(zzhp.zzjr);
    }
    
    public final MetadataBundle zzp() {
        return this.zzaw;
    }
    
    public static class Builder
    {
        private final MetadataBundle zzaw;
        private AppVisibleCustomProperties.zza zzax;
        
        public Builder() {
            this.zzaw = MetadataBundle.zzaw();
        }
        
        private static void zza(final String s, final int n, final int n2) {
            Preconditions.checkArgument(n2 <= n, (Object)String.format(Locale.US, "%s must be no more than %d bytes, but is %d bytes.", s, n, n2));
        }
        
        private static int zzb(@Nullable final String s) {
            if (s == null) {
                return 0;
            }
            return s.getBytes().length;
        }
        
        private final AppVisibleCustomProperties.zza zzq() {
            if (this.zzax == null) {
                this.zzax = new AppVisibleCustomProperties.zza();
            }
            return this.zzax;
        }
        
        public MetadataChangeSet build() {
            if (this.zzax != null) {
                this.zzaw.zzb(zzhp.zzix, this.zzax.zzat());
            }
            return new MetadataChangeSet(this.zzaw);
        }
        
        public Builder deleteCustomProperty(final CustomPropertyKey customPropertyKey) {
            Preconditions.checkNotNull((Object)customPropertyKey, (Object)"key");
            this.zzq().zza(customPropertyKey, null);
            return this;
        }
        
        public Builder setCustomProperty(final CustomPropertyKey customPropertyKey, final String s) {
            Preconditions.checkNotNull((Object)customPropertyKey, (Object)"key");
            Preconditions.checkNotNull((Object)s, (Object)"value");
            zza("The total size of key string and value string of a custom property", 124, zzb(customPropertyKey.getKey()) + zzb(s));
            this.zzq().zza(customPropertyKey, s);
            return this;
        }
        
        public Builder setDescription(final String s) {
            this.zzaw.zzb(zzhp.zziy, s);
            return this;
        }
        
        public Builder setIndexableText(final String s) {
            zza("Indexable text size", 131072, zzb(s));
            this.zzaw.zzb(zzhp.zzje, s);
            return this;
        }
        
        public Builder setLastViewedByMeDate(final Date date) {
            this.zzaw.zzb(zzic.zzko, date);
            return this;
        }
        
        public Builder setMimeType(@NonNull final String s) {
            Preconditions.checkNotNull((Object)s);
            this.zzaw.zzb(zzhp.zzjs, s);
            return this;
        }
        
        public Builder setPinned(final boolean b) {
            this.zzaw.zzb(zzhp.zzjk, b);
            return this;
        }
        
        public Builder setStarred(final boolean b) {
            this.zzaw.zzb(zzhp.zzjz, b);
            return this;
        }
        
        public Builder setTitle(@NonNull final String s) {
            Preconditions.checkNotNull((Object)s, (Object)"Title cannot be null.");
            this.zzaw.zzb(zzhp.zzkb, s);
            return this;
        }
        
        public Builder setViewed() {
            this.zzaw.zzb(zzhp.zzjr, true);
            return this;
        }
        
        @Deprecated
        public Builder setViewed(final boolean b) {
            if (b) {
                this.zzaw.zzb(zzhp.zzjr, true);
            }
            else if (this.zzaw.zzd(zzhp.zzjr)) {
                this.zzaw.zzc(zzhp.zzjr);
                return this;
            }
            return this;
        }
    }
}
