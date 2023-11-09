package org.jack.common.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    private final BusinessExceptionEnum exception;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
