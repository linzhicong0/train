package org.jack.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    public static final String KEY = "12345678";
    private static final Logger LOG = LoggerFactory.getLogger(JWTUtil.class);


    public static String createToken(Long id, String mobile) {

        GlobalBouncyCastleProvider.setUseBouncyCastle(false);

        DateTime now = DateTime.now();
        DateTime expiredTime = now.offsetNew(DateField.HOUR, 24);

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("mobile", mobile);
        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.EXPIRES_AT, expiredTime);
        payload.put(JWTPayload.NOT_BEFORE, now);

        String token = cn.hutool.jwt.JWTUtil.createToken(payload, KEY.getBytes());

        LOG.info("JWT token: {}", token);

        return token;

    }


    public static boolean validate(String token) {

        GlobalBouncyCastleProvider.setUseBouncyCastle(false);

        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(KEY.getBytes());

        boolean validate = jwt.validate(0);

        LOG.info("JWT token validate result: {}", validate);

        return validate;

    }

    public static JSONObject getJsonObject(String token) {

        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(KEY.getBytes());

        JSONObject payload = jwt.getPayloads();
        payload.remove(JWTPayload.ISSUED_AT);
        payload.remove(JWTPayload.EXPIRES_AT);
        payload.remove(JWTPayload.NOT_BEFORE);

        LOG.info("Json payload: {}", payload);

        return payload;


    }

}
