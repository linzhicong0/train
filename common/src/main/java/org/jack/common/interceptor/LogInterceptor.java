package org.jack.common.interceptor;

import ch.qos.logback.core.testUtil.RandomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        MDC.put("LOG_ID",  Long.toString( System.currentTimeMillis() + RandomUtil.getPositiveInt()));
        return true;
    }
}
