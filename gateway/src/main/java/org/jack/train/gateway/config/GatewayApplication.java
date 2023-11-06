package org.jack.train.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
//@ComponentScan("org.jack")
public class GatewayApplication {
    private static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        var ioc = SpringApplication.run(GatewayApplication.class, args);
        Environment env = ioc.getEnvironment();
        LOG.info("{} started. Address: http://127.0.0.1:{}", env.getProperty("spring.application.name"), env.getProperty("server.port"));
    }
}
