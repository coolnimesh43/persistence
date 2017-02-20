package com.coolnimesh43.persistence.constant;

public interface PersistenceConstant {

    public static final String[] OPEN_RS_END_POINT = { "/api/project/user" };

    public interface Status {
        public static final String ACTIVE = "ACTIVE";
        public static final String DELETED = "DELETED";
    }

    public interface Auth {
        public static final String X_AUTH_PROJECT_ID = "x-auth-project-id";
        public static final String X_AUTH_TOKEN = "x-auth-token";
    }

    public interface Response {
        public interface Value {
            public static final String SUCCESS = "success";
            public static final String ERROR = "error";
        }
    }
}
