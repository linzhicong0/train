package org.jack.common.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginResp {

    private Long id;

    private String mobile;

    private String accessToken;

}
