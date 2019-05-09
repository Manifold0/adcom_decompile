package com.google.android.gms.internal.ads;

import com.kongregate.android.internal.browser.C0462b;
import java.io.IOException;

public final class zziu extends zzbfc<zziu> {
    private static volatile zziu[] zzaow;
    private zziy zzaox;
    private zzja zzaoy;
    private zzjb zzaoz;
    private zzjc zzapa;
    private zziv zzapb;
    private zziz zzapc;
    private zzix zzapd;
    private Integer zzape;
    private Integer zzapf;
    private zzis zzapg;
    private Integer zzaph;
    private Integer zzapi;
    private Integer zzapj;
    private Integer zzapk;
    private Integer zzapl;
    private Long zzapm;

    public zziu() {
        this.zzaox = null;
        this.zzaoy = null;
        this.zzaoz = null;
        this.zzapa = null;
        this.zzapb = null;
        this.zzapc = null;
        this.zzapd = null;
        this.zzape = null;
        this.zzapf = null;
        this.zzapg = null;
        this.zzaph = null;
        this.zzapi = null;
        this.zzapj = null;
        this.zzapk = null;
        this.zzapl = null;
        this.zzapm = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zziu[] zzhu() {
        if (zzaow == null) {
            synchronized (zzbfg.zzebs) {
                if (zzaow == null) {
                    zzaow = new zziu[0];
                }
            }
        }
        return zzaow;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 42:
                    if (this.zzaox == null) {
                        this.zzaox = new zziy();
                    }
                    zzbez.zza(this.zzaox);
                    continue;
                case 50:
                    if (this.zzaoy == null) {
                        this.zzaoy = new zzja();
                    }
                    zzbez.zza(this.zzaoy);
                    continue;
                case 58:
                    if (this.zzaoz == null) {
                        this.zzaoz = new zzjb();
                    }
                    zzbez.zza(this.zzaoz);
                    continue;
                case 66:
                    if (this.zzapa == null) {
                        this.zzapa = new zzjc();
                    }
                    zzbez.zza(this.zzapa);
                    continue;
                case 74:
                    if (this.zzapb == null) {
                        this.zzapb = new zziv();
                    }
                    zzbez.zza(this.zzapb);
                    continue;
                case 82:
                    if (this.zzapc == null) {
                        this.zzapc = new zziz();
                    }
                    zzbez.zza(this.zzapc);
                    continue;
                case 90:
                    if (this.zzapd == null) {
                        this.zzapd = new zzix();
                    }
                    zzbez.zza(this.zzapd);
                    continue;
                case 96:
                    this.zzape = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 104:
                    this.zzapf = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 114:
                    if (this.zzapg == null) {
                        this.zzapg = new zzis();
                    }
                    zzbez.zza(this.zzapg);
                    continue;
                case C0462b.f362a /*120*/:
                    this.zzaph = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 128:
                    this.zzapi = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 136:
                    this.zzapj = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 144:
                    this.zzapk = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 152:
                    this.zzapl = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 160:
                    this.zzapm = Long.valueOf(zzbez.zzacd());
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzaox != null) {
            zzbfa.zza(5, this.zzaox);
        }
        if (this.zzaoy != null) {
            zzbfa.zza(6, this.zzaoy);
        }
        if (this.zzaoz != null) {
            zzbfa.zza(7, this.zzaoz);
        }
        if (this.zzapa != null) {
            zzbfa.zza(8, this.zzapa);
        }
        if (this.zzapb != null) {
            zzbfa.zza(9, this.zzapb);
        }
        if (this.zzapc != null) {
            zzbfa.zza(10, this.zzapc);
        }
        if (this.zzapd != null) {
            zzbfa.zza(11, this.zzapd);
        }
        if (this.zzape != null) {
            zzbfa.zzm(12, this.zzape.intValue());
        }
        if (this.zzapf != null) {
            zzbfa.zzm(13, this.zzapf.intValue());
        }
        if (this.zzapg != null) {
            zzbfa.zza(14, this.zzapg);
        }
        if (this.zzaph != null) {
            zzbfa.zzm(15, this.zzaph.intValue());
        }
        if (this.zzapi != null) {
            zzbfa.zzm(16, this.zzapi.intValue());
        }
        if (this.zzapj != null) {
            zzbfa.zzm(17, this.zzapj.intValue());
        }
        if (this.zzapk != null) {
            zzbfa.zzm(18, this.zzapk.intValue());
        }
        if (this.zzapl != null) {
            zzbfa.zzm(19, this.zzapl.intValue());
        }
        if (this.zzapm != null) {
            zzbfa.zza(20, this.zzapm.longValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzaox != null) {
            zzr += zzbfa.zzb(5, this.zzaox);
        }
        if (this.zzaoy != null) {
            zzr += zzbfa.zzb(6, this.zzaoy);
        }
        if (this.zzaoz != null) {
            zzr += zzbfa.zzb(7, this.zzaoz);
        }
        if (this.zzapa != null) {
            zzr += zzbfa.zzb(8, this.zzapa);
        }
        if (this.zzapb != null) {
            zzr += zzbfa.zzb(9, this.zzapb);
        }
        if (this.zzapc != null) {
            zzr += zzbfa.zzb(10, this.zzapc);
        }
        if (this.zzapd != null) {
            zzr += zzbfa.zzb(11, this.zzapd);
        }
        if (this.zzape != null) {
            zzr += zzbfa.zzq(12, this.zzape.intValue());
        }
        if (this.zzapf != null) {
            zzr += zzbfa.zzq(13, this.zzapf.intValue());
        }
        if (this.zzapg != null) {
            zzr += zzbfa.zzb(14, this.zzapg);
        }
        if (this.zzaph != null) {
            zzr += zzbfa.zzq(15, this.zzaph.intValue());
        }
        if (this.zzapi != null) {
            zzr += zzbfa.zzq(16, this.zzapi.intValue());
        }
        if (this.zzapj != null) {
            zzr += zzbfa.zzq(17, this.zzapj.intValue());
        }
        if (this.zzapk != null) {
            zzr += zzbfa.zzq(18, this.zzapk.intValue());
        }
        if (this.zzapl != null) {
            zzr += zzbfa.zzq(19, this.zzapl.intValue());
        }
        return this.zzapm != null ? zzr + zzbfa.zze(20, this.zzapm.longValue()) : zzr;
    }
}
