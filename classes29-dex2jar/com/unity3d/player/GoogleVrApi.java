// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi
{
    private static AtomicReference a;
    
    static {
        GoogleVrApi.a = new AtomicReference();
    }
    
    private GoogleVrApi() {
    }
    
    static void a() {
        GoogleVrApi.a.set(null);
    }
    
    static void a(final f f) {
        GoogleVrApi.a.compareAndSet(null, new GoogleVrProxy(f));
    }
    
    static GoogleVrProxy b() {
        return GoogleVrApi.a.get();
    }
    
    public static GoogleVrVideo getGoogleVrVideo() {
        return GoogleVrApi.a.get();
    }
}
