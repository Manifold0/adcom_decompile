// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.request;

import java.util.List;
import java.util.Map;

public interface IWebRequestProgressListener
{
    void onRequestProgress(final String p0, final long p1, final long p2);
    
    void onRequestStart(final String p0, final long p1, final int p2, final Map<String, List<String>> p3);
}
