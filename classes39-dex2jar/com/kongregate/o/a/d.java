// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import org.json.JSONObject;
import android.app.Application;
import android.app.Activity;
import java.util.HashMap;
import java.util.Map;
import com.kongregate.android.internal.util.j;
import android.content.pm.PackageInfo;
import android.content.Context;

public class d
{
    public d(final Context context) {
    }
    
    public static String a(final PackageInfo packageInfo) {
        if (packageInfo == null) {
            j.d("validateDeltaConfig: Package info unavaliable. Can not verify config.");
        }
        else if (packageInfo.activities == null) {
            j.a("validateDeltaConfig: no activities specified.");
            return null;
        }
        return null;
    }
    
    public Map<String, Object> a() {
        return new HashMap<String, Object>();
    }
    
    public void a(final Activity activity) {
    }
    
    public void a(final Activity activity, final Application application, final Map<String, Object> map, final String s) {
    }
    
    public void a(final b b) {
    }
    
    public void a(final c c) {
    }
    
    public void a(final String s) {
    }
    
    public void a(final String s, final double n, final Map<String, Object> map, final String s2, final String s3) {
    }
    
    public void a(final String s, final Map<String, Object> map) {
    }
    
    public void a(final Map<String, String> map) {
    }
    
    public Object b(final String s) {
        return new HashMap();
    }
    
    public void b(final Activity activity) {
    }
    
    public void b(final Map<String, Object> map) {
    }
    
    public void c(final String s) {
    }
    
    public class a
    {
        public void a(final String s) {
        }
    }
    
    public interface b
    {
        void a(final String p0, final String p1);
        
        void b(final String p0, final String p1);
    }
    
    public interface c
    {
        void a(final JSONObject p0);
    }
}
