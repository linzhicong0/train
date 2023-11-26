package org.jack.train.member.response;

import lombok.Data;

@Data
public class PassengerQueryResp {

    private Long id;

    private Long memberId;

    private String name;

    private String idCard;

    private String type;
}
