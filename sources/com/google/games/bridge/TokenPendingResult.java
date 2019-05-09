package com.google.games.bridge;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TokenPendingResult extends PendingResult<TokenResult> {
    private static final String TAG = "TokenPendingResult";
    private CountDownLatch latch = new CountDownLatch(1);
    TokenResult result = new TokenResult();
    private ResultCallback<? super TokenResult> resultCallback;

    @NonNull
    public TokenResult await() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            setResult(null, null, null, 14);
        }
        return getResult();
    }

    @NonNull
    public TokenResult await(long l, @NonNull TimeUnit timeUnit) {
        try {
            if (!this.latch.await(l, timeUnit)) {
                setResult(null, null, null, 15);
            }
        } catch (InterruptedException e) {
            setResult(null, null, null, 14);
        }
        return getResult();
    }

    public void cancel() {
        setResult(null, null, null, 16);
        this.latch.countDown();
    }

    public boolean isCanceled() {
        return getResult() != null && getResult().getStatus().isCanceled();
    }

    public void setResultCallback(@NonNull ResultCallback<? super TokenResult> resultCallback) {
        if (this.latch.getCount() == 0) {
            resultCallback.onResult(getResult());
        } else {
            setCallback(resultCallback);
        }
    }

    public void setResultCallback(@NonNull ResultCallback<? super TokenResult> resultCallback, long l, @NonNull TimeUnit timeUnit) {
        try {
            if (!this.latch.await(l, timeUnit)) {
                setResult(null, null, null, 15);
            }
        } catch (InterruptedException e) {
            setResult(null, null, null, 14);
        }
        resultCallback.onResult(getResult());
    }

    private synchronized void setCallback(ResultCallback<? super TokenResult> callback) {
        this.resultCallback = callback;
    }

    private synchronized ResultCallback<? super TokenResult> getCallback() {
        return this.resultCallback;
    }

    private synchronized void setResult(String authCode, String email, String idToken, int resultCode) {
        String atok;
        String itok;
        String em;
        if (this.result == null || authCode != null) {
            atok = authCode;
        } else {
            atok = this.result.getAuthCode();
        }
        if (this.result == null || idToken != null) {
            itok = idToken;
        } else {
            itok = this.result.getIdToken();
        }
        if (this.result == null || email != null) {
            em = email;
        } else {
            em = this.result.getEmail();
        }
        this.result = new TokenResult(atok, em, itok, resultCode);
    }

    private synchronized TokenResult getResult() {
        return this.result;
    }

    public void setStatus(int status) {
        this.result.setStatus(status);
        this.latch.countDown();
        ResultCallback<? super TokenResult> cb = getCallback();
        TokenResult res = getResult();
        if (cb != null) {
            Log.d(TAG, " Calling onResult for callback: " + cb + " result: " + res);
            getCallback().onResult(res);
        }
    }

    public void setEmail(String email) {
        this.result.setEmail(email);
    }

    public void setAuthCode(String accessToken) {
        this.result.setAuthCode(accessToken);
    }

    public void setIdToken(String idToken) {
        this.result.setIdToken(idToken);
    }
}
