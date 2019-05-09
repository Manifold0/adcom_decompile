package com.facebook.ads.internal.p051s;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import com.facebook.ads.internal.p040j.C2025c;
import com.facebook.ads.internal.p040j.C2030d;
import com.facebook.ads.internal.p041k.C2038e;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2084b.C2083a;
import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.ironsource.sdk.constants.LocationConst;
import com.tapjoy.TapjoyConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.s.i */
public class C2092i implements C2083a {
    /* renamed from: a */
    private static final String f4773a = C2092i.class.getSimpleName();
    /* renamed from: b */
    private Context f4774b;
    /* renamed from: c */
    private C2030d f4775c;

    @VisibleForTesting
    public C2092i(Context context, C2030d c2030d) {
        this.f4774b = context;
        this.f4775c = c2030d;
    }

    /* renamed from: a */
    private static JSONArray m5217a(JSONArray jSONArray, JSONArray jSONArray2, int i) {
        if (jSONArray == null) {
            return jSONArray2;
        }
        if (jSONArray2 == null) {
            return jSONArray;
        }
        int length = jSONArray.length();
        int length2 = jSONArray2.length();
        JSONArray jSONArray3 = new JSONArray();
        Object obj = null;
        double d = Double.MAX_VALUE;
        double d2 = Double.MAX_VALUE;
        Object obj2 = null;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if ((i3 < length || i2 < length2) && i > 0) {
                int i4;
                JSONObject jSONObject;
                double d3;
                Object obj3;
                int i5;
                if (i3 >= length || obj != null) {
                    i4 = i3;
                } else {
                    try {
                        jSONObject = jSONArray.getJSONObject(i3);
                        d3 = jSONObject.getDouble(LocationConst.TIME);
                    } catch (JSONException e) {
                        jSONObject = null;
                        d3 = Double.MAX_VALUE;
                    }
                    d = d3;
                    i4 = i3 + 1;
                    obj = jSONObject;
                }
                if (i2 >= length2 || obj2 != null) {
                    d3 = d2;
                    obj3 = obj2;
                    i5 = i2;
                } else {
                    try {
                        jSONObject = jSONArray2.getJSONObject(i2);
                        d3 = jSONObject.getDouble(LocationConst.TIME);
                    } catch (JSONException e2) {
                        jSONObject = null;
                        d3 = Double.MAX_VALUE;
                    }
                    obj3 = jSONObject;
                    i5 = i2 + 1;
                }
                if (obj == null && obj3 == null) {
                    d2 = d3;
                    i2 = i5;
                    obj2 = obj3;
                    i3 = i4;
                } else {
                    Object obj4;
                    Object obj5;
                    double d4;
                    if (obj == null || d3 < d) {
                        jSONArray3.put(obj3);
                        d3 = Double.MAX_VALUE;
                        double d5 = d;
                        obj4 = null;
                        obj5 = obj;
                        d4 = d5;
                    } else {
                        jSONArray3.put(obj);
                        d4 = Double.MAX_VALUE;
                        obj5 = null;
                        obj4 = obj3;
                    }
                    i--;
                    d2 = d3;
                    i2 = i5;
                    i3 = i4;
                    obj2 = obj4;
                    obj = obj5;
                    d = d4;
                }
            }
        }
        if (i > 0) {
            if (obj != null) {
                jSONArray3.put(obj);
            } else if (obj2 != null) {
                jSONArray3.put(obj2);
            }
        }
        return jSONArray3;
    }

    /* renamed from: a */
    private JSONObject m5218a(int i) {
        Cursor cursor;
        Throwable th;
        Cursor d;
        Cursor a;
        try {
            d = this.f4775c.m4913d();
            try {
                a = this.f4775c.m4905a(i);
                try {
                    JSONArray jSONArray;
                    Object obj;
                    JSONArray a2;
                    Object a3;
                    JSONObject jSONObject;
                    if (a.getCount() > 0) {
                        JSONObject a4 = m5219a(a);
                        jSONArray = new JSONArray();
                        a.moveToPosition(-1);
                        while (a.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("id", a.getString(2));
                            jSONObject2.put("token_id", a.getString(0));
                            jSONObject2.put("type", a.getString(4));
                            jSONObject2.put(LocationConst.TIME, C2597v.m6667a(a.getDouble(5)));
                            jSONObject2.put("session_time", C2597v.m6667a(a.getDouble(6)));
                            jSONObject2.put(TapjoyConstants.TJC_SESSION_ID, a.getString(7));
                            String string = a.getString(8);
                            jSONObject2.put("data", string != null ? new JSONObject(string) : new JSONObject());
                            jSONObject2.put("attempt", a.getString(9));
                            jSONArray.put(jSONObject2);
                        }
                        obj = a4;
                    } else {
                        jSONArray = null;
                        obj = null;
                    }
                    if (C2078a.m5106p(this.f4774b)) {
                        a2 = C2038e.m4935a(this.f4774b, i);
                        if (a2 != null && a2.length() > 0) {
                            a3 = C2092i.m5217a(a2, jSONArray, i);
                            if (a3 == null) {
                                jSONObject = new JSONObject();
                                if (obj != null) {
                                    jSONObject.put("tokens", obj);
                                }
                                jSONObject.put(EventEntry.TABLE_NAME, a3);
                            } else {
                                jSONObject = null;
                            }
                            if (d != null) {
                                d.close();
                            }
                            if (a != null) {
                                return jSONObject;
                            }
                            a.close();
                            return jSONObject;
                        }
                    }
                    a2 = jSONArray;
                    if (a3 == null) {
                        jSONObject = null;
                    } else {
                        jSONObject = new JSONObject();
                        if (obj != null) {
                            jSONObject.put("tokens", obj);
                        }
                        jSONObject.put(EventEntry.TABLE_NAME, a3);
                    }
                    if (d != null) {
                        d.close();
                    }
                    if (a != null) {
                        return jSONObject;
                    }
                    a.close();
                    return jSONObject;
                } catch (JSONException e) {
                    cursor = a;
                    a = d;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (JSONException e2) {
                cursor = null;
                a = d;
                if (a != null) {
                    a.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                a = null;
                if (d != null) {
                    d.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        } catch (JSONException e3) {
            cursor = null;
            a = null;
            if (a != null) {
                a.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            a = null;
            d = null;
            if (d != null) {
                d.close();
            }
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private JSONObject m5219a(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        while (cursor.moveToNext()) {
            jSONObject.put(cursor.getString(0), cursor.getString(1));
        }
        return jSONObject;
    }

    /* renamed from: a */
    private void m5220a(String str) {
        if (C2038e.m4942c(str)) {
            C2038e.m4937a(str);
        } else {
            this.f4775c.m4909a(str);
        }
    }

    /* renamed from: e */
    private JSONObject m5221e() {
        Cursor cursor;
        Throwable th;
        Cursor f;
        Cursor e;
        try {
            f = this.f4775c.m4915f();
            try {
                e = this.f4775c.m4914e();
                try {
                    JSONArray jSONArray;
                    Object obj;
                    Object a;
                    JSONObject jSONObject;
                    if (f.getCount() <= 0 || e.getCount() <= 0) {
                        jSONArray = null;
                        obj = null;
                    } else {
                        JSONObject a2 = m5219a(f);
                        jSONArray = new JSONArray();
                        e.moveToPosition(-1);
                        while (e.moveToNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("id", e.getString(C2025c.f4487a.f4483a));
                            jSONObject2.put("token_id", e.getString(C2025c.f4488b.f4483a));
                            jSONObject2.put("type", e.getString(C2025c.f4490d.f4483a));
                            jSONObject2.put(LocationConst.TIME, C2597v.m6667a(e.getDouble(C2025c.f4491e.f4483a)));
                            jSONObject2.put("session_time", C2597v.m6667a(e.getDouble(C2025c.f4492f.f4483a)));
                            jSONObject2.put(TapjoyConstants.TJC_SESSION_ID, e.getString(C2025c.f4493g.f4483a));
                            String string = e.getString(C2025c.f4494h.f4483a);
                            jSONObject2.put("data", string != null ? new JSONObject(string) : new JSONObject());
                            jSONObject2.put("attempt", e.getString(C2025c.f4495i.f4483a));
                            jSONArray.put(jSONObject2);
                        }
                        obj = a2;
                    }
                    if (C2078a.m5106p(this.f4774b)) {
                        JSONArray a3 = C2038e.m4934a(this.f4774b);
                        if (a3 != null && a3.length() > 0) {
                            int i = 0;
                            if (a3 != null) {
                                i = 0 + a3.length();
                            }
                            if (jSONArray != null) {
                                i += jSONArray.length();
                            }
                            a = C2092i.m5217a(a3, jSONArray, i);
                            if (a == null) {
                                jSONObject = new JSONObject();
                                if (obj != null) {
                                    jSONObject.put("tokens", obj);
                                }
                                jSONObject.put(EventEntry.TABLE_NAME, a);
                            } else {
                                jSONObject = null;
                            }
                            if (f != null) {
                                f.close();
                            }
                            if (e != null) {
                                return jSONObject;
                            }
                            e.close();
                            return jSONObject;
                        }
                    }
                    JSONArray jSONArray2 = jSONArray;
                    if (a == null) {
                        jSONObject = null;
                    } else {
                        jSONObject = new JSONObject();
                        if (obj != null) {
                            jSONObject.put("tokens", obj);
                        }
                        jSONObject.put(EventEntry.TABLE_NAME, a);
                    }
                    if (f != null) {
                        f.close();
                    }
                    if (e != null) {
                        return jSONObject;
                    }
                    e.close();
                    return jSONObject;
                } catch (JSONException e2) {
                    cursor = e;
                    e = f;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (JSONException e3) {
                cursor = null;
                e = f;
                if (e != null) {
                    e.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                e = null;
                if (f != null) {
                    f.close();
                }
                if (e != null) {
                    e.close();
                }
                throw th;
            }
        } catch (JSONException e4) {
            cursor = null;
            e = null;
            if (e != null) {
                e.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            e = null;
            f = null;
            if (f != null) {
                f.close();
            }
            if (e != null) {
                e.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public JSONObject mo5490a() {
        int x = C2078a.m5114x(this.f4774b);
        return x > 0 ? m5218a(x) : m5221e();
    }

    /* renamed from: a */
    public boolean mo5491a(JSONArray jSONArray) {
        boolean p = C2078a.m5106p(this.f4774b);
        boolean z = true;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("id");
                int i2 = jSONObject.getInt("code");
                if (i2 == 1) {
                    if (!this.f4775c.m4911b(string) && p) {
                        C2038e.m4940b(string);
                    }
                } else if (i2 >= 1000 && i2 < 2000) {
                    m5220a(string);
                    z = false;
                } else if (i2 >= 2000 && i2 < 3000 && !this.f4775c.m4911b(string) && p) {
                    C2038e.m4940b(string);
                }
            } catch (JSONException e) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: b */
    public void mo5492b() {
        this.f4775c.m4916g();
        this.f4775c.m4910b();
        C2038e.m4943d(this.f4774b);
    }

    /* renamed from: b */
    public void mo5493b(JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                m5220a(jSONArray.getJSONObject(i).getString("id"));
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: c */
    public void mo5494c() {
        this.f4775c.m4917h();
        C2038e.m4938b(this.f4774b);
    }

    /* renamed from: d */
    public boolean mo5495d() {
        Throwable th;
        Cursor cursor;
        boolean z = true;
        int x = C2078a.m5114x(this.f4774b);
        if (x < 1) {
            return false;
        }
        Cursor cursor2 = null;
        try {
            cursor2 = this.f4775c.m4913d();
            try {
                if ((cursor2.moveToFirst() ? cursor2.getInt(0) : 0) + C2038e.m4941c(this.f4774b) <= x) {
                    z = false;
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
