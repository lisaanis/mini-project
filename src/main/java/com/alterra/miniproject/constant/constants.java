package com.alterra.miniproject.constant;

public class constants {
    
    private constants() {}

    // public static final String DEFAULT_SYSTEM = "SYSTEM";

    // public static final String SUCCESS = "SUCCESS";
    // public static final String DATA_NOT_FOUND  = "DATA_NOT_FOUND";
    // public static final String HAPPENED_ERROR  = "HAPPENED_ERROR";

    public enum ResponseCode {

        SUCCESS("SUCCESS"),
        DATA_NOT_FOUND("DATA_NOT_FOUND"),
        UNKNOWN_ERROR("HAPPENED_ERROR");

        private final String message;

        private ResponseCode(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

    }
}
