package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzavb implements zzaug<zzauf> {
    zzavb() {
    }

    private final zzauf zzg(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzawu zzab = zzawu.zzab(zzbah);
            if (zzab instanceof zzawu) {
                zzab = zzab;
                zzazq.zzj(zzab.getVersion(), 0);
                zzavh.zza(zzab.zzxs());
                zzawq zzxs = zzab.zzxs();
                zzaww zzxu = zzxs.zzxu();
                return new zzayp(zzayt.zza(zzavh.zza(zzxu.zzyh()), zzab.zzyc().toByteArray(), zzab.zzyd().toByteArray()), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah zzbah) throws GeneralSecurityException {
        return zzg(zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzawu) {
            zzawu zzawu = (zzawu) zzbcu;
            zzazq.zzj(zzawu.getVersion(), 0);
            zzavh.zza(zzawu.zzxs());
            zzawq zzxs = zzawu.zzxs();
            zzaww zzxu = zzxs.zzxu();
            return new zzayp(zzayt.zza(zzavh.zza(zzxu.zzyh()), zzawu.zzyc().toByteArray(), zzawu.zzyd().toByteArray()), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
