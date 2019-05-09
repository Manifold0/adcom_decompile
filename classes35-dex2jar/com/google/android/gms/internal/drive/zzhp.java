// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.metadata.internal.zzs;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.drive.metadata.internal.zzi;
import com.google.android.gms.drive.metadata.internal.zzt;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.drive.metadata.internal.zzo;
import com.google.android.gms.drive.metadata.internal.zzu;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.MetadataField;

public final class zzhp
{
    public static final MetadataField<DriveId> zziv;
    public static final MetadataField<String> zziw;
    public static final zzhs zzix;
    public static final MetadataField<String> zziy;
    public static final MetadataField<String> zziz;
    public static final MetadataField<String> zzja;
    public static final MetadataField<Long> zzjb;
    public static final MetadataField<String> zzjc;
    public static final MetadataField<Boolean> zzjd;
    public static final MetadataField<String> zzje;
    public static final MetadataField<Boolean> zzjf;
    public static final MetadataField<Boolean> zzjg;
    public static final MetadataField<Boolean> zzjh;
    public static final MetadataField<Boolean> zzji;
    public static final MetadataField<Boolean> zzjj;
    public static final zzht zzjk;
    public static final MetadataField<Boolean> zzjl;
    public static final MetadataField<Boolean> zzjm;
    public static final MetadataField<Boolean> zzjn;
    public static final MetadataField<Boolean> zzjo;
    public static final MetadataField<Boolean> zzjp;
    public static final MetadataField<Boolean> zzjq;
    public static final MetadataField<Boolean> zzjr;
    public static final zzhu zzjs;
    public static final MetadataField<String> zzjt;
    public static final zzb<String> zzju;
    public static final zzu zzjv;
    public static final zzu zzjw;
    public static final zzo zzjx;
    public static final zzhv zzjy;
    public static final zzhx zzjz;
    public static final MetadataField<BitmapTeleporter> zzka;
    public static final zzhy zzkb;
    public static final zzhz zzkc;
    public static final MetadataField<String> zzkd;
    public static final MetadataField<String> zzke;
    public static final MetadataField<String> zzkf;
    public static final com.google.android.gms.drive.metadata.internal.zzb zzkg;
    public static final MetadataField<String> zzkh;
    public static final MetadataField<String> zzki;
    public static final zzhw zzkj;
    public static final MetadataField<String> zzkk;
    public static final MetadataField<Boolean> zzkl;
    
    static {
        zziv = zzij.zzkt;
        zziw = new zzt("alternateLink", 4300000);
        zzix = new zzhs(5000000);
        zziy = new zzt("description", 4300000);
        zziz = new zzt("embedLink", 4300000);
        zzja = new zzt("fileExtension", 4300000);
        zzjb = new zzi("fileSize", 4300000);
        zzjc = new zzt("folderColorRgb", 7500000);
        zzjd = new com.google.android.gms.drive.metadata.internal.zzb("hasThumbnail", 4300000);
        zzje = new zzt("indexableText", 4300000);
        zzjf = new com.google.android.gms.drive.metadata.internal.zzb("isAppData", 4300000);
        zzjg = new com.google.android.gms.drive.metadata.internal.zzb("isCopyable", 4300000);
        zzjh = new com.google.android.gms.drive.metadata.internal.zzb("isEditable", 4100000);
        zzji = new zzhq("isExplicitlyTrashed", Collections.singleton("trashed"), Collections.emptySet(), 7000000);
        zzjj = new com.google.android.gms.drive.metadata.internal.zzb("isLocalContentUpToDate", 7800000);
        zzjk = new zzht("isPinned", 4100000);
        zzjl = new com.google.android.gms.drive.metadata.internal.zzb("isOpenable", 7200000);
        zzjm = new com.google.android.gms.drive.metadata.internal.zzb("isRestricted", 4300000);
        zzjn = new com.google.android.gms.drive.metadata.internal.zzb("isShared", 4300000);
        zzjo = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosFolder", 7000000);
        zzjp = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosRootFolder", 7000000);
        zzjq = new com.google.android.gms.drive.metadata.internal.zzb("isTrashable", 4400000);
        zzjr = new com.google.android.gms.drive.metadata.internal.zzb("isViewed", 4300000);
        zzjs = new zzhu(4100000);
        zzjt = new zzt("originalFilename", 4300000);
        zzju = new zzs("ownerNames", 4300000);
        zzjv = new zzu("lastModifyingUser", 6000000);
        zzjw = new zzu("sharingUser", 6000000);
        zzjx = new zzo(4100000);
        zzjy = new zzhv("quotaBytesUsed", 4300000);
        zzjz = new zzhx("starred", 4100000);
        zzka = new zzhr("thumbnail", Collections.emptySet(), Collections.emptySet(), 4400000);
        zzkb = new zzhy("title", 4100000);
        zzkc = new zzhz("trashed", 4100000);
        zzkd = new zzt("webContentLink", 4300000);
        zzke = new zzt("webViewLink", 4300000);
        zzkf = new zzt("uniqueIdentifier", 5000000);
        zzkg = new com.google.android.gms.drive.metadata.internal.zzb("writersCanShare", 6000000);
        zzkh = new zzt("role", 6000000);
        zzki = new zzt("md5Checksum", 7000000);
        zzkj = new zzhw(7000000);
        zzkk = new zzt("recencyReason", 8000000);
        zzkl = new com.google.android.gms.drive.metadata.internal.zzb("subscribed", 8000000);
    }
}
