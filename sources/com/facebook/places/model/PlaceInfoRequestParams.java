package com.facebook.places.model;

import java.util.HashSet;
import java.util.Set;

public final class PlaceInfoRequestParams {
    private final Set<String> fields;
    private final String placeId;

    public static class Builder {
        private final Set<String> fields = new HashSet();
        private String placeId;

        public Builder setPlaceId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Builder addField(String field) {
            this.fields.add(field);
            return this;
        }

        public Builder addFields(String[] fields) {
            for (String field : fields) {
                this.fields.add(field);
            }
            return this;
        }

        public PlaceInfoRequestParams build() {
            return new PlaceInfoRequestParams();
        }
    }

    private PlaceInfoRequestParams(Builder b) {
        this.fields = new HashSet();
        this.placeId = b.placeId;
        this.fields.addAll(b.fields);
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public Set<String> getFields() {
        return this.fields;
    }
}
