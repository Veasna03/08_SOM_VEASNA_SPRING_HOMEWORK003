package org.example.homework003.exception;

import java.util.Map;

public class NullPointerExceptino extends RuntimeException {
    private final Map<String, String> errorDetails;

    public NullPointerExceptino(Map<String, String> errorDetails) {
        super(errorDetails.toString());
        this.errorDetails = errorDetails;
    }
    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }
}
