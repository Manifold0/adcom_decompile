package com.google.android.gms.drive.query;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.internal.drive.zzic;
import java.util.Date;

public class SearchableField {
    public static final SearchableMetadataField<Boolean> IS_PINNED = zzhp.zzjk;
    public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME = zzic.zzko;
    public static final SearchableMetadataField<String> MIME_TYPE = zzhp.zzjs;
    public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE = zzic.zzkp;
    public static final SearchableCollectionMetadataField<DriveId> PARENTS = zzhp.zzjx;
    public static final SearchableMetadataField<Boolean> STARRED = zzhp.zzjz;
    public static final SearchableMetadataField<String> TITLE = zzhp.zzkb;
    public static final SearchableMetadataField<Boolean> TRASHED = zzhp.zzkc;
    public static final SearchableOrderedMetadataField<Date> zzle = zzic.zzkr;
    public static final SearchableMetadataField<AppVisibleCustomProperties> zzlf = zzhp.zzix;
}
