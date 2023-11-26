package org.jack.common.context;

import org.jack.common.response.MemberLoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginMemberContext {

    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberContext.class);

    private static ThreadLocal<MemberLoginResp> threadLocalMember = new ThreadLocal<>();

    public static MemberLoginResp getThreadLocalMember() { return threadLocalMember.get();}


    public static void setThreadLocalMember(MemberLoginResp threadLocalMember) {LoginMemberContext.threadLocalMember.set(threadLocalMember);}

    public static Long getId() {
        try{
            return threadLocalMember.get().getId();
        } catch(Exception e) {
            LOG.error("get member id error: {}", e );
            throw e;
        }

    }
}
