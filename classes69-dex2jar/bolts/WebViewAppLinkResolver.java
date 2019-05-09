// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

import android.webkit.JavascriptInterface;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import java.net.URL;
import java.util.concurrent.Callable;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.net.URLConnection;
import android.net.Uri;
import org.json.JSONException;
import java.util.Map;
import org.json.JSONArray;
import android.content.Context;

public class WebViewAppLinkResolver implements AppLinkResolver
{
    private static final String KEY_AL_VALUE = "value";
    private static final String KEY_ANDROID = "android";
    private static final String KEY_APP_NAME = "app_name";
    private static final String KEY_CLASS = "class";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_SHOULD_FALLBACK = "should_fallback";
    private static final String KEY_URL = "url";
    private static final String KEY_WEB = "web";
    private static final String KEY_WEB_URL = "url";
    private static final String META_TAG_PREFIX = "al";
    private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
    private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    private final Context context;
    
    public WebViewAppLinkResolver(final Context context) {
        this.context = context;
    }
    
    private static List<Map<String, Object>> getAlList(final Map<String, Object> map, final String s) {
        List<Map<String, Object>> emptyList;
        if ((emptyList = map.get(s)) == null) {
            emptyList = Collections.emptyList();
        }
        return emptyList;
    }
    
    private static AppLink makeAppLinkFromAlData(final Map<String, Object> map, final Uri uri) {
        final ArrayList<AppLink.Target> list = new ArrayList<AppLink.Target>();
        Object emptyList;
        if ((emptyList = map.get("android")) == null) {
            emptyList = Collections.emptyList();
        }
        for (final Map<String, Object> map2 : emptyList) {
            final List<Map<String, Object>> alList = getAlList(map2, "url");
            final List<Map<String, Object>> alList2 = getAlList(map2, "package");
            final List<Map<String, Object>> alList3 = getAlList(map2, "class");
            final List<Map<String, Object>> alList4 = getAlList(map2, "app_name");
            for (int max = Math.max(alList.size(), Math.max(alList2.size(), Math.max(alList3.size(), alList4.size()))), i = 0; i < max; ++i) {
                Object value;
                if (alList.size() > i) {
                    value = alList.get(i).get("value");
                }
                else {
                    value = null;
                }
                final Uri tryCreateUrl = tryCreateUrl((String)value);
                Object value2;
                if (alList2.size() > i) {
                    value2 = alList2.get(i).get("value");
                }
                else {
                    value2 = null;
                }
                final String s = (String)value2;
                Object value3;
                if (alList3.size() > i) {
                    value3 = alList3.get(i).get("value");
                }
                else {
                    value3 = null;
                }
                final String s2 = (String)value3;
                Object value4;
                if (alList4.size() > i) {
                    value4 = alList4.get(i).get("value");
                }
                else {
                    value4 = null;
                }
                list.add(new AppLink.Target(s, s2, tryCreateUrl, (String)value4));
            }
        }
        final List<Map> list2 = map.get("web");
        Uri tryCreateUrl2 = uri;
        if (list2 != null) {
            tryCreateUrl2 = uri;
            if (list2.size() > 0) {
                final Map<K, List> map3 = list2.get(0);
                final List<Map> list3 = map3.get("url");
                final List<Map> list4 = map3.get("should_fallback");
                Uri uri2 = uri;
                if (list4 != null) {
                    uri2 = uri;
                    if (list4.size() > 0) {
                        final String s3 = list4.get(0).get("value");
                        uri2 = uri;
                        if (Arrays.asList("no", "false", "0").contains(s3.toLowerCase())) {
                            uri2 = null;
                        }
                    }
                }
                if ((tryCreateUrl2 = uri2) != null) {
                    tryCreateUrl2 = uri2;
                    if (list3 != null) {
                        tryCreateUrl2 = uri2;
                        if (list3.size() > 0) {
                            tryCreateUrl2 = tryCreateUrl(((Map<K, String>)list3.get(0)).get("value"));
                        }
                    }
                }
            }
        }
        return new AppLink(uri, list, tryCreateUrl2);
    }
    
    private static Map<String, Object> parseAlData(final JSONArray jsonArray) throws JSONException {
        final HashMap<String, List<Map<String, Object>>> hashMap = new HashMap<String, List<Map<String, Object>>>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String[] split = jsonObject.getString("property").split(":");
            if (split[0].equals("al")) {
                Object o = hashMap;
                for (int j = 1; j < split.length; ++j) {
                    List<Map<String, Object>> list;
                    if ((list = ((Map<String, List<Map<String, Object>>>)o).get(split[j])) == null) {
                        list = new ArrayList<Map<String, Object>>();
                        ((Map<String, List<Map<String, Object>>>)o).put(split[j], list);
                    }
                    if (list.size() > 0) {
                        o = list.get(list.size() - 1);
                    }
                    else {
                        o = null;
                    }
                    if (o == null || j == split.length - 1) {
                        o = new HashMap<String, List<Map<String, Object>>>();
                        list.add((Map<String, Object>)o);
                    }
                }
                if (jsonObject.has("content")) {
                    if (jsonObject.isNull("content")) {
                        ((Map<String, List<Map<String, Object>>>)o).put("value", null);
                    }
                    else {
                        ((Map<String, List<Map<String, Object>>>)o).put("value", (List<Map<String, Object>>)jsonObject.getString("content"));
                    }
                }
            }
        }
        return (Map<String, Object>)hashMap;
    }
    
    private static String readFromConnection(final URLConnection urlConnection) throws IOException {
        while (true) {
            Label_0075: {
                if (!(urlConnection instanceof HttpURLConnection)) {
                    break Label_0075;
                }
                Object o = urlConnection;
                InputStream inputStream = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                Label_0083: {
                    try {
                        inputStream = urlConnection.getInputStream();
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            o = new byte[1024];
                            while (true) {
                                final int read = inputStream.read((byte[])o);
                                if (read == -1) {
                                    break Label_0083;
                                }
                                byteArrayOutputStream.write((byte[])o, 0, read);
                            }
                        }
                        finally {
                            inputStream.close();
                        }
                    }
                    catch (Exception ex) {
                        inputStream = ((HttpURLConnection)o).getErrorStream();
                        continue;
                    }
                    break Label_0075;
                }
                final URLConnection urlConnection2;
                String contentEncoding;
                final String s = contentEncoding = urlConnection2.getContentEncoding();
                if (s == null) {
                    final String[] split = urlConnection2.getContentType().split(";");
                    final int length = split.length;
                    int n = 0;
                    String substring;
                    while (true) {
                        substring = s;
                        if (n >= length) {
                            break;
                        }
                        final String trim = split[n].trim();
                        if (trim.startsWith("charset=")) {
                            substring = trim.substring("charset=".length());
                            break;
                        }
                        ++n;
                    }
                    if ((contentEncoding = substring) == null) {
                        contentEncoding = "UTF-8";
                    }
                }
                final String s2 = new String(byteArrayOutputStream.toByteArray(), contentEncoding);
                inputStream.close();
                return s2;
            }
            InputStream inputStream = urlConnection.getInputStream();
            continue;
        }
    }
    
    private static Uri tryCreateUrl(final String s) {
        if (s == null) {
            return null;
        }
        return Uri.parse(s);
    }
    
    @Override
    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final Capture capture = new Capture();
        final Capture capture2 = new Capture();
        return (Task<AppLink>)Task.callInBackground((Callable)new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                URL url = new URL(uri.toString());
                URLConnection openConnection = null;
                while (url != null) {
                    openConnection = url.openConnection();
                    if (openConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection)openConnection).setInstanceFollowRedirects(true);
                    }
                    openConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
                    openConnection.connect();
                    if (openConnection instanceof HttpURLConnection) {
                        final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
                        if (httpURLConnection.getResponseCode() >= 300 && httpURLConnection.getResponseCode() < 400) {
                            url = new URL(httpURLConnection.getHeaderField("Location"));
                            httpURLConnection.disconnect();
                        }
                        else {
                            url = null;
                        }
                    }
                    else {
                        url = null;
                    }
                }
                try {
                    capture.set((Object)readFromConnection(openConnection));
                    capture2.set((Object)openConnection.getContentType());
                    return null;
                }
                finally {
                    if (openConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection)openConnection).disconnect();
                    }
                }
            }
        }).onSuccessTask((Continuation)new Continuation<Void, Task<JSONArray>>() {
            public Task<JSONArray> then(final Task<Void> task) throws Exception {
                final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                final WebView webView = new WebView(WebViewAppLinkResolver.this.context);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setNetworkAvailable(false);
                webView.setWebViewClient((WebViewClient)new WebViewClient() {
                    private boolean loaded = false;
                    
                    private void runJavaScript(final WebView webView) {
                        if (!this.loaded) {
                            this.loaded = true;
                            webView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
                        }
                    }
                    
                    public void onLoadResource(final WebView webView, final String s) {
                        super.onLoadResource(webView, s);
                        this.runJavaScript(webView);
                    }
                    
                    public void onPageFinished(final WebView webView, final String s) {
                        super.onPageFinished(webView, s);
                        this.runJavaScript(webView);
                    }
                });
                webView.addJavascriptInterface((Object)new Object() {
                    @JavascriptInterface
                    public void setValue(final String s) {
                        try {
                            taskCompletionSource.trySetResult((Object)new JSONArray(s));
                        }
                        catch (JSONException ex) {
                            taskCompletionSource.trySetError((Exception)ex);
                        }
                    }
                }, "boltsWebViewAppLinkResolverResult");
                String s = null;
                if (capture2.get() != null) {
                    s = ((String)capture2.get()).split(";")[0];
                }
                webView.loadDataWithBaseURL(uri.toString(), (String)capture.get(), s, (String)null, (String)null);
                return (Task<JSONArray>)taskCompletionSource.getTask();
            }
        }, Task.UI_THREAD_EXECUTOR).onSuccess((Continuation)new Continuation<JSONArray, AppLink>() {
            public AppLink then(final Task<JSONArray> task) throws Exception {
                return makeAppLinkFromAlData(parseAlData((JSONArray)task.getResult()), uri);
            }
        });
    }
}
