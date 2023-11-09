package org.jack.train.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRegisterRequest {

    @NotBlank(message = "mobile can not be empty")
    private String mobile;
}
