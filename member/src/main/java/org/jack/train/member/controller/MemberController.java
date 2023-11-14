package org.jack.train.member.controller;

import jakarta.validation.Valid;
import org.jack.common.response.Response;
import org.jack.train.member.request.MemberRegisterRequest;
import org.jack.train.member.request.MemberSendCodeReq;
import org.jack.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public Response<Integer> count() {
        return Response.success(memberService.count());
    }

    @PostMapping("/register")
    public Response<Long> register(
            @RequestBody
            @Valid
            MemberRegisterRequest request
    ) {
        return Response.success(memberService.register(request));
    }

    @PostMapping("/sendCode")
    public Response<Long> sendCode(
            @RequestBody
            @Valid
            MemberSendCodeReq request
    ) {

        memberService.sendCode(request);
        return Response.success();
    }
}
