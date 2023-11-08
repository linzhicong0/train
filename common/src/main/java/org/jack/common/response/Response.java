package org.jack.common.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
public class Response<T> {
    /**
     * business success or not
     */
    private boolean success;
    /**
     * return message
     */
    private String message;
    /**
     * the return data
     */
    private T data;



    public static <T> Response<T> success() {
        return success(null);
    }
    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .data(data)
                .success(true)
                .message("success")
                .build();
    }

    public static <T> Response<T> fail() {
        return fail(null);
    }

    public static <T> Response<T> fail(String message) {
        return Response.<T>builder()
                .success(false)
                .message("fail")
                .data(null)
                .build();
    }
}
