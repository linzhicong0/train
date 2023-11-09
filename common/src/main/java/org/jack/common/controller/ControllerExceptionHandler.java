package org.jack.common.controller;

import org.jack.common.exception.BusinessException;
import org.jack.common.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e)  {
        LOG.error("exceptionHandler", e);
        return Response.fail("system error");
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Response businessExceptionHandler(BusinessException e) {
        LOG.error("business error: {}", e.getException().getDesc());
        return Response.fail(e.getException().getDesc());

    }
}
