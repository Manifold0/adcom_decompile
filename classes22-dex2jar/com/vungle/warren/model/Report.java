// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.model;

import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.io.IOException;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Arrays;
import com.vungle.warren.persistence.MemoryUtils;
import java.nio.ByteBuffer;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import com.vungle.warren.persistence.Memorable;

public class Report implements Memorable
{
    private long adDuration;
    private final long adStartTime;
    private final String adToken;
    private final String adType;
    private final String advertisementID;
    private final String appId;
    private final String campaign;
    private final ArrayList<String> clickedThrough;
    private final ArrayList<String> errors;
    private final boolean incentivized;
    private final int ordinal;
    private final String placementId;
    private final String templateId;
    private final int ttDownload;
    private final String url;
    private final ArrayList<UserAction> userActions;
    private final String userID;
    private int videoViewed;
    private boolean wasCTAClicked;
    
    public Report(@NonNull final Advertisement advertisement, @NonNull final Placement placement, final long n) {
        this(advertisement, placement, n, null);
    }
    
    public Report(@NonNull final Advertisement advertisement, @NonNull final Placement placement, final long adStartTime, @Nullable final String userID) {
        this.placementId = placement.getId();
        this.adToken = advertisement.getAdToken();
        this.advertisementID = advertisement.getId();
        this.appId = advertisement.getAppID();
        this.incentivized = placement.isIncentivized();
        this.adStartTime = adStartTime;
        this.url = advertisement.getUrl();
        this.ttDownload = -1;
        this.campaign = advertisement.getCampaign();
        switch (advertisement.getAdType()) {
            default: {
                throw new IllegalArgumentException("Unknown ad type, cannot process!");
            }
            case 0: {
                this.adType = "vungle_local";
                break;
            }
            case 1: {
                this.adType = "vungle_mraid";
                break;
            }
        }
        this.templateId = advertisement.getTemplateId();
        this.clickedThrough = new ArrayList<String>();
        this.errors = new ArrayList<String>();
        this.userActions = new ArrayList<UserAction>();
        if (userID == null) {
            this.userID = "";
        }
        else {
            this.userID = userID;
        }
        this.ordinal = advertisement.getAdConfig().getOrdinal();
    }
    
    public Report(final byte[] array) {
        final boolean b = true;
        if (array.length == 0) {
            throw new IllegalArgumentException("Cannot create Report from empty array");
        }
        this.advertisementID = "";
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        this.placementId = MemoryUtils.extractString(wrap);
        this.adToken = MemoryUtils.extractString(wrap);
        this.appId = MemoryUtils.extractString(wrap);
        Label_0237: {
            if (wrap.get() != 1) {
                break Label_0237;
            }
            boolean incentivized = true;
        Label_0150_Outer:
            while (true) {
                this.incentivized = incentivized;
                this.adStartTime = wrap.getLong();
                this.url = MemoryUtils.extractString(wrap);
                this.adDuration = wrap.getLong();
                this.ttDownload = wrap.getInt();
                this.campaign = MemoryUtils.extractString(wrap);
                this.videoViewed = wrap.getInt();
                this.adType = MemoryUtils.extractString(wrap);
                this.templateId = MemoryUtils.extractString(wrap);
                Label_0243: {
                    if (wrap.get() != 1) {
                        break Label_0243;
                    }
                    boolean wasCTAClicked = b;
                Label_0211_Outer:
                    while (true) {
                        this.wasCTAClicked = wasCTAClicked;
                        this.clickedThrough = new ArrayList<String>(Arrays.asList(MemoryUtils.extractStringArray(wrap)));
                        this.errors = new ArrayList<String>(Arrays.asList(MemoryUtils.extractStringArray(wrap)));
                        final int int1 = wrap.getInt();
                        this.userActions = new ArrayList<UserAction>(int1);
                        int n = 0;
                        while (true) {
                            if (n >= int1) {
                                break Label_0237;
                            }
                            try {
                                this.userActions.add(MemoryUtils.extractMemorable(wrap, UserAction.class));
                                ++n;
                                continue;
                                incentivized = false;
                                continue Label_0150_Outer;
                                wasCTAClicked = false;
                                continue Label_0211_Outer;
                            }
                            catch (NoSuchMethodException ex) {
                                ex.printStackTrace();
                                throw new RuntimeException(ex);
                            }
                            catch (IllegalAccessException ex2) {
                                ex2.printStackTrace();
                                throw new RuntimeException(ex2);
                            }
                            catch (InstantiationException ex3) {
                                ex3.printStackTrace();
                                throw new RuntimeException(ex3);
                            }
                            catch (InvocationTargetException ex4) {
                                ex4.printStackTrace();
                                throw new RuntimeException(ex4);
                            }
                            break;
                        }
                        break;
                    }
                }
                break;
            }
        }
        this.userID = MemoryUtils.extractString(wrap);
        this.ordinal = wrap.getInt();
    }
    
    public static Report restore(final int n, final int n2, final byte[] array) {
        if (array == null || array.length <= 0) {
            return null;
        }
        return new Report(Arrays.copyOfRange(array, 1, array.length));
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Report)) {
            return false;
        }
        final Report report = (Report)o;
        if (!report.placementId.equals(this.placementId)) {
            return false;
        }
        if (!report.adToken.equals(this.adToken)) {
            return false;
        }
        if (!report.appId.equals(this.appId)) {
            return false;
        }
        if (report.incentivized != this.incentivized) {
            return false;
        }
        if (report.adStartTime != this.adStartTime) {
            return false;
        }
        if (!report.url.equals(this.url)) {
            return false;
        }
        if (report.adDuration != this.adDuration) {
            return false;
        }
        if (report.ttDownload != this.ttDownload) {
            return false;
        }
        if (!report.campaign.equals(this.campaign)) {
            return false;
        }
        if (!report.adType.equals(this.adType)) {
            return false;
        }
        if (!report.templateId.equals(this.templateId)) {
            return false;
        }
        if (report.wasCTAClicked != this.wasCTAClicked) {
            return false;
        }
        if (!report.userID.equals(this.userID)) {
            return false;
        }
        if (report.clickedThrough.size() != this.clickedThrough.size()) {
            return false;
        }
        for (int i = 0; i < this.clickedThrough.size(); ++i) {
            if (!report.clickedThrough.get(i).equals(this.clickedThrough.get(i))) {
                return false;
            }
        }
        if (report.errors.size() != this.errors.size()) {
            return false;
        }
        for (int j = 0; j < this.errors.size(); ++j) {
            if (!report.errors.get(j).equals(this.errors.get(j))) {
                return false;
            }
        }
        if (report.userActions.size() != this.userActions.size()) {
            return false;
        }
        for (int k = 0; k < this.userActions.size(); ++k) {
            if (!report.userActions.get(k).equals(this.userActions.get(k))) {
                return false;
            }
        }
        return true;
    }
    
    public long getAdStartTime() {
        return this.adStartTime;
    }
    
    public String getAdvertisementID() {
        return this.advertisementID;
    }
    
    @NonNull
    @Override
    public String getId() {
        return this.placementId + "_" + this.adStartTime;
    }
    
    public String getPlacementId() {
        return this.placementId;
    }
    
    public String getUserID() {
        return this.userID;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    public boolean isCTAClicked() {
        return this.wasCTAClicked;
    }
    
    public void recordAction(final String s, final String s2, final long n) {
        this.userActions.add(new UserAction(s, s2, n));
        this.clickedThrough.add(s);
        if (s.equals("download")) {
            this.wasCTAClicked = true;
        }
    }
    
    public void recordError(final String s) {
        this.errors.add(s);
    }
    
    public void recordProgress(final int videoViewed) {
        this.videoViewed = videoViewed;
    }
    
    public void setAdDuration(final int n) {
        this.adDuration = n;
    }
    
    @Override
    public byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = null;
    Label_0133_Outer:
        while (true) {
            final int n = 1;
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
            Label_0279:
                while (true) {
                    try {
                        MemoryUtils.writeString(this.placementId, byteArrayOutputStream);
                        MemoryUtils.writeString(this.adToken, byteArrayOutputStream);
                        MemoryUtils.writeString(this.appId, byteArrayOutputStream);
                        if (this.incentivized) {
                            final int n2 = 1;
                            byteArrayOutputStream.write(n2);
                            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adStartTime));
                            MemoryUtils.writeString(this.url, byteArrayOutputStream);
                            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adDuration));
                            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ttDownload));
                            MemoryUtils.writeString(this.campaign, byteArrayOutputStream);
                            byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoViewed));
                            MemoryUtils.writeString(this.adType, byteArrayOutputStream);
                            MemoryUtils.writeString(this.templateId, byteArrayOutputStream);
                            if (this.wasCTAClicked) {
                                final int n3 = n;
                                byteArrayOutputStream.write(n3);
                                MemoryUtils.writeStringArray(this.clickedThrough.toArray(new String[this.clickedThrough.size()]), byteArrayOutputStream);
                                MemoryUtils.writeStringArray(this.errors.toArray(new String[this.errors.size()]), byteArrayOutputStream);
                                byteArrayOutputStream.write(MemoryUtils.toBytes(this.userActions.size()));
                                final Iterator<UserAction> iterator = this.userActions.iterator();
                                while (iterator.hasNext()) {
                                    MemoryUtils.writeMemorable(iterator.next(), byteArrayOutputStream);
                                }
                                break;
                            }
                            break Label_0279;
                        }
                    }
                    catch (IOException ex) {
                        Log.e("Report.java", "Failed to write " + this + " to a byte array");
                        return new byte[0];
                    }
                    final int n2 = 0;
                    continue Label_0133_Outer;
                }
                final int n3 = 0;
                continue;
            }
        }
        MemoryUtils.writeString(this.userID, byteArrayOutputStream);
        byteArrayOutputStream.write(MemoryUtils.toBytes(this.ordinal));
        return byteArrayOutputStream.toByteArray();
    }
    
    public JsonObject toReportBody() {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("placement_reference_id", this.placementId);
        jsonObject.addProperty("ad_token", this.adToken);
        jsonObject.addProperty("app_id", this.appId);
        int n;
        if (this.incentivized) {
            n = 1;
        }
        else {
            n = 0;
        }
        jsonObject.addProperty("incentivized", (Number)n);
        jsonObject.addProperty("adStartTime", (Number)this.adStartTime);
        jsonObject.addProperty("url", this.url);
        jsonObject.addProperty("adDuration", (Number)this.adDuration);
        jsonObject.addProperty("ttDownload", (Number)this.ttDownload);
        jsonObject.addProperty("campaign", this.campaign);
        jsonObject.addProperty("adType", this.adType);
        jsonObject.addProperty("templateId", this.templateId);
        final JsonArray jsonArray = new JsonArray();
        final JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("startTime", (Number)this.adStartTime);
        if (this.videoViewed > 0) {
            jsonObject2.addProperty("videoViewed", (Number)this.videoViewed);
            jsonObject2.addProperty("videoLength", (Number)this.adDuration);
        }
        final JsonArray jsonArray2 = new JsonArray();
        final Iterator<UserAction> iterator = this.userActions.iterator();
        while (iterator.hasNext()) {
            jsonArray2.add((JsonElement)iterator.next().toJson());
        }
        jsonObject2.add("userActions", (JsonElement)jsonArray2);
        jsonArray.add((JsonElement)jsonObject2);
        jsonObject.add("plays", (JsonElement)jsonArray);
        final JsonArray jsonArray3 = new JsonArray();
        final Iterator<String> iterator2 = this.errors.iterator();
        while (iterator2.hasNext()) {
            jsonArray3.add((String)iterator2.next());
        }
        jsonObject.add("errors", (JsonElement)jsonArray3);
        final JsonArray jsonArray4 = new JsonArray();
        final Iterator<String> iterator3 = this.clickedThrough.iterator();
        while (iterator3.hasNext()) {
            jsonArray4.add((String)iterator3.next());
        }
        jsonObject.add("clickedThrough", (JsonElement)jsonArray4);
        if (this.incentivized && !TextUtils.isEmpty((CharSequence)this.userID)) {
            jsonObject.addProperty("user", this.userID);
        }
        if (this.ordinal > 0) {
            jsonObject.addProperty("ordinal_view", (Number)this.ordinal);
        }
        return jsonObject;
    }
    
    public static class UserAction implements Memorable
    {
        private final String action;
        private final long timestamp;
        private final String value;
        
        public UserAction(final String action, final String value, final long timestamp) {
            this.action = action;
            this.value = value;
            this.timestamp = timestamp;
        }
        
        public UserAction(final byte[] array) {
            final ByteBuffer wrap = ByteBuffer.wrap(array);
            this.action = MemoryUtils.extractString(wrap);
            this.value = MemoryUtils.extractString(wrap);
            this.timestamp = wrap.getLong();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof UserAction) {
                final UserAction userAction = (UserAction)o;
                if (userAction.action.equals(this.action) && userAction.value.equals(this.value) && userAction.timestamp == this.timestamp) {
                    return true;
                }
            }
            return false;
        }
        
        @NonNull
        @Override
        public String getId() {
            return "" + this.timestamp;
        }
        
        @Override
        public byte[] toByteArray() {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                MemoryUtils.writeString(this.action, byteArrayOutputStream);
                MemoryUtils.writeString(this.value, byteArrayOutputStream);
                byteArrayOutputStream.write(MemoryUtils.toBytes(this.timestamp));
                return byteArrayOutputStream.toByteArray();
            }
            catch (IOException ex) {
                Log.e("Report.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }
        
        public JsonObject toJson() {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", this.action);
            if (!this.value.isEmpty()) {
                jsonObject.addProperty("value", this.value);
            }
            jsonObject.addProperty("timestamp_millis", (Number)this.timestamp);
            return jsonObject;
        }
    }
}
