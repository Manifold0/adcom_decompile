// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzavb implements zzaug<zzauf>
{
    private final zzauf zzg(final zzbah zzbah) throws GeneralSecurityException {
        zzawu zzab;
        try {
            zzab = zzawu.zzab(zzbah);
            if (!(zzab instanceof zzawu)) {
                throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", (Throwable)zzbbu);
        }
        final zzawu zzawu = zzab;
        zzazq.zzj(zzawu.getVersion(), 0);
        zzavh.zza(zzawu.zzxs());
        final zzawq zzxs = zzawu.zzxs();
        final zzaww zzxu = zzxs.zzxu();
        return new zzayp(zzayt.zza(zzavh.zza(zzxu.zzyh()), zzawu.zzyc().toByteArray(), zzawu.zzyd().toByteArray()), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
