package org.jack.train.member.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerQueryRequest {
    private Long memberId;
}
