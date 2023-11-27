package org.jack.train.member.request;

import lombok.Builder;
import lombok.Data;
import org.jack.common.request.PaginationRequest;

@Data
@Builder
public class PassengerQueryRequest extends PaginationRequest {
    private Long memberId;
}
