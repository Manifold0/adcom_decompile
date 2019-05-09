package com.vungle.warren.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.warren.AdConfig;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class Advertisement implements Memorable, Cloneable {
    public static final int DONE = 3;
    public static final int ERROR = 4;
    public static final String KEY_APP_ICON = "appicon";
    public static final String KEY_POSTROLL = "postroll";
    public static final String KEY_POWERED_BY_VUNGLE = "powervungle";
    public static final String KEY_TEMPLATE = "template";
    public static final String KEY_VIDEO = "video";
    public static final int LANDSCAPE = 1;
    public static final int NEW = 0;
    public static final int PORTRAIT = 0;
    public static final String POSTROLL_UNZIP = "postrollUnzip";
    public static final int READY = 1;
    public static final int ROTATE = 2;
    private static final String TAG = "Advertisement";
    public static final int TYPE_VUNGLE_LOCAL = 0;
    public static final int TYPE_VUNGLE_MRAID = 1;
    public static final int VIEWING = 2;
    private AdConfig adConfig;
    private final String adMarketId;
    private final String adToken;
    @AdType
    private final int adType;
    private final String appID;
    private final String bidToken;
    private final String campaign;
    private final ArrayList<Checkpoint> checkpoints;
    private final String[] clickUrls;
    private final String[] closeUrls;
    private final int countdown;
    private final int ctaClickArea;
    private final String ctaDestinationUrl;
    private final boolean ctaOverlayEnabled;
    private final boolean ctaShowOnClick;
    private final int ctaTimeEnabled;
    private final int ctaTimeShow;
    private final String ctaUrl;
    private final int delay;
    private final boolean enableMoat;
    private final long expireTime;
    private final String identifier;
    private final String md5;
    private final String moatExtraVast;
    private final String[] muteUrls;
    private final String[] postRollClickUrls;
    private final String[] postRollViewUrls;
    private final String postrollBundleUrl;
    private final boolean requiresNonMarketInstall;
    private final int retryCount;
    private final int showCloseDelay;
    private final int showCloseIncentivized;
    private int state = 0;
    private final String templateId;
    private final Map<String, String> templateSettings;
    private final String templateType;
    private final String templateUrl;
    private final String[] unmuteUrls;
    private final int videoHeight;
    private final String videoIdentifier;
    private final String videoUrl;
    private final int videoWidth;

    public @interface AdType {
    }

    public @interface CacheKey {
    }

    public static class Checkpoint implements Comparable<Checkpoint>, Memorable {
        private final byte percentage;
        private final String[] urls;

        public Checkpoint(byte[] array) {
            ByteBuffer buffy = ByteBuffer.wrap(array);
            this.percentage = buffy.get();
            this.urls = MemoryUtils.extractStringArray(buffy);
        }

        public Checkpoint(JsonObject json) throws IllegalArgumentException {
            if (json.has("checkpoint")) {
                this.percentage = (byte) ((int) (json.get("checkpoint").getAsFloat() * 100.0f));
                if (JsonUtil.hasNonNull(json, "urls")) {
                    JsonArray urlsArray = json.getAsJsonArray("urls");
                    this.urls = new String[urlsArray.size()];
                    int x = 0;
                    while (x < urlsArray.size()) {
                        if (urlsArray.get(x) == null || "null".equalsIgnoreCase(urlsArray.get(x).toString())) {
                            this.urls[x] = "";
                        } else {
                            this.urls[x] = urlsArray.get(x).getAsString();
                        }
                        x++;
                    }
                    return;
                }
                throw new IllegalArgumentException("Checkpoint missing reporting URL!");
            }
            throw new IllegalArgumentException("Checkpoint missing percentage!");
        }

        public Checkpoint(JsonArray urlsArray, byte percentage) {
            if (urlsArray.size() == 0) {
                throw new IllegalArgumentException("Empty URLS!");
            }
            this.urls = new String[urlsArray.size()];
            for (int x = 0; x < urlsArray.size(); x++) {
                this.urls[x] = urlsArray.get(x).getAsString();
            }
            this.percentage = percentage;
        }

        public String[] getUrls() {
            return this.urls;
        }

        public byte getPercentage() {
            return this.percentage;
        }

        public int compareTo(@NonNull Checkpoint o) {
            return Float.compare((float) this.percentage, (float) o.percentage);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Checkpoint)) {
                return false;
            }
            Checkpoint wrap = (Checkpoint) obj;
            if (wrap.percentage != this.percentage || wrap.urls.length != this.urls.length) {
                return false;
            }
            for (int x = 0; x < this.urls.length; x++) {
                if (!wrap.urls[x].equals(this.urls[x])) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return super.hashCode();
        }

        public byte[] toByteArray() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                out.write(this.percentage);
                MemoryUtils.writeStringArray(this.urls, out);
                return out.toByteArray();
            } catch (IOException e) {
                Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }

        @NonNull
        public String getId() {
            return "checkpoint";
        }
    }

    public @interface Orientation {
    }

    public @interface State {
    }

    public Advertisement(byte[] array) {
        boolean z = true;
        if (array.length == 0) {
            throw new IllegalArgumentException("Empty array cannot be used to construct Advertisement");
        }
        boolean z2;
        ByteBuffer buffy = ByteBuffer.wrap(array);
        this.adConfig = new AdConfig();
        this.adType = buffy.getInt();
        this.expireTime = buffy.getLong();
        this.delay = buffy.getInt();
        this.showCloseDelay = buffy.getInt();
        this.showCloseIncentivized = buffy.getInt();
        this.countdown = buffy.getInt();
        this.videoWidth = buffy.getInt();
        this.videoHeight = buffy.getInt();
        if (buffy.get() == (byte) 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.ctaOverlayEnabled = z2;
        if (buffy.get() == (byte) 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.ctaShowOnClick = z2;
        this.ctaTimeEnabled = buffy.getInt();
        this.ctaTimeShow = buffy.getInt();
        this.ctaClickArea = buffy.getInt();
        this.retryCount = buffy.getInt();
        if (buffy.get() == (byte) 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.enableMoat = z2;
        if (buffy.get() == (byte) 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.requiresNonMarketInstall = z2;
        int settings = buffy.getInt();
        int flexCloseSec = buffy.getInt();
        int ordinalCount = buffy.getInt();
        this.adConfig.setFlexViewCloseTime(flexCloseSec);
        this.adConfig.setOrdinal(ordinalCount);
        AdConfig adConfig = this.adConfig;
        if ((settings & 16) == 16) {
            z2 = true;
        } else {
            z2 = false;
        }
        adConfig.setAutoRotate(z2);
        adConfig = this.adConfig;
        if ((settings & 4) == 4) {
            z2 = true;
        } else {
            z2 = false;
        }
        adConfig.setImmersiveMode(z2);
        adConfig = this.adConfig;
        if ((settings & 1) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        adConfig.setMuted(z2);
        Map<String, Object> extras = new HashMap();
        String str = AdConfig.FLAG_DIRECT_DOWNLOAD;
        if ((settings & 32) == 32) {
            z2 = true;
        } else {
            z2 = false;
        }
        extras.put(str, Boolean.valueOf(z2));
        this.adConfig.setExtraSettings(extras);
        AdConfig adConfig2 = this.adConfig;
        if ((settings & 8) != 8) {
            z = false;
        }
        adConfig2.setTransitionAnimationEnabled(z);
        this.identifier = MemoryUtils.extractString(buffy);
        this.appID = MemoryUtils.extractString(buffy);
        this.campaign = MemoryUtils.extractString(buffy);
        this.videoUrl = MemoryUtils.extractString(buffy);
        this.md5 = MemoryUtils.extractString(buffy);
        this.postrollBundleUrl = MemoryUtils.extractString(buffy);
        this.ctaDestinationUrl = MemoryUtils.extractString(buffy);
        this.ctaUrl = MemoryUtils.extractString(buffy);
        this.adToken = MemoryUtils.extractString(buffy);
        this.videoIdentifier = MemoryUtils.extractString(buffy);
        this.muteUrls = MemoryUtils.extractStringArray(buffy);
        this.unmuteUrls = MemoryUtils.extractStringArray(buffy);
        this.closeUrls = MemoryUtils.extractStringArray(buffy);
        this.postRollClickUrls = MemoryUtils.extractStringArray(buffy);
        this.postRollViewUrls = MemoryUtils.extractStringArray(buffy);
        this.clickUrls = MemoryUtils.extractStringArray(buffy);
        this.templateUrl = MemoryUtils.extractString(buffy);
        this.templateId = MemoryUtils.extractString(buffy);
        this.templateType = MemoryUtils.extractString(buffy);
        this.moatExtraVast = MemoryUtils.extractString(buffy);
        this.adMarketId = MemoryUtils.extractString(buffy);
        this.bidToken = MemoryUtils.extractString(buffy);
        int checkpointCount = buffy.getInt();
        this.checkpoints = new ArrayList(checkpointCount);
        int x = 0;
        while (x < checkpointCount) {
            try {
                this.checkpoints.add(MemoryUtils.extractMemorable(buffy, Checkpoint.class));
                x++;
            } catch (NoSuchMethodException noMethod) {
                throw new RuntimeException(noMethod);
            } catch (IllegalAccessException ignored) {
                throw new RuntimeException(ignored);
            } catch (InstantiationException ignored2) {
                throw new RuntimeException(ignored2);
            } catch (InvocationTargetException ignored3) {
                throw new RuntimeException(ignored3);
            }
        }
        this.templateSettings = MemoryUtils.extractStringMap(buffy);
        this.state = buffy.getInt();
    }

    public boolean isCtaOverlayEnabled() {
        return this.ctaOverlayEnabled;
    }

    public boolean isCtaShowOnClick() {
        return this.ctaShowOnClick;
    }

    public int getCtaTimeEnabled() {
        return this.ctaTimeEnabled * 1000;
    }

    public int getCtaTimeShow() {
        return this.ctaTimeShow * 1000;
    }

    public int getCtaClickArea() {
        return this.ctaClickArea >= 1 ? this.ctaClickArea : 1;
    }

    public boolean isRequiresNonMarketInstall() {
        return this.requiresNonMarketInstall;
    }

    public Advertisement(@NonNull JsonObject json) throws IllegalArgumentException {
        if (JsonUtil.hasNonNull(json, "ad_markup")) {
            JsonObject adMarkup = json.getAsJsonObject("ad_markup");
            if (adMarkup.has("adType")) {
                String adTypeRaw = adMarkup.get("adType").getAsString();
                Object obj = -1;
                switch (adTypeRaw.hashCode()) {
                    case -1852456483:
                        if (adTypeRaw.equals("vungle_local")) {
                            obj = null;
                            break;
                        }
                        break;
                    case -1851445271:
                        if (adTypeRaw.equals("vungle_mraid")) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        this.adType = 0;
                        this.postrollBundleUrl = JsonUtil.hasNonNull(adMarkup, "postBundle") ? adMarkup.get("postBundle").getAsString() : "";
                        this.templateSettings = new HashMap();
                        this.templateUrl = "";
                        this.templateId = "";
                        this.templateType = "";
                        break;
                    case 1:
                        this.adType = 1;
                        this.postrollBundleUrl = "";
                        if (JsonUtil.hasNonNull(adMarkup, "templateSettings")) {
                            this.templateSettings = new HashMap();
                            JsonElement templateJson = adMarkup.getAsJsonObject("templateSettings");
                            if (JsonUtil.hasNonNull(templateJson, "normal_replacements")) {
                                for (Entry<String, JsonElement> element : templateJson.getAsJsonObject("normal_replacements").entrySet()) {
                                    this.templateSettings.put(element.getKey(), ((JsonElement) element.getValue()).getAsString());
                                }
                            }
                            if (JsonUtil.hasNonNull(templateJson, "cacheable_replacements")) {
                                for (Entry<String, JsonElement> element2 : templateJson.getAsJsonObject("cacheable_replacements").entrySet()) {
                                    if (JsonUtil.hasNonNull((JsonElement) element2.getValue(), "url")) {
                                        this.templateSettings.put(element2.getKey(), ((JsonElement) element2.getValue()).getAsJsonObject().get("url").getAsString());
                                    }
                                }
                            }
                            if (JsonUtil.hasNonNull(adMarkup, "templateId")) {
                                this.templateId = adMarkup.get("templateId").getAsString();
                                if (JsonUtil.hasNonNull(adMarkup, MessengerShareContentUtility.TEMPLATE_TYPE)) {
                                    this.templateType = adMarkup.get(MessengerShareContentUtility.TEMPLATE_TYPE).getAsString();
                                    if (JsonUtil.hasNonNull(adMarkup, "templateURL")) {
                                        this.templateUrl = adMarkup.get("templateURL").getAsString();
                                        break;
                                    }
                                    throw new IllegalArgumentException("Template URL missing!");
                                }
                                throw new IllegalArgumentException("Template Type missing!");
                            }
                            throw new IllegalArgumentException("Missing templateID!");
                        }
                        throw new IllegalArgumentException("Missing template adConfig!");
                    default:
                        throw new IllegalArgumentException("Unknown Ad Type " + adTypeRaw + "! Please add this ad type");
                }
                if (JsonUtil.hasNonNull(adMarkup, "id")) {
                    this.identifier = adMarkup.get("id").getAsString();
                    if (JsonUtil.hasNonNull(adMarkup, "campaign")) {
                        this.campaign = adMarkup.get("campaign").getAsString();
                        if (JsonUtil.hasNonNull(adMarkup, "app_id")) {
                            this.appID = adMarkup.get("app_id").getAsString();
                            if (!JsonUtil.hasNonNull(adMarkup, "expiry") || adMarkup.get("expiry").isJsonNull()) {
                                this.expireTime = System.currentTimeMillis() / 1000;
                            } else {
                                long expire = adMarkup.get("expiry").getAsLong();
                                if (expire > 0) {
                                    this.expireTime = expire;
                                } else {
                                    this.expireTime = System.currentTimeMillis() / 1000;
                                }
                            }
                            if (JsonUtil.hasNonNull(adMarkup, "tpat")) {
                                int x;
                                String muteKey;
                                String unmuteKey;
                                String closeKey;
                                String clickKey;
                                String viewKey;
                                JsonArray urls;
                                JsonElement tpat = adMarkup.getAsJsonObject("tpat");
                                this.checkpoints = new ArrayList(5);
                                switch (this.adType) {
                                    case 0:
                                        if (tpat.has("play_percentage")) {
                                            JsonArray checkpointData = tpat.getAsJsonArray("play_percentage");
                                            for (x = 0; x < checkpointData.size(); x++) {
                                                this.checkpoints.add(new Checkpoint(checkpointData.get(x).getAsJsonObject()));
                                            }
                                            Collections.sort(this.checkpoints);
                                            break;
                                        }
                                        break;
                                    case 1:
                                        for (x = 0; x < 5; x++) {
                                            String checkpoint = String.format(Locale.ENGLISH, "checkpoint.%d", new Object[]{Integer.valueOf(x * 25)});
                                            Checkpoint cpoint = null;
                                            if (tpat.has(checkpoint)) {
                                                cpoint = new Checkpoint(tpat.getAsJsonArray(checkpoint), (byte) percent);
                                            }
                                            this.checkpoints.add(x, cpoint);
                                        }
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Unknown Ad Type!");
                                }
                                if (tpat.has("clickUrl")) {
                                    JsonArray clickUrlsJon = tpat.getAsJsonArray("clickUrl");
                                    this.clickUrls = new String[clickUrlsJon.size()];
                                    x = 0;
                                    Iterator it = clickUrlsJon.iterator();
                                    while (it.hasNext()) {
                                        int x2 = x + 1;
                                        this.clickUrls[x] = ((JsonElement) it.next()).getAsString();
                                        x = x2;
                                    }
                                } else {
                                    this.clickUrls = new String[0];
                                }
                                if (tpat.has("moat")) {
                                    JsonObject moatObject = tpat.getAsJsonObject("moat");
                                    this.enableMoat = moatObject.get("is_enabled").getAsBoolean();
                                    this.moatExtraVast = moatObject.get("extra_vast").getAsString();
                                } else {
                                    this.enableMoat = false;
                                    this.moatExtraVast = "";
                                }
                                switch (this.adType) {
                                    case 0:
                                        muteKey = "mute";
                                        unmuteKey = "unmute";
                                        closeKey = "video_close";
                                        clickKey = "postroll_click";
                                        viewKey = "postroll_view";
                                        break;
                                    case 1:
                                        muteKey = "video.mute";
                                        unmuteKey = "video.unmute";
                                        closeKey = "video.close";
                                        clickKey = "postroll.click";
                                        viewKey = "postroll.view";
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Unknown AdType!");
                                }
                                if (JsonUtil.hasNonNull(tpat, muteKey)) {
                                    urls = tpat.getAsJsonArray(muteKey);
                                    this.muteUrls = new String[urls.size()];
                                    x = 0;
                                    while (x < urls.size()) {
                                        if (urls.get(x) == null || "null".equalsIgnoreCase(urls.get(x).toString())) {
                                            this.muteUrls[x] = "";
                                        } else {
                                            this.muteUrls[x] = urls.get(x).getAsString();
                                        }
                                        x++;
                                    }
                                } else {
                                    this.muteUrls = new String[0];
                                }
                                if (JsonUtil.hasNonNull(tpat, unmuteKey)) {
                                    urls = tpat.getAsJsonArray(unmuteKey);
                                    this.unmuteUrls = new String[urls.size()];
                                    x = 0;
                                    while (x < urls.size()) {
                                        if (urls.get(x) == null || "null".equalsIgnoreCase(urls.get(x).toString())) {
                                            this.unmuteUrls[x] = "";
                                        } else {
                                            this.unmuteUrls[x] = urls.get(x).getAsString();
                                        }
                                        x++;
                                    }
                                } else {
                                    this.unmuteUrls = new String[0];
                                }
                                if (JsonUtil.hasNonNull(tpat, closeKey)) {
                                    urls = tpat.getAsJsonArray(closeKey);
                                    this.closeUrls = new String[urls.size()];
                                    x = 0;
                                    while (x < urls.size()) {
                                        if (urls.get(x) == null || "null".equalsIgnoreCase(urls.get(x).toString())) {
                                            this.closeUrls[x] = "";
                                        } else {
                                            this.closeUrls[x] = urls.get(x).getAsString();
                                        }
                                        x++;
                                    }
                                } else {
                                    this.closeUrls = new String[0];
                                }
                                if (JsonUtil.hasNonNull(tpat, clickKey)) {
                                    urls = tpat.getAsJsonArray(clickKey);
                                    this.postRollClickUrls = new String[urls.size()];
                                    x = 0;
                                    while (x < urls.size()) {
                                        if (urls.get(x) == null || "null".equalsIgnoreCase(urls.get(x).toString())) {
                                            this.postRollClickUrls[x] = "";
                                        } else {
                                            this.postRollClickUrls[x] = urls.get(x).getAsString();
                                        }
                                        x++;
                                    }
                                } else {
                                    this.postRollClickUrls = new String[0];
                                }
                                if (JsonUtil.hasNonNull(tpat, viewKey)) {
                                    urls = tpat.getAsJsonArray(viewKey);
                                    this.postRollViewUrls = new String[urls.size()];
                                    x = 0;
                                    while (x < urls.size()) {
                                        if (urls.get(x) == null || "null".equalsIgnoreCase(urls.get(x).toString())) {
                                            this.postRollViewUrls[x] = "";
                                        } else {
                                            this.postRollViewUrls[x] = urls.get(x).getAsString();
                                        }
                                        x++;
                                    }
                                } else {
                                    this.postRollViewUrls = new String[0];
                                }
                            } else {
                                this.checkpoints = new ArrayList();
                                this.muteUrls = new String[0];
                                this.closeUrls = new String[0];
                                this.unmuteUrls = new String[0];
                                this.postRollViewUrls = new String[0];
                                this.postRollClickUrls = new String[0];
                                this.clickUrls = new String[0];
                                this.enableMoat = false;
                                this.moatExtraVast = "";
                            }
                            if (adMarkup.has("delay")) {
                                this.delay = adMarkup.get("delay").getAsInt();
                            } else {
                                this.delay = 0;
                            }
                            if (adMarkup.has("showClose")) {
                                this.showCloseDelay = adMarkup.get("showClose").getAsInt();
                            } else {
                                this.showCloseDelay = 0;
                            }
                            if (adMarkup.has("showCloseIncentivized")) {
                                this.showCloseIncentivized = adMarkup.get("showCloseIncentivized").getAsInt();
                            } else {
                                this.showCloseIncentivized = 0;
                            }
                            if (adMarkup.has("countdown")) {
                                this.countdown = adMarkup.get("countdown").getAsInt();
                            } else {
                                this.countdown = 0;
                            }
                            if (JsonUtil.hasNonNull(adMarkup, "url")) {
                                this.videoUrl = adMarkup.get("url").getAsString();
                                if (adMarkup.has(String.VIDEO_WIDTH)) {
                                    this.videoWidth = adMarkup.get(String.VIDEO_WIDTH).getAsInt();
                                    if (adMarkup.has(String.VIDEO_HEIGHT)) {
                                        this.videoHeight = adMarkup.get(String.VIDEO_HEIGHT).getAsInt();
                                        if (adMarkup.has("md5")) {
                                            this.md5 = adMarkup.get("md5").getAsString();
                                        } else {
                                            this.md5 = "";
                                        }
                                        if (adMarkup.has("cta_overlay")) {
                                            JsonObject cta = adMarkup.getAsJsonObject("cta_overlay");
                                            if (cta.has(String.ENABLED)) {
                                                this.ctaOverlayEnabled = cta.get(String.ENABLED).getAsBoolean();
                                            } else {
                                                this.ctaOverlayEnabled = false;
                                            }
                                            if (cta.has("show_onclick")) {
                                                this.ctaShowOnClick = cta.get("show_onclick").getAsBoolean();
                                            } else {
                                                this.ctaShowOnClick = false;
                                            }
                                            if (cta.has("time_enabled")) {
                                                this.ctaTimeEnabled = cta.get("time_enabled").getAsInt();
                                            } else {
                                                this.ctaTimeEnabled = -1;
                                            }
                                            if (cta.has("click_area")) {
                                                this.ctaClickArea = cta.get("click_area").getAsInt();
                                            } else {
                                                this.ctaClickArea = -1;
                                            }
                                            if (cta.has("time_show")) {
                                                this.ctaTimeShow = cta.get("time_show").getAsInt();
                                            } else {
                                                this.ctaTimeShow = -1;
                                            }
                                        } else {
                                            this.ctaOverlayEnabled = false;
                                            this.ctaClickArea = -1;
                                            this.ctaTimeEnabled = -1;
                                            this.ctaTimeShow = -1;
                                            this.ctaShowOnClick = false;
                                        }
                                        this.ctaDestinationUrl = JsonUtil.hasNonNull(adMarkup, "callToActionDest") ? adMarkup.get("callToActionDest").getAsString() : null;
                                        this.ctaUrl = JsonUtil.hasNonNull(adMarkup, "callToActionUrl") ? adMarkup.get("callToActionUrl").getAsString() : null;
                                        if (adMarkup.has("retryCount")) {
                                            this.retryCount = adMarkup.get("retryCount").getAsInt();
                                        } else {
                                            this.retryCount = 1;
                                        }
                                        if (adMarkup.has("ad_token")) {
                                            this.adToken = adMarkup.get("ad_token").getAsString();
                                            if (adMarkup.has("video_object_id")) {
                                                this.videoIdentifier = adMarkup.get("video_object_id").getAsString();
                                            } else {
                                                this.videoIdentifier = "";
                                            }
                                            if (adMarkup.has("requires_sideloading")) {
                                                this.requiresNonMarketInstall = adMarkup.get("requires_sideloading").getAsBoolean();
                                            } else {
                                                this.requiresNonMarketInstall = false;
                                            }
                                            if (adMarkup.has("ad_market_id")) {
                                                this.adMarketId = adMarkup.get("ad_market_id").getAsString();
                                            } else {
                                                this.adMarketId = "";
                                            }
                                            if (adMarkup.has("bid_token")) {
                                                this.bidToken = adMarkup.get("bid_token").getAsString();
                                            } else {
                                                this.bidToken = "";
                                            }
                                            this.adConfig = new AdConfig();
                                            return;
                                        }
                                        throw new IllegalArgumentException("AdToken missing!");
                                    }
                                    throw new IllegalArgumentException("Missing video height!");
                                }
                                throw new IllegalArgumentException("Missing video width!");
                            }
                            throw new IllegalArgumentException("Missing video URL!");
                        }
                        throw new IllegalArgumentException("Missing app Id, cannot process advertisement!");
                    }
                    throw new IllegalArgumentException("Missing campaign information, cannot process advertisement!");
                }
                throw new IllegalArgumentException("Missing identifier, cannot process advertisement!");
            }
            throw new IllegalArgumentException("Advertisement did not contain an adType!");
        }
        throw new IllegalArgumentException("JSON does not contain ad markup!");
    }

    @AdType
    public int getAdType() {
        return this.adType;
    }

    public List<Checkpoint> getCheckpoints() {
        return this.checkpoints;
    }

    public void configure(AdConfig settings) {
        if (settings == null) {
            this.adConfig = new AdConfig();
        } else {
            this.adConfig = settings;
        }
    }

    public AdConfig getAdConfig() {
        return this.adConfig;
    }

    @Orientation
    public int getOrientation() {
        if (this.videoWidth > this.videoHeight) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Advertisement)) {
            return false;
        }
        Advertisement wrap = (Advertisement) obj;
        if (getId() == null) {
            return false;
        }
        if (wrap.getId() == null) {
            return false;
        }
        if (!wrap.getId().equals(getId())) {
            return false;
        }
        if (wrap.adType != this.adType) {
            return false;
        }
        if (wrap.expireTime != this.expireTime) {
            return false;
        }
        if (wrap.delay != this.delay) {
            return false;
        }
        if (wrap.showCloseDelay != this.showCloseDelay) {
            return false;
        }
        if (wrap.showCloseIncentivized != this.showCloseIncentivized) {
            return false;
        }
        if (wrap.countdown != this.countdown) {
            return false;
        }
        if (wrap.videoWidth != this.videoWidth) {
            return false;
        }
        if (wrap.videoHeight != this.videoHeight) {
            return false;
        }
        if (wrap.ctaOverlayEnabled != this.ctaOverlayEnabled) {
            return false;
        }
        if (wrap.ctaShowOnClick != this.ctaShowOnClick) {
            return false;
        }
        if (wrap.ctaTimeEnabled != this.ctaTimeEnabled) {
            return false;
        }
        if (wrap.ctaTimeShow != this.ctaTimeShow) {
            return false;
        }
        if (wrap.ctaClickArea != this.ctaClickArea) {
            return false;
        }
        if (wrap.retryCount != this.retryCount) {
            return false;
        }
        if (wrap.enableMoat != this.enableMoat) {
            return false;
        }
        if (wrap.requiresNonMarketInstall != this.requiresNonMarketInstall) {
            return false;
        }
        if (!wrap.identifier.equals(this.identifier)) {
            return false;
        }
        if (!wrap.campaign.equals(this.campaign)) {
            return false;
        }
        if (!wrap.videoUrl.equals(this.videoUrl)) {
            return false;
        }
        if (!wrap.md5.equals(this.md5)) {
            return false;
        }
        if (!wrap.postrollBundleUrl.equals(this.postrollBundleUrl)) {
            return false;
        }
        if (!wrap.ctaDestinationUrl.equals(this.ctaDestinationUrl)) {
            return false;
        }
        if (!wrap.ctaUrl.equals(this.ctaUrl)) {
            return false;
        }
        if (!wrap.adToken.equals(this.adToken)) {
            return false;
        }
        if (!wrap.videoIdentifier.equals(this.videoIdentifier)) {
            return false;
        }
        if (!wrap.moatExtraVast.equals(this.moatExtraVast)) {
            return false;
        }
        if (wrap.state != this.state) {
            return false;
        }
        if (wrap.muteUrls.length != this.muteUrls.length) {
            return false;
        }
        int x;
        for (x = 0; x < this.muteUrls.length; x++) {
            if (!wrap.muteUrls[x].equals(this.muteUrls[x])) {
                return false;
            }
        }
        if (wrap.unmuteUrls.length != this.unmuteUrls.length) {
            return false;
        }
        for (x = 0; x < this.unmuteUrls.length; x++) {
            if (!wrap.unmuteUrls[x].equals(this.unmuteUrls[x])) {
                return false;
            }
        }
        if (wrap.closeUrls.length != this.closeUrls.length) {
            return false;
        }
        for (x = 0; x < this.closeUrls.length; x++) {
            if (!wrap.closeUrls[x].equals(this.closeUrls[x])) {
                return false;
            }
        }
        if (wrap.postRollClickUrls.length != this.postRollClickUrls.length) {
            return false;
        }
        for (x = 0; x < this.postRollClickUrls.length; x++) {
            if (!wrap.postRollClickUrls[x].equals(this.postRollClickUrls[x])) {
                return false;
            }
        }
        if (wrap.postRollViewUrls.length != this.postRollViewUrls.length) {
            return false;
        }
        for (x = 0; x < this.postRollViewUrls.length; x++) {
            if (!wrap.postRollViewUrls[x].equals(this.postRollViewUrls[x])) {
                return false;
            }
        }
        if (wrap.checkpoints.size() != this.checkpoints.size()) {
            return false;
        }
        for (x = 0; x < this.checkpoints.size(); x++) {
            if (!((Checkpoint) wrap.checkpoints.get(x)).equals(this.checkpoints.get(x))) {
                return false;
            }
        }
        if (!wrap.adMarketId.equals(this.adMarketId)) {
            return false;
        }
        if (wrap.bidToken.equals(this.bidToken)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public byte[] toByteArray() {
        int i = 1;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int i2;
            out.write(MemoryUtils.toBytes(this.adType));
            out.write(MemoryUtils.toBytes(this.expireTime));
            out.write(MemoryUtils.toBytes(this.delay));
            out.write(MemoryUtils.toBytes(this.showCloseDelay));
            out.write(MemoryUtils.toBytes(this.showCloseIncentivized));
            out.write(MemoryUtils.toBytes(this.countdown));
            out.write(MemoryUtils.toBytes(this.videoWidth));
            out.write(MemoryUtils.toBytes(this.videoHeight));
            out.write(this.ctaOverlayEnabled ? 1 : 0);
            if (this.ctaShowOnClick) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            out.write(i2);
            out.write(MemoryUtils.toBytes(this.ctaTimeEnabled));
            out.write(MemoryUtils.toBytes(this.ctaTimeShow));
            out.write(MemoryUtils.toBytes(this.ctaClickArea));
            out.write(MemoryUtils.toBytes(this.retryCount));
            if (this.enableMoat) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            out.write(i2);
            if (!this.requiresNonMarketInstall) {
                i = 0;
            }
            out.write(i);
            out.write(MemoryUtils.toBytes(this.adConfig.getSettings()));
            out.write(MemoryUtils.toBytes(this.adConfig.getFlexViewCloseTime()));
            out.write(MemoryUtils.toBytes(this.adConfig.getOrdinal()));
            MemoryUtils.writeString(this.identifier, out);
            MemoryUtils.writeString(this.appID, out);
            MemoryUtils.writeString(this.campaign, out);
            MemoryUtils.writeString(this.videoUrl, out);
            MemoryUtils.writeString(this.md5, out);
            MemoryUtils.writeString(this.postrollBundleUrl, out);
            MemoryUtils.writeString(this.ctaDestinationUrl, out);
            MemoryUtils.writeString(this.ctaUrl, out);
            MemoryUtils.writeString(this.adToken, out);
            MemoryUtils.writeString(this.videoIdentifier, out);
            MemoryUtils.writeStringArray(this.muteUrls, out);
            MemoryUtils.writeStringArray(this.unmuteUrls, out);
            MemoryUtils.writeStringArray(this.closeUrls, out);
            MemoryUtils.writeStringArray(this.postRollClickUrls, out);
            MemoryUtils.writeStringArray(this.postRollViewUrls, out);
            MemoryUtils.writeStringArray(this.clickUrls, out);
            MemoryUtils.writeString(this.templateUrl, out);
            MemoryUtils.writeString(this.templateId, out);
            MemoryUtils.writeString(this.templateType, out);
            MemoryUtils.writeString(this.moatExtraVast, out);
            MemoryUtils.writeString(this.adMarketId, out);
            MemoryUtils.writeString(this.bidToken, out);
            out.write(MemoryUtils.toBytes(this.checkpoints.size()));
            Iterator it = this.checkpoints.iterator();
            while (it.hasNext()) {
                MemoryUtils.writeMemorable((Checkpoint) it.next(), out);
            }
            MemoryUtils.writeStringMap(this.templateSettings, out);
            out.write(MemoryUtils.toBytes(this.state));
            return out.toByteArray();
        } catch (IOException e) {
            Log.e("Advertisement.java", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] getTpatUrls(@android.support.annotation.NonNull java.lang.String r10) {
        /*
        r9 = this;
        r7 = 3;
        r6 = 2;
        r4 = -1;
        r5 = 1;
        r3 = 0;
        r8 = r9.adType;
        switch(r8) {
            case 0: goto L_0x0012;
            case 1: goto L_0x0083;
            default: goto L_0x000a;
        };
    L_0x000a:
        r3 = new java.lang.IllegalStateException;
        r4 = "Unknown Advertisement Type!";
        r3.<init>(r4);
        throw r3;
    L_0x0012:
        r8 = r10.hashCode();
        switch(r8) {
            case -1964722632: goto L_0x0067;
            case -840405966: goto L_0x0053;
            case 3363353: goto L_0x0049;
            case 109635558: goto L_0x003f;
            case 1370606900: goto L_0x005d;
            case 1666667655: goto L_0x0035;
            default: goto L_0x0019;
        };
    L_0x0019:
        switch(r4) {
            case 0: goto L_0x0071;
            case 1: goto L_0x0074;
            case 2: goto L_0x0077;
            case 3: goto L_0x007a;
            case 4: goto L_0x007d;
            case 5: goto L_0x0080;
            default: goto L_0x001c;
        };
    L_0x001c:
        r3 = new java.lang.IllegalArgumentException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Unknown TPAT Event ";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r4 = r4.toString();
        r3.<init>(r4);
        throw r3;
    L_0x0035:
        r5 = "postroll_view";
        r5 = r10.equals(r5);
        if (r5 == 0) goto L_0x0019;
    L_0x003d:
        r4 = r3;
        goto L_0x0019;
    L_0x003f:
        r3 = "postroll_click";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x0019;
    L_0x0047:
        r4 = r5;
        goto L_0x0019;
    L_0x0049:
        r3 = "mute";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x0019;
    L_0x0051:
        r4 = r6;
        goto L_0x0019;
    L_0x0053:
        r3 = "unmute";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x0019;
    L_0x005b:
        r4 = r7;
        goto L_0x0019;
    L_0x005d:
        r3 = "video_close";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x0019;
    L_0x0065:
        r4 = 4;
        goto L_0x0019;
    L_0x0067:
        r3 = "click_url";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x0019;
    L_0x006f:
        r4 = 5;
        goto L_0x0019;
    L_0x0071:
        r2 = r9.postRollViewUrls;
    L_0x0073:
        return r2;
    L_0x0074:
        r2 = r9.postRollClickUrls;
        goto L_0x0073;
    L_0x0077:
        r2 = r9.muteUrls;
        goto L_0x0073;
    L_0x007a:
        r2 = r9.unmuteUrls;
        goto L_0x0073;
    L_0x007d:
        r2 = r9.closeUrls;
        goto L_0x0073;
    L_0x0080:
        r2 = r9.clickUrls;
        goto L_0x0073;
    L_0x0083:
        r8 = "checkpoint";
        r8 = r10.startsWith(r8);
        if (r8 == 0) goto L_0x00aa;
    L_0x008b:
        r2 = new java.lang.String[r3];
        r3 = "\\.";
        r3 = r10.split(r3);
        r3 = r3[r5];
        r1 = java.lang.Integer.parseInt(r3);
        r3 = r9.checkpoints;
        r4 = r1 / 25;
        r0 = r3.get(r4);
        r0 = (com.vungle.warren.model.Advertisement.Checkpoint) r0;
        if (r0 == 0) goto L_0x0073;
    L_0x00a5:
        r2 = r0.getUrls();
        goto L_0x0073;
    L_0x00aa:
        r8 = r10.hashCode();
        switch(r8) {
            case -1663300692: goto L_0x00f5;
            case -1293192841: goto L_0x00e1;
            case -481751803: goto L_0x00ff;
            case -32221499: goto L_0x00ce;
            case 906443463: goto L_0x00eb;
            case 1621415126: goto L_0x00d7;
            default: goto L_0x00b1;
        };
    L_0x00b1:
        r3 = r4;
    L_0x00b2:
        switch(r3) {
            case 0: goto L_0x0109;
            case 1: goto L_0x010d;
            case 2: goto L_0x0111;
            case 3: goto L_0x0115;
            case 4: goto L_0x0119;
            case 5: goto L_0x011d;
            default: goto L_0x00b5;
        };
    L_0x00b5:
        r3 = new java.lang.IllegalArgumentException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Unknown TPAT Event ";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r4 = r4.toString();
        r3.<init>(r4);
        throw r3;
    L_0x00ce:
        r5 = "video.close";
        r5 = r10.equals(r5);
        if (r5 == 0) goto L_0x00b1;
    L_0x00d6:
        goto L_0x00b2;
    L_0x00d7:
        r3 = "postroll.view";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x00b1;
    L_0x00df:
        r3 = r5;
        goto L_0x00b2;
    L_0x00e1:
        r3 = "postroll.click";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x00b1;
    L_0x00e9:
        r3 = r6;
        goto L_0x00b2;
    L_0x00eb:
        r3 = "clickUrl";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x00b1;
    L_0x00f3:
        r3 = r7;
        goto L_0x00b2;
    L_0x00f5:
        r3 = "video.mute";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x00b1;
    L_0x00fd:
        r3 = 4;
        goto L_0x00b2;
    L_0x00ff:
        r3 = "video.unmute";
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x00b1;
    L_0x0107:
        r3 = 5;
        goto L_0x00b2;
    L_0x0109:
        r2 = r9.closeUrls;
        goto L_0x0073;
    L_0x010d:
        r2 = r9.postRollViewUrls;
        goto L_0x0073;
    L_0x0111:
        r2 = r9.postRollClickUrls;
        goto L_0x0073;
    L_0x0115:
        r2 = r9.clickUrls;
        goto L_0x0073;
    L_0x0119:
        r2 = r9.muteUrls;
        goto L_0x0073;
    L_0x011d:
        r2 = r9.unmuteUrls;
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.model.Advertisement.getTpatUrls(java.lang.String):java.lang.String[]");
    }

    @NonNull
    public String getId() {
        if (this.identifier == null) {
            return "";
        }
        return this.identifier;
    }

    public String getAdToken() {
        return this.adToken;
    }

    public String getAppID() {
        return this.appID;
    }

    String getUrl() {
        return this.videoUrl;
    }

    public String getCampaign() {
        return this.campaign;
    }

    String getTemplateId() {
        return this.templateId;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    public int getShowCloseDelay(boolean incentivized) {
        if (incentivized) {
            return this.showCloseIncentivized * 1000;
        }
        return this.showCloseDelay * 1000;
    }

    public boolean getMoatEnabled() {
        return this.enableMoat;
    }

    public String getMoatVastExtra() {
        return this.moatExtraVast;
    }

    public long getExpireTime() {
        return this.expireTime * 1000;
    }

    public JsonObject getMRAIDArgs() {
        if (this.templateSettings == null) {
            throw new IllegalArgumentException("Advertisment does not have MRAID Arguments!");
        }
        JsonObject ret = new JsonObject();
        for (Entry<String, String> entry : this.templateSettings.entrySet()) {
            ret.addProperty((String) entry.getKey(), (String) entry.getValue());
        }
        return ret;
    }

    @Nullable
    public String getCTAURL(boolean tpat) {
        switch (this.adType) {
            case 0:
                return tpat ? this.ctaUrl : this.ctaDestinationUrl;
            case 1:
                return this.ctaUrl;
            default:
                throw new IllegalArgumentException("Unknown AdType " + this.adType);
        }
    }

    public boolean hasPostroll() {
        return !TextUtils.isEmpty(this.postrollBundleUrl);
    }

    public Map<String, String> getDownloadableUrls() {
        HashMap<String, String> ret = new HashMap();
        switch (this.adType) {
            case 0:
                ret.put("video", this.videoUrl);
                if (!TextUtils.isEmpty(this.postrollBundleUrl)) {
                    ret.put(KEY_POSTROLL, this.postrollBundleUrl);
                    break;
                }
                break;
            case 1:
                ret.put("template", this.templateUrl);
                if (this.templateSettings.containsKey("MAIN_VIDEO")) {
                    ret.put("video", this.templateSettings.get("MAIN_VIDEO"));
                }
                if (this.templateSettings.containsKey("APP_ICON")) {
                    ret.put(KEY_APP_ICON, this.templateSettings.get("APP_ICON"));
                }
                if (this.templateSettings.containsKey("POWERED_BY_VUNGLE")) {
                    ret.put(KEY_POWERED_BY_VUNGLE, this.templateSettings.get("POWERED_BY_VUNGLE"));
                    break;
                }
                break;
            default:
                throw new IllegalStateException("Advertisement created without adType!");
        }
        return ret;
    }

    public void setMraidAssetDir(File dir) {
        File video = new File(dir, "video");
        if (video.exists()) {
            this.templateSettings.put("MAIN_VIDEO", "file://" + video.getPath());
        }
        File icon = new File(dir, KEY_APP_ICON);
        if (icon.exists()) {
            this.templateSettings.put("APP_ICON", "file://" + icon.getPath());
        }
        File vungle = new File(dir, KEY_POWERED_BY_VUNGLE);
        if (vungle.exists()) {
            this.templateSettings.put("POWERED_BY_VUNGLE", "file://" + vungle.getPath());
        }
    }

    public void setState(@State int state) {
        this.state = state;
    }

    @State
    public int getState() {
        return this.state;
    }

    public String getAdMarketId() {
        return this.adMarketId;
    }

    public String getBidToken() {
        return this.bidToken;
    }

    public Advertisement copy() {
        try {
            return (Advertisement) clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    public String toString() {
        return "Advertisement{adType=" + this.adType + ", identifier='" + this.identifier + '\'' + ", appID='" + this.appID + '\'' + ", expireTime=" + this.expireTime + ", checkpoints=" + this.checkpoints + ", muteUrls=" + Arrays.toString(this.muteUrls) + ", unmuteUrls=" + Arrays.toString(this.unmuteUrls) + ", closeUrls=" + Arrays.toString(this.closeUrls) + ", postRollClickUrls=" + Arrays.toString(this.postRollClickUrls) + ", postRollViewUrls=" + Arrays.toString(this.postRollViewUrls) + ", clickUrls=" + Arrays.toString(this.clickUrls) + ", delay=" + this.delay + ", campaign='" + this.campaign + '\'' + ", showCloseDelay=" + this.showCloseDelay + ", showCloseIncentivized=" + this.showCloseIncentivized + ", countdown=" + this.countdown + ", videoUrl='" + this.videoUrl + '\'' + ", videoWidth=" + this.videoWidth + ", videoHeight=" + this.videoHeight + ", md5='" + this.md5 + '\'' + ", postrollBundleUrl='" + this.postrollBundleUrl + '\'' + ", ctaOverlayEnabled=" + this.ctaOverlayEnabled + ", ctaShowOnClick=" + this.ctaShowOnClick + ", ctaTimeEnabled=" + this.ctaTimeEnabled + ", ctaTimeShow=" + this.ctaTimeShow + ", ctaClickArea=" + this.ctaClickArea + ", ctaDestinationUrl='" + this.ctaDestinationUrl + '\'' + ", ctaUrl='" + this.ctaUrl + '\'' + ", adConfig=" + this.adConfig + ", retryCount=" + this.retryCount + ", adToken='" + this.adToken + '\'' + ", videoIdentifier='" + this.videoIdentifier + '\'' + ", templateUrl='" + this.templateUrl + '\'' + ", templateSettings=" + this.templateSettings + ", templateId='" + this.templateId + '\'' + ", templateType='" + this.templateType + '\'' + ", enableMoat=" + this.enableMoat + ", moatExtraVast='" + this.moatExtraVast + '\'' + ", requiresNonMarketInstall=" + this.requiresNonMarketInstall + ", adMarketId='" + this.adMarketId + '\'' + ", bidToken='" + this.bidToken + '\'' + ", state=" + this.state + '}';
    }
}
