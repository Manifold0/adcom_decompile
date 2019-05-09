package com.tapjoy;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.adjust.sdk.Constants;
import com.facebook.ads.AudienceNetworkActivity;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TapjoyUtil {
    /* renamed from: a */
    private static String f7104a = null;
    /* renamed from: b */
    private static HashMap f7105b = new HashMap();

    public static String SHA1(String text) {
        return m7132a(Constants.SHA1, text);
    }

    public static String SHA256(String text) {
        return m7132a(Constants.SHA256, text);
    }

    /* renamed from: a */
    private static String m7132a(String str, String str2) {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(str2.getBytes("iso-8859-1"), 0, str2.length());
        byte[] digest = instance.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            int i2 = (digest[i] >>> 4) & 15;
            int i3 = 0;
            while (true) {
                if (i2 < 0 || i2 > 9) {
                    stringBuffer.append((char) ((i2 - 10) + 97));
                } else {
                    stringBuffer.append((char) (i2 + 48));
                }
                int i4 = digest[i] & 15;
                i2 = i3 + 1;
                if (i3 > 0) {
                    break;
                }
                i3 = i2;
                i2 = i4;
            }
        }
        return stringBuffer.toString();
    }

    public static Document buildDocument(String xml) {
        Document document = null;
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            document = newInstance.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        } catch (Exception e) {
            TapjoyLog.m7128e("TapjoyUtil", "buildDocument exception: " + e.toString());
        }
        return document;
    }

    public static String getNodeTrimValue(NodeList nodeList) {
        int i = 0;
        Element element = (Element) nodeList.item(0);
        String str = "";
        if (element == null) {
            return null;
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        String str2 = str;
        while (i < length) {
            Node item = childNodes.item(i);
            if (item != null) {
                str2 = str2 + item.getNodeValue();
            }
            i++;
        }
        if (str2 == null || str2.equals("")) {
            return null;
        }
        return str2.trim();
    }

    public static void deleteFileOrDirectory(File fileOrDirectory) {
        if (fileOrDirectory != null) {
            if (fileOrDirectory.isDirectory()) {
                File[] listFiles = fileOrDirectory.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File deleteFileOrDirectory : listFiles) {
                        deleteFileOrDirectory(deleteFileOrDirectory);
                    }
                }
            }
            TapjoyLog.m7126d("TapjoyUtil", "****************************************");
            TapjoyLog.m7126d("TapjoyUtil", "deleteFileOrDirectory: " + fileOrDirectory.getAbsolutePath());
            TapjoyLog.m7126d("TapjoyUtil", "****************************************");
            fileOrDirectory.delete();
        }
    }

    public static long fileOrDirectorySize(File fileOrDirectory) {
        long j = 0;
        for (File file : fileOrDirectory.listFiles()) {
            if (file.isFile()) {
                j += file.length();
            } else {
                j += fileOrDirectorySize(file);
            }
        }
        return j;
    }

    public static void writeFileToDevice(BufferedInputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static Bitmap createBitmapFromView(View v) {
        Bitmap bitmap = null;
        if (v == null || v.getLayoutParams().width <= 0 || v.getLayoutParams().height <= 0) {
            return null;
        }
        try {
            bitmap = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
            v.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            TapjoyLog.m7126d("TapjoyUtil", "error creating bitmap: " + e.toString());
            return bitmap;
        }
    }

    public static View scaleDisplayAd(View adView, int targetWidth) {
        int i = adView.getLayoutParams().width;
        int i2 = adView.getLayoutParams().height;
        TapjoyLog.m7126d("TapjoyUtil", "wxh: " + i + "x" + i2);
        if (i > targetWidth) {
            int intValue = Double.valueOf(Double.valueOf(Double.valueOf((double) targetWidth).doubleValue() / Double.valueOf((double) i).doubleValue()).doubleValue() * 100.0d).intValue();
            ((WebView) adView).getSettings().setSupportZoom(true);
            ((WebView) adView).setPadding(0, 0, 0, 0);
            ((WebView) adView).setVerticalScrollBarEnabled(false);
            ((WebView) adView).setHorizontalScrollBarEnabled(false);
            ((WebView) adView).setInitialScale(intValue);
            adView.setLayoutParams(new LayoutParams(targetWidth, (i2 * targetWidth) / i));
        }
        return adView;
    }

    public static void safePut(Map map, String key, String value, boolean encode) {
        if (key != null && key.length() > 0 && value != null && value.length() > 0) {
            if (encode) {
                map.put(Uri.encode(key), Uri.encode(value));
            } else {
                map.put(key, value);
            }
        }
    }

    public static void safePut(Map map, String key, Number value) {
        if (key != null && key.length() > 0 && value != null) {
            map.put(key, value.toString());
        }
    }

    public static String convertURLParams(Map source, boolean encode) {
        String str = "";
        for (Entry entry : source.entrySet()) {
            if (str.length() > 0) {
                str = str + RequestParameters.AMPERSAND;
            }
            if (encode) {
                str = str + Uri.encode((String) entry.getKey()) + RequestParameters.EQUAL + Uri.encode((String) entry.getValue());
            } else {
                str = str + ((String) entry.getKey()) + RequestParameters.EQUAL + ((String) entry.getValue());
            }
        }
        return str;
    }

    public static Map convertURLParams(String source, boolean decode) {
        Map hashMap = new HashMap();
        Object obj = "";
        Object obj2 = "";
        Object obj3 = null;
        int i = 0;
        while (i < source.length() && i != -1) {
            char charAt = source.charAt(i);
            if (obj3 == null) {
                if (charAt == '=') {
                    if (decode) {
                        obj2 = Uri.decode(obj);
                    } else {
                        obj2 = obj;
                    }
                    obj = "";
                    obj3 = 1;
                } else {
                    obj = obj + charAt;
                }
            } else if (obj3 == 1) {
                if (charAt == '&') {
                    if (decode) {
                        obj3 = Uri.decode(obj);
                    } else {
                        obj3 = obj;
                    }
                    obj = "";
                    hashMap.put(obj2, obj3);
                    obj3 = null;
                } else {
                    obj = obj + charAt;
                }
            }
            i++;
        }
        if (obj3 == 1 && obj.length() > 0) {
            if (decode) {
                obj = Uri.decode(obj);
            }
            hashMap.put(obj2, obj);
        }
        return hashMap;
    }

    public static String copyTextFromJarIntoString(String source) {
        return copyTextFromJarIntoString(source, null);
    }

    public static Bitmap loadBitmapFromJar(String source, Context context) {
        Bitmap decodeByteArray;
        Exception e;
        Throwable th;
        InputStream inputStream = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        byte[] bArr = (byte[]) getResource(source);
        if (bArr != null) {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } else {
            decodeByteArray = null;
        }
        if (decodeByteArray == null) {
            source = "com/tapjoy/res/" + source;
            InputStream open;
            try {
                URL resource = TapjoyUtil.class.getClassLoader().getResource(source);
                if (resource == null) {
                    AssetManager assets = context.getAssets();
                    try {
                        BitmapFactory.decodeStream(assets.open(source), null, options);
                        open = assets.open(source);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (open != null) {
                                try {
                                    open.close();
                                } catch (IOException e3) {
                                }
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream = open;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                            throw th;
                        }
                    }
                }
                String file = resource.getFile();
                if (file.startsWith("jar:")) {
                    file = file.substring(4);
                }
                if (file.startsWith("file:")) {
                    file = file.substring(5);
                }
                int indexOf = file.indexOf("!");
                if (indexOf > 0) {
                    file = file.substring(0, indexOf);
                }
                JarFile jarFile = new JarFile(file);
                ZipEntry jarEntry = jarFile.getJarEntry(source);
                BitmapFactory.decodeStream(jarFile.getInputStream(jarEntry), null, options);
                open = jarFile.getInputStream(jarEntry);
                decodeByteArray = BitmapFactory.decodeStream(open);
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (Exception e6) {
                e = e6;
                open = null;
                e.printStackTrace();
                if (open != null) {
                    open.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }
        float deviceScreenDensityScale = TapjoyConnectCore.getDeviceScreenDensityScale();
        if (decodeByteArray != null) {
            return Bitmap.createScaledBitmap(decodeByteArray, (int) (((float) options.outWidth) * deviceScreenDensityScale), (int) (deviceScreenDensityScale * ((float) options.outHeight)), true);
        }
        return decodeByteArray;
    }

    public static String copyTextFromJarIntoString(String source, Context context) {
        InputStream inputStream;
        Throwable th;
        String str = null;
        byte[] bArr = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer();
        URL resource = TapjoyUtil.class.getClassLoader().getResource(source);
        if (context == null || resource != null) {
            String file = resource.getFile();
            if (file.startsWith("jar:")) {
                file = file.substring(4);
            }
            if (file.startsWith("file:")) {
                file = file.substring(5);
            }
            int indexOf = file.indexOf("!");
            if (indexOf > 0) {
                file = file.substring(0, indexOf);
            }
            JarFile jarFile = new JarFile(file);
            inputStream = jarFile.getInputStream(jarFile.getJarEntry(source));
        } else {
            try {
                inputStream = context.getAssets().open(source);
            } catch (Exception e) {
                Exception e2 = e;
                Object obj = str;
                try {
                    TapjoyLog.m7126d("TapjoyUtil", "file exception: " + e2.toString());
                    e2.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                inputStream = str;
                th = th3;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                stringBuffer.append(new String(bArr).substring(0, read));
            } catch (Exception e5) {
                e2 = e5;
            }
        }
        str = stringBuffer.toString();
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e6) {
            }
        }
        return str;
    }

    public static void setResource(String key, Object value) {
        f7105b.put(key, value);
    }

    public static Object getResource(String key) {
        return f7105b.get(key);
    }

    public static String getRedirectDomain(String hostURL) {
        String str = "";
        if (hostURL != null) {
            return hostURL.substring(hostURL.indexOf("//") + 2, hostURL.lastIndexOf("/"));
        }
        return str;
    }

    public static String determineMimeType(String url) {
        String str = "";
        if (url.endsWith(".")) {
            url = url.substring(0, url.length() - 1);
        }
        if (url.lastIndexOf(46) != -1) {
            str = url.substring(url.lastIndexOf(46) + 1);
        }
        if (str.equals("css")) {
            return "text/css";
        }
        if (str.equals("js")) {
            return "text/javascript";
        }
        if (str.equals(String.HTML)) {
            return AudienceNetworkActivity.WEBVIEW_MIME_TYPE;
        }
        return "application/octet-stream";
    }

    public static Map jsonToStringMap(JSONObject json) {
        Map hashMap = new HashMap();
        if (json != JSONObject.NULL) {
            return toStringMap(json);
        }
        return hashMap;
    }

    public static Map toStringMap(JSONObject object) {
        Map hashMap = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, object.get(str).toString());
        }
        return hashMap;
    }

    public static void runOnMainThread(Runnable task) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            task.run();
        } else {
            new Handler(Looper.getMainLooper()).post(task);
        }
    }
}
