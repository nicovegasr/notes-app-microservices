package com.nicovegasr.users_service.queries;

public class GetUserQuery {
    private String username;

    private GetUserQuery(Builder builder) {
        this.username = builder.username;
    }

    public String getUsername() {
        return username;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;

        public Builder username(String username) {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("username cannot be null");
            }
            this.username = username;
            return this;
        }

        public GetUserQuery build() {
            return new GetUserQuery(this);
        }
    }
}