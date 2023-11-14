package org.jack.common.controller;

import org.jack.common.exception.BusinessException;
import org.jack.common.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e) {
        LOG.error("exceptionHandler", e);
        return Response.fail("system error");
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseEntity<Response> businessExceptionHandler(BusinessException e) {
        LOG.error("business error: {}", e.getException().getDesc());
        return new ResponseEntity<>(Response.fail(e.getException().getDesc()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseEntity<Response> bindExceptionHandler(BindException e) {
        var br = e.getBindingResult();

        var errors = br.getAllErrors();
        StringBuilder sb = new StringBuilder();

        for (var error : errors) {
            sb.append(error.getDefaultMessage()).append(";");
        }

        LOG.error("validation error: {}", sb);
        return new ResponseEntity<>(Response.fail(sb.toString()), HttpStatus.BAD_REQUEST);

    }
}
