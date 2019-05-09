// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.io.Serializable;

public class User implements IJsonSerializable, Serializable
{
    private String accountAcquisitionDate;
    private String accountId;
    private String anonUserAcquisitionDate;
    private String authUserAcquisitionDate;
    private String authUserId;
    private String id;
    private String storeRegion;
    private String userAgent;
    
    public User() {
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
    }
    
    public void addToHashMap(final Map<String, String> map) {
        if (this.accountAcquisitionDate != null) {
            map.put("ai.user.accountAcquisitionDate", this.accountAcquisitionDate);
        }
        if (this.accountId != null) {
            map.put("ai.user.accountId", this.accountId);
        }
        if (this.userAgent != null) {
            map.put("ai.user.userAgent", this.userAgent);
        }
        if (this.id != null) {
            map.put("ai.user.id", this.id);
        }
        if (this.storeRegion != null) {
            map.put("ai.user.storeRegion", this.storeRegion);
        }
        if (this.authUserId != null) {
            map.put("ai.user.authUserId", this.authUserId);
        }
        if (this.anonUserAcquisitionDate != null) {
            map.put("ai.user.anonUserAcquisitionDate", this.anonUserAcquisitionDate);
        }
        if (this.authUserAcquisitionDate != null) {
            map.put("ai.user.authUserAcquisitionDate", this.authUserAcquisitionDate);
        }
    }
    
    public String getAccountAcquisitionDate() {
        return this.accountAcquisitionDate;
    }
    
    public String getAccountId() {
        return this.accountId;
    }
    
    public String getAnonUserAcquisitionDate() {
        return this.anonUserAcquisitionDate;
    }
    
    public String getAuthUserAcquisitionDate() {
        return this.authUserAcquisitionDate;
    }
    
    public String getAuthUserId() {
        return this.authUserId;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getStoreRegion() {
        return this.storeRegion;
    }
    
    public String getUserAgent() {
        return this.userAgent;
    }
    
    @Override
    public void serialize(final Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        this.serializeContent(writer);
        writer.write(125);
    }
    
    protected String serializeContent(final Writer writer) throws IOException {
        String s = "";
        if (this.accountAcquisitionDate != null) {
            writer.write("" + "\"ai.user.accountAcquisitionDate\":");
            writer.write(JsonHelper.convert(this.accountAcquisitionDate));
            s = ",";
        }
        String s2 = s;
        if (this.accountId != null) {
            writer.write(s + "\"ai.user.accountId\":");
            writer.write(JsonHelper.convert(this.accountId));
            s2 = ",";
        }
        String s3 = s2;
        if (this.userAgent != null) {
            writer.write(s2 + "\"ai.user.userAgent\":");
            writer.write(JsonHelper.convert(this.userAgent));
            s3 = ",";
        }
        String s4 = s3;
        if (this.id != null) {
            writer.write(s3 + "\"ai.user.id\":");
            writer.write(JsonHelper.convert(this.id));
            s4 = ",";
        }
        String s5 = s4;
        if (this.storeRegion != null) {
            writer.write(s4 + "\"ai.user.storeRegion\":");
            writer.write(JsonHelper.convert(this.storeRegion));
            s5 = ",";
        }
        String s6 = s5;
        if (this.authUserId != null) {
            writer.write(s5 + "\"ai.user.authUserId\":");
            writer.write(JsonHelper.convert(this.authUserId));
            s6 = ",";
        }
        String s7 = s6;
        if (this.anonUserAcquisitionDate != null) {
            writer.write(s6 + "\"ai.user.anonUserAcquisitionDate\":");
            writer.write(JsonHelper.convert(this.anonUserAcquisitionDate));
            s7 = ",";
        }
        String s8 = s7;
        if (this.authUserAcquisitionDate != null) {
            writer.write(s7 + "\"ai.user.authUserAcquisitionDate\":");
            writer.write(JsonHelper.convert(this.authUserAcquisitionDate));
            s8 = ",";
        }
        return s8;
    }
    
    public void setAccountAcquisitionDate(final String accountAcquisitionDate) {
        this.accountAcquisitionDate = accountAcquisitionDate;
    }
    
    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }
    
    public void setAnonUserAcquisitionDate(final String anonUserAcquisitionDate) {
        this.anonUserAcquisitionDate = anonUserAcquisitionDate;
    }
    
    public void setAuthUserAcquisitionDate(final String authUserAcquisitionDate) {
        this.authUserAcquisitionDate = authUserAcquisitionDate;
    }
    
    public void setAuthUserId(final String authUserId) {
        this.authUserId = authUserId;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setStoreRegion(final String storeRegion) {
        this.storeRegion = storeRegion;
    }
    
    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }
}
