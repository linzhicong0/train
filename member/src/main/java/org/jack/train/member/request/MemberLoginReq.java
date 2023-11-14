package org.jack.train.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberLoginReq {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "1\\d{10}$", message = "请输入正确的手机号码")
    private String mobile;

    @NotBlank
    private String verificationCode;

}
