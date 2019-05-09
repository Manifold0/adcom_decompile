package com.facebook.ads.internal.p046v.p047a;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.facebook.ads.internal.v.a.p */
public class C2152p implements Map<String, String> {
    /* renamed from: a */
    private Map<String, String> f4981a = new HashMap();

    /* renamed from: a */
    public C2152p m5508a(Map<? extends String, ? extends String> map) {
        putAll(map);
        return this;
    }

    /* renamed from: a */
    public String m5509a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.f4981a.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(RequestParameters.AMPERSAND);
            }
            stringBuilder.append(str);
            String str2 = (String) this.f4981a.get(str2);
            if (str2 != null) {
                stringBuilder.append(RequestParameters.EQUAL);
                try {
                    stringBuilder.append(URLEncoder.encode(str2, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public String m5510a(Object obj) {
        return (String) this.f4981a.get(obj);
    }

    /* renamed from: a */
    public String m5511a(String str, String str2) {
        return (String) this.f4981a.put(str, str2);
    }

    /* renamed from: b */
    public C2152p m5512b(String str, String str2) {
        this.f4981a.put(str, str2);
        return this;
    }

    /* renamed from: b */
    public String m5513b(Object obj) {
        return (String) this.f4981a.remove(obj);
    }

    /* renamed from: b */
    public byte[] m5514b() {
        byte[] bArr = null;
        try {
            bArr = m5509a().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public void clear() {
        this.f4981a.clear();
    }

    public boolean containsKey(Object obj) {
        return this.f4981a.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.f4981a.containsValue(obj);
    }

    public Set<Entry<String, String>> entrySet() {
        return this.f4981a.entrySet();
    }

    public /* synthetic */ Object get(Object obj) {
        return m5510a(obj);
    }

    public boolean isEmpty() {
        return this.f4981a.isEmpty();
    }

    public Set<String> keySet() {
        return this.f4981a.keySet();
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return m5511a((String) obj, (String) obj2);
    }

    public void putAll(Map<? extends String, ? extends String> map) {
        this.f4981a.putAll(map);
    }

    public /* synthetic */ Object remove(Object obj) {
        return m5513b(obj);
    }

    public int size() {
        return this.f4981a.size();
    }

    public Collection<String> values() {
        return this.f4981a.values();
    }
}
