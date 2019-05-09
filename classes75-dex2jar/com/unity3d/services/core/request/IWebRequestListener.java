// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.request;

import java.util.List;
import java.util.Map;

public interface IWebRequestListener
{
    void onComplete(final String p0, final String p1, final int p2, final Map<String, List<String>> p3);
    
    void onFailed(final String p0, final String p1);
}
