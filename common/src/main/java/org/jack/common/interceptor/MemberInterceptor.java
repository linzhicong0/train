package org.jack.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jack.common.context.LoginMemberContext;
import org.jack.common.response.MemberLoginResp;
import org.jack.common.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class MemberInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(MemberInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOG.info("memberInterceptor start.");
        String token = request.getHeader("Authorization");

        if(StrUtil.isNotBlank(token)) {
            LOG.info("member token:{}", token);
            JSONObject loginMember = JWTUtil.getJsonObject(token);
            LOG.info("member:{}", loginMember);
            MemberLoginResp member = JSONUtil.toBean(loginMember, MemberLoginResp.class);
            LoginMemberContext.setThreadLocalMember(member);

        }

        LOG.info("memberInterceptor end.");
        return true;
    }
}
