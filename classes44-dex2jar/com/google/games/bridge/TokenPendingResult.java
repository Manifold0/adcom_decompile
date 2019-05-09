// 
// Decompiled by Procyon v0.5.34
// 

package com.google.games.bridge;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.api.PendingResult;

public class TokenPendingResult extends PendingResult<TokenResult>
{
    private static final String TAG = "TokenPendingResult";
    private CountDownLatch latch;
    TokenResult result;
    private ResultCallback<? super TokenResult> resultCallback;
    
    public TokenPendingResult() {
        this.latch = new CountDownLatch(1);
        this.result = new TokenResult();
    }
    
    private ResultCallback<? super TokenResult> getCallback() {
        synchronized (this) {
            return this.resultCallback;
        }
    }
    
    private TokenResult getResult() {
        synchronized (this) {
            return this.result;
        }
    }
    
    private void setCallback(final ResultCallback<? super TokenResult> resultCallback) {
        synchronized (this) {
            this.resultCallback = resultCallback;
        }
    }
    
    private void setResult(String authCode, String email, String idToken, final int n) {
        synchronized (this) {
            if (this.result != null && authCode == null) {
                authCode = this.result.getAuthCode();
            }
            if (this.result != null && idToken == null) {
                idToken = this.result.getIdToken();
            }
            if (this.result != null && email == null) {
                email = this.result.getEmail();
            }
            this.result = new TokenResult(authCode, email, idToken, n);
        }
    }
    
    @NonNull
    public TokenResult await() {
        try {
            this.latch.await();
            return this.getResult();
        }
        catch (InterruptedException ex) {
            this.setResult(null, null, null, 14);
            return this.getResult();
        }
    }
    
    @NonNull
    public TokenResult await(final long n, @NonNull final TimeUnit timeUnit) {
        try {
            if (!this.latch.await(n, timeUnit)) {
                this.setResult(null, null, null, 15);
            }
            return this.getResult();
        }
        catch (InterruptedException ex) {
            this.setResult(null, null, null, 14);
            return this.getResult();
        }
    }
    
    public void cancel() {
        this.setResult(null, null, null, 16);
        this.latch.countDown();
    }
    
    public boolean isCanceled() {
        return this.getResult() != null && this.getResult().getStatus().isCanceled();
    }
    
    public void setAuthCode(final String authCode) {
        this.result.setAuthCode(authCode);
    }
    
    public void setEmail(final String email) {
        this.result.setEmail(email);
    }
    
    public void setIdToken(final String idToken) {
        this.result.setIdToken(idToken);
    }
    
    public void setResultCallback(@NonNull final ResultCallback<? super TokenResult> callback) {
        if (this.latch.getCount() == 0L) {
            callback.onResult((Result)this.getResult());
            return;
        }
        this.setCallback(callback);
    }
    
    public void setResultCallback(@NonNull final ResultCallback<? super TokenResult> resultCallback, final long n, @NonNull final TimeUnit timeUnit) {
        while (true) {
            try {
                if (!this.latch.await(n, timeUnit)) {
                    this.setResult(null, null, null, 15);
                }
                resultCallback.onResult((Result)this.getResult());
            }
            catch (InterruptedException ex) {
                this.setResult(null, null, null, 14);
                continue;
            }
            break;
        }
    }
    
    public void setStatus(final int status) {
        this.result.setStatus(status);
        this.latch.countDown();
        final ResultCallback<? super TokenResult> callback = this.getCallback();
        final TokenResult result = this.getResult();
        if (callback != null) {
            Log.d("TokenPendingResult", " Calling onResult for callback: " + callback + " result: " + result);
            this.getCallback().onResult((Result)result);
        }
    }
}
