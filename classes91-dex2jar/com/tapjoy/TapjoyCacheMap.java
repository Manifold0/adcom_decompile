// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.io.File;
import android.content.SharedPreferences$Editor;
import java.util.Iterator;
import java.util.Map;
import android.content.Context;
import java.util.concurrent.ConcurrentHashMap;

public class TapjoyCacheMap extends ConcurrentHashMap
{
    private Context a;
    private int b;
    
    public TapjoyCacheMap(final Context a, final int b) {
        this.b = -1;
        this.a = a;
        this.b = b;
    }
    
    private String a() {
        long n = -1L;
        String s = "";
        for (final Map.Entry<Object, Object> entry : this.entrySet()) {
            final long timestampInSeconds = entry.getValue().getTimestampInSeconds();
            if (n == 0L || timestampInSeconds < n) {
                s = entry.getKey();
                n = timestampInSeconds;
            }
        }
        return s;
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    public TapjoyCachedAssetData put(final String s, final TapjoyCachedAssetData tapjoyCachedAssetData) {
        TapjoyLog.d("TapjoyCacheMap", "TapjoyCacheMap::put() -- key: " + s + " assetURL: " + tapjoyCachedAssetData.getAssetURL());
        if (tapjoyCachedAssetData != null && tapjoyCachedAssetData.getTimeOfDeathInSeconds() > System.currentTimeMillis() / 1000L) {
            if (this.size() == this.b) {
                this.remove(this.a());
            }
            final SharedPreferences$Editor edit = this.a.getSharedPreferences("tapjoyCacheData", 0).edit();
            edit.putString(tapjoyCachedAssetData.getLocalFilePath(), tapjoyCachedAssetData.toRawJSONString());
            edit.commit();
            return super.put(s, tapjoyCachedAssetData);
        }
        return null;
    }
    
    @Override
    public TapjoyCachedAssetData remove(final Object o) {
        if (this.containsKey(o)) {
            final SharedPreferences$Editor edit = this.a.getSharedPreferences("tapjoyCacheData", 0).edit();
            edit.remove(this.get(o).getLocalFilePath());
            edit.commit();
            final String localFilePath = this.get(o).getLocalFilePath();
            if (localFilePath != null && localFilePath.length() > 0) {
                TapjoyUtil.deleteFileOrDirectory(new File(localFilePath));
            }
            TapjoyLog.d("TapjoyCacheMap", "TapjoyCacheMap::remove() -- key: " + o);
            return super.remove(o);
        }
        return null;
    }
    
    public TapjoyCachedAssetData replace(final String s, final TapjoyCachedAssetData tapjoyCachedAssetData) {
        throw new UnsupportedOperationException();
    }
    
    public boolean replace(final String s, final TapjoyCachedAssetData tapjoyCachedAssetData, final TapjoyCachedAssetData tapjoyCachedAssetData2) {
        throw new UnsupportedOperationException();
    }
}
