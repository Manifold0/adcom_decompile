package com.facebook.ads.internal.p041k;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p025w.p026b.C2585o;
import com.facebook.ads.internal.p025w.p026b.C2589s;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import com.facebook.ads.internal.p050r.C2078a;
import com.ironsource.sdk.constants.LocationConst;
import com.tapjoy.TapjoyConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.k.e */
public class C2038e {
    /* renamed from: a */
    private static final String f4546a = C2038e.class.getName();
    /* renamed from: b */
    private static final Object f4547b = new Object();
    /* renamed from: c */
    private static final Set<String> f4548c = Collections.synchronizedSet(new HashSet());
    /* renamed from: d */
    private static final Map<String, Integer> f4549d = Collections.synchronizedMap(new HashMap());
    /* renamed from: e */
    private static AtomicInteger f4550e = new AtomicInteger();

    /* renamed from: a */
    public static C2034d m4933a(Exception exception, Context context, Map<String, String> map) {
        try {
            C2034d c2034d = new C2034d(C2585o.m6652b(), C2585o.m6653c(), new C2036b(C2589s.m6655a((Throwable) exception), map, true).m4931a());
            try {
                C2038e.m4936a(c2034d, context);
                return c2034d;
            } catch (Exception e) {
                return c2034d;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    @WorkerThread
    /* renamed from: a */
    public static JSONArray m4934a(Context context) {
        return C2038e.m4935a(context, -1);
    }

    @WorkerThread
    /* renamed from: a */
    public static JSONArray m4935a(Context context, int i) {
        FileInputStream openFileInput;
        Throwable e;
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader = null;
        JSONArray jSONArray = new JSONArray();
        synchronized (f4547b) {
            InputStreamReader inputStreamReader2;
            BufferedReader bufferedReader;
            try {
                if (new File(context.getFilesDir(), C2616a.m6730a("debuglogs", context)).exists()) {
                    openFileInput = context.openFileInput(C2616a.m6730a("debuglogs", context));
                    try {
                        inputStreamReader2 = new InputStreamReader(openFileInput);
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader2);
                            int i2 = i;
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null || i2 == 0) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(readLine);
                                    if (!jSONObject.has("attempt")) {
                                        jSONObject.put("attempt", String.valueOf(0));
                                    }
                                    readLine = jSONObject.getString("id");
                                    if (!f4548c.contains(readLine)) {
                                        int i3 = jSONObject.getInt("attempt");
                                        if (f4549d.containsKey(readLine)) {
                                            jSONObject.put("attempt", String.valueOf(f4549d.get(readLine)));
                                        } else if (f4548c.contains(readLine)) {
                                            throw new RuntimeException("finished event should not be updated to OngoingEvent.");
                                        } else {
                                            if (f4549d.containsKey(readLine)) {
                                                f4549d.remove(readLine);
                                            }
                                            f4549d.put(readLine, Integer.valueOf(i3));
                                        }
                                        jSONArray.put(jSONObject);
                                        if (i2 > 0) {
                                            i2--;
                                        }
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    inputStreamReader = inputStreamReader2;
                                    fileInputStream = openFileInput;
                                } catch (JSONException e3) {
                                    e = e3;
                                }
                            }
                        } catch (IOException e4) {
                            e = e4;
                            bufferedReader = null;
                            inputStreamReader = inputStreamReader2;
                            fileInputStream = openFileInput;
                            openFileInput = fileInputStream;
                            inputStreamReader2 = inputStreamReader;
                            try {
                                Log.e(f4546a, "Failed to read crashes", e);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e5) {
                                        Log.e(f4546a, "Failed to close buffers", e5);
                                    }
                                }
                                if (inputStreamReader2 != null) {
                                    inputStreamReader2.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                return jSONArray;
                            } catch (Throwable th) {
                                e5 = th;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e6) {
                                        Log.e(f4546a, "Failed to close buffers", e6);
                                        throw e5;
                                    }
                                }
                                if (inputStreamReader2 != null) {
                                    inputStreamReader2.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                throw e5;
                            }
                        } catch (JSONException e7) {
                            e5 = e7;
                            bufferedReader = null;
                            Log.e(f4546a, "Failed to read crashes", e5);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader2 != null) {
                                inputStreamReader2.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            return jSONArray;
                        } catch (Throwable th2) {
                            e5 = th2;
                            bufferedReader = null;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader2 != null) {
                                inputStreamReader2.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            throw e5;
                        }
                    } catch (IOException e8) {
                        e5 = e8;
                        bufferedReader = null;
                        fileInputStream = openFileInput;
                        openFileInput = fileInputStream;
                        inputStreamReader2 = inputStreamReader;
                        Log.e(f4546a, "Failed to read crashes", e5);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        return jSONArray;
                    } catch (JSONException e9) {
                        e5 = e9;
                        bufferedReader = null;
                        inputStreamReader2 = null;
                        Log.e(f4546a, "Failed to read crashes", e5);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        return jSONArray;
                    } catch (Throwable th3) {
                        e5 = th3;
                        bufferedReader = null;
                        inputStreamReader2 = null;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        throw e5;
                    }
                }
                bufferedReader = null;
                inputStreamReader2 = null;
                openFileInput = null;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e52) {
                        Log.e(f4546a, "Failed to close buffers", e52);
                    }
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
            } catch (IOException e10) {
                e52 = e10;
                bufferedReader = null;
                fileInputStream = null;
                openFileInput = fileInputStream;
                inputStreamReader2 = inputStreamReader;
                Log.e(f4546a, "Failed to read crashes", e52);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return jSONArray;
            } catch (JSONException e11) {
                e52 = e11;
                bufferedReader = null;
                inputStreamReader2 = null;
                openFileInput = null;
                Log.e(f4546a, "Failed to read crashes", e52);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return jSONArray;
            } catch (Throwable th4) {
                e52 = th4;
                bufferedReader = null;
                inputStreamReader2 = null;
                openFileInput = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                throw e52;
            }
        }
        return jSONArray;
    }

    @WorkerThread
    /* renamed from: a */
    public static void m4936a(C2034d c2034d, Context context) {
        if (c2034d != null && context != null) {
            synchronized (f4547b) {
                try {
                    String a = C2616a.m6730a("debuglogs", context);
                    File file = new File(context.getFilesDir(), a);
                    if (file.exists()) {
                        int ae = C2078a.ae(context);
                        long length = file.length();
                        if (ae > 0 && length > ((long) ae)) {
                            boolean delete = file.delete();
                            C2038e.m4939b(context, 0);
                            f4548c.clear();
                            f4549d.clear();
                            if (delete) {
                                C2038e.m4933a(new Exception("Purge debug events file. File size: " + length + ", DropCounter: " + f4550e.getAndIncrement()), context, new HashMap());
                                return;
                            }
                            Log.e(AudienceNetworkAds.TAG, "Can't delete debug events file.");
                        }
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", UUID.randomUUID().toString());
                    jSONObject.put("type", c2034d.mo5463a());
                    jSONObject.put(LocationConst.TIME, C2597v.m6667a(c2034d.m4926b()));
                    jSONObject.put("session_time", C2597v.m6667a(c2034d.m4927c()));
                    jSONObject.put(TapjoyConstants.TJC_SESSION_ID, c2034d.m4928d());
                    jSONObject.put("data", c2034d.m4929e() != null ? new JSONObject(c2034d.m4929e()) : new JSONObject());
                    jSONObject.put("attempt", String.valueOf(0));
                    FileOutputStream openFileOutput = context.openFileOutput(a, 32768);
                    openFileOutput.write((jSONObject.toString() + "\n").getBytes());
                    openFileOutput.close();
                    C2038e.m4939b(context, context.getApplicationContext().getSharedPreferences(C2616a.m6730a("DEBUG_PREF", context), 0).getInt("EventCount", 0) + 1);
                } catch (Throwable e) {
                    Log.e(f4546a, "Failed to store crash", e);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m4937a(String str) {
        Integer num = (Integer) f4549d.get(str);
        if (num == null) {
            num = Integer.valueOf(0);
        } else {
            f4549d.remove(str);
        }
        f4549d.put(str, Integer.valueOf(num.intValue() + 1));
    }

    @WorkerThread
    /* renamed from: b */
    public static void m4938b(Context context) {
        synchronized (f4547b) {
            File file = new File(context.getFilesDir(), C2616a.m6730a("debuglogs", context));
            if (file.exists()) {
                file.delete();
            }
            C2038e.m4939b(context, 0);
            f4548c.clear();
            f4549d.clear();
        }
    }

    /* renamed from: b */
    private static void m4939b(Context context, int i) {
        Editor edit = context.getApplicationContext().getSharedPreferences(C2616a.m6730a("DEBUG_PREF", context), 0).edit();
        String str = "EventCount";
        if (i < 0) {
            i = 0;
        }
        edit.putInt(str, i).apply();
    }

    /* renamed from: b */
    public static void m4940b(String str) {
        if (f4549d.containsKey(str)) {
            f4549d.remove(str);
        }
        f4548c.add(str);
    }

    /* renamed from: c */
    public static int m4941c(Context context) {
        return context.getApplicationContext().getSharedPreferences(C2616a.m6730a("DEBUG_PREF", context), 0).getInt("EventCount", 0) - f4548c.size();
    }

    /* renamed from: c */
    public static boolean m4942c(String str) {
        return f4548c.contains(str) || f4549d.containsKey(str);
    }

    @WorkerThread
    /* renamed from: d */
    public static boolean m4943d(Context context) {
        FileInputStream openFileInput;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        Throwable e;
        Object obj;
        Throwable th;
        Object obj2;
        Object obj3;
        boolean z = false;
        FileOutputStream fileOutputStream = null;
        JSONArray jSONArray = new JSONArray();
        synchronized (f4547b) {
            try {
                if (new File(context.getFilesDir(), C2616a.m6730a("debuglogs", context)).exists()) {
                    openFileInput = context.openFileInput(C2616a.m6730a("debuglogs", context));
                    try {
                        inputStreamReader = new InputStreamReader(openFileInput);
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(readLine);
                                    readLine = jSONObject.getString("id");
                                    if (!f4548c.contains(readLine)) {
                                        if (f4549d.containsKey(readLine)) {
                                            jSONObject.put("attempt", String.valueOf(f4549d.get(readLine)));
                                        }
                                        jSONArray.put(jSONObject);
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                } catch (JSONException e3) {
                                    e = e3;
                                }
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                stringBuilder.append(jSONArray.getJSONObject(i).toString()).append('\n');
                            }
                            fileOutputStream = context.openFileOutput(C2616a.m6730a("debuglogs", context), 0);
                            fileOutputStream.write(stringBuilder.toString().getBytes());
                        } catch (IOException e4) {
                            e = e4;
                            obj = fileOutputStream;
                            try {
                                Log.e(f4546a, "Failed to rewrite File.", e);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e5) {
                                        Log.e(f4546a, "Failed to close buffers", e5);
                                        f4548c.clear();
                                        f4549d.clear();
                                        return z;
                                    }
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                f4548c.clear();
                                f4549d.clear();
                                return z;
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e52) {
                                        Log.e(f4546a, "Failed to close buffers", e52);
                                        f4548c.clear();
                                        f4549d.clear();
                                        throw th;
                                    }
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                f4548c.clear();
                                f4549d.clear();
                                throw th;
                            }
                        } catch (JSONException e6) {
                            e52 = e6;
                            obj = fileOutputStream;
                            Log.e(f4546a, "Failed to rewrite File.", e52);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            f4548c.clear();
                            f4549d.clear();
                            return z;
                        } catch (Throwable th3) {
                            th = th3;
                            obj = fileOutputStream;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            f4548c.clear();
                            f4549d.clear();
                            throw th;
                        }
                    } catch (IOException e7) {
                        e52 = e7;
                        obj = fileOutputStream;
                        obj2 = fileOutputStream;
                        Log.e(f4546a, "Failed to rewrite File.", e52);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        f4548c.clear();
                        f4549d.clear();
                        return z;
                    } catch (JSONException e8) {
                        e52 = e8;
                        obj = fileOutputStream;
                        obj2 = fileOutputStream;
                        Log.e(f4546a, "Failed to rewrite File.", e52);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        f4548c.clear();
                        f4549d.clear();
                        return z;
                    } catch (Throwable th4) {
                        th = th4;
                        obj = fileOutputStream;
                        obj2 = fileOutputStream;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        f4548c.clear();
                        f4549d.clear();
                        throw th;
                    }
                }
                obj = fileOutputStream;
                obj2 = fileOutputStream;
                obj3 = fileOutputStream;
                C2038e.m4939b(context, C2038e.m4941c(context));
                z = true;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e522) {
                        Log.e(f4546a, "Failed to close buffers", e522);
                    }
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4548c.clear();
                f4549d.clear();
            } catch (IOException e9) {
                e522 = e9;
                obj = fileOutputStream;
                obj2 = fileOutputStream;
                obj3 = fileOutputStream;
                Log.e(f4546a, "Failed to rewrite File.", e522);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4548c.clear();
                f4549d.clear();
                return z;
            } catch (JSONException e10) {
                e522 = e10;
                obj = fileOutputStream;
                obj2 = fileOutputStream;
                obj3 = fileOutputStream;
                Log.e(f4546a, "Failed to rewrite File.", e522);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4548c.clear();
                f4549d.clear();
                return z;
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = fileOutputStream;
                inputStreamReader = fileOutputStream;
                openFileInput = fileOutputStream;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4548c.clear();
                f4549d.clear();
                throw th;
            }
        }
        return z;
    }
}
