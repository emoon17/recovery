package com.example.wehago.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private Integer success;
    private T data;
    private String message;

    //성공 응답
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, data, null);
    }

    // 실패 응답
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(400, null, message);
    }


}
