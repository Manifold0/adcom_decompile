// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

final class zzan
{
    final String zza;
    final long zzb;
    final long zzc;
    long zzca;
    final String zzcb;
    final long zzd;
    final long zze;
    final List<zzl> zzg;
    
    zzan(final String s, final zzc zzc) {
        final String zza = zzc.zza;
        final long zzb = zzc.zzb;
        final long zzc2 = zzc.zzc;
        final long zzd = zzc.zzd;
        final long zze = zzc.zze;
        List<zzl> zzg;
        if (zzc.zzg != null) {
            zzg = zzc.zzg;
        }
        else {
            final Map<String, String> zzf = zzc.zzf;
            final ArrayList list = new ArrayList<zzl>(zzf.size());
            final Iterator<Map.Entry<String, String>> iterator = zzf.entrySet().iterator();
            while (true) {
                zzg = (List<zzl>)list;
                if (!iterator.hasNext()) {
                    break;
                }
                final Map.Entry<String, String> entry = iterator.next();
                list.add(new zzl(entry.getKey(), entry.getValue()));
            }
        }
        this(s, zza, zzb, zzc2, zzd, zze, zzg);
        this.zzca = zzc.data.length;
    }
    
    private zzan(String s, final String s2, final long zzb, final long zzc, final long zzd, final long zze, final List<zzl> zzg) {
        this.zzcb = s;
        s = s2;
        if ("".equals(s2)) {
            s = null;
        }
        this.zza = s;
        this.zzb = zzb;
        this.zzc = zzc;
        this.zzd = zzd;
        this.zze = zze;
        this.zzg = zzg;
    }
    
    static zzan zzc(final zzao zzao) throws IOException {
        if (zzam.zzb((InputStream)zzao) != 538247942) {
            throw new IOException();
        }
        return new zzan(zzam.zza(zzao), zzam.zza(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzb(zzao));
    }
    
    final boolean zza(final OutputStream outputStream) {
        while (true) {
        Label_0152:
            while (true) {
                try {
                    zzam.zza(outputStream, 538247942);
                    zzam.zza(outputStream, this.zzcb);
                    if (this.zza == null) {
                        final String zza = "";
                        zzam.zza(outputStream, zza);
                        zzam.zza(outputStream, this.zzb);
                        zzam.zza(outputStream, this.zzc);
                        zzam.zza(outputStream, this.zzd);
                        zzam.zza(outputStream, this.zze);
                        final List<zzl> zzg = this.zzg;
                        if (zzg != null) {
                            zzam.zza(outputStream, zzg.size());
                            for (final zzl zzl : zzg) {
                                zzam.zza(outputStream, zzl.getName());
                                zzam.zza(outputStream, zzl.getValue());
                            }
                            break;
                        }
                        break Label_0152;
                    }
                }
                catch (IOException ex) {
                    zzaf.d("%s", ex.toString());
                    return false;
                }
                final String zza = this.zza;
                continue;
            }
            zzam.zza(outputStream, 0);
            break;
        }
        outputStream.flush();
        return true;
    }
}
