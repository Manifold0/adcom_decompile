// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import org.json.JSONObject;
import android.support.annotation.Nullable;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzajh
{
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
    
    public zzajh(final zzaji zzaji, @Nullable final zzaqw zzaqw, @Nullable final zzwx zzwx, @Nullable final zzxq zzxq, @Nullable final String s, @Nullable final zzxa zzxa, @Nullable final zzpb zzpb, @Nullable final String s2) {
        this(zzaji.zzcgs.zzccv, null, zzaji.zzcos.zzbsn, zzaji.errorCode, zzaji.zzcos.zzbso, zzaji.zzcos.zzces, zzaji.zzcos.orientation, zzaji.zzcos.zzbsu, zzaji.zzcgs.zzccy, zzaji.zzcos.zzceq, null, null, null, zzaji.zzcod, null, zzaji.zzcos.zzcer, zzaji.zzacv, zzaji.zzcos.zzcep, zzaji.zzcoh, zzaji.zzcoi, zzaji.zzcos.zzcev, zzaji.zzcob, null, zzaji.zzcos.zzcfe, zzaji.zzcos.zzcff, zzaji.zzcos.zzcff, zzaji.zzcos.zzcfh, zzaji.zzcos.zzcfi, null, zzaji.zzcos.zzbsr, zzaji.zzcos.zzcfl, zzaji.zzcoq, zzaji.zzcos.zzzl, zzaji.zzcor, zzaji.zzcos.zzcfp, zzaji.zzcos.zzbsp, zzaji.zzcos.zzzm, zzaji.zzcos.zzcfq);
    }
    
    public zzajh(final zzjj zzccv, @Nullable final zzaqw zzbyo, final List<String> list, final int errorCode, final List<String> list2, final List<String> list3, final int orientation, final long zzbsu, final String zzccy, final boolean zzceq, @Nullable final zzwx zzbtw, @Nullable final zzxq zzbtx, @Nullable final String zzbty, final zzwy zzcod, @Nullable final zzxa zzbtz, final long zzcer, final zzjn zzcof, final long zzcep, final long zzcoh, final long zzcoi, final String zzcev, final JSONObject zzcob, @Nullable final zzpb zzcoj, final zzaig zzcfe, final List<String> list4, final List<String> list5, final boolean zzcfh, final zzael zzcfi, @Nullable final String zzcoe, final List<String> list6, final String zzcfl, final zzhs zzcoq, final boolean zzzl, final boolean zzcor, final boolean zzcfp, final List<String> list7, final boolean zzzm, final String zzcfq) {
        this.zzcok = false;
        this.zzcol = false;
        this.zzcom = false;
        this.zzcon = false;
        this.zzcoo = false;
        this.zzcop = false;
        this.zzccv = zzccv;
        this.zzbyo = zzbyo;
        this.zzbsn = zzn(list);
        this.errorCode = errorCode;
        this.zzbso = zzn(list2);
        this.zzces = zzn(list3);
        this.orientation = orientation;
        this.zzbsu = zzbsu;
        this.zzccy = zzccy;
        this.zzceq = zzceq;
        this.zzbtw = zzbtw;
        this.zzbtx = zzbtx;
        this.zzbty = zzbty;
        this.zzcod = zzcod;
        this.zzbtz = zzbtz;
        this.zzcer = zzcer;
        this.zzcof = zzcof;
        this.zzcep = zzcep;
        this.zzcoh = zzcoh;
        this.zzcoi = zzcoi;
        this.zzcev = zzcev;
        this.zzcob = zzcob;
        this.zzcoj = zzcoj;
        this.zzcfe = zzcfe;
        this.zzcog = zzn(list4);
        this.zzcfg = zzn(list5);
        this.zzcfh = zzcfh;
        this.zzcfi = zzcfi;
        this.zzcoe = zzcoe;
        this.zzbsr = zzn(list6);
        this.zzcfl = zzcfl;
        this.zzcoq = zzcoq;
        this.zzzl = zzzl;
        this.zzcor = zzcor;
        this.zzcfp = zzcfp;
        this.zzbsp = zzn(list7);
        this.zzzm = zzzm;
        this.zzcfq = zzcfq;
    }
    
    @Nullable
    private static <T> List<T> zzn(@Nullable final List<T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList((List<? extends T>)list);
    }
    
    public final boolean zzfz() {
        return this.zzbyo != null && this.zzbyo.zzuf() != null && this.zzbyo.zzuf().zzfz();
    }
}
