// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.Comparator;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DetectedActivityCreator")
@SafeParcelable$Reserved({ 1000 })
public class DetectedActivity extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<DetectedActivity> CREATOR;
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    private static final Comparator<DetectedActivity> zzo;
    private static final int[] zzp;
    private static final int[] zzq;
    private static final int[] zzr;
    @SafeParcelable$Field(id = 1)
    private int zzi;
    @SafeParcelable$Field(id = 2)
    private int zzs;
    
    static {
        zzo = new zzh();
        zzp = new int[] { 9, 10 };
        zzq = new int[] { 0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17, 18, 19 };
        zzr = new int[] { 0, 1, 2, 3, 7, 8, 16, 17 };
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    @SafeParcelable$Constructor
    public DetectedActivity(@SafeParcelable$Param(id = 1) final int zzi, @SafeParcelable$Param(id = 2) final int zzs) {
        this.zzi = zzi;
        this.zzs = zzs;
    }
    
    public static void zzb(final int n) {
        boolean b = false;
        final int[] zzr = DetectedActivity.zzr;
        for (int length = zzr.length, i = 0; i < length; ++i) {
            if (zzr[i] == n) {
                b = true;
            }
        }
        if (!b) {
            Log.w("DetectedActivity", new StringBuilder(81).append(n).append(" is not a valid DetectedActivity supported by Activity Transition API.").toString());
        }
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final DetectedActivity detectedActivity = (DetectedActivity)o;
            if (this.zzi != detectedActivity.zzi || this.zzs != detectedActivity.zzs) {
                return false;
            }
        }
        return true;
    }
    
    public int getConfidence() {
        return this.zzs;
    }
    
    public int getType() {
        final int zzi = this.zzi;
        int n;
        if (zzi > 19 || (n = zzi) < 0) {
            n = 4;
        }
        return n;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzi, this.zzs });
    }
    
    public String toString() {
        final int type = this.getType();
        String string = null;
        switch (type) {
            default: {
                string = Integer.toString(type);
                break;
            }
            case 0: {
                string = "IN_VEHICLE";
                break;
            }
            case 1: {
                string = "ON_BICYCLE";
                break;
            }
            case 2: {
                string = "ON_FOOT";
                break;
            }
            case 3: {
                string = "STILL";
                break;
            }
            case 4: {
                string = "UNKNOWN";
                break;
            }
            case 5: {
                string = "TILTING";
                break;
            }
            case 7: {
                string = "WALKING";
                break;
            }
            case 8: {
                string = "RUNNING";
                break;
            }
            case 16: {
                string = "IN_ROAD_VEHICLE";
                break;
            }
            case 17: {
                string = "IN_RAIL_VEHICLE";
                break;
            }
            case 18: {
                string = "IN_TWO_WHEELER_VEHICLE";
                break;
            }
            case 19: {
                string = "IN_FOUR_WHEELER_VEHICLE";
                break;
            }
        }
        return new StringBuilder(String.valueOf(string).length() + 48).append("DetectedActivity [type=").append(string).append(", confidence=").append(this.zzs).append("]").toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzi);
        SafeParcelWriter.writeInt(parcel, 2, this.zzs);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
