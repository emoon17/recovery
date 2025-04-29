package com.example.wehago.common;

public class BaseController {

    protected <T> ApiResponse<T> success(T data) {
        return ApiResponse.success(data);
    }

    protected ApiResponse<Void> success() {
        return ApiResponse.success(null);
    }

    protected ApiResponse<Void> fail(String message) {
        return ApiResponse.fail(message);
    }
}
