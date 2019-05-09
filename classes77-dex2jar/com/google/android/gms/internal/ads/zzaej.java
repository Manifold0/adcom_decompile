// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.text.TextUtils;
import java.util.Collections;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@ParametersAreNonnullByDefault
@SafeParcelable$Class(creator = "AdResponseParcelCreator")
public final class zzaej extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaej> CREATOR;
    @SafeParcelable$Field(id = 5)
    public final int errorCode;
    @SafeParcelable$Field(id = 12)
    public final int orientation;
    @SafeParcelable$Field(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(id = 30)
    public final String zzamj;
    @SafeParcelable$Field(id = 23)
    public final boolean zzare;
    @SafeParcelable$Field(id = 31)
    public final boolean zzarf;
    @SafeParcelable$Field(id = 32)
    public final boolean zzarg;
    @SafeParcelable$Field(id = 4)
    public final List<String> zzbsn;
    @SafeParcelable$Field(id = 6)
    public final List<String> zzbso;
    @SafeParcelable$Field(id = 52)
    public final List<String> zzbsp;
    @SafeParcelable$Field(id = 40)
    public final List<String> zzbsr;
    @SafeParcelable$Field(id = 42)
    public final boolean zzbss;
    @SafeParcelable$Field(id = 11)
    public final long zzbsu;
    private zzaef zzbuc;
    @SafeParcelable$Field(id = 2)
    public final String zzbyq;
    @SafeParcelable$Field(id = 24)
    public final boolean zzcdd;
    @SafeParcelable$Field(id = 38)
    public final boolean zzcdr;
    @Nullable
    @SafeParcelable$Field(id = 39)
    public String zzcds;
    @SafeParcelable$Field(id = 47)
    public final boolean zzced;
    @SafeParcelable$Field(id = 3)
    public String zzceo;
    @SafeParcelable$Field(id = 7)
    public final long zzcep;
    @SafeParcelable$Field(id = 8)
    public final boolean zzceq;
    @SafeParcelable$Field(id = 9)
    public final long zzcer;
    @SafeParcelable$Field(id = 10)
    public final List<String> zzces;
    @SafeParcelable$Field(id = 13)
    public final String zzcet;
    @SafeParcelable$Field(id = 14)
    public final long zzceu;
    @SafeParcelable$Field(id = 15)
    public final String zzcev;
    @SafeParcelable$Field(id = 18)
    public final boolean zzcew;
    @SafeParcelable$Field(id = 19)
    public final String zzcex;
    @SafeParcelable$Field(id = 21)
    public final String zzcey;
    @SafeParcelable$Field(id = 22)
    public final boolean zzcez;
    @SafeParcelable$Field(id = 25)
    public final boolean zzcfa;
    @SafeParcelable$Field(id = 26)
    public final boolean zzcfb;
    @SafeParcelable$Field(id = 28)
    private zzaev zzcfc;
    @SafeParcelable$Field(id = 29)
    public String zzcfd;
    @Nullable
    @SafeParcelable$Field(id = 33)
    public final zzaig zzcfe;
    @Nullable
    @SafeParcelable$Field(id = 34)
    public final List<String> zzcff;
    @Nullable
    @SafeParcelable$Field(id = 35)
    public final List<String> zzcfg;
    @SafeParcelable$Field(id = 36)
    public final boolean zzcfh;
    @Nullable
    @SafeParcelable$Field(id = 37)
    public final zzael zzcfi;
    @Nullable
    @SafeParcelable$Field(id = 43)
    public final String zzcfj;
    @Nullable
    @SafeParcelable$Field(id = 44)
    public final zzaiq zzcfk;
    @Nullable
    @SafeParcelable$Field(id = 45)
    public final String zzcfl;
    @SafeParcelable$Field(id = 46)
    public final boolean zzcfm;
    @SafeParcelable$Field(id = 48)
    private Bundle zzcfn;
    @SafeParcelable$Field(id = 50)
    public final int zzcfo;
    @SafeParcelable$Field(id = 51)
    public final boolean zzcfp;
    @Nullable
    @SafeParcelable$Field(id = 54)
    public final String zzcfq;
    @SafeParcelable$Field(id = 49)
    public final boolean zzzl;
    @SafeParcelable$Field(id = 53)
    public final boolean zzzm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaek();
    }
    
    public zzaej(final int n) {
        this(19, null, null, null, n, null, -1L, false, -1L, null, -1L, -1, null, -1L, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null, true, false, null, false, 0, false, null, false, null);
    }
    
    public zzaej(final int n, final long n2) {
        this(19, null, null, null, n, null, -1L, false, -1L, null, n2, -1, null, -1L, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null, true, false, null, false, 0, false, null, false, null);
    }
    
    @SafeParcelable$Constructor
    zzaej(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final String zzbyq, @SafeParcelable$Param(id = 3) final String zzceo, @SafeParcelable$Param(id = 4) final List<String> list, @SafeParcelable$Param(id = 5) final int errorCode, @SafeParcelable$Param(id = 6) final List<String> list2, @SafeParcelable$Param(id = 7) final long zzcep, @SafeParcelable$Param(id = 8) final boolean zzceq, @SafeParcelable$Param(id = 9) final long zzcer, @SafeParcelable$Param(id = 10) final List<String> list3, @SafeParcelable$Param(id = 11) final long zzbsu, @SafeParcelable$Param(id = 12) final int orientation, @SafeParcelable$Param(id = 13) final String zzcet, @SafeParcelable$Param(id = 14) final long zzceu, @SafeParcelable$Param(id = 15) final String zzcev, @SafeParcelable$Param(id = 18) final boolean zzcew, @SafeParcelable$Param(id = 19) final String zzcex, @SafeParcelable$Param(id = 21) final String zzcey, @SafeParcelable$Param(id = 22) final boolean zzcez, @SafeParcelable$Param(id = 23) final boolean zzare, @SafeParcelable$Param(id = 24) final boolean zzcdd, @SafeParcelable$Param(id = 25) final boolean zzcfa, @SafeParcelable$Param(id = 26) final boolean zzcfb, @SafeParcelable$Param(id = 28) final zzaev zzcfc, @SafeParcelable$Param(id = 29) final String zzcfd, @SafeParcelable$Param(id = 30) final String zzamj, @SafeParcelable$Param(id = 31) final boolean zzarf, @SafeParcelable$Param(id = 32) final boolean zzarg, @SafeParcelable$Param(id = 33) final zzaig zzcfe, @SafeParcelable$Param(id = 34) final List<String> zzcff, @SafeParcelable$Param(id = 35) final List<String> zzcfg, @SafeParcelable$Param(id = 36) final boolean zzcfh, @SafeParcelable$Param(id = 37) final zzael zzcfi, @SafeParcelable$Param(id = 38) final boolean zzcdr, @SafeParcelable$Param(id = 39) final String zzcds, @SafeParcelable$Param(id = 40) final List<String> zzbsr, @SafeParcelable$Param(id = 42) final boolean zzbss, @SafeParcelable$Param(id = 43) final String zzcfj, @SafeParcelable$Param(id = 44) final zzaiq zzcfk, @SafeParcelable$Param(id = 45) final String zzcfl, @SafeParcelable$Param(id = 46) final boolean zzcfm, @SafeParcelable$Param(id = 47) final boolean zzced, @SafeParcelable$Param(id = 48) final Bundle zzcfn, @SafeParcelable$Param(id = 49) final boolean zzzl, @SafeParcelable$Param(id = 50) final int zzcfo, @SafeParcelable$Param(id = 51) final boolean zzcfp, @SafeParcelable$Param(id = 52) final List<String> list4, @SafeParcelable$Param(id = 53) final boolean zzzm, @SafeParcelable$Param(id = 54) final String zzcfq) {
        this.versionCode = versionCode;
        this.zzbyq = zzbyq;
        this.zzceo = zzceo;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.zzbsn = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.zzbso = unmodifiableList2;
        this.zzcep = zzcep;
        this.zzceq = zzceq;
        this.zzcer = zzcer;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.zzces = unmodifiableList3;
        this.zzbsu = zzbsu;
        this.orientation = orientation;
        this.zzcet = zzcet;
        this.zzceu = zzceu;
        this.zzcev = zzcev;
        this.zzcew = zzcew;
        this.zzcex = zzcex;
        this.zzcey = zzcey;
        this.zzcez = zzcez;
        this.zzare = zzare;
        this.zzcdd = zzcdd;
        this.zzcfa = zzcfa;
        this.zzcfm = zzcfm;
        this.zzcfb = zzcfb;
        this.zzcfc = zzcfc;
        this.zzcfd = zzcfd;
        this.zzamj = zzamj;
        if (this.zzceo == null && this.zzcfc != null) {
            final zzafj zzafj = this.zzcfc.zza(com.google.android.gms.internal.ads.zzafj.CREATOR);
            if (zzafj != null && !TextUtils.isEmpty((CharSequence)zzafj.zzbgu)) {
                this.zzceo = zzafj.zzbgu;
            }
        }
        this.zzarf = zzarf;
        this.zzarg = zzarg;
        this.zzcfe = zzcfe;
        this.zzcff = zzcff;
        this.zzcfg = zzcfg;
        this.zzcfh = zzcfh;
        this.zzcfi = zzcfi;
        this.zzcdr = zzcdr;
        this.zzcds = zzcds;
        this.zzbsr = zzbsr;
        this.zzbss = zzbss;
        this.zzcfj = zzcfj;
        this.zzcfk = zzcfk;
        this.zzcfl = zzcfl;
        this.zzced = zzced;
        this.zzcfn = zzcfn;
        this.zzzl = zzzl;
        this.zzcfo = zzcfo;
        this.zzcfp = zzcfp;
        List<String> unmodifiableList4;
        if (list4 != null) {
            unmodifiableList4 = Collections.unmodifiableList((List<? extends String>)list4);
        }
        else {
            unmodifiableList4 = null;
        }
        this.zzbsp = unmodifiableList4;
        this.zzzm = zzzm;
        this.zzcfq = zzcfq;
    }
    
    public zzaej(final zzaef zzbuc, final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final String s5, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final String s6, final boolean b7, final boolean b8, final zzaig zzaig, final List<String> list4, final List<String> list5, final boolean b9, final zzael zzael, final boolean b10, final String s7, final List<String> list6, final boolean b11, final String s8, final zzaiq zzaiq, final String s9, final boolean b12, final boolean b13, final boolean b14, final int n6, final boolean b15, final List<String> list7, final boolean b16, final String s10) {
        this(19, s, s2, list, -2, list2, n, b, -1L, list3, n3, n4, s3, n5, s4, false, null, s5, b2, b3, b4, b5, false, null, null, s6, b7, b8, zzaig, list4, list5, b9, zzael, b10, s7, list6, b11, s8, zzaiq, s9, b12, b13, null, b14, n6, b15, list7, b16, s10);
        this.zzbuc = zzbuc;
    }
    
    public zzaej(final zzaef zzbuc, final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final boolean b2, final String s5, final String s6, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final String s7, final boolean b8, final boolean b9, final zzaig zzaig, final List<String> list4, final List<String> list5, final boolean b10, final zzael zzael, final boolean b11, final String s8, final List<String> list6, final boolean b12, final String s9, final zzaiq zzaiq, final String s10, final boolean b13, final boolean b14, final boolean b15, final int n6, final boolean b16, final List<String> list7, final boolean b17, final String s11) {
        this(19, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3, n5, s4, b2, s5, s6, b3, b4, b5, b6, b7, null, null, s7, b8, b9, zzaig, list4, list5, b10, zzael, b11, s8, list6, b12, s9, zzaiq, s10, b13, b14, null, b15, 0, b16, list7, b17, s11);
        this.zzbuc = zzbuc;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        if (this.zzbuc != null && this.zzbuc.versionCode >= 9 && !TextUtils.isEmpty((CharSequence)this.zzceo)) {
            this.zzcfc = new zzaev((SafeParcelable)new zzafj(this.zzceo));
            this.zzceo = null;
        }
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzbyq, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzceo, false);
        SafeParcelWriter.writeStringList(parcel, 4, (List)this.zzbsn, false);
        SafeParcelWriter.writeInt(parcel, 5, this.errorCode);
        SafeParcelWriter.writeStringList(parcel, 6, (List)this.zzbso, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzcep);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzceq);
        SafeParcelWriter.writeLong(parcel, 9, this.zzcer);
        SafeParcelWriter.writeStringList(parcel, 10, (List)this.zzces, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzbsu);
        SafeParcelWriter.writeInt(parcel, 12, this.orientation);
        SafeParcelWriter.writeString(parcel, 13, this.zzcet, false);
        SafeParcelWriter.writeLong(parcel, 14, this.zzceu);
        SafeParcelWriter.writeString(parcel, 15, this.zzcev, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzcew);
        SafeParcelWriter.writeString(parcel, 19, this.zzcex, false);
        SafeParcelWriter.writeString(parcel, 21, this.zzcey, false);
        SafeParcelWriter.writeBoolean(parcel, 22, this.zzcez);
        SafeParcelWriter.writeBoolean(parcel, 23, this.zzare);
        SafeParcelWriter.writeBoolean(parcel, 24, this.zzcdd);
        SafeParcelWriter.writeBoolean(parcel, 25, this.zzcfa);
        SafeParcelWriter.writeBoolean(parcel, 26, this.zzcfb);
        SafeParcelWriter.writeParcelable(parcel, 28, (Parcelable)this.zzcfc, n, false);
        SafeParcelWriter.writeString(parcel, 29, this.zzcfd, false);
        SafeParcelWriter.writeString(parcel, 30, this.zzamj, false);
        SafeParcelWriter.writeBoolean(parcel, 31, this.zzarf);
        SafeParcelWriter.writeBoolean(parcel, 32, this.zzarg);
        SafeParcelWriter.writeParcelable(parcel, 33, (Parcelable)this.zzcfe, n, false);
        SafeParcelWriter.writeStringList(parcel, 34, (List)this.zzcff, false);
        SafeParcelWriter.writeStringList(parcel, 35, (List)this.zzcfg, false);
        SafeParcelWriter.writeBoolean(parcel, 36, this.zzcfh);
        SafeParcelWriter.writeParcelable(parcel, 37, (Parcelable)this.zzcfi, n, false);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzcdr);
        SafeParcelWriter.writeString(parcel, 39, this.zzcds, false);
        SafeParcelWriter.writeStringList(parcel, 40, (List)this.zzbsr, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzbss);
        SafeParcelWriter.writeString(parcel, 43, this.zzcfj, false);
        SafeParcelWriter.writeParcelable(parcel, 44, (Parcelable)this.zzcfk, n, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzcfl, false);
        SafeParcelWriter.writeBoolean(parcel, 46, this.zzcfm);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzced);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzcfn, false);
        SafeParcelWriter.writeBoolean(parcel, 49, this.zzzl);
        SafeParcelWriter.writeInt(parcel, 50, this.zzcfo);
        SafeParcelWriter.writeBoolean(parcel, 51, this.zzcfp);
        SafeParcelWriter.writeStringList(parcel, 52, (List)this.zzbsp, false);
        SafeParcelWriter.writeBoolean(parcel, 53, this.zzzm);
        SafeParcelWriter.writeString(parcel, 54, this.zzcfq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
