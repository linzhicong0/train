package org.jack.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import org.jack.common.exception.BusinessException;
import org.jack.common.exception.BusinessExceptionEnum;
import org.jack.common.util.SnowFlakeUtil;
import org.jack.train.member.domain.Member;
import org.jack.train.member.domain.MemberExample;
import org.jack.train.member.mapper.MemberMapper;
import org.jack.train.member.request.MemberRegisterRequest;
import org.jack.train.member.request.MemberSendCodeReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberMapper memberMapper;

    public int count() {

        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterRequest request) {
        String mobile = request.getMobile();

        // check if the mobile already existed
        var memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        if (memberMapper.countByExample(memberExample) > 0) {
//            throw new RuntimeException("mobile already existed");
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        // not exist then create the user with mobile
        Member member = new Member();
        member.setId(SnowFlakeUtil.getSnowFlakeId());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {

        String mobile = req.getMobile();

        // check if the mobile already existed
        var memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        List<Member> list = memberMapper.selectByExample(memberExample);

        if (CollUtil.isEmpty(list)) {
            LOG.info("mobile does not exist, insert a record");
            Member member = new Member();
            member.setId(SnowFlakeUtil.getSnowFlakeId());
            member.setMobile(req.getMobile());

            memberMapper.insert(member);
        } else {
            LOG.info("mobile exists");
        }

        // create code

        String code = RandomUtil.randomString(4);
        LOG.info("verification code: {}", code);

        //TODO: save the code: mobile, code, expired date, is verified, business type, send time, verified time
        //TODO: integrate with msg api

    }
}
