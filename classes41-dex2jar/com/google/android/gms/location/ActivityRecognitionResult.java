// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import java.util.Iterator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Collections;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ActivityRecognitionResultCreator")
@SafeParcelable$Reserved({ 1000 })
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<ActivityRecognitionResult> CREATOR;
    @SafeParcelable$Field(id = 5)
    private Bundle extras;
    @SafeParcelable$Field(id = 1)
    private List<DetectedActivity> zze;
    @SafeParcelable$Field(id = 2)
    private long zzf;
    @SafeParcelable$Field(id = 3)
    private long zzg;
    @SafeParcelable$Field(id = 4)
    private int zzh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @VisibleForTesting
    public ActivityRecognitionResult(final DetectedActivity detectedActivity, final long n, final long n2) {
        this(detectedActivity, n, n2, 0, null);
    }
    
    private ActivityRecognitionResult(final DetectedActivity detectedActivity, final long n, final long n2, final int n3, final Bundle bundle) {
        this(Collections.singletonList(detectedActivity), n, n2, 0, null);
    }
    
    public ActivityRecognitionResult(final List<DetectedActivity> list, final long n, final long n2) {
        this(list, n, n2, 0, null);
    }
    
    @SafeParcelable$Constructor
    public ActivityRecognitionResult(@SafeParcelable$Param(id = 1) final List<DetectedActivity> zze, @SafeParcelable$Param(id = 2) final long zzf, @SafeParcelable$Param(id = 3) final long zzg, @SafeParcelable$Param(id = 4) final int zzh, @SafeParcelable$Param(id = 5) final Bundle extras) {
        final boolean b = true;
        Preconditions.checkArgument(zze != null && zze.size() > 0, (Object)"Must have at least 1 detected activity");
        Preconditions.checkArgument(zzf > 0L && zzg > 0L && b, (Object)"Must set times");
        this.zze = zze;
        this.zzf = zzf;
        this.zzg = zzg;
        this.zzh = zzh;
        this.extras = extras;
    }
    
    public static ActivityRecognitionResult extractResult(final Intent intent) {
        while (true) {
            Label_0059: {
                if (!hasResult(intent)) {
                    break Label_0059;
                }
                final Object value = intent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
                ActivityRecognitionResult activityRecognitionResult;
                if (value instanceof byte[]) {
                    activityRecognitionResult = (ActivityRecognitionResult)SafeParcelableSerializer.deserializeFromBytes((byte[])value, (Parcelable$Creator)ActivityRecognitionResult.CREATOR);
                }
                else {
                    if (!(value instanceof ActivityRecognitionResult)) {
                        break Label_0059;
                    }
                    activityRecognitionResult = (ActivityRecognitionResult)value;
                }
                if (activityRecognitionResult != null) {
                    return activityRecognitionResult;
                }
                final List<ActivityRecognitionResult> zza = zza(intent);
                if (zza == null || zza.isEmpty()) {
                    return null;
                }
                return zza.get(zza.size() - 1);
            }
            ActivityRecognitionResult activityRecognitionResult = null;
            continue;
        }
    }
    
    public static boolean hasResult(final Intent intent) {
        if (intent != null) {
            if (intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
                return true;
            }
            final List<ActivityRecognitionResult> zza = zza(intent);
            if (zza != null && !zza.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    @Nullable
    private static List<ActivityRecognitionResult> zza(final Intent intent) {
        if (intent == null || !intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) {
            return null;
        }
        return (List<ActivityRecognitionResult>)SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", (Parcelable$Creator)ActivityRecognitionResult.CREATOR);
    }
    
    private static boolean zza(final Bundle bundle, final Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return true;
        }
        if ((bundle == null && bundle2 != null) || (bundle != null && bundle2 == null)) {
            return false;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (final String s : bundle.keySet()) {
            if (!bundle2.containsKey(s)) {
                return false;
            }
            if (bundle.get(s) == null) {
                if (bundle2.get(s) != null) {
                    return false;
                }
                continue;
            }
            else if (bundle.get(s) instanceof Bundle) {
                if (!zza(bundle.getBundle(s), bundle2.getBundle(s))) {
                    return false;
                }
                continue;
            }
            else {
                if (!bundle.get(s).equals(bundle2.get(s))) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult)o;
            if (this.zzf != activityRecognitionResult.zzf || this.zzg != activityRecognitionResult.zzg || this.zzh != activityRecognitionResult.zzh || !Objects.equal((Object)this.zze, (Object)activityRecognitionResult.zze) || !zza(this.extras, activityRecognitionResult.extras)) {
                return false;
            }
        }
        return true;
    }
    
    public int getActivityConfidence(final int n) {
        for (final DetectedActivity detectedActivity : this.zze) {
            if (detectedActivity.getType() == n) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }
    
    public long getElapsedRealtimeMillis() {
        return this.zzg;
    }
    
    public DetectedActivity getMostProbableActivity() {
        return this.zze.get(0);
    }
    
    public List<DetectedActivity> getProbableActivities() {
        return this.zze;
    }
    
    public long getTime() {
        return this.zzf;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzf, this.zzg, this.zzh, this.zze, this.extras });
    }
    
    public String toString() {
        final String value = String.valueOf(this.zze);
        return new StringBuilder(String.valueOf(value).length() + 124).append("ActivityRecognitionResult [probableActivities=").append(value).append(", timeMillis=").append(this.zzf).append(", elapsedRealtimeMillis=").append(this.zzg).append("]").toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.zze, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzf);
        SafeParcelWriter.writeLong(parcel, 3, this.zzg);
        SafeParcelWriter.writeInt(parcel, 4, this.zzh);
        SafeParcelWriter.writeBundle(parcel, 5, this.extras, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
