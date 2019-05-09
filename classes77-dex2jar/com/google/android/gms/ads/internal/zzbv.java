// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.ads.zzakv;
import com.google.android.gms.internal.ads.zzaky;
import com.google.android.gms.internal.ads.zzakw;
import com.google.android.gms.internal.ads.zzakx;
import com.google.android.gms.internal.ads.zzakz;
import com.google.android.gms.internal.ads.zzala;
import android.os.Build$VERSION;
import com.google.android.gms.internal.ads.zzajv;
import com.google.android.gms.internal.ads.zzamq;
import com.google.android.gms.internal.ads.zzalb;
import com.google.android.gms.internal.ads.zzuq;
import com.google.android.gms.internal.ads.zzaor;
import com.google.android.gms.internal.ads.zzaqg;
import com.google.android.gms.internal.ads.zzaiy;
import com.google.android.gms.internal.ads.zzhr;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzamh;
import com.google.android.gms.internal.ads.zzxg;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.overlay.zzu;
import com.google.android.gms.internal.ads.zzamg;
import com.google.android.gms.internal.ads.zzwg;
import com.google.android.gms.internal.ads.zztw;
import com.google.android.gms.internal.ads.zzaok;
import com.google.android.gms.internal.ads.zzagc;
import com.google.android.gms.internal.ads.zzalk;
import com.google.android.gms.internal.ads.zznp;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.ads.zzhd;
import com.google.android.gms.internal.ads.zzhc;
import com.google.android.gms.internal.ads.zzajm;
import com.google.android.gms.internal.ads.zzgg;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.internal.ads.zzadi;
import com.google.android.gms.ads.internal.overlay.zza;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzbv
{
    private static final Object sLock;
    private static zzbv zzabf;
    private final zza zzabg;
    private final zzadi zzabh;
    private final zzl zzabi;
    private final zzabl zzabj;
    private final zzakk zzabk;
    private final zzarc zzabl;
    private final zzakq zzabm;
    private final zzgg zzabn;
    private final zzajm zzabo;
    private final zzhc zzabp;
    private final zzhd zzabq;
    private final Clock zzabr;
    private final zzad zzabs;
    private final zznp zzabt;
    private final zzalk zzabu;
    private final zzagc zzabv;
    private final zzaok zzabw;
    private final zztw zzabx;
    private final zzwg zzaby;
    private final zzamg zzabz;
    private final zzu zzaca;
    private final zzv zzacb;
    private final zzxg zzacc;
    private final zzamh zzacd;
    private final zzbb zzace;
    private final zzaan zzacf;
    private final zzhr zzacg;
    private final zzaiy zzach;
    private final zzaqg zzaci;
    private final zzaor zzacj;
    private final zzuq zzack;
    private final zzalb zzacl;
    private final zzamq zzacm;
    private final zzajv zzacn;
    
    static {
        sLock = new Object();
        final zzbv zzabf = new zzbv();
        synchronized (zzbv.sLock) {
            zzbv.zzabf = zzabf;
        }
    }
    
    protected zzbv() {
        this.zzabg = new zza();
        this.zzabh = new zzadi();
        this.zzabi = new zzl();
        this.zzabj = new zzabl();
        this.zzabk = new zzakk();
        this.zzabl = new zzarc();
        final int sdk_INT = Build$VERSION.SDK_INT;
        zzakv zzabm;
        if (sdk_INT >= 21) {
            zzabm = new zzala();
        }
        else if (sdk_INT >= 19) {
            zzabm = new zzakz();
        }
        else if (sdk_INT >= 18) {
            zzabm = new zzakx();
        }
        else if (sdk_INT >= 17) {
            zzabm = new zzakw();
        }
        else if (sdk_INT >= 16) {
            zzabm = new zzaky();
        }
        else {
            zzabm = new zzakv();
        }
        this.zzabm = zzabm;
        this.zzabn = new zzgg();
        this.zzabo = new zzajm();
        this.zzacn = new zzajv();
        this.zzabp = new zzhc();
        this.zzabq = new zzhd();
        this.zzabr = DefaultClock.getInstance();
        this.zzabs = new zzad();
        this.zzabt = new zznp();
        this.zzabu = new zzalk();
        this.zzabv = new zzagc();
        this.zzack = new zzuq();
        this.zzabw = new zzaok();
        this.zzabx = new zztw();
        this.zzaby = new zzwg();
        this.zzabz = new zzamg();
        this.zzaca = new zzu();
        this.zzacb = new zzv();
        this.zzacc = new zzxg();
        this.zzacd = new zzamh();
        this.zzace = new zzbb();
        this.zzacf = new zzaan();
        this.zzacg = new zzhr();
        this.zzach = new zzaiy();
        this.zzaci = new zzaqg();
        this.zzacj = new zzaor();
        this.zzacl = new zzalb();
        this.zzacm = new zzamq();
    }
    
    private static zzbv zzef() {
        synchronized (zzbv.sLock) {
            return zzbv.zzabf;
        }
    }
    
    public static zzadi zzeg() {
        return zzef().zzabh;
    }
    
    public static zza zzeh() {
        return zzef().zzabg;
    }
    
    public static zzl zzei() {
        return zzef().zzabi;
    }
    
    public static zzabl zzej() {
        return zzef().zzabj;
    }
    
    public static zzakk zzek() {
        return zzef().zzabk;
    }
    
    public static zzarc zzel() {
        return zzef().zzabl;
    }
    
    public static zzakq zzem() {
        return zzef().zzabm;
    }
    
    public static zzgg zzen() {
        return zzef().zzabn;
    }
    
    public static zzajm zzeo() {
        return zzef().zzabo;
    }
    
    public static zzajv zzep() {
        return zzef().zzacn;
    }
    
    public static zzhd zzeq() {
        return zzef().zzabq;
    }
    
    public static Clock zzer() {
        return zzef().zzabr;
    }
    
    public static zzad zzes() {
        return zzef().zzabs;
    }
    
    public static zznp zzet() {
        return zzef().zzabt;
    }
    
    public static zzalk zzeu() {
        return zzef().zzabu;
    }
    
    public static zzagc zzev() {
        return zzef().zzabv;
    }
    
    public static zzaok zzew() {
        return zzef().zzabw;
    }
    
    public static zztw zzex() {
        return zzef().zzabx;
    }
    
    public static zzwg zzey() {
        return zzef().zzaby;
    }
    
    public static zzamg zzez() {
        return zzef().zzabz;
    }
    
    public static zzaan zzfa() {
        return zzef().zzacf;
    }
    
    public static zzu zzfb() {
        return zzef().zzaca;
    }
    
    public static zzv zzfc() {
        return zzef().zzacb;
    }
    
    public static zzxg zzfd() {
        return zzef().zzacc;
    }
    
    public static zzamh zzfe() {
        return zzef().zzacd;
    }
    
    public static zzaqg zzff() {
        return zzef().zzaci;
    }
    
    public static zzaor zzfg() {
        return zzef().zzacj;
    }
    
    public static zzaiy zzfh() {
        return zzef().zzach;
    }
    
    public static zzuq zzfi() {
        return zzef().zzack;
    }
    
    public static zzalb zzfj() {
        return zzef().zzacl;
    }
    
    public static zzamq zzfk() {
        return zzef().zzacm;
    }
}
