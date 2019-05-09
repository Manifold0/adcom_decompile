// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.internal.ads.zzakb;
import android.text.TextUtils;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaf implements zzv<Object>
{
    private final Object mLock;
    @GuardedBy("mLock")
    private final Map<String, zzag> zzbnf;
    
    public zzaf() {
        this.mLock = new Object();
        this.zzbnf = new HashMap<String, zzag>();
    }
    
    @Override
    public final void zza(Object o, Map<String, String> o2) {
        final String s = ((Map<String, String>)o2).get("id");
        final String s2 = ((Map<String, String>)o2).get("fail");
        final String s3 = ((Map<String, String>)o2).get("fail_reason");
        Object mLock = ((Map<String, String>)o2).get("fail_stack");
        final String s4 = ((Map<String, String>)o2).get("result");
        while (true) {
            Label_0343: {
                if (!TextUtils.isEmpty((CharSequence)mLock)) {
                    break Label_0343;
                }
                o2 = "Unknown Fail Reason.";
                zzag zzag = null;
                Label_0138: {
                    if (!TextUtils.isEmpty((CharSequence)mLock)) {
                        break Label_0138;
                    }
                Block_11_Outer:
                    while (true) {
                        mLock = this.mLock;
                        synchronized (mLock) {
                            zzag = this.zzbnf.remove(s);
                            if (zzag == null) {
                                final String value = String.valueOf(s);
                                if (value.length() != 0) {
                                    o = "Received result for unexpected method invocation: ".concat(value);
                                }
                                else {
                                    o = new String("Received result for unexpected method invocation: ");
                                }
                                zzakb.zzdk((String)o);
                                return;
                            }
                            break;
                            while (true) {
                                final String value2;
                                "\n".concat(value2);
                                continue Block_11_Outer;
                                Label_0160: {
                                    o = new String("\n");
                                }
                                continue Block_11_Outer;
                                value2 = String.valueOf(mLock);
                                continue;
                            }
                        }
                        // iftrue(Label_0160:, value2.length() == 0)
                        break;
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)s2)) {
                    final String value3 = String.valueOf(o2);
                    final Throwable t;
                    final String value4 = String.valueOf(t);
                    String concat;
                    if (value4.length() != 0) {
                        concat = value3.concat(value4);
                    }
                    else {
                        concat = new String(value3);
                    }
                    zzag.zzau(concat);
                    // monitorexit(mLock)
                    return;
                }
                if (s4 == null) {
                    zzag.zzd(null);
                    // monitorexit(mLock)
                    return;
                }
                while (true) {
                    try {
                        final JSONObject jsonObject = new JSONObject(s4);
                        if (zzakb.zzqp()) {
                            final String value5 = String.valueOf(jsonObject.toString(2));
                            if (value5.length() != 0) {
                                o = "Result GMSG: ".concat(value5);
                            }
                            else {
                                o = new String("Result GMSG: ");
                            }
                            zzakb.v((String)o);
                        }
                        zzag.zzd(jsonObject);
                        // monitorexit(mLock)
                        return;
                    }
                    catch (JSONException ex) {
                        zzag.zzau(ex.getMessage());
                        continue;
                    }
                    break;
                }
            }
            o2 = s3;
            continue;
        }
    }
    
    public final void zza(final String s, final zzag zzag) {
        synchronized (this.mLock) {
            this.zzbnf.put(s, zzag);
        }
    }
}
