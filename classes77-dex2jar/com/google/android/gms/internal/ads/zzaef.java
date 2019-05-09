// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.Collections;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.ArrayList;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.content.pm.ApplicationInfo;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@ParametersAreNonnullByDefault
@SafeParcelable$Class(creator = "AdRequestInfoParcelCreator")
public final class zzaef extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaef> CREATOR;
    @SafeParcelable$Field(id = 6)
    public final ApplicationInfo applicationInfo;
    @SafeParcelable$Field(id = 1)
    public final int versionCode;
    @SafeParcelable$Field(id = 28)
    public final String zzaco;
    @SafeParcelable$Field(id = 5)
    public final String zzacp;
    @SafeParcelable$Field(id = 11)
    public final zzang zzacr;
    @SafeParcelable$Field(id = 4)
    public final zzjn zzacv;
    @SafeParcelable$Field(id = 29)
    public final zzpl zzadj;
    @Nullable
    @SafeParcelable$Field(id = 46)
    public final zzlu zzadl;
    @SafeParcelable$Field(id = 53)
    public final List<Integer> zzadn;
    @SafeParcelable$Field(id = 14)
    public final List<String> zzads;
    @SafeParcelable$Field(id = 20)
    public final float zzagu;
    @SafeParcelable$Field(id = 42)
    public final boolean zzbss;
    @Nullable
    @SafeParcelable$Field(id = 2)
    public final Bundle zzccu;
    @SafeParcelable$Field(id = 3)
    public final zzjj zzccv;
    @Nullable
    @SafeParcelable$Field(id = 7)
    public final PackageInfo zzccw;
    @SafeParcelable$Field(id = 8)
    public final String zzccx;
    @SafeParcelable$Field(id = 9)
    public final String zzccy;
    @SafeParcelable$Field(id = 10)
    public final String zzccz;
    @SafeParcelable$Field(id = 12)
    public final Bundle zzcda;
    @SafeParcelable$Field(id = 13)
    public final int zzcdb;
    @SafeParcelable$Field(id = 15)
    public final Bundle zzcdc;
    @SafeParcelable$Field(id = 16)
    public final boolean zzcdd;
    @SafeParcelable$Field(id = 18)
    public final int zzcde;
    @SafeParcelable$Field(id = 19)
    public final int zzcdf;
    @SafeParcelable$Field(id = 21)
    public final String zzcdg;
    @SafeParcelable$Field(id = 25)
    public final long zzcdh;
    @SafeParcelable$Field(id = 26)
    public final String zzcdi;
    @Nullable
    @SafeParcelable$Field(id = 27)
    public final List<String> zzcdj;
    @SafeParcelable$Field(id = 30)
    public final List<String> zzcdk;
    @SafeParcelable$Field(id = 31)
    public final long zzcdl;
    @SafeParcelable$Field(id = 33)
    public final String zzcdm;
    @SafeParcelable$Field(id = 34)
    public final float zzcdn;
    @SafeParcelable$Field(id = 35)
    public final int zzcdo;
    @SafeParcelable$Field(id = 36)
    public final int zzcdp;
    @SafeParcelable$Field(id = 37)
    public final boolean zzcdq;
    @SafeParcelable$Field(id = 38)
    public final boolean zzcdr;
    @SafeParcelable$Field(id = 39)
    public final String zzcds;
    @SafeParcelable$Field(id = 40)
    public final boolean zzcdt;
    @SafeParcelable$Field(id = 41)
    public final String zzcdu;
    @SafeParcelable$Field(id = 43)
    public final int zzcdv;
    @SafeParcelable$Field(id = 44)
    public final Bundle zzcdw;
    @SafeParcelable$Field(id = 45)
    public final String zzcdx;
    @SafeParcelable$Field(id = 47)
    public final boolean zzcdy;
    @SafeParcelable$Field(id = 48)
    public final Bundle zzcdz;
    @Nullable
    @SafeParcelable$Field(id = 49)
    public final String zzcea;
    @Nullable
    @SafeParcelable$Field(id = 50)
    public final String zzceb;
    @Nullable
    @SafeParcelable$Field(id = 51)
    public final String zzcec;
    @SafeParcelable$Field(id = 52)
    public final boolean zzced;
    @SafeParcelable$Field(id = 54)
    public final String zzcee;
    @SafeParcelable$Field(id = 55)
    public final List<String> zzcef;
    @SafeParcelable$Field(id = 56)
    public final int zzceg;
    @SafeParcelable$Field(id = 57)
    public final boolean zzceh;
    @SafeParcelable$Field(id = 58)
    public final boolean zzcei;
    @SafeParcelable$Field(id = 59)
    public final boolean zzcej;
    @SafeParcelable$Field(id = 60)
    public final ArrayList<String> zzcek;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaeh();
    }
    
    @SafeParcelable$Constructor
    zzaef(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final Bundle zzccu, @SafeParcelable$Param(id = 3) final zzjj zzccv, @SafeParcelable$Param(id = 4) final zzjn zzacv, @SafeParcelable$Param(id = 5) final String zzacp, @SafeParcelable$Param(id = 6) final ApplicationInfo applicationInfo, @SafeParcelable$Param(id = 7) final PackageInfo zzccw, @SafeParcelable$Param(id = 8) final String zzccx, @SafeParcelable$Param(id = 9) final String zzccy, @SafeParcelable$Param(id = 10) final String zzccz, @SafeParcelable$Param(id = 11) final zzang zzacr, @SafeParcelable$Param(id = 12) final Bundle zzcda, @SafeParcelable$Param(id = 13) final int zzcdb, @SafeParcelable$Param(id = 14) final List<String> zzads, @SafeParcelable$Param(id = 15) final Bundle zzcdc, @SafeParcelable$Param(id = 16) final boolean zzcdd, @SafeParcelable$Param(id = 18) final int zzcde, @SafeParcelable$Param(id = 19) final int zzcdf, @SafeParcelable$Param(id = 20) final float zzagu, @SafeParcelable$Param(id = 21) final String zzcdg, @SafeParcelable$Param(id = 25) final long zzcdh, @SafeParcelable$Param(id = 26) final String zzcdi, @SafeParcelable$Param(id = 27) final List<String> list, @SafeParcelable$Param(id = 28) final String zzaco, @SafeParcelable$Param(id = 29) final zzpl zzadj, @SafeParcelable$Param(id = 30) final List<String> list2, @SafeParcelable$Param(id = 31) final long zzcdl, @SafeParcelable$Param(id = 33) final String zzcdm, @SafeParcelable$Param(id = 34) final float zzcdn, @SafeParcelable$Param(id = 40) final boolean zzcdt, @SafeParcelable$Param(id = 35) final int zzcdo, @SafeParcelable$Param(id = 36) final int zzcdp, @SafeParcelable$Param(id = 37) final boolean zzcdq, @SafeParcelable$Param(id = 38) final boolean zzcdr, @SafeParcelable$Param(id = 39) final String zzcds, @SafeParcelable$Param(id = 41) final String zzcdu, @SafeParcelable$Param(id = 42) final boolean zzbss, @SafeParcelable$Param(id = 43) final int zzcdv, @SafeParcelable$Param(id = 44) final Bundle zzcdw, @SafeParcelable$Param(id = 45) final String zzcdx, @SafeParcelable$Param(id = 46) final zzlu zzadl, @SafeParcelable$Param(id = 47) final boolean zzcdy, @SafeParcelable$Param(id = 48) final Bundle zzcdz, @SafeParcelable$Param(id = 49) final String zzcea, @SafeParcelable$Param(id = 50) final String zzceb, @SafeParcelable$Param(id = 51) final String zzcec, @SafeParcelable$Param(id = 52) final boolean zzced, @SafeParcelable$Param(id = 53) final List<Integer> zzadn, @SafeParcelable$Param(id = 54) final String zzcee, @SafeParcelable$Param(id = 55) final List<String> zzcef, @SafeParcelable$Param(id = 56) final int zzceg, @SafeParcelable$Param(id = 57) final boolean zzceh, @SafeParcelable$Param(id = 58) final boolean zzcei, @SafeParcelable$Param(id = 59) final boolean zzcej, @SafeParcelable$Param(id = 60) final ArrayList<String> zzcek) {
        this.versionCode = versionCode;
        this.zzccu = zzccu;
        this.zzccv = zzccv;
        this.zzacv = zzacv;
        this.zzacp = zzacp;
        this.applicationInfo = applicationInfo;
        this.zzccw = zzccw;
        this.zzccx = zzccx;
        this.zzccy = zzccy;
        this.zzccz = zzccz;
        this.zzacr = zzacr;
        this.zzcda = zzcda;
        this.zzcdb = zzcdb;
        this.zzads = zzads;
        List<String> zzcdk;
        if (list2 == null) {
            zzcdk = Collections.emptyList();
        }
        else {
            zzcdk = Collections.unmodifiableList((List<? extends String>)list2);
        }
        this.zzcdk = zzcdk;
        this.zzcdc = zzcdc;
        this.zzcdd = zzcdd;
        this.zzcde = zzcde;
        this.zzcdf = zzcdf;
        this.zzagu = zzagu;
        this.zzcdg = zzcdg;
        this.zzcdh = zzcdh;
        this.zzcdi = zzcdi;
        List<String> zzcdj;
        if (list == null) {
            zzcdj = Collections.emptyList();
        }
        else {
            zzcdj = Collections.unmodifiableList((List<? extends String>)list);
        }
        this.zzcdj = zzcdj;
        this.zzaco = zzaco;
        this.zzadj = zzadj;
        this.zzcdl = zzcdl;
        this.zzcdm = zzcdm;
        this.zzcdn = zzcdn;
        this.zzcdt = zzcdt;
        this.zzcdo = zzcdo;
        this.zzcdp = zzcdp;
        this.zzcdq = zzcdq;
        this.zzcdr = zzcdr;
        this.zzcds = zzcds;
        this.zzcdu = zzcdu;
        this.zzbss = zzbss;
        this.zzcdv = zzcdv;
        this.zzcdw = zzcdw;
        this.zzcdx = zzcdx;
        this.zzadl = zzadl;
        this.zzcdy = zzcdy;
        this.zzcdz = zzcdz;
        this.zzcea = zzcea;
        this.zzceb = zzceb;
        this.zzcec = zzcec;
        this.zzced = zzced;
        this.zzadn = zzadn;
        this.zzcee = zzcee;
        this.zzcef = zzcef;
        this.zzceg = zzceg;
        this.zzceh = zzceh;
        this.zzcei = zzcei;
        this.zzcej = zzcej;
        this.zzcek = zzcek;
    }
    
    private zzaef(@Nullable final Bundle bundle, final zzjj zzjj, final zzjn zzjn, final String s, final ApplicationInfo applicationInfo, @Nullable final PackageInfo packageInfo, final String s2, final String s3, final String s4, final zzang zzang, final Bundle bundle2, final int n, final List<String> list, final List<String> list2, final Bundle bundle3, final boolean b, final int n2, final int n3, final float n4, final String s5, final long n5, final String s6, @Nullable final List<String> list3, final String s7, final zzpl zzpl, final long n6, final String s8, final float n7, final boolean b2, final int n8, final int n9, final boolean b3, final boolean b4, final String s9, final String s10, final boolean b5, final int n10, final Bundle bundle4, final String s11, @Nullable final zzlu zzlu, final boolean b6, final Bundle bundle5, final String s12, final String s13, final String s14, final boolean b7, final List<Integer> list4, final String s15, final List<String> list5, final int n11, final boolean b8, final boolean b9, final boolean b10, final ArrayList<String> list6) {
        this(24, bundle, zzjj, zzjn, s, applicationInfo, packageInfo, s2, s3, s4, zzang, bundle2, n, list, bundle3, b, n2, n3, n4, s5, n5, s6, list3, s7, zzpl, list2, n6, s8, n7, b2, n8, n9, b3, b4, s9, s10, b5, n10, bundle4, s11, zzlu, b6, bundle5, s12, s13, s14, b7, list4, s15, list5, n11, b8, b9, b10, list6);
    }
    
    public zzaef(final zzaeg zzaeg, final long n, final String s, final String s2, final String s3) {
        this(zzaeg.zzccu, zzaeg.zzccv, zzaeg.zzacv, zzaeg.zzacp, zzaeg.applicationInfo, zzaeg.zzccw, zzano.zza(zzaeg.zzcem, ""), zzaeg.zzccy, zzaeg.zzccz, zzaeg.zzacr, zzaeg.zzcda, zzaeg.zzcdb, zzaeg.zzads, zzaeg.zzcdk, zzaeg.zzcdc, zzaeg.zzcdd, zzaeg.zzcde, zzaeg.zzcdf, zzaeg.zzagu, zzaeg.zzcdg, zzaeg.zzcdh, zzaeg.zzcdi, zzaeg.zzcdj, zzaeg.zzaco, zzaeg.zzadj, n, zzaeg.zzcdm, zzaeg.zzcdn, zzaeg.zzcdt, zzaeg.zzcdo, zzaeg.zzcdp, zzaeg.zzcdq, zzaeg.zzcdr, zzano.zza(zzaeg.zzcel, "", 1L, TimeUnit.SECONDS), zzaeg.zzcdu, zzaeg.zzbss, zzaeg.zzcdv, zzaeg.zzcdw, zzaeg.zzcdx, zzaeg.zzadl, zzaeg.zzcdy, zzaeg.zzcdz, s, s2, s3, zzaeg.zzced, zzaeg.zzadn, zzaeg.zzcee, zzaeg.zzcef, zzaeg.zzceg, zzaeg.zzceh, zzaeg.zzcei, zzaeg.zzcej, zzaeg.zzcek);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzccu, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzccv, n, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzacv, n, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzacp, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.applicationInfo, n, false);
        SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.zzccw, n, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzccx, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzccy, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzccz, false);
        SafeParcelWriter.writeParcelable(parcel, 11, (Parcelable)this.zzacr, n, false);
        SafeParcelWriter.writeBundle(parcel, 12, this.zzcda, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzcdb);
        SafeParcelWriter.writeStringList(parcel, 14, (List)this.zzads, false);
        SafeParcelWriter.writeBundle(parcel, 15, this.zzcdc, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzcdd);
        SafeParcelWriter.writeInt(parcel, 18, this.zzcde);
        SafeParcelWriter.writeInt(parcel, 19, this.zzcdf);
        SafeParcelWriter.writeFloat(parcel, 20, this.zzagu);
        SafeParcelWriter.writeString(parcel, 21, this.zzcdg, false);
        SafeParcelWriter.writeLong(parcel, 25, this.zzcdh);
        SafeParcelWriter.writeString(parcel, 26, this.zzcdi, false);
        SafeParcelWriter.writeStringList(parcel, 27, (List)this.zzcdj, false);
        SafeParcelWriter.writeString(parcel, 28, this.zzaco, false);
        SafeParcelWriter.writeParcelable(parcel, 29, (Parcelable)this.zzadj, n, false);
        SafeParcelWriter.writeStringList(parcel, 30, (List)this.zzcdk, false);
        SafeParcelWriter.writeLong(parcel, 31, this.zzcdl);
        SafeParcelWriter.writeString(parcel, 33, this.zzcdm, false);
        SafeParcelWriter.writeFloat(parcel, 34, this.zzcdn);
        SafeParcelWriter.writeInt(parcel, 35, this.zzcdo);
        SafeParcelWriter.writeInt(parcel, 36, this.zzcdp);
        SafeParcelWriter.writeBoolean(parcel, 37, this.zzcdq);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzcdr);
        SafeParcelWriter.writeString(parcel, 39, this.zzcds, false);
        SafeParcelWriter.writeBoolean(parcel, 40, this.zzcdt);
        SafeParcelWriter.writeString(parcel, 41, this.zzcdu, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzbss);
        SafeParcelWriter.writeInt(parcel, 43, this.zzcdv);
        SafeParcelWriter.writeBundle(parcel, 44, this.zzcdw, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzcdx, false);
        SafeParcelWriter.writeParcelable(parcel, 46, (Parcelable)this.zzadl, n, false);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzcdy);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzcdz, false);
        SafeParcelWriter.writeString(parcel, 49, this.zzcea, false);
        SafeParcelWriter.writeString(parcel, 50, this.zzceb, false);
        SafeParcelWriter.writeString(parcel, 51, this.zzcec, false);
        SafeParcelWriter.writeBoolean(parcel, 52, this.zzced);
        SafeParcelWriter.writeIntegerList(parcel, 53, (List)this.zzadn, false);
        SafeParcelWriter.writeString(parcel, 54, this.zzcee, false);
        SafeParcelWriter.writeStringList(parcel, 55, (List)this.zzcef, false);
        SafeParcelWriter.writeInt(parcel, 56, this.zzceg);
        SafeParcelWriter.writeBoolean(parcel, 57, this.zzceh);
        SafeParcelWriter.writeBoolean(parcel, 58, this.zzcei);
        SafeParcelWriter.writeBoolean(parcel, 59, this.zzcej);
        SafeParcelWriter.writeStringList(parcel, 60, (List)this.zzcek, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
