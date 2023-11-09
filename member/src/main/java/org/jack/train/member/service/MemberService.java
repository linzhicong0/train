package org.jack.train.member.service;

import org.jack.common.exception.BusinessException;
import org.jack.common.exception.BusinessExceptionEnum;
import org.jack.common.util.SnowFlakeUtil;
import org.jack.train.member.domain.Member;
import org.jack.train.member.domain.MemberExample;
import org.jack.train.member.mapper.MemberMapper;
import org.jack.train.member.request.MemberRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

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
}
