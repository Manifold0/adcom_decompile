package com.google.games.bridge;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class TokenResult implements Result {
    private String authCode;
    private String email;
    private String idToken;
    private Status status;

    TokenResult(String authCode, String email, String idToken, int resultCode) {
        this.status = new Status(resultCode);
        this.authCode = authCode;
        this.idToken = idToken;
        this.email = email;
    }

    public String toString() {
        return "Status: " + this.status + " email: " + (this.email == null ? "<null>" : this.email) + " id:" + (this.idToken == null ? "<null>" : this.idToken) + " access: " + (this.authCode == null ? "<null>" : this.authCode);
    }

    public Status getStatus() {
        return this.status;
    }

    public int getStatusCode() {
        return this.status.getStatusCode();
    }

    public String getAuthCode() {
        return this.authCode == null ? "" : this.authCode;
    }

    public String getIdToken() {
        return this.idToken == null ? "" : this.idToken;
    }

    public String getEmail() {
        return this.email == null ? "" : this.email;
    }

    public void setStatus(int status) {
        this.status = new Status(status);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
