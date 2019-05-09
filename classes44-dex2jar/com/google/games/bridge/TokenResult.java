// 
// Decompiled by Procyon v0.5.34
// 

package com.google.games.bridge;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

public class TokenResult implements Result
{
    private String authCode;
    private String email;
    private String idToken;
    private Status status;
    
    public TokenResult() {
    }
    
    TokenResult(final String authCode, final String email, final String idToken, final int n) {
        this.status = new Status(n);
        this.authCode = authCode;
        this.idToken = idToken;
        this.email = email;
    }
    
    public String getAuthCode() {
        if (this.authCode == null) {
            return "";
        }
        return this.authCode;
    }
    
    public String getEmail() {
        if (this.email == null) {
            return "";
        }
        return this.email;
    }
    
    public String getIdToken() {
        if (this.idToken == null) {
            return "";
        }
        return this.idToken;
    }
    
    public Status getStatus() {
        return this.status;
    }
    
    public int getStatusCode() {
        return this.status.getStatusCode();
    }
    
    public void setAuthCode(final String authCode) {
        this.authCode = authCode;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setIdToken(final String idToken) {
        this.idToken = idToken;
    }
    
    public void setStatus(final int n) {
        this.status = new Status(n);
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("Status: ").append(this.status).append(" email: ");
        String email;
        if (this.email == null) {
            email = "<null>";
        }
        else {
            email = this.email;
        }
        final StringBuilder append2 = append.append(email).append(" id:");
        String idToken;
        if (this.idToken == null) {
            idToken = "<null>";
        }
        else {
            idToken = this.idToken;
        }
        final StringBuilder append3 = append2.append(idToken).append(" access: ");
        String authCode;
        if (this.authCode == null) {
            authCode = "<null>";
        }
        else {
            authCode = this.authCode;
        }
        return append3.append(authCode).toString();
    }
}
