package org.jack.train.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PassengerDeleteRequest {

    @NotBlank(message = "[id] can not be null")
    private String id;
}
