// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.zzz;
import com.google.android.gms.drive.query.internal.zzd;
import com.google.android.gms.drive.query.internal.zzv;
import com.google.android.gms.drive.query.internal.zzp;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.query.internal.zzn;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.query.internal.zzb;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.drive.query.internal.zzx;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;

public class Filters
{
    public static Filter and(@NonNull final Filter filter, @NonNull final Filter... array) {
        Preconditions.checkNotNull((Object)filter, (Object)"Filter may not be null.");
        Preconditions.checkNotNull((Object)array, (Object)"Additional filters may not be null.");
        return new zzr(zzx.zzmf, filter, array);
    }
    
    public static Filter and(@NonNull final Iterable<Filter> iterable) {
        Preconditions.checkNotNull((Object)iterable, (Object)"Filters may not be null");
        return new zzr(zzx.zzmf, iterable);
    }
    
    public static Filter contains(@NonNull final SearchableMetadataField<String> searchableMetadataField, @NonNull final String s) {
        Preconditions.checkNotNull((Object)searchableMetadataField, (Object)"Field may not be null.");
        Preconditions.checkNotNull((Object)s, (Object)"Value may not be null.");
        return new zzb<Object>(zzx.zzmi, searchableMetadataField, s);
    }
    
    public static Filter eq(@NonNull final CustomPropertyKey customPropertyKey, @NonNull final String s) {
        Preconditions.checkNotNull((Object)customPropertyKey, (Object)"Custom property key may not be null.");
        Preconditions.checkNotNull((Object)s, (Object)"Custom property value may not be null.");
        return new zzn<Object>(SearchableField.zzlf, new AppVisibleCustomProperties.zza().zza(customPropertyKey, s).zzat());
    }
    
    public static <T> Filter eq(@NonNull final SearchableMetadataField<T> searchableMetadataField, @NonNull final T t) {
        Preconditions.checkNotNull((Object)searchableMetadataField, (Object)"Field may not be null.");
        Preconditions.checkNotNull((Object)t, (Object)"Value may not be null.");
        return new zzb<Object>(zzx.zzma, searchableMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter greaterThan(@NonNull final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, @NonNull final T t) {
        Preconditions.checkNotNull((Object)searchableOrderedMetadataField, (Object)"Field may not be null.");
        Preconditions.checkNotNull((Object)t, (Object)"Value may not be null.");
        return new zzb<Object>(zzx.zzmd, searchableOrderedMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter greaterThanEquals(@NonNull final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, @NonNull final T t) {
        Preconditions.checkNotNull((Object)searchableOrderedMetadataField, (Object)"Field may not be null.");
        Preconditions.checkNotNull((Object)t, (Object)"Value may not be null.");
        return new zzb<Object>(zzx.zzme, searchableOrderedMetadataField, t);
    }
    
    public static <T> Filter in(@NonNull final SearchableCollectionMetadataField<T> searchableCollectionMetadataField, @NonNull final T t) {
        Preconditions.checkNotNull((Object)searchableCollectionMetadataField, (Object)"Field may not be null.");
        Preconditions.checkNotNull((Object)t, (Object)"Value may not be null.");
        return new zzp<Object>(searchableCollectionMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter lessThan(@NonNull final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, @NonNull final T t) {
        Preconditions.checkNotNull((Object)searchableOrderedMetadataField, (Object)"Field may not be null.");
        Preconditions.checkNotNull((Object)t, (Object)"Value may not be null.");
        return new zzb<Object>(zzx.zzmb, searchableOrderedMetadataField, t);
    }
    
    public static <T extends Comparable<T>> Filter lessThanEquals(@NonNull final SearchableOrderedMetadataField<T> searchableOrderedMetadataField, @NonNull final T t) {
        Preconditions.checkNotNull((Object)searchableOrderedMetadataField, (Object)"Field may not be null.");
        Preconditions.checkNotNull((Object)t, (Object)"Value may not be null.");
        return new zzb<Object>(zzx.zzmc, searchableOrderedMetadataField, t);
    }
    
    public static Filter not(@NonNull final Filter filter) {
        Preconditions.checkNotNull((Object)filter, (Object)"Filter may not be null");
        return new zzv(filter);
    }
    
    public static Filter openedByMe() {
        return new zzd(SearchableField.LAST_VIEWED_BY_ME);
    }
    
    public static Filter or(@NonNull final Filter filter, @NonNull final Filter... array) {
        Preconditions.checkNotNull((Object)filter, (Object)"Filter may not be null.");
        Preconditions.checkNotNull((Object)array, (Object)"Additional filters may not be null.");
        return new zzr(zzx.zzmg, filter, array);
    }
    
    public static Filter or(@NonNull final Iterable<Filter> iterable) {
        Preconditions.checkNotNull((Object)iterable, (Object)"Filters may not be null");
        return new zzr(zzx.zzmg, iterable);
    }
    
    public static Filter ownedByMe() {
        return new zzz();
    }
    
    public static Filter sharedWithMe() {
        return new zzd(SearchableField.zzle);
    }
}
