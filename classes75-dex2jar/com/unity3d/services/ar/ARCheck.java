// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar;

public class ARCheck
{
    public static boolean isFrameworkPresent() {
        try {
            Class.forName("com.google.ar.core.Session");
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
