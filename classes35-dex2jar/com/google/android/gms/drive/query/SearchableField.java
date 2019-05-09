// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.internal.drive.zzic;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import java.util.Date;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;

public class SearchableField
{
    public static final SearchableMetadataField<Boolean> IS_PINNED;
    public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME;
    public static final SearchableMetadataField<String> MIME_TYPE;
    public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE;
    public static final SearchableCollectionMetadataField<DriveId> PARENTS;
    public static final SearchableMetadataField<Boolean> STARRED;
    public static final SearchableMetadataField<String> TITLE;
    public static final SearchableMetadataField<Boolean> TRASHED;
    public static final SearchableOrderedMetadataField<Date> zzle;
    public static final SearchableMetadataField<AppVisibleCustomProperties> zzlf;
    
    static {
        TITLE = zzhp.zzkb;
        MIME_TYPE = zzhp.zzjs;
        TRASHED = zzhp.zzkc;
        PARENTS = zzhp.zzjx;
        zzle = zzic.zzkr;
        STARRED = zzhp.zzjz;
        MODIFIED_DATE = zzic.zzkp;
        LAST_VIEWED_BY_ME = zzic.zzko;
        IS_PINNED = zzhp.zzjk;
        zzlf = zzhp.zzix;
    }
}
