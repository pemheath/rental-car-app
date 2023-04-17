package com.nashss.se.rentalcarservice.activity.requests;

public class SearchCarsRequest {

    private final String criteria;

    private SearchCarsRequest(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "SearchCarsRequest{" +
                "criteria='" + criteria + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String criteria;

        public Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchCarsRequest build() {
            return new SearchCarsRequest(criteria);
        }
    }
}
