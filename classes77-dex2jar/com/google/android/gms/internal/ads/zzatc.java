// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;
import android.net.http.SslError;
import android.net.Uri;
import android.text.TextUtils;
import android.os.Bundle;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzatc
{
    private static final String[] zzdbo;
    private static final String[] zzdbp;
    
    static {
        zzdbo = new String[] { "UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS" };
        zzdbp = new String[] { "NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID" };
    }
    
    private static void zzd(String host, final String s, final String s2) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzazy)) {
            return;
        }
        final Bundle bundle = new Bundle();
        bundle.putString("err", host);
        bundle.putString("code", s);
        while (true) {
            Label_0075: {
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    break Label_0075;
                }
                final Uri parse = Uri.parse(s2);
                if (parse.getHost() == null) {
                    break Label_0075;
                }
                host = parse.getHost();
                bundle.putString("host", host);
                return;
            }
            host = "";
            continue;
        }
    }
    
    final void zzb(@Nullable final SslError sslError) {
        if (sslError == null) {
            return;
        }
        final int primaryError = sslError.getPrimaryError();
        String value;
        if (primaryError >= 0 && primaryError < zzatc.zzdbp.length) {
            value = zzatc.zzdbp[primaryError];
        }
        else {
            value = String.valueOf(primaryError);
        }
        zzd("ssl_err", value, sslError.getUrl());
    }
    
    final void zze(final int n, final String s) {
        String value;
        if (n < 0 && -n - 1 < zzatc.zzdbo.length) {
            value = zzatc.zzdbo[-n - 1];
        }
        else {
            value = String.valueOf(n);
        }
        zzd("http_err", value, s);
    }
}
