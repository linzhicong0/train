package org.jack.train.member.service;

import org.jack.train.member.domain.Member;
import org.jack.train.member.domain.MemberExample;
import org.jack.train.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public int count() {

        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String mobile) {
        // check if the mobile already existed
        var memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        if (memberMapper.countByExample(memberExample) > 0) {
            throw new RuntimeException("mobile already existed");
        }


        // not exist then create the user with mobile
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }
}
