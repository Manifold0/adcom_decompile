package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class zzd implements Runnable {
    private static final Logger zzbd = new Logger("RevokeAccessOperation", new String[0]);
    private final String zzbe;
    private final StatusPendingResult zzbf = new StatusPendingResult(null);

    private zzd(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzbe = str;
    }

    public final void run() {
        Result result;
        Result result2;
        IOException iOException;
        Logger logger;
        Exception exception;
        Status status = Status.RESULT_INTERNAL_ERROR;
        String valueOf;
        String valueOf2;
        Object obj;
        try {
            valueOf = String.valueOf("https://accounts.google.com/o/oauth2/revoke?token=");
            valueOf2 = String.valueOf(this.zzbe);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                result = Status.RESULT_SUCCESS;
            } else {
                zzbd.e("Unable to revoke access!", new Object[0]);
                obj = status;
            }
            try {
                zzbd.d("Response Code: " + responseCode, new Object[0]);
            } catch (IOException e) {
                IOException iOException2 = e;
                result2 = result;
                iOException = iOException2;
                logger = zzbd;
                valueOf = "IOException when revoking access: ";
                valueOf2 = String.valueOf(iOException.toString());
                logger.e(valueOf2.length() == 0 ? valueOf.concat(valueOf2) : new String(valueOf), new Object[0]);
                obj = status;
                this.zzbf.setResult(result);
            } catch (Exception e2) {
                Exception exception2 = e2;
                result2 = result;
                exception = exception2;
                logger = zzbd;
                valueOf = "Exception when revoking access: ";
                valueOf2 = String.valueOf(exception.toString());
                logger.e(valueOf2.length() == 0 ? valueOf.concat(valueOf2) : new String(valueOf), new Object[0]);
                obj = status;
                this.zzbf.setResult(result);
            }
        } catch (IOException e3) {
            iOException = e3;
            logger = zzbd;
            valueOf = "IOException when revoking access: ";
            valueOf2 = String.valueOf(iOException.toString());
            if (valueOf2.length() == 0) {
            }
            logger.e(valueOf2.length() == 0 ? valueOf.concat(valueOf2) : new String(valueOf), new Object[0]);
            obj = status;
            this.zzbf.setResult(result);
        } catch (Exception e4) {
            exception = e4;
            logger = zzbd;
            valueOf = "Exception when revoking access: ";
            valueOf2 = String.valueOf(exception.toString());
            if (valueOf2.length() == 0) {
            }
            logger.e(valueOf2.length() == 0 ? valueOf.concat(valueOf2) : new String(valueOf), new Object[0]);
            obj = status;
            this.zzbf.setResult(result);
        }
        this.zzbf.setResult(result);
    }

    public static PendingResult<Status> zzc(String str) {
        if (str == null) {
            return PendingResults.immediateFailedResult(new Status(4), null);
        }
        Object zzd = new zzd(str);
        new Thread(zzd).start();
        return zzd.zzbf;
    }
}
