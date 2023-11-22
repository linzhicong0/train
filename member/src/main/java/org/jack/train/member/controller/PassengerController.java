package org.jack.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.jack.common.response.Response;
import org.jack.train.member.request.PassengerSaveRequest;
import org.jack.train.member.service.PassengerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
