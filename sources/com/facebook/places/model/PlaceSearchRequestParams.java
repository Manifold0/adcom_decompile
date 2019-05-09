package com.facebook.places.model;

import java.util.HashSet;
import java.util.Set;

public final class PlaceSearchRequestParams {
    private final Set<String> categories;
    private final int distance;
    private final Set<String> fields;
    private final int limit;
    private final String searchText;

    public static class Builder {
        private final Set<String> categories = new HashSet();
        private int distance;
        private final Set<String> fields = new HashSet();
        private int limit;
        private String searchText;

        public Builder setDistance(int distance) {
            this.distance = distance;
            return this;
        }

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setSearchText(String searchText) {
            this.searchText = searchText;
            return this;
        }

        public Builder addCategory(String category) {
            this.categories.add(category);
            return this;
        }

        public Builder addField(String field) {
            this.fields.add(field);
            return this;
        }

        public PlaceSearchRequestParams build() {
            return new PlaceSearchRequestParams();
        }
    }

    private PlaceSearchRequestParams(Builder b) {
        this.categories = new HashSet();
        this.fields = new HashSet();
        this.distance = b.distance;
        this.limit = b.limit;
        this.searchText = b.searchText;
        this.categories.addAll(b.categories);
        this.fields.addAll(b.fields);
    }

    public int getDistance() {
        return this.distance;
    }

    public int getLimit() {
        return this.limit;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public Set<String> getFields() {
        return this.fields;
    }
}
