package com.unity3d.services.monetization.placementcontent.core;

import java.util.HashMap;
import java.util.Map;

public class CustomEvent {
    private String category;
    private Map<String, Object> data;
    private String type;

    public static final class Builder {
        private String category;
        private Map<String, Object> data;
        private String type;

        private Builder() {
        }

        public Builder withCategory(String val) {
            this.category = val;
            return this;
        }

        public Builder withType(String val) {
            this.type = val;
            return this;
        }

        public Builder withData(Map<String, Object> val) {
            this.data = val;
            return this;
        }

        public Builder putAllData(Map<String, Object> vals) {
            if (this.data == null) {
                this.data = new HashMap(vals);
            } else {
                this.data.putAll(vals);
            }
            return this;
        }

        public Builder putData(String key, Object value) {
            if (this.data == null) {
                this.data = new HashMap();
            }
            this.data.put(key, value);
            return this;
        }

        public CustomEvent build() {
            return new CustomEvent();
        }
    }

    public CustomEvent(String type) {
        this.type = type;
    }

    public CustomEvent(String type, Map<String, Object> data) {
        this.type = type;
        this.data = data;
    }

    public CustomEvent(String category, String type, Map<String, Object> data) {
        this.category = category;
        this.type = type;
        this.data = data;
    }

    private CustomEvent(Builder builder) {
        setCategory(builder.category);
        setType(builder.type);
        setData(builder.data);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
