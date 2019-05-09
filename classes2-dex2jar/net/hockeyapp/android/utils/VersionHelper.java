// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import java.util.Iterator;
import android.annotation.SuppressLint;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.File;
import android.text.TextUtils;
import org.json.JSONException;
import java.util.Scanner;
import android.content.Context;
import java.util.ArrayList;
import org.json.JSONObject;
import net.hockeyapp.android.UpdateInfoListener;

public class VersionHelper
{
    public static final String VERSION_MAX = "99.0";
    private int mCurrentVersionCode;
    private UpdateInfoListener mListener;
    private JSONObject mNewest;
    private ArrayList<JSONObject> mSortedVersions;
    
    public VersionHelper(final Context context, final String s, final UpdateInfoListener mListener) {
        this.mListener = mListener;
        this.loadVersions(context, s);
        this.sortVersions();
    }
    
    public static int compareVersionStrings(final String s, final String s2) {
        final int n = -1;
        int n2;
        if (s == null || s2 == null) {
            n2 = 0;
        }
        else {
            try {
                final Scanner scanner = new Scanner(s.replaceAll("\\-.*", ""));
                final Scanner scanner2 = new Scanner(s2.replaceAll("\\-.*", ""));
                scanner.useDelimiter("\\.");
                scanner2.useDelimiter("\\.");
                while (scanner.hasNextInt() && scanner2.hasNextInt()) {
                    final int nextInt = scanner.nextInt();
                    final int nextInt2 = scanner2.nextInt();
                    n2 = n;
                    if (nextInt < nextInt2) {
                        return n2;
                    }
                    if (nextInt > nextInt2) {
                        return 1;
                    }
                }
                if (scanner.hasNextInt()) {
                    return 1;
                }
                final boolean hasNextInt = scanner2.hasNextInt();
                n2 = n;
                if (!hasNextInt) {
                    return 0;
                }
            }
            catch (Exception ex) {
                return 0;
            }
        }
        return n2;
    }
    
    private static long failSafeGetLongFromJSON(final JSONObject jsonObject, final String s, final long n) {
        try {
            return jsonObject.getLong(s);
        }
        catch (JSONException ex) {
            return n;
        }
    }
    
    private static String failSafeGetStringFromJSON(final JSONObject jsonObject, final String s, final String s2) {
        try {
            return jsonObject.getString(s);
        }
        catch (JSONException ex) {
            return s2;
        }
    }
    
    private String getRestoreButton(final int n, final JSONObject jsonObject) {
        final StringBuilder sb = new StringBuilder();
        final String versionID = this.getVersionID(jsonObject);
        if (!TextUtils.isEmpty((CharSequence)versionID)) {
            sb.append("<a href='restore:" + versionID + "'  style='background: #c8c8c8; color: #000; display: block; float: right; padding: 7px; margin: 0px 10px 10px; text-decoration: none;'>Restore</a>");
        }
        return sb.toString();
    }
    
    private Object getSeparator() {
        return "<hr style='border-top: 1px solid #c8c8c8; border-bottom: 0px; margin: 40px 10px 0px 10px;' />";
    }
    
    private int getVersionCode(final JSONObject jsonObject) {
        try {
            return jsonObject.getInt("version");
        }
        catch (JSONException ex) {
            return 0;
        }
    }
    
    private String getVersionID(final JSONObject jsonObject) {
        try {
            return jsonObject.getString("id");
        }
        catch (JSONException ex) {
            return "";
        }
    }
    
    private String getVersionLine(final int n, final JSONObject jsonObject) {
        final StringBuilder sb = new StringBuilder();
        final int versionCode = this.getVersionCode(this.mNewest);
        final int versionCode2 = this.getVersionCode(jsonObject);
        final String versionName = this.getVersionName(jsonObject);
        sb.append("<div style='padding: 20px 10px 10px;'><strong>");
        if (n == 0) {
            sb.append("Newest version:");
        }
        else {
            sb.append("Version " + versionName + " (" + versionCode2 + "): ");
            if (versionCode2 != versionCode && versionCode2 == this.mCurrentVersionCode) {
                this.mCurrentVersionCode = -1;
                sb.append("[INSTALLED]");
            }
        }
        sb.append("</strong></div>");
        return sb.toString();
    }
    
    private String getVersionName(final JSONObject jsonObject) {
        try {
            return jsonObject.getString("shortversion");
        }
        catch (JSONException ex) {
            return "";
        }
    }
    
    private String getVersionNotes(final int n, final JSONObject jsonObject) {
        final StringBuilder sb = new StringBuilder();
        final String failSafeGetStringFromJSON = failSafeGetStringFromJSON(jsonObject, "notes", "");
        sb.append("<div style='padding: 0px 10px;'>");
        if (failSafeGetStringFromJSON.trim().length() == 0) {
            sb.append("<em>No information.</em>");
        }
        else {
            sb.append(failSafeGetStringFromJSON);
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    public static boolean isNewerThanLastUpdateTime(final Context context, final long n) {
        if (context != null) {
            try {
                if (n > new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified() / 1000L + 1800L) {
                    return true;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    private void loadVersions(final Context context, final String s) {
        JSONArray jsonArray;
        int n;
        int n2;
        JSONObject jsonObject;
        boolean b = false;
        boolean b2 = false;
        Label_0134_Outer:Label_0119_Outer:
        while (true) {
            this.mNewest = new JSONObject();
            this.mSortedVersions = new ArrayList<JSONObject>();
            this.mCurrentVersionCode = this.mListener.getCurrentVersionCode();
            while (true) {
                while (true) {
                    Label_0167: {
                        try {
                            jsonArray = new JSONArray(s);
                            n = this.mListener.getCurrentVersionCode();
                            n2 = 0;
                            while (true) {
                                if (n2 < jsonArray.length()) {
                                    jsonObject = jsonArray.getJSONObject(n2);
                                    b = (jsonObject.getInt("version") > n);
                                    b2 = (jsonObject.getInt("version") == n && isNewerThanLastUpdateTime(context, jsonObject.getLong("timestamp")));
                                    break Label_0167;
                                }
                                goto Label_0164;
                                this.mSortedVersions.add(jsonObject);
                                ++n2;
                                continue Label_0134_Outer;
                            }
                            this.mNewest = jsonObject;
                            n = jsonObject.getInt("version");
                            continue Label_0119_Outer;
                        }
                        catch (NullPointerException ex) {}
                        catch (JSONException ex2) {
                            return;
                        }
                    }
                    if (!b && !b2) {
                        continue Label_0119_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    public static String mapGoogleVersion(final String s) {
        String s2;
        if (s == null || s.equalsIgnoreCase("L")) {
            s2 = "5.0";
        }
        else {
            if (s.equalsIgnoreCase("M")) {
                return "6.0";
            }
            if (s.equalsIgnoreCase("N")) {
                return "7.0";
            }
            s2 = s;
            if (Pattern.matches("^[a-zA-Z]+", s)) {
                return "99.0";
            }
        }
        return s2;
    }
    
    private void sortVersions() {
        Collections.sort(this.mSortedVersions, new Comparator<JSONObject>() {
            @Override
            public int compare(final JSONObject jsonObject, final JSONObject jsonObject2) {
                try {
                    if (jsonObject.getInt("version") > jsonObject2.getInt("version")) {}
                    return 0;
                }
                catch (NullPointerException ex) {
                    return 0;
                }
                catch (JSONException ex2) {
                    return 0;
                }
            }
        });
    }
    
    @SuppressLint({ "SimpleDateFormat" })
    public String getFileDateString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(1000L * failSafeGetLongFromJSON(this.mNewest, "timestamp", 0L)));
    }
    
    public long getFileSizeBytes() {
        final boolean booleanValue = Boolean.valueOf(failSafeGetStringFromJSON(this.mNewest, "external", "false"));
        long failSafeGetLongFromJSON;
        final long n = failSafeGetLongFromJSON = failSafeGetLongFromJSON(this.mNewest, "appsize", 0L);
        if (booleanValue) {
            failSafeGetLongFromJSON = n;
            if (n == 0L) {
                failSafeGetLongFromJSON = -1L;
            }
        }
        return failSafeGetLongFromJSON;
    }
    
    public String getReleaseNotes(final boolean b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body style='padding: 0px 0px 20px 0px'>");
        int n = 0;
        for (final JSONObject jsonObject : this.mSortedVersions) {
            if (n > 0) {
                sb.append(this.getSeparator());
                if (b) {
                    sb.append(this.getRestoreButton(n, jsonObject));
                }
            }
            sb.append(this.getVersionLine(n, jsonObject));
            sb.append(this.getVersionNotes(n, jsonObject));
            ++n;
        }
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
    
    public String getVersionString() {
        return failSafeGetStringFromJSON(this.mNewest, "shortversion", "") + " (" + failSafeGetStringFromJSON(this.mNewest, "version", "") + ")";
    }
}
