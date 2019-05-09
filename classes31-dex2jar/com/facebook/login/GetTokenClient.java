// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.os.Bundle;
import android.content.Context;
import com.facebook.internal.PlatformServiceClient;

final class GetTokenClient extends PlatformServiceClient
{
    GetTokenClient(final Context context, final String s) {
        super(context, 65536, 65537, 20121101, s);
    }
    
    @Override
    protected void populateRequestBundle(final Bundle bundle) {
    }
}
