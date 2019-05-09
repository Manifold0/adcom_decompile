package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Report implements Memorable {
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

    public static class UserAction implements Memorable {
        private final String action;
        private final long timestamp;
        private final String value;

        public UserAction(byte[] array) {
            ByteBuffer buffy = ByteBuffer.wrap(array);
            this.action = MemoryUtils.extractString(buffy);
            this.value = MemoryUtils.extractString(buffy);
            this.timestamp = buffy.getLong();
        }

        public UserAction(String action, String value, long timestamp) {
            this.action = action;
            this.value = value;
            this.timestamp = timestamp;
        }

        public byte[] toByteArray() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                MemoryUtils.writeString(this.action, out);
                MemoryUtils.writeString(this.value, out);
                out.write(MemoryUtils.toBytes(this.timestamp));
                return out.toByteArray();
            } catch (IOException e) {
                Log.e("Report.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof UserAction)) {
                return false;
            }
            UserAction wrap = (UserAction) obj;
            if (wrap.action.equals(this.action) && wrap.value.equals(this.value) && wrap.timestamp == this.timestamp) {
                return true;
            }
            return false;
        }

        @NonNull
        public String getId() {
            return "" + this.timestamp;
        }

        public JsonObject toJson() {
            JsonObject ret = new JsonObject();
            ret.addProperty("action", this.action);
            if (!this.value.isEmpty()) {
                ret.addProperty("value", this.value);
            }
            ret.addProperty("timestamp_millis", Long.valueOf(this.timestamp));
            return ret;
        }
    }

    public Report(byte[] array) {
        boolean z = true;
        if (array.length == 0) {
            throw new IllegalArgumentException("Cannot create Report from empty array");
        }
        boolean z2;
        this.advertisementID = "";
        ByteBuffer buffy = ByteBuffer.wrap(array);
        this.placementId = MemoryUtils.extractString(buffy);
        this.adToken = MemoryUtils.extractString(buffy);
        this.appId = MemoryUtils.extractString(buffy);
        if (buffy.get() == (byte) 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.incentivized = z2;
        this.adStartTime = buffy.getLong();
        this.url = MemoryUtils.extractString(buffy);
        this.adDuration = buffy.getLong();
        this.ttDownload = buffy.getInt();
        this.campaign = MemoryUtils.extractString(buffy);
        this.videoViewed = buffy.getInt();
        this.adType = MemoryUtils.extractString(buffy);
        this.templateId = MemoryUtils.extractString(buffy);
        if (buffy.get() != (byte) 1) {
            z = false;
        }
        this.wasCTAClicked = z;
        this.clickedThrough = new ArrayList(Arrays.asList(MemoryUtils.extractStringArray(buffy)));
        this.errors = new ArrayList(Arrays.asList(MemoryUtils.extractStringArray(buffy)));
        int userActionCount = buffy.getInt();
        this.userActions = new ArrayList(userActionCount);
        int x = 0;
        while (x < userActionCount) {
            try {
                this.userActions.add(MemoryUtils.extractMemorable(buffy, UserAction.class));
                x++;
            } catch (NoSuchMethodException noMethod) {
                noMethod.printStackTrace();
                throw new RuntimeException(noMethod);
            } catch (IllegalAccessException illegalAccess) {
                illegalAccess.printStackTrace();
                throw new RuntimeException(illegalAccess);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }
        this.userID = MemoryUtils.extractString(buffy);
        this.ordinal = buffy.getInt();
    }

    public Report(@NonNull Advertisement advertisement, @NonNull Placement placement, long startTime) {
        this(advertisement, placement, startTime, null);
    }

    public Report(@NonNull Advertisement advertisement, @NonNull Placement placement, long startTime, @Nullable String userID) {
        this.placementId = placement.getId();
        this.adToken = advertisement.getAdToken();
        this.advertisementID = advertisement.getId();
        this.appId = advertisement.getAppID();
        this.incentivized = placement.isIncentivized();
        this.adStartTime = startTime;
        this.url = advertisement.getUrl();
        this.ttDownload = -1;
        this.campaign = advertisement.getCampaign();
        switch (advertisement.getAdType()) {
            case 0:
                this.adType = "vungle_local";
                break;
            case 1:
                this.adType = "vungle_mraid";
                break;
            default:
                throw new IllegalArgumentException("Unknown ad type, cannot process!");
        }
        this.templateId = advertisement.getTemplateId();
        this.clickedThrough = new ArrayList();
        this.errors = new ArrayList();
        this.userActions = new ArrayList();
        if (userID == null) {
            this.userID = "";
        } else {
            this.userID = userID;
        }
        this.ordinal = advertisement.getAdConfig().getOrdinal();
    }

    public void recordAction(String action, String value, long timestamp) {
        this.userActions.add(new UserAction(action, value, timestamp));
        this.clickedThrough.add(action);
        if (action.equals("download")) {
            this.wasCTAClicked = true;
        }
    }

    public void recordError(String description) {
        this.errors.add(description);
    }

    public void recordProgress(int viewed) {
        this.videoViewed = viewed;
    }

    public void setAdDuration(int duration) {
        this.adDuration = (long) duration;
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public String getAdvertisementID() {
        return this.advertisementID;
    }

    public boolean isCTAClicked() {
        return this.wasCTAClicked;
    }

    public String getUserID() {
        return this.userID;
    }

    public JsonObject toReportBody() {
        JsonObject request = new JsonObject();
        request.addProperty("placement_reference_id", this.placementId);
        request.addProperty("ad_token", this.adToken);
        request.addProperty("app_id", this.appId);
        request.addProperty("incentivized", Integer.valueOf(this.incentivized ? 1 : 0));
        request.addProperty("adStartTime", Long.valueOf(this.adStartTime));
        request.addProperty("url", this.url);
        request.addProperty("adDuration", Long.valueOf(this.adDuration));
        request.addProperty("ttDownload", Integer.valueOf(this.ttDownload));
        request.addProperty("campaign", this.campaign);
        request.addProperty("adType", this.adType);
        request.addProperty("templateId", this.templateId);
        JsonArray plays = new JsonArray();
        JsonObject playsObject = new JsonObject();
        playsObject.addProperty("startTime", Long.valueOf(this.adStartTime));
        if (this.videoViewed > 0) {
            playsObject.addProperty("videoViewed", Integer.valueOf(this.videoViewed));
            playsObject.addProperty(String.VIDEO_LENGTH, Long.valueOf(this.adDuration));
        }
        JsonArray userActionJson = new JsonArray();
        Iterator it = this.userActions.iterator();
        while (it.hasNext()) {
            userActionJson.add(((UserAction) it.next()).toJson());
        }
        playsObject.add("userActions", userActionJson);
        plays.add(playsObject);
        request.add("plays", plays);
        JsonArray errorsJson = new JsonArray();
        it = this.errors.iterator();
        while (it.hasNext()) {
            errorsJson.add((String) it.next());
        }
        request.add("errors", errorsJson);
        JsonArray clicked = new JsonArray();
        it = this.clickedThrough.iterator();
        while (it.hasNext()) {
            clicked.add((String) it.next());
        }
        request.add("clickedThrough", clicked);
        if (this.incentivized && !TextUtils.isEmpty(this.userID)) {
            request.addProperty("user", this.userID);
        }
        if (this.ordinal > 0) {
            request.addProperty("ordinal_view", Integer.valueOf(this.ordinal));
        }
        return request;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Report)) {
            return false;
        }
        Report wrap = (Report) obj;
        if (!wrap.placementId.equals(this.placementId)) {
            return false;
        }
        if (!wrap.adToken.equals(this.adToken)) {
            return false;
        }
        if (!wrap.appId.equals(this.appId)) {
            return false;
        }
        if (wrap.incentivized != this.incentivized) {
            return false;
        }
        if (wrap.adStartTime != this.adStartTime) {
            return false;
        }
        if (!wrap.url.equals(this.url)) {
            return false;
        }
        if (wrap.adDuration != this.adDuration) {
            return false;
        }
        if (wrap.ttDownload != this.ttDownload) {
            return false;
        }
        if (!wrap.campaign.equals(this.campaign)) {
            return false;
        }
        if (!wrap.adType.equals(this.adType)) {
            return false;
        }
        if (!wrap.templateId.equals(this.templateId)) {
            return false;
        }
        if (wrap.wasCTAClicked != this.wasCTAClicked) {
            return false;
        }
        if (!wrap.userID.equals(this.userID)) {
            return false;
        }
        if (wrap.clickedThrough.size() != this.clickedThrough.size()) {
            return false;
        }
        int x;
        for (x = 0; x < this.clickedThrough.size(); x++) {
            if (!((String) wrap.clickedThrough.get(x)).equals(this.clickedThrough.get(x))) {
                return false;
            }
        }
        if (wrap.errors.size() != this.errors.size()) {
            return false;
        }
        for (x = 0; x < this.errors.size(); x++) {
            if (!((String) wrap.errors.get(x)).equals(this.errors.get(x))) {
                return false;
            }
        }
        if (wrap.userActions.size() != this.userActions.size()) {
            return false;
        }
        for (x = 0; x < this.userActions.size(); x++) {
            if (!((UserAction) wrap.userActions.get(x)).equals(this.userActions.get(x))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public byte[] toByteArray() {
        int i = 1;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            MemoryUtils.writeString(this.placementId, out);
            MemoryUtils.writeString(this.adToken, out);
            MemoryUtils.writeString(this.appId, out);
            out.write(this.incentivized ? 1 : 0);
            out.write(MemoryUtils.toBytes(this.adStartTime));
            MemoryUtils.writeString(this.url, out);
            out.write(MemoryUtils.toBytes(this.adDuration));
            out.write(MemoryUtils.toBytes(this.ttDownload));
            MemoryUtils.writeString(this.campaign, out);
            out.write(MemoryUtils.toBytes(this.videoViewed));
            MemoryUtils.writeString(this.adType, out);
            MemoryUtils.writeString(this.templateId, out);
            if (!this.wasCTAClicked) {
                i = 0;
            }
            out.write(i);
            MemoryUtils.writeStringArray((String[]) this.clickedThrough.toArray(new String[this.clickedThrough.size()]), out);
            MemoryUtils.writeStringArray((String[]) this.errors.toArray(new String[this.errors.size()]), out);
            out.write(MemoryUtils.toBytes(this.userActions.size()));
            Iterator it = this.userActions.iterator();
            while (it.hasNext()) {
                MemoryUtils.writeMemorable((UserAction) it.next(), out);
            }
            MemoryUtils.writeString(this.userID, out);
            out.write(MemoryUtils.toBytes(this.ordinal));
            return out.toByteArray();
        } catch (IOException e) {
            Log.e("Report.java", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    @NonNull
    public String getId() {
        return this.placementId + "_" + this.adStartTime;
    }

    public long getAdStartTime() {
        return this.adStartTime;
    }

    public static Report restore(int oldVersion, int newVersion, byte[] data) {
        if (data == null || data.length <= 0) {
            return null;
        }
        return new Report(Arrays.copyOfRange(data, 1, data.length));
    }
}
