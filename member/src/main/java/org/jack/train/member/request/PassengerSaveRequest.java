package org.jack.train.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PassengerSaveRequest {

    private Long memberId;

    @NotBlank(message = "[姓名] 不能为空")
    private String name;

    @NotBlank(message = "[身份证号] 不能为空")
    private String idCard;

    @NotBlank(message = "[旅客类型] 不能为空")
    private String type;

}
