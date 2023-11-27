package org.jack.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.jack.common.context.LoginMemberContext;
import org.jack.common.response.PaginationResponse;
import org.jack.common.util.SnowFlakeUtil;
import org.jack.train.member.domain.Passenger;
import org.jack.train.member.domain.PassengerExample;
import org.jack.train.member.mapper.PassengerMapper;
import org.jack.train.member.request.PassengerDeleteRequest;
import org.jack.train.member.request.PassengerQueryRequest;
import org.jack.train.member.request.PassengerSaveRequest;
import org.jack.train.member.request.PassengerUpdateRequest;
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


    public void update(PassengerUpdateRequest request) {

        Passenger passenger = BeanUtil.copyProperties(request, Passenger.class);

        DateTime now = DateTime.now();
        passenger.setUpdateTime(now);

        passengerMapper.updateByPrimaryKey(passenger);
    }

    public PaginationResponse<PassengerQueryResp> getList(PassengerQueryRequest request) {
        PassengerExample pe = new PassengerExample();
        pe.createCriteria().andMemberIdEqualTo(request.getMemberId());

        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<Passenger> passengers = passengerMapper.selectByExample(pe);
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengers);

        var list = BeanUtil.copyToList(passengers, PassengerQueryResp.class);

        PaginationResponse<PassengerQueryResp> response = new PaginationResponse<>();
        response.setTotal(pageInfo.getTotal());
        response.setList(list);
        response.setPageNo(pageInfo.getPageNum());
        response.setPageSize(pageInfo.getPageSize());

         return response;
    }

    public void delete(PassengerDeleteRequest request) {
        // check if the id of passenger exist

        PassengerExample ex = new PassengerExample();
        ex.createCriteria().andIdEqualTo(Long.parseLong( request.getId()));

        if(passengerMapper.countByExample(ex) == 1) {
            passengerMapper.deleteByPrimaryKey(Long.parseLong(request.getId()));
        }




    }
}
