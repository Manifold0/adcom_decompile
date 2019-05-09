// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.zzb;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import android.content.Context;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "AdSizeParcelCreator")
@SafeParcelable$Reserved({ 1 })
public class zzjn extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzjn> CREATOR;
    @SafeParcelable$Field(id = 3)
    public final int height;
    @SafeParcelable$Field(id = 4)
    public final int heightPixels;
    @SafeParcelable$Field(id = 6)
    public final int width;
    @SafeParcelable$Field(id = 7)
    public final int widthPixels;
    @SafeParcelable$Field(id = 2)
    public final String zzarb;
    @SafeParcelable$Field(id = 5)
    public final boolean zzarc;
    @SafeParcelable$Field(id = 8)
    public final zzjn[] zzard;
    @SafeParcelable$Field(id = 9)
    public final boolean zzare;
    @SafeParcelable$Field(id = 10)
    public final boolean zzarf;
    @SafeParcelable$Field(id = 11)
    public boolean zzarg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzjo();
    }
    
    public zzjn() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }
    
    public zzjn(final Context context, final AdSize adSize) {
        this(context, new AdSize[] { adSize });
    }
    
    public zzjn(final Context context, final AdSize[] array) {
        final AdSize adSize = array[0];
        this.zzarc = false;
        this.zzarf = adSize.isFluid();
        if (this.zzarf) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        }
        else {
            this.width = adSize.getWidth();
            this.height = adSize.getHeight();
        }
        boolean b;
        if (this.width == -1) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (this.height == -2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = 0;
        Label_0170: {
            if (b) {
                zzkb.zzif();
                while (true) {
                    Label_0329: {
                        if (!zzamu.zzbi(context)) {
                            break Label_0329;
                        }
                        zzkb.zzif();
                        if (!zzamu.zzbj(context)) {
                            break Label_0329;
                        }
                        final int widthPixels = displayMetrics.widthPixels;
                        zzkb.zzif();
                        this.widthPixels = widthPixels - zzamu.zzbk(context);
                        final double n = this.widthPixels / displayMetrics.density;
                        width = (int)n;
                        if (n - (int)n >= 0.01) {
                            ++width;
                        }
                        break Label_0170;
                    }
                    this.widthPixels = displayMetrics.widthPixels;
                    continue;
                }
            }
            width = this.width;
            zzkb.zzif();
            this.widthPixels = zzamu.zza(displayMetrics, this.width);
        }
        int n2;
        if (b2) {
            n2 = zzd(displayMetrics);
        }
        else {
            n2 = this.height;
        }
        zzkb.zzif();
        this.heightPixels = zzamu.zza(displayMetrics, n2);
        if (b || b2) {
            this.zzarb = new StringBuilder(26).append(width).append("x").append(n2).append("_as").toString();
        }
        else if (this.zzarf) {
            this.zzarb = "320x50_mb";
        }
        else {
            this.zzarb = adSize.toString();
        }
        if (array.length > 1) {
            this.zzard = new zzjn[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.zzard[i] = new zzjn(context, array[i]);
            }
        }
        else {
            this.zzard = null;
        }
        this.zzare = false;
        this.zzarg = false;
    }
    
    public zzjn(final zzjn zzjn, final zzjn[] array) {
        this(zzjn.zzarb, zzjn.height, zzjn.heightPixels, zzjn.zzarc, zzjn.width, zzjn.widthPixels, array, zzjn.zzare, zzjn.zzarf, zzjn.zzarg);
    }
    
    @SafeParcelable$Constructor
    zzjn(@SafeParcelable$Param(id = 2) final String zzarb, @SafeParcelable$Param(id = 3) final int height, @SafeParcelable$Param(id = 4) final int heightPixels, @SafeParcelable$Param(id = 5) final boolean zzarc, @SafeParcelable$Param(id = 6) final int width, @SafeParcelable$Param(id = 7) final int widthPixels, @SafeParcelable$Param(id = 8) final zzjn[] zzard, @SafeParcelable$Param(id = 9) final boolean zzare, @SafeParcelable$Param(id = 10) final boolean zzarf, @SafeParcelable$Param(id = 11) final boolean zzarg) {
        this.zzarb = zzarb;
        this.height = height;
        this.heightPixels = heightPixels;
        this.zzarc = zzarc;
        this.width = width;
        this.widthPixels = widthPixels;
        this.zzard = zzard;
        this.zzare = zzare;
        this.zzarf = zzarf;
        this.zzarg = zzarg;
    }
    
    public static int zzb(final DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }
    
    public static int zzc(final DisplayMetrics displayMetrics) {
        return (int)(zzd(displayMetrics) * displayMetrics.density);
    }
    
    private static int zzd(final DisplayMetrics displayMetrics) {
        final int n = (int)(displayMetrics.heightPixels / displayMetrics.density);
        if (n <= 400) {
            return 32;
        }
        if (n <= 720) {
            return 50;
        }
        return 90;
    }
    
    public static zzjn zzf(final Context context) {
        return new zzjn("320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }
    
    public static zzjn zzhx() {
        return new zzjn("reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzarc);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, (Parcelable[])this.zzard, n, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzare);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzarf);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzarg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final AdSize zzhy() {
        return zzb.zza(this.width, this.height, this.zzarb);
    }
}
