package com.alterra.miniproject.constant;

public class constants {
    
    private constants() {}

    public static final String DEFAULT_SYSTEM = "SYSTEM";

    public enum ResponseCode {

        SUCCESS("Success!"),
        DATA_NOT_FOUND("Data not found!"),
        UNKNOWN_ERROR("Happened error!");

        private final String message;

        private ResponseCode(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

    }
}
