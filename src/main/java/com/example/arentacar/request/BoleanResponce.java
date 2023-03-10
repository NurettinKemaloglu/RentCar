package com.example.arentacar.request;

public class BoleanResponce {

    public record BooleanResponse(
            boolean result
    ) {
        public static BooleanResponse success() {
            return new BooleanResponse(true);
        }

        public static BooleanResponse fail() {
            return new BooleanResponse(false);
        }
    }

}
