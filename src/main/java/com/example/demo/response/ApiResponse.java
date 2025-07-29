package com.example.demo.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Standard API response wrapper")
public class ApiResponse<T> {

    @Schema(example = "SUCCESS")
    private String code;

    @Schema(example = "Operation successful")
    private String message;

    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", "Operation successful", data);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}