package org.jack.train.member.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginResp {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String mobile;

    private String accessToken;

}
