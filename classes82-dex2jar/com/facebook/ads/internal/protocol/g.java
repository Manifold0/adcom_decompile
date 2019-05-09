// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import com.facebook.ads.internal.w.h.a;
import org.json.JSONObject;
import android.text.TextUtils;
import android.content.Context;
import android.support.annotation.Nullable;

public final class g
{
    private final a a;
    @Nullable
    private final Long b;
    @Nullable
    private final String c;
    @Nullable
    private final String d;
    
    public g(final Context context, final String s, final String s2, final e e) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.a = g.a.c;
            this.b = null;
            this.d = null;
            this.c = null;
        }
        else {
            JSONObject jsonObject = null;
            while (true) {
                Label_0237: {
                    Label_0144: {
                        while (true) {
                            Label_0505: {
                                try {
                                    jsonObject = new JSONObject(s);
                                    switch (g$1.a[g.a.valueOf(jsonObject.getString("type").toUpperCase()).ordinal()]) {
                                        case 1: {
                                            break Label_0144;
                                        }
                                        case 2: {
                                            break Label_0237;
                                        }
                                        default: {
                                            break Label_0505;
                                        }
                                    }
                                    throw new b(AdErrorType.BID_PAYLOAD_ERROR, "Unsupported BidPayload type " + jsonObject.getString("type"));
                                }
                                catch (JSONException ex) {
                                    com.facebook.ads.internal.w.h.a.b(context, "api", com.facebook.ads.internal.w.h.b.d, (Exception)ex);
                                    throw new b(AdErrorType.BID_PAYLOAD_ERROR, "Invalid BidPayload", (Throwable)ex);
                                }
                                break Label_0144;
                            }
                            continue;
                        }
                    }
                    this.a = g.a.a;
                    this.b = Long.valueOf(jsonObject.getString("bid_id"));
                    this.d = jsonObject.getString("device_id");
                    this.c = null;
                    break;
                }
                this.a = g.a.b;
                this.b = Long.valueOf(jsonObject.getString("bid_id"));
                this.d = jsonObject.getString("device_id");
                this.c = new JSONObject(jsonObject.getString("payload")).toString();
                break;
            }
            if (!jsonObject.getString("sdk_version").equals("5.1.0")) {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for SDK version %s being used on SDK version %s", this.b, jsonObject.getString("sdk_version"), "5.1.0"));
            }
            if (!jsonObject.getString("resolved_placement_id").equals(s2)) {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for placement %s being used on placement %s", this.b, jsonObject.getString("resolved_placement_id"), s2));
            }
            final HashSet set = new HashSet((Collection<? extends E>)Arrays.asList(e.g.a(), e.h.a(), e.i.a(), e.f.a()));
            if (jsonObject.getInt("template") != e.a() && (!set.contains(jsonObject.getInt("template")) || !set.contains(e.a()))) {
                throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for template %s being used on template %s", this.b, jsonObject.getInt("template"), e));
            }
        }
    }
    
    public void a(final String s) {
        if (!this.d.equals(s)) {
            throw new b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format(Locale.US, "Bid %d for IDFA %s being used on IDFA %s", this.b, this.d, s));
        }
    }
    
    public boolean a() {
        return this.a == g.a.b;
    }
    
    @Nullable
    public String b() {
        return this.c;
    }
    
    public boolean c() {
        return this.a != g.a.c;
    }
    
    @Nullable
    public String d() {
        if (this.b == null) {
            return null;
        }
        return this.b.toString();
    }
    
    private enum a
    {
        a, 
        b, 
        c;
    }
}
