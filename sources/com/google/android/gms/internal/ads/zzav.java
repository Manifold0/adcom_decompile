package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.io.UnsupportedEncodingException;

public class zzav extends zzr<String> {
    private final Object mLock = new Object();
    private zzz<String> zzck;

    public zzav(int i, String str, zzz<String> zzz, zzy zzy) {
        super(i, str, zzy);
        this.zzck = zzz;
    }

    protected final zzx<String> zza(zzp zzp) {
        Object str;
        try {
            byte[] bArr = zzp.data;
            String str2 = "ISO-8859-1";
            String str3 = (String) zzp.zzab.get("Content-Type");
            if (str3 != null) {
                String[] split = str3.split(";");
                for (int i = 1; i < split.length; i++) {
                    String[] split2 = split[i].trim().split(RequestParameters.EQUAL);
                    if (split2.length == 2 && split2[0].equals("charset")) {
                        str3 = split2[1];
                        break;
                    }
                }
            }
            str3 = str2;
            str = new String(bArr, str3);
        } catch (UnsupportedEncodingException e) {
            str = new String(zzp.data);
        }
        return zzx.zza(str, zzap.zzb(zzp));
    }

    protected /* synthetic */ void zza(Object obj) {
        zzh((String) obj);
    }

    protected void zzh(String str) {
        synchronized (this.mLock) {
            zzz zzz = this.zzck;
        }
        if (zzz != null) {
            zzz.zzb(str);
        }
    }
}
