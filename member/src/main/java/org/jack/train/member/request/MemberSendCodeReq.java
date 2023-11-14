package org.jack.train.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberSendCodeReq {

    @NotBlank(message = "mobile can not be empty")
    @Pattern(regexp = "1\\d{10}$", message = "invalid phone number")
    private String mobile;
}
