package org.jack.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.jack.common.context.LoginMemberContext;
import org.jack.common.response.PaginationResponse;
import org.jack.common.response.Response;
import org.jack.train.member.request.PassengerQueryRequest;
import org.jack.train.member.request.PassengerSaveRequest;
import org.jack.train.member.request.PassengerUpdateRequest;
import org.jack.train.member.response.PassengerQueryResp;
import org.jack.train.member.service.PassengerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public Response save(@Valid @RequestBody PassengerSaveRequest request) {

        passengerService.save(request);

        return Response.success();

    }

    @PutMapping("save")
    public Response update(@Valid @RequestBody PassengerUpdateRequest request) {

        passengerService.update(request);

        return Response.success();

    }


    @GetMapping("/list")
    public Response<PaginationResponse<PassengerQueryResp>> getList(@Valid PassengerQueryRequest request) {

        request.setMemberId(LoginMemberContext.getId());

        return Response.success(passengerService.getList(request));
    }
}
