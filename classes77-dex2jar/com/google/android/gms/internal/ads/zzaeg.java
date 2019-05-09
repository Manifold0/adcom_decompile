// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;
import java.util.ArrayList;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;
import android.support.annotation.Nullable;
import android.content.pm.ApplicationInfo;

@zzadh
public final class zzaeg
{
    public final ApplicationInfo applicationInfo;
    public final String zzaco;
    public final String zzacp;
    public final zzang zzacr;
    public final zzjn zzacv;
    public final zzpl zzadj;
    @Nullable
    public final zzlu zzadl;
    public final List<Integer> zzadn;
    public final List<String> zzads;
    public final float zzagu;
    public final boolean zzbss;
    @Nullable
    public final Bundle zzccu;
    public final zzjj zzccv;
    @Nullable
    public final PackageInfo zzccw;
    public final String zzccy;
    public final String zzccz;
    public final Bundle zzcda;
    public final int zzcdb;
    public final Bundle zzcdc;
    public final boolean zzcdd;
    public final int zzcde;
    public final int zzcdf;
    public final String zzcdg;
    public final long zzcdh;
    public final String zzcdi;
    @Nullable
    public final List<String> zzcdj;
    public final List<String> zzcdk;
    public final String zzcdm;
    public final float zzcdn;
    public final int zzcdo;
    public final int zzcdp;
    public final boolean zzcdq;
    public final boolean zzcdr;
    public final boolean zzcdt;
    public final String zzcdu;
    public final int zzcdv;
    public final Bundle zzcdw;
    public final String zzcdx;
    public final boolean zzcdy;
    @Nullable
    public final Bundle zzcdz;
    public final boolean zzced;
    public final String zzcee;
    public final List<String> zzcef;
    public final int zzceg;
    public final boolean zzceh;
    public final boolean zzcei;
    public final boolean zzcej;
    public final ArrayList<String> zzcek;
    public final Future<String> zzcel;
    public final Future<String> zzcem;
    
    public zzaeg(@Nullable final Bundle zzccu, final zzjj zzccv, final zzjn zzacv, final String zzacp, final ApplicationInfo applicationInfo, @Nullable final PackageInfo zzccw, final String zzccy, final String zzccz, final zzang zzacr, final Bundle zzcda, final List<String> zzads, final List<String> zzcdk, final Bundle zzcdc, final boolean zzcdd, final int zzcde, final int zzcdf, final float zzagu, final String zzcdg, final long zzcdh, final String zzcdi, @Nullable final List<String> zzcdj, final String zzaco, final zzpl zzadj, final String zzcdm, final float zzcdn, final boolean zzcdt, final int zzcdo, final int zzcdp, final boolean zzcdq, final boolean zzcdr, final Future<String> zzcel, final String zzcdu, final boolean zzbss, final int zzcdv, final Bundle zzcdw, final String zzcdx, @Nullable final zzlu zzadl, final boolean zzcdy, final Bundle zzcdz, final boolean zzced, final Future<String> zzcem, final List<Integer> zzadn, final String zzcee, final List<String> zzcef, final int zzceg, final boolean zzceh, final boolean zzcei, final boolean zzcej, final ArrayList<String> zzcek) {
        this.zzccu = zzccu;
        this.zzccv = zzccv;
        this.zzacv = zzacv;
        this.zzacp = zzacp;
        this.applicationInfo = applicationInfo;
        this.zzccw = zzccw;
        this.zzccy = zzccy;
        this.zzccz = zzccz;
        this.zzacr = zzacr;
        this.zzcda = zzcda;
        this.zzcdd = zzcdd;
        this.zzcde = zzcde;
        this.zzcdf = zzcdf;
        this.zzagu = zzagu;
        if (zzads != null && zzads.size() > 0) {
            this.zzcdb = 3;
            this.zzads = zzads;
            this.zzcdk = zzcdk;
        }
        else {
            this.zzcdb = 0;
            this.zzads = null;
            this.zzcdk = null;
        }
        this.zzcdc = zzcdc;
        this.zzcdg = zzcdg;
        this.zzcdh = zzcdh;
        this.zzcdi = zzcdi;
        this.zzcdj = zzcdj;
        this.zzaco = zzaco;
        this.zzadj = zzadj;
        this.zzcdm = zzcdm;
        this.zzcdn = zzcdn;
        this.zzcdt = zzcdt;
        this.zzcdo = zzcdo;
        this.zzcdp = zzcdp;
        this.zzcdq = zzcdq;
        this.zzcdr = zzcdr;
        this.zzcel = zzcel;
        this.zzcdu = zzcdu;
        this.zzbss = zzbss;
        this.zzcdv = zzcdv;
        this.zzcdw = zzcdw;
        this.zzcdx = zzcdx;
        this.zzadl = zzadl;
        this.zzcdy = zzcdy;
        this.zzcdz = zzcdz;
        this.zzced = zzced;
        this.zzcem = zzcem;
        this.zzadn = zzadn;
        this.zzcee = zzcee;
        this.zzcef = zzcef;
        this.zzceg = zzceg;
        this.zzceh = zzceh;
        this.zzcei = zzcei;
        this.zzcej = zzcej;
        this.zzcek = zzcek;
    }
}
