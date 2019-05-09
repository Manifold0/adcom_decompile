// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.internal.drive.zzic;
import com.google.android.gms.internal.drive.zzhp;
import java.util.Date;
import com.google.android.gms.drive.metadata.SortableMetadataField;

public class SortableField
{
    public static final SortableMetadataField<Date> CREATED_DATE;
    public static final SortableMetadataField<Date> LAST_VIEWED_BY_ME;
    public static final SortableMetadataField<Date> MODIFIED_BY_ME_DATE;
    public static final SortableMetadataField<Date> MODIFIED_DATE;
    public static final SortableMetadataField<Long> QUOTA_USED;
    public static final SortableMetadataField<Date> SHARED_WITH_ME_DATE;
    public static final SortableMetadataField<String> TITLE;
    private static final SortableMetadataField<Date> zzli;
    
    static {
        TITLE = zzhp.zzkb;
        CREATED_DATE = zzic.zzkn;
        MODIFIED_DATE = zzic.zzkp;
        MODIFIED_BY_ME_DATE = zzic.zzkq;
        LAST_VIEWED_BY_ME = zzic.zzko;
        SHARED_WITH_ME_DATE = zzic.zzkr;
        QUOTA_USED = zzhp.zzjy;
        zzli = zzic.zzks;
    }
}
