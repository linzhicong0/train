package org.jack.train.gateway.config;


import org.jack.train.gateway.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoginMemberFilter implements Ordered, GlobalFilter {

   private static final Logger LOG = LoggerFactory.getLogger(LoginMemberFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        if (path.contains("/admin")
                || path.contains("/member/login")
                || path.contains("/member/sendCode")
        ) {
            LOG.info("no token validation: {}", path);
            return chain.filter(exchange);
        }
        else {
            LOG.info("token validation: {}", path);
        }

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        LOG.info("start validating the token: {}", token);

        if (token == null || token.isEmpty()){
            LOG.info("no token in request");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }


        boolean validate = JWTUtil.validate(token);

        if (validate) {
            LOG.info("token validate");
            return chain.filter(exchange);
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
