package org.jack.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;
import org.jack.common.context.LoginMemberContext;
import org.jack.common.util.SnowFlakeUtil;
import org.jack.train.member.domain.Passenger;
import org.jack.train.member.domain.PassengerExample;
import org.jack.train.member.mapper.PassengerMapper;
import org.jack.train.member.request.PassengerQueryRequest;
import org.jack.train.member.request.PassengerSaveRequest;
import org.jack.train.member.response.PassengerQueryResp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveRequest request) {

        Passenger passenger = BeanUtil.copyProperties(request, Passenger.class);
        passenger.setId(SnowFlakeUtil.getSnowFlakeId());

        DateTime now = DateTime.now();
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);

        passenger.setMemberId(LoginMemberContext.getId());

        passengerMapper.insert(passenger);
    }


    public List<PassengerQueryResp> getList(PassengerQueryRequest request) {
        PassengerExample pe = new PassengerExample();
        pe.createCriteria().andMemberIdEqualTo(request.getMemberId());
        List<Passenger> passengers = passengerMapper.selectByExample(pe);

        return BeanUtil.copyToList(passengers, PassengerQueryResp.class);
    }
}
