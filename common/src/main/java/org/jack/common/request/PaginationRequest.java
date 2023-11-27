package org.jack.common.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PaginationRequest {
    @Min(value = 0, message = "page NO. can not be less than 0")
    private int pageNo = 0;
    @Max(value = 100, message = "page size can not be greater than 100")
    private int pageSize = 5;
}
