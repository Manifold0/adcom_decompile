// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import android.util.Base64;
import android.os.Parcel;
import java.io.IOException;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
final class zzuc
{
    final zzjj zzaao;
    final int zzbop;
    final String zzye;
    
    @VisibleForTesting
    private zzuc(final zzjj zzaao, final String zzye, final int zzbop) {
        this.zzaao = zzaao;
        this.zzye = zzye;
        this.zzbop = zzbop;
    }
    
    zzuc(final zzty zzty) {
        this(zzty.zzlf(), zzty.getAdUnitId(), zzty.getNetworkType());
    }
    
    static zzuc zzba(String o) throws IOException {
        final String[] split = ((String)o).split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        final Parcel obtain = Parcel.obtain();
        try {
            o = new String(Base64.decode(split[0], 0), "UTF-8");
            final int int1 = Integer.parseInt(split[1]);
            final byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            o = new zzuc((zzjj)zzjj.CREATOR.createFromParcel(obtain), (String)o, int1);
            return (zzuc)o;
        }
        catch (SafeParcelReader$ParseException ex) {}
        catch (IllegalStateException o) {}
        catch (IllegalArgumentException o) {}
    }
    
    final String zzlu() {
        final Parcel obtain = Parcel.obtain();
        try {
            final String encodeToString = Base64.encodeToString(this.zzye.getBytes("UTF-8"), 0);
            final String string = Integer.toString(this.zzbop);
            this.zzaao.writeToParcel(obtain, 0);
            final String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            return new StringBuilder(String.valueOf(encodeToString).length() + 2 + String.valueOf(string).length() + String.valueOf(encodeToString2).length()).append(encodeToString).append("\u0000").append(string).append("\u0000").append(encodeToString2).toString();
        }
        catch (UnsupportedEncodingException ex) {
            zzakb.e("QueueSeed encode failed because UTF-8 is not available.");
            return "";
        }
        finally {
            obtain.recycle();
        }
    }
}
