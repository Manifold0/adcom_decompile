// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch;

import com.tonyodev.fetch.exception.NotUsableException;
import android.net.Uri;
import com.tonyodev.fetch.exception.InvalidStatusException;
import com.tonyodev.fetch.callback.FetchTask;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;
import com.tonyodev.fetch.request.Header;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.IOException;
import java.io.File;
import android.database.Cursor;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import java.util.List;

final class Utils
{
    private Utils() {
    }
    
    static String bundleListToHeaderString(final List<Bundle> list, final boolean b) {
        if (list == null) {
            return "{}";
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            for (final Bundle bundle : list) {
                final String string = bundle.getString("com.tonyodev.fetch.extra_header_name");
                String string2;
                if ((string2 = bundle.getString("com.tonyodev.fetch.extra_header_value")) == null) {
                    string2 = "";
                }
                if (string != null) {
                    jsonObject.put(string, (Object)string2);
                }
            }
        }
        catch (JSONException ex) {
            if (b) {
                ex.printStackTrace();
            }
            return "{}";
        }
        return jsonObject.toString();
    }
    
    static boolean containsRequest(final Cursor cursor, final boolean b) {
        if (cursor != null && cursor.getCount() > 0) {
            if (b) {
                cursor.close();
            }
            return true;
        }
        return false;
    }
    
    static boolean createDirIfNotExist(final String s) throws NullPointerException {
        final File file = new File(s);
        return file.exists() || file.mkdirs();
    }
    
    static boolean createFileIfNotExist(final String s) throws IOException, NullPointerException {
        final File file = new File(s);
        return file.exists() || file.createNewFile();
    }
    
    static void createFileOrThrow(final String s) throws IOException, NullPointerException {
        final File file = getFile(s);
        final boolean dirIfNotExist = createDirIfNotExist(file.getParentFile().getAbsolutePath());
        final boolean fileIfNotExist = createFileIfNotExist(file.getAbsolutePath());
        if (!dirIfNotExist || !fileIfNotExist) {
            throw new IOException("File could not be created for the filePath:" + s);
        }
    }
    
    static RequestInfo createRequestInfo(final Cursor cursor, final boolean b) {
        if (cursor == null || cursor.isClosed() || cursor.getCount() < 1) {
            return null;
        }
        final long long1 = cursor.getLong(0);
        final int int1 = cursor.getInt(3);
        final String string = cursor.getString(1);
        final String string2 = cursor.getString(2);
        final int int2 = cursor.getInt(7);
        final long long2 = cursor.getLong(6);
        final int int3 = cursor.getInt(8);
        final long long3 = cursor.getLong(5);
        return new RequestInfo(long1, int1, string, string2, getProgress(long3, long2), long3, long2, int2, headerStringToList(cursor.getString(4), b), int3);
    }
    
    static ArrayList<Bundle> cursorToQueryResultList(final Cursor cursor, final boolean b, final boolean b2) {
        final ArrayList<Bundle> list = new ArrayList<Bundle>();
        if (cursor == null) {
            return list;
        }
        try {
            try {
                if (cursor.isClosed()) {
                    return list;
                }
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    final long long1 = cursor.getLong(0);
                    final int int1 = cursor.getInt(3);
                    final String string = cursor.getString(1);
                    final String string2 = cursor.getString(2);
                    final int int2 = cursor.getInt(7);
                    final long long2 = cursor.getLong(6);
                    final int int3 = cursor.getInt(8);
                    final long long3 = cursor.getLong(5);
                    final ArrayList<Bundle> headersToBundleList = headersToBundleList(cursor.getString(4), b2);
                    final int progress = getProgress(long3, long2);
                    final Bundle bundle = new Bundle();
                    bundle.putLong("com.tonyodev.fetch.extra_id", long1);
                    bundle.putInt("com.tonyodev.fetch.extra_status", int1);
                    bundle.putString("com.tonyodev.fetch.extra_url", string);
                    bundle.putString("com.tonyodev.fetch.extra_file_path", string2);
                    bundle.putInt("com.tonyodev.fetch.extra_error", int2);
                    bundle.putLong("com.tonyodev.fetch.extra_downloaded_bytes", long3);
                    bundle.putLong("com.tonyodev.fetch.extra_file_size", long2);
                    bundle.putInt("com.tonyodev.fetch.extra_progress", progress);
                    bundle.putInt("com.tonyodev.fetch.extra_priority", int3);
                    bundle.putParcelableArrayList("com.tonyodev.fetch.extra_headers", (ArrayList)headersToBundleList);
                    list.add(bundle);
                    cursor.moveToNext();
                }
            }
            catch (Exception ex) {
                if (b2) {
                    ex.printStackTrace();
                }
                return list;
            }
            return list;
        }
        finally {
            if (cursor != null && b) {
                cursor.close();
            }
        }
    }
    
    static RequestInfo cursorToRequestInfo(final Cursor cursor, final boolean b, final boolean b2) {
        RequestInfo requestInfo = null;
        Label_0028: {
            if (cursor == null) {
                break Label_0028;
            }
            try {
                if (cursor.isClosed() || cursor.getCount() < 1) {
                    return null;
                }
                cursor.moveToFirst();
                RequestInfo requestInfo2;
                requestInfo = (requestInfo2 = createRequestInfo(cursor, b2));
                if (cursor != null) {
                    requestInfo2 = requestInfo;
                    if (b) {
                        cursor.close();
                        requestInfo2 = requestInfo;
                    }
                }
                return requestInfo2;
            }
            catch (Exception ex) {
                if (b2) {
                    ex.printStackTrace();
                }
                RequestInfo requestInfo2 = requestInfo;
                if (cursor == null) {
                    return requestInfo2;
                }
                requestInfo2 = requestInfo;
                if (b) {
                    cursor.close();
                    requestInfo2 = requestInfo;
                    return requestInfo2;
                }
                return requestInfo2;
            }
            finally {
                if (cursor != null && b) {
                    cursor.close();
                }
            }
        }
    }
    
    static List<RequestInfo> cursorToRequestInfoList(final Cursor cursor, final boolean b, final boolean b2) {
        final ArrayList<RequestInfo> list = new ArrayList<RequestInfo>();
        if (cursor == null) {
            return list;
        }
        try {
            try {
                if (cursor.isClosed() || cursor.getCount() < 1) {
                    return list;
                }
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    list.add(createRequestInfo(cursor, b2));
                    cursor.moveToNext();
                }
            }
            catch (Exception ex) {
                if (b2) {
                    ex.printStackTrace();
                }
                return list;
            }
            return list;
        }
        finally {
            if (cursor != null && b) {
                cursor.close();
            }
        }
    }
    
    static boolean deleteFile(final String s) {
        return new File(s).delete();
    }
    
    static boolean fileExist(final String s) {
        return new File(s).exists();
    }
    
    static long generateRequestId() {
        return System.nanoTime();
    }
    
    static File getFile(final String s) {
        return new File(s);
    }
    
    static long getFileSize(final String s) {
        return new File(s).length();
    }
    
    static int getProgress(final long n, final long n2) {
        if (n2 < 1L || n < 1L) {
            return 0;
        }
        if (n >= n2) {
            return 100;
        }
        return (int)(n / (double)n2 * 100.0);
    }
    
    static boolean hasIntervalElapsed(final long n, final long n2, final long n3) {
        return TimeUnit.NANOSECONDS.toMillis(n2 - n) >= n3;
    }
    
    static String headerListToString(final List<Header> list, final boolean b) {
        if (list == null) {
            return "{}";
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            for (final Header header : list) {
                jsonObject.put(header.getHeader(), (Object)header.getValue());
            }
        }
        catch (JSONException ex) {
            if (b) {
                ex.printStackTrace();
            }
            return "{}";
        }
        return jsonObject.toString();
    }
    
    static List<Header> headerStringToList(final String s, final boolean b) {
        final ArrayList<Header> list = new ArrayList<Header>();
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s2 = keys.next();
                list.add(new Header(s2, jsonObject.getString(s2)));
            }
        }
        catch (JSONException ex) {
            if (b) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    
    static ArrayList<Bundle> headersToBundleList(final String s, final boolean b) {
        final ArrayList<Bundle> list = new ArrayList<Bundle>();
        if (s != null) {
            try {
                final JSONObject jsonObject = new JSONObject(s);
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    final Bundle bundle = new Bundle();
                    bundle.putString("com.tonyodev.fetch.extra_header_name", s2);
                    bundle.putString("com.tonyodev.fetch.extra_header_value", jsonObject.getString(s2));
                    list.add(bundle);
                }
            }
            catch (JSONException ex) {
                if (b) {
                    ex.printStackTrace();
                    return list;
                }
            }
        }
        return list;
    }
    
    static boolean isNetworkAvailable(final Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    static boolean isOnWiFi(final Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }
    
    static void sendEventUpdate(final LocalBroadcastManager localBroadcastManager, final long n, final int n2, final int n3, final long n4, final long n5, final int n6) {
        if (localBroadcastManager == null) {
            return;
        }
        final Intent intent = new Intent("com.tonyodev.fetch.event_action_update");
        intent.putExtra("com.tonyodev.fetch.extra_id", n);
        intent.putExtra("com.tonyodev.fetch.extra_status", n2);
        intent.putExtra("com.tonyodev.fetch.extra_progress", n3);
        intent.putExtra("com.tonyodev.fetch.extra_downloaded_bytes", n4);
        intent.putExtra("com.tonyodev.fetch.extra_file_size", n5);
        intent.putExtra("com.tonyodev.fetch.extra_error", n6);
        localBroadcastManager.sendBroadcast(intent);
    }
    
    static void throwIfFetchTaskNull(final FetchTask fetchTask) {
        if (fetchTask == null) {
            throw new NullPointerException("FetchTask cannot be null");
        }
    }
    
    static void throwIfInvalidStatus(final int n) {
        switch (n) {
            default: {
                throw new InvalidStatusException(n + " is not a valid status ", -114);
            }
            case -900:
            case 900:
            case 901:
            case 902:
            case 903:
            case 904:
            case 905: {}
        }
    }
    
    static void throwIfInvalidUrl(final String s) {
        final String scheme = Uri.parse(s).getScheme();
        if (scheme == null || (!scheme.equals("http") && !scheme.equals("https"))) {
            throw new IllegalArgumentException("Can only download HTTP/HTTPS URIs: " + s);
        }
    }
    
    static void throwIfNotUsable(final Fetch fetch) {
        if (fetch == null) {
            throw new NullPointerException("Fetch cannot be null");
        }
        if (fetch.isReleased()) {
            throw new NotUsableException("Fetch instance: " + fetch.toString() + " cannot be reused after calling its release() method.Call Fetch.getInstance() for a new instance of Fetch.", -115);
        }
    }
}
