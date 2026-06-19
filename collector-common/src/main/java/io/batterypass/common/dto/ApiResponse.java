package io.batterypass.common.dto;

import java.time.OffsetDateTime;

public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private OffsetDateTime timestamp;

    public ApiResponse() {}

    public ApiResponse(int code, String message, T data, OffsetDateTime timestamp) {
        this.code = code; this.message = message; this.data = data; this.timestamp = timestamp;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data, OffsetDateTime.now());
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null, OffsetDateTime.now());
    }

    public static <T> ApiResponse<T> error(int code, String message, T data) {
        return new ApiResponse<>(code, message, data, OffsetDateTime.now());
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public OffsetDateTime getTimestamp() { return timestamp; }

    public void setCode(int code) { this.code = code; }
    public void setMessage(String message) { this.message = message; }
    public void setData(T data) { this.data = data; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
}
