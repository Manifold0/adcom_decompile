// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.api.Response;

public class CredentialRequestResponse extends Response<CredentialRequestResult>
{
    public Credential getCredential() {
        return ((CredentialRequestResult)this.getResult()).getCredential();
    }
}
