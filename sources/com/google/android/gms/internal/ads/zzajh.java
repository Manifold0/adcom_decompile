package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzajh {
    public final int errorCode;
    public final int orientation;
    public final List<String> zzbsn;
    public final List<String> zzbso;
    public final List<String> zzbsp;
    @Nullable
    public final List<String> zzbsr;
    public final long zzbsu;
    @Nullable
    public final zzwx zzbtw;
    @Nullable
    public final zzxq zzbtx;
    @Nullable
    public final String zzbty;
    @Nullable
    public final zzxa zzbtz;
    @Nullable
    public final zzaqw zzbyo;
    public final zzjj zzccv;
    public final String zzccy;
    private final long zzcep;
    public final boolean zzceq;
    private final long zzcer;
    public final List<String> zzces;
    public final String zzcev;
    @Nullable
    public final zzaig zzcfe;
    @Nullable
    public final List<String> zzcfg;
    public final boolean zzcfh;
    private final zzael zzcfi;
    public final String zzcfl;
    public final boolean zzcfp;
    private final String zzcfq;
    public final JSONObject zzcob;
    public boolean zzcoc;
    public final zzwy zzcod;
    @Nullable
    public final String zzcoe;
    public final zzjn zzcof;
    @Nullable
    public final List<String> zzcog;
    public final long zzcoh;
    public final long zzcoi;
    @Nullable
    public final zzpb zzcoj;
    public boolean zzcok;
    public boolean zzcol;
    public boolean zzcom;
    public boolean zzcon;
    public boolean zzcoo;
    public boolean zzcop;
    public final zzhs zzcoq;
    public final boolean zzcor;
    public final boolean zzzl;
    public final boolean zzzm;

    public zzajh(zzaji zzaji, @Nullable zzaqw zzaqw, @Nullable zzwx zzwx, @Nullable zzxq zzxq, @Nullable String str, @Nullable zzxa zzxa, @Nullable zzpb zzpb, @Nullable String str2) {
        this(zzaji.zzcgs.zzccv, null, zzaji.zzcos.zzbsn, zzaji.errorCode, zzaji.zzcos.zzbso, zzaji.zzcos.zzces, zzaji.zzcos.orientation, zzaji.zzcos.zzbsu, zzaji.zzcgs.zzccy, zzaji.zzcos.zzceq, null, null, null, zzaji.zzcod, null, zzaji.zzcos.zzcer, zzaji.zzacv, zzaji.zzcos.zzcep, zzaji.zzcoh, zzaji.zzcoi, zzaji.zzcos.zzcev, zzaji.zzcob, null, zzaji.zzcos.zzcfe, zzaji.zzcos.zzcff, zzaji.zzcos.zzcff, zzaji.zzcos.zzcfh, zzaji.zzcos.zzcfi, null, zzaji.zzcos.zzbsr, zzaji.zzcos.zzcfl, zzaji.zzcoq, zzaji.zzcos.zzzl, zzaji.zzcor, zzaji.zzcos.zzcfp, zzaji.zzcos.zzbsp, zzaji.zzcos.zzzm, zzaji.zzcos.zzcfq);
    }

    public zzajh(zzjj zzjj, @Nullable zzaqw zzaqw, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, @Nullable zzwx zzwx, @Nullable zzxq zzxq, @Nullable String str2, zzwy zzwy, @Nullable zzxa zzxa, long j2, zzjn zzjn, long j3, long j4, long j5, String str3, JSONObject jSONObject, @Nullable zzpb zzpb, zzaig zzaig, List<String> list4, List<String> list5, boolean z2, zzael zzael, @Nullable String str4, List<String> list6, String str5, zzhs zzhs, boolean z3, boolean z4, boolean z5, List<String> list7, boolean z6, String str6) {
        this.zzcok = false;
        this.zzcol = false;
        this.zzcom = false;
        this.zzcon = false;
        this.zzcoo = false;
        this.zzcop = false;
        this.zzccv = zzjj;
        this.zzbyo = zzaqw;
        this.zzbsn = zzn(list);
        this.errorCode = i;
        this.zzbso = zzn(list2);
        this.zzces = zzn(list3);
        this.orientation = i2;
        this.zzbsu = j;
        this.zzccy = str;
        this.zzceq = z;
        this.zzbtw = zzwx;
        this.zzbtx = zzxq;
        this.zzbty = str2;
        this.zzcod = zzwy;
        this.zzbtz = zzxa;
        this.zzcer = j2;
        this.zzcof = zzjn;
        this.zzcep = j3;
        this.zzcoh = j4;
        this.zzcoi = j5;
        this.zzcev = str3;
        this.zzcob = jSONObject;
        this.zzcoj = zzpb;
        this.zzcfe = zzaig;
        this.zzcog = zzn(list4);
        this.zzcfg = zzn(list5);
        this.zzcfh = z2;
        this.zzcfi = zzael;
        this.zzcoe = str4;
        this.zzbsr = zzn(list6);
        this.zzcfl = str5;
        this.zzcoq = zzhs;
        this.zzzl = z3;
        this.zzcor = z4;
        this.zzcfp = z5;
        this.zzbsp = zzn(list7);
        this.zzzm = z6;
        this.zzcfq = str6;
    }

    @Nullable
    private static <T> List<T> zzn(@Nullable List<T> list) {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    public final boolean zzfz() {
        return (this.zzbyo == null || this.zzbyo.zzuf() == null) ? false : this.zzbyo.zzuf().zzfz();
    }
}
