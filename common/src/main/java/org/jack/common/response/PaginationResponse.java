package org.jack.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PaginationResponse<T> implements Serializable {

    private long total;

    private int pageNo;

    private int pageSize;

    private List<T> list;

}
